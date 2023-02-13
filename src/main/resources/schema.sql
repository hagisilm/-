CREATE TABLE notices
(
    notice_id BINARY(16) PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    content VARCHAR(500) NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) DEFAULT NULL
);
