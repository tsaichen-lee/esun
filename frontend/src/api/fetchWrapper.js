export async function fetchWithAuth(url, options = {}) {
    const token = localStorage.getItem('token');

    const headers = {
        'Content-Type': 'application/json',
        ...(options.headers || {})
    };

    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    return fetch(url, {
        ...options,
        headers
    });
}
