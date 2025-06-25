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

        private MedicoWSClient wsMedico;
        private TurnoWSClient wsTurno;

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

            if (!IsPostBack)
            {
                //wsCita = new CitaWSClient();
                //BindingList<cita> citas = new BindingList<cita>(wsCita.listarXPaciente(IdPacienteLogueado));
                //if (citas != null)
                //{
                //    rptMisCitas.DataSource = citas;
                //    rptMisCitas.DataBind();
                //}
                //else
                //{
                //    lblMensajeGeneral.Text = "Error al cargar tus citas: ";
                //    lblMensajeGeneral.Visible = true;
                //}
            }
            try
            {
                wsCita = new CitaWSClient();
                citas = new BindingList<cita>(wsCita.listarXPacienteCompletoSinDatosPaciente(IdPacienteLogueado));

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
                return 1; //Para la prueba
            }
        }

        protected void rptMisCitas_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                cita ct = (cita)e.Item.DataItem;
                var lit = (Literal)e.Item.FindControl("litNombreMedico");
                if (lit != null)
                {
                    lit.Text = "Dr. " + ct.horarioTrabajo.medico.nombre.ToString() + ct.horarioTrabajo.medico.apellidoPaterno.ToString();
                }

                lit = (Literal)e.Item.FindControl("litSubNom");
                if (lit != null)
                {
                    lit.Text = ct.horarioTrabajo.medico.email.ToString();
                }

                lit = (Literal)e.Item.FindControl("litEspecialidadMedico");
                if (lit != null)
                {

                    lit.Text = ct.horarioTrabajo.medico.especialidad.nombre.ToString();
                }

                lit = (Literal)e.Item.FindControl("litEstadoCita");
                if (lit != null)
                {
                    if (estadoCita.CANCELADA == ct.estadoCita)
                    {
                        ;
                    }
                    else if (estadoCita.PROGRAMADA == ct.estadoCita)
                    {
                        ;
                    }
                    else if (estadoCita.ATENDIDA == ct.estadoCita)
                    {
                        ;
                    }
                    lit.Text = ct.estadoCita.ToString();
                }

                lit = (Literal)e.Item.FindControl("litFecha");
                if (lit != null)
                {
                    DateTime d = ct.fecha;
                    lit.Text = d.ToString("dd/MM/yyyy");
                }

                lit = (Literal)e.Item.FindControl("litHora");
                if (lit != null)
                {

                    lit.Text = ct.horarioTrabajo.turno.horaInicio.ToString("HH:mm");
                }
            }
        }

        protected void btnCancelarCita_Click(object sender, EventArgs e)
        {

        }

        protected void btnVerDetalle_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string idCita = btn.CommandArgument;
            Response.Redirect("DetalleCitaPaciente.aspx?id=" + idCita);
        }
    }
}