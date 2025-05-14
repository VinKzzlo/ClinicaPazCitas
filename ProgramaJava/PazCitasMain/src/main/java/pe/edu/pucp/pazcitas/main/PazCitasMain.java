
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
import pe.edu.pucp.pazcitas.cita.bo.DisponibilidadBO;
import pe.edu.pucp.pazcitas.cita.bo.TurnoMedicoBO;
import pe.edu.pucp.pazcitas.cita.impl.CitaImpl;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.cita.model.DiaSemana;
import pe.edu.pucp.pazcitas.cita.model.Disponibilidad;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.cita.model.EstadoCita;
import pe.edu.pucp.pazcitas.cita.model.TurnoMedico;
import pe.edu.pucp.pazcitas.financiero.bo.SeguroBO;

import pe.edu.pucp.pazcitas.financiero.model.Seguro;
import pe.edu.pucp.pazcitas.financiero.model.TipoSeguro;
import pe.edu.pucp.pazcitas.ubicacion.bo.ConsultorioBO;
import pe.edu.pucp.pazcitas.ubicacion.bo.SedeBO;


import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.usuario.bo.AdministradorBO;
import pe.edu.pucp.pazcitas.usuario.bo.MedicoBO;
import pe.edu.pucp.pazcitas.usuario.bo.RecepcionistaBO;
import pe.edu.pucp.pazcitas.usuario.bo.PacienteBO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;
import pe.edu.pucp.pazcitas.usuario.model.AsistenteMedico;
import pe.edu.pucp.pazcitas.usuario.model.Medico;
import pe.edu.pucp.pazcitas.usuario.model.Recepcionista;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

/**
 *
 * @author asant
 */
public class PazCitasMain {

    public static void main(String[] args) {
        
        
        //Primero insertamos varias sedes
        Sede sede = new Sede();
        sede.setDireccion("Avenida Universitaria Nº 2020");
        sede.setNombre("Sede Los Olivos");
        SedeBO boSede = new SedeBO();
        boSede.insertar(sede);

        sede = new Sede();
        sede.setDireccion("Calle Las Magnolias Nº 20");
        sede.setNombre("Sede Magdalena");
        boSede.insertar(sede);
        
        sede = new Sede();
        sede.setDireccion("Calle Ultima Esperanza Nº 20");
        sede.setNombre("Sede Miraflores");
        boSede.insertar(sede);
        
        //Listamos las sedes ingresadas
        ArrayList<Sede> sedes = boSede.listarTodos();

        //Creamos un consultorio
        Consultorio con = new Consultorio();
        con.setNombreConsultorio("A101");
        con.setCapacidad(5);
        con.setPiso(1);
        //Listamos sedes
        con.setSede(sedes.get(sedes.size()-1));
        ConsultorioBO boCons = new ConsultorioBO();
        boCons.insertar(con); //Insertamos consultorio
        
        con = new Consultorio();
        con.setNombreConsultorio("A201");
        con.setCapacidad(10);
        con.setPiso(2);
        //Listamos sedes
        con.setSede(sedes.get(sedes.size()-1));
        boCons.insertar(con);//Insertamos consultorio
        
        //Insertamos especialidades
        Especialidad esp = new Especialidad();
        esp.setNombre("Gastroenterologia");
        esp.setDescripcion("Especialidad medica que trata enfermedades del sistema digestvo");
        EspecialidadBO boEsp = new EspecialidadBO();
        boEsp.insertar(esp);
        
        esp.setNombre("Nutricion");
        esp.setDescripcion("Especialidad medica que trata sobre nutricion");
        boEsp.insertar(esp);
        
        esp.setNombre("Neumologia");
        esp.setDescripcion("Especialidad medica que trata enfermedades del sistema respiratorio");
        boEsp.insertar(esp);
        
        ArrayList<Especialidad> especialidades = boEsp.listarTodos();
        
        //Insertamos medico
        MedicoBO boMed = new MedicoBO();
        Medico medico = new Medico();
        medico.setNombre("Juan");
        medico.setApellidoPaterno("Perez");
        medico.setApellidoMaterno("Zavala");
        medico.setDni("12345678");
        medico.setEmail("jpzavala@pazcitas.pe");
        medico.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        medico.setGenero('F');
        medico.setHashPassword("password");
        medico.setCodigoMedico("jp123");
        medico.setSede(sedes.get(sedes.size()-1));
        boMed.insertar(medico);
        
        medico.setNombre("Pablo");
        medico.setApellidoPaterno("Gran");
        medico.setApellidoMaterno("Pastor");
        medico.setDni("45612389");
        medico.setEmail("pgpastor@pazcitas.pe");
        medico.setFechaNacimiento(LocalDate.of(2003, 12, 11));
        medico.setGenero('M');
        medico.setHashPassword("password");
        medico.setCodigoMedico("pg123");
        medico.setSede(sedes.get(sedes.size()-1));
        boMed.insertar(medico);
        
        //Insertamos seguros
        Seguro seguro = new Seguro();
        seguro.setNombreSeguro("Rimac Oncologico");
        seguro.setPorcentajeCobertura(10.00);
        seguro.setTipo(TipoSeguro.PARCIAL);
        seguro.setVigencia(LocalDate.of(2025, 12, 31));
        SeguroBO boSeguro = new SeguroBO();
        boSeguro.insertar(seguro);
        
        seguro.setNombreSeguro("Pacifico Integral");
        seguro.setPorcentajeCobertura(100.00);
        seguro.setTipo(TipoSeguro.TOTAL);
        seguro.setVigencia(LocalDate.of(2025, 12, 31));
        boSeguro.insertar(seguro);
        
        ArrayList<Seguro> seguros = boSeguro.listarTodos();

        //Insertamos paciente
        PacienteBO boPaciente = new PacienteBO();
        Paciente paciente = new Paciente();
        paciente.setNombre("Maria");
        paciente.setApellidoMaterno("Dolores");
        paciente.setApellidoPaterno("Fuertes");
        paciente.setDni("87654321");
        paciente.setEmail("mdfuertes@gmail.com");
        paciente.setFechaNacimiento(LocalDate.of(2005, 12, 3));
        paciente.setGenero('F');
        paciente.setHashPassword("fuertes123");
        paciente.setDireccion("Calle Ultima Agonia Nº 123");
        paciente.setTelefono(45659878);
        paciente.setSeguro(seguros.get(seguros.size()-1));
        boPaciente.insertar(paciente);
        
        paciente.setNombre("Juan");
        paciente.setApellidoMaterno("Barriga");
        paciente.setApellidoPaterno("Torres");
        paciente.setDni("25361485");
        paciente.setEmail("jbtorres@gmail.com");
        paciente.setFechaNacimiento(LocalDate.of(2000, 11, 14));
        paciente.setGenero('M');
        paciente.setHashPassword("fuertes123");
        paciente.setDireccion("Calle Apocalipsis Nº 999");
        paciente.setTelefono(45659878);
        paciente.setSeguro(seguros.get(seguros.size()-1));
        boPaciente.insertar(paciente);
        //Listamos medicos, pacientes y consultorios
        ArrayList<Consultorio> cons = boCons.listarTodos();
        ArrayList<Medico> meds = boMed.listarTodos();
        ArrayList<Paciente>pacs = boPaciente.listarTodos();
        
        
        //Insertamos turno
        TurnoMedicoBO boTurno= new TurnoMedicoBO();
        TurnoMedico turno = new TurnoMedico();
        turno.setDia(DiaSemana.MARTES);
        turno.setHoraInicio(LocalTime.of(12, 30, 0));
        turno.setHoraFin(LocalTime.of(15,0,0));
        turno.setConsultorio(cons.get(cons.size()-1));
        turno.setMedico(meds.get(meds.size()-1));
        boTurno.insertar(turno);
        
        turno.setDia(DiaSemana.MIERCOLES);
        turno.setHoraInicio(LocalTime.of(10, 00, 0));
        turno.setHoraFin(LocalTime.of(13,0,0));
        turno.setConsultorio(cons.get(cons.size()-1));
        turno.setMedico(meds.get(meds.size()-1));
        boTurno.insertar(turno);
        
        ArrayList<TurnoMedico> turnos = boTurno.listarTodos();
        
        Disponibilidad dispo = new Disponibilidad();
        dispo.setFecha(LocalDate.of(2025, 04, 28));
        dispo.setHora(LocalTime.of(10, 10));
        dispo.setDisponible(true);
        dispo.setTurnoMedico(turnos.get(turnos.size()-1));
        DisponibilidadBO boDispo = new DisponibilidadBO();
        boDispo.insertar(dispo);
        
        ArrayList<Disponibilidad> dispos = boDispo.listarTodos();
        
        Recepcionista recepcionista = new Recepcionista();
        recepcionista.setNombre("Daniela");
        recepcionista.setApellidoMaterno("Flores");
        recepcionista.setApellidoPaterno("Palacios");
        recepcionista.setDni("64821937");
        recepcionista.setEmail("dfpalacios@gmail.com");
        recepcionista.setFechaNacimiento(LocalDate.of(2000, 11, 14));
        recepcionista.setGenero('M');
        recepcionista.setHashPassword("fuertes123");
        recepcionista.setSede(sedes.get(sedes.size()-1));
        RecepcionistaBO boRecepcionista = new RecepcionistaBO();
        boRecepcionista.insertar(recepcionista);
        
        ArrayList<Recepcionista> receps = boRecepcionista.listarTodos();    
        
        Cita cita = new Cita();
        cita.setFecha(LocalDateTime.of(2025, 5, 14, 13, 00, 0));
        cita.setEstadoCita(EstadoCita.PROGRAMADA);
        cita.setMotivoConsulta("Dolor abdominal y nauseas");
        cita.setPaciente(pacs.get(0));
        cita.setDisponibilidad(dispos.get(0));
        cita.setRecepcionista(receps.get(0));
        cita.setEspecialidad(especialidades.get(0));
        
        CitaBO boCita = new CitaBO();
        boCita.insertar(cita);
        
    }
}
