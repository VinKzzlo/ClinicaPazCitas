
package pe.edu.pucp.pazcitas.cita.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.dao.EspecialidadDAO;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.config.DBManager;


public class EspecialidadImpl implements EspecialidadDAO{
    private ResultSet rs;
    @Override
    public int insertar(Especialidad e) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, e.getNombre());
        parametrosEntrada.put(3, e.getDescripcion());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ESPECIALIDAD", parametrosEntrada, parametrosSalida);
        e.setIdEspecialidad((int) parametrosSalida.get(1));
        return e.getIdEspecialidad();
    }

    @Override
    public int modificar(Especialidad e) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1,e.getIdEspecialidad());
        parametrosEntrada.put(2, e.getNombre());
        parametrosEntrada.put(3, e.getDescripcion());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ESPECIALIDAD", parametrosEntrada, null);
        return resultado;    
    }

    @Override
    public int eliminar(int idEspecialidad) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idEspecialidad);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_ESPECIALIDAD", parametrosEntrada, null);
        return resultado;      
    }

    @Override
    public ArrayList<Especialidad> listarTodos() {
        ArrayList<Especialidad> especialidades = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ESPECIALIDAD_TODOS", null);
        try{
            while(rs.next()){
                if(especialidades == null) especialidades = new ArrayList<>();
                Especialidad c = new Especialidad();
                c.setIdEspecialidad(rs.getInt("id_especialidad"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                especialidades.add(c);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return especialidades;    
    }

    @Override
    public Especialidad obtenerPorId(int idEspecialidad) {
        Especialidad c = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idEspecialidad);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_ESPECIALIDAD_X_ID", parametrosEntrada);
        try{
            while(rs.next()){
                if(c == null) c = new Especialidad();
                c.setIdEspecialidad(rs.getInt("id_especialidad"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setActivo(rs.getBoolean("activo"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return c;     
    }

    @Override
    public ArrayList<Especialidad> listarEspxSede(int idSede) {
        ArrayList<Especialidad> especialidades = new ArrayList<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idSede);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ESPECIALIDAD_X_SEDE", parametrosEntrada);
        System.out.println("Lectura de sede...");
        try {
            if (rs.next()) {
                if(especialidades == null) especialidades = new ArrayList<>();
                Especialidad c = new Especialidad();
                c.setIdEspecialidad(rs.getInt("id_especialidad"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                especialidades.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return especialidades;    
    }
    
}