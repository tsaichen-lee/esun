const BASE_URL = '/api';

export async function fetchWithAuth(path, options = {}) {
    const token = localStorage.getItem('token');

    const headers = {
        'Content-Type': 'application/json',
        ...options.headers,
        Authorization: token ? `Bearer ${token}` : ''
    };

    return fetch(`${BASE_URL}${path}`, { ...options, headers });
}
