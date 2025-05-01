const BASE_URL = '/api/users';

export async function register(user) {
    const res = await fetch(`${BASE_URL}/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });

    if (!res.ok) throw new Error('註冊失敗');
}

export async function login(form) {
    const res = await fetch(`${BASE_URL}/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form)
    });

    if (!res.ok) throw new Error('登入失敗');
    return res.text(); // 回傳 token
}
