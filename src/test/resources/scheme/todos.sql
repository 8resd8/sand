CREATE database sandbox;
use sandbox;

CREATE TABLE testTodo
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    content   VARCHAR(255) NOT NULL,
    completed BOOLEAN      NOT NULL DEFAULT FALSE
);
