SELECT pf.id,
       pf.name,
       pf.sha512,
       pf.size,
       pf.changelog,
       pf.created_at,
       pf.updated_at,
       pf.released,
       pf.release_type,
       pf.classifier,
       pf.processing_status,
       pf.processing_status_changed,
       pf.project_id,
       p.project_type_slug,
       p.game_slug,
       pf.user_id,
       u.username as username
FROM project_files pf
         JOIN projects p ON (p.id = pf.project_id)
         JOIN users u ON (u.id = pf.user_id)
         JOIN project_file_game_versions pfgv ON (pf.id = pfgv.project_file_id)
         JOIN game_versions gv ON (pfgv.game_version_id = gv.id)
WHERE p.game_slug = ?
  AND p.project_type_slug = ?
  AND p.slug = ?
  AND (pf.released = TRUE OR ?)
  AND gv.version = ?
ORDER BY '%sort%' '%order%'
LIMIT ?, ?;