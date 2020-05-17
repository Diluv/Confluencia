SELECT id,
       name,
       slug,
       project_type_slug,
       game_slug
FROM tags
WHERE game_slug = ?
  AND project_type_slug = ?;
