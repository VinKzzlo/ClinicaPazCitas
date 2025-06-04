/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.dao.TurnoMedicoDAO;
import pe.edu.pucp.pazcitas.cita.model.DiaSemana;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

/**
 *
 * @author asant
 */
public class TurnoMedicoImpl implements TurnoMedicoDAO {

    private ResultSet rs;

    @Override
    public int insertar(TurnoMedico turno) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, turno.getDia().name());
        parametrosEntrada.put(3, new java.sql.Time(turno.getHoraInicio().getTime()));
        parametrosEntrada.put(4, new java.sql.Time(turno.getHoraFin().getTime()));
        parametrosEntrada.put(5, turno.getConsultorio().getIdConsultorio());
        parametrosEntrada.put(6, turno.getMedico().getIdUsuario());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_TURNO_MEDICO", parametrosEntrada, parametrosSalida);
        turno.setIdTurno((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del turno");
        return turno.getIdTurno();
    }

    @Override
    public int eliminar(int idTurno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(TurnoMedico turno) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, turno.getIdTurno());
        parametrosEntrada.put(2, turno.getDia());
        parametrosEntrada.put(3, turno.getHoraInicio());
        parametrosEntrada.put(4, turno.getHoraFin());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_TURNO_FECHA_HORA", parametrosEntrada, null);
        System.out.println("Se ha modificado la disponibilidad");
        return resultado;
    }

    @Override
    public ArrayList<TurnoMedico> listarTodos() {
        ArrayList<TurnoMedico> turnos = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_TURNOS_TODOS", null);
        System.out.println("Lectura de turnos...");

        try {
            while (rs.next()) {
                if (turnos == null) {
                    turnos = new ArrayList<>();
                }
                TurnoMedico t = new TurnoMedico();
                t.setIdTurno(rs.getInt("id_turno"));
                DiaSemana dia = DiaSemana.valueOf(rs.getString("dia").toUpperCase());
                t.setDia(dia);
                t.setHoraInicio(rs.getDate("hora_inicio"));
                t.setHoraFin(rs.getDate("hora_fin"));
                Consultorio cons = new Consultorio();
                cons.setIdConsultorio(rs.getInt("id_consultorio"));
                Medico med = new Medico();
                med.setIdUsuario(rs.getInt("id_medico"));

                t.setConsultorio(cons);
                t.setMedico(med);

                turnos.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return turnos;
    }
    
    //NUEVOS PROCEDMIENTOS AÑADIDOS MAX
    @Override
    public ArrayList<String> listarDiasAtencionPorMedico(int idMedico) {
        ArrayList<String> dias = null;
        Map<Integer, Object> parametros = new HashMap<>();
        parametros.put(1, idMedico);

        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("listar_dias_turno_por_medico", parametros);

        try {
            while (rs.next()) {
                if (dias == null) {
                    dias = new ArrayList<>();
                }
                dias.add(rs.getString("dia")); // debe coincidir con el alias del campo en el SELECT
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer días de atención: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return dias;
    }

    @Override
    public ArrayList<String> listarHorariosPorMedicoYDia(int idMedico, String dia) {
        ArrayList<String> horarios = null;
        Map<Integer, Object> parametros = new HashMap<>();
        parametros.put(1, idMedico);
        parametros.put(2, dia);  // ej. 'MARTES'

        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura(
                "listar_horarios_por_medico_y_dia", parametros
        );

        try {
            while (rs.next()) {
                if (horarios == null) {
                    horarios = new ArrayList<>();
                }
                // Combinar hora_inicio y hora_fin en una sola cadena
                String horario = rs.getString("hora_inicio") + " - " + rs.getString("hora_fin");
                horarios.add(horario);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer horarios: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return horarios;
    }
    

}
