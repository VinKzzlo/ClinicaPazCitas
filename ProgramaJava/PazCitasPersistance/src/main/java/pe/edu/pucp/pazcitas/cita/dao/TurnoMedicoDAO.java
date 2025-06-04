/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.dao;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;
import pe.edu.pucp.pazcitas.dao.ICrud;

/**
 *
 * @author asant
 */
public interface TurnoMedicoDAO extends ICrud<TurnoMedico>{
    public ArrayList<String> listarDiasAtencionPorMedico(int idMedico);
    public ArrayList<String> listarHorariosPorMedicoYDia(int idMedico, String dia);
}
