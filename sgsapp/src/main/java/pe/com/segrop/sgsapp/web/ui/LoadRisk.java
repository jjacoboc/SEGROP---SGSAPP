/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.com.segrop.sgsapp.domain.SegDetRiesgo;
import pe.com.segrop.sgsapp.util.JSFUtils;

/**
 *
 * @author JJ
 */
public class LoadRisk extends HttpServlet implements Serializable {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        try {
            System.out.println("processRequest...");
            MatrizMB matrizMB = (MatrizMB) JSFUtils.getSessionAttribute("matrizMB");
            List<SegDetRiesgo> listaRiesgo = matrizMB.getListaRiesgo();
            String index = (String) request.getParameter("indice");
            SegDetRiesgo riesgo = (SegDetRiesgo) listaRiesgo.get(Integer.parseInt(index));
            
            
            StringBuilder html = new StringBuilder();
            html.append("<table cellspacing='0' width='100%' style='font-size: 9px;border: 1px solid black;'>");
            html.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'><b>RESUMEN DE LA ").append(riesgo.getVTipoRiesgo()).append(" (CÓDIGO: ").append(riesgo.getNCodRiesgo().toString()).append(")</b></td></tr>");
            html.append("<tr><td style='border: 1px solid black;' width='100'><b>Nombre: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVDescBreve()).append("</td></tr>");
            html.append("<tr><td style='border: 1px solid black;'><b>Estado: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVEstado()).append("</td></tr>");
            html.append("<tr><td style='border: 1px solid black;'><b>Fecha y Hora: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVFecHora()).append("</td></tr>");
            html.append("<tr><td style='border: 1px solid black;'><b>Lugar: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVLugar()).append("</td></tr>");
            html.append("<tr><td style='border: 1px solid black;'><b>Responsable: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVResponsable()).append("</td></tr>");
            html.append("<tr><td style='border: 1px solid black;'><b>Acciones Tomadas: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVAccTomadas()).append("</td></tr>");
            html.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'><b>SEGUIMIENTO</b></td></tr>");
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoadRisk</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadRisk at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
            out.println(html.toString());
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
