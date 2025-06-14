/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.dao.RecetaDAO;
import pe.edu.pucp.pazcitas.atencion.impl.RecetaImpl;
import pe.edu.pucp.pazcitas.atencion.model.Receta;

/**
 *
 * @author asant
 */
public class RecetaBO {

    private final RecetaDAO daoReceta;
    
    public RecetaBO(){
        daoReceta = new RecetaImpl();
    }
    
    public int insertar(Receta receta){
        return daoReceta.insertar(receta);
    }
    
    public int modificar(Receta receta){
        return daoReceta.modificar(receta);
    }
    
    public ArrayList<Receta>listarTodas(){
        return daoReceta.listarTodos();
    }
    public int eliminar(int idReceta){
        return daoReceta.eliminar(idReceta);
    }
}
