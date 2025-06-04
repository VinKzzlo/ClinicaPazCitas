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