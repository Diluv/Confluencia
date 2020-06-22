UPDATE temp_users
SET verification_code = ?
WHERE email = ?
  AND username = ?