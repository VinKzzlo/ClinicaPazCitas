<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="Reportes.aspx.cs" Inherits="PazCitasWA.Reportes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Reportes
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
    <div class="construction-message">
        <div class="construction-icon">
            🛠️
        </div>
        <h1>¡Página en Construcción!</h1>
        <p>Estamos trabajando en esta sección. ¡Pronto estará disponible!</p>
    </div>
</asp:Content>
