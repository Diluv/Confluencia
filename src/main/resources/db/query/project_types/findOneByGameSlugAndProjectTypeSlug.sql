SELECT pt.name,
       pt.slug,
       pt.game_slug,
       pt.max_size
FROM project_types pt
WHERE pt.game_slug = ?
  AND pt.slug = ?
LIMIT 1;
