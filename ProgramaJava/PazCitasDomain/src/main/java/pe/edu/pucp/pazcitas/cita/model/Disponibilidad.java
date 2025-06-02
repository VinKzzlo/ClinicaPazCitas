
package pe.edu.pucp.pazcitas.cita.model;

import java.util.Date;



public class Disponibilidad {
    private int idDisponibilidad;
    private Date hora;
    private Date fecha;
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

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
