<template>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container">
            <a class="navbar-brand" href="#">Forum</a>
            <div class="collapse navbar-collapse" id="navbarText">
                <form class="d-flex search" role="search"> <input
                        class="form-control me-2" type="search"
                        placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success"
                        type="submit">Search</button>
                </form>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        zine
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">我的帖子</a></li>
                        <li><a class="dropdown-item" href="#">用户空间</a></li>
                        <li><a class="dropdown-item last-item" href="#">登出</a></li>
                    </ul>
                </li>

            </div>
        </div>
    </nav>
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";

export default {
    name: "NavBar",
}

</script>
<style scoped>
.navbar-collapse {
    display: flex;
    justify-content: right;
    position: relative;
}

.search {
    margin-right: 50px;
}

.dropdown{
    list-style-type: none;
}

.last-item {
    border-top: 1px solid #DDDDDD; /* 默认所有 li 有下边框 */
  padding-top: 8px;
}
</style>