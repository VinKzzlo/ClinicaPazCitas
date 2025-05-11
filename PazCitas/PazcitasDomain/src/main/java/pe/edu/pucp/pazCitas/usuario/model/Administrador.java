/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazCitas.usuario.model;

import java.util.ArrayList;
import pe.edu.pucp.pazCitas.financiero.model.Pago;
import pe.edu.pucp.pazCitas.ubicacion.model.Sede;

/**
 *
 * @author Joel
 */
public class Administrador extends Usuario{
    private boolean activo;
    private Sede sede;
    private ArrayList<Pago> pagos;

    public Administrador() {
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

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }
    
    
}
