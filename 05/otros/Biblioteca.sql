CREATE DATABASE Biblioteca;

USE Biblioteca;

CREATE TABLE Usuarios (
  Nombre    VARCHAR(20) NOT NULL,
  Apellidos VARCHAR(40) NOT NULL,
  DNI       VARCHAR(9)  NOT NULL PRIMARY KEY
);

CREATE TABLE Libros (
  id     INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Titulo VARCHAR(20)     NOT NULL
);

CREATE TABLE Autores (
  id        INT(9) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Nombre    VARCHAR(20)     NOT NULL,
  Apellidos VARCHAR(40)     NOT NULL
);


CREATE TABLE Prestamos (
  DNI_usuario VARCHAR(9) NOT NULL,
  id_libro    INT(9) UNSIGNED NOT NULL,
  CONSTRAINT fk_libros FOREIGN KEY (id_libro) REFERENCES Libros (id) ON DELETE CASCADE,
  CONSTRAINT fk_usuario FOREIGN KEY (DNI_usuario) REFERENCES Usuarios (DNI) ON DELETE CASCADE
);

CREATE TABLE Libros_Autores
(
  libro_id INT(9) UNSIGNED NOT NULL,
  autor_id INT(9) UNSIGNED NOT NULL,
  PRIMARY KEY(libro_id, autor_id),
  CONSTRAINT Autores_Libros_Libros_id_fk FOREIGN KEY (libro_id) REFERENCES Libros (id) ON DELETE CASCADE,
  CONSTRAINT Autores_Libros_Autores_id_fk FOREIGN KEY (autor_id) REFERENCES Autores (id) ON DELETE CASCADE
);

#INSERTAR DATOS
INSERT INTO Autores VALUES (NULL,'Miguel', 'de Cervantes Saavedra');
INSERT INTO Libros VALUES (NULL,'Don Quijote de la Mancha');
INSERT INTO Libros_Autores VALUES (1,1);
INSERT INTO Usuarios VALUES ('Alberto', 'Navarro Gordillo', '77894561H');
INSERT INTO Prestamos VALUES ('77894561H', 1);

#CREAR USUARIO
CREATE USER 'prueba'@'localhost' IDENTIFIED BY '123';
GRANT ALL ON Biblioteca.* TO 'prueba'@'localhost';



