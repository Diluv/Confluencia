DROP TABLE images;

CREATE TABLE images
(
    id         VARCHAR(50) NOT NULL,
    ext        VARCHAR(5)   NOT NULL,
    width      SMALLINT     NOT NULL,

    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    released   BOOL         NOT NULL DEFAULT FALSE,
    updated    BOOL         NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id, ext, width)
);