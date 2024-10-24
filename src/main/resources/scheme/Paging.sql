CREATE database sandbox;
use sandbox;

CREATE TABLE paging (
                       id INT AUTO_INCREMENT PRIMARY KEY,  -- 자동 증가 ID
                       title VARCHAR(255) NOT NULL,        -- 제목
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 생성 시점 (기본값은 현재 시간)
);
