import { fetchWithAuth } from './fetchWrapper';

export async function addComment(postId, content) {
    const res = await fetchWithAuth('/comments/tx', {
        method: 'POST',
        body: JSON.stringify({ postId, content })
    });

    if (!res.ok) throw new Error('留言失敗');
}

export async function getComments(postId) {
    const res = await fetchWithAuth(`/comments/post/${postId}`);

    if (!res.ok) throw new Error('留言載入失敗');
    return res.json();
}
