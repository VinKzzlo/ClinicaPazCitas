/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.financiero.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.financiero.bo.PagoBO;
import pe.edu.pucp.pazcitas.financiero.model.Pago;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "PagoWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class PagoWS {

    private PagoBO bopago;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listarPago")
    public ArrayList<Pago> listarPago() {
        bopago = new PagoBO();
        return bopago.listarTodos();
    }
    @WebMethod(operationName = "insertarPago")
    public int insertarPago(@WebParam(name = "pago") Pago pago) {
        bopago = new PagoBO();
        return bopago.insertar(pago);
    }
    @WebMethod(operationName = "modificarPago")
    public int modificarPago(@WebParam(name = "pago") Pago pago) {
        bopago = new PagoBO();
        return bopago.modificar(pago);
    }
    @WebMethod(operationName = "eliminarPago")
    public int eliminarPago(@WebParam(name = "idpago") int idPago) {
        bopago = new PagoBO();
        return bopago.eliminar(idPago);
    }
}
