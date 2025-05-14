/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pe.edu.pucp.pazcitas.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.bo.MedicamentoBO;
import pe.edu.pucp.pazcitas.atencion.model.Medicamento;
import pe.edu.pucp.pazcitas.cita.bo.CitaBO;
import pe.edu.pucp.pazcitas.cita.bo.EspecialidadBO;
import pe.edu.pucp.pazcitas.cita.bo.TurnoMedicoBO;
import pe.edu.pucp.pazcitas.cita.dao.CitaDAO;
import pe.edu.pucp.pazcitas.cita.dao.DisponibilidadDAO;
import pe.edu.pucp.pazcitas.cita.dao.EspecialidadDAO;
import pe.edu.pucp.pazcitas.cita.dao.TurnoMedicoDAO;
import pe.edu.pucp.pazcitas.cita.impl.CitaImpl;
import pe.edu.pucp.pazcitas.cita.impl.DisponibilidadImpl;
import pe.edu.pucp.pazcitas.cita.impl.EspecialidadImpl;
import pe.edu.pucp.pazcitas.cita.impl.TurnoMedicoImpl;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.cita.model.DiaSemana;
import pe.edu.pucp.pazcitas.cita.model.Disponibilidad;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.cita.model.EstadoCita;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;
import pe.edu.pucp.pazcitas.financiero.bo.SeguroBO;
import pe.edu.pucp.pazcitas.financiero.dao.SeguroDAO;
import pe.edu.pucp.pazcitas.financiero.impl.SeguroImpl;
import pe.edu.pucp.pazcitas.financiero.model.Seguro;
import pe.edu.pucp.pazcitas.financiero.model.TipoSeguro;
import pe.edu.pucp.pazcitas.ubicacion.bo.ConsultorioBO;
import pe.edu.pucp.pazcitas.ubicacion.bo.SedeBO;
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

//        Medicamento med = new Medicamento();
//        med.setNombre("Paracetamol");
//        med.setPresentacion("Blister x10 Tabletas - 500mg");
//        med.setStock(20);
//
//        MedicamentoBO boMed = new MedicamentoBO();
//        boMed.insertar(med);
//        Especialidad esp = new Especialidad();
//        esp.setNombre("Oftamologia");
//        esp.setDescripcion("OJO");
//        EspecialidadBO boEsp = new EspecialidadBO();
//        boEsp.insertar(esp);
//        Seguro seguro = new Seguro();
//        seguro.setIdSeguro(1);
//        seguro.setNombreSeguro("Rimac Oncologico");
//        seguro.setPorcentajeCobertura(10.00);
//        seguro.setTipo(TipoSeguro.TOTAL);
//        seguro.setVigencia(LocalDate.of(2003, 11, 1));
//        SeguroBO boSeguro = new SeguroBO();
//        boSeguro.insertar(seguro);
//
//        ArrayList<Seguro> seguros = boSeguro.listarTodos();
//        
//        for(Seguro s : seguros){
//            System.out.println(s.getIdSeguro() +" - " + s.getNombreSeguro());
//        }
//        PacienteBO pacienteBO = new PacienteBO();
//        Paciente paciente = new Paciente();
//        paciente.setApellidoMaterno("SANTE");
//        paciente.setApellidoPaterno("VEGA");
//        paciente.setDni("80809090");
//        paciente.setEmail("A34243232@");
//        paciente.setFechaNacimiento(LocalDate.of(2004, 05, 01));
//        paciente.setGenero('M');
//        paciente.setHashPassword("ASDASD");
//        paciente.setNombre("Salvador");
//        paciente.setDireccion("203das2");
//        paciente.setTelefono(21321321);
//        paciente.setSeguro(seguros.g);
//        
//        pacienteBO.insertar(paciente);
//        Sede sede = new Sede();
//        sede.setDireccion("Avenida Universitaria Nº 2020");
//        sede.setNombre("Sede Los Olivos");
//        SedeDAO daoSede = new SedeImpl();
//        daoSede.insertar(sede);
//        Consultorio
//        SedeBO boSede = new SedeBO();
//        ArrayList<Sede> sedes = boSede.listarTodos();
//
//        Consultorio con = new Consultorio();
//        con.setCapacidad(5);
//        con.setNombreConsultorio("A101");
//        con.setPiso(2);
//        con.setSede(sedes.get(sedes.size()-1));
//        ConsultorioBO boCons = new ConsultorioBO();
//        MedicoBO boMed = new MedicoBO();
//        boCons.insertar(con);
//        Medico medico = new Medico();
//        MedicoBO medicoBO = new MedicoBO();
//        medico.setApellidoMaterno("GOMEZ");
//        medico.setApellidoPaterno("LARA");
//        medico.setDni("323232");
//        medico.setEmail("A34243232@");
//        medico.setFechaNacimiento(LocalDate.of(2003, 12, 11));
//        medico.setGenero('M');
//        medico.setHashPassword("ASDASD");
//        medico.setNombre("Maria");
//        medico.setCodigoMedico("fafaa");
//
//        medico.setSede(sedes.get(sedes.size()-1));
//
//        medicoBO.insertar(medico);
//        ArrayList<Consultorio> cons = boCons.listarTodos();
//        ArrayList<Medico> meds = medicoBO.listarTodos();
//        
//        for(Consultorio c : cons){
//            System.out.println(c.getIdConsultorio() + "  " + c.getNombreConsultorio());
//        }
//        
//        for(Medico m : meds){
//            System.out.println(m.getIdUsuario() + "  " + m.getNombre());
//        }
//        
//        TurnoMedicoBO boTurno= new TurnoMedicoBO();
//        TurnoMedico turno = new TurnoMedico();
//        turno.setDia(DiaSemana.MARTES);
//        turno.setHoraInicio(LocalTime.of(12, 30, 0));
//        turno.setHoraFin(LocalTime.of(15,0,0));
//        turno.setConsultorio(cons.get(cons.size()-1));
//        turno.setMedico(meds.get(meds.size()-1));
//        
//        boTurno.insertar(turno);
        MedicoBO medicoBO = new MedicoBO();
        medicoBO.listarTodos();
        Sede sede = new Sede();
        sede.setDireccion("GINO DASSADSA 34");
        sede.setNombre("MASDAS3D");
        SedeDAO daoSede = new SedeImpl();
        daoSede.insertar(sede);

        Administrador admin = new Administrador();
        AdministradorBO boAdministrador = new AdministradorBO();

        admin.setNombre("FREDDY");
        admin.setApellidoPaterno("PAZ");
        admin.setApellidoMaterno("ESPINOZA");
        admin.setDni("wawamo");
        admin.setEmail("pfaz@gmail.com");
        admin.setGenero('M');
        admin.setFechaNacimiento(LocalDate.of(1990, 11, 1));
        admin.setHashPassword("password");

        sede.setIdSede(1);
        admin.setSede(sede);

        boAdministrador.insertar(admin);

        ArrayList<Administrador> administradores = boAdministrador.listarTodos();

        for (Administrador a : administradores) {
            System.out.println(a.getNombre() + a.getApellidoPaterno()
                    + a.getDni());
        }

        Medicamento med = new Medicamento();
        med.setNombre("Omeprazol");
        med.setPresentacion("Blister x10 Tabletas - 10mg");
        med.setStock(15);

        MedicamentoBO boMed = new MedicamentoBO();
        boMed.insertar(med);

        ArrayList<Medicamento> meds = boMed.listarTodos();

        for (Medicamento m : meds) {
            System.out.println(m.getIdMedicamento() + ". - " + m.getNombre()
                    + " - " + m.getPresentacion() + " - " + m.getStock());
        }

        AsistenteMedico asistente = new AsistenteMedico();
        asistente.setApellidoMaterno("GOMEZ");
        asistente.setApellidoPaterno("LARA");
        asistente.setDni("wawamo1");
        asistente.setEmail("A34243232@");
        asistente.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        asistente.setGenero('M');
        asistente.setHashPassword("ASDASD");
        asistente.setNombre("Maria");
        asistente.setCodigoPersonal("wawamo3");

        Medico medico = new Medico();

        medico.setApellidoMaterno("GOMEZ");
        medico.setApellidoPaterno("LARA");
        medico.setDni("wawamo4");
        medico.setEmail("A34243232@");
        medico.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        medico.setGenero('M');
        medico.setHashPassword("ASDASD");
        medico.setNombre("Maria");
        medico.setCodigoMedico("331");

        medico.setSede(sede);

        medicoBO.insertar(medico);

        Seguro seguro = new Seguro();
        seguro.setIdSeguro(211);
        seguro.setNombreSeguro("Seguritis433");
        seguro.setPorcentajeCobertura(10.00);
        seguro.setTipo(TipoSeguro.TOTAL);
        seguro.setVigencia(LocalDate.of(2003, 11, 1));
        SeguroBO seguroBO = new SeguroBO();
        seguroBO.insertar(seguro);

        PacienteBO pacienteBO = new PacienteBO();
        Paciente paciente = new Paciente();
        paciente.setApellidoMaterno("GOMEZ");
        paciente.setApellidoPaterno("LARA");
        paciente.setDni("wawamo5");
        paciente.setEmail("A34243232@");
        paciente.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        paciente.setGenero('M');
        paciente.setHashPassword("ASDASD");
        paciente.setNombre("Maria");
        paciente.setDireccion("203das2");
        paciente.setTelefono(21321321);
        paciente.setSeguro(seguro);

        pacienteBO.insertar(paciente);
        Consultorio con = new Consultorio();
        con.setCapacidad(12);
        con.setNombreConsultorio("Vas a caer Juan Garcia");
        con.setPiso(1);
        con.setSede(sede);

        ConsultorioDAO daoCon = new ConsultorioImpl();
        daoCon.insertar(con);

        Especialidad esp = new Especialidad();
        esp.setNombre("Oftalmologia");
        esp.setDescripcion("waaa");
        EspecialidadBO espBO = new EspecialidadBO();
        espBO.insertar(esp);

        Cita cita = new Cita();

        cita.setEspecialidad(esp);
        cita.setEstadoCita(EstadoCita.CANCELADA);
        cita.setFecha(LocalDateTime.of(2025, 5, 12, 14, 00, 0));
        cita.setMotivoConsulta("Botame tu ga");
        cita.setPaciente(paciente);

        TurnoMedico turnoMed = new TurnoMedico();
        turnoMed.setMedico(medico);
        turnoMed.setConsultorio(con);
        turnoMed.setDia(DiaSemana.LUNES);
        turnoMed.setHoraInicio(LocalTime.of(12, 12));
        turnoMed.setHoraFin(LocalTime.of(10, 10));

        TurnoMedicoDAO daoTurno = new TurnoMedicoImpl();
        daoTurno.insertar(turnoMed);

        Disponibilidad dispo = new Disponibilidad();
        dispo.setFecha(LocalDate.of(2025, 04, 28));
        dispo.setHora(LocalTime.of(10, 10));
        dispo.setDisponible(true);
        dispo.setTurnoMedico(turnoMed);
        DisponibilidadDAO daoDispo = new DisponibilidadImpl();
        daoDispo.insertar(dispo);

        cita.setDisponibilidad(dispo);
        CitaBO citaBO = new CitaBO();
        citaBO.insertar(cita);

    }
}
