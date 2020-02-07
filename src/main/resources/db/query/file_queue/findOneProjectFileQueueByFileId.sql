SELECT pf.id,
       pf.name,
       pf.changelog,
       pf.created_at,
       pf.project_id,
       pf.user_id,
       pf.size,
       pf.status,
       pf.status_change_time,
       u.username
FROM project_file_queue pf,
     users u
WHERE pf.id = ?
  AND pf.user_id = u.id
LIMIT 1;