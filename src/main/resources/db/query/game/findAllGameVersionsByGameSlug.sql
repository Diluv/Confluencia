SELECT gv.id,
       gv.game_slug,
       gv.version,
       gv.type,
       gv.released
FROM game_versions gv
WHERE gv.game_slug = ?
ORDER BY gv.released DESC;