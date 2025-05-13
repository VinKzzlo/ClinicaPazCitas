/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.dao.HistorialMedicoDAO;
import pe.edu.pucp.pazcitas.atencion.impl.HistorialMedicoImpl;
import pe.edu.pucp.pazcitas.atencion.model.HistorialMedico;

/**
 *
 * @author asant
 */
public class HistorialMedicoBO {

    private final HistorialMedicoDAO daoHistorial;

    public HistorialMedicoBO() {
        daoHistorial = new HistorialMedicoImpl();
    }
    
    public int insertar(HistorialMedico historial){
        return daoHistorial.insertar(historial);
    }
    
    public int eliminar(int idHistorial){
        return daoHistorial.eliminar(idHistorial);
    }
    
    public ArrayList<HistorialMedico> listarTodos(){
        return daoHistorial.listarTodos();
    }
}
