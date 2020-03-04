# Offset is used as not a huge amount of games
SELECT slug,
       name,
       url
FROM games
ORDER BY name
LIMIT ?, ?;