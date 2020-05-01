SELECT id,
       username,
       display_name,
       email,
       password,
       password_type,
       mfa,
       mfa_secret,
       created_at
FROM users
WHERE id = ?
LIMIT 1;