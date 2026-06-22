<template>
  <el-menu
    :default-active="route.path"
    mode="horizontal"
    :ellipsis="false"
    class="navbar"
    @select="handleSelect"
  >
    <el-menu-item index="/">
      <el-icon><Flower /></el-icon>
      <span class="brand">Flower Shop</span>
    </el-menu-item>

    <div class="flex-grow" />

    <el-menu-item index="/">
      <el-icon><HomeFilled /></el-icon>
      <span>Home</span>
    </el-menu-item>

    <el-menu-item index="/#flowers">
      <el-icon><Goods /></el-icon>
      <span>Flowers</span>
    </el-menu-item>

    <template v-if="authStore.isLoggedIn">
      <el-sub-menu index="user" v-if="authStore.isAdmin">
        <template #title>
          <el-icon><Setting /></el-icon>
          <span>Admin</span>
        </template>
        <el-menu-item index="/admin/flowers">
          <el-icon><List /></el-icon>
          <span>Manage Flowers</span>
        </el-menu-item>
        <el-menu-item index="/admin/categories">
          <el-icon><Collection /></el-icon>
          <span>Manage Categories</span>
        </el-menu-item>
      </el-sub-menu>

      <el-sub-menu index="profile">
        <template #title>
          <el-icon><UserFilled /></el-icon>
          <span>{{ authStore.user?.username }}</span>
        </template>
        <el-menu-item index="logout" @click="handleLogout">
          <el-icon><SwitchButton /></el-icon>
          <span>Logout</span>
        </el-menu-item>
      </el-sub-menu>
    </template>

    <template v-else>
      <el-menu-item index="/login">
        <el-icon><User /></el-icon>
        <span>Login</span>
      </el-menu-item>
      <el-menu-item index="/register">
        <el-icon><UserPlus /></el-icon>
        <span>Register</span>
      </el-menu-item>
    </template>
  </el-menu>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()

function handleSelect(index: string) {
  if (index === 'logout') {
    handleLogout()
  } else if (index.startsWith('/')) {
    router.push(index)
  }
}

function handleLogout() {
  authStore.logout()
  router.push('/')
}
</script>

<style scoped>
.navbar {
  padding: 0 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-bottom: none;
}

.navbar .el-menu-item,
.navbar .el-sub-menu__title {
  color: rgba(255, 255, 255, 0.9) !important;
}

.navbar .el-menu-item:hover,
.navbar .el-sub-menu__title:hover {
  background-color: rgba(255, 255, 255, 0.15) !important;
  color: #fff !important;
}

.navbar .el-menu-item.is-active {
  color: #fff !important;
  border-bottom-color: #fff !important;
  background-color: rgba(255, 255, 255, 0.1) !important;
}

.flex-grow {
  flex-grow: 1;
}

.brand {
  font-size: 18px;
  font-weight: bold;
  margin-left: 8px;
}
</style>
