<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasPaciente.Master" AutoEventWireup="true" CodeBehind="RegistrarCita.aspx.cs" Inherits="PazCitasWA.RegistrarCita" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container mt-4">
        <h2 class="mb-4">Reservar Cita</h2>
        <div class="progress mb-4">
            <div id="barraProgreso" runat="server" class="progress-bar" role="progressbar" style="width: 0%"></div>
        </div>

        <!-- Paso 1 -->
        <asp:Panel ID="pnlPaso1" runat="server" CssClass="form-section">
            <h4>Paso 1: Selección de Sede</h4>
            <asp:DropDownList ID="ddlSede" runat="server" CssClass="form-control mb-3" />
            <asp:Button ID="btnPaso1" runat="server" Text="Siguiente" CssClass="btn btn-primary btn-nav" OnClick="btnPaso1_Click" />
        </asp:Panel>

        <!-- Paso 2 -->
        <asp:Panel ID="pnlPaso2" runat="server" CssClass="form-section" Visible="false">
            <h4>Paso 2: Selección de Especialidad</h4>
            <asp:DropDownList ID="ddlEspecialidad" runat="server" CssClass="form-control mb-3" />
            <div class="d-flex justify-content-between">
                <asp:Button ID="btnAtras2" runat="server" Text="Anterior" CssClass="btn btn-outline-primary btn-nav" OnClick="btnAtras2_Click" />
                <asp:Button ID="btnPaso2" runat="server" Text="Siguiente" CssClass="btn btn-primary btn-nav" OnClick="btnPaso2_Click" />
            </div>
        </asp:Panel>

        <!-- Paso 3 -->
        <asp:Panel ID="pnlPaso3" runat="server" CssClass="form-section" Visible="false">
            <h4>Paso 3: Selección de Médico</h4>
            <asp:DropDownList ID="ddlMedico" runat="server" CssClass="form-control mb-3" />
            <div class="d-flex justify-content-between">
                <asp:Button ID="btnAtras3" runat="server" Text="Anterior" CssClass="btn btn-outline-primary btn-nav" OnClick="btnAtras3_Click" />
                <asp:Button ID="btnPaso3" runat="server" Text="Siguiente" CssClass="btn btn-primary btn-nav" OnClick="btnPaso3_Click" />
            </div>
        </asp:Panel>

        <!-- Paso 4 -->
        <asp:Panel ID="pnlPaso4" runat="server" CssClass="form-section" Visible="false">
            <h4>Paso 4: Selección de Día</h4>
            <asp:DropDownList ID="ddlDias" runat="server" CssClass="form-control mb-3" />
            <div class="d-flex justify-content-between">
                <asp:Button ID="btnAtras4" runat="server" Text="Anterior" CssClass="btn btn-outline-primary btn-nav" OnClick="btnAtras4_Click" />
                <asp:Button ID="btnPaso4" runat="server" Text="Siguiente" CssClass="btn btn-primary btn-nav" OnClick="btnPaso4_Click" />
            </div>
        </asp:Panel>

        <!-- Paso 5 -->
        <asp:Panel ID="pnlPaso5" runat="server" CssClass="form-section" Visible="false">
            <h4>Paso 5: Selección de Horario</h4>
            <asp:DropDownList ID="ddlHorarios" runat="server" CssClass="form-control mb-3" />
            <div class="d-flex justify-content-between">
                <asp:Button ID="btnAtras5" runat="server" Text="Anterior" CssClass="btn btn-outline-primary btn-nav" OnClick="btnAtras5_Click" />
                <asp:Button ID="btnPaso5" runat="server" Text="Siguiente" CssClass="btn btn-primary btn-nav" OnClick="btnPaso5_Click" />
            </div>
        </asp:Panel>

        <!-- Paso 6 -->
        <asp:Panel ID="pnlPaso6" runat="server" CssClass="form-section" Visible="false">
            <h4>Paso 6: Confirmación de la Cita</h4>
            <div class="card mb-3 p-3">
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>Especialidad:</strong> <asp:Label ID="lblEspecialidad" runat="server" /></p>
                        <p><strong>Fecha:</strong> <asp:Label ID="lblFecha" runat="server" /></p>
                        <p><strong>Sede:</strong> <asp:Label ID="lblSede" runat="server" /></p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Médico:</strong> <asp:Label ID="lblMedico" runat="server" /></p>
                        <p><strong>Hora:</strong> <asp:Label ID="lblHora" runat="server" /></p>
                    </div>
                </div>
            </div>
            <div class="alert alert-info">
                <i class="fas fa-check-circle"></i> Al confirmar esta reserva, acepta los términos y condiciones de PazCitas.
            </div>
            <div class="d-flex justify-content-between">
                <asp:Button ID="btnAtras6" runat="server" Text="Anterior" CssClass="btn btn-outline-primary btn-nav" OnClick="btnAtras6_Click" />
                <asp:Button ID="btnConfirmar" runat="server" Text="Confirmar Reserva" CssClass="btn btn-success btn-nav" OnClick="btnConfirmar_Click" />
            </div>
        </asp:Panel>
    </div>
</asp:Content>
