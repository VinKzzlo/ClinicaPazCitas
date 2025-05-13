/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.financiero.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.financiero.dao.SeguroDAO;
import pe.edu.pucp.pazcitas.financiero.impl.SeguroImpl;
import pe.edu.pucp.pazcitas.financiero.model.Seguro;

/**
 *
 * @author asant
 */
public class SeguroBO {

    private final SeguroDAO daoSeguro;

    public SeguroBO() {
        daoSeguro = new SeguroImpl();
    }
    
    public int insertar(Seguro seguro) {
        return daoSeguro.insertar(seguro);
    }
    
    public ArrayList<Seguro> listarTodos() {
        return daoSeguro.listarTodos();
    }
    
}
