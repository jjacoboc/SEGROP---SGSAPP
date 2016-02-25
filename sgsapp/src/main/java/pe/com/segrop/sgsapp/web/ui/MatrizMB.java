/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import pe.com.segrop.sgsapp.dao.AccionDao;
import pe.com.segrop.sgsapp.dao.InsPreAccionDao;
import pe.com.segrop.sgsapp.dao.InsPresencialDao;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDao;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.dao.NovedadDao;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDao;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.dao.RiesgoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetAcciones;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencialId;
import pe.com.segrop.sgsapp.domain.SegDetInspreAcciones;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetInspreevalDetalle;
import pe.com.segrop.sgsapp.domain.SegDetNovEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;
import pe.com.segrop.sgsapp.domain.SegDetNovedadId;
import pe.com.segrop.sgsapp.domain.SegDetNovevalDetalle;
import pe.com.segrop.sgsapp.domain.SegDetRiesgo;
import pe.com.segrop.sgsapp.domain.SegDetRiesgoId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class MatrizMB implements Serializable {

    private Date searchFechaInicio;
    private Date searchFechaFin;
    private List<SegDetRiesgo> listaRiesgo;
    private SegDetRiesgo selectedRiesgo;
    private SegDetNovedad selectedNovedad;
    private SegDetNovEvaluacion novedadEvaluacion;
    private SegDetInsPresencial selectedInsPresencial;
    private SegDetInspreEvaluacion inspeccionEvaluacion;
    private String datos;
    private String html;
    private int rowKey;
    
    /** Creates a new instance of MatrizMB */
    public MatrizMB() {
    }
    
    public Date getSearchFechaInicio() {
        return searchFechaInicio;
    }

    public void setSearchFechaInicio(Date searchFechaInicio) {
        this.searchFechaInicio = searchFechaInicio;
    }

    public Date getSearchFechaFin() {
        return searchFechaFin;
    }

    public void setSearchFechaFin(Date searchFechaFin) {
        this.searchFechaFin = searchFechaFin;
    }

    public final List<SegDetRiesgo> getListaRiesgo() {
        if(listaRiesgo == null){
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            RiesgoDao riesgoDao = (RiesgoDao) ServiceFinder.findBean("RiesgoDao");
            SegDetRiesgoId segDetRiesgoId = new SegDetRiesgoId();
            segDetRiesgoId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            SegDetRiesgo segDetRiesgo = new SegDetRiesgo();
            segDetRiesgo.setId(segDetRiesgoId);
            listaRiesgo = riesgoDao.listarRiesgosMatriz(segDetRiesgo);
        }
        return listaRiesgo;
    }

    /**
     * @param listaRiesgo the listaRiesgo to set
     */
    public void setListaRiesgo(List<SegDetRiesgo> listaRiesgo) {
        this.listaRiesgo = listaRiesgo;
    }

    /**
     * @return the selectedRiesgo
     */
    public SegDetRiesgo getSelectedRiesgo() {
        return selectedRiesgo;
    }

    /**
     * @param selectedRiesgo the selectedRiesgo to set
     */
    public void setSelectedRiesgo(SegDetRiesgo selectedRiesgo) {
        this.selectedRiesgo = selectedRiesgo;
    }

    /**
     * @return the selectedNovedad
     */
    public SegDetNovedad getSelectedNovedad() {
        return selectedNovedad;
    }

    /**
     * @param selectedNovedad the selectedNovedad to set
     */
    public void setSelectedNovedad(SegDetNovedad selectedNovedad) {
        this.selectedNovedad = selectedNovedad;
    }

    /**
     * @return the novedadEvaluacion
     */
    public SegDetNovEvaluacion getNovedadEvaluacion() {
        return novedadEvaluacion;
    }

    /**
     * @param novedadEvaluacion the novedadEvaluacion to set
     */
    public void setNovedadEvaluacion(SegDetNovEvaluacion novedadEvaluacion) {
        this.novedadEvaluacion = novedadEvaluacion;
    }

    /**
     * @return the selectedInsPresencial
     */
    public SegDetInsPresencial getSelectedInsPresencial() {
        return selectedInsPresencial;
    }

    /**
     * @param selectedInsPresencial the selectedInsPresencial to set
     */
    public void setSelectedInsPresencial(SegDetInsPresencial selectedInsPresencial) {
        this.selectedInsPresencial = selectedInsPresencial;
    }

    /**
     * @return the inspeccionEvaluacion
     */
    public SegDetInspreEvaluacion getInspeccionEvaluacion() {
        return inspeccionEvaluacion;
    }

    /**
     * @param inspeccionEvaluacion the inspeccionEvaluacion to set
     */
    public void setInspeccionEvaluacion(SegDetInspreEvaluacion inspeccionEvaluacion) {
        this.inspeccionEvaluacion = inspeccionEvaluacion;
    }

    /**
     * @return the datos
     */
    public String getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getRowKey() {
        return rowKey;
    }

    public void setRowKey(int rowKey) {
        this.rowKey = rowKey;
    }
    
    public void buscarRiesgos(ActionEvent event) {
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            RiesgoDao riesgoDao = (RiesgoDao) ServiceFinder.findBean("RiesgoDao");
            SegDetRiesgoId segDetRiesgoId = new SegDetRiesgoId();
            segDetRiesgoId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            SegDetRiesgo segDetRiesgo = new SegDetRiesgo();
            segDetRiesgo.setId(segDetRiesgoId);
            segDetRiesgo.setDFecInicio(this.getSearchFechaInicio() != null ? this.getSearchFechaInicio() : null);
            segDetRiesgo.setDFecFin(this.getSearchFechaFin() != null ? this.getSearchFechaFin() : null);
            this.setListaRiesgo(riesgoDao.buscarRiesgosMatriz(segDetRiesgo));
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void onDrop(ActionEvent event) {
        String valor = null;
        String probabilidad = null;
        String impacto = null;
        String nivelProbabilidad = null;
        String nivelImpacto = null;
        String tipo = null;
        String codigo = null;
        ResourceBundle bundle;
        List lista = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            NovedadDao novedadDao =(NovedadDao)ServiceFinder.findBean("NovedadDao");
            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
            InsPresencialDao insPresencialDao =(InsPresencialDao)ServiceFinder.findBean("InsPresencialDao");
            InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
            valor = this.getDatos();
            probabilidad = valor.substring(0,2);
            impacto = valor.substring(2,4);
            nivelProbabilidad = valor.substring(4,5);
            nivelImpacto = valor.substring(5,6);
            tipo = valor.substring(6,7);
            codigo = valor.substring(7);
            if(tipo != null && tipo.equals(bundle.getString("TIPO_RIESGO_NOVEDAD"))){
                SegDetNovedadId novedadId = new SegDetNovedadId();
                novedadId.setNCodNovedad(BigDecimal.valueOf(Long.parseLong(codigo)));
                SegDetNovedad novedad = new SegDetNovedad();
                novedad.setId(novedadId);
                SegDetNovEvaluacion eval = novedadEvaluacionDao.obtenerEvaluacionNovedad(novedadDao.obtenerNovedadById(novedad));
                eval.setNOcurrencia(BigDecimal.valueOf(Long.parseLong(probabilidad)));
                eval.setNImpacto(BigDecimal.valueOf(Long.parseLong(impacto)));
                eval.setNNivelOcurrencia(BigDecimal.valueOf(Long.parseLong(nivelProbabilidad)));
                eval.setNNivelImpacto(BigDecimal.valueOf(Long.parseLong(nivelImpacto)));
                eval.setDFecModificacion(new Date());
                eval.setVUsuModificacion(usuarioSession.getVUsuario());
                eval.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                novedadEvaluacionDao.registrarEvaluacion(eval);
            }else if(tipo != null && tipo.equals(bundle.getString("TIPO_RIESGO_INSPECCION"))){
                SegDetInsPresencialId inspeccionId = new SegDetInsPresencialId();
                inspeccionId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
                inspeccionId.setNCodInspresencial(BigDecimal.valueOf(Long.parseLong(codigo)));
                SegDetInsPresencial inspeccion = new SegDetInsPresencial();
                inspeccion.setId(inspeccionId);
                SegDetInspreEvaluacion eval = inspreEvaluacionDao.obtenerEvaluacionInspeccion(insPresencialDao.obtenerInspeccionPresencialById(inspeccion));
                eval.setNOcurrencia(BigDecimal.valueOf(Long.parseLong(probabilidad)));
                eval.setNImpacto(BigDecimal.valueOf(Long.parseLong(impacto)));
                eval.setNNivelOcurrencia(BigDecimal.valueOf(Long.parseLong(nivelProbabilidad)));
                eval.setNNivelImpacto(BigDecimal.valueOf(Long.parseLong(nivelImpacto)));
                eval.setDFecModificacion(new Date());
                eval.setVUsuModificacion(usuarioSession.getVUsuario());
                eval.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                inspreEvaluacionDao.registrarEvaluacion(eval);
            }
            lista = (List<SegDetRiesgo>)BaseBean.getSession().getAttribute("listaRiesgo");
            if(lista != null && !lista.isEmpty()){
                for(int i=0;i<lista.size();i++){
                    SegDetRiesgo riesgo = (SegDetRiesgo)lista.get(i);
                    if(tipo.equals(riesgo.getNTipoRiesgo().toString()) && codigo.equals(riesgo.getNCodRiesgo().toString())){
                        riesgo.setNOcurrencia(BigDecimal.valueOf(Long.parseLong(probabilidad)));
                        riesgo.setNImpacto(BigDecimal.valueOf(Long.parseLong(impacto)));
                    }
                }
            }
            BaseBean.getSession().setAttribute("listaRiesgo", lista);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void onDeleteRisk(ActionEvent event) {
        String valor = null;
        String tipo = null;
        String codigo = null;
        ResourceBundle bundle;
        List lista = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            NovedadDao novedadDao =(NovedadDao)ServiceFinder.findBean("NovedadDao");
            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
            InsPresencialDao insPresencialDao =(InsPresencialDao)ServiceFinder.findBean("InsPresencialDao");
            InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
            valor = this.getDatos();
            tipo = valor.substring(0,1);
            codigo = valor.substring(1);
            if(tipo != null && tipo.equals(bundle.getString("TIPO_RIESGO_NOVEDAD"))){
                SegDetNovedadId novedadId = new SegDetNovedadId();
                novedadId.setNCodNovedad(BigDecimal.valueOf(Long.parseLong(codigo)));
                SegDetNovedad novedad = new SegDetNovedad();
                novedad.setId(novedadId);
                SegDetNovEvaluacion eval = novedadEvaluacionDao.obtenerEvaluacionNovedad(novedadDao.obtenerNovedadById(novedad));
                eval.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_SOLUCIONADA"))));
                eval.setDFecModificacion(new Date());
                eval.setVUsuModificacion(usuarioSession.getVUsuario());
                eval.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                novedadEvaluacionDao.registrarEvaluacion(eval);
            }else if(tipo != null && tipo.equals(bundle.getString("TIPO_RIESGO_INSPECCION"))){
                SegDetInsPresencialId inspeccionId = new SegDetInsPresencialId();
                inspeccionId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
                inspeccionId.setNCodInspresencial(BigDecimal.valueOf(Long.parseLong(codigo)));
                SegDetInsPresencial inspeccion = new SegDetInsPresencial();
                inspeccion.setId(inspeccionId);
                SegDetInspreEvaluacion eval = inspreEvaluacionDao.obtenerEvaluacionInspeccion(insPresencialDao.obtenerInspeccionPresencialById(inspeccion));
                eval.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_SOLUCIONADA"))));
                eval.setDFecModificacion(new Date());
                eval.setVUsuModificacion(usuarioSession.getVUsuario());
                eval.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                inspreEvaluacionDao.registrarEvaluacion(eval);
            }
            lista = (List<SegDetRiesgo>)BaseBean.getSession().getAttribute("listaRiesgo");
            if(lista != null && !lista.isEmpty()){
                for(int i=0;i<lista.size();i++){
                    SegDetRiesgo riesgo = (SegDetRiesgo)lista.get(i);
                    if(tipo.equals(riesgo.getNTipoRiesgo().toString()) && codigo.equals(riesgo.getNCodRiesgo().toString())){
                        lista.remove(riesgo);
                        break;
                    }
                }
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void showRisk(ActionEvent event) {
        SimpleDateFormat sdf = null;
        List<SegDetRiesgo> lista = null;
        SegDetRiesgo riesgo = null;
        List<SegDetAcciones> segDetNovAcciones = null;
        SegDetNovEvaluacion segDetNovEvaluacion = null;
        List<SegDetInspreAcciones> segDetInspreAcciones = null;
        SegDetInspreEvaluacion segDetInspreEvaluacion = null;
        try{            
            sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            lista = (List<SegDetRiesgo>) BaseBean.getSessionAttribute("listaRiesgo");
            riesgo = lista.get(this.getRowKey());
            if("1".equals(riesgo.getNTipoRiesgo().toString())){
                segDetNovAcciones = (List<SegDetAcciones>)riesgo.getSegDetAcciones();
                segDetNovEvaluacion = (SegDetNovEvaluacion)riesgo.getSegDetNovEvaluacion();
            }else{
                segDetInspreAcciones = (List<SegDetInspreAcciones>)riesgo.getSegDetInspreAcciones();
                segDetInspreEvaluacion = (SegDetInspreEvaluacion)riesgo.getSegDetInspreEvaluacion();
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("<table cellspacing='0' width='100%' style='font-size: 11px;border: 1px solid black;'>");
            sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>RESUMEN DE LA ").append(riesgo.getVTipoRiesgo()).append(" (CÓDIGO: ").append(riesgo.getNCodRiesgo().toString()).append(")</b></td></tr>");
            sb.append("<tr><td style='border: 1px solid black;' width='110'><b>Nombre: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVDescBreve()).append("</td></tr>");
            sb.append("<tr><td style='border: 1px solid black;'><b>Estado: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVEstado()).append("</td></tr>");
            sb.append("<tr><td style='border: 1px solid black;'><b>Fecha y Hora: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVFecHora()).append("</td></tr>");
            sb.append("<tr><td style='border: 1px solid black;'><b>Lugar: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVLugar()).append("</td></tr>");
            sb.append("<tr><td style='border: 1px solid black;'><b>Responsable: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVResponsable()).append("</td></tr>");
            sb.append("<tr><td style='border: 1px solid black;'><b>Acciones Tomadas: </b></td>").append("<td style='border: 1px solid black;'>").append(riesgo.getVAccTomadas()).append("</td></tr>");
            sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>SEGUIMIENTO</b></td></tr>");
            if("1".equals(riesgo.getNTipoRiesgo().toString())){
                if(segDetNovAcciones != null && !segDetNovAcciones.isEmpty()){
                    for(int m=0;m<segDetNovAcciones.size();m++){
                        SegDetAcciones acciones = (SegDetAcciones)segDetNovAcciones.get(m);
                        sb.append("<tr><td style='border: 1px solid black;'><b>").append(sdf.format(acciones.getDFecHora())).append("</b></td><td style='border: 1px solid black;'>").append(acciones.getVDescripcion()).append("</td></tr>");
                    }
                }else{
                    sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ningún seguimiento para mostrar</td></tr>");
                }
                if(segDetNovEvaluacion != null){
                    List<SegDetNovevalDetalle> listadetalles = (List<SegDetNovevalDetalle>)segDetNovEvaluacion.getSegDetNovevalDetalles();
                    if(listadetalles != null && !listadetalles.isEmpty()){
                        sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>DIAGNÓSTICOS</b></td></tr>");
                        for(int x=0;x<listadetalles.size();x++){
                            SegDetNovevalDetalle detalle = (SegDetNovevalDetalle)listadetalles.get(x);
                            sb.append("<tr><td style='border: 1px solid black;'><b>").append(sdf.format(detalle.getDFechora())).append("</b></td><td style='border: 1px solid black;'>").append(detalle.getVDiagnostico()).append("</td></tr>");
                        }
                        sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>RECOMENDACIONES</b></td></tr>");
                        for(int x=0;x<listadetalles.size();x++){
                            SegDetNovevalDetalle detalle = (SegDetNovevalDetalle)listadetalles.get(x);
                            sb.append("<tr><td style='border: 1px solid black;'><b>").append(sdf.format(detalle.getDFechora())).append("</b></td><td style='border: 1px solid black;'>").append(detalle.getVRecomendacion()).append("</td></tr>");
                        }
                    }else{
                        sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>DIAGNÓSTICOS</b></td></tr>");
                        sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ningún diagnóstico para mostrar</td></tr>");
                        sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>RECOMENDACIONES</b></td></tr>");
                        sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ninguna recomendación para mostrar</td></tr>");
                    }
                }else{
                    sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>DIAGNÓSTICOS</b></td></tr>");
                    sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ningún diagnóstico para mostrar</td></tr>");
                    sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>RECOMENDACIONES</b></td></tr>");
                    sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ninguna recomendación para mostrar</td></tr>");
                }
            }else{
                if(segDetInspreAcciones != null && !segDetInspreAcciones.isEmpty()){
                    for(int m=0;m<segDetInspreAcciones.size();m++){
                        SegDetInspreAcciones acciones = (SegDetInspreAcciones)segDetInspreAcciones.get(m);
                        sb.append("<tr><td style='border: 1px solid black;'><b>").append(sdf.format(acciones.getDFecHora())).append("</b></td><td style='border: 1px solid black;'>").append(acciones.getVDescripcion()).append("</td></tr>");
                    }
                }else{
                    sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ningún seguimiento para mostrar</td></tr>");
                }
                if(segDetInspreEvaluacion != null){
                    List<SegDetInspreevalDetalle> listadetalles = (List<SegDetInspreevalDetalle>)segDetInspreEvaluacion.getSegDetInspreevalDetalles();
                    if(listadetalles != null && !listadetalles.isEmpty()){
                        sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>DIAGNÓSTICOS</b></td></tr>");
                        for(int x=0;x<listadetalles.size();x++){
                            SegDetInspreevalDetalle detalle = (SegDetInspreevalDetalle)listadetalles.get(x);
                            sb.append("<tr><td style='border: 1px solid black;'><b>").append(sdf.format(detalle.getDFechora())).append("</b></td><td style='border: 1px solid black;'>").append(detalle.getVDiagnostico()).append("</td></tr>");
                        }
                        sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>RECOMENDACIONES</b></td></tr>");
                        for(int x=0;x<listadetalles.size();x++){
                            SegDetInspreevalDetalle detalle = (SegDetInspreevalDetalle)listadetalles.get(x);
                            sb.append("<tr><td style='border: 1px solid black;'><b>").append(sdf.format(detalle.getDFechora())).append("</b></td><td style='border: 1px solid black;'>").append(detalle.getVRecomendacion()).append("</td></tr>");
                        }
                    }else{
                        sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>DIAGNÓSTICOS</b></td></tr>");
                        sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ningún diagnóstico para mostrar</td></tr>");
                        sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>RECOMENDACIONES</b></td></tr>");
                        sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ninguna recomendación para mostrar</td></tr>");
                    }
                }else{
                    sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>DIAGNÓSTICOS</b></td></tr>");
                    sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ningún diagnóstico para mostrar</td></tr>");
                    sb.append("<tr style='background-color: #cccccc;'><td style='border: 1px solid black;' colspan='2' align='center'><b>RECOMENDACIONES</b></td></tr>");
                    sb.append("<tr><td style='border: 1px solid black;' colspan='2' align='center'>Ninguna recomendación para mostrar</td></tr>");
                }
            }
            sb.append("</table>");
            sb.append("<table width='100%'>");
            if("1".equals(riesgo.getNTipoRiesgo().toString())){
                sb.append("<tr><td colspan='2' align='center'>");
                sb.append("<input type='button' value='Seguimiento' onclick='JavaScript:segNovedad(").append(this.getRowKey()).append(");' status='bodystatus' style='font-size: 11px;height: 2em' ");
                if(riesgo.getNSeguimiento().equals(BigDecimal.ZERO)){
                    sb.append("disabled='disabled' ");
                }
                sb.append("/>");
                sb.append("&nbsp;&nbsp;");
                sb.append("<input type='button' value='Evaluación' onclick='JavaScript:evalNovedad(").append(this.getRowKey()).append(");' status='bodystatus' style='font-size: 11px;height: 2em'/>");
                sb.append("</td></tr>");
            }else{
                sb.append("<tr><td colspan='2' align='center'>");
                sb.append("<input type='button' value='Seguimiento' onclick='JavaScript:segInspeccion(").append(this.getRowKey()).append(");' status='bodystatus' style='font-size: 11px;height: 2em' ");
                if(riesgo.getNSeguimiento().equals(BigDecimal.ZERO)){
                    sb.append("disabled='disabled' ");
                }
                sb.append("/>");
                sb.append("&nbsp;&nbsp;");
                sb.append("<input type='button' value='Evaluación' onclick='JavaScript:evalInspeccion(").append(this.getRowKey()).append(");' status='bodystatus' style='font-size: 11px;height: 2em'/>");
                sb.append("</td></tr>");
            }
            sb.append("</table>");
            
            
            
            this.setHtml(sb.toString());
            //out.println(html.toString());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toSeguimientoNovedad(ActionEvent event) {
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
            AccionDao accionDao =(AccionDao)ServiceFinder.findBean("AccionDao");
            NovedadMB novedadMB = (NovedadMB)BaseBean.getSessionAttribute("novedadMB");
            novedadMB = novedadMB != null ? novedadMB : new NovedadMB();
            
            SegDetRiesgo riesgo = this.getListaRiesgo().get(this.getRowKey());
            
            SegDetNovedadId segDetNovedadId = new SegDetNovedadId();
            segDetNovedadId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            segDetNovedadId.setNCodNovedad(riesgo.getNCodRiesgo());
            SegDetNovedad segDetNovedad = new SegDetNovedad();
            segDetNovedad.setId(segDetNovedadId);
            segDetNovedad.setNCodEmpresa(segDetNovedadId.getNCodEmpresa());
            segDetNovedad = novedadDao.obtenerNovedadById(segDetNovedad);
            novedadMB.setListaNovedad(new ArrayList());
            novedadMB.getListaNovedad().add(segDetNovedad);
            
            novedadMB.setSelectedNovedad(segDetNovedad);
            novedadMB.setListaAcciones(accionDao.obtenerListaAccionesByNovedad(segDetNovedad));
            if(novedadMB.getListaAcciones() !=null && !novedadMB.getListaAcciones().isEmpty()){
                novedadMB.setListaAccionesVacia(false);
            }else{
                novedadMB.setListaAccionesVacia(true);
            }
            novedadMB.setFromMatrix(true);
            novedadMB.setActionOnLoad("Richfaces.showModalPanel('segDlg')");
            
            BaseBean.getSession().setAttribute("novedadMB", novedadMB);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toEvaluacionNovedad(ActionEvent event) {
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
            NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
            NovedadMB novedadMB = (NovedadMB)BaseBean.getSessionAttribute("novedadMB");
            novedadMB = novedadMB != null ? novedadMB : new NovedadMB();
            
            SegDetRiesgo riesgo = this.getListaRiesgo().get(this.getRowKey());
            SegDetNovedadId segDetNovedadId = new SegDetNovedadId();
            segDetNovedadId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            segDetNovedadId.setNCodNovedad(riesgo.getNCodRiesgo());
            SegDetNovedad segDetNovedad = new SegDetNovedad();
            segDetNovedad.setId(segDetNovedadId);
            segDetNovedad.setNCodEmpresa(segDetNovedadId.getNCodEmpresa());
            segDetNovedad = novedadDao.obtenerNovedadById(segDetNovedad);
            novedadMB.setListaNovedad(new ArrayList());
            novedadMB.getListaNovedad().add(segDetNovedad);
            
            novedadMB.setSelectedNovedad(segDetNovedad);
            novedadMB.setNovedadEvaluacion(novedadEvaluacionDao.obtenerEvaluacionNovedad(segDetNovedad));
            if(novedadMB.getNovedadEvaluacion() !=null){
                if(novedadMB.getNovedadEvaluacion().getNEstado() == null){
                    novedadMB.getNovedadEvaluacion().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE_ANALISIS"))));
                }
                List detalles = novedadEvaluacionDetalleDao.obtenerListaDetalleEvaluacionNovedad(novedadMB.getNovedadEvaluacion());
                novedadMB.getNovedadEvaluacion().setSegDetNovevalDetalles(detalles);
            }else{
                novedadMB.setNovedadEvaluacion(new SegDetNovEvaluacion());
            }
            novedadMB.setFromMatrix(true);
            novedadMB.setActionOnLoad("Richfaces.showModalPanel('evalDlg')");
            
            BaseBean.getSession().setAttribute("novedadMB", novedadMB);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toSeguimientoInspeccion(ActionEvent event) {
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
            InsPreAccionDao insPreAccionDao =(InsPreAccionDao)ServiceFinder.findBean("InsPreAccionDao");
            PresencialMB presencialMB = (PresencialMB)BaseBean.getSessionAttribute("presencialMB");
            presencialMB = presencialMB != null ? presencialMB : new PresencialMB();
            
            SegDetRiesgo riesgo = this.getListaRiesgo().get(this.getRowKey());
            
            SegDetInsPresencialId segDetInsPresencialId = new SegDetInsPresencialId();
            segDetInsPresencialId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            segDetInsPresencialId.setNCodInspresencial(riesgo.getNCodRiesgo());
            SegDetInsPresencial segDetInsPresencial = new SegDetInsPresencial();
            segDetInsPresencial.setId(segDetInsPresencialId);
            segDetInsPresencial.setNCodEmpresa(segDetInsPresencialId.getNCodEmpresa());
            segDetInsPresencial = insPresencialDao.obtenerInspeccionPresencialById(segDetInsPresencial);
            presencialMB.setListaInsPresencial(new ArrayList());
            presencialMB.getListaInsPresencial().add(segDetInsPresencial);
            
            presencialMB.setSelectedInsPresencial(segDetInsPresencial);
            presencialMB.setListaInspreAcciones(insPreAccionDao.obtenerListaAccionesByInspeccionPresencial(segDetInsPresencial));
            if(presencialMB.getListaInspreAcciones() !=null && !presencialMB.getListaInspreAcciones().isEmpty()){
                presencialMB.setListaAccionesVacia(false);
            }else{
                presencialMB.setListaAccionesVacia(true);
            }
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
            presencialMB.setFromMatrix(true);
            presencialMB.setActionOnLoad("Richfaces.showModalPanel('segDlg')");
            
            BaseBean.getSession().setAttribute("presencialMB", presencialMB);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toEvaluacionInspeccion(ActionEvent event) {
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
            InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
            InspreEvaluacionDetalleDao inspreEvaluacionDetalleDao =(InspreEvaluacionDetalleDao)ServiceFinder.findBean("InspreEvaluacionDetalleDao");
            PresencialMB presencialMB = (PresencialMB)BaseBean.getSessionAttribute("presencialMB");
            presencialMB = presencialMB != null ? presencialMB : new PresencialMB();
            
            SegDetRiesgo riesgo = this.getListaRiesgo().get(this.getRowKey());
            
            SegDetInsPresencialId segDetInsPresencialId = new SegDetInsPresencialId();
            segDetInsPresencialId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            segDetInsPresencialId.setNCodInspresencial(riesgo.getNCodRiesgo());
            SegDetInsPresencial segDetInsPresencial = new SegDetInsPresencial();
            segDetInsPresencial.setId(segDetInsPresencialId);
            segDetInsPresencial.setNCodEmpresa(segDetInsPresencialId.getNCodEmpresa());
            segDetInsPresencial = insPresencialDao.obtenerInspeccionPresencialById(segDetInsPresencial);
            presencialMB.setListaInsPresencial(new ArrayList());
            presencialMB.getListaInsPresencial().add(segDetInsPresencial);
            
            presencialMB.setSelectedInsPresencial(segDetInsPresencial);
            presencialMB.setInspeccionEvaluacion(inspreEvaluacionDao.obtenerEvaluacionInspeccion(segDetInsPresencial));
            if(presencialMB.getInspeccionEvaluacion() !=null){
                if(presencialMB.getInspeccionEvaluacion().getNEstado() == null){
                    presencialMB.getInspeccionEvaluacion().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE_ANALISIS"))));
                }
                List detalles = inspreEvaluacionDetalleDao.obtenerListaDetalleEvaluacionInspeccion(presencialMB.getInspeccionEvaluacion());
                presencialMB.getInspeccionEvaluacion().setSegDetInspreevalDetalles(detalles);
            }else{
                presencialMB.setInspeccionEvaluacion(new SegDetInspreEvaluacion());
            }
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
            presencialMB.setFromMatrix(true);
            presencialMB.setActionOnLoad("Richfaces.showModalPanel('evalDlg')");
            
            BaseBean.getSession().setAttribute("presencialMB", presencialMB);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
}
