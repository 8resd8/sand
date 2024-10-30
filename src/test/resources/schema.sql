CREATE TABLE IF NOT EXISTS todos (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 content VARCHAR(255) NOT NULL,
                                completed BOOLEAN DEFAULT FALSE
);