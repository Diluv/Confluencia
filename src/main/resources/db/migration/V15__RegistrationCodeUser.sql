ALTER TABLE registration_codes
    ADD COLUMN user_id    BIGINT    NOT NULL,
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    ADD FOREIGN KEY (user_id) REFERENCES users (id);
