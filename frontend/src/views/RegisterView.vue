<script setup>
import { register as registerAPI } from '@/api/auth'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const user = reactive({
  phone: '',
  username: '',
  email: '',
  password: ''
})

const doRegister = async () => {
  try {
    await registerAPI(user)
    alert('註冊成功')
    router.push('/login')
  } catch (err) {
    alert(err.message)
  }
}
</script>

<template>
  <div>
    <h2>註冊</h2>
    <input v-model="user.phone" placeholder="手機號碼" />
    <input v-model="user.username" placeholder="使用者名稱" />
    <input v-model="user.email" placeholder="Email" />
    <input v-model="user.password" type="password" placeholder="密碼" />
    <button @click="doRegister">註冊</button>
  </div>
</template>
