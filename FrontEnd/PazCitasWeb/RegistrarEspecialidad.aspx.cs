using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class RegistrarEspecialidad : System.Web.UI.Page
    {
        private EspecialidadWSClient wsEspecialidad;
        private Estado estado;
        private especialidad objEspecialidad;
        protected void Page_Load(object sender, EventArgs e)
        {
            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                estado = Estado.Nuevo;
                objEspecialidad = new especialidad();
                lblTitulo.Text = "Registrar Especialidad";
            }
            else if (accion == "modificar")
            {
                estado = Estado.Modificar;
                lblTitulo.Text = "Modificar Especialidad";
                objEspecialidad = (especialidad)Session["espSeleccionada"];
                if (!IsPostBack)
                {
                    txtIDArea.Text = objEspecialidad.idEspecialidad.ToString();
                    txtNombre.Text = objEspecialidad.nombre;
                    txtDescripcion.Text = objEspecialidad.descripcion;
                }
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            wsEspecialidad = new EspecialidadWSClient();
            objEspecialidad.nombre = txtNombre.Text;
            objEspecialidad.descripcion = txtDescripcion.Text;
            try
            {
                if (estado == Estado.Nuevo)
                {
                    wsEspecialidad.insertarEspecialidad(objEspecialidad);
                }
                else if (estado == Estado.Modificar)
                {
                    wsEspecialidad.modificarEspecialidad(objEspecialidad);
                }
            }
            catch (Exception ex)
            {
                /*lblMensajeError.Text = ex.Message;
                string script =
                    "mostrarModalError();";
                ScriptManager.RegisterStartupScript
                    (this, GetType(), "modalError", script, true);
                return;*/
            }
            Response.Redirect("ListarEspecialidades.aspx");
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarEspecialidades.aspx");
        }
    }
}