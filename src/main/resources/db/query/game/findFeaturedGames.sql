SELECT g.slug,
       g.name,
       g.url,
       gdpt.project_type_slug AS default_project_type
FROM featured_games fg
         JOIN games g ON (fg.slug = g.slug)
         JOIN game_default_project_type gdpt ON (g.slug = gdpt.game_slug)
GROUP BY (fg.slug)
ORDER BY RAND()
LIMIT 4;