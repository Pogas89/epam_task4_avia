
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
insert into `aviacompany`.`user` (us_login, us_password, us_Fname, us_Lname, us_email, us_role) values('user1', 'user1', 'Ostap', 'Bender','bender@gmail.com',1);

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
  `crew_id` INT NOT NULL,
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


