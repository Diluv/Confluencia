SELECT pf.id,
       pf.name,
       pf.sha512,
       pf.size,
       pf.changelog,
       pf.created_at,
       pf.updated_at,
       pf.released,
       pf.project_id,
       pf.user_id,
       u.username as username
FROM project_files pf,
     projects p,
     users u
WHERE pf.released = TRUE
  AND pf.project_id = p.id
  AND u.id = pf.user_id
  AND p.game_slug = ?
  AND p.project_type_slug = ?
  AND p.slug = ?
LIMIT 20;
