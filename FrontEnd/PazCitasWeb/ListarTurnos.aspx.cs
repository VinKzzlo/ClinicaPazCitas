using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;


namespace PazCitasWA
{
    public partial class ListarTurnos : System.Web.UI.Page
    {
      
        private MedicoWSClient boMedico;
        private BindingList<medico> medicos;

        protected void Page_Load(object sender, EventArgs e)
        {
//            boMedico = new MedicoWSClient();
//            BindingList<medico> medicos = new BindingList<medico>(boMedico.listarMedico());
//            dgvMedicos.DataSource = medicos;
//            dgvMedicos.DataBind();

///*            boEmpleado = new EmpleadoBO();
//            empleados = boEmpleado.listarTodos();
//            dgvMedicos.DataSource = medicos;
//            dgvMedicos.DataBind();*/
        }

        protected void ListarDisponibilidad_Click(object sender, EventArgs e)
        {

        }

        protected void dgvMedicos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            //dgvMedicos.PageIndex = e.NewPageIndex;
            //dgvMedicos.DataBind();
        }
    }
}