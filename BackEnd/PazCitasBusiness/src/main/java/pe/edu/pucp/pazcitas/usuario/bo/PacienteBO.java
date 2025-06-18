/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.dao.PacienteDAO;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;
import pe.edu.pucp.pazcitas.usuario.impl.PacienteImpl;


/**
 *
 * @author Joel
 */
public class PacienteBO {
    private  PacienteDAO daoPaciente;
        public PacienteBO()
        {
            daoPaciente = new PacienteImpl();
        }
        public int insertar(Paciente paciente)
        {
            return daoPaciente.insertar(paciente);
        }
        
        public int eliminar(int idPaciente)
        {
            return daoPaciente.eliminar(idPaciente);
        }
        public int modificar(Paciente paciente){
            return daoPaciente.modificar(paciente);
        }
        public ArrayList<Paciente> listarTodos()
        {
            return daoPaciente.listarTodos();
        }
        
        public Paciente obtenerXId(int idPaciente)
        {
            return daoPaciente.obtenerPorID(idPaciente);
        }
        
        public Paciente obtenerXDNI(String dni)
        {
            return daoPaciente.obtenerPorDNI(dni);
        }
}
