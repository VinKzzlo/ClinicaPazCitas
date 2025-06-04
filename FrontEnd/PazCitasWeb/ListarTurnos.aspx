<%@ Page Title="" Language="C#" MasterPageFile="~/PazCitasMedico.Master" AutoEventWireup="true" CodeBehind="ListarTurnos.aspx.cs" Inherits="PazCitasWA.ListarTurnos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
    <style>
        .appointment-header-row {
            display: flex;
            flex-direction: row;
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            padding: 12px 10px;
            font-weight: bold;
            text-align: center;
            border-radius: 8px 8px 0 0;
            color: #333;
        }

        .appointment-row {
            display: flex;
            flex-direction: row;
            align-items: center;
            border: 1px solid #dee2e6;
            border-top: none;
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 0 0 8px 8px;
            background-color: #ffffff;
        }

        .appointment-cell {
            flex: 1;
            padding: 0 10px;
            text-align: center;
            font-size: 15px;
        }

        .col-medico {
            flex: 2;
            text-align: left;
        }

        .col-acciones {
            flex: 1.2;
        }

        .doctor-info {
            display: flex;
            align-items: center;
        }

        .doctor-avatar-placeholder {
            width: 40px;
            height: 40px;
            background-color: #e0e0e0;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 10px;
            font-size: 18px;
            color: #007bff;
        }

        .actions-btn {
            background-color: #0d6efd;
            color: white;
            padding: 6px 12px;
            border-radius: 5px;
            text-decoration: none;
            border: none;
            font-size: 14px;
            display: inline-block;
        }

            .actions-btn:hover {
                background-color: #0b5ed7;
                color: white;
            }


        .appointment-row {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: space-between;
            border: 1px solid #dee2e6;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 8px;
            background-color: #fff;
        }

        .appointment-cell {
            flex: 1;
            padding: 0 10px;
            font-size: 15px;
        }

        .col-medico {
            display: flex;
            align-items: center;
            flex: 2;
        }

        .doctor-info {
            display: flex;
            align-items: center;
        }

        .doctor-avatar-placeholder {
            width: 40px;
            height: 40px;
            background-color: #f0f0f0;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 10px;
            font-size: 18px;
            color: #007bff;
        }

        .actions-btn {
            background-color: #0d6efd;
            color: white;
            padding: 5px 12px;
            border-radius: 5px;
            text-decoration: none;
            border: none;
        }

            .actions-btn i {
                margin-right: 5px;
            }







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
    <!-- Cabecera de columnas -->
    <div class="appointment-header-row">
        <div class="appointment-cell col-medico"><i class="fas fa-calendar-day me-1"></i> Día y Horario</div>
        <div class="appointment-cell col-especialidad"><i class="fas fa-stethoscope me-1"></i> Especialidad</div>
        <div class="appointment-cell col-sede"><i class="fas fa-hospital me-1"></i> Sede</div>
        <div class="appointment-cell col-consultorio"><i class="fas fa-door-open me-1"></i> Consultorio</div>
        <div class="appointment-cell col-acciones"><i class="fas fa-cogs me-1"></i> Disponibilidad</div>
    </div>


    <asp:Repeater ID="rptTurnosMedico" runat="server" OnItemDataBound="rptTurnosMedico_ItemDataBound">
        <ItemTemplate>
            <div class="appointment-row">
                <!--  Día y horario -->
                <div class="appointment-cell col-medico">
                    <div class="doctor-info">
                        <div class="doctor-avatar-placeholder">
                            <i class="fas fa-calendar-day"></i>
                        </div>
                        <div>
                            <div>
                                <asp:Literal ID="litDia" runat="server" /></div>
                            <div>
                                <asp:Literal ID="litHorario" runat="server" /></div>
                        </div>
                    </div>
                </div>

                <!--  Especialidad -->
                <div class="appointment-cell col-especialidad">
                    <asp:Literal ID="litEspecialidad" runat="server" />
                </div>

                <!--  Sede -->
                <div class="appointment-cell col-sede">
                    <asp:Literal ID="litSede" runat="server" />
                </div>

                <!--  Consultorio -->
                <div class="appointment-cell col-consultorio">
                    <asp:Literal ID="litConsultorio" runat="server" />
                </div>

                <!--  Acción -->
                <div class="appointment-cell col-acciones">
                    <asp:LinkButton ID="btnVerDisponibilidad" runat="server"
                        CssClass="actions-btn"
                        CommandName="VerDisponibilidad"
                        CommandArgument='<%# Eval("idTurno") %>'
                        OnClick="btnVerDisponibilidad_Click"
                        ToolTip="Ver disponibilidad del turno">
                    <i class="fa-solid fa-eye"></i> Ver
                    </asp:LinkButton>
                </div>
            </div>
        </ItemTemplate>
    </asp:Repeater>
    <!--
       <asp:GridView ID="dgvMedicos" runat="server" AutoGenerateColumns="true"
            OnPageIndexChanging="dgvMedicos_PageIndexChanging" PageSize="5"
            CssClass="table table-hover table-responsive table-striped">
            <columns>
                <asp:BoundField HeaderText="Dia" ItemStyle-CssClass="align-middle" DataField="dia"/>
                <asp:BoundField HeaderText="Hora Inicio" ItemStyle-CssClass="align-middle" DataField="horaInicio"/>
                <asp:BoundField HeaderText="Hora Fin" ItemStyle-CssClass="align-middle" DataField="horaFin"/>
                <asp:TemplateField>
                    <itemtemplate>
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i> Listar Disponibilidad" CssClass="btn btn-danger" OnClick="ListarDisponibilidad_Click"/>
                    </itemtemplate>
                </asp:TemplateField>
            </columns>
        </asp:GridView>
 -->

<%--   
    <div class="construction-message">
        <div class="construction-icon">
            🛠️
        </div>
        <h1>¡Página en Construcción!</h1>
        <p>Estamos trabajando en esta sección. ¡Pronto estará disponible!</p>
    </div>--%>

</asp:Content>
