CREATE TABLE `promo_points` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(250) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT NULL,
  `updated_by` varchar(250) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `points` bigint(20) NOT NULL,
  `promo_code` varchar(255) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa4smy9qnht9g67fwxbkw25yr8` (`customer_id`),
  CONSTRAINT `FKa4smy9qnht9g67fwxbkw25yr8` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
