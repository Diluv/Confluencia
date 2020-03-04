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
FROM projects p
         JOIN users u on (u.id = p.user_id)
WHERE p.released = TRUE
  AND p.game_slug = ?
  AND p.project_type_slug = ?
  AND p.id > ?
LIMIT ?;
