/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.bo;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.dao.MedicamentoDAO;
import pe.edu.pucp.pazcitas.atencion.impl.MedicamentoImpl;
import pe.edu.pucp.pazcitas.atencion.model.Medicamento;

/**
 *
 * @author asant
 */
public class MedicamentoBO {

    private MedicamentoDAO daoMedicamento;

    public MedicamentoBO() {
        daoMedicamento = new MedicamentoImpl();
    }
    
    public int insertar(Medicamento medicamento){
        return daoMedicamento.insertar(medicamento);
    }
    
    public int modificar(Medicamento medicamento){
        return daoMedicamento.modificar(medicamento);
    }
    public int eliminar(int idMedicamento){
        return daoMedicamento.eliminar(idMedicamento);
    }
    public ArrayList<Medicamento> listarTodos(){
        return daoMedicamento.listarTodos();
        
    }

    public ArrayList<Medicamento> listarXNombre(String nombre){
        return daoMedicamento.listarXNombre(nombre);
    }
    
}
