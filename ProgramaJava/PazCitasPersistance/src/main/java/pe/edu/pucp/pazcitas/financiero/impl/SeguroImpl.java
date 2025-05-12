/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.financiero.impl;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.financiero.dao.SeguroDAO;
import pe.edu.pucp.pazcitas.financiero.model.Seguro;

/**
 *
 * @author asant
 */
public class SeguroImpl implements SeguroDAO{

    @Override
    public int insertar(Seguro seguro) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        
        parametrosEntrada.put(2, seguro.getNombreSeguro());
        parametrosEntrada.put(3, seguro.getTipo().name());
        parametrosEntrada.put(4, Date.valueOf(seguro.getVigencia()));
        parametrosEntrada.put(5, seguro.getPorcentajeCobertura());
        
        
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_SEGURO", parametrosEntrada, parametrosSalida);
        seguro.setIdSeguro((int) parametrosSalida.get(1));
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
