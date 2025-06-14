DROP SCHEMA IF EXISTS PAZCITAS;
CREATE SCHEMA IF NOT EXISTS PAZCITAS;
USE PAZCITAS;

DROP TABLE IF EXISTS pago;
DROP TABLE IF EXISTS nota_clinica;
DROP TABLE IF EXISTS receta_medicamento;
DROP TABLE IF EXISTS medicamento;
DROP TABLE IF EXISTS receta;
DROP TABLE IF EXISTS historial_medico;
DROP TABLE IF EXISTS cita;
DROP TABLE IF EXISTS horario_trabajo;
DROP TABLE IF EXISTS turno;
DROP TABLE IF EXISTS cuenta_usuario;
DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS medico;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS seguro;
DROP TABLE IF EXISTS consultorio;
DROP TABLE IF EXISTS sede_especialidad;
DROP TABLE IF EXISTS especialidad;
DROP TABLE IF EXISTS sede;


-- Tabla: Sede
CREATE TABLE sede (
    id_sede INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    activo TINYINT NOT NULL
)ENGINE=InnoDB;

-- Tabla: Consultorio
CREATE TABLE consultorio (
    id_consultorio INT PRIMARY KEY AUTO_INCREMENT,
    nombre_consultorio VARCHAR(20) NOT NULL,
    piso INT,
    capacidad INT,
    asignado TINYINT,
    activo TINYINT,
    fid_sede INT,
    FOREIGN KEY (fid_sede) REFERENCES sede(id_sede)
)ENGINE=InnoDB;

-- Tabla : Especialidad
CREATE TABLE especialidad (
    id_especialidad INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100),
    activo TINYINT NOT NULL
)ENGINE=InnoDB;

-- Relacion de * - * entre Sede y Especialidad
CREATE TABLE sede_especialidad (
	id_sede_especialidad INT AUTO_INCREMENT,
    fid_sede INT,
    fid_especialidad INT,
    activo TINYINT,
    PRIMARY KEY (id_sede_especialidad),
    FOREIGN KEY (fid_sede) REFERENCES sede(id_sede),
    FOREIGN KEY (fid_especialidad) REFERENCES especialidad(id_especialidad)
)ENGINE=InnoDB;

-- Tabla: Seguro
CREATE TABLE seguro (
    id_seguro INT PRIMARY KEY AUTO_INCREMENT,
    nombre_seguro VARCHAR(100) NOT NULL,
    tipo ENUM('NINGUNO','PARCIAL','TOTAL'),
    porcentaje_cobertura DECIMAL(10,2) NOT NULL, 
    vigencia DATE,
    activo TINYINT
)ENGINE=InnoDB;

-- Tabla base: Usuario
CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50),
    dni VARCHAR(8) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    genero CHAR NOT NULL
)ENGINE=InnoDB;


-- Paciente hereda de Usuario
CREATE TABLE paciente (
    id_paciente INT PRIMARY KEY,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    activo TINYINT,
    fid_seguro INT,
    FOREIGN KEY (id_paciente) REFERENCES usuario(id_usuario),
    FOREIGN KEY (fid_seguro) REFERENCES seguro(id_Seguro)
)ENGINE=InnoDB;

-- Medico hereda de Usuario
CREATE TABLE medico (
    id_medico INT PRIMARY KEY,
    codigo_medico VARCHAR(20) NOT NULL UNIQUE,
    activo TINYINT,
    fid_sede INT,
    fid_consultorio INT,
    fid_especialidad INT,
    FOREIGN KEY (id_medico) REFERENCES usuario(id_usuario),
    FOREIGN KEY (fid_sede) REFERENCES sede(id_sede),
    FOREIGN KEY (fid_consultorio) REFERENCES consultorio(id_consultorio),
    FOREIGN KEY (fid_especialidad) REFERENCES especialidad(id_especialidad)
)ENGINE=InnoDB;

-- Administrador hereda de Usuario
CREATE TABLE administrador (
    id_administrador INT PRIMARY KEY,
    activo TINYINT,
    FOREIGN KEY (id_administrador) REFERENCES usuario(id_usuario)
)ENGINE=InnoDB;

-- Tabla: Cuenta Usuario
CREATE TABLE cuenta_usuario (
    id_cuenta INT PRIMARY KEY AUTO_INCREMENT,
    fid_usuario INT UNIQUE,  -- Referencia a la tabla `usuario`
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    rol ENUM('PACIENTE', 'MÉDICO', 'ADMINISTRADOR') NOT NULL,
    activo TINYINT,
    FOREIGN KEY (fid_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla: Turno
CREATE TABLE turno (
    id_turno INT PRIMARY KEY AUTO_INCREMENT,
    dia ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO'),
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    activo TINYINT
)ENGINE=InnoDB;

-- Tabla: HorarioTrabajo
CREATE TABLE horario_trabajo(
    id_horario_trabajo INT PRIMARY KEY AUTO_INCREMENT,
    fid_medico INT,
    fid_turno INT,
    activo TINYINT,
    FOREIGN KEY (fid_medico) REFERENCES medico(id_medico),
    FOREIGN KEY (fid_turno) REFERENCES turno(id_turno)
)ENGINE=InnoDB;

-- Tabla: Cita
CREATE TABLE cita (
    id_cita INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    estado_cita ENUM('PROGRAMADA','CANCELADA','ATENDIDA'),
    estado_atencion ENUM('PENDIENTE','CITADO','EN ESPERA','EN CONSULTORIO'),
    motivo_consulta VARCHAR(100),
    fid_paciente INT,
    fid_horario_trabajo INT,
    activo TINYINT,
    FOREIGN KEY (fid_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (fid_horario_trabajo) REFERENCES horario_trabajo(id_horario_trabajo)
)ENGINE=InnoDB;

-- Tabla: HistorialMedico
CREATE TABLE historial_medico (
    id_historial INT PRIMARY KEY AUTO_INCREMENT,
    fecha_actualizacion DATETIME,
    activo TINYINT,
    fid_paciente INT,
    FOREIGN KEY (fid_paciente) REFERENCES paciente(id_paciente)
)ENGINE=InnoDB;

-- Tabla: Receta
CREATE TABLE receta (
    id_receta INT PRIMARY KEY AUTO_INCREMENT,
    fecha_prescripcion DATE,
    indicaciones VARCHAR(100),
    activo TINYINT
)ENGINE=InnoDB;

-- Tabla: Medicamento
CREATE TABLE medicamento (
    id_medicamento INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    presentacion VARCHAR(100) NOT NULL,
    stock INT NOT NULL,
    activo TINYINT
)ENGINE=InnoDB;

-- Relacion de * - * entre Receta y Medicamento
CREATE TABLE receta_medicamento (	
	id_receta_medicamento INT PRIMARY KEY AUTO_INCREMENT,
    fid_receta INT,
    fid_medicamento INT,
    cantidad INT,
    activo TINYINT,
    FOREIGN KEY (fid_receta) REFERENCES receta(id_receta),
    FOREIGN KEY (fid_medicamento) REFERENCES medicamento(id_medicamento)
)ENGINE=InnoDB;

-- Tabla: NotaClinica
CREATE TABLE nota_clinica (
    id_nota INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(120),
    diagnostico VARCHAR(120),
    observaciones VARCHAR(120),
    fid_cita INT,
    fid_receta INT,
    fid_historial INT,
    activo TINYINT,
    FOREIGN KEY (fid_cita) REFERENCES cita(id_cita),
    FOREIGN KEY (fid_receta) REFERENCES receta(id_receta),
    FOREIGN KEY (fid_historial) REFERENCES historial_medico(id_historial)
)ENGINE=InnoDB;

-- Tabla: Pago
CREATE TABLE pago (
    id_pago INT PRIMARY KEY AUTO_INCREMENT,
    fecha_pago DATETIME NOT NULL,
    monto_total DECIMAL(10,2) NOT NULL,
    monto_cubierto_seguro DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2),
    estado ENUM('PENDIENTE','CANCELADO','VENCIDO'),
    fid_seguro INT,
    fid_cita INT,
    activo TINYINT,
    FOREIGN KEY (fid_seguro) REFERENCES seguro(id_seguro),
    FOREIGN KEY (fid_cita) REFERENCES cita(id_cita)
)ENGINE=InnoDB;