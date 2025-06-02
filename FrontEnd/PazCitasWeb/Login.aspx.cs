using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PazCitasWA
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
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

        //Aqui falta el boton para ingresar














    }
}