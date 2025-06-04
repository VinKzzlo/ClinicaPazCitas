using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Security.Cryptography;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class ListarTratamientos : System.Web.UI.Page
    {
        private MedicamentoWSClient wsMedicamento;
        private BindingList<medicamento> medicamentos;
        protected void Page_Load(object sender, EventArgs e)
        {
            wsMedicamento = new MedicamentoWSClient();
            medicamentos = new BindingList<medicamento>(wsMedicamento.listarMedicamento());
            gvTratamientos.DataSource = medicamentos;
            gvTratamientos.DataBind();
        }

        protected void gvTratamientos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvTratamientos.PageIndex = e.NewPageIndex;
            gvTratamientos.DataBind();
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            int idMedicamento = Int32.Parse(((LinkButton)sender).CommandArgument);
            medicamento medicamentoSeleccionado = medicamentos.SingleOrDefault(x => x.idMedicamento == idMedicamento);
            Session["medicamentoSeleccionado"] = medicamentoSeleccionado;
            Response.Redirect("RegistrarTratamiento.aspx?accion=modificar");
        }

       

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarTratamiento.aspx");
        }

        protected void gvTratamientos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                // Index de columna Stock, cuenta desde 0
                int stock = Convert.ToInt32(DataBinder.Eval(e.Row.DataItem, "stock"));
                TableCell cell = e.Row.Cells[3]; // ← Columna "Stock" (ajusta si cambia el orden)

                // Crear un contenedor div para el stock
                var div = new System.Web.UI.WebControls.Literal();
                div.Text = $"<div class='stock-box'>{stock}</div>";  // Se muestra el valor del stock

                // Limpiar la celda y agregar el div con el texto
                cell.Controls.Clear();
                cell.Controls.Add(div);

                // Agregar clases según el stock
                if (stock <= 10)
                {
                    cell.CssClass += " bg-danger";
                }
                else if (stock <= 30)
                {
                    cell.CssClass += " bg-warning";
                }
                else
                {
                    cell.CssClass += " bg-success";
                }
            }
        }
    }
}