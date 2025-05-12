/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.usuario.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.dao.RecepcionistaDAO;
import pe.edu.pucp.pazcitas.usuario.model.Recepcionista;

/**
 *
 * @author asant
 */
public class RecepcionistaImpl implements RecepcionistaDAO {

    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Recepcionista recepcionista) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        
        parametrosEntrada.put(2, recepcionista.getNombre());
        parametrosEntrada.put(3, recepcionista.getApellidoPaterno());
        parametrosEntrada.put(4, recepcionista.getApellidoPaterno());
        parametrosEntrada.put(5, String.valueOf(recepcionista.getDni()));
        parametrosEntrada.put(6, recepcionista.getEmail());
        parametrosEntrada.put(7, Date.valueOf(recepcionista.getFechaNacimiento()));
        parametrosEntrada.put(8, String.valueOf(recepcionista.getGenero()));
        parametrosEntrada.put(9, recepcionista.getHashPassword());
        parametrosEntrada.put(10, recepcionista.getSede().getIdSede());
        
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_RECEPCIONISTA", parametrosEntrada, parametrosSalida);
        recepcionista.setIdUsuario((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del recepcionista");
        return recepcionista.getIdUsuario();
    }

    @Override
    public int eliminar(int idRecepcionista) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idRecepcionista);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_RECEPCIONISTA", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del recepcionista");
        return resultado;
    }

    @Override
    public int modificar(Recepcionista modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Recepcionista> listarTodos() {
        ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_RECEPCIONISTA_TODOS", null);
        System.out.println("Lectura de recepcionistas...");
        try{
            while(rs.next()){
                Recepcionista e = new Recepcionista();
                e.setIdUsuario(rs.getInt("id_persona"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidoPaterno(rs.getString("apellido_paterno"));
                e.setApellidoMaterno(rs.getString("apellido_materno"));
                e.setDni(rs.getString("dni"));
                e.setEmail(rs.getString("email"));
                e.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                e.setGenero(rs.getString("genero").charAt(0));
                e.setHashPassword(rs.getString("hash_password"));
             
                
                Sede sede = new Sede();
                sede.setIdSede(rs.getInt("id_sede"));
                sede.setNombre(rs.getString("nombre_sede"));
                sede.setDireccion(rs.getString("direccion"));
                
                e.setSede(sede);
                
                
                recepcionistas.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return recepcionistas;
    }

}
