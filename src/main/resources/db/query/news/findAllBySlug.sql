SELECT slug,
       title,
       summary,
       description,
       username,
       created_at
FROM news
WHERE slug = ?