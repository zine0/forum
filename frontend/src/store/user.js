import { defineStore } from "pinia";
import { reactive } from "vue";
import $ from "jquery";

export const useUserStore = defineStore("user", () => {
    const user = reactive({
        id: null,
        username: null,
        avatar: null,
        is_login: false,
        token: ""
    })


    function login(data) {
        $.ajax({
            url: "http://localhost:3000/user/account/login",
            type: "post",
            data: {
                username: data.username,
                password: data.password,
            },
            success(ref) {
                if (ref.message === 'success') {
                    user.token = ref.token;
                    user.is_login = true;
                    getInfo();
                    data.success();
                } else {
                    data.error();
                }
            }, error() {
                data.error();
            }
        })
    }
    function register(data) {
        $.ajax({
            url: "http://localhost:3000/user/account/register",
            type: "post",
            data: {
                username: data.username,
                password: data.password,
                confirmPassword: data.confirmPassword,
                email: data.email
            },
            success(ref) {
                if (ref.message === 'success') {
                    user.token = ref.token;
                    user.is_login = true;
                    getInfo();
                    data.success();
                } else {
                    data.error(ref.message);
                }
            }, error(ref) {
                data.error(ref.message);
            }
        })
    }

    function getInfo() {
        $.ajax({
            url: "http://localhost:3000/user/info",
            headers: {
                Authorization: "Bearer " + user.token,
            },
            success(ref) {
                user.id = ref.id;
                user.username = ref.username;
                user.avatar = ref.avatar;
            },
        })
    }
    function logout() {
        user.id = null;
        user.username = null;
        user.avatar = null;
        user.is_login = false;
        user.token = null;
    }

    return { user, login, logout, register };
},
    {
        persist: true,
    },
);