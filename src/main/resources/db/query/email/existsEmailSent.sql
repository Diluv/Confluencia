SELECT message_id
FROM email_sent
WHERE message_id = ?
LIMIT 1;