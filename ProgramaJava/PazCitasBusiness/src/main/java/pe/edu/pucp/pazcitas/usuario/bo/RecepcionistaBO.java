/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.usuario.dao.RecepcionistaDAO;
import pe.edu.pucp.pazcitas.usuario.model.Recepcionista;
import pe.edu.pucp.pazcitas.usuario.impl.RecepcionistaImpl;


/**
 *
 * @author Joel
 */
public class RecepcionistaBO {
        private  RecepcionistaDAO daoRecepcionista;
        public RecepcionistaBO()
        {
            daoRecepcionista = new RecepcionistaImpl();
        }
        public int insertar(Recepcionista recepcionista)
        {
            return daoRecepcionista.insertar(recepcionista);
        }
        
        public int eliminar(int idRecep)
        {
            return daoRecepcionista.eliminar(idRecep);
        }
        public int modificar(Recepcionista recep){
            return daoRecepcionista.modificar(recep);
        }
        public ArrayList<Recepcionista> listarTodos()
        {
            return daoRecepcionista.listarTodos();
        }
}
