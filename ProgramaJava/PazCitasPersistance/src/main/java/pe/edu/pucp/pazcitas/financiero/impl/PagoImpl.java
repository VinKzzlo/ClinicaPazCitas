/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pazcitas.financiero.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.config.DBManager;
import pe.edu.pucp.pazcitas.financiero.dao.PagoDAO;
import pe.edu.pucp.pazcitas.financiero.model.EstadoPago;
import pe.edu.pucp.pazcitas.financiero.model.Pago;
import pe.edu.pucp.pazcitas.financiero.model.Seguro;

/**
 *
 * @author asant
 */
public class PagoImpl implements PagoDAO{
    
    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Pago pago) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        
        parametrosEntrada.put(2, pago.getFechaPago());
        parametrosEntrada.put(3, pago.getMontoTotal());
        parametrosEntrada.put(4, pago.getMontoCubiertoSeguro());
        parametrosEntrada.put(5, pago.getMontoSubtotal());
        parametrosEntrada.put(6, pago.getEstado().name());
        parametrosEntrada.put(7, pago.getSeguro().getIdSeguro());
        parametrosEntrada.put(8, pago.getCita().getIdCita());
        
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_PAGO", parametrosEntrada, parametrosSalida);
        pago.setIdPago((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro el pago");
        return pago.getIdPago();
    }

    @Override
    public int eliminar(int idPago) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idPago);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_PAGO", parametrosEntrada, null);
        return resultado;
    }

    @Override
    public int modificar(Pago pago) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, pago.getIdPago());
        
        parametrosEntrada.put(2, pago.getFechaPago());
        parametrosEntrada.put(3, pago.getMontoTotal());
        parametrosEntrada.put(4, pago.getMontoCubiertoSeguro());
        parametrosEntrada.put(5, pago.getMontoSubtotal());
        parametrosEntrada.put(6, pago.getEstado().name());

        
        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_PAGO", parametrosEntrada, null);
        System.out.println("Se ha realizado el modificar el pago");
        return pago.getIdPago();
    }

    @Override
    public ArrayList<Pago> listarTodos() {
       ArrayList<Pago> pagos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_TODOS_PAGO", null);
        System.out.println("Lectura de pagos...");
        try{
            while(rs.next()){
                Pago e = new Pago();
                e.setIdPago(rs.getInt("id_pago"));
                e.setFechaPago(rs.getTimestamp("fecha_pago").toLocalDateTime());
                e.setMontoTotal(rs.getDouble("monto_total"));
                e.setMontoCubiertoSeguro(rs.getDouble("monto_cubierto"));
                e.setMontoSubtotal(rs.getDouble("subototal"));
                
                EstadoPago estado = EstadoPago.valueOf(rs.getString("estado").toUpperCase());
                e.setEstado(estado);
                
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("fid_seguro"));
                Cita cita = new Cita();
                cita.setIdCita(rs.getInt("fid_cita"));
                
                e.setSeguro(seguro);
                e.setCita(cita);
                
                
                
                pagos.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return pagos;
    }
    
}
