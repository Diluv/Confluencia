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
       p.user_id
FROM projects p,
     games g
WHERE p.released = TRUE
  AND p.game_slug = ?
  AND p.project_type_slug = ?
LIMIT 20;
