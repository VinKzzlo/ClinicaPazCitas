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
        admin.setDni("21211212");
        admin.setEmail("pfaz@gmail.com");
        admin.setGenero('M');
        admin.setFechaNacimiento(LocalDate.of(1990, 11, 1));
        admin.setHashPassword("password");
        Sede sede = new Sede();
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

        meds.get(2).setStock(100);

        boMed.modificar(meds.get(2));
        meds = boMed.listarTodos();

        for (Medicamento m : meds) {
            System.out.println(m.getIdMedicamento() + ". - " + m.getNombre()
                    + " - " + m.getPresentacion() + " - " + m.getStock());
        }

        sede = new Sede();
        sede.setDireccion("GINO DASSADSA 34");
        sede.setNombre("MASDASD");
        SedeDAO daoSede = new SedeImpl();
        daoSede.insertar(sede);
//        

        AsistenteMedico asistente = new AsistenteMedico();
        asistente.setApellidoMaterno("GOMEZ");
        asistente.setApellidoPaterno("LARA");
        asistente.setDni("aeaaeae");
        asistente.setEmail("A34243232@");
        asistente.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        asistente.setGenero('M');
        asistente.setHashPassword("ASDASD");
        asistente.setNombre("Maria");
        asistente.setCodigoPersonal("aeaea");

        Medico medico = new Medico();

        MedicoBO medicoBO = new MedicoBO();
        medico.setApellidoMaterno("GOMEZ");
        medico.setApellidoPaterno("LARA");
        medico.setDni("daeda");
        medico.setEmail("A34243232@");
        medico.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        medico.setGenero('M');
        medico.setHashPassword("ASDASD");
        medico.setNombre("Maria");
        medico.setCodigoMedico("fafa");

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
        paciente.setDni("abgdsg");
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
        con.setNombreConsultorio("numer");
        con.setPiso(1);
        con.setSede(sede);

        ConsultorioDAO daoCon = new ConsultorioImpl();
        daoCon.insertar(con);
        Especialidad esp = new Especialidad();
        esp.setNombre("Oftalmologia");
        esp.setDescripcion("Numero1");
        EspecialidadDAO daoEsp = new EspecialidadImpl();
        daoEsp.insertar(esp);

        Cita cita = new Cita();

        cita.setEspecialidad(esp);
        cita.setEstadoCita(EstadoCita.ATENDIDA);
        cita.setFecha(LocalDateTime.MIN);
        cita.setFechaActualizacion(LocalDateTime.MIN);
        cita.setMotivoConsulta("Dolor de estomago");
        cita.setPaciente(paciente);

        TurnoMedico turnoMed = new TurnoMedico();
        turnoMed.setDia(DiaSemana.LUNES);
        turnoMed.setHoraInicio(LocalTime.of(12, 0));
        turnoMed.setHoraFin(LocalTime.of(16, 0));
        turnoMed.setMedico(medico);
        turnoMed.setConsultorio(con);
        
        TurnoMedicoDAO daoTurno = new TurnoMedicoImpl();
        daoTurno.insertar(turnoMed);
        
        Disponibilidad dispo = new Disponibilidad();
        dispo.setFecha(LocalDate.of(2025, 04, 28));
        dispo.setHora(LocalTime.of(14, 0));
        dispo.setDisponible(true);
        dispo.setTurnoMedico(turnoMed);
        DisponibilidadDAO daoDispo = new DisponibilidadImpl();
        daoDispo.insertar(dispo);
        
        cita.setDisponibilidad(dispo);
        CitaDAO daocita = new CitaImpl();
        daocita.insertar(cita);

    }
}
