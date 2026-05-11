-- ============================================
-- Inicialización de MySQL para Docker Compose
-- Crea las bases de datos y usuarios necesarios
-- ============================================

-- Base de datos 1
CREATE DATABASE IF NOT EXISTS dockerMysql -- cambiar nombre base de datos para adaptar
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Base de datos 2
CREATE DATABASE IF NOT EXISTS dockerMysql2 -- cambiar nombre base de datos para adaptar
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Usuario para base de datos 1
CREATE USER IF NOT EXISTS 'usuario1'@'%' IDENTIFIED BY 'usuario_1_2026';
GRANT ALL PRIVILEGES ON dockerMysql.* TO 'usuario_usuarios'@'%'; -- cambiar dockerMysql.* a {nombreBD}.*

-- Usuario para base de datos 2
CREATE USER IF NOT EXISTS 'usuario2'@'%' IDENTIFIED BY 'usuario_2_2026';
GRANT ALL PRIVILEGES ON dockerMysql2.* TO 'usuario_productos'@'%'; -- cambiar dockerMysql.* a {nombreBD}.*

FLUSH PRIVILEGES;
