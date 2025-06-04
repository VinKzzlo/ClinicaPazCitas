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
import pe.edu.pucp.pazcitas.financiero.model.Seguro;
import pe.edu.pucp.pazcitas.financiero.model.TipoSeguro;
import pe.edu.pucp.pazcitas.usuario.dao.PacienteDAO;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

/**
 *
 * @author asant
 */
public class PacienteImpl implements PacienteDAO{

    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(Paciente paciente) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        
        parametrosEntrada.put(2, paciente.getNombre());
        parametrosEntrada.put(3, paciente.getApellidoPaterno());
        parametrosEntrada.put(4, paciente.getApellidoMaterno());
        parametrosEntrada.put(5, paciente.getDni());
        parametrosEntrada.put(6, paciente.getEmail());
        parametrosEntrada.put(7, paciente.getFechaNacimiento());
        parametrosEntrada.put(8, String.valueOf(paciente.getGenero()));
        parametrosEntrada.put(9, paciente.getDireccion());
        parametrosEntrada.put(10, paciente.getTelefono());
        parametrosEntrada.put(11, paciente.getSeguro().getIdSeguro());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_PACIENTE", parametrosEntrada, parametrosSalida);
        paciente.setIdUsuario((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del paciente");
        return paciente.getIdUsuario();
    }

    @Override
    public int eliminar(int idPaciente) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idPaciente);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_PACIENTE",
                parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del paciente");
        return resultado;
    }

    @Override
    public int modificar(Paciente paciente) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, paciente.getIdUsuario());
        
        parametrosEntrada.put(2, paciente.getNombre());
        parametrosEntrada.put(3, paciente.getApellidoPaterno());
        parametrosEntrada.put(4, paciente.getApellidoMaterno());
        parametrosEntrada.put(5, paciente.getDni());
        parametrosEntrada.put(6, paciente.getEmail());
        parametrosEntrada.put(7, paciente.getFechaNacimiento());
        parametrosEntrada.put(8, String.valueOf(paciente.getGenero()));
        parametrosEntrada.put(9, paciente.getDireccion());
        parametrosEntrada.put(10, paciente.getTelefono());
        parametrosEntrada.put(11, paciente.getSeguro().getIdSeguro());

        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_PACIENTE", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del paciente");
        return paciente.getIdUsuario();
    }

    @Override
    public ArrayList<Paciente> listarTodos() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_PACIENTE_TODOS", null);
        System.out.println("Lectura de pacientes...");
        try{
            while(rs.next()){
                Paciente e = new Paciente();
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidoPaterno(rs.getString("apellido_paterno"));
                e.setApellidoMaterno(rs.getString("apellido_materno"));
                e.setDni(rs.getString("dni"));
                e.setEmail(rs.getString("email"));
                e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                e.setGenero(rs.getString("genero").charAt(0));
                e.setHashPassword(rs.getString("hash_password"));
                e.setDireccion(rs.getString("direccion"));
                e.setTelefono(rs.getString("telefono"));
                
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("id_seguro"));
                seguro.setNombreSeguro(rs.getString("nombre_seguro"));
                TipoSeguro tipo = TipoSeguro.valueOf(rs.getString("tipo").toUpperCase());
                seguro.setTipo(tipo);
                seguro.setPorcentajeCobertura(rs.getDouble("porcentaje_cobertura"));
                
                e.setSeguro(seguro);
                
                pacientes.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return pacientes;
    }
    
}
