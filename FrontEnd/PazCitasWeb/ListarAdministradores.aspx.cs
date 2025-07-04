using PazCitasWA.ServiciosWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PazCitasWA
{
    public partial class ListarAdministradores : System.Web.UI.Page
    {
        private AdministradorWSClient wsAdmin;
        private BindingList<administrador> administradores;

        protected void Page_Load(object sender, EventArgs e)
        {
            wsAdmin = new AdministradorWSClient();
            administradores = new BindingList<administrador>(wsAdmin.listarAdministrador());
            gvAdministradores.DataSource = administradores;
            gvAdministradores.DataBind();
        }

        protected void gvAdministradores_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "dni").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "nombre").ToString() + " " +
                    DataBinder.Eval(e.Row.DataItem, "apellidoPaterno").ToString() + " " +
                    DataBinder.Eval(e.Row.DataItem, "apellidoMaterno").ToString();
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "email").ToString();
            }
        }

        protected void gvAdministradores_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvAdministradores.PageIndex = e.NewPageIndex;
            gvAdministradores.DataBind();
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            int idAdmin = Int32.Parse(((LinkButton)sender).CommandArgument);
            administrador adminSeleccionado = administradores.SingleOrDefault(x => x.idUsuario == idAdmin);
            Session["adminSeleccionado"] = adminSeleccionado;
            Response.Redirect("RegistrarAdministrador.aspx?accion=modificar");
        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            wsAdmin = new AdministradorWSClient();
            int idAdmin = Int32.Parse(((LinkButton)sender).CommandArgument);
            wsAdmin.eliminarAdministrador(idAdmin);
            Response.Redirect("ListarAdministradores.aspx");
        }

        protected void btnVer_Click(object sender, EventArgs e)
        {
            int idAdmin = Int32.Parse(((LinkButton)sender).CommandArgument);
            administrador adminSeleccionado = administradores.SingleOrDefault(x => x.idUsuario == idAdmin);
            Session["adminSeleccionado"] = adminSeleccionado;
            Response.Redirect("RegistrarAdministrador.aspx?accion=ver");
        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarAdministrador.aspx");
        }
    }
}