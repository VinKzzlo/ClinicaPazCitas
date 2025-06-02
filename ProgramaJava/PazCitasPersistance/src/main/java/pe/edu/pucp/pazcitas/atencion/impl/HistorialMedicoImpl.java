/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.atencion.dao.HistorialMedicoDAO;
import pe.edu.pucp.pazcitas.atencion.model.HistorialMedico;
import pe.edu.pucp.pazcitas.atencion.model.Triaje;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

/**
 *
 * @author asant
 */
public class HistorialMedicoImpl implements HistorialMedicoDAO {

    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(HistorialMedico historial) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, historial.getPaciente().getIdUsuario());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_HISTORIAL_MEDICO", parametrosEntrada, parametrosSalida);
        historial.setIdhistorial((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del historial medico...");
        return historial.getIdhistorial();
    }

    @Override
    public int eliminar(int idHistorial) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idHistorial);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_HISTORIAL", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del historial");
        return resultado;
    }

    @Override
    public int modificar(HistorialMedico historial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HistorialMedico> listarTodos() {
        ArrayList<HistorialMedico> historiales = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_HISTORIALES_TODOS", null);
        System.out.println("Lectura de historiales...");
        
        try
        {
            while(rs.next()){
                if(historiales == null) historiales = new ArrayList<>();
                HistorialMedico h = new HistorialMedico();
                h.setIdhistorial(rs.getInt("id_historial"));
                h.setFechaActualizacion(rs.getDate("fecha_actualizacion"));
                Paciente p = new Paciente();
                p.setIdUsuario(rs.getInt("id_paciente"));
                p.setNombre(rs.getString("nombre"));
                p.setApellidoPaterno(rs.getString("apellido_paterno"));
                p.setApellidoMaterno(rs.getString("apellido_materno"));
                p.setDni(rs.getString("dni"));
                h.setPaciente(p);
                historiales.add(h);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        
        return historiales;
    }

}
