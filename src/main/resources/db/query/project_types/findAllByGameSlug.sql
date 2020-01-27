SELECT name,
       slug,
       game_slug,
       max_size
FROM project_types
WHERE game_slug = ?
LIMIT 20;
