/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.atencion.dao.NotaClinicaDAO;
import pe.edu.pucp.pazcitas.atencion.model.HistorialMedico;
import pe.edu.pucp.pazcitas.atencion.model.NotaClinica;
import pe.edu.pucp.pazcitas.atencion.model.Receta;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.config.DBManager;

/**
 *
 * @author asant
 */
public class NotaClinicaImpl implements NotaClinicaDAO {

    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(NotaClinica nota) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, nota.getDescripcion());
        parametrosEntrada.put(3, nota.getDiagnostico());
        parametrosEntrada.put(4, nota.getObservaciones());
        parametrosEntrada.put(5, nota.getCita().getIdCita());
        parametrosEntrada.put(6, nota.getReceta().getIdReceta());
        parametrosEntrada.put(7, nota.getHistorial().getIdhistorial());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_NOTA_CLINICA", parametrosEntrada, parametrosSalida);
        nota.setIdNota((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro de la nota clinica...");
        return nota.getIdNota();
    }

    @Override
    public int eliminar(int idNota) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int modificar(NotaClinica nota) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, nota.getIdNota());
        parametrosEntrada.put(2, nota.getDescripcion());
        parametrosEntrada.put(3, nota.getDiagnostico());
        parametrosEntrada.put(4, nota.getObservaciones());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_NOTA_CLINICA", parametrosEntrada, null);
        System.out.println("Se ha modificado la nota clinica...");
        return resultado;
    }

    @Override
    public ArrayList<NotaClinica> listarTodos() {
        ArrayList<NotaClinica> notas = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_NOTAS_CLINICAS_TODOS", null);
        System.out.println("Lectura de notas...");

        try {
            while (rs.next()) {
                if (notas == null) {
                    notas = new ArrayList<>();
                }
                NotaClinica n = new NotaClinica();
                n.setIdNota(rs.getInt("id_nota"));
                n.setDescripcion(rs.getString("descripcion"));
                n.setDiagnostico(rs.getString("diagnostico"));
                n.setObservaciones(rs.getString("observaciones"));
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt("id_cita"));
                n.setCita(cita);

                Receta receta = new Receta();
                receta.setIdReceta(rs.getInt("id_receta"));
                n.setReceta(receta);
                HistorialMedico hist = new HistorialMedico();
                hist.setIdhistorial(rs.getInt("id_historial"));
                n.setHistorial(hist);
                notas.add(n);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return notas;
    }

    @Override
    public ArrayList<NotaClinica> listarXhistorial(int idHis) {
        ArrayList<NotaClinica> notas = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idHis);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_NOTAS_CLINICAS_X_HISTORIAL", parametrosEntrada);
        System.out.println("Lectura de notas...");

        try {
            while (rs.next()) {
                if (notas == null) {
                    notas = new ArrayList<>();
                }
                NotaClinica n = new NotaClinica();
                n.setIdNota(rs.getInt("id_nota"));
                n.setDescripcion(rs.getString("descripcion"));
                n.setDiagnostico(rs.getString("diagnostico"));
                n.setObservaciones(rs.getString("observaciones"));
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt("id_cita"));
                n.setCita(cita);

                Receta receta = new Receta();
                receta.setIdReceta(rs.getInt("id_receta"));
                n.setReceta(receta);
                HistorialMedico hist = new HistorialMedico();
                hist.setIdhistorial(rs.getInt("id_historial"));
                n.setHistorial(hist);
                notas.add(n);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return notas;
    }

}
