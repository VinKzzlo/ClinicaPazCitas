using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class RegistrarTratamiento : System.Web.UI.Page
    {
        private MedicamentoWSClient wsMedicamento;
        private medicamento objMedicamento;
        private Estado estado;
        protected void Page_Load(object sender, EventArgs e)
        {
            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                estado = Estado.Nuevo;
                objMedicamento = new medicamento();
                lblTitulo.Text = "Registrar Sede";
            }
            else if (accion == "modificar")
            {
                estado = Estado.Modificar;
                lblTitulo.Text = "Modificar Sede";
                objMedicamento = (medicamento)Session["medicamentoSeleccionado"];
                if (!IsPostBack)
                {
                    txtIDMedicamento.Text = objMedicamento.idMedicamento.ToString();
                    txtNombre.Text = objMedicamento.nombre;
                    txtPresentación.Text = objMedicamento.presentacion;
                    txtStock.Text = objMedicamento.stock.ToString();
                }
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarTratamientos.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            wsMedicamento = new MedicamentoWSClient();
            objMedicamento.nombre = txtNombre.Text;
            objMedicamento.presentacion = txtPresentación.Text;
            objMedicamento.stock = Int32.Parse(txtStock.Text);
            try
            {
                if (estado == Estado.Nuevo)
                {
                    wsMedicamento.insertarMedicamento(objMedicamento);
                }
                else if (estado == Estado.Modificar)
                {
                    wsMedicamento.modificarMedicamento(objMedicamento);
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
            Response.Redirect("ListarTratamientos.aspx");
        }
    }
}