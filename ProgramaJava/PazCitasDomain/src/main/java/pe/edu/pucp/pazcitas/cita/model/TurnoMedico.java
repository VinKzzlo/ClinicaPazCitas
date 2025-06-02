/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.model;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

/**
 *
 * @author asant
 */
public class TurnoMedico {
    private int idTurno;
    private DiaSemana dia;
    private Date horaInicio;
    private Date horaFin;
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

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

       
    
}
