SELECT g.slug,
       g.name,
       g.url,
       gdpt.project_type_slug AS default_project_type
FROM games g
         JOIN game_default_project_type gdpt ON (g.slug = gdpt.game_slug)
WHERE g.name LIKE ?
ORDER BY '%sort%' '%order%'
LIMIT ?, ?;