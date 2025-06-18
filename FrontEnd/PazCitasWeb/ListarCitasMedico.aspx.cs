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
    public partial class ListarCitasMedico : System.Web.UI.Page
    {
        private CitaWSClient citabo;
        BindingList<cita> citasmed;
        medico med;
        protected void Page_Load(object sender, EventArgs e)
        {
            lblMensaje.Text = "";
            citabo = new CitaWSClient();
            med = (medico)Session["medico"];
            if (citabo.listaCitaPorMedico(med.idUsuario) != null) {
                citasmed = new BindingList<cita>(citabo.listaCitaPorMedico(med.idUsuario));
                gvCitasMed.DataSource = citasmed;
                gvCitasMed.DataBind();

                Session["citasmed"] = citasmed;
            }
            else
            {
                lblMensaje.Text = "No tiene citas pendientes";
            }
        }

        protected void gvCitasMed_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvCitasMed.PageIndex = e.NewPageIndex;
            gvCitasMed.DataBind();
        }

        protected void BtonSeleccionar_Click(object sender, EventArgs e)
        {
            int idCita = Int32.Parse(((LinkButton)sender).CommandArgument);
            cita citaSeleccionada = citasmed.SingleOrDefault(x => x.idCita == idCita);
            Session["citaSeleccionada"] = citaSeleccionada;
            Response.Redirect("VerCitaMedico.aspx");
        }

        protected void gvCitasMed_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = ((paciente)DataBinder.Eval(e.Row.DataItem, "paciente")).nombre + " " + ((paciente)DataBinder.Eval(e.Row.DataItem, "paciente")).apellidoPaterno;
                DateTime fecha = (DateTime)DataBinder.Eval(e.Row.DataItem, "fecha");
                e.Row.Cells[1].Text = fecha.ToString("dd/MM/yyyy");
                //TurnoWSClient wsTurno = new TurnoWSClient();
                //turno turnoTemp = new turno();
                //e.Row.Cells[2].Text = (DataBinder.Eval(e.Row.DataItem, "horario.turno.horaInicio")).ToString();
                e.Row.Cells[3].Text = (DataBinder.Eval(e.Row.DataItem, "estadoCita")).ToString();
                e.Row.Cells[4].Text = (DataBinder.Eval(e.Row.DataItem, "estadoAtencion")).ToString();

            }
        }
    }
}