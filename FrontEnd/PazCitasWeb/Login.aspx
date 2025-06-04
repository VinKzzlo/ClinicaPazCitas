<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="PazCitasWA.Login" %>

<!DOCTYPE html>
<html lang="es">
<head runat="server">
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Login - PazCitas</title>

    <!-- Bootstrap -->
    <link href="Content/bootstrap-grid.min.css" rel="stylesheet" />
    <link href="Content/bootstrap-reboot.min.css" rel="stylesheet" />
    <!-- Font Awesome -->
    <link href="Content/Fonts/css/all.min.css" rel="stylesheet" />
    <!-- Bootstrap Bundle + jQuery -->
    <script src="Scripts/bootstrap.bundle.min.js"></script>
    <script src="Scripts/jquery-3.7.1.min.js"></script>

    <style>
        body {
            background-color: #f8f9fa;
            min-height: 100vh;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 40px 20px;
        }

        .login-wrapper {
            max-width: 1300px;
            width: 100%;
            height: 650px;
            display: flex;
            background-color: #fff;
            border-radius: 24px;
            box-shadow: 0 12px 40px rgba(0,0,0,0.15);
            overflow: hidden;
            flex-direction: row;
        }

        .login-header {
            flex: 1;
            background-color: #0d6efd;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 60px 30px;
        }

        .login-icon {
            font-size: 120px;
            margin-bottom: 30px;
        }

        .login-title {
            font-size: 34px;
            font-weight: bold;
            text-align: center;
        }

        .login-body {
            flex: 1;
            padding: 60px 60px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .form-label {
            font-weight: 600;
            font-size: 18px;
            margin-bottom: 8px;
        }

        .input-group-text {
            font-size: 24px;
            padding: 14px 18px;
        }

        .form-control {
            font-size: 20px;
            padding: 16px;
        }

        .form-control::placeholder {
            font-size: 16px;
            opacity: 0.75;
        }

        .btn-login {
            border-radius: 12px;
            font-weight: bold;
            font-size: 22px;
            padding: 18px;
            margin-top: 30px;
        }

        .text-small {
            font-size: 15px;
        }

        .login-footer {
            margin-top: 40px;
            text-align: center;
        }

        @media (max-width: 768px) {
            .login-wrapper {
                flex-direction: column;
                height: auto;
            }

            .login-body {
                padding: 40px 30px;
            }

            .login-title {
                font-size: 26px;
            }

            .login-icon {
                font-size: 70px;
            }
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="login-wrapper">
            <!-- CABECERA -->
            <div id="loginHeader" runat="server" class="login-header" style="background-color: #0d6efd;">
                <div id="iconoRol" runat="server" class="login-icon">
                    <i class="fas fa-user text-white"></i>
                </div>
                <div id="tituloRol" runat="server" class="login-title">Acceso</div>
            </div>

            <!-- FORMULARIO -->
            <div class="login-body">
                <div class="mb-4">
                    <label class="form-label">Nombre de usuario</label>
                    <div class="input-group">
                        <span class="input-group-text"><i id="iconUser" runat="server" class="fas fa-user text-warning"></i></span>
                        <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control" placeholder="Ingrese su DNI"></asp:TextBox>
                    </div>
                </div>

                <div class="mb-2">
                    <label class="form-label">Contraseña</label>
                    <div class="input-group">
                        <span class="input-group-text"><i id="iconLock" runat="server" class="fas fa-lock text-warning"></i></span>
                        <asp:TextBox ID="txtClave" runat="server" CssClass="form-control" TextMode="Password" placeholder="Ingrese su contraseña"></asp:TextBox>
                    </div>
                    <div class="text-end mt-2">
                        <a href="#" class="text-decoration-none text-small">¿Olvidó su contraseña?</a>
                    </div>
                </div>

                <div class="d-grid">
                    <asp:Button ID="btnLogin" runat="server" CssClass="btn btn-warning btn-login" Text="Iniciar sesión" OnClick="btnLogin_Click"/>
                </div>

                <div class="login-footer">
                    <a href="Inicio.aspx" class="text-decoration-none text-primary text-small">
                        <i class="fas fa-arrow-left me-1"></i> Volver al inicio
                    </a>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
