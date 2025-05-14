/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.dao.AdministradorDAO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;

/**
 *
 * @author asant
 */
public class AdministradorImpl implements AdministradorDAO{

    private ResultSet rs;
    
    
    @Override
    public int insertar(Administrador administrador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        
        parametrosEntrada.put(2, administrador.getNombre());
        parametrosEntrada.put(3, administrador.getApellidoPaterno());
        parametrosEntrada.put(4, administrador.getApellidoPaterno());
        parametrosEntrada.put(5, administrador.getDni());
        parametrosEntrada.put(6, administrador.getEmail());
        parametrosEntrada.put(7, administrador.getFechaNacimiento());
        parametrosEntrada.put(8, String.valueOf(administrador.getGenero()));
        parametrosEntrada.put(9, administrador.getHashPassword());
        parametrosEntrada.put(10, administrador.getSede().getIdSede());
        
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ADMINISTRADOR", parametrosEntrada, parametrosSalida);
        administrador.setIdUsuario((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del administrador");
        return administrador.getIdUsuario();
    }

    @Override
    public int eliminar(int idAdministrador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idAdministrador);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_ADMINISTRADOR", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del administrador");
        return resultado;
    }

    @Override
    public int modificar(Administrador administrador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1,administrador.getIdUsuario() );
        parametrosEntrada.put(2, administrador.getNombre());
        parametrosEntrada.put(3, administrador.getApellidoPaterno());
        parametrosEntrada.put(4, administrador.getApellidoPaterno());
        parametrosEntrada.put(5, String.valueOf(administrador.getDni()));
        parametrosEntrada.put(6, administrador.getEmail());
        parametrosEntrada.put(7, Date.valueOf(administrador.getFechaNacimiento()));
        parametrosEntrada.put(8, String.valueOf(administrador.getGenero()));
        parametrosEntrada.put(9, administrador.getHashPassword());
        parametrosEntrada.put(10, administrador.getSede().getIdSede());
        
        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ADMINISTRADOR", parametrosEntrada, null);
        System.out.println("Se ha realizado el modificacion del administrador");
        return administrador.getIdUsuario();
    }

    @Override
    public ArrayList<Administrador> listarTodos() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ADMINISTRADOR_TODOS", null);
        System.out.println("Lectura de administradores...");
        try{
            while(rs.next()){
                Administrador e = new Administrador();
                e.setIdUsuario(rs.getInt("id_usuario"));
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
                
                
                administradores.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return administradores;
    }
    
    
}

