/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.atencion.dao;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.model.NotaClinica;
import pe.edu.pucp.pazcitas.dao.ICrud;

/**
 *
 * @author asant
 */
public interface NotaClinicaDAO extends ICrud<NotaClinica>{
    ArrayList<NotaClinica> listarXhistorial(int idHis);
}
