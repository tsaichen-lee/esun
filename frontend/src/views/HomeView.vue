<template>
  <div class="container">
    <!-- 僅登入後才顯示 -->
    <PostForm v-if="isLoggedIn" />

    <PostList />
  </div>
</template>

<script setup>
import PostForm from '@/components/PostForm.vue'
import PostList from '@/components/PostList.vue'
import { ref, onMounted } from 'vue'

const isLoggedIn = ref(false)

onMounted(() => {
  isLoggedIn.value = !!localStorage.getItem('token')
})
</script>
