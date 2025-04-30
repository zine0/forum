<template>
    <NavBar></NavBar>

    <div class="container">
        <div class="register-box">
            <div class="register-header">
                <h2>创建账户</h2>
                <p>填写以下信息注册新账户</p>
            </div>
            <form @submit.prevent="handleRegister" class="register-form">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <div class="input-wrapper">
                        <svg class="icon" viewBox="0 0 24 24">
                            <path
                                d="M12,4A4,4 0 0,1 16,8A4,4 0 0,1 12,12A4,4 0 0,1 8,8A4,4 0 0,1 12,4M12,14C16.42,14 20,15.79 20,18V20H4V18C4,15.79 7.58,14 12,14Z" />
                        </svg>
                        <input type="text" id="username" v-model="username"
                            placeholder="请输入用户名" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">电子邮箱</label>
                    <div class="input-wrapper">
                        <svg class="icon" viewBox="0 0 24 24">
                            <path
                                d="M22 6C22 4.9 21.1 4 20 4H4C2.9 4 2 4.9 2 6V18C2 19.1 2.9 20 4 20H20C21.1 20 22 19.1 22 18V6M20 6L12 11L4 6H20M20 18H4V8L12 13L20 8V18Z" />
                        </svg>
                        <input type="email" id="email" v-model="email"
                            placeholder="请输入电子邮箱" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <div class="input-wrapper">
                        <svg class="icon" viewBox="0 0 24 24">
                            <path
                                d="M12,3A4,4 0 0,0 8,7V8H7A2,2 0 0,0 5,10V20A2,2 0 0,0 7,22H17A2,2 0 0,0 19,20V10A2,2 0 0,0 17,8H16V7A4,4 0 0,0 12,3M12,5A2,2 0 0,1 14,7V8H10V7A2,2 0 0,1 12,5Z" />
                        </svg>
                        <input type="password" id="password" v-model="password"
                            placeholder="请输入密码(至少6位)" required minlength="6">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">确认密码</label>
                    <div class="input-wrapper">
                        <svg class="icon" viewBox="0 0 24 24">
                            <path
                                d="M12,3A4,4 0 0,0 8,7V8H7A2,2 0 0,0 5,10V20A2,2 0 0,0 7,22H17A2,2 0 0,0 19,20V10A2,2 0 0,0 17,8H16V7A4,4 0 0,0 12,3M12,5A2,2 0 0,1 14,7V8H10V7A2,2 0 0,1 12,5Z" />
                        </svg>
                        <input type="password" id="confirmPassword"
                            v-model="confirmPassword" placeholder="请再次输入密码"
                            required minlength="6">
                    </div>
                </div>

                <div v-if="errorMessage" class="error-message">
                    <svg viewBox="0 0 24 24" class="error-icon">
                        <path
                            d="M13,13H11V7H13M13,17H11V15H13M12,2A10,10 0 0,0 2,12A10,10 0 0,0 12,22A10,10 0 0,0 22,12A10,10 0 0,0 12,2Z" />
                    </svg>
                    {{ errorMessage }}
                </div>
                <button type="submit" class="register-btn" :disabled="loading">
                    <span v-if="!loading">注册</span>
                    <span v-else class="loading-spinner"></span>
                </button>
            </form>
            <div class="register-footer">
                <p>已有账号? <router-link to="/login">立即登录</router-link></p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/store/user';
import NavBar from "@/components/NavBar";

const username = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const errorMessage = ref('');
const loading = ref(false);
const router = useRouter();
const userStore = useUserStore();

const handleRegister = async () => {
    errorMessage.value = '';

    // 验证密码是否一致
    if (password.value !== confirmPassword.value) {
        errorMessage.value = "两次输入的密码不一致";
        return;
    }

    // 验证密码长度
    if (password.value.length < 6) {
        errorMessage.value = "密码长度至少为6位";
        return;
    }

    loading.value = true;

    try {
        await userStore.register({
            username: username.value,
            email: email.value,
            password: password.value,
            confirmPassword:confirmPassword.value,
            success() {
                router.push('/'); // 注册成功后跳转到登录页
            },
            error(msg) {
                errorMessage.value = msg || "注册失败，请稍后重试";
            }
        });
    } catch (error) {
        errorMessage.value = '注册失败，请稍后重试';
    } finally {
        loading.value = false;
    }
};
</script>

<style scoped>
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100vw;
    min-height: calc(100vh - 80px);
}

.register-box {
    width: 420px;
    padding: 40px 50px;
    background-color: white;
    border-radius: 16px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
    transform: translateY(0);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    border: 1px solid rgba(0, 0, 0, 0.05);
}

.register-box:hover {
    transform: translateY(-5px);
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.12);
}

.register-header {
    text-align: center;
    margin-bottom: 36px;
}

.register-header h2 {
    font-size: 28px;
    font-weight: 700;
    color: #1e293b;
    margin-bottom: 8px;
}

.register-header p {
    color: #64748b;
    font-size: 15px;
}

.register-form {
    margin-bottom: 24px;
}

.form-group {
    margin-bottom: 24px;
}

label {
    display: block;
    margin-bottom: 10px;
    font-weight: 600;
    color: #334155;
    font-size: 14px;
}

.input-wrapper {
    position: relative;
    display: flex;
    align-items: center;
}

.icon {
    position: absolute;
    left: 14px;
    width: 20px;
    height: 20px;
    fill: #94a3b8;
    z-index: 1;
}

input {
    width: 100%;
    padding: 12px 16px 12px 44px;
    border: 1px solid #e2e8f0;
    border-radius: 8px;
    font-size: 15px;
    transition: all 0.3s ease;
    background-color: #f8fafc;
    color: #1e293b;
}

input:focus {
    border-color: #3b82f6;
    outline: none;
    box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.15);
    background-color: white;
}

.register-btn {
    width: 100%;
    padding: 14px;
    background-color: #3b82f6;
    color: white;
    border: none;
    border-radius: 8px;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s ease;
    margin-top: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 48px;
}

.register-btn:hover {
    background-color: #2563eb;
    transform: translateY(-1px);
}

.register-btn:active {
    transform: translateY(0);
}

.register-btn:disabled {
    background-color: #93c5fd;
    cursor: not-allowed;
    transform: none;
}

.loading-spinner {
    width: 20px;
    height: 20px;
    border: 3px solid rgba(255, 255, 255, 0.3);
    border-radius: 50%;
    border-top-color: white;
    animation: spin 1s ease-in-out infinite;
}

@keyframes spin {
    to {
        transform: rotate(360deg);
    }
}

.error-message {
    margin-top: 20px;
    margin-bottom: 20px;
    color: #ef4444;
    font-size: 14px;
    padding: 12px;
    background-color: #fef2f2;
    border-radius: 8px;
    display: flex;
    align-items: center;
    gap: 8px;
}

.error-icon {
    width: 18px;
    height: 18px;
    fill: #ef4444;
    flex-shrink: 0;
}

.register-footer {
    text-align: center;
    font-size: 14px;
    color: #64748b;
    border-top: 1px solid #f1f5f9;
    padding-top: 20px;
}

.register-footer a {
    color: #3b82f6;
    text-decoration: none;
    font-weight: 500;
    transition: color 0.2s;
}

.register-footer a:hover {
    color: #2563eb;
    text-decoration: underline;
}
</style>