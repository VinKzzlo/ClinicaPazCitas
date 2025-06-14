/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.dao.TurnoDAO;
import pe.edu.pucp.pazcitas.cita.impl.TurnoImpl;
import pe.edu.pucp.pazcitas.cita.model.Turno;

/**
 *
 * @author Joel
 */
public class TurnoBO {
    private final TurnoDAO daoTurno;
    
    public TurnoBO(){
        daoTurno = new TurnoImpl();
    }
    
    public int insertar(Turno turno){
        return daoTurno.insertar(turno);
    }
    public int modificar(Turno turno){
        return daoTurno.modificar(turno);
    }
    public int eliminar(int idTurno){
        return daoTurno.eliminar(idTurno);
    }
    public ArrayList<Turno> listarTodos(){
        return daoTurno.listarTodos();
    }
}
