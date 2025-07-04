<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasAdmin.Master" AutoEventWireup="true" CodeBehind="AsignarTurnos.aspx.cs" Inherits="PazCitasWA.AsignarTurnos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Asignar Turnos
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        /* Estilos personalizados con paleta verde */
        .filter-card {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
            border: none !important;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(72, 187, 120, 0.3);
        }

            .filter-card .card-header {
                background: rgba(255, 255, 255, 0.1) !important;
                border: none !important;
                color: white !important;
                border-radius: 15px 15px 0 0 !important;
                backdrop-filter: blur(10px);
            }

            .filter-card .card-body {
                background: rgba(255, 255, 255, 0.95);
                border-radius: 0 0 15px 15px;
            }

        .doctors-card {
            border: none !important;
            border-radius: 15px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
        }

            .doctors-card .card-header {
                background: linear-gradient(135deg, #4fd1c7 0%, #14b8a6 100%) !important;
                border: none !important;
                color: white !important;
                border-radius: 15px 15px 0 0 !important;
            }

        .doctor-item {
            background: linear-gradient(135deg, #f0fdf4 0%, #ffffff 100%) !important;
            border: 2px solid #d1fae5 !important;
            border-radius: 12px !important;
            transition: all 0.3s ease;
            position: relative;
            overflow: hidden;
        }

            .doctor-item::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                width: 4px;
                height: 100%;
                background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
            }

            .doctor-item:hover {
                transform: translateY(-3px);
                box-shadow: 0 12px 35px rgba(72, 187, 120, 0.2);
                border-color: #48bb78 !important;
            }

        .doctor-avatar {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%) !important;
            width: 60px !important;
            height: 60px !important;
            font-size: 18px;
            box-shadow: 0 4px 15px rgba(72, 187, 120, 0.3);
            border: 3px solid white;
        }

        .status-badge {
            background: linear-gradient(135deg, #10b981 0%, #059669 100%) !important;
            border: none !important;
            color: white !important;
            padding: 6px 12px;
            border-radius: 20px;
            font-size: 11px;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .btn-assign {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%) !important;
            border: none !important;
            border-radius: 25px !important;
            padding: 8px 20px !important;
            font-weight: 600;
            transition: all 0.3s ease;
            box-shadow: 0 4px 15px rgba(72, 187, 120, 0.3);
        }

            .btn-assign:hover {
                transform: translateY(-2px);
                box-shadow: 0 8px 25px rgba(72, 187, 120, 0.4);
                background: linear-gradient(135deg, #38a169 0%, #2f855a 100%) !important;
            }

        .btn-filter {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%) !important;
            border: none !important;
            border-radius: 25px !important;
            padding: 12px 30px !important;
            font-weight: 600;
            transition: all 0.3s ease;
            box-shadow: 0 4px 15px rgba(72, 187, 120, 0.3);
        }

            .btn-filter:hover {
                transform: translateY(-2px);
                box-shadow: 0 8px 25px rgba(72, 187, 120, 0.4);
                background: linear-gradient(135deg, #38a169 0%, #2f855a 100%) !important;
            }

        .form-select, .form-control {
            border: 2px solid #d1fae5;
            border-radius: 10px;
            padding: 12px 15px;
            transition: all 0.3s ease;
        }

            .form-select:focus, .form-control:focus {
                border-color: #48bb78;
                box-shadow: 0 0 0 0.2rem rgba(72, 187, 120, 0.25);
            }

        .form-label {
            color: #1f2937;
            font-weight: 600;
            margin-bottom: 8px;
        }

        .doctor-name {
            color: #1f2937 !important;
            font-weight: 700 !important;
            font-size: 1.1rem;
        }

        .doctor-info {
            color: #6b7280 !important;
            font-size: 0.9rem;
        }

        .specialty-text {
            color: #374151 !important;
            font-weight: 600;
            font-size: 0.95rem;
        }

        .sede-text {
            color: #6b7280 !important;
            font-size: 0.85rem;
        }

        .empty-state {
            text-align: center;
            padding: 40px 20px;
            color: #6b7280;
        }

        .page-title {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            font-weight: 800;
            margin-bottom: 30px;
            text-align: center;
        }

        .icon-gradient {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container mt-4">
        <h2 class="page-title">
            <i class="fa fa-calendar-plus me-3"></i>Gestión de Turnos Médicos
        </h2>

        <div class="row g-4">
            <!-- Filtros a la izquierda -->
            <div class="col-lg-4">
                <div class="card filter-card">
                    <div class="card-header text-center fw-bold">
                        <i class="fa fa-filter me-2"></i>Filtros de Búsqueda
                    </div>
                    <div class="card-body">
                        <!-- Sede -->
                        <div class="mb-4">
                            <label class="form-label" for="ddlSede">
                                <i class="fa fa-building me-2 icon-gradient"></i>Sede:
                            </label>
                            <asp:DropDownList ID="ddlSede" runat="server" CssClass="form-select"
                                AutoPostBack="true" OnSelectedIndexChanged="ddlSede_SelectedIndexChanged" />
                        </div>

                        <!-- Especialidad -->
                        <div class="mb-4">
                            <label class="form-label" for="ddlEspecialidad">
                                <i class="fa fa-stethoscope me-2 icon-gradient"></i>Especialidad:
                            </label>
                            <asp:DropDownList ID="ddlEspecialidad" runat="server" CssClass="form-select" />
                        </div>

                        <asp:Button ID="btnFiltrar" runat="server" Text="Buscar Médicos"
                            CssClass="btn btn-filter w-100"
                            OnClick="btnFiltrar_Click" />
                    </div>
                </div>
            </div>

            <!-- Lista de médicos a la derecha -->
            <div class="col-lg-8">
                <div class="card doctors-card">
                    <div class="card-header text-center fw-bold">
                        <i class="fa fa-user-md me-2"></i>Médicos Disponibles
                    </div>
                    <div class="card-body">
                        <asp:Repeater ID="rptMedicos" runat="server" OnItemCommand="rptMedicos_ItemCommand">
                            <ItemTemplate>
                                <div class="doctor-item d-flex align-items-center p-4 mb-3">
                                    <!-- Avatar mejorado -->
                                    <div class="doctor-avatar rounded-circle text-white d-flex justify-content-center align-items-center me-4">
                                        <%# Eval("nombre").ToString().Substring(0,1) + Eval("apellidoPaterno").ToString().Substring(0,1) %>
                                    </div>

                                    <!-- Info del médico -->
                                    <div class="flex-grow-1">
                                        <h5 class="doctor-name mb-2">Dr. <%# Eval("nombre") %> <%# Eval("apellidoPaterno") %> <%# Eval("apellidoMaterno") %>
                                        </h5>
                                        <div class="doctor-info">
                                            <i class="fa fa-id-card me-1"></i>DNI: <%# Eval("dni") %>
                                            <span class="mx-2">|</span>
                                            <i class="fa fa-certificate me-1"></i>CMP: <%# Eval("codigoMedico") %>
                                        </div>
                                    </div>

                                    <!-- Especialidad / Sede -->
                                    <div class="text-end me-4">
                                        <div class="mb-2">
                                            <span class="status-badge">
                                                <i class="fa fa-check-circle me-1"></i>Activo
                                            </span>
                                        </div>
                                        <div class="specialty-text mb-1">
                                            <i class="fa fa-stethoscope me-1"></i><%# Eval("especialidad.nombre") %>
                                        </div>
                                        <div class="sede-text">
                                            <i class="fa fa-map-marker-alt me-1"></i><%# Eval("sede.nombre") %>
                                        </div>
                                    </div>

                                    <!-- Botón de acción mejorado -->
                                    <div>
                                        <asp:LinkButton runat="server"
                                            CssClass="btn btn-assign text-white"
                                            CommandName="Asignar"
                                            CommandArgument='<%# Eval("idUsuario") %>'>
                                             <i class="fa fa-calendar-plus me-2"></i>Asignar Turnos
                                        </asp:LinkButton>
                                    </div>
                                </div>
                            </ItemTemplate>
                        </asp:Repeater>

                        <asp:Label ID="lblVacio" runat="server" CssClass="empty-state" Visible="false">
                            <div>
                                <i class="fa fa-search fa-3x mb-3" style="color: #e2e8f0;"></i>
                                <p class="mb-0">No se encontraron médicos para los filtros seleccionados.</p>
                                <small>Intenta ajustar los criterios de búsqueda.</small>
                            </div>
                        </asp:Label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
