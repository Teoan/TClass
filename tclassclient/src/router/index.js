import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/home',
    name: 'Home',
    component: () => import('@/views/Home')
  }
]

const router = new VueRouter({
  routes
})

export default router
