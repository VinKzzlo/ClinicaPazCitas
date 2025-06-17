using PazCitasWA.ServiciosWS;
using System;

namespace PazCitasWA
{
    public partial class RegistrarSede : System.Web.UI.Page
    {
        private SedeWSClient wsSede;
        private Estado estado;
        private sede objSede;
        protected void Page_Load(object sender, EventArgs e)
        {
            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                estado = Estado.Nuevo;
                objSede = new sede();
                lblTitulo.Text = "Registrar Sede";
            }
            else if (accion == "modificar")
            {
                estado = Estado.Modificar;
                lblTitulo.Text = "Modificar Sede";
                objSede = (sede)Session["sedeSeleccionada"];
                if (!IsPostBack)
                {
                    txtIDSede.Text = objSede.idSede.ToString();
                    txtNombre.Text = objSede.nombre;
                    txtDireccion.Text = objSede.direccion;
                }
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarEspecialidades.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            wsSede = new SedeWSClient();
            objSede.nombre = txtNombre.Text;
            objSede.direccion = txtDireccion.Text;
            try
            {
                if (estado == Estado.Nuevo)
                {
                    wsSede.insertarSede(objSede);
                }
                else if (estado == Estado.Modificar)
                {
                    wsSede.modificarSede(objSede);
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
            Response.Redirect("ListarSedes.aspx");
        }
    }
}