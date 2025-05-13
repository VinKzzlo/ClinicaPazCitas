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
import pe.edu.pucp.pazcitas.cita.dao.DisponibilidadDAO;
import pe.edu.pucp.pazcitas.cita.model.DiaSemana;
import pe.edu.pucp.pazcitas.cita.model.Disponibilidad;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

/**
 *
 * @author asant
 */
public class DisponibilidadImpl implements DisponibilidadDAO {

    private ResultSet rs;

    @Override
    public int insertar(Disponibilidad disponibilidad) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, Date.valueOf(disponibilidad.getFecha()));
        parametrosEntrada.put(3, Time.valueOf(disponibilidad.getHora()));
        parametrosEntrada.put(4, disponibilidad.getTurnoMedico().getIdTurno());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_DISPONIBILIDAD", parametrosEntrada, parametrosSalida);
        disponibilidad.setIdDisponibilidad((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro de la disponibilidad");
        return disponibilidad.getIdDisponibilidad();
    }

    @Override
    public int eliminar(int idDisponibilidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(Disponibilidad disponibilidad) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, disponibilidad.getIdDisponibilidad());
        parametrosEntrada.put(2, disponibilidad.getFecha());
        parametrosEntrada.put(3, disponibilidad.getHora());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_DISPONIBILIDAD", parametrosEntrada, null);
        System.out.println("Se ha modificado la disponibilidad");
        return resultado;
    }

    @Override
    public ArrayList<Disponibilidad> listarTodos() {
        ArrayList<Disponibilidad> dispos = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_DISPONIBILIDADES_TODAS", null);
        System.out.println("Lectura de recetas...");

        try {
            while (rs.next()) {
                if (dispos == null) {
                    dispos = new ArrayList<>();
                }
                Disponibilidad d = new Disponibilidad();
                d.setIdDisponibilidad(rs.getInt("id_disponibilidad"));
                d.setFecha(rs.getDate("fecha").toLocalDate());
                d.setHora(rs.getTime("hora").toLocalTime());
                
                TurnoMedico turno = new TurnoMedico();
                turno.setIdTurno(rs.getInt("turno_id"));
                DiaSemana dia = DiaSemana.valueOf(rs.getString("turno_dia").toUpperCase());
                turno.setDia(dia);
                turno.setHoraInicio(rs.getTime("turno_hora_inicio").toLocalTime());
                turno.setHoraFin(rs.getTime("turno_hora_fin").toLocalTime());
                
                Medico med = new Medico();
                med.setIdUsuario(rs.getInt("turno_id_medico"));
                Consultorio cons = new Consultorio();
                cons.setIdConsultorio(rs.getInt("turno_id_consultorio"));
                
                turno.setMedico(med);
                turno.setConsultorio(cons);
                d.setTurnoMedico(turno);
                
                dispos.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return dispos;
    }

}
