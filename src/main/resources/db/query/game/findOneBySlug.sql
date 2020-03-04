SELECT slug,
       name,
       url,
       banner_url
FROM games
WHERE slug = ?
LIMIT 1;