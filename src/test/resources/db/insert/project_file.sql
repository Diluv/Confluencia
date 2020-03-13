INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id,
                          user_id)
VALUES (1, 'ProjectFile.jar', 100, 'Project file changelog',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 2, 1, 1, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id,
                          user_id)
VALUES (2, 'ProjectFile2.jar', 101, 'Project file changelog',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 2, 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id,
                          user_id)
VALUES (3, 'ProjectFile.jar', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, 0, 2, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id,
                          user_id)
VALUES (4, 'ProjectFile.jar', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 1, 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id,
                          user_id)
VALUES (5, 'ProjectFile.jar', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 5, 0, 1, 1);

INSERT INTO project_file_game_versions(project_file_id, game_version_id)
VALUES (1, 6);