using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class ListarConsultorios : System.Web.UI.Page
    {
        private ConsultorioWSClient wsConsultorio;
        private BindingList<consultorio> consultorios;
        protected void Page_Load(object sender, EventArgs e)
        {
            wsConsultorio = new ConsultorioWSClient();
            consultorios = new BindingList<consultorio>(wsConsultorio.listarConsultorio());
            gvConsultorios.DataSource = consultorios;
            gvConsultorios.DataBind();
        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarConsultorio.aspx");
        }

        protected void gvConsultorios_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvConsultorios.PageIndex = e.NewPageIndex;
            gvConsultorios.DataBind();
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            int idConsultorio = Int32.Parse(((LinkButton)sender).CommandArgument);
            consultorio consultSeleccionado = consultorios.SingleOrDefault(x=>x.idConsultorio == idConsultorio);
            Session["consultSeleccionado"] = consultSeleccionado;
            Response.Redirect("RegistrarConsultorio.aspx?accion=modificar");
        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            int idConsultorio = Int32.Parse(((LinkButton)sender).CommandArgument);
            wsConsultorio.eliminarConsultorio(idConsultorio);
            Response.Redirect("ListarConsultorios.aspx");
        }
    }
}