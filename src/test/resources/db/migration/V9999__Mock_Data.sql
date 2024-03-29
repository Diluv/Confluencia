INSERT INTO games(slug, name, url)
VALUES ('minecraft-je', 'Minecraft Java Edition', 'https://www.minecraft.net'),
       ('minecraft-bedrock', 'Minecraft Bedrock Edition', 'https://www.minecraft.net'),
       ('subnautica', 'Subnautica', 'https://unknownworlds.com/subnautica/'),
       ('stardewvalley', 'Stardew Valley', 'https://unknownworlds.com/subnautica/');

INSERT INTO games(slug, name, url, deleted)
VALUES ('banned', 'Banned', 'https://www.example.com', TRUE);

INSERT INTO project_types(game_slug, slug, name, max_file_size)
VALUES ('minecraft-je', 'mods', 'Mods', 25000000),
       ('minecraft-je', 'maps', 'Maps', 25000000),
       ('minecraft-bedrock', 'mods', 'Mods', 25000000),
       ('subnautica', 'mods', 'Mods', 25000000),
       ('stardewvalley', 'mods', 'Mods', 25000000);

INSERT INTO game_default_project_type(game_slug, project_type_slug)
VALUES ('minecraft-je', 'mods'),
       ('minecraft-bedrock', 'mods'),
       ('subnautica', 'mods'),
       ('stardewvalley', 'mods');

INSERT INTO project_type_loaders(game_slug, project_type_slug, slug, name)
VALUES ('minecraft-je', 'mods', 'forge', 'Minecraft Forge'),
       ('minecraft-je', 'mods', 'fabric', 'Fabric');

INSERT INTO tags(game_slug, project_type_slug, slug, name)
VALUES ('minecraft-je', 'mods', 'tech', 'Tech'),
       ('minecraft-je', 'mods', 'magic', 'Magic'),
       ('subnautica', 'mods', 'magic', 'Magic');

INSERT INTO game_versions(game_slug, version, type, released)
VALUES ('minecraft-je', 'rd-132211', 'old_alpha', STR_TO_DATE('2009-05-13T20:11:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'rd-132328', 'old_alpha', STR_TO_DATE('2009-05-13T21:28:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'rd-20090515', 'old_alpha', STR_TO_DATE('2009-05-14T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'rd-160052', 'old_alpha', STR_TO_DATE('2009-05-15T22:52:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'rd-161348', 'old_alpha', STR_TO_DATE('2009-05-16T11:48:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'c0.0.11a', 'old_alpha', STR_TO_DATE('2009-05-16T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'c0.0.13a_03', 'old_alpha', STR_TO_DATE('2009-05-21T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'c0.0.13a', 'old_alpha', STR_TO_DATE('2009-05-30T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'c0.30_01c', 'old_alpha', STR_TO_DATE('2009-12-21T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'inf-20100618', 'old_alpha', STR_TO_DATE('2010-06-15T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.0.4', 'old_alpha', STR_TO_DATE('2010-07-08T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.0.5_01', 'old_alpha', STR_TO_DATE('2010-07-12T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.0.11', 'old_alpha', STR_TO_DATE('2010-07-22T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.0.14', 'old_alpha', STR_TO_DATE('2010-07-29T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.0.15', 'old_alpha', STR_TO_DATE('2010-08-03T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.0.16', 'old_alpha', STR_TO_DATE('2010-08-11T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.0.17_02', 'old_alpha', STR_TO_DATE('2010-08-19T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.0.17_04', 'old_alpha', STR_TO_DATE('2010-08-22T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.1.0', 'old_alpha', STR_TO_DATE('2010-09-12T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.1.2', 'old_alpha', STR_TO_DATE('2010-09-19T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.1.2_01', 'old_alpha', STR_TO_DATE('2010-09-22T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.0', 'old_alpha', STR_TO_DATE('2010-10-29T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.0_01', 'old_alpha', STR_TO_DATE('2010-10-30T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.0_02', 'old_alpha', STR_TO_DATE('2010-11-03T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.1', 'old_alpha', STR_TO_DATE('2010-11-04T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.1_01', 'old_alpha', STR_TO_DATE('2010-11-04T22:00:01', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.2a', 'old_alpha', STR_TO_DATE('2010-11-09T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.2b', 'old_alpha', STR_TO_DATE('2010-11-09T22:00:01', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.3', 'old_alpha', STR_TO_DATE('2010-11-23T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.3_01', 'old_alpha', STR_TO_DATE('2010-11-23T22:00:01', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.3_02', 'old_alpha', STR_TO_DATE('2010-11-24T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.3_04', 'old_alpha', STR_TO_DATE('2010-11-25T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.4_01', 'old_alpha', STR_TO_DATE('2010-11-29T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.5', 'old_alpha', STR_TO_DATE('2010-11-30T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'a1.2.6', 'old_alpha', STR_TO_DATE('2010-12-02T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.0', 'old_beta', STR_TO_DATE('2010-12-19T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.0_01', 'old_beta', STR_TO_DATE('2010-12-19T22:00:01', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.0.2', 'old_beta', STR_TO_DATE('2010-12-20T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.1_01', 'old_beta', STR_TO_DATE('2010-12-21T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.1_02', 'old_beta', STR_TO_DATE('2010-12-21T22:00:01', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.2', 'old_beta', STR_TO_DATE('2011-01-12T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.2_01', 'old_beta', STR_TO_DATE('2011-01-13T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.2_02', 'old_beta', STR_TO_DATE('2011-01-20T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.3b', 'old_beta', STR_TO_DATE('2011-02-21T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.3_01', 'old_beta', STR_TO_DATE('2011-02-22T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.4', 'old_beta', STR_TO_DATE('2011-03-30T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.4_01', 'old_beta', STR_TO_DATE('2011-04-04T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.5', 'old_beta', STR_TO_DATE('2011-04-18T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.5_01', 'old_beta', STR_TO_DATE('2011-04-19T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.6', 'old_beta', STR_TO_DATE('2011-05-25T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.6.1', 'old_beta', STR_TO_DATE('2011-05-25T22:00:01', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.6.2', 'old_beta', STR_TO_DATE('2011-05-25T22:00:02', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.6.3', 'old_beta', STR_TO_DATE('2011-05-25T22:00:03', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.6.4', 'old_beta', STR_TO_DATE('2011-05-25T22:00:04', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.6.5', 'old_beta', STR_TO_DATE('2011-05-27T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.6.6', 'old_beta', STR_TO_DATE('2011-05-30T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.7', 'old_beta', STR_TO_DATE('2011-06-29T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.7.2', 'old_beta', STR_TO_DATE('2011-06-30T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.7.3', 'old_beta', STR_TO_DATE('2011-07-07T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.8', 'old_beta', STR_TO_DATE('2011-09-14T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', 'b1.8.1', 'old_beta', STR_TO_DATE('2011-09-18T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.0', 'release', STR_TO_DATE('2011-11-17T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.1', 'release', STR_TO_DATE('2012-01-11T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.2.1', 'release', STR_TO_DATE('2012-02-29T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.2.2', 'release', STR_TO_DATE('2012-02-29T22:00:01', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.2.3', 'release', STR_TO_DATE('2012-03-01T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.2.4', 'release', STR_TO_DATE('2012-03-21T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.2.5', 'release', STR_TO_DATE('2012-03-29T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.3.1', 'release', STR_TO_DATE('2012-07-31T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.3.2', 'release', STR_TO_DATE('2012-08-15T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.4.2', 'release', STR_TO_DATE('2012-11-24T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.4.4', 'release', STR_TO_DATE('2012-12-13T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.4.6', 'release', STR_TO_DATE('2012-12-19T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.4.5', 'release', STR_TO_DATE('2012-12-19T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.4.7', 'release', STR_TO_DATE('2012-12-27T22:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.5.1', 'release', STR_TO_DATE('2013-03-20T10:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.5.2', 'release', STR_TO_DATE('2013-04-25T15:45:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.6.1', 'release', STR_TO_DATE('2013-06-28T14:48:41', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.6.2', 'release', STR_TO_DATE('2013-07-05T13:09:02', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.6.4', 'release', STR_TO_DATE('2013-09-19T15:52:37', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.2', 'release', STR_TO_DATE('2013-10-25T13:00:00', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.3', 'release', STR_TO_DATE('2013-12-06T13:55:34', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.4', 'release', STR_TO_DATE('2013-12-09T12:28:10', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.5', 'release', STR_TO_DATE('2014-02-26T09:22:17', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.6', 'release', STR_TO_DATE('2014-04-09T07:52:06', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.7', 'release', STR_TO_DATE('2014-04-09T07:52:16', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.8', 'release', STR_TO_DATE('2014-04-09T07:58:16', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.9', 'release', STR_TO_DATE('2014-04-14T13:29:23', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.7.10', 'release', STR_TO_DATE('2014-05-14T17:29:23', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8', 'release', STR_TO_DATE('2014-09-02T08:24:35', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.1', 'release', STR_TO_DATE('2014-11-24T14:13:31', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.2', 'release', STR_TO_DATE('2015-02-19T15:47:29', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.3', 'release', STR_TO_DATE('2015-02-20T14:00:09', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.4', 'release', STR_TO_DATE('2015-04-17T11:37:50', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.5', 'release', STR_TO_DATE('2015-05-22T11:15:28', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.6', 'release', STR_TO_DATE('2015-05-25T10:31:19', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.7', 'release', STR_TO_DATE('2015-06-05T10:10:44', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.8', 'release', STR_TO_DATE('2015-07-27T10:31:28', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.8.9', 'release', STR_TO_DATE('2015-12-03T09:24:39', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.9', 'release', STR_TO_DATE('2016-02-29T13:49:54', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.9.1', 'release', STR_TO_DATE('2016-03-30T13:43:07', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.9.2', 'release', STR_TO_DATE('2016-03-30T15:23:55', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.9.3', 'release', STR_TO_DATE('2016-05-10T08:33:35', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.9.4', 'release', STR_TO_DATE('2016-05-10T10:17:16', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.10', 'release', STR_TO_DATE('2016-06-08T13:06:18', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.10.1', 'release', STR_TO_DATE('2016-06-22T10:13:22', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.10.2', 'release', STR_TO_DATE('2016-06-23T09:17:32', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.11', 'release', STR_TO_DATE('2016-11-14T14:34:40', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.11.1', 'release', STR_TO_DATE('2016-12-20T14:05:34', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.11.2', 'release', STR_TO_DATE('2016-12-21T09:29:12', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.12', 'release', STR_TO_DATE('2017-06-02T13:50:27', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.12.1', 'release', STR_TO_DATE('2017-08-03T12:40:39', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.12.2', 'release', STR_TO_DATE('2017-09-18T08:39:46', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.13', 'release', STR_TO_DATE('2018-07-18T15:11:46', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.13.1', 'release', STR_TO_DATE('2018-08-22T14:03:42', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.13.2', 'release', STR_TO_DATE('2018-10-22T11:41:07', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.14', 'release', STR_TO_DATE('2019-04-23T14:52:44', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.14.1', 'release', STR_TO_DATE('2019-05-13T11:10:12', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.14.2', 'release', STR_TO_DATE('2019-05-27T11:48:25', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.14.3', 'release', STR_TO_DATE('2019-06-24T12:52:52', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.14.4', 'release', STR_TO_DATE('2019-07-19T09:25:47', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.15', 'release', STR_TO_DATE('2019-12-09T13:13:38', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.15.1', 'release', STR_TO_DATE('2019-12-16T10:29:47', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.15.2', 'release', STR_TO_DATE('2020-01-17T10:03:52', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.16', 'release', STR_TO_DATE('2020-06-23T16:20:52', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.16.1', 'release', STR_TO_DATE('2020-06-24T10:31:40', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.16.2', 'release', STR_TO_DATE('2020-08-11T10:13:46', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.16.3', 'release', STR_TO_DATE('2020-09-10T13:42:37', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.16.4', 'release', STR_TO_DATE('2020-10-29T15:49:37', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.16.5', 'release', STR_TO_DATE('2021-01-14T16:05:32', '%Y-%m-%dT%H:%i:%s')),
       ('minecraft-je', '1.17', 'release', STR_TO_DATE('2021-06-07T11:46:28', '%Y-%m-%dT%H:%i:%s'));

INSERT INTO roles(name)
VALUES ('admin');

INSERT INTO users(id, username, display_name, email, password, password_type, mfa, mfa_secret, created_at)
VALUES (1, 'darkhax', 'Darkhax', 'darkhax@diluv.com',
        '$2y$12$Y09/RQkc7icbiOonlBqTeegjtk9VYPKamMTJqkFVtfKDawRwifc8i', 'bcrypt', FALSE, NULL, NOW()),
       (2, 'jaredlll08', 'Jaredlll08', 'jaredlll08@diluv.com',
        '$2y$12$Y09/RQkc7icbiOonlBqTeegjtk9VYPKamMTJqkFVtfKDawRwifc8i', 'bcrypt', FALSE, NULL, NOW()),
       (3, 'lclc98', 'lclc98', 'lclc98@diluv.com',
        '$2y$12$Y09/RQkc7icbiOonlBqTeegjtk9VYPKamMTJqkFVtfKDawRwifc8i', 'bcrypt', TRUE, '123456', NOW()),
       (4, 'noprojects', 'noprojects', 'noprojects@diluv.com',
        '$2y$12$Y09/RQkc7icbiOonlBqTeegjtk9VYPKamMTJqkFVtfKDawRwifc8i', 'bcrypt', FALSE, NULL, NOW()),
       (5, 'delete', 'noprojects', 'delete@diluv.com',
        '$2y$12$Y09/RQkc7icbiOonlBqTeegjtk9VYPKamMTJqkFVtfKDawRwifc8i', 'bcrypt', FALSE, NULL, NOW());

INSERT INTO user_mfa_recovery(user_id, code, valid)
VALUES (3, '22222222', TRUE),
       (3, '33333333', TRUE),
       (4, '99999999', TRUE);

INSERT INTO user_mfa_email(user_id, code)
VALUES (2, '22222222'),
       (2, '33333333');

INSERT INTO user_roles(user_id, role_id)
VALUES (2, 1);

INSERT INTO temp_users(id, username, email, password, password_type, created_at)
VALUES (1, 'tempuser', 'tempuser@diluv.com', '', '', NOW()),
       (2, 'tempuser2', 'tempuser2@diluv.com', '', '', NOW()),
       (3, 'tempuser3', 'tempuser3@diluv.com', '', '', NOW());

INSERT INTO projects(name, slug, summary, description, cached_downloads, review, released, created_at, updated_at,
                     user_id, game_slug, project_type_slug)
VALUES ('Bookshelf', 'bookshelf',
        'An open source library for other mods!',
        'Bookshelf is a library mod which adds a lot of reusable code. The goal of bookshelf is to make writing complex mods much easier, while also expanding the capabilities of various systems within Minecraft and Forge. Bookshelf is used by many large mods and makes them easier to update and maintain.
:::warning NOTICE
Alpha and Beta releases may contain breaking changes or world corrupting bugs. Please stick to release versions unless you know what you\'re doing or you\'re working with someone else who does. Always back up your worlds when changing your mods!
:::
## Features
- Lazy loaded registry for Vanilla and Forge registry entries.
- Massive collection of utility functions.
- Extensive additions to the Data Pack specification.
- Weighted registries.
- Markdown table generators.
[![Nodecraft](https://nodecraft.com/assets/images/logo-dark.png)](https://nodecraft.com/r/darkhax)
This project is sponsored by Nodecraft! Use code Darkhax for 30% off your first month of service!
## Data Pack Documentation
- [Recipe Types](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#recipe-types)
  - [Shaped Damage Recipe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#shaped-damage-recipe)
  - [Shapeless Damage Recipe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#shapeless-damage-recipe)
- [Ingredients](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#ingredients)
  - [Mod ID](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#mod-id)
  - [Any Hoe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-hoe)
  - [Any Pickaxe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-pickaxe)
  - [Any Axe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-axe)
  - [Any Shovel](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-shovel)
  - [Any Sword](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-sword)
- [Loot Conditions](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#loot-conditions)
  - [In Biome Tag](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-biome-tag)
  - [In Dimension](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-dimension)
  - [In Village](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-for-village)
  - [Active Raid](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-for-raid)
  - [In Slime Chunk](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-for-slime-chunk)
  - [Redstone Power](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-redstone-power)
- [Loot Modifiers](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#global-loot-modifiers)
  - [Clear Loot](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#clear-items)
  - [Apply Silk Touch](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#apply-silk-touch)
  - [Convert Table](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#convert-to-different-table)
- [Item Predicate](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#item-predicates)
  - [Mod ID](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#mod-id-predicate)
  - [Ingredient](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#ingredient-predicate)', 0, FALSE, TRUE,
        FROM_UNIXTIME(1426128779), FROM_UNIXTIME(1583375717), 1, 'minecraft-je', 'mods'),
       ('Upgrade Modifiers', 'upgrade-modifiers',
        'Adds upgrade modifiers that can be used to improve your existing equipment.',
        'Adds upgrade modifiers that can be used to improve your existing equipment.', 0, TRUE, TRUE,
        FROM_UNIXTIME(1575426564), FROM_UNIXTIME(1581058918), 1, 'minecraft-je', 'mods'),
       ('Splashy', 'splashy',
        'Allows greater control over the splash text on the main menu.',
        'Allows greater control over the splash text on the main menu.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1576326746), FROM_UNIXTIME(1583107620), 1, 'minecraft-je', 'mods'),
       ('Modded Bingo', 'modded-bingo',
        'Adds a bingo like scavenger hunt minigame to your modded world.',
        'Adds a bingo like scavenger hunt minigame to your modded world.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1558066682), FROM_UNIXTIME(1580828240), 1, 'minecraft-je', 'mods'),
       ('Enchantment Descriptions', 'enchantment-descriptions',
        'Provides a way to get enchantment descriptions from enchanted books.',
        'Provides a way to get enchantment descriptions from enchanted books.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1472450423), FROM_UNIXTIME(1583225636), 1, 'minecraft-je', 'mods'),
       ('Eerie Entities', 'eerie-entities',
        'Adds new mobs that fit the minecraft atmosphere.',
        'Adds new mobs that fit the minecraft atmosphere.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1539544785), FROM_UNIXTIME(1580546708), 1, 'minecraft-je', 'mods'),
       ('OldJavaWarning', 'oldjavawarning',
        'Warns users when java is outdated.',
        'Warns users when java is outdated.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1525740110), FROM_UNIXTIME(1579932587), 1, 'minecraft-je', 'mods'),
       ('Wawla - What Are We Looking At', 'wawla-what-are-we-looking-at',
        'Wawla is a mod that works along side Waila to further provide information about the...',
        'Wawla is a mod that works along side Waila to further provide information about the...', 0, FALSE, TRUE,
        FROM_UNIXTIME(1412537933), FROM_UNIXTIME(1580940550), 1, 'minecraft-je', 'mods'),
       ('Dark Utilities', 'dark-utilities',
        'Expansive content mod which adds a little bit of everything.',
        'Expansive content mod which adds a little bit of everything.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1456376130), FROM_UNIXTIME(1583382601), 1, 'minecraft-je', 'mods'),
       ('Cursed', 'cursed', 'Adds new curses to Minecraft',
        'Adds new curses to Minecraft', 0, FALSE, TRUE,
        FROM_UNIXTIME(1570773911), FROM_UNIXTIME(1583189079), 1, 'minecraft-je', 'mods'),
       ('Botany Pots', 'botany-pots', 'Adds pots that you can use to grow crops!',
        'Adds pots that you can use to grow crops!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1575709035), FROM_UNIXTIME(1582103822), 1, 'minecraft-je', 'mods'),
       ('Resource Hogs', 'resource-hogs',
        'Adds pigs which can help players get resources.',
        'Adds pigs which can help players get resources.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1537778190), FROM_UNIXTIME(1582159835), 1, 'minecraft-je', 'mods'),
       ('Hunting Dimension', 'hunting-dimension',
        'Adds a dimension for hunting mobs.',
        'Adds a dimension for hunting mobs.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1512817395), FROM_UNIXTIME(1579031323), 1, 'minecraft-je', 'mods'),
       ('Thirsty Bottles', 'thirsty-bottles',
        'A mod to make water bottles as thirsty as I am.',
        'A mod to make water bottles as thirsty as I am.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1490299618), FROM_UNIXTIME(1582157328), 1, 'minecraft-je', 'mods'),
       ('Open Loader', 'open-loader',
        'An open source resource and data loader for Minecraft.',
        'An open source resource and data loader for Minecraft.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1576035011), FROM_UNIXTIME(1580592340), 1, 'minecraft-je', 'mods'),
       ('Nightmares', 'nightmares',
        'Adds nightmares and nightmare mobs', 'Adds nightmares and nightmare mobs', 0, FALSE, FALSE,
        FROM_UNIXTIME(1507134742), FROM_UNIXTIME(1582146181), 1, 'minecraft-je', 'mods'),
       ('Better Burning', 'better-burning',
        'Improves the burning and fire mechanics of Minecraft.',
        'Improves the burning and fire mechanics of Minecraft.', 0, TRUE, FALSE,
        FROM_UNIXTIME(1575287161), FROM_UNIXTIME(1583098748), 1, 'minecraft-je', 'mods'),
       ('AttributeFix', 'attributefix',
        'Removes arbitrary limits on Minecraft\'s attribute system. Fixes MANY mods!',
        'Removes arbitrary limits on Minecraft\'s attribute system. Fixes MANY mods!', 0, TRUE, TRUE,
        FROM_UNIXTIME(1508788988), FROM_UNIXTIME(1583100221), 1, 'minecraft-je', 'mods'),
       ('Bad Mobs', 'bad-mobs',
        'A mod to blacklist mobs from spawning.',
        'A mod to blacklist mobs from spawning.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1438051659), FROM_UNIXTIME(1583105091), 1, 'minecraft-je', 'mods'),
       ('Coloured Tooltips', 'coloured-tooltips',
        'Allows the tooltip colors to be configured.',
        'Allows the tooltip colors to be configured.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1516341777), FROM_UNIXTIME(1583108043), 1, 'minecraft-je', 'mods'),
       ('More Swords Legacy', 'more-swords-legacy',
        'The legacy of More Swords',
        'The legacy of More Swords', 0, FALSE, TRUE,
        FROM_UNIXTIME(1558499363), FROM_UNIXTIME(1571187684), 1, 'minecraft-je', 'mods'),
       ('Friendly Fire', 'friendly-fire',
        'Modifies combat to prevent friendly fire to pets and other mobs.',
        'Modifies combat to prevent friendly fire to pets and other mobs.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1481691209), FROM_UNIXTIME(1571187431), 1, 'minecraft-je', 'mods'),
       ('Plummet', 'plummet',
        'Allows you to prevent flying in some circumstances.',
        'Allows you to prevent flying in some circumstances.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1573167604), FROM_UNIXTIME(1573168137), 1, 'minecraft-je', 'mods'),
       ('Amnesia Blocks', 'amnesia-blocks',
        'Makes you randomly forget what a block is.',
        'Makes you randomly forget what a block is.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1567061806), FROM_UNIXTIME(1568835541), 1, 'minecraft-je', 'mods'),
       ('Never Enough Candy', 'never-enough-candy',
        'Adds new candys for you to eat and collect.',
        'Adds new candys for you to eat and collect.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1444452425), FROM_UNIXTIME(1576294472), 1, 'minecraft-je', 'mods'),
       ('Pillagers', 'pillagers',
        'Allows you to get villager loot by killing them.',
        'Allows you to get villager loot by killing them.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1519350324), FROM_UNIXTIME(1571187718), 1, 'minecraft-je', 'mods'),
       ('RedShark', 'redshark',
        'Allows the logging and tracking of network packets.',
        'Allows the logging and tracking of network packets.', 0, TRUE, TRUE,
        FROM_UNIXTIME(1523329118), FROM_UNIXTIME(1571187793), 1, 'minecraft-je', 'mods'),
       ('Item Stages', 'item-stages',
        'Allows items to be restricted to a stage.',
        'Allows items to be restricted to a stage.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1508536731), FROM_UNIXTIME(1571187786), 1, 'minecraft-je', 'mods'),
       ('Get Ya\' Tanks Here', 'get-ya-tanks-here',
        'Adds fluid tanks of various materials.',
        'Adds fluid tanks of various materials.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1420275000), FROM_UNIXTIME(1571187826), 1, 'minecraft-je', 'mods'),
       ('Nautilus', 'nautilus',
        'A mixin library that adds additional features for mods to take advantage of.',
        'A mixin library that adds additional features for mods to take advantage of.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1526173745), FROM_UNIXTIME(1571187764), 1, 'minecraft-je', 'mods'),
       ('Damage Control', 'damage-control',
        'Allows for health and damage to be modified and rebalanced!',
        'Allows for health and damage to be modified and rebalanced!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1487824492), FROM_UNIXTIME(1571187845), 1, 'minecraft-je', 'mods'),
       ('Stuff A Sock In It', 'stuff-a-sock-in-it',
        'Console filtering made easy!',
        'Console filtering made easy!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1488691129), FROM_UNIXTIME(1571187443), 1, 'minecraft-je', 'mods'),
       ('Caliper', 'caliper',
        'A collection of tools for testing and developing mods and modpacks.',
        'A collection of tools for testing and developing mods and modpacks.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1493916343), FROM_UNIXTIME(1571190557), 1, 'minecraft-je', 'mods'),
       ('Mob Banners', 'mob-banners',
        'Adds decorative banners to commemorate your combat conquests.',
        'Adds decorative banners to commemorate your combat conquests.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1538191953), FROM_UNIXTIME(1571187709), 1, 'minecraft-je', 'mods'),
       ('Mod Debug World Type', 'mod-debug-world-type',
        'Allows the debug world type to show mods from a specific mod ID.',
        'Allows the debug world type to show mods from a specific mod ID.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1549092831), FROM_UNIXTIME(1571187860), 1, 'minecraft-je', 'mods'),
       ('More Swords Mod', 'more-swords-mod',
        'A simple mod that adds new swords and enchantments into the game.',
        'A simple mod that adds new swords and enchantments into the game.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1371980040), FROM_UNIXTIME(1556874728), 1, 'minecraft-je', 'mods'),
       ('Snail Mail', 'snail-mail',
        'Adds new types of mail to the game.',
        'Adds new types of mail to the game.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1543395108), FROM_UNIXTIME(1543971012), 1, 'minecraft-je', 'mods'),
       ('Dark Utilities (Fabric)', 'dark-utilities-fabric',
        'Adds random blocks that are sometimes useful.',
        'Adds random blocks that are sometimes useful.', 0, TRUE, TRUE,
        FROM_UNIXTIME(1545172644), FROM_UNIXTIME(1545176373), 1, 'minecraft-je', 'mods'),
       ('Informational Accessories', 'informational-accessories',
        'Trinkets to help you better understand your world.',
        'Trinkets to help you better understand your world.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1505453855), FROM_UNIXTIME(1543881759), 1, 'minecraft-je', 'mods'),
       ('JourneyMapStages', 'journeymapstages',
        'Allows the JourneyMap mod to be staged with GameStages',
        'Allows the JourneyMap mod to be staged with GameStages', 0, FALSE, TRUE,
        FROM_UNIXTIME(1520540191), FROM_UNIXTIME(1552663257), 1, 'minecraft-je', 'mods'),
       ('No AI Spawn Eggs', 'no-ai-spawn-eggs',
        'Adds spawn eggs that spawn mobs with no AI.',
        'Adds spawn eggs that spawn mobs with no AI.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1539034393), FROM_UNIXTIME(1543884635), 1, 'minecraft-je', 'mods'),
       ('Tips', 'tips',
        'This mod adds tips to loading menus. It also allows for modpacks and mods to...',
        'This mod adds tips to loading menus. It also allows for modpacks and mods to...', 0, FALSE, TRUE,
        FROM_UNIXTIME(1541576533), FROM_UNIXTIME(1550911369), 1, 'minecraft-je', 'mods'),
       ('Enchanting Plus', 'enchanting-plus',
        'Enchanting Plus',
        'Enchanting Plus', 0, FALSE, TRUE,
        FROM_UNIXTIME(1371579488), FROM_UNIXTIME(1560549086), 1, 'minecraft-je', 'mods'),
       ('Conflux Cubes', 'conflux-cubes',
        'Adds blocks with randomized effects. Has support for custom content and Twitch integration.',
        'Adds blocks with randomized effects. Has support for custom content and Twitch integration.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1550955595), FROM_UNIXTIME(1551167711), 1, 'minecraft-je', 'mods'),
       ('Stage Tabels', 'stage-tabels',
        'Allows game stages to be placed into weighted tables.',
        'Allows game stages to be placed into weighted tables.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1547433081), FROM_UNIXTIME(1551404157), 1, 'minecraft-je', 'mods'),
       ('Dark Elevators', 'dark-elevators',
        'Adds a simple elevator block to the game.',
        'Adds a simple elevator block to the game.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1546154771), FROM_UNIXTIME(1557210505), 1, 'minecraft-je', 'mods'),
       ('No Tema Stahp', 'no-tema-stahp',
        'An addon for XU2 that removes some of the weird and cheaty stuff in XU2',
        'An addon for XU2 that removes some of the weird and cheaty stuff in XU2', 0, FALSE, TRUE,
        FROM_UNIXTIME(1544339050), FROM_UNIXTIME(1553196510), 1, 'minecraft-je', 'mods'),
       ('Surge', 'surge',
        'An open source performance enhancement mod.',
        'An open source performance enhancement mod.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1472188259), FROM_UNIXTIME(1548629194), 1, 'minecraft-je', 'mods'),
       ('Kelpie', 'kelpie',
        'Adds the kelpie from Scottish lore to MC.',
        'Adds the kelpie from Scottish lore to MC.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1506993271), FROM_UNIXTIME(1543883866), 1, 'minecraft-je', 'mods'),
       ('LootTableTweaker', 'loottabletweaker',
        'LootTable support for CraftTweaker / MineTweaker3',
        'LootTable support for CraftTweaker / MineTweaker3', 0, FALSE, TRUE,
        FROM_UNIXTIME(1487716299), FROM_UNIXTIME(1543883916), 1, 'minecraft-je', 'mods'),
       ('Biome Specific Dungeons', 'biome-specific-dungeons',
        'Adds new dungeons which are based on the dungeons they spawn in.',
        'Adds new dungeons which are based on the dungeons they spawn in.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1551847678), FROM_UNIXTIME(1552336182), 1, 'minecraft-je', 'mods'),
       ('Simply Arrows', 'simply-arrows',
        'Adds new arrows. What do you expect.',
        'Adds new arrows. What do you expect.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1516939956), FROM_UNIXTIME(1538777131), 1, 'minecraft-je', 'mods'),
       ('No Worldgen 5 You', 'no-worldgen-5-you', 'A simple mod that allows worldgen to be disabled.',
        'A simple mod that allows worldgen to be disabled.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1502919021), FROM_UNIXTIME(1538497936), 1, 'minecraft-je', 'mods'),
       ('Solar Village', 'solar-village',
        'A basic solar panel for Tesla',
        'A basic solar panel for Tesla', 0, FALSE, TRUE,
        FROM_UNIXTIME(1463870396), FROM_UNIXTIME(1538460048), 1, 'minecraft-je', 'mods'),
       ('Ore Stages', 'ore-stages',
        'Allows blocks like ores to be hidden and staged with Game Stages',
        'Allows blocks like ores to be hidden and staged with Game Stages', 0, FALSE, TRUE,
        FROM_UNIXTIME(1521172290), FROM_UNIXTIME(1542928249), 1, 'minecraft-je', 'mods'),
       ('Health Bars', 'health-bars',
        'Adds health bars to enemy mobs.',
        'Adds health bars to enemy mobs.', 0, TRUE, TRUE,
        FROM_UNIXTIME(1536810753), FROM_UNIXTIME(1542487132), 1, 'minecraft-je', 'mods'),
       ('Costume', 'costume',
        'Adds halloween costumes to the game',
        'Adds halloween costumes to the game', 0, FALSE, TRUE,
        FROM_UNIXTIME(1539310654), FROM_UNIXTIME(1539666473), 1, 'minecraft-je', 'mods'),
       ('Dimension Stages', 'dimension-stages',
        'Allows access to dimensions to be restricted based on stages.',
        'Allows access to dimensions to be restricted based on stages.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1496909591), FROM_UNIXTIME(1543878872), 1, 'minecraft-je', 'mods'),
       ('Spooky Jam 2018', 'spooky-jam-2018',
        'The results of MMD\'s SpookyJam 2018!',
        'The results of MMD\'s SpookyJam 2018!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1539638931), FROM_UNIXTIME(1540248595), 1, 'minecraft-je', 'mods'),
       ('Power Adapters', 'power-adapters',
        'Adds new power adapter block to convert power.',
        'Adds new power adapter block to convert power.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1518908037), FROM_UNIXTIME(1538512725), 1, 'minecraft-je', 'mods'),
       ('Cravings', 'cravings',
        'Adds cravings for random food items. Eating a desired food can give the player benefits.',
        'Adds cravings for random food items. Eating a desired food can give the player benefits.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1510260104), FROM_UNIXTIME(1543877894), 1, 'minecraft-je', 'mods'),
       ('PigUtils', 'pigutils',
        'A fork of iChunUtils without the EULA.',
        'A fork of iChunUtils without the EULA.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1529347386), FROM_UNIXTIME(1538801894), 1, 'minecraft-je', 'mods'),
       ('Waila Stages', 'waila-stages',
        'Allows access to Waila HUD and specific tool tip info to be restricted based on...',
        'Allows access to Waila HUD and specific tool tip info to be restricted based on...', 0, FALSE, TRUE,
        FROM_UNIXTIME(1496984426), FROM_UNIXTIME(1538512764), 1, 'minecraft-je', 'mods'),
       ('TESLA', 'tesla',
        'A power API',
        'A power API', 0, FALSE, TRUE,
        FROM_UNIXTIME(1462179367), FROM_UNIXTIME(1507310625), 1, 'minecraft-je', 'mods'),
       ('Spooky Jam', 'spooky-jam',
        'The results of MMD\'s SpookyJam 2017!',
        'The results of MMD\'s SpookyJam 2017!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1508151257), FROM_UNIXTIME(1508151574), 1, 'minecraft-je', 'mods'),
       ('Custom Tweaks', 'custom-tweaks',
        'A series of tools to help customize modpacks',
        'A series of tools to help customize modpacks', 0, FALSE, TRUE,
        FROM_UNIXTIME(1471576171), FROM_UNIXTIME(1538449461), 1, 'minecraft-je', 'mods'),
       ('Bookcase', 'bookcase',
        'Additional utilities for making stardew mods and ensuring cross compatibility.',
        'Additional utilities for making stardew mods and ensuring cross compatibility.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1530511531), FROM_UNIXTIME(1543479262), 1, 'minecraft-je', 'mods'),
       ('Price Tooltips', 'price-tooltips',
        'Adds item sell price info to tooltips',
        'Adds item sell price info to tooltips', 0, FALSE, TRUE,
        FROM_UNIXTIME(1532578908), FROM_UNIXTIME(1536387325), 1, 'minecraft-je', 'mods'),
       ('Natural Harvest', 'natural-harvest',
        'Adds an abundance of natural resources to the world.',
        'Adds an abundance of natural resources to the world.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1493406965), FROM_UNIXTIME(1495051439), 1, 'minecraft-je', 'mods'),
       ('Waila Inhibitors', 'waila-inhibitors',
        'This mod provides some balance to the Waila Mod.',
        'Bookshelf is a library mod which adds a lot of reusable code. The goal of bookshelf is to make writing complex mods much easier, while also expanding the capabilities of various systems within Minecraft and Forge. Bookshelf is used by many large mods and makes them easier to update and maintain.
:::warning NOTICE
Alpha and Beta releases may contain breaking changes or world corrupting bugs. Please stick to release versions unless you know what you\'re doing or you\'re working with someone else who does. Always back up your worlds when changing your mods!
:::
## Features
- Lazy loaded registry for Vanilla and Forge registry entries.
- Massive collection of utility functions.
- Extensive additions to the Data Pack specification.
- Weighted registries.
- Markdown table generators.
[![Nodecraft](https://nodecraft.com/assets/images/logo-dark.png)](https://nodecraft.com/r/darkhax)
This project is sponsored by Nodecraft! Use code Darkhax for 30% off your first month of service!
## Data Pack Documentation
- [Recipe Types](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#recipe-types)
  - [Shaped Damage Recipe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#shaped-damage-recipe)
  - [Shapeless Damage Recipe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#shapeless-damage-recipe)
- [Ingredients](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#ingredients)
  - [Mod ID](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#mod-id)
  - [Any Hoe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-hoe)
  - [Any Pickaxe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-pickaxe)
  - [Any Axe](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-axe)
  - [Any Shovel](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-shovel)
  - [Any Sword](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#any-sword)
- [Loot Conditions](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#loot-conditions)
  - [In Biome Tag](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-biome-tag)
  - [In Dimension](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-dimension)
  - [In Village](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-for-village)
  - [Active Raid](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-for-raid)
  - [In Slime Chunk](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-for-slime-chunk)
  - [Redstone Power](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#check-redstone-power)
- [Loot Modifiers](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#global-loot-modifiers)
  - [Clear Loot](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#clear-items)
  - [Apply Silk Touch](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#apply-silk-touch)
  - [Convert Table](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#convert-to-different-table)
- [Item Predicate](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#item-predicates)
  - [Mod ID](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#mod-id-predicate)
  - [Ingredient](https://github.com/Darkhax-Minecraft/Bookshelf/wiki/Data-Packs#ingredient-predicate)', 0, TRUE, TRUE,
        FROM_UNIXTIME(1441076820), FROM_UNIXTIME(1445549924), 1, 'minecraft-je', 'mods'),
       ('Musica', 'musica',
        'A framework for easily adding new records into Minecraft. Great for music lovers and server...',
        'A framework for easily adding new records into Minecraft. Great for music lovers and server...',
        0, FALSE, TRUE,
        FROM_UNIXTIME(1429233240), FROM_UNIXTIME(1429849826), 1, 'minecraft-je', 'mods'),
       ('Cosmetic Armor', 'cosmetic-armor',
        'Because it\'s important to be stylish',
        'Because it\'s important to be stylish', 0, FALSE, TRUE,
        FROM_UNIXTIME(1416978702), FROM_UNIXTIME(1417390296), 1, 'minecraft-je', 'mods'),
       ('StarStones', 'starstones',
        'Meteors!',
        'Meteors!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1422387999), FROM_UNIXTIME(1434065271), 1, 'minecraft-je', 'mods'),
       ('Waila Events', 'waila-events',
        'This mod adds several new events to the Waila mod.',
        'This mod adds several new events to the Waila mod.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1439971529), FROM_UNIXTIME(1439989989), 1, 'minecraft-je', 'mods'),
       ('Get Ya\' Barrels Here', 'get-ya-barrels-here',
        'Basic item storage and transport!',
        'Basic item storage and transport!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1487979399), FROM_UNIXTIME(1491167140), 1, 'minecraft-je', 'mods'),
       ('Game Stages', 'game-stages',
        'An API for adding stages, for modpacks and other mods to use!',
        'An API for adding stages, for modpacks and other mods to use!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1496012694), FROM_UNIXTIME(1571187452), 1, 'minecraft-je', 'mods'),
       ('Mob Stages', 'mob-stages',
        'This mod hooks in to the GameStage API, and allows mob spawning to be gated...',
        'This mod hooks in to the GameStage API, and allows mob spawning to be gated...', 0, FALSE, TRUE,
        FROM_UNIXTIME(1505796349), FROM_UNIXTIME(1543884038), 1, 'minecraft-je', 'mods'),
       ('Additional Banners', 'additional-banners',
        'A whole new range of banner patterns, to enhance your world!',
        'A whole new range of banner patterns, to enhance your world!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1430706855), FROM_UNIXTIME(1564433660), 1, 'minecraft-je', 'mods'),
       ('Zalgo\'s Command', 'zalgo-s-command',
        'Adds a utility command, which lets you add combine multiple commands together.',
        'Adds a utility command, which lets you add combine multiple commands together.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1495732981), FROM_UNIXTIME(1538512806), 1, 'minecraft-je', 'mods'),
       ('TinkerStages', 'tinkerstages',
        'This mod hooks in to the GameStage API, and allows various aspects of Tinkers Construct...',
        'This mod hooks in to the GameStage API, and allows various aspects of Tinkers Construct...', 0, FALSE, TRUE,
        FROM_UNIXTIME(1503601801), FROM_UNIXTIME(1540773110), 1, 'minecraft-je', 'mods'),
       ('Colorful Mobs', 'colorful-mobs', 'Add diversity to your Minecraft world, by adding more colors!',
        'Add diversity to your Minecraft world, by adding more colors!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1426129663), FROM_UNIXTIME(1431582068), 1, 'minecraft-je', 'mods'),
       ('Loot Chests', 'loot-chests',
        'A utility for map makers, which provides access to predefined loot table chests!',
        'A utility for map makers, which provides access to predefined loot table chests!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1476369205), FROM_UNIXTIME(1478701535), 1, 'minecraft-je', 'mods'),
       ('MCStats - Twitch Integration', 'mcstats-twitch-integration', 'Shows your statistics in a Twitch Extension!',
        'Shows your statistics in a Twitch Extension!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1566876588), FROM_UNIXTIME(1568489346), 2, 'minecraft-je', 'mods'),
       ('ModTweaker', 'modtweaker',
        'ModTweaker is an addon for CraftTweaker, a recipe manipulator utility for Minecraft. It allows you...',
        'ModTweaker is an addon for CraftTweaker, a recipe manipulator utility for Minecraft. It allows you...', 0, 0,
        TRUE, FROM_UNIXTIME(1401662691), FROM_UNIXTIME(1581643023), 2, 'minecraft-je', 'mods'),
       ('How Do I Do This Again?', 'how-do-i-do-this-again',
        'Randomizes the main menu button locations.',
        'Randomizes the main menu button locations.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1567006700), FROM_UNIXTIME(1567006920), 2, 'minecraft-je', 'mods'),
       ('Ambient Environment', 'ambient-environment',
        'Adds more ambiance to a Minecraft world',
        'Adds more ambiance to a Minecraft world', 0, FALSE, TRUE,
        FROM_UNIXTIME(1556150856), FROM_UNIXTIME(1583127730), 2, 'minecraft-je', 'mods'),
       ('CraftTweaker', 'crafttweaker',
        'A continuation of MineTweaker originally by StanH',
        'A continuation of MineTweaker originally by StanH', 0, FALSE, TRUE,
        FROM_UNIXTIME(1451259974), FROM_UNIXTIME(1582845758), 2, 'minecraft-je', 'mods'),
       ('Clumps', 'clumps',
        'Clumps XP orbs together to reduce lag',
        'Clumps XP orbs together to reduce lag', 0, FALSE, TRUE,
        FROM_UNIXTIME(1483789583), FROM_UNIXTIME(1563136846), 2, 'minecraft-je', 'mods'),
       ('TipTheScales', 'tipthescales',
        'Allows for more options when adjusting the GUIScale option as well as making it a...',
        'Allows for more options when adjusting the GUIScale option as well as making it a...', 0, FALSE, TRUE,
        FROM_UNIXTIME(1511212153), FROM_UNIXTIME(1553126238), 2, 'minecraft-je', 'mods'),
       ('SewingKit', 'sewingkit',
        'It\'s CraftTweaker for fabric!',
        'It\'s CraftTweaker for fabric!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1546907649), FROM_UNIXTIME(1559643560), 2, 'minecraft-je', 'mods'),
       ('Fabric Controlling', 'fabric-controlling',
        'Adds a search bar to the Key-Bindings menu',
        'Adds a search bar to the Key-Bindings menu', 0, FALSE, TRUE,
        FROM_UNIXTIME(1544587271), FROM_UNIXTIME(1570556618), 2, 'minecraft-je', 'mods'),
       ('Controlling', 'controlling',
        'Adds a search bar to the Key-Bindings menu',
        'Adds a search bar to the Key-Bindings menu', 0, FALSE, TRUE,
        FROM_UNIXTIME(1472418436), FROM_UNIXTIME(1580168022), 2, 'minecraft-je', 'mods'),
       ('Recipe Stages', 'recipe-stages',
        'Allows for Crafting table recipes to be locked behind stages.',
        'Allows for Crafting table recipes to be locked behind stages.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1508862748), FROM_UNIXTIME(1566472126), 2, 'minecraft-je', 'mods'),
       ('Prestige', 'prestige',
        'Allows progress that extends beyond a world.',
        'Allows progress that extends beyond a world.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1521874163), FROM_UNIXTIME(1566510360), 2, 'minecraft-je', 'mods'),
       ('ContentTweaker', 'contenttweaker',
        'Allows you to add Items and Blocks',
        'Allows you to add Items and Blocks', 0, FALSE, TRUE,
        FROM_UNIXTIME(1445007558), FROM_UNIXTIME(1545878794), 2, 'minecraft-je', 'mods'),
       ('Hotbar Hotswap', 'hotbar-hotswap',
        'Allows you to press a button corresponding to a hotbar slot and move an item...',
        'Allows you to press a button corresponding to a hotbar slot and move an item...', 0, FALSE, TRUE,
        FROM_UNIXTIME(1531428949), FROM_UNIXTIME(1531857465), 2, 'minecraft-je', 'mods'),
       ('Fused', 'fused',
        'Makes gunpowder placeable and trigger TNT blocks',
        'Makes gunpowder placeable and trigger TNT blocks', 0, FALSE, TRUE,
        FROM_UNIXTIME(1510515738), FROM_UNIXTIME(1510515855), 2, 'minecraft-je', 'mods'),
       ('World Book', 'world-book',
        'Makes the world selection screen easier to use and find worlds',
        'Makes the world selection screen easier to use and find worlds', 0, FALSE, TRUE,
        FROM_UNIXTIME(1546753941), FROM_UNIXTIME(1546754056), 2, 'minecraft-je', 'mods'),
       ('SAtIn', 'satin',
        'Allows to custom Attack Indicator locations and scale',
        'Allows to custom Attack Indicator locations and scale', 0, FALSE, TRUE,
        FROM_UNIXTIME(1517360905), FROM_UNIXTIME(1517360982), 2, 'minecraft-je', 'mods'),
       ('PrimalChests', 'primalchests',
        'Adds intermediate chests that cannot hold as much as a normal chests',
        'Adds intermediate chests that cannot hold as much as a normal chests', 0, FALSE, TRUE,
        FROM_UNIXTIME(1503346503), FROM_UNIXTIME(1521493174), 2, 'minecraft-je', 'mods'),
       ('WaitingTime', 'waitingtime',
        'Adds a pong game to play while the pack is loading',
        'Adds a pong game to play while the pack is loading', 0, FALSE, TRUE,
        FROM_UNIXTIME(1514327681), FROM_UNIXTIME(1548714806), 2, 'minecraft-je', 'mods'),
       ('PDQ Paths', 'pdq-paths',
        'Paths that are pretty damn quick',
        'Paths that are pretty damn quick', 0, FALSE, TRUE,
        FROM_UNIXTIME(1542331746), FROM_UNIXTIME(1542331969), 2, 'minecraft-je', 'mods'),
       ('MTLib', 'mtlib',
        'Library files for Minetweaker Addons',
        'Library files for Minetweaker Addons', 0, FALSE, TRUE,
        FROM_UNIXTIME(1478253985), FROM_UNIXTIME(1551999748), 2, 'minecraft-je', 'mods'),
       ('WAIM - What am I missing?', 'waim-what-am-i-missing',
        'A mod that makes the missing entries gui a bit easier to traverse!',
        'A mod that makes the missing entries gui a bit easier to traverse!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1517361561), FROM_UNIXTIME(1517361814), 2, 'minecraft-je', 'mods'),
       ('Trash Trash', 'trash-trash',
        'Makes it easy to quickly trash \'Trash\' quality items without opening the inventory!',
        'Makes it easy to quickly trash \'Trash\' quality items without opening the inventory!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1531362124), FROM_UNIXTIME(1531362312), 2, 'minecraft-je', 'mods'),
       ('Reputed', 'reputed',
        'Allows for the tracking of weapon kills!',
        'Allows for the tracking of weapon kills!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1518270101), FROM_UNIXTIME(1520030404), 2, 'minecraft-je', 'mods'),
       ('You\'ve Got Mail - Twitch Integration', 'you-ve-got-mail-twitch-integration',
        'Allows for viewers to buy ingame mail for streamers!',
        'Allows for viewers to buy ingame mail for streamers!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1543579597), FROM_UNIXTIME(1549420940), 2, 'minecraft-je', 'mods'),
       ('ColCol', 'colcol',
        'Addadd somsom colcol toto youyou liflif.',
        'Addadd somsom colcol toto youyou liflif.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1537698963), FROM_UNIXTIME(1537699040), 2, 'minecraft-je', 'mods'),
       ('DarkRooms', 'darkrooms',
        'Brings back the old dark room mechanic for farming flowers easily!',
        'Brings back the old dark room mechanic for farming flowers easily!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1548254796), FROM_UNIXTIME(1548256311), 2, 'minecraft-je', 'mods'),
       ('Item Tooltips', 'item-tooltips',
        'Displays the name of the currently held item.',
        'Displays the name of the currently held item.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1531014424), FROM_UNIXTIME(1542486882), 2, 'minecraft-je', 'mods'),
       ('SlimyBoyos', 'slimyboyos',
        'Makes slimes pickup items.',
        'Makes slimes pickup items.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1510761744), FROM_UNIXTIME(1510761773), 2, 'minecraft-je', 'mods'),
       ('Health Indicators', 'health-indicators',
        'Plays a sound when you are low on health.',
        'Plays a sound when you are low on health.', 0, FALSE, TRUE,
        FROM_UNIXTIME(1442105852), FROM_UNIXTIME(1442106849), 2, 'minecraft-je', 'mods'),
       ('FTB Tweaks', 'ftb-tweaks',
        'This is a very simple mod that introduces the concept of game modes. For want...',
        'This is a very simple mod that introduces the concept of game modes. For want...', 0, FALSE, TRUE,
        FROM_UNIXTIME(1445106481), FROM_UNIXTIME(1495550385), 2, 'minecraft-je', 'mods'),
       ('Visualize', 'visualize',
        'Visualize syncs video options between modpacks!',
        'Visualize syncs video options between modpacks!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1497368958), FROM_UNIXTIME(1498839553), 2, 'minecraft-je', 'mods'),
       ('Initial Inventory', 'initial-inventory',
        'Allows you to set an Initial Inventory for a player using ZenScript',
        'Allows you to set an Initial Inventory for a player using ZenScript', 0, FALSE, TRUE,
        FROM_UNIXTIME(1478289201), FROM_UNIXTIME(1502919051), 2, 'minecraft-je', 'mods'),
       ('Pixelmongo', 'pixelmongo',
        'Pixelmongo is an addon to Pixelmon that adds pixelmon pokestops to Minecraft',
        'Pixelmongo is an addon to Pixelmon that adds pixelmon pokestops to Minecraft', 0, FALSE, TRUE,
        FROM_UNIXTIME(1494530964), FROM_UNIXTIME(1494531094), 2, 'minecraft-je', 'mods'),
       ('Custom NPCs Spawner', 'custom-npcs-spawner',
        'Allows Clientside-cloned NPCs to be used in multiple worlds automagically, and allows servers to have...',
        'Allows Clientside-cloned NPCs to be used in multiple worlds automagically, and allows servers to have...', 0,
        FALSE, TRUE, FROM_UNIXTIME(1439238704), FROM_UNIXTIME(1508590784), 2, 'minecraft-je', 'mods'),
       ('Tanks', 'tanks',
        'Adds tanks that can hold 32 buckets of any liquid',
        'Adds tanks that can hold 32 buckets of any liquid', 0, FALSE, TRUE,
        FROM_UNIXTIME(1451582922), FROM_UNIXTIME(1460640474), 2, 'minecraft-je', 'mods'),
       ('{ JSONAbles }', 'jsonables',
        'Add Tinker\'s Construct tools via json!',
        'Add Tinker\'s Construct tools via json!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1425900845), FROM_UNIXTIME(1456684790), 2, 'minecraft-je', 'mods'),
       ('Fluxed Trinkets', 'fluxed-trinkets',
        'RF Powered Baubles',
        'RF Powered Baubles', 0, FALSE, TRUE,
        FROM_UNIXTIME(1410258283), FROM_UNIXTIME(1415804138), 2, 'minecraft-je', 'mods'),
       ('Fluxed-Crystals', 'fluxed-crystals',
        'RF-Powered Crystal Farming',
        'RF-Powered Crystal Farming', 0, FALSE, TRUE,
        FROM_UNIXTIME(1418153072), FROM_UNIXTIME(1433446981), 2, 'minecraft-je', 'mods'),
       ('Harvest Reader', 'harvest-reader',
        'Adds an item to display information about Harvest Festival crops!',
        'Adds an item to display information about Harvest Festival crops!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1497213017), FROM_UNIXTIME(1497213125), 2, 'minecraft-je', 'mods'),
       ('Mystical Trinkets', 'mystical-trinkets',
        'Mystical Trinkets',
        'Mystical Trinkets', 0, FALSE, TRUE,
        FROM_UNIXTIME(1398643005), FROM_UNIXTIME(1404340023), 2, 'minecraft-je', 'mods'),
       ('Machines and Stuff', 'machines-and-stuff',
        'Adds useful machines and generators!',
        'Adds useful machines and generators!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1494282563), FROM_UNIXTIME(1495575112), 2, 'minecraft-je', 'mods'),
       ('Regressments', 'regressments',
        'Removes advancements', 'Removes advancements', 0, FALSE, TRUE,
        FROM_UNIXTIME(1499308297), FROM_UNIXTIME(1499308384), 2, 'minecraft-je', 'mods'),
       ('Fluxed-Crystals 2', 'fluxed-crystals-2',
        'Generate resources and materials at the expense of RF!',
        'Generate resources and materials at the expense of RF!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1435188634), FROM_UNIXTIME(1439913880), 2, 'minecraft-je', 'mods'),
       ('Fluxed-Core', 'fluxed-core',
        'A mod that contains helper functions for all of my mods',
        'A mod that contains helper functions for all of my mods', 0, FALSE, TRUE,
        FROM_UNIXTIME(1444912766), FROM_UNIXTIME(1460629442), 2, 'minecraft-je', 'mods'),
       ('JAIM - Just Another Information Mod', 'jaim-just-another-information-mod',
        'Overhauls the select world screen and displays extra information!',
        'Overhauls the select world screen and displays extra information!', 0, FALSE, TRUE,
        FROM_UNIXTIME(1481492093), FROM_UNIXTIME(1481492202), 2, 'minecraft-je', 'mods');

INSERT INTO projects(name, slug, summary, description, cached_downloads, review, released, created_at, updated_at,
                     user_id, game_slug, project_type_slug)
VALUES ('Bookshelf Bedrock', 'bookshelf', 'A bedrock bookshelf', 'A bedrock bookshelf', 0, FALSE, TRUE,
        NOW(), NOW(), 1, 'minecraft-bedrock', 'mods');

INSERT INTO project_authors(project_id, user_id, role)
VALUES (94, 1, 'Coauthor'),
       (44, 2, 'Coauthor'),
       (48, 2, 'Coauthor'),
       (60, 2, 'Coauthor'),
       (29, 2, 'Coauthor'),
       (6, 2, 'Coauthor'),
       (1, 2, 'Testing');

INSERT INTO project_author_permissions(project_author_id, permission)
VALUES (6, 'project.upload'),
       (6, 'project.edit');

INSERT INTO project_tags(project_id, tag_id)
VALUES (1, 1),
       (128, 2),
       (2, 1),
       (2, 2);

INSERT INTO project_links(project_id, type, url)
VALUES (1, 'Patreon', 'https://www.patreon.com/diluv');

INSERT INTO featured_games(slug)
VALUES ('minecraft-je'),
       ('minecraft-bedrock');

INSERT INTO featured_projects(project_id)
VALUES (1),
       (2),
       (5),
       (10),
       (15),
       (11),
       (12),
       (99);

INSERT INTO news(slug, title, summary, description, user_id)
VALUES ('example', 'Example Post', 'Summary', 'Example', 1);

INSERT INTO password_reset(user_id, code, created_at)
VALUES (2, 'daf1f148-effd-400e-9b65-a4bf96e5215d', NOW());

INSERT INTO user_compromised_passwords(password_hash, occurrences)
VALUES ('5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8', 3861493);

INSERT INTO username_blocklist(username)
VALUES ('blocked');

INSERT INTO contains_username_blocklist(username)
VALUES ('diluv');

INSERT INTO email_domain_blocklist(domain)
VALUES ('banned.com'),
       ('banned2.com');

INSERT INTO email_blocklist (email, reason)
VALUES ('blocked@diluv.com', 'Example disabled email');

INSERT INTO email_sent(message_id, email, type, sent_at)
VALUES ('e4a291f7-740a-4b88-bc32-63e97e2d0812', 'test@diluv.com', 'test', NOW());

INSERT INTO project_files(id, version, name, size, downloads, changelog, sha512, release_type, classifier,
                          processing_status,
                          released, project_id, user_id)
VALUES (1, '1.0.4.187', 'Bookshelf-1.7.10-1.0.4.187.jar', 230589, 1000, '- Final RC for 1.7.10
- Added Java Tuples from SAP Manager Pack.
- Removed experimental rainbow utils.',
        '0a8380d56025c978c8dc310447fa977686279da85df092c78b88a145927d7f7a8bde0cd2780caa64c6fe552fce0ccbabec778980a53b58bc16295627b5a098e2',
        'release', 'binary', 2, TRUE, 1, 1),
       (2, '1.1.2.207', 'Bookshelf-1.8.9-1.1.2.207.jar', 149936, 1500, '- Final RC for 1.8.9
- Force RenderUtils to only load on the client.
- Add a new EntityDiggingFX with public constructor.
- Add functions for spawning different types of digging particles.',
        '51f3402f731f13ca32e557bad1b422a1b517aab48e7937a299b1005cde2c26decb4314b6b5c156459c4dbb4ac020aaf4ca3c3a447afaf56f89d4dbd6d91e557d',
        'release', 'binary', 2, TRUE, 1, 1),
       (3, '1.2.3.279', 'Bookshelf-1.9.4-1.2.3.279.jar', 161197, 200, '- Final RC for 1.9.4
- Added Chinese language support. Thanks to 3TUSK
- Added utils to check for player inventories.
- Added ATs for working with inventories.',
        'cd6bef2830dccdb9c3471195861d3b72827d733ba537466d19c4d99de072106e0b1e36761634809716083686df5e9e5721626dc81bad6b3a50f7dcc68c5e332b',
        'release', 'binary', 2, TRUE, 1, 1),
       (4, '1.4.4.348', 'Bookshelf-1.10.2-1.4.4.348.jar', 210212, 1500, '- Final RC for 1.10.2
- Fixed supporters.json not being found.',
        'ceaea82aa3aae3bbb991abdb0fddebe870b2bf31fbafe93afffcb8979e603c7b04a5ac446a5833ccca47167ce0a78ea72697c00bb575c3f1efc52ee6fdfaa255',
        'release', 'binary', 2, TRUE, 1, 1),
       (5, '2.0.0.388', 'Bookshelf-1.11.2-2.0.0.388.jar', 229669, 1500, '- Final RC for 1.11.2
- Fixed ore dict entries being registered too early.',
        '069c153f7273407caad0e61423978657483dd3f8b8a9c30ead7905f5d666af2b1dbc5a3db1abd7c8c517f99e65557581eed1c4543d4e8f8ec298ed57f7066070',
        'release', 'binary', 2, TRUE, 1, 1),
       (6, '2.3.590', 'Bookshelf-1.12.2-2.3.590.jar', 266636, 1500,
        '- Compiled against the latest Forge and MCP builds.',
        'a3217d1072d98c9c3ff2d9190a7c37cc81aad390179ac72abea7edea3485c6f2851a6cc5f94f07c229d5ac1fd78db58f558b45a299ceaae11ccdfd1aa351fcd7',
        'release', 'binary', 2, TRUE, 1, 1),
       (7, '3.0.20', 'Bookshelf-1.13.2-3.0.20.jar', 81769, 1500, '- Final RC for 1.13.2
- Implemented multiproject jenkins pipeline via Jenkinsfile.',
        '50bbe431610cdedaa847eff47e02762f92796afdd9e3ab07edf624c0aabe5a6ffe60b52fcddb8c620658b8b93b98c2808520aa67cb8f7b2fc9bca94ed00c6d00',
        'release', 'binary', 2, TRUE, 1, 1),
       (8, '4.4.74', 'Bookshelf-1.14.4-4.4.74.jar', 124990, 1500, '- Removed unused example code.
- Implemented Forge''s update checker.
- Added Discord webhook to build script.',
        '4b8d0e4ffc490cb91c2244810728bee1ea46c1f109a1981b5256f5af8359569ef5bff855ab7140ccb1d4f11fffb48377dfadc0cc78ae65d5bfbd3ee77b7fc230',
        'release', 'binary', 2, TRUE, 1, 1),
       (9, '5.6.40', 'Bookshelf-1.15.2-5.6.40.jar', 178961, 1500, '- Add modid based ItemPredicate
- Add ingredient based ItemPredicate
- Add modid ingredient.',
        '4f4267d8d8f81795e73b7cff887c6ab7e5957b50afd2a007690bdfd1de0a8a8a68807901f49765c72a6b98ac34a2d20b88d7302bfdce52c6eb31cfcaf572cb09',
        'release', 'binary', 2, TRUE, 1, 1);

INSERT INTO project_file_loaders(project_file_id, loader_id)
VALUES (9, 1);

INSERT INTO project_files(id, version, name, size, changelog, sha512, release_type, classifier, processing_status,
                          released, project_id, user_id)
VALUES (10, '1.0.0', 'forge_mod.jar', 100, 'Project file changelog',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 2, FALSE, 1, 1),
       (11, '1.0.1', 'forge_mod_signed.jar', 101, 'Project file changelog',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, FALSE, 1, 1),
       (12, '1.0.2', 'forge_mod_tampered.jar', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, FALSE, 1, 1),
       (13, '1.0.3', 'zip_archive.zip', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, FALSE, 1, 1),
       (14, '1.0.4', 'zip_archive.zip', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 5, FALSE, 1, 1),
       (15, '1.0.5', 'malware.txt', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, FALSE, 1, 1),
       (16, '1.0.6', 'non_existing_file.txt', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, FALSE, 1, 1),
       (17, '1.0.7', 'non_existing_file.txt', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 2, TRUE, 17, 1),
       (18, '1.0.8', 'zip_archive.zip', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, FALSE, 16, 1),
       (19, '1.5.8', 'zip_archive.zip', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, FALSE, 70, 1);

INSERT INTO project_file_dependencies(project_file_id, dependency_project_id)
VALUES (1, 2),
       (1, 3);

INSERT INTO project_file_game_versions(project_file_id, game_version_id)
VALUES (1, 89),
       (2, 99),
       (3, 104),
       (4, 107),
       (5, 110),
       (6, 113),
       (7, 116),
       (8, 121),
       (9, 124);

# dbfb6b1c-a8cc-4a41-995b-b9949a14394f
INSERT INTO api_tokens(token, name, user_id)
VALUES ('91e048664cf660bf7005b4002bece81b5099888fa8771654621fdfbf5702ceba4e7c447c301b11bdd64b19016ac4c57aca1b77cde97fbc016f74a2235293b9e3',
        'Testing', 1),
       ('50c42db342f7f7d403fce41c79fb4010fb2cc4fb9d061914c5836ae5af432eed0f1aa468cc363509b7b931c28687c215df835a6cfc9fdded5ec828a614a11be8',
        'Testing', 2),
       ('ec7f484ea5c13a89f9ee28fe0f4d4510d188c8fc48d76564427545d33d5f9c3ec1c9a301a2fcb509eeafd0d7353fcc785c682a25c89952fd948d92f6cd75d18d',
        'Testing', 1),
       ('35885ebfff24dafa101ed816ca60ce17b40ea8b18015ae4389e75ac375c2cf29f21c4e2eca9c77c0a5f3ad734ab64404c8274c91ebdf92d7e1a1919252155861',
        'Testing', 5);

INSERT INTO api_token_permissions(id, permission)
VALUES (1, 'project.edit'),
       (1, 'file.edit'),
       (1, 'file.delete'),
       (1, 'file.upload'),
       (2, 'project.edit'),
       (2, 'file.edit'),
       (2, 'file.delete'),
       (2, 'file.upload');

INSERT INTO nodecdn_commits(hash)
VALUES ('d9f5bb5b-22af-4f58-bb15-f6c8a373aae9');

INSERT INTO user_change_email (user_id, email, code)
VALUES (4, 'newemail@diluv.com', '12345678');

INSERT INTO images(id, ext, width)
VALUES ('games-minecraft-je', 'png', -1);

INSERT INTO project_review(project_id, reviewed_by)
VALUES (29, 1);

INSERT INTO project_request_change(project_review_id, reason)
VALUES (1, 'Reason for change');

INSERT INTO registration_codes(code, valid, user_id)
VALUES ('9cfc7734-faff-4c51-8ce6-9d3b83d63be9', TRUE, 1),
       ('8482bd6d-f4e4-46c1-b5f6-f319aa6802dc', FALSE, 1),
       ('9d54f2a1-1245-4343-a7ec-8a10e043f472', TRUE, 2),
       ('e6454299-4642-49d0-a24e-4991631965f8', FALSE, 2);

INSERT INTO notifications(text, type, viewed_at, user_id)
VALUES ('Project was released', 'GENERIC', NULL, 1),
       ('You have been invited to join the project', 'PROJECT_INVITE', NULL, 1),
       ('You have been invited to join the project', 'PROJECT_INVITE', NOW(), 1),
       ('Project was released', 'GENERIC', NULL, 2),
       ('Project was released', 'GENERIC', NOW(), 2),
       ('You have been invited to join the project', 'PROJECT_INVITE', NULL, 2),
       ('You have been invited to join the project', 'PROJECT_INVITE', NULL, 2),
       ('You have been invited to join the project', 'PROJECT_INVITE', NULL, 2),
       ('You have been invited to join the project', 'PROJECT_INVITE', NULL, 2),
       ('You have been invited to join the project', 'PROJECT_INVITE', NULL, 3);

INSERT INTO notification_project_invites(id, role, status, project_id, sender_id)
VALUES (2, 'Test', 'PENDING', 83, 2),
       (3, 'Test', 'PENDING', 83, 2),
       (6, 'Test', 'PENDING', 12, 1),
       (7, 'Test', 'CANCELLED', 12, 1),
       (8, 'Test', 'ACCEPTED', 12, 1),
       (9, 'Test', 'DECLINED', 12, 1),
       (10, 'Test', 'EXPIRED', 12, 1);