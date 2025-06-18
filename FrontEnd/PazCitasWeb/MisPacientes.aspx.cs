using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.ServiceModel.Description;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class MisPacientes : System.Web.UI.Page
    {
        private PacienteWSClient bopac;
        private NotaClinicaWSClient bonota;

        private HistorialMedicoWSClient bohist;
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

                bonota = new NotaClinicaWSClient();
                if (bonota.listarNotaClinicaXHistorial(pac.historialMedico.idhistorial) != null)
                {
                    BindingList<notaClinica> notas = new BindingList<notaClinica>(bonota.listarNotaClinicaXHistorial(pac.historialMedico.idhistorial));
                    if (notas != null)
                    {
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
            dgvNotas.PageIndex = e.NewPageIndex;
            dgvNotas.DataBind();
        }

        protected void lbModificar_Click(object sender, EventArgs e)
        {

        }

        protected void lbVisualizar_Click(object sender, EventArgs e)
        {

        }
    
    }
}