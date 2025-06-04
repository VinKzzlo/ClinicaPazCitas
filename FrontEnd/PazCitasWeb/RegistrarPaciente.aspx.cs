using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class RegistrarPaciente : System.Web.UI.Page
    {
        private PacienteWSClient wsPaciente;
        private SeguroWSClient wsSeguro;
        private Estado estado;
        private paciente paciente;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                wsSeguro = new SeguroWSClient();
                ddlSeguro.DataSource = wsSeguro.listarSeguro();
                ddlSeguro.DataTextField = "nombreSeguro";
                ddlSeguro.DataValueField = "idSeguro";
                ddlSeguro.DataBind();
                ddlSeguro.Items.Insert(0, new ListItem("-- Seleccione --", ""));
            }

            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                estado = Estado.Nuevo;
                paciente = new paciente();
                lblTitulo.Text = "Registrar Paciente";
            }
            else if (accion == "modificar")
            {
                estado = Estado.Modificar;
                lblTitulo.Text = "Modificar Paciente";
                paciente = (paciente)Session["pacienteSeleccionado"];
                if (!IsPostBack)
                {
                    txtIDUsuario.Text = paciente.idUsuario.ToString();
                    txtDNI.Text = paciente.dni;
                    txtNombre.Text = paciente.nombre;
                    txtPaterno.Text = paciente.apellidoPaterno;
                    txtMaterno.Text = paciente.apellidoMaterno;
                    if (paciente.genero.Equals('M')) rbMasculino.Checked = true;
                    else rbFemenino.Checked = true;
                    dtpFechaNacimiento.Value = paciente.fechaNacimiento.ToString("yyyy-MM-dd");
                    txtEmail.Text = paciente.email;
                    ddlSeguro.SelectedValue = paciente.seguro.idSeguro.ToString();
                    txtDireccion.Text = paciente.direccion;
                    txtTelefono.Text = paciente.telefono.ToString();
                }
            }

        }


        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarPacientes.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            wsPaciente = new PacienteWSClient();
            if (estado == Estado.Modificar)
            {
                // Asegúrate de que tomas el id que está en el TextBox oculto o similar:
                paciente.idUsuario = Int32.Parse(txtIDUsuario.Text);
            }
            paciente.dni = txtDNI.Text;
            paciente.nombre = txtNombre.Text;
            paciente.apellidoPaterno = txtPaterno.Text;
            paciente.apellidoMaterno = txtMaterno.Text;
            paciente.fechaNacimiento = DateTime.Parse(dtpFechaNacimiento.Value);
            paciente.fechaNacimientoSpecified = true;
            if (rbMasculino.Checked) paciente.genero = 'M';
            else if (rbFemenino.Checked) paciente.genero = 'F';
            seguro objSeguro = new seguro();
            objSeguro.idSeguro = Int32.Parse(ddlSeguro.SelectedValue);
            paciente.email = txtEmail.Text;
            paciente.seguro = objSeguro;
            paciente.direccion = txtDireccion.Text;
            paciente.telefono = txtTelefono.Text;

            try
            {
                if (estado == Estado.Nuevo)
                {
                    wsPaciente.insertarPaciente(paciente);
                }
                else if (estado == Estado.Modificar)
                {
                    wsPaciente.modificarPaciente(paciente);
                }
            }
            catch (Exception ex)
            {
                throw;
            }

            Response.Redirect("ListarPacientes.aspx");
        }
    }
}