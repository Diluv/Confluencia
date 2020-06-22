CREATE TABLE email_sent
(
    message_id CHAR(36)     NOT NULL,
    email      VARCHAR(255) NOT NULL,
    type       TEXT         NOT NULL,

    sent_at    TIMESTAMP    NOT NULL DEFAULT NOW(),

    PRIMARY KEY (message_id)
);

CREATE TABLE email_bounce
(
    message_id  CHAR(36)  NOT NULL,

    bounce_type TEXT      NOT NULL,
    description TEXT      NOT NULL,

    bounced_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (message_id),
    FOREIGN KEY (message_id) REFERENCES email_sent (message_id)
);

CREATE TABLE email_blacklist
(
    email  VARCHAR(255) NOT NULL,
    reason TEXT         NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE email_domain_blacklist
(
    domain VARCHAR(255) NOT NULL,
    PRIMARY KEY (domain)
);

CREATE TABLE username_blacklist
(
    username VARCHAR(30) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE contains_username_blacklist
(
    username VARCHAR(30) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE user_compromised_passwords
(
    password_hash CHAR(40)  NOT NULL,
    occurrences   BIGINT    NOT NULL,
    last_updated  TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY (password_hash)
);