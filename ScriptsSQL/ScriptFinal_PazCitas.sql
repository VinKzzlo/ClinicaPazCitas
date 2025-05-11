DROP TABLE IF EXISTS nota_clinica;
DROP TABLE IF EXISTS receta_medicamento;
DROP TABLE IF EXISTS medicamento;
DROP TABLE IF EXISTS receta;
DROP TABLE IF EXISTS historial_medico;
DROP TABLE IF EXISTS triaje;
DROP TABLE IF EXISTS pago;
DROP TABLE IF EXISTS cita;
DROP TABLE IF EXISTS disponibilidad;
DROP TABLE IF EXISTS turno;
DROP TABLE IF EXISTS medico_especialidad;
DROP TABLE IF EXISTS sede_consultorio;
DROP TABLE IF EXISTS asistente_medico;
DROP TABLE IF EXISTS medico;
DROP TABLE IF EXISTS recepcionista;
DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS paciente;
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
    activa TINYINT NOT NULL
)ENGINE=InnoDB;

-- Tabla : Especialidad
CREATE TABLE especialidad (
    id_especialidad INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100),
    activa TINYINT NOT NULL
)ENGINE=InnoDB;

-- Relacion de * - * entre Sede y Especialidad
CREATE TABLE sede_especialidad (
    fid_sede INT,
    fid_especialidad INT,
    PRIMARY KEY(fid_sede,fid_especialidad),
    FOREIGN KEY (fid_sede) REFERENCES sede(id_sede),
    FOREIGN KEY (fid_especialidad) REFERENCES especialidad(id_especialidad)
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
    genero CHAR NOT NULL,
    hash_password VARCHAR(100) NOT NULL
)ENGINE=InnoDB;

-- Tabla: Seguro
CREATE TABLE seguro (
    id_seguro INT PRIMARY KEY,
    nombre_seguro VARCHAR(100) NOT NULL,
    tipo ENUM('NINGUNO','PARCIAL','TOTAL'),
    porcentaje_cobertura DECIMAL(10,2) NOT NULL, 
    vigencia DATE,
    activo TINYINT
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
    FOREIGN KEY (id_medico) REFERENCES usuario(id_usuario),
    FOREIGN KEY (fid_sede) REFERENCES sede(id_sede)
)ENGINE=InnoDB;

-- Administrador hereda de Usuario
CREATE TABLE administrador (
    id_administrador INT PRIMARY KEY,
    activo TINYINT,
    fid_sede INT,
    FOREIGN KEY(fid_sede) REFERENCES sede(id_sede),
    FOREIGN KEY (id_administrador) REFERENCES usuario(id_usuario)
)ENGINE=InnoDB;

-- Asistente Médico hereda de Usuario
CREATE TABLE asistente_medico (
    id_asistente_medico INT PRIMARY KEY,
    codigo_personal VARCHAR(20),
    activo TINYINT,
    fid_medico INT,
    fid_sede INT,
    FOREIGN KEY (id_asistente_medico) REFERENCES usuario(id_usuario),
    FOREIGN KEY (fid_medico) REFERENCES medico(id_medico),
    FOREIGN KEY(fid_sede) REFERENCES sede(id_sede)
)ENGINE=InnoDB;

-- Recepcionista hereda de Usuario
CREATE TABLE recepcionista (
    id_recepcionista INT PRIMARY KEY,
    activo TINYINT,
    fid_sede INT,
    FOREIGN KEY (id_recepcionista) REFERENCES usuario(id_usuario),
    FOREIGN KEY(fid_sede) REFERENCES sede(id_sede)
)ENGINE=InnoDB;

-- Tabla: Consultorio
CREATE TABLE consultorio (
    id_consultorio INT PRIMARY KEY AUTO_INCREMENT,
    nombre_consultorio VARCHAR(20) NOT NULL,
    piso INT,
    capacidad INT,
    activo TINYINT
)ENGINE=InnoDB;

-- Relacion de * - * entre Sede y Consultorio
CREATE TABLE sede_consultorio (
    fid_sede INT,
    fid_consultorio INT,
    PRIMARY KEY (fid_sede, fid_consultorio),
    FOREIGN KEY (fid_sede) REFERENCES sede(id_sede),
    FOREIGN KEY (fid_consultorio) REFERENCES consultorio(id_consultorio)
)ENGINE=InnoDB;

-- Relacion de * - * entre Medico y Especialidad
CREATE TABLE medico_especialidad (
    fid_medico INT,
    fid_especialidad INT,
    PRIMARY KEY (fid_medico, fid_especialidad),
    FOREIGN KEY (fid_medico) REFERENCES medico(id_medico),
    FOREIGN KEY (fid_especialidad) REFERENCES especialidad(id_especialidad)
)ENGINE=InnoDB;

-- Tabla: TurnoMedico
CREATE TABLE turno (
    id_turno INT PRIMARY KEY AUTO_INCREMENT,
    dia ENUM('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO'),
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    fid_consultorio INT,
    fid_medico INT,
    FOREIGN KEY (fid_consultorio) REFERENCES consultorio(id_consultorio),
    FOREIGN KEY (fid_medico) REFERENCES medico(id_medico)
)ENGINE=InnoDB;

-- Tabla: Disponibilidad
CREATE TABLE disponibilidad(
    id_disponibilidad INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    disponible TINYINT,
    fid_turno_medico INT,
    FOREIGN KEY(fid_turno_medico) REFERENCES turno(id_turno)
)ENGINE=InnoDB;

-- Tabla: Cita
CREATE TABLE cita (
    id_cita INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    estado_cita ENUM('PROGRAMADA','CANCELADA','ATENDIDA'),
    motivo_consulta VARCHAR(100),
    fecha_actualizacion DATETIME,
    fid_paciente INT,
    fid_disponibilidad INT,
    fid_recepcionista INT,
    fid_especialidad INT,
    FOREIGN KEY (fid_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (fid_disponibilidad) REFERENCES disponibilidad(id_disponibilidad),
    FOREIGN KEY (fid_recepcionista) REFERENCES recepcionista(id_recepcionista),
    FOREIGN KEY (fid_especialidad) REFERENCES especialidad(id_especialidad)
)ENGINE=InnoDB;

-- Tabla: Triaje
CREATE TABLE triaje (
    id_triaje INT PRIMARY KEY AUTO_INCREMENT,
    peso DECIMAL(10,2),
    altura DECIMAL(10,2),
    presion_arterial VARCHAR(20),
    temperatura DECIMAL(10,2),
    fecha_registro DATETIME,
    activo TINYINT,
    fid_asistente_medico INT,
    FOREIGN KEY (fid_asistente_medico) REFERENCES asistente_medico(id_asistente_medico)
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
    indicaciones VARCHAR(100)
)ENGINE=InnoDB;

-- Tabla: Medicamento
CREATE TABLE medicamento (
    id_medicamento INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    presentacion VARCHAR(100) NOT NULL,
    stock INT NOT NULL
)ENGINE=InnoDB;

-- Relacion de * - * entre Receta y Medicamento
CREATE TABLE receta_medicamento (
    fid_receta INT,
    fid_medicamento INT,
    PRIMARY KEY(fid_receta,fid_medicamento),
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
    fid_triaje INT,
    fid_receta INT,
    fid_historial INT,
    FOREIGN KEY (fid_cita) REFERENCES cita(id_cita),
    FOREIGN KEY (fid_triaje) REFERENCES triaje(id_triaje),
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
    FOREIGN KEY (fid_seguro) REFERENCES seguro(id_seguro),
    FOREIGN KEY (fid_cita) REFERENCES cita(id_cita)
)ENGINE=InnoDB;
