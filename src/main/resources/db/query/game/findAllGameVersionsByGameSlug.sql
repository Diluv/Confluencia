SELECT id,
       game_slug,
       version,
       type,
       released
FROM game_versions
WHERE game_slug = ?
ORDER BY released DESC;