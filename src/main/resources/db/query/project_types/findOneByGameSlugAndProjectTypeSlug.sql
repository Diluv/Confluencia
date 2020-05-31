SELECT pt.name,
       pt.slug,
       pt.game_slug,
       g.name AS game_name,
       pt.max_file_size,
       pt.project_count
FROM project_types pt
         JOIN games g ON (pt.game_slug = g.slug)
WHERE pt.game_slug = ?
  AND pt.slug = ?
LIMIT 1;
