using PazCitasWA.ServiciosWS;
using System;

namespace PazCitasWA
{
    public partial class HomeMedico : System.Web.UI.Page
    {
        medico medLogeado;
        MedicoWSClient wsMedico;
        protected void Page_Load(object sender, EventArgs e)
        {
            wsMedico = new MedicoWSClient();
            if (!IsPostBack)
            {
                int idMedico = (int)Session["id_usuario"];
                medLogeado = wsMedico.obtenerMedico(idMedico);
                Session["medico"] = medLogeado;
                lblNombreMedico.Text = $"Bienvenido(a), Doctor(a) {medLogeado.nombre} {medLogeado.apellidoPaterno}";

            }
        }
    }
}