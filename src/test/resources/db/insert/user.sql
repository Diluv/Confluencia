INSERT INTO users(id, username, email, password, password_type, mfa, mfa_secret, created_at)
VALUES (1, 'test', 'test@example.com', 'password', 'bcrypt', 0, null, NOW()),
       (2, 'test2', 'test2@example.com', 'password', 'bcrypt', 0, null, NOW()),
       (3, 'test3', 'test3@example.com', 'password', 'bcrypt', 0, null, NOW());