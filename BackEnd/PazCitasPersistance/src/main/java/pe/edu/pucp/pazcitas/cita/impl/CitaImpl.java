
package pe.edu.pucp.pazcitas.cita.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.dao.CitaDAO;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.cita.model.EstadoAtencion;
import pe.edu.pucp.pazcitas.cita.model.EstadoCita;
import pe.edu.pucp.pazcitas.cita.model.HorarioTrabajo;
import pe.edu.pucp.pazcitas.cita.model.Turno;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.usuario.model.Medico;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

public class CitaImpl implements CitaDAO{
    private ResultSet rs;
    @Override
    public int insertar(Cita cita) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
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
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1,cita.getIdCita());
        parametrosEntrada.put(2, cita.getEstadoCita().name());
        parametrosEntrada.put(3, cita.getEstadoAtencion().name());
        parametrosEntrada.put(4, cita.getMotivoConsulta());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_CITA", parametrosEntrada, null);
        return resultado;
    }

    @Override
    public int eliminar(int idCita) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCita);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_CITA", parametrosEntrada, null);
        return resultado;    
    }

    @Override
    public ArrayList<Cita> listarTodos() {
        ArrayList<Cita> citas = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CITA_TODOS", null);
        try{
            while(rs.next()){
                if(citas == null) citas = new ArrayList<>();
                Cita c = new Cita();
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);
                
                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);
                
                c.setMotivoConsulta(rs.getString("motivo_consulta"));
               
                Paciente paciente=new Paciente();
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
                
                HorarioTrabajo horario=new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));
                
                Medico med=new Medico();
                med.setIdUsuario(rs.getInt("fid_medico"));
                
                Turno tur=new Turno();
                tur.setIdTurno(rs.getInt("fid_turno"));
                
                horario.setMedico(med);
                horario.setTurno(tur);
                
                c.setPaciente(paciente);
                c.setHorarioTrabajo(horario);
                citas.add(c);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
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
        try{
            while(rs.next()){
                if(c == null) c = new Cita();
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);
                
                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);
                
                c.setMotivoConsulta(rs.getString("motivo_consulta"));
               
                Paciente paciente=new Paciente();
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
                
                HorarioTrabajo horario=new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));
                
                Medico med=new Medico();
                med.setIdUsuario(rs.getInt("fid_medico"));
                
                Turno tur=new Turno();
                tur.setIdTurno(rs.getInt("fid_turno"));
                
                horario.setMedico(med);
                horario.setTurno(tur);
                
                c.setPaciente(paciente);
                c.setHorarioTrabajo(horario);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
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
        try{
            while(rs.next()){
                if(citas == null) citas = new ArrayList<>();
                Cita c = new Cita();
                c.setIdCita(rs.getInt("id_cita"));
                c.setFecha(rs.getDate("fecha"));
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                c.setEstadoCita(estadoCita);
                
                EstadoAtencion estadoAtencion = EstadoAtencion.valueOf(rs.getString("estado_atencion").toUpperCase());
                c.setEstadoAtencion(estadoAtencion);
                
                c.setMotivoConsulta(rs.getString("motivo_consulta"));
               
                Paciente paciente=new Paciente();
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
                
                HorarioTrabajo horario=new HorarioTrabajo();
                horario.setIdHorarioTrabajo(rs.getInt("id_horario_trabajo"));
                
                Medico med=new Medico();
                med.setIdUsuario(rs.getInt("fid_medico"));
                
                Turno tur=new Turno();
                tur.setIdTurno(rs.getInt("fid_turno"));
                
                horario.setMedico(med);
                horario.setTurno(tur);
                
                c.setPaciente(paciente);
                c.setHorarioTrabajo(horario);
                citas.add(c);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return citas;
    }
    
}
