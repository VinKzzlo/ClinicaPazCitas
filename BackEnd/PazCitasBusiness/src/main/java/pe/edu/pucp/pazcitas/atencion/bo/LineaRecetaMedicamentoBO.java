/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.bo;

import pe.edu.pucp.pazcitas.atencion.dao.LineaRecetaMedicamentoDAO;
import pe.edu.pucp.pazcitas.atencion.impl.LineaRecetaMedicamentoImpl;
import pe.edu.pucp.pazcitas.atencion.model.LineaRecetaMedicamento;

/**
 *
 * @author Joel
 */
public class LineaRecetaMedicamentoBO {
    private final LineaRecetaMedicamentoDAO daolineareceta;
    
    public LineaRecetaMedicamentoBO(){
        daolineareceta = new LineaRecetaMedicamentoImpl();
    }
    
    public int insertar(LineaRecetaMedicamento linea){
        return daolineareceta.insertar(linea);
    }
    public int eliminar(int idlinea){
        return daolineareceta.eliminar(idlinea);
    }
}
