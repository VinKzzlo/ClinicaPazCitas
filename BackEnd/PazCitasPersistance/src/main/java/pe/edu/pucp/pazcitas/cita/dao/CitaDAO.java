/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.dao;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.dao.ICrud;

/**
 *
 * @author asant
 */
public interface CitaDAO extends ICrud<Cita>{
    public Cita obtenerPorId(int idCita);
    public ArrayList<Cita> obtenerCitaPorPaciente(int idPaciente);
}
