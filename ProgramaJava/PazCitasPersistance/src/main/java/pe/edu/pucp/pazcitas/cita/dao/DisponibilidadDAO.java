/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.cita.dao;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.cita.model.Disponibilidad;
import pe.edu.pucp.pazcitas.dao.ICrud;

/**
 *
 * @author asant
 */
public interface DisponibilidadDAO extends ICrud<Disponibilidad>{
    public ArrayList<String> listarFechasDisponiblesPorMedico(int idMedico);
}
