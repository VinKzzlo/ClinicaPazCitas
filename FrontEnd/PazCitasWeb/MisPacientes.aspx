<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasMedico.Master" AutoEventWireup="true" CodeBehind="MisPacientes.aspx.cs" Inherits="PazCitasWA.MisPacientes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
        <script src="Scripts/PazcitasS/RegistrarLineaMedicamento.js"></script>

</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <asp:UpdatePanel ID="upContenedor" runat="server" UpdateMode="Conditional">
        <ContentTemplate>
        <div class="card border shadow-sm mb-4">
            <div class="card-header fw-bold">Ingrese dni del Paciente</div>
            <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblDni" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold" runat="server" Text="DNI:"></asp:Label>
                    <div class="col-sm-6">
                        <asp:TextBox ID="txtDni" CssClass="form-control" runat="server"></asp:TextBox>
                    </div>
                </div>
                

            <div class="card-footer clearfix bg-light rounded-bottom shadow-sm">
                <asp:LinkButton ID="btnRegresar" runat="server" Text="Regresar" CssClass="float-start btn btn-secondary btn-sm rounded-pill shadow-sm" OnClick="btnRegresar_Click" />
                <asp:LinkButton ID="btnBuscar" runat="server" Text="Buscar" CssClass="float-end btn btn-primary btn-sm rounded-pill shadow-sm" OnClick="btnBuscar_Click" />
            </div>
        </div>
        <div class="card border shadow-sm mb-4">
            <div class="card-header fw-bold">Información del Paciente</div>
            <div class="card-body row g-3">
                <div class="mb-3 row">
                    <asp:Label ID="LabelId" runat="server" Text="ID del Paciente:" CssClass="col-sm-2 col-form-label fw-semibold" />
                    <div class="col-sm-3">
                        <asp:TextBox ID="TextID" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="LabelNombre" runat="server" Text="Nombre del Paciente:" CssClass="col-sm-2 col-form-label fw-semibold" />
                    <div class="col-sm-3">
                        <asp:TextBox ID="TextNombre" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblApellido" runat="server" Text="Apellido del Paciente:" CssClass="col-sm-2 col-form-label fw-semibold" />
                    <div class="col-sm-3">
                        <asp:TextBox ID="textApellido" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="Labeltelefono" runat="server" Text="Teléfono:" CssClass="col-sm-2 col-form-label fw-semibold" />
                    <div class="col-sm-3">
                        <asp:TextBox ID="TextTelefono" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                    </div>
                </div>

                <div class="mb-3 row">
                    <asp:Label ID="Labelemail" runat="server" Text="Email:" CssClass="col-sm-2 col-form-label fw-semibold" />
                    <div class="col-sm-3">
                        <asp:TextBox ID="Textemail" runat="server" Enabled="false" CssClass="form-control form-control-sm" />
                    </div>
                </div>
            </div>
        </div>

            <div class="container row">
                <asp:GridView ID="dgvNotas" runat="server" AutoGenerateColumns="false"
                    OnRowDataBound="dgvNotas_RowDataBound" AllowPaging="true"
                    OnPageIndexChanging="dgvNotas_PageIndexChanging" PageSize="5"
                    CssClass="table table-hover table-responsive table-striped">
                    <Columns>
                        <asp:BoundField HeaderText="Descripcion" DataField="descripcion" ItemStyle-CssClass="align-middle" />
                        <asp:BoundField HeaderText="Diagnóstico Completo" DataField="diagnostico" ItemStyle-CssClass="align-middle" />
                        <asp:BoundField HeaderText="Observación" DataField="observaciones" ItemStyle-CssClass="align-middle" />

                        <asp:TemplateField HeaderText="Acciones">
                            <ItemTemplate>
                                <asp:LinkButton ID="btnVerDetalle" runat="server"
                                    Text="Ver Detalle" CssClass="btn btn-sm btn-outline-primary rounded-pill"
                                    CommandName="VerDetalle" CommandArgument='<%# Eval("IdNota") %>' OnClick="btnVerDetalle_Click"/>
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>
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