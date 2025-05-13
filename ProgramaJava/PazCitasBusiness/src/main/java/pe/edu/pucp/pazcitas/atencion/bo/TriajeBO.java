/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.dao.TriajeDAO;
import pe.edu.pucp.pazcitas.atencion.impl.TriajeImpl;
import pe.edu.pucp.pazcitas.atencion.model.Triaje;

/**
 *
 * @author asant
 */
public class TriajeBO {
    
    private final TriajeDAO daoTriaje;
    
    public TriajeBO(){
        daoTriaje = new TriajeImpl();
    }
    
    public int insertar(Triaje triaje){
        return daoTriaje.insertar(triaje);
    }
    
    public int eliminar(int idTriaje){
        return daoTriaje.eliminar(idTriaje);
    }
    
    public int modificar(Triaje triaje){
        return daoTriaje.modificar(triaje);
    }
    
    public ArrayList<Triaje> listarTodos(){
        return daoTriaje.listarTodos();
    }
    
}
