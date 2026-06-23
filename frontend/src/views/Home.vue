<template>
  <div class="home">
    <!-- Hero Section -->
    <div class="hero-section">
      <div class="hero-content">
        <h1>卉语卉坊</h1>
        <p>发现自然之美 — 浏览我们的新鲜花卉</p>
        <el-button type="primary" size="large" @click="$router.push('/login')" v-if="!authStore.isLoggedIn">
          开始探索
        </el-button>
      </div>
    </div>

    <!-- Category Filter -->
    <div class="filter-section" id="flowers">
      <el-row :gutter="20" class="filter-row">
        <el-col :span="12">
          <h2 class="section-title">花卉展示</h2>
        </el-col>
        <el-col :span="12" class="filter-controls">
          <el-select v-model="selectedCategory" placeholder="分类筛选" clearable @change="loadFlowers">
            <el-option
              v-for="cat in categories"
              :key="cat.id"
              :label="cat.name"
              :value="cat.id"
            />
          </el-select>
          <el-input
            v-model="keyword"
            placeholder="搜索花卉..."
            clearable
            class="search-input"
            @clear="loadFlowers"
            @keyup.enter="loadFlowers"
          >
            <template #prefix>
              <el-icon><Search /></el-icon>
            </template>
          </el-input>
          <el-button type="primary" @click="loadFlowers">
            <el-icon><Search /></el-icon>
            搜索
          </el-button>
        </el-col>
      </el-row>
    </div>

    <!-- Loading -->
    <div v-if="loading" class="loading-container">
      <el-skeleton :rows="3" animated />
    </div>

    <!-- Empty State -->
    <div v-else-if="flowers.length === 0" class="empty-container">
      <el-empty description="暂无花卉" />
    </div>

    <!-- Flower Grid -->
    <div v-else class="flower-grid">
      <el-row :gutter="20">
        <el-col
          v-for="flower in flowers"
          :key="flower.id"
          :xs="12"
          :sm="8"
          :md="6"
          :lg="4"
          class="flower-col"
        >
          <el-card
            :body-style="{ padding: '0px' }"
            shadow="hover"
            class="flower-card"
            @click="$router.push(`/flowers/${flower.id}`)"
          >
            <div class="flower-image-wrapper">
              <el-image
                :src="flower.imageUrl"
                :alt="flower.name"
                class="flower-image"
                fit="cover"
              >
                <template #error>
                  <div class="image-placeholder">
                    <el-icon :size="40"><Flower /></el-icon>
                  </div>
                </template>
              </el-image>
              <el-tag
                :type="getStockType(flower.stockStatus)"
                size="small"
                class="stock-tag"
              >
                {{ getStockLabel(flower.stockStatus) }}
              </el-tag>
            </div>
            <div class="flower-info">
              <h3 class="flower-name">{{ flower.name }}</h3>
              <p class="flower-language" v-if="flower.language">
                <el-icon><ChatDotRound /></el-icon>
                {{ flower.language }}
              </p>
              <p class="flower-price">
                <span class="price-symbol">¥</span>
                <span class="price-value">{{ flower.price.toFixed(2) }}</span>
              </p>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useAuthStore } from '@/stores/auth'
import { getFlowers } from '@/api/flowers'
import { getCategories } from '@/api/categories'
import type { Flower, Category } from '@/types'

const authStore = useAuthStore()
const flowers = ref<Flower[]>([])
const categories = ref<Category[]>([])
const selectedCategory = ref<number | undefined>(undefined)
const keyword = ref('')
const loading = ref(true)

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
    const res = await getFlowers(selectedCategory.value, keyword.value)
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

onMounted(() => {
  loadFlowers()
  loadCategories()
})
</script>

<style scoped>
.hero-section {
  background-image: url('/images/hero-bg.svg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border-radius: 16px;
  padding: 60px 40px;
  margin-bottom: 30px;
  text-align: center;
}

.hero-content h1 {
  font-size: 42px;
  margin-bottom: 12px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero-content p {
  font-size: 18px;
  color: #606266;
  margin-bottom: 20px;
}

.filter-section {
  margin-bottom: 24px;
}

.filter-row {
  display: flex;
  align-items: center;
}

.section-title {
  font-size: 24px;
  color: #303133;
}

.filter-controls {
  display: flex;
  gap: 12px;
  justify-content: flex-end;
  align-items: center;
}

.search-input {
  width: 200px;
}

.flower-grid {
  margin-top: 20px;
}

.flower-col {
  margin-bottom: 20px;
}

.flower-card {
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  border-radius: 12px;
  overflow: hidden;
}

.flower-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
}

.flower-image-wrapper {
  position: relative;
  width: 100%;
  height: 200px;
  overflow: hidden;
}

.flower-image {
  width: 100%;
  height: 100%;
}

.image-placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #e0e7ff 0%, #f0e6ff 100%);
  color: #764ba2;
}

.stock-tag {
  position: absolute;
  top: 8px;
  right: 8px;
}

.flower-info {
  padding: 14px;
}

.flower-name {
  font-size: 16px;
  margin-bottom: 6px;
  color: #303133;
}

.flower-language {
  font-size: 13px;
  color: #909399;
  margin-bottom: 8px;
}

.flower-price {
  color: #e63946;
  font-weight: bold;
}

.price-symbol {
  font-size: 14px;
}

.price-value {
  font-size: 20px;
}

.loading-container,
.empty-container {
  padding: 60px 0;
}
</style>
