UPDATE project_files
SET processing_status        = ?,
    processing_status_changed = NOW()
WHERE id = ?;