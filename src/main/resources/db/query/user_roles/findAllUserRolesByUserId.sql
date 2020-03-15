SELECT r.id,
       r.name,
       GROUP_CONCAT(rp.permission SEPARATOR ' ') AS permissions
FROM user_roles ur
         LEFT OUTER JOIN roles r on ur.role_id = r.id
         LEFT OUTER JOIN role_permissions rp on r.id = rp.role_id
WHERE ur.user_id = ?
GROUP BY r.id;
