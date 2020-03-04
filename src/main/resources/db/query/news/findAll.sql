SELECT slug,
       title,
       summary,
       description,
       username,
       created_at
FROM news
ORDER BY created_at
LIMIT ?, ?;
