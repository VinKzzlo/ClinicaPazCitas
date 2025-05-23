
package pe.edu.pucp.pazcitas.cita.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Disponibilidad {
    private int idDisponibilidad;
    private LocalTime hora;
    private LocalDate fecha;
    private boolean disponible;
    private Cita cita;
    private TurnoMedico turnoMedico;

    public Disponibilidad() {
    }

    public int getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(int idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
