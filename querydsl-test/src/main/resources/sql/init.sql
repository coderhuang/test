CREATE TABLE `book` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  `author` varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  `price` decimal(10,5) DEFAULT NULL,
  `category` mediumint NOT NULL DEFAULT '0',
  `flag_bit` int NOT NULL DEFAULT '0',
  `sku_code` bigint NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci


CREATE TABLE `sku_property` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `sku_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `sku_code` bigint unsigned NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `SKU_CODE_UNQ` (`sku_code`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci