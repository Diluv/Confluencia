SELECT name,
       slug,
       icon_url,
       project_type_slug,
       game_slug
FROM categories
WHERE game_slug = ?
  AND project_type_slug = ?
LIMIT ?, ?;
