/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.usuario.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.usuario.dao.AsistenteMedicoDAO;
import pe.edu.pucp.pazcitas.usuario.model.AsistenteMedico;

/**
 *
 * @author asant
 */
public class AsistenteMedicoImpl implements AsistenteMedicoDAO{

    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(AsistenteMedico asistente) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, asistente.getNombre());
        parametrosEntrada.put(3, asistente.getApellidoPaterno());
        parametrosEntrada.put(4, asistente.getApellidoPaterno());
        parametrosEntrada.put(5, String.valueOf(asistente.getDni()));
        parametrosEntrada.put(6, asistente.getEmail());
        parametrosEntrada.put(7, new Date(asistente.getFechaNacimiento().getTime()));
        parametrosEntrada.put(8, String.valueOf(asistente.getGenero()));
        parametrosEntrada.put(9, asistente.getHashPassword());
        
        
        parametrosEntrada.put(10, asistente.getCodigoPersonal());
        parametrosEntrada.put(11, asistente.getSede().getIdSede());
        parametrosEntrada.put(12, asistente.getMedico().getIdUsuario());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ASISTENTE_MEDICO", parametrosEntrada, parametrosSalida);
        asistente.setIdUsuario((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del asistente");
        return asistente.getIdUsuario();  
    }

    @Override
    public int eliminar(int idModelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(AsistenteMedico modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AsistenteMedico> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
