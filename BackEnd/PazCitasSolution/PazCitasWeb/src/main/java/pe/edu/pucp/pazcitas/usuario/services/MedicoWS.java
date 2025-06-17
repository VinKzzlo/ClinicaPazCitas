/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.bo.MedicoBO;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "MedicoWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class MedicoWS {

    private MedicoBO bomedico;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "listarMedico")
    public ArrayList<Medico> listarMedico() {
        bomedico = new MedicoBO();
        return bomedico.listarTodos();
    }
    @WebMethod(operationName = "insertarMedico")
    public int insertarMedico(@WebParam(name = "medico") Medico medico) {
        bomedico = new MedicoBO();
        return bomedico.insertar(medico);
    }
    @WebMethod(operationName = "modificarMedico")
    public int modificarMedico(@WebParam(name = "medico") Medico medico) {
        bomedico = new MedicoBO();
        return bomedico.modificar(medico);
    }
    @WebMethod(operationName = "eliminarMedico")
    public int eliminarMedico(@WebParam(name = "idmedico") int idmedico) {
        bomedico = new MedicoBO();
        return bomedico.eliminar(idmedico);
    }
    @WebMethod(operationName = "obtenerMedico")
    public Medico obtenerMedico(@WebParam(name = "idmedico") int idmedico) {
        bomedico = new MedicoBO();
        return bomedico.obtenerXId(idmedico);
    }
    @WebMethod(operationName = "listarMedicoXEspXSede")
    public ArrayList<Medico> listarMedicoXEspXSede(@WebParam(name = "idSede") int idSede, 
            @WebParam(name = "idEsp") int idEsp) {
        bomedico = new MedicoBO();
        return bomedico.listarxEspecialidadxSede(idSede, idEsp);
    }
}
