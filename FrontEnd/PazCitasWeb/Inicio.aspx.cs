using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PazCitasWA
{
    public partial class Inicio : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.QueryString["logout"] == "true")
            {
                Session.Clear();
                Session.Abandon();
            }
        }

        protected void BtnPacientes_Click(object sender, EventArgs e)
        {
            Response.Redirect("Login.aspx?rol=paciente");
        }

        protected void BtnPersonal_Click(object sender, EventArgs e)
        {
            Response.Redirect("Login.aspx?rol=medico");
        }

        protected void BtnAdmin_Click(object sender, EventArgs e)
        {
            Response.Redirect("Login.aspx?rol=admin");
        }
    }
}