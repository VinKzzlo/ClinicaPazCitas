package pe.edu.pucp.pazcitas.cita.model;


import java.util.Date;
import pe.edu.pucp.pazcitas.atencion.model.NotaClinica;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;
import pe.edu.pucp.pazcitas.usuario.model.Recepcionista;

public class Cita {

    private int idCita;
    private Date fecha;
    private EstadoCita estadoCita;
    private String motivoConsulta;
    private Date fechaActualizacion;

    private Disponibilidad disponibilidad;
    private Recepcionista recepcionista;
    private NotaClinica notaClinica;
    private Especialidad especialidad;
    private Paciente paciente;

    public Cita() {
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    public NotaClinica getNotaClinica() {
        return notaClinica;
    }

    public void setNotaClinica(NotaClinica notaClinica) {
        this.notaClinica = notaClinica;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

}
