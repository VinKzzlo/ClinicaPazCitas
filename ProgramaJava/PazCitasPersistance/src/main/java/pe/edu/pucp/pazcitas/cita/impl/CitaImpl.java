/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.print.attribute.standard.DateTimeAtCompleted;
import pe.edu.pucp.pazcitas.cita.dao.CitaDAO;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.cita.model.Disponibilidad;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.cita.model.EstadoCita;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

/**
 *
 * @author asant
 */
public class CitaImpl implements CitaDAO {

    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Cita cita) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        
        parametrosEntrada.put(2, Timestamp.valueOf(cita.getFecha()));
        parametrosEntrada.put(3, cita.getEstadoCita().name());
        parametrosEntrada.put(4, cita.getMotivoConsulta());
        parametrosEntrada.put(5, Timestamp.valueOf(cita.getFechaActualizacion()));
        parametrosEntrada.put(6, cita.getPaciente().getIdUsuario());
        parametrosEntrada.put(7, cita.getDisponibilidad().getIdDisponibilidad());
        parametrosEntrada.put(8, cita.getEspecialidad().getIdEspecialidad());
        

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_CITA", parametrosEntrada, parametrosSalida);
        cita.setIdCita((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del cita");
        return cita.getIdCita();
    }

    @Override
    public int eliminar(int cita) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Cita modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Cita> listarTodos() {
        ArrayList<Cita> citas = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CITAS_TODAS", null);
        System.out.println("Lectura de pacientes...");
        try{
            while(rs.next()){
                Cita e = new Cita();
                e.setIdCita(rs.getInt("id_cita"));
                e.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                EstadoCita estadoCita = EstadoCita.valueOf(rs.getString("estado_cita").toUpperCase());
                e.setEstadoCita(estadoCita);
                
                e.setMotivoConsulta(rs.getString("motivo_consulta"));
                e.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion").toLocalDateTime());
                
                Paciente paciente = new Paciente();
                paciente.setIdUsuario(rs.getInt("fid_paciente"));
                
                Disponibilidad dis = new Disponibilidad();
                dis.setIdDisponibilidad(rs.getInt("fid_disponibilidad"));
                
                Especialidad es = new Especialidad();
                es.setIdEspecialidad(rs.getInt("fid_especialidad"));
                
                
                e.setPaciente(paciente);
                e.setDisponibilidad(dis);
                e.setEspecialidad(es);
                
                citas.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return citas;
    }

}
