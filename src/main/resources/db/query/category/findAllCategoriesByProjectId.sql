SELECT c.id,
       c.name,
       c.slug,
       c.icon_url,
       c.project_type_slug,
       c.game_slug
FROM categories c,
     projects p,
     project_categories pc
WHERE c.id = pc.categories_id
  AND p.id = pc.project_id
  AND p.id = ?
