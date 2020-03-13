SELECT slug,
       name,
       url,
       image_url,
       banner_url
FROM games
ORDER BY '%sort%' '%order%';