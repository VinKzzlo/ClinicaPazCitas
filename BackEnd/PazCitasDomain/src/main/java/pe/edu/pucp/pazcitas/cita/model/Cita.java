package pe.edu.pucp.pazcitas.cita.model;


import java.util.Date;
import pe.edu.pucp.pazcitas.atencion.model.NotaClinica;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

public class Cita {

    private int idCita;
    private Date fecha;
    private EstadoCita estadoCita;
    private EstadoAtencion estadoAtencion;
    private String motivoConsulta;
    private HorarioTrabajo horarioTrabajo;
    private Paciente paciente;
    private NotaClinica notaClinica;
    private boolean activo;
    
    public Cita() {
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public EstadoAtencion getEstadoAtencion() {
        return estadoAtencion;
    }

    public void setEstadoAtencion(EstadoAtencion estadoAtencion) {
        this.estadoAtencion = estadoAtencion;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public HorarioTrabajo getHorarioTrabajo() {
        return horarioTrabajo;
    }

    public void setHorarioTrabajo(HorarioTrabajo horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public NotaClinica getNotaClinica() {
        return notaClinica;
    }

    public void setNotaClinica(NotaClinica notaClinica) {
        this.notaClinica = notaClinica;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
}
