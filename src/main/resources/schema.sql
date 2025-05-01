-- 使用者表
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       phone VARCHAR(20) NOT NULL UNIQUE,
                       username VARCHAR(50),
                       email VARCHAR(100) UNIQUE,
                       password VARCHAR(255),
                       biography TEXT,
                       cover_image VARCHAR(255)
);

-- 發文表
CREATE TABLE posts (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       content TEXT,
                       image VARCHAR(255),
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 留言表
CREATE TABLE comments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          post_id BIGINT NOT NULL,
                          content TEXT,
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 發文 SP
DELIMITER //

CREATE PROCEDURE create_post (
    IN p_user_id BIGINT,
    IN p_content TEXT,
    IN p_image VARCHAR(255)
)
BEGIN
INSERT INTO posts (user_id, content, image, created_at)
VALUES (p_user_id, p_content, p_image, NOW());
END //

DELIMITER ;
