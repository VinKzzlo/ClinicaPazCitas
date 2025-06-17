/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.ubicacion.services;

import java.util.ArrayList;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.pazcitas.ubicacion.bo.SedeBO;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "SedeWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class SedeWS {

    private SedeBO bosede;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarSede")
    public ArrayList<Sede> listarSede() {
        bosede = new SedeBO();
        return bosede.listarTodos();
    }
    @WebMethod(operationName = "insertarSede")
    public int insertarSede(@WebParam(name = "Sede") Sede sede) {
        bosede = new SedeBO();
        return bosede.insertar(sede);
    }
    @WebMethod(operationName = "modificarSede")
    public int modificarSede(@WebParam(name = "Sede") Sede sede) {
        bosede = new SedeBO();
        return bosede.modificar(sede);
    }
    @WebMethod(operationName = "eliminarSede")
    public int eliminarSede(@WebParam(name = "idSede") int idSede) {
        bosede = new SedeBO();
        return bosede.eliminar(idSede);
    }
    
    @WebMethod(operationName = "obtenerSedePorId")
    public Sede obtenerSedePorId(@WebParam(name = "idSede")int idSede) {
        bosede = new SedeBO();
        return bosede.obtenerPorId(idSede);
    }
    @WebMethod(operationName = "listarXEspecialidad")
    public ArrayList<Sede>  listarXEspecialidad(@WebParam(name = "idEspecialidad") int idEspecialidad) {
        bosede = new SedeBO();
        return bosede.listarxEspecialidad(idEspecialidad);
    }
    
}
