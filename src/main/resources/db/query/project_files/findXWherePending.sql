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
WHERE pf.status = 0
  AND pf.released = FALSE
ORDER BY created_at
LIMIT ?;