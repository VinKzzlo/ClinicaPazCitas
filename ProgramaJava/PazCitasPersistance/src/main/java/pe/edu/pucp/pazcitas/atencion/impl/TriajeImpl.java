/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.impl;

import java.sql.Connection;
import java.sql.ResultSet;
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
        return triaje.getIdTriaje();
    }

    @Override
    public int eliminar(int idModelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Triaje modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Triaje> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
