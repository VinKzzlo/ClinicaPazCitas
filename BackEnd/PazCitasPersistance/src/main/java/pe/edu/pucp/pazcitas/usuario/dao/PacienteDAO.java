/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.dao;

import pe.edu.pucp.pazcitas.dao.ICrud;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

/**
 *
 * @author asant
 */
public interface PacienteDAO extends ICrud<Paciente>{
    public Paciente obtenerPorID(int idPaciente);
    public Paciente obtenerPorDNI(String dni);
}
