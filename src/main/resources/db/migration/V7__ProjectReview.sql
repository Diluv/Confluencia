CREATE TABLE project_review
(
    id          BIGINT    NOT NULL AUTO_INCREMENT,
    project_id  BIGINT    NOT NULL,
    reviewed_by BIGINT    NOT NULL,

    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (reviewed_by) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE project_request_change
(
    project_review_id BIGINT   NOT NULL,
    reason            LONGTEXT NOT NULL,

    PRIMARY KEY (project_review_id),
    FOREIGN KEY (project_review_id) REFERENCES project_review (id) ON DELETE CASCADE ON UPDATE CASCADE
);