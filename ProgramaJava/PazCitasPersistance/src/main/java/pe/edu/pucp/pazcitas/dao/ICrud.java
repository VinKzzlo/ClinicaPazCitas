/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.dao;

import java.util.ArrayList;

public interface ICrud<T> {
    int insertar(T modelo);
    int eliminar(int idModelo);
    int modificar(T modelo);
    ArrayList<T> listarTodos();
}
