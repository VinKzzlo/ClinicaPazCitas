/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazCitas.atencion.model;

import java.time.LocalDate;
import java.util.Date;
import pe.edu.pucp.pazCitas.usuario.model.AsistenteMedico;

/**
 *
 * @author Joel
 */
public class Triaje {
    private int idTriaje;
    private double peso;
    private double altura;
    private String presionArterial;
    private int temperatura;
    private LocalDate fechaRegistro;
    private String prioridad;
    private boolean activo;
    private NotaClinica notaClinica;
    private AsistenteMedico asistenteMedico;

    public Triaje() {
    }

    public int getIdTriaje() {
        return idTriaje;
    }

    public void setIdTriaje(int idTriaje) {
        this.idTriaje = idTriaje;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

   

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public NotaClinica getNotaClinica() {
        return notaClinica;
    }

    public void setNotaClinica(NotaClinica notaClinica) {
        this.notaClinica = notaClinica;
    }

    public AsistenteMedico getAsistenteMedico() {
        return asistenteMedico;
    }

    public void setAsistenteMedico(AsistenteMedico asistenteMedico) {
        this.asistenteMedico = asistenteMedico;
    }
    
    
            
}
