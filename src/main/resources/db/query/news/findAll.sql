SELECT n.slug,
       n.title,
       n.summary,
       n.description,
       n.user_id,
       u.username,
       u.display_name,
       n.created_at
FROM news n
         JOIN users u ON (n.user_id = u.id)
ORDER BY '%sort%' '%order%'
LIMIT ?, ?;
