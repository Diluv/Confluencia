ALTER TABLE api_tokens
    ADD COLUMN id        BIGINT    NOT NULL AUTO_INCREMENT FIRST,
    ADD COLUMN last_used TIMESTAMP NOT NULL DEFAULT NOW() AFTER created_at,
    ADD COLUMN deleted   BOOL      NOT NULL DEFAULT FALSE AFTER last_used,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (id),
    ADD UNIQUE unique_index_token (token);

DROP TABLE api_token_permissions;

CREATE TABLE api_token_permissions
(
    id         BIGINT       NOT NULL,
    permission VARCHAR(255) NOT NULL,

    PRIMARY KEY (id, permission),
    FOREIGN KEY (id) REFERENCES api_tokens (id) ON DELETE CASCADE
);