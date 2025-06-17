using PazCitasWA.ServiciosWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PazCitasWA
{
    public partial class RegistrarTurnosMedico : System.Web.UI.Page
    {
        private MedicoWSClient wsMedico = new MedicoWSClient();
        private TurnoWSClient wsTurno = new TurnoWSClient();
        private HorarioTrabajoWSClient wsHorario = new HorarioTrabajoWSClient();

        // Lista simple para mantener turnos asignados
        private List<int> turnosAsignados = new List<int>();

        protected int IdMedico =>
            int.TryParse(Request.QueryString["idMedico"], out var id) ? id : 0;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (IdMedico == 0) Response.Redirect("AsignarTurnos.aspx");
                CargarTurnosAsignados();
                CargarDatosMedico();
                CargarTurnosFijos();
            }
            else
            {
                // Recuperar del ViewState en postback
                if (ViewState["TurnosAsignados"] != null)
                    turnosAsignados = (List<int>)ViewState["TurnosAsignados"];
            }
        }

        private void CargarTurnosAsignados()
        {
            try
            {
                // Intentar cargar turnos ya asignados
                var horariosAsignados = wsHorario.listarPorMedico(IdMedico);
                turnosAsignados = horariosAsignados?.Select(h => h.turno.idTurno).ToList() ?? new List<int>();
            }
            catch
            {
                // Si hay error o no existe el método, lista vacía
                turnosAsignados = new List<int>();
            }

            // Guardar en ViewState
            ViewState["TurnosAsignados"] = turnosAsignados;
        }

        private void CargarDatosMedico()
        {
            var medico = wsMedico.obtenerMedico(IdMedico);
            lblNombreMedico.Text = $"Dr. {medico.nombre} {medico.apellidoPaterno} {medico.apellidoMaterno}";
            lblDNIMedico.Text = medico.dni;
            lblCMPMedico.Text = medico.codigoMedico;
        }

        private void CargarTurnosFijos()
        {
            var turnos = wsTurno.listarTurno();
            gvTurnos.DataSource = turnos;
            gvTurnos.DataBind();
        }

        // Método público para usar en el ASPX
        public bool EsTurnoAsignado(int idTurno)
        {
            if (ViewState["TurnosAsignados"] != null)
                turnosAsignados = (List<int>)ViewState["TurnosAsignados"];

            return turnosAsignados.Contains(idTurno);
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                var asignaciones = new List<int>();

                foreach (GridViewRow row in gvTurnos.Rows)
                {
                    var chk = (CheckBox)row.FindControl("chkAsignar");
                    if (chk?.Checked == true)
                    {
                        int idTurno = (int)gvTurnos.DataKeys[row.RowIndex].Value;
                        asignaciones.Add(idTurno);
                    }
                }

                // Eliminar asignaciones previas
                wsHorario.eliminarHorarioPorMedico(IdMedico);
                
                // Insertar nuevas asignaciones
                horarioTrabajo horarioIn= new horarioTrabajo();
                medico medico = new medico();
                medico = wsMedico.obtenerMedico(IdMedico);
                horarioIn.medico = medico;
                foreach (int idTurno in asignaciones)
                {
                    turno turnoIn = new turno();
                    turnoIn.idTurno = idTurno;
                    horarioIn.turno = turnoIn;
                    wsHorario.insertaHorario(horarioIn);
                }

                // Mensaje de éxito y redirección
                Response.Write("<script>alert('✅ Turnos guardados correctamente'); window.location='AsignarTurnos.aspx';</script>");
            }
            catch (Exception ex)
            {
                Response.Write($"<script>alert('❌ Error al guardar: {ex.Message}');</script>");
            }
        }
    }
}