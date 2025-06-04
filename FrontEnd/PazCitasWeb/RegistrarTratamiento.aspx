<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="RegistrarTratamiento.aspx.cs" Inherits="PazCitasWA.RegistrarTratamiento" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Registrar Tratamiento
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header text-center">
                <h4 class="fw-bold m-0">
                    <asp:Label ID="lblTitulo" runat="server" Text="Label"></asp:Label>
                </h4>
            </div>
            <div class="card-body">
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblIDMedicamento" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold" runat="server" Text="ID Medicamento:"></asp:Label>
                    <div class="col-sm-2">
                        <asp:TextBox ID="txtIDMedicamento" CssClass="form-control" Enabled="false" runat="server"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblNombre" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold" runat="server" Text="Nombre:"></asp:Label>
                    <div class="col-sm-6">
                        <asp:TextBox ID="txtNombre" CssClass="form-control" runat="server"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-start">
                    <asp:Label ID="lblPresentación" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold " runat="server" Text="Presentación:"></asp:Label>
                    <div class="col-sm-8">
                        <asp:TextBox ID="txtPresentación" CssClass="form-control" runat="server"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-start">
                    <asp:Label ID="lblStock" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold " runat="server" Text="Stock:"></asp:Label>
                    <div class="col-sm-8">
                        <asp:TextBox ID="txtStock" CssClass="form-control" runat="server"></asp:TextBox>
                    </div>
                </div>
            </div>
            <div class="card-footer clearfix">
                <asp:LinkButton ID="btnRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="btnRegresar_Click" />
                <asp:LinkButton ID="btnGuardar" CssClass="float-end btn btn-success" runat="server" Text="<i class='fa-solid fa-floppy-disk pe-2'></i> Guardar" OnClick="btnGuardar_Click" />
            </div>
        </div>
    </div>
</asp:Content>
