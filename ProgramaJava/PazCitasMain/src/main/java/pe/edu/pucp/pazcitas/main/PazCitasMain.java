/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.pucp.pazcitas.main;

import java.util.Date;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.bo.AdministradorBO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;

/**
 *
 * @author asant
 */
public class PazCitasMain {

    public static void main(String[] args) {
        
        Administrador admin = new Administrador();
        AdministradorBO boAdministrador = new AdministradorBO();
        
        admin.setNombre("JUAN");
        admin.setApellidoPaterno("GARCIA");
        admin.setDni("72727272");
        admin.setEmail("juan@gmail.com");
        admin.setGenero('F');
        admin.setFechaNacimiento(new Date(1900,05,05));
        admin.setHashPassword("password");
        Sede sede = new Sede();
        sede.setIdSede(1);
        admin.setSede(sede);
        
        
        
        boAdministrador.insertar(admin);
    }
}
