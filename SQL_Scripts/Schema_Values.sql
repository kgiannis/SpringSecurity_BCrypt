CREATE DATABASE IF NOT EXISTS `spring_sec`;
USE `spring_sec`;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `roles` (`role_id`, `role`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` text NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `users` (`user_id`, `status`, `username`, `password`, `enabled`) VALUES
	(11, 'AcTiVe MeMbEr', 'ccc', '$2a$10$KXyYF1LKNilBLUAloej/CuulqWIpLxePM4xPWLcMrov80PEKfkSIu', 1),
	(12, 'AcTiVe MeMbEr', 'ooo', '$2a$10$l0W9nBBLrG3eVZtjyoYbAef4WA3anjC0iyI6KEaCU.V46UmQiWx82', 1),
	(13, 'AcTiVe MeMbEr', 'ppp', '$2a$10$EABtpZAL2htIq6OxRrfe5uzfszAW77v9E.92Zua6YdqXsCcstn96G', 1),
	(14, 'AcTiVe MeMbEr', 'ppp2', '$2a$10$6oMxogZJgS85AHzFgwIHD.umh3QR7CAp6f7GQ6/JAAVQZCF4lzVLy', 1);

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `user_roles` (`user_id`, `role_id`) VALUES
	(11, 1),
	(12, 2);
