/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.impl;

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
import pe.edu.pucp.pazcitas.usuario.dao.AsistenteMedicoDAO;
import pe.edu.pucp.pazcitas.usuario.model.AsistenteMedico;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

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
        parametrosEntrada.put(7, Date.valueOf(asistente.getFechaNacimiento()));
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
    public int eliminar(int idAsistente) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idAsistente);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_ASISTENTE_MEDICO", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del asistente");
        return resultado;
    }

    @Override
    public int modificar(AsistenteMedico modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AsistenteMedico> listarTodos() {
        ArrayList<AsistenteMedico> asistentes = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ASISTENTE_MEDICO_TODOS", null);
        System.out.println("Lectura de asistentes...");
        try{
            while(rs.next()){
                AsistenteMedico e = new AsistenteMedico();
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidoPaterno(rs.getString("apellido_paterno"));
                e.setApellidoMaterno(rs.getString("apellido_materno"));
                e.setDni(rs.getString("dni"));
                e.setEmail(rs.getString("email"));
                e.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                e.setGenero(rs.getString("genero").charAt(0));
                e.setHashPassword(rs.getString("hash_password"));
                e.setCodigoPersonal(rs.getString("codigo_personal"));
                
                Medico medico = new Medico();
                medico.setIdUsuario(rs.getInt("id_medico"));
                medico.setCodigoMedico(rs.getString("codigo_medico"));
                
                Sede sede = new Sede();
                sede.setIdSede(rs.getInt("id_sede"));
                sede.setNombre(rs.getString("nombre_sede"));
                sede.setDireccion(rs.getString("direccion"));
                
                e.setSede(sede);
                e.setMedico(medico);
                
                asistentes.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return asistentes;
    }
    
}
