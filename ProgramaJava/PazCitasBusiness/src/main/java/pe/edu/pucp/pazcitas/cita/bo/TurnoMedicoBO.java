/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.dao.TurnoMedicoDAO;
import pe.edu.pucp.pazcitas.cita.impl.TurnoMedicoImpl;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;

/**
 *
 * @author asant
 */
public class TurnoMedicoBO {

    private final TurnoMedicoDAO daoTurnoMedico;

    public TurnoMedicoBO() {
        daoTurnoMedico = new TurnoMedicoImpl();
    }
    
    public int insertar(TurnoMedico turno) {
        return daoTurnoMedico.insertar(turno);
    }
    
    public int modificar(TurnoMedico turno) {
        return daoTurnoMedico.modificar(turno);
    }
    
    public ArrayList<TurnoMedico> listarTodos() {
        return daoTurnoMedico.listarTodos();
    }
}
