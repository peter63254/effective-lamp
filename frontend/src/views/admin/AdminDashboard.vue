<template>
  <div class="admin-layout">
    <el-row :gutter="20">
      <el-col :span="4">
        <el-card class="admin-sidebar">
          <template #header>
            <span class="sidebar-title">管理后台</span>
          </template>
          <el-menu
            :default-active="route.path"
            router
            class="admin-menu"
            :collapse="false"
          >
            <el-menu-item index="/admin/flowers">
              <el-icon><Flower /></el-icon>
              <span>花卉管理</span>
            </el-menu-item>
            <el-menu-item index="/admin/categories">
              <el-icon><Collection /></el-icon>
              <span>分类管理</span>
            </el-menu-item>
          </el-menu>
        </el-card>
      </el-col>
      <el-col :span="20">
        <div class="admin-content">
          <router-view />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'

const route = useRoute()
</script>

<style scoped>
.admin-layout {
  min-height: 70vh;
}

.admin-sidebar {
  border-radius: 12px;
}

.sidebar-title {
  font-weight: bold;
  font-size: 16px;
}

.admin-menu {
  border-right: none;
}

.admin-content {
  min-height: 70vh;
}
</style>
