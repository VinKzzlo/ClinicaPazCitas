using System;

namespace PazCitasWA
{
    public partial class Inicio : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

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