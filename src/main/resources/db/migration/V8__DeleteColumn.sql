ALTER TABLE games
    ADD COLUMN deleted BOOL DEFAULT FALSE;
ALTER TABLE project_types
    ADD COLUMN deleted BOOL DEFAULT FALSE;
ALTER TABLE projects
    ADD COLUMN deleted BOOL DEFAULT FALSE;
ALTER TABLE users
    ADD COLUMN deleted BOOL DEFAULT FALSE;