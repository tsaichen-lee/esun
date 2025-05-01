<template>
  <div>
    <header class="app-header">
      <div class="nav-container">
        <h1 class="site-title">社群平台</h1>
        <nav class="nav-links">
          <router-link to="/">首頁</router-link>
          <router-link to="/login">登入</router-link>
          <router-link to="/register">註冊</router-link>
        </nav>
      </div>
    </header>

    <main class="main-content">
      <router-view />
    </main>
  </div>
</template>

<style scoped>
.app-header {
  background-color: #d8e3dc; /* 莫蘭迪綠 */
  border-bottom: 1px solid #ccc;
  padding: 1rem 2rem;
  font-family: 'Segoe UI', sans-serif;
}

.nav-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.site-title {
  font-size: 1.5rem;
  font-weight: bold;
  color: #4a5759;
  margin: 0;
}

.nav-links a {
  margin-left: 1.5rem;
  color: #4a5759;
  text-decoration: none;
  font-weight: 500;
  padding: 6px 12px;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.nav-links a:hover {
  background-color: #eef0f2;
}
</style>
