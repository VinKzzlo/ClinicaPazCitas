/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazcitas.cita.bo.CitaBO;
import pe.edu.pucp.pazcitas.cita.model.Cita;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "CitaWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class CitaWS {

    private CitaBO bocita;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarCita")
    public ArrayList<Cita> listarCita() {
        bocita = new CitaBO();
        return bocita.listarTodos();
    }

    @WebMethod(operationName = "insertarCita")
    public int insertarCita(@WebParam(name = "cita") Cita cita) {
        bocita = new CitaBO();
        return bocita.insertar(cita);
    }

    @WebMethod(operationName = "modificarCita")
    public int modificarCita(@WebParam(name = "cita") Cita cita) {
        bocita = new CitaBO();
        return bocita.modificar(cita);
    }

    @WebMethod(operationName = "eliminarCita")
    public int eliminarCita(@WebParam(name = "idcita") int idcita) {
        bocita = new CitaBO();
        return bocita.eliminar(idcita);
    }

    @WebMethod(operationName = "obtenerCita")
    public Cita obtenerCita(@WebParam(name = "idcita") int idcita) {
        bocita = new CitaBO();
        return bocita.obtnerXId(idcita);
    }

    @WebMethod(operationName = "listarXPaciente")
    public ArrayList<Cita> listarXPaciente(@WebParam(name = "idPaciente") int idPaciente) {
        bocita = new CitaBO();
        return bocita.listarXPaciente(idPaciente);
    }

    @WebMethod(operationName = "listaCitaPorMedico")
    public ArrayList<Cita> listaCitaPorMedico(@WebParam(name = "idMed") int idMed) {
        bocita = new CitaBO();
        return bocita.listarXMedico(idMed);
    }

    @WebMethod(operationName = "listaCitaPorMedicoXestado")
    public ArrayList<Cita> listaCitaPorMedicoXestado(@WebParam(name = "idMed") int idMed,
            @WebParam(name = "estado") String estado) {
        bocita = new CitaBO();
        return bocita.listarXMedicoXEstado(idMed, estado);
    }

    @WebMethod(operationName = "listarXPacienteCompletoSinDatosPaciente")
    public ArrayList<Cita> listarXPacienteCompletoSinDatosPaciente(@WebParam(name = "idPaciente") int idPaciente) {
        bocita = new CitaBO();
        return bocita.listarXPacienteCompletoSinDatosPaciente(idPaciente);
    }

    @WebMethod(operationName = "obtenerXIdCompletoSinDatosPaciente")
    public Cita obtenerXIdCompletoSinDatosPaciente(@WebParam(name = "idCita") int idCita) {
        bocita = new CitaBO();
        return bocita.obtenerXIdCompleSinDatosPaciente(idCita);
    }

    @WebMethod(operationName = "modificarFechaCita")
    public int modificarFechaCita(@WebParam(name = "idCita") int idCita, @WebParam(name = "fecha") Date fecha, @WebParam(name = "hora") Date hora, @WebParam(name = "idMedico") int idMedico) {
        bocita = new CitaBO();
        Time horaSQL = new Time(hora.getTime());
        return bocita.modificarFechaCita(idCita, fecha, horaSQL, idMedico);
    }
}
