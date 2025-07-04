<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasPaciente.Master" AutoEventWireup="true" CodeBehind="ListarSedesPaciente.aspx.cs" Inherits="PazCitasWA.ListarSedesPaciente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Nuestras Sedes
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        :root {
            --sede-amarillo-base: #FFC300;
            --sede-amarillo-oscuro: #E6B000;
            --sede-amarillo-claro: #FFD54F;
            --sede-amarillo-muy-claro: #FFF8E1;
            --sede-texto-principal: #424242; /* Para buen contraste sobre amarillo */
            --sede-texto-secundario: #757575;
            --sede-fondo-pagina: #fefdf9; 
        }


        .sede-header {
            margin-bottom: 2.5rem;
            text-align: center; /* Centrar el título principal */
        }

            .sede-header h1.main-title {
                font-size: 2.8rem;
                font-weight: bold;
                color: var(--sede-texto-principal);
            }

            .sede-header h1.highlight-title {
                font-size: 2.8rem;
                font-weight: bold;
                color: var(--sede-amarillo-oscuro); /* Usar el amarillo oscuro para destacar */
                background: linear-gradient(90deg, var(--sede-amarillo-oscuro), var(--sede-amarillo-base));
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                background-clip: text;
                text-fill-color: transparent;
                display: inline-block; 
            }

            .sede-header p.subtitle-text {
                font-size: 1.5rem;
                color: var(--sede-texto-secundario);
                margin-top: 0.5rem;
            }

        .sede-card {
            background: linear-gradient(145deg, #ffffff, #ffffff); 
            border: 1px solid #FFE082; 
            border-radius: 15px;
            box-shadow: 0 6px 20px rgba(255, 195, 0, 0.15);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            height: 100%;
            display: flex;
            flex-direction: column;
            padding: 1.5rem;
            text-align: center;
        }

            .sede-card:hover {
                transform: translateY(-7px);
                box-shadow: 0 10px 30px rgba(255, 195, 0, 0.25);
            }

        .icon-circle-sede {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background: linear-gradient(135deg, var(--sede-amarillo-claro), var(--sede-amarillo-base));
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 1rem;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .sede-icon {
            font-size: 2.5rem; /* Tamaño del icono de Font Awesome */
            color: var(--sede-texto-principal); /* Icono oscuro sobre fondo amarillo */
        }

        .sede-card .card-title {
            font-size: 1.2rem;
            font-weight: 600;
            color: var(--sede-texto-principal);
            margin-top: 0.5rem;
            margin-bottom: 0.5rem; /* Espacio antes de la dirección */
        }

        .sede-card .card-address {
            font-size: 0.9rem;
            color: var(--sede-texto-secundario);
            line-height: 1.4;
            min-height: 50px; /* Espacio para 2-3 líneas de dirección */
        }


        /* Elemento decorativo opcional (ej. ondas de fondo) */
        .decorative-waves {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 150px; /* Altura de las ondas */
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1440 320'%3E%3Cpath fill='%23FFF8E1' fill-opacity='0.7' d='M0,192L48,176C96,160,192,128,288,133.3C384,139,480,181,576,186.7C672,192,768,160,864,138.7C960,117,1056,107,1152,117.3C1248,128,1344,160,1392,176L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z'%3E%3C/path%3E%3C/svg%3E");
            background-repeat: no-repeat;
            background-size: cover;
            z-index: 0; /* Detrás del contenido */
            opacity: 0.8;
        }

        @media (max-width: 768px) {
            .sede-header h1.main-title,
            .sede-header h1.highlight-title {
                font-size: 2.2rem;
            }

            .sede-header p.subtitle-text {
                font-size: 1.2rem;
            }
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container mt-4 position-relative pb-5">

        <div class="sede-header position-relative" style="z-index: 1;">
            <h1 class="main-title">Encuentra Nuestras</h1>
            <h1 class="highlight-title">Sedes Disponibles</h1>
            <p class="subtitle-text">Estamos cerca para cuidarte.</p>
        </div>

        <asp:Repeater ID="rptSedes" runat="server">
            <HeaderTemplate>
                <div class="row g-4 position-relative" style="z-index: 1;">
            </HeaderTemplate>
            <ItemTemplate>
                <div class="col-lg-4 col-md-6 col-sm-6 col-12 mb-4 d-flex">
                    <a href='#' class="text-decoration-none w-100">
                        <div class="card sede-card">
                            <div class="icon-circle-sede mx-auto">
                                <i class="fas fa-hospital-alt sede-icon"></i>
                            </div>
                            <h5 class="card-title"><%# Eval("nombre") %></h5>
                            <p class="card-address"><%# Eval("direccion") %></p>
                        </div>
                    </a>
                </div>
            </ItemTemplate>
            <FooterTemplate>
                </div>
            </FooterTemplate>
        </asp:Repeater>

        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger mt-3 d-block text-center" Visible="false"></asp:Label>
    </div>
</asp:Content>
