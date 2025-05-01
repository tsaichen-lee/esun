<script setup>
import { login as loginAPI } from '@/api/auth'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const form = reactive({ phone: '', password: '' })

const doLogin = async () => {
  try {
    const token = await loginAPI(form)
    localStorage.setItem('token', token)
    alert('登入成功')
    router.push('/')
  } catch (err) {
    alert(err.message)
  }
}
</script>

<template>
  <div>
    <h2>登入</h2>
    <input v-model="form.phone" placeholder="手機號碼" />
    <input v-model="form.password" type="password" placeholder="密碼" />
    <button @click="doLogin">登入</button>
  </div>
</template>
