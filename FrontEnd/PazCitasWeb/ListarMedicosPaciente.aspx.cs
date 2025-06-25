using System;
using System.ComponentModel;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class ListarMedicosPaciente : System.Web.UI.Page
    {
        private MedicoWSClient wsMedico;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // La primera vez que carga la página, mostramos todos los médicos.
                CargarMedicos("");
            }
        }

        /// <summary>
        /// Este evento se dispara automáticamente cuando el texto en el TextBox cambia 
        /// y el usuario sale del campo (pierde el foco).
        /// </summary>
        protected void txtBusqueda_TextChanged(object sender, EventArgs e)
        {
            string filtro = txtBusqueda.Text.Trim();
            CargarMedicos(filtro);
        }

        /// <summary>
        /// Método centralizado para obtener los médicos y enlazarlos al Repeater.
        /// </summary>
        /// <param name="filtro">El texto por el cual buscar.</param>
        private void CargarMedicos(string filtro)
        {
            try
            {
                wsMedico = new MedicoWSClient();
                var medicos = new BindingList<medico>(wsMedico.listarMedicoXCadena(filtro));

                rptMedicos.DataSource = medicos;
                rptMedicos.DataBind();

                // Lógica para mostrar el mensaje de "No hay resultados"
                if (medicos != null && medicos.Count == 0 && !string.IsNullOrEmpty(filtro))
                {
                    pnlNoResultados.Visible = true;
                    // Mostramos el término que buscó el usuario para darle feedback
                    litTerminoBusqueda.Text = Server.HtmlEncode(filtro);
                }
                else
                {
                    pnlNoResultados.Visible = false;
                }

                lblMensaje.Visible = false;
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Ocurrió un error al cargar los médicos. Por favor, intente más tarde.";
                lblMensaje.Visible = true;
                System.Diagnostics.Debug.WriteLine("Error en CargarMedicos: " + ex.Message);
                rptMedicos.DataSource = null;
                rptMedicos.DataBind();
            }
        }

        // Ya no necesitamos un evento explícito para el botón buscar, pero lo dejamos por si acaso.
        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            CargarMedicos(txtBusqueda.Text.Trim());
        }

        // No es necesario tener lógica en el ItemDataBound si todo se maneja con Eval().
        protected void rptMedicos_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            // Puedes añadir lógica aquí en el futuro si es necesario.
        }
    }
}