CREATE TABLE notifications
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,

    text       TEXT         NOT NULL,
    type       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    viewed_at  TIMESTAMP    NULL,

    user_id    BIGINT       NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE notification_project_invites
(
    id         BIGINT       NOT NULL,
    status     VARCHAR(200) NOT NULL DEFAULT 'PENDING',

    role       VARCHAR(255) NOT NULL,

    project_id BIGINT       NOT NULL,
    sender_id  BIGINT       NOT NULL,

    updated_at TIMESTAMP    NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES notifications (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE notification_project_invite_permissions
(
    id         BIGINT       NOT NULL,
    permission VARCHAR(255) NOT NULL,

    PRIMARY KEY (id, permission),
    FOREIGN KEY (id) REFERENCES notification_project_invites (id) ON DELETE CASCADE ON UPDATE CASCADE
);
