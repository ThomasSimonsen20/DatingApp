DROP DATABASE IF EXISTS `hello`;
CREATE DATABASE `hello`;
USE `hello`;


CREATE TABLE Users (
  `idUsers` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `SurName` varchar(150) NOT NULL,
  `TelephoneNumber` int NOT NULL,
  `ZipCode` int NOT NULL,
  `City` varchar(45) NOT NULL,
  `Street` varchar(45) NOT NULL,
  `HouseNumber` smallint NOT NULL,
  `HouseFloor` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idUsers`),
  UNIQUE KEY `idUsers_UNIQUE` (`idUsers`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;