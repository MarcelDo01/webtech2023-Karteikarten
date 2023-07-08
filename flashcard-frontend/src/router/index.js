import {createRouter, createWebHashHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'

import FlashcardsView from "@/views/FlashcardsView.vue";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: AboutView
  },
  {
    path: '/flashcards',
    name: 'Flashcards',
    component: FlashcardsView
  }

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
