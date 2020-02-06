INSERT INTO email_blacklist (email)
VALUES ('blacklisted@example.com');

INSERT INTO email_domain_blacklist (domain)
VALUES ('diluv.com');

INSERT INTO email_sent(message_id, email, type, sent_at)
VALUES ('e4a291f7-740a-4b88-bc32-63e97e2d0812', 'test@example.com', 'test', NOW());