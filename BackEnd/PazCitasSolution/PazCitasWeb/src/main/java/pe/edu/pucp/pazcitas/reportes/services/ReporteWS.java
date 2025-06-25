/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pazcitas.reportes.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.awt.Image;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.pazcitas.config.DBManager;

/**
 *
 * @author darwi
 */
@WebService(serviceName = "ReporteWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class ReporteWS {

    @WebMethod(operationName = "generarReporteCita")
    public byte[] generarReporteCita(@WebParam(name = "nombre") String nombre) {
        byte[]reporte =null;
        try{
            
            //1) creamos el 'reporte', asignando el 'URL del Reporte'
            JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().
                    getClassLoader().getResourceAsStream("/pe/edu/pucp//pazcitas/reportes/reporteCitas.jasper"));
            
            //2) enviamos paramestros de informacion para que se pueda lanzar el reporte
            
            //d. reporte con info de la BD e IMAGEN
            HashMap parametros = new HashMap();
            parametros.put("nombre", nombre);
            
            //Referenciamos la imagen del reporte
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/pazcitas/reportes/imagen.png");
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            parametros.put("Logo", logo);
            URL subreporteCXE = getClass().getResource("/pe/edu/pucp/pazcitas/reportes/reporteCitasPorEspecialidad.jasper");
            URL subreporteCXS = getClass().getResource("/pe/edu/pucp/pazcitas/reportes/reporteCitasPorSemana.jasper");
            

            String rutasubreporteCXE = URLDecoder.decode(subreporteCXE.getPath(),"UTF-8");
            String rutasubreporteCXS = URLDecoder.decode(subreporteCXS.getPath(),"UTF-8");

            parametros.put("rutaSubReporteCitasPorEspecialidad", rutasubreporteCXE);
            parametros.put("rutaSubReporteCitaPorSemana", rutasubreporteCXS);

            //Establecemos la conexion
            Connection con = DBManager.getInstance().getConnection();
            //Probamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr,parametros,con);

            con.close();
            
            //3) enviar a C# el pdf en formato de arreglo de bytes
            reporte = JasperExportManager.exportReportToPdf(jp);
            
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
}
