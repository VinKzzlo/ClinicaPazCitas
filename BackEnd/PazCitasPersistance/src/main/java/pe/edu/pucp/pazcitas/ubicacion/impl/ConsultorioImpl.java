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
import pe.edu.pucp.pazcitas.ubicacion.dao.ConsultorioDAO;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;

/**
 *
 * @author asant
 */
public class ConsultorioImpl implements ConsultorioDAO {

    private ResultSet rs;

    @Override
    public int insertar(Consultorio consultorio) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);

        parametrosEntrada.put(2, consultorio.getNombreConsultorio());
        parametrosEntrada.put(3, consultorio.getPiso());
        parametrosEntrada.put(4, consultorio.getCapacidad());
        parametrosEntrada.put(5, consultorio.getSede().getIdSede());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_CONSULTORIO", parametrosEntrada, parametrosSalida);
        consultorio.setIdConsultorio((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del consultorio");
        return consultorio.getIdConsultorio();
    }

    @Override
    public int eliminar(int idConsultorio) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idConsultorio);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_CONSULTORIO", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del consultorio");
        return resultado;
    }

    @Override
    public int modificar(Consultorio consultorio) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put(1, consultorio.getIdConsultorio());

        parametrosEntrada.put(2, consultorio.getNombreConsultorio());
        parametrosEntrada.put(3, consultorio.getPiso());
        parametrosEntrada.put(4, consultorio.getCapacidad());
        parametrosEntrada.put(5, consultorio.getSede().getIdSede());

        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_CONSULTORIO", parametrosEntrada, null);
        System.out.println("Se ha realizado el modificar del consultorio");
        return consultorio.getIdConsultorio();
    }

    @Override
    public ArrayList<Consultorio> listarTodos() {
        ArrayList<Consultorio> consultorios = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CONSULTORIOS_TODOS", null);
        System.out.println("Lectura de consultorios...");
        try {
            while (rs.next()) {
                Consultorio e = new Consultorio();
                e.setIdConsultorio(rs.getInt("id_consultorio"));
                e.setNombreConsultorio(rs.getString("nombre_consultorio"));
                e.setPiso(rs.getInt("piso"));
                e.setCapacidad(rs.getInt("capacidad"));
                if(rs.getInt("asignado") == 1)e.setAsignado(true);
                else e.setAsignado(false);
                e.setActivo(true);

                Sede sede = new Sede();
                sede.setIdSede(rs.getInt("fid_sede"));
                sede.setNombre(rs.getString("nombre"));
                sede.setDireccion(rs.getString("direccion"));
                e.setSede(sede);
                consultorios.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return consultorios;
    }

    
    @Override  
    public Consultorio obtenerPorId(int idConsultorio) {
        Consultorio consultorio = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idConsultorio);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_CONSULTORIO_X_ID", parametrosEntrada);
        System.out.println("Lectura de consultorio...");
        try {
            if (rs.next()) {
                consultorio = new Consultorio();
                consultorio.setIdConsultorio(rs.getInt("id_consultorio"));
                consultorio.setNombreConsultorio(rs.getString("nombre_consultorio"));
                consultorio.setPiso(rs.getInt("piso"));
                consultorio.setCapacidad(rs.getInt("capacidad"));
                
                if(rs.getInt("asignado") == 1)consultorio.setAsignado(true);
                else consultorio.setAsignado(false);
                consultorio.setActivo(true);
                
                if(rs.getInt("activo") == 1)consultorio.setActivo(true);
                else consultorio.setActivo(false);

                Sede sede = new Sede();
                sede.setIdSede(rs.getInt("fid_sede"));
                sede.setNombre(rs.getString("nombre"));
                sede.setDireccion(rs.getString("direccion"));
                consultorio.setSede(sede);
                

                // Si quieres, puedes también recuperar la sede asociada con otro DAO
                // int idSede = rs.getInt("fid_sede");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return consultorio;
    }

    @Override
    public ArrayList<Consultorio> listarConsultoriosXSede(int idSede) {
        ArrayList<Consultorio> consultorios = new ArrayList<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idSede);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_CONSULTORIOS_X_SEDE", parametrosEntrada);
        System.out.println("Lectura de consultorios por sede...");
        try {
            if (rs.next()) {
                if(consultorios == null) consultorios = new ArrayList<>();
                Consultorio c = new Consultorio();
                c.setIdConsultorio(rs.getInt("id_consultorio"));
                c.setNombreConsultorio(rs.getString("nombre_consultorio"));
                c.setPiso(rs.getInt("piso"));
                c.setCapacidad(rs.getInt("capacidad"));
                if(rs.getInt("asignado") == 1)c.setAsignado(true);
                else c.setAsignado(false);
                c.setActivo(true);
                consultorios.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return consultorios;
    }
    
}
