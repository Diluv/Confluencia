UPDATE temp_users
SET verificationCode = ?
WHERE email = ?
  AND username = ?