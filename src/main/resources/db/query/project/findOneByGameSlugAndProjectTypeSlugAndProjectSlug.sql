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
       p.reviewed,
       p.user_id
FROM projects p,
     games g,
     users u
WHERE p.user_id = u.id
  AND (p.game_slug = ? AND p.project_type_slug = ? AND p.slug = ?)
LIMIT 1;