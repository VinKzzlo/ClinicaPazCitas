
package pe.edu.pucp.pazCitas.cita.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazCitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazCitas.usuario.model.Medico;


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

    public ArrayList<Disponibilidad> getDetalleTurnos() {
        return detalleTurnos;
    }

    public void setDetalleTurnos(ArrayList<Disponibilidad> detalleTurnos) {
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

    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

   
    
    
}
