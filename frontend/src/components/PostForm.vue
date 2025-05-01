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
import { createPost } from '@/api/post'

const post = ref({ content: '', image: '' })
const isLoggedIn = ref(false)

onMounted(() => {
  isLoggedIn.value = !!localStorage.getItem('token')
})

const submitPost = async () => {
  if (!post.value.content) return alert('內容不可為空')
  await createPost(post.value)
  alert('發文成功')
  post.value = { content: '', image: '' }
}
</script>
