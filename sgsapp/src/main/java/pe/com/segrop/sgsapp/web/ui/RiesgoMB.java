/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import pe.com.segrop.sgsapp.dao.InsPresencialDao;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDao;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.dao.NovedadDao;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDao;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.dao.RiesgoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencialId;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacionId;
import pe.com.segrop.sgsapp.domain.SegDetInspreevalDetalle;
import pe.com.segrop.sgsapp.domain.SegDetInspreevalDetalleId;
import pe.com.segrop.sgsapp.domain.SegDetNovEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetNovEvaluacionId;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;
import pe.com.segrop.sgsapp.domain.SegDetNovedadId;
import pe.com.segrop.sgsapp.domain.SegDetNovevalDetalle;
import pe.com.segrop.sgsapp.domain.SegDetNovevalDetalleId;
import pe.com.segrop.sgsapp.domain.SegDetRiesgo;
import pe.com.segrop.sgsapp.domain.SegDetRiesgoId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class RiesgoMB implements Serializable {

    private BigDecimal searchTipoRiesgo;
    private BigDecimal searchLocal;
    private BigDecimal searchArea;
    private BigDecimal searchLugar;
    private BigDecimal searchResponsable;
    private BigDecimal searchCargo;
    private BigDecimal searchEstado;
    private Date searchFechaInicio;
    private Date searchFechaFin;
    private List<SegDetRiesgo> listaRiesgo;
    private SegDetRiesgo selectedRiesgo;
    private SegDetNovedad selectedNovedad;
    private SegDetNovEvaluacion novedadEvaluacion;
    private SegDetInsPresencial selectedInsPresencial;
    private SegDetInspreEvaluacion inspeccionEvaluacion;
    private String diagnostico;
    private String recomendacion;
    private String action;
    
    /** Creates a new instance of RiesgoMB */
    public RiesgoMB() {
        this.selectedNovedad = new SegDetNovedad();
        this.novedadEvaluacion = new SegDetNovEvaluacion();
        this.selectedInsPresencial = new SegDetInsPresencial();
        this.inspeccionEvaluacion = new SegDetInspreEvaluacion();
    }

    /**
     * @return the searchTipoRiesgo
     */
    public BigDecimal getSearchTipoRiesgo() {
        return searchTipoRiesgo;
    }

    /**
     * @param searchTipoRiesgo the searchTipoRiesgo to set
     */
    public void setSearchTipoRiesgo(BigDecimal searchTipoRiesgo) {
        this.searchTipoRiesgo = searchTipoRiesgo;
    }

    /**
     * @return the searchLocal
     */
    public BigDecimal getSearchLocal() {
        return searchLocal;
    }

    /**
     * @param searchLocal the searchLocal to set
     */
    public void setSearchLocal(BigDecimal searchLocal) {
        this.searchLocal = searchLocal;
    }

    /**
     * @return the searchArea
     */
    public BigDecimal getSearchArea() {
        return searchArea;
    }

    /**
     * @param searchArea the searchArea to set
     */
    public void setSearchArea(BigDecimal searchArea) {
        this.searchArea = searchArea;
    }

    /**
     * @return the searchLugar
     */
    public BigDecimal getSearchLugar() {
        return searchLugar;
    }

    /**
     * @param searchLugar the searchLugar to set
     */
    public void setSearchLugar(BigDecimal searchLugar) {
        this.searchLugar = searchLugar;
    }

    /**
     * @return the searchResponsable
     */
    public BigDecimal getSearchResponsable() {
        return searchResponsable;
    }

    /**
     * @param searchResponsable the searchResponsable to set
     */
    public void setSearchResponsable(BigDecimal searchResponsable) {
        this.searchResponsable = searchResponsable;
    }

    /**
     * @return the searchCargo
     */
    public BigDecimal getSearchCargo() {
        return searchCargo;
    }

    /**
     * @param searchCargo the searchCargo to set
     */
    public void setSearchCargo(BigDecimal searchCargo) {
        this.searchCargo = searchCargo;
    }

    /**
     * @return the searchEstado
     */
    public BigDecimal getSearchEstado() {
        return searchEstado;
    }

    /**
     * @param searchEstado the searchEstado to set
     */
    public void setSearchEstado(BigDecimal searchEstado) {
        this.searchEstado = searchEstado;
    }

    /**
     * @return the searchFechaInicio
     */
    public Date getSearchFechaInicio() {
        return searchFechaInicio;
    }

    /**
     * @param searchFechaInicio the searchFechaInicio to set
     */
    public void setSearchFechaInicio(Date searchFechaInicio) {
        this.searchFechaInicio = searchFechaInicio;
    }

    /**
     * @return the searchFechaFin
     */
    public Date getSearchFechaFin() {
        return searchFechaFin;
    }

    /**
     * @param searchFechaFin the searchFechaFin to set
     */
    public void setSearchFechaFin(Date searchFechaFin) {
        this.searchFechaFin = searchFechaFin;
    }

    /**
     * @return the listaRiesgo
     */
    public List<SegDetRiesgo> getListaRiesgo() {
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
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * @return the recomendacion
     */
    public String getRecomendacion() {
        return recomendacion;
    }
    
    /**
     * @param recomendacion the recomendacion to set
     */
    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public void buscarRiesgo(ActionEvent actionEvent) {
        try {
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            RiesgoDao riesgoDao = (RiesgoDao) ServiceFinder.findBean("RiesgoDao");
            SegDetRiesgo segDetRiesgo = new SegDetRiesgo();
            SegDetRiesgoId segDetRiesgoId = new SegDetRiesgoId();
            segDetRiesgoId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            segDetRiesgoId.setNTipoRiesgo(this.getSearchTipoRiesgo() != null ? this.getSearchTipoRiesgo() : null);
            segDetRiesgo.setId(segDetRiesgoId);
            segDetRiesgo.setNLocal(this.getSearchLocal() != null ? this.getSearchLocal() : null);
            segDetRiesgo.setNArea(this.getSearchArea() != null ? this.getSearchArea() : null);
            segDetRiesgo.setNLugar(this.getSearchLugar() != null ? this.getSearchLugar() : null);
            segDetRiesgo.setNResponsable(this.getSearchResponsable() != null ? this.getSearchResponsable() : null);
            segDetRiesgo.setNCargo(this.getSearchCargo() != null ? this.getSearchCargo() : null);
            segDetRiesgo.setNEstado(this.getSearchEstado() != null ? this.getSearchEstado() : null);
            segDetRiesgo.setDFecInicio(this.getSearchFechaInicio() != null ? this.getSearchFechaInicio() : null);
            segDetRiesgo.setDFecFin(this.getSearchFechaFin() != null ? this.getSearchFechaFin() : null);
            setListaRiesgo(riesgoDao.buscarRiesgos(segDetRiesgo));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toEvaluacion(ActionEvent actionEvent){
        ResourceBundle bundle;
        try{
            String rowKey = BaseBean.getRequestParameter("rowKey");
            SegDetRiesgo segDetRiesgo = this.getListaRiesgo().get(Integer.parseInt(rowKey));
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            if(segDetRiesgo.getId().getNTipoRiesgo().equals(BigDecimal.ONE)){  //Si el riesgo es una Novedad.
                NovedadDao novedadDao =(NovedadDao)ServiceFinder.findBean("NovedadDao");
                SegDetNovedadId segDetNovedadId = new SegDetNovedadId();
                segDetNovedadId.setNCodEmpresa(segDetRiesgo.getId().getNCodEmpresa());
                segDetNovedadId.setNCodNovedad(segDetRiesgo.getId().getNCodRiesgo());
                SegDetNovedad segDetNovedad = new SegDetNovedad();
                segDetNovedad.setId(segDetNovedadId);
                segDetNovedad = novedadDao.obtenerNovedadById(segDetNovedad);
                NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
                NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
                this.setSelectedNovedad(segDetNovedad);
                this.setNovedadEvaluacion(novedadEvaluacionDao.obtenerEvaluacionNovedad(segDetNovedad));
                if(this.getNovedadEvaluacion() !=null){
                    if(this.getNovedadEvaluacion().getNEstado() == null){
                        this.getNovedadEvaluacion().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE_ANALISIS"))));
                    }
                    List detalles = novedadEvaluacionDetalleDao.obtenerListaDetalleEvaluacionNovedad(this.getNovedadEvaluacion());
                    this.getNovedadEvaluacion().setSegDetNovevalDetalles(detalles);
                }else{
                    this.setNovedadEvaluacion(new SegDetNovEvaluacion());
                }
                this.setAction("Richfaces.showModalPanel('evalNovDlg')");
            }else{  // Si el riesgo es una Inspección Presencial.
                InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
                SegDetInsPresencialId segDetInsPresencialId = new SegDetInsPresencialId();
                segDetInsPresencialId.setNCodEmpresa(segDetRiesgo.getId().getNCodEmpresa());
                segDetInsPresencialId.setNCodInspresencial(segDetRiesgo.getId().getNCodRiesgo());
                SegDetInsPresencial segDetInsPresencial = new SegDetInsPresencial();
                segDetInsPresencial.setId(segDetInsPresencialId);
                segDetInsPresencial = insPresencialDao.obtenerInspeccionPresencialById(segDetInsPresencial);
                InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
                InspreEvaluacionDetalleDao inspreEvaluacionDetalleDao =(InspreEvaluacionDetalleDao)ServiceFinder.findBean("InspreEvaluacionDetalleDao");
                this.setSelectedInsPresencial(segDetInsPresencial);
                this.setInspeccionEvaluacion(inspreEvaluacionDao.obtenerEvaluacionInspeccion(segDetInsPresencial));
                if(this.getInspeccionEvaluacion() !=null){
                    if(this.getInspeccionEvaluacion().getNEstado() == null){
                        this.getInspeccionEvaluacion().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE_ANALISIS"))));
                    }
                    List detalles = inspreEvaluacionDetalleDao.obtenerListaDetalleEvaluacionInspeccion(this.getInspeccionEvaluacion());
                    this.getInspeccionEvaluacion().setSegDetInspreevalDetalles(detalles);
                }else{
                    this.setInspeccionEvaluacion(new SegDetInspreEvaluacion());
                }
                this.setAction("Richfaces.showModalPanel('evalInsDlg')");
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarEvaluacionNovedad(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.getNovedadEvaluacion().getNOcurrencia() != null){
                if(this.getNovedadEvaluacion().getNImpacto() != null){
                    if(this.diagnostico != null && !"".equals(this.diagnostico.trim())){
                        if(this.recomendacion != null && !"".equals(this.recomendacion.trim())){
                            bundle = ResourceBundle.getBundle(Parameters.getParameters());
                            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
                            NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
                            SegDetNovEvaluacionId segDetNovEvaluacionId = new SegDetNovEvaluacionId();
                            SegDetNovEvaluacion segDetNovEvaluacion = new SegDetNovEvaluacion();
                            if(this.getNovedadEvaluacion().getDFecCreacion() != null){
                                this.getNovedadEvaluacion().setDFecModificacion(new Date());
                                this.getNovedadEvaluacion().setVUsuModificacion(usuarioSession.getVUsuario());
                                this.getNovedadEvaluacion().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                                novedadEvaluacionDao.registrarEvaluacion(this.getNovedadEvaluacion());
                            }else{
                                segDetNovEvaluacionId.setNCodEvaluacion(BigDecimal.valueOf(novedadEvaluacionDao.nextSequenceValue().longValue()));
                                segDetNovEvaluacionId.setNCodNovedad(this.getSelectedNovedad().getId().getNCodNovedad());
                                segDetNovEvaluacionId.setNCodEmpresa(this.getSelectedNovedad().getId().getNCodEmpresa());
                                segDetNovEvaluacion.setId(segDetNovEvaluacionId);
                                segDetNovEvaluacion.setNImpacto(this.getNovedadEvaluacion().getNImpacto() != null ? this.getNovedadEvaluacion().getNImpacto() : null);
                                segDetNovEvaluacion.setNOcurrencia(this.getNovedadEvaluacion().getNOcurrencia() != null ? this.getNovedadEvaluacion().getNOcurrencia() : null);
                                segDetNovEvaluacion.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_EVALUACION"))));
                                segDetNovEvaluacion.setDFecCreacion(new Date());
                                segDetNovEvaluacion.setVUsuCreacion(usuarioSession.getVUsuario());
                                segDetNovEvaluacion.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                                novedadEvaluacionDao.registrarEvaluacion(segDetNovEvaluacion);
                            }

                            SegDetNovevalDetalleId segDetNovevalDetalleId = new SegDetNovevalDetalleId();
                            segDetNovevalDetalleId.setNCodDetalle(BigDecimal.valueOf(novedadEvaluacionDetalleDao.nextSequenceValue().longValue()));
                            if(this.getNovedadEvaluacion().getDFecCreacion() != null){
                                segDetNovevalDetalleId.setNCodEvaluacion(this.getNovedadEvaluacion().getNCodEvaluacion());
                                segDetNovevalDetalleId.setNCodNovedad(this.getNovedadEvaluacion().getNCodNovedad());
                                segDetNovevalDetalleId.setNCodEmpresa(this.getNovedadEvaluacion().getNCodEmpresa());
                            }else{
                                segDetNovevalDetalleId.setNCodEvaluacion(segDetNovEvaluacionId.getNCodEvaluacion());
                                segDetNovevalDetalleId.setNCodNovedad(segDetNovEvaluacionId.getNCodNovedad());
                                segDetNovevalDetalleId.setNCodEmpresa(segDetNovEvaluacionId.getNCodEmpresa());
                            }
                            SegDetNovevalDetalle segDetNovevalDetalle = new SegDetNovevalDetalle();
                            segDetNovevalDetalle.setId(segDetNovevalDetalleId);
                            segDetNovevalDetalle.setDFechora(new Date());
                            segDetNovevalDetalle.setVDiagnostico(this.diagnostico != null ? this.diagnostico : null);
                            segDetNovevalDetalle.setVRecomendacion(this.recomendacion != null ? this.recomendacion : null);
                            segDetNovevalDetalle.setDFecCreacion(new Date());
                            segDetNovevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetNovevalDetalle.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                            novedadEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetNovevalDetalle);
                            this.setDiagnostico(null);
                            this.setRecomendacion(null);
                            this.setAction("Richfaces.hideModalPanel('evalNovDlg')");
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese una recomendación.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese su diagnostico.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique cuál es el impacto.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique la probabilidad de ocurrencia.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarCerrarEvaluacionNovedad(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.getNovedadEvaluacion().getNOcurrencia() != null){
                if(this.getNovedadEvaluacion().getNImpacto() != null){
                    if(this.diagnostico != null && !"".equals(this.diagnostico.trim())){
                        if(this.recomendacion != null && !"".equals(this.recomendacion.trim())){
                            bundle = ResourceBundle.getBundle(Parameters.getParameters());
                            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
                            NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
                            SegDetNovEvaluacionId segDetNovEvaluacionId = new SegDetNovEvaluacionId();
                            SegDetNovEvaluacion segDetNovEvaluacion = new SegDetNovEvaluacion();
                            if(this.getNovedadEvaluacion() != null){
                                this.getNovedadEvaluacion().setDFecModificacion(new Date());
                                this.getNovedadEvaluacion().setVUsuModificacion(usuarioSession.getVUsuario());
                                this.getNovedadEvaluacion().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                                novedadEvaluacionDao.registrarEvaluacion(this.getNovedadEvaluacion());
                            }else{
                                segDetNovEvaluacionId.setNCodEvaluacion(BigDecimal.valueOf(novedadEvaluacionDao.nextSequenceValue().longValue()));
                                segDetNovEvaluacionId.setNCodNovedad(this.getSelectedNovedad().getId().getNCodNovedad());
                                segDetNovEvaluacionId.setNCodEmpresa(this.getSelectedNovedad().getId().getNCodEmpresa());
                                segDetNovEvaluacion.setId(segDetNovEvaluacionId);
                                segDetNovEvaluacion.setNImpacto(this.getNovedadEvaluacion().getNImpacto() != null ? this.getNovedadEvaluacion().getNImpacto() : null);
                                segDetNovEvaluacion.setNOcurrencia(this.getNovedadEvaluacion().getNOcurrencia() != null ? this.getNovedadEvaluacion().getNOcurrencia() : null);
                                segDetNovEvaluacion.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_SOLUCIONADA"))));
                                segDetNovEvaluacion.setDFecCreacion(new Date());
                                segDetNovEvaluacion.setVUsuCreacion(usuarioSession.getVUsuario());
                                segDetNovEvaluacion.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                                novedadEvaluacionDao.registrarEvaluacion(segDetNovEvaluacion);
                            }

                            SegDetNovevalDetalleId segDetNovevalDetalleId = new SegDetNovevalDetalleId();
                            segDetNovevalDetalleId.setNCodDetalle(BigDecimal.valueOf(novedadEvaluacionDetalleDao.nextSequenceValue().longValue()));
                            if(this.getNovedadEvaluacion().getDFecCreacion() != null){
                                segDetNovevalDetalleId.setNCodEvaluacion(this.getNovedadEvaluacion().getNCodEvaluacion());
                                segDetNovevalDetalleId.setNCodNovedad(this.getNovedadEvaluacion().getNCodNovedad());
                                segDetNovevalDetalleId.setNCodEmpresa(this.getNovedadEvaluacion().getNCodEmpresa());
                            }else{
                                segDetNovevalDetalleId.setNCodEvaluacion(segDetNovEvaluacionId.getNCodEvaluacion());
                                segDetNovevalDetalleId.setNCodNovedad(segDetNovEvaluacionId.getNCodNovedad());
                                segDetNovevalDetalleId.setNCodEmpresa(segDetNovEvaluacionId.getNCodEmpresa());
                            }
                            SegDetNovevalDetalle segDetNovevalDetalle = new SegDetNovevalDetalle();
                            segDetNovevalDetalle.setId(segDetNovevalDetalleId);
                            segDetNovevalDetalle.setDFechora(new Date());
                            segDetNovevalDetalle.setVDiagnostico(this.diagnostico != null ? this.diagnostico : null);
                            segDetNovevalDetalle.setVRecomendacion(this.recomendacion != null ? this.recomendacion : null);
                            segDetNovevalDetalle.setDFecCreacion(new Date());
                            segDetNovevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetNovevalDetalle.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                            novedadEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetNovevalDetalle);
                            this.setDiagnostico(null);
                            this.setRecomendacion(null);
                            this.setAction("Richfaces.hideModalPanel('evalNovDlg')");
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese una recomendación.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese su diagnostico.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique cuál es el impacto.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique la probabilidad de ocurrencia.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarEvaluacionInspeccion(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.getInspeccionEvaluacion().getNOcurrencia() != null){
                if(this.getInspeccionEvaluacion().getNImpacto() != null){
                    if(this.getDiagnostico() != null && !"".equals(this.diagnostico.trim())){
                        if(this.getRecomendacion() != null && !"".equals(this.recomendacion.trim())){
                            bundle = ResourceBundle.getBundle(Parameters.getParameters());
                            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                            InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
                            InspreEvaluacionDetalleDao inspreEvaluacionDetalleDao =(InspreEvaluacionDetalleDao)ServiceFinder.findBean("InspreEvaluacionDetalleDao");
                            SegDetInspreEvaluacionId segDetInspreEvaluacionId = new SegDetInspreEvaluacionId();
                            SegDetInspreEvaluacion segDetInspreEvaluacion = new SegDetInspreEvaluacion();
                            if(this.getInspeccionEvaluacion().getDFecCreacion() != null){
                                this.getInspeccionEvaluacion().setDFecModificacion(new Date());
                                this.getInspeccionEvaluacion().setVUsuModificacion(usuarioSession.getVUsuario());
                                this.getInspeccionEvaluacion().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                                inspreEvaluacionDao.registrarEvaluacion(this.getInspeccionEvaluacion());
                            }else{
                                segDetInspreEvaluacionId.setNCodEvaluacion(BigDecimal.valueOf(inspreEvaluacionDao.nextSequenceValue().longValue()));
                                segDetInspreEvaluacionId.setNCodInspresencial(this.getSelectedInsPresencial().getId().getNCodInspresencial());
                                segDetInspreEvaluacionId.setNCodEmpresa(this.getSelectedInsPresencial().getId().getNCodEmpresa());
                                segDetInspreEvaluacion.setId(segDetInspreEvaluacionId);
                                segDetInspreEvaluacion.setNImpacto(this.getInspeccionEvaluacion().getNImpacto() != null ? this.getInspeccionEvaluacion().getNImpacto() : null);
                                segDetInspreEvaluacion.setNOcurrencia(this.getInspeccionEvaluacion().getNOcurrencia() != null ? this.getInspeccionEvaluacion().getNOcurrencia() : null);
                                segDetInspreEvaluacion.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_EVALUACION"))));
                                segDetInspreEvaluacion.setDFecCreacion(new Date());
                                segDetInspreEvaluacion.setVUsuCreacion(usuarioSession.getVUsuario());
                                segDetInspreEvaluacion.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                                inspreEvaluacionDao.registrarEvaluacion(segDetInspreEvaluacion);
                            }

                            SegDetInspreevalDetalleId segDetInspreevalDetalleId = new SegDetInspreevalDetalleId();
                            segDetInspreevalDetalleId.setNCodDetalle(BigDecimal.valueOf(inspreEvaluacionDetalleDao.nextSequenceValue().longValue()));
                            if(this.getInspeccionEvaluacion().getDFecCreacion() != null){
                                segDetInspreevalDetalleId.setNCodEvaluacion(this.getInspeccionEvaluacion().getNCodEvaluacion());
                                segDetInspreevalDetalleId.setNCodInspresencial(this.getInspeccionEvaluacion().getNCodInspresencial());
                                segDetInspreevalDetalleId.setNCodEmpresa(this.getInspeccionEvaluacion().getNCodEmpresa());
                            }else{
                                segDetInspreevalDetalleId.setNCodEvaluacion(segDetInspreEvaluacionId.getNCodEvaluacion());
                                segDetInspreevalDetalleId.setNCodInspresencial(segDetInspreEvaluacionId.getNCodInspresencial());
                                segDetInspreevalDetalleId.setNCodEmpresa(segDetInspreEvaluacionId.getNCodEmpresa());
                            }
                            SegDetInspreevalDetalle segDetInspreevalDetalle = new SegDetInspreevalDetalle();
                            segDetInspreevalDetalle.setId(segDetInspreevalDetalleId);
                            segDetInspreevalDetalle.setDFechora(new Date());
                            segDetInspreevalDetalle.setVDiagnostico(this.getDiagnostico() != null ? this.getDiagnostico() : null);
                            segDetInspreevalDetalle.setVRecomendacion(this.getRecomendacion() != null ? this.getRecomendacion() : null);
                            segDetInspreevalDetalle.setDFecCreacion(new Date());
                            segDetInspreevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetInspreevalDetalle.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                            inspreEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetInspreevalDetalle);
                            this.setDiagnostico(null);
                            this.setRecomendacion(null);
                            this.setAction("Richfaces.hideModalPanel('evalInsDlg')");
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese una recomendación.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese su diagnostico.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique cuál es el impacto.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique la probabilidad de ocurrencia.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarCerrarEvaluacionInspeccion(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.getInspeccionEvaluacion().getNOcurrencia() != null){
                if(this.getInspeccionEvaluacion().getNImpacto() != null){
                    if(this.getDiagnostico() != null && !"".equals(this.diagnostico.trim())){
                        if(this.getRecomendacion() != null && !"".equals(this.recomendacion.trim())){
                            bundle = ResourceBundle.getBundle(Parameters.getParameters());
                            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                            InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
                            InspreEvaluacionDetalleDao inspreEvaluacionDetalleDao =(InspreEvaluacionDetalleDao)ServiceFinder.findBean("InspreEvaluacionDetalleDao");
                            SegDetInspreEvaluacionId segDetInspreEvaluacionId = new SegDetInspreEvaluacionId();
                            SegDetInspreEvaluacion segDetInspreEvaluacion = new SegDetInspreEvaluacion();
                            if(this.getInspeccionEvaluacion().getDFecCreacion() != null){
                                this.getInspeccionEvaluacion().setDFecModificacion(new Date());
                                this.getInspeccionEvaluacion().setVUsuModificacion(usuarioSession.getVUsuario());
                                this.getInspeccionEvaluacion().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                                inspreEvaluacionDao.registrarEvaluacion(this.getInspeccionEvaluacion());
                            }else{
                                segDetInspreEvaluacionId.setNCodEvaluacion(BigDecimal.valueOf(inspreEvaluacionDao.nextSequenceValue().longValue()));
                                segDetInspreEvaluacionId.setNCodInspresencial(this.getSelectedInsPresencial().getId().getNCodInspresencial());
                                segDetInspreEvaluacionId.setNCodEmpresa(this.getSelectedInsPresencial().getId().getNCodEmpresa());
                                segDetInspreEvaluacion.setId(segDetInspreEvaluacionId);
                                segDetInspreEvaluacion.setNImpacto(this.getInspeccionEvaluacion().getNImpacto() != null ? this.getInspeccionEvaluacion().getNImpacto() : null);
                                segDetInspreEvaluacion.setNOcurrencia(this.getInspeccionEvaluacion().getNOcurrencia() != null ? this.getInspeccionEvaluacion().getNOcurrencia() : null);
                                segDetInspreEvaluacion.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_SOLUCIONADA"))));
                                segDetInspreEvaluacion.setDFecCreacion(new Date());
                                segDetInspreEvaluacion.setVUsuCreacion(usuarioSession.getVUsuario());
                                segDetInspreEvaluacion.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                                inspreEvaluacionDao.registrarEvaluacion(segDetInspreEvaluacion);
                            }

                            SegDetInspreevalDetalleId segDetInspreevalDetalleId = new SegDetInspreevalDetalleId();
                            segDetInspreevalDetalleId.setNCodDetalle(BigDecimal.valueOf(inspreEvaluacionDetalleDao.nextSequenceValue().longValue()));
                            if(this.getInspeccionEvaluacion().getDFecCreacion() != null){
                                segDetInspreevalDetalleId.setNCodEvaluacion(this.getInspeccionEvaluacion().getNCodEvaluacion());
                                segDetInspreevalDetalleId.setNCodInspresencial(this.getInspeccionEvaluacion().getNCodInspresencial());
                                segDetInspreevalDetalleId.setNCodEmpresa(this.getInspeccionEvaluacion().getNCodEmpresa());
                            }else{
                                segDetInspreevalDetalleId.setNCodEvaluacion(segDetInspreEvaluacionId.getNCodEvaluacion());
                                segDetInspreevalDetalleId.setNCodInspresencial(segDetInspreEvaluacionId.getNCodInspresencial());
                                segDetInspreevalDetalleId.setNCodEmpresa(segDetInspreEvaluacionId.getNCodEmpresa());
                            }
                            SegDetInspreevalDetalle segDetInspreevalDetalle = new SegDetInspreevalDetalle();
                            segDetInspreevalDetalle.setId(segDetInspreevalDetalleId);
                            segDetInspreevalDetalle.setDFechora(new Date());
                            segDetInspreevalDetalle.setVDiagnostico(this.getDiagnostico() != null ? this.getDiagnostico() : null);
                            segDetInspreevalDetalle.setVRecomendacion(this.getRecomendacion() != null ? this.getRecomendacion() : null);
                            segDetInspreevalDetalle.setDFecCreacion(new Date());
                            segDetInspreevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetInspreevalDetalle.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                            inspreEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetInspreevalDetalle);
                            this.setDiagnostico(null);
                            this.setRecomendacion(null);
                            this.setAction("Richfaces.hideModalPanel('evalInsDlg')");
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese una recomendación.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese su diagnostico.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique cuál es el impacto.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique la probabilidad de ocurrencia.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void reabrirEvaluacion(ActionEvent actionEvent){
        ResourceBundle bundle;
        try{
            String rowKey = BaseBean.getRequestParameter("rowKey");
            SegDetRiesgo segDetRiesgo = this.getListaRiesgo().get(Integer.parseInt(rowKey));
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            if(segDetRiesgo.getId().getNTipoRiesgo().equals(BigDecimal.ONE)){
                NovedadDao novedadDao =(NovedadDao)ServiceFinder.findBean("NovedadDao");
                SegDetNovedadId segDetNovedadId = new SegDetNovedadId();
                segDetNovedadId.setNCodEmpresa(segDetRiesgo.getId().getNCodEmpresa());
                segDetNovedadId.setNCodNovedad(segDetRiesgo.getId().getNCodRiesgo());
                SegDetNovedad segDetNovedad = new SegDetNovedad();
                segDetNovedad.setId(segDetNovedadId);
                segDetNovedad = novedadDao.obtenerNovedadById(segDetNovedad);
                NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
                NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
                this.setSelectedNovedad(segDetNovedad);
                this.setNovedadEvaluacion(novedadEvaluacionDao.obtenerEvaluacionNovedad(segDetNovedad));
                if(this.getNovedadEvaluacion() !=null){
                    List detalles = novedadEvaluacionDetalleDao.obtenerListaDetalleEvaluacionNovedad(this.getNovedadEvaluacion());
                    this.getNovedadEvaluacion().setSegDetNovevalDetalles(detalles);
                }
                this.getNovedadEvaluacion().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_EVALUACION"))));
                this.getNovedadEvaluacion().setDFecModificacion(new Date());
                this.getNovedadEvaluacion().setVUsuModificacion(usuarioSession.getVUsuario());
                this.getNovedadEvaluacion().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                novedadEvaluacionDao.registrarEvaluacion(this.getNovedadEvaluacion());
                SegDetNovevalDetalleId segDetNovevalDetalleId = new SegDetNovevalDetalleId();
                segDetNovevalDetalleId.setNCodDetalle(BigDecimal.valueOf(novedadEvaluacionDetalleDao.nextSequenceValue().longValue()));
                segDetNovevalDetalleId.setNCodEvaluacion(this.getNovedadEvaluacion().getId().getNCodEvaluacion());
                segDetNovevalDetalleId.setNCodNovedad(this.getNovedadEvaluacion().getId().getNCodNovedad());
                segDetNovevalDetalleId.setNCodEmpresa(this.getNovedadEvaluacion().getId().getNCodEmpresa());
                SegDetNovevalDetalle segDetNovevalDetalle = new SegDetNovevalDetalle();
                segDetNovevalDetalle.setId(segDetNovevalDetalleId);
                segDetNovevalDetalle.setDFechora(new Date());
                segDetNovevalDetalle.setVDiagnostico("RIESGO REABIERTO.");
                segDetNovevalDetalle.setVRecomendacion("NINGUNA RECOMENDACIÓN");
                segDetNovevalDetalle.setDFecCreacion(new Date());
                segDetNovevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetNovevalDetalle.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                novedadEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetNovevalDetalle);
            }else{
                InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
                SegDetInsPresencialId segDetInsPresencialId = new SegDetInsPresencialId();
                segDetInsPresencialId.setNCodEmpresa(segDetRiesgo.getId().getNCodEmpresa());
                segDetInsPresencialId.setNCodInspresencial(segDetRiesgo.getId().getNCodRiesgo());
                SegDetInsPresencial segDetInsPresencial = new SegDetInsPresencial();
                segDetInsPresencial.setId(segDetInsPresencialId);
                segDetInsPresencial = insPresencialDao.obtenerInspeccionPresencialById(segDetInsPresencial);
                InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
                InspreEvaluacionDetalleDao inspreEvaluacionDetalleDao =(InspreEvaluacionDetalleDao)ServiceFinder.findBean("InspreEvaluacionDetalleDao");
                this.setSelectedInsPresencial(segDetInsPresencial);
                this.setInspeccionEvaluacion(inspreEvaluacionDao.obtenerEvaluacionInspeccion(segDetInsPresencial));
                if(this.getInspeccionEvaluacion() !=null){
                    List detalles = inspreEvaluacionDetalleDao.obtenerListaDetalleEvaluacionInspeccion(this.getInspeccionEvaluacion());
                    this.getInspeccionEvaluacion().setSegDetInspreevalDetalles(detalles);
                }
                this.getInspeccionEvaluacion().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_EVALUACION"))));
                this.getInspeccionEvaluacion().setDFecModificacion(new Date());
                this.getInspeccionEvaluacion().setVUsuModificacion(usuarioSession.getVUsuario());
                this.getInspeccionEvaluacion().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                inspreEvaluacionDao.registrarEvaluacion(this.getInspeccionEvaluacion());
                SegDetInspreevalDetalleId segDetInspreevalDetalleId = new SegDetInspreevalDetalleId();
                segDetInspreevalDetalleId.setNCodDetalle(BigDecimal.valueOf(inspreEvaluacionDetalleDao.nextSequenceValue().longValue()));
                segDetInspreevalDetalleId.setNCodEvaluacion(this.getInspeccionEvaluacion().getId().getNCodEvaluacion());
                segDetInspreevalDetalleId.setNCodInspresencial(this.getInspeccionEvaluacion().getId().getNCodInspresencial());
                segDetInspreevalDetalleId.setNCodEmpresa(this.getInspeccionEvaluacion().getId().getNCodEmpresa());
                SegDetInspreevalDetalle segDetInspreevalDetalle = new SegDetInspreevalDetalle();
                segDetInspreevalDetalle.setId(segDetInspreevalDetalleId);
                segDetInspreevalDetalle.setDFechora(new Date());
                segDetInspreevalDetalle.setVDiagnostico("RIESGO REABIERTO.");
                segDetInspreevalDetalle.setVRecomendacion("NINGUNA RECOMENDACIÓN");
                segDetInspreevalDetalle.setDFecCreacion(new Date());
                segDetInspreevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetInspreevalDetalle.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                inspreEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetInspreevalDetalle);
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
}
