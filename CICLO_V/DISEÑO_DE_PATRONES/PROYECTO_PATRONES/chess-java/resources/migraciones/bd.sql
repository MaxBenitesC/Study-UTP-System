CREATE DATABASE IF NOT EXISTS chess_game;
USE chess_game;

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fechaRegistro DATETIME DEFAULT NOW(),
    partidasGanadas INT DEFAULT 0,
    partidasPerdidas INT DEFAULT 0,
    partidasEmpatadas INT DEFAULT 0,
    esAdmin BOOLEAN DEFAULT FALSE
);

INSERT INTO usuario (nombreUsuario, nombre, apellido, password, esAdmin) 
SELECT 'gfarfan', 'Gustavo', 'Farfan', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', TRUE
WHERE NOT EXISTS (SELECT * FROM usuario WHERE nombreUsuario = 'gfarfan');
