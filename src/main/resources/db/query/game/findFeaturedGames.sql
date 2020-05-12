SELECT g.slug,
       g.name,
       g.url
FROM featured_games fg
         JOIN games g ON (fg.slug = g.slug)
ORDER BY RAND()
LIMIT 4;