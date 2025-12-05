CREATE DATABASE IF NOT EXISTS basket_db;
USE basket_db;
----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
    );

----------------------------------------------------
CREATE TABLE IF NOT EXISTS equipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100),
    conferencia ENUM('ESTE', 'OESTE') DEFAULT 'ESTE'
    );
----------------------------------------------------
CREATE TABLE IF NOT EXISTS jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100),
    fecha_nacimiento DATE,
    altura_cm INT,
    peso_kg DECIMAL(5,2),
    posicion ENUM('BASE', 'ESCOLTA', 'ALERO', 'ALA-PIVOT', 'PIVOT'),
    lesionado BOOLEAN DEFAULT FALSE,
    salario_bruto DECIMAL(15, 2),
    equipo_id INT,
    FOREIGN KEY (equipo_id) REFERENCES equipos(id) ON DELETE SET NULL
    );

----------------------------------------------------
CREATE TABLE IF NOT EXISTS favoritos (
    usuario_id INT,
    jugador_id INT,
    RIMARY KEY (usuario_id, jugador_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (jugador_id) REFERENCES jugadores(id) ON DELETE CASCADE
    );
----------------------------------------------------

INSERT INTO usuarios (username, password) VALUES
    ('admin', 'admin123'),
    ('usuario', '1234');
-----------------------------------------------------
INSERT INTO equipos (nombre, ciudad, conferencia) VALUES
    ('Lakers', 'Los Angeles', 'OESTE'),
    ('Celtics', 'Boston', 'ESTE'),
    ('Bulls', 'Chicago', 'ESTE'),
    ('Warriors', 'San Francisco', 'OESTE');

------------------------------------------------------
INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, altura_cm, peso_kg, posicion, lesionado, salario_bruto, equipo_id) VALUES
    ('LeBron', 'James', '1984-12-30', 206, 113.4, 'ALERO', FALSE, 47000000, 1),
    ('Anthony', 'Davis', '1993-03-11', 208, 115.0, 'ALA-PIVOT', TRUE, 38000000, 1);
-------------------------------------------------------
INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, altura_cm, peso_kg, posicion, lesionado, salario_bruto, equipo_id) VALUES
    ('Jayson', 'Tatum', '1998-03-03', 203, 95.0, 'ALERO', FALSE, 32000000, 2);

-------------------------------------------------------
INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, altura_cm, peso_kg, posicion, lesionado, salario_bruto, equipo_id) VALUES
    ('Stephen', 'Curry', '1988-03-14', 188, 84.0, 'BASE', FALSE, 51000000, 4);
-------------------------------------------------------
INSERT INTO favoritos (usuario_id, jugador_id) VALUES
    (1, 1),
    (1, 4);