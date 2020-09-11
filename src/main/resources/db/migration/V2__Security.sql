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
    FOREIGN KEY (message_id) REFERENCES email_sent (message_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE email_blocklist
(
    email  VARCHAR(255) NOT NULL,
    reason TEXT         NOT NULL,

    PRIMARY KEY (email)
);

CREATE TABLE email_domain_blocklist
(
    domain VARCHAR(255) NOT NULL,

    PRIMARY KEY (domain)
);

CREATE TABLE username_blocklist
(
    username VARCHAR(30) NOT NULL,

    PRIMARY KEY (username)
);

CREATE TABLE contains_username_blocklist
(
    username VARCHAR(30) NOT NULL,

    PRIMARY KEY (username)
);

CREATE TABLE user_mfa_recovery
(
    user_id BIGINT  NOT NULL,

    code    CHAR(8) NOT NULL,
    valid   BOOL DEFAULT TRUE,

    PRIMARY KEY (user_id, code),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_mfa_email
(
    user_id    BIGINT    NOT NULL,
    code       CHAR(8)   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY (user_id, code),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_compromised_passwords
(
    password_hash CHAR(40)  NOT NULL,
    occurrences   BIGINT    NOT NULL,
    last_updated  TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY (password_hash)
);

CREATE TABLE user_change_email
(
    user_id    BIGINT       NOT NULL,

    email      VARCHAR(255) NOT NULL,
    code       CHAR(8)      NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),

    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
)