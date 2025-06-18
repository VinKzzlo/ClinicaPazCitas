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
import pe.edu.pucp.pazcitas.atencion.dao.RecetaDAO;
import pe.edu.pucp.pazcitas.atencion.model.LineaRecetaMedicamento;
import pe.edu.pucp.pazcitas.atencion.model.Receta;
import pe.edu.pucp.pazcitas.config.DBManager;

/**
 *
 * @author asant
 */
public class RecetaImpl implements RecetaDAO {

    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Receta receta) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, receta.getIndicaciones());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_RECETA", parametrosEntrada, parametrosSalida);
        receta.setIdReceta((int) parametrosSalida.get(1));

        for (LineaRecetaMedicamento line : receta.getLineasReceta()) {
            parametrosSalida = new HashMap<>();
            parametrosEntrada = new HashMap<>();
            parametrosSalida.put(1, Types.INTEGER);
            parametrosEntrada.put(2, receta.getIdReceta());
            parametrosEntrada.put(3, line.getMedicamento().getIdMedicamento());
            parametrosEntrada.put(4, line.getCantidad());
            DBManager.getInstance().ejecutarProcedimiento("INSERTAR_RECETA_MEDICAMENTO", parametrosEntrada, parametrosSalida);
            line.setIdLineaRecetaMedicamento((int) parametrosSalida.get(1));
        }
        return receta.getIdReceta();
    }

    @Override
    public int eliminar(int idReceta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Receta receta) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, receta.getIdReceta());
        parametrosEntrada.put(2, receta.getIndicaciones());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_RECETA", parametrosEntrada, null);
        System.out.println("Se ha modificado la receta...");
        return resultado;
    }

    @Override
    public ArrayList<Receta> listarTodos() {
        ArrayList<Receta> recetas = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_SOLO_RECETAS", null);
        System.out.println("Lectura de recetas...");

        try {
            while (rs.next()) {
                if (recetas == null) {
                    recetas = new ArrayList<>();
                }
                Receta r = new Receta();
                r.setIdReceta(rs.getInt("id_receta"));
                r.setFechaPrescripcion(rs.getDate("fecha_prescripcion"));
                r.setIndicaciones(rs.getString("indicaciones"));
                recetas.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return recetas;
    }

}
