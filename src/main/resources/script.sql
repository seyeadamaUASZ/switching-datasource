CREATE DATABASE  IF NOT EXISTS `db_national`;
USE `db_national`;
--
-- Table structure for table `student`
--
DROP TABLE IF EXISTS `user_national`;
CREATE TABLE `user_national` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) DEFAULT NULL,
                           `age` varchar(45) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--
-- Data for table `user_national`
--
INSERT INTO `user_national` VALUES
                          (1,'ranil','18'),
                          (2,'anil','17'),
                          (3,'sunil','16');
CREATE DATABASE  IF NOT EXISTS `db_international`;
USE `db_international`;
--
-- Table structure for table `user_international`
--
DROP TABLE IF EXISTS `user_international`;
CREATE TABLE `user_international` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) DEFAULT NULL,
                           `age` varchar(45) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--
-- Data for table `user_international`
--
INSERT INTO `user_international` VALUES
                          (1,'cheikh dieng','15'),
                          (2,'mountary','16'),
                          (3,'bamba dieng','18');