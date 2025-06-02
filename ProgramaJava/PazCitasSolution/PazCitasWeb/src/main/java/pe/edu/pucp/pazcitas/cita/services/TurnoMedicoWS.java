/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.bo.TurnoMedicoBO;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "TurnoMedicoWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class TurnoMedicoWS {

    private TurnoMedicoBO boturno;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarTurnoMedico")
    public ArrayList<TurnoMedico> listarTurnoMedico() {
        boturno = new TurnoMedicoBO();
        return boturno.listarTodos();
    }
    @WebMethod(operationName = "insertarTurnoMedico")
    public int insertarTurnoMedico(@WebParam(name = "disponibilidad") TurnoMedico turno) {
        boturno = new TurnoMedicoBO();
        return boturno.insertar(turno);
    }
    @WebMethod(operationName = "modificarTurnoMedico")
    public int modificarTurnoMedico(@WebParam(name = "disponibilidad") TurnoMedico turno) {
        boturno = new TurnoMedicoBO();
        return boturno.modificar(turno);
    }
}
