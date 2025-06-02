/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.bo.DisponibilidadBO;
import pe.edu.pucp.pazcitas.cita.model.Disponibilidad;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "DisponibilidadWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class DisponibilidadWS {

    private DisponibilidadBO bodisponibilidad;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarDisponibilidad")
    public ArrayList<Disponibilidad> listarDisponibilidad() {
        bodisponibilidad = new DisponibilidadBO();
        return bodisponibilidad.listarTodos();
    }
    @WebMethod(operationName = "insertarDisponibilidad")
    public int insertarDisponibilidad(@WebParam(name = "disponibilidad") Disponibilidad disponibilidad) {
        bodisponibilidad = new DisponibilidadBO();
        return bodisponibilidad.insertar(disponibilidad);
    }
    @WebMethod(operationName = "modificarDisponibilidad")
    public int modificarDisponibilidad(@WebParam(name = "disponibilidad") Disponibilidad disponibilidad) {
        bodisponibilidad = new DisponibilidadBO();
        return bodisponibilidad.modificar(disponibilidad);
    }
    
}
