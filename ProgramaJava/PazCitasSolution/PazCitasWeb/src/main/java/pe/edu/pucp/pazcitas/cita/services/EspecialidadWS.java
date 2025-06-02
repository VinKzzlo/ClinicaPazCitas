/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.bo.EspecialidadBO;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "EspecialidadWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class EspecialidadWS {

    private EspecialidadBO boespecialidad;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarEspecialidad")
    public ArrayList<Especialidad> listarEspecialidad() {
        boespecialidad = new EspecialidadBO();
        return boespecialidad.listarTodos();
    }
    @WebMethod(operationName = "insertarEspecialidad")
    public int insertarEspecialidad(@WebParam(name = "especialidad") Especialidad especialidad) {
        boespecialidad = new EspecialidadBO();
        return boespecialidad.insertar(especialidad);
    }
    @WebMethod(operationName = "modificarEspecialidad")
    public int modificarEspecialidad(@WebParam(name = "especialidad") Especialidad especialidad) {
        boespecialidad = new EspecialidadBO();
        return boespecialidad.modificar(especialidad);
    }
    @WebMethod(operationName = "eliminarEspecialidad")
    public int eliminarEspecialidad(@WebParam(name = "idepecialidad") int idepecialidad) {
        boespecialidad = new EspecialidadBO();
        return boespecialidad.eliminar(idepecialidad);
    }
}
