<template>
    <div class="feature-preview" @click="navigateToPost">
        <!-- 元信息 -->
        <div class="post-meta">
            <span class="author">
                <img src="https://avatars.githubusercontent.com/u/46991452?v=4"
                    alt="作者头像" class="avatar">
                zine
            </span>
        </div>
        <h2 class="post-title">title</h2>
        <!-- 互动数据 -->
        <div class="post-stats">
            <span class="stat-item">
                <i class="fa fa-solid fa-heart"></i>
                10
            </span>
        </div>
    </div>
</template>

<script setup>

const navigateToPost = () => {
    console.log("click");
}

</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}

.fa-heart {
    color: red;
}


/* .post-title {
} */

.feature-preview {
    display: flex;
    align-items: center;
    width: 60vw;
    padding: 10px 3px;
    margin: auto;
    margin-top: 1.5rem;
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