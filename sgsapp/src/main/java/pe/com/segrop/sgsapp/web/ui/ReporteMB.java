/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import pe.com.segrop.sgsapp.dao.ExportDao;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */

public class ReporteMB implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Connection conexion;
    private String report;
    private List<SelectItem> listaEntidades;
    private Date fechaInicio;
    private Date fechaFin;
    
    /** Creates a new instance of ReporteMB */
    public ReporteMB() {
        try {
            ResourceBundle bundle = null;
            String driver = null;
            String url = null;
            String user = null;
            String password = null;
            SimpleDateFormat sdf = null;
            try {
                bundle = ResourceBundle.getBundle(Parameters.getJDBC());
                driver = bundle.getString("driver");
                url = bundle.getString("url");
                user = bundle.getString("username");
                password = bundle.getString("password");
                Class.forName(driver);
                conexion = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReporteMB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ReporteMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.fechaFin = new Date();
            this.fechaInicio = sdf.parse("01".concat(sdf.format(this.fechaFin).substring(2)));
        } catch (ParseException ex) {
            Logger.getLogger(ReporteMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public List<SelectItem> getListaEntidades() {
        ExportDao exportDao = (ExportDao) ServiceFinder.findBean("ExportDao");
        listaEntidades = new Items(exportDao.obtenerListaEntidades(), Items.FIRST_ITEM_SELECT, "NCodEntidad","VDescripcion").getItems();
        return listaEntidades;
    }

    public void setListaEntidades(List<SelectItem> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public void handlerButtonPdf(){
        try{
            int valor = Integer.parseInt(this.getReport());
            if(valor == 1){
                this.pdf("report1.jasper");
            } else if(valor == 2){
                this.pdf("report2.jasper");
            } else if(valor == 3){
                this.pdf("report3.jasper");
            } else if(valor == 4){
                this.pdf("report4.jasper");
            } else if(valor == 5){
                this.pdf("report5.jasper");
            } else if(valor == 6){
                this.pdf("report6.jasper");
            } else if(valor == 7){
                this.pdf("report7.jasper");
            } else if(valor == 8){
                this.pdf("report8.jasper");
            } else if(valor == 9){
                this.pdf("report9.jasper");
            } else if(valor == 10){
                this.pdf("report10.jasper");
            } else if(valor == 11){
                this.pdf("report11.jasper");
            } else if(valor == 12){
                this.pdf("report12.jasper");
            } else if(valor == 13){
                this.pdf("report13.jasper");
            } else if(valor == 14){
                this.pdf("report14.jasper");
            }
        }catch(NumberFormatException e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void handlerButtonDoc(){
        try{
            int valor = Integer.parseInt(this.getReport());
            if(valor == 1){
                this.docx("report1.jasper");
            } else if(valor == 2){
                this.docx("report2.jasper");
            } else if(valor == 3){
                this.docx("report3.jasper");
            } else if(valor == 4){
                this.docx("report4.jasper");
            } else if(valor == 5){
                this.docx("report5.jasper");
            } else if(valor == 6){
                this.docx("report6.jasper");
            } else if(valor == 7){
                this.docx("report7.jasper");
            } else if(valor == 8){
                this.docx("report8.jasper");
            } else if(valor == 9){
                this.docx("report9.jasper");
            } else if(valor == 10){
                this.docx("report10.jasper");
            } else if(valor == 11){
                this.docx("report11.jasper");
            } else if(valor == 12){
                this.docx("report12.jasper");
            } else if(valor == 13){
                this.docx("report13.jasper");
            } else if(valor == 14){
                this.docx("report14.jasper");
            }
        }catch(NumberFormatException e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void pdf(String fileName){
        String  reportPath = null;
        JasperPrint jasperPrint = null;
        HttpServletResponse httpServletResponse = null;
        ServletOutputStream servletOutputStream = null;
        JRPdfExporter pdfExporter = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            reportPath =  JSFUtils.getServletContext().getRealPath("/pages/report/".concat(fileName));
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fechaInicio", sdf.format(this.getFechaInicio()));
            parametros.put("fechaFin", sdf.format(this.getFechaFin()));
            jasperPrint = JasperFillManager.fillReport(reportPath, parametros, this.conexion);
            httpServletResponse = JSFUtils.getResponse();
            httpServletResponse.setContentType("application/octet-stream");
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
            servletOutputStream = httpServletResponse.getOutputStream();
            pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
            pdfExporter.exportReport();
            FacesContext.getCurrentInstance().responseComplete();
        }catch(JRException e){
            e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void docx(String fileName){
        String  reportPath = null;
        JasperPrint jasperPrint = null;
        HttpServletResponse httpServletResponse = null;
        ServletOutputStream servletOutputStream = null;
        JRDocxExporter docxExporter = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            reportPath =  JSFUtils.getServletContext().getRealPath("/pages/report/".concat(fileName));
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fechaInicio", sdf.format(this.getFechaInicio()));
            parametros.put("fechaFin", sdf.format(this.getFechaFin()));
            jasperPrint = JasperFillManager.fillReport(reportPath, parametros, this.conexion);
            httpServletResponse = JSFUtils.getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.docx");
            servletOutputStream = httpServletResponse.getOutputStream();
            docxExporter = new JRDocxExporter();
            docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
            docxExporter.exportReport();
            FacesContext.getCurrentInstance().responseComplete();
        }catch(JRException e){
            e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
