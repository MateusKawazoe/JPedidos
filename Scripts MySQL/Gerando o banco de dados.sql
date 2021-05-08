-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`login` (
  `login_id` INT NOT NULL AUTO_INCREMENT,
  `login_username` VARCHAR(45) NOT NULL,
  `login_password` VARCHAR(45) NOT NULL,
  `login_token` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE INDEX `login_username_UNIQUE` (`login_username` ASC) VISIBLE,
  UNIQUE INDEX `login_token_UNIQUE` (`login_token` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_type` VARCHAR(45) NOT NULL,
  `user_phone` INT NOT NULL,
  `login_login_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_ud_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE,
  INDEX `fk_user_login_idx` (`login_login_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_login`
    FOREIGN KEY (`login_login_id`)
    REFERENCES `mydb`.`login` (`login_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`client_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client_order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `order_value` FLOAT NULL DEFAULT NULL,
  `order_status` INT NOT NULL,
  `order_open` DATETIME NOT NULL,
  `order_close` DATETIME NULL DEFAULT NULL,
  `user_user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC) VISIBLE,
  INDEX `fk_order_user1_idx` (`user_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `mydb`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(20) NOT NULL,
  `product_description` VARCHAR(100) NOT NULL,
  `product_price` FLOAT NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `product_name_UNIQUE` (`product_name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`order_item` (
  `order_item_qtd` INT NOT NULL,
  `product_product_id` INT NOT NULL,
  `client_order_order_id` INT NOT NULL,
  INDEX `fk_order_item_product1_idx` (`product_product_id` ASC) VISIBLE,
  INDEX `fk_order_item_client_order1_idx` (`client_order_order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_item_client_order1`
    FOREIGN KEY (`client_order_order_id`)
    REFERENCES `mydb`.`client_order` (`order_id`),
  CONSTRAINT `fk_order_item_product1`
    FOREIGN KEY (`product_product_id`)
    REFERENCES `mydb`.`product` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
