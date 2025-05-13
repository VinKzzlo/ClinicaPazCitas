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
import pe.edu.pucp.pazcitas.financiero.dao.SeguroDAO;
import pe.edu.pucp.pazcitas.financiero.impl.SeguroImpl;
import pe.edu.pucp.pazcitas.financiero.model.Seguro;
import pe.edu.pucp.pazcitas.financiero.model.TipoSeguro;
import pe.edu.pucp.pazcitas.ubicacion.dao.ConsultorioDAO;
import pe.edu.pucp.pazcitas.ubicacion.dao.SedeDAO;
import pe.edu.pucp.pazcitas.ubicacion.impl.ConsultorioImpl;
import pe.edu.pucp.pazcitas.ubicacion.impl.SedeImpl;
import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.bo.AdministradorBO;
import pe.edu.pucp.pazcitas.usuario.bo.MedicoBO;
import pe.edu.pucp.pazcitas.usuario.bo.PacienteBO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;
import pe.edu.pucp.pazcitas.usuario.model.AsistenteMedico;
import pe.edu.pucp.pazcitas.usuario.model.Medico;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

/**
 *
 * @author asant
 */
public class PazCitasMain {

    public static void main(String[] args) {
        
        Administrador admin = new Administrador();
        AdministradorBO boAdministrador = new AdministradorBO();
        
        admin.setNombre("FREDDY");
        admin.setApellidoPaterno("PAZ");
        admin.setApellidoMaterno("ESPINOZA");
        admin.setDni("87654321");
        admin.setEmail("pfaz@gmail.com");
        admin.setGenero('M');
        admin.setFechaNacimiento(LocalDate.of(1990,11,1));
        admin.setHashPassword("password");
        Sede sede = new Sede();
        sede.setIdSede(1);
        admin.setSede(sede);
        
        boAdministrador.insertar(admin);
        
        ArrayList<Administrador> administradores = boAdministrador.listarTodos();
        
        for(Administrador a : administradores)
            System.out.println(a.getNombre() + a.getApellidoPaterno()
             + a.getDni());
        
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
        
        sede = new Sede();
        sede.setDireccion("GINO DASSADSA 34");
        sede.setNombre("MASDASD");
        SedeDAO daoSede = new SedeImpl();
        daoSede.insertar(sede);
//        
      
        AsistenteMedico asistente = new AsistenteMedico();
        asistente.setApellidoMaterno("GOMEZ");
        asistente.setApellidoPaterno("LARA");
        asistente.setDni("321321");
        asistente.setEmail("A34243232@");
        asistente.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        asistente.setGenero('M');
        asistente.setHashPassword("ASDASD");
        asistente.setNombre("Maria");
        asistente.setCodigoPersonal("2030232");
        
        
        Medico medico = new Medico();
        
        MedicoBO medicoBO= new MedicoBO();
        medico.setApellidoMaterno("GOMEZ");
        medico.setApellidoPaterno("LARA");
        medico.setDni("4343111");
        medico.setEmail("A34243232@");
        medico.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        medico.setGenero('M');
        medico.setHashPassword("ASDASD");
        medico.setNombre("Maria");
        medico.setCodigoMedico("2030232");
        
        medico.setSede(sede);
        
        medicoBO.insertar(medico);
        
        
        Seguro seguro = new Seguro();
        seguro.setIdSeguro(1233);
        seguro.setNombreSeguro("Seguritis433");
        seguro.setPorcentajeCobertura(10.00);
        seguro.setTipo(TipoSeguro.TOTAL);
        seguro.setVigencia(LocalDate.of(2003, 11, 1));
        SeguroDAO daoSeguro = new SeguroImpl();
        daoSeguro.insertar(seguro);
        
        PacienteBO pacienteBO = new PacienteBO();
        Paciente paciente = new Paciente();
        paciente.setApellidoMaterno("GOMEZ");
        paciente.setApellidoPaterno("LARA");
        paciente.setDni("43334");
        paciente.setEmail("A34243232@");
        paciente.setFechaNacimiento( LocalDate.of(2003, 12, 11));
        paciente.setGenero('M');
        paciente.setHashPassword("ASDASD");
        paciente.setNombre("Maria");
        paciente.setDireccion("203das2");
        paciente.setTelefono(21321321);
        paciente.setSeguro(seguro);
        
        pacienteBO.insertar(paciente);
          Consultorio con = new Consultorio();
          con.setCapacidad(12);
          con.setNombreConsultorio("numer");
          con.setPiso(1);
          con.setSede(sede);
          
          ConsultorioDAO daoCon = new ConsultorioImpl();
          daoCon.insertar(con);
        
        
    }
}
