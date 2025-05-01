<template>
  <div class="login-page">
    <h2>登入</h2>
    <input v-model="form.phone" placeholder="手機號碼" />
    <input v-model="form.password" type="password" placeholder="密碼" />
    <button @click="doLogin">登入</button>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { login as loginAPI } from '@/api/auth';

const router = useRouter();
const form = reactive({ phone: '', password: '' });

const doLogin = async () => {
  try {
    const token = await loginAPI(form);
    localStorage.setItem('token', token);

    // 🔍 解碼 JWT，取得 payload
    const payload = JSON.parse(atob(token.split('.')[1]));

    // ✅ 儲存登入者資訊
    localStorage.setItem('userId', payload.userId); // 後端要有 userId
    localStorage.setItem('phone', payload.sub);     // JWT 的 subject = phone

    alert('登入成功');
    router.push('/');
  } catch (err) {
    alert('登入失敗：' + err.message);
  }
};
</script>

<style scoped>
.login-page {
  max-width: 400px;
  margin: 2rem auto;
  padding: 1.5rem;
  border: 1px solid #ccc;
  border-radius: 6px;
  background-color: #fff;
}

input {
  display: block;
  margin-bottom: 1rem;
  padding: 0.75rem;
  width: 100%;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 0.75rem;
  background-color: #4a5759;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
