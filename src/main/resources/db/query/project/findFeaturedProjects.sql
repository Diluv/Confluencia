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
       u.display_name,
       u.created_at AS user_created_at
FROM featured_projects fp
         JOIN projects p ON (fp.project_id = p.id)
         JOIN users u ON (u.id = p.user_id)
ORDER BY RAND()
LIMIT 4;
