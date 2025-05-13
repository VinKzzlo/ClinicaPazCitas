/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.financiero.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.financiero.dao.PagoDAO;
import pe.edu.pucp.pazcitas.financiero.impl.PagoImpl;
import pe.edu.pucp.pazcitas.financiero.model.Pago;

/**
 *
 * @author asant
 */
public class PagoBO {
    private final PagoDAO daoPago;
    
    public PagoBO(){
        daoPago = new PagoImpl();
    }
    
    public int insertar(Pago pago) {
        return daoPago.insertar(pago);
    }
    
    public ArrayList<Pago> listarTodos() {
        return daoPago.listarTodos();
    }
}
