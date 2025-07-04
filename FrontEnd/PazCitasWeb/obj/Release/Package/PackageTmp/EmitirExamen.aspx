<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasMedico.Master" AutoEventWireup="true" CodeBehind="EmitirExamen.aspx.cs" Inherits="PazCitasWA.EmitirExamen" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">

    <div class="container mt-4">
        <h2 class="mb-4"><i class="fa-solid fa-microscope pe-2"></i>Solicitar Exámenes Médicos</h2>

        <!-- 🔍 Buscar paciente -->
        <div class="card mb-4">
            <div class="card-header">Buscar Paciente</div>
            <div class="card-body row g-3">
                <div class="col-md-4">
                    <input type="text" class="form-control" id="txtDNI" placeholder="Ingrese DNI del paciente">
                </div>
                <div class="col-md-2">
                    <button class="btn btn-primary w-100"><i class="fa-solid fa-search"></i>Buscar</button>
                </div>
            </div>
        </div>

        <!-- 📋 Datos del paciente y cita -->
        <div class="card mb-4">
            <div class="card-header">Datos del Paciente y de la Cita</div>
            <div class="card-body row g-3">
                <div class="col-md-4">
                    <label>Nombre:</label>
                    <input type="text" class="form-control" id="txtNombre" disabled>
                </div>
                <div class="col-md-2">
                    <label>Edad:</label>
                    <input type="text" class="form-control" id="txtEdad" disabled>
                </div>
                <div class="col-md-3">
                    <label>Sede:</label>
                    <input type="text" class="form-control" id="txtSede" disabled>
                </div>
                <div class="col-md-3">
                    <label>Fecha de Cita:</label>
                    <input type="text" class="form-control" id="txtFechaCita" disabled>
                </div>
            </div>
        </div>

        <!-- 🔬 Solicitar examen -->
        <div class="card mb-4">
            <div class="card-header">Exámenes a Solicitar</div>
            <div class="card-body">
                <label class="form-label">Seleccionar exámenes:</label>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="chkHemograma" value="Hemograma">
                    <label class="form-check-label" for="chkHemograma">Hemograma completo</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="chkOrina" value="Análisis de Orina">
                    <label class="form-check-label" for="chkOrina">Análisis de Orina</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="chkGlucosa" value="Glucosa en sangre">
                    <label class="form-check-label" for="chkGlucosa">Glucosa en sangre</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="chkRayosX" value="Radiografía de Tórax">
                    <label class="form-check-label" for="chkRayosX">Radiografía de Tórax</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="chkEcografia" value="Ecografía abdominal">
                    <label class="form-check-label" for="chkEcografia">Ecografía abdominal</label>
                </div>

                <!-- Observaciones -->
                <label class="form-label mt-3">Observaciones clínicas:</label>
                <textarea class="form-control" rows="2" id="txtObservaciones" placeholder="Ej: Realizar en ayunas, sin medicamentos previos..."></textarea>

                <!-- Botón emitir -->
                <div class="d-grid mt-4">
                    <button class="btn btn-primary btn-lg"><i class="fa-solid fa-print pe-2"></i>Emitir Orden de Examen</button>
                </div>
            </div>
        </div>
    </div>

</asp:Content>
