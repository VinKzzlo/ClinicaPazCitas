/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.bo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazcitas.cita.dao.CitaDAO;
import pe.edu.pucp.pazcitas.cita.impl.CitaImpl;
import pe.edu.pucp.pazcitas.cita.model.Cita;

/**
 *
 * @author asant
 */
public class CitaBO {

    private final CitaDAO daoCita;

    public CitaBO() {
        daoCita = new CitaImpl();
    }

    public int insertar(Cita cita) {
        return daoCita.insertar(cita);
    }

    public int modificar(Cita cita) {
        return daoCita.modificar(cita);
    }

    public int eliminar(int idCita) {
        return daoCita.eliminar(idCita);
    }

    public ArrayList<Cita> listarTodos() {
        return daoCita.listarTodos();
    }

    public Cita obtnerXId(int idCita) {
        return daoCita.obtenerPorId(idCita);
    }

    public ArrayList<Cita> listarXPaciente(int idPaciente) {
        return daoCita.obtenerCitaPorPaciente(idPaciente);
    }

    public ArrayList<Cita> listarXMedico(int idMedico) {
        return daoCita.listarXMedico(idMedico);
    }

    public ArrayList<Cita> listarXMedicoXEstado(int idMedico, String estado) {
        return daoCita.listarXMedicoXEstado(idMedico, estado);
    }

    public ArrayList<Cita> listarXPacienteCompletoSinDatosPaciente(int idPaciente) {
        return daoCita.listarCitaXPacienteCompletoSinPaciente(idPaciente);
    }

    public Cita obtenerXIdCompleSinDatosPaciente(int idCita) {
        return daoCita.obtenerPorIdCompletoSinPaciente(idCita);
    }

    public int modificarFechaCita(int idCita, Date fecha, Time hora, int idMedico) {
        return daoCita.modificarFechaCita(idCita, fecha, hora, idMedico);
    }

}
