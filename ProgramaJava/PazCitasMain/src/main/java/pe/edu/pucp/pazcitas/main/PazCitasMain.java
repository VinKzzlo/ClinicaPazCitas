/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.pucp.pazcitas.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazcitas.atencion.bo.MedicamentoBO;
import pe.edu.pucp.pazcitas.atencion.model.Medicamento;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.bo.AdministradorBO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;

/**
 *
 * @author asant
 */
public class PazCitasMain {

    public static void main(String[] args) {
        
//        Administrador admin = new Administrador();
//        AdministradorBO boAdministrador = new AdministradorBO();
//        
//        admin.setNombre("FREDDY");
//        admin.setApellidoPaterno("PAZ");
//        admin.setDni("12345678");
//        admin.setEmail("pfaz@gmail.com");
//        admin.setGenero('M');
//        admin.setFechaNacimiento(LocalDate.of(1990,11,1));
//        admin.setHashPassword("password");
//        Sede sede = new Sede();
//        sede.setIdSede(1);
//        admin.setSede(sede);
//        
//        boAdministrador.insertar(admin);
//        
//        ArrayList<Administrador> administradores = boAdministrador.listarTodos();
//        
//        for(Administrador a : administradores)
//            System.out.println(a.getNombre() + a.getApellidoPaterno()
//             + a.getDni());
        
        Medicamento med = new Medicamento();
        med.setNombre("Omeprazol");
        med.setPresentacion("Blister x10 Tabletas - 10mg");
        med.setStock(15);
        
        MedicamentoBO boMed = new MedicamentoBO();
        boMed.insertar(med);

        ArrayList<Medicamento> meds = boMed.listarTodos();
        
        for(Medicamento m : meds)
            System.out.println(m.getIdMedicamento() + ". - " + m.getNombre()
              + " - " + m.getPresentacion() + " - " +m.getStock());
        
        meds.get(2).setStock(100);
        
        boMed.modificar(meds.get(2));
        meds = boMed.listarTodos();
        
        for(Medicamento m : meds)
            System.out.println(m.getIdMedicamento() + ". - " + m.getNombre()
              + " - " + m.getPresentacion() + " - " +m.getStock());
        
        
    }
}
