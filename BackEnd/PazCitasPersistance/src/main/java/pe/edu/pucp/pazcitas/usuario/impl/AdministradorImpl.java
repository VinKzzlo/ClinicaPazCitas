package pe.edu.pucp.pazcitas.usuario.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.dao.AdministradorDAO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;
import pe.edu.pucp.pazcitas.usuario.model.Medico;


public class AdministradorImpl implements AdministradorDAO{

    private ResultSet rs;
    
    @Override
    public int insertar(Administrador a) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, a.getNombre());
        parametrosEntrada.put(3, a.getApellidoPaterno());
        parametrosEntrada.put(4, a.getApellidoMaterno());
        parametrosEntrada.put(5, a.getDni());
        parametrosEntrada.put(6, a.getEmail());
        parametrosEntrada.put(7, a.getFechaNacimiento());
        parametrosEntrada.put(8, String.valueOf(a.getGenero()));
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ADMINISTRADOR", parametrosEntrada, parametrosSalida);
        a.setIdUsuario((int) parametrosSalida.get(1));
        return a.getIdUsuario();
    }

    @Override
    public int modificar(Administrador a) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, a.getIdUsuario());
        parametrosEntrada.put(2, a.getNombre());
        parametrosEntrada.put(3, a.getApellidoPaterno());
        parametrosEntrada.put(4, a.getApellidoMaterno());
        parametrosEntrada.put(5, a.getDni());
        parametrosEntrada.put(6, a.getEmail());
        parametrosEntrada.put(7, a.getFechaNacimiento());
        parametrosEntrada.put(8, String.valueOf(a.getGenero()));
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ADMINISTRADOR", parametrosEntrada, null);
        return resultado;    
    }

    @Override
    public int eliminar(int idAdministrador) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idAdministrador);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_ADMINISTRADOR", parametrosEntrada, null);
        return resultado;     
    }

    @Override
    public ArrayList<Administrador> listarTodos() {
        ArrayList<Administrador> administradores = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ADMINISTRADOR_TODOS", null);
        try{
            while(rs.next()){
                if(administradores == null) administradores = new ArrayList<>();
                Administrador a = new Administrador();
                a.setIdUsuario(rs.getInt("id_usuario"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidoPaterno(rs.getString("apellido_paterno"));
                a.setApellidoMaterno(rs.getString("apellido_materno"));
                a.setDni(rs.getString("dni"));
                a.setEmail(rs.getString("email"));
                a.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                a.setGenero(rs.getString("genero").charAt(0));
                administradores.add(a);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return administradores;
    }

    @Override
    public Administrador obtenerPorID(int idAdmin) {
        Administrador a = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idAdmin);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_ADMINISTRADOR_X_ID", parametrosEntrada);
        try{
            while(rs.next()){
                if(a == null) a = new Administrador();
                a.setIdUsuario(rs.getInt("id_usuario"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidoPaterno(rs.getString("apellido_paterno"));
                a.setApellidoMaterno(rs.getString("apellido_materno"));
                a.setDni(rs.getString("dni"));
                a.setEmail(rs.getString("email"));
                a.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                a.setGenero(rs.getString("genero").charAt(0));
                a.setActivo(true);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return a; 
    }
    
    
    
}
