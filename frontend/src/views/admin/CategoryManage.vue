<template>
  <div class="category-manage">
    <el-card>
      <template #header>
        <div class="manage-header">
          <span class="manage-title">分类管理</span>
          <el-button type="primary" @click="dialogVisible = true">
            <el-icon><Plus /></el-icon>
            新增分类
          </el-button>
        </div>
      </template>

      <el-table :data="categories" v-loading="loading" stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="60" />
        <el-table-column prop="name" label="名称" min-width="150" />
        <el-table-column prop="description" label="Description" min-width="250">
          <template #default="{ row }">
            <span class="desc-text">{{ row.description || '-' }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="sortOrder" label="排序号" width="100" />
        <el-table-column label="操作" width="160" fixed="right">
          <template #default="{ row }">
            <el-button size="small" type="primary" link @click="editCategory(row)">编辑</el-button>
            <el-popconfirm
              title="确定删除此分类？"
              confirm-button-text="删除"
              @confirm="handleDelete(row.id)"
            >
              <template #reference>
                <el-button size="small" type="danger" link>删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- New/Edit Dialog -->
    <el-dialog
      v-model="dialogVisible"
      :title="editingCategory ? '编辑分类' : '新增分类'"
      width="500px"
    >
      <el-form ref="formRef" :model="form" :rules="formRules" label-width="100px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="分类名称" />
        </el-form-item>
        <el-form-item label="Description">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="描述（选填）" />
        </el-form-item>
        <el-form-item label="排序号">
          <el-input-number v-model="form.sortOrder" :min="0" :max="999" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="handle保存">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { getCategories, createCategory, updateCategory, deleteCategory } from '@/api/categories'
import type { Category } from '@/types'
import { ElMessage } from 'element-plus'

const categories = ref<Category[]>([])
const loading = ref(false)
const saving = ref(false)
const dialogVisible = ref(false)
const editingCategory = ref<Category | null>(null)
const formRef = ref()

const form = reactive({
  name: '',
  description: '',
  sortOrder: 0
})

const formRules = {
  name: [{ required: true, message: '请输入分类名称', trigger: 'blur' }]
}

async function loadCategories() {
  loading.value = true
  try {
    const res = await getCategories()
    categories.value = res.data
  } catch (err) {
    console.error('分类加载失败:', err)
  } finally {
    loading.value = false
  }
}

function editCategory(category: Category) {
  editingCategory.value = category
  form.name = category.name
  form.description = category.description || ''
  form.sortOrder = category.sortOrder
  dialogVisible.value = true
}

function resetForm() {
  editingCategory.value = null
  form.name = ''
  form.description = ''
  form.sortOrder = 0
}

async function handle保存() {
  const valid = await formRef.value.validate().catch(() => false)
  if (!valid) return
  saving.value = true
  try {
    const data = { name: form.name, description: form.description, sortOrder: form.sortOrder }
    let res
    if (editingCategory.value) {
      res = await updateCategory(editingCategory.value.id, data)
    } else {
      res = await createCategory(data)
    }
    if (res.data.success) {
      ElMessage.success(editingCategory.value ? '分类已更新' : '分类已创建')
      dialogVisible.value = false
      resetForm()
      await loadCategories()
    } else {
      ElMessage.error(res.data.message)
    }
  } catch (err) {
    ElMessage.error('操作失败')
  } finally {
    saving.value = false
  }
}

async function handleDelete(id: number) {
  try {
    const res = await deleteCategory(id)
    if (res.data.success) {
      ElMessage.success('分类已删除')
      await loadCategories()
    } else {
      ElMessage.error(res.data.message)
    }
  } catch (err) {
    ElMessage.error('删除分类失败')
  }
}

onMounted(loadCategories)
</script>

<style scoped>
.manage-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.manage-title {
  font-size: 18px;
  font-weight: bold;
}

.desc-text {
  color: #606266;
}
</style>


