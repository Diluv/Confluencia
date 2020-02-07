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
WHERE status = 'pending'
  AND pf.user_id = u.id
ORDER BY created_at
LIMIT ?;