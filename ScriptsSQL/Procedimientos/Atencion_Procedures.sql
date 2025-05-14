DROP PROCEDURE IF EXISTS INSERTAR_RECETA;
DROP PROCEDURE IF EXISTS MODIFICAR_RECETA;
DROP PROCEDURE IF EXISTS LISTAR_SOLO_RECETAS;
DROP PROCEDURE IF EXISTS LISTAR_DETALLE_RECETA;
DROP PROCEDURE IF EXISTS INSERTAR_MEDICAMENTO;
DROP PROCEDURE IF EXISTS MODIFICAR_MEDICAMENTO;
DROP PROCEDURE IF EXISTS LISTAR_MEDICAMENTOS_TODOS;
DROP PROCEDURE IF EXISTS INSERTAR_HISTORIAL_MEDICO;
DROP PROCEDURE IF EXISTS LISTAR_HISTORIALES_TODOS;
DROP PROCEDURE IF EXISTS ELIMINAR_HISTORIAL;
DROP PROCEDURE IF EXISTS INSERTAR_TRIAJE;
DROP PROCEDURE IF EXISTS MODIFICAR_TRIAJE;
DROP PROCEDURE IF EXISTS LISTAR_TRIAJES_TODOS;
DROP PROCEDURE IF EXISTS ELIMINAR_TRIAJE;
DROP PROCEDURE IF EXISTS INSERTAR_NOTA_CLINICA;
DROP PROCEDURE IF EXISTS MODIFICAR_NOTA_CLINICA;
DROP PROCEDURE IF EXISTS LISTAR_NOTAS_CLINICAS_TODAS;

DELIMITER $
-- Procedimientos para Receta
CREATE PROCEDURE INSERTAR_RECETA(
	OUT _id_receta INT,
    IN _indicaciones VARCHAR(100)
)
BEGIN
	INSERT INTO receta(fecha_prescripcion,indicaciones)
    VALUES (SYSDATE(),_indicaciones);
    SET _id_receta = @@last_insert_id;
END$
CREATE PROCEDURE MODIFICAR_RECETA(
	IN _id_receta INT,
	IN _indicaciones VARCHAR(100)
)
BEGIN
	UPDATE receta SET indicaciones = _indicaciones
    WHERE id_receta = _id_receta;
END$
CREATE PROCEDURE LISTAR_SOLO_RECETAS()
BEGIN
    -- Consultar solo las recetas
    SELECT id_receta, fecha_prescripcion, indicaciones
    FROM receta;
END$
CREATE PROCEDURE LISTAR_DETALLE_RECETA()
BEGIN
    -- Consultar las recetas y los medicamentos asociados
    SELECT r.id_receta, r.fecha_prescripcion, r.indicaciones,
       rm.id_medicamento, m.nombre AS medicamento, rm.cantidad
	FROM receta r
	INNER JOIN receta_medicamento rm ON r.id_receta = rm.id_receta
	INNER JOIN medicamento m ON rm.id_medicamento = m.id_medicamento;
END$

-- Procedimientos para Medicamento
CREATE PROCEDURE INSERTAR_MEDICAMENTO(
	OUT _id_medicamento INT,
    IN _nombre VARCHAR(100),
    IN _presentacion VARCHAR(100),
    IN _stock INT
)
BEGIN
	INSERT INTO medicamento(nombre,presentacion,stock)
    VALUES (_nombre, _presentacion, _stock);
    SET _id_medicamento = @@last_insert_id;
END$
CREATE PROCEDURE MODIFICAR_MEDICAMENTO(
	IN _id_medicamento INT,
    IN _nombre VARCHAR(100),
    IN _presentacion VARCHAR(100),
    IN _stock INT
)
BEGIN
	UPDATE medicamento
    SET nombre = nombre,
		presentacion = _presentacion,
        stock = _stock
	WHERE id_medicamento = _id_medicamento;
END$
CREATE PROCEDURE LISTAR_MEDICAMENTOS_TODOS()
BEGIN
	SELECT
		id_medicamento,
		nombre,
		presentacion,
		stock
    FROM medicamento;
END$

-- Procedimientos para Historial Medico
CREATE PROCEDURE INSERTAR_HISTORIAL_MEDICO(
	OUT _id_historial INT,
    IN _fid_paciente INT
)
BEGIN
	INSERT INTO historial_medico(fecha_actualizacion,activo,fid_paciente)
    VALUES(SYSDATE(),1,_fid_paciente);
    SET _id_historial = @@last_insert_id;
END$
CREATE PROCEDURE LISTAR_HISTORIALES_TODOS()
BEGIN
	SELECT
    h.id_historial, h.fecha_actualizacion, p.id_paciente, 
    u.nombre, u.apellido_paterno, u.apellido_materno, u.dni
    FROM historial_medico h
    INNER JOIN paciente p ON h.fid_paciente = p.id_paciente
    INNER JOIN usuario u ON p.id_paciente = u.id_usuario
    WHERE h.activo = 1;
END$
CREATE PROCEDURE ELIMINAR_HISTORIAL(
	IN _id_historial INT
)
BEGIN
	UPDATE historial_medico
    SET activo = 0
    WHERE id_historial = _id_historial;
END$

-- Procedimientos para Triaje
CREATE PROCEDURE INSERTAR_TRIAJE(
	OUT _id_triaje INT,
    IN _peso DECIMAL(10,2),
    IN _altura DECIMAL(10,2),
    IN _presion_arterial VARCHAR(20),
    IN _temperatura DECIMAL(10,2)
)
BEGIN
	INSERT INTO triaje(peso,altura,presion_arterial,temperatura,fecha_registro,activo)
    VALUES (_peso,_altura,_presion_arterial,_temperatura,SYSDATE(),1);
    SET _id_triaje = @@last_insert_id;
END$
CREATE PROCEDURE MODIFICAR_TRIAJE(
	IN _id_triaje INT,
    IN _peso DECIMAL(10,2),
    IN _altura DECIMAL(10,2),
    IN _presion_arterial VARCHAR(20),
    IN _temperatura DECIMAL(10,2)
)
BEGIN
	UPDATE triaje
    SET peso = _peso, altura=_altura, presion_arterial=_presion_arterial,
    temperatura=_temperatura, fecha_actualizacion = SYSDATE()
    WHERE id_triaje=_id_triaje;
END$
CREATE PROCEDURE LISTAR_TRIAJES_TODOS()
BEGIN
	SELECT 
        id_triaje, 
        peso, 
        altura, 
        presion_arterial, 
        temperatura, 
        fecha_registro
    FROM 
        triaje
	WHERE activo = 1;
END$
CREATE PROCEDURE ELIMINAR_TRIAJE(
	IN _id_triaje INT
)
BEGIN
	UPDATE triaje
    SET activo = 0
    WHERE id_triaje=_id_triaje;
END$

-- Procedimientos para Nota Clinica
CREATE PROCEDURE INSERTAR_NOTA_CLINICA(
	OUT _id_nota INT,
    IN _descripcion VARCHAR(120),
    IN _diagnostico VARCHAR(120),
    IN _observaciones VARCHAR(120),
    IN _fid_cita INT,
    IN _fid_triaje INT,
    IN _fid_receta INT,
    IN _fid_historial INT
)
BEGIN
	INSERT INTO nota_clinica(descripcion,diagnostico,observaciones,fid_cita,fid_triaje,fid_receta,fid_historial)
    VALUES(_descripcion,_diagnostico,_observaciones,_fid_cita,_fid_triaje,_fid_receta,_fid_historial);
    SET _id_nota = @@last_insert_id;
END$
CREATE PROCEDURE MODIFICAR_NOTA_CLINICA(
	IN _id_nota INT,
    IN _descripcion VARCHAR(120),
    IN _diagnostico VARCHAR(120),
    IN _observaciones VARCHAR(120)
)
BEGIN
	UPDATE nota_clinica
    SET descripcion = _descripcion,
    diagnostico = _diagnostico,
    observaciones = _observaciones
    WHERE id_nota = _id_nota;
END$
CREATE PROCEDURE LISTAR_NOTAS_CLINICAS_TODAS()
BEGIN
	SELECT 
        nc.id_nota, 
        nc.descripcion, 
        nc.diagnostico, 
        nc.observaciones, 
        nc.fid_cita AS id_cita, 
        nc.fid_triaje AS id_triaje, 
        nc.fid_receta AS id_receta, 
        nc.fid_historial AS id_historial
    FROM 
        nota_clinica nc;
END$
