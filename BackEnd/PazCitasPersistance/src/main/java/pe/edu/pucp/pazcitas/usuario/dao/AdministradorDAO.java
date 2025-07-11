/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pazcitas.usuario.dao;

import pe.edu.pucp.pazcitas.dao.ICrud;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;

/**
 *
 * @author asant
 */
public interface AdministradorDAO extends ICrud<Administrador>{
    public Administrador obtenerPorID(int idAdmin);
}
