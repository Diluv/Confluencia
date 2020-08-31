ALTER TABLE project_file_dependencies
    ADD type VARCHAR(50) DEFAULT 'required' AFTER dependency_project_id;