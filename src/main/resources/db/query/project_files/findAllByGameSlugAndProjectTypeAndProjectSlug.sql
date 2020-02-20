SELECT pf.id,
       pf.name,
       pf.sha512,
       pf.size,
       pf.changelog,
       pf.created_at,
       pf.updated_at,
       pf.released,
       pf.status,
       pf.status_change,
       pf.project_id,
       pf.user_id,
       u.username as username
FROM project_files pf
         JOIN projects p ON (p.id = pf.project_id)
         JOIN users u ON (u.id = pf.user_id)
WHERE pf.released = TRUE
  AND p.game_slug = ?
  AND p.project_type_slug = ?
  AND p.slug = ?
LIMIT 20;