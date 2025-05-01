<template>
    <div class="feature-preview" @click="navigateToPost">
        <!-- 元信息 -->
        <div class="post-meta">
            <span class="author">
                <img :src="post.authorAvatar" alt="作者头像" class="avatar">
                {{ post.authorName }}
            </span>
        </div>

        <h2 class="post-title">{{ post.title }}</h2>

        <!-- 互动数据 -->
        <div class="left">
            <div class="actions" v-if="showAction">
                <button class="delete-btn btn btn-danger"
                    @click.stop="handleDelete">
                    取消置顶
                </button>
            </div>
            <div class="post-stats">
                <span class="stat-item">
                    <i class="fa fa-solid fa-heart"></i>
                    {{ post.likes }}
                </span>
            </div>
        </div>
    </div>
</template>

<script setup>

import { defineProps, defineEmits, ref, onMounted } from 'vue';
import { useUserStore } from '@/store/user';
import $ from 'jquery';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const userStore = useUserStore();
const emit = defineEmits(['post-click']);
const showAction = ref(false);

const navigateToPost = () => {
    emit('post-click', props.post.id);
}

const props = defineProps({
    post: {
        type: Object,
        required: true
    }
});

const handleDelete = () => {
    $.ajax({
        url: `http://localhost:3000/post/feature?postId=${props.post.id}`,
        type: "delete",
        headers: {
            "Authorization": `Bearer ${userStore.user.token}`
        },
        success(data) {
            if (data.message === "success") {
                toast.success("取消置顶成功");
            } else {
                toast.error(data.message);
            }
        }, error() {
            toast.error("取消置顶失败");
        }
    })
}

onMounted(() => {
    showAction.value = userStore.user.permission == 1;
})

</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}

.fa-heart {
    color: red;
}

.left {
    margin-left: auto;
}

.actions {
    display: inline;
    margin-right: 10px;
}

.btn {
    font-size: 13px;
    padding: 2px 6px;
}

.feature-preview {
    display: flex;
    align-items: center;
    width: 60vw;
    padding: 10px 3px;
    margin: auto;
    margin-top: 8px;
    border-radius: 8px;
    background: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease;
    cursor: pointer;
}

.feature-preview:hover {
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}


.post-title {
    font-size: 20px;
    color: #333;
}

.post-meta {
    align-items: center;
    font-size: 0.9rem;
    color: #666;
    margin-left: 20px;
    margin-right: 15px;
}

.post-stats {
    display: inline;
    right: 0px;
    margin-right: 20px;
    margin-left: auto;
    font-size: 0.9rem;
    color: #888;
}

.avatar {
    width: 24px;
    margin-right: 5px;
}
</style>