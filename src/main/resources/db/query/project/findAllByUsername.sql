SELECT p.name,
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
WHERE u.username = ?
  AND (p.user_id = u.id OR EXISTS(
        SELECT *
        FROM project_authors
        WHERE author_id = u.id
    ))
  AND p.game_slug = g.slug
LIMIT 20;
