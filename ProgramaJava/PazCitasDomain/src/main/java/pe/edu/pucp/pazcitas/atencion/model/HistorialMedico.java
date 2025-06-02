/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.model;


import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

public class HistorialMedico {
    private int idhistorial;
    private Date fechaActualizacion;
    private boolean activo;
    private ArrayList<NotaClinica> notasClinicas;
    private Paciente paciente;

    public HistorialMedico() {
    }

    public int getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(int idhistorial) {
        this.idhistorial = idhistorial;
    }


    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<NotaClinica> getNotasClinicas() {
        return notasClinicas;
    }

    public void setNotasClinicas(ArrayList<NotaClinica> notasClinicas) {
        this.notasClinicas = notasClinicas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    
}

