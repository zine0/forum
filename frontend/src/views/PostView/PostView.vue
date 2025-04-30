<template>

    <NavBar />
    <div class="post-detail">
        <!-- 返回按钮 -->
        <div class="back-button" @click="goBack">
            <i class="icon-back"></i> 返回列表
        </div>

        <!-- 文章标题 -->
        <h1 class="post-title">{{ post.title }}</h1>

        <!-- 作者信息 -->
        <div class="post-meta">
            <div class="author-info">
                <img :src="post.authorAvatar" alt="作者头像" class="avatar">
                <div class="author-details">
                    <span class="author-name">{{ post.authorName }}</span>
                </div>
            </div>
        </div>

        <!-- 文章内容 -->
        <div class="post-content render-html" v-html="post.parsedContent">
        </div>

        <!-- 互动区域 -->
        <div class="interaction-area">
            <button class="interaction-button" @click="likePost">
                <i :class="{ 'fa': true, 'fa-regular':true, 'fa-heart':true, 'liked':liked}"></i> 点赞 ({{ post.likes }})
            </button>
            <button class="interaction-button"
                @click="showComments = !showComments">
                <i class="icon-comment"></i> 评论 ({{ post.comments }})
            </button>
        </div>

        <!-- 评论区域 -->
        <div class="comments-section" v-if="showComments">
            <h3>评论</h3>
            <div class="comment-list">
                <!-- 评论列表内容 -->
            </div>
            <div class="comment-form">
                <textarea placeholder="写下你的评论..."></textarea>
                <button class="submit-button">提交</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import NavBar from '@/components/NavBar.vue';
import $ from "jquery";
import { useRoute, useRouter } from 'vue-router';
import { useUserStore } from '@/store/user';
import { marked } from 'marked';
import hljs from 'highlight.js';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
const showComments = ref(false);

const post = ref({});
const liked = ref(false);


const goBack = () => {
    router.go(-1)
}

const likePost = () => {
    $.ajax({
        url: `http://localhost:3000/post/like?postId=${post.value.id}`,
        type: "get",
        headers: {
            "Authorization": `Bearer ${userStore.user.token}`
        },
        success(data) {
            if (data.message === 'success')
            {
                toast.success("点赞成功");
                liked.value = true;
                post.value.likes += 1;
            }
            else {
                toast.error("点赞失败");
            }
        }, error() {
            
                toast.error("点赞失败");
        }
    })
}

const getPost = () => {
    $.ajax({
        url: `http://localhost:3000/post/${route.params.id}`,
        type: "get",

        headers: {
            "Authorization": `Bearer ${userStore.user.token}`
        },
        success(data) {
            if (data != null) {
                post.value = {
                    ...data,
                    parsedContent: marked.parse(data.content || '')
                };
            }
        }
    })

    $.ajax({
        url: `http://localhost:3000/user/post/like?postId=${route.params.id}`,
        type: 'get',
        headers: {
            "Authorization": `Bearer ${userStore.user.token}`
        },
        success(data)
        {
            liked.value = data.isLike;
        }
    })
}

const initMarked = () => {
    marked.setOptions({
        renderer: new marked.Renderer(),
        highlight: function (code, lang) {
            const language = hljs.getLanguage(lang) ? lang : 'plaintext'
            return hljs.highlight(code, { language }).value
        },
        langPrefix: 'hljs language-',
        pedantic: false,
        gfm: true,
        breaks: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
        xhtml: false
    })
}


initMarked();
onMounted(getPost)
</script>

<style scoped>

.liked {
    color:red;
}

.post-detail {
    width: 60vw;
    padding: 2rem;
    margin: 0 auto;
    margin-top: 30px;
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.back-button {
    display: flex;
    align-items: center;
    margin-bottom: 1.5rem;
    color: #666;
    cursor: pointer;
    font-size: 0.9rem;
    transition: color 0.2s;
}

.back-button:hover {
    color: #333;
}

.post-title {
    margin: 0 0 1rem;
    font-size: 2rem;
    color: #333;
    line-height: 1.3;
}

.post-meta {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 1.5rem;
    padding-bottom: 1rem;
    border-bottom: 1px solid #eee;
}

.author-info {
    display: flex;
    align-items: center;
    gap: 0.8rem;
}

.avatar {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    object-fit: cover;
    font-size: 11px;
}

.author-details {
    display: flex;
    flex-direction: column;
}

.author-name {
    font-weight: 500;
    color: #333;
}

.publish-date {
    font-size: 0.8rem;
    color: #888;
}

.post-stats {
    display: flex;
    gap: 1.5rem;
}

.stat-item {
    display: flex;
    align-items: center;
    gap: 0.3rem;
    color: #666;
}

.cover-image {
    width: 100%;
    max-height: 400px;
    object-fit: cover;
    border-radius: 6px;
    margin-bottom: 2rem;
}

.post-content {
    line-height: 1.8;
    color: #444;
    margin-bottom: 2rem;
}

.render-html {
    white-space: normal;
    word-wrap: break-word;
    overflow-wrap: break-word;
    word-break: break-word;
}

.render-html :deep(p) {
    margin-bottom: 1.5em;
}

.render-html :deep(img) {
    max-width: 100%;
    border-radius: 4px;
}

.render-html :deep(a) {
    color: #4285f4;
    text-decoration: none;
}

.render-html :deep(a:hover) {
    text-decoration: underline;
}

.post-tags {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
    margin-bottom: 2rem;
}

.tag {
    padding: 0.3rem 0.8rem;
    background: #f0f0f0;
    border-radius: 16px;
    font-size: 0.8rem;
    color: #555;
}

.interaction-area {
    display: flex;
    gap: 1rem;
    margin-bottom: 2rem;
    padding-top: 1rem;
    border-top: 1px solid #eee;
    font-size: 13px;
}

.interaction-button {
    display: flex;
    align-items: center;
    gap: 0.3rem;
    padding: 0.5rem 1rem;
    background: #f5f5f5;
    border: none;
    border-radius: 20px;
    cursor: pointer;
    transition: background 0.2s;
}

.interaction-button:hover {
    background: #e0e0e0;
}

.comments-section {
    margin-top: 2rem;
    padding-top: 2rem;
    border-top: 1px solid #eee;
}

.comments-section h3 {
    margin-bottom: 1rem;
    color: #333;
}

.comment-form textarea {
    width: 100%;
    min-height: 100px;
    padding: 0.8rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    margin-bottom: 1rem;
}

.submit-button {
    padding: 0.5rem 1.5rem;
    background: #4285f4;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

@media (max-width: 768px) {
    .post-detail {
        width: 90vw;
        padding: 1rem;
    }

    .post-title {
        font-size: 1.5rem;
    }

    .post-meta {
        flex-direction: column;
        align-items: flex-start;
        gap: 1rem;
    }
}
</style>