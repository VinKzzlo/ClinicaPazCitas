-- INSERTAR
DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_PAGO$
DROP PROCEDURE IF EXISTS INSERTAR_SEGURO$


DROP PROCEDURE IF EXISTS MODIFICAR_PAGO$
DROP PROCEDURE IF EXISTS MODIFICAR_SEGURO$
DROP PROCEDURE IF EXISTS ELIMINAR_PAGO$
DROP PROCEDURE IF EXISTS ELIMINAR_SEGURO$
DROP PROCEDURE IF EXISTS LISTAR_TODOS_PAGO$
DROP PROCEDURE IF EXISTS LISTAR_TODOS_SEGURO$




-- insertar
DELIMITER $
CREATE PROCEDURE INSERTAR_PAGO(
	OUT _id_pago INT,
	IN _fecha_pago datetime,
	IN _monto_total decimal(10,2),
	IN _monto_cubierto_seguro decimal(10,2),
    IN _subtotal decimal(10,2),
	in estado ENUM('PENDIENTE','CANCELADO','VENCIDO'),
	IN _fid_seguro INT,
    in _fid_cita int
    
) 
BEGIN
	INSERT INTO pago(fecha_pago,monto_total,monto_cubierto_seguro,subtotal,estado,fid_seguro,fid_cita)
	VALUES (_fecha_pago,_monto_total,_monto_cubierto_seguro,_subtotal,_estado,_fid_seguro_fid_cita);
	SET _id_pago=@@last_insert_id;
END$



DELIMITER $
select *from seguro;
$
CREATE PROCEDURE INSERTAR_SEGURO(
	OUT _id_seguro INT,
	IN _nombre_seguro VARCHAR(100),
	IN _tipo ENUM('TOTAL','PARCIAL','NINGUNO'),
	IN _vigencia date,
	IN _porcentaje_cobertura decimal(10,2)
)
BEGIN
	INSERT INTO seguro(nombre_seguro,tipo,porcentaje_cobertura,vigencia,activo)
	VALUES (_nombre_seguro,_tipo,_porcentaje_cobertura,_vigencia, 1);
	SET _id_seguro = @@last_insert_id;
END$
------------------------------------------------------------------------

-- MODIFICAR

------------------------------------------------------------------------
DELIMITER $
CREATE PROCEDURE MODIFICAR_PAGO(
	IN _id_pago INT,
	IN _fecha_pago datetime,
	IN _monto_total decimal(10,2),
	IN _monto_cubierto_seguro decimal(10,2),
	IN _subtotal decimal(10,2),
	in estado ENUM('PENDIENTE','CANCELADO','VENCIDO')
)
BEGIN
	UPDATE pago 
	SET fecha_pago=_fecha_pago,monto_total=_monto_total,monto_cubierto_seguro=_monto_cubierto_seguro,subtotal=_subtotal,estado=_estado 
	WHERE id_pago = _id_pago;
END$

DELIMITER $
CREATE PROCEDURE MODIFICAR_SEGURO(
	IN _id_seguro INT,
	IN _nombre_seguro VARCHAR(100),
	IN _tipo ENUM('TOTAL','PARCIAL','NINGUNO'),
	IN _porcentaje_cobertura DOUBLE	
)
BEGIN
	UPDATE seguro 
	SET nombre_seguro=_nombre_seguro,tipo=_tipo,porcentaje_cobertura=_porcentaje_cobertura 
	WHERE id_seguro = _id_seguro;
END$
------------------------------------------------------------------------


-- ELIMINAR

------------------------------------------------------------------------
DELIMITER $
CREATE PROCEDURE ELIMINAR_PAGO(
	IN _id_pago INT
)
BEGIN
	UPDATE pago SET activo = 0 WHERE id_pago = _id_pago;
END$


DELIMITER $
CREATE PROCEDURE ELIMINAR_SEGURO(
	IN _id_seguro INT
)
BEGIN
	UPDATE seguro SET activo = 0 WHERE id_seguro = _id_seguro;
END$
------------------------------------------------------------------------


--listar TODOS
------------------------------------------------------------------------
DELIMITER $
CREATE PROCEDURE LISTAR_TODOS_PAGO()
BEGIN
	SELECT id_pago,fecha_pago,monto_total,monto_cubierto_seguro,subtotal,estado,fid_seguro,fid_cita
	FROM pago INNER JOIN seguro s ON p.fid_seguro = s.id_seguro
	WHERE s.activo=1;
END$


DELIMITER $
CREATE PROCEDURE LISTAR_TODOS_SEGURO()
BEGIN
	SELECT s.id_seguro,s.nombre_seguro,s.tipo,s.porcentaje_cobertura,s.activo
	FROM seguro s
	WHERE s.activo=1;
END$
