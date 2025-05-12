/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.ubicacion.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.ubicacion.dao.SedeDAO;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;

/**
 *
 * @author asant
 */
public class SedeImpl implements SedeDAO {

    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Sede sede) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, sede.getNombre());
        parametrosEntrada.put(3, sede.getDireccion());
        
        
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_SEDE", parametrosEntrada, parametrosSalida);
        sede.setIdSede((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro de la sede");
        return sede.getIdSede();
    }

    @Override
    public int eliminar(int idModelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Sede modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Sede> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
