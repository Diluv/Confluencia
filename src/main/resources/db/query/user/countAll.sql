SELECT COUNT(*)
FROM (
         SELECT user_id
         FROM projects
         WHERE released = TRUE
         UNION
         SELECT user_id
         FROM project_authors
     ) users