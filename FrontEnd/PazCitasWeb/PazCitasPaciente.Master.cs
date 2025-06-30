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
    public partial class PazCitasPaciente : System.Web.UI.MasterPage
    {
        private PacienteWSClient wsPaciente;
        private paciente pct;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (IdPacienteLogueado == 0)
            {
                Response.Redirect("IniciarSesion.aspx");
            }
            if (!IsPostBack)
            {
                wsPaciente = new PacienteWSClient();
                pct = wsPaciente.obtenerPacienteXiD(IdPacienteLogueado);
                lblNombreCompleto.Text = pct.nombre + " " + pct.apellidoPaterno;
                lblCabeceraSaludo.Text = "¡Hola, " + pct.nombre + "! Bienvenido de nuevo.";
            }
        }
        private int IdPacienteLogueado 
        {
            get
            {
                if (Session["id_usuario"] != null && int.TryParse(Session["id_usuario"].ToString(), out int id))
                {
                    return id;
                }
                Response.Redirect("Login.aspx");
                return -1; 
            }
        }
    }
}