/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazCitas.cita.model;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Joel
 */
public class DetalleTurno {
    private int idDetalleTurno;
    private LocalTime hora;
    private boolean estado;
    private Cita cita;
    private TurnoMedico turnoMedico;

    public DetalleTurno() {
    }

    public int getIdDetalleTurno() {
        return idDetalleTurno;
    }

    public void setIdDetalleTurno(int idDetalleTurno) {
        this.idDetalleTurno = idDetalleTurno;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public TurnoMedico getTurnoMedico() {
        return turnoMedico;
    }

    public void setTurnoMedico(TurnoMedico turnoMedico) {
        this.turnoMedico = turnoMedico;
    }
    
    
    
}
