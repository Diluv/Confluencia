INSERT INTO email_blacklist (email)
VALUES ('blacklisted@example.com');

INSERT INTO email_domain_blacklist (domain)
VALUES ('diluv.com');

SELECT *
FROM email_blacklist;