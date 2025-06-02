/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.financiero.model;

import java.util.Date;
import pe.edu.pucp.pazcitas.cita.model.Cita;

/**
 *
 * @author asant
 */
public class Pago {
    private int idPago;
    private Date fechaPago;
    private double montoTotal;
    private double montoCubiertoSeguro;
    private double montoSubtotal;
    private Seguro seguro;
    private EstadoPago estado;
    private Cita cita;

    public Pago() {
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoCubiertoSeguro() {
        return montoCubiertoSeguro;
    }

    public void setMontoCubiertoSeguro(double montoCubiertoSeguro) {
        this.montoCubiertoSeguro = montoCubiertoSeguro;
    }

    public double getMontoSubtotal() {
        return montoSubtotal;
    }

    public void setMontoSubtotal(double montoSubtotal) {
        this.montoSubtotal = montoSubtotal;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

}
