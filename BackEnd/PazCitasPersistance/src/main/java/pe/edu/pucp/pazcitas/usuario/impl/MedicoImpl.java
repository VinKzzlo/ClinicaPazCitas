
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
import pe.edu.pucp.pazcitas.usuario.dao.MedicoDAO;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

public class MedicoImpl implements MedicoDAO{
    private ResultSet rs;

    @Override
    public int insertar(Medico m) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, m.getNombre());
        parametrosEntrada.put(3, m.getApellidoPaterno());
        parametrosEntrada.put(4, m.getApellidoMaterno());
        parametrosEntrada.put(5, m.getDni());
        parametrosEntrada.put(6, m.getEmail());
        parametrosEntrada.put(7, m.getFechaNacimiento());
        parametrosEntrada.put(8, String.valueOf(m.getGenero()));
        parametrosEntrada.put(9, m.getCodigoMedico());
        parametrosEntrada.put(10, m.getSede().getIdSede());
        parametrosEntrada.put(11, m.getConsultorio().getIdConsultorio());
        parametrosEntrada.put(12, m.getEspecialidad().getIdEspecialidad());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_MEDICO", parametrosEntrada, parametrosSalida);
        m.setIdUsuario((int) parametrosSalida.get(1));
        return m.getIdUsuario();    
    }

    @Override
    public int modificar(Medico m) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, m.getIdUsuario());
        parametrosEntrada.put(2, m.getNombre());
        parametrosEntrada.put(3, m.getApellidoPaterno());
        parametrosEntrada.put(4, m.getApellidoMaterno());
        parametrosEntrada.put(5, m.getDni());
        parametrosEntrada.put(6, m.getEmail());
        parametrosEntrada.put(7, m.getFechaNacimiento());
        parametrosEntrada.put(8, String.valueOf(m.getGenero()));
        parametrosEntrada.put(9, m.getCodigoMedico());
        parametrosEntrada.put(10, m.getSede().getIdSede());
        parametrosEntrada.put(11, m.getConsultorio().getIdConsultorio());
        parametrosEntrada.put(12, m.getEspecialidad().getIdEspecialidad());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_MEDICO", parametrosEntrada, null);
        return resultado;    
    }

    @Override
    public int eliminar(int idMedico) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idMedico);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_MEDICO", parametrosEntrada, null);
        return resultado;      
    }

    @Override
    public ArrayList<Medico> listarTodos() {
        ArrayList<Medico> medicos = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_MEDICO_TODOS", null);
        try{
            while(rs.next()){
                if(medicos == null) medicos = new ArrayList<>();
                Medico m = new Medico();
                m.setIdUsuario(rs.getInt("id_usuario"));
                m.setNombre(rs.getString("nombre"));
                m.setApellidoPaterno(rs.getString("apellido_paterno"));
                m.setApellidoMaterno(rs.getString("apellido_materno"));
                m.setDni(rs.getString("dni"));
                m.setEmail(rs.getString("email"));
                m.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                m.setGenero(rs.getString("genero").charAt(0));
                m.setCodigoMedico(rs.getString("codigo_medico"));
                
                Sede s=new Sede();
                s.setIdSede(rs.getInt("id_sede"));
                s.setNombre(rs.getString("nombre_sede"));
                s.setDireccion(rs.getString("direccion"));
                
                Consultorio c=new Consultorio();
                c.setIdConsultorio(rs.getInt("id_consultorio"));
                c.setNombreConsultorio(rs.getString("nombre_consultorio"));
                c.setPiso(rs.getInt("piso"));
                c.setCapacidad(rs.getInt("capacidad"));
                c.setAsignado(rs.getBoolean("asignado"));
                
                Especialidad e=new Especialidad();
                e.setIdEspecialidad(rs.getInt("id_especialidad"));
                e.setNombre(rs.getString("nombre_especialidad"));
                e.setDescripcion(rs.getString("descripcion"));
                m.setEspecialidad(e);
                m.setSede(s);
                m.setConsultorio(c);
                medicos.add(m);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return medicos;    
    }
    

    @Override
    public Medico obtenerPorID(int idMedico) {
        Medico m = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idMedico);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_MEDICO_X_ID", parametrosEntrada);
        try{
            while(rs.next()){
                if(m == null) m = new Medico();
                m.setIdUsuario(rs.getInt("id_usuario"));
                m.setNombre(rs.getString("nombre"));
                m.setApellidoPaterno(rs.getString("apellido_paterno"));
                m.setApellidoMaterno(rs.getString("apellido_materno"));
                m.setDni(rs.getString("dni"));
                m.setEmail(rs.getString("email"));
                m.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                m.setGenero(rs.getString("genero").charAt(0));
                m.setCodigoMedico(rs.getString("codigo_medico"));
                
                Sede s=new Sede();
                s.setIdSede(rs.getInt("id_sede"));
                s.setNombre(rs.getString("nombre_sede"));
                s.setDireccion(rs.getString("direccion"));
                
                Consultorio c=new Consultorio();
                c.setIdConsultorio(rs.getInt("id_consultorio"));
                c.setNombreConsultorio(rs.getString("nombre_consultorio"));
                c.setPiso(rs.getInt("piso"));
                c.setCapacidad(rs.getInt("capacidad"));
                c.setAsignado(rs.getBoolean("asignado"));
                
                Especialidad e=new Especialidad();
                e.setIdEspecialidad(rs.getInt("id_especialidad"));
                e.setNombre(rs.getString("nombre_especialidad"));
                e.setDescripcion(rs.getString("descripcion"));
                m.setEspecialidad(e);
                m.setSede(s);
                m.setConsultorio(c);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return m; 
    }

    @Override
    public ArrayList<Medico> listarXEspXSede(int idSede, int idEsp) {
        ArrayList<Medico> medicos = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idSede);
        parametrosEntrada.put(2, idEsp);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_MEDICOS_X_SEDE_X_ESP", parametrosEntrada);
        try{
            while(rs.next()){
                if(medicos == null) medicos = new ArrayList<>();
                Medico m = new Medico();
                m.setIdUsuario(rs.getInt("id_usuario"));
                m.setNombre(rs.getString("nombre"));
                m.setApellidoPaterno(rs.getString("apellido_paterno"));
                m.setApellidoMaterno(rs.getString("apellido_materno"));
                m.setDni(rs.getString("dni"));
                m.setEmail(rs.getString("email"));
                m.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                m.setGenero(rs.getString("genero").charAt(0));
                m.setCodigoMedico(rs.getString("codigo_medico"));
                
                Sede s=new Sede();
                s.setIdSede(rs.getInt("id_sede"));
                s.setNombre(rs.getString("nombre_sede"));
                s.setDireccion(rs.getString("direccion"));
                
                Consultorio c=new Consultorio();
                c.setIdConsultorio(rs.getInt("id_consultorio"));
                c.setNombreConsultorio(rs.getString("nombre_consultorio"));
                c.setPiso(rs.getInt("piso"));
                c.setCapacidad(rs.getInt("capacidad"));
                c.setAsignado(rs.getBoolean("asignado"));
                
                Especialidad e=new Especialidad();
                e.setIdEspecialidad(rs.getInt("id_especialidad"));
                e.setNombre(rs.getString("nombre_especialidad"));
                e.setDescripcion(rs.getString("descripcion"));
                m.setEspecialidad(e);
                m.setSede(s);
                m.setConsultorio(c);
                medicos.add(m);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return medicos;
    }
    
    
    
}
