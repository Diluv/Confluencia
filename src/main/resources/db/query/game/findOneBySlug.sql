SELECT g.slug,
       g.name,
       g.url
FROM games g
WHERE g.slug = ?
LIMIT 1;