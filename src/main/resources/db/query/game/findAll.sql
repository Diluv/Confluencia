SELECT g.slug,
       g.name,
       g.url,
       g.default_project_type
FROM games g
ORDER BY '%sort%' '%order%';