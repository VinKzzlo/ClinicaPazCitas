﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

namespace PazCitasWA
{
    public partial class RegistrarConsultorio : System.Web.UI.Page
    {
        private SedeWSClient wsSede;
        private Estado estado;
        private ConsultorioWSClient wsConsultorio;
        private consultorio consultorio;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                wsConsultorio = new ConsultorioWSClient();
                wsSede = new SedeWSClient();
                ddlSede.DataSource = wsSede.listarSede();
                ddlSede.DataTextField = "nombre";
                ddlSede.DataValueField = "idSede";
                
                ddlSede.DataBind();

                ddlSede.Items.Insert(0, new ListItem("-- Seleccione Sede --", ""));
            }

            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                estado = Estado.Nuevo;
                consultorio = new consultorio();
                lblTitulo.Text = "Registrar Consultorio";
            }
            else if (accion == "modificar")
            {
                estado = Estado.Modificar;
                lblTitulo.Text = "Modificar Consultorio";
                consultorio = (consultorio)Session["consultSeleccionado"];
                if (!IsPostBack)
                {
                    txtIDConsultorio.Text = consultorio.idConsultorio.ToString();
                    txtNombre.Text = consultorio.nombreConsultorio;
                    txtPiso.Text = consultorio.piso.ToString();
                    txtCapacidad.Text = consultorio.capacidad.ToString();
                    ddlSede.SelectedValue = consultorio.sede.idSede.ToString();
                }
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            wsConsultorio = new ConsultorioWSClient();
            consultorio.nombreConsultorio = txtNombre.Text;
            consultorio.piso = Int32.Parse(txtPiso.Text);
            consultorio.capacidad = Int32.Parse(txtCapacidad.Text);
            sede sede = new sede();
            sede.idSede = Int32.Parse(ddlSede.SelectedValue);
            consultorio.sede = sede;
            try
            {
                if (estado == Estado.Nuevo)
                {
                    wsConsultorio.insertarConsultorio(consultorio);
                }
                else if (estado == Estado.Modificar)
                {
                    wsConsultorio.modificarConsultorio(consultorio);
                }
            }
            catch (Exception ex)
            { //lanzarMensajedeError(ex.Message); return;
            }

            Response.Redirect("ListarConsultorios.aspx");
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarConsultorios.aspx");
        }
    }
}