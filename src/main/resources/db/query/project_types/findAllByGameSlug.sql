SELECT pt.name,
       pt.slug,
       pt.game_slug,
       pt.max_file_size,
       pt.project_count
FROM project_types pt
WHERE pt.game_slug = ?;
