UPDATE project_files
SET status             = 'running',
    status_change_time = NOW()
WHERE id = ?;