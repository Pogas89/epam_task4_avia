
-- -----------------------------------------------------
-- Schema aviacompany
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `aviacompany`;
CREATE DATABASE IF NOT EXISTS `aviacompany` DEFAULT CHARACTER SET utf8 ;
USE `aviacompany` ;

-- -----------------------------------------------------
-- Table `aviacompany`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`staff` (
  `st_id` INT NOT NULL AUTO_INCREMENT,
  `st_Fname` VARCHAR(45) NOT NULL,
  `st_Lname` VARCHAR(45) NOT NULL,
  `department` TINYINT NOT NULL,
  PRIMARY KEY (`st_id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `us_login` VARCHAR(30) NOT NULL,
  `us_password` VARCHAR(30) NOT NULL,
  `us_Fname` VARCHAR(45) NOT NULL,
  `us_Lname` VARCHAR(45) NOT NULL,
  `us_email` VARCHAR(20) NOT NULL,
  `us_role` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`us_login` ASC))
  ENGINE = InnoDB;

insert into `aviacompany`.`user` (us_login, us_password, us_Fname, us_Lname, us_email, us_role) values('admin', 'admin', 'Dmitry', 'Ivanov','pogas89@gmail.com',0);
insert into `aviacompany`.`user` (us_login, us_password, us_Fname, us_Lname, us_email, us_role) values('user1', '1111', 'Ostap', 'Bender','bender@gmail.com',1);

-- -----------------------------------------------------
-- Table `aviacompany`.`crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`crew` (
  `cr_id` INT NOT NULL AUTO_INCREMENT,
  `cr_name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`cr_id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `aviacompany`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`flight` (
  `fl_id` INT NOT NULL AUTO_INCREMENT,
  `fl_name` VARCHAR(45) NOT NULL,
  `fl_departure` VARCHAR(45) NOT NULL,
  `fl_destination` VARCHAR(45) NOT NULL,
  `fl_date` DATE NOT NULL,
  `fl_time` TIME NOT NULL,
  `fl_statatus` TINYINT NOT NULL,
  `crew_id` INT NULL,
  PRIMARY KEY (`fl_id`),
  INDEX `crew_id_idx` (`crew_id` ASC),
  CONSTRAINT `crew_id`
  FOREIGN KEY (`crew_id`)
  REFERENCES `aviacompany`.`crew` (`cr_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`crew_staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`crew_staff` (
  `cr_id` INT NOT NULL,
  `st_id` INT NOT NULL,
  INDEX `cr_id_idx` (`cr_id` ASC),
  INDEX `st_id_idx` (`st_id` ASC),
  CONSTRAINT `cr_id`
  FOREIGN KEY (`cr_id`)
  REFERENCES `aviacompany`.`crew` (`cr_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `st_id`
  FOREIGN KEY (`st_id`)
  REFERENCES `aviacompany`.`staff` (`st_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Test insert into staff
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Alexis','Roy',0),('Kylan','Knapp',2),('Lacota','Rosario',2),('Rylee','Case',3),('Martha','Slater',2),('Vanna','Bruce',2),('Marsden','Meyers',1),('Bruno','Wooten',3),('Nyssa','Waters',3),('Forrest','Riddle',2);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Kasimir','Brennan',3),('Medge','Landry',2),('Gareth','Contreras',3),('Lunea','Mcclain',3),('Amanda','Austin',0),('Ignatius','Norris',1),('Gregory','Valdez',1),('Pamela','Hardy',0),('Xaviera','Gillespie',0),('Jasper','Valentine',2);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Mollie','Sosa',2),('Shoshana','Blair',3),('Yen','Wynn',1),('Judith','Holmes',1),('Devin','Hewitt',0),('Xerxes','Guthrie',0),('Jessica','Benjamin',2),('Hoyt','May',3),('Merritt','Barrett',1),('Idona','Rosales',1);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Sloane','Swanson',3),('Kiara','Wilkins',3),('Aileen','Mcmahon',1),('Lane','Kirby',1),('Rhiannon','Hutchinson',3),('Amy','Monroe',1),('Dawn','Tanner',2),('Signe','Padilla',0),('Eve','Gutierrez',3),('Colleen','Simpson',1);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Sylvia','Schmidt',0),('Yardley','Frost',3),('Kato','Hensley',2),('Tanner','Kirby',2),('Faith','Howard',3),('Raphael','Patel',3),('Joy','Dickerson',0),('Kiona','Strickland',3),('Palmer','Odonnell',2),('Mallory','Reid',2);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Wesley','Barr',0),('Odessa','Stuart',3),('Isabella','Mason',2),('Herman','Booth',2),('Quincy','Holland',2),('Isaiah','Petty',0),('Bernard','Lester',2),('Gabriel','Malone',3),('Kai','Russo',1),('Zoe','Mccray',2);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Uma','Carr',3),('Chaim','White',3),('Ryan','Richmond',2),('Isabella','Estes',3),('Merrill','Rowland',2),('Nash','Petty',2),('Philip','Chang',1),('Russell','Edwards',0),('Vernon','Prince',1),('Bo','Sanders',0);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Bernard','Boyer',1),('Madison','Matthews',1),('Noelle','Hoffman',2),('Ivy','Jennings',1),('Burton','Doyle',2),('Christen','Baker',2),('Alfonso','Newton',2),('Jared','Decker',2),('Calista','Norman',0),('Echo','Mayo',3);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Aimee','Sweeney',0),('Constance','Kane',2),('Lewis','Sparks',2),('Nora','Osborne',1),('Zachary','Gomez',1),('Prescott','Farley',0),('Nicholas','Mejia',1),('Blossom','Fitzgerald',0),('Fallon','Garner',2),('Edward','Myers',1);
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`department`) VALUES ('Gil','Sampson',1),('Linda','Head',3),('Joelle','Frazier',0),('Orli','Valdez',3),('Ainsley','Camacho',3),('Alec','Lara',0),('Tatum','Sweet',0),('Breanna','Finley',1),('Aline','Berry',2),('Jamalia','Obrien',1);

-- -----------------------------------------------------
-- Test insert into flight
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('79339','Myanmar','Dominica','16.01.18','18:12:45',2),('88170','Norfolk Island','Brazil','17.09.18','00:07:24',0),('57670','Mali','Christmas Island','25.01.18','11:40:42',3),('42514','Congo, the Democratic Republic of the','British Indian Ocean Territory','07.01.18','09:41:46',2),('40655','Zambia','Mauritania','21.07.18','09:48:05',3),('74467','United States Minor Outlying Islands','Nicaragua','22.10.17','15:44:59',1),('84799','El Salvador','Costa Rica','15.07.17','23:41:28',2),('39301','Marshall Islands','Tokelau','23.03.18','17:59:25',3),('82228','Lebanon','Romania','31.07.18','08:41:48',1),('71099','Cambodia','Wallis and Futuna','30.05.17','04:47:04',3);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('11590','Pakistan','Congo, the Democratic Republic of the','16.05.17','19:49:06',0),('25395','Montenegro','Algeria','02.10.17','19:25:15',3),('57395','Tokelau','Swaziland','31.07.17','05:02:28',0),('28655','Palestine, State of','Bulgaria','01.08.17','01:24:28',3),('05539','Sudan','Canada','08.07.17','08:37:45',3),('36014','Greenland','United Kingdom (Great Britain)','08.03.19','15:14:15',2),('06524','Cocos (Keeling) Islands','Swaziland','23.02.18','10:20:30',0),('46564','Guadeloupe','Bahrain','01.05.19','03:52:42',1),('56118','Ecuador','Denmark','23.05.18','07:09:05',1),('28387','Bulgaria','Nicaragua','12.06.17','20:28:53',1);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('47256','Tonga','Greenland','06.03.18','02:05:42',0),('75135','Grenada','Virgin Islands, British','05.04.18','17:35:33',2),('94756','Armenia','Kiribati','03.02.19','13:31:32',2),('86122','Ivoire (Ivory Coast)','France','19.07.18','16:29:59',3),('62567','Denmark','Tokelau','02.08.17','17:33:27',0),('67601','Martinique','Kazakhstan','29.05.17','13:52:50',1),('51473','Angola','El Salvador','19.05.17','08:50:38',2),('35023','Holy See (Vatican City State)','Georgia','19.06.18','00:32:18',2),('94601','Sweden','Slovakia','16.04.18','18:08:45',2),('51846','Uganda','Cameroon','14.03.19','14:34:01',3);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('76134','Guernsey','Bahamas','31.10.17','10:54:36',0),('27250','Ivoire (Ivory Coast)','Hong Kong','11.02.18','03:33:34',3),('57016','Somalia','Nepal','26.01.19','03:56:43',2),('43560','Guam','Kazakhstan','03.12.18','09:01:22',2),('47889','Cayman Islands','Curaçao','07.05.18','18:24:44',3),('82238','Moldova','New Zealand','22.08.17','00:22:02',1),('89278','Aruba','Bahrain','03.04.18','05:06:53',0),('57165','New Caledonia','Bangladesh','01.07.18','19:34:10',0),('20809','Seychelles','Sweden','20.10.18','22:59:48',0),('83879','Sint Maarten','Albania','28.11.18','10:22:17',2);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('30599','Romania','Martinique','09.05.19','23:47:00',3),('76064','Guam','Sint Maarten','25.08.17','07:50:10',0),('50807','Cook Islands','Guam','25.10.18','08:39:39',0),('81539','Bolivia','Macedonia','08.05.19','00:57:32',3),('75424','Maldives','Ireland','12.05.19','04:56:11',3),('11500','Falkland Islands','Gibraltar','06.06.18','12:13:22',0),('99421','Slovakia','Svalbard and Jan Mayen Islands','01.09.17','21:59:20',3),('94211','Italy','Fiji','28.03.18','20:27:16',1),('75847','Portugal','Korea, South','10.09.17','17:52:23',2),('89466','Madagascar','Haiti','24.01.19','03:57:55',0);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('09241','Zambia','Denmark','03.11.18','08:25:02',3),('12269','Wallis and Futuna','Tonga','07.08.17','03:45:37',0),('78318','Norway','Algeria','19.03.19','15:21:19',2),('55874','Western Sahara','Aruba','15.11.18','11:09:19',1),('41539','Finland','Mauritania','13.03.18','07:50:17',1),('67444','Northern Mariana Islands','Belize','02.12.18','12:40:15',1),('90985','Aruba','Canada','19.03.18','01:28:23',2),('80029','Bangladesh','Australia','15.01.19','18:35:48',3),('51582','Iraq','New Zealand','13.01.18','04:34:48',0),('35083','Guyana','Mayotte','02.04.19','16:13:29',0);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('73018','Guatemala','Saint Barthélemy','27.08.18','02:10:17',3),('91633','Sudan','France','05.06.18','13:29:19',2),('44692','Korea, North','Tokelau','30.09.17','21:42:03',3),('15819','Comoros','Niue','25.02.19','06:05:04',1),('40206','Somalia','Congo, the Democratic Republic of the','19.05.18','20:18:57',1),('88353','Uganda','Lesotho','14.06.17','15:52:26',3),('10071','Indonesia','Faroe Islands','22.10.17','18:58:19',1),('79997','Congo, the Democratic Republic of the','Andorra','21.12.17','00:00:34',3),('59008','Turkey','Cape Verde','05.03.18','09:55:21',2),('67238','Paraguay','Saint Helena, Ascension and Tristan da Cunha','28.09.18','20:46:03',3);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('51901','Holy See (Vatican City State)','Iran','30.03.19','16:25:05',0),('85862','British Indian Ocean Territory','Pakistan','08.08.17','03:21:34',2),('88802','Indonesia','Tunisia','16.02.18','01:22:21',0),('90226','Mongolia','Sudan','11.09.17','14:32:09',2),('82798','Ghana','Gibraltar','07.10.17','12:54:24',1),('97171','Saint Martin','Swaziland','25.09.17','08:41:13',0),('12581','United States','Italy','17.12.18','00:35:31',1),('33059','Moldova','Madagascar','25.06.18','06:50:43',2),('97366','Thailand','Tajikistan','22.11.18','05:22:43',1),('44414','Armenia','Guadeloupe','22.08.18','12:02:06',1);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('47241','Moldova','Sri Lanka','15.07.17','14:40:58',1),('24914','Guam','Portugal','14.02.18','17:40:20',0),('92263','Bangladesh','Ivoire (Ivory Coast)','23.11.17','04:43:11',2),('64290','Fiji','Ivoire (Ivory Coast)','25.06.17','00:55:06',0),('51285','Guinea','Cocos (Keeling) Islands','09.06.18','04:50:40',3),('09759','Egypt','New Zealand','18.12.18','23:39:15',3),('90483','Aruba','Guam','31.05.17','17:42:19',0),('16234','Czech Republic','Venezuela','05.08.17','15:32:19',1),('42833','Saint Vincent and The Grenadines','Liechtenstein','23.04.19','23:48:04',3),('99669','Thailand','Zimbabwe','17.06.18','10:33:09',3);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('65522','Cook Islands','Cameroon','08.02.19','03:26:30',3),('00375','Solomon Islands','Estonia','13.06.17','07:39:01',2),('51582','Holy See (Vatican City State)','Cambodia','05.01.19','16:20:24',2),('16483','Reunion','France','23.07.18','11:41:17',1),('13386','Korea, North','Swaziland','06.06.17','22:59:26',2),('74009','Egypt','Falkland Islands','05.06.18','23:04:58',0),('98204','Chad','Paraguay','20.07.17','05:51:36',3),('66441','Poland','Uzbekistan','13.01.19','07:10:24',3),('54664','Central African Republic','Fiji','22.10.18','02:54:00',3),('08647','Mongolia','Ivoire (Ivory Coast)','10.05.19','00:22:11',3);

