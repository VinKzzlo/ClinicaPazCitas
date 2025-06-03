<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="RegistrarMedico.aspx.cs" Inherits="PazCitasWA.RegistrarMedico" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Registrar Médico
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        /* radios a la altura de un .form-control (≈ 2.375 rem) */
        .form-check-input.custom-radio-big {
            width: 2.375rem;
            height: 2.375rem;
            margin-top: 0; /* para que queden centrados verticalmente */
        }

        /* texto alineado verticalmente */
        .form-check-label.custom-radio-big {
            line-height: 2.375rem;
            padding-left: .5rem;
        }
        /* radio ±21 px → centrado dentro de 2.375 rem (≈38 px) */
        .form-check-input.custom-radio-md {
            width: 1.3rem;
            height: 1.3rem;
            /* (2.375 rem − 1.3 rem) / 2 ≈ 0.54 rem */
            margin-top: 0.55rem;
        }

        .form-check-label.custom-radio-md {
            line-height: 2.375rem; /* el mismo alto visual del resto */
            padding-left: .5rem;
        }
    </style>
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
                <!-- Id Área + DNI + CMP -->
                <div class="mb-3 row align-items-center">
                    <!-- Id Área -->
                    <asp:Label ID="lblIDUsuario" runat="server"
                        Text="Id Área:" CssClass="col-form-label fw-bold col-auto pe-2" />
                    <div class="col-2">
                        <asp:TextBox ID="txtIDUsuario" CssClass="form-control" Enabled="false" runat="server" />
                    </div>

                    <!-- DNI -->
                    <asp:Label ID="lblDNI" runat="server"
                        Text="DNI:" CssClass="col-form-label fw-bold col-auto ps-lg-4 pe-2" />
                    <div class="col-2">
                        <asp:TextBox ID="txtDNI" CssClass="form-control" runat="server" />
                    </div>

                    <!-- CMP -->
                    <asp:Label ID="lblCMP" runat="server"
                        Text="CMP:" CssClass="col-form-label fw-bold col-auto ps-lg-4 pe-2" />
                    <div class="col-2">
                        <asp:TextBox ID="txtCMP" CssClass="form-control" runat="server" />
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblNombre" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold" runat="server" Text="Nombre:"></asp:Label>
                    <div class="col-sm-6">
                        <asp:TextBox ID="txtNombre" CssClass="form-control" runat="server"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblPaterno" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold" runat="server" Text="Apellido Paterno:"></asp:Label>
                    <div class="col-sm-6">
                        <asp:TextBox ID="txtPaterno" CssClass="form-control" runat="server"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblMaterno" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold" runat="server" Text="Apellido Materno:"></asp:Label>
                    <div class="col-sm-6">
                        <asp:TextBox ID="txtMaterno" CssClass="form-control" runat="server"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-start">
                    <asp:Label ID="lblFechaNacimiento" CssClass="col-form-label col-sm-auto pe-sm-2 fw-bold " runat="server" Text="Fecha de nacimiento:"></asp:Label>
                    <div class="col-sm-8">
                        <input id="dtpFechaNacimiento" class="form-control" type="date" runat="server" />
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblGenero" runat="server"
                        Text="Género:" CssClass="col-form-label fw-bold col-auto pe-2" />
                    <div class="col-auto">
                        <div class="form-check form-check-inline">
                            <input runat="server" id="rbMasculino"
                                type="radio" name="genero"
                                class="form-check-input custom-radio-md" />
                            <label runat="server"
                                for="cphContenido_rbMasculino"
                                class="form-check-label custom-radio-md">
                                Masculino</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input runat="server" id="rbFemenino"
                                type="radio" name="genero"
                                class="form-check-input custom-radio-md" />
                            <label runat="server"
                                for="cphContenido_rbFemenino"
                                class="form-check-label custom-radio-md">
                                Femenino</label>
                        </div>
                    </div>
                </div>
                <!-- Especialidad + Sede -->
                <div class="mb-3 row align-items-center">
                    <!-- Especialidad -->
                    <asp:Label ID="lblEspecialidad" runat="server"
                        Text="Especialidad:" CssClass="col-form-label fw-bold col-auto pe-2" />
                    <div class="col-4 col-lg-3">
                        <asp:DropDownList ID="ddlEspecialidad" runat="server" CssClass="form-select" />
                    </div>

                    <!-- Sede -->
                    <asp:Label ID="lblSede" runat="server"
                        Text="Sede:" CssClass="col-form-label fw-bold col-auto ps-lg-4 pe-2" />
                    <div class="col-4 col-lg-3">
                        <asp:DropDownList ID="ddlSede" runat="server" CssClass="form-select" />
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
