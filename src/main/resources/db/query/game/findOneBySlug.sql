SELECT slug,
       name,
       url
FROM games
WHERE slug = ?
LIMIT 1;