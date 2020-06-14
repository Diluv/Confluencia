SELECT p.id,
       p.name,
       p.slug,
       p.summary,
       p.description,
       p.cached_downloads,
       p.created_at,
       p.updated_at,
       p.game_slug,
       (SELECT g.name
        FROM games g
        WHERE g.slug = p.game_slug)          AS game_name,
       p.project_type_slug,
       (SELECT pt.name
        FROM project_types pt
        WHERE pt.game_slug = p.game_slug
          AND pt.slug = p.project_type_slug) AS project_type_name,
       p.released,
       p.review,
       p.user_id,
       u.username,
       u.display_name,
       u.created_at                          AS user_created_at
FROM projects p
         JOIN users u ON (u.id = p.user_id)
WHERE p.released = TRUE
  AND p.game_slug = ?
  AND p.project_type_slug = ?
  AND (? OR p.id IN (SELECT pf.project_id
                     FROM project_files pf
                              JOIN project_file_game_versions pfgv ON (pf.id = pfgv.project_file_id)
                              JOIN game_versions gv ON (pfgv.game_version_id = gv.id)
                     WHERE gv.version = ?
                       AND gv.game_slug = p.game_slug))
  AND (? OR p.id IN (SELECT pt.project_id
                     FROM project_tags pt
                              JOIN tags t ON (pt.tag_id = t.id)
                     WHERE t.slug IN (?)
                       AND t.game_slug = p.game_slug
                       AND t.project_type_slug = p.project_type_slug
                     GROUP BY pt.project_id
                     HAVING COUNT(pt.project_id) = ?))
  AND p.name LIKE ?
ORDER BY '%sort%' '%order%'
LIMIT ?, ?