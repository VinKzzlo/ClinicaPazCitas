/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.bo.AdministradorBO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;


/**
 *
 * @author Joel
 */
@WebService(serviceName = "AdministradorWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class AdministradorWS {
    private AdministradorBO boadministrador;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarAdministrador")
    public ArrayList<Administrador> listarAdministrador() {
        boadministrador = new AdministradorBO();
        return boadministrador.listarTodos();
    }
    @WebMethod(operationName = "insertarAdministrador")
    public int insertarAdministrador(@WebParam(name = "administrador") Administrador administrador) {
        boadministrador = new AdministradorBO();
        return boadministrador.insertar(administrador);
    }
    @WebMethod(operationName = "modificarAdministrador")
    public int modificarAdministrador(@WebParam(name = "administrador") Administrador administrador) {
        boadministrador = new AdministradorBO();
        return boadministrador.modificar(administrador);
    }
    @WebMethod(operationName = "eliminarAdministrador")
    public int eliminarAdministrador(@WebParam(name = "idadministrador") int idAdmi) {
        boadministrador = new AdministradorBO();
        return boadministrador.eliminar(idAdmi);
    }
    
    @WebMethod(operationName = "obtenerPorIDAdministrador")
    public Administrador obtenerPorIDAdministrador(@WebParam(name = "idAdmin") int idAdmin){
        boadministrador = new AdministradorBO();
        return boadministrador.obtenerPorID(idAdmin);
    }
}
