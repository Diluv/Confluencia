CREATE TABLE nodecdn_commits
(
    id         BIGINT    NOT NULL AUTO_INCREMENT,

    hash       CHAR(36)  NOT NULL UNIQUE,

    completed  BOOL      NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY (ID)
);