<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="HistorialesMedicos.aspx.cs" Inherits="PazCitasWA.HistorialesMedicos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Historiales Médicos
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        /* Card contenedor y header */
        .card-doctores .card-header {
            background: linear-gradient(135deg, #3add5f 0%, #10ab33 100%);
            color: white;
        }

            .card-doctores .card-header h5 {
                font-size: 1rem;
                margin: 0;
            }

        /* Tabla */
        #gvHistoriales {
            font-size: 0.85rem;
        }

            #gvHistoriales th,
            #gvHistoriales td {
                vertical-align: middle;
                padding: 0.5rem;
            }

            #gvHistoriales thead th {
                background-color: #19692c;
                color: #fff;
                border-bottom: none;
            }

            #gvHistoriales.table-striped > tbody > tr:nth-of-type(odd) > * {
                background-color: #f7fdf7;
            }

            #gvHistoriales.table-hover tbody tr:hover > * {
                background-color: #e2f8e2;
            }

        /* Paginador */
        .pagination-container a,
        .pagination-container span {
            padding: 0.25rem 0.5rem;
            font-size: 0.8rem;
        }

        .Center {
            text-align: center;
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container-fluid">
        <!-- Card que engloba la tabla -->
        <div class="card card-doctores shadow-sm mb-4">
            <!-- Header verde -->
            <div class="card-header d-flex justify-content-between align-items-center">
                <h4 class="mb-0 fw-bold">
                    <i class="fa-solid fa-user-doctor me-2"></i>
                    Historias Clínicas
                </h4>
            </div>
            <!-- Cuerpo de la card -->
            <div class="card-body p-3">
                <asp:GridView ID="gvHistoriales" runat="server" AutoGenerateColumns="false"
                    CssClass="table table-hover table-striped table-bordered"
                    OnRowDataBound="gvHistoriales_RowDataBound"
                    AllowPaging="true" PageSize="7" OnPageIndexChanging="gvHistoriales_PageIndexChanging"
                    PagerStyle-CssClass="pagination-container text-center"
                    PagerStyle-HorizontalAlign="Center">
                    <Columns>
                        <asp:BoundField HeaderText="Nº Historia Clínica"
                            HeaderStyle-CssClass="bg-success text-white align-middle"
                            ItemStyle-Width="20%" ItemStyle-CssClass="align-middle" />
                        <asp:BoundField HeaderText="Paciente"
                            HeaderStyle-CssClass="bg-success text-white align-middle"
                            ItemStyle-Width="30%" ItemStyle-CssClass="align-middle" />
                        <asp:BoundField HeaderText="DNI"
                            HeaderStyle-CssClass="bg-success text-white align-middle"
                            ItemStyle-Width="10%" ItemStyle-CssClass="text-wrap align-middle" />
                        <asp:BoundField HeaderText="Fecha Actualizacion"
                            HeaderStyle-CssClass="bg-success text-white align-middle"
                            ItemStyle-Width="20%" ItemStyle-CssClass="text-wrap align-middle" />

                        <asp:TemplateField HeaderText="Acciones" HeaderStyle-CssClass="bg-success text-white align-middle"
                            ItemStyle-CssClass="Center">
                            <ItemTemplate>
                                <div class="d-flex gap-2">
                                    <asp:LinkButton ID="btnVer" runat="server" CssClass="btn btn-primary btn-sm"
                                        Text="<i class='fa-solid fa-eye me-2'></i> Ver a Detalle"
                                        OnClick="btnVer_Click"
                                        CommandArgument='<%# Eval("idHistorial") %>' />
                                </div>
                            </ItemTemplate>
                            <ItemStyle Width="20%" />
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>
        </div>
    </div>
</asp:Content>
