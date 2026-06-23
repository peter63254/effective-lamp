<template>
  <div class="flower-detail" v-loading="loading">
    <el-button class="back-btn" text @click="$router.push('/')">
      <el-icon><ArrowLeft /></el-icon>
      返回列表
    </el-button>

    <div v-if="flower" class="detail-content">
      <el-row :gutter="40">
        <el-col :xs="24" :md="10">
          <div class="detail-image-wrapper">
            <el-image
              :src="flower.imageUrl"
              :alt="flower.name"
              fit="cover"
              class="detail-image"
            >
              <template #error>
                <div class="image-placeholder-large">
                  <el-icon :size="80"><Flower /></el-icon>
                </div>
              </template>
            </el-image>
          </div>
        </el-col>
        <el-col :xs="24" :md="14">
          <div class="detail-info">
            <h1 class="detail-name">{{ flower.name }}</h1>
            <el-tag
              :type="getStockType(flower.stockStatus)"
              size="medium"
              class="detail-stock"
            >
              {{ getStockLabel(flower.stockStatus) }}
            </el-tag>

            <p class="detail-price">
              <span class="price-label">价格：</span>
              <span class="price-value-detail">¥{{ flower.price.toFixed(2) }}</span>
            </p>

            <el-divider />

            <div class="detail-attribute" v-if="flower.category">
              <span class="attribute-label">分类：</span>
              <el-tag>{{ flower.category.name }}</el-tag>
            </div>

            <div class="detail-attribute" v-if="flower.color">
              <span class="attribute-label">颜色：</span>
              <span>{{ flower.color }}</span>
            </div>

            <div class="detail-attribute" v-if="flower.language">
              <span class="attribute-label">花语：</span>
              <span class="language-text">{{ flower.language }}</span>
            </div>

            <el-divider />

            <div class="detail-description" v-if="flower.description">
              <h3>描述</h3>
              <p>{{ flower.description }}</p>
            </div>

            <el-divider />

            <div class="detail-meta">
              <p class="meta-text">创建时间： {{ formatDate(flower.createdAt) }}</p>
              <p class="meta-text" v-if="flower.updatedAt !== flower.createdAt">
                最后更新： {{ formatDate(flower.updatedAt) }}
              </p>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

    <div v-else-if="!loading">
      <el-empty description="未找到该花卉" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getFlowerById } from '@/api/flowers'
import type { Flower } from '@/types'
import { ArrowLeft } from '@element-plus/icons-vue'

const route = useRoute()
const flower = ref<Flower | null>(null)
const loading = ref(true)

function getStockLabel(status: string) {
  const map: Record<string, string> = { IN_STOCK: '有货', LOW_STOCK: '库存紧张', OUT_OF_STOCK: '缺货' }
  return map[status] || status
}

function getStockType(status: string) {
  const map: Record<string, string> = { IN_STOCK: 'success', LOW_STOCK: 'warning', OUT_OF_STOCK: 'danger' }
  return map[status] || 'info'
}

function formatDate(dateStr: string) {
  if (!dateStr) return ''
  return new Date(dateStr).toLocaleDateString('zh-CN', {
    year: 'numeric', month: 'long', day: 'numeric'
  })
}

async function loadFlower() {
  loading.value = true
  try {
    const id = Number(route.params.id)
    const res = await getFlowerById(id)
    flower.value = res.data
  } catch (err) {
    console.error('Failed to load flower:', err)
  } finally {
    loading.value = false
  }
}

onMounted(loadFlower)
</script>

<style scoped>
.flower-detail {
  padding: 20px 0;
}

.back-btn {
  margin-bottom: 20px;
}

.detail-image-wrapper {
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.detail-image {
  width: 100%;
  height: 400px;
}

.image-placeholder-large {
  width: 100%;
  height: 400px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #e0e7ff 0%, #f0e6ff 100%);
  color: #764ba2;
}

.detail-name {
  font-size: 32px;
  margin-bottom: 12px;
  color: #303133;
}

.detail-stock {
  margin-bottom: 16px;
}

.detail-price {
  margin: 16px 0;
}

.price-label {
  font-size: 16px;
  color: #606266;
  margin-right: 8px;
}

.price-value-detail {
  font-size: 28px;
  color: #e63946;
  font-weight: bold;
}

.detail-attribute {
  margin: 12px 0;
  display: flex;
  align-items: center;
  gap: 8px;
}

.attribute-label {
  font-size: 15px;
  color: #606266;
  font-weight: 500;
  min-width: 100px;
}

.language-text {
  color: #764ba2;
  font-style: italic;
}

.detail-description h3 {
  font-size: 18px;
  margin-bottom: 8px;
  color: #303133;
}

.detail-description p {
  font-size: 15px;
  color: #606266;
  line-height: 1.6;
}

.detail-meta {
  color: #909399;
  font-size: 13px;
}

.meta-text {
  margin: 4px 0;
}
</style>

