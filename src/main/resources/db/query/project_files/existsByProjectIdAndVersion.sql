SELECT id
FROM project_files
WHERE project_id = ?
  AND version = ?
LIMIT 1;