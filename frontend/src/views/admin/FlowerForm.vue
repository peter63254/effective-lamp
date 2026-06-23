<template>
  <div class="flower-form">
    <el-card>
      <template #header>
        <div class="form-header">
          <span class="form-title">{{ isEdit ? '编辑花卉' : '新增花卉' }}</span>
          <el-button @click="$router.push('/admin/flowers')">返回</el-button>
        </div>
      </template>

      <el-form
        ref="formRef"
        :model="form"
        :rules="formRules"
        label-width="120px"
        v-loading="pageLoading"
      >
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name" placeholder="花卉名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分类" prop="categoryId">
              <el-select v-model="form.categoryId" placeholder="选择分类" clearable style="width: 100%">
                <el-option
                  v-for="cat in categories"
                  :key="cat.id"
                  :label="cat.name"
                  :value="cat.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="价格" prop="price">
              <el-input-number
                v-model="form.price"
                :min="0"
                :precision="2"
                :step="10"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库存状态" prop="stockStatus">
              <el-select v-model="form.stockStatus" style="width: 100%">
                <el-option label="有货" value="IN_STOCK" />
                <el-option label="库存紧张" value="LOW_STOCK" />
                <el-option label="缺货" value="OUT_OF_STOCK" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="颜色">
              <el-input v-model="form.color" placeholder="例如：红、白、粉" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="花语">
              <el-input v-model="form.language" placeholder="例如：爱情与浪漫" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="图片链接">
          <el-input v-model="form.imageUrl" placeholder="图片链接（选填）" />
        </el-form-item>

        <el-form-item label="描述" prop="description">
          <el-input
            v-model="form.description"
            type="textarea"
            :rows="4"
            placeholder="花卉描述"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" :loading="saving" @click="handleSave">
            {{ isEdit ? '更新' : '创建' }}
          </el-button>
          <el-button @click="$router.push('/admin/flowers')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getFlowerById, createFlower, updateFlower } from '@/api/flowers'
import { getCategories } from '@/api/categories'
import type { Category } from '@/types'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()
const formRef = ref()
const categories = ref<Category[]>([])
const pageLoading = ref(false)
const saving = ref(false)

const isEdit = computed(() => !!route.params.id)

const form = reactive({
  name: '',
  description: '',
  price: 0,
  imageUrl: '',
  stockStatus: 'IN_STOCK',
  categoryId: null as number | null,
  color: '',
  language: ''
})

const formRules = {
  name: [{ required: true, message: '请输入花卉名称', trigger: 'blur' }],
  price: [{ required: true, message: '请输入价格', trigger: 'blur' }],
  description: [{ required: true, message: '请输入描述', trigger: 'blur' }]
}

async function loadCategories() {
  try {
    const res = await getCategories()
    categories.value = res.data
  } catch (err) {
    console.error('Failed to load categories:', err)
  }
}

async function loadFlower() {
  if (!isEdit.value) return
  pageLoading.value = true
  try {
    const id = Number(route.params.id)
    const res = await getFlowerById(id)
    const flower = res.data
    form.name = flower.name
    form.description = flower.description
    form.price = flower.price
    form.imageUrl = flower.imageUrl
    form.stockStatus = flower.stockStatus
    form.categoryId = flower.category?.id
    form.color = flower.color || ''
    form.language = flower.language || ''
  } catch (err) {
    ElMessage.error('加载花卉失败')
    router.push('/admin/flowers')
  } finally {
    pageLoading.value = false
  }
}

async function handleSave() {
  const valid = await formRef.value.validate().catch(() => false)
  if (!valid) return
  saving.value = true
  try {
    const data = {
      name: form.name,
      description: form.description,
      price: form.price,
      imageUrl: form.imageUrl,
      stockStatus: form.stockStatus,
      categoryId: form.categoryId,
      color: form.color,
      language: form.language
    }

    let res
    if (isEdit.value) {
      res = await updateFlower(Number(route.params.id), data)
    } else {
      res = await createFlower(data)
    }

    if (res.data.success) {
      ElMessage.success(isEdit.value ? '花卉已更新' : '花卉已创建')
      router.push('/admin/flowers')
    } else {
      ElMessage.error(res.data.message)
    }
  } catch (err) {
    ElMessage.error('操作失败')
  } finally {
    saving.value = false
  }
}

onMounted(() => {
  loadCategories()
  loadFlower()
})
</script>

<style scoped>
.form-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.form-title {
  font-size: 18px;
  font-weight: bold;
}
</style>


