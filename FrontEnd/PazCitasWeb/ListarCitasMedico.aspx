<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasMedico.Master" AutoEventWireup="true" CodeBehind="ListarCitasMedico.aspx.cs" Inherits="PazCitasWA.ListarCitasMedico" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">

    <div class="container">
        <div class="container row">
            <div class="table-responsive">
                <asp:GridView ID="gvCitasMed" runat="server" CssClass="table table-hover table-responsive table-striped"
                    AutoGenerateColumns="false" PageSize="7" AllowPaging="true"
                    OnPageIndexChanging="gvCitasMed_PageIndexChanging" OnRowDataBound="gvCitasMed_RowDataBound">
                    <Columns>
                        <asp:BoundField HeaderText="Nombre Paciente" />
                        <asp:BoundField HeaderText="Fecha Cita" />
                        <%--<asp:BoundField HeaderText="Hora inicio" />--%>
                        <asp:BoundField HeaderText="Estado Cita" />
                        <asp:BoundField HeaderText="Estado Paciente" />

                        <asp:TemplateField>
                            <ItemTemplate>
                                <asp:LinkButton
                                    runat="server"
                                    Text="Ver detalle"
                                    OnClick="BtonSeleccionar_Click"
                                    CommandArgument='<%# Eval("IdCita") %>'
                                    CssClass="btn btn-outline-primary btn-sm" />
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>

            <div class="form-group mt-2">
                <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger text-center d-block" EnableViewState="false"></asp:Label>
            </div>
        </div>
    </div>

</asp:Content>
