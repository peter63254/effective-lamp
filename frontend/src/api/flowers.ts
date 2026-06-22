import api from './index'
import type { Flower, ApiResponse } from '@/types'

export function getFlowers(categoryId?: number, keyword?: string) {
  const params: any = {}
  if (categoryId) params.categoryId = categoryId
  if (keyword) params.keyword = keyword
  return api.get<Flower[]>('/flowers', { params })
}

export function getFlowerById(id: number) {
  return api.get<Flower>(`/flowers/${id}`)
}

export function createFlower(data: any) {
  return api.post<ApiResponse<Flower>>('/admin/flowers', data, { params: { categoryId: data.categoryId } })
}

export function updateFlower(id: number, data: any) {
  return api.put<ApiResponse<Flower>>(`/admin/flowers/${id}`, data, { params: { categoryId: data.categoryId } })
}

export function updateStockStatus(id: number, status: string) {
  return api.patch<ApiResponse<Flower>>(`/admin/flowers/${id}/stock`, { status })
}

export function deleteFlower(id: number) {
  return api.delete<ApiResponse<void>>(`/admin/flowers/${id}`)
}
