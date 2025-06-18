using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class Login : System.Web.UI.Page
    {
        private CuentaUsuarioWSClient bocuenta;
        private PacienteWSClient bopaciente;
        private MedicoWSClient bomedico;
        private AdministradorWSClient boadmin;
        private CitaWSClient bocita;
        protected void Page_Load(object sender, EventArgs e)
        {
            bocuenta = new CuentaUsuarioWSClient();
            if (!IsPostBack)
            {
                string rol = Request.QueryString["rol"];

                switch (rol)
                {
                    case "paciente":
                        tituloRol.InnerText = "Ingreso para Pacientes";
                        iconoRol.InnerHtml = "<i class='fas fa-user-injured text-white'></i>";
                        loginHeader.Style["background-color"] = "#fbbc04"; // amarillo
                        btnLogin.CssClass = "btn btn-warning btn-login";
                        iconUser.Attributes["class"] = "fas fa-user text-warning";
                        iconLock.Attributes["class"] = "fas fa-lock text-warning";

                        
                        break;

                    case "medico":
                        tituloRol.InnerText = "Ingreso para Personal Médico";
                        iconoRol.InnerHtml = "<i class='fas fa-user-md text-white'></i>";
                        loginHeader.Style["background-color"] = "#4285f4"; // azul
                        btnLogin.CssClass = "btn btn-primary btn-login";
                        iconUser.Attributes["class"] = "fas fa-user text-primary";
                        iconLock.Attributes["class"] = "fas fa-lock text-primary";
                        break;

                    case "admin":
                        tituloRol.InnerText = "Ingreso para Administración";
                        iconoRol.InnerHtml = "<i class='fas fa-cogs text-white'></i>";
                        loginHeader.Style["background-color"] = "#34a853"; // verde
                        btnLogin.CssClass = "btn btn-success btn-login";
                        iconUser.Attributes["class"] = "fas fa-user text-success";
                        iconLock.Attributes["class"] = "fas fa-lock text-success";
                        break;

                    default:
                        tituloRol.InnerText = "Acceso";
                        iconoRol.InnerHtml = "<i class='fas fa-user text-white'></i>";
                        loginHeader.Style["background-color"] = "#6c757d"; // gris
                        btnLogin.CssClass = "btn btn-secondary btn-login";
                        iconUser.Attributes["class"] = "fas fa-user text-secondary";
                        iconLock.Attributes["class"] = "fas fa-lock text-secondary";
                        break;
                }
            }
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            bocita = new CitaWSClient();
            int resultado = 0;
            lblMensaje.Text = "";
            cuentaUsuario cuentaUsuario = new cuentaUsuario();
            cuentaUsuario.username = txtUsuario.Text;
            cuentaUsuario.password = txtClave.Text;
            string rol = Request.QueryString["rol"];
            switch (rol)
            {
                case "paciente":
                    resultado = bocuenta.verificarCuentaPaciente(txtUsuario.Text, txtClave.Text);
                    
                   
                    
                    if(resultado != 0)
                    {
                        bopaciente = new PacienteWSClient();
                        paciente pac = bopaciente.obtenerPacienteXiD(resultado);
                        Session["paciente"] = pac;
                        Session["IdPaciente"] = pac.idUsuario;
                        FormsAuthenticationTicket tkt;
                        string cookiestr;
                        HttpCookie ck;

                        tkt = new FormsAuthenticationTicket(1,
                        cuentaUsuario.username, DateTime.Now,
                        DateTime.Now.AddMinutes(30), true, "datos adicionales");

                        cookiestr = FormsAuthentication.Encrypt(tkt);
                        ck = new HttpCookie(FormsAuthentication.FormsCookieName,
                            cookiestr);
                        ck.Expires = tkt.Expiration;
                        ck.Path = FormsAuthentication.FormsCookiePath;
                        Response.Cookies.Add(ck);

                        string strRedirect;
                        strRedirect = Request["ReturnUrl"];
                        if (strRedirect == null)
                            Response.Redirect("HomePaciente.aspx");
                        Response.Redirect(strRedirect, true);
                        

                    }
                    else 
                        lblMensaje.Text = "No ha ingresado correctamente su usuario/password";
                    
                    break;

                case "medico":
                    resultado = bocuenta.verificarCuentaMedico(txtUsuario.Text, txtClave.Text);

                    if (resultado != 0)
                    {
                        bomedico = new MedicoWSClient();
                        medico med = bomedico.obtenerMedico(resultado);
                        Session["medico"] = med;
                        FormsAuthenticationTicket tkt;
                        string cookiestr;
                        HttpCookie ck;

                        tkt = new FormsAuthenticationTicket(1,
                        cuentaUsuario.username, DateTime.Now,
                        DateTime.Now.AddMinutes(30), true, "datos adicionales");

                        cookiestr = FormsAuthentication.Encrypt(tkt);
                        ck = new HttpCookie(FormsAuthentication.FormsCookieName,
                            cookiestr);
                        ck.Expires = tkt.Expiration;
                        ck.Path = FormsAuthentication.FormsCookiePath;
                        Response.Cookies.Add(ck);

                        string strRedirect;
                        strRedirect = Request["ReturnUrl"];
                        if (strRedirect == null)
                            Response.Redirect("HomeMedico.aspx");
                        Response.Redirect(strRedirect, true);


                    }
                    else
                        lblMensaje.Text = "No ha ingresado correctamente su usuario/password";
                    break;

                case "admin":
                    resultado = bocuenta.verificarCuentaAdministrador(txtUsuario.Text, txtClave.Text);

                    if (resultado != 0)
                    {
                        boadmin = new AdministradorWSClient();
                        administrador adm = boadmin.obtenerPorIDAdministrador(resultado);
                        Session["administrador"] = adm;
                        FormsAuthenticationTicket tkt;
                        string cookiestr;
                        HttpCookie ck;

                        tkt = new FormsAuthenticationTicket(1,
                        cuentaUsuario.username, DateTime.Now,
                        DateTime.Now.AddMinutes(30), true, "datos adicionales");

                        cookiestr = FormsAuthentication.Encrypt(tkt);
                        ck = new HttpCookie(FormsAuthentication.FormsCookieName,
                            cookiestr);
                        ck.Expires = tkt.Expiration;
                        ck.Path = FormsAuthentication.FormsCookiePath;
                        Response.Cookies.Add(ck);

                        string strRedirect;
                        strRedirect = Request["ReturnUrl"];
                        if (strRedirect == null)
                            Response.Redirect("HomeAdmin.aspx");
                        Response.Redirect(strRedirect, true);


                    }
                    else
                        lblMensaje.Text = "No ha ingresado correctamente su usuario/password";
                    break;

                default:
                    Response.Redirect("Home.aspx");
                    break;
            }
        }

        //Aqui falta el boton para ingresar














    }
}