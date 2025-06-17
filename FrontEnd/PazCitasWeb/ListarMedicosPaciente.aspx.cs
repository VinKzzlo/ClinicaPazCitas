using PazCitasWA.ServiciosWS;
using System;
using System.ComponentModel;
using System.Web.UI.WebControls;

namespace PazCitasWA
{
    public partial class ListarMedicosPaciente : System.Web.UI.Page
    {
        private MedicoWSClient wsMedico;
        private BindingList<medico> medicos;
        protected void Page_Load(object sender, EventArgs e)
        {
            // Para búsqueda "en vivo" al cambiar el texto
            //txtBusqueda.AutoPostBack = true;
            //txtBusqueda.TextChanged += TxtBusqueda_TextChanged;

            if (!IsPostBack)
            {
                wsMedico = new MedicoWSClient();
                medicos = new BindingList<medico>(wsMedico.listarMedico());

                rptMedicos.DataSource = medicos;
                rptMedicos.DataBind();
            }
            try
            {
                wsMedico = new MedicoWSClient();
                medicos = new BindingList<medico>(wsMedico.listarMedico());

                rptMedicos.DataSource = medicos;
                rptMedicos.DataBind();
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al cargar medicos: " + ex.Message;
                lblMensaje.Visible = true;
            }
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {

        }

        protected void rptMedicos_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {

        }
    }
}