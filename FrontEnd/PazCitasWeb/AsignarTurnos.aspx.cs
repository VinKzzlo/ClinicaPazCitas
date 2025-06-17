using PazCitasWA.ServiciosWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PazCitasWA
{
    public partial class AsignarTurnos : System.Web.UI.Page
    {
        private SedeWSClient wsSede = new SedeWSClient();

        // Propiedades para mantener el estado entre postbacks
        public int idSedeSelec
        {
            get { return ViewState["idSedeSelec"] != null ? (int)ViewState["idSedeSelec"] : 0; }
            set { ViewState["idSedeSelec"] = value; }
        }

        public int idEspSelec
        {
            get { return ViewState["idEspSelec"] != null ? (int)ViewState["idEspSelec"] : 0; }
            set { ViewState["idEspSelec"] = value; }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                LoadDropDowns();
            }
        }

        private void LoadDropDowns()
        {
            ;
            ddlSede.DataSource = wsSede.listarSede();
            ddlSede.DataTextField = "nombre";
            ddlSede.DataValueField = "idSede";
            ddlSede.DataBind();

            ddlSede.Items.Insert(0, new ListItem("-- Seleccione --", ""));

            // Poner placeholder en especialidades
            ddlEspecialidad.Items.Clear();
            ddlEspecialidad.Items.Add(new ListItem("-- Seleccione sede primero --", ""));
            ddlEspecialidad.Enabled = false;

        }

        protected void ddlSede_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(ddlSede.SelectedValue))
            {
                idSedeSelec = int.Parse(ddlSede.SelectedValue);

                EspecialidadWSClient wsEspecialidad = new EspecialidadWSClient();
                ddlEspecialidad.DataSource = wsEspecialidad.listarXSede(idSedeSelec);
                ddlEspecialidad.DataTextField = "nombre";
                ddlEspecialidad.DataValueField = "idEspecialidad";
                ddlEspecialidad.DataBind();
                ddlEspecialidad.Items.Insert(0, new ListItem("-- Seleccione --", ""));
                ddlEspecialidad.Enabled = true;
            }

            // Limpia los médicos si cambias de sede
            rptMedicos.DataSource = null;
            rptMedicos.DataBind();
            lblVacio.Visible = false;
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(ddlSede.SelectedValue))
                idSedeSelec = int.Parse(ddlSede.SelectedValue);

            if (!string.IsNullOrEmpty(ddlEspecialidad.SelectedValue))
                idEspSelec = int.Parse(ddlEspecialidad.SelectedValue);

            // Cargar especialidades si no están cargadas aún
            if (ddlEspecialidad.Items.Count <= 1)
            {
                EspecialidadWSClient wsEspecialidad = new EspecialidadWSClient();
                ddlEspecialidad.DataSource = wsEspecialidad.listarXSede(idSedeSelec);
                ddlEspecialidad.DataTextField = "nombre";
                ddlEspecialidad.DataValueField = "idEspecialidad";
                ddlEspecialidad.DataBind();
                ddlEspecialidad.Items.Insert(0, new ListItem("-- Seleccione --", ""));
            }

            // Cargar médicos
            MedicoWSClient wsMedico = new MedicoWSClient();
            var lista = wsMedico.listarMedicoXEspXSede(idSedeSelec, idEspSelec);

            rptMedicos.DataSource = lista;
            rptMedicos.DataBind();

            lblVacio.Visible = lista == null || lista.Length == 0;
            lblVacio.Text = "No se encontraron médicos para los filtros seleccionados.";
        }

        protected void rptMedicos_ItemCommand(object sender, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "Asignar")
            {
                int idMedico = int.Parse(e.CommandArgument.ToString());
                Response.Redirect($"RegistrarTurnosMedico.aspx?idMedico={idMedico}");
            }
        }

    }
}