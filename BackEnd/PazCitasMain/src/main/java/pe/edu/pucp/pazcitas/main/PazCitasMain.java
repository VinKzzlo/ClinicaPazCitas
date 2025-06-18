package pe.edu.pucp.pazcitas.main;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazcitas.atencion.bo.LineaRecetaMedicamentoBO;
import pe.edu.pucp.pazcitas.atencion.bo.MedicamentoBO;
import pe.edu.pucp.pazcitas.atencion.bo.RecetaBO;
import pe.edu.pucp.pazcitas.atencion.model.LineaRecetaMedicamento;
import pe.edu.pucp.pazcitas.atencion.model.Medicamento;
import pe.edu.pucp.pazcitas.atencion.model.Receta;
import pe.edu.pucp.pazcitas.cita.bo.CitaBO;
import pe.edu.pucp.pazcitas.cita.bo.EspecialidadBO;
import pe.edu.pucp.pazcitas.cita.bo.HorarioTrabajoBO;
import pe.edu.pucp.pazcitas.cita.bo.TurnoBO;
import pe.edu.pucp.pazcitas.cita.impl.CitaImpl;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.cita.model.DiaSemana;
import pe.edu.pucp.pazcitas.cita.model.Especialidad;
import pe.edu.pucp.pazcitas.cita.model.EstadoCita;
import pe.edu.pucp.pazcitas.cita.model.HorarioTrabajo;
import pe.edu.pucp.pazcitas.cita.model.Turno;
import pe.edu.pucp.pazcitas.financiero.bo.SeguroBO;

import pe.edu.pucp.pazcitas.financiero.model.Seguro;
import pe.edu.pucp.pazcitas.financiero.model.TipoSeguro;
import pe.edu.pucp.pazcitas.ubicacion.bo.ConsultorioBO;
import pe.edu.pucp.pazcitas.ubicacion.bo.SedeBO;
import pe.edu.pucp.pazcitas.ubicacion.bo.SedeEspecialidadBO;

import pe.edu.pucp.pazcitas.ubicacion.model.Consultorio;
import pe.edu.pucp.pazcitas.ubicacion.model.Sede;
import pe.edu.pucp.pazcitas.ubicacion.model.SedeEspecialidad;
import pe.edu.pucp.pazcitas.usuario.bo.AdministradorBO;
import pe.edu.pucp.pazcitas.usuario.bo.MedicoBO;
import pe.edu.pucp.pazcitas.usuario.bo.PacienteBO;
import pe.edu.pucp.pazcitas.usuario.model.Administrador;
import pe.edu.pucp.pazcitas.usuario.model.Medico;
import pe.edu.pucp.pazcitas.usuario.model.Paciente;

/**
 *
 * @author asant
 */
public class PazCitasMain {

    public static void main(String[] args) throws Exception {
//        
//        
//        //Primero insertamos varias sedes
//        Sede sede = new Sede();
        ////        sede.setDireccion("Avenida Universitaria Nº 2020");
////        sede.setNombre("Sede Los Olivos");
//        SedeBO boSede = new SedeBO();
////        boSede.insertar(sede);
//
////        sede = new Sede();
////        sede.setDireccion("Calle Las Magnolias Nº 20");
////        sede.setNombre("Sede Magdalena");
////        boSede.insertar(sede);
////        
////        sede = new Sede();
////        sede.setDireccion("Calle Ultima Esperanza Nº 20");
////        sede.setNombre("Sede Miraflores");
////        boSede.insertar(sede);
//        
//        //Listamos las sedes ingresadas
////        ArrayList<Sede> sedes = boSede.listarTodos();
//
//        //Creamos un consultorio
//        Consultorio con = new Consultorio();
////        con.setNombreConsultorio("B301");
////        con.setCapacidad(5);
////        con.setPiso(1);
//        //Listamos sedes
////        con.setSede(sedes.get(0));
//        ConsultorioBO boCons = new ConsultorioBO();
////        boCons.insertar(con); //Insertamos consultorio
////        
////        con = new Consultorio();
////        con.setNombreConsultorio("C101");
////        con.setCapacidad(10);
////        con.setPiso(2);
////        //Listamos sedes
////        con.setSede(sedes.get(0));
////        boCons.insertar(con);//Insertamos consultorio
////        ArrayList<Consultorio> consultorios = boCons.listarTodos();
//        //Insertamos especialidades
////        Especialidad esp = new Especialidad();
////        esp.setNombre("Gastroenterologia");
////        esp.setDescripcion("Especialidad medica que trata enfermedades del sistema digestvo");
//        EspecialidadBO boEsp = new EspecialidadBO();
////        boEsp.insertar(esp);
////        
////        esp.setNombre("Nutricion");
////        esp.setDescripcion("Especialidad medica que trata sobre nutricion");
////        boEsp.insertar(esp);
////        
////        esp.setNombre("Neumologia");
////        esp.setDescripcion("Especialidad medica que trata enfermedades del sistema respiratorio");
////        boEsp.insertar(esp);
////        
////        ArrayList<Especialidad> especialidades = boEsp.listarTodos();
//        
////        SedeEspecialidadBO sedeespbo = new SedeEspecialidadBO();
////        SedeEspecialidad sedesp = new SedeEspecialidad();
////        sedesp.setEspecialidad(especialidades.get(0));
////        sedesp.setSede(sedes.get(0));
////        sedeespbo.insertar(sedesp);
////        
////        sedesp = new SedeEspecialidad();
////        sedesp.setEspecialidad(especialidades.get(1));
////        sedesp.setSede(sedes.get(1));
////        sedeespbo.insertar(sedesp);
//            
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        //Insertamos medico
//        MedicoBO boMed = new MedicoBO();
//        ArrayList<Medico> m=boMed.listarTodos();
//        for(Medico med:m){
//            System.out.println(med.getIdUsuario()+" "+med.getNombre() + " " + med.getSede().getNombre()+" " + med.getConsultorio().getIdConsultorio());
//        }
////        Medico medico = new Medico();
////        medico.setNombre("Martin");
////        medico.setApellidoPaterno("Torres");
////        medico.setApellidoMaterno("Palacios");
////        medico.setDni("32d32");
////        medico.setEmail("mtpalacios@pazcitas.pe");
////        medico.setFechaNacimiento(sdf.parse("1992-09-10"));
////        medico.setGenero('F');
////        medico.setCodigoMedico("123321");
////        medico.setSede(sedes.get(0));
////        medico.setEspecialidad(especialidades.get(0));
////        medico.setConsultorio(consultorios.get(0));
////        boMed.insertar(medico);
////        
////        medico.setNombre("Pablo");
////        medico.setApellidoPaterno("Gran");
////        medico.setApellidoMaterno("Pastor");
////        medico.setDni("323d2");
////        medico.setEmail("pgpastor@pazcitas.pe");
////        medico.setFechaNacimiento(sdf.parse("1992-09-10"));
////        medico.setGenero('M');
////        medico.setCodigoMedico("1232232");
////        medico.setSede(sedes.get(0));
////        medico.setEspecialidad(especialidades.get(0));
////        medico.setConsultorio(consultorios.get(1));
////        boMed.insertar(medico);
//        
//        //Insertamos seguros
////        Seguro seguro = new Seguro();
////        seguro.setNombreSeguro("Rimac Oncologico");
////        seguro.setPorcentajeCobertura(10.00);
////        seguro.setTipo(TipoSeguro.PARCIAL);
////        seguro.setVigencia(sdf.parse("1992-09-10"));
//        SeguroBO boSeguro = new SeguroBO();
////        boSeguro.insertar(seguro);
////        
////        seguro.setNombreSeguro("Pacifico Integral");
////        seguro.setPorcentajeCobertura(100.00);
////        seguro.setTipo(TipoSeguro.TOTAL);
////        seguro.setVigencia(sdf.parse("1992-09-10"));
////        boSeguro.insertar(seguro);
//        
////        ArrayList<Seguro> seguros = boSeguro.listarTodos();
////
////        //Insertamos paciente
//        PacienteBO boPaciente = new PacienteBO();
////        Paciente paciente = new Paciente();
////        paciente.setNombre("Maria");
////        paciente.setApellidoMaterno("Dolores");
////        paciente.setApellidoPaterno("Fuertes");
////        paciente.setDni("3211321");
////        paciente.setEmail("mdfuertes@gmail.com");
////        paciente.setFechaNacimiento(sdf.parse("1992-09-10"));
////        paciente.setGenero('F');
////        paciente.setDireccion("Calle Ultima Agonia Nº 123");
////        paciente.setTelefono("45659878");
////        paciente.setSeguro(seguros.get(seguros.size()-1));
////        boPaciente.insertar(paciente);
////        
//        
////        paciente = new Paciente();
////        paciente.setNombre("Jorge");
////        paciente.setApellidoMaterno("Curioso");
////        paciente.setApellidoPaterno("Melgarejo");
////        paciente.setDni("78339322");
////        paciente.setEmail("mCuriosos@gmail.com");
////        paciente.setFechaNacimiento(sdf.parse("199-09-10"));
////        paciente.setGenero('M');
////        paciente.setDireccion("Calle Ultima Agonia Nº 123");
////        paciente.setTelefono("99959878");
////        paciente.setSeguro(seguros.get(seguros.size()-1));
////        boPaciente.insertar(paciente);
////        
////        paciente.setNombre("Juan");
////        paciente.setApellidoMaterno("Barriga");
////        paciente.setApellidoPaterno("Torres");
////        paciente.setDni("25361485");
////        paciente.setEmail("jbtorres@gmail.com");
////        paciente.setFechaNacimiento(sdf.parse("1992-09-10"));
////        paciente.setGenero('M');
////        paciente.setDireccion("Calle Apocalipsis Nº 999");
////        paciente.setTelefono("45659878");
////        paciente.setSeguro(seguros.get(seguros.size()-1));
////        boPaciente.insertar(paciente);
//        //Listamos medicos, pacientes y consultorios
////        ArrayList<Consultorio> cons = boCons.listarTodos();
////        ArrayList<Medico> meds = boMed.listarTodos();
////        ArrayList<Paciente>pacs = boPaciente.listarTodos();
//        
////        
////        //Insertamos turno
//        TurnoBO boTurno= new TurnoBO();
////        Turno turno = new Turno();
////        turno.setDia(DiaSemana.VIERNES);
////        turno.setHoraInicio(java.sql.Time.valueOf("08:30:00"));
////        turno.setHoraFin(java.sql.Time.valueOf("09:30:00"));
////        boTurno.insertar(turno);
////        
////        turno.setDia(DiaSemana.SABADO);
////        turno.setHoraInicio(java.sql.Time.valueOf("10:30:00"));
////        turno.setHoraFin(java.sql.Time.valueOf("11:30:00"));
////        boTurno.insertar(turno);
//        
////        ArrayList<Turno> turnos = boTurno.listarTodos();
////        
//        HorarioTrabajoBO bohorario = new HorarioTrabajoBO();
////        HorarioTrabajo horario = new HorarioTrabajo();
////        horario.setMedico(meds.get(0));
////        horario.setTurno(turnos.get(1));
////        bohorario.insertar(horario);
////        
////        horario = new HorarioTrabajo();
////        horario.setMedico(meds.get(meds.size()-1));
////        horario.setTurno(turnos.get(1));
////        bohorario.insertar(horario);
////        dispo.setFecha(sdf.parse("1992-09-10"));
////        dispo.setHora(java.sql.Time.valueOf("08:30:00"));
////        dispo.setDisponible(true);
////        dispo.setTurnoMedico(turnos.get(turnos.size()-1));
////        DisponibilidadBO boDispo = new DisponibilidadBO();
////        boDispo.insertar(dispo);
////        
////        ArrayList<HorarioTrabajo> horarios = bohorario.listarTodos();
////        
//
//        
////        Cita cita = new Cita();
////        cita.setFecha(sdf.parse("1992-09-10"));
////        cita.setEstadoCita(EstadoCita.PROGRAMADA);
////        cita.setMotivoConsulta("Dolor abdominal y nauseas");
////        cita.setPaciente(pacs.get(0));
////        cita.setHorarioTrabajo(horarios.get(0));
////        CitaBO boCita = new CitaBO();
////       boCita.insertar(cita);
//
//        MedicamentoBO medicamentobo = new MedicamentoBO();
////        Medicamento medic = new Medicamento();
////        medic.setNombre("Paracetamol");
////        medic.setPresentacion("500 mg");
////        medic.setStock(100);
////        
////        medicamentobo.insertar(medic);
////        
////         medic = new Medicamento();
////        medic.setNombre("Rinodan");
////        medic.setPresentacion("500 mg");
////        medic.setStock(200);
//        
////        medicamentobo.insertar(medic);
////        ArrayList<Medicamento> medicamentos = medicamentobo.listarTodos();
//        
//        RecetaBO boreceta = new RecetaBO();
////        Receta receta = new Receta();
////        receta.setIndicaciones("Tomar cada 8 horas");
////        boreceta.insertar(receta);
////        
////        ArrayList<Receta> recetas = boreceta.listarTodas();
//        
////        LineaRecetaMedicamentoBO lineabo = new LineaRecetaMedicamentoBO();
////        LineaRecetaMedicamento linea = new LineaRecetaMedicamento();
////        linea.setCantidad(6);
////        linea.setMedicamento(medicamentos.get(1));
////        linea.setReceta(recetas.get(1));
//        
////        lineabo.insertar(linea);
///
///
///
///
//        TurnoBO boTurno = new TurnoBO();
//        
//
//        ArrayList<Turno> turnos = boTurno.listarTodos();
//
//        for(Turno t : turnos){
//            
//        }

        ConsultorioBO boCons = new ConsultorioBO();
        
        ArrayList<Consultorio> consultorios = new ArrayList<>();
        consultorios = boCons.listarXSede(1);
        
    }
}
