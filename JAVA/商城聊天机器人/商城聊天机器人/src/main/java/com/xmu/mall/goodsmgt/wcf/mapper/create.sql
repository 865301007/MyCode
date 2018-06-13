
-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `142548_brand`;
CREATE TABLE `142548_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `website` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `type` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `142548_category`;
CREATE TABLE `142548_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `upper_category_id` bigint(20) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT '0',
  `type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `upperCategoryId` (`upper_category_id`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`upper_category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `142548_category`;
CREATE TABLE `142548_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `upper_category_id` bigint(20) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT '0',
  `type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `upperCategoryId` (`upper_category_id`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`upper_category_id`) REFERENCES `142548_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `142548_goods`;
CREATE TABLE `142548_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `serial_code` varchar(40) NOT NULL,
  `weight` double(12,0) unsigned zerofill DEFAULT NULL,
  `stock_count` int(11) unsigned zerofill DEFAULT '00000000000',
  `pre_sale_number` int(11) unsigned zerofill DEFAULT '00000000000',
  `store_id` varchar(40) DEFAULT NULL,
  `mid_user_price` double(12,0) unsigned zerofill DEFAULT '000000000000',
  `market_price` double(12,0) unsigned zerofill DEFAULT '000000000000',
  `real_price` double(12,0) unsigned zerofill DEFAULT '000000000000',
  `redeem_point` int(11) DEFAULT '0',
  `category_id` bigint(11) DEFAULT NULL,
  `brand_id` bigint(11) DEFAULT NULL,
  `stand_id` bigint(11) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `description` longtext,
  `image_path` varchar(999) DEFAULT NULL,
  `is_dividable` tinyint(1) DEFAULT '0',
  `default_express` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `serialCode` (`serial_code`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;