<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="GestionarMedicos.aspx.cs" Inherits="PazCitasWA.GestionarMedicos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Gestionar Médicos
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        /* ── 1 · Animación genérica ─────────────────────────────────────────────── */
        .hover-shadow:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 16px rgba(0,0,0,.15) !important;
            transition: all .25s;
        }

        /* ── 2 · Paleta clínica ─────────────────────────────────────────────────── */
        .card-medicos {
            border-left: 6px solid #80DEEA;
        }

            .card-medicos i {
                color: #00838F;
            }

            .card-medicos:hover {
                box-shadow: 0 6px 16px rgba(0,131,143,.35) !important;
            }

        .card-asistentes {
            border-left: 6px solid #80DEEA;
        }

            .card-asistentes i {
                color: #00838F;
            }

            .card-asistentes:hover {
                box-shadow: 0 6px 16px rgba(0,131,143,.35) !important;
            }

        .card-recepcion {
            border-left: 6px solid #80DEEA;
        }

            .card-recepcion i {
                color: #00838F;
            }

            .card-recepcion:hover {
                box-shadow: 0 6px 16px rgba(0,131,143,.35) !important;
            }

        /* ── 3 · Centrado vertical de la vista ──────────────────────────────────── */
        .center-vh {
            min-height: calc(100vh - 140px);
            display: flex;
            align-items: center;
        }

        /* ── 4 · Tarjeta XL y alineación interna ───────────────────────────────── */
        .card-col-xl {
            max-width: 24rem;
        }
        /* antes 22 → 24 rem      */
        .card-body {
            padding: 5rem 1.25rem; /* más alto               */
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

            /* Icono grande */
            .card-body i {
                font-size: 4.5rem;
            }

            /* Título: hasta 2 líneas, siempre ocupa el mismo alto (48 px) */
            .card-body h4 {
                font-size: 1.45rem; /* un poco mayor */
                font-weight: 700;
                min-height: 3rem; /* ≈ 48 px (2 líneas) */
                line-height: 1.2;
                margin-bottom: .75rem;
                text-align: center;
            }

            /* Sub-título: letra mayor, 2 líneas de reserva (40 px) */
            .card-body .card-desc {
                font-size: 1.05rem; /* más grande    */
                min-height: 2.5rem; /* ≈ 40 px       */
                line-height: 1.25;
                margin: 0;
                color: #5f6368; /* gris suave    */
            }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="center-vh">
        <%-- quita esta clase si NO quieres centrado vertical --%>
        <div class="container">
            <h4 class="fw-bold text-center mb-4">Elija el tipo de empleado:</h4>

            <!-- Contenedor flex que centra horizontalmente -->
            <div class="row row-cols-1 row-cols-md-3 g-4 justify-content-center">
                <!-- 1 · Médicos -->
                <div class="col card-col-xl">
                    <a href="ListarMedicos.aspx" class="text-decoration-none">
                        <div class="card shadow-sm h-100 border-0 hover-shadow card-medicos">
                            <div class="card-body text-center">
                                <i class="fa-solid fa-user-doctor fa-3x mb-3"></i>
                                <h4 class="fw-bold mb-0">Listar Médicos</h4>
                                <p class="card-desc">
                                    Ver, Modificar y Eliminar médicos de PazCitas
                                </p>
                            </div>
                        </div>
                    </a>
                </div>

                <!-- 2 · Asistentes -->
                <div class="col card-col-xl">
                    <a href="AsignarTurnos.aspx" class="text-decoration-none">
                        <div class="card shadow-sm h-100 border-0 hover-shadow card-asistentes">
                            <div class="card-body text-center">
                                <i class="fa-solid fa-calendar-days"></i>
                                <h4 class="fw-bold mb-0">Asignar Turnos</h4>
                                <p class="card-desc">
                                    Asigne turnos a los médicos
                                </p>
                            </div>
                        </div>
                    </a>
                </div>

            </div>
        </div>
    </div>
</asp:Content>
