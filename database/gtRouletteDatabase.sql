/*
SQLyog Community v12.2.5 (64 bit)
MySQL - 10.1.16-MariaDB : Database - gt_roulette_database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gt_roulette_database` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `gt_roulette_database`;

/*Table structure for table `gt_bets` */

DROP TABLE IF EXISTS `gt_bets`;

CREATE TABLE `gt_bets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_config_id` int(11) DEFAULT NULL,
  `bet_type` varchar(20) DEFAULT NULL,
  `min_stake_value` float DEFAULT NULL,
  `max_stake_value` float DEFAULT NULL,
  `payout` int(11) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `gt_bets` */

insert  into `gt_bets`(`id`,`game_config_id`,`bet_type`,`min_stake_value`,`max_stake_value`,`payout`,`modified_date`,`created_date`) values 
(1,1,'COLUMN',0,250,3,'2017-09-27 13:04:13','2017-09-27 13:04:20'),
(2,1,'CORNER',0,100,9,'2017-09-27 13:04:13','2017-09-27 14:00:45'),
(3,1,'DOZEN',0,250,3,'2017-09-27 13:04:13','2017-09-27 14:01:07'),
(4,1,'OUTSIDE',0,500,2,'2017-09-27 13:04:13','2017-09-27 14:01:14'),
(5,1,'PICK_1',0,25,36,'2017-09-27 13:04:13','2017-09-27 14:01:27'),
(6,1,'SIX',0,150,6,'2017-09-27 13:04:13','2017-09-27 14:01:35'),
(7,1,'SPLIT',0,50,18,'2017-09-27 13:04:13','2017-09-27 14:01:44'),
(8,1,'STREET',0,250,12,'2017-09-27 13:04:13','2017-09-27 14:01:50');

/*Table structure for table `gt_game_account` */

DROP TABLE IF EXISTS `gt_game_account`;

CREATE TABLE `gt_game_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '0->close 1->spin',
  `bet_amount` float DEFAULT NULL,
  `draw` tinyint(4) DEFAULT NULL,
  `winnings` float DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

/*Data for the table `gt_game_account` */

insert  into `gt_game_account`(`id`,`player_id`,`state`,`bet_amount`,`draw`,`winnings`,`modified_date`,`created_date`) values 
(6,0,0,0.04,22,39.02,'2017-10-10 19:05:22',NULL),
(7,0,0,0.04,26,36.05,'2017-10-11 10:52:04',NULL),
(8,0,0,28.1,2,0.17,'2017-10-11 10:54:15',NULL),
(9,0,0,28.1,16,3.11,'2017-10-11 10:57:25',NULL),
(10,0,0,28.1,7,39.03,'2017-10-11 13:35:49',NULL),
(11,0,0,28.1,16,3.11,'2017-10-11 13:36:22',NULL),
(12,0,0,28.1,34,3.02,'2017-10-11 14:31:38',NULL),
(13,0,0,28.1,2,0.17,'2017-10-11 14:55:07',NULL),
(14,0,0,28.1,7,39.03,'2017-10-11 14:55:43',NULL),
(15,0,0,28.1,15,0.27,'2017-10-11 14:56:53',NULL),
(16,0,0,28.1,18,36.14,'2017-10-11 15:00:42',NULL),
(17,0,0,28.1,31,21,'2017-10-11 15:01:46',NULL),
(18,0,0,28.1,15,0.27,'2017-10-11 15:13:21',NULL),
(19,0,0,28.1,0,0.09,'2017-10-11 15:26:27',NULL),
(20,0,0,28.1,31,21,'2017-10-11 15:26:52',NULL),
(21,0,0,28.1,7,39.03,'2017-10-11 15:30:01',NULL),
(22,0,0,28.1,21,36.03,'2017-10-11 16:42:37',NULL),
(23,0,0,28.1,35,0.03,'2017-10-11 16:45:05',NULL),
(24,0,0,28.1,6,0.08,'2017-10-11 16:48:37',NULL),
(25,0,0,28.1,27,234.03,'2017-10-11 16:48:57',NULL),
(26,0,0,28.1,5,0.06,'2017-10-11 16:49:27',NULL),
(27,0,0,28.1,0,0.09,'2017-10-11 16:53:09',NULL),
(28,0,0,28.1,31,21,'2017-10-11 16:56:41',NULL),
(29,0,0,28.1,15,0.27,'2017-10-11 17:19:26',NULL),
(30,0,0,28.1,29,18.03,'2017-10-11 17:20:23',NULL),
(31,0,0,28.1,17,36.21,'2017-10-11 17:22:49',NULL),
(32,0,0,28.1,5,0.06,'2017-10-12 11:13:14',NULL),
(33,0,0,28.1,7,39.03,'2017-10-12 11:17:41',NULL),
(34,0,0,28.1,33,0.03,'2017-10-13 09:30:14',NULL),
(35,0,0,28.1,33,0.03,'2017-10-13 09:33:58',NULL),
(36,0,0,28.1,31,21,'2017-10-13 09:34:09',NULL),
(37,0,0,0.01,35,0,'2017-10-13 10:47:35',NULL),
(38,0,0,28.1,17,36.21,'2017-10-23 19:03:07',NULL),
(39,0,0,28.1,3,0.15,'2017-10-23 19:03:54',NULL),
(40,0,0,28.1,13,3.24,'2017-10-24 10:41:41',NULL),
(41,0,0,28.1,36,0.05,'2017-10-25 14:32:06',NULL),
(42,0,0,28.1,14,0.47,'2017-10-25 14:36:30',NULL);

/*Table structure for table `gt_game_config` */

DROP TABLE IF EXISTS `gt_game_config`;

CREATE TABLE `gt_game_config` (
  `game_config_id` int(11) NOT NULL AUTO_INCREMENT,
  `domain` varchar(255) NOT NULL,
  `min_animation_time` int(11) DEFAULT '1000' COMMENT 'minimum time taken for spinning the wheel on UI.',
  `max_animation_time` int(11) DEFAULT NULL COMMENT 'maximum time taken for spinning the wheel on UI.',
  `min_stake` float DEFAULT '0.1',
  `max_stake` float DEFAULT NULL,
  `default_stake` float DEFAULT '0.1',
  `max_winnings` float DEFAULT NULL,
  `min_number` tinyint(4) DEFAULT '0' COMMENT 'lower bound of range numbers to draw',
  `max_number` tinyint(4) DEFAULT '36' COMMENT 'upper bound of range of numbers to draw',
  `total_draws` int(11) DEFAULT '1' COMMENT 'count of numbers to draw at a time',
  `draw_duplicates` tinyint(1) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0 -> inactive, 1 -> active',
  PRIMARY KEY (`game_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `gt_game_config` */

insert  into `gt_game_config`(`game_config_id`,`domain`,`min_animation_time`,`max_animation_time`,`min_stake`,`max_stake`,`default_stake`,`max_winnings`,`min_number`,`max_number`,`total_draws`,`draw_duplicates`,`status`) values 
(1,'localhost',1000,30000,0.1,1000,0.1,100000,0,36,1,1,1),
(2,'192.168.0.50',1000,NULL,1,2000,0.1,100000,0,36,1,1,1);

/*Table structure for table `gt_player_bets` */

DROP TABLE IF EXISTS `gt_player_bets`;

CREATE TABLE `gt_player_bets` (
  `game_id` int(11) DEFAULT NULL,
  `player_id` int(11) DEFAULT NULL,
  `bet_name` varchar(255) DEFAULT NULL,
  `stake` float DEFAULT NULL,
  `selection` varchar(255) DEFAULT NULL,
  KEY `game_id` (`game_id`),
  CONSTRAINT `gt_player_bets_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `gt_game_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `gt_player_bets` */

insert  into `gt_player_bets`(`game_id`,`player_id`,`bet_name`,`stake`,`selection`) values 
(28,0,'PICK_1',1,'17'),
(28,0,'PICK_1',1,'18'),
(28,0,'PICK_1',1,'19'),
(28,0,'PICK_1',1,'12'),
(28,0,'PICK_1',1,'21'),
(28,0,'PICK_1',1,'22'),
(28,0,'PICK_1',1,'23'),
(28,0,'PICK_1',1,'25'),
(28,0,'PICK_1',1,'27'),
(28,0,'OUTSIDE_EVEN',0.01,''),
(28,0,'COLUMN_1_34',1,''),
(28,0,'COLUMN_2_35',0.01,''),
(28,0,'COLUMN_3_36',0.01,''),
(28,0,'CORNER_0_1_2_3',0.01,''),
(28,0,'CORNER_10_11_13_14',0.01,''),
(28,0,'CORNER_11_12_14_15',0.01,''),
(28,0,'CORNER_13_14_16_17',0.01,''),
(28,0,'CORNER_14_15_17_18',0.01,''),
(28,0,'DOZEN_1_12',0.01,''),
(28,0,'SIX_10_15',0.01,''),
(28,0,'SPLIT_29_30',1,''),
(28,0,'SPLIT_28_31',1,''),
(28,0,'SPLIT_27_30',11,''),
(28,0,'SPLIT_25_26',2,''),
(28,0,'STREET_7_8_9',3,''),
(29,0,'PICK_1',1,'17'),
(29,0,'PICK_1',1,'18'),
(29,0,'PICK_1',1,'19'),
(29,0,'PICK_1',1,'12'),
(29,0,'PICK_1',1,'21'),
(29,0,'PICK_1',1,'22'),
(29,0,'PICK_1',1,'23'),
(29,0,'PICK_1',1,'25'),
(29,0,'PICK_1',1,'27'),
(29,0,'OUTSIDE_EVEN',0.01,''),
(29,0,'COLUMN_1_34',1,''),
(29,0,'COLUMN_2_35',0.01,''),
(29,0,'COLUMN_3_36',0.01,''),
(29,0,'CORNER_0_1_2_3',0.01,''),
(29,0,'CORNER_10_11_13_14',0.01,''),
(29,0,'CORNER_11_12_14_15',0.01,''),
(29,0,'CORNER_13_14_16_17',0.01,''),
(29,0,'CORNER_14_15_17_18',0.01,''),
(29,0,'DOZEN_1_12',0.01,''),
(29,0,'SIX_10_15',0.01,''),
(29,0,'SPLIT_29_30',1,''),
(29,0,'SPLIT_28_31',1,''),
(29,0,'SPLIT_27_30',11,''),
(29,0,'SPLIT_25_26',2,''),
(29,0,'STREET_7_8_9',3,''),
(30,0,'PICK_1',1,'17'),
(30,0,'PICK_1',1,'18'),
(30,0,'PICK_1',1,'19'),
(30,0,'PICK_1',1,'12'),
(30,0,'PICK_1',1,'21'),
(30,0,'PICK_1',1,'22'),
(30,0,'PICK_1',1,'23'),
(30,0,'PICK_1',1,'25'),
(30,0,'PICK_1',1,'27'),
(30,0,'OUTSIDE_EVEN',0.01,''),
(30,0,'COLUMN_1_34',1,''),
(30,0,'COLUMN_2_35',0.01,''),
(30,0,'COLUMN_3_36',0.01,''),
(30,0,'CORNER_0_1_2_3',0.01,''),
(30,0,'CORNER_10_11_13_14',0.01,''),
(30,0,'CORNER_11_12_14_15',0.01,''),
(30,0,'CORNER_13_14_16_17',0.01,''),
(30,0,'CORNER_14_15_17_18',0.01,''),
(30,0,'DOZEN_1_12',0.01,''),
(30,0,'SIX_10_15',0.01,''),
(30,0,'SPLIT_29_30',1,''),
(30,0,'SPLIT_28_31',1,''),
(30,0,'SPLIT_27_30',11,''),
(30,0,'SPLIT_25_26',2,''),
(30,0,'STREET_7_8_9',3,''),
(31,0,'PICK_1',1,'17'),
(31,0,'PICK_1',1,'18'),
(31,0,'PICK_1',1,'19'),
(31,0,'PICK_1',1,'12'),
(31,0,'PICK_1',1,'21'),
(31,0,'PICK_1',1,'22'),
(31,0,'PICK_1',1,'23'),
(31,0,'PICK_1',1,'25'),
(31,0,'PICK_1',1,'27'),
(31,0,'OUTSIDE_EVEN',0.01,''),
(31,0,'COLUMN_1_34',1,''),
(31,0,'COLUMN_2_35',0.01,''),
(31,0,'COLUMN_3_36',0.01,''),
(31,0,'CORNER_0_1_2_3',0.01,''),
(31,0,'CORNER_10_11_13_14',0.01,''),
(31,0,'CORNER_11_12_14_15',0.01,''),
(31,0,'CORNER_13_14_16_17',0.01,''),
(31,0,'CORNER_14_15_17_18',0.01,''),
(31,0,'DOZEN_1_12',0.01,''),
(31,0,'SIX_10_15',0.01,''),
(31,0,'SPLIT_29_30',1,''),
(31,0,'SPLIT_28_31',1,''),
(31,0,'SPLIT_27_30',11,''),
(31,0,'SPLIT_25_26',2,''),
(31,0,'STREET_7_8_9',3,''),
(32,0,'PICK_1',1,'17'),
(32,0,'PICK_1',1,'18'),
(32,0,'PICK_1',1,'19'),
(32,0,'PICK_1',1,'12'),
(32,0,'PICK_1',1,'21'),
(32,0,'PICK_1',1,'22'),
(32,0,'PICK_1',1,'23'),
(32,0,'PICK_1',1,'25'),
(32,0,'PICK_1',1,'27'),
(32,0,'OUTSIDE_EVEN',0.01,''),
(32,0,'COLUMN_1_34',1,''),
(32,0,'COLUMN_2_35',0.01,''),
(32,0,'COLUMN_3_36',0.01,''),
(32,0,'CORNER_0_1_2_3',0.01,''),
(32,0,'CORNER_10_11_13_14',0.01,''),
(32,0,'CORNER_11_12_14_15',0.01,''),
(32,0,'CORNER_13_14_16_17',0.01,''),
(32,0,'CORNER_14_15_17_18',0.01,''),
(32,0,'DOZEN_1_12',0.01,''),
(32,0,'SIX_10_15',0.01,''),
(32,0,'SPLIT_29_30',1,''),
(32,0,'SPLIT_28_31',1,''),
(32,0,'SPLIT_27_30',11,''),
(32,0,'SPLIT_25_26',2,''),
(32,0,'STREET_7_8_9',3,''),
(33,0,'PICK_1',1,'17'),
(33,0,'PICK_1',1,'18'),
(33,0,'PICK_1',1,'19'),
(33,0,'PICK_1',1,'12'),
(33,0,'PICK_1',1,'21'),
(33,0,'PICK_1',1,'22'),
(33,0,'PICK_1',1,'23'),
(33,0,'PICK_1',1,'25'),
(33,0,'PICK_1',1,'27'),
(33,0,'OUTSIDE_EVEN',0.01,''),
(33,0,'COLUMN_1_34',1,''),
(33,0,'COLUMN_2_35',0.01,''),
(33,0,'COLUMN_3_36',0.01,''),
(33,0,'CORNER_0_1_2_3',0.01,''),
(33,0,'CORNER_10_11_13_14',0.01,''),
(33,0,'CORNER_11_12_14_15',0.01,''),
(33,0,'CORNER_13_14_16_17',0.01,''),
(33,0,'CORNER_14_15_17_18',0.01,''),
(33,0,'DOZEN_1_12',0.01,''),
(33,0,'SIX_10_15',0.01,''),
(33,0,'SPLIT_29_30',1,''),
(33,0,'SPLIT_28_31',1,''),
(33,0,'SPLIT_27_30',11,''),
(33,0,'SPLIT_25_26',2,''),
(33,0,'STREET_7_8_9',3,''),
(34,0,'PICK_1',1,'17'),
(34,0,'PICK_1',1,'18'),
(34,0,'PICK_1',1,'19'),
(34,0,'PICK_1',1,'12'),
(34,0,'PICK_1',1,'21'),
(34,0,'PICK_1',1,'22'),
(34,0,'PICK_1',1,'23'),
(34,0,'PICK_1',1,'25'),
(34,0,'PICK_1',1,'27'),
(34,0,'OUTSIDE_EVEN',0.01,''),
(34,0,'COLUMN_1_34',1,''),
(34,0,'COLUMN_2_35',0.01,''),
(34,0,'COLUMN_3_36',0.01,''),
(34,0,'CORNER_0_1_2_3',0.01,''),
(34,0,'CORNER_10_11_13_14',0.01,''),
(34,0,'CORNER_11_12_14_15',0.01,''),
(34,0,'CORNER_13_14_16_17',0.01,''),
(34,0,'CORNER_14_15_17_18',0.01,''),
(34,0,'DOZEN_1_12',0.01,''),
(34,0,'SIX_10_15',0.01,''),
(34,0,'SPLIT_29_30',1,''),
(34,0,'SPLIT_28_31',1,''),
(34,0,'SPLIT_27_30',11,''),
(34,0,'SPLIT_25_26',2,''),
(34,0,'STREET_7_8_9',3,''),
(35,0,'PICK_1',1,'17'),
(35,0,'PICK_1',1,'18'),
(35,0,'PICK_1',1,'19'),
(35,0,'PICK_1',1,'12'),
(35,0,'PICK_1',1,'21'),
(35,0,'PICK_1',1,'22'),
(35,0,'PICK_1',1,'23'),
(35,0,'PICK_1',1,'25'),
(35,0,'PICK_1',1,'27'),
(35,0,'OUTSIDE_EVEN',0.01,''),
(35,0,'COLUMN_1_34',1,''),
(35,0,'COLUMN_2_35',0.01,''),
(35,0,'COLUMN_3_36',0.01,''),
(35,0,'CORNER_0_1_2_3',0.01,''),
(35,0,'CORNER_10_11_13_14',0.01,''),
(35,0,'CORNER_11_12_14_15',0.01,''),
(35,0,'CORNER_13_14_16_17',0.01,''),
(35,0,'CORNER_14_15_17_18',0.01,''),
(35,0,'DOZEN_1_12',0.01,''),
(35,0,'SIX_10_15',0.01,''),
(35,0,'SPLIT_29_30',1,''),
(35,0,'SPLIT_28_31',1,''),
(35,0,'SPLIT_27_30',11,''),
(35,0,'SPLIT_25_26',2,''),
(35,0,'STREET_7_8_9',3,''),
(36,0,'PICK_1',1,'17'),
(36,0,'PICK_1',1,'18'),
(36,0,'PICK_1',1,'19'),
(36,0,'PICK_1',1,'12'),
(36,0,'PICK_1',1,'21'),
(36,0,'PICK_1',1,'22'),
(36,0,'PICK_1',1,'23'),
(36,0,'PICK_1',1,'25'),
(36,0,'PICK_1',1,'27'),
(36,0,'OUTSIDE_EVEN',0.01,''),
(36,0,'COLUMN_1_34',1,''),
(36,0,'COLUMN_2_35',0.01,''),
(36,0,'COLUMN_3_36',0.01,''),
(36,0,'CORNER_0_1_2_3',0.01,''),
(36,0,'CORNER_10_11_13_14',0.01,''),
(36,0,'CORNER_11_12_14_15',0.01,''),
(36,0,'CORNER_13_14_16_17',0.01,''),
(36,0,'CORNER_14_15_17_18',0.01,''),
(36,0,'DOZEN_1_12',0.01,''),
(36,0,'SIX_10_15',0.01,''),
(36,0,'SPLIT_29_30',1,''),
(36,0,'SPLIT_28_31',1,''),
(36,0,'SPLIT_27_30',11,''),
(36,0,'SPLIT_25_26',2,''),
(36,0,'STREET_7_8_9',3,''),
(37,0,'PICK_1',0.01,'33'),
(38,0,'PICK_1',1,'17'),
(38,0,'PICK_1',1,'18'),
(38,0,'PICK_1',1,'19'),
(38,0,'PICK_1',1,'12'),
(38,0,'PICK_1',1,'21'),
(38,0,'PICK_1',1,'22'),
(38,0,'PICK_1',1,'23'),
(38,0,'PICK_1',1,'25'),
(38,0,'PICK_1',1,'27'),
(38,0,'OUTSIDE_EVEN',0.01,''),
(38,0,'COLUMN_1_34',1,''),
(38,0,'COLUMN_2_35',0.01,''),
(38,0,'COLUMN_3_36',0.01,''),
(38,0,'CORNER_0_1_2_3',0.01,''),
(38,0,'CORNER_10_11_13_14',0.01,''),
(38,0,'CORNER_11_12_14_15',0.01,''),
(38,0,'CORNER_13_14_16_17',0.01,''),
(38,0,'CORNER_14_15_17_18',0.01,''),
(38,0,'DOZEN_1_12',0.01,''),
(38,0,'SIX_10_15',0.01,''),
(38,0,'SPLIT_29_30',1,''),
(38,0,'SPLIT_28_31',1,''),
(38,0,'SPLIT_27_30',11,''),
(38,0,'SPLIT_25_26',2,''),
(38,0,'STREET_7_8_9',3,''),
(39,0,'PICK_1',1,'17'),
(39,0,'PICK_1',1,'18'),
(39,0,'PICK_1',1,'19'),
(39,0,'PICK_1',1,'12'),
(39,0,'PICK_1',1,'21'),
(39,0,'PICK_1',1,'22'),
(39,0,'PICK_1',1,'23'),
(39,0,'PICK_1',1,'25'),
(39,0,'PICK_1',1,'27'),
(39,0,'OUTSIDE_EVEN',0.01,''),
(39,0,'COLUMN_1_34',1,''),
(39,0,'COLUMN_2_35',0.01,''),
(39,0,'COLUMN_3_36',0.01,''),
(39,0,'CORNER_0_1_2_3',0.01,''),
(39,0,'CORNER_10_11_13_14',0.01,''),
(39,0,'CORNER_11_12_14_15',0.01,''),
(39,0,'CORNER_13_14_16_17',0.01,''),
(39,0,'CORNER_14_15_17_18',0.01,''),
(39,0,'DOZEN_1_12',0.01,''),
(39,0,'SIX_10_15',0.01,''),
(39,0,'SPLIT_29_30',1,''),
(39,0,'SPLIT_28_31',1,''),
(39,0,'SPLIT_27_30',11,''),
(39,0,'SPLIT_25_26',2,''),
(39,0,'STREET_7_8_9',3,''),
(40,0,'PICK_1',1,'17'),
(40,0,'PICK_1',1,'18'),
(40,0,'PICK_1',1,'19'),
(40,0,'PICK_1',1,'12'),
(40,0,'PICK_1',1,'21'),
(40,0,'PICK_1',1,'22'),
(40,0,'PICK_1',1,'23'),
(40,0,'PICK_1',1,'25'),
(40,0,'PICK_1',1,'27'),
(40,0,'OUTSIDE_EVEN',0.01,''),
(40,0,'COLUMN_1_34',1,''),
(40,0,'COLUMN_2_35',0.01,''),
(40,0,'COLUMN_3_36',0.01,''),
(40,0,'CORNER_0_1_2_3',0.01,''),
(40,0,'CORNER_10_11_13_14',0.01,''),
(40,0,'CORNER_11_12_14_15',0.01,''),
(40,0,'CORNER_13_14_16_17',0.01,''),
(40,0,'CORNER_14_15_17_18',0.01,''),
(40,0,'DOZEN_1_12',0.01,''),
(40,0,'SIX_10_15',0.01,''),
(40,0,'SPLIT_29_30',1,''),
(40,0,'SPLIT_28_31',1,''),
(40,0,'SPLIT_27_30',11,''),
(40,0,'SPLIT_25_26',2,''),
(40,0,'STREET_7_8_9',3,''),
(41,0,'PICK_1',1,'17'),
(41,0,'PICK_1',1,'18'),
(41,0,'PICK_1',1,'19'),
(41,0,'PICK_1',1,'12'),
(41,0,'PICK_1',1,'21'),
(41,0,'PICK_1',1,'22'),
(41,0,'PICK_1',1,'23'),
(41,0,'PICK_1',1,'25'),
(41,0,'PICK_1',1,'27'),
(41,0,'OUTSIDE_EVEN',0.01,''),
(41,0,'COLUMN_1_34',1,''),
(41,0,'COLUMN_2_35',0.01,''),
(41,0,'COLUMN_3_36',0.01,''),
(41,0,'CORNER_0_1_2_3',0.01,''),
(41,0,'CORNER_10_11_13_14',0.01,''),
(41,0,'CORNER_11_12_14_15',0.01,''),
(41,0,'CORNER_13_14_16_17',0.01,''),
(41,0,'CORNER_14_15_17_18',0.01,''),
(41,0,'DOZEN_1_12',0.01,''),
(41,0,'SIX_10_15',0.01,''),
(41,0,'SPLIT_29_30',1,''),
(41,0,'SPLIT_28_31',1,''),
(41,0,'SPLIT_27_30',11,''),
(41,0,'SPLIT_25_26',2,''),
(41,0,'STREET_7_8_9',3,''),
(42,0,'PICK_1',1,'17'),
(42,0,'PICK_1',1,'18'),
(42,0,'PICK_1',1,'19'),
(42,0,'PICK_1',1,'12'),
(42,0,'PICK_1',1,'21'),
(42,0,'PICK_1',1,'22'),
(42,0,'PICK_1',1,'23'),
(42,0,'PICK_1',1,'25'),
(42,0,'PICK_1',1,'27'),
(42,0,'OUTSIDE_EVEN',0.01,''),
(42,0,'COLUMN_1_34',1,''),
(42,0,'COLUMN_2_35',0.01,''),
(42,0,'COLUMN_3_36',0.01,''),
(42,0,'CORNER_0_1_2_3',0.01,''),
(42,0,'CORNER_10_11_13_14',0.01,''),
(42,0,'CORNER_11_12_14_15',0.01,''),
(42,0,'CORNER_13_14_16_17',0.01,''),
(42,0,'CORNER_14_15_17_18',0.01,''),
(42,0,'DOZEN_1_12',0.01,''),
(42,0,'SIX_10_15',0.01,''),
(42,0,'SPLIT_29_30',1,''),
(42,0,'SPLIT_28_31',1,''),
(42,0,'SPLIT_27_30',11,''),
(42,0,'SPLIT_25_26',2,''),
(42,0,'STREET_7_8_9',3,'');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
