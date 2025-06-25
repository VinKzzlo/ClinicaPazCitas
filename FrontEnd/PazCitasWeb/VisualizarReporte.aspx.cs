using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class VisualizarReporte : System.Web.UI.Page
    {
        private ReporteWSClient boReporte;
        protected void Page_Load(object sender, EventArgs e)
        {
            boReporte = new ReporteWSClient();
            string nombrePaciente = "";
            if (Session["Paciente"] != null)
            {
                paciente paciente = (paciente)Session["Paciente"];
                nombrePaciente = paciente.nombre;
            }

            byte[] reporte = boReporte.generarReporteCita(nombrePaciente);
            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "inline;filename=ReporteCitasProgramadas");
            Response.BinaryWrite(reporte);
            Response.End();
        }
    }
}