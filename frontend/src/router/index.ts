import { createRouter, createWebHistory } from 'vue-router'
import { getCurrentUser } from '@/api'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: () => import('@/views/Home.vue')
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/Login.vue')
    },
    {
      path: '/register',
      name: 'Register',
      component: () => import('@/views/Register.vue')
    },
    {
      path: '/flowers/:id',
      name: 'FlowerDetail',
      component: () => import('@/views/FlowerDetail.vue')
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import('@/views/admin/AdminDashboard.vue'),
      meta: { requiresAuth: true, requiresAdmin: true },
      children: [
        {
          path: '',
          redirect: { name: 'FlowerManage' }
        },
        {
          path: 'flowers',
          name: 'FlowerManage',
          component: () => import('@/views/admin/FlowerManage.vue')
        },
        {
          path: 'flowers/new',
          name: 'FlowerCreate',
          component: () => import('@/views/admin/FlowerForm.vue')
        },
        {
          path: 'flowers/:id/edit',
          name: 'FlowerEdit',
          component: () => import('@/views/admin/FlowerForm.vue')
        },
        {
          path: 'categories',
          name: 'CategoryManage',
          component: () => import('@/views/admin/CategoryManage.vue')
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  const user = getCurrentUser()
  if (to.meta.requiresAuth && !user) {
    next({ name: 'Login', query: { redirect: to.fullPath } })
  } else if (to.meta.requiresAdmin && user?.role !== 'ADMIN') {
    next({ name: 'Home' })
  } else {
    next()
  }
})

export default router
