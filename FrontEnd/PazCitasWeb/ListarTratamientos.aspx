<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="ListarTratamientos.aspx.cs" Inherits="PazCitasWA.ListarTratamientos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Listar Medicamentos
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
     <style>
        .stock-box {
            display: inline-block;
            width: 100%;
            padding: 10px;
            text-align: center;
            font-weight: bold;
            border-radius: 5px;
            min-width: 50px;  /* Establece un tamaño mínimo para que el texto esté centrado */
            color: white;
        }
        .bg-danger {
            background-color: #dc3545 !important;
            color: white;
        }
        .bg-warning {
            background-color: #ffc107 !important;
            color: white;
        }
        .bg-success {
            background-color: #28a745 !important;
            color: white;
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container">
        <div class="row align-items-center">
            <div class="row align-items-center mb-3">
                <div class="col d-flex justify-content-between align-items-center">
                    <h5 class="fw-bold m-0">Medicamentos Disponibles</h5>
                    <asp:LinkButton ID="btnRegistrar" CssClass="btn btn-success"
                        runat="server" OnClick="btnRegistrar_Click"
                        Text="Registrar Tratamiento <i class='fa-solid fa-square-plus ms-1'></i>" />
                </div>
            </div>
        </div>
        <div class="container row">
            <asp:GridView ID="gvTratamientos" runat="server"
                CssClass="table table-hover table-striped table-bordered" OnRowDataBound="gvTratamientos_RowDataBound"
                AutoGenerateColumns="false" PageSize="7" AllowPaging="true" OnPageIndexChanging="gvTratamientos_PageIndexChanging"
                PagerStyle-CssClass="pagination-container text-center" PagerStyle-HorizontalAlign="Center"
                PagerSettings-Mode="Numeric" PagerSettings-Position="Bottom">
                <Columns>
                    <asp:BoundField HeaderText="ID" DataField="idMedicamento"
                        HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-Width="10%" ItemStyle-CssClass="align-middle" />
                    <asp:BoundField HeaderText="Nombre" DataField="nombre"
                        HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-Width="25%" ItemStyle-CssClass="align-middle" />
                    <asp:BoundField HeaderText="Presentación" DataField="presentacion"
                        HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-Width="30%" ItemStyle-CssClass="text-wrap align-middle" />
                    <asp:BoundField HeaderText="Stock" DataField="stock"
                        HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-Width="20%" ItemStyle-CssClass="text-wrap align-middle" />

                    <asp:TemplateField HeaderText="Acciones" HeaderStyle-CssClass="bg-success text-white align-middle"
                        ItemStyle-CssClass="align-middle">
                        <ItemTemplate>
                            <div class="d-flex gap-2">
                                <asp:LinkButton runat="server" ID="btnModificar" CssClass="btn btn-success btn-sm btn-modificar align-items-center"
                                    Text="<i class='fa-solid fa-pen me-1'></i> Modificar"
                                    OnClick="btnModificar_Click"
                                    CommandArgument='<%# Eval("idMedicamento") %>' />

                            </div>
                        </ItemTemplate>
                        <ItemStyle Width="15%" />
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>
