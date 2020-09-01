CREATE TABLE user_email
(
    user_id    BIGINT       NOT NULL,

    email      VARCHAR(255) NOT NULL,
    code       CHAR(8)      NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),

    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
)