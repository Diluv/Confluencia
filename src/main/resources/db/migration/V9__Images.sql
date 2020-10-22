CREATE TABLE images
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    url        VARCHAR(800) NOT NULL,

    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    released   BOOL         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id)
);

ALTER TABLE project_files
    ADD COLUMN deleted BOOL DEFAULT FALSE;