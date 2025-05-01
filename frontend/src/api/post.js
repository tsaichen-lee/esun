import { fetchWithAuth } from './fetchWrapper';

export async function createPost(post) {
    const res = await fetchWithAuth('/posts/sp', {
        method: 'POST',
        body: JSON.stringify(post)
    });

    if (!res.ok) throw new Error('建立貼文失敗');
}

export async function getPosts() {
    const res = await fetchWithAuth('/posts');

    if (!res.ok) throw new Error('取得貼文失敗');
    return res.json();
}
