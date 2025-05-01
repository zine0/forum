import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import LoginView from '@/views/AccountView/LoginView.vue';
import RegisterView from '@/views/AccountView/RegisterView.vue';
import PostView from '@/views/PostView/PostView.vue';
import NotFound from '@/views/NotFound.vue';
import CreateView from '@/views/PostView/CreatePostView.vue';
import UserPostView from '@/views/UserView/UserPostView.vue';
import UpdatePostView from '@/views/PostView/UpdatePostView.vue';
import SearchPostView from '@/views/PostView/SearchPostView.vue';

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
    path: "/user/post",
    name: "userPost",
    component:UserPostView
  },
  {
    path: "/post/update",
    name: "updatePost",
    component:UpdatePostView
  },
  {
    path: "/post/search",
    name: "searchPost",
    component:SearchPostView
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