/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.ubicacion.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.ubicacion.dao.ConsultorioDAO;
import pe.edu.pucp.pazcitas.ubicacion.impl.ConsultorioImpl;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;

/**
 *
 * @author asant
 */
public class ConsultorioBO {
    private final ConsultorioDAO daoConsultorio;
    
    public ConsultorioBO(){
        daoConsultorio = new ConsultorioImpl();
    }
    
    public int insertar(Consultorio consultorio) {
        return daoConsultorio.insertar(consultorio);
    }
    
    public int modificar(Consultorio consultorio) {
        return daoConsultorio.modificar(consultorio);
    }
    
    public int eliminar(int idHincha) {
        return daoConsultorio.eliminar(idHincha);
    }
    
    public ArrayList<Consultorio> listarTodos() {
        return daoConsultorio.listarTodos();
    }
    
    public Consultorio obtenerPorId(int idConsultorio){
        return daoConsultorio.obtenerPorId(idConsultorio);
    }
}
