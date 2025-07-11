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
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_SEDES_TODOS", null);
        System.out.println("Lectura de sedes...");
        try {
            while (rs.next()) {
                Sede e = new Sede();
                e.setIdSede(rs.getInt("id_sede"));
                e.setNombre(rs.getString("nombre"));
                e.setDireccion(rs.getString("direccion"));
                e.setActivo(true);
                sedes.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return sedes;
    }

    @Override
    public Sede obtenerPorId(int idSede) {
        Sede sede = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idSede);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_SEDE_X_ID", parametrosEntrada);
        System.out.println("Lectura de sede...");
        try {
            if (rs.next()) {
                sede = new Sede();
                sede.setIdSede(rs.getInt("id_sede"));
                sede.setNombre(rs.getString("nombre"));
                sede.setDireccion(rs.getString("direccion"));
                sede.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return sede;
    }

    @Override
    public ArrayList<Sede> listarSedePorEspecialidad(int idEsp) {
        ArrayList<Sede> sedes = new ArrayList<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idEsp);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_SEDE_X_ESPECIALIDAD", parametrosEntrada);
        System.out.println("Lectura de sede...");
        try {
            if (rs.next()) {
                if(sedes == null) sedes = new ArrayList<>();
                Sede s = new Sede();
                s.setIdSede(rs.getInt("id_sede"));
                s.setNombre(rs.getString("nombre"));
                s.setDireccion(rs.getString("direccion"));
                sedes.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return sedes;
    }
    
}
