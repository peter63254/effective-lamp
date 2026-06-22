export interface User {
  id: number
  username: string
  password?: string
  phone: string
  email: string
  role: 'USER' | 'ADMIN'
  createdAt: string
  updatedAt: string
}

export interface Category {
  id: number
  name: string
  description: string
  sortOrder: number
  createdAt: string
  updatedAt: string
}

export interface Flower {
  id: number
  name: string
  description: string
  price: number
  imageUrl: string
  stockStatus: 'IN_STOCK' | 'LOW_STOCK' | 'OUT_OF_STOCK'
  category: Category | null
  color: string
  language: string
  createdAt: string
  updatedAt: string
}

export interface ApiResponse<T> {
  success: boolean
  message: string
  data: T
}

export interface LoginRequest {
  username: string
  password: string
}

export interface RegisterRequest {
  username: string
  password: string
  phone?: string
  email?: string
}

export const STOCK_STATUS_MAP: Record<string, string> = {
  'IN_STOCK': '有货',
  'LOW_STOCK': '少量',
  'OUT_OF_STOCK': '缺货'
}

export const STOCK_STATUS_TYPE: Record<string, string> = {
  'IN_STOCK': 'success',
  'LOW_STOCK': 'warning',
  'OUT_OF_STOCK': 'danger'
}
