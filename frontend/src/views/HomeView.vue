<template>
  <NavBar></NavBar>
  <FeaturedPost></FeaturedPost>
  <PostPreview v-for="post in posts" :key="post.id" :post="post"
    @post-click="handlePostClick" />

  <div class="create-post-btn">
    <CreatePost></CreatePost>

  </div>
  <div class="pagination">
    <button :disabled="!pagination.hasPrevious"
      @click="changePage(currentPage - 1)">
      上一页
    </button>
    <span>第 {{ currentPage }} 页 / 共 {{ pagination.totalPages }} 页</span>
    <button :disabled="!pagination.hasNext"
      @click="changePage(currentPage + 1)">
      下一页
    </button>
  </div>
</template>

<script setup>
import NavBar from '../components/NavBar.vue';
import PostPreview from '../components/PostPreview.vue'
import CreatePost from '../components/CreatePost.vue';
import { ref, onMounted } from 'vue';
import $ from "jquery"
import { useUserStore } from "@/store/user";
import router from '@/router';
import FeaturedPost from '@/components/FeaturedPost.vue';

const userStore = useUserStore();

let posts = ref([]);
const loading = ref(false);
const error = ref(null);
const currentPage = ref(1);
const pagination = ref({
  totalPages: 0,
  hasNext: false,
  hasPrevious: false
});
const fetchPosts = (page = 1) => {
  loading.value = true;
  error.value = null;

  $.ajax({
    url: `http://localhost:3000/post/page?page=${page}&size=10`,
    method: "GET",
    headers: {
      "Authorization": `Bearer ${userStore.user.token}`
    },
    success: (data) => {
      posts.value = data.posts || [];
      pagination.value = {
        totalPages: data.totalPages,
        hasNext: data.hasNext,
        hasPrevious: data.hasPrevious
      };
      currentPage.value = page;
    },
    error: (xhr) => {
      console.error("获取帖子列表失败:", xhr);
      error.value = xhr.responseJSON?.message || "获取帖子列表失败，请稍后重试";
    },
    complete: () => {
      loading.value = false;
    }
  });
};

// 切换页码
const changePage = (newPage) => {
  if (newPage < 1 || newPage > pagination.value.totalPages) return;
  fetchPosts(newPage);
};

// 处理帖子点击
const handlePostClick = (postId) => {
  router.push(`/post/${postId}`);
};
onMounted(() => fetchPosts(1));
</script>

<style scoped>
.create-post-btn {
  position: fixed;
  bottom: 20vh;
  left: 85vw;
  z-index: 1000;
}

.status-message {
  padding: 20px;
  text-align: center;
  font-size: 16px;
}

.error {
  color: #ff4d4f;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin: 20px 0;
  padding: 10px;
}

.pagination button {
  padding: 8px 16px;
  border: 1px solid #d9d9d9;
  background: #fff;
  border-radius: 4px;
  cursor: pointer;
}

.pagination button:disabled {
  color: #d9d9d9;
  cursor: not-allowed;
}

.pagination button:not(:disabled):hover {
  border-color: #1890ff;
  color: #1890ff;
}
</style>
