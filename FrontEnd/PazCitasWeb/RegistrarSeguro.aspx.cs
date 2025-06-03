using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class RegistrarSeguro : System.Web.UI.Page
    {
        private SeguroWSClient wsSeguro;
        private seguro objSeguro;
        private Estado estado;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                wsSeguro = new SeguroWSClient();
                CargarTipoSeguro();
            }
            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                estado = Estado.Nuevo;
                objSeguro = new seguro();
                lblTitulo.Text = "Registrar Seguro";
            }
            else if (accion == "modificar")
            {
                estado = Estado.Modificar;
                lblTitulo.Text = "Modificar Seguro";
                objSeguro = (seguro)Session["seguroSeleccionado"];
                if (!IsPostBack)
                {
                    txtIDSeguro.Text = objSeguro.idSeguro.ToString();
                    txtNombreSeguro.Text = objSeguro.nombreSeguro;
                    txtlblPorcentCobertura.Text = objSeguro.porcentajeCobertura.ToString("F2");
                    ddlTipo.SelectedValue = ((int)objSeguro.tipo).ToString();
                    dtpVigencia.Value = objSeguro.vigencia.ToString("yyyy-MM-dd");

                }
            }

        }

        private void CargarTipoSeguro()
        {
            ddlTipo.DataSource = Enum.GetValues(typeof(tipoSeguro))
                                     .Cast<tipoSeguro>()
                                     .Select(t => new { Id = (int)t, Nombre = t.ToString() });
            ddlTipo.DataTextField = "Nombre";
            ddlTipo.DataValueField = "Id";
            ddlTipo.DataBind();

            ddlTipo.Items.Insert(0, new ListItem("-- Seleccione --", ""));
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarSeguros.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            wsSeguro = new SeguroWSClient();
            objSeguro.nombreSeguro = txtNombreSeguro.Text;
            objSeguro.porcentajeCobertura = Double.Parse(txtlblPorcentCobertura.Text);
            int idTipo = Int32.Parse(ddlTipo.SelectedValue);
            objSeguro.tipo = (tipoSeguro)idTipo;
            objSeguro.tipoSpecified = true;
            objSeguro.vigencia = DateTime.Parse(dtpVigencia.Value);
            objSeguro.vigenciaSpecified = true;

            try
            {
                if (estado == Estado.Nuevo)
                {
                    wsSeguro.insertarSeguro(objSeguro);
                }
                else if (estado == Estado.Modificar)
                {
                    wsSeguro.modificarSeguro(objSeguro);
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
            Response.Redirect("ListarSeguros.aspx");
        }
    }
}