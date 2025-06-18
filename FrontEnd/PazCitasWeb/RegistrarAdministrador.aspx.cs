using PazCitasWA.ServiciosWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PazCitasWA
{
    public partial class RegistrarAdministrador : System.Web.UI.Page
    {
        private AdministradorWSClient wsAdmin;
        private Estado estado;
        private administrador admin;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                
                DateTime maxDate = DateTime.Today.AddYears(-18);
                DateTime minDate = new DateTime(1940, 1, 1);

                dtpFechaNacimiento.Attributes["min"] = minDate.ToString("yyyy-MM-dd");
                dtpFechaNacimiento.Attributes["max"] = maxDate.ToString("yyyy-MM-dd");

                // Establecer el valor máximo de la fecha como la fecha actual
                rvFechaNacimiento.MaximumValue = DateTime.Today.ToString("yyyy-MM-dd");
            }

            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                estado = Estado.Nuevo;
                admin = new administrador();
                lblTitulo.Text = "Registrar Administrador";
            }
            else if (accion == "modificar")
            {
                estado = Estado.Modificar;
                lblTitulo.Text = "Modificar Administrador";
                admin = (administrador)Session["adminSeleccionado"];
                if (!IsPostBack)
                {
                    txtIDUsuario.Text = admin.idUsuario.ToString();
                    txtDNI.Text = admin.dni;
                    txtNombre.Text = admin.nombre;
                    txtPaterno.Text = admin.apellidoPaterno;
                    txtMaterno.Text = admin.apellidoMaterno;
                    if (admin.genero.Equals('M')) rbMasculino.Checked = true;
                    else rbFemenino.Checked = true;
                    dtpFechaNacimiento.Value = admin.fechaNacimiento.ToString("yyyy-MM-dd");
                    txtEmail.Text = admin.email;

                    txtUsername.Enabled = false;
                    txtPassword.Enabled = false;
                }
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarAdministradores.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            wsAdmin = new AdministradorWSClient();
            if (estado == Estado.Modificar)
            {
                // Asegúrate de que tomas el id que está en el TextBox oculto o similar:
                admin.idUsuario = Int32.Parse(txtIDUsuario.Text);
            }
            admin.dni = txtDNI.Text;
            admin.nombre = txtNombre.Text;
            admin.apellidoPaterno = txtPaterno.Text;
            admin.apellidoMaterno = txtMaterno.Text;
            admin.fechaNacimiento = DateTime.Parse(dtpFechaNacimiento.Value);
            admin.fechaNacimientoSpecified = true;
            if (rbMasculino.Checked) admin.genero = 'M';
            else if (rbFemenino.Checked) admin.genero = 'F';
            admin.email = txtEmail.Text;

            CuentaUsuarioWSClient wsCuenta = new CuentaUsuarioWSClient();
            cuentaUsuario cuenta = new cuentaUsuario();
            cuenta.username = txtUsername.Text;
            cuenta.password = txtPassword.Text;
            cuenta.rol = rol.ADMINISTRADOR;
            cuenta.rolSpecified = true;
            cuenta.usuario = admin;

            try
            {
                if (estado == Estado.Nuevo)
                {
                    int idInsertado = wsAdmin.insertarAdministrador(admin);
                    cuenta.usuario.idUsuario = idInsertado;
                    wsCuenta.insertarCuenta(cuenta);
                }
                else if (estado == Estado.Modificar)
                {
                    wsAdmin.modificarAdministrador(admin);
                }
            }
            catch (Exception ex)
            {
                throw;
            }

            Response.Redirect("ListarAdministradores.aspx");
        }
    }
}