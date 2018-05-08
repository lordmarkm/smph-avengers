CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(250) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` char(1) DEFAULT NULL,
  `updated_by` varchar(250) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `coupon_code` varchar(255) NOT NULL,
  `priority` int(11) NOT NULL,
  `promo_code` varchar(255) NOT NULL,
  `redeemer_email` varchar(255) DEFAULT NULL,
  `redeemer_uuid` varchar(255) DEFAULT NULL,
  `reward` varchar(255) NOT NULL,
  `redemption_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
