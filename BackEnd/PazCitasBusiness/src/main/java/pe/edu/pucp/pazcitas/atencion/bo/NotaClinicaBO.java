/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.dao.NotaClinicaDAO;
import pe.edu.pucp.pazcitas.atencion.impl.NotaClinicaImpl;
import pe.edu.pucp.pazcitas.atencion.model.NotaClinica;

/**
 *
 * @author asant
 */
public class NotaClinicaBO {
    
    private final NotaClinicaDAO daoNotaClinica;
    
    public NotaClinicaBO(){
        daoNotaClinica = new NotaClinicaImpl();
    }
    
    public int insertar(NotaClinica nota){
        return daoNotaClinica.insertar(nota);
    }
    
    public int modificar(NotaClinica nota){
        return daoNotaClinica.modificar(nota);
    }
    
    public ArrayList<NotaClinica>listarTodas(){
        return daoNotaClinica.listarTodos();
    }
    
    public ArrayList<NotaClinica>listarXHistorial(int idHis){
        return daoNotaClinica.listarXhistorial(idHis);
    }
    
}
