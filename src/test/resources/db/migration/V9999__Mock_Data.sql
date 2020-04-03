INSERT INTO users(id, username, display_name, email, password, password_type, mfa, mfa_secret, created_at)
VALUES (1, 'darkhax', 'Darkhax', 'darkhax@diluv.com', '$2y$12$Y09/RQkc7icbiOonlBqTeegjtk9VYPKamMTJqkFVtfKDawRwifc8i',
        'bcrypt', TRUE, NULL, NOW()),
       (2, 'jaredlll08', 'Jaredlll08', 'jaredlll08@diluv.com',
        '$2y$12$Y09/RQkc7icbiOonlBqTeegjtk9VYPKamMTJqkFVtfKDawRwifc8i', 'bcrypt', FALSE, NULL, NOW()),
       (3, 'lclc98', 'lclc98', 'lclc98@diluv.com',
        '$2y$12$Y09/RQkc7icbiOonlBqTeegjtk9VYPKamMTJqkFVtfKDawRwifc8i', 'bcrypt', FALSE, NULL, NOW());

INSERT INTO user_roles(user_id, role_id)
VALUES (2, 1);

INSERT INTO temp_users(id, username, email, password, password_type, created_at, verificationCode)
VALUES (1, 'tempuser', 'tempuser@diluv.com', '', '', NOW(), 'c1632ff7-367e-485f-91dd-92ab75903fa4'),
       (2, 'tempuser2', 'tempuser2@diluv.com', '', '', NOW(), '14964974-663a-4005-9cf2-d1f390c3b2cc'),
       (3, 'tempuser3', 'tempuser3@diluv.com', '', '', NOW(), '14164974-663a-4005-9cf2-d1f390c3b2cc');

INSERT INTO api_tokens(user_id, code, name)
VALUES (1, '4b3b85e3-f7ac-4c7b-b71a-df972909b213', 'testing token');

INSERT INTO projects(name, slug, summary, description, cached_downloads, review, released, created_at, updated_at,
                     user_id, game_slug, project_type_slug)
VALUES ('Bookshelf', 'bookshelf', 'An open source library for other mods!', 'An open source library for other mods!', 0,
        0, 1, FROM_UNIXTIME(1426128779), FROM_UNIXTIME(1583375717), 1, 'minecraft', 'mods'),
       ('Upgrade Modifiers', 'upgrade-modifiers',
        'Adds upgrade modifiers that can be used to improve your existing equipment.',
        'Adds upgrade modifiers that can be used to improve your existing equipment.', 0, 0, 1,
        FROM_UNIXTIME(1575426564), FROM_UNIXTIME(1581058918),
        1, 'minecraft', 'mods'),
       ('Splashy', 'splashy', 'Allows greater control over the splash text on the main menu.',
        'Allows greater control over the splash text on the main menu.', 0, 0, 1, FROM_UNIXTIME(1576326746),
        FROM_UNIXTIME(1583107620), 1,
        'minecraft', 'mods'),
       ('Modded Bingo', 'modded-bingo', 'Adds a bingo like scavenger hunt minigame to your modded world.',
        'Adds a bingo like scavenger hunt minigame to your modded world.', 0, 0, 1, FROM_UNIXTIME(1558066682),
        FROM_UNIXTIME(1580828240), 1,
        'minecraft', 'mods'),
       ('Enchantment Descriptions', 'enchantment-descriptions',
        'Provides a way to get enchantment descriptions from enchanted books.',
        'Provides a way to get enchantment descriptions from enchanted books.', 0, 0, 1, FROM_UNIXTIME(1472450423),
        FROM_UNIXTIME(1583225636), 1,
        'minecraft', 'mods'),
       ('Eerie Entities', 'eerie-entities', 'Adds new mobs that fit the minecraft atmosphere.',
        'Adds new mobs that fit the minecraft atmosphere.', 0, 0, 1, FROM_UNIXTIME(1539544785),
        FROM_UNIXTIME(1580546708), 1, 'minecraft', 'mods'),
       ('OldJavaWarning', 'oldjavawarning', 'Warns users when java is outdated.', 'Warns users when java is outdated.',
        0, 0, 1, FROM_UNIXTIME(1525740110), FROM_UNIXTIME(1579932587), 1, 'minecraft', 'mods'),
       ('Wawla - What Are We Looking At', 'wawla-what-are-we-looking-at',
        'Wawla is a mod that works along side Waila to further provide information about the...',
        'Wawla is a mod that works along side Waila to further provide information about the...', 0, 0, 1,
        FROM_UNIXTIME(1412537933),
        FROM_UNIXTIME(1580940550), 1, 'minecraft', 'mods'),
       ('Dark Utilities', 'dark-utilities', 'Expansive content mod which adds a little bit of everything.',
        'Expansive content mod which adds a little bit of everything.', 0, 0, 1, FROM_UNIXTIME(1456376130),
        FROM_UNIXTIME(1583382601), 1, 'minecraft',
        'mods'),
       ('Cursed', 'cursed', 'Adds new curses to Minecraft', 'Adds new curses to Minecraft', 0, 0, 1,
        FROM_UNIXTIME(1570773911),
        FROM_UNIXTIME(1583189079), 1, 'minecraft', 'mods'),
       ('Botany Pots', 'botany-pots', 'Adds pots that you can use to grow crops!',
        'Adds pots that you can use to grow crops!', 0, 0, 1, FROM_UNIXTIME(1575709035), FROM_UNIXTIME(1582103822), 1,
        'minecraft', 'mods'),
       ('Resource Hogs', 'resource-hogs', 'Adds pigs which can help players get resources.',
        'Adds pigs which can help players get resources.', 0, 0, 1, FROM_UNIXTIME(1537778190),
        FROM_UNIXTIME(1582159835), 1, 'minecraft', 'mods'),
       ('Hunting Dimension', 'hunting-dimension', 'Adds a dimension for hunting mobs.',
        'Adds a dimension for hunting mobs.', 0, 0, 1, FROM_UNIXTIME(1512817395), FROM_UNIXTIME(1579031323), 1,
        'minecraft', 'mods'),
       ('Thirsty Bottles', 'thirsty-bottles', 'A mod to make water bottles as thirsty as I am.',
        'A mod to make water bottles as thirsty as I am.', 0, 0, 1, FROM_UNIXTIME(1490299618),
        FROM_UNIXTIME(1582157328), 1, 'minecraft', 'mods'),
       ('Open Loader', 'open-loader', 'An open source resource and data loader for Minecraft.',
        'An open source resource and data loader for Minecraft.', 0, 0, 1, FROM_UNIXTIME(1576035011),
        FROM_UNIXTIME(1580592340), 1, 'minecraft',
        'mods'),
       ('Nightmares', 'nightmares', 'Adds nightmares and nightmare mobs', 'Adds nightmares and nightmare mobs', 0, 0, 1,
        FROM_UNIXTIME(1507134742), FROM_UNIXTIME(1582146181), 1, 'minecraft', 'mods'),
       ('Better Burning', 'better-burning', 'Improves the burning and fire mechanics of Minecraft.',
        'Improves the burning and fire mechanics of Minecraft.', 0, 0, 1, FROM_UNIXTIME(1575287161),
        FROM_UNIXTIME(1583098748), 1, 'minecraft',
        'mods'),
       ('AttributeFix', 'attributefix', 'Removes arbitrary limits on Minecraft\'s attribute system. Fixes MANY mods!',
        'Removes arbitrary limits on Minecraft\'s attribute system. Fixes MANY mods!', 0, 0, 1,
        FROM_UNIXTIME(1508788988), FROM_UNIXTIME(1583100221),
        1, 'minecraft', 'mods'),
       ('Bad Mobs', 'bad-mobs', 'A mod to blacklist mobs from spawning.', 'A mod to blacklist mobs from spawning.', 0,
        0, 1, FROM_UNIXTIME(1438051659), FROM_UNIXTIME(1583105091), 1, 'minecraft', 'mods'),
       ('Coloured Tooltips', 'coloured-tooltips', 'Allows the tooltip colors to be configured.',
        'Allows the tooltip colors to be configured.', 0, 0, 1, FROM_UNIXTIME(1516341777), FROM_UNIXTIME(1583108043),
        1, 'minecraft', 'mods'),
       ('More Swords Legacy', 'more-swords-legacy', 'The legacy of More Swords', 'The legacy of More Swords', 0, 0, 1,
        FROM_UNIXTIME(1558499363), FROM_UNIXTIME(1571187684), 1, 'minecraft', 'mods'),
       ('Friendly Fire', 'friendly-fire', 'Modifies combat to prevent friendly fire to pets and other mobs.',
        'Modifies combat to prevent friendly fire to pets and other mobs.', 0, 0, 1, FROM_UNIXTIME(1481691209),
        FROM_UNIXTIME(1571187431), 1,
        'minecraft', 'mods'),
       ('Plummet', 'plummet', 'Allows you to prevent flying in some circumstances.',
        'Allows you to prevent flying in some circumstances.', 0, 0, 1, FROM_UNIXTIME(1573167604),
        FROM_UNIXTIME(1573168137), 1, 'minecraft', 'mods'),
       ('Amnesia Blocks', 'amnesia-blocks', 'Makes you randomly forget what a block is.',
        'Makes you randomly forget what a block is.', 0, 0, 1, FROM_UNIXTIME(1567061806), FROM_UNIXTIME(1568835541),
        1, 'minecraft', 'mods'),
       ('Never Enough Candy', 'never-enough-candy', 'Adds new candys for you to eat and collect.',
        'Adds new candys for you to eat and collect.', 0, 0, 1, FROM_UNIXTIME(1444452425), FROM_UNIXTIME(1576294472),
        1, 'minecraft', 'mods'),
       ('Pillagers', 'pillagers', 'Allows you to get villager loot by killing them.',
        'Allows you to get villager loot by killing them.', 0, 0, 1, FROM_UNIXTIME(1519350324),
        FROM_UNIXTIME(1571187718), 1, 'minecraft', 'mods'),
       ('RedShark', 'redshark', 'Allows the logging and tracking of network packets.',
        'Allows the logging and tracking of network packets.', 0, 0, 1, FROM_UNIXTIME(1523329118),
        FROM_UNIXTIME(1571187793), 1, 'minecraft', 'mods'),
       ('Item Stages', 'item-stages', 'Allows items to be restricted to a stage.',
        'Allows items to be restricted to a stage.', 0, 0, 1, FROM_UNIXTIME(1508536731), FROM_UNIXTIME(1571187786), 1,
        'minecraft', 'mods'),
       ('Get Ya\' Tanks Here', 'get-ya-tanks-here', 'Adds fluid tanks of various materials.',
        'Adds fluid tanks of various materials.', 0, 0, 1, FROM_UNIXTIME(1420275000), FROM_UNIXTIME(1571187826), 1,
        'minecraft', 'mods'),
       ('Nautilus', 'nautilus', 'A mixin library that adds additional features for mods to take advantage of.',
        'A mixin library that adds additional features for mods to take advantage of.', 0, 0, 1,
        FROM_UNIXTIME(1526173745), FROM_UNIXTIME(1571187764),
        1, 'minecraft', 'mods'),
       ('Damage Control', 'damage-control', 'Allows for health and damage to be modified and rebalanced!',
        'Allows for health and damage to be modified and rebalanced!', 0, 0, 1, FROM_UNIXTIME(1487824492),
        FROM_UNIXTIME(1571187845), 1, 'minecraft',
        'mods'),
       ('Stuff A Sock In It', 'stuff-a-sock-in-it', 'Console filtering made easy!', 'Console filtering made easy!', 0,
        0, 1, FROM_UNIXTIME(1488691129), FROM_UNIXTIME(1571187443), 1, 'minecraft', 'mods'),
       ('Caliper', 'caliper', 'A collection of tools for testing and developing mods and modpacks.',
        'A collection of tools for testing and developing mods and modpacks.', 0, 0, 1, FROM_UNIXTIME(1493916343),
        FROM_UNIXTIME(1571190557), 1,
        'minecraft', 'mods'),
       ('Mob Banners', 'mob-banners', 'Adds decorative banners to commemorate your combat conquests.',
        'Adds decorative banners to commemorate your combat conquests.', 0, 0, 1, FROM_UNIXTIME(1538191953),
        FROM_UNIXTIME(1571187709), 1,
        'minecraft', 'mods'),
       ('Mod Debug World Type', 'mod-debug-world-type',
        'Allows the debug world type to show mods from a specific mod ID.',
        'Allows the debug world type to show mods from a specific mod ID.', 0, 0, 1, FROM_UNIXTIME(1549092831),
        FROM_UNIXTIME(1571187860), 1,
        'minecraft', 'mods'),
       ('More Swords Mod', 'more-swords-mod', 'A simple mod that adds new swords and enchantments into the game.',
        'A simple mod that adds new swords and enchantments into the game.', 0, 0, 1, FROM_UNIXTIME(1371980040),
        FROM_UNIXTIME(1556874728), 1,
        'minecraft', 'mods'),
       ('Snail Mail', 'snail-mail', 'Adds new types of mail to the game.', 'Adds new types of mail to the game.', 0, 0,
        1, FROM_UNIXTIME(1543395108), FROM_UNIXTIME(1543971012), 1, 'minecraft', 'mods'),
       ('Dark Utilities (Fabric)', 'dark-utilities-fabric', 'Adds random blocks that are sometimes useful.',
        'Adds random blocks that are sometimes useful.', 0, 0, 1, FROM_UNIXTIME(1545172644),
        FROM_UNIXTIME(1545176373), 1, 'minecraft', 'mods'),
       ('Informational Accessories', 'informational-accessories', 'Trinkets to help you better understand your world.',
        'Trinkets to help you better understand your world.', 0, 0, 1, FROM_UNIXTIME(1505453855),
        FROM_UNIXTIME(1543881759), 1, 'minecraft', 'mods'),
       ('JourneyMapStages', 'journeymapstages', 'Allows the JourneyMap mod to be staged with GameStages',
        'Allows the JourneyMap mod to be staged with GameStages', 0, 0, 1, FROM_UNIXTIME(1520540191),
        FROM_UNIXTIME(1552663257), 1, 'minecraft',
        'mods'),
       ('No AI Spawn Eggs', 'no-ai-spawn-eggs', 'Adds spawn eggs that spawn mobs with no AI.',
        'Adds spawn eggs that spawn mobs with no AI.', 0, 0, 1, FROM_UNIXTIME(1539034393), FROM_UNIXTIME(1543884635),
        1, 'minecraft', 'mods'),
       ('Tips', 'tips', 'This mod adds tips to loading menus. It also allows for modpacks and mods to...',
        'This mod adds tips to loading menus. It also allows for modpacks and mods to...', 0, 0, 1,
        FROM_UNIXTIME(1541576533),
        FROM_UNIXTIME(1550911369), 1, 'minecraft', 'mods'),
       ('Enchanting Plus', 'enchanting-plus', 'Enchanting Plus', 'Enchanting Plus', 0, 0, 1, FROM_UNIXTIME(1371579488),
        FROM_UNIXTIME(1560549086), 1,
        'minecraft', 'mods'),
       ('Conflux Cubes', 'conflux-cubes',
        'Adds blocks with randomized effects. Has support for custom content and Twitch integration.',
        'Adds blocks with randomized effects. Has support for custom content and Twitch integration.', 0, 0, 1,
        FROM_UNIXTIME(1550955595), FROM_UNIXTIME(1551167711), 1, 'minecraft', 'mods'),
       ('Stage Tabels', 'stage-tabels', 'Allows game stages to be placed into weighted tables.',
        'Allows game stages to be placed into weighted tables.', 0, 0, 1, FROM_UNIXTIME(1547433081),
        FROM_UNIXTIME(1551404157), 1, 'minecraft',
        'mods'),
       ('Dark Elevators', 'dark-elevators', 'Adds a simple elevator block to the game.',
        'Adds a simple elevator block to the game.', 0, 0, 1, FROM_UNIXTIME(1546154771), FROM_UNIXTIME(1557210505), 1,
        'minecraft', 'mods'),
       ('No Tema Stahp', 'no-tema-stahp', 'An addon for XU2 that removes some of the weird and cheaty stuff in XU2',
        'An addon for XU2 that removes some of the weird and cheaty stuff in XU2', 0, 0, 1, FROM_UNIXTIME(1544339050),
        FROM_UNIXTIME(1553196510), 1,
        'minecraft', 'mods'),
       ('Surge', 'surge', 'An open source performance enhancement mod.', 'An open source performance enhancement mod.',
        0, 0, 1, FROM_UNIXTIME(1472188259), FROM_UNIXTIME(1548629194), 1, 'minecraft', 'mods'),
       ('Kelpie', 'kelpie', 'Adds the kelpie from Scottish lore to MC.', 'Adds the kelpie from Scottish lore to MC.', 0,
        0, 1, FROM_UNIXTIME(1506993271), FROM_UNIXTIME(1543883866), 1, 'minecraft', 'mods'),
       ('LootTableTweaker', 'loottabletweaker', 'LootTable support for CraftTweaker / MineTweaker3',
        'LootTable support for CraftTweaker / MineTweaker3', 0, 0, 1, FROM_UNIXTIME(1487716299),
        FROM_UNIXTIME(1543883916), 1, 'minecraft', 'mods'),
       ('Biome Specific Dungeons', 'biome-specific-dungeons',
        'Adds new dungeons which are based on the dungeons they spawn in.',
        'Adds new dungeons which are based on the dungeons they spawn in.', 0, 0, 1, FROM_UNIXTIME(1551847678),
        FROM_UNIXTIME(1552336182), 1,
        'minecraft', 'mods'),
       ('Simply Arrows', 'simply-arrows', 'Adds new arrows. What do you expect.',
        'Adds new arrows. What do you expect.', 0, 0, 1, FROM_UNIXTIME(1516939956), FROM_UNIXTIME(1538777131), 1,
        'minecraft', 'mods'),
       ('No Worldgen 5 You', 'no-worldgen-5-you', 'A simple mod that allows worldgen to be disabled.',
        'A simple mod that allows worldgen to be disabled.', 0, 0, 1, FROM_UNIXTIME(1502919021),
        FROM_UNIXTIME(1538497936), 1, 'minecraft', 'mods'),
       ('Solar Village', 'solar-village', 'A basic solar panel for Tesla', 'A basic solar panel for Tesla', 0, 0, 1,
        FROM_UNIXTIME(1463870396), FROM_UNIXTIME(1538460048), 1, 'minecraft', 'mods'),
       ('Ore Stages', 'ore-stages', 'Allows blocks like ores to be hidden and staged with Game Stages',
        'Allows blocks like ores to be hidden and staged with Game Stages', 0, 0, 1, FROM_UNIXTIME(1521172290),
        FROM_UNIXTIME(1542928249), 1,
        'minecraft', 'mods'),
       ('Health Bars', 'health-bars', 'Adds health bars to enemy mobs.', 'Adds health bars to enemy mobs.', 0, 0, 1,
        FROM_UNIXTIME(1536810753), FROM_UNIXTIME(1542487132), 1, 'minecraft', 'mods'),
       ('Costume', 'costume', 'Adds halloween costumes to the game', 'Adds halloween costumes to the game', 0, 0, 1,
        FROM_UNIXTIME(1539310654), FROM_UNIXTIME(1539666473), 1, 'minecraft', 'mods'),
       ('Dimension Stages', 'dimension-stages', 'Allows access to dimensions to be restricted based on stages.',
        'Allows access to dimensions to be restricted based on stages.', 0, 0, 1, FROM_UNIXTIME(1496909591),
        FROM_UNIXTIME(1543878872), 1,
        'minecraft', 'mods'),
       ('Spooky Jam 2018', 'spooky-jam-2018', 'The results of MMD\'s SpookyJam 2018!',
        'The results of MMD\'s SpookyJam 2018!', 0, 0, 1, FROM_UNIXTIME(1539638931), FROM_UNIXTIME(1540248595), 1,
        'minecraft', 'mods'),
       ('Power Adapters', 'power-adapters', 'Adds new power adapter block to convert power.',
        'Adds new power adapter block to convert power.', 0, 0, 1, FROM_UNIXTIME(1518908037),
        FROM_UNIXTIME(1538512725), 1, 'minecraft', 'mods'),
       ('Cravings', 'cravings',
        'Adds cravings for random food items. Eating a desired food can give the player benefits.',
        'Adds cravings for random food items. Eating a desired food can give the player benefits.', 0, 0, 1,
        FROM_UNIXTIME(1510260104),
        FROM_UNIXTIME(1543877894), 1, 'minecraft', 'mods'),
       ('PigUtils', 'pigutils', 'A fork of iChunUtils without the EULA.', 'A fork of iChunUtils without the EULA.', 0,
        0, 1, FROM_UNIXTIME(1529347386), FROM_UNIXTIME(1538801894), 1, 'minecraft', 'mods'),
       ('Waila Stages', 'waila-stages',
        'Allows access to Waila HUD and specific tool tip info to be restricted based on...',
        'Allows access to Waila HUD and specific tool tip info to be restricted based on...', 0, 0, 1,
        FROM_UNIXTIME(1496984426),
        FROM_UNIXTIME(1538512764), 1, 'minecraft', 'mods'),
       ('TESLA', 'tesla', 'A power API', 'A power API', 0, 0, 1, FROM_UNIXTIME(1462179367), FROM_UNIXTIME(1507310625),
        1, 'minecraft', 'mods'),
       ('Spooky Jam', 'spooky-jam', 'The results of MMD\'s SpookyJam 2017!', 'The results of MMD\'s SpookyJam 2017!', 0,
        0, 1, FROM_UNIXTIME(1508151257), FROM_UNIXTIME(1508151574), 1, 'minecraft', 'mods'),
       ('Custom Tweaks', 'custom-tweaks', 'A series of tools to help customize modpacks',
        'A series of tools to help customize modpacks', 0, 0, 1, FROM_UNIXTIME(1471576171), FROM_UNIXTIME(1538449461),
        1, 'minecraft', 'mods'),
       ('Bookcase', 'bookcase', 'Additional utilities for making stardew mods and ensuring cross compatibility.',
        'Additional utilities for making stardew mods and ensuring cross compatibility.', 0, 0, 1,
        FROM_UNIXTIME(1530511531),
        FROM_UNIXTIME(1543479262), 1, 'minecraft', 'mods'),
       ('Price Tooltips', 'price-tooltips', 'Adds item sell price info to tooltips',
        'Adds item sell price info to tooltips', 0, 0, 1, FROM_UNIXTIME(1532578908), FROM_UNIXTIME(1536387325), 1,
        'minecraft', 'mods'),
       ('Natural Harvest', 'natural-harvest', 'Adds an abundance of natural resources to the world.',
        'Adds an abundance of natural resources to the world.', 0, 0, 1, FROM_UNIXTIME(1493406965),
        FROM_UNIXTIME(1495051439), 1, 'minecraft',
        'mods'),
       ('Waila Inhibitors', 'waila-inhibitors', 'This mod provides some balance to the Waila Mod.',
        'This mod provides some balance to the Waila Mod.', 0, 0, 1, FROM_UNIXTIME(1441076820),
        FROM_UNIXTIME(1445549924), 1, 'minecraft', 'mods'),
       ('Musica', 'musica',
        'A framework for easily adding new records into Minecraft. Great for music lovers and server...',
        'A framework for easily adding new records into Minecraft. Great for music lovers and server...', 0, 0, 1,
        FROM_UNIXTIME(1429233240), FROM_UNIXTIME(1429849826), 1, 'minecraft', 'mods'),
       ('Cosmetic Armor', 'cosmetic-armor', 'Because it\'s important to be stylish',
        'Because it\'s important to be stylish', 0, 0, 1, FROM_UNIXTIME(1416978702), FROM_UNIXTIME(1417390296), 1,
        'minecraft', 'mods'),
       ('StarStones', 'starstones', 'Meteors!', 'Meteors!', 0, 0, 1, FROM_UNIXTIME(1422387999),
        FROM_UNIXTIME(1434065271), 1, 'minecraft', 'mods'),
       ('Waila Events', 'waila-events', 'This mod adds several new events to the Waila mod.',
        'This mod adds several new events to the Waila mod.', 0, 0, 1, FROM_UNIXTIME(1439971529),
        FROM_UNIXTIME(1439989989), 1, 'minecraft', 'mods'),
       ('Get Ya\' Barrels Here', 'get-ya-barrels-here', 'Basic item storage and transport!',
        'Basic item storage and transport!', 0, 0, 1, FROM_UNIXTIME(1487979399), FROM_UNIXTIME(1491167140), 1,
        'minecraft', 'mods'),
       ('Game Stages', 'game-stages', 'An API for adding stages, for modpacks and other mods to use!',
        'An API for adding stages, for modpacks and other mods to use!', 0, 0, 1, FROM_UNIXTIME(1496012694),
        FROM_UNIXTIME(1571187452), 1,
        'minecraft', 'mods'),
       ('Mob Stages', 'mob-stages', 'This mod hooks in to the GameStage API, and allows mob spawning to be gated...',
        'This mod hooks in to the GameStage API, and allows mob spawning to be gated...', 0, 0, 1,
        FROM_UNIXTIME(1505796349),
        FROM_UNIXTIME(1543884038), 1, 'minecraft', 'mods'),
       ('Additional Banners', 'additional-banners', 'A whole new range of banner patterns, to enhance your world!',
        'A whole new range of banner patterns, to enhance your world!', 0, 0, 1, FROM_UNIXTIME(1430706855),
        FROM_UNIXTIME(1564433660), 1, 'minecraft',
        'mods'),
       ('Zalgo\'s Command', 'zalgo-s-command',
        'Adds a utility command, which lets you add combine multiple commands together.',
        'Adds a utility command, which lets you add combine multiple commands together.', 0, 0, 1,
        FROM_UNIXTIME(1495732981),
        FROM_UNIXTIME(1538512806), 1, 'minecraft', 'mods'),
       ('TinkerStages', 'tinkerstages',
        'This mod hooks in to the GameStage API, and allows various aspects of Tinkers Construct...',
        'This mod hooks in to the GameStage API, and allows various aspects of Tinkers Construct...', 0, 0, 1,
        FROM_UNIXTIME(1503601801), FROM_UNIXTIME(1540773110), 1, 'minecraft', 'mods'),
       ('Colorful Mobs', 'colorful-mobs', 'Add diversity to your Minecraft world, by adding more colors!',
        'Add diversity to your Minecraft world, by adding more colors!', 0, 0, 1, FROM_UNIXTIME(1426129663),
        FROM_UNIXTIME(1431582068), 1,
        'minecraft', 'mods'),
       ('Loot Chests', 'loot-chests',
        'A utility for map makers, which provides access to predefined loot table chests!',
        'A utility for map makers, which provides access to predefined loot table chests!', 0, 0, 1,
        FROM_UNIXTIME(1476369205), FROM_UNIXTIME(1478701535), 1, 'minecraft', 'mods'),
       ('MCStats - Twitch Integration', 'mcstats-twitch-integration', 'Shows your statistics in a Twitch Extension!',
        'Shows your statistics in a Twitch Extension!', 0, 0, 1, FROM_UNIXTIME(1566876588), FROM_UNIXTIME(1568489346),
        2, 'minecraft', 'mods'),
       ('ModTweaker', 'modtweaker',
        'ModTweaker is an addon for CraftTweaker, a recipe manipulator utility for Minecraft. It allows you...',
        'ModTweaker is an addon for CraftTweaker, a recipe manipulator utility for Minecraft. It allows you...', 0, 0,
        1, FROM_UNIXTIME(1401662691), FROM_UNIXTIME(1581643023), 2, 'minecraft', 'mods'),
       ('How Do I Do This Again?', 'how-do-i-do-this-again', 'Randomizes the main menu button locations.',
        'Randomizes the main menu button locations.', 0, 0, 1, FROM_UNIXTIME(1567006700), FROM_UNIXTIME(1567006920),
        2, 'minecraft', 'mods'),
       ('Ambient Environment', 'ambient-environment', 'Adds more ambiance to a Minecraft world',
        'Adds more ambiance to a Minecraft world', 0, 0, 1, FROM_UNIXTIME(1556150856), FROM_UNIXTIME(1583127730), 2,
        'minecraft', 'mods'),
       ('CraftTweaker', 'crafttweaker', 'A continuation of MineTweaker originally by StanH',
        'A continuation of MineTweaker originally by StanH', 0, 0, 1, FROM_UNIXTIME(1451259974),
        FROM_UNIXTIME(1582845758), 2, 'minecraft', 'mods'),
       ('Clumps', 'clumps', 'Clumps XP orbs together to reduce lag', 'Clumps XP orbs together to reduce lag', 0, 0, 1,
        FROM_UNIXTIME(1483789583), FROM_UNIXTIME(1563136846), 2, 'minecraft', 'mods'),
       ('TipTheScales', 'tipthescales',
        'Allows for more options when adjusting the GUIScale option as well as making it a...',
        'Allows for more options when adjusting the GUIScale option as well as making it a...', 0, 0, 1,
        FROM_UNIXTIME(1511212153),
        FROM_UNIXTIME(1553126238), 2, 'minecraft', 'mods'),
       ('SewingKit', 'sewingkit', 'It\'s CraftTweaker for fabric!', 'It\'s CraftTweaker for fabric!', 0, 0, 1,
        FROM_UNIXTIME(1546907649),
        FROM_UNIXTIME(1559643560), 2, 'minecraft', 'mods'),
       ('Fabric Controlling', 'fabric-controlling', 'Adds a search bar to the Key-Bindings menu',
        'Adds a search bar to the Key-Bindings menu', 0, 0, 1, FROM_UNIXTIME(1544587271), FROM_UNIXTIME(1570556618),
        2, 'minecraft', 'mods'),
       ('Controlling', 'controlling', 'Adds a search bar to the Key-Bindings menu',
        'Adds a search bar to the Key-Bindings menu', 0, 0, 1, FROM_UNIXTIME(1472418436), FROM_UNIXTIME(1580168022),
        2, 'minecraft', 'mods'),
       ('Recipe Stages', 'recipe-stages', 'Allows for Crafting table recipes to be locked behind stages.',
        'Allows for Crafting table recipes to be locked behind stages.', 0, 0, 1, FROM_UNIXTIME(1508862748),
        FROM_UNIXTIME(1566472126), 2,
        'minecraft', 'mods'),
       ('Prestige', 'prestige', 'Allows progress that extends beyond a world.',
        'Allows progress that extends beyond a world.', 0, 0, 1, FROM_UNIXTIME(1521874163), FROM_UNIXTIME(1566510360),
        2, 'minecraft', 'mods'),
       ('ContentTweaker', 'contenttweaker', 'Allows you to add Items and Blocks', 'Allows you to add Items and Blocks',
        0, 0, 1, FROM_UNIXTIME(1445007558), FROM_UNIXTIME(1545878794), 2, 'minecraft', 'mods'),
       ('Hotbar Hotswap', 'hotbar-hotswap',
        'Allows you to press a button corresponding to a hotbar slot and move an item...',
        'Allows you to press a button corresponding to a hotbar slot and move an item...', 0, 0, 1,
        FROM_UNIXTIME(1531428949),
        FROM_UNIXTIME(1531857465), 2, 'minecraft', 'mods'),
       ('Fused', 'fused', 'Makes gunpowder placeable and trigger TNT blocks',
        'Makes gunpowder placeable and trigger TNT blocks', 0, 0, 1, FROM_UNIXTIME(1510515738),
        FROM_UNIXTIME(1510515855), 2, 'minecraft', 'mods'),
       ('World Book', 'world-book', 'Makes the world selection screen easier to use and find worlds',
        'Makes the world selection screen easier to use and find worlds', 0, 0, 1, FROM_UNIXTIME(1546753941),
        FROM_UNIXTIME(1546754056), 2,
        'minecraft', 'mods'),
       ('SAtIn', 'satin', 'Allows to custom Attack Indicator locations and scale',
        'Allows to custom Attack Indicator locations and scale', 0, 0, 1, FROM_UNIXTIME(1517360905),
        FROM_UNIXTIME(1517360982), 2, 'minecraft',
        'mods'),
       ('PrimalChests', 'primalchests', 'Adds intermediate chests that cannot hold as much as a normal chests',
        'Adds intermediate chests that cannot hold as much as a normal chests', 0, 0, 1, FROM_UNIXTIME(1503346503),
        FROM_UNIXTIME(1521493174), 2,
        'minecraft', 'mods'),
       ('WaitingTime', 'waitingtime', 'Adds a pong game to play while the pack is loading',
        'Adds a pong game to play while the pack is loading', 0, 0, 1, FROM_UNIXTIME(1514327681),
        FROM_UNIXTIME(1548714806), 2, 'minecraft', 'mods'),
       ('PDQ Paths', 'pdq-paths', 'Paths that are pretty damn quick', 'Paths that are pretty damn quick', 0, 0, 1,
        FROM_UNIXTIME(1542331746), FROM_UNIXTIME(1542331969), 2, 'minecraft', 'mods'),
       ('MTLib', 'mtlib', 'Library files for Minetweaker Addons', 'Library files for Minetweaker Addons', 0, 0, 1,
        FROM_UNIXTIME(1478253985), FROM_UNIXTIME(1551999748), 2, 'minecraft', 'mods'),
       ('WAIM - What am I missing?', 'waim-what-am-i-missing',
        'A mod that makes the missing entries gui a bit easier to traverse!',
        'A mod that makes the missing entries gui a bit easier to traverse!', 0, 0, 1, FROM_UNIXTIME(1517361561),
        FROM_UNIXTIME(1517361814), 2,
        'minecraft', 'mods'),
       ('Trash Trash', 'trash-trash',
        'Makes it easy to quickly trash \'Trash\' quality items without opening the inventory!',
        'Makes it easy to quickly trash \'Trash\' quality items without opening the inventory!', 0, 0, 1,
        FROM_UNIXTIME(1531362124),
        FROM_UNIXTIME(1531362312), 2, 'minecraft', 'mods'),
       ('Reputed', 'reputed', 'Allows for the tracking of weapon kills!', 'Allows for the tracking of weapon kills!', 0,
        0, 1, FROM_UNIXTIME(1518270101), FROM_UNIXTIME(1520030404), 2, 'minecraft', 'mods'),
       ('You\'ve Got Mail - Twitch Integration', 'you-ve-got-mail-twitch-integration',
        'Allows for viewers to buy ingame mail for streamers!', 'Allows for viewers to buy ingame mail for streamers!',
        0, 0, 1, FROM_UNIXTIME(1543579597), FROM_UNIXTIME(1549420940), 2, 'minecraft', 'mods'),
       ('ColCol', 'colcol', 'Addadd somsom colcol toto youyou liflif.', 'Addadd somsom colcol toto youyou liflif.', 0,
        0, 1, FROM_UNIXTIME(1537698963), FROM_UNIXTIME(1537699040), 2, 'minecraft', 'mods'),
       ('DarkRooms', 'darkrooms', 'Brings back the old dark room mechanic for farming flowers easily!',
        'Brings back the old dark room mechanic for farming flowers easily!', 0, 0, 1, FROM_UNIXTIME(1548254796),
        FROM_UNIXTIME(1548256311), 2,
        'minecraft', 'mods'),
       ('Item Tooltips', 'item-tooltips', 'Displays the name of the currently held item.',
        'Displays the name of the currently held item.', 0, 0, 1, FROM_UNIXTIME(1531014424),
        FROM_UNIXTIME(1542486882), 2, 'minecraft', 'mods'),
       ('SlimyBoyos', 'slimyboyos', 'Makes slimes pickup items.', 'Makes slimes pickup items.', 0, 0, 1,
        FROM_UNIXTIME(1510761744),
        FROM_UNIXTIME(1510761773), 2, 'minecraft', 'mods'),
       ('Health Indicators', 'health-indicators', 'Plays a sound when you are low on health.',
        'Plays a sound when you are low on health.', 0, 0, 1, FROM_UNIXTIME(1442105852), FROM_UNIXTIME(1442106849), 2,
        'minecraft', 'mods'),
       ('FTB Tweaks', 'ftb-tweaks', 'This is a very simple mod that introduces the concept of game modes. For want...',
        'This is a very simple mod that introduces the concept of game modes. For want...', 0, 0, 1,
        FROM_UNIXTIME(1445106481), FROM_UNIXTIME(1495550385), 2, 'minecraft', 'mods'),
       ('Visualize', 'visualize', 'Visualize syncs video options between modpacks!',
        'Visualize syncs video options between modpacks!', 0, 0, 1, FROM_UNIXTIME(1497368958),
        FROM_UNIXTIME(1498839553), 2, 'minecraft', 'mods'),
       ('Initial Inventory', 'initial-inventory', 'Allows you to set an Initial Inventory for a player using ZenScript',
        'Allows you to set an Initial Inventory for a player using ZenScript', 0, 0, 1, FROM_UNIXTIME(1478289201),
        FROM_UNIXTIME(1502919051), 2,
        'minecraft', 'mods'),
       ('Pixelmongo', 'pixelmongo', 'Pixelmongo is an addon to Pixelmon that adds pixelmon pokestops to Minecraft',
        'Pixelmongo is an addon to Pixelmon that adds pixelmon pokestops to Minecraft', 0, 0, 1,
        FROM_UNIXTIME(1494530964), FROM_UNIXTIME(1494531094),
        2, 'minecraft', 'mods'),
       ('Custom NPCs Spawner', 'custom-npcs-spawner',
        'Allows Clientside-cloned NPCs to be used in multiple worlds automagically, and allows servers to have...',
        'Allows Clientside-cloned NPCs to be used in multiple worlds automagically, and allows servers to have...', 0,
        0, 1, FROM_UNIXTIME(1439238704), FROM_UNIXTIME(1508590784), 2, 'minecraft', 'mods'),
       ('Tanks', 'tanks', 'Adds tanks that can hold 32 buckets of any liquid',
        'Adds tanks that can hold 32 buckets of any liquid', 0, 0, 1, FROM_UNIXTIME(1451582922),
        FROM_UNIXTIME(1460640474), 2, 'minecraft', 'mods'),
       ('{ JSONAbles }', 'jsonables', 'Add Tinker\'s Construct tools via json!',
        'Add Tinker\'s Construct tools via json!', 0, 0, 1, FROM_UNIXTIME(1425900845), FROM_UNIXTIME(1456684790), 2,
        'minecraft', 'mods'),
       ('Fluxed Trinkets', 'fluxed-trinkets', 'RF Powered Baubles', 'RF Powered Baubles', 0, 0, 1,
        FROM_UNIXTIME(1410258283), FROM_UNIXTIME(1415804138), 2, 'minecraft', 'mods'),
       ('Fluxed-Crystals', 'fluxed-crystals', 'RF-Powered Crystal Farming', 'RF-Powered Crystal Farming', 0, 0, 1,
        FROM_UNIXTIME(1418153072), FROM_UNIXTIME(1433446981), 2, 'minecraft', 'mods'),
       ('Harvest Reader', 'harvest-reader', 'Adds an item to display information about Harvest Festival crops!',
        'Adds an item to display information about Harvest Festival crops!', 0, 0, 1, FROM_UNIXTIME(1497213017),
        FROM_UNIXTIME(1497213125), 2,
        'minecraft', 'mods'),
       ('Mystical Trinkets', 'mystical-trinkets', 'Mystical Trinkets', 'Mystical Trinkets', 0, 0, 1,
        FROM_UNIXTIME(1398643005),
        FROM_UNIXTIME(1404340023), 2, 'minecraft', 'mods'),
       ('Machines and Stuff', 'machines-and-stuff', 'Adds useful machines and generators!',
        'Adds useful machines and generators!', 0, 0, 1, FROM_UNIXTIME(1494282563), FROM_UNIXTIME(1495575112), 2,
        'minecraft', 'mods'),
       ('Regressments', 'regressments', 'Removes advancements', 'Removes advancements', 0, 0, 1,
        FROM_UNIXTIME(1499308297), FROM_UNIXTIME(1499308384),
        2, 'minecraft', 'mods'),
       ('Fluxed-Crystals 2', 'fluxed-crystals-2', 'Generate resources and materials at the expense of RF!',
        'Generate resources and materials at the expense of RF!', 0, 0, 1, FROM_UNIXTIME(1435188634),
        FROM_UNIXTIME(1439913880), 2, 'minecraft',
        'mods'),
       ('Fluxed-Core', 'fluxed-core', 'A mod that contains helper functions for all of my mods',
        'A mod that contains helper functions for all of my mods', 0, 0, 1, FROM_UNIXTIME(1444912766),
        FROM_UNIXTIME(1460629442), 2, 'minecraft',
        'mods'),
       ('JAIM - Just Another Information Mod', 'jaim-just-another-information-mod',
        'Overhauls the select world screen and displays extra information!',
        'Overhauls the select world screen and displays extra information!', 0, 0, 1, FROM_UNIXTIME(1481492093),
        FROM_UNIXTIME(1481492202), 2,
        'minecraft', 'mods');

INSERT INTO project_authors(project_id, user_id, role)
VALUES (94, 1, 'Coauthor'),
       (44, 2, 'Coauthor'),
       (48, 2, 'Coauthor'),
       (60, 2, 'Coauthor'),
       (6, 2, 'Coauthor');

INSERT INTO project_authors(id, project_id, user_id, role)
VALUES (6, 1, 2, 'Testing'),
       (7, 1, 3, 'Testing');

INSERT INTO project_author_permissions(project_author_id, permission)
VALUES (6, 'project.upload'),
       (6, 'project.edit');

INSERT INTO project_categories(project_id, categories_id)
VALUES (1, 1);

INSERT INTO project_links(project_id, type, url)
VALUES (1, 'Patreon', 'https://www.patreon.com/diluv');

INSERT INTO featured_games(slug)
VALUES ('minecraft');

INSERT INTO featured_projects(project_id)
VALUES (1),
       (2),
       (99);

INSERT INTO refresh_tokens(user_id, code, expired_at)
VALUES (1, '9bd63558-3835-4e01-963f-66a0f467291c', NOW() + INTERVAL 1 DAY);

INSERT INTO refresh_tokens(user_id, code, expired_at)
VALUES (1, 'de4602e9-3b81-412f-aaa5-95092b080266', NOW() + INTERVAL 1 DAY);

INSERT INTO refresh_tokens(user_id, code, expired_at)
VALUES (2, '592d3885-2fa1-4987-8626-e22c1e92e479', NOW() + INTERVAL 1 DAY);

INSERT INTO refresh_tokens(user_id, code, expired_at)
VALUES (2, '592d3285-2fa1-4987-8626-e22c1e92e479', NOW() + INTERVAL 1 DAY);

INSERT INTO refresh_tokens(user_id, code, expired_at)
VALUES (2, '52236885-2fa1-4987-8626-e22c1e92e479', NOW() + INTERVAL 1 DAY);

INSERT INTO news(slug, title, summary, description, user_id)
VALUES ('example', 'Example Post', 'Summary', 'Example', 1);

INSERT INTO password_reset(user_id, code, created_at)
VALUES (2, 'daf1f148-effd-400e-9b65-a4bf96e5215d', NOW());

INSERT INTO user_compromised_passwords(password_hash, occurrences)
VALUES ('025160DEE13179BC80BB05102CE5B3CD3FE', 11);

INSERT INTO email_domain_blacklist(domain)
VALUES ('banned.com'),
       ('banned2.com');

INSERT INTO email_blacklist (email)
VALUES ('blacklisted@diluv.com');

INSERT INTO email_sent(message_id, email, type, sent_at)
VALUES ('e4a291f7-740a-4b88-bc32-63e97e2d0812', 'test@diluv.com', 'test', NOW());

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id, user_id)
VALUES (1, 'forge_mod.jar', 100, 'Project file changelog',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 2, 1, 1, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id, user_id)
VALUES (2, 'forge_mod_signed.jar', 101, 'Project file changelog',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id, user_id)
VALUES (3, 'forge_mod_tampered.jar', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id, user_id)
VALUES (4, 'zip_archive.zip', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id, user_id)
VALUES (5, 'zip_archive.zip', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 5, 0, 1, 1);

INSERT INTO project_files(id, name, size, changelog, sha512, release_type, classifier, processing_status, released,
                          project_id, user_id)
VALUES (6, 'malware.txt', 1000, 'Changing',
        '5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3',
        'release', 'binary', 0, 0, 1, 1);

INSERT INTO project_file_game_versions(project_file_id, game_version_id)
VALUES (1, 6);