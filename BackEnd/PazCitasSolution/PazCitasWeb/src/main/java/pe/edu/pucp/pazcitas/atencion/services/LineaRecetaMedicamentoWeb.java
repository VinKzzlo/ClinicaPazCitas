/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.pazcitas.atencion.bo.LineaRecetaMedicamentoBO;
import pe.edu.pucp.pazcitas.atencion.model.LineaRecetaMedicamento;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "LineaRecetaMedicamentoWeb",
        targetNamespace = "http://services.pucp.edu.pe")
public class LineaRecetaMedicamentoWeb {
    
    private LineaRecetaMedicamentoBO bolinea;
    
    @WebMethod(operationName = "insertarLineaReceta")
    public int insertarLineaReceta(@WebParam(name = "linea") LineaRecetaMedicamento linea) {
        bolinea = new LineaRecetaMedicamentoBO();
        return bolinea.insertar(linea);
    }
    @WebMethod(operationName = "eliminarLineaReceta")
    public int eliminarLineaReceta(@WebParam(name = "idLinea") int idLinea) {
        bolinea = new LineaRecetaMedicamentoBO();
        return bolinea.eliminar(idLinea);
    }
}
