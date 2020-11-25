ALTER TABLE projects
    MODIFY COLUMN review BOOL NOT NULL DEFAULT TRUE;

ALTER TABLE temp_users
    DROP COLUMN display_name,
    DROP COLUMN code;

DROP TABLE reference_token_permissions;

DROP TABLE PersistedGrants;

DROP TABLE DeviceCodes;

DROP TABLE ApiScopeClaims;

DROP TABLE IdentityResourceProperties;

DROP TABLE IdentityResourceClaims;

DROP TABLE ClientSecrets;

DROP TABLE ClientScopes;

DROP TABLE ClientRedirectUris;

DROP TABLE ClientProperties;

DROP TABLE ClientPostLogoutRedirectUris;

DROP TABLE ClientIdPRestrictions;

DROP TABLE ClientGrantTypes;

DROP TABLE ClientCorsOrigins;

DROP TABLE ClientClaims;

DROP TABLE ApiScopeProperties;

DROP TABLE ApiResourceSecrets;

DROP TABLE ApiResourceScopes;

DROP TABLE ApiScopes;

DROP TABLE ApiResourceProperties;

DROP TABLE ApiResourceClaims;

DROP TABLE IdentityResources;

DROP TABLE Clients;

DROP TABLE ApiResources;

CREATE TABLE api_tokens
(
    token      VARCHAR(128) NOT NULL,
    user_id    BIGINT       NOT NULL,

    name       VARCHAR(50)  NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),

    PRIMARY KEY (token),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE api_token_permissions
(
    token      VARCHAR(128) NOT NULL,
    permission VARCHAR(255) NOT NULL,

    PRIMARY KEY (token, permission),
    FOREIGN KEY (token) REFERENCES api_tokens (token) ON DELETE CASCADE
);