INSERT INTO projects(id, name, slug, summary, description, cached_downloads, review, released, created_at, updated_at,
                     user_id, game_slug, project_type_slug)
VALUES (1, 'Project 1', 'project_1', 'Project 1 Summary', 'Project 1 Description', 0, 0, 1, NOW(), NOW(), 1,
        'minecraft', 'mods');

INSERT INTO projects(id, name, slug, summary, description, cached_downloads, review, released, created_at, updated_at,
                     user_id, game_slug, project_type_slug)
VALUES (2, 'Unreleased 1', 'unreleased_1', 'Unreleased 1 Summary', 'Unreleased 1 Description', 0, 0, 0, NOW(), NOW(), 1,
        'minecraft', 'mods');
