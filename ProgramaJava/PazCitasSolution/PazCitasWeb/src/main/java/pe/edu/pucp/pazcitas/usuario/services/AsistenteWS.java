/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.bo.AsistenteBO;
import pe.edu.pucp.pazcitas.usuario.model.AsistenteMedico;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "AsistenteWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class AsistenteWS {

    /**
     * This is a sample web service operation
     */
    private AsistenteBO boasistente;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "listarAsistente")
    public ArrayList<AsistenteMedico> listarAsistente() {
        boasistente = new AsistenteBO();
        return boasistente.listarTodos();
    }
    @WebMethod(operationName = "insertarAsistente")
    public int insertarAsistente(@WebParam(name = "asistente") AsistenteMedico asistente) {
        boasistente = new AsistenteBO();
        return boasistente.insertar(asistente);
    }
    @WebMethod(operationName = "modificarAsistente")
    public int modificarAsistente(@WebParam(name = "asistente") AsistenteMedico asistente) {
        boasistente = new AsistenteBO();
        return boasistente.modificar(asistente);
    }
    @WebMethod(operationName = "eliminarAsistente")
    public int eliminarAsistente(@WebParam(name = "idasistente") int idasistente) {
        boasistente = new AsistenteBO();
        return boasistente.eliminar(idasistente);
    }
}
