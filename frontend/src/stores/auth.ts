import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import type { User } from '@/types'
import { getCurrentUser, setUser, clearUser } from '@/api'
import api from '@/api'
import type { ApiResponse as ApiResp } from '@/types'

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User | null>(getCurrentUser())

  const isLoggedIn = computed(() => user.value !== null)
  const isAdmin = computed(() => user.value?.role === 'ADMIN')

  async function login(username: string, password: string) {
    const res = await api.post<ApiResp<User>>('/auth/login', { username, password })
    if (res.data.success) {
      user.value = res.data.data
      setUser(res.data.data)
    }
    return res.data
  }

  async function register(data: { username: string; password: string; phone?: string; email?: string }) {
    const res = await api.post<ApiResp<User>>('/auth/register', data)
    return res.data
  }

  function logout() {
    user.value = null
    clearUser()
  }

  return { user, isLoggedIn, isAdmin, login, register, logout }
})
