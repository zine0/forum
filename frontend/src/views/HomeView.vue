<template>
    <NavBar></NavBar>

    <PostPreview v-for="post in posts" :key="post.id" :post="post"
        @post-click="handlePostClick" />


</template>

<script>
import NavBar from '../components/NavBar.vue';
import PostPreview from '../components/PostPreview.vue'

export default {
    components: {
        NavBar,
        PostPreview
    },
    data() {
        return {
            posts: [
                {
                    id: 1,
                    title: 'Vue 3 新特性全面解析',
                    author: {
                        name: '张三',
                        avatar: 'https://example.com/avatar1.jpg'
                    },
                    publishDate: '2023-05-15',
                    category: '前端开发',
                    excerpt: '本文将详细介绍Vue 3中的Composition API、性能优化等新特性...',
                    likes: 124,
                    comments: 32,
                    views: 2560,
                    tags: ['Vue', '前端', 'JavaScript']
                },
                {
                    id: 1,
                    title: 'Vue 3 新特性全面解析',
                    author: {
                        name: '张三',
                        avatar: 'https://example.com/avatar1.jpg'
                    },
                    publishDate: '2023-05-15',
                    category: '前端开发',
                    excerpt: '本文将详细介绍Vue 3中的Composition API、性能优化等新特性...',
                    likes: 124,
                    comments: 32,
                    views: 2560,
                    tags: ['Vue', '前端', 'JavaScript']
                },
            ]
        }
    },
    methods: {
        handlePostClick(postId) {
            console.log('点击了帖子:', postId)
            // 处理点击逻辑
        }
    }
}

</script>

<style scoped>

</style>
