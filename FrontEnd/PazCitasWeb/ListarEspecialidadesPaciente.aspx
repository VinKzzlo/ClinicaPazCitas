<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasPaciente.Master" AutoEventWireup="true" CodeBehind="ListarEspecialidadesPaciente.aspx.cs" Inherits="PazCitasWA.ListarEspecialidades_pacientes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Nuestras Especialidades
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <%-- Puedes agregar scripts específicos para esta página aquí si es necesario --%>
    <style>
        .specialty-header {
            margin-bottom: 2.5rem; /* Espacio debajo del título principal */
        }

            .specialty-header h1.main-title {
                font-size: 2.8rem; /* Tamaño grande para el título */
                font-weight: bold;
                color: #2c3e50; /* Color oscuro para el texto principal */
            }

            .specialty-header h1.highlight-title,
            .specialty-header p.priority-text {
                font-size: 2.8rem;
                font-weight: bold;
                color: #007bff; /* Azul destacado, ajusta según tu paleta */
            }

            .specialty-header p.priority-text {
                font-size: 1.8rem; /* Un poco más pequeño para el subtítulo */
                margin-top: -10px; /* Ajuste para acercarlo al título */
            }

        .specialty-card {
            background-color: #fff;
            border: none; /* Sin borde visible por defecto */
            border-radius: 15px; /* Bordes redondeados como en la imagen */
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08); /* Sombra sutil */
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            height: 100%; /* Para que todas las tarjetas en una fila tengan la misma altura */
            display: flex;
            flex-direction: column;
            justify-content: center; /* Centra el contenido verticalmente */
            padding: 1.5rem; /* Espaciado interno */
        }

            .specialty-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 25px rgba(0, 0, 0, 0.12);
            }

        .icon-circle {
            width: 80px; /* Tamaño del círculo contenedor del icono */
            height: 80px;
            border-radius: 50%;
            background-color: #e7f3ff; /* Fondo azul claro para el círculo */
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 1rem; /* Espacio debajo del icono */
        }

        .specialty-icon {
            max-width: 45px; /* Tamaño máximo del icono dentro del círculo */
            max-height: 45px;
            /* Para colorear un PNG/SVG genérico a azul (si no son ya azules): */
            /* filter: invert(38%) sepia(99%) saturate(2555%) hue-rotate(206deg) brightness(99%) contrast(101%); */
        }

        .specialty-card .card-title {
            font-size: 1.1rem;
            font-weight: 600;
            color: #343a40;
            margin-top: 0.5rem;
        }

        /* Opcional: Estrella decorativa como en la imagen */
        .decorative-star {
            position: absolute;
            top: 20px; /* Ajusta según necesites */
            right: 50px; /* Ajusta según necesites */
            font-size: 5rem; /* Tamaño de la estrella */
            color: #007bff; /* Color de la estrella */
            z-index: 0; /* Detrás del contenido principal si se superpone */
            opacity: 0.8;
        }

        @media (max-width: 768px) {
            .decorative-star {
                display: none; /* Ocultar en pantallas pequeñas */
            }

            .specialty-header h1.main-title,
            .specialty-header h1.highlight-title {
                font-size: 2rem;
            }

            .specialty-header p.priority-text {
                font-size: 1.3rem;
            }
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">


    <div class="container mt-4 position-relative">
        <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%23007bff'%3E%3Cpath d='M12 17.27L18.18 21l-1.64-7.03L22 9.24l-7.19-.61L12 2 9.19 8.63 2 9.24l5.46 4.73L5.82 21z'/%3E%3C/svg%3E"
            alt="star" class="decorative-star" style="width: 80px; height: 80px;" />

        <div class="text-start text-md-left specialty-header">
            <h1 class="main-title">Conoce todas nuestras</h1>
            <h1 class="highlight-title">especialidades médicas.</h1>
            <p class="priority-text">Tu salud es nuestra prioridad</p>
        </div>

        <asp:Repeater ID="rptEspecialidades" runat="server">
            <headertemplate>
                <div class="row g-4">
            </headertemplate>

            <itemtemplate>
                <div class="col-lg-3 col-md-4 col-sm-6 col-12 mb-4">
                    <a href='DetalleEspecialidadPaciente.aspx?id=<%# Eval("idEspecialidad") %>' class="text-decoration-none">
                        <div class="card specialty-card text-center">
                            <div class="mx-auto icon-circle">
                                <i class="fa-solid fa-truck-medical"></i>
                            </div>
                            <h5 class="card-title"><%# Eval("nombre") %></h5>
                        </div>
                    </a>
                </div>
            </itemtemplate>

            <footertemplate>
                </div>
            </footertemplate>

        </asp:Repeater>

        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger" Visible="false"></asp:Label>
    </div>

</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="cph_PageTitle" runat="server">
</asp:Content>