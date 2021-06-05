CREATE DATABASE  IF NOT EXISTS `repair` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 */;
USE `repair`;
-- MySQL Workbench Forward Engineering

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema repair
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `repair`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `repair`.`user`;

CREATE TABLE IF NOT EXISTS `repair`.`user`
(
    `id`       INT            NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(20)    NULL DEFAULT NULL,
    `password` VARCHAR(20)    NULL DEFAULT NULL,
    `role`     VARCHAR(25)    NULL DEFAULT 'USER',
    `wallet`   DECIMAL(20, 2) NULL DEFAULT '0.00',
    `email`    VARCHAR(50)    NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `login` (`login` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 39
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (id, login, password, role, wallet, email)
VALUES
(1, 'user', '1', 'USER', 100.00, 'user@gmail.com'),
(2, 'master', '1', 'USER', 100.00, 'master@gmail.com'),
(3, 'admin', '1', 'USER', 100.00, 'admin@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `repair`.`invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `repair`.`invoice`;

CREATE TABLE IF NOT EXISTS `repair`.`invoice`
(
    `invoice_id`  INT            NOT NULL AUTO_INCREMENT,
    `brand`       VARCHAR(120)   NOT NULL,
    `model`       VARCHAR(220)   NOT NULL,
    `description` VARCHAR(120)   NOT NULL,
    `price`       DECIMAL(20, 2) NULL DEFAULT '0.00',
    `feedback`    VARCHAR(500)   NULL DEFAULT NULL,
    `user_id`     INT            NOT NULL,
    `engineer_id` INT            NULL DEFAULT '25',
    `status`      VARCHAR(50)    NULL DEFAULT 'Payment expected',
    `date`        DATETIME       NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`invoice_id`),
    INDEX `fk_requests_invoice_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_invoice_user1_idx` (`engineer_id` ASC) VISIBLE,
    CONSTRAINT `fk_invoice_user1`
        FOREIGN KEY (`engineer_id`)
            REFERENCES `repair`.`user` (`id`),
    CONSTRAINT `fk_requests_invoice`
        FOREIGN KEY (`user_id`)
            REFERENCES `repair`.`user` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 18
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice`(invoice_id, brand, model, description, price, feedback, user_id, engineer_id, status, date)
VALUES
(1, 'apple', 'iphone 6', 'low battery', 10.00, 'nice', 1, 2, 'Done', CURRENT_TIMESTAMP),
(2, 'xiaomi', 'note 8', 'low battery', 5.00, 'good', 1, 2, 'Done', CURRENT_TIMESTAMP),
(3, 'samsung', 'galaxy 10', 'low battery', 8.00, 'bad', 1, 2, 'Done', CURRENT_TIMESTAMP);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
