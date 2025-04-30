
<template>
    <div class="post-preview" @click="navigateToPost">
        <!-- 头部区域 -->
        <div class="head">
            <h2 class="post-title">{{ post.title }}</h2>
            <div class="actions" v-if="showAction">
                <button class="edit-btn btn btn-primary"
                    @click.stop="handleEdit">
                    修改
                </button>
                <button class="delete-btn btn btn-danger"
                    @click.stop="handleDelete">
                    删除
                </button>
            </div>
        </div>

        <!-- 元信息 -->
        <div class="post-meta">
            <span class="author">
                <img :src="post.authorAvatar" alt="作者头像" class="avatar">
                {{ post.authorName }}
            </span>
        </div>

        <!-- 内容摘要 -->
        <p class="post-excerpt render-html" v-html="parsedPreview"></p>

        <!-- 互动数据 -->
        <div class="post-stats">
            <span class="stat-item">
                <i class="fa fa-solid fa-heart"></i> {{post.likes }}
            </span>
            <span class="stat-item">
                <i class="icon-comment"></i> {{ post.comments }}
            </span>
            <span class="stat-item">
                <i class="icon-view"></i> {{ post.views }}
            </span>
        </div>
    </div>
</template>

<script setup>
import { defineProps, defineEmits, ref, onMounted } from 'vue';
import { useUserStore } from '@/store/user';
import { marked } from 'marked';
import hljs from 'highlight.js';
import $ from "jquery";
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import { useRouter } from "vue-router";

// 只需要初始化一次 marked
const initMarked = () => {
    marked.setOptions({
        renderer: new marked.Renderer(),
        highlight: function (code, lang) {
            const language = hljs.getLanguage(lang) ? lang : 'plaintext';
            return hljs.highlight(code, { language }).value;
        },
        langPrefix: 'hljs language-',
        pedantic: false,
        gfm: true,
        breaks: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
        xhtml: false
    });
};

// 初始化 marked
initMarked();

const emit = defineEmits(['post-click', 'edit', 'delete']);
const userStore = useUserStore();
const showAction = ref(false);
const router = useRouter();

const props = defineProps({
    post: {
        type: Object,
        required: true,
    }
});

// 解析预览内容
const parsedPreview = ref(marked.parse(props.post.preview || ''));

function navigateToPost() {
    emit('post-click', props.post.id);
}

function handleEdit() {
    router.push({ name: 'updatePost', query: { id: props.post.id } });
}

function handleDelete() {
    $.ajax({
        url: `http://localhost:3000/user/post?id=${props.post.id}`,
        type: "delete",
        headers: {
            "Authorization": `Bearer ${userStore.user.token}`
        },
        success(data) {
            if (data.message === "success") {
                toast.success("删除post成功");
            } else {
                toast.error("删除失败");
            }
        }, error() {
            toast.error("删除失败");
        }
    })
}

onMounted(() => {
    showAction.value = (props.post.authorId == userStore.user.id);
});
</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}
.fa-heart {
    color: red;
}
.head {
    position: relative;
}

.post-title {
    display: inline;
}

.actions {
    position: absolute;
    display: inline-block;
    right: 0px;
}

.edit-btn {
    font-size: 12px;
    margin: 0 3px;
    padding: 5px 10px;
}

.delete-btn {
    font-size: 12px;
    margin: 0 3px;
    padding: 5px 10px;
}

.post-preview {
    width: 60vw;
    padding: 1.5rem;
    margin: auto;
    margin-top: 1.5rem;
    border-radius: 8px;
    background: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease;
    cursor: pointer;
}

.post-preview:hover {
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.render-html {
    /* 基础换行 */
    white-space: normal;
    word-wrap: break-word;
    overflow-wrap: break-word;

    /* 保留HTML样式的同时强制换行 */
    display: block;
    word-break: break-word;

    /* 可选样式 */
    line-height: 1.6;
    margin: 1em 0;
}

/* 确保嵌套标签也继承样式 */
.render-html strong,
.render-html a,
.render-html span {
    display: inline;
    /* 保持行内元素特性 */
    word-break: inherit;
    /* 继承换行规则 */
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

.post-title {
    margin: 0 0 0.5rem;
    font-size: 1.5rem;
    color: #333;
}

.post-meta {
    display: flex;
    align-items: center;
    margin-bottom: 1rem;
    margin-top: 0.5rem;
    font-size: 0.9rem;
    color: #666;
}

.avatar {
    width: 24px;
    height: 24px;
    border-radius: 50%;
    margin-right: 0.5rem;
    vertical-align: middle;
}

.divider {
    margin: 0 0.5rem;
}

.post-excerpt {
    margin: 0 0 1rem;
    color: #444;
    line-height: 1.6;
    white-space: normal;
    /* 默认值，允许换行 */
    word-wrap: break-word;
    /* 允许在单词内换行 */
    overflow-wrap: break-word;
    /* 更标准的属性 */

    /* 中文换行优化 */
    word-break: break-word;
    /* 对中文更友好的换行 */
}

.post-stats {
    display: flex;
    gap: 1.5rem;
    margin-bottom: 1rem;
    font-size: 0.9rem;
    color: #888;
}

.stat-item {
    display: flex;
    align-items: center;
    gap: 0.3rem;
}

.post-tags {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
}

.tag {
    padding: 0.2rem 0.6rem;
    background: #f0f0f0;
    border-radius: 12px;
    font-size: 0.8rem;
    color: #555;
}
</style>