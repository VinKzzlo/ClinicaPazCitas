/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.model;

import java.util.Date;

public class Triaje {
    private int idTriaje;
    private double peso;
    private double altura;
    private String presionArterial;
    private double temperatura;
    private Date fechaRegistro;
    private boolean activo;
    private NotaClinica notaClinica;

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

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    

       
}
