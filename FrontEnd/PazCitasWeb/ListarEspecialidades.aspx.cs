using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Security.Cryptography;
using System.ServiceModel.Channels;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class ListarEspecialidades : System.Web.UI.Page
    {
        private EspecialidadWSClient wsEspecialidad;
        private BindingList<especialidad> especialidades;
        protected void Page_Load(object sender, EventArgs e)
        {
            wsEspecialidad = new EspecialidadWSClient();
            especialidades = new BindingList<especialidad>(wsEspecialidad.listarEspecialidad());
            gvEspecialidades.DataSource = especialidades;
            gvEspecialidades.DataBind();
        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarEspecialidad.aspx");
        }

        protected void gvEspecialidades_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEspecialidades.PageIndex = e.NewPageIndex;
            gvEspecialidades.DataBind();
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            int idEspecialidad = Int32.Parse(((LinkButton)sender).CommandArgument);
            especialidad espSeleccionada = especialidades.SingleOrDefault(x => x.idEspecialidad == idEspecialidad);
            Session["espSeleccionada"] = espSeleccionada;
            Response.Redirect("RegistrarEspecialidad.aspx?accion=modificar");
        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            int idEspecialidad = Int32.Parse(((LinkButton)sender).CommandArgument);
            wsEspecialidad.eliminarEspecialidad(idEspecialidad);
            Response.Redirect("ListarEspecialidades.aspx");
        }
    }
}