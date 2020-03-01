DELETE
FROM api_tokens
WHERE user_id = ?
  AND code = ?;