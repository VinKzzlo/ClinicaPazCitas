/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.bo.NotaClinicaBO;
import pe.edu.pucp.pazcitas.atencion.model.NotaClinica;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "NotaClinicaWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class NotaClinicaWS {

    private NotaClinicaBO bonotaclinica;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "listarNotaClinica")
    public ArrayList<NotaClinica> listarNotaClinica() {
        bonotaclinica = new NotaClinicaBO();
        return bonotaclinica.listarTodas();
    }
    @WebMethod(operationName = "insertarNotaClinica")
    public int insertarNotaClinica(@WebParam(name = "notaClinica") NotaClinica nota) {
        bonotaclinica = new NotaClinicaBO();
        return bonotaclinica.insertar(nota);
    }
    @WebMethod(operationName = "modificarNotaClinica")
    public int modificarNotaClinica(@WebParam(name = "notaClinica") NotaClinica nota) {
        bonotaclinica = new NotaClinicaBO();
        return bonotaclinica.modificar(nota);
    }
    
    @WebMethod(operationName = "listarNotaClinicaXHistorial")
    public ArrayList<NotaClinica> listarNotaClinicaXHistorial(@WebParam(name = "idhis") int idhis) {
        bonotaclinica = new NotaClinicaBO();
        return bonotaclinica.listarXHistorial(idhis);
    }
}
