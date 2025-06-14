/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.dao.CitaDAO;
import pe.edu.pucp.pazcitas.cita.impl.CitaImpl;
import pe.edu.pucp.pazcitas.cita.model.Cita;

/**
 *
 * @author asant
 */

public class CitaBO {
    
    
    private final CitaDAO daoCita;
    
    public CitaBO(){
        daoCita = new CitaImpl();
    }
    
    public int insertar(Cita cita){
        return daoCita.insertar(cita);
    }
    
    public int modificar(Cita cita){
        return daoCita.modificar(cita);
    }
    
    public int eliminar(int idCita){
        return daoCita.eliminar(idCita);
    }
    
    public ArrayList<Cita>listarTodos(){
        return daoCita.listarTodos();
    }
    
    public Cita obtnerXId(int idCita){
        return daoCita.obtenerPorId(idCita);
    }
            
}
