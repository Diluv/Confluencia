SELECT g.slug,
       g.name,
       g.url
FROM games g
ORDER BY '%sort%' '%order%';