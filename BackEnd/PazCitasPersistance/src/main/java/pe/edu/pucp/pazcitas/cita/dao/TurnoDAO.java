/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.dao;

import pe.edu.pucp.pazcitas.cita.model.Turno;
import pe.edu.pucp.pazcitas.dao.ICrud;

/**
 *
 * @author Joel
 */
public interface TurnoDAO extends ICrud<Turno>{
    public Turno obtenerPorId(int idTurno);
}
