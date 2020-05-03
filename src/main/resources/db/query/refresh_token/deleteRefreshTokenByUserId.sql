DELETE
FROM PersistedGrants
WHERE SubjectId = ?
  AND Type = 'refresh_token';