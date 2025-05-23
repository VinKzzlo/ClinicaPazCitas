/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.dao.EspecialidadDAO;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.config.DBManager;

/**
 *
 * @author asant
 */
public class EspecialidadImpl implements EspecialidadDAO {

    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Especialidad especialidad) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        
        parametrosEntrada.put(2, especialidad.getNombre());
        parametrosEntrada.put(3, especialidad.getDescripcion());
        
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ESPECIALIDAD", parametrosEntrada, parametrosSalida);
        especialidad.setIdEspecialidad((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro de la especialidad");
        return especialidad.getIdEspecialidad();
    }

    @Override
    public int eliminar(int idEspecialidad) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idEspecialidad);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_ESPECIALIDAD", parametrosEntrada, null);
        return resultado;
    }

    @Override
    public int modificar(Especialidad especialidad) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, especialidad.getIdEspecialidad());
        parametrosEntrada.put(2, especialidad.getNombre());
        parametrosEntrada.put(3, especialidad.getDescripcion());
            
        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ESPECIALIDAD", parametrosEntrada, null);

        System.out.println("Se ha realizado el registro de la especialidad");
        return especialidad.getIdEspecialidad();
    }

    @Override
    public ArrayList<Especialidad> listarTodos() {
        ArrayList<Especialidad> especialidades = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ESPECIALIDADES_TODAS", null);
        System.out.println("Lectura de especialidades...");
        try{
            while(rs.next()){
                Especialidad e = new Especialidad();
                e.setIdEspecialidad(rs.getInt("id_especialidad"));
                e.setNombre(rs.getString("nombre"));
                e.setDescripcion(rs.getString("descripcion"));
                
                especialidades.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return especialidades;
    }

}
