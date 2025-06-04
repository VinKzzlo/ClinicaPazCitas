/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.ubicacion.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.ubicacion.dao.SedeDAO;
import pe.edu.pucp.pazcitas.ubicacion.impl.SedeImpl;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;

/**
 *
 * @author asant
 */
public class SedeBO {
    
    private final SedeDAO daoSede;
    
    public SedeBO(){
        daoSede = new SedeImpl();
    }
    
    public int insertar(Sede sede) {
        return daoSede.insertar(sede);
    }
    
    public int modificar(Sede sede){
        return daoSede.modificar(sede);
    }
    
    public int eliminar(int idSede){
        return daoSede.eliminar(idSede);
    }
    
    public ArrayList<Sede> listarTodos() {
        return daoSede.listarTodos();
    }
    
    public Sede obtenerPorId(int idSede){
        return daoSede.obtenerPorId(idSede);
    }
    
}
