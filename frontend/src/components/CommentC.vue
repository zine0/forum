<template>
    <div class="comment-container">
        <!-- 元信息 -->
        <div class="comment-meta">
            <span class="author">
                <img :src="avatar" alt="作者头像" class="avatar">
                {{ username }}
            </span>
        </div>

        <p class="comment-excerpt" >{{ content }}</p>
    </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props=defineProps({
    avatar: {
        type: String,
        default: 'https://avatars.githubusercontent.com/u/46991452?v=4'
    },
    username: {
        type: String,
        default: '匿名用户'
    },
    content: {
        type: String,
        default: '这是一条评论内容'
    },
})

console.log(props.avatar)

</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}

.comment-container {
    width: 60vw;
    padding: 1.5rem;
    margin: auto;
    margin-top: 1.5rem;
    border-radius: 8px;
    background: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease;
}

.comment-container:hover {
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}
.comment-meta {
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

.comment-excerpt {
    margin: 0 0 1rem;
    color: #444;
    line-height: 1.6;
    white-space: normal;
    word-wrap: break-word;
    overflow-wrap: break-word;
    word-break: break-word;
}

</style>