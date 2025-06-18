<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="RegistrarAdministrador.aspx.cs" Inherits="PazCitasWA.RegistrarAdministrador" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Registrar Administrador
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
                    <div class="col-6">
                        <!-- Id Área -->
                        <asp:Label ID="lblIDUsuario" runat="server"
                            Text="Id Administrador:" CssClass="col-form-label fw-medium pe-2" />
                        <asp:TextBox ID="txtIDUsuario" CssClass="form-control w-75" Enabled="false" runat="server" />
                        <div style="min-height: 1.25rem;"></div>
                    </div>
                    <div class="col-6">
                        <!-- DNI -->
                        <asp:Label ID="lblDNI" runat="server"
                            Text="DNI:" CssClass="col-form-label fw-medium pe-2" />
                        <asp:TextBox ID="txtDNI" CssClass="form-control" runat="server" MaxLength="8" />
                        <div style="min-height: 1.25rem;">
                            <asp:RegularExpressionValidator
                                ID="revDNI" runat="server" ControlToValidate="txtDNI"
                                ErrorMessage="El DNI solo debe contener 8 números."
                                ValidationExpression="^\d{8}$"
                                CssClass="text-danger small"
                                Display="Dynamic" />
                        </div>
                    </div>
                </div>

                <!-- NOMBRE + APELLIDOS -->
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
                        <div style="min-height: 1.25rem;">
                            <asp:RangeValidator
                                ID="rvFechaNacimiento"
                                runat="server"
                                ControlToValidate="dtpFechaNacimiento"
                                MinimumValue="1940-01-01"
                                Type="Date"
                                ErrorMessage="La fecha debe estar entre 01/01/1940 y hoy."
                                CssClass="text-danger small"
                                Display="Dynamic" />
                        </div>
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
                
                <!-- Cuenta de Usuario -->
                <div class="mb-3 row align-items-center">
                    <div class="col-6">
                        <asp:Label ID="lblUsername" CssClass="col-form-label fw-medium pe-2" runat="server" Text="Nombre de usuario:"></asp:Label>
                        <asp:TextBox ID="txtUsername" CssClass="form-control" runat="server"></asp:TextBox>
                        <div style="min-height: 1.25rem;">
                            <asp:RegularExpressionValidator
                                ID="revUsuario"
                                runat="server"
                                ControlToValidate="txtUsername"
                                ValidationExpression="^[a-zA-Z0-9_]{5,15}$"
                                ForeColor="Red"
                                ErrorMessage="El nombre de usuario debe contener entre 5 y 15 caracteres, y solo puede tener letras, números y guiones bajos."></asp:RegularExpressionValidator>
                        </div>
                    </div>
                    <div class="col-6">
                        <asp:Label ID="lblPassword" CssClass="col-form-label fw-medium pe-2" runat="server" Text="Contraseña:"></asp:Label>
                        <asp:TextBox ID="txtPassword" CssClass="form-control" runat="server"></asp:TextBox>
                        <div style="min-height: 1.25rem;">
                            <asp:RegularExpressionValidator
                                ID="revPassword"
                                runat="server"
                                ControlToValidate="txtPassword"
                                ValidationExpression="^[A-Za-z0-9]{8,}$"
                                ForeColor="Red"
                                ErrorMessage="La contraseña debe tener al menos 8 caracteres y solo permitir letras y números."></asp:RegularExpressionValidator>
                        </div>
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
