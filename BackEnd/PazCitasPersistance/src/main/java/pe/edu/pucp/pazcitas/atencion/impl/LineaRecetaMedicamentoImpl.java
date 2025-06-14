/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.atencion.dao.LineaRecetaMedicamentoDAO;
import pe.edu.pucp.pazcitas.atencion.model.LineaRecetaMedicamento;
import pe.edu.pucp.pazcitas.config.DBManager;

/**
 *
 * @author Joel
 */
public class LineaRecetaMedicamentoImpl implements LineaRecetaMedicamentoDAO{

    @Override
    public int insertar(LineaRecetaMedicamento linea) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, linea.getReceta().getIdReceta());
        parametrosEntrada.put(3, linea.getMedicamento().getIdMedicamento());
        parametrosEntrada.put(4, linea.getCantidad());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_RECETA_MEDICAMENTO", parametrosEntrada, parametrosSalida);
        linea.setIdLineaRecetaMedicamento((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro de la linea receta...");
        return linea.getIdLineaRecetaMedicamento();
    }

    @Override
    public int eliminar(int idModelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(LineaRecetaMedicamento modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<LineaRecetaMedicamento> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
