<template>
    <div class="post-preview" @click="navigateToPost">
        <!-- 标题 -->
        <h2 class="post-title">{{ post.title }}</h2>

        <!-- 元信息 -->
        <div class="post-meta">
            <span class="author">
                <img :src="post.author.avatar" alt="作者头像" class="avatar">
                {{ post.author.name }}
            </span>
            <span class="divider">•</span>
            <span class="date">{{ formatDate(post.publishDate) }}</span>
            <span class="divider">•</span>
            <span class="category">{{ post.category }}</span>
        </div>

        <!-- 内容摘要 -->
        <p class="post-excerpt">{{ post.excerpt }}</p>

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

        <!-- 标签 -->
        <div class="post-tags" v-if="post.tags && post.tags.length">
            <span class="tag" v-for="tag in post.tags" :key="tag">{{ tag
                }}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: 'PostPreview',
    props: {
        post: {
            type: Object,
            required: true,
            validator: (value) => {
                return (
                    value.title &&
                    value.author &&
                    value.publishDate &&
                    value.excerpt
                )
            }
        }
    },
    methods: {
        formatDate(date) {
            // 这里可以使用 day.js 或 moment.js 等库进行更复杂的日期格式化
            return new Date(date).toLocaleDateString()
        },
        navigateToPost() {
            // 触发路由跳转或 emit 事件
            this.$emit('post-click', this.post.id)
            // 或者使用路由
            // this.$router.push(`/posts/${this.post.id}`)
        }
    }
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