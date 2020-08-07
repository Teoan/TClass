import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home'

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
    component: Home,
    redirect: '/homeInfo',
    children: [{
      path: '/homeInfo',
      name: 'homeInfo',
      component: () => import('@/views/home/homeInfo'),
      meta: { title: '首页', icon: 'fas fa-home', roles: ['ROLE_admin', 'ROLE_student'] }
    }]
  },
  {
    path: '/home',
    name: '',
    component: Home,
    meta: { title: '个人信息', icon: 'fas fa-info-circle', roles: ['ROLE_admin', 'ROLE_student'] },
    children: [{
      path: '/userinfo',
      name: 'userInfo',
      component: () => import('@/views/user/userInfo'),
      meta: { title: '个人信息', icon: 'fas fa-address-card', roles: ['ROLE_admin', 'ROLE_student'] }
    }]
  },
  {
    path: '/home',
    name: '',
    component: Home,
    meta: { title: '用户', icon: 'el-icon-custom', roles: ['ROLE_admin'] },
    children: [{
      path: '/user',
      name: 'student',
      component: () => import('@/views/user/index'),
      meta: { title: '学生管理', icon: 'fas fa-user-graduate' }
    }]
  },
  {
    path: '/home',
    name: '',
    component: Home,
    meta: { title: '通知', icon: 'fas fa-comment-alt', roles: ['ROLE_admin', 'ROLE_student'] },
    children: [{
      path: '/natice',
      name: 'natice',
      component: () => import('@/views/natice/index'),
      meta: { title: '查看通知' }
    }, {
      path: '/naticemana',
      name: 'naticeMana',
      component: () => import('@/views/natice/naticeMana'),
      meta: { title: '通知管理', roles: ['ROLE_admin'] }
    }]
  },
  {
    path: '/home',
    name: '',
    component: Home,
    meta: { title: '作业', icon: 'fas fa-calendar-alt', roles: ['ROLE_admin', 'ROLE_student'] },
    children: [{
      path: '/work',
      name: 'work',
      component: () => import('@/views/work/index'),
      meta: { title: '查看作业' }
    }, {
      path: '/workmana',
      name: 'workManae',
      component: () => import('@/views/work/workMana'),
      meta: { title: '作业管理', roles: ['ROLE_admin'] }
    }]
  }
]

// 解决路由重复错误
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  routes
})

export default router
