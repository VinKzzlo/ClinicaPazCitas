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
import pe.edu.pucp.pazcitas.atencion.dao.MedicamentoDAO;
import pe.edu.pucp.pazcitas.atencion.model.Medicamento;
import pe.edu.pucp.pazcitas.config.DBManager;

/**
 *
 * @author asant
 */
public class MedicamentoImpl implements MedicamentoDAO {

    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Medicamento medicamento) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, medicamento.getNombre());
        parametrosEntrada.put(3, medicamento.getPresentacion());
        parametrosEntrada.put(4, medicamento.getStock());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_MEDICAMENTO", parametrosEntrada, parametrosSalida);
        medicamento.setIdMedicamento((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del medicamento...");
        return medicamento.getIdMedicamento();
    }

    @Override
    public int eliminar(int idMedicamento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Medicamento medicamento) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, medicamento.getIdMedicamento());
        parametrosEntrada.put(2, medicamento.getNombre());
        parametrosEntrada.put(3, medicamento.getPresentacion());
        parametrosEntrada.put(4, medicamento.getStock());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_MEDICAMENTO", parametrosEntrada, null);
        System.out.println("Se ha modificado el medicamento");
        return resultado;
    }

    @Override
    public ArrayList<Medicamento> listarTodos() {
        ArrayList<Medicamento> medicamentos = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_MEDICAMENTOS_TODOS", null);
        System.out.println("Lectura de medicamentos...");

        try {
            while (rs.next()) {
                if (medicamentos == null) {
                    medicamentos = new ArrayList<>();
                }
                Medicamento m = new Medicamento();
                m.setIdMedicamento(rs.getInt("id_medicamento"));
                m.setNombre(rs.getString("nombre"));
                m.setPresentacion(rs.getString("presentacion"));
                m.setStock(rs.getInt("stock"));
                medicamentos.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return medicamentos;
    }

}
