/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.financiero.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.financiero.dao.SeguroDAO;
import pe.edu.pucp.pazcitas.financiero.model.Seguro;
import pe.edu.pucp.pazcitas.financiero.model.TipoSeguro;

/**
 *
 * @author asant
 */
public class SeguroImpl implements SeguroDAO{
    
    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Seguro seguro) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, seguro.getIdSeguro());
        parametrosEntrada.put(2, seguro.getNombreSeguro());
        parametrosEntrada.put(3, seguro.getTipo().name());
        parametrosEntrada.put(4, seguro.getVigencia());
        parametrosEntrada.put(5, seguro.getPorcentajeCobertura());
        
       
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_SEGURO", parametrosEntrada, null);
        
        System.out.println("Se ha realizado el registro de la SEGURO");
        return seguro.getIdSeguro();
    }

    @Override
    public int eliminar(int idModelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Seguro modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Seguro> listarTodos() {
        ArrayList<Seguro> seguros = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ADMINISTRADOR_TODOS", null);
        System.out.println("Lectura de seguros...");
        try{
            while(rs.next()){
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("id_seguro"));
                seguro.setNombreSeguro(rs.getString("nombre_seguro"));
                TipoSeguro tipo = TipoSeguro.valueOf(rs.getString("tipo").toUpperCase());
                seguro.setTipo(tipo);
                seguro.setPorcentajeCobertura(rs.getDouble("porcentaje_cobertura"));

                seguros.add(seguro);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return seguros;
    }
    
}
