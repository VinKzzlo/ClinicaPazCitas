using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.SessionState;
using System.Web.UI;

namespace PazCitasWA
{
    public class Global : System.Web.HttpApplication
    {

        protected void Application_Start(object sender, EventArgs e)
        {
            // Registrar el mapeo de jQuery
            ScriptResourceDefinition jqueryDef = new ScriptResourceDefinition
            {
                Path = "~/Scripts/jquery-3.7.1.min.js", // ruta al archivo minificado
                DebugPath = "~/Scripts/jquery-3.7.1.js", // ruta al archivo de desarrollo
                CdnPath = "https://code.jquery.com/jquery-3.7.1.min.js", // opcional: CDN
                CdnDebugPath = "https://code.jquery.com/jquery-3.7.1.js", // opcional: CDN debug
                LoadSuccessExpression = "window.jQuery"
            };

            ScriptManager.ScriptResourceMapping.AddDefinition("jquery", null, jqueryDef);
        }

        protected void Session_Start(object sender, EventArgs e)
        {

        }

        protected void Application_BeginRequest(object sender, EventArgs e)
        {

        }

        protected void Application_AuthenticateRequest(object sender, EventArgs e)
        {

        }

        protected void Application_Error(object sender, EventArgs e)
        {

        }

        protected void Session_End(object sender, EventArgs e)
        {

        }

        protected void Application_End(object sender, EventArgs e)
        {

        }
    }
}