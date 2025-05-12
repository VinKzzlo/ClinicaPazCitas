
package pe.edu.pucp.pazCitas.cita.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import pe.edu.pucp.pazCitas.atencion.model.NotaClinica;
import pe.edu.pucp.pazCitas.financiero.model.Pago;
import pe.edu.pucp.pazCitas.usuario.model.Recepcionista;

/**
 *
 * @author Joel
 */
public class Cita {
    private int idCita;
    private LocalDateTime fecha;
    private EstadoCita estadoCita;
    private String motivoConsulta;
    private LocalDateTime fechaActualizacion;
    
    private Disponibilidad disponibilidad;
    private Recepcionista recepcionista;
    private NotaClinica notaClinica;
    private Pago pago;
    private Especialidad especialidad;
    
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

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    
    
    
}
