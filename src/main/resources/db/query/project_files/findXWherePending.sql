SELECT pf.id,
       pf.name,
       pfh.sha512,
       pf.size,
       pf.changelog,
       pf.created_at,
       pf.updated_at,
       pf.released,
       pf.status,
       pf.status_change_time,
       pf.project_id,
       pf.user_id,
       u.username as username
FROM project_files pf
         JOIN projects p ON (p.id = pf.project_id)
         JOIN users u ON (u.id = pf.user_id)
         LEFT OUTER JOIN project_file_hash pfh ON (pf.id = pfh.project_file_id)
WHERE pf.status = 'pending'
  AND pf.released = FALSE
ORDER BY created_at
LIMIT ?;