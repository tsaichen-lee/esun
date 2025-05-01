<template>
  <div>
    <h2>所有貼文</h2>
    <div v-for="post in posts" :key="post.id" class="post">
      <p><strong>內容：</strong>{{ post.content }}</p>
      <p><strong>圖片：</strong>{{ post.image }}</p>
      <p><strong>留言數：</strong>{{ post.commentCount }}</p>

      <div class="comments">
        <h4>留言：</h4>
        <ul>
          <li v-for="c in comments[post.id]" :key="c.id">{{ c.content }}</li>
        </ul>
        <input v-model="newComments[post.id]" placeholder="新增留言..." />
        <button @click="submitComment(post.id)">留言</button>
      </div>
      <hr />
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getPosts } from '@/api/post.js';
import { addComment, getComments } from '@/api/comment.js';

const posts = ref([]);
const comments = ref({});
const newComments = ref({});

onMounted(async () => {
  posts.value = await getPosts();

  for (const post of posts.value) {
    comments.value[post.id] = await getComments(post.id);
    newComments.value[post.id] = '';
  }
});

const submitComment = async (postId) => {
  const content = newComments.value[postId];
  if (!content) return;

  await addComment(postId, content);
  newComments.value[postId] = '';
  comments.value[postId] = await getComments(postId);
};
</script>

<style scoped>
.post {
  margin-bottom: 2rem;
  padding: 1rem;
  border: 1px solid #ccc;
}
</style>
