INSERT INTO user_compromised_passwords(password_hash, occurrences, last_updated)
VALUES (?, ?, NOW())