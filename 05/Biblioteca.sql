create database Biblioteca;

USE Biblioteca;

CREATE TABLE Usuarios(
                     Nombre varchar(20) NOT NULL,
                     Apellidos varchar(40) NOT NULL,
                     DNI varchar(9) NOT NULL PRIMARY KEY);
                     
CREATE TABLE Libros(id int(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    Titulo varchar(20) NOT NULL);

CREATE TABLE Autores(id int(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      Nombre varchar(20) NOT NULL,
                      Apellidos varchar(40) NOT NULL
                    );
                     
                     
CREATE TABLE Prestamos(DNI_usuario varchar(9) NOT NULL,
                      id_libro int(9) NOT NULL,
                      CONSTRAINT fk_libros FOREIGN KEY (id_libro) REFERENCES Libros(id));
