
DROP PROCEDURE IF EXISTS INSERTAR_MEDICO;
DROP PROCEDURE IF EXISTS INSERTAR_PACIENTE;
DROP PROCEDURE IF EXISTS INSERTAR_ADMINISTRADOR;
DROP PROCEDURE IF EXISTS INSERTAR_ASISTENTE_MEDICO;
DROP PROCEDURE IF EXISTS INSERTAR_RECEPCIONISTA;
DROP PROCEDURE IF EXISTS ELIMINAR_MEDICO;
DROP PROCEDURE IF EXISTS ELIMINAR_PACIENTE;
DROP PROCEDURE IF EXISTS  ELIMINAR_ADMINISTRADOR;
DROP PROCEDURE IF EXISTS  ELIMINAR_ASISTENTE_MEDICO;
DROP PROCEDURE IF EXISTS ELIMINAR_RECEPCIONISTA;
DROP PROCEDURE IF EXISTS LISTAR_MEDICO_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_PACIENTE_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_ADMINISTRADOR_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_ASISTENTE_MEDICO_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_RECEPCIONISTA_TODOS;

DELIMITER $
CREATE procedure INSERTAR_MEDICO(
	OUT _id_medico int,
    in _nombre varchar(75),
    in _apellido_paterno varchar(75),
    in _apellido_materno varchar(75),
    in _dni varchar(10),
    in _email varchar(30),
    in _fecha_nacimiento date,
    in _genero char,
    in _hash_password varchar(15),
    in _codigo_medico varchar(15),
    in _fid_sede int
)
BEGIN
	INSERT INTO usuario(nombre,apellido_paterno,apellido_materno, dni, email, fecha_nacimiento, genero,hash_password) 
    VALUES(_nombre,_apellido_paterno,_apellido_materno,_dni, _email, _fecha_nacimiento, _genero,hash_password);
    SET _id_medico = @@last_insert_id;
    INSERT INTO medico(id_medico,codigo_medico,activo,fid_sede) VALUES(_id_medico,_codigo_medico,1,_fid_sede);
END$
$
select *from cita;
$
CREATE PROCEDURE ELIMINAR_MEDICO(
	IN _id_medico INT
)
BEGIN
	UPDATE medico SET activo = 0 WHERE id_medico = _id_medico;
END$
CREATE PROCEDURE LISTAR_MEDICO_TODOS()
BEGIN
	SELECT u.id_usuario,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,u.hash_password,
    m.codigo_medico, s.id_sede, s.nombre as nombre_sede, s.direccion FROM medico m INNER JOIN usuario u ON m.id_medico = u.id_usuario INNER JOIN sede s ON s.id_sede = m.fid_sede WHERE m.activo = 1;
END$
/*PACIENTE*/
$
Select *from medico;
$
CREATE procedure INSERTAR_PACIENTE(
	OUT _id_paciente int,
    in _nombre varchar(75),
    in _apellido_paterno varchar(75),
    in _apellido_materno varchar(75),
    in _dni varchar(10),
    in _email varchar(30),
    in _fecha_nacimiento date,
    in _genero char,
    in _hash_password varchar(20),
    in _direccion varchar(30),
    in _telefono int,
    in _fid_seguro int
)
BEGIN
	INSERT INTO usuario(nombre,apellido_paterno,apellido_materno, dni, email, fecha_nacimiento, genero,hash_password) 
    VALUES(_nombre,_apellido_paterno,_apellido_materno,_dni, _email, _fecha_nacimiento, _genero,_hash_password);
    SET _id_paciente = @@last_insert_id;
    INSERT INTO paciente(id_paciente,direccion,telefono,activo,fid_seguro) VALUES(_id_paciente,_direccion,_telefono,1,_fid_seguro);
END$
select *from paciente;
$

CREATE PROCEDURE LISTAR_PACIENTE_TODOS()
BEGIN
	SELECT u.id_usuario,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,u.hash_password,
    p.direccion, p.telefono, seg.id_seguro, seg.nombre_seguro,
    seg.tipo, seg.porcentaje_cobertura FROM paciente p INNER JOIN usuario u ON p.id_paciente = u.id_usuario
	inner join seguro seg on seg.id_seguro = p.fid_seguro WHERE p.activo = 1;
END$
CREATE PROCEDURE ELIMINAR_PACIENTE(
	IN _id_paciente INT
)
BEGIN
	UPDATE paciente SET activo = 0 WHERE id_paciente = _id_paciente;
END$

/*ADMINISTRADOR*/
CREATE procedure INSERTAR_ADMINISTRADOR(

	OUT _id_administrador int,
    in _nombre varchar(75),
    in _apellido_paterno varchar(75),
    in _apellido_materno varchar(75),
    in _dni varchar(10),
    in _email varchar(30),
    in _fecha_nacimiento date,
    in _genero char,
    in _hash_password varchar(15),
    in _fid_sede int
)
BEGIN
	INSERT INTO usuario(nombre,apellido_paterno,apellido_materno, dni, email, fecha_nacimiento, genero,hash_password) 
    VALUES(_nombre,_apellido_paterno,_apellido_materno,_dni, _email, _fecha_nacimiento, _genero,__hash_password);
    SET _id_administrador = @@last_insert_id;
    INSERT INTO administrador(id_administrador,activo,fid_sede) VALUES(_id_administrador,1,_fid_sede);
END$
$
cREATE PROCEDURE ELIMINAR_ADMINISTRADOR(
	IN _id_administrador INT
)
BEGIN
	UPDATE administrador SET activo = 0 WHERE id_administrador = _id_administrador;
END$
CREATE PROCEDURE LISTAR_ADMINISTRADOR_TODOS()
BEGIN
	SELECT u.id_usuario,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,u.hash_password,
	s.id_sede, s.nombre as nombre_sede, s.direccion FROM administrador a INNER JOIN usuario u ON a.id_administrador = u.id_usuario INNER JOIN sede s ON s.id_sede = a.fid_sede WHERE a.activo = 1;
END$

$
/*ASISTENTE MEDICO*/
CREATE procedure INSERTAR_ASISTENTE_MEDICO(
	OUT _id_asistente_medico int,
    in _nombre varchar(75),
    in _apellido_paterno varchar(75),
     in _apellido_materno varchar(75),
    in _dni varchar(10),
    in _email varchar(30),
    in _fecha_nacimiento date,
    in _genero char,
    in _hash_password varchar(15),
    in _codigo_personal varchar(15),
    in _fid_medico int,
    in _fid_sede int
)
BEGIN
	INSERT INTO usuario(nombre,apellido_paterno,apellido_materno, dni, email, fecha_nacimiento, genero,hash_password) 
    VALUES(_nombre,_apellido_paterno,_apellido_materno,_dni, _email, _fecha_nacimiento, _genero,__hash_password);
    SET _id_asistente_medico = @@last_insert_id;
    INSERT INTO asistente_medico(id_asistente_medico,codigo_personal,activo,fid_medico,fid_sede)
    VALUES(_id_asistente_medico,_codigo_personal,1,_fid_medico,_fid_sede);
END$

$
CREATE PROCEDURE LISTAR_ASISTENTE_MEDICO_TODOS()
BEGIN
	SELECT u.id_usuario,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,u.hash_password,
	a.codigo_personal,m.id_medico, m.codigo_medico, s.id_sede, s.nombre as nombre_sede, s.direccion FROM asistente_medico a INNER JOIN usuario u ON a.id_asistente_medico = u.id_usuario 
    INNER JOIN sede s ON s.id_sede = a.fid_sede inner join medico m on m.id_medico = a.fid_medico WHERE a.activo = 1;
END$
cREATE PROCEDURE ELIMINAR_ASISTENTE_MEDICO(
	IN _id_asistente_medico INT
)
BEGIN
	UPDATE asistente_medico SET activo = 0 WHERE id_asistente_medico = _id_asistente_medico;
END$

/*RECEPCIONISTA*/
CREATE procedure INSERTAR_RECEPCIONISTA(
	OUT _id_recepcionista int,
    in _nombre varchar(75),
    in _apellido_paterno varchar(75),
    in _apellido_materno varchar(75),
    in _dni varchar(10),
    in _email varchar(30),
    in _fecha_nacimiento date,
    in _genero char,
    in _hash_password varchar(15),
    in _fid_sede int
)
BEGIN
	INSERT INTO usuario(nombre,apellido_paterno,apellido_materno, dni, email, fecha_nacimiento, genero,hash_password) 
    VALUES(_nombre,_apellido_paterno,_apellido_materno,_dni, _email, _fecha_nacimiento, _genero,_hash_password);
    SET _id_recepcionista = @@last_insert_id;
    INSERT INTO recepcionista(id_recepcionista,activo,fid_sede) VALUES(_id_recepcionista,1,_fid_sede);
END$
CREATE PROCEDURE LISTAR_RECEPCIONISTA_TODOS()
BEGIN
	SELECT u.id_usuario ,u.nombre,
    u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,u.hash_password,
	s.id_sede, s.nombre as nombre_sede, s.direccion FROM recepcionista r INNER JOIN usuario u ON r.id_recepcionista = u.id_usuario INNER JOIN sede s ON s.id_sede = r.fid_sede WHERE r.activo = 1;
END$
cREATE PROCEDURE ELIMINAR_RECEPCIONISTA(
	IN _id_recepcionista INT
)
BEGIN
	UPDATE recepcionista SET activo = 0 WHERE id_recepcionista = _id_recepcionista;
END$
