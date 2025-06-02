/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.bo.TriajeBO;
import pe.edu.pucp.pazcitas.atencion.model.Triaje;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "TriajeWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class TriajeWS {

     private TriajeBO botriaje;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarTriaje")
    public ArrayList<Triaje> listarTriaje() {
        botriaje = new TriajeBO();
        return botriaje.listarTodos();
    }
    @WebMethod(operationName = "insertarTriaje")
    public int insertarTriaje(@WebParam(name = "triaje") Triaje triaje) {
        botriaje = new TriajeBO();
        return botriaje.insertar(triaje);
    }
    @WebMethod(operationName = "modificarTriaje")
    public int modificarTriaje(@WebParam(name = "triaje") Triaje triaje) {
        botriaje = new TriajeBO();
        return botriaje.modificar(triaje);
    }
    @WebMethod(operationName = "eliminarTriaje")
    public int eliminarTriaje(@WebParam(name = "idtriaje") int idtriaje) {
        botriaje = new TriajeBO();
        return botriaje.eliminar(idtriaje);
    }
}
