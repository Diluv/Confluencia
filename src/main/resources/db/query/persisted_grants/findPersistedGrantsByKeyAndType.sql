SELECT `Key`,
       Type,
       SubjectId,
       SessionId,
       ClientId,
       Description,
       CreationTime,
       Expiration,
       Data
FROM PersistedGrants
WHERE `Key` = ?
  AND Type = ?