<template>
    <div class="post-preview" @click="navigateToPost">
        <!-- 标题 -->
        <h2 class="post-title">{{ post.title }}</h2>

        <!-- 元信息 -->
        <div class="post-meta">
            <span class="author">
                <img :src="post.authorAvatar" alt="作者头像" class="avatar">
                {{ post.authorName }}
            </span>
        </div>

        <!-- 内容摘要 -->
        <p class="post-excerpt render-html" v-html="post.preview"></p>

        <!-- 互动数据 -->
        <div class="post-stats">
            <span class="stat-item">
                <i class="icon-like"></i> {{ post.likes }}
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
import { defineProps, defineEmits } from 'vue'
const emit = defineEmits(['post-click'])
const props = defineProps({
    post: {
        type: Object,
        required: true,
        validator: (value) => {
            return (
                typeof value.title === 'string' &&
                typeof value.author === 'object' &&
                value.author.name &&
                value.author.avatar &&
                value.publishDate &&
                typeof value.excerpt === 'string'
            )
        }
    }
})
function navigateToPost() {
    // 触发路由跳转或 emit 事件
    emit('post-click', props.post.id)
    // 或者使用路由
    // this.$router.push(`/posts/${this.post.id}`)
}
</script>

<style scoped>
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

.post-title {
    margin: 0 0 0.5rem;
    font-size: 1.5rem;
    color: #333;
}

.post-meta {
    display: flex;
    align-items: center;
    margin-bottom: 1rem;
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
    white-space: normal;      /* 默认值，允许换行 */
  word-wrap: break-word;    /* 允许在单词内换行 */
  overflow-wrap: break-word; /* 更标准的属性 */
  
  /* 中文换行优化 */
  word-break: break-word;   /* 对中文更友好的换行 */
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