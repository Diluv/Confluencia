SELECT pf.id,
       pf.name,
       pf.sha512,
       pf.size,
       pf.changelog,
       pf.created_at,
       pf.updated_at,
       pf.released,
       pf.release_type,
       pf.classifier,
       pf.processing_status,
       pf.processing_status_changed,
       pf.project_id,
       p.project_type_slug,
       p.game_slug,
       pf.user_id,
       u.username     AS username,
       u.display_name AS display_name
FROM project_files pf
         JOIN projects p ON (p.id = pf.project_id)
         JOIN users u ON (u.id = pf.user_id)
WHERE pf.processing_status = ?
ORDER BY created_at
LIMIT ?;