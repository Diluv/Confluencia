SELECT gv.id,
       gv.game_slug,
       gv.version,
       gv.type,
       gv.released
FROM game_versions gv,
     project_files pf,
     project_file_game_versions pfgv
WHERE pf.id = pfgv.project_file_id
  AND gv.id = pfgv.game_version_id
  AND pf.id = ?
ORDER BY gv.released DESC;