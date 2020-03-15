INSERT INTO user_compromised_passwords(password_hash, occurrences, last_updated)
VALUES (?, ?, NOW())
ON DUPLICATE KEY UPDATE occurrences=? AND last_updated = NOW();