/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.bo.MedicamentoBO;
import pe.edu.pucp.pazcitas.atencion.model.Medicamento;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "MedicamentoWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class MedicamentoWS {

    private MedicamentoBO bomedicamento;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "listarMedicamento")
    public ArrayList<Medicamento> listarMedicamento() {
        bomedicamento = new MedicamentoBO();
        return bomedicamento.listarTodos();
    }
    @WebMethod(operationName = "insertarMedicamento")
    public int insertarMedicamento(@WebParam(name = "medicamento") Medicamento medicamento) {
        bomedicamento = new MedicamentoBO();
        return bomedicamento.insertar(medicamento);
    }
    @WebMethod(operationName = "modificarMedicamento")
    public int modificarMedicamento(@WebParam(name = "medicamento") Medicamento medicamento) {
        bomedicamento = new MedicamentoBO();
        return bomedicamento.modificar(medicamento);
    }
    
    @WebMethod(operationName = "eliminarMedicamento")
    public int eliminarMedicamento(@WebParam(name="idMedicamento")int idMedicamento){
        bomedicamento = new MedicamentoBO();
        return bomedicamento.eliminar(idMedicamento);
    }
    
    @WebMethod(operationName = "listarMedicamentoXnombre")
    public ArrayList<Medicamento> listarMedicamentoXnombre(@WebParam(name = "nombre") String nombre) {
        bomedicamento = new MedicamentoBO();
        return bomedicamento.listarXNombre(nombre);
    }
}
