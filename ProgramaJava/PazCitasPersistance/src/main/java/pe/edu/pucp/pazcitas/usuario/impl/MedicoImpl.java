/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.dao.MedicoDAO;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

/**
 *
 * @author asant
 */
public class MedicoImpl implements MedicoDAO {

    private ResultSet rs;

    @Override
    public int insertar(Medico medico) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);

        parametrosEntrada.put(2, medico.getNombre());
        parametrosEntrada.put(3, medico.getApellidoPaterno());
        parametrosEntrada.put(4, medico.getApellidoPaterno());
        parametrosEntrada.put(5, medico.getDni());
        parametrosEntrada.put(6, medico.getEmail());
        parametrosEntrada.put(7, medico.getFechaNacimiento());
        parametrosEntrada.put(8, String.valueOf(medico.getGenero()));
        parametrosEntrada.put(9, medico.getHashPassword());
        parametrosEntrada.put(10, medico.getCodigoMedico());
        parametrosEntrada.put(11, medico.getSede().getIdSede());
        parametrosEntrada.put(12, medico.getEspecialidad().getIdEspecialidad());
        
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_MEDICO", parametrosEntrada, parametrosSalida);
        medico.setIdUsuario((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del medico");
        return medico.getIdUsuario();
    }

    @Override
    public int eliminar(int idMedico) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idMedico);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_MEDICO", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del medico");
        return resultado;
    }

    @Override
    public int modificar(Medico medico) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, medico.getIdUsuario());

        parametrosEntrada.put(2, medico.getNombre());
        parametrosEntrada.put(3, medico.getApellidoPaterno());
        parametrosEntrada.put(4, medico.getApellidoPaterno());
        parametrosEntrada.put(5, medico.getDni());
        parametrosEntrada.put(6, medico.getEmail());
        parametrosEntrada.put(7, medico.getFechaNacimiento());
        parametrosEntrada.put(8, medico.getGenero());
        parametrosEntrada.put(9, medico.getHashPassword());
        parametrosEntrada.put(10, medico.getCodigoMedico());
        parametrosEntrada.put(11, medico.getSede().getIdSede());
        parametrosEntrada.put(12, medico.getEspecialidad().getIdEspecialidad());
        
        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_MEDICO", parametrosEntrada, null);
        System.out.println("Se ha realizado el modificacion del medico");
        return medico.getIdUsuario();
    }

    @Override
    public ArrayList<Medico> listarTodos() {
        ArrayList<Medico> medicos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_MEDICO_TODOS", null);
        System.out.println("Lectura de empleados...");
        try {
            while (rs.next()) {
                Medico e = new Medico();
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidoPaterno(rs.getString("apellido_paterno"));
                e.setApellidoMaterno(rs.getString("apellido_materno"));
                e.setDni(rs.getString("dni"));
                e.setEmail(rs.getString("email"));
                e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                e.setGenero(rs.getString("genero").charAt(0));
                e.setHashPassword(rs.getString("hash_password"));
                e.setCodigoMedico(rs.getString("codigo_medico"));
                
                Sede sede = new Sede();
                sede.setIdSede(rs.getInt("id_sede"));
                sede.setNombre(rs.getString("nombre_sede"));
                sede.setDireccion(rs.getString("direccion"));
                
                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("id_especialidad"));
                especialidad.setNombre(rs.getString("nombre_especialidad"));
                especialidad.setDescripcion(rs.getString("descripcion"));
                
                e.setSede(sede);
                e.setEspecialidad(especialidad);
                
                medicos.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return medicos;
    }

    @Override
    public ArrayList<Medico> listarXEspXSede(int idSede, int idEsp) {
        ArrayList<Medico> medicos = new ArrayList<>();
        Map<Integer, Object> parametros = new HashMap<>();
        parametros.put(1, idSede);
        parametros.put(2, idEsp);

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_MEDICO_POR_SEDE_Y_ESPECIALIDAD", parametros);
        System.out.println("Lectura de médicos por especialidad y sede...");

        try {
            while (rs.next()) {
                Medico e = new Medico();
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidoPaterno(rs.getString("apellido_paterno"));
                e.setApellidoMaterno(rs.getString("apellido_materno"));
                e.setDni(rs.getString("dni"));
                e.setEmail(rs.getString("email"));
                e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                e.setGenero(rs.getString("genero").charAt(0));
                e.setHashPassword(rs.getString("hash_password"));
                e.setCodigoMedico(rs.getString("codigo_medico"));

                Sede sede = new Sede();
                sede.setIdSede(rs.getInt("id_sede"));
                sede.setNombre(rs.getString("nombre_sede"));
                sede.setDireccion(rs.getString("direccion"));

                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("id_especialidad"));
                especialidad.setNombre(rs.getString("nombre_especialidad"));
                especialidad.setDescripcion(rs.getString("descripcion"));

                e.setSede(sede);
                e.setEspecialidad(especialidad);

                medicos.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return medicos;
    }

}
