UPDATE project_files
SET status        = ?,
    status_change = NOW()
WHERE id = ?;