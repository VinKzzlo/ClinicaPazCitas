/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.dao.AsistenteMedicoDAO;
import pe.edu.pucp.pazcitas.usuario.model.AsistenteMedico;
import pe.edu.pucp.pazcitas.usuario.impl.AsistenteMedicoImpl;


/**
 *
 * @author Joel
 */
public class AsistenteBO {
        private  AsistenteMedicoDAO daoAsistente;
        public AsistenteBO()
        {
            daoAsistente = new AsistenteMedicoImpl();
        }
        public int insertar(AsistenteMedico asistente)
        {
            return daoAsistente.insertar(asistente);
        }
        
        public int eliminar(int idAsistente)
        {
            return daoAsistente.eliminar(idAsistente);
        }
        
        public int modificar(AsistenteMedico asistente){
            return daoAsistente.modificar(asistente);
        }
        
        public ArrayList<AsistenteMedico> listarTodos()
        {
            return daoAsistente.listarTodos();
        }
}
