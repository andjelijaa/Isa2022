import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue')
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('../views/SignupView.vue')
  },
  {
    path: '/istorija',
    name: 'istorija',
    component: () => import('../views/IstorijaTermina.vue')
  },
  {
    path: '/zakazani',
    name: 'zakazani',
    component: () => import('../views/ZakazaniTermini.vue')
  },
  {
    path: '/profilKorisnika',
    name: 'profilKorisnika',
    component: () => import('../views/ProfilKorisnikaView.vue')
  },
  {
    path: '/upitnik',
    name: 'upitnik',
    component: () => import('../views/UpitnikView.vue')
  },
  {
    path: '/qr',
    name: 'qr',
    component: () => import('../views/QrView.vue')
  },
  {
    path: '/zalbe',
    name: 'zalbe',
    component: () => import('../views/ZalbeView.vue')
  },
  {
    path: '/centar/:id',
    name: 'centar',
    component: () => import('../views/CentarView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
