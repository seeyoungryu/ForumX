CREATE TABLE article
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    content     TEXT         NOT NULL,
    hashtag     VARCHAR(255),
    created_at  TIMESTAMP,
    created_by  VARCHAR(100),
    modified_at TIMESTAMP,
    modified_by VARCHAR(100)
);
