
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
  `isArchive` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`st_id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `us_login` VARCHAR(30) NOT NULL,
  `us_password` VARCHAR(100) NOT NULL,
  `us_Fname` VARCHAR(45) NOT NULL,
  `us_Lname` VARCHAR(45) NOT NULL,
  `us_email` VARCHAR(20) NOT NULL,
  `us_role` TINYINT NOT NULL,
  `isArchive` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`us_login` ASC))
  ENGINE = InnoDB;

insert into `aviacompany`.`user` (us_login, us_password, us_Fname, us_Lname, us_email, us_role) values
  ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Dmitry', 'Ivanov','poshta@gmail.com',0);
insert into `aviacompany`.`user` (us_login, us_password, us_Fname, us_Lname, us_email, us_role) values
  ('user1', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 'Ostap', 'Bender','bender@gmail.com',1);

-- -----------------------------------------------------
-- Table `aviacompany`.`crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`crew` (
  `cr_id` INT NOT NULL AUTO_INCREMENT,
  `cr_name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  `isArchive` TINYINT(1) NOT NULL DEFAULT 0,
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
  `isArchive` TINYINT(1) NOT NULL DEFAULT 0,
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
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('79339','Myanmar','Dominica','16.01.18','18:12:45',0),('88170','Norfolk Island','Brazil','17.09.18','00:07:24',0),('57670','Mali','Christmas Island','25.01.18','11:40:42',0),('42514','Congo','British Indian Ocean Territory','07.01.18','09:41:46',0),('40655','Zambia','Mauritania','21.07.18','09:48:05',0),('74467','Greenland','Nicaragua','22.10.17','15:44:59',0),('84799','El Salvador','Costa Rica','15.07.17','23:41:28',0),('39301','Marshall Islands','Tokelau','23.03.18','17:59:25',0),('82228','Lebanon','Romania','31.07.18','08:41:48',0),('71099','Cambodia','Wallis and Futuna','30.05.17','04:47:04',0);
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_statatus`) VALUES ('11590','Pakistan','Congo, the Democratic Republic of the','16.05.17','19:49:06',0),('25395','Montenegro','Algeria','02.10.17','19:25:15',0),('57395','Tokelau','Swaziland','31.07.17','05:02:28',0),('28655','Palestine','Bulgaria','01.08.17','01:24:28',0),('05539','Sudan','Canada','08.07.17','08:37:45',0),('36014','Greenland','United Kingdom (Great Britain)','08.03.19','15:14:15',0),('06524','Cocos Islands','Swaziland','23.02.18','10:20:30',0),('46564','Guadeloupe','Bahrain','01.05.19','03:52:42',0),('56118','Ecuador','Denmark','23.05.18','07:09:05',0),('28387','Bulgaria','Nicaragua','12.06.17','20:28:53',0);


