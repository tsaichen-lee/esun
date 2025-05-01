<template>
  <div>
    <PostForm @refresh="refreshPosts" />

    <h2>所有貼文</h2>

    <div v-for="post in posts" :key="post.id" class="post">

      <!-- 編輯模式 -->
      <div v-if="editingPostId === post.id">
        <textarea v-model="editedContent" rows="3" style="width: 100%;" />
        <button @click="saveEdit(post.id)">儲存</button>
        <button @click="cancelEdit">取消</button>
      </div>

      <!-- 顯示模式 -->
      <div v-else>
        <p><strong>內容：</strong>{{ post.content }}</p>
        <p><strong>圖片：</strong>{{ post.image }}</p>
        <p><strong>留言數：</strong>{{ post.commentCount }}</p>

        <!-- 僅作者可見 -->
        <div v-if="post.userId === currentUserId">
          <button @click="startEdit(post)">✏️ 編輯</button>
          <button @click="deletePost(post.id)">🗑️ 刪除</button>
        </div>
      </div>

      <!-- 留言區 -->
      <div class="comments">
        <h4>留言：</h4>
        <ul v-if="comments[post.id]?.length">
          <li v-for="c in comments[post.id]" :key="c.id">
            <strong>{{ c.username || '匿名者' }}：</strong> {{ c.content }}
          </li>
        </ul>
        <p v-else class="no-comment">尚無留言</p>

        <input v-model="newComments[post.id]" placeholder="新增留言..." />
        <button @click="submitComment(post.id)">留言</button>
      </div>

      <hr />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import PostForm from '@/components/PostForm.vue';
import { getPosts, updatePost, deletePostById } from '@/api/post.js';
import { addComment, getComments } from '@/api/comment.js';

const posts = ref([]);
const comments = ref({});
const newComments = ref({});
const editingPostId = ref(null);
const editedContent = ref('');

// 取出目前登入者 ID（轉數字）
const currentUserId = Number(localStorage.getItem('userId') || '0');

// 取得貼文與留言
const refreshPosts = async () => {
  posts.value = await getPosts();

  for (const post of posts.value) {
    comments.value[post.id] = await getComments(post.id);
    newComments.value[post.id] = '';
  }
};

onMounted(refreshPosts);

// 留言
const submitComment = async (postId) => {
  const content = newComments.value[postId];
  if (!content) return;
  await addComment(postId, content);
  newComments.value[postId] = '';
  comments.value[postId] = await getComments(postId);
};

// 編輯貼文
const startEdit = (post) => {
  editingPostId.value = post.id;
  editedContent.value = post.content;
};

const cancelEdit = () => {
  editingPostId.value = null;
};

const saveEdit = async (postId) => {
  const updated = await updatePost(postId, { content: editedContent.value });
  const idx = posts.value.findIndex(p => p.id === postId);
  posts.value[idx].content = updated.content;
  editingPostId.value = null;
};

// 刪除貼文
const deletePost = async (postId) => {
  if (!confirm('確定要刪除這篇貼文？')) return;
  await deletePostById(postId);
  posts.value = posts.value.filter(p => p.id !== postId);
};
</script>

<style scoped>
.post {
  margin-bottom: 2rem;
  padding: 1rem;
  border: 1px solid #ccc;
  border-radius: 6px;
}
button {
  margin-right: 0.5rem;
  margin-top: 0.5rem;
}
textarea {
  width: 100%;
  margin-bottom: 0.5rem;
}
.comments {
  margin-top: 1rem;
}
</style>
