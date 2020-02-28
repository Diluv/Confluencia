SELECT u.id,
       u.username,
       u.created_at,
       pa.role,
       GROUP_CONCAT(pap.permission SEPARATOR ' ') AS permissions
FROM project_authors pa
         JOIN users u ON (u.id = pa.user_id)
         JOIN project_author_permissions pap ON (pap.project_author_id = pa.id)
WHERE pa.project_id = ?
GROUP BY pa.id