SELECT p.id,
       p.name,
       p.slug,
       p.summary,
       p.description,
       p.cached_downloads,
       p.created_at,
       p.updated_at,
       p.game_slug,
       p.project_type_slug,
       p.released,
       p.review,
       p.user_id,
       u.username,
       u.display_name,
       u.created_at AS user_created_at
FROM projects p
         JOIN users u on (u.id = p.user_id)
         JOIN project_files pf on p.id = pf.project_id
         JOIN project_file_game_versions pfgv on pf.id = pfgv.project_file_id
         JOIN game_versions gv on pfgv.game_version_id = gv.id
WHERE p.released = TRUE
  AND p.game_slug = ?
  AND p.project_type_slug = ?
  AND gv.version = ?
ORDER BY '%sort%' '%order%'
LIMIT ?, ?;