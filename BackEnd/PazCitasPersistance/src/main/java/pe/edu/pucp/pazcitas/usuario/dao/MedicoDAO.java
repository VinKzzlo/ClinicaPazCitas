/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.dao;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.dao.ICrud;
import pe.edu.pucp.pazcitas.usuario.model.Medico;

/**
 *
 * @author asant
 */
public interface MedicoDAO extends ICrud<Medico>{
    public ArrayList<Medico> listarXEspXSede(int idEsp, int idSede);
    public Medico obtenerPorID(int idMedico);
    public ArrayList<Medico> listarPorCadena(String cadena);
}
