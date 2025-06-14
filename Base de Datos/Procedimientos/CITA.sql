-- -- Eliminar Procedimientos de Cita
DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_CITA$ 
DROP PROCEDURE IF EXISTS MODIFICAR_CITA$ 
DROP PROCEDURE IF EXISTS OBTENER_CITA_X_ID$ 
DROP PROCEDURE IF EXISTS LISTAR_CITA_TODOS$ 
DROP PROCEDURE IF EXISTS ELIMINAR_CITA$

-- Eliminar Procedimientos de horario trabajo
DROP PROCEDURE IF EXISTS INSERTAR_HORARIO$
DROP PROCEDURE IF EXISTS MODIFICAR_HORARIO$
DROP PROCEDURE IF EXISTS OBTENER_HORARIO_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_HORARIO_TODOS$
DROP PROCEDURE IF EXISTS ELIMINAR_HORARIO$

-- Eliminar Procedimientos de Turno
DROP PROCEDURE IF EXISTS INSERTAR_TURNO$
DROP PROCEDURE IF EXISTS MODIFICAR_TURNO$
DROP PROCEDURE IF EXISTS OBTENER_TURNO_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_TURNO_TODOS$

-- Eliminar Procedimientos de Especialidad
DROP PROCEDURE IF EXISTS INSERTAR_ESPECIALIDAD$
DROP PROCEDURE IF EXISTS MODIFICAR_ESPECIALIDAD$
DROP PROCEDURE IF EXISTS OBTENER_ESPECIALIDAD_X_ID$
DROP PROCEDURE IF EXISTS LISTAR_ESPECIALIDAD_TODOS$
DROP PROCEDURE IF EXISTS ELIMINAR_ESPECIALIDAD$

-- Procedure: Cita
-- - - Insertar 
DELIMITER $
CREATE PROCEDURE INSERTAR_CITA(
	OUT _id_cita INT,
    in _fecha date,
    IN _estado_cita ENUM('PROGRAMADA','CANCELADA','ATENDIDA'),
    in _estado_atencion ENUM('PENDIENTE','CITADO','EN ESPERA','EN CONSULTORIO'),
    IN _motivo_consulta VARCHAR(100),
    IN _fid_paciente INT,
    IN _fid_horario INT
)
BEGIN
	INSERT INTO cita( fecha,estado_cita,estado_atencion, motivo_consulta,fid_paciente,fid_horario, activo)
    VALUES(_fecha, _estado_cita,_estado_atencion, _motivo_consulta,_fid_paciente,_fid_horario, 1);
    SET _id_cita = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_CITA(
    IN _id_cita INT,
    IN _estado_cita ENUM('PROGRAMADA','CANCELADA','ATENDIDA'),
    in _estado_atencion ENUM('PENDIENTE','CITADO','EN ESPERA','EN CONSULTORIO'),
    IN _motivo_consulta VARCHAR(50)
)
BEGIN
    UPDATE cita SET estado_cita = _estado_cita, estado_atencion = _estado_atencion, 
    motivo_consulta = _motivo_consulta WHERE id_cita = _id_cita;
END$
-- - - Obtener
DELIMITER $
CREATE PROCEDURE OBTENER_CITA_X_ID(
    IN _id_cita INT
)
BEGIN
    SELECT c.id_cita, c.fecha, c.estado_cita, c.estado_atencion, c.motivo_consulta, 
    c.fid_paciente, u.nombre, u.apellido_paterno, u.apellido_paterno, u.dni, u.fecha_nacimiento,
    u.email, u.genero, p.direccion, p.telefono, h.id_horario_trabajo 
    FROM cita c inner join paciente p on c.fid_paciente = p.id_paciente inner join usuario u on u.id_usuario = p.id_paciente inner join 
    horario_trabajo h on c.fid_horario_trabajo = h.id_horario_trabajo where c.id_cita = c.id_cita;
END$

-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_CITA_TODOS()
BEGIN
    SELECT c.id_cita, c.fecha, c.estado_cita, c.estado_atencion, c.motivo_consulta, 
    c.fid_paciente, u.nombre, u.apellido_paterno, u.apellido_paterno, u.dni, u.fecha_nacimiento,
    u.email, u.genero, p.direccion, p.telefono, h.id_horario_trabajo 
    FROM cita c inner join paciente p on c.fid_paciente = p.id_paciente inner join usuario u on u.id_usuario = p.id_paciente inner join 
    horario_trabajo h on c.fid_horario_trabajo = h.id_horario_trabajo where c.activo = 1;
END$
-- - - Eliminar
DELIMITER $
CREATE PROCEDURE ELIMINAR_CITA(
    IN _id_cita INT
)
BEGIN
    UPDATE cita SET estado_cita = 'CANCELADA' WHERE id_cita = _id_cita;
END$


-- Procedure: horario
-- - - Insertar 
DELIMITER $

CREATE PROCEDURE INSERTAR_HORARIO(
	OUT _id_horario_trabajo INT,
    IN _fid_medico int,
    IN _fid_turno int
)
BEGIN
	INSERT INTO horario_trabajo(fid_medico,fid_turno, activo)
    VALUES (_fid_medico,_fid_turno, 1);
    SET _id_horario_trabajo = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_HORARIO(
	IN _id_horario_trabajo INT,
    IN _fid_medico int,
    IN _fid_turno int
)
BEGIN
	UPDATE turno 
    SET fid_medico = _fid_medico, fid_turno = _fid_turno
    WHERE id_horario_trabajo = _id_horario_trabajo;
END$
-- - - obtener
DELIMITER $
CREATE PROCEDURE OBTENER_HORARIO_X_ID(
    IN _id_horario INT
)
BEGIN
    SELECT h.id_horario_trabajo, h.fid_turno, t.dia, t.hora_fin, t.hora_fin ,h.fid_medico 
    FROM horario_trabajo h inner join turno t on h.fid_turno = t.id_turno WHERE h.id_horario = _id_horario;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_HORARIO_TODOS()
BEGIN
	SELECT h.id_horario_trabajo, h.fid_turno, t.dia, t.hora_fin, t.hora_fin ,h.fid_medico 
    FROM horario_trabajo h inner join turno t on h.fid_turno = t.id_turno WHERE h.activo = 1;
END$
-- - - Eliminar
create procedure ELIMINAR_HORARIO(
	in _id_horario_trabajo int
)
begin
	update horario_trabajo set activo = 0 where id_horario_trabajo = _id_horario_trabajo; 
end$
    
-- Procedure: Turno
-- - - Insertar 
DELIMITER $
CREATE PROCEDURE INSERTAR_TURNO(
	OUT _id_turno INT,
    IN _dia ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO'),
    IN _hora_inicio TIME,
    IN _hora_fin TIME
)
BEGIN
	INSERT INTO turno(dia,hora_inicio,hora_fin)
    VALUES (_dia,_hora_inicio,_hora_fin);
    SET _id_turno = @@last_insert_id;
END$
-- - - Modificar
DELIMITER $
CREATE PROCEDURE MODIFICAR_TURNO(
	IN _id_turno INT,
    IN _dia ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO'),
    IN _hora_inicio TIME,
    IN _hora_fin TIME
)
BEGIN
	UPDATE turno
    SET dia = _dia, hora_inicio = _hora_inicio, hora_fin = _hora_fin
    WHERE id_turno = _id_turno;
END$
-- - - Obtener
DELIMITER $
CREATE PROCEDURE OBTENER_TURNO_X_ID(
    IN _id_turno INT
)
BEGIN
    SELECT id_turno, dia, hora_inicio, hora_fin
    FROM turno WHERE id_turno = _id_turno;
END$
-- - - Listar
CREATE PROCEDURE LISTAR_TURNO_TODOS()
BEGIN
	SELECT 
        t.id_turno,
        t.dia,
        t.hora_inicio,
        t.hora_fin
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
    SELECT id_especialidad, nombre, descripcion, activo
    FROM especialidad WHERE id_especialidad = _id_especialidad;
END$
-- - - Listar
DELIMITER $
CREATE PROCEDURE LISTAR_ESPECIALIDAD_TODOS()
BEGIN
    SELECT id_especialidad, nombre, descripcion
    FROM especialidad WHERE activo = 1;
END$
-- - -Eliminar
DELIMITER $
CREATE PROCEDURE ELIMINAR_ESPECIALIDAD(
    IN _id_especialidad INT
)
BEGIN
    UPDATE especialidad SET activo = 0 WHERE id_especialidad = _id_especialidad;
END$