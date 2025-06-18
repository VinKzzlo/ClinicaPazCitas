<%@ Page Title="Registrar Cita" Language="C#" MasterPageFile="~/PazCitasPaciente.Master" AutoEventWireup="true" CodeBehind="RegistrarCitaPaciente.aspx.cs" Inherits="PazCitasWA.RegistrarCitaPaciente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Registrar Cita
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet" />

    <style>
        .form-wrapper {
            display: flex;
            justify-content: center;
            padding: 60px 20px;
        }

        .form-container {
            width: 100%;
            max-width: 1024px;
            padding: 50px;
            background-color: #ffffff;
            border-radius: 16px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.08);
        }

        .form-title {
            text-align: center;
            font-size: 30px;
            font-weight: 700;
            margin-bottom: 35px;
            color: #212529;
        }

        h4 {
            font-size: 20px;
            font-weight: 600;
            color: #495057;
            margin-bottom: 14px;
            border-left: 4px solid #0d6efd;
            padding-left: 12px;
        }

        .form-select {
            padding: 0.75rem 1rem;
            border-radius: 12px !important;
            background-color: #fff;
            border: 1px solid #ced4da;
            font-size: 16px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.06);
        }

        /* NUEVO PANEL 4 - Estilo moderno horizontal tipo tarjetas */

        .calendar-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 10px;
            margin-bottom: 24px;
        }

            .calendar-header .btn {
                border-radius: 50%;
                width: 40px;
                height: 40px;
                font-size: 20px;
                padding: 0;
            }

            .calendar-header h5 {
                font-size: 18px;
                font-weight: 600;
                margin: 0;
            }



        .calendar-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 24px;
        }

        .day-card {
            background-color: #ffffff;
            border: 1px solid #dee2e6;
            border-radius: 14px;
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            box-shadow: 0 3px 10px rgba(0,0,0,0.05);
            transition: box-shadow 0.3s ease-in-out;
        }

            .day-card:hover {
                box-shadow: 0 6px 20px rgba(0,0,0,0.08);
            }

        .day-title {
            font-size: 16px;
            font-weight: 600;
            color: #0d6efd;
            margin-bottom: 12px;
            text-align: center;
            border-bottom: 1px dashed #adb5bd;
            padding-bottom: 6px;
        }

        .hour-list {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            justify-content: center;
        }

        .time-slot {
            background-color: #f1f3f5;
            border: 1px solid #ced4da;
            border-radius: 50rem;
            padding: 10px 18px;
            font-size: 14px;
            font-weight: 500;
            color: #212529;
            cursor: pointer;
            transition: all 0.2s ease-in-out;
        }

            .time-slot:hover {
                background-color: #e0f0ff;
            }

            .time-slot.selected {
                background-color: #0d6efd !important;
                color: #ffffff;
                border: 1px solid #0a58ca;
                box-shadow: 0 0 0 2px #a5d8ff;
            }

        .modal-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: none;
            justify-content: center;
            align-items: center;
            z-index: 9999;
        }

        .modal-box {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            max-width: 400px;
            text-align: center;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
        }

            .modal-box.success {
                border-left: 6px solid #28a745;
            }

            .modal-box.error {
                border-left: 6px solid #dc3545;
            }
    </style>

    <script>
        function mostrarModal(mensaje, exito) {
            const modal = document.getElementById("modalMensaje");
            const texto = document.getElementById("modalTexto");
            const contenedor = modal.querySelector(".modal-box");
            texto.innerText = mensaje;
            contenedor.className = "modal-box " + (exito ? "success" : "error");
            modal.style.display = "flex";
        }

        function cerrarModal() {
            window.location.href = 'ListarCitasPaciente.aspx';
        }

        function seleccionarTurno(btn, fieldId, valor) {
            document.querySelectorAll('.time-slot').forEach(b => b.classList.remove('selected'));
            btn.classList.add('selected');
            document.getElementById(fieldId).value = valor;
        }
    </script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="form-wrapper">
        <div class="form-container text-center">
            <h2 class="form-title">🗓 Reserva tu cita paso a paso</h2>

            <!-- Panel 1: Sede -->
            <asp:Panel ID="Panel1" runat="server" Visible="true">
                <h4 class="text-start">Paso 1: Selecciona la sede</h4>
                <div class="mb-4">
                    <asp:DropDownList ID="DropDownList1" runat="server" CssClass="form-select form-select-lg" />
                </div>
                <asp:LinkButton ID="Button1" runat="server" OnClick="btnSiguiente1_Click" CssClass="btn btn-primary px-5 py-2">
                    <i class="bi bi-arrow-right-circle me-2"></i> Siguiente
                </asp:LinkButton>
            </asp:Panel>

            <!-- Panel 2: Especialidad -->
            <asp:Panel ID="Panel2" runat="server" Visible="false">
                <h4 class="text-start">Paso 2: Elige una especialidad</h4>
                <div class="mb-4">
                    <asp:DropDownList ID="DropDownList2" runat="server" CssClass="form-select form-select-lg" />
                </div>
                <div class="d-flex justify-content-center gap-3">
                    <asp:LinkButton ID="Button2" runat="server" OnClick="btnAtras1_Click" CssClass="btn btn-outline-secondary px-5 py-2">
                        <i class="bi bi-arrow-left-circle me-2"></i> Atrás
                    </asp:LinkButton>
                    <asp:LinkButton ID="Button3" runat="server" OnClick="btnSiguiente2_Click" CssClass="btn btn-primary px-5 py-2">
                        <i class="bi bi-arrow-right-circle me-2"></i> Siguiente
                    </asp:LinkButton>
                </div>
            </asp:Panel>

            <!-- Panel 3: Médico -->
            <asp:Panel ID="Panel3" runat="server" Visible="false">
                <h4 class="text-start">Paso 3: Selecciona al médico</h4>
                <div class="mb-4">
                    <asp:DropDownList ID="DropDownList3" runat="server" CssClass="form-select form-select-lg" />
                </div>
                <div class="d-flex justify-content-center gap-3">
                    <asp:LinkButton ID="Button4" runat="server" OnClick="btnAtras2_Click" CssClass="btn btn-outline-secondary px-5 py-2">
                        <i class="bi bi-arrow-left-circle me-2"></i> Atrás
                    </asp:LinkButton>
                    <asp:LinkButton ID="Button5" runat="server" OnClick="btnSiguiente3_Click" CssClass="btn btn-primary px-5 py-2">
                        <i class="bi bi-arrow-right-circle me-2"></i> Siguiente
                    </asp:LinkButton>
                </div>
            </asp:Panel>

            <!-- Panel 4: Turnos -->
            <asp:Panel ID="Panel4" runat="server" Visible="false">
                <h4 class="text-start">Paso 4: Selecciona una fecha y horario disponible</h4>
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <asp:LinkButton ID="btnSemanaAnterior" runat="server" OnClick="btnSemanaAnterior_Click" CssClass="btn btn-outline-secondary">
                        <i class="bi bi-chevron-left"></i>
                    </asp:LinkButton>
                    <asp:Label ID="lblSemanaActual" runat="server" Font-Bold="true" Font-Size="Large" CssClass="text-primary fw-bold" />
                    <asp:LinkButton ID="btnSemanaSiguiente" runat="server" OnClick="btnSemanaSiguiente_Click" CssClass="btn btn-outline-secondary">
                        <i class="bi bi-chevron-right"></i>
                    </asp:LinkButton>
                </div>

                <!-- Nuevo diseño de turnos por tarjetas horizontales -->
                <div class="calendar-container">
                    <asp:Literal ID="Literal1" runat="server" />
                </div>

                <asp:HiddenField ID="HiddenField1" runat="server" />
                <div class="d-flex justify-content-center gap-3 mt-4">
                    <asp:LinkButton ID="Button6" runat="server" OnClick="btnAtras3_Click" CssClass="btn btn-outline-secondary px-5 py-2">
                        <i class="bi bi-arrow-left-circle me-2"></i> Atrás
                    </asp:LinkButton>
                    <asp:LinkButton ID="Button7" runat="server" OnClick="btnSiguiente4_Click" CssClass="btn btn-primary px-5 py-2">
                        <i class="bi bi-arrow-right-circle me-2"></i> Siguiente
                    </asp:LinkButton>
                </div>
            </asp:Panel>

            <!-- Panel 5: Confirmación -->
            <asp:Panel ID="Panel5" runat="server" Visible="false">
                <h4 class="text-start">Paso 5: Confirmación de Cita</h4>
                <div class="text-start mb-3">
                    <p>
                        <strong>Sede:</strong>
                        <asp:Label ID="Label1" runat="server" />
                    </p>
                    <p>
                        <strong>Especialidad:</strong>
                        <asp:Label ID="Label2" runat="server" />
                    </p>
                    <p>
                        <strong>Médico:</strong>
                        <asp:Label ID="Label3" runat="server" />
                    </p>
                    <p>
                        <strong>Horario:</strong>
                        <asp:Label ID="Label4" runat="server" />
                    </p>
                </div>
                <div class="d-flex justify-content-center gap-3">
                    <asp:LinkButton ID="Button8" runat="server" OnClick="btnAtras4_Click" CssClass="btn btn-outline-secondary px-5 py-2">
                        <i class="bi bi-arrow-left-circle me-2"></i> Atrás
                    </asp:LinkButton>
                    <asp:LinkButton ID="Button9" runat="server" OnClick="btnRegistrar_Click" CssClass="btn btn-success px-5 py-2">
                        <i class="bi bi-check-circle-fill me-2"></i> Confirmar y Registrar
                    </asp:LinkButton>
                </div>
            </asp:Panel>

            <!-- Mensaje de error -->
            <asp:Label ID="Label5" runat="server" CssClass="text-danger mt-4 d-block fw-semibold" />

            <!-- Modal -->
            <div id="modalMensaje" class="modal-overlay">
                <div class="modal-box">
                    <h4 id="modalTexto">Mensaje</h4>
                    <button type="button" onclick="cerrarModal()">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
</asp:Content>

