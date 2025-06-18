<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasMedico.Master" AutoEventWireup="true" CodeBehind="VerCitaMedico.aspx.cs" Inherits="PazCitasWA.VerCitaMedico" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <ContentTemplate>
        <div class="container mt-4">
            <h2 class="mb-4">
                <i class="fa-solid fa-prescription-bottle-medical pe-2"></i>
                Información de la Cita
            </h2>

            <!-- Información del Paciente -->
            <div class="card mb-4">
                <div class="card-header bg-warning text-dark">
                    <i class="fa-solid fa-user pe-2"></i> Información del Paciente
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <asp:Label ID="lblDNICliente" runat="server" Text="DNI del Paciente:" CssClass="form-label" />
                            <asp:TextBox ID="txtDNIPaciente" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                        <div class="col-md-6">
                            <asp:Label ID="LabelNombre" runat="server" Text="Nombre del Paciente:" CssClass="form-label" />
                            <asp:TextBox ID="TextNombre" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <asp:Label ID="LablEmail" runat="server" Text="Email:" CssClass="form-label" />
                            <asp:TextBox ID="txtEmail" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                    </div>
                </div>
            </div>

            <!-- Información de la Cita (Médico) -->
            <div class="card mb-4">
                <div class="card-header bg-primary text-white">
                    <i class="fa-solid fa-calendar-check pe-2"></i> Información de la Cita
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <asp:Label ID="Label1" runat="server" Text="Nro. de Cita:" CssClass="form-label" />
                            <asp:TextBox ID="TxtNume" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                        <div class="col-md-6">
                            <asp:Label ID="Label2" runat="server" Text="Fecha:" CssClass="form-label" />
                            <asp:TextBox ID="TxtFecha" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <asp:Label ID="Label3" runat="server" Text="Estado:" CssClass="form-label" />
                            <asp:TextBox ID="TextEstado" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                        <div class="col-md-6">
                            <asp:Label ID="Label4" runat="server" Text="Motivo de Consulta:" CssClass="form-label" />
                            <asp:TextBox ID="TextMotivo" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                    </div>
                </div>
            </div>

            <!-- Botones -->
            <div class="card-footer clearfix mb-3">
                <asp:LinkButton ID="btnHistorial" runat="server" CssClass="btn btn-outline-primary float-end" OnClick="btnHistorial_Click">
                    <i class="fa-solid fa-file-arrow-down pe-2"></i>Descargar historial médico
                </asp:LinkButton>
            </div>

            <div class="card-footer clearfix">
                <asp:LinkButton ID="btnRegresar" runat="server" CssClass="btn btn-secondary float-start btn-lg px-4 py-2" OnClick="btnRegresar_Click">
                    <i class="fa-solid fa-arrow-left pe-2"></i>Regresar
                </asp:LinkButton>

                <asp:LinkButton ID="btnContinuar" runat="server" CssClass="btn btn-primary float-end btn-lg px-4 py-2" OnClick="btnContinuar_Click">
                    <i class="fa-solid fa-arrow-right pe-2"></i>Continuar
                </asp:LinkButton>
            </div>
        </div>
    </ContentTemplate>
</asp:Content>
