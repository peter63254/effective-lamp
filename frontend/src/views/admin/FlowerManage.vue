<template>
  <div class="flower-manage">
    <el-card>
      <template #header>
        <div class="manage-header">
          <span class="manage-title">花卉管理</span>
          <el-button type="primary" @click="$router.push('/admin/flowers/new')">
            <el-icon><Plus /></el-icon>
            新增花卉
          </el-button>
        </div>
      </template>

      <div class="manage-toolbar">
        <el-select v-model="filterCategory" placeholder="按分类筛选" clearable style="width: 200px">
          <el-option
            v-for="cat in categories"
            :key="cat.id"
            :label="cat.name"
            :value="cat.id"
          />
        </el-select>
        <el-input
          v-model="filterKeyword"
          placeholder="搜索..."
          clearable
          style="width: 200px"
          @keyup.enter="loadFlowers"
        />
        <el-button type="primary" @click="loadFlowers">搜索</el-button>
      </div>

      <el-table
        :data="flowers"
        v-loading="loading"
        stripe
        style="width: 100%"
        @sort-change="handleSortChange"
      >
        <el-table-column prop="name" label="名称" min-width="140" />
        <el-table-column label="分类" width="120">
          <template #default="{ row }">
            <el-tag size="small">{{ row.category?.name || '-' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="价格" width="100" sortable="custom">
          <template #default="{ row }">
            <span class="price-cell">¥{{ row.price.toFixed(2) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="stockStatus" label="库存状态" width="130">
          <template #default="{ row }">
            <el-tag
              :type="getStockType(row.stockStatus)"
              size="small"
            >
              {{ getStockLabel(row.stockStatus) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="库存操作" width="150">
          <template #default="{ row }">
            <el-select
              :model-value="row.stockStatus"
              size="small"
              @change="(val: string) => updateStock(row.id, val)"
            >
              <el-option label="有货" value="IN_STOCK" />
              <el-option label="库存紧张" value="LOW_STOCK" />
              <el-option label="缺货" value="OUT_OF_STOCK" />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prop="color" label="颜色" width="100" />
        <el-table-column label="操作" width="160" fixed="right">
          <template #default="{ row }">
            <el-button
              size="small"
              type="primary"
              link
              @click="$router.push(`/admin/flowers/${row.id}/edit`)"
            >
              Edit
            </el-button>
            <el-popconfirm
              title="确定删除此花卉？"
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
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getFlowers, deleteFlower, updateStockStatus } from '@/api/flowers'
import { getCategories } from '@/api/categories'
import type { Flower, Category } from '@/types'
import { ElMessage } from 'element-plus'

const flowers = ref<Flower[]>([])
const categories = ref<Category[]>([])
const loading = ref(false)
const filterCategory = ref<number | undefined>(undefined)
const filterKeyword = ref('')

function getStockLabel(status: string) {
  const map: Record<string, string> = { IN_STOCK: '有货', LOW_STOCK: '库存紧张', OUT_OF_STOCK: '缺货' }
  return map[status] || status
}

function getStockType(status: string) {
  const map: Record<string, string> = { IN_STOCK: 'success', LOW_STOCK: 'warning', OUT_OF_STOCK: 'danger' }
  return map[status] || 'info'
}

async function loadFlowers() {
  loading.value = true
  try {
    const res = await getFlowers(filterCategory.value, filterKeyword.value)
    flowers.value = res.data
  } catch (err) {
    console.error('Failed to load flowers:', err)
  } finally {
    loading.value = false
  }
}

async function loadCategories() {
  try {
    const res = await getCategories()
    categories.value = res.data
  } catch (err) {
    console.error('Failed to load categories:', err)
  }
}

async function updateStock(id: number, status: string) {
  try {
    const res = await updateStockStatus(id, status)
    if (res.data.success) {
      ElMessage.success('库存状态已更新')
      await loadFlowers()
    }
  } catch (err) {
    ElMessage.error('更新库存失败')
  }
}

async function handleDelete(id: number) {
  try {
    const res = await deleteFlower(id)
    if (res.data.success) {
      ElMessage.success('花卉已删除')
      await loadFlowers()
    }
  } catch (err) {
    ElMessage.error('删除花卉失败')
  }
}

function handleSortChange(sort: any) {
  if (sort.prop === 'price') {
    flowers.value.sort((a, b) => sort.order === 'ascending' ? a.price - b.price : b.price - a.price)
  }
}

onMounted(() => {
  loadFlowers()
  loadCategories()
})
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

.manage-toolbar {
  display: flex;
  gap: 12px;
  margin-bottom: 16px;
}

.price-cell {
  color: #e63946;
  font-weight: bold;
}
</style>


