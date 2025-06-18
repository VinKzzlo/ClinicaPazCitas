using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.SqlClient;
using System.Data.SqlTypes;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using PazCitasWA.ServiciosWS;

// using para modal
using System.Globalization;
using System.Diagnostics.PerformanceData;


namespace PazCitasWA
{
    public partial class DetalleCitaPaciente : System.Web.UI.Page
    {
        // Propiedad que usa ViewState para guardar/leer la cita entre postbacks.
        private cita CitaGuardada
        {
            get { return ViewState["CitaGuardada"] as cita; }
            set { ViewState["CitaGuardada"] = value; }
        }

        private CitaWSClient wsCita;
        private MedicoWSClient wsMedico;
        private TurnoWSClient wsTurno;

        private cita c = null;
        protected void Page_Load(object sender, EventArgs e)
        {
            // Esta parte se puede ejecutar siempre, ya que solo lee el QueryString
            
            string idCita = Request.QueryString["id"];
            if (string.IsNullOrEmpty(idCita))
            {
                Response.Redirect("ListarCitasPaciente.aspx");
                return; // Detiene la ejecución si no hay ID
            }

            if (!IsPostBack)
            {
                // Aquí llamas al servicio web para obtener los detalles de esa especialidad
                wsCita = new CitaWSClient();
                wsMedico = new MedicoWSClient();
                wsTurno = new TurnoWSClient();

                c = wsCita.obtenerCita(Int32.Parse(idCita));
                if (c == null)
                {
                    Response.Redirect("ListarCitasPaciente.aspx"); // O a una página de error
                    return;
                }

                // 2. GUARDA la cita en ViewState. Este es el paso clave.
                this.CitaGuardada = c;

                int idMedico = c.horarioTrabajo.medico.idUsuario;
                medico med = wsMedico.obtenerMedico(idMedico);
                turno tur = wsTurno.obtenerXId(c.horarioTrabajo.turno.idTurno);

                lblEspecialidadNombre.Text = med.especialidad.nombre.ToString();
                lblSedeNombre.Text = med.sede.nombre.ToString();
                lblEstadoPagoNombre.Text = c.estadoCita.ToString();
                lblConsultorioNombre.Text = med.consultorio.nombreConsultorio.ToString();

                lblMedicoCodigo.Text = "CMP: " + med.codigoMedico.ToString();
                lblMedicoEmail.Text = med.email.ToString();
                lblMedicoNombreCompleto.Text = "Dr. " + med.nombre.ToString() + med.apellidoPaterno.ToString();

                //DateTime fechaDeLaCita = c.fecha;//new DateTime(2025, 6, 24, 16, 0, 0)
                //BuildStaticCalendar(fechaDeLaCita);
                string estadoActualCita = c.estadoAtencion.ToString();
                SetAppointmentStatus(estadoActualCita);

                decimal costoTotalCita = 250m;//Obtener el costo real
                decimal coberturaSeguro = 180m;
                string estadoDelPago = "pendiente";

                //// 2. Llama al método para configurar la interfaz
                SetPaymentOverview(costoTotalCita, coberturaSeguro, estadoDelPago);

                lblConsultationReason.Text = c.motivoConsulta.ToString();

                SetupActionButtons(c);

                InitializeScheduler();

            }
            cita cguar = this.CitaGuardada;
            if (cguar != null)
            {
                BuildStaticCalendar(cguar);
            }

        }

        private void BuildStaticCalendar(cita citaDate)
        {
            wsTurno = new TurnoWSClient();
            turno tur = wsTurno.obtenerXId(citaDate.horarioTrabajo.turno.idTurno);
            DateTime today = DateTime.Today; // Obtenemos la fecha actual para compararla

            lblCalendarMonth.Text = citaDate.fecha.ToString("MMMM yyyy");
            lblAppointmentTime.Text = tur.horaInicio.ToString("HH:mm") + " - " + tur.horaFin.ToString("HH:mm");

            var firstDayOfMonth = new DateTime(citaDate.fecha.Year, citaDate.fecha.Month, 1);
            int startDayOfWeek = (int)firstDayOfMonth.DayOfWeek;
            // Ajuste para que la semana empiece en Lunes (1) en lugar de Domingo (0)
            if (startDayOfWeek == 0) startDayOfWeek = 7;

            var daysInMonth = DateTime.DaysInMonth(citaDate.fecha.Year, citaDate.fecha.Month);
            var prevMonthLastDay = firstDayOfMonth.AddDays(-1);

            var sb = new StringBuilder();

            // 1. Añadir días del mes anterior para rellenar
            for (int i = 1; i < startDayOfWeek; i++)
            {
                int day = prevMonthLastDay.Day - (startDayOfWeek - i - 1);
                sb.Append($"<div class='calendar-day-cell other-month'>{day}</div>");
            }

            // 2. Añadir días del mes actual
            for (int i = 1; i <= daysInMonth; i++)
            {
                var currentDate = new DateTime(citaDate.fecha.Year, citaDate.fecha.Month, i);
                string cssClasses = "calendar-day-cell";

                // ¿Es el día de la cita? Esta es la prioridad más alta.
                if (currentDate.Date == citaDate.fecha.Date)
                {
                    cssClasses += " selected-day";
                }
                // Si NO es el día de la cita, ¿es el día de hoy?
                else if (currentDate.Date == today)
                {
                    cssClasses += " today-day";
                }

                sb.Append($"<div class='{cssClasses}'>{i}</div>");
            }

            phCalendarDays.Controls.Add(new Literal { Text = sb.ToString() });
        }
        private void SetAppointmentStatus(string estado)
        {
            // Reseteamos las clases y visibilidad
            stepEspera.Attributes["class"] = "timeline-step";
            stepConsultorio.Attributes["class"] = "timeline-step";
            stepAtendido.Attributes["class"] = "timeline-step";

            phTagEspera.Visible = false;
            phTagConsultorio.Visible = false;
            phTagAtendido.Visible = false;

            // Reseteamos las descripciones
            lblDescEspera.Text = "Te atenderemos pronto";
            lblDescConsultorio.Text = "Te están atendiendo";
            lblDescAtendido.Text = "Atención Completada";

            switch (estado.ToLower().Trim())
            {
                case "en_consultorio":
                    // Paso 1 completado
                    stepEspera.Attributes["class"] += " completed";
                    phTagEspera.Visible = true;
                    phTagEspera.Controls.Add(new Literal { Text = "<div class='step-status-tag tag-completed'>Completado</div>" });

                    // Paso 2 activo
                    stepConsultorio.Attributes["class"] += " active";
                    phTagConsultorio.Visible = true;
                    phTagConsultorio.Controls.Add(new Literal { Text = "<div class='step-status-tag tag-in-progress'>En Progreso</div>" });
                    break;

                case "atendido":
                    // Pasos 1 y 2 completados
                    stepEspera.Attributes["class"] += " completed";
                    phTagEspera.Visible = true;
                    phTagEspera.Controls.Add(new Literal { Text = "<div class='step-status-tag tag-completed'>Completado</div>" });

                    stepConsultorio.Attributes["class"] += " completed";
                    phTagConsultorio.Visible = true;
                    phTagConsultorio.Controls.Add(new Literal { Text = "<div class='step-status-tag tag-completed'>Completado</div>" });

                    // Paso 3 completado (final)
                    stepAtendido.Attributes["class"] += " completed";
                    phTagAtendido.Visible = true;
                    phTagAtendido.Controls.Add(new Literal { Text = "<div class='step-status-tag tag-completed'>Finalizado</div>" });
                    break;

                case "en_espera":
                default:
                    // Paso 1 activo
                    stepEspera.Attributes["class"] += " active";
                    phTagEspera.Visible = true;
                    phTagEspera.Controls.Add(new Literal { Text = "<div class='step-status-tag tag-in-progress'>En Progreso</div>" });
                    break;
            }
        }
        private void SetPaymentOverview(decimal montoTotal, decimal montoSeguro, string estadoPago)
        {
            decimal montoPaciente = montoTotal - montoSeguro;

            // 1. Asignar los montos a los labels
            // "C0" formatea como moneda sin decimales. Usa "C" o "C2" si los quieres.
            lblAmountTotal.Text = montoTotal.ToString("C0");
            lblAmountInsurance.Text = montoSeguro.ToString("C0");
            lblAmountPatient.Text = montoPaciente.ToString("C0");

            // 2. Calcular y asignar el alto de las barras de progreso
            // La barra del total siempre está al 100% (o un poco menos para efecto visual)
            barFillTotal.Style["height"] = "95%";

            // Las otras barras son un porcentaje del total
            if (montoTotal > 0)
            {
                double insurancePercent = (double)(montoSeguro / montoTotal) * 100;
                double patientPercent = (double)(montoPaciente / montoTotal) * 100;

                barFillInsurance.Style["height"] = insurancePercent.ToString("F0") + "%";
                barFillPatient.Style["height"] = patientPercent.ToString("F0") + "%";
            }
            else // Evitar división por cero
            {
                barFillInsurance.Style["height"] = "0%";
                barFillPatient.Style["height"] = "0%";
            }

            // 3. Configurar la píldora de estado de pago
            string tagHtml = "";
            switch (estadoPago.ToLower().Trim())
            {
                case "cancelada":
                    tagHtml = "<div class='payment-status-tag tag-payment-paid'>Pagado</div>";
                    break;
                case "vencido":
                    tagHtml = "<div class='payment-status-tag tag-payment-partial'>Pago Parcial</div>";
                    break;
                case "pendiente":
                default:
                    tagHtml = "<div class='payment-status-tag tag-payment-pending'>Pendiente</div>";
                    break;
            }
            phPaymentStatusTag.Controls.Add(new Literal { Text = tagHtml });
        }
        private void SetupActionButtons(cita c)
        {
            // === EJEMPLO DE CONDICIONALES ===
            bool canModify = true; // CAMBIA A 'true' PARA PROBAR
            string msjCancelarCita = "";
            bool canCancel = true;  // CAMBIA A 'false' PARA PROBAR
            string msjModificarCita = "";

            DateTime fechaActual = DateTime.Now.Date;
            TimeSpan diferencia = c.fecha - fechaActual;
            int dias = diferencia.Days;
            if (c.estadoCita == estadoCita.CANCELADA)
            {
                msjCancelarCita = "Tu cita ya se encuetra cancelada";
                canCancel = false;
                msjModificarCita = "Tu cita ya se encuetra cancelada";
                canModify = false;
            }
            else if (dias < 0)
            {
                msjCancelarCita = "Ya paso el día de tu cita";
                canCancel = false;
                msjModificarCita = "Ya paso el día de tu cita";
                canModify = false;
            }
            else if (dias < 4)
            {
                msjModificarCita = "Queda menos de 4 días para tu cita, no puedes modificarla";
                canModify = false;
                

            }
            else if (dias < 2)
            {
                msjCancelarCita = "Queda menos de 2 días para tu cita, no puedes cancelarla";
                canCancel = false;
            }

            canModify = true;

            // Lógica para el botón MODIFICAR
            if (canModify)
            {
                btnModifyAppointment.Enabled = true;
                divModifyWarning.Visible = false; // Ocultamos el div contenedor
            }
            else
            {
                btnModifyAppointment.Enabled = false;
                lblModifyWarning.Text = msjModificarCita;
                divModifyWarning.Visible = true; // Mostramos el div contenedor
            }

            // Lógica para el botón CANCELAR
            if (canCancel)
            {
                btnCancelAppointment.Enabled = true;
                divCancelWarning.Visible = false; // Ocultamos el div contenedor
            }
            else
            {
                btnCancelAppointment.Enabled = false;
                lblCancelWarning.Text = msjCancelarCita;
                divCancelWarning.Visible = true; // Mostramos el div contenedor
            }
        }

        //protected void btnModifyAppointment_Click(object sender, EventArgs e)
        //{
        //    // Aquí va la lógica para mostrar tu modal de modificación.
        //    // Por ahora, usamos un simple alert de JavaScript.
        //    string script = "alert('Modal para MODIFICAR cita aparecerá aquí.');";
        //    ScriptManager.RegisterStartupScript(this, GetType(), "ShowModifyModal", script, true);
        //}

        //protected void btnCancelAppointment_Click(object sender, EventArgs e)
        //{
        //    // Aquí va la lógica para mostrar tu modal de cancelación.
        //    // Por ahora, usamos un simple alert de JavaScript.
        //    string script = "alert('Modal para CANCELAR cita aparecerá aquí.');";
        //    ScriptManager.RegisterStartupScript(this, GetType(), "ShowCancelModal", script, true);
        //}

        protected void btnConfirmCancel_Click(object sender, EventArgs e)
        {
            // 1. Aquí irá tu lógica para llamar al Web Service y cancelar la cita.
            string idCita = Request.QueryString["id"];
            wsCita = new CitaWSClient();
            cita ct = wsCita.obtenerCita(Int32.Parse(idCita));
            ct.estadoCita = estadoCita.CANCELADA;
            ct.estadoCitaSpecified = true;
            wsCita.modificarCita(ct);
            
            // 2. Muestra un mensaje de éxito y redirige.
            string script = "alert('Tu cita ha sido cancelada exitosamente.'); window.location='ListarCitasPaciente.aspx';";
            //InitializeScheduler();
            ScriptManager.RegisterStartupScript(this, GetType(), "CancelSuccess", script, true);
        }















        // --- REEMPLAZA TODA LA SECCIÓN DE MÉTODOS DEL SCHEDULER CON ESTO ---

        // Este método se llama cuando el usuario hace clic en "Modificar Cita".
        // Su única función es reiniciar todo y abrir el modal.
        protected void btnModifyAppointment_Click(object sender, EventArgs e)
        {
            InitializeScheduler();
            ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModifyModalScript", "openModal('modalModify');", true);
        }

        // Inicializa el scheduler a su estado por defecto.
        private void InitializeScheduler()
        {
            // Resetea los campos ocultos
            hdnSelectedDate.Value = string.Empty;
            hdnSelectedTime.Value = string.Empty;
            hdnDateOffset.Value = "0";

            // Genera y enlaza la lista de fechas
            BindDates();

            // Oculta las secciones de horarios
            pnlTimeSlots.Visible = false;
            pnlNoTimes.Visible = false;

            // Deshabilita el botón de guardado
            UpdateButtonState();
        }

        // NUEVO MÉTODO: Centraliza la creación de la lista de fechas.
        private void BindDates()
        {
            // Lee el offset actual desde el campo oculto
            int offsetWeeks = 0;
            int.TryParse(hdnDateOffset.Value, out offsetWeeks);

            // Calcula el día de inicio. Cada "página" son 14 días (2 semanas).
            int startDayOffset = offsetWeeks * 14;

            var dates = new List<DateTime>();
            for (int i = 1; i <= 14; i++)
            {
                // Añade los días a partir del offset calculado
                dates.Add(DateTime.Today.AddDays(startDayOffset + i));
            }
            rptDates.DataSource = dates;
            rptDates.DataBind();
        }

        // Este es el evento central que se ejecuta en cada clic de fecha u hora.
        protected void btnRefreshScheduler_Click(object sender, EventArgs e)
        {
            // 1. SIEMPRE volvemos a enlazar las fechas. Esto evita que desaparezcan.
            BindDates();

            // 2. Leemos la fecha seleccionada del campo oculto.
            DateTime? selectedDate = null;
            if (DateTime.TryParse(hdnSelectedDate.Value, out DateTime parsedDate))
            {
                selectedDate = parsedDate;
            }

            // 3. Si hay una fecha seleccionada, mostramos sus horarios.
            if (selectedDate.HasValue)
            {
                BindTimeSlotsForDate(selectedDate.Value);
            }
            else
            {
                // Si no hay fecha (o se reseteó), nos aseguramos de que los paneles de hora estén ocultos.
                pnlTimeSlots.Visible = false;
                pnlNoTimes.Visible = false;
            }

            // 4. Actualizamos el estado del botón de guardado.
            UpdateButtonState();
        }

        // La lógica de BindTimeSlotsForDate se mantiene igual.
        private void BindTimeSlotsForDate(DateTime date)
        {
            var availableTimes = new BindingList<object>();
            if (date.Day % 2 == 0) // Simulación
            {
                availableTimes.Add(new { Time = "09:00 AM" });
                availableTimes.Add(new { Time = "09:30 AM" });
                availableTimes.Add(new { Time = "11:00 AM" });
            }

            if (availableTimes.Count > 0)
            {
                rptTimes.DataSource = availableTimes;
                rptTimes.DataBind();
                pnlTimeSlots.Visible = true;
                pnlNoTimes.Visible = false;
            }
            else
            {
                pnlTimeSlots.Visible = false;
                pnlNoTimes.Visible = true;
            }
        }

        // La lógica de UpdateButtonState se mantiene igual.
        private void UpdateButtonState()
        {
            bool isSelectionComplete = !string.IsNullOrEmpty(hdnSelectedDate.Value) && !string.IsNullOrEmpty(hdnSelectedTime.Value);
            btnConfirmModify.Enabled = isSelectionComplete;
            btnConfirmModify.CssClass = isSelectionComplete ? "btn btn-primary" : "btn btn-primary aspNetDisabled";
        }

        // La lógica de los métodos GetCssClass se mantiene igual.
        protected string GetDateItemCssClass(object dataItem)
        {
            DateTime date = (DateTime)dataItem;
            if (DateTime.TryParse(hdnSelectedDate.Value, out DateTime selectedDate))
            {
                return date.Date == selectedDate.Date ? "date-item selected" : "date-item";
            }
            return "date-item";
        }
        protected string GetTimeSlotCssClass(object timeValue)
        {
            if (timeValue == null) return "time-slot-button";

            // 'timeValue' ya es el string de la hora, por ejemplo "09:00 AM".
            string time = timeValue.ToString();

            // Comparamos directamente con el valor del campo oculto.
            return !string.IsNullOrEmpty(hdnSelectedTime.Value) && time == hdnSelectedTime.Value
                   ? "time-slot-button selected"
                   : "time-slot-button";
        }

        // El método de guardado se mantiene igual.
        protected void btnConfirmModify_Click(object sender, EventArgs e)
        {
            string selectedDateValue = hdnSelectedDate.Value;
            string selectedTimeValue = hdnSelectedTime.Value;

            if (string.IsNullOrEmpty(selectedDateValue) || string.IsNullOrEmpty(selectedTimeValue))
            {
                return;
            }

            DateTime nuevaFecha = DateTime.Parse(selectedDateValue);
            DateTime nuevaFechaCompleta = DateTime.Parse(
                $"{nuevaFecha.ToShortDateString()} {selectedTimeValue}",
                CultureInfo.InvariantCulture
            );

            // Tu lógica de guardado aquí...
            // wsCita.modificarCita(idCita, nuevaFechaCompleta);

            string script = "closeModal('modalModify'); alert('Cita modificada con éxito para el " + nuevaFechaCompleta.ToString("dd 'de' MMMM 'a las' hh:mm tt") + "'); window.location.reload();";
            ScriptManager.RegisterStartupScript(this, this.GetType(), "ModifySuccessScript", script, true);
        }

    }
}