<template>
  <div class="flower-form">
    <el-card>
      <template #header>
        <div class="form-header">
          <span class="form-title">{{ isEdit ? 'Edit Flower' : 'Add New Flower' }}</span>
          <el-button @click="$router.push('/admin/flowers')">Back</el-button>
        </div>
      </template>

      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="120px"
        v-loading="loading"
      >
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Name" prop="name">
              <el-input v-model="form.name" placeholder="Flower name" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Category" prop="categoryId">
              <el-select v-model="form.categoryId" placeholder="Select category" clearable style="width: 100%">
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
            <el-form-item label="Price" prop="price">
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
            <el-form-item label="Stock Status" prop="stockStatus">
              <el-select v-model="form.stockStatus" style="width: 100%">
                <el-option label="In Stock" value="IN_STOCK" />
                <el-option label="Low Stock" value="LOW_STOCK" />
                <el-option label="Out of Stock" value="OUT_OF_STOCK" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Color">
              <el-input v-model="form.color" placeholder="e.g., Red, White, Pink" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Flower Language">
              <el-input v-model="form.language" placeholder="e.g., Love and passion" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="Image URL">
          <el-input v-model="form.imageUrl" placeholder="Image URL (optional)" />
        </el-form-item>

        <el-form-item label="Description" prop="description">
          <el-input
            v-model="form.description"
            type="textarea"
            :rows="4"
            placeholder="Flower description"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" :loading="saving" @click="handleSave">
            {{ isEdit ? 'Update' : 'Create' }}
          </el-button>
          <el-button @click="$router.push('/admin/flowers')">Cancel</el-button>
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
const loading = ref(false)
const saving = ref(false)

const isEdit = computed(() => !!route.params.id)

const form = reactive({
  name: '',
  description: '',
  price: 0,
  imageUrl: '',
  stockStatus: 'IN_STOCK',
  categoryId: undefined as number | undefined,
  color: '',
  language: ''
})

const rules = {
  name: [{ required: true, message: 'Please enter flower name', trigger: 'blur' }],
  price: [{ required: true, message: 'Please enter price', trigger: 'blur' }],
  description: [{ required: true, message: 'Please enter description', trigger: 'blur' }]
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
  loading.value = true
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
    ElMessage.error('Failed to load flower')
    router.push('/admin/flowers')
  } finally {
    loading.value = false
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
      ElMessage.success(isEdit.value ? 'Flower updated' : 'Flower created')
      router.push('/admin/flowers')
    } else {
      ElMessage.error(res.data.message)
    }
  } catch (err) {
    ElMessage.error('Operation failed')
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
