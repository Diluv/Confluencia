ALTER TABLE project_files
    ADD COLUMN display_name VARCHAR(255) NOT NULL DEFAULT (name) AFTER name;
