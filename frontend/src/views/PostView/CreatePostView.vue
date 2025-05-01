<template>
  <NavBar />
  <div class="create-post-page">
    <div class="editor-container">
      <div class="title-section">
        <input
          v-model="post.title"
          type="text"
          placeholder="输入文章标题..."
          class="title-input"
        />
      </div>

      <div class="md-editor">
        <v-md-editor
          v-model="post.content"
          :disabled-menus="[]"
          height="80vh"
        ></v-md-editor>
      </div>

      <div class="action-buttons">
        <button class="btn publish" @click="publishPost">发布文章</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { toast } from "vue3-toastify";
import "vue3-toastify/dist/index.css";
import $ from "jquery";
import { useUserStore } from "@/store/user";

const router = useRouter();
const userStore = useUserStore();
// 文章数据
const post = ref({
  title: "",
  content: "",
});

// 发布文章
const publishPost = () => {
  if (!post.value.title.trim()) {
    toast.error("请输入文章标题");
    return;
  }
  if (!post.value.content.trim()) {
    toast.error("请输入文章内容");
    return;
  }

  // toast.success('文章发布成功');

  $.ajax({
    url: "http://localhost:3000/post/create",
    type: "post",
    dataType: "json",
    data: JSON.stringify({
      title: post.value.title,
      content: post.value.content,
    }),
    contentType: "application/json;charset=utf-8",
    headers: {
      Authorization: `Bearer ${userStore.user.token}`,
    },
    success(ref) {
      if (ref.message === "success") {
        toast.success("文章发布成功");
      } else {
        toast.error("发布失败");
        console.log(ref.message);
      }
    },
    error() {
      toast.error("发布失败");
      console.log(ref.message);
    },
  });

  router.push("/");
};
</script>

<style scoped>
.create-post-page {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.editor-container {
  margin-top: 20px;
}

.title-section {
  margin-bottom: 20px;

  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

.title-input {
  width: 100%;
  padding: 12px 16px;
  font-size: 20px;
  border: 1px solid #e1e1e1;
  border-radius: 6px;
  outline: none;
  transition: border-color 0.3s;
}

.title-input:focus {
  border-color: #4285f4;
  box-shadow: 0 0 0 2px rgba(66, 133, 244, 0.2);
}

.md-editor {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
  margin-bottom: 24px;
  border: 1px solid #e1e1e1;
  border-radius: 6px;
  overflow: hidden;
}

.action-buttons {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
}

.btn {
  padding: 10px 20px;
  border-radius: 6px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s;
  border: none;
}

.publish {
  background-color: #4285f4;
  color: white;
}

.publish:hover {
  background-color: #3367d6;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .create-post-page {
    padding: 10px;
  }

  .editor-container {
    padding: 16px;
  }

  .title-input {
    font-size: 1.2rem;
  }

  .action-buttons {
    justify-content: space-between;
  }

  .btn {
    width: 48%;
    padding: 12px;
  }
}
</style>
