-- MySQL Script generated by MySQL Workbench
-- Sat Feb  6 14:40:44 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema trabajo1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `trabajo1`;
USE `trabajo1` ;

-- -----------------------------------------------------
-- Table `trabajo1`.`insumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trabajo1`.`insumo` (
  `ID_INSUMO` INT(11) NOT NULL AUTO_INCREMENT,
  `CANTIDAD` DOUBLE NULL DEFAULT NULL,
  `DESCRIPCION` VARCHAR(255) NULL DEFAULT NULL,
  `ESTADO` BIT(1) NULL DEFAULT NULL,
  `IMAGEN` LONGBLOB NULL DEFAULT NULL,
  `NOMBRE` VARCHAR(255) NULL DEFAULT NULL,
  `PRECIO_UNITARIO` DOUBLE NULL DEFAULT '0',
  `TIPO` VARCHAR(50) NULL DEFAULT 'Tipo Cantidad',
  PRIMARY KEY (`ID_INSUMO`))
ENGINE = InnoDB
AUTO_INCREMENT = 111;


-- -----------------------------------------------------
-- Table `trabajo1`.`acciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trabajo1`.`acciones` (
  `id_accion` INT(30) NOT NULL AUTO_INCREMENT,
  `cantidad_accion` DOUBLE(10,2) NULL DEFAULT NULL,
  `producto_accion` VARCHAR(100) NULL DEFAULT NULL,
  `descripcion_accion` VARCHAR(50) NULL DEFAULT NULL,
  `fecha_accion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `usuario_accion` VARCHAR(100) NULL DEFAULT NULL,
  `ultima_cantidad` DOUBLE(10,2) NULL DEFAULT '0.00',
  `fk_id_insumo` INT(11) NULL DEFAULT NULL,
  `ultimo_precio` DOUBLE(10,2) NULL DEFAULT '0.00',
  `factura_accion` VARCHAR(300) NULL DEFAULT 'Sin Documento',
  PRIMARY KEY (`id_accion`),
  FOREIGN KEY (`fk_id_insumo`)
    REFERENCES `trabajo1`.`insumo` (`ID_INSUMO`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1291;


-- -----------------------------------------------------
-- Table `trabajo1`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trabajo1`.`proveedor` (
  `id_proveedor` INT(5) NOT NULL AUTO_INCREMENT,
  `cedula_prov` VARCHAR(30) NOT NULL,
  `nombres_prov` VARCHAR(100) NOT NULL,
  `apellidos_prov` VARCHAR(100) NOT NULL,
  `empresa_prov` VARCHAR(100) NULL DEFAULT 'Sin Empresa',
  `email_prov` VARCHAR(100) NULL DEFAULT 'Sin Email',
  `direccion_prov` VARCHAR(300) NULL DEFAULT 'Sin direccion',
  `telefono_prov` VARCHAR(30) NULL DEFAULT 'Sin telefono',
  `estado_prov` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`))
ENGINE = InnoDB
AUTO_INCREMENT = 21;


-- -----------------------------------------------------
-- Table `trabajo1`.`documentacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trabajo1`.`documentacion` (
  `id_doc` INT(5) NOT NULL AUTO_INCREMENT,
  `nombre_doc` VARCHAR(300) NULL DEFAULT NULL,
  `fecha_doc` VARCHAR(300) NULL DEFAULT NULL,
  `documento` LONGBLOB NULL DEFAULT NULL,
  `fk_proveedor` INT(5) NULL DEFAULT NULL,
  `estado_doc` BIT(1) NULL DEFAULT b'1',
  PRIMARY KEY (`id_doc`),
    FOREIGN KEY (`fk_proveedor`)
    REFERENCES `trabajo1`.`proveedor` (`id_proveedor`))
ENGINE = InnoDB
AUTO_INCREMENT = 63;


-- -----------------------------------------------------
-- Table `trabajo1`.`notificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trabajo1`.`notificacion` (
  `ID_NOTIFICACION` INT(11) NOT NULL AUTO_INCREMENT,
  `CANTIDAD` DOUBLE NULL DEFAULT '0',
  `ESTADO` BIT(1) NULL DEFAULT b'1',
  `F_CREACION` VARCHAR(255) NULL DEFAULT NULL,
  `FECHA` VARCHAR(255) NULL DEFAULT NULL,
  `FK_INSUMO` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_NOTIFICACION`),
    FOREIGN KEY (`FK_INSUMO`)
    REFERENCES `trabajo1`.`insumo` (`ID_INSUMO`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 85;


-- -----------------------------------------------------
-- Table `trabajo1`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trabajo1`.`usuario` (
  `nombre_usuario` VARCHAR(100) NOT NULL,
  `apellido_usuario` VARCHAR(45) NOT NULL,
  `password_usuario` VARCHAR(100) NOT NULL,
  `correo_usuario` VARCHAR(45) NOT NULL,
  `tipo_usuario` VARCHAR(45) NOT NULL,
  `estado_usuario` BIT(1) NULL DEFAULT b'1',
  PRIMARY KEY (`nombre_usuario`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Crear el Usuario 'Prolase'
-- -----------------------------------------------------
CREATE USER prolase IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON trabajo1.* TO 'prolase' WITH GRANT OPTION;

-- -----------------------------------------------------
-- Agregar un Usuario para acceder al Sistema
-- -----------------------------------------------------

insert into usuario(nombre_usuario, apellido_usuario, password_usuario, correo_usuario, tipo_usuario, estado_usuario) values
('Administrador', 'Usuario', '123', 'usuario@gmail.com','Administrador', 1);
