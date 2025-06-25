using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Security.Cryptography;
using System.ServiceModel.Description;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class MisPacientes : System.Web.UI.Page
    {
        private PacienteWSClient bopac;
        private NotaClinicaWSClient bonota;

        private HistorialMedicoWSClient bohist;
        BindingList<notaClinica> notas;
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            bopac = new PacienteWSClient();
            bohist = new HistorialMedicoWSClient();
            paciente pac;
            pac = bopac.obtenerPacientexDNI(txtDni.Text);

            if (pac != null)
            {
                textApellido.Text = pac.apellidoPaterno.ToString();
                TextNombre.Text = pac.nombre.ToString();
                TextID.Text = pac.idUsuario.ToString();
                TextTelefono.Text = pac.telefono.ToString();
                Textemail.Text = pac.email.ToString();

                pac.historialMedico = bohist.obtenerHistorial(pac.idUsuario);

                Session["pac"] = pac;
                bonota = new NotaClinicaWSClient();
                if (bonota.listarNotaClinicaXHistorial(pac.historialMedico.idhistorial) != null)
                {
                    notas = new BindingList<notaClinica>(bonota.listarNotaClinicaXHistorial(pac.historialMedico.idhistorial));
                    if (notas != null)
                    {
                        Session["notas"] = notas;
                        dgvNotas.DataSource = notas;
                        dgvNotas.DataBind();
                    }
                }
            }
            else
            {

                lblMensajeError.Text = "El DNI ingresado no esta registrado";
                string script = "showModalFormError();";
                ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
                return;

            }

        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {

        }

        protected void dgvNotas_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "descripcion").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "diagnostico").ToString();
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "observaciones").ToString();

            }
        }

        protected void dgvNotas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            /*dgvNotas.PageIndex = e.NewPageIndex;

            bopac = new PacienteWSClient();
            bohist = new HistorialMedicoWSClient();

            paciente pac = bopac.obtenerPacientexDNI(txtDni.Text);
            if (pac != null)
            {
                pac.historialMedico = bohist.obtenerHistorial(pac.idUsuario);

                bonota = new NotaClinicaWSClient();
                var notasList = bonota.listarNotaClinicaXHistorial(pac.historialMedico.idhistorial);

                if (notasList != null)
                {
                    BindingList<notaClinica> notas = new BindingList<notaClinica>(notasList);
                    dgvNotas.DataSource = notas;
                    dgvNotas.DataBind();
                }
            }*/
        }

        protected void lbModificar_Click(object sender, EventArgs e)
        {

        }

        protected void lbVisualizar_Click(object sender, EventArgs e)
        {

        }

        protected void btnVerDetalle_Click(object sender, EventArgs e)
        {
            int idNota = Int32.Parse(((LinkButton)sender).CommandArgument);
            notas = (BindingList<notaClinica>)Session["notas"];
            notaClinica notaSel = notas.SingleOrDefault(x => x.idNota == idNota);
            Session["notaSeleccionada"] = notaSel;
            Response.Redirect("EmitirReceta.aspx?accion=ver");
        }
    }
}