/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.model;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;

public class Medico extends Usuario {
    private String codigoMedico;
    private boolean activo;
    private ArrayList<TurnoMedico> turnosMedico;
    private Sede sede;
    private ArrayList<Especialidad> especialidades;
    private AsistenteMedico asistenteMedico;
    
    
    public Medico() {
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<TurnoMedico> getTurnosMedico() {
        return turnosMedico;
    }

    public void setTurnosMedico(ArrayList<TurnoMedico> turnosMedico) {
        this.turnosMedico = turnosMedico;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public AsistenteMedico getAsistenteMedico() {
        return asistenteMedico;
    }

    public void setAsistenteMedico(AsistenteMedico asistenteMedico) {
        this.asistenteMedico = asistenteMedico;
    }
    
}
