/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.ubicacion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.ubicacion.bo.ConsultorioBO;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "ConsultorioWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class ConsultorioWS {

     private ConsultorioBO boconsultorio;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarConsultorio")
    public ArrayList<Consultorio> listarConsultorio() {
        boconsultorio = new ConsultorioBO();
        return boconsultorio.listarTodos();
    }
    @WebMethod(operationName = "insertarConsultorio")
    public int insertarConsultorio(@WebParam(name = "Consultorio") Consultorio consultorio) {
        boconsultorio = new ConsultorioBO();
        return boconsultorio.insertar(consultorio);
    }
    @WebMethod(operationName = "modificarConsultorio")
    public int modificarConsultorio(@WebParam(name = "Consultorio") Consultorio consultorio) {
        boconsultorio = new ConsultorioBO();
        return boconsultorio.modificar(consultorio);
    }
    @WebMethod(operationName = "eliminarConsultorio")
    public int eliminarConsultorio(@WebParam(name = "idConsultorio") int idconsultorio) {
        boconsultorio = new ConsultorioBO();
        return boconsultorio.eliminar(idconsultorio);
    }
    
    @WebMethod(operationName = "obtenerConsultorioPorId")
    public Consultorio obtenerConsultorioPorId(@WebParam(name = "idConsultorio") int idConsultorio) {
        boconsultorio = new ConsultorioBO();
        return boconsultorio.obtenerPorId(idConsultorio);
    }
    
    @WebMethod(operationName = "listarConsultPorSede")
    public ArrayList<Consultorio> listarConsulPorSede(@WebParam(name = "idSede")int idSede){
        boconsultorio = new ConsultorioBO();
        return boconsultorio.listarXSede(idSede);
    }
    
    @WebMethod(operationName = "listarConsulPorSedeNoAsig")
    public ArrayList<Consultorio> listarConsulPorSedeNoAsig(@WebParam(name = "idSede")int idSede){
        boconsultorio = new ConsultorioBO();
        return boconsultorio.listarXSedeNoAsignados(idSede);
    }
    
    @WebMethod(operationName = "marcarAsignado")
    public int marcarAsignado(@WebParam(name = "idConsultorio") int idconsultorio) {
        boconsultorio = new ConsultorioBO();
        return boconsultorio.marcarAsignado(idconsultorio);
    }
    
    @WebMethod(operationName = "marcarNoAsignado")
    public int marcarNoAsignado(@WebParam(name = "idConsultorio") int idconsultorio) {
        boconsultorio = new ConsultorioBO();
        return boconsultorio.marcarNoAsignado(idconsultorio);
    }
    
    @WebMethod(operationName = "consultorioExiste")
    public boolean consultorioExiste(@WebParam(name = "nombre") String nombre,
            @WebParam(name = "idSede") int idSede) {
        boconsultorio = new ConsultorioBO();
        return boconsultorio.consultorioExisteEnSede(nombre, idSede);
    }
    
}
