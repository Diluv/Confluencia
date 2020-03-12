SELECT slug,
       name,
       url,
       image_url,
       banner_url
FROM games
WHERE slug = ?
LIMIT 1;