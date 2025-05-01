<template>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container">
            <router-link class="navbar-brand"
                :to="{ 'name': 'Home' }">Forum</router-link>
            <div class="collapse navbar-collapse" id="navbarText">
                <form class="d-flex search" role="search"> <input
                        class="form-control me-2" type="search"
                        placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success"
                        type="submit">Search</button>
                </form>
                <li class="nav-item dropdown" v-if="userStore.user.is_login">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        {{ userStore.user.username }}
                    </a>
                    <ul class="dropdown-menu">
                        <li><router-link class="dropdown-item"
                                :to="{ 'name': 'userPost' }">我的帖子</router-link>
                        </li>
                        <li><a class="dropdown-item" href="#">用户空间</a></li>
                        <li><a class="dropdown-item last-item"
                                @click="userStore.logout" href="#">登出</a>
                        </li>
                    </ul>
                </li>
                <ul class="navbar-nav" v-else>
                    <li class="nav-item">
                        <router-link class="nav-link "
                            :to="{ 'name': 'login' }"
                            role="button">Login</router-link>
                    </li>
                    <span class="nav-link">/</span>
                    <li class="nav-item ">
                        <router-link class="nav-link "
                            :to="{ 'name': 'register' }"
                            role="button">Register</router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="placehold"></div>
</template>

<script setup>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import { useUserStore } from "@/store/user";
const userStore = useUserStore();


</script>
<style scoped>
.container {
    width: 80vw;
}
.navbar {
    top: 0px;
    position: fixed;
    background-color: #2c3e50;
    width: 100%;
    /* 深蓝灰色 */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    padding: 0.8rem 1rem;
    z-index: 1000;
}

.placehold {
    height: 65px;
}

.navbar-brand {
    color: #3498db !important;
    font-weight: 700;
    font-size: 1.5rem;
    transition: all 0.3s ease;
}

.navbar-collapse {
    display: flex;
    justify-content: flex-end;
    align-items: center;
}

/* 搜索框样式 */
.search {
    margin-right: 30px;
    width: 300px;
}

.form-control {
    border: 2px solid #bdc3c7;
    border-radius: 4px;
    padding: 0.5rem 1rem;
    transition: all 0.3s;
}

.form-control:focus {
    border-color: #3498db;
    box-shadow: 0 0 0 0.25rem rgba(52, 152, 219, 0.25);
}

.btn-search {
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 4px;
    padding: 0.5rem 1.2rem;
    margin-left: 0.5rem;
    transition: all 0.3s;
}

.btn-search:hover {
    background-color: #2980b9;
    transform: translateY(-1px);
}

/* 用户菜单样式 */
.user-menu {
    color: #ecf0f1 !important;
    font-weight: 500;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    transition: all 0.3s;
}

.user-menu:hover,
.dropdown-toggle.show {
    background-color: rgba(255, 255, 255, 0.1);
    color: #3498db !important;
}

li {

    list-style-type: none;
}

.dropdown {
    list-style-type: none;
}


.dropdown-menu {
    border: none;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    border-radius: 6px;
    padding: 0;
    overflow: hidden;
}

.dropdown-item {
    padding: 0.6rem 1.2rem;
    transition: all 0.2s;
    color: #2c3e50;
}

.dropdown-item:hover {
    background-color: #f8f9fa;
    color: #3498db;
    padding-left: 1.4rem;
}

.last-item {
    border-top: 1px solid #e0e0e0;
    padding-top: 0.6rem;
    margin-top: 0.3rem;
}

.btn {
    color: #3498db;
    border-color: #3498db;
}

.btn:hover {
    background-color: #3498db;
    border-color: #3498db;
    color: #fff;
}

/* 响应式调整 */
@media (max-width: 992px) {
    .navbar-collapse {
        justify-content: flex-start;
    }

    .search {
        margin-right: 0;
        margin-bottom: 1rem;
        width: 100%;
    }
}
</style>