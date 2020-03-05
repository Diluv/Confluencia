SELECT id,
       game_slug,
       version,
       changelog_url
FROM game_versions
WHERE game_slug = ?;