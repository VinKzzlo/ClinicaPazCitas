/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.financiero.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.financiero.bo.SeguroBO;
import pe.edu.pucp.pazcitas.financiero.model.Seguro;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "SeguroWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class SeguroWS {

    private SeguroBO boseguro;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarSeguro")
    public ArrayList<Seguro> listarSeguro() {
        boseguro = new SeguroBO();
        return boseguro.listarTodos();
    }
    @WebMethod(operationName = "insertarSeguro")
    public int insertarSeguro(@WebParam(name = "seguro") Seguro seguro) {
        boseguro = new SeguroBO();
        return boseguro.insertar(seguro);
    }
    @WebMethod(operationName = "modificarSeguro")
    public int modificarSeguro(@WebParam(name = "seguro") Seguro seguro) {
        boseguro = new SeguroBO();
        return boseguro.modificar(seguro);
    }
    @WebMethod(operationName = "eliminarSeguro")
    public int eliminarSeguro(@WebParam(name = "idseguro") int idSeguro) {
        boseguro = new SeguroBO();
        return boseguro.eliminar(idSeguro);
    }
}
