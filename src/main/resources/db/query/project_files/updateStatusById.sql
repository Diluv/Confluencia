UPDATE project_files
SET status        = 1,
    status_change = NOW()
WHERE id = ?;