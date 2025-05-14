-- Eliminar Procedimientos de Cita
DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_CITA$ 
DROP PROCEDURE IF EXISTS MODIFICAR_CITA$ 
DROP PROCEDURE IF EXISTS OBTENER_CITA_X_ID$ 
DROP PROCEDURE IF EXISTS LISTAR_CITAS_TODAS$ 
DROP PROCEDURE IF EXISTS ELIMINAR_CITA$

-- Eliminar Procedimientos de DetalleTurno
DROP PROCEDURE IF EXISTS INSERTAR_DISPONIBILIDAD$
DROP PROCEDURE IF EXISTS MODIFICAR_DISPONIBILIDAD$
DROP PROCEDURE IF EXISTS OBTENER_DISPONIBILIDAD_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_DISPONIBILIDADES_TODAS$
DROP PROCEDURE IF EXISTS ELIMINAR_DISPONIBILIDAD$

-- Eliminar Procedimientos de TurnoMedico
DROP PROCEDURE IF EXISTS INSERTAR_TURNO_MEDICO$
DROP PROCEDURE IF EXISTS MODIFICAR_TURNO_FECHA_HORA$
DROP PROCEDURE IF EXISTS OBTENER_TURNO_MEDICO_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_TURNOS_TODOS$

-- Eliminar Procedimientos de Especialidad
DROP PROCEDURE IF EXISTS INSERTAR_ESPECIALIDAD$
DROP PROCEDURE IF EXISTS MODIFICAR_ESPECIALIDAD$
DROP PROCEDURE IF EXISTS OBTENER_ESPECIALIDAD_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_ESPECIALIDADES_TODAS$
DROP PROCEDURE IF EXISTS ELIMINAR_ESPECIALIDAD$





-- Procedure: Cita
-- - - Insertar 
DELIMITER $
CREATE PROCEDURE INSERTAR_CITA(
	OUT _id_cita INT,
	IN _fecha datetime,
    IN _estado_cita ENUM('PROGRAMADA','CANCELADA','ATENDIDA'),
    IN _motivo_consulta VARCHAR(50),
    IN _fid_paciente INT,
    IN _fid_disponibilidad INT,
    IN _fid_especialidad INT,
    IN _fid_recepcionista INT
)
BEGIN
	INSERT INTO cita( fecha,estado_cita, motivo_consulta,fecha_actualizacion,fid_paciente,fid_disponibilidad,fid_especialidad,fid_recepcionista)
    VALUES(_fecha,_estado_cita,_motivo_consulta,SYSDATE(),_fid_paciente,_fid_disponibilidad,_fid_especialidad,_fid_recepcionista);
    SET _id_cita = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_CITA(
    IN _id_cita INT,
    IN _fecha datetime,
    IN _estado_cita ENUM('PROGRAMADA','CANCELADA','ATENDIDA'),
    IN _motivo_consulta VARCHAR(50)
)
BEGIN
    UPDATE cita SET fecha = _fecha, estado_cita = _estado_cita, motivo_consulta = _motivo_consulta,  fecha_actualizacion = SYSDATE()
    WHERE id_cita = _id_cita;
END$
-- - - Obtener
DELIMITER $
CREATE PROCEDURE OBTENER_CITA_X_ID(
    IN _id_cita INT
)
BEGIN
    SELECT id_cita, fecha, estado_cita, motivo_consulta,fecha_actualizacion, fid_paciente, fid_disponibilidad, fid_especialidad 
    FROM cita WHERE id_cita = _id_cita;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_CITAS_TODAS()
BEGIN
    SELECT id_cita, fecha, estado_cita, motivo_consulta, fecha_actualizacion, fid_paciente, fid_disponibilidad, fid_especialidad 
    FROM cita;
END$
-- - - Eliminar
DELIMITER $
CREATE PROCEDURE ELIMINAR_CITA(
    IN _id_cita INT
)
BEGIN
    UPDATE cita SET estado_cita = 'CANCELADA' WHERE id_cita = _id_cita;
END$


SELECT *FROM cita$
-- Procedure: DetalleTurno
-- - - Insertar 
DELIMITER $
Select *from disponibilidad;
$
CREATE PROCEDURE INSERTAR_DISPONIBILIDAD(
	OUT _id_disponibilidad INT,
    IN _fecha DATE,
    IN _hora TIME,
    IN _fid_turno_medico INT
)
BEGIN
	INSERT INTO disponibilidad(fecha,hora,disponible,fid_turno_medico)
    VALUES (_fecha,_hora,1,_fid_turno_medico);
    SET _id_disponibilidad = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_DISPONIBILIDAD(
	IN _id_disponibilidad INT,
    IN _fecha DATE,
    IN _hora TIME
)
BEGIN
	UPDATE 
		disponibilidad
    SET
		fecha = _fecha, hora = _hora
    WHERE
		id_disponibilidad = _id_disponibilidad;
END$
-- - - obtener
DELIMITER $
CREATE PROCEDURE OBTENER_DISPONIBILIDAD_X_ID(
    IN _id_disponibilidad INT
)
BEGIN
    SELECT id_detalle_turno, hora, estado, fid_turno_medico 
    FROM disponibilidad WHERE id_disponibilidad = _id_disponibilidad;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_DISPONIBILIDADES_TODAS()
BEGIN
	SELECT 
        d.id_disponibilidad,
        d.fecha,
        d.hora,
        t.id_turno AS turno_id,
        t.dia AS turno_dia,
        t.hora_inicio AS turno_hora_inicio,
        t.hora_fin AS turno_hora_fin,
        t.fid_medico AS turno_id_medico,
        t.fid_consultorio AS turno_id_consultorio

    FROM disponibilidad d
    INNER JOIN turno t ON d.fid_turno_medico = t.id_turno
    WHERE d.disponible = 1;
END$
-- - - Eliminar

-- Procedure: TurnoMedico
-- - - Insertar 
DELIMITER $
CREATE PROCEDURE INSERTAR_TURNO_MEDICO(
	OUT _id_turno INT,
    IN _dia ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO'),
    IN _hora_inicio TIME,
    IN _hora_fin TIME,
    IN _fid_consultorio INT,
    IN _fid_medico INT
)
BEGIN
	INSERT INTO turno(dia,hora_inicio,hora_fin,fid_consultorio,fid_medico)
    VALUES (_dia,_hora_inicio,_hora_fin,_fid_consultorio,_fid_medico);
    SET _id_turno = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_TURNO_FECHA_HORA(
	IN _id_turno INT,
    IN _dia ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO'),
    IN _hora_inicio TIME,
    IN _hora_fin TIME
)
BEGIN
	UPDATE 
		turno
    SET
		dia = _dia, hora_inicio = _hora_inicio, hora_fin = _hora_fin
    WHERE
		id_turno = _id_turno;
END$
-- - - Obtener
DELIMITER $
CREATE PROCEDURE OBTENER_TURNO_MEDICO_X_ID(
    IN _id_turno INT
)
BEGIN
    SELECT id_turno, dia, hora_inicio, hora_fin, fid_consultorio, fid_medico
    FROM turno WHERE id_turno = _id_turno;
END$
-- - - Listar
CREATE PROCEDURE LISTAR_TURNOS_TODOS()
BEGIN
	SELECT 
        t.id_turno,
        t.dia,
        t.hora_inicio,
        t.hora_fin,
        t.fid_consultorio AS id_consultorio,
        t.fid_medico AS id_medico
    FROM turno t;
END$





-- Procedure: Especialidad
-- - - Insertar 
DELIMITER $
CREATE PROCEDURE INSERTAR_ESPECIALIDAD(
    OUT _id_especialidad INT,
    IN _nombre VARCHAR(100),
    IN _descripcion VARCHAR(100)
)
BEGIN
    INSERT INTO especialidad(nombre, descripcion, activa)
    VALUES(_nombre, _descripcion, 1);
    SET _id_especialidad = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_ESPECIALIDAD(
    IN _id_especialidad INT,
    IN _nombre VARCHAR(100),
    IN _descripcion VARCHAR(100)
)
BEGIN
    UPDATE especialidad SET nombre = _nombre, descripcion = _descripcion
    WHERE id_especialidad = _id_especialidad;
END$
-- - - Obtener

DELIMITER $
CREATE PROCEDURE OBTENER_ESPECIALIDAD_X_ID(
    IN _id_especialidad INT
)
BEGIN
    SELECT id_especialidad, nombre, descripcion, activa
    FROM especialidad WHERE id_especialidad = _id_especialidad;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_ESPECIALIDADES_TODAS()
BEGIN
    SELECT id_especialidad, nombre, descripcion
    FROM especialidad WHERE activa = 1;
END$
-- - -Eliminar
DELIMITER $
CREATE PROCEDURE ELIMINAR_ESPECIALIDAD(
    IN _id_especialidad INT
)
BEGIN
    UPDATE especialidad SET activa = 0 WHERE id_especialidad = _id_especialidad;
END$