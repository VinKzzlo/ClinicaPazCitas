using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class ListarCitasPaciente : System.Web.UI.Page
    {
        private CitaWSClient wsCita;
        private BindingList<cita> citas;

        private DisponibilidadWSClient wsDisponibilidad;
        private BindingList<disponibilidad> disponibilidades;

        private TurnoMedicoWSClient wsTurnoMedico;
        private BindingList<turnoMedico> turnosMedicos;

        private MedicoWSClient wsMedico;
        private BindingList<medico> medicos;

        private EspecialidadWSClient wsExpecialidad;
        private BindingList<especialidad> especialidades;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (IdPacienteLogueado == 0)
            {
                // Manejar caso de no estar logueado (ya se haría en la propiedad IdPacienteLogueado)
                lblMensajeGeneral.Text = "Debe iniciar sesión para ver sus citas.";
                lblMensajeGeneral.Visible = true;
                rptMisCitas.DataSource = new BindingList<cita>(); // Vaciar repeater
                rptMisCitas.DataBind();
                return;
            }

            //if (!IsPostBack)
            //{
            //    wsCita = new CitaWSClient();
            //    citas = new BindingList<cita>(wsCita.listarCita());
            //    rptMisCitas.DataSource = citas;
            //    rptMisCitas.DataBind();
            //}
            try
            {
                wsCita = new CitaWSClient();
                BindingList<cita> citasTotal = new BindingList<cita>(wsCita.listarCita());
                citas = new BindingList<cita>();
                foreach (cita c in citasTotal)
                {
                    if(c.paciente.idUsuario == IdPacienteLogueado)
                    {
                        citas.Add(c);
                    }
                }

                wsTurnoMedico = new TurnoMedicoWSClient();
                turnosMedicos = new BindingList<turnoMedico>(wsTurnoMedico.listarTurnoMedico());

                wsMedico = new MedicoWSClient();
                medicos = new BindingList<medico>(wsMedico.listarMedico());

                wsExpecialidad = new EspecialidadWSClient();
                especialidades = new BindingList<especialidad>(wsExpecialidad.listarEspecialidad());

                wsDisponibilidad = new DisponibilidadWSClient();
                disponibilidades = new BindingList<disponibilidad>(wsDisponibilidad.listarDisponibilidad());

                // ENLAZAR DATOS AL REPEATER
                rptMisCitas.DataSource = citas;
                rptMisCitas.DataBind();
            }
            catch (Exception ex)
            {
                lblMensajeGeneral.Text = "Error al cargar tus citas: " + ex.Message;
                lblMensajeGeneral.Visible = true;
            }
        }
        private int IdPacienteLogueado // Prueba
        {
            get
            {
                if (Session["IdPaciente"] != null && int.TryParse(Session["IdPaciente"].ToString(), out int id))
                {
                    return id;
                }
                // Redirigir a login si no hay sesión o devolver un valor que indique error
                // Response.Redirect("Login.aspx"); 
                return 3; //Para la prueba
            }
        }

        protected void rptMisCitas_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                cita data = (cita)e.Item.DataItem;

                var lit = (Literal)e.Item.FindControl("litNombreMedico");
                if (lit != null)
                {
                    int idDisponibilidad = data.disponibilidad.idDisponibilidad;
                    //wsTurnoMedico = new TurnoMedicoWSClient();
                    //turnosMedicos = new BindingList<turnoMedico>(wsTurnoMedico.listarTurnoMedico());
                    disponibilidad disp = disponibilidades.FirstOrDefault(c => c.idDisponibilidad == idDisponibilidad);
                    turnoMedico turmen = turnosMedicos.FirstOrDefault(c => c.idTurno == disp.turnoMedico.idTurno);

                    //wsMedico = new MedicoWSClient();
                    //medicos = new BindingList<medico>(wsMedico.listarMedico());
                    medico med = medicos.FirstOrDefault(c => c.idUsuario == turmen.medico.idUsuario);
                    lit.Text ="Dr. " + med.nombre.ToString() + med.apellidoPaterno.ToString();
                }
                lit = (Literal)e.Item.FindControl("litSubNom");
                if (lit != null)
                {
                    int idDisponibilidad = data.disponibilidad.idDisponibilidad;
                    disponibilidad disp = disponibilidades.FirstOrDefault(c => c.idDisponibilidad == idDisponibilidad);
                    turnoMedico turmen = turnosMedicos.FirstOrDefault(c => c.idTurno == disp.turnoMedico.idTurno);
                    medico med = medicos.FirstOrDefault(c => c.idUsuario == turmen.medico.idUsuario);
                    lit.Text = med.email.ToString();
                }
                lit = (Literal)e.Item.FindControl("litEspecialidadMedico");
                if (lit != null)
                {
                    int idEspecialidad = data.especialidad.idEspecialidad;
                    especialidad esp = especialidades.FirstOrDefault(c => c.idEspecialidad == idEspecialidad);

                    lit.Text = esp.nombre.ToString();
                }
                lit = (Literal)e.Item.FindControl("litEstadoCita");
                if (lit != null)
                {
                    if(estadoCita.CANCELADA == data.estadoCita)
                    {
                        ;
                    }
                    else if(estadoCita.PROGRAMADA == data.estadoCita)
                    {
                        ;
                    }
                    else if(estadoCita.ATENDIDA == data.estadoCita)
                    {
                        ;
                    }
                    lit.Text = data.estadoCita.ToString();
                }
                lit = (Literal)e.Item.FindControl("litFecha");
                if (lit != null)
                {
                    DateTime d = data.fecha;
                    lit.Text = d.ToString("dd/MM/yyyy");
                }
                lit = (Literal)e.Item.FindControl("litHora");
                if (lit != null)
                {
                    int idDisponibilidad = data.disponibilidad.idDisponibilidad;
                    disponibilidad disp = disponibilidades.FirstOrDefault(c => c.idDisponibilidad == idDisponibilidad);
                    DateTime d = disp.hora;

                    lit.Text = d.ToString("HH:mm");
                }
            }
        }

        protected void btnCancelarCita_Click(object sender, EventArgs e)
        {

        }

        protected void btnVerDetalle_Click(object sender, EventArgs e)
        {

        }
    }
}