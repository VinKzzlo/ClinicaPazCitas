/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.dao.EspecialidadDAO;
import pe.edu.pucp.pazcitas.cita.impl.EspecialidadImpl;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;

/**
 *
 * @author asant
 */
public class EspecialidadBO {
    
    private final EspecialidadDAO daoEspecialidad;
    
    public EspecialidadBO(){
        daoEspecialidad = new EspecialidadImpl();
    }
    
    public int insertar(Especialidad especialidad) {
        return daoEspecialidad.insertar(especialidad);
    }
    
    public int eliminar(int idEspecialidad) {
        return daoEspecialidad.eliminar(idEspecialidad);
    }
    
    public ArrayList<Especialidad> listarTodos() {
        return daoEspecialidad.listarTodos();
    }
    
}
