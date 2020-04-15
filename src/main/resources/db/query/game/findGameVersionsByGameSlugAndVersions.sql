SELECT id,
       game_slug,
       version,
       type,
       released
FROM game_versions
WHERE game_slug = ?
  AND version IN (?)
ORDER BY released DESC;