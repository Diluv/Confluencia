SELECT p.id,
       p.name,
       p.slug,
       p.summary,
       p.description,
       p.cached_downloads,
       p.created_at,
       p.updated_at,
       p.game_slug,
       g.name       AS game_name,
       p.project_type_slug,
       pt.name      AS project_type_name,
       p.released,
       p.review,
       p.user_id,
       u.username,
       u.display_name,
       u.created_at AS user_created_at
FROM projects p
         JOIN users u
         JOIN games g ON (p.game_slug = g.slug)
         JOIN project_types pt ON (p.project_type_slug = pt.slug)
WHERE u.username = ?
  AND (p.released = TRUE OR ?)
  AND (p.user_id = u.id OR EXISTS(
        SELECT *
        FROM project_authors
        WHERE p.user_id = u.id
    ))
ORDER BY '%sort%' '%order%'
LIMIT ?, ?;
