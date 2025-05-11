/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazCitas.usuario.model;

import java.util.Date;

/**
 *
 * @author Joel
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String Dni;
    private String email;
    private Date fechaNacimiento;
    private String hashContrasenia;
    private char rol;

    public Usuario() {
    }
    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getHashContrasenia() {
        return hashContrasenia;
    }

    public void setHashContrasenia(String hashContrasenia) {
        this.hashContrasenia = hashContrasenia;
    }

    public char getRol() {
        return rol;
    }

    public void setRol(char rol) {
        this.rol = rol;
    }
    
    
    
}
