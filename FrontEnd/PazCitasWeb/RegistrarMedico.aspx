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
                <div class="mb-3 row align-items-start">
                    <!-- Id Médico -->
                    <div class="col-4">
                        <asp:Label ID="lblIDUsuario" runat="server"
                            Text="Id Médico:" CssClass="col-form-label fw-medium pe-2" />
                        <asp:TextBox ID="txtIDUsuario" CssClass="form-control w-75" Enabled="false" runat="server" />
                        <div style="min-height: 1.25rem;"></div>
                        <!-- Para mantener alineado con los otros -->
                    </div>

                    <!-- DNI -->
                    <div class="col-4">
                        <asp:Label ID="lblDNI" runat="server"
                            Text="DNI:" CssClass="col-form-label fw-medium pe-2" />
                        <asp:TextBox ID="txtDNI" CssClass="form-control w-75" runat="server" MaxLength="8" />
                        <div style="min-height: 1.25rem;">
                            <asp:RegularExpressionValidator
                                ID="revDNI" runat="server" ControlToValidate="txtDNI"
                                ErrorMessage="El DNI solo debe contener 8 números."
                                ValidationExpression="^\d{8}$"
                                CssClass="text-danger small"
                                Display="Dynamic" />
                        </div>
                    </div>

                    <!-- CMP -->
                    <div class="col-4">
                        <asp:Label ID="lblCMP" runat="server"
                            Text="CMP:" CssClass="col-form-label fw-medium pe-2" />
                        <asp:TextBox ID="txtCMP" CssClass="form-control w-75" runat="server" />
                        <div style="min-height: 1.25rem;"></div>
                        <!-- Para mantener alineado con los otros -->
                    </div>
                </div>

                <div class="mb-3 row align-items-center">
                    <div class="col-6">
                        <asp:Label ID="lblNombre" CssClass="col-form-label fw-medium pe-2" runat="server" Text="Nombres:"></asp:Label>
                        <asp:TextBox ID="txtNombre" CssClass="form-control" runat="server"></asp:TextBox>
                        <div style="min-height: 1.25rem;">
                            <asp:RegularExpressionValidator
                                ID="revNombres" runat="server" ControlToValidate="txtNombre"
                                ErrorMessage="Solo se permiten letras y espacios."
                                ValidationExpression="^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]{2,50}$"
                                CssClass="text-danger small"
                                Display="Dynamic" />
                        </div>
                    </div>
                    <div class="col-3">
                        <asp:Label ID="lblPaterno" CssClass="col-form-label fw-medium pe-2" runat="server" Text="Apellido Paterno:"></asp:Label>
                        <asp:TextBox ID="txtPaterno" CssClass="form-control" runat="server"></asp:TextBox>
                        <div style="min-height: 1.25rem;">
                            <asp:RegularExpressionValidator
                                ID="revPaterno" runat="server" ControlToValidate="txtPaterno"
                                ErrorMessage="Solo se permiten letras y espacios."
                                ValidationExpression="^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]{2,50}$"
                                CssClass="text-danger small"
                                Display="Dynamic" />
                        </div>
                    </div>
                    <div class="col-3">
                        <asp:Label ID="lblMaterno" CssClass="col-form-label fw-medium pe-2" runat="server" Text="Apellido Materno:"></asp:Label>
                        <asp:TextBox ID="txtMaterno" CssClass="form-control" runat="server"></asp:TextBox>
                        <div style="min-height: 1.25rem;">
                            <asp:RegularExpressionValidator
                                ID="revMaterno" runat="server" ControlToValidate="txtMaterno"
                                ErrorMessage="Solo se permiten letras y espacios."
                                ValidationExpression="^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]{2,50}$"
                                CssClass="text-danger small"
                                Display="Dynamic" />
                        </div>
                    </div>
                </div>

                <div class="mb-3 row align-items-start">
                    <div class="col-4">
                        <asp:Label ID="lblFechaNacimiento" CssClass="col-form-label fw-medium pe-2" runat="server" Text="Fecha de nacimiento:"></asp:Label>
                        <input id="dtpFechaNacimiento" runat="server" class="form-control" type="date" />
                    </div>
                    <div class="col-3">
                        <asp:Label ID="lblGenero" runat="server"
                            Text="Género:" CssClass="col-form-label fw-medium pe-2" />
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
                    <div class="col-5">
                        <asp:Label ID="lblEmail" CssClass="col-form-label fw-medium pe-2" runat="server" Text="E-mail:"></asp:Label>
                        <asp:TextBox ID="txtEmail" CssClass="form-control" runat="server"></asp:TextBox>
                    </div>
                </div>
                <!--  Sede + Consultorio + Especialidad -->
                <div class="mb-3 row align-items-center">

                    <!-- Sede -->
                    <div class="col-4">
                        <asp:Label ID="lblSede" runat="server"
                            Text="Sede:" CssClass="col-form-label fw-medium pe-2" />
                        <asp:DropDownList ID="ddlSede" runat="server" CssClass="form-select" AutoPostBack="true"
                            OnSelectedIndexChanged="ddlSede_SelectedIndexChanged" />
                    </div>
                    <!-- Consultorio -->
                    <div class="col-4">
                        <asp:Label ID="lblConsultorio" runat="server"
                            Text="Consultorio:" CssClass="col-form-label fw-medium pe-2" />
                        <asp:DropDownList ID="ddlConsultorio" runat="server" CssClass="form-select" />
                    </div>
                    <!-- Especialidad -->
                    <div class="col-4">
                        <asp:Label ID="lblEspecialidad" runat="server"
                            Text="Especialidad:" CssClass="col-form-label fw-medium pe-2" />
                        <asp:DropDownList ID="ddlEspecialidad" runat="server" CssClass="form-select" />
                    </div>
                </div>

            </div>
            <div class="card-footer clearfix">
                <asp:LinkButton ID="btnRegresar" runat="server" CausesValidation="false" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="btnRegresar_Click" />
                <asp:LinkButton ID="btnGuardar" CssClass="float-end btn btn-success" runat="server" Text="<i class='fa-solid fa-floppy-disk pe-2'></i> Guardar" OnClick="btnGuardar_Click" />
            </div>
        </div>
    </div>
</asp:Content>
