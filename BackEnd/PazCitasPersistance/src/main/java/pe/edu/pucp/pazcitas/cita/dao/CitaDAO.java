/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.dao;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.dao.ICrud;

/**
 *
 * @author asant
 */
public interface CitaDAO extends ICrud<Cita>{
    public Cita obtenerPorId(int idCita);
    public ArrayList<Cita> obtenerCitaPorPaciente(int idPaciente);
    ArrayList<Cita> listarXMedico(int idMed);
    public ArrayList<Cita> listarCitaXPacienteCompletoSinPaciente(int idPaciente);
    public Cita obtenerPorIdCompletoSinPaciente(int idCita);
    public int modificarFechaCita(int idCita, Date fecha,Time hora, int idMedico);
}
