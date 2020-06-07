SELECT pt.name,
       pt.slug,
       pt.game_slug,
       g.name                                AS game_name,
       pt.max_file_size,
       (SELECT COUNT(*)
        FROM projects p
        WHERE p.game_slug = g.slug
          AND p.project_type_slug = pt.slug) AS project_count
FROM project_types pt
         JOIN games g ON (pt.game_slug = g.slug)
WHERE pt.game_slug = ?;
