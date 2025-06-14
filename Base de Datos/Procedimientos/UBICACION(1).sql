-- PAQUETE UBICACION
-- Eliminar Procedimientos de Sede
DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_SEDE$ 
DROP PROCEDURE IF EXISTS MODIFICAR_SEDE$
DROP PROCEDURE IF EXISTS OBTENER_SEDE_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_SEDES_TODOS$
DROP PROCEDURE IF EXISTS ELIMINAR_SEDE$

-- Eliminar Procedimientos de Consultorio
DROP PROCEDURE IF EXISTS INSERTAR_CONSULTORIO$
DROP PROCEDURE IF EXISTS MODIFICAR_CONSULTORIO$
DROP PROCEDURE IF EXISTS OBTENER_CONSULTORIO_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_CONSULTORIOS_TODOS$
DROP PROCEDURE IF EXISTS ELIMINAR_CONSULTORIO$

DROP PROCEDURE IF EXISTS INSERTAR_SEDE_ESPECIALIDAD$
DROP PROCEDURE IF EXISTS ELIMINAR_SEDE_ESPECIALIDAD$
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
    IN _direccion VARCHAR(255)
)
BEGIN
    UPDATE sede SET nombre = _nombre, direccion = _direccion
    WHERE id_sede = _id_sede;
END$
-- - - Obtener
DELIMITER $
CREATE PROCEDURE OBTENER_SEDE_X_ID(
    IN _id_sede INT
)
BEGIN
    SELECT id_sede, nombre, direccion
    FROM sede WHERE id_sede = _id_sede;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_SEDES_TODOS()
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
    INSERT INTO consultorio(nombre_consultorio, piso, capacidad, asignado, activo, fid_sede)
    VALUES(_nombre_consultorio, _piso, _capacidad,0,1, _fid_sede);
    SET _id_consultorio = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_CONSULTORIO(
    IN _id_consultorio INT,
    IN _nombre_consultorio VARCHAR(100),
    IN _piso INT,
    IN _capacidad INT,
    IN _asignado tinyint,
    IN _fid_sede INT
)
BEGIN
    UPDATE consultorio SET nombre_consultorio = _nombre_consultorio, piso = _piso, capacidad = _capacidad, asignado = _asignado, fid_sede = _fid_sede
    WHERE id_consultorio = _id_consultorio;
END$
-- - - Obtener
DELIMITER $
CREATE PROCEDURE OBTENER_CONSULTORIO_X_ID(
    IN _id_consultorio INT
)
BEGIN
    SELECT id_consultorio, nombre_consultorio, piso, capacidad, asignado, fid_sede
    FROM consultorio WHERE id_consultorio = _id_consultorio;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_CONSULTORIOS_TODOS()
BEGIN
    SELECT id_consultorio, nombre_consultorio, piso, capacidad, asignado, fid_sede,
    s.nombre, s.direccion FROM consultorio c 
    inner join sede s on s.id_sede = c.fid_sede where c.activo = 1;
END$
-- - - Eliminar
DELIMITER $
CREATE PROCEDURE ELIMINAR_CONSULTORIO(
    IN _id_consultorio INT
)
BEGIN
    UPDATE consultorio SET activo = 0 WHERE id_consultorio = _id_consultorio;
END$

-- para la tabla intermedia
CREATE PROCEDURE INSERTAR_SEDE_ESPECIALIDAD(
	out _id_sede_especialidad INT,
    in _fid_sede INT,
    in _fid_especialidad INT
)
BEGIN
	insert into sede_especialidad(fid_sede, fid_especialidad,activo) values(_fid_sede, _fid_especialidad, 1);
    SET _id_sede_especialidad = @@last_insert_id;
END$

CREATE PROCEDURE ELIMINAR_SEDE_ESPECIALIDAD(
	in _id_sede_especialidad INT
)
BEGIN
	update sede_especialidad set activo = 0 where id_sede_especialidad = _id_sede_especialidad;
END$


