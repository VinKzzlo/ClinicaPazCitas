using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class ListarSeguros : System.Web.UI.Page
    {
        private SeguroWSClient wsSeguro;
        private BindingList<seguro> seguros;
        protected void Page_Load(object sender, EventArgs e)
        {
            wsSeguro = new SeguroWSClient();
            seguros = new BindingList<seguro>(wsSeguro.listarSeguro());
            gvSeguros.DataSource = seguros;
            gvSeguros.DataBind();
        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarSeguro.aspx");
        }

        protected void gvSeguros_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvSeguros.PageIndex = e.NewPageIndex;
            gvSeguros.DataBind();
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {

        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {

        }
    }
}