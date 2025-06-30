using System;
using System.ComponentModel;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class ListarMedicosPaciente : System.Web.UI.Page
    {
        private MedicoWSClient wsMedico;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarMedicos("");
            }
        }

        protected void txtBusqueda_TextChanged(object sender, EventArgs e)
        {
            string filtro = txtBusqueda.Text.Trim();
            CargarMedicos(filtro);
        }
        private void CargarMedicos(string filtro)
        {
            try
            {
                wsMedico = new MedicoWSClient();
                var medicos = new BindingList<medico>(wsMedico.listarMedicoXCadena(filtro));

                rptMedicos.DataSource = medicos;
                rptMedicos.DataBind();

                if (medicos != null && medicos.Count == 0 && !string.IsNullOrEmpty(filtro))
                {
                    pnlNoResultados.Visible = true;
                    litTerminoBusqueda.Text = Server.HtmlEncode(filtro);
                }
                else
                {
                    pnlNoResultados.Visible = false;
                }

                lblMensaje.Visible = false;
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Ocurrió un error al cargar los médicos. Por favor, intente más tarde.";
                lblMensaje.Visible = true;
                System.Diagnostics.Debug.WriteLine("Error en CargarMedicos: " + ex.Message);
                rptMedicos.DataSource = null;
                rptMedicos.DataBind();
            }
        }
        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            CargarMedicos(txtBusqueda.Text.Trim());
        }

        protected void rptMedicos_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            //NO es necesario una logica para el repeater
        }
    }
}