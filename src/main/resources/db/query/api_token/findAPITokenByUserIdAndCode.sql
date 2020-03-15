SELECT t.user_id,
       t.code,
       t.name,
       GROUP_CONCAT(p.permission SEPARATOR ' ') AS permissions
FROM api_tokens t
         LEFT OUTER JOIN api_token_permissions AS p ON (t.user_id = p.user_id AND t.code = p.code)
WHERE t.user_id = ?
  AND t.code = ?
GROUP BY t.user_id, t.code
LIMIT 1;