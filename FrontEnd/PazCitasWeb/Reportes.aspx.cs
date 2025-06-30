using System;

namespace PazCitasWA
{
    public partial class Reportes : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            Response.Redirect("VisualizarReporte.aspx");
        }
    }
}