INSERT INTO project_files(id, name, size, changelog, status, released, project_id, user_id)
VALUES (1, 'ProjectFile.jar', 100, 'Project file changelog', 'done', 1, 1, 1);

INSERT INTO project_files(id, name, size, changelog, status, released, project_id, user_id)
VALUES (2, 'ProjectFile2.jar', 101, 'Project file changelog', 'done', 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, status, released, project_id, user_id)
VALUES (3, 'ProjectFile.jar', 1000, 'Changing', 'pending', 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, status, released, project_id, user_id)
VALUES (4, 'ProjectFile.jar', 1000, 'Changing', 'running', 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, status, released, project_id, user_id)
VALUES (5, 'ProjectFile.jar', 1000, 'Changing', 'error', 0, 1, 1);

INSERT INTO project_file_hash(project_file_id, sha512)
VALUES (1,
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3'),
       (2,
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3');