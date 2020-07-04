SELECT COUNT(*)
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
    ));
