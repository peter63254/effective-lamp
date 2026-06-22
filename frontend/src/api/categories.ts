import api from './index'
import type { Category, ApiResponse } from '@/types'

export function getCategories() {
  return api.get<Category[]>('/categories')
}

export function createCategory(data: { name: string; description?: string; sortOrder?: number }) {
  return api.post<ApiResponse<Category>>('/admin/categories', data)
}

export function updateCategory(id: number, data: { name: string; description?: string; sortOrder?: number }) {
  return api.put<ApiResponse<Category>>(`/admin/categories/${id}`, data)
}

export function deleteCategory(id: number) {
  return api.delete<ApiResponse<void>>(`/admin/categories/${id}`)
}
