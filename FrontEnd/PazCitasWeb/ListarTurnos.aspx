<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasMedico.Master" AutoEventWireup="true" CodeBehind="ListarTurnos.aspx.cs" Inherits="PazCitasWA.ListarTurnos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .construction-message {
            text-align: center;
            font-size: 30px;
            color: #333;
            margin-top: 150px;
            font-family: Arial, sans-serif;
        }

            .construction-message h1 {
                font-size: 50px;
                color: #FF6347; /* Tomato color */
            }

            .construction-message p {
                font-size: 20px;
                color: #888;
            }

        .construction-icon {
            font-size: 100px;
            color: #FF6347;
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <%--<!--LISTADO DE 'TURNOS'-->
    <div class="container row">
        <asp:GridView ID="dgvMedicos" runat="server" AutoGenerateColumns="true"
            OnPageIndexChanging="dgvMedicos_PageIndexChanging" PageSize="5"
            CssClass="table table-hover table-responsive table-striped">
            <columns>
                <asp:BoundField HeaderText="Dia" ItemStyle-CssClass="align-middle" />
                <asp:BoundField HeaderText="Hora Inicio" ItemStyle-CssClass="align-middle" />
                <asp:BoundField HeaderText="Hora Fin" ItemStyle-CssClass="align-middle" />
                <asp:TemplateField>
                    <itemtemplate>
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i> Listar Disponibilidad" CssClass="btn btn-danger" OnClick="ListarDisponibilidad_Click"/>
                    </itemtemplate>
                </asp:TemplateField>
            </columns>
        </asp:GridView>
    </div>--%>

    <div class="construction-message">
        <div class="construction-icon">
            🛠️
        </div>
        <h1>¡Página en Construcción!</h1>
        <p>Estamos trabajando en esta sección. ¡Pronto estará disponible!</p>
    </div>

</asp:Content>
