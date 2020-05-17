SELECT t.id,
       t.name,
       t.slug,
       t.project_type_slug,
       t.game_slug
FROM tags t,
     projects p,
     project_tags pt
WHERE t.id = pt.tag_id
  AND p.id = pt.project_id
  AND p.id = ?
