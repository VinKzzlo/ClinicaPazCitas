/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazCitas.atencion.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazCitas.usuario.model.Paciente;

/**
 *
 * @author Joel
 */
public class HistorialMedico {
    private int idhistorial;
    private String notas;
    private LocalDate fechaActualizacion;
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

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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
    
    
}
