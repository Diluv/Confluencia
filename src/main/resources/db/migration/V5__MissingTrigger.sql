CREATE TRIGGER incrementFileDownloads
    AFTER INSERT
    ON project_file_downloads
    FOR EACH ROW
BEGIN
    UPDATE project_files SET downloads = downloads + 1 WHERE id = NEW.project_file_id;
    UPDATE projects
    SET cached_downloads = cached_downloads + 1
    WHERE id = (SELECT project_id FROM project_files where id = NEW.project_file_id);
END;