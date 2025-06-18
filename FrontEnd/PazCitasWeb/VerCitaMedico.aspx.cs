using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class VerCitaMedico : System.Web.UI.Page
    {

        CitaWSClient bocita;
        protected void Page_Load(object sender, EventArgs e)
        {
            bocita = new CitaWSClient();
            if (!IsPostBack)
            {
                cita citaselec = (cita)Session["citaSeleccionada"];
                txtDNIPaciente.Text = citaselec.paciente.dni;
                TextNombre.Text = citaselec.paciente.nombre + citaselec.paciente.apellidoPaterno;
                txtEmail.Text = citaselec.paciente.email;

                TxtNume.Text = citaselec.idCita.ToString();
                TxtFecha.Text = citaselec.fecha.ToString("dd/MM/yyyy");
                
                TextEstado.Text = citaselec.estadoAtencion.ToString();
                TextMotivo.Text = citaselec.motivoConsulta.ToString();

            }
        }

        
        protected void btnContinuar_Click(object sender, EventArgs e)
        {
            cita citaselec = (cita)Session["citaSeleccionada"];
            estadoAtencion es;
            es = estadoAtencion.EN_CONSULTORIO;
            citaselec.estadoAtencion = es;
            citaselec.estadoAtencionSpecified = true;
            bocita.modificarCita(citaselec);
            Response.Redirect("EmitirReceta.aspx");
        }

        protected void btnHistorial_Click(object sender, EventArgs e)
        {

        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            txtDNIPaciente.Text = "";
            TextNombre.Text = "";
            txtEmail.Text = "";

            TxtNume.Text = "";
            TxtFecha.Text = "";
            TextEstado.Text = "";
            TextMotivo.Text = "";

            Response.Redirect("ListarCitasMedico.aspx");
        }
    }
}