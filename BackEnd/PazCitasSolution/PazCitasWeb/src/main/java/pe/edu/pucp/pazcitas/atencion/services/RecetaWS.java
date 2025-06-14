/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.bo.RecetaBO;
import pe.edu.pucp.pazcitas.atencion.model.Receta;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "RecetaWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class RecetaWS {

    private RecetaBO boreceta;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "listarReceta")
    public ArrayList<Receta> listarReceta() {
        boreceta = new RecetaBO();
        return boreceta.listarTodas();
    }
    @WebMethod(operationName = "insertarReceta")
    public int insertarReceta(@WebParam(name = "receta") Receta receta) {
        boreceta = new RecetaBO();
        return boreceta.insertar(receta);
    }
    @WebMethod(operationName = "modificarReceta")
    public int modificarReceta(@WebParam(name = "receta") Receta receta) {
        boreceta = new RecetaBO();
        return boreceta.modificar(receta);
    }
}
