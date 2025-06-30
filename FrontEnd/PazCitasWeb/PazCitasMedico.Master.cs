using PazCitasWA.ServiciosWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PazCitasWA
{
    public partial class PazCitasMedico : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            MedicoWSClient wsMedico;
            try
            {
                // Verificar si hay un administrador en sesión
                if (Session["id_usuario"] != null)
                {
                    int idMedico = (int)Session["id_usuario"];
                    medico medicoSelect = new medico();
                    wsMedico = new MedicoWSClient();
                    medicoSelect = wsMedico.obtenerMedico(idMedico);
                    // Mostrar nombre completo del administrador
                    string nombreCompleto = $"{medicoSelect.nombre} {medicoSelect.apellidoPaterno}";

                    // Si el nombre es muy largo, truncarlo
                    if (nombreCompleto.Length > 20)
                    {
                        nombreCompleto = nombreCompleto.Substring(0, 17) + "...";
                    }

                    lblNombreCompleto.Text = nombreCompleto;
                }
                else
                {
                    // Si no hay sesión, redirigir al login
                    Response.Redirect("~/Inicio.aspx");
                }
            }
            catch (Exception ex)
            {
                // En caso de error, mostrar texto por defecto y log del error
                lblNombreCompleto.Text = "Médico";

                // Opcional: Log del error
                System.Diagnostics.Debug.WriteLine($"Error cargando médico: {ex.Message}");
            }
        }
    }
}