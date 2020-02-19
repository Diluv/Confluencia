CREATE TABLE user_compromised_passwords
(
    password_hash CHAR(40) NOT NULL,
    occurrences   BIGINT   NOT NULL,

    PRIMARY KEY (password_hash)
);

CREATE TABLE user_mfa_recoveries
(
    user_id  BIGINT UNSIGNED NOT NULL,

    2fa_code CHAR(8)         NOT NULL,
    valid    BOOL DEFAULT TRUE,

    PRIMARY KEY (user_id, 2fa_code),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE game_modloaders
(
    id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,

    game_slug VARCHAR(200)    NOT NULL,
    name      VARCHAR(255)    NOT NULL,

    url       VARCHAR(255)    NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (game_slug) REFERENCES games (slug)
);

CREATE TABLE game_versions
(
    id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,

    game_slug VARCHAR(200)    NOT NULL,
    version   VARCHAR(255)    NOT NULL,

    url       VARCHAR(255)    NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (game_slug) REFERENCES games (slug)
);

CREATE TABLE project_links
(
    project_id BIGINT UNSIGNED NOT NULL,
    type       VARCHAR(255)    NOT NULL,
    url        VARCHAR(255)    NOT NULL,

    PRIMARY KEY (project_id, type),
    FOREIGN KEY (project_id) REFERENCES projects (id)
);

CREATE TABLE project_file_versions
(
    project_file_id BIGINT UNSIGNED NOT NULL,
    game_version_id BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (project_file_id, game_version_id),
    FOREIGN KEY (project_file_id) REFERENCES project_files (id),
    FOREIGN KEY (game_version_id) REFERENCES game_versions (id)
);

CREATE TABLE project_file_modloaders
(
    project_file_id BIGINT UNSIGNED NOT NULL,
    modloader_id    BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (project_file_id, modloader_id),
    FOREIGN KEY (project_file_id) REFERENCES project_files (id),
    FOREIGN KEY (modloader_id) REFERENCES game_modloaders (id)
);