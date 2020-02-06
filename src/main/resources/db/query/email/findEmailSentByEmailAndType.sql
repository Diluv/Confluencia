SELECT message_id, email, type, sent_at
FROM email_sent
WHERE email = ?
  AND type = ?
ORDER BY sent_at
LIMIT 1;