SELECT p.id,
       p.name,
       p.slug,
       p.summary,
       p.description,
       p.cached_downloads,
       p.created_at,
       p.updated_at,
       p.game_slug,
       (SELECT g.name FROM games g WHERE g.slug = p.game_slug) AS game_name,
       p.project_type_slug,
       (SELECT pt.name
        FROM project_types pt
        WHERE pt.game_slug = p.game_slug
          AND pt.slug = p.project_type_slug)                   AS project_type_name,
       p.released,
       p.review,
       p.user_id,
       u.username,
       u.display_name,
       u.created_at                                            AS user_created_at
FROM projects p
         JOIN users u ON (u.id = p.user_id)
WHERE p.id IN (?);