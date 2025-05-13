/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

/**
 *
 * @author asant
 */
public class TurnoMedico {
    private int idTurno;
    private DiaSemana dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Consultorio consultorio;
    private Medico medico;
    private ArrayList<Disponibilidad> detalleTurnos;
    
    public TurnoMedico() {
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public ArrayList<Disponibilidad> getDetalleTurnos() {
        return detalleTurnos;
    }

    public void setDetalleTurnos(ArrayList<Disponibilidad> detalleTurnos) {
        this.detalleTurnos = detalleTurnos;
    }

       
    
}
