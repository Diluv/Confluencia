# This will be updated up until production
CREATE TABLE users
(
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username      VARCHAR(30)     NOT NULL UNIQUE,
    email         VARCHAR(255)    NOT NULL UNIQUE,
    password      CHAR(60)        NOT NULL,
    password_type VARCHAR(30)     NOT NULL,

    mfa           BOOL                     DEFAULT FALSE,
    mfa_secret    VARCHAR(16),

    created_at    TIMESTAMP       NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id)
);

CREATE TABLE temp_users
(
    id               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username         VARCHAR(30)     NOT NULL UNIQUE,
    email            VARCHAR(255)    NOT NULL UNIQUE,
    password         CHAR(60)        NOT NULL,
    password_type    VARCHAR(30)     NOT NULL,
    created_at       TIMESTAMP       NOT NULL DEFAULT NOW(),

    verificationCode CHAR(36)        NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE refresh_tokens
(
    user_id    BIGINT UNSIGNED NOT NULL,
    code       CHAR(36)        NOT NULL,

    expired_at TIMESTAMP       NOT NULL NOT NULL,

    PRIMARY KEY (user_id, code)
);

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

CREATE TABLE games
(
    slug VARCHAR(200) NOT NULL,

    name VARCHAR(255) NOT NULL,
    url  VARCHAR(255) NOT NULL,

    PRIMARY KEY (slug)
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

CREATE TABLE project_types
(
    game_slug VARCHAR(200)    NOT NULL,
    slug      VARCHAR(200)    NOT NULL,

    name      VARCHAR(200)    NOT NULL,

    max_size  BIGINT UNSIGNED NOT NULL DEFAULT 5000000,

    PRIMARY KEY (game_slug, slug),
    FOREIGN KEY (game_slug) REFERENCES games (slug)
);

# Project
CREATE TABLE projects
(
    id                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name              VARCHAR(255)    NOT NULL,
    slug              VARCHAR(255)    NOT NULL,
    summary           VARCHAR(255)    NOT NULL,
    description       TEXT            NOT NULL,
    cached_downloads  BIGINT UNSIGNED NOT NULL DEFAULT 0,

    reviewed          BOOL                     DEFAULT FALSE,
    released          BOOL                     DEFAULT FALSE,

    created_at        TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at        TIMESTAMP       NOT NULL DEFAULT NOW() ON UPDATE NOW(),

    user_id           BIGINT UNSIGNED NOT NULL,
    game_slug         VARCHAR(200)    NOT NULL,
    project_type_slug VARCHAR(200)    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (game_slug, project_type_slug) REFERENCES project_types (game_slug, slug),
    FOREIGN KEY (game_slug) REFERENCES games (slug)
);

CREATE TABLE project_authors
(
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,

    project_id    BIGINT UNSIGNED NOT NULL,
    author_id     BIGINT UNSIGNED NOT NULL,

    role          VARCHAR(255)    NOT NULL,

    uploaded_file BOOL            NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES projects (id),
    FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE project_author_permissions
(
    project_author_id BIGINT UNSIGNED NOT NULL,
    permission        VARCHAR(255)    NOT NULL,

    PRIMARY KEY (project_author_id, permission),
    FOREIGN KEY (project_author_id) REFERENCES project_authors (id)
);

CREATE TABLE project_links
(
    project_id BIGINT UNSIGNED NOT NULL,
    type       VARCHAR(255)    NOT NULL,
    url        VARCHAR(255)    NOT NULL,

    PRIMARY KEY (project_id, type),
    FOREIGN KEY (project_id) REFERENCES projects (id)
);

CREATE TABLE project_files
(
    id                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,

    name               VARCHAR(255)    NOT NULL,
    size               BIGINT UNSIGNED NOT NULL,

    changelog          TEXT            NOT NULL,
    created_at         TIMESTAMP       NOT NULL                     DEFAULT NOW(),
    updated_at         TIMESTAMP       NOT NULL                     DEFAULT NOW() ON UPDATE NOW(),

    released           BOOL            NOT NULL                     DEFAULT FALSE,

    status             ENUM ('pending', 'running', 'done', 'error') DEFAULT 'pending',
    status_change_time TIMESTAMP       NOT NULL                     DEFAULT NOW(),

    project_id         BIGINT UNSIGNED NOT NULL,
    user_id            BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES projects (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE project_file_hash
(
    project_file_id BIGINT UNSIGNED NOT NULL,
    sha512          VARCHAR(128)    NOT NULL,

    PRIMARY KEY (project_file_id),
    FOREIGN KEY (project_file_id) REFERENCES project_files (id)
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

# Insert default data
INSERT IGNORE INTO games(slug, name, url)
VALUES ('minecraft', 'Minecraft', 'https://minecraft.net/');

INSERT IGNORE INTO project_types(game_slug, slug, name, max_size)
VALUES ('minecraft', 'mods', 'Mods', 25000000);