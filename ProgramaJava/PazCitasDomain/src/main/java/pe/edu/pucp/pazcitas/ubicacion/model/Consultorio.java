/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.ubicacion.model;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;

/**
 *
 * @author asant
 */
public class Consultorio {
    private int idConsultorio;
    private String nombreConsultorio;
    private int piso;
    private int capacidad;
    private boolean activo;
    private Sede sede;
    private ArrayList<TurnoMedico> turnosMedicos;

    public Consultorio() {
    }

    public int getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public String getNombreConsultorio() {
        return nombreConsultorio;
    }

    public void setNombreConsultorio(String nombreConsultorio) {
        this.nombreConsultorio = nombreConsultorio;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public ArrayList<TurnoMedico> getTurnosMedicos() {
        return turnosMedicos;
    }

    public void setTurnosMedicos(ArrayList<TurnoMedico> turnosMedicos) {
        this.turnosMedicos = turnosMedicos;
    }
    
    
}
