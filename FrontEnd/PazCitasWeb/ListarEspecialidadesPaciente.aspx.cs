using PazCitasWA.ServiciosWS;
using System;
using System.ComponentModel;

namespace PazCitasWA
{
    public partial class ListarEspecialidades_pacientes : System.Web.UI.Page
    {
        private EspecialidadWSClient wsEspecialidad;
        private BindingList<especialidad> especialidades;
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                wsEspecialidad = new EspecialidadWSClient();
                especialidades = new BindingList<especialidad>(wsEspecialidad.listarEspecialidad());


                // ENLAZAR DATOS AL REPEATER
                rptEspecialidades.DataSource = especialidades;
                rptEspecialidades.DataBind();
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al cargar especialidades: " + ex.Message;
                lblMensaje.Visible = true;
            }
        }
    }
}