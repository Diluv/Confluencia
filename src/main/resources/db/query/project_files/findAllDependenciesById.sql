SELECT pfd.dependency_project_id
FROM project_file_dependencies pfd
WHERE pfd.project_file_id = ?;