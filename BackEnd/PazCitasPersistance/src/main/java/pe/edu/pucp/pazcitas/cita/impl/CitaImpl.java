package pe.edu.pucp.pazcitas.cita.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.dao.CitaDAO;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.cita.model.DiaSemana;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.cita.model.EstadoAtencion;
import pe.edu.pucp.pazcitas.cita.model.EstadoCita;
import pe.edu.pucp.pazcitas.cita.model.HorarioTrabajo;
import pe.edu.pucp.pazcitas.cita.model.Turno;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.model.Medico;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

public class CitaImpl implements CitaDAO {

    private ResultSet rs;

    @Override
    public int insertar(Cita cita) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, cita.getFecha());
        parametrosEntrada.put(3, cita.getMotivoConsulta());
        parametrosEntrada.put(4, cita.getPaciente().getIdUsuario());
        parametrosEntrada.put(5, cita.getHorarioTrabajo().getIdHorarioTrabajo());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_CITA", parametrosEntrada, parametrosSalida);
        cita.setIdCita((int) parametrosSalida.get(1));
        return cita.getIdCita();
    }

    @Override
    public int modificar(Cita cita) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, cita.getIdCita());
        parametrosEntrada.put(2, cita.getEstadoCita().name());
        parametrosEntrada.put(3, cita.getEstadoAtencion().name());
        parametrosEntrada.put(4, cita.getMotivoConsulta());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_CITA", parametrosEntrada, null);
        return resultado;
    }

    @Override
    public int eliminar(int idCita) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCita);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_CITA", parametrosEntrada, null);
        return resultado;
    }

    @Override
    public ArrayList<Cita> listarTodos() {
        ArrayList<Cita> citas = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CITA_TODOS", null);
        try {
            while (rs.next()) {
                if (citas == null) {
                    citas = new ArrayList<>();
                }
                Cita c = new Cita();
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);

                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);

                c.setMotivoConsulta(rs.getString("motivo_consulta"));

                Paciente paciente = new Paciente();
                paciente.setIdUsuario(rs.getInt("fid_paciente"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellidoPaterno(rs.getString("apellido_paterno"));
                paciente.setApellidoMaterno(rs.getString("apellido_materno"));
                paciente.setDni(rs.getString("dni"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                paciente.setEmail(rs.getString("email"));
                paciente.setGenero(rs.getString("genero").charAt(0));

                paciente.setDireccion(rs.getString("direccion"));
                paciente.setTelefono(rs.getString("telefono"));

                HorarioTrabajo horario = new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));

                Medico med = new Medico();
                med.setIdUsuario(rs.getInt("fid_medico"));

                Turno tur = new Turno();
                tur.setIdTurno(rs.getInt("fid_turno"));

                horario.setMedico(med);
                horario.setTurno(tur);

                c.setPaciente(paciente);
                c.setHorarioTrabajo(horario);
                citas.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return citas;
    }

    @Override
    public Cita obtenerPorId(int idCita) {
        Cita c = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCita);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_CITA_X_ID", parametrosEntrada);
        try {
            if (rs.next()) {
                if (c == null) {
                    c = new Cita();
                }
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);

                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);

                c.setMotivoConsulta(rs.getString("motivo_consulta"));

                Paciente paciente = new Paciente();
                paciente.setIdUsuario(rs.getInt("fid_paciente"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellidoPaterno(rs.getString("apellido_paterno"));
                paciente.setApellidoMaterno(rs.getString("apellido_materno"));
                paciente.setDni(rs.getString("dni"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                paciente.setEmail(rs.getString("email"));
                paciente.setGenero(rs.getString("genero").charAt(0));

                paciente.setDireccion(rs.getString("direccion"));
                paciente.setTelefono(rs.getString("telefono"));

                HorarioTrabajo horario = new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));

                Medico med = new Medico();
                med.setIdUsuario(rs.getInt("fid_medico"));

                Turno tur = new Turno();
                tur.setIdTurno(rs.getInt("fid_turno"));

                horario.setMedico(med);
                horario.setTurno(tur);

                c.setPaciente(paciente);
                c.setHorarioTrabajo(horario);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return c;
    }

    @Override
    public ArrayList<Cita> obtenerCitaPorPaciente(int idPaciente) {
        ArrayList<Cita> citas = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idPaciente);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CITA_X_PACIENTE", parametrosEntrada);
        try {
            while (rs.next()) {
                if (citas == null) {
                    citas = new ArrayList<>();
                }
                Cita c = new Cita();
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);

                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);

                c.setMotivoConsulta(rs.getString("motivo_consulta"));

                Paciente paciente = new Paciente();
                paciente.setIdUsuario(rs.getInt("fid_paciente"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellidoPaterno(rs.getString("apellido_paterno"));
                paciente.setApellidoMaterno(rs.getString("apellido_materno"));
                paciente.setDni(rs.getString("dni"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                paciente.setEmail(rs.getString("email"));
                paciente.setGenero(rs.getString("genero").charAt(0));

                paciente.setDireccion(rs.getString("direccion"));
                paciente.setTelefono(rs.getString("telefono"));

                HorarioTrabajo horario = new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));

                Medico med = new Medico();
                med.setIdUsuario(rs.getInt("fid_medico"));

                Turno tur = new Turno();
                tur.setIdTurno(rs.getInt("fid_turno"));

                horario.setMedico(med);
                horario.setTurno(tur);

                c.setPaciente(paciente);
                c.setHorarioTrabajo(horario);
                citas.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return citas;
    }

    @Override
    public ArrayList<Cita> listarXMedico(int idMed) {
        ArrayList<Cita> citas = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idMed);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CITA_X_MEDICO", parametrosEntrada);
        try {
            while (rs.next()) {
                if (citas == null) {
                    citas = new ArrayList<>();
                }
                Cita c = new Cita();
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);

                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);

                c.setMotivoConsulta(rs.getString("motivo_consulta"));

                Paciente paciente = new Paciente();
                paciente.setIdUsuario(rs.getInt("fid_paciente"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellidoPaterno(rs.getString("apellido_paterno"));
                paciente.setApellidoMaterno(rs.getString("apellido_materno"));
                paciente.setDni(rs.getString("dni"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                paciente.setEmail(rs.getString("email"));
                paciente.setGenero(rs.getString("genero").charAt(0));

                paciente.setDireccion(rs.getString("direccion"));
                paciente.setTelefono(rs.getString("telefono"));

                HorarioTrabajo horario = new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));

                Medico med = new Medico();
                med.setIdUsuario(rs.getInt("fid_medico"));

                Turno tur = new Turno();
                tur.setIdTurno(rs.getInt("fid_turno"));

                horario.setMedico(med);
                horario.setTurno(tur);

                c.setPaciente(paciente);
                c.setHorarioTrabajo(horario);
                citas.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return citas;
    }

    @Override
    public ArrayList<Cita> listarCitaXPacienteCompletoSinPaciente(int idPaciente) {
        ArrayList<Cita> citas = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idPaciente);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CITA_X_PACIENTE_COMPLETO_SIN_PACIENTE", parametrosEntrada);
        try {
            if (citas == null) {
                citas = new ArrayList<>();
            }
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);

                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);

                c.setMotivoConsulta(rs.getString("motivo_consulta"));

                Medico m = new Medico();
                m.setIdUsuario(rs.getInt("id_medico_usuario"));
                m.setCodigoMedico(rs.getString("codigo_medico"));
                m.setNombre(rs.getString("nombre_medico"));
                m.setApellidoPaterno(rs.getString("apellido_paterno_medico"));
                m.setApellidoMaterno(rs.getString("apellido_materno_medico"));
                m.setDni(rs.getString("dni_medico"));
                m.setEmail(rs.getString("email_medico"));
                m.setGenero(rs.getString("genero_medico").charAt(0));

                HorarioTrabajo horario = new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));

                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("id_especialidad"));
                especialidad.setNombre(rs.getString("nombre_especialidad"));
                especialidad.setDescripcion(rs.getString("descripcion_especialidad"));

                Sede s = new Sede();
                s.setIdSede(rs.getInt("id_sede"));
                s.setNombre(rs.getString("nombre_sede"));
                s.setDireccion(rs.getString("direccion_sede"));

                Consultorio consultorio = new Consultorio();
                consultorio.setIdConsultorio(rs.getInt("id_consultorio"));
                consultorio.setNombreConsultorio(rs.getString("nombre_consultorio"));
                consultorio.setPiso(rs.getInt("piso"));
                consultorio.setCapacidad(rs.getInt("capacidad"));

                Turno tur = new Turno();
                tur.setIdTurno(rs.getInt("id_turno"));
                DiaSemana dia = DiaSemana.valueOf(rs.getString("dia").toUpperCase());
                tur.setDia(dia);
                tur.setHoraInicio(rs.getTime("hora_inicio"));
                tur.setHoraFin(rs.getTime("hora_fin"));

                m.setEspecialidad(especialidad);
                m.setConsultorio(consultorio);
                m.setSede(s);
                horario.setMedico(m);
                horario.setTurno(tur);
                c.setHorarioTrabajo(horario);
                citas.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return citas;
    }

    @Override
    public Cita obtenerPorIdCompletoSinPaciente(int idCita) {
        Cita c = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCita);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_CITA_X_ID_COMPLETO_SIN_PACIENTE", parametrosEntrada);
        try {
            if (rs.next()) {
                if (c == null) {
                    c = new Cita();
                }
                
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);

                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);

                c.setMotivoConsulta(rs.getString("motivo_consulta"));

                Medico m = new Medico();
                m.setIdUsuario(rs.getInt("id_medico_usuario"));
                m.setCodigoMedico(rs.getString("codigo_medico"));
                m.setNombre(rs.getString("nombre_medico"));
                m.setApellidoPaterno(rs.getString("apellido_paterno_medico"));
                m.setApellidoMaterno(rs.getString("apellido_materno_medico"));
                m.setDni(rs.getString("dni_medico"));
                m.setEmail(rs.getString("email_medico"));
                m.setGenero(rs.getString("genero_medico").charAt(0));
                
                HorarioTrabajo horario = new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));
                
                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("id_especialidad"));
                especialidad.setNombre(rs.getString("nombre_especialidad"));
                especialidad.setDescripcion(rs.getString("descripcion_especialidad"));
                
                Sede s = new Sede();
                s.setIdSede(rs.getInt("id_sede"));
                s.setNombre(rs.getString("nombre_sede"));
                s.setDireccion(rs.getString("direccion_sede"));
                
                 Consultorio consultorio = new Consultorio();
                consultorio.setIdConsultorio(rs.getInt("id_consultorio"));
                consultorio.setNombreConsultorio(rs.getString("nombre_consultorio"));
                consultorio.setPiso(rs.getInt("piso"));
                consultorio.setCapacidad(rs.getInt("capacidad"));
                
                Turno tur = new Turno();
                tur.setIdTurno(rs.getInt("id_turno"));
                DiaSemana dia = DiaSemana.valueOf(rs.getString("dia").toUpperCase());
                tur.setDia(dia);
                tur.setHoraInicio(rs.getTime("hora_inicio"));
                tur.setHoraFin(rs.getTime("hora_fin"));
                
                m.setEspecialidad(especialidad);
                m.setConsultorio(consultorio);
                m.setSede(s);
                horario.setMedico(m);
                horario.setTurno(tur);
                c.setHorarioTrabajo(horario);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return c;
    }

    @Override
    public int modificarFechaCita(int idCita, Date fecha, Time hora, int idMedico) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCita);
        parametrosEntrada.put(2, fecha);
        parametrosEntrada.put(3, hora);
        parametrosEntrada.put(4, idMedico);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_FECHA_CITA_POR_FECHA_Y_HORA", parametrosEntrada, null);
        return resultado;
    }

}
