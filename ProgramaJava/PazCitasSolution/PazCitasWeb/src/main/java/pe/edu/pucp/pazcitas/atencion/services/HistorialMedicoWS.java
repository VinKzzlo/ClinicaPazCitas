/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.bo.HistorialMedicoBO;
import pe.edu.pucp.pazcitas.atencion.model.HistorialMedico;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "HistorialMedicoWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class HistorialMedicoWS {
    
    private HistorialMedicoBO bohistorial;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "listarHistorial")
    public ArrayList<HistorialMedico> listarHistorial() {
        bohistorial = new HistorialMedicoBO();
        return bohistorial.listarTodos();
    }
    @WebMethod(operationName = "insertarHistorial")
    public int insertarHistorial(@WebParam(name = "historial") HistorialMedico historial) {
        bohistorial = new HistorialMedicoBO();
        return bohistorial.insertar(historial);
    }
    @WebMethod(operationName = "eliminarHistorial")
    public int eliminarHistorial(@WebParam(name = "historial") int idhistorial) {
        bohistorial = new HistorialMedicoBO();
        return bohistorial.eliminar(idhistorial);
    }
}
