/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.dao.AdministradorDAO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;
import pe.edu.pucp.pazcitas.usuario.impl.AdministradorImpl;


/**
 *
 * @author Joel
 */
public class AdministradorBO {
    
        private final AdministradorDAO daoAdmi;
        public AdministradorBO()
        {
            daoAdmi = new AdministradorImpl();
        }
        public int insertar(Administrador admi)
        {
            return daoAdmi.insertar(admi);
        }
        
        public int eliminar(int idAdmi)
        {
            return daoAdmi.eliminar(idAdmi);
        }
        
        public int modificar(Administrador admi){
            return daoAdmi.modificar(admi);
        }
        

        
        public ArrayList<Administrador> listarTodos()
        {
            return daoAdmi.listarTodos();
        }
}
