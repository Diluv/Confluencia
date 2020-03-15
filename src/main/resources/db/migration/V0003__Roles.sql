CREATE TABLE roles
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE role_permissions
(
    role_id    BIGINT       NOT NULL,
    permission VARCHAR(255) NOT NULL,

    PRIMARY KEY (role_id, permission),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);