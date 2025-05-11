/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazCitas.usuario.model;

import java.util.ArrayList;
import pe.edu.pucp.pazCitas.atencion.model.Triaje;
import pe.edu.pucp.pazCitas.ubicacion.model.Sede;

/**
 *
 * @author Joel
 */
public class AsistenteMedico extends Usuario{
    private String codigoPersonal;
    private boolean activo;
    private Medico medico;
    private Sede sede;
    private ArrayList<Triaje> triajes;

    public AsistenteMedico() {
    }

    public String getCodigoPersonal() {
        return codigoPersonal;
    }

    public void setCodigoPersonal(String codigoPersonal) {
        this.codigoPersonal = codigoPersonal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public ArrayList<Triaje> getTriajes() {
        return triajes;
    }

    public void setTriajes(ArrayList<Triaje> triajes) {
        this.triajes = triajes;
    }
    
    
}
