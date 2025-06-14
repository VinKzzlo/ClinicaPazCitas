/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.dao.MedicoDAO;
import pe.edu.pucp.pazcitas.usuario.model.Medico;
import pe.edu.pucp.pazcitas.usuario.impl.MedicoImpl;


/**
 *
 * @author Joel
 */
public class MedicoBO {
    private  MedicoDAO daoMedico;
        public MedicoBO()
        {
            daoMedico = new MedicoImpl();
        }
        public int insertar(Medico medico)
        {
            return daoMedico.insertar(medico);
        }
        
        public int eliminar(int idMedico)
        {
            return daoMedico.eliminar(idMedico);
        }
        
        public int modificar(Medico med){
            return daoMedico.modificar(med);
        }
        
        public ArrayList<Medico> listarTodos()
        {
            return daoMedico.listarTodos();
        }
        public ArrayList<Medico> listarxEspecialidad(int idEsp, int idSed)
        {
            return daoMedico.listarXEspXSede(idEsp, idSed);
        }
        
        public Medico obtenerXId(int idMedico){
            return daoMedico.obtenerPorID(idMedico);
        }
}
