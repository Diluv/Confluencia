SELECT id,
       username,
       email,
       password,
       password_type,
       created_at,
       verification_code
FROM temp_users
WHERE email = ?
  AND username = ?
LIMIT 1;