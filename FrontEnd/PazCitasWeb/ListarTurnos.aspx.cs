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
    public partial class ListarTurnos : System.Web.UI.Page
    {

        private TurnoMedicoWSClient boTurnoMedico;
        private MedicoWSClient boMedico;
        private BindingList<medico> medicos;

        protected void Page_Load(object sender, EventArgs e)
        {

            if (!IsPostBack)
            {
                /*FALTA VALIDAR Y HACER QUE EN VEZ DE '17' APAREZCA EL ID DEL MEDICO QUE INICIÓ SESION.*/
                int idMedico = 17; // <- asegúrate que lo obtienes correctamente

                boTurnoMedico = new TurnoMedicoWSClient();
                BindingList<turnoMedico> turnos = new BindingList<turnoMedico>(
                    boTurnoMedico.listarTurnoMedico().Where(t => t.medico.idUsuario == idMedico).ToList()
                );

                rptTurnosMedico.DataSource = turnos;
                rptTurnosMedico.DataBind();
            }

            
            //boMedico = new MedicoWSClient();
            //BindingList<medico> medicos = new BindingList<medico>(boMedico.listarMedico());
            //dgvMedicos.DataSource = medicos;
            //dgvMedicos.DataBind();

            //turnoMedico = new TurnoMedicoWSClient();



            /*            boEmpleado = new EmpleadoBO();
                        empleados = boEmpleado.listarTodos();
                        dgvMedicos.DataSource = medicos;
                        dgvMedicos.DataBind();*/
        }

        protected void rptTurnosMedico_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {

            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                turnoMedico turno = (turnoMedico)e.Item.DataItem;

                Literal lit = (Literal)e.Item.FindControl("litDia");
                if (lit != null) lit.Text = turno.dia.ToString();

                lit = (Literal)e.Item.FindControl("litHorario");
                if (lit != null)
                {
                    lit.Text = $"De {turno.horaInicio:hh\\:mm} a {turno.horaFin:hh\\:mm}";
                }


                // ESPECIALIDAD
                lit = (Literal)e.Item.FindControl("litEspecialidad");
                if (lit != null)
                {
                    try
                    {
                        int idEspecialidad = turno.medico.especialidad != null ? turno.medico.especialidad.idEspecialidad : 0;
                        if (idEspecialidad > 0)
                        {
                            //especialidad esp = ObtenerEspecialidadPorID(idEspecialidad);
                            EspecialidadWSClient wsEsp = new EspecialidadWSClient();
                            especialidad esp = wsEsp.obtenerEspecialidadPorId(idEspecialidad);
                            System.Console.WriteLine(esp.nombre);

                            lit.Text = esp != null ? esp.nombre : "Sin especialidad";
                        }
                        else
                        {
                            lit.Text = "Sin especialidad";
                        }
                    }
                    catch { lit.Text = "Error al obtener especialidad"; }
                }

                // SEDE
                lit = (Literal)e.Item.FindControl("litSede");
                if (lit != null)
                {
                    try
                    {
                        int idSede = turno.medico.sede != null ? turno.medico.sede.idSede : 0;
                        if (idSede > 0)
                        {
                            //sede sed = ObtenerSedePorID(idSede);
                            SedeWSClient wsSede = new SedeWSClient();
                            sede sed = wsSede.obtenerSedePorId(idSede);

                            lit.Text = sed != null ? sed.nombre : "Sin sede";
                        }
                        else
                        {
                            lit.Text = "Sin sede";
                        }
                    }
                    catch { lit.Text = "Error al obtener sede"; }
                }

                // CONSULTORIO
                lit = (Literal)e.Item.FindControl("litConsultorio");
                if (lit != null)
                {
                    try
                    {
                        int idConsultorio = turno.consultorio != null ? turno.consultorio.idConsultorio : 0;
                        if (idConsultorio > 0)
                        {
                            //consultorio cons = ObtenerConsultorioPorID(idConsultorio);
                            ConsultorioWSClient wsCons = new ConsultorioWSClient();
                            consultorio cons = wsCons.obtenerConsultorioPorId(idConsultorio);

                            lit.Text = cons != null ? cons.nombreConsultorio : "Sin consultorio";
                        }
                        else
                        {
                            lit.Text = "Sin consultorio";
                        }
                    }
                    catch { lit.Text = "Error al obtener consultorio"; }
                }
            }
        }


        protected void btnVerDisponibilidad_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string turnoId = btn.CommandArgument;

            Response.Redirect("ListarDisponibilidad.aspx?turnoId=" + turnoId);
        }








        protected void ListarDisponibilidad_Click(object sender, EventArgs e)
        {

        }

        protected void dgvMedicos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            //dgvMedicos.PageIndex = e.NewPageIndex;
            //dgvMedicos.DataBind();
        }
    }
}