import axios from 'axios'

const api = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// Store user info in session storage
export function setUser(user: any) {
  sessionStorage.setItem('currentUser', JSON.stringify(user))
}

export function getCurrentUser(): any | null {
  const user = sessionStorage.getItem('currentUser')
  return user ? JSON.parse(user) : null
}

export function clearUser() {
  sessionStorage.removeItem('currentUser')
}

export default api
