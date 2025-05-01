-- 使用者（密碼為加密前的範例值，實際登入請用對應 bcrypt）
INSERT INTO users (phone, username, email, password, biography, cover_image) VALUES
                                                                                 ('0912345678', 'Alice', 'alice@example.com', '$2a$10$U7klzHkGwLMWDnD7ZTl28.5Uv5xxt1ddsc1OlSu66xVYxfIyfZMeC', 'I love coding!', 'cover1.jpg'),
                                                                                 ('0922333444', 'Bob', 'bob@example.com', '$2a$10$d7IVNfZxRM9oLZGu5FxRpO1IKQ61tvM/fKi1K7nrFOlzDHoYslSpS', 'Traveler and foodie', 'cover2.jpg');

-- 發文
INSERT INTO posts (user_id, content, image, created_at) VALUES
                                                            (1, '這是 Alice 的第一篇貼文', 'pic1.jpg', NOW()),
                                                            (2, 'Bob 分享了一段旅遊故事', NULL, NOW());

-- 留言
INSERT INTO comments (user_id, post_id, content, created_at) VALUES
                                                                 (2, 1, '不錯的貼文 Alice！', NOW()),
                                                                 (1, 2, '感謝分享 Bob！', NOW());
