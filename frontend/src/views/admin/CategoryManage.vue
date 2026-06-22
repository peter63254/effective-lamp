<template>
  <div class="category-manage">
    <el-card>
      <template #header>
        <div class="manage-header">
          <span class="manage-title">Category Management</span>
          <el-button type="primary" @click="showAddDialog = true">
            <el-icon><Plus /></el-icon>
            Add Category
          </el-button>
        </div>
      </template>

      <el-table :data="categories" v-loading="loading" stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="60" />
        <el-table-column prop="name" label="Name" min-width="150" />
        <el-table-column prop="description" label="Description" min-width="250">
          <template #default="{ row }">
            <span class="desc-text">{{ row.description || '-' }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="sortOrder" label="Sort Order" width="100" />
        <el-table-column label="Actions" width="160" fixed="right">
          <template #default="{ row }">
            <el-button size="small" type="primary" link @click="editCategory(row)">Edit</el-button>
            <el-popconfirm
              title="Delete this category?"
              confirm-button-text="Delete"
              @confirm="handleDelete(row.id)"
            >
              <template #reference>
                <el-button size="small" type="danger" link>Delete</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- Add/Edit Dialog -->
    <el-dialog
      v-model="dialogVisible"
      :title="editingCategory ? 'Edit Category' : 'Add Category'"
      width="500px"
    >
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="Name" prop="name">
          <el-input v-model="form.name" placeholder="Category name" />
        </el-form-item>
        <el-form-item label="Description">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="Description (optional)" />
        </el-form-item>
        <el-form-item label="Sort Order">
          <el-input-number v-model="form.sortOrder" :min="0" :max="999" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" :loading="saving" @click="handleSave">Save</el-button>
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

const rules = {
  name: [{ required: true, message: 'Please enter category name', trigger: 'blur' }]
}

async function loadCategories() {
  loading.value = true
  try {
    const res = await getCategories()
    categories.value = res.data
  } catch (err) {
    console.error('Failed to load categories:', err)
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

async function handleSave() {
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
      ElMessage.success(editingCategory.value ? 'Category updated' : 'Category created')
      dialogVisible.value = false
      resetForm()
      await loadCategories()
    } else {
      ElMessage.error(res.data.message)
    }
  } catch (err) {
    ElMessage.error('Operation failed')
  } finally {
    saving.value = false
  }
}

async function handleDelete(id: number) {
  try {
    const res = await deleteCategory(id)
    if (res.data.success) {
      ElMessage.success('Category deleted')
      await loadCategories()
    } else {
      ElMessage.error(res.data.message)
    }
  } catch (err) {
    ElMessage.error('Failed to delete category')
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
