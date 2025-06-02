/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.bo.PacienteBO;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;


/**
 *
 * @author Joel
 */
@WebService(serviceName = "PacienteWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class PacienteWS {

    private PacienteBO bopaciente;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "listarPaciente")
    public ArrayList<Paciente> listarPaciente() {
        bopaciente = new PacienteBO();
        return bopaciente.listarTodos();
    }
    @WebMethod(operationName = "insertarPaciente")
    public int insertarPaciente(@WebParam(name = "paciente") Paciente paciente) {
        bopaciente = new PacienteBO();
        return bopaciente.insertar(paciente);
    }
    @WebMethod(operationName = "modificarPaciente")
    public int modificarPaciente(@WebParam(name = "paciente") Paciente paciente) {
        bopaciente = new PacienteBO();
        return bopaciente.modificar(paciente);
    }
    @WebMethod(operationName = "eliminarPaciente")
    public int eliminarPaciente(@WebParam(name = "idpaciente") int idpaciente) {
        bopaciente = new PacienteBO();
        return bopaciente.eliminar(idpaciente);
    }
}
