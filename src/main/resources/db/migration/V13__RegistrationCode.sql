CREATE TABLE registration_codes
(
    code  VARCHAR(36) NOT NULL,
    valid BOOL DEFAULT TRUE,

    PRIMARY KEY (code)
)