<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasMedico.Master" AutoEventWireup="true" CodeBehind="EmitirReceta.aspx.cs" Inherits="PazCitasWA.EmitirReceta" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server" />
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <script src="Scripts/PazcitasS/RegistrarLineaMedicamento.js"></script>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <asp:UpdatePanel ID="upContenedor" runat="server" UpdateMode="Conditional">
        <ContentTemplate>

            <div class="container mt-4">
                <h2 class="mb-4"><i class="fa-solid fa-prescription-bottle-medical pe-2"></i> Emitir Nota Clinica</h2>

                <!-- 🧾 Información del paciente -->
                <div class="card border shadow-sm mb-4">
                    <div class="card-header fw-bold">Información del Paciente</div>
                    <div class="card-body row g-3">
                        <div class="mb-3 row">
                            <asp:Label ID="lblDNICliente" runat="server" Text="DNI del Paciente:" CssClass="col-sm-2 col-form-label fw-semibold" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="txtDNIPaciente" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="LabelNombre" runat="server" Text="Nombre del Paciente:" CssClass="col-sm-2 col-form-label fw-semibold" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="TextNombre" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="Labeltelefono" runat="server" Text="Teléfono:" CssClass="col-sm-2 col-form-label fw-semibold" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="TextTelefono" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 💊 Detalle de receta -->
                <div class="card border shadow-sm mb-4">
                    <div class="card-header fw-bold">Detalle de Receta</div>
                    <div class="card-body">
                        <div class="mb-3 row">
                            <asp:Label ID="lblIDMedicamento" runat="server" Text="ID del Medicamento:" CssClass="col-sm-2 col-form-label fw-semibold" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="txtIDMedicamento" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                            </div>
                            <asp:LinkButton ID="btnBuscarMedicamento" runat="server" Text="<i class='fa-solid fa-magnifying-glass'></i>" CssClass="btn btn-outline-primary btn-sm col-sm-auto rounded-pill shadow-sm" OnClick="btnBuscarMedicamento_Click" />
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblNombreMedicamento" runat="server" Text="Nombre:" CssClass="col-sm-2 col-form-label fw-semibold" />
                            <div class="col-sm-5">
                                <asp:TextBox ID="txtNombreMedicamento" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblPresentacionMedicamento" runat="server" Text="Presentación:" CssClass="col-sm-2 col-form-label fw-semibold" />
                            <div class="col-sm-5">
                                <asp:TextBox ID="TextPresentacion" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblCantidadUnidades" runat="server" Text="Cantidad:" CssClass="col-sm-2 col-form-label fw-semibold" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="txtCantidadUnidades" runat="server" CssClass="form-control form-control-sm" />
                            </div>
                            <div class="col-sm-3">
                                <asp:LinkButton ID="lbAgregarMedicamento" runat="server" CssClass="btn btn-success btn-sm rounded-pill shadow-sm px-3" Text="<i class='fa-solid fa-plus pe-2'></i> Agregar" OnClick="lbAgregarMedicamento_Click" />
                            </div>
                        </div>

                        <!-- Grid de Medicamentos -->
                        <div class="row">
                            <asp:GridView ID="gvLineasMedicamento" runat="server"
                                AllowPaging="true" PageSize="5" AutoGenerateColumns="false"
                                CssClass="table table-bordered table-hover table-striped text-center align-middle shadow-sm"
                                OnRowDataBound="gvLineasMedicamento_RowDataBound">
                                <HeaderStyle CssClass="table-primary" />
                                <Columns>
                                    <asp:BoundField HeaderText="Nombre del Producto" />
                                    <asp:BoundField HeaderText="Presentación del Producto" />
                                    <asp:BoundField HeaderText="Cant" />
                                    <asp:TemplateField>
                                        <ItemTemplate>
                                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i>" 
                                                
                                                CssClass="btn btn-outline-danger btn-sm rounded-circle shadow-sm" OnClick="trashBouton" 
                                                CommandArgument="<%# Container.DataItemIndex %>" />
                                        </ItemTemplate>
                                    </asp:TemplateField>
                                </Columns>
                            </asp:GridView>
                        </div>

                         <div class="card border shadow-sm mb-4">
     
                        <div class="mb-3 row">
                            <asp:Label ID="LabelIndicaciones" runat="server" Text="Indicaciones:" CssClass="col-sm-2 col-form-label fw-semibold" />
                            <div class="col-sm-5">
                                <asp:TextBox ID="txtIndicaciones" runat="server" CssClass="form-control form-control-sm" />
                            </div>
                        </div>
                    </div>
                </div>

             
                
                <div class="card border shadow-sm mb-4">
                    <div class="card-header fw-bold">Registrar Observaciones</div>
    <div class="card-body row g-3">
        <div class="mb-3 row align-items-center">
            <asp:Label ID="lblDescripcion" CssClass="col-sm-2 col-form-label fw-semibold" runat="server" Text="Descripción:" />
            <div class="col-sm-6">
                <asp:TextBox ID="TextDescripcion" CssClass="form-control form-control-sm" runat="server" />
            </div>
        </div>

        <div class="mb-3 row align-items-center">
            <asp:Label ID="lblDiagnostico" CssClass="col-sm-2 col-form-label fw-semibold" runat="server" Text="Diagnóstico:" />
            <div class="col-sm-6">
                <asp:TextBox ID="TextDiag" CssClass="form-control form-control-sm" runat="server" />
            </div>
        </div>

        <div class="mb-3 row align-items-center">
            <asp:Label ID="LabelObs" CssClass="col-sm-2 col-form-label fw-semibold" runat="server" Text="Observación:" />
            <div class="col-sm-6">
                <asp:TextBox ID="TextObs" CssClass="form-control form-control-sm" runat="server" />
            </div>
        </div>
    </div>
</div>
                </div>

                    <!-- Botones de acción -->
    <div class="card-footer clearfix bg-light rounded-bottom shadow-sm">
        <asp:LinkButton ID="btnRegresar" runat="server" Text="Regresar" CssClass="float-start btn btn-secondary btn-sm rounded-pill shadow-sm" OnClick="btnRegresar_Click" />
        <asp:LinkButton ID="btnGuardar" runat="server" Text="Guardar" CssClass="float-end btn btn-primary btn-sm rounded-pill shadow-sm" OnClick="btnGuardar_Click" />
    </div>
</div>
            <!-- Modal de búsqueda de medicamentos -->
            <div class="modal fade" id="form-modal-medicamento">
                <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable shadow-lg rounded-3">
                    <div class="modal-content rounded-3">
                        <div class="modal-header bg-primary text-white">
                            <h5 class="modal-title"><i class="fa-solid fa-capsules pe-2"></i> Búsqueda de Medicamentos</h5>
                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <asp:UpdatePanel ID="upBusqMedicamento" runat="server" UpdateMode="Conditional">
                                <ContentTemplate>
                                    
                                    <div class="row pb-3 pt-3 align-items-center">
                                        <div class="col-auto">
                                            <asp:Label CssClass="form-label fw-semibold" runat="server" Text="Ingresar nombre del Medicamento:" />
                                        </div>
                                        <div class="col-sm-3">
                                            <asp:TextBox CssClass="form-control form-control-sm" ID="txtNombreMedicamentoModal" runat="server" />
                                        </div>
                                        <div class="col-sm-2">
                                            <asp:LinkButton ID="lbBusquedaMedicamentoModal" runat="server" CssClass="btn btn-info btn-sm rounded-pill shadow-sm" 
                                                Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="lbBusquedaMedicamentoModal_Click"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <asp:GridView ID="gvMedicamentos" runat="server"
                                            PageSize="5" AllowPaging="true" AutoGenerateColumns="false"
                                            CssClass="table table-bordered table-hover table-striped align-middle shadow-sm"
                                            OnRowDataBound="gvMedicamentos_RowDataBound"
                                            OnPageIndexChanging="gvMedicamentos_PageIndexChanging">
                                            <HeaderStyle CssClass="table-info" />
                                            <Columns>
                                                <asp:BoundField HeaderText="Nro." />
                                                <asp:BoundField HeaderText="Nombre del medicamento" />
                                                <asp:BoundField HeaderText="Presentación del medicamento" />
                                                <asp:BoundField HeaderText="Stock" />
                                                <asp:TemplateField>
                                                    <ItemTemplate>
                                                        <asp:LinkButton runat="server"
                                                            Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar"
                                                            CommandArgument='<%# Eval("IdMedicamento") %>'
                                                            CssClass="btn btn-outline-success btn-sm rounded-pill shadow-sm"
                                                            OnClick="lbSeleccionarMedicamento_Click" />
                                                    </ItemTemplate>
                                                </asp:TemplateField>
                                            </Columns>
                                        </asp:GridView>
                                    </div>
                                </ContentTemplate>
                            </asp:UpdatePanel>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal de error -->
            <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered rounded-3 shadow">
                    <div class="modal-content rounded-3">
                        <div class="modal-header bg-danger text-white rounded-top">
                            <h5 class="modal-title" id="errorModalLabel">
                                <i class="bi bi-exclamation-triangle-fill me-2"></i> Mensaje de Error
                            </h5>
                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <asp:Label ID="lblMensajeError" runat="server" Text="Mensaje de ejemplo..." CssClass="form-text text-danger fw-bold" />
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-light btn-sm rounded-pill" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>

        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
