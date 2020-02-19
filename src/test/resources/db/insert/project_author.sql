INSERT INTO project_authors(id, project_id, user_id, role)
VALUES (1, 1, 2, 'Testing');

INSERT INTO project_author_permissions(project_author_id, permission)
VALUES (1, 'project.upload'),
       (1, 'project.edit');