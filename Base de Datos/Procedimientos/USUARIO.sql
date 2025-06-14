-- Procedimientos para el paquete usuario

DROP PROCEDURE IF EXISTS INSERTAR_MEDICO;
DROP PROCEDURE IF EXISTS INSERTAR_PACIENTE;
DROP PROCEDURE IF EXISTS INSERTAR_ADMINISTRADOR;
DROP PROCEDURE IF EXISTS INSERTAR_CUENTA;
DROP PROCEDURE IF EXISTS MODIFICAR_MEDICO;
DROP PROCEDURE IF EXISTS MODIFICAR_PACIENTE;
DROP PROCEDURE IF EXISTS MODIFICAR_ADMINISTRADOR;
DROP PROCEDURE IF EXISTS MODIFICAR_CUENTA;
DROP PROCEDURE IF EXISTS ELIMINAR_MEDICO;
DROP PROCEDURE IF EXISTS ELIMINAR_PACIENTE;
DROP PROCEDURE IF EXISTS ELIMINAR_ADMINISTRADOR;
DROP PROCEDURE IF EXISTS ELIMINAR_CUENTA;
DROP PROCEDURE IF EXISTS LISTAR_MEDICO_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_PACIENTE_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_ADMINISTRADOR_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_CUENTA_TODOS;
DROP PROCEDURE IF EXISTS VERIFICAR_CUENTA_PACIENTE;
DROP PROCEDURE IF EXISTS VERIFICAR_CUENTA_MEDICO;
DROP PROCEDURE IF EXISTS VERIFICAR_CUENTA_ADMINISTRADOR;
DROP PROCEDURE IF EXISTS OBTENER_MEDICO_X_ID;
DROP PROCEDURE IF EXISTS OBTENER_PACIENTE_X_ID;

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
    in _codigo_medico varchar(15),
    in _fid_sede int,
    in _fid_consultorio int,
    in _fid_especialidad int
)
BEGIN
	INSERT INTO usuario(nombre,apellido_paterno,apellido_materno, dni, email, fecha_nacimiento, genero) 
    VALUES(_nombre,_apellido_paterno,_apellido_materno,_dni, _email, _fecha_nacimiento, _genero);
    SET _id_medico = @@last_insert_id;
    INSERT INTO medico(id_medico,codigo_medico,activo,fid_sede, fid_consultorio, fid_especialidad) 
    VALUES(_id_medico,_codigo_medico,1,_fid_sede,_fid_consultorio,_fid_especialidad);
END$
CREATE procedure MODIFICAR_MEDICO(
	in _id_medico int,
    in _nombre varchar(75),
    in _apellido_paterno varchar(75),
    in _apellido_materno varchar(75),
    in _dni varchar(10),
    in _email varchar(30),
    in _fecha_nacimiento date,
    in _genero char,
    in _codigo_medico varchar(15),
    in _fid_sede int,
    in _fid_consultorio int,
    in _fid_especialidad int
)
BEGIN
	UPDATE usuario SET nombre = _nombre, apellido_paterno = _apellido_paterno, apellido_materno = _apellido_materno, dni = _dni,
    email = _email, fecha_nacimiento = _fecha_nacimiento, genero = _genero WHERE id_usuario = _id_medico;
    update medico set codigo_medico = _codigo_medico, fid_sede = _fid_sede,
    fid_consultorio = _fid_consultorio, fid_especialidad = _fid_especialidad WHERE id_medico = _id_medico;
END$

CREATE PROCEDURE ELIMINAR_MEDICO(
	IN _id_medico INT
)
BEGIN
	UPDATE medico SET activo = 0 WHERE id_medico = _id_medico;
END$

CREATE PROCEDURE LISTAR_MEDICO_TODOS()
BEGIN
	SELECT u.id_persona,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,
    m.codigo_medico, s.id_sede, s.nombre as nombre_sede, s.direccion, c.id_consultorio, c.nombre_consultorio,
    c.piso, c.capacidad, c.asignado FROM medico m 
    INNER JOIN usuario u ON m.id_medico = u.id_usuario INNER JOIN sede s ON s.id_sede = m.fid_sede INNER JOIN consultorio c on m.fid_consultorio = c.id_consultorio 
    inner join especialidad e on m.fid_especialidad = e.id_especialidad WHERE m.activo = 1;
END$
CREATE PROCEDURE OBTENER_MEDICO_X_ID(
	IN _id_medico int
)
BEGIN 
	SELECT u.id_persona,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,
    m.codigo_medico, s.id_sede, s.nombre as nombre_sede, s.direccion, c.id_consultorio, c.nombre_consultorio,
    c.piso, c.capacidad, c.asignado FROM medico m 
    INNER JOIN usuario u ON m.id_medico = u.id_usuario INNER JOIN sede s ON s.id_sede = m.fid_sede INNER JOIN consultorio c on m.fid_consultorio = c.id_consultorio 
    inner join especialidad e on m.fid_especialidad = e.id_especialidad WHERE m.id_medico = _id_medico;
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
    in _direccion varchar(30),
    in _telefono varchar(20),
    in _fid_seguro int
)
BEGIN
	INSERT INTO usuario(nombre,apellido_paterno,apellido_materno, dni, email, fecha_nacimiento, genero)
    VALUES(_nombre,_apellido_paterno,_apellido_materno,_dni, _email, _fecha_nacimiento, _genero);
    SET _id_paciente = @@last_insert_id;
    INSERT INTO paciente(id_paciente,direccion,telefono,activo,fid_seguro) 
    VALUES(_id_paciente,_direccion,_telefono,1,_fid_seguro);
END$
CREATE procedure MODIFICAR_PACIENTE(
	in _id_paciente int,
    in _nombre varchar(75),
    in _apellido_paterno varchar(75),
    in _apellido_materno varchar(75),
    in _dni varchar(10),
    in _email varchar(30),
    in _fecha_nacimiento date,
    in _genero char,
    in _direccion varchar(30),
    in _telefono varchar(20),
    in _fid_seguro int
)
BEGIN
	UPDATE usuario SET nombre = _nombre, apellido_paterno = _apellido_paterno, apellido_materno = _apellido_materno, dni = _dni,
    email = _email, fecha_nacimiento = _fecha_nacimiento, genero = _genero WHERE id_usuario = _id_paciente;
    update paciente set direccion = _direccion, telefono = _telefono, fid_seguro = _fid_seguro WHERE id_paciente = _id_paciente;
END$

CREATE PROCEDURE LISTAR_PACIENTE_TODOS()
BEGIN
	SELECT u.id_usuario,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,
    p.direccion, p.telefono, seg.id_seguro, seg.nombre_seguro,
    seg.tipo, seg.porcentaje_cobertura, seg.vigencia FROM paciente p INNER JOIN usuario u ON p.id_paciente = u.id_usuario
	inner join seguro seg on seg.id_seguro = p.fid_seguro WHERE p.activo = 1;
END$

CREATE PROCEDURE OBTENER_PACIENTE_X_ID(
	in _id_paciente int
)
BEGIN
	SELECT u.id_usuario,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero,
    p.direccion, p.telefono, seg.id_seguro, seg.nombre_seguro,
    seg.tipo, seg.porcentaje_cobertura, seg.vigencia FROM paciente p INNER JOIN usuario u ON p.id_paciente = u.id_usuario
	inner join seguro seg on seg.id_seguro = p.fid_seguro WHERE p.id_paciente = _id_paciente;
    
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
    in _genero char
)
BEGIN
	INSERT INTO usuario(nombre,apellido_paterno,apellido_materno, dni, email, fecha_nacimiento, genero) 
    VALUES(_nombre,_apellido_paterno,_apellido_materno,_dni, _email, _fecha_nacimiento, _genero);
    SET _id_administrador = @@last_insert_id;
    INSERT INTO administrador(id_administrador,activo) VALUES(_id_administrador,1);
END$
CREATE procedure MODIFICAR_ADMINISTRADOR(
	in _id_administrador int,
    in _nombre varchar(75),
    in _apellido_paterno varchar(75),
    in _apellido_materno varchar(75),
    in _dni varchar(10),
    in _email varchar(30),
    in _fecha_nacimiento date,
    in _genero char
)
BEGIN
	UPDATE usuario SET nombre = _nombre, apellido_paterno = _apellido_paterno, apellido_materno = _apellido_materno, dni = _dni,
    email = _email, fecha_nacimiento = _fecha_nacimiento, genero = _genero WHERE id_usuario = _id_administrador;
END$
cREATE PROCEDURE ELIMINAR_ADMINISTRADOR(
	IN _id_administrador INT
)
BEGIN
	UPDATE administrador SET activo = 0 WHERE id_administrador = _id_administrador;
END$
CREATE PROCEDURE LISTAR_ADMINISTRADOR_TODOS()
BEGIN
	SELECT u.id_usuario,u.nombre,u.apellido_paterno,u.apellido_materno, u.dni, u.email, u.fecha_nacimiento, u.genero
	FROM administrador a INNER JOIN usuario u ON a.id_administrador = u.id_usuario WHERE a.activo = 1;
END$

-- CUENTA
CREATE procedure INSERTAR_CUENTA(
	OUT _id_cuenta int,
    in _username varchar(75),
    in _password varchar(75),
    in _rol enum('A','P','M'),
    in _fid_usuario int
)
BEGIN
	INSERT INTO cuenta_usuario(username,password,rol, fid_usuario)
    VALUES(_username, MD5(_password), _rol, _fid_usuario);
    SET _id_cuenta = @@last_insert_id;
END$

CREATE PROCEDURE VERIFICAR_CUENTA_PACIENTE
(
	IN _username VARCHAR(30),
    IN _password VARCHAR(150)
)
BEGIN
	SELECT id_cuenta, fid_usuario, username, password 
    FROM cuenta_usuario c inner join paciente p on p.id_paciente = c.fid_usuario 
    where c.activo = 1 AND username = _username AND password = MD5(_password);
END$

CREATE PROCEDURE VERIFICAR_CUENTA_MEDICO
(
	IN _username VARCHAR(30),
    IN _password VARCHAR(150)
)
BEGIN
	SELECT id_cuenta, fid_usuario, username, password 
    FROM cuenta_usuario c inner join medico m on m.id_medico = c.fid_usuario 
    where c.activo = 1 AND username = _username AND password = MD5(_password);
END$
CREATE PROCEDURE VERIFICAR_CUENTA_ADMINISTRADOR
(
	IN _username VARCHAR(30),
    IN _password VARCHAR(150)
)
BEGIN
	SELECT id_cuenta, fid_usuario, username, password 
    FROM cuenta_usuario c inner join medico m on m.id_medico = c.fid_usuario 
    where c.activo = 1 AND username = _username AND password = MD5(_password);
END$
