SELECT project_id,
       type,
       url
FROM project_links
WHERE project_id = ?;