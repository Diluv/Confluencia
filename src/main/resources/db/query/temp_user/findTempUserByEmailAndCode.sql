SELECT id,
       username,
       email,
       password,
       password_type,
       created_at,
       verification_code
FROM temp_users
WHERE email = ?
  AND verification_code = ?
LIMIT 1;