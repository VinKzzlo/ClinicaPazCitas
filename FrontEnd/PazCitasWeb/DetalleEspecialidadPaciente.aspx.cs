using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class DetalleEspecialidadPaciente : System.Web.UI.Page
    {
        private EspecialidadWSClient wsEspecialidad;
        private BindingList<especialidad> especialidades;
        private especialidad espGlob;

        private MedicoWSClient wsMedico;
        private BindingList<medico> medicos;

        protected void Page_Load(object sender, EventArgs e)
        {
            string idEspecialidad = Request.QueryString["id"];
            if (!string.IsNullOrEmpty(idEspecialidad))
            {
                // Aquí llamas al servicio web para obtener los detalles de esa especialidad
                wsEspecialidad = new EspecialidadWSClient();
                especialidades = new BindingList<especialidad>(wsEspecialidad.listarEspecialidad());
                espGlob = especialidades.FirstOrDefault(c => c.idEspecialidad == Int32.Parse(idEspecialidad));
                // Mostrar detalles en controles de la página
                litPageTitle.Text = espGlob.nombre;
                litNombreEspecialidad.Text = espGlob.nombre;
                litNombreEspecialidadMedicos.Text = espGlob.nombre;
                litDescripcionEspecialidad.Text = Server.HtmlDecode(espGlob.descripcion); // Decodificar si guardas

                wsMedico=new MedicoWSClient();
                BindingList<medico> medicosTotal = new BindingList<medico>(wsMedico.listarMedico());
                medicos = new BindingList<medico>();
                foreach(medico med in medicosTotal)
                {
                    if(med.especialidad.idEspecialidad == Int32.Parse(idEspecialidad))
                    {
                        medicos.Add(med);
                    }
                }
                
                rptMedicos.DataSource = medicos;
                rptMedicos.DataBind();

                pnlDetalleEspecialidad.Visible = true;
                pnlError.Visible = false;
            }
            else
            {
                // Manejar caso sin id (mostrar mensaje o redirigir)
                Response.Redirect("ListarCitasPaciente.aspx");
            }
        }
    }
}