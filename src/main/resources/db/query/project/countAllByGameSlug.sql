SELECT COUNT(p.id)
FROM projects p
WHERE p.game_slug = ?
  AND p.released = TRUE;