SELECT p.id,
       p.name,
       p.slug,
       p.summary,
       p.description,
       p.cached_downloads,
       p.created_at,
       p.updated_at,
       p.game_slug,
       p.project_type_slug,
       p.released,
       p.review,
       p.user_id,
       u.username,
       u.created_at AS user_created_at
FROM projects p,
     users u
WHERE u.username = ?
  AND (p.user_id = u.id OR EXISTS(
        SELECT *
        FROM project_authors
        WHERE p.user_id = u.id
    ))
LIMIT 20;
