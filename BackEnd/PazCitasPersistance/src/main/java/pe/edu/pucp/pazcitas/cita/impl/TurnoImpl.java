
package pe.edu.pucp.pazcitas.cita.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.dao.TurnoDAO;
import pe.edu.pucp.pazcitas.cita.model.DiaSemana;
import pe.edu.pucp.pazcitas.cita.model.Turno;
import pe.edu.pucp.pazcitas.config.DBManager;

public class TurnoImpl implements TurnoDAO{
    private ResultSet rs;

    @Override
    public int insertar(Turno t) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, t.getDia().name());
        parametrosEntrada.put(3, t.getHoraInicio());
        parametrosEntrada.put(4, t.getHoraFin());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_TURNO", parametrosEntrada, parametrosSalida);
        t.setIdTurno((int) parametrosSalida.get(1));
        return t.getIdTurno();    
    }

    @Override
    public int modificar(Turno t) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, t.getIdTurno());
        parametrosEntrada.put(2, t.getDia().name());
        parametrosEntrada.put(3, t.getHoraInicio());
        parametrosEntrada.put(4, t.getHoraFin());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_TURNO", parametrosEntrada, null);
        return resultado;       }

    @Override
    public int eliminar(int idTurno) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idTurno);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_TURNO", parametrosEntrada, null);
        return resultado;      
    }

    @Override
    public ArrayList<Turno> listarTodos() {
        ArrayList<Turno> turnos = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_TURNO_TODOS", null);
        try{
            while(rs.next()){
                if(turnos == null) turnos = new ArrayList<>();
                Turno t = new Turno();
                t.setIdTurno(rs.getInt("id_turno"));
                DiaSemana dia=DiaSemana.valueOf(rs.getString("dia").toUpperCase());
                t.setDia(dia);
                t.setHoraInicio(rs.getTime("hora_inicio"));
                t.setHoraFin(rs.getTime("hora_fin"));
                turnos.add(t);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return turnos;    
    }

    @Override
    public Turno obtenerPorId(int idTurno) {
        Turno t = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idTurno);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_TURNO_X_ID", parametrosEntrada);
        try{
            while(rs.next()){
                if(t == null) t = new Turno();
                t.setIdTurno(rs.getInt("id_turno"));
                DiaSemana dia=DiaSemana.valueOf(rs.getString("dia").toUpperCase());
                t.setDia(dia);
                t.setHoraInicio(rs.getTime("hora_inicio"));
                t.setHoraFin(rs.getTime("hora_fin"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return t;      
    }
    
}