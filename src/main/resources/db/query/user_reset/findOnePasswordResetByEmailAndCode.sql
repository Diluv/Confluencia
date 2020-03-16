SELECT pr.user_id,
       pr.code,
       pr.created_at
FROM password_reset pr
         JOIN users u ON (u.id = pr.user_id)
WHERE u.email = ?
  AND pr.code = ?
LIMIT 1;