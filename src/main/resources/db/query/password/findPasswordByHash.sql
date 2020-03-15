SELECT password_hash,
       occurrences,
       last_updated
FROM user_compromised_passwords
WHERE password_hash = ?
LIMIT 1;