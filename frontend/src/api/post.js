import { fetchWithAuth } from './fetchWrapper';

export async function createPost(post) {
    const res = await fetchWithAuth('/api/posts', {
        method: 'POST',
        body: JSON.stringify(post)
    });

    if (!res.ok) throw new Error('建立貼文失敗');
}

export async function getPosts() {
    const res = await fetchWithAuth('/api/posts');

    if (!res.ok) throw new Error('取得貼文失敗');
    return res.json();
}

export async function updatePost(postId, data) {
    const res = await fetchWithAuth(`/posts/${postId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    });

    if (!res.ok) throw new Error('更新貼文失敗');
    return await res.json(); // 確保回傳新內容
}

export async function deletePostById(postId) {
    await fetchWithAuth(`/posts/${postId}`, { method: 'DELETE' });
}
