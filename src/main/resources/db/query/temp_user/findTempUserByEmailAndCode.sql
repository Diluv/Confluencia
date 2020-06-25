SELECT id,
       username,
       email,
       password,
       password_type,
       created_at,
       code
FROM temp_users
WHERE email = ?
  AND code = ?
LIMIT 1;