<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasPaciente.Master" AutoEventWireup="true" CodeBehind="ListarMedicosPaciente.aspx.cs" Inherits="PazCitasWA.ListarMedicosPaciente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Encuentra tu Médico
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .hero-search-section {
            background-color: #e0f2fe; /* Light blue background like in the image */
            padding: 40px 20px;
            text-align: center;
        }

            .hero-search-section .main-title {
                font-size: 2.5rem;
                font-weight: bold;
                color: #1e3a8a; /* Dark blue */
                margin-bottom: 0.5rem;
            }

            .hero-search-section .highlight-title {
                color: #2563eb; /* Brighter blue for highlight */
            }

        .search-bar-container {
            max-width: 600px;
            margin: 20px auto 0;
            position: relative;
        }

            .search-bar-container .form-control {
                border-radius: 30px;
                padding: 12px 20px 12px 45px; /* Space for icon */
                font-size: 1rem;
                box-shadow: 0 4px 15px rgba(0,0,0,0.1);
                border: 1px solid #ced4da;
            }

            .search-bar-container .search-icon {
                position: absolute;
                left: 15px;
                top: 50%;
                transform: translateY(-50%);
                color: #6c757d;
                font-size: 1.1rem;
            }

            .search-bar-container .btn-search-action {
                border-radius: 30px;
                padding: 12px 20px;
            }


        .doctors-grid-container {
            padding: 30px 15px; /* Add padding around the grid */
        }

        .doctor-card {
            background-color: #fff;
            border: 1px solid #e0e0e0;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
            text-align: left;
            height: 100%; /* For d-flex to work well for equal height columns */
            display: flex;
            flex-direction: column;
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
        }

            .doctor-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
            }

            .doctor-card .card-img-top-wrapper {
                height: 240px; /* Fixed height for the image area */
                overflow: hidden; /* Ensure image doesn't overflow rounded corners */
                border-top-left-radius: 15px;
                border-top-right-radius: 15px;
                background-color: #f0f4f7; /* Placeholder background */
            }

            .doctor-card .card-img-top {
                width: 100%;
                height: 100%;
                object-fit: cover; /* Cover the area, might crop */
            }

            .doctor-card .doctor-placeholder-icon {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100%;
                font-size: 5rem;
                color: #adb5bd;
            }

            .doctor-card .card-body {
                padding: 1rem;
                flex-grow: 1;
                display: flex;
                flex-direction: column;
            }

            .doctor-card .specialty-tag {
                font-size: 0.75rem;
                padding: 0.25rem 0.6rem;
                background-color: #e7f3ff;
                color: #007bff;
                border-radius: 20px;
                display: inline-block;
                margin-bottom: 0.5rem;
            }

            .doctor-card .card-title {
                font-size: 1.1rem;
                font-weight: 600;
                margin-bottom: 0.25rem;
                color: #333;
            }

            .doctor-card .cmp-text {
                font-size: 0.85rem;
                color: #6c757d;
                margin-bottom: 1rem;
            }

            .doctor-card .btn-know-doctor {
                color: #007bff;
                font-weight: 500;
                text-decoration: none;
                margin-top: auto; /* Pushes button to bottom if card body has extra space */
                align-self: flex-start;
            }

                .doctor-card .btn-know-doctor:hover {
                    text-decoration: underline;
                }

                .doctor-card .btn-know-doctor .arrow-icon {
                    margin-left: 5px;
                    transition: transform 0.2s ease-in-out;
                }

                .doctor-card .btn-know-doctor:hover .arrow-icon {
                    transform: translateX(3px);
                }

        .no-doctors-message {
            text-align: center;
            padding: 40px 20px;
            font-size: 1.2rem;
            color: #6c757d;
        }
        .doctor-img {
            max-height: 200px;
            object-fit: cover;
            width: 100%;
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="hero-search-section">
        <h1 class="main-title">¡Hola!, Encuentra aquí a tu médico y <span class="highlight-title">reserva tu cita</span></h1>
        <div class="search-bar-container">
            <i class="fas fa-search search-icon"></i>
            <asp:TextBox ID="txtBusqueda" runat="server" CssClass="form-control" placeholder="Busca a tu médico aquí o escribe la especialidad"></asp:TextBox>
            <%-- El botón es opcional si se quiere búsqueda en cada keypress (con AutoPostBack y OnTextChanged)
                 o un botón explícito. Aquí pongo un botón. --%>
            <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="btn btn-primary mt-2 d-none" OnClick="btnBuscar_Click" />
            <%-- AutoPostBack on TextChanged is generally preferred for live search feeling --%>
        </div>
    </div>

    <div class="container doctors-grid-container">
        <asp:Repeater ID="rptMedicos" runat="server">
            <HeaderTemplate>
                <div class="row g-4">
            </HeaderTemplate>
            <ItemTemplate>
                <div class="col-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="doctor-card-wrapper">
                        <div class="card doctor-card">
                            <asp:PlaceHolder ID="phDoctorImage" runat="server">
                                <img src="/images/genericDoctor.png" class="img-fluid doctor-img" />
                                <%--<asp:Image ID="imgDoctor" runat="server" ImageUrl='<%# Eval("UrlFotoMedico") %>' CssClass="card-img-top" AlternateText='<%# "Foto de " + Eval("NombreCompletoMedico") %>' />--%>
                            </asp:PlaceHolder>
                            <asp:PlaceHolder ID="phDoctorPlaceholder" runat="server" Visible="false">
                                <div class="doctor-placeholder">
                                    <i class="fas fa-user-md"></i>
                                </div>
                            </asp:PlaceHolder>

                            <div class="card-body">
                                <div>
                                    <span class="specialty-tag"><%# Eval("sede.nombre") %></span>
                                    <h5 class="card-title"><%# Eval("nombre") %></h5>
                                    <p class="cmp-text">CMP: <%# Eval("codigoMedico") %></p>
                                </div>
                                <%--<a href='<%# Eval("UrlPerfilMedico", "PerfilMedico.aspx?idMedico={0}") %>' class="btn-know-doctor">--%>
                                <a href='#' class="btn-know-doctor">Conoce al médico <i class="fas fa-arrow-right arrow-icon"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </ItemTemplate>
            <FooterTemplate>
                </div>
            </FooterTemplate>
        </asp:Repeater>
        <asp:Panel ID="pnlErrorGeneral" runat="server" Visible="false" CssClass="alert alert-danger mt-3 text-center">
            Ocurrió un error al cargar los médicos. Por favor, intenta más tarde.
        </asp:Panel>
    </div>
    <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger" Visible="false"></asp:Label>

</asp:Content>
