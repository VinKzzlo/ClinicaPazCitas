<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasPaciente.Master" AutoEventWireup="true" CodeBehind="DetalleEspecialidadPaciente.aspx.cs" Inherits="PazCitasWA.DetalleEspecialidadPaciente" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
     <asp:Literal ID="litPageTitle" runat="server">Detalle Especialidad</asp:Literal>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .specialty-detail-header {
            margin-bottom: 3rem;
        }

        .specialty-image-container {
            position: relative;
            text-align: center; /* Centra la imagen si es más pequeña que el contenedor */
        }

        .specialty-main-image {
            max-width: 100%;
            height: auto;
            border-radius: 150px 150px 150px 150px / 250px 250px 150px 150px; /* Forma ovalada/pill más pronunciada en la parte superior */
            object-fit: cover; /* Asegura que la imagen cubra el área sin distorsionarse */
            max-height: 400px; /* Limita la altura de la imagen */
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }

        /* Para el texto curvo, sería más complejo y podría requerir SVG o JS. 
           Una simplificación es ponerlo como parte de la imagen o texto plano.
           Aquí lo omitimos para enfocarnos en la estructura. */

        .doctors-section-title {
            font-weight: bold;
            margin-bottom: 1.5rem;
            margin-top: 2rem;
            font-size: 1.8rem;
        }

        .doctor-card-container {
            overflow-x: auto; /* Permite scroll horizontal */
            white-space: nowrap; /* Evita que las tarjetas salten a la siguiente línea */
            padding-bottom: 15px; /* Espacio para la barra de scroll si aparece */
            margin-left: -12px; /* Compensa padding de columnas Bootstrap */
            margin-right: -12px;
        }

        .doctor-card-wrapper {
            display: inline-block; /* Para que las tarjetas se alineen horizontalmente */
            width: 280px; /* Ancho fijo para cada tarjeta */
            vertical-align: top;
            margin-right: 1rem; /* Espacio entre tarjetas */
            white-space: normal; /* Restaura el normal wrapping del texto dentro de la tarjeta */
        }

            .doctor-card-wrapper:last-child {
                margin-right: 0;
            }

        .doctor-card {
            background-color: #fff;
            border: 1px solid #e0e0e0;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
            text-align: left;
            height: 100%;
            display: flex;
            flex-direction: column;
        }

            .doctor-card .card-img-top {
                border-top-left-radius: 15px;
                border-top-right-radius: 15px;
                height: 220px; /* Altura fija para la imagen del doctor */
                object-fit: cover; /* Cubre el espacio sin distorsionar */
                background-color: #f0f4f7; /* Color de fondo para placeholders */
            }

            .doctor-card .card-body {
                padding: 1rem;
                flex-grow: 1; /* Permite que el card-body ocupe el espacio restante */
                display: flex;
                flex-direction: column;
            }

            .doctor-card .specialty-tag {
                font-size: 0.75rem;
                padding: 0.25rem 0.6rem;
                background-color: #e7f3ff; /* Azul claro */
                color: #007bff; /* Azul principal */
                border-radius: 20px;
                display: inline-block;
                margin-bottom: 0.5rem;
            }

            .doctor-card .card-title {
                font-size: 1.1rem;
                font-weight: 600;
                margin-bottom: 0.25rem;
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
                margin-top: auto; /* Empuja el botón hacia abajo */
                align-self: flex-start; /* Alinea el botón a la izquierda */
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

        .nav-arrows {
            text-align: right;
            margin-top: 1rem;
        }

            .nav-arrows .btn-arrow {
                background: #f0f4f7;
                border: none;
                color: #333;
                border-radius: 50%;
                width: 40px;
                height: 40px;
                margin-left: 0.5rem;
            }

                .nav-arrows .btn-arrow:hover {
                    background: #dde6ed;
                }

        /* Placeholder para imagen de doctor */
        .doctor-placeholder {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 220px; /* Misma altura que la imagen */
            background-color: #e9ecef; /* Color de fondo del placeholder */
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
        }

            .doctor-placeholder i {
                font-size: 5rem; /* Tamaño del icono */
                color: #adb5bd; /* Color del icono */
            }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">

    <asp:Panel ID="pnlDetalleEspecialidad" runat="server" Visible="false">
        <div class="container mt-4">
            <!-- Sección de Descripción de la Especialidad -->
            <div class="row specialty-detail-header align-items-center">
                <div class="col-lg-7 col-md-6 order-md-1">
                    <h1 class="display-5 fw-bold mb-3">
                        <asp:Literal ID="litNombreEspecialidad" runat="server"></asp:Literal>
                    </h1>
                    <p class="lead">
                        <asp:Literal ID="litDescripcionEspecialidad" runat="server"></asp:Literal>
                    </p>
                </div>
                <div class="col-lg-5 col-md-6 order-md-2 specialty-image-container">
                    <%--<asp:Image ID="imgEspecialidad" runat="server" CssClass="specialty-main-image" AlternateText="Imagen de la especialidad" />--%>
                    <img src="/images/doctorGenericImage.png" class="img-fluid"/>
                </div>
            </div>

            <hr />

            <!-- Sección de Médicos Especialistas -->
            <h2 class="doctors-section-title">Los especialistas en
                <asp:Literal ID="litNombreEspecialidadMedicos" runat="server"></asp:Literal>
            </h2>

            <div class="doctor-card-container" id="doctorCardScroller">
                <asp:Repeater ID="rptMedicos" runat="server">
                    <ItemTemplate>
                        <div class="doctor-card-wrapper">
                            <div class="card doctor-card">
                                <asp:PlaceHolder ID="phDoctorImage" runat="server">
                                    <img src="/images/genericDoctor.png" />
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
                                    <a href='#' class="btn-know-doctor">
                                        Conoce al médico <i class="fas fa-arrow-right arrow-icon"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
             <%-- Las flechas necesitarían JavaScript para funcionar como un carrusel --%>
             <%-- Por ahora, el scroll horizontal es la funcionalidad principal --%>
            <div class="nav-arrows d-none"> <%-- Ocultas por defecto, habilitar si implementas JS --%>
                <button class="btn btn-arrow" onclick="scrollDoctors(-1)"><i class="fas fa-arrow-left"></i></button>
                <button class="btn btn-arrow" onclick="scrollDoctors(1)"><i class="fas fa-arrow-right"></i></button>
            </div>
        </div>
    </asp:Panel>

    <asp:Panel ID="pnlError" runat="server" CssClass="container mt-4 text-center" Visible="false">
        <div class="alert alert-warning" role="alert">
            <h4 class="alert-heading">Información no disponible</h4>
            <p><asp:Literal ID="litError" runat="server"></asp:Literal></p>
            <hr>
            <p class="mb-0">Por favor, intente más tarde o seleccione otra especialidad.</p>
            <a href="ListarEspecialidadesPaciente.aspx" class="btn btn-primary mt-3">Volver a Especialidades</a>
        </div>
    </asp:Panel>

    <script type="text/javascript">
        // Script simple para scroll, si se habilitan los botones.
        function scrollDoctors(direction) {
            const scroller = document.getElementById('doctorCardScroller');
            const cardWidth = 280 + 16; // Ancho de la tarjeta + margen derecho (1rem = 16px aprox)
            if (scroller) {
                scroller.scrollBy({ left: direction * cardWidth, behavior: 'smooth' });
            }
        }
    </script>

</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="cph_PageTitle" runat="server">
</asp:Content>