DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT 'email',
  `mobile` varchar(30) DEFAULT '' COMMENT 'mobile',
  `name` varchar(50) DEFAULT '' COMMENT 'name',
  `position` varchar(50) DEFAULT '' COMMENT 'position',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

