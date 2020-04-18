CREATE TABLE AspNetRoles
(
    Id               VARCHAR(255) NOT NULL,
    ConcurrencyStamp LONGTEXT,
    Name             VARCHAR(256),
    NormalizedName   VARCHAR(256) UNIQUE,
    PRIMARY KEY (Id)
);

CREATE TABLE AspNetRoleClaims
(
    Id         INT AUTO_INCREMENT,
    ClaimType  LONGTEXT,
    ClaimValue LONGTEXT,
    RoleId     VARCHAR(255) NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (RoleId) references AspNetRoles (Id) ON DELETE CASCADE
);

CREATE TABLE AspNetUsers
(
    Id                   VARCHAR(255) NOT NULL,
    AccessFailedCount    INT          NOT NULL,
    ConcurrencyStamp     LONGTEXT,
    Email                VARCHAR(256),
    EmailConfirmed       tinyINT(1)   NOT NULL,
    LockoutEnabled       tinyINT(1)   NOT NULL,
    LockoutEnd           datetime(6),
    NormalizedEmail      VARCHAR(256),
    NormalizedUserName   VARCHAR(256) UNIQUE,
    PasswordHash         LONGTEXT,
    PhoneNumber          LONGTEXT,
    PhoneNumberConfirmed tinyINT(1)   NOT NULL,
    SecurityStamp        LONGTEXT,
    TwoFactorEnabled     tinyINT(1)   NOT NULL,
    UserName             VARCHAR(256),
    PRIMARY KEY (Id)
);

CREATE TABLE AspNetUserClaims
(
    Id         INT          NOT NULL AUTO_INCREMENT,
    ClaimType  LONGTEXT,
    ClaimValue LONGTEXT,
    UserId     VARCHAR(255) NOT NULL,

    PRIMARY KEY (Id),
    FOREIGN KEY (UserId) references AspNetUsers (Id) ON DELETE CASCADE
);

CREATE TABLE AspNetUserLogins
(
    LoginProvider       VARCHAR(255) NOT NULL,
    ProviderKey         VARCHAR(255) NOT NULL,
    ProviderDisplayName LONGTEXT,
    UserId              VARCHAR(255) NOT NULL,
    PRIMARY KEY (LoginProvider, ProviderKey),
    FOREIGN KEY (UserId) references AspNetUsers (Id) ON DELETE CASCADE
);

CREATE TABLE AspNetUserRoles
(
    UserId VARCHAR(255) NOT NULL,
    RoleId VARCHAR(255) NOT NULL,
    PRIMARY KEY (UserId, RoleId),
    FOREIGN KEY (RoleId) references AspNetRoles (Id) ON DELETE CASCADE,
    FOREIGN KEY (UserId) references AspNetUsers (Id) ON DELETE CASCADE
);

CREATE TABLE AspNetUserTokens
(
    UserId        VARCHAR(255) NOT NULL,
    LoginProvider VARCHAR(255) NOT NULL,
    Name          VARCHAR(255) NOT NULL,
    Value         LONGTEXT,
    PRIMARY KEY (UserId, LoginProvider, Name),
    FOREIGN KEY (UserId) REFERENCES AspNetUsers (Id) ON DELETE CASCADE
);


