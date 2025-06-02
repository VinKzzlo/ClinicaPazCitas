/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.bo.RecepcionistaBO;
import pe.edu.pucp.pazcitas.usuario.model.Recepcionista;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "RecepcionistaWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class RecepcionistaWS {

    private RecepcionistaBO borecepcionista;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "listarRecepcionista")
    public ArrayList<Recepcionista> listarRecepcionista() {
        borecepcionista = new RecepcionistaBO();
        return borecepcionista.listarTodos();
    }
    @WebMethod(operationName = "insertarRecepcionista")
    public int insertarRecepcionista(@WebParam(name = "recepcionista") Recepcionista recepcionista) {
        borecepcionista = new RecepcionistaBO();
        return borecepcionista.insertar(recepcionista);
    }
    @WebMethod(operationName = "modificarRecepcionista")
    public int modificarRecepcionista(@WebParam(name = "recepcionista") Recepcionista recepcionista) {
        borecepcionista = new RecepcionistaBO();
        return borecepcionista.modificar(recepcionista);
    }
    @WebMethod(operationName = "eliminarRecepcionista")
    public int eliminarRecepcionista(@WebParam(name = "idRecepcionista") int idRecepcionista) {
        borecepcionista = new RecepcionistaBO();
        return borecepcionista.eliminar(idRecepcionista);
    }
}
