CREATE TABLE user_mfa_recoveries
(
    user_id  BIGINT  NOT NULL,

    2fa_code CHAR(8) NOT NULL,
    valid    BOOL DEFAULT TRUE,

    PRIMARY KEY (user_id, 2fa_code),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE project_links
(
    project_id BIGINT       NOT NULL,
    type       VARCHAR(255) NOT NULL,
    url        VARCHAR(255) NOT NULL,

    PRIMARY KEY (project_id, type),
    FOREIGN KEY (project_id) REFERENCES projects (id)
);