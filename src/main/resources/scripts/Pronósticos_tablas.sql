
DROP DATABASE IF EXISTS `pronosticos_deportivos`;
CREATE DATABASE `pronosticos_deportivos`;
USE `pronosticos_deportivos`;

DROP TABLE IF EXISTS `pronosticos_deportivos`.`fases`;
CREATE TABLE `fases` (
  `id_fases` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_fases`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `pronosticos_deportivos`.`fases`
(`nombre`)
VALUES
("Fase De Grupos"),
("Fase De Eliminación");

DROP TABLE IF EXISTS `pronosticos_deportivos`.`rondas`;
CREATE TABLE `rondas` (
  `id_rondas` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fases_id` int NOT NULL,
  PRIMARY KEY (`id_rondas`,`fases_id`),
  KEY `fases_rondas_idx` (`fases_id`),
  CONSTRAINT `rondas_fases` FOREIGN KEY (`fases_id`) REFERENCES `fases` (`id_fases`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `pronosticos_deportivos`.`rondas`
(`nombre`,
`fases_id`)
VALUES
("Fase de Grupos",1),
("Fase de Grupos",2),
("Fase de Grupos",3),
("Octavos De Final",4),
("Cuartos De Final",5),
("Semi-Final",6),
("Final",7);

DROP TABLE IF EXISTS `pronosticos_deportivos`.`personas`;
CREATE TABLE `personas` (
  `id_personas` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_personas`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `pronosticos_deportivos`.`personas`
(`nombre`)
VALUES
("Diego"),
("Julieta"),
("Mauro");


DROP TABLE IF EXISTS `pronosticos_deportivos`.`partidos`;
CREATE TABLE `pronosticos_deportivos`.`partidos` (
  `id_partidos` INT NOT NULL AUTO_INCREMENT,
  `equipo1` VARCHAR(45) NOT NULL,
  `golesEquipo1` INT NOT NULL,
  `golesEquipo2` INT NOT NULL,
  `equipo2` VARCHAR(45) NOT NULL,
  `rondas_id` INT NOT NULL,
  PRIMARY KEY (`id_partidos`, `rondas_id`),
  CONSTRAINT `rondasid`
    FOREIGN KEY (`rondas_id`)
    REFERENCES `pronosticos_deportivos`.`rondas` (`id_rondas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

INSERT INTO `pronosticos_deportivos`.`partidos`
(`equipo1`,
`golesEquipo1`,
`golesEquipo2`,
`equipo2`,
`rondas_id`)
VALUES
("Argentina",1,2,"Arabia Saudita",1),
("México",0,0,"Polonia",1),
("Arabia Saudita",0,2,"Polonia",2),
("México",0,2,"Argentina",2),
("Argentina",2,0,"Polonia",3),
("Arabia Saudita",1,2,"México",3),
("Países Bajos",3,1,"Estados Unidos",4),
("Argentina",2,1,"Australia",4),
("Francia",3,1,"Polonia",4),
("Inglaterra",3,1,"Senegal",4),
("Japón",1,3,"Croacia",4),
("Brasil",4,1,"Corea del Sur",4),
("Marruecos",3,0,"España",4),
("Portugal",6,1,"Suiza",4),
("Croacia",4,2,"Brasil",5),
("Países Bajos",3,4,"Argentina",5),
("Marruecos",1,0,"Portugal",5),
("Inglaterra",1,2,"Francia",5),
("Argentina",3,0,"Croacia",6),
("Marruecos",0,2,"Francia",6),
("Argentina",4,2,"Francia",7);

DROP TABLE IF EXISTS `pronosticos_deportivos`.`pronosticos`;
CREATE TABLE `pronosticos_deportivos`.`pronosticos` (
  `id_pronosticos` int NOT NULL AUTO_INCREMENT,
  `persona` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `equipo1` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gana1` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `empate` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gana2` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `equipo2` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `persona_id` INT NOT NULL,
  `ronda_id` int NOT NULL,
  PRIMARY KEY (`id_pronosticos`, `persona_id`,`ronda_id`),
  CONSTRAINT `personas_pronosticos`
  FOREIGN KEY (`persona_id`)
  REFERENCES `pronosticos_deportivos`.`personas` (`id_personas`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `pronosticos_deportivos`.`pronosticos`
ADD CONSTRAINT `rondas_pronosticos`
  FOREIGN KEY (`ronda_id`)
  REFERENCES `pronosticos_deportivos`.`rondas` (`id_rondas`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO `pronosticos_deportivos`.`pronosticos`
(`persona`,`equipo1`,`gana1`,`empate`,`gana2`,`equipo2`,`persona_id`,`id_rondas`)
VALUES
-- Ronda 1 Grupos
("Diego","Argentina","x","","","Arabia Saudita",1,1),
("Diego","México","","x","","Polonia",1,1),
("Julieta","Argentina","x","","","Arabia Saudita",2,1),
("Julieta","México","","","x","Polonia",2,1),
("Mauro","Argentina","","x","","Arabia Saudita",3,1),
("Mauro","México","x","","","Polonia",3,1),
-- Ronda 2 Grupos
("Diego","Arabia Saudita","","","x","Polonia",1,2),
("Diego","México","","","x","Argentina",1,2),
("Julieta","Arabia Saudita","","x","","Polonia",2,2),
("Julieta","México","","","x","Argentina",2,2),
("Mauro","Arabia Saudita","","","x","Polonia",3,2),
("Mauro","México","","x","","Argentina",3,2),
-- Ronda 3 Grupos
("Diego","Argentina","x","","","Polonia",1,3),
("Diego","Arabia Saudita","","","x","México",1,3),
("Julieta","Argentina","x","","","Polonia",1,3),
("Julieta","Arabia Saudita","","x","","México",1,3),
("Mauro","Argentina","x","","","Polonia",1,3),
("Mauro","Arabia Saudita","","x","","México",1,3),
-- Ronda 4 Fase Eliminación Octavos
("Diego","Países Bajos","x","","","Estados Unidos",1,4),
("Diego","Argentina","x","","","Australia",1,4),
("Diego","Francia","x","","","Polonia",1,4),
("Diego","Inglaterra","x","","","Senegal",1,4),
("Diego","Japón","x","","","Croacia",1,4),
("Diego","Brasil","x","","","Corea del Sur",1,4),
("Diego","Marruecos","","","x","España",1,4),
("Diego","Portugal","x","","","Suiza",1,4),
("Julieta","Países Bajos","","","x","Estados Unidos",2,4),
("Julieta","Argentina","x","","","Australia",2,4),
("Julieta","Francia","x","","","Polonia",2,4),
("Julieta","Inglaterra","x","","","Senegal",2,4),
("Julieta","Japón","","","x","Croacia",2,4),
("Julieta","Brasil","x","","","Corea del Sur",2,4),
("Julieta","Marruecos","","","x","España",2,4),
("Julieta","Portugal","","","x","Suiza",2,4),
("Mauro","Países Bajos","x","","","Estados Unidos",3,4),
("Mauro","Argentina","x","","","Australia",3,4),
("Mauro","Francia","x","","","Polonia",3,4),
("Mauro","Inglaterra","x","","","Senegal",3,4),
("Mauro","Japón","","","x","Croacia",3,4),
("Mauro","Brasil","x","","","Corea del Sur",3,4),
("Mauro","Marruecos","x","","","España",3,4),
("Mauro","Portugal","x","","","Suiza",3,4),
-- Ronda 5 Fase Eliminación Cuartos
("Diego","Croacia","","","x","Brasil",1,5),
("Diego","Países Bajos","","","x","Argentina",1,5),
("Diego","Marruecos","","","x","Portugal",1,5),
("Diego","Inglaterra","x","","","Francia",1,5),
("Julieta","Croacia","x","","","Brasil",2,5),
("Julieta","Países Bajos","","","x","Argentina",2,5),
("Julieta","Marruecos","x","","","Portugal",2,5),
("Julieta","Inglaterra","","","x","Francia",2,5),
("Mauro","Croacia","","","x","Brasil",3,5),
("Mauro","Países Bajos","","","x","Argentina",3,5),
("Mauro","Marruecos","","","x","Portugal",3,5),
("Mauro","Inglaterra","x","","","Francia",3,5),
-- Ronda 6 Fase Eliminación Semifinales
("Diego","Argentina","x","","","Croacia",1,6),
("Diego","Marruecos","","","x","Francia",1,6),
("Julieta","Argentina","x","","","Croacia",2,6),
("Julieta","Marruecos","x","","","Francia",2,6),
("Mauro","Argentina","","","x","Croacia",3,6),
("Mauro","Marruecos","x","","","Francia",3,6),
-- Ronda 7 Fase Eliminación Final
("Diego","Argentina","x","","","Francia",1,7),
("Julieta","Argentina","x","","","Francia",2,7),
("Mauro","Argentina","x","","","Francia",3,7);