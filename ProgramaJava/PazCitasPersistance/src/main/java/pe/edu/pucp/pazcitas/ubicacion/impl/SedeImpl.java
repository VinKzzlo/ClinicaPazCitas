/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.ubicacion.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(2, sede.getNombre());
        parametrosEntrada.put(3, sede.getDireccion());

        Map<Integer, Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_SEDE", parametrosEntrada, parametrosSalida);
        sede.setIdSede((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro de la sede");
        return sede.getIdSede();
    }

    @Override
    public int eliminar(int idSede) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idSede);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_SEDE", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion de la sede");
        return resultado;
    }

    @Override
    public int modificar(Sede sede) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, sede.getIdSede());
        parametrosEntrada.put(2, sede.getNombre());
        parametrosEntrada.put(3, sede.getDireccion());

        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_SEDE", parametrosEntrada, null);
        System.out.println("Se ha realizado el modificar de la sede");
        return sede.getIdSede();   
    }

    @Override
    public ArrayList<Sede> listarTodos() {
        ArrayList<Sede> sedes = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_SEDES_TODAS", null);
        System.out.println("Lectura de sedes...");
        try {
            while (rs.next()) {
                Sede e = new Sede();
                e.setIdSede(rs.getInt("id_sede"));
                e.setNombre(rs.getString("nombre"));
                e.setDireccion(rs.getString("direccion"));

                sedes.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return sedes;
    }

}
