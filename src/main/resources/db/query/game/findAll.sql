SELECT g.slug,
       g.name,
       g.url,
       g.default_project_type
FROM games g
WHERE g.name LIKE ?
ORDER BY '%sort%' '%order%'
LIMIT ?, ?;