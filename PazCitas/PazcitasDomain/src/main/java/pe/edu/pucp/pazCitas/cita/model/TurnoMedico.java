/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazCitas.cita.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazCitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazCitas.usuario.model.Medico;

/**
 *
 * @author Joel
 */
public class TurnoMedico {
    private int idTurno;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Consultorio consultorio;
    private Medico medico;
    private ArrayList<DetalleTurno> detalleTurnos;
    
    public TurnoMedico() {
    }

    public ArrayList<DetalleTurno> getDetalleTurnos() {
        return detalleTurnos;
    }

    public void setDetalleTurnos(ArrayList<DetalleTurno> detalleTurnos) {
        this.detalleTurnos = detalleTurnos;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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

   
    
    
}
