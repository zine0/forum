import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/AccountView/LoginView.vue'
import RegisterView from '@/views/AccountView/RegisterView.vue'
import PostView from '@/views/PostView/PostView.vue'
import NotFound from '@/views/NotFound.vue'
import CreateView from '@/views/PostView/CreateView.vue'

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomeView
  },
  {
    path: "/login",
    name: "login",
    component: LoginView
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView
  },
  {
    path: "/post/:id",
    name: "post",
    component: PostView
  },
  {
    path: "/post/create",
    name: "createPost",
    component:CreateView
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
