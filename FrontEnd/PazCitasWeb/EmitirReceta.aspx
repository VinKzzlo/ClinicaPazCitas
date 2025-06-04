<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasMedico.Master" AutoEventWireup="true" CodeBehind="EmitirReceta.aspx.cs" Inherits="PazCitasWA.EmitirReceta" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container mt-4">
        <h2 class="mb-4"><i class="fa-solid fa-prescription-bottle-medical pe-2"></i>Emitir Receta Médica</h2>

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
            <div class="card-header">Datos del Paciente y Cita</div>
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

        <!-- 💊 Recetar medicamentos -->
        <div class="card mb-4">
            <div class="card-header">Receta Médica</div>
            <div class="card-body row g-3 align-items-end">
                <div class="col-md-5">
                    <label>Medicamento:</label>
                    <select class="form-select" id="selectMedicamento">
                        <option value="">Seleccione un medicamento</option>
                        <option value="Paracetamol">Paracetamol 500mg</option>
                        <option value="Ibuprofeno">Ibuprofeno 400mg</option>
                        <option value="Amoxicilina">Amoxicilina 875mg</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <label>Dosis y frecuencia:</label>
                    <input type="text" class="form-control" id="txtDosis" placeholder="Ej: 1 cada 8 horas">
                </div>
                <div class="col-md-2">
                    <button class="btn btn-success w-100" onclick="agregarMedicamento()"><i class="fa-solid fa-plus"></i>Agregar</button>
                </div>
            </div>

            <!-- 🧾 Tabla de medicamentos -->
            <hr>
            <div class="table-responsive">
                <table class="table table-bordered table-sm mt-3" id="tablaReceta">
                    <thead class="table-light">
                        <tr>
                            <th>Medicamento</th>
                            <th>Dosis</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Aquí se agregan dinámicamente los medicamentos -->
                    </tbody>
                </table>
            </div>

            <!-- Observaciones -->
            <label class="form-label mt-3">Observaciones adicionales:</label>
            <textarea class="form-control" rows="2" id="txtObservaciones"></textarea>

            <!-- Guardar receta -->
            <div class="d-grid mt-4">
                <button class="btn btn-primary btn-lg"><i class="fa-solid fa-print pe-2"></i>Emitir Receta</button>
            </div>
        </div>
    </div>

    <!-- JS básico para simular tabla de medicamentos -->
    <script>
        function agregarMedicamento() {
            const select = document.getElementById("selectMedicamento");
            const dosis = document.getElementById("txtDosis").value;
            const tabla = document.getElementById("tablaReceta").getElementsByTagName('tbody')[0];

            if (select.value && dosis) {
                const fila = tabla.insertRow();
                fila.innerHTML = `
                <td>${select.value}</td>
                <td>${dosis}</td>
                <td><button class="btn btn-danger btn-sm" onclick="eliminarFila(this)"><i class="fa-solid fa-trash"></i></button></td>
            `;
            }
        }

        function eliminarFila(boton) {
            const fila = boton.closest("tr");
            fila.remove();
        }
    </script>


</asp:Content>
