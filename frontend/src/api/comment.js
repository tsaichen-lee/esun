import { fetchWithAuth } from './fetchWrapper';

export async function addComment(postId, content) {
    const res = await fetchWithAuth('/api/comments/tx', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ postId, content })
    });

    if (!res.ok) {
        const errorText = await res.text();
        console.error('留言失敗原因：', errorText);
        throw new Error('留言失敗');
    }

    return await res.json();
}

export async function getComments(postId) {
    const res = await fetch(`/api/comments/post/${postId}`);
    if (!res.ok) throw new Error('留言取得失敗');
    return await res.json();
}
