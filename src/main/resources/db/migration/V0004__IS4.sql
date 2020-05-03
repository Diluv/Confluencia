CREATE TABLE ApiResources
(
    Id           INT          NOT NULL AUTO_INCREMENT,
    Enabled      TINYINT(1)   NOT NULL DEFAULT TRUE,
    Name         VARCHAR(200) NOT NULL,
    DisplayName  VARCHAR(200),
    Description  VARCHAR(1000),
    Created      DATETIME     NOT NULL DEFAULT NOW(),
    Updated      DATETIME,
    LastAccessed DATETIME,
    NonEditable  TINYINT(1)   NOT NULL DEFAULT TRUE,
    PRIMARY KEY (Id)
);

CREATE TABLE Clients
(
    Id                                INT          NOT NULL AUTO_INCREMENT,
    Enabled                           TINYINT(1)   NOT NULL DEFAULT TRUE,
    ClientId                          VARCHAR(200) NOT NULL,
    ProtocolType                      VARCHAR(200) NOT NULL DEFAULT 'oidc',
    RequireClientSecret               TINYINT(1)   NOT NULL DEFAULT TRUE,
    ClientName                        VARCHAR(200),
    Description                       VARCHAR(1000),
    ClientUri                         VARCHAR(2000),
    LogoUri                           VARCHAR(2000),
    RequireConsent                    TINYINT(1)   NOT NULL DEFAULT TRUE,
    AllowRememberConsent              TINYINT(1)   NOT NULL DEFAULT TRUE,
    AlwaysIncludeUserClaimsInIdToken  TINYINT(1)   NOT NULL DEFAULT FALSE,
    RequirePkce                       TINYINT(1)   NOT NULL DEFAULT FALSE,
    AllowPlainTextPkce                TINYINT(1)   NOT NULL DEFAULT FALSE,
    AllowAccessTokensViaBrowser       TINYINT(1)   NOT NULL DEFAULT FALSE,
    FrontChannelLogoutUri             VARCHAR(2000),
    FrontChannelLogoutSessionRequired TINYINT(1)   NOT NULL DEFAULT TRUE,
    BackChannelLogoutUri              VARCHAR(2000),
    BackChannelLogoutSessionRequired  TINYINT(1)   NOT NULL DEFAULT TRUE,
    AllowOfflineAccess                TINYINT(1)   NOT NULL DEFAULT FALSE,
    IdentityTokenLifetime             INT          NOT NULL DEFAULT 300,
    AccessTokenLifetime               INT          NOT NULL DEFAULT 3600,
    AuthorizationCodeLifetime         INT          NOT NULL DEFAULT 300,
    ConsentLifetime                   INT,
    AbsoluteRefreshTokenLifetime      INT          NOT NULL DEFAULT 2592000,
    SlidingRefreshTokenLifetime       INT          NOT NULL DEFAULT 1296000,
    RefreshTokenUsage                 INT          NOT NULL DEFAULT 1,
    UpdateAccessTokenClaimsOnRefresh  TINYINT(1)   NOT NULL DEFAULT FALSE,
    RefreshTokenExpiration            INT          NOT NULL DEFAULT 1,
    AccessTokenType                   INT          NOT NULL DEFAULT 0,
    EnableLocalLogin                  TINYINT(1)   NOT NULL DEFAULT TRUE,
    IncludeJwtId                      TINYINT(1)   NOT NULL DEFAULT FALSE,
    AlwaysSendClientClaims            TINYINT(1)   NOT NULL DEFAULT FALSE,
    ClientClaimsPrefix                VARCHAR(200),
    PairWiseSubjectSalt               VARCHAR(200),
    Created                           DATETIME     NOT NULL DEFAULT NOW(),
    Updated                           DATETIME,
    LastAccessed                      DATETIME,
    UserSsoLifetime                   INT,
    UserCodeType                      VARCHAR(100),
    DeviceCodeLifetime                INT          NOT NULL DEFAULT 300,
    NonEditable                       TINYINT(1)   NOT NULL DEFAULT FALSE,
    PRIMARY KEY (Id)
);

CREATE TABLE IdentityResources
(
    Id                      INT          NOT NULL AUTO_INCREMENT,
    Enabled                 TINYINT(1)   NOT NULL DEFAULT TRUE,
    Name                    VARCHAR(200) NOT NULL,
    DisplayName             VARCHAR(200),
    Description             VARCHAR(1000),
    Required                TINYINT(1)   NOT NULL DEFAULT FALSE,
    Emphasize               TINYINT(1)   NOT NULL DEFAULT FALSE,
    ShowInDiscoveryDocument TINYINT(1)   NOT NULL DEFAULT FALSE,
    Created                 DATETIME     NOT NULL DEFAULT NOW(),
    Updated                 DATETIME,
    NonEditable             TINYINT(1)   NOT NULL DEFAULT TRUE,
    PRIMARY KEY (Id)
);

CREATE TABLE ApiClaims
(
    Id            INT          NOT NULL AUTO_INCREMENT,
    Type          VARCHAR(200) NOT NULL,
    ApiResourceId INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ApiResourceId) REFERENCES ApiResources (Id) ON DELETE CASCADE
);

CREATE TABLE ApiProperties
(
    Id            INT           NOT NULL AUTO_INCREMENT,
    `Key`         VARCHAR(250)  NOT NULL,
    Value         VARCHAR(2000) NOT NULL,
    ApiResourceId INT           NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ApiResourceId) REFERENCES ApiResources (Id) ON DELETE CASCADE
);

CREATE TABLE ApiScopes
(
    Id                      INT          NOT NULL AUTO_INCREMENT,
    Name                    VARCHAR(200) NOT NULL,
    DisplayName             VARCHAR(200),
    Description             VARCHAR(1000),
    Required                TINYINT(1)   NOT NULL DEFAULT FALSE,
    Emphasize               TINYINT(1)   NOT NULL DEFAULT FALSE,
    ShowInDiscoveryDocument TINYINT(1)   NOT NULL DEFAULT FALSE,
    ApiResourceId           INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ApiResourceId) REFERENCES ApiResources (Id) ON DELETE CASCADE
);

CREATE TABLE ApiSecrets
(
    Id            INT           NOT NULL AUTO_INCREMENT,
    Description   VARCHAR(1000),
    Value         VARCHAR(4000) NOT NULL,
    Expiration    DATETIME,
    Type          VARCHAR(250)  NOT NULL DEFAULT 'SharedSecret',
    Created       DATETIME      NOT NULL DEFAULT NOW(),
    ApiResourceId INT           NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ApiResourceId) REFERENCES ApiResources (Id) ON DELETE CASCADE
);

CREATE TABLE ClientClaims
(
    Id       INT          NOT NULL AUTO_INCREMENT,
    Type     VARCHAR(250) NOT NULL,
    Value    VARCHAR(250) NOT NULL,
    ClientId INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE ClientCorsOrigins
(
    Id       INT          NOT NULL AUTO_INCREMENT,
    Origin   VARCHAR(150) NOT NULL,
    ClientId INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE ClientGrantTypes
(
    Id        INT          NOT NULL AUTO_INCREMENT,
    GrantType VARCHAR(250) NOT NULL,
    ClientId  INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE ClientIdPRestrictions
(
    Id       INT          NOT NULL AUTO_INCREMENT,
    Provider VARCHAR(200) NOT NULL,
    ClientId INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE ClientPostLogoutRedirectUris
(
    Id                    INT           NOT NULL AUTO_INCREMENT,
    PostLogoutRedirectUri VARCHAR(2000) NOT NULL,
    ClientId              INT           NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE ClientProperties
(
    Id       INT           NOT NULL AUTO_INCREMENT,
    `Key`    VARCHAR(250)  NOT NULL,
    Value    VARCHAR(2000) NOT NULL,
    ClientId INT           NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE ClientRedirectUris
(
    Id          INT           NOT NULL AUTO_INCREMENT,
    RedirectUri VARCHAR(2000) NOT NULL,
    ClientId    INT           NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE ClientScopes
(
    Id       INT          NOT NULL AUTO_INCREMENT,
    Scope    VARCHAR(200) NOT NULL,
    ClientId INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE ClientSecrets
(
    Id          INT           NOT NULL AUTO_INCREMENT,
    Description VARCHAR(2000) NULL,
    Value       VARCHAR(4000) NOT NULL,
    Expiration  DATETIME      NULL,
    Type        VARCHAR(250)  NOT NULL,
    Created     DATETIME      NOT NULL,
    ClientId    INT           NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClientId) REFERENCES Clients (Id) ON DELETE CASCADE
);

CREATE TABLE IdentityClaims
(
    Id                 INT          NOT NULL AUTO_INCREMENT,
    Type               VARCHAR(200) NOT NULL,
    IdentityResourceId INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (IdentityResourceId) REFERENCES IdentityResources (Id) ON DELETE CASCADE
);

CREATE TABLE IdentityProperties
(
    Id                 INT           NOT NULL AUTO_INCREMENT,
    `Key`              VARCHAR(250)  NOT NULL,
    Value              VARCHAR(2000) NOT NULL,
    IdentityResourceId INT           NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (IdentityResourceId) REFERENCES IdentityResources (Id) ON DELETE CASCADE
);

CREATE TABLE ApiScopeClaims
(
    Id         INT          NOT NULL AUTO_INCREMENT,
    Type       VARCHAR(200) NOT NULL,
    ApiScopeId INT          NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ApiScopeId) REFERENCES ApiScopes (Id) ON DELETE CASCADE
);

CREATE TABLE DeviceCodes
(
    UserCode     VARCHAR(200) NOT NULL,
    DeviceCode   VARCHAR(200) NOT NULL,
    SubjectId    VARCHAR(200),
    SessionId    VARCHAR(100),
    ClientId     VARCHAR(200) NOT NULL,
    Description  VARCHAR(200),
    CreationTime DATETIME     NOT NULL,
    Expiration   DATETIME     NOT NULL,
    Data         LONGTEXT     NOT NULL,
    PRIMARY KEY (UserCode)
);

CREATE TABLE PersistedGrants
(
    `Key`        VARCHAR(200) NOT NULL,
    Type         VARCHAR(50)  NOT NULL,
    SubjectId    VARCHAR(200),
    SessionId    VARCHAR(100),
    ClientId     VARCHAR(200) NOT NULL,
    Description  VARCHAR(200),
    CreationTime DATETIME     NOT NULL,
    Expiration   DATETIME,
    Data         LONGTEXT     NOT NULL,
    PRIMARY KEY (`Key`)
);

CREATE TABLE reference_token_permissions
(
    `Key`      VARCHAR(200) NOT NULL,

    permission VARCHAR(255) NOT NULL,

    PRIMARY KEY (`Key`, permission),
    FOREIGN KEY (`Key`) REFERENCES PersistedGrants (`Key`) ON DELETE CASCADE
);