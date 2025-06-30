/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.ubicacion.dao;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.dao.ICrud;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;

/**
 *
 * @author asant
 */
public interface ConsultorioDAO extends ICrud<Consultorio>{
    Consultorio obtenerPorId(int idConsultorio);
    ArrayList<Consultorio> listarConsultoriosXSede(int idSede);
    ArrayList<Consultorio> listarConsultoriosXSedeNoAsignados(int idSede);
    int marcarAsignado(int idConsultorio);
    int marcarNoAsignado(int idConsultorio);
    int verificarExisteEnSede(String nombreConsultorio, int idSede);
}
