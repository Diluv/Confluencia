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

CREATE TABLE games
(
    slug VARCHAR(200) NOT NULL,

    name VARCHAR(255) NOT NULL,
    url  VARCHAR(255) NOT NULL,

    PRIMARY KEY (slug)
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


CREATE TABLE news
(
    slug        VARCHAR(50)     NOT NULL,

    title       VARCHAR(255)    NOT NULL,
    summary     VARCHAR(255)    NOT NULL,

    description TEXT            NOT NULL,

    username    VARCHAR(255)    NOT NULL,

    created_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    PRIMARY KEY (slug)
);

# Insert default data
INSERT IGNORE INTO games(slug, name, url)
VALUES ('minecraft', 'Minecraft', 'https://minecraft.net/');

INSERT IGNORE INTO project_types(game_slug, slug, name, max_size)
VALUES ('minecraft', 'mods', 'Mods', 25000000);