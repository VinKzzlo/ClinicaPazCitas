using PazCitasWA.ServiciosWS;
using System;
using System.ComponentModel;

namespace PazCitasWA
{
    public partial class ListarSedesPaciente : System.Web.UI.Page
    {
        private SedeWSClient wsCliente;
        private BindingList<sede> sedes;
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                wsCliente = new SedeWSClient();
                sedes = new BindingList<sede>(wsCliente.listarSede());

                // ENLAZAR DATOS AL REPEATER
                rptSedes.DataSource = sedes;
                rptSedes.DataBind();
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al cargar especialidades: " + ex.Message;
                lblMensaje.Visible = true;
            }
        }
    }
}