using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class RegistrarMedico : System.Web.UI.Page
    {
        private SedeWSClient wsSede;
        private EspecialidadWSClient wsEspecialidad;
        private MedicoWSClient wsMedico;
        private Estado estado;
        private medico medico;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                LoadDropDowns();
            }
            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                estado = Estado.Nuevo;
                medico = new medico();
                lblTitulo.Text = "Registrar Médico";
            }
            else if (accion == "modificar")
            {
                estado = Estado.Modificar;
                lblTitulo.Text = "Modificar Médico";
                medico = (medico)Session["medicoSeleccionado"];
                if (!IsPostBack)
                {
                    txtIDUsuario.Text = medico.idUsuario.ToString();
                    txtDNI.Text = medico.dni;
                    txtCMP.Text = medico.codigoMedico;
                    txtNombre.Text = medico.nombre;
                    txtPaterno.Text = medico.apellidoPaterno;
                    txtMaterno.Text = medico.apellidoMaterno;
                    if (medico.genero.Equals('M')) rbMasculino.Checked = true;
                    else rbFemenino.Checked = true;
                    dtpFechaNacimiento.Value = medico.fechaNacimiento.ToString("yyyy-MM-dd");
                    txtEmail.Text = medico.email;

                    // Preseleccionamos sede y especialidad:
                    ddlSede.SelectedValue = medico.sede.idSede.ToString();
                    CargarEspecialidadesPorSede(medico.sede.idSede);
                    ddlEspecialidad.SelectedValue = medico.especialidad.idEspecialidad.ToString();
                    ddlEspecialidad.Enabled = true;
                }
            }

        }

        private void LoadDropDowns()
        {
            wsEspecialidad = new EspecialidadWSClient();
            wsSede = new SedeWSClient();
            ddlSede.DataSource = wsSede.listarSede();
            ddlSede.DataTextField = "nombre";
            ddlSede.DataValueField = "idSede";
            ddlSede.DataBind();
            ddlSede.Items.Insert(0, new ListItem("-- Seleccione --", ""));

            // Poner placeholder en especialidades
            ddlEspecialidad.Items.Clear();
            ddlEspecialidad.Items.Add(new ListItem("-- Seleccione sede primero --", ""));
            ddlEspecialidad.Enabled = false;


        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarMedicos.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
           
            wsMedico = new MedicoWSClient();
            if (estado == Estado.Modificar)
            {
                // Asegúrate de que tomas el id que está en el TextBox oculto o similar:
                medico.idUsuario = Int32.Parse(txtIDUsuario.Text);
            }
            medico.dni = txtDNI.Text;
            medico.codigoMedico = txtCMP.Text;
            medico.nombre = txtNombre.Text;
            medico.apellidoPaterno = txtPaterno.Text;
            medico.apellidoMaterno = txtMaterno.Text;
            medico.fechaNacimiento = DateTime.Parse(dtpFechaNacimiento.Value);
            medico.fechaNacimientoSpecified = true;
            if (rbMasculino.Checked) medico.genero = 'M';
            else if (rbFemenino.Checked) medico.genero = 'F';
            sede objSede = new sede();
            objSede.idSede = Int32.Parse(ddlSede.SelectedValue);
            especialidad objEspecialidad = new especialidad();
            objEspecialidad.idEspecialidad = Int32.Parse(ddlEspecialidad.SelectedValue);
            medico.email = txtEmail.Text;
            medico.sede = objSede;
            medico.especialidad = objEspecialidad;

            try
            {
                if (estado == Estado.Nuevo)
                {
                    wsMedico.insertarMedico(medico);    
                }
                else if (estado == Estado.Modificar)
                {
                    wsMedico.modificarMedico(medico);
                }
            }
            catch (Exception ex)
            {
                throw;
            }

            Response.Redirect("ListarMedicos.aspx");
        }

        protected void ddlSede_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(ddlSede.SelectedValue))
            {
                int idSede = int.Parse(ddlSede.SelectedValue);
                CargarEspecialidadesPorSede(idSede);
                ddlEspecialidad.Enabled = true;
            }
            else
            {
                ddlEspecialidad.Items.Clear();
                ddlEspecialidad.Items.Add(new ListItem("-- Seleccione sede primero --", ""));
                ddlEspecialidad.Enabled = false;
            }
        }

        private void CargarEspecialidadesPorSede(int idSede)
        {
            wsEspecialidad = new EspecialidadWSClient();

            // Supongamos que tienes este método en tu WS:
            // listarXSede(int idSede)

            ddlEspecialidad.DataSource = wsEspecialidad.listarXSede(idSede);
            ddlEspecialidad.DataTextField = "nombre";
            ddlEspecialidad.DataValueField = "idEspecialidad";
            ddlEspecialidad.DataBind();

            ddlEspecialidad.Items.RemoveAt(0);
            ddlEspecialidad.Items.Insert(0, new ListItem("-- Seleccione --", ""));
        }

    }
}