<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="ListarEspecialidades.aspx.cs" Inherits="PazCitasWA.ListarEspecialidades" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Listar Especialidades
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        /* ---------------- Tabla de Especialidades ---------------- */

        /* Cabecera verde */
        #gvEspecialidades thead tr {
            background: #198754;
            color: #fff;
        }

        /* Zebra: filas impares en verde muy claro */
        #gvEspecialidades.table-striped > tbody > tr:nth-of-type(odd) > * {
            background-color: #deffef !important;
        }

        /* Hover: tono un poco más intenso */
        #gvEspecialidades.table-hover tbody tr:hover > * {
            background-color: #b4dac3 !important;
        }

        /* Columna ID destacada (opcional) */
        #gvEspecialidades tbody td:first-child {
            font-weight: 600;
            text-align: center;
            background: #f1f8f4;
        }

        /* -------- Botón “Modificar” solo dentro del GridView -------- */
        #gvEspecialidades .btn-success {
            background: #49d052;
            border-color: #49d052;
            color: #fff;
        }

            #gvEspecialidades .btn-success:hover,
            #gvEspecialidades .btn-success:focus {
                background: #1c9024;
                border-color: #1c9024;
                color: #fff;
            }

        /* ---------------- Paginador ---------------- */
        .pagination-container table {
            margin: auto;
        }

        .pagination-container td {
            padding: 0.25rem 0.5rem;
        }

        .pagination-container a,
        .pagination-container span {
            display: inline-block;
            padding: 0.375rem 0.75rem;
            margin: 0 0.2rem;
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
            color: #198754;
            text-decoration: none;
        }

        .pagination-container span {
            background-color: #198754;
            color: #fff;
            font-weight: bold;
        }
        /* Solo Modificar */
        .btn-modificar {
            background: #18c35a;
            border-color: #18c35a;
            color: #fff;
        }

            .btn-modificar:hover,
            .btn-modificar:focus {
                background: #1aa85a; /* un tono más oscuro */
                border-color: #1aa85a;
            }
    </style>

</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container">
        <div class="row align-items-center">
            <div class="row align-items-center mb-3">
                <div class="col d-flex justify-content-between align-items-center">
                    <h5 class="fw-bold m-0">Especialidades</h5>
                    <asp:LinkButton ID="btnRegistrar" CssClass="btn btn-success"
                        runat="server" OnClick="btnRegistrar_Click"
                        Text="Registrar Especialidad <i class='fa-solid fa-square-plus ms-1'></i>" />
                </div>
            </div>
        </div>
        <div class="container row">
            <asp:GridView ID="gvEspecialidades" runat="server"
                CssClass="table table-hover table-striped table-bordered"
                AutoGenerateColumns="false" PageSize="7" AllowPaging="true" OnPageIndexChanging="gvEspecialidades_PageIndexChanging"
                PagerStyle-CssClass="pagination-container text-center" PagerStyle-HorizontalAlign="Center"
                PagerSettings-Mode="Numeric" PagerSettings-Position="Bottom">
                <Columns>
                    <asp:BoundField HeaderText="ID" DataField="idEspecialidad"
                        HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-Width="10%" ItemStyle-CssClass="align-middle" />
                    <asp:BoundField HeaderText="Nombre" DataField="nombre"
                        HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-Width="20%" ItemStyle-CssClass="align-middle" />
                    <asp:BoundField HeaderText="Descripción" DataField="descripcion"
                        HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-Width="50%" ItemStyle-CssClass="text-wrap align-middle" />

                    <asp:TemplateField HeaderText="Acciones" HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-CssClass="align-middle">
                        <ItemTemplate>
                            <div class="d-flex gap-2">
                                <asp:LinkButton runat="server" ID="btnModificar" CssClass="btn btn-success btn-sm btn-modificar"
                                    Text="<i class='fa-solid fa-pen me-1'></i> Modificar"
                                    OnClick="btnModificar_Click"
                                    CommandArgument='<%# Eval("idEspecialidad") %>' />

                                <asp:LinkButton runat="server" ID="btnEliminar" CssClass="btn btn-danger btn-sm"
                                    Text="<i class='fa-solid fa-trash me-1'></i> Eliminar"
                                    OnClick="btnEliminar_Click"
                                    CommandArgument='<%# Eval("idEspecialidad") %>' />
                            </div>
                        </ItemTemplate>
                        <ItemStyle Width="20%" />
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>
