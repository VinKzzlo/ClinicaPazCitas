-- Eliminar Procedimientos de Sede
DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_SEDE$ 
DROP PROCEDURE IF EXISTS MODIFICAR_SEDE$
DROP PROCEDURE IF EXISTS OBTENER_SEDE_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_SEDES_TODAS$
DROP PROCEDURE IF EXISTS ELIMINAR_SEDE$

-- Eliminar Procedimientos de Consultorio
DROP PROCEDURE IF EXISTS INSERTAR_CONSULTORIO$
DROP PROCEDURE IF EXISTS MODIFICAR_CONSULTORIO$
DROP PROCEDURE IF EXISTS OBTENER_CONSULTORIO_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_CONSULTORIOS_TODOS$
DROP PROCEDURE IF EXISTS ELIMINAR_CONSULTORIO$

-- Procedure: Sede
-- - - Insertar 
DELIMITER $
CREATE PROCEDURE INSERTAR_SEDE(
    OUT _id_sede INT,
    IN _nombre VARCHAR(100),
    IN _direccion VARCHAR(255)

)
BEGIN
    INSERT INTO sede(nombre, direccion, activo)
    VALUES(_nombre, _direccion, 1);
    SET _id_sede = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_SEDE(
    IN _id_sede INT,
    IN _nombre VARCHAR(100),
    IN _direccion VARCHAR(255),
    IN _activo tinyint
)
BEGIN
    UPDATE sede SET nombre = _nombre, direccion = _direccion, activo = _activo
    WHERE id_sede = _id_sede;
END$
-- - - Obtener
DELIMITER $
CREATE PROCEDURE OBTENER_SEDE_X_ID(
    IN _id_sede INT
)
BEGIN
    SELECT id_sede, nombre, direccion, activo
    FROM sede WHERE id_sede = _id_sede;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_SEDES_TODAS()
BEGIN
    SELECT id_sede, nombre, direccion
    FROM sede where activo = 1;
END$
-- - - Eliminar
DELIMITER $
CREATE PROCEDURE ELIMINAR_SEDE(
    IN _id_sede INT
)
BEGIN
    UPDATE sede SET activo = 0 WHERE id_sede = _id_sede;
END$

sELECT *FROM seguro;
$


-- Procedure: Consultorio
-- - - Insertar 
DELIMITER $
CREATE PROCEDURE INSERTAR_CONSULTORIO(
    OUT _id_consultorio INT,
    IN _nombre_consultorio VARCHAR(100),
    IN _piso INT,
    IN _capacidad INT,
    IN _fid_sede INT
)
BEGIN
    INSERT INTO consultorio(nombre_consultorio, piso, capacidad, activo, fid_sede)
    VALUES(_nombre_consultorio, _piso, _capacidad, _activo, _fid_sede);
    SET _id_consultorio = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_CONSULTORIO(
    IN _id_consultorio INT,
    IN _nombre_consultorio VARCHAR(100),
    IN _piso INT,
    IN _capacidad INT,
    IN _activo tinyint,
    IN _fid_sede INT
)
BEGIN
    UPDATE consultorio SET nombre_consultorio = _nombre_consultorio, piso = _piso, capacidad = _capacidad, activo = _activo, fid_sede = _fid_sede
    WHERE id_consultorio = _id_consultorio;
END$
-- - - Obtener
DELIMITER $
CREATE PROCEDURE OBTENER_CONSULTORIO_X_ID(
    IN _id_consultorio INT
)
BEGIN
    SELECT id_consultorio, nombre_consultorio, piso, capacidad, activo, fid_sede
    FROM consultorio WHERE id_consultorio = _id_consultorio;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_CONSULTORIOS_TODOS()
BEGIN
    SELECT id_consultorio, nombre_consultorio, piso, capacidad, activo, fid_sede
    FROM consultorio;
END$
-- - - Eliminar
DELIMITER $
CREATE PROCEDURE ELIMINAR_CONSULTORIO(
    IN _id_consultorio INT
)
BEGIN
    UPDATE consultorio SET activo = 0 WHERE id_consultorio = _id_consultorio;
END$