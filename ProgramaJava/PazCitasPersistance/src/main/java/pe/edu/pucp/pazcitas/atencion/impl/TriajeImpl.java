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
import pe.edu.pucp.pazcitas.atencion.dao.TriajeDAO;
import pe.edu.pucp.pazcitas.atencion.model.Triaje;
import pe.edu.pucp.pazcitas.config.DBManager;

/**
 *
 * @author asant
 */
public class TriajeImpl implements TriajeDAO{

    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(Triaje triaje) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, triaje.getPeso());
        parametrosEntrada.put(3, triaje.getAltura());
        parametrosEntrada.put(4, triaje.getPresionArterial());
        parametrosEntrada.put(5, triaje.getTemperatura());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_TRIAJE",parametrosEntrada, parametrosSalida);
        triaje.setIdTriaje((int)parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del triaje");
        return triaje.getIdTriaje();
    }

    @Override
    public int eliminar(int idTriaje) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idTriaje);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_TRIAJE", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del triaje");
        return resultado;
    }

    @Override
    public int modificar(Triaje triaje) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, triaje.getIdTriaje());
        parametrosEntrada.put(2, triaje.getPeso());
        parametrosEntrada.put(3, triaje.getAltura());
        parametrosEntrada.put(4, triaje.getPresionArterial());
        parametrosEntrada.put(5, triaje.getTemperatura());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_TRIAJE",parametrosEntrada, null);
        System.out.println("Se ha modificado el triaje");
        return resultado;
    }

    @Override
    public ArrayList<Triaje> listarTodos() {
        ArrayList<Triaje> triajes = null;
        
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_TRIAJES_TODOS", null);
        System.out.println("Lectura de triajes...");
        
        try
        {
            while(rs.next()){
                if(triajes == null) triajes = new ArrayList<>();
                Triaje t = new Triaje();
                t.setIdTriaje(rs.getInt("id_triaje"));
                t.setPeso(rs.getDouble("peso"));
                t.setAltura(rs.getDouble("altura"));
                t.setPresionArterial(rs.getString("presion_arterial"));
                t.setTemperatura(rs.getDouble("temperatura"));
                t.setFechaRegistro(rs.getDate("fecha_registro"));
                triajes.add(t);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        
        return triajes;
    }
    
}
