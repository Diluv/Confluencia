CREATE TABLE project_file_downloads
(
    project_file_id BIGINT    NOT NULL,
    ip              CHAR(128) NOT NULL,

    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY (project_file_id, ip),
    FOREIGN KEY (project_file_id) REFERENCES project_files (id)
);

ALTER TABLE project_files
    ADD downloads BIGINT DEFAULT 0 AFTER sha512;

CREATE TRIGGER incrementFileDownloads
    AFTER INSERT
    ON project_file_downloads
    FOR EACH ROW
BEGIN
    UPDATE project_files SET downloads = downloads + 1 WHERE id = NEW.project_file_id;
END;