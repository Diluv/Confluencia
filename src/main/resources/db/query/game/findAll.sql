# Offset is used as not a huge amount of games
SELECT slug,
       name,
       url,
       banner_url
FROM games
ORDER BY name
LIMIT ?, ?;