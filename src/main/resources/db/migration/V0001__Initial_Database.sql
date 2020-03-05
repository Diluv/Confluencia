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

    expired_at TIMESTAMP       NOT NULL,

    PRIMARY KEY (user_id, code)
);

CREATE TABLE api_tokens
(
    user_id BIGINT UNSIGNED NOT NULL,
    code    CHAR(36)        NOT NULL,

    name    VARCHAR(20)     NOT NULL,

    PRIMARY KEY (user_id, code)
);

CREATE TABLE api_token_permissions
(
    user_id    BIGINT UNSIGNED NOT NULL,
    code       CHAR(36)        NOT NULL,

    permission VARCHAR(255)    NOT NULL,

    PRIMARY KEY (user_id, code, permission),
    FOREIGN KEY (user_id, code) REFERENCES api_tokens (user_id, code) ON DELETE CASCADE
);

CREATE TABLE games
(
    slug       VARCHAR(200) NOT NULL,

    name       VARCHAR(255) NOT NULL,
    url        VARCHAR(255) NOT NULL,

    banner_url VARCHAR(255) NOT NULL,

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

CREATE TABLE categories
(
    game_slug         VARCHAR(200) NOT NULL,
    project_type_slug VARCHAR(200) NOT NULL,
    slug              VARCHAR(200) NOT NULL,

    name              VARCHAR(200) NOT NULL,
    icon_url          VARCHAR(255) NOT NULL,

    PRIMARY KEY (game_slug, project_type_slug, slug),
    FOREIGN KEY (game_slug) REFERENCES games (slug),
    FOREIGN KEY (game_slug, project_type_slug) REFERENCES project_types (game_slug, slug)
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

    review            BOOL                     DEFAULT FALSE,
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
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,

    project_id BIGINT UNSIGNED NOT NULL,
    user_id    BIGINT UNSIGNED NOT NULL,

    role       VARCHAR(255)    NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES projects (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE project_author_permissions
(
    project_author_id BIGINT UNSIGNED NOT NULL,
    permission        VARCHAR(255)    NOT NULL,

    PRIMARY KEY (project_author_id, permission),
    FOREIGN KEY (project_author_id) REFERENCES project_authors (id)
);

CREATE TABLE project_files
(
    id                        BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,

    name                      VARCHAR(255)     NOT NULL,
    size                      BIGINT UNSIGNED  NOT NULL,
    sha512                    VARCHAR(128)     NOT NULL,

    changelog                 TEXT             NOT NULL,
    created_at                TIMESTAMP        NOT NULL DEFAULT NOW(),
    updated_at                TIMESTAMP        NOT NULL DEFAULT NOW() ON UPDATE NOW(),

    release_type              VARCHAR(255)     NOT NULL,
    classifier                VARCHAR(255)     NOT NULL,

    processing_status         TINYINT UNSIGNED NOT NULL DEFAULT 0,
    processing_status_changed TIMESTAMP        NOT NULL DEFAULT NOW(),
    released                  BOOL             NOT NULL DEFAULT FALSE,

    project_id                BIGINT UNSIGNED  NOT NULL,
    user_id                   BIGINT UNSIGNED  NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES projects (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE project_file_antivirus
(
    project_file_id BIGINT UNSIGNED NOT NULL,
    malware         VARCHAR(128)    NOT NULL,

    PRIMARY KEY (project_file_id),
    FOREIGN KEY (project_file_id) REFERENCES project_files (id)
);

CREATE TABLE news
(
    slug        VARCHAR(50)  NOT NULL,

    title       VARCHAR(255) NOT NULL,
    summary     VARCHAR(255) NOT NULL,

    description TEXT         NOT NULL,

    username    VARCHAR(255) NOT NULL,

    created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
    PRIMARY KEY (slug)
);

# Insert default data
INSERT IGNORE INTO games(slug, name, url, banner_url)
VALUES ('minecraft', 'Minecraft', 'https://minecraft.net/', 'https://images.placeholders.dev/?width=1200&height=150');

INSERT IGNORE INTO project_types(game_slug, slug, name, max_size)
VALUES ('minecraft', 'mods', 'Mods', 25000000);