-- Script de referencia: refleja las tablas reales de la base de datos db_paqueteria.
CREATE DATABASE IF NOT EXISTS db_paqueteria;
USE db_paqueteria;

CREATE TABLE IF NOT EXISTS ciudad (
    idCiudad INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS camionero (
    idCamionero INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Telefono CHAR(8) NOT NULL,
    Direccion VARCHAR(100) NOT NULL,
    Salario DECIMAL(10,2) NOT NULL,
    Poblacion VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS camion (
    idCamion INT AUTO_INCREMENT PRIMARY KEY,
    NoPlaca VARCHAR(10) NOT NULL UNIQUE,
    Modelo VARCHAR(50) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    Potencia DECIMAL(8,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS camioneroCamion (
    idCamioneroCamion INT AUTO_INCREMENT PRIMARY KEY,
    idCamionero INT NOT NULL,
    idCamion INT NOT NULL,
    FOREIGN KEY (idCamionero) REFERENCES camionero(idCamionero),
    FOREIGN KEY (idCamion) REFERENCES camion(idCamion)
);

CREATE TABLE IF NOT EXISTS paquete (
    idPaquete INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(100) NOT NULL,
    Destinatario VARCHAR(100) NOT NULL,
    DireccionEnvio VARCHAR(100) NOT NULL,
    idCamionero INT NOT NULL,
    idCiudad INT NOT NULL,
    FOREIGN KEY (idCamionero) REFERENCES camionero(idCamionero),
    FOREIGN KEY (idCiudad) REFERENCES ciudad(idCiudad)
);
