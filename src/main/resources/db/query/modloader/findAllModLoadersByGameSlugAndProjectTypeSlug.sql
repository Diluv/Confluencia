SELECT id,
       name,
       project_type_slug,
       game_slug
FROM modloaders
WHERE game_slug = ?
  AND project_type_slug = ?;