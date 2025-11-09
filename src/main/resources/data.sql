-- -----------------------------------------------------
-- Datos Semilla para el Trabajo Práctico (Álbum de Figuritas)
-- Se ejecuta automáticamente al iniciar la aplicación.
-- -----------------------------------------------------

-- Deshabilitamos chequeos de FK temporalmente para insertar
SET FOREIGN_KEY_CHECKS=0;

-- Limpiamos las tablas antes de insertar para evitar duplicados al reiniciar
TRUNCATE TABLE stickers;
TRUNCATE TABLE albums;

-- -----------------------------------------------------
-- ÁLBUM 1: Dificil (usará "weightedStrategy")
-- -----------------------------------------------------
INSERT INTO albums (id, titulo, descripcion, categoria, dificultad, total_figuritas, publicado) 
VALUES 
(1, 'Copa América 2024', 'Álbum oficial del torneo Conmebol', 'Deportes', 2, 0, false);

-- Stickers para el Álbum 1
INSERT INTO stickers (album_id, nombre, numero, imagen_url, seccion, rareza, stock_total, stock_disponible) 
VALUES
(1, 'Lionel Messi', 10, 'http://img.example.com/messi.png', 'Argentina', 'EPICA', 50, 50),
(1, 'Escudo AFA', 9, 'http://img.example.com/afa.png', 'Argentina', 'COMUN', 500, 500),
(1, 'Vinicius Jr', 7, 'http://img.example.com/vini.png', 'Brasil', 'RARA', 150, 150),
(1, 'Trofeo Copa América', 0, 'http://img.example.com/trofeo.png', NULL, 'RARA', 100, 100); -- Sticker sin sección

-- Actualizamos el conteo del álbum
UPDATE albums SET total_figuritas = 4 WHERE id = 1;

-- -----------------------------------------------------
-- ÁLBUM 2: Fácil (usará "uniformStrategy")
-- -----------------------------------------------------
INSERT INTO albums (id, titulo, descripcion, categoria, dificultad, total_figuritas, publicado) 
VALUES 
(2, 'Mundial 2026 (Fácil)', 'Versión de prueba con figuritas fáciles', 'Deportes', 0, 0, false);
-- Dejamos este álbum vacío a propósito para que puedas probar a AÑADIRLE figuritas con el POST.
-- Al añadirlas, notarás que la "uniformStrategy" genera muchas Raras y Épicas.


-- -----------------------------------------------------
-- ÁLBUM 3: Manual (usará "presetStrategy")
-- -----------------------------------------------------
INSERT INTO albums (id, titulo, descripcion, categoria, dificultad, total_figuritas, publicado) 
VALUES 
(3, 'Álbum Manual (Admin)', 'Prueba de carga manual con rarezas predefinidas', 'Test', 1, 0, false);

-- Stickers para el Álbum 3 (demuestra el caso de tu JSON de ejemplo)
INSERT INTO stickers (album_id, nombre, numero, imagen_url, seccion, rareza, stock_total, stock_disponible) 
VALUES
(3, 'Jude Bellingham', 10, 'http://img.example.com/bellingham.png', 'Inglaterra', 'COMUN', 1000, 1000),
(3, 'Jude Bellingham', 10, 'http://img.example.com/bellingham.png', 'Inglaterra', 'EPICA', 20, 20);

-- Actualizamos el conteo del álbum
UPDATE albums SET total_figuritas = 2 WHERE id = 3;


-- Reactivamos los chequeos de FK
SET FOREIGN_KEY_CHECKS=1;