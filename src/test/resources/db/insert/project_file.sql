INSERT INTO project_files(id, name, sha512, size, changelog, created_at, updated_at, released, project_id, user_id)
VALUES (1, 'ProjectFile.jar',
        '38E9C3BCDE3C6ADF2F95CF93C8EA794982B3B145306A41FADF1431A1AF3131B56F7C385F9BE6015F6F477EF84FA876AA693799418FF089397010115C3642DCC5',
        100, 'Project file changelog', NOW(), NOW(), 1, 1, 1);

INSERT INTO project_files(id, name, sha512, size, changelog, created_at, updated_at, released, project_id, user_id)
VALUES (2, 'ProjectFile2.jar',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        101, 'Project file changelog', NOW(), NOW(), 0, 1, 1);