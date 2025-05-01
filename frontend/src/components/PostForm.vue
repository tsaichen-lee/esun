<template>
  <div v-if="isLoggedIn" class="post-form">
    <h3>發表新貼文</h3>
    <textarea v-model="post.content" placeholder="輸入內容..." />
    <input v-model="post.image" placeholder="圖片連結（可選）" />
    <button @click="submitPost">送出</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { createPost } from '@/api/post.js'

const emit = defineEmits(['refresh'])

const post = ref({ content: '', image: '' })
const isLoggedIn = ref(false)

onMounted(() => {
  isLoggedIn.value = !!localStorage.getItem('token')
})

const submitPost = async () => {
  if (!post.value.content) return alert('內容不可為空')
  try {
    await createPost(post.value)
    alert('發文成功')
    post.value = { content: '', image: '' }
    emit('refresh') // ✅ 通知父元件刷新貼文列表
  } catch (err) {
    alert('發文失敗')
    console.error(err)
  }
}
</script>

<style scoped>
.post-form {
  margin-bottom: 2rem;
  padding: 1rem;
  border: 1px solid #bbb;
  background-color: #f9f9f9;
  border-radius: 6px;
}
textarea, input {
  width: 100%;
  margin-bottom: 0.5rem;
  padding: 0.75rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}
button {
  padding: 0.5rem 1.2rem;
  background-color: #7d9d9c;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background-color: #5d7f7e;
}
</style>
