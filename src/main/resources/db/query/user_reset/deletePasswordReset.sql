DELETE
FROM password_reset
WHERE user_id = ?
  AND code = ?;