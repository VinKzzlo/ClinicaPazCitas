<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="RegistrarTurnosMedico.aspx.cs" Inherits="PazCitasWA.RegistrarTurnosMedico" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Registrar Turnos a Médico
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        /* Estilos simples con paleta verde */
        .doctor-card {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
            border: none !important;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(72, 187, 120, 0.3);
            color: white;
        }

            .doctor-card .card-header {
                background: rgba(255, 255, 255, 0.1) !important;
                border: none !important;
                color: white !important;
                border-radius: 12px 12px 0 0 !important;
            }

            .doctor-card .card-body {
                background: rgba(255, 255, 255, 0.95);
                color: #1f2937;
                border-radius: 0 0 12px 12px;
            }

        .turnos-card {
            border: none !important;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
        }

            .turnos-card .card-header {
                background: linear-gradient(135deg, #4fd1c7 0%, #14b8a6 100%) !important;
                border: none !important;
                color: white !important;
                border-radius: 12px 12px 0 0 !important;
            }

        /* Mejorar la tabla */
        .table-custom {
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

            .table-custom thead th {
                background: linear-gradient(135deg, #f0fdf4 0%, #dcfce7 100%);
                color: #065f46;
                font-weight: 600;
                border: none;
                padding: 15px;
            }

            .table-custom tbody tr {
                transition: all 0.3s ease;
            }

                .table-custom tbody tr:hover {
                    background-color: #f0fdf4;
                    transform: scale(1.01);
                }

            .table-custom tbody td {
                padding: 12px 15px;
                border-color: #e5e7eb;
                vertical-align: middle;
            }

        /* Checkbox personalizado */
        .custom-checkbox {
            width: 20px;
            height: 20px;
            accent-color: #48bb78;
            cursor: pointer;
        }

        /* Botones mejorados */
        .btn-save {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%) !important;
            border: none !important;
            border-radius: 20px !important;
            padding: 10px 25px !important;
            font-weight: 600;
            transition: all 0.3s ease;
            box-shadow: 0 4px 12px rgba(72, 187, 120, 0.3);
        }

            .btn-save:hover {
                transform: translateY(-2px);
                box-shadow: 0 6px 18px rgba(72, 187, 120, 0.4);
            }

        .btn-cancel {
            background: #6b7280 !important;
            border: none !important;
            border-radius: 20px !important;
            padding: 10px 25px !important;
            font-weight: 600;
            transition: all 0.3s ease;
        }

            .btn-cancel:hover {
                background: #4b5563 !important;
                transform: translateY(-2px);
            }

        /* Badges para días */
        .day-badge {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
            color: white;
            padding: 6px 12px;
            border-radius: 15px;
            font-size: 0.85rem;
            font-weight: 600;
        }

        /* Horarios */
        .time-display {
            font-family: 'Courier New', monospace;
            font-weight: 600;
            color: #1f2937;
        }

        /* Fila seleccionada */
        .selected-row {
            background-color: #ecfdf5 !important;
            border-left: 4px solid #48bb78;
        }
    </style>

    <script type="text/javascript">
        function toggleRowSelection(checkbox) {
            var row = checkbox.closest('tr');
            if (checkbox.checked) {
                row.classList.add('selected-row');
            } else {
                row.classList.remove('selected-row');
            }
            updateCounter();
        }

        function updateCounter() {
            // Buscar todos los checkboxes que contengan 'chkAsignar' en su ID
            var checkboxes = document.querySelectorAll('input[type="checkbox"][id*="chkAsignar"]');
            var selected = 0;

            checkboxes.forEach(function (cb) {
                if (cb.checked) selected++;
            });

            var counter = document.getElementById('selectedCounter');
            if (counter) {
                counter.textContent = selected + ' turnos seleccionados';
            }
        }

        // Inicializar cuando la página esté completamente cargada
        document.addEventListener('DOMContentLoaded', function () {
            // Marcar filas ya seleccionadas
            var checkboxes = document.querySelectorAll('input[type="checkbox"][id*="chkAsignar"]');
            checkboxes.forEach(function (cb) {
                if (cb.checked) {
                    cb.closest('tr').classList.add('selected-row');
                }
            });

            // Actualizar contador inicial
            updateCounter();
        });
    </script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container mt-4">
        <!-- Información del Médico -->
        <asp:Panel runat="server" ID="pnlDatosMedico" CssClass="card doctor-card mb-4">
            <div class="card-header fw-bold text-center">
                <i class="fa fa-user-md me-2"></i>Información del Médico
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-4">
                        <p class="mb-2">
                            <i class="fa fa-user me-2 text-success"></i>
                            <strong>Nombre:</strong><br>
                            <asp:Label ID="lblNombreMedico" runat="server" CssClass="ms-4" />
                        </p>
                    </div>
                    <div class="col-md-4">
                        <p class="mb-2">
                            <i class="fa fa-id-card me-2 text-success"></i>
                            <strong>DNI:</strong><br>
                            <asp:Label ID="lblDNIMedico" runat="server" CssClass="ms-4" />
                        </p>
                    </div>
                    <div class="col-md-4">
                        <p class="mb-2">
                            <i class="fa fa-certificate me-2 text-success"></i>
                            <strong>CMP:</strong><br>
                            <asp:Label ID="lblCMPMedico" runat="server" CssClass="ms-4" />
                        </p>
                    </div>
                </div>
            </div>
        </asp:Panel>

        <!-- Selección de Turnos -->
        <asp:Panel runat="server" ID="pnlTurnos" CssClass="card turnos-card">
            <div class="card-header fw-bold text-center">
                <i class="fa fa-calendar-alt me-2"></i>Seleccionar Turnos de Trabajo
            </div>
            <div class="card-body">
                <!-- Contador de seleccionados -->
                <div class="alert alert-info d-flex align-items-center mb-3">
                    <i class="fa fa-info-circle me-2"></i>
                    <span id="selectedCounter">0 turnos seleccionados</span>
                </div>

                <asp:GridView ID="gvTurnos" runat="server"
                    AutoGenerateColumns="false"
                    CssClass="table table-custom table-hover"
                    DataKeyNames="idTurno"
                    HeaderStyle-CssClass="table-header">
                    <Columns>
                        <asp:TemplateField HeaderText="Seleccionar" ItemStyle-Width="100px" ItemStyle-CssClass="text-center">
                            <ItemTemplate>
                                <asp:CheckBox ID="chkAsignar" runat="server"
                                    CssClass="custom-checkbox"
                                    Checked='<%# EsTurnoAsignado((int)Eval("idTurno")) %>'
                                    onclick="toggleRowSelection(this)" />
                            </ItemTemplate>
                        </asp:TemplateField>

                        <asp:TemplateField HeaderText="Día" ItemStyle-Width="150px">
                            <ItemTemplate>
                                <span class="day-badge"><%# Eval("dia") %></span>
                            </ItemTemplate>
                        </asp:TemplateField>

                        <asp:TemplateField HeaderText="Hora de Inicio" ItemStyle-Width="150px" ItemStyle-CssClass="text-center">
                            <ItemTemplate>
                                <span class="time-display">
                                    <i class="fa fa-clock me-1 text-success"></i>
                                    <%# ((DateTime) Eval("horaInicio")).ToString(@"HH:mm") %>
                                </span>
                            </ItemTemplate>
                        </asp:TemplateField>

                        <asp:TemplateField HeaderText="Hora de Fin" ItemStyle-Width="150px" ItemStyle-CssClass="text-center">
                            <ItemTemplate>
                                <span class="time-display">
                                    <i class="fa fa-clock me-1 text-success"></i>
                                    <%# ((DateTime) Eval("horaFin")).ToString(@"HH:mm") %>
                                </span>
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>

                <div class="mt-4 text-end">
                    <asp:Button ID="btnGuardar" runat="server"
                        Text="💾 Guardar Asignaciones"
                        CssClass="btn btn-save me-3"
                        OnClick="btnGuardar_Click" />
                    <asp:Button ID="btnCancelar" runat="server"
                        Text="❌ Cancelar"
                        CssClass="btn btn-cancel"
                        OnClientClick="window.history.back(); return false;" />
                </div>
            </div>
        </asp:Panel>
    </div>
</asp:Content>
