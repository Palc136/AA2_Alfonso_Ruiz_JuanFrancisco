-- Crear base de datos
CREATE DATABASE IF NOT EXISTS cineplus_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE cineplus_db;

-- ═══════════════════════════════════════════════════════════════
-- TABLA: USUARIOS
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    INDEX idx_email (email),
    INDEX idx_activo (activo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ═══════════════════════════════════════════════════════════════
-- TABLA: GENEROS
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE generos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT,
    INDEX idx_nombre (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ═══════════════════════════════════════════════════════════════
-- TABLA: PELICULAS
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE peliculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    anio INT NOT NULL,
    genero_id INT NOT NULL,
    -- FIX: DECIMAL(3,1) para soportar correctamente valores hasta 10.0
    rating DECIMAL(3,1) NOT NULL DEFAULT 0.0,
    imagen_portada VARCHAR(255),
    sinopsis TEXT,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (genero_id) REFERENCES generos(id) ON DELETE RESTRICT,
    INDEX idx_titulo (titulo),
    INDEX idx_anio (anio),
    INDEX idx_genero (genero_id),
    INDEX idx_rating (rating),
    CHECK (rating >= 0.0 AND rating <= 5.0),
    -- FIX: CURDATE() no está permitida en CHECK constraints en MySQL.
    -- Se reemplaza por un año máximo fijo. Actualizar según sea necesario.
    CHECK (anio >= 1888 AND anio <= 2030)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ═══════════════════════════════════════════════════════════════
-- TABLA: ACTORES
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE actores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    imagen VARCHAR(255),
    biografia TEXT,
    fecha_nacimiento DATE,
    INDEX idx_nombre (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ═══════════════════════════════════════════════════════════════
-- TABLA: REPARTO_PELICULA (Relación N:N)
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE reparto_pelicula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pelicula_id INT NOT NULL,
    actor_id INT NOT NULL,
    personaje VARCHAR(150) NOT NULL,
    orden_aparicion INT NOT NULL DEFAULT 1,
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(id) ON DELETE CASCADE,
    FOREIGN KEY (actor_id) REFERENCES actores(id) ON DELETE CASCADE,
    INDEX idx_pelicula (pelicula_id),
    INDEX idx_actor (actor_id),
    UNIQUE KEY unique_actor_pelicula (pelicula_id, actor_id, personaje)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ═══════════════════════════════════════════════════════════════
-- TABLA: FAVORITOS
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE favoritos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    pelicula_id INT NOT NULL,
    fecha_agregado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(id) ON DELETE CASCADE,
    UNIQUE KEY unique_favorito (usuario_id, pelicula_id),
    INDEX idx_usuario (usuario_id),
    INDEX idx_pelicula (pelicula_id),
    INDEX idx_fecha (fecha_agregado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ═══════════════════════════════════════════════════════════════
-- TABLA: COMENTARIOS
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE comentarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pelicula_id INT NOT NULL,
    usuario_id INT NOT NULL,
    calificacion INT NOT NULL,
    texto TEXT NOT NULL,
    fecha_comentario DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    INDEX idx_pelicula (pelicula_id),
    INDEX idx_usuario (usuario_id),
    INDEX idx_fecha (fecha_comentario),
    CHECK (calificacion >= 1 AND calificacion <= 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ═══════════════════════════════════════════════════════════════
-- TABLA: CONTACTO
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE contacto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_envio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('pendiente', 'leido', 'respondido') NOT NULL DEFAULT 'pendiente',
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE SET NULL,
    INDEX idx_usuario (usuario_id),
    INDEX idx_estado (estado),
    INDEX idx_fecha (fecha_envio)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ═══════════════════════════════════════════════════════════════
-- INSERCIÓN DE DATOS INICIALES
-- ═══════════════════════════════════════════════════════════════

-- Insertar géneros
INSERT INTO generos (nombre, descripcion) VALUES
('Acción', 'Películas con escenas de acción, combates y aventuras'),
('Aventura', 'Películas de exploración y descubrimiento'),
('Ciencia Ficción', 'Películas con elementos futuristas y tecnológicos'),
('Comedia', 'Películas humorísticas y entretenidas'),
('Drama', 'Películas con temas serios y emocionales'),
('Terror', 'Películas de miedo y suspenso'),
('Romance', 'Películas de amor y relaciones'),
('Thriller', 'Películas de suspenso e intriga');


-- Insertar actores
INSERT INTO actores (nombre, imagen, fecha_nacimiento) VALUES
('Robert Downey Jr.', 'img/personajes_actores/Robert(ironman).jpeg', '1965-04-04'),
('Chris Evans', 'img/personajes_actores/chris_evans(capitanA).jpeg', '1981-06-13'),
('Scarlett Johansson', 'img/personajes_actores/scarlett(matasha ).jpeg', '1984-11-22'),
('Robert Pattinson', 'img/personajes_actores/robertP(batman).jpeg', '1986-05-13'),
('Zoë Kravitz', 'img/personajes_actores/Zoe(selenia kyle).jpeg', '1988-12-01'),
('Paul Dano', 'img/personajes_actores/Paul(riddler).jpeg', '1984-06-19'),
('Tom Holland', 'img/personajes_actores/TomHallan(spiderman).jpeg', '1996-06-01'),
('Zendaya', 'img/personajes_actores/zendaya(MJ).jpeg', '1996-09-01'),
('Benedict Cumberbatch', 'img/personajes_actores/benedict(doctorstange).jpeg', '1976-07-19'),
('Timothée Chalamet', 'img/personajes_actores/chalament(atreides).jpeg', '1995-12-27'),
('Rebecca Ferguson', 'img/personajes_actores/rebecca(leydyatreides).jpeg', '1983-10-19'),
('Oscar Isaac', 'img/personajes_actores/oscar(dukeatreides).jpeg', '1979-03-09'),
('Tom Cruise', 'img/personajes_actores/Tomcruz(maverick).jpeg', '1962-07-03'),
('Miles Teller', 'img/personajes_actores/miles(rooster).jpeg', '1987-02-20'),
('Jennifer Connelly', 'img/personajes_actores/jennifer(penny).jpeg', '1970-12-12'),
('Letitia Wright', 'img/personajes_actores/letitia(shuri).jpeg', '1993-10-31'),
('Lupita Nyongo', 'img/personajes_actores/lupita(nakia).jpeg', '1983-03-01'),
('Danai Gurira', 'img/personajes_actores/danai(okoye).jpeg', '1978-02-14'),
('Michelle Yeoh', 'img/personajes_actores/michelle(evelyn).jpeg', '1962-08-06'),
('Stephanie Hsu', 'img/personajes_actores/stephanie(joy wang).jpeg', '1990-11-25'),
('Ke Huy Quan', 'img/personajes_actores/kehuyquan(waymond_wang).jpeg', '1971-08-20'),
('Ezra Miller', 'img/personajes_actores/ezra(flah).jpeg', '1992-09-30'),
('Michael Keaton', 'img/personajes_actores/michael(brucewayne).jpeg', '1951-09-05'),
('Sasha Calle', 'img/personajes_actores/sasha(karazorel).jpeg', '1995-08-07');


-- Insertar películas
INSERT INTO peliculas (titulo, anio, genero_id, rating, imagen_portada, sinopsis) VALUES
('Avengers: Endgame', 2019, 1, 4.8, 'img/portadas/avengers.webp', 
 'Los Vengadores restantes deben encontrar una manera de recuperar a sus aliados para un enfrentamiento épico con Thanos, el malvado que diezmó el planeta y el universo.'),

('The Batman', 2022, 1, 4.5, 'img/portadas/batman.jpg',
 'Batman explora la corrupción existente en la ciudad de Gotham y el vínculo de esta con su propia familia. Además, entra en conflicto con un asesino en serie conocido como el Acertijo.'),

('Spider-Man: No Way Home', 2021, 2, 4.7, 'img/portadas/spiderman.webp',
 'Peter Parker desenmascarado ya no puede separar su vida normal de los altos riesgos de ser un súper héroe. Cuando pide ayuda al Doctor Strange, los riesgos se vuelven aún más peligrosos.'),

('Dune', 2021, 3, 4.3, 'img/portadas/dune.webp',
 'Paul Atreides, un joven brillante y talentoso nacido en un gran destino más allá de su comprensión, debe viajar al planeta más peligroso del universo para asegurar el futuro de su familia y su pueblo.'),

('Top Gun: Maverick', 2022, 1, 4.6, 'img/portadas/top_gun.webp',
 'Después de más de treinta años de servicio como uno de los mejores aviadores de la Marina, Pete Maverick Mitchell está en casa, forzando los límites como un valiente piloto de pruebas.'),

('Black Panther: Wakanda Forever', 2022, 1, 4.2, 'img/portadas/black_panter.webp',
 'La gente de Wakanda lucha para embarcarse en un nuevo capítulo tras la muerte del Rey TChalla, combinando sus esfuerzos con la guerra que libran bajo el agua de la nación de Talokan.'),

('Everything Everywhere All at Once', 2022, 4, 4.9, 'img/portadas/everithing.webp',
 'Una inmigrante china anciana se ve envuelta en una aventura loca donde solo ella puede salvar el mundo explorando otros universos que se conectan con las vidas que podría haber llevado.'),

('The Flash', 2023, 1, 4.0, 'img/portadas/flash.webp',
 'Barry Allen utiliza sus superpoderes para viajar en el tiempo y cambiar los eventos del pasado, pero su intento de salvar a su familia crea un mundo sin superhéroes.');


-- Insertar reparto de películas
-- Avengers: Endgame
INSERT INTO reparto_pelicula (pelicula_id, actor_id, personaje, orden_aparicion) VALUES
(1, 1, 'Iron Man / Tony Stark', 1),
(1, 2, 'Captain America / Steve Rogers', 2),
(1, 3, 'Black Widow / Natasha Romanoff', 3);

-- The Batman
INSERT INTO reparto_pelicula (pelicula_id, actor_id, personaje, orden_aparicion) VALUES
(2, 4, 'Batman / Bruce Wayne', 1),
(2, 5, 'Catwoman / Selina Kyle', 2),
(2, 6, 'Riddler / Edward Nashton', 3);

-- Spider-Man: No Way Home
INSERT INTO reparto_pelicula (pelicula_id, actor_id, personaje, orden_aparicion) VALUES
(3, 7, 'Spider-Man / Peter Parker', 1),
(3, 8, 'MJ', 2),
(3, 9, 'Doctor Strange / Stephen Strange', 3);

-- Dune
INSERT INTO reparto_pelicula (pelicula_id, actor_id, personaje, orden_aparicion) VALUES
(4, 10, 'Paul Atreides', 1),
(4, 11, 'Lady Jessica', 2),
(4, 12, 'Duke Leto Atreides', 3);

-- Top Gun: Maverick
INSERT INTO reparto_pelicula (pelicula_id, actor_id, personaje, orden_aparicion) VALUES
(5, 13, 'Pete Maverick Mitchell', 1),
(5, 14, 'Bradley Rooster Bradshaw', 2),
(5, 15, 'Penny Benjamin', 3);

-- Black Panther: Wakanda Forever
INSERT INTO reparto_pelicula (pelicula_id, actor_id, personaje, orden_aparicion) VALUES
(6, 16, 'Shuri', 1),
(6, 17, 'Nakia', 2),
(6, 18, 'Okoye', 3);

-- Everything Everywhere All at Once
INSERT INTO reparto_pelicula (pelicula_id, actor_id, personaje, orden_aparicion) VALUES
(7, 19, 'Evelyn Wang', 1),
(7, 20, 'Joy Wang', 2),
(7, 21, 'Waymond Wang', 3);

-- The Flash
INSERT INTO reparto_pelicula (pelicula_id, actor_id, personaje, orden_aparicion) VALUES
(8, 22, 'The Flash / Barry Allen', 1),
(8, 23, 'Batman / Bruce Wayne', 2),
(8, 24, 'Supergirl / Kara Zor-El', 3);


-- ═══════════════════════════════════════════════════════════════
-- TRIGGER: ACTUALIZAR RATING DE PELÍCULA AL AGREGAR COMENTARIO
-- ═══════════════════════════════════════════════════════════════

DELIMITER //

CREATE TRIGGER actualizar_rating_pelicula
AFTER INSERT ON comentarios
FOR EACH ROW
BEGIN
    UPDATE peliculas
    SET rating = (
        SELECT ROUND(AVG(calificacion), 1)
        FROM comentarios
        WHERE pelicula_id = NEW.pelicula_id
    )
    WHERE id = NEW.pelicula_id;
END//

DELIMITER ;


-- ═══════════════════════════════════════════════════════════════
-- VISTAS ÚTILES
-- ═══════════════════════════════════════════════════════════════

-- Vista: Películas con su género
CREATE VIEW vista_peliculas_completas AS
SELECT 
    p.id,
    p.titulo,
    p.anio,
    g.nombre AS genero,
    p.rating,
    p.imagen_portada,
    p.sinopsis,
    p.fecha_creacion
FROM peliculas p
JOIN generos g ON p.genero_id = g.id;


-- Vista: Reparto completo de películas
CREATE VIEW vista_reparto_completo AS
SELECT 
    p.id AS pelicula_id,
    p.titulo AS pelicula,
    a.id AS actor_id,
    a.nombre AS actor,
    a.imagen AS actor_imagen,
    rp.personaje,
    rp.orden_aparicion
FROM peliculas p
JOIN reparto_pelicula rp ON p.id = rp.pelicula_id
JOIN actores a ON rp.actor_id = a.id
ORDER BY p.id, rp.orden_aparicion;


-- Vista: Estadísticas de comentarios por película
CREATE VIEW vista_estadisticas_comentarios AS
SELECT 
    p.id AS pelicula_id,
    p.titulo,
    COUNT(c.id) AS total_comentarios,
    ROUND(AVG(c.calificacion), 1) AS calificacion_promedio,
    MAX(c.fecha_comentario) AS ultimo_comentario
FROM peliculas p
LEFT JOIN comentarios c ON p.id = c.pelicula_id
GROUP BY p.id, p.titulo;
