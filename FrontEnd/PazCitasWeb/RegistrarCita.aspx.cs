using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Web.UI;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class RegistrarCita : System.Web.UI.Page
    {
        private SedeWSClient boSede;
        private EspecialidadWSClient boEspecialidad;
        private MedicoWSClient boMedico;
        //private TurnoMedicoWSClient boTurno;

        protected System.Web.UI.HtmlControls.HtmlGenericControl barraProgreso;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                boSede = new SedeWSClient();
                ddlSede.DataSource = boSede.listarSede();
                ddlSede.DataTextField = "nombre";
                ddlSede.DataValueField = "idSede";
                ddlSede.DataBind();
                ddlSede.Items.Insert(0, new System.Web.UI.WebControls.ListItem("<Seleccione una sede>", ""));
                ActualizarProgreso(0);
            }
        }

        protected void btnPaso1_Click(object sender, EventArgs e)
        {
            Session["idSede"] = int.Parse(ddlSede.SelectedValue);
            boEspecialidad = new EspecialidadWSClient();
            ddlEspecialidad.DataSource = boEspecialidad.listarXSede((int)Session["idSede"]);
            ddlEspecialidad.DataTextField = "nombre";
            ddlEspecialidad.DataValueField = "idEspecialidad";
            ddlEspecialidad.DataBind();
            ddlEspecialidad.Items.Insert(0, new System.Web.UI.WebControls.ListItem("<Seleccione una especialidad>", ""));
            pnlPaso1.Visible = false;
            pnlPaso2.Visible = true;
            ActualizarProgreso(16);
        }

        protected void btnAtras2_Click(object sender, EventArgs e)
        {
            pnlPaso2.Visible = false;
            pnlPaso1.Visible = true;
            ActualizarProgreso(0);
        }

        protected void btnPaso2_Click(object sender, EventArgs e)
        {
            Session["idEspecialidad"] = int.Parse(ddlEspecialidad.SelectedValue);
            boMedico = new MedicoWSClient();
            ddlMedico.DataSource = boMedico.listarMedicoXEspXSede((int)Session["idSede"], (int)Session["idEspecialidad"]);
            ddlMedico.DataTextField = "nombre";
            ddlMedico.DataValueField = "idUsuario";
            ddlMedico.DataBind();
            ddlMedico.Items.Insert(0, new System.Web.UI.WebControls.ListItem("<Seleccione un médico>", ""));
            pnlPaso2.Visible = false;
            pnlPaso3.Visible = true;
            ActualizarProgreso(33);
        }

        protected void btnAtras3_Click(object sender, EventArgs e)
        {
            pnlPaso3.Visible = false;
            pnlPaso2.Visible = true;
            ActualizarProgreso(16);
        }

        protected void btnPaso3_Click(object sender, EventArgs e)
        {
            Session["idMedico"] = int.Parse(ddlMedico.SelectedValue);
            //boTurno = new TurnoMedicoWSClient();
            //ddlDias.DataSource = boTurno.listarDiasAtencionPorMedico((int)Session["idMedico"]);
            ddlDias.DataBind();
            ddlDias.Items.Insert(0, new System.Web.UI.WebControls.ListItem("<Seleccione un día>", ""));
            pnlPaso3.Visible = false;
            pnlPaso4.Visible = true;
            ActualizarProgreso(50);
        }

        protected void btnAtras4_Click(object sender, EventArgs e)
        {
            pnlPaso4.Visible = false;
            pnlPaso3.Visible = true;
            ActualizarProgreso(33);
        }

        protected void btnPaso4_Click(object sender, EventArgs e)
        {
            string dia = ddlDias.SelectedValue;
            //boTurno = new TurnoMedicoWSClient();
            //int idMedico = (int)Session["idMedico"];
            //ddlHorarios.DataSource = boTurno.listarHorariosPorMedicoYDia(idMedico, dia);
            ddlHorarios.DataBind();
            ddlHorarios.Items.Insert(0, new System.Web.UI.WebControls.ListItem("<Seleccione un horario>", ""));
            pnlPaso4.Visible = false;
            pnlPaso5.Visible = true;
            ActualizarProgreso(66);
        }

        protected void btnAtras5_Click(object sender, EventArgs e)
        {
            pnlPaso5.Visible = false;
            pnlPaso4.Visible = true;
            ActualizarProgreso(50);
        }

        protected void btnPaso5_Click(object sender, EventArgs e)
        {
            lblSede.Text = ddlSede.SelectedItem.Text;
            lblEspecialidad.Text = ddlEspecialidad.SelectedItem.Text;
            lblMedico.Text = ddlMedico.SelectedItem.Text;
            lblFecha.Text = ddlDias.SelectedItem.Text;
            lblHora.Text = ddlHorarios.SelectedItem.Text;

            pnlPaso5.Visible = false;
            pnlPaso6.Visible = true;
            ActualizarProgreso(100);
        }

        protected void btnAtras6_Click(object sender, EventArgs e)
        {
            pnlPaso6.Visible = false;
            pnlPaso5.Visible = true;
            ActualizarProgreso(66);
        }

        protected void btnConfirmar_Click(object sender, EventArgs e)
        {
            //Implementar ingreso de cita

        }

        private void ActualizarProgreso(int porcentaje)
        {
            barraProgreso.Style["width"] = porcentaje + "%";
        }
    }
}
