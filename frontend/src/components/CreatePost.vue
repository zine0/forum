<template>
    <div class="create_post">
        <button class="mi-add-button" aria-label="添加">
            <div class="plus"></div>
        </button>
    </div>
</template>

<script>
export default {
    name: 'CreatePost',
}
</script>

<style scoped>

.mi-add-button {
    width: 60px;
    height: 60px;
    background-color: #3498db;
    /* 小米品牌橙色 */
    /* border: 2px solid #3498db; */
    border: none;
    /* 添加边框 */
    border-radius: 16px;
    /* 小米特有的圆角程度 */
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    box-shadow: 0 4px 12px rgba(2, 188, 221, 0.315);
    transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
    position: relative;
    overflow: hidden;
    padding: 0;
}

/* 加号设计 */
.mi-add-button .plus {
    position: relative;
    width: 24px;
    height: 24px;
    transition: all 0.3s ease;
}

.mi-add-button .plus::before,
.mi-add-button .plus::after {
    content: "";
    position: absolute;
    background-color: white;
    /* 初始白色加号 */
    border-radius: 2px;
    transition: all 0.3s ease;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}

.mi-add-button .plus::before {
    width: 24px;
    height: 4px;
}

.mi-add-button .plus::after {
    width: 4px;
    height: 24px;
}

/* 悬停反色效果 */
.mi-add-button:hover {
    background-color: white;
    border-color: #3498db;
    box-shadow: 0 6px 16px rgba(2, 188, 221, 0.315);
}

.mi-add-button:hover .plus::before,
.mi-add-button:hover .plus::after {
    background-color: #3498db;
    /* 悬停时加号变橙色 */
}

/* 点击效果 */
.mi-add-button:active {
    transform: scale(0.96);
}

/* 悬停时加号轻微动画 */
.mi-add-button:hover .plus {
    transform: rotate(90deg);
}
</style>