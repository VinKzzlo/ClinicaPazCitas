/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.dao.DisponibilidadDAO;
import pe.edu.pucp.pazcitas.cita.impl.DisponibilidadImpl;
import pe.edu.pucp.pazcitas.cita.model.Disponibilidad;

/**
 *
 * @author asant
 */
public class DisponibilidadBO {
    
    private final DisponibilidadDAO daoDispo;
    
    public DisponibilidadBO(){
        daoDispo = new DisponibilidadImpl();
    }
    
    public int insertar(Disponibilidad dispo){
        return daoDispo.insertar(dispo);
    }
    
    public int modificar(Disponibilidad disponibilidad) {
        return daoDispo.modificar(disponibilidad);
    }
    
    public ArrayList<Disponibilidad> listarTodos() {
        return daoDispo.listarTodos();
    }
    
    public ArrayList<String> listarFechasDisponiblesPorMedico(int idMedico) {
        return daoDispo.listarFechasDisponiblesPorMedico(idMedico);
    }
    
}
