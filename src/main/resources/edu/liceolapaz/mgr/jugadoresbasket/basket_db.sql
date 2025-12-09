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
    PRIMARY KEY (usuario_id, jugador_id),
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
-------------------------------------------------------
INSERT INTO equipos (nombre, ciudad, conferencia) VALUES
                                                      ('76ers', 'Philadelphia', 'ESTE'),
                                                      ('Knicks', 'New York', 'ESTE'),
                                                      ('Nets', 'Brooklyn', 'ESTE'),
                                                      ('Raptors', 'Toronto', 'ESTE'),
                                                      ('Cavaliers', 'Cleveland', 'ESTE'),
                                                      ('Pistons', 'Detroit', 'ESTE'),
                                                      ('Pacers', 'Indianapolis', 'ESTE'),
                                                      ('Bucks', 'Milwaukee', 'ESTE'),
                                                      ('Hawks', 'Atlanta', 'ESTE'),
                                                      ('Hornets', 'Charlotte', 'ESTE'),
                                                      ('Heat', 'Miami', 'ESTE'),
                                                      ('Magic', 'Orlando', 'ESTE'),
                                                      ('Wizards', 'Washington', 'ESTE'),
                                                      ('Clippers', 'Los Angeles', 'OESTE'),
                                                      ('Suns', 'Phoenix', 'OESTE'),
                                                      ('Kings', 'Sacramento', 'OESTE'),
                                                      ('Nuggets', 'Denver', 'OESTE'),
                                                      ('Timberwolves', 'Minnesota', 'OESTE'),
                                                      ('Thunder', 'Oklahoma City', 'OESTE'),
                                                      ('Trail Blazers', 'Portland', 'OESTE'),
                                                      ('Jazz', 'Utah', 'OESTE'),
                                                      ('Mavericks', 'Dallas', 'OESTE'),
                                                      ('Rockets', 'Houston', 'OESTE'),
                                                      ('Grizzlies', 'Memphis', 'OESTE'),
                                                      ('Pelicans', 'New Orleans', 'OESTE'),
                                                      ('Spurs', 'San Antonio', 'OESTE');

-------------------------------------------------------
INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, altura_cm, peso_kg, posicion, lesionado, salario_bruto, equipo_id) VALUES

-------------------------------------------------------

                                                                                                                                   ('Kristaps', 'Porzingis', '1995-08-02', 218, 109.0, 'ALA-PIVOT', FALSE, 36000000, (SELECT id FROM equipos WHERE nombre = 'Hawks')),
                                                                                                                                   ('Jrue', 'Holiday', '1990-06-12', 193, 93.0, 'BASE', FALSE, 36800000, (SELECT id FROM equipos WHERE nombre = 'Celtics')),
                                                                                                                                   ('Mikal', 'Bridges', '1996-08-30', 198, 95.0, 'ALERO', FALSE, 21700000, (SELECT id FROM equipos WHERE nombre = 'Nets')),
                                                                                                                                   ('Ben', 'Simmons', '1996-07-20', 208, 109.0, 'BASE', TRUE, 37800000, (SELECT id FROM equipos WHERE nombre = 'Clippers')),
                                                                                                                                   ('Jalen', 'Brunson', '1996-08-31', 188, 86.0, 'BASE', FALSE, 26300000, (SELECT id FROM equipos WHERE nombre = 'Knicks')),
                                                                                                                                   ('Julius', 'Randle', '1994-11-29', 203, 113.0, 'ALA-PIVOT', TRUE, 28200000, (SELECT id FROM equipos WHERE nombre = 'Knicks')),
                                                                                                                                   ('Joel', 'Embiid', '1994-03-16', 213, 127.0, 'PIVOT', TRUE, 47600000, (SELECT id FROM equipos WHERE nombre = '76ers')),
                                                                                                                                   ('Tyrese', 'Maxey', '2000-11-04', 188, 91.0, 'BASE', FALSE, 4300000, (SELECT id FROM equipos WHERE nombre = '76ers')),
                                                                                                                                   ('Scottie', 'Barnes', '2001-08-01', 201, 102.0, 'ALERO', FALSE, 8000000, (SELECT id FROM equipos WHERE nombre = 'Raptors')),
                                                                                                                                   ('RJ', 'Barrett', '2000-06-14', 198, 97.0, 'ESCOLTA', FALSE, 23800000, (SELECT id FROM equipos WHERE nombre = 'Raptors')),
                                                                                                                                   ('DeMar', 'DeRozan', '1989-08-07', 198, 100.0, 'ALERO', FALSE, 28600000, (SELECT id FROM equipos WHERE nombre = 'Bulls')),
                                                                                                                                   ('Nikola', 'Vucevic', '1990-10-24', 208, 118.0, 'PIVOT', FALSE, 18500000, (SELECT id FROM equipos WHERE nombre = 'Bulls')),
                                                                                                                                   ('Donovan', 'Mitchell', '1996-09-07', 191, 98.0, 'ESCOLTA', FALSE, 33100000, (SELECT id FROM equipos WHERE nombre = 'Cavaliers')),
                                                                                                                                   ('Darius', 'Garland', '2000-01-26', 185, 87.0, 'BASE', FALSE, 34000000, (SELECT id FROM equipos WHERE nombre = 'Cavaliers')),
                                                                                                                                   ('Cade', 'Cunningham', '2001-09-25', 198, 100.0, 'BASE', FALSE, 11000000, (SELECT id FROM equipos WHERE nombre = 'Pistons')),
                                                                                                                                   ('Jaden', 'Ivey', '2002-02-13', 193, 88.0, 'ESCOLTA', FALSE, 7600000, (SELECT id FROM equipos WHERE nombre = 'Pistons')),
                                                                                                                                   ('Tyrese', 'Haliburton', '2000-02-29', 196, 84.0, 'BASE', FALSE, 5800000, (SELECT id FROM equipos WHERE nombre = 'Pacers')),
                                                                                                                                   ('Pascal', 'Siakam', '1994-04-02', 203, 104.0, 'ALA-PIVOT', FALSE, 37800000, (SELECT id FROM equipos WHERE nombre = 'Pacers')),
                                                                                                                                   ('Giannis', 'Antetokounmpo', '1994-12-06', 211, 110.0, 'ALA-PIVOT', FALSE, 45600000, (SELECT id FROM equipos WHERE nombre = 'Bucks')),
                                                                                                                                   ('Damian', 'Lillard', '1990-07-15', 188, 88.0, 'BASE', FALSE, 45600000, (SELECT id FROM equipos WHERE nombre = 'Bucks')),
                                                                                                                                   ('Trae', 'Young', '1998-09-19', 185, 74.0, 'BASE', FALSE, 40000000, (SELECT id FROM equipos WHERE nombre = 'Hawks')),
                                                                                                                                   ('Dejounte', 'Murray', '1996-09-19', 196, 82.0, 'ESCOLTA', FALSE, 18200000, (SELECT id FROM equipos WHERE nombre = 'Hawks')),
                                                                                                                                   ('LaMelo', 'Ball', '2001-08-22', 201, 82.0, 'BASE', TRUE, 10900000, (SELECT id FROM equipos WHERE nombre = 'Hornets')),
                                                                                                                                   ('Brandon', 'Miller', '2002-11-22', 206, 91.0, 'ALERO', FALSE, 11000000, (SELECT id FROM equipos WHERE nombre = 'Hornets')),
                                                                                                                                   ('Jimmy', 'Butler', '1989-09-14', 201, 104.0, 'ALERO', FALSE, 45100000, (SELECT id FROM equipos WHERE nombre = 'Warriors')),
                                                                                                                                   ('Bam', 'Adebayo', '1997-07-18', 206, 116.0, 'PIVOT', FALSE, 32600000, (SELECT id FROM equipos WHERE nombre = 'Heat')),
                                                                                                                                   ('Paolo', 'Banchero', '2002-11-12', 208, 113.0, 'ALA-PIVOT', FALSE, 11600000, (SELECT id FROM equipos WHERE nombre = 'Magic')),
                                                                                                                                   ('Franz', 'Wagner', '2001-08-27', 208, 100.0, 'ALERO', FALSE, 5500000, (SELECT id FROM equipos WHERE nombre = 'Magic')),
                                                                                                                                   ('Kyle', 'Kuzma', '1995-07-24', 206, 100.0, 'ALA-PIVOT', FALSE, 25500000, (SELECT id FROM equipos WHERE nombre = 'Wizards')),
                                                                                                                                   ('Jordan', 'Poole', '1999-06-19', 193, 88.0, 'ESCOLTA', FALSE, 27400000, (SELECT id FROM equipos WHERE nombre = 'Wizards')),
                                                                                                                                   ('Nikola', 'Jokic', '1995-02-19', 211, 129.0, 'PIVOT', FALSE, 47600000, (SELECT id FROM equipos WHERE nombre = 'Nuggets')),
                                                                                                                                   ('Jamal', 'Murray', '1997-02-23', 193, 98.0, 'BASE', FALSE, 33800000, (SELECT id FROM equipos WHERE nombre = 'Nuggets')),
                                                                                                                                   ('Anthony', 'Edwards', '2001-08-05', 193, 102.0, 'ESCOLTA', FALSE, 13500000, (SELECT id FROM equipos WHERE nombre = 'Timberwolves')),
                                                                                                                                   ('Karl-Anthony', 'Towns', '1995-11-15', 213, 112.0, 'ALA-PIVOT', FALSE, 36000000, (SELECT id FROM equipos WHERE nombre = 'Timberwolves')),
                                                                                                                                   ('Shai', 'Gilgeous-Alexander', '1998-07-12', 198, 88.0, 'BASE', FALSE, 33300000, (SELECT id FROM equipos WHERE nombre = 'Thunder')),
                                                                                                                                   ('Chet', 'Holmgren', '2002-05-01', 216, 94.0, 'PIVOT', FALSE, 10300000, (SELECT id FROM equipos WHERE nombre = 'Thunder')),
                                                                                                                                   ('Jerami', 'Grant', '1994-03-12', 201, 95.0, 'ALA-PIVOT', FALSE, 27500000, (SELECT id FROM equipos WHERE nombre = 'Trail Blazers')),
                                                                                                                                   ('Deandre', 'Ayton', '1998-07-23', 213, 113.0, 'PIVOT', FALSE, 32400000, (SELECT id FROM equipos WHERE nombre = 'Lakers')),
                                                                                                                                   ('Lauri', 'Markkanen', '1997-05-22', 213, 109.0, 'ALA-PIVOT', FALSE, 17200000, (SELECT id FROM equipos WHERE nombre = 'Jazz')),
                                                                                                                                   ('Jordan', 'Clarkson', '1992-06-07', 196, 88.0, 'ESCOLTA', FALSE, 23400000, (SELECT id FROM equipos WHERE nombre = 'Jazz')),
                                                                                                                                   ('Draymond', 'Green', '1990-03-04', 198, 104.0, 'ALA-PIVOT', FALSE, 22300000, (SELECT id FROM equipos WHERE nombre = 'Warriors')),
                                                                                                                                   ('Andrew', 'Wiggins', '1995-02-23', 201, 89.0, 'ALERO', FALSE, 24300000, (SELECT id FROM equipos WHERE nombre = 'Warriors')),
                                                                                                                                   ('Kawhi', 'Leonard', '1991-06-29', 201, 102.0, 'ALERO', FALSE, 45600000, (SELECT id FROM equipos WHERE nombre = 'Clippers')),
                                                                                                                                   ('Paul', 'George', '1990-05-02', 203, 100.0, 'ESCOLTA', FALSE, 45600000, (SELECT id FROM equipos WHERE nombre = 'Clippers')),
                                                                                                                                   ('DAngelo', 'Russell', '1996-02-23', 193, 88.0, 'BASE', FALSE, 17300000, (SELECT id FROM equipos WHERE nombre = 'Mavericks')),
                                                                                                                                   ('Austin', 'Reaves', '1998-05-29', 196, 93.0, 'ESCOLTA', FALSE, 12000000, (SELECT id FROM equipos WHERE nombre = 'Lakers')),
                                                                                                                                   ('Kevin', 'Durant', '1988-09-29', 211, 109.0, 'ALA-PIVOT', FALSE, 47600000, (SELECT id FROM equipos WHERE nombre = 'Rockets')),
                                                                                                                                   ('Devin', 'Booker', '1996-10-30', 196, 93.0, 'ESCOLTA', FALSE, 36000000, (SELECT id FROM equipos WHERE nombre = 'Suns')),
                                                                                                                                   ('DeAaron', 'Fox', '1997-12-20', 191, 84.0, 'BASE', FALSE, 32600000, (SELECT id FROM equipos WHERE nombre = 'Kings')),
                                                                                                                                   ('Domantas', 'Sabonis', '1996-05-03', 208, 109.0, 'PIVOT', FALSE, 30600000, (SELECT id FROM equipos WHERE nombre = 'Kings')),
                                                                                                                                   ('Tim', 'Hardaway Jr.', '1992-03-16', 196, 93.0, 'ESCOLTA', FALSE, 17900000, (SELECT id FROM equipos WHERE nombre = 'Mavericks')),
                                                                                                                                   ('Dereck', 'Lively II', '2004-02-12', 216, 104.0, 'PIVOT', FALSE, 4700000, (SELECT id FROM equipos WHERE nombre = 'Mavericks')),
                                                                                                                                   ('Fred', 'VanVleet', '1994-02-25', 183, 88.0, 'BASE', FALSE, 40800000, (SELECT id FROM equipos WHERE nombre = 'Rockets')),
                                                                                                                                   ('Alperen', 'Sengun', '2002-07-25', 211, 110.0, 'PIVOT', FALSE, 3500000, (SELECT id FROM equipos WHERE nombre = 'Rockets')),
                                                                                                                                   ('Ja', 'Morant', '1999-08-10', 188, 79.0, 'BASE', TRUE, 34000000, (SELECT id FROM equipos WHERE nombre = 'Grizzlies')),
                                                                                                                                   ('Jaren', 'Jackson Jr.', '1999-09-15', 208, 110.0, 'ALA-PIVOT', FALSE, 27100000, (SELECT id FROM equipos WHERE nombre = 'Grizzlies')),
                                                                                                                                   ('Zion', 'Williamson', '2000-07-06', 198, 129.0, 'ALA-PIVOT', FALSE, 34000000, (SELECT id FROM equipos WHERE nombre = 'Pelicans')),
                                                                                                                                   ('Brandon', 'Ingram', '1997-09-02', 203, 86.0, 'ALERO', FALSE, 33800000, (SELECT id FROM equipos WHERE nombre = 'Raptors')),
                                                                                                                                   ('Victor', 'Wembanyama', '2004-01-04', 224, 95.0, 'PIVOT', FALSE, 12100000, (SELECT id FROM equipos WHERE nombre = 'Spurs')),
                                                                                                                                   ('Jeremy', 'Sochan', '2003-05-20', 203, 104.0, 'ALA-PIVOT', FALSE, 5300000, (SELECT id FROM equipos WHERE nombre = 'Spurs'));
