/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import pe.com.segrop.sgsapp.dao.AreaDao;
import pe.com.segrop.sgsapp.dao.CargoDao;
import pe.com.segrop.sgsapp.dao.InsPreAccionDao;
import pe.com.segrop.sgsapp.dao.InsPresencialDao;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDao;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.dao.LocalDao;
import pe.com.segrop.sgsapp.dao.LugarDao;
import pe.com.segrop.sgsapp.dao.NovedadDao;
import pe.com.segrop.sgsapp.dao.ResponsableDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetAreaId;
import pe.com.segrop.sgsapp.domain.SegDetCargo;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencialId;
import pe.com.segrop.sgsapp.domain.SegDetInspreAcciones;
import pe.com.segrop.sgsapp.domain.SegDetInspreAccionesId;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacionId;
import pe.com.segrop.sgsapp.domain.SegDetInspreevalDetalle;
import pe.com.segrop.sgsapp.domain.SegDetInspreevalDetalleId;
import pe.com.segrop.sgsapp.domain.SegDetLocal;
import pe.com.segrop.sgsapp.domain.SegDetLocalId;
import pe.com.segrop.sgsapp.domain.SegDetLugar;
import pe.com.segrop.sgsapp.domain.SegDetLugarId;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;
import pe.com.segrop.sgsapp.domain.SegDetNovedadId;
import pe.com.segrop.sgsapp.domain.SegDetResponsable;
import pe.com.segrop.sgsapp.domain.SegDetResponsableId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class PresencialMB implements Serializable{

    private BigDecimal searchTipoInspeccion;
    private BigDecimal searchNovedad;
    private BigDecimal searchLocal;
    private BigDecimal searchArea;
    private BigDecimal searchLugar;
    private BigDecimal searchResponsable;
    private BigDecimal searchCargo;
    private Boolean persona;
    private Boolean activo;
    private Boolean proceso;
    private BigDecimal tipoInspeccion;
    private BigDecimal novedad;
    private boolean disabledNovedad;
    private Date fechaHora;
    private String descBreve;
    private String descripcion;
    private BigDecimal local;
    private BigDecimal area;
    private BigDecimal lugar;
    private BigDecimal responsable;
    private BigDecimal cargo;
    private String accionTomada;
    private String cumpleControles;
    private String controlAdicional;
    private String controlesAdicionales;
    private String analisis;
    private String seguimiento;
    private boolean visualizar;
    private boolean listaAccionesVacia;
    private SegDetInsPresencial selectedInsPresencial;
    private List<SegDetInsPresencial> listaInsPresencial;
    private List<SegDetInspreAcciones> listaInspreAcciones;
    private List<SegDetNovedad> listaNovedades;
    private SegDetInspreEvaluacion inspeccionEvaluacion;
    private BigDecimal ocurrencia;
    private BigDecimal impacto;
    private String diagnostico;
    private String recomendacion;
    private String descripcionLocal;
    private SegDetLocal selectedLocal;
    private List<SegDetLocal> listaLocales;
    private boolean disabledArea;
    private boolean renderedArea;
    private String descripcionArea;
    private SegDetArea selectedArea;
    private List<SegDetArea> listaAreas;
    private boolean disabledLugar;
    private boolean renderedLugar;
    private String descripcionLugar;
    private SegDetLugar selectedLugar;
    private List<SegDetLugar> listaLugares;
    private boolean disabledResponsable;
    private boolean renderedResponsable;
    private String nombreResponsable;
    private String apellidoResponsable;
    private SegDetResponsable selectedResponsable;
    private List<SegDetResponsable> listaResponsables;
    private String descripcionCargo;
    private SegDetCargo selectedCargo;
    private List<SegDetCargo> listaCargos;
    private String action;
    private String actionOnLoad;
    private boolean fromMatrix;
    
    /** Creates a new instance of PresencialMB */
    public PresencialMB() {
        this.selectedInsPresencial = new SegDetInsPresencial();
        this.inspeccionEvaluacion = new SegDetInspreEvaluacion();
        this.disabledArea = true;
        this.renderedArea = false;
        this.disabledLugar = true;
        this.renderedLugar = false;
        this.disabledResponsable = true;
        this.renderedResponsable = false;
        this.fromMatrix = false;
    }

    public BigDecimal getSearchTipoInspeccion() {
        return searchTipoInspeccion;
    }

    public void setSearchTipoInspeccion(BigDecimal searchTipoInspeccion) {
        this.searchTipoInspeccion = searchTipoInspeccion;
    }

    public BigDecimal getSearchNovedad() {
        return searchNovedad;
    }

    public void setSearchNovedad(BigDecimal searchNovedad) {
        this.searchNovedad = searchNovedad;
    }

    public boolean isDisabledNovedad() {
        return disabledNovedad;
    }

    public void setDisabledNovedad(boolean disabledNovedad) {
        this.disabledNovedad = disabledNovedad;
    }

    public BigDecimal getSearchLocal() {
        return searchLocal;
    }

    public void setSearchLocal(BigDecimal searchLocal) {
        this.searchLocal = searchLocal;
    }

    public BigDecimal getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(BigDecimal searchArea) {
        this.searchArea = searchArea;
    }

    public BigDecimal getSearchLugar() {
        return searchLugar;
    }

    public void setSearchLugar(BigDecimal searchLugar) {
        this.searchLugar = searchLugar;
    }

    public BigDecimal getSearchResponsable() {
        return searchResponsable;
    }

    public void setSearchResponsable(BigDecimal searchResponsable) {
        this.searchResponsable = searchResponsable;
    }

    public BigDecimal getSearchCargo() {
        return searchCargo;
    }

    public void setSearchCargo(BigDecimal searchCargo) {
        this.searchCargo = searchCargo;
    }

    public Boolean getPersona() {
        return persona;
    }

    public void setPersona(Boolean persona) {
        this.persona = persona;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getProceso() {
        return proceso;
    }

    public void setProceso(Boolean proceso) {
        this.proceso = proceso;
    }

    public BigDecimal getTipoInspeccion() {
        return tipoInspeccion;
    }

    public void setTipoInspeccion(BigDecimal tipoInspeccion) {
        this.tipoInspeccion = tipoInspeccion;
    }

    public BigDecimal getNovedad() {
        return novedad;
    }

    public void setNovedad(BigDecimal novedad) {
        this.novedad = novedad;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescBreve() {
        return descBreve;
    }

    public void setDescBreve(String descBreve) {
        this.descBreve = descBreve;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getLocal() {
        return local;
    }

    public void setLocal(BigDecimal local) {
        this.local = local;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getLugar() {
        return lugar;
    }

    public void setLugar(BigDecimal lugar) {
        this.lugar = lugar;
    }

    public BigDecimal getResponsable() {
        return responsable;
    }

    public void setResponsable(BigDecimal responsable) {
        this.responsable = responsable;
    }

    public BigDecimal getCargo() {
        return cargo;
    }

    public void setCargo(BigDecimal cargo) {
        this.cargo = cargo;
    }

    public String getAccionTomada() {
        return accionTomada;
    }

    public void setAccionTomada(String accionTomada) {
        this.accionTomada = accionTomada;
    }

    public String getCumpleControles() {
        return cumpleControles;
    }

    public void setCumpleControles(String cumpleControles) {
        this.cumpleControles = cumpleControles;
    }

    public String getControlAdicional() {
        return controlAdicional;
    }

    public void setControlAdicional(String controlAdicional) {
        this.controlAdicional = controlAdicional;
    }

    public String getControlesAdicionales() {
        return controlesAdicionales;
    }

    public void setControlesAdicionales(String controlesAdicionales) {
        this.controlesAdicionales = controlesAdicionales;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public String getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public boolean isListaAccionesVacia() {
        return listaAccionesVacia;
    }

    public void setListaAccionesVacia(boolean listaAccionesVacia) {
        this.listaAccionesVacia = listaAccionesVacia;
    }

    public SegDetInsPresencial getSelectedInsPresencial() {
        return selectedInsPresencial;
    }

    public void setSelectedInsPresencial(SegDetInsPresencial selectedInsPresencial) {
        this.selectedInsPresencial = selectedInsPresencial;
    }

    public List<SegDetInsPresencial> getListaInsPresencial() {
        return listaInsPresencial;
    }

    public void setListaInsPresencial(List<SegDetInsPresencial> listaInsPresencial) {
        this.listaInsPresencial = listaInsPresencial;
    }

    public List<SegDetInspreAcciones> getListaInspreAcciones() {
        return listaInspreAcciones;
    }

    public void setListaInspreAcciones(List<SegDetInspreAcciones> listaInspreAcciones) {
        this.listaInspreAcciones = listaInspreAcciones;
    }

    public List<SegDetNovedad> getListaNovedades() {
        return listaNovedades;
    }

    public void setListaNovedades(List<SegDetNovedad> listaNovedades) {
        this.listaNovedades = listaNovedades;
    }

    public SegDetInspreEvaluacion getInspeccionEvaluacion() {
        return inspeccionEvaluacion;
    }

    public void setInspeccionEvaluacion(SegDetInspreEvaluacion inspeccionEvaluacion) {
        this.inspeccionEvaluacion = inspeccionEvaluacion;
    }

    public BigDecimal getOcurrencia() {
        return ocurrencia;
    }

    public void setOcurrencia(BigDecimal ocurrencia) {
        this.ocurrencia = ocurrencia;
    }

    public BigDecimal getImpacto() {
        return impacto;
    }

    public void setImpacto(BigDecimal impacto) {
        this.impacto = impacto;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getDescripcionLocal() {
        return descripcionLocal;
    }

    public void setDescripcionLocal(String descripcionLocal) {
        this.descripcionLocal = descripcionLocal;
    }

    public SegDetLocal getSelectedLocal() {
        return selectedLocal;
    }

    public void setSelectedLocal(SegDetLocal selectedLocal) {
        this.selectedLocal = selectedLocal;
    }

    public List<SegDetLocal> getListaLocales() {
        return listaLocales;
    }

    public void setListaLocales(List<SegDetLocal> listaLocales) {
        this.listaLocales = listaLocales;
    }

    public boolean isDisabledArea() {
        return disabledArea;
    }

    public void setDisabledArea(boolean disabledArea) {
        this.disabledArea = disabledArea;
    }

    public boolean isRenderedArea() {
        return renderedArea;
    }

    public void setRenderedArea(boolean renderedArea) {
        this.renderedArea = renderedArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public SegDetArea getSelectedArea() {
        return selectedArea;
    }

    public void setSelectedArea(SegDetArea selectedArea) {
        this.selectedArea = selectedArea;
    }

    public List<SegDetArea> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<SegDetArea> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public boolean isDisabledLugar() {
        return disabledLugar;
    }

    public void setDisabledLugar(boolean disabledLugar) {
        this.disabledLugar = disabledLugar;
    }

    public boolean isRenderedLugar() {
        return renderedLugar;
    }

    public void setRenderedLugar(boolean renderedLugar) {
        this.renderedLugar = renderedLugar;
    }

    public String getDescripcionLugar() {
        return descripcionLugar;
    }

    public void setDescripcionLugar(String descripcionLugar) {
        this.descripcionLugar = descripcionLugar;
    }

    public SegDetLugar getSelectedLugar() {
        return selectedLugar;
    }

    public void setSelectedLugar(SegDetLugar selectedLugar) {
        this.selectedLugar = selectedLugar;
    }

    public List<SegDetLugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<SegDetLugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    public boolean isDisabledResponsable() {
        return disabledResponsable;
    }

    public void setDisabledResponsable(boolean disabledResponsable) {
        this.disabledResponsable = disabledResponsable;
    }

    public boolean isRenderedResponsable() {
        return renderedResponsable;
    }

    public void setRenderedResponsable(boolean renderedResponsable) {
        this.renderedResponsable = renderedResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public SegDetResponsable getSelectedResponsable() {
        return selectedResponsable;
    }

    public void setSelectedResponsable(SegDetResponsable selectedResponsable) {
        this.selectedResponsable = selectedResponsable;
    }

    public List<SegDetResponsable> getListaResponsables() {
        return listaResponsables;
    }

    public void setListaResponsables(List<SegDetResponsable> listaResponsables) {
        this.listaResponsables = listaResponsables;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    public SegDetCargo getSelectedCargo() {
        return selectedCargo;
    }

    public void setSelectedCargo(SegDetCargo selectedCargo) {
        this.selectedCargo = selectedCargo;
    }

    public List<SegDetCargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<SegDetCargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionOnLoad() {
        return actionOnLoad;
    }

    public void setActionOnLoad(String actionOnLoad) {
        this.actionOnLoad = actionOnLoad;
    }

    public boolean isFromMatrix() {
        return fromMatrix;
    }

    public void setFromMatrix(boolean fromMatrix) {
        this.fromMatrix = fromMatrix;
    }
    
    public void handleSelectControlAdicional(ValueChangeEvent event){
        ResourceBundle bundle;
        String ctrlAdicional = null;
        try{
            if(event != null && event.getNewValue() != null){
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                ctrlAdicional = event.getNewValue().toString();
                if(ctrlAdicional.equals(bundle.getString("ONE"))){
                    this.setVisualizar(true);
                }else{
                    this.setVisualizar(false);
                }
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void handleSelectTipoInspeccion(ValueChangeEvent event){
        ResourceBundle bundle;
        try{
            if(event != null){
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                String value = event.getNewValue().toString();
                if(value.equals(bundle.getString("TIPO_INSPECCION_ESPECIFICA"))){
                    this.setDisabledNovedad(false);
                }else{
                    this.setDisabledNovedad(true);
                    this.setNovedad(null);
                    this.setLocal(null);
                    this.setArea(null);
                    this.setLugar(null);
                    this.setResponsable(null);
                    this.setCargo(null);
                    this.setDisabledArea(true);
                    this.setRenderedArea(false);
                    this.setDisabledLugar(true);
                    this.setRenderedLugar(false);
                    this.setDisabledResponsable(true);
                    this.setRenderedResponsable(false);
                }
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void handleSelectNovedad(ValueChangeEvent event){
        try{
            NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
            SegDetNovedadId segDetNovedadId = new SegDetNovedadId();
            segDetNovedadId.setNCodNovedad(BigDecimal.valueOf(Long.parseLong(event.getNewValue().toString())));
            SegDetNovedad segDetNovedad = new SegDetNovedad();
            segDetNovedad.setId(segDetNovedadId);
            segDetNovedad = novedadDao.obtenerNovedadById(segDetNovedad);
            
            if(segDetNovedad != null){
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");

                SegDetLocalId segDetLocalId = new SegDetLocalId();
                segDetLocalId.setNCodLocal(segDetNovedad.getNLocal());
                SegDetLocal segDetLocal = new SegDetLocal();
                segDetLocal.setId(segDetLocalId);
                AreaDao areaDao =(AreaDao)ServiceFinder.findBean("AreaDao");
                listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems());

                SegDetAreaId segDetAreaId = new SegDetAreaId();
                segDetAreaId.setNCodArea(segDetNovedad.getNArea());
                SegDetArea segDetArea = new SegDetArea();
                segDetArea.setId(segDetAreaId);
                LugarDao lugarDao =(LugarDao)ServiceFinder.findBean("LugarDao");
                listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems());
                ResponsableDao responsableDao =(ResponsableDao)ServiceFinder.findBean("ResponsableDao");
                listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable","VApellidos").getItems());

                this.setLocal(segDetNovedad.getNLocal());
                this.setArea(segDetNovedad.getNArea());
                this.setLugar(segDetNovedad.getNLugar());
                this.setResponsable(segDetNovedad.getNResponsable());
                this.setCargo(segDetNovedad.getNCargo());
                this.setDisabledArea(false);
                this.setRenderedArea(true);
                this.setDisabledLugar(false);
                this.setRenderedLugar(true);
                this.setDisabledResponsable(false);
                this.setRenderedResponsable(true);
            }else{
                this.setLocal(null);
                this.setArea(null);
                this.setLugar(null);
                this.setResponsable(null);
                this.setCargo(null);
                this.setDisabledArea(true);
                this.setRenderedArea(false);
                this.setDisabledLugar(true);
                this.setRenderedLugar(false);
                this.setDisabledResponsable(true);
                this.setRenderedResponsable(false);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void getListaNovedad(ActionEvent actionEvent){
        try{
            NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
            this.setListaNovedades(novedadDao.obtenerListaNovedades());
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void handleChangeLocalValue(ActionEvent actionEvent){
        try{
            if(actionEvent != null){
//                HtmlAjaxSupport ajaxSupport = (HtmlAjaxSupport)actionEvent.getSource();
//                HtmlSelectOneMenu selectOneMenu = (HtmlSelectOneMenu)ajaxSupport.getParent();
//                BigDecimal codLocal = (BigDecimal)selectOneMenu.getValue();
//                if("-1".equals(codLocal.toString())){
//                    this.setDisabledArea(true);
//                    this.setRenderedArea(false);
//                }else{
//                    this.setDisabledArea(false);
//                    this.setRenderedArea(true);
//                }
//                this.setLocal(codLocal);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void handleChangeAreaValue(ActionEvent actionEvent){
        try{
            if(actionEvent != null){
//                HtmlAjaxSupport ajaxSupport = (HtmlAjaxSupport)actionEvent.getSource();
//                HtmlSelectOneMenu selectOneMenu = (HtmlSelectOneMenu)ajaxSupport.getParent();
//                BigDecimal codArea = (BigDecimal)selectOneMenu.getValue();
//                if("-1".equals(codArea.toString())){
//                    this.setDisabledLugar(true);
//                    this.setRenderedLugar(false);
//                    this.setDisabledResponsable(true);
//                    this.setRenderedResponsable(false);
//                }else{
//                    this.setDisabledLugar(false);
//                    this.setRenderedLugar(true);
//                    this.setDisabledResponsable(false);
//                    this.setRenderedResponsable(true);
//                }
//                this.setArea(codArea);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void buscarInspeccionPresencial(ActionEvent actionEvent) {
        try {
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
            SegDetInsPresencialId segDetInsPresencialId = new SegDetInsPresencialId();
            segDetInsPresencialId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            SegDetInsPresencial segDetInsPresencial = new SegDetInsPresencial();
            segDetInsPresencial.setId(segDetInsPresencialId);
            segDetInsPresencial.setNCodEmpresa(segDetInsPresencialId.getNCodEmpresa());
            segDetInsPresencial.setNTipoInspeccion(this.getSearchTipoInspeccion() != null ? this.getSearchTipoInspeccion() : null);
            segDetInsPresencial.setNCodNovedad(this.getSearchNovedad() != null ? this.getSearchNovedad() : null);
            segDetInsPresencial.setNLocal(this.getSearchLocal() != null ? this.getSearchLocal() : null);
            segDetInsPresencial.setNArea(this.getSearchArea() != null ? this.getSearchArea() : null);
            segDetInsPresencial.setNLugar(this.getSearchLugar() != null ? this.getSearchLugar() : null);
            segDetInsPresencial.setNResponsable(this.getSearchResponsable() != null ? this.getSearchResponsable() : null);
            segDetInsPresencial.setNCargo(this.getSearchCargo() != null ? this.getSearchCargo() : null);
            setListaInsPresencial(insPresencialDao.buscarInspeccionesPresenciales(segDetInsPresencial));
        } catch (Exception e) {
            e.getMessage();
            
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
            this.setPersona(null);
            this.setActivo(null);
            this.setProceso(null);
            this.setTipoInspeccion(null);
            this.setNovedad(null);
            this.setDisabledNovedad(true);
            this.setFechaHora(null);
            this.setDescBreve(null);
            this.setDescripcion(null);
            this.setLocal(BigDecimal.valueOf(-1));
            this.setArea(BigDecimal.valueOf(-1));
            this.setLugar(BigDecimal.valueOf(-1));
            this.setResponsable(BigDecimal.valueOf(-1));
            this.setCargo(BigDecimal.valueOf(-1));
            this.setDisabledArea(true);
            this.setRenderedArea(false);
            this.setDisabledLugar(true);
            this.setRenderedLugar(false);
            this.setDisabledResponsable(true);
            this.setRenderedResponsable(false);
            this.setAccionTomada(null);
            this.setCumpleControles(null);
            this.setControlAdicional(null);
            this.setVisualizar(false);
            this.setControlesAdicionales(null);
            this.setAnalisis(null);
            this.setSeguimiento(null);
        } catch (Exception e) {
            e.getMessage();
            
        }
    }
    
    public void registrarInspeccionPresencial(ActionEvent actionEvent) {
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
            SegDetInsPresencialId segDetInsPresencialId = new SegDetInsPresencialId();
            segDetInsPresencialId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            SegDetInsPresencial segDetInsPresencial = new SegDetInsPresencial();
            segDetInsPresencial.setId(segDetInsPresencialId);
            segDetInsPresencial.setNCodNovedad(segDetInsPresencialId.getNCodInspresencial());
            segDetInsPresencial.setNCodEmpresa(segDetInsPresencialId.getNCodEmpresa());
            segDetInsPresencial.setBPersona(this.persona != null ? this.persona : null);
            segDetInsPresencial.setBActivo(this.activo != null ? this.activo : null);
            segDetInsPresencial.setBProceso(this.proceso != null ? this.proceso : null);
            segDetInsPresencial.setNTipoInspeccion(this.tipoInspeccion != null ? this.tipoInspeccion : null);
            segDetInsPresencial.setNCodNovedad(this.novedad != null ? this.novedad : null);
            segDetInsPresencial.setDFecHora(this.fechaHora != null ? this.fechaHora : null);
            segDetInsPresencial.setVDescBreve(this.descBreve != null ? this.descBreve.toUpperCase().trim() : null);
            segDetInsPresencial.setVDescripcion(this.descripcion != null ? this.descripcion.toUpperCase().trim() : null);
            segDetInsPresencial.setNLocal(this.local != null ? this.local : null);
            segDetInsPresencial.setNArea(this.area != null ? this.area : null);
            segDetInsPresencial.setNLugar(this.lugar != null ? this.lugar : null);
            segDetInsPresencial.setNResponsable(this.responsable != null ? this.responsable : null);
            segDetInsPresencial.setNCargo(this.cargo != null ? this.cargo : null);
            segDetInsPresencial.setVAccTomadas(this.accionTomada != null ? this.accionTomada.toUpperCase().trim() : null);
            
            
//            if(segDetInsPresencial.getNTipoInspeccion().toString().equals(bundle.getString("TIPO_INSPECCION_ESPECIFICA"))){
            segDetInsPresencial.setNCumpleControl(this.cumpleControles != null ? BigDecimal.valueOf(Long.parseLong(this.cumpleControles)) : null);
            segDetInsPresencial.setNControlAdicional(this.controlAdicional != null ? BigDecimal.valueOf(Long.parseLong(this.controlAdicional)) : null);
            segDetInsPresencial.setVControlAdicional(this.controlesAdicionales != null ? this.controlesAdicionales.toUpperCase().trim() : null);
            segDetInsPresencial.setNAnalisis(this.analisis != null ? BigDecimal.valueOf(Long.parseLong(this.analisis)) : null);
            segDetInsPresencial.setNSeguimiento(this.seguimiento != null ? BigDecimal.valueOf(Long.parseLong(this.seguimiento)) : null);
            if(segDetInsPresencial.getNSeguimiento() != null){
                if(segDetInsPresencial.getNSeguimiento().equals(BigDecimal.ONE)){
                    segDetInsPresencial.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE"))));
                }else if(segDetInsPresencial.getNSeguimiento().equals(BigDecimal.ZERO)){
                    segDetInsPresencial.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
                }
            }
//            }else{
//                segDetInsPresencial.setNCumpleControl(BigDecimal.ONE);
//                segDetInsPresencial.setNControlAdicional(BigDecimal.ZERO);
//                segDetInsPresencial.setNAnalisis(BigDecimal.ZERO);
//                segDetInsPresencial.setNSeguimiento(BigDecimal.ZERO);
//                segDetInsPresencial.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
//            }
            
            if(!errorValidation(segDetInsPresencial)){
                segDetInsPresencial.getId().setNCodInspresencial(BigDecimal.valueOf(insPresencialDao.nextSequenceValue()));
                segDetInsPresencial.setDFecCreacion(new Date());
                segDetInsPresencial.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetInsPresencial.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                insPresencialDao.registrarInspeccionPresencial(segDetInsPresencial);
                this.setAction("Richfaces.hideModalPanel('dlg')");
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void toEdit(ActionEvent actionEvent){
        ResourceBundle bundle;
        String rowkey = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
            rowkey = BaseBean.getRequestParameter("rowkey");
            SegDetInsPresencial segDetInsPresencial = this.getListaInsPresencial().get(Integer.parseInt(rowkey));
            this.setSelectedInsPresencial(segDetInsPresencial);
            
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodLocal(segDetInsPresencial.getNLocal());
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            AreaDao areaDao =(AreaDao)ServiceFinder.findBean("AreaDao");
            listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems());
            
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(segDetInsPresencial.getNArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            LugarDao lugarDao =(LugarDao)ServiceFinder.findBean("LugarDao");
            listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems());
            ResponsableDao responsableDao =(ResponsableDao)ServiceFinder.findBean("ResponsableDao");
            listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable","VApellidos").getItems());
            
            if(segDetInsPresencial.getNControlAdicional().equals(BigDecimal.ONE)){
                this.setVisualizar(true);
            }else{
                this.setVisualizar(false);
                this.setControlesAdicionales(null);
                this.setAnalisis(null);
                this.setSeguimiento(null);
            }
            
            if(segDetInsPresencial.getNTipoInspeccion().toString().equals(bundle.getString("TIPO_INSPECCION_ESPECIFICA"))){
                this.setDisabledNovedad(false);
            }else{
                this.setDisabledNovedad(true);
            }
            
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarInspeccionPresencial(ActionEvent actionEvent) {
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
            SegDetInsPresencial segDetInsPresencial = this.getSelectedInsPresencial();
            segDetInsPresencial.setBPersona(segDetInsPresencial.getBPersona() != null ? segDetInsPresencial.getBPersona() : null);
            segDetInsPresencial.setBActivo(segDetInsPresencial.getBActivo() != null ? segDetInsPresencial.getBActivo() : null);
            segDetInsPresencial.setBProceso(segDetInsPresencial.getBProceso() != null ? segDetInsPresencial.getBProceso() : null);
            segDetInsPresencial.setNTipoInspeccion(segDetInsPresencial.getNTipoInspeccion() != null ? segDetInsPresencial.getNTipoInspeccion() : null);
            segDetInsPresencial.setNCodNovedad(segDetInsPresencial.getNCodNovedad() != null ? segDetInsPresencial.getNCodNovedad() : null);
            segDetInsPresencial.setDFecHora(segDetInsPresencial.getDFecHora() != null ? segDetInsPresencial.getDFecHora() : null);
            segDetInsPresencial.setVDescBreve(segDetInsPresencial.getVDescBreve() != null ? segDetInsPresencial.getVDescBreve().toUpperCase().trim() : null);
            segDetInsPresencial.setVDescripcion(segDetInsPresencial.getVDescripcion() != null ? segDetInsPresencial.getVDescripcion().toUpperCase().trim() : null);
            segDetInsPresencial.setNLocal(segDetInsPresencial.getNLocal() != null ? segDetInsPresencial.getNLocal() : null);
            segDetInsPresencial.setNArea(segDetInsPresencial.getNArea() != null ? segDetInsPresencial.getNArea() : null);
            segDetInsPresencial.setNLugar(segDetInsPresencial.getNLugar() != null ? segDetInsPresencial.getNLugar() : null);
            segDetInsPresencial.setNResponsable(segDetInsPresencial.getNResponsable() != null ? segDetInsPresencial.getNResponsable() : null);
            segDetInsPresencial.setNCargo(segDetInsPresencial.getNCargo() != null ? segDetInsPresencial.getNCargo() : null);
            segDetInsPresencial.setVAccTomadas(segDetInsPresencial.getVAccTomadas() != null ? segDetInsPresencial.getVAccTomadas().toUpperCase().trim() : null);
            
//            if(segDetInsPresencial.getNTipoInspeccion().toString().equals(bundle.getString("TIPO_INSPECCION_ESPECIFICA"))){
            segDetInsPresencial.setNCumpleControl(segDetInsPresencial.getNCumpleControl() != null ? segDetInsPresencial.getNCumpleControl() : null);
            segDetInsPresencial.setNControlAdicional(segDetInsPresencial.getNControlAdicional() != null ? segDetInsPresencial.getNControlAdicional() : null);
            segDetInsPresencial.setVControlAdicional(segDetInsPresencial.getVControlAdicional() != null ? segDetInsPresencial.getVControlAdicional().toUpperCase().trim() : null);
            segDetInsPresencial.setNAnalisis(segDetInsPresencial.getNAnalisis() != null ? segDetInsPresencial.getNAnalisis() : null);
            segDetInsPresencial.setNSeguimiento(segDetInsPresencial.getNSeguimiento() != null ? segDetInsPresencial.getNSeguimiento() : null);
            if(segDetInsPresencial.getNSeguimiento() != null){
                if(segDetInsPresencial.getNSeguimiento().equals(BigDecimal.ONE)){
                    segDetInsPresencial.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE"))));
                }else if(segDetInsPresencial.getNSeguimiento().equals(BigDecimal.ZERO)){
                    segDetInsPresencial.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
                }
            }
//            }else{
//                segDetInsPresencial.setNCumpleControl(BigDecimal.ONE);
//                segDetInsPresencial.setNControlAdicional(BigDecimal.ZERO);
//                segDetInsPresencial.setNAnalisis(BigDecimal.ZERO);
//                segDetInsPresencial.setNSeguimiento(BigDecimal.ZERO);
//                segDetInsPresencial.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
//            }
            
            if(!errorValidation(segDetInsPresencial)){
                segDetInsPresencial.setDFecModificacion(new Date());
                segDetInsPresencial.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetInsPresencial.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                insPresencialDao.registrarInspeccionPresencial(segDetInsPresencial);
                this.action = "Richfaces.hideModalPanel('editDlg')";
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void toVer(ActionEvent actionEvent){
        String rowkey = null;
        try{
            rowkey = BaseBean.getRequestParameter("rowkey");
            SegDetInsPresencial segDetInsPresencial = this.getListaInsPresencial().get(Integer.parseInt(rowkey));
            this.setSelectedInsPresencial(segDetInsPresencial);
            
            if(segDetInsPresencial.getNControlAdicional().compareTo(BigDecimal.ONE) == 0){
                this.setVisualizar(true);
            }else{
                this.setVisualizar(false);
            }
            
            InsPreAccionDao insPreAccionDao =(InsPreAccionDao)ServiceFinder.findBean("InsPreAccionDao");
            this.setListaInspreAcciones(insPreAccionDao.obtenerListaAccionesByInspeccionPresencial(segDetInsPresencial));
            if(this.getListaInspreAcciones() !=null && !this.getListaInspreAcciones().isEmpty()){
                this.setListaAccionesVacia(false);
            }else{
                this.setListaAccionesVacia(true);
            }
            
            InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
            InspreEvaluacionDetalleDao inspreEvaluacionDetalleDao =(InspreEvaluacionDetalleDao)ServiceFinder.findBean("InspreEvaluacionDetalleDao");
            this.setInspeccionEvaluacion(inspreEvaluacionDao.obtenerEvaluacionInspeccion(segDetInsPresencial));
            if(this.getInspeccionEvaluacion() !=null){
                List<SegDetInspreevalDetalle> detalles = inspreEvaluacionDetalleDao.obtenerListaDetalleEvaluacionInspeccion(this.getInspeccionEvaluacion());
                this.getInspeccionEvaluacion().setSegDetInspreevalDetalles(detalles);
            }else{
                this.setInspeccionEvaluacion(new SegDetInspreEvaluacion());
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void toSeguimiento(ActionEvent actionEvent){
        String rowkey = null;
        try{
            rowkey = BaseBean.getRequestParameter("rowkey");
            SegDetInsPresencial segDetInsPresencial = this.getListaInsPresencial().get(Integer.parseInt(rowkey));
            this.setSelectedInsPresencial(segDetInsPresencial);
            
            InsPreAccionDao insPreAccionDao =(InsPreAccionDao)ServiceFinder.findBean("InsPreAccionDao");
            this.setListaInspreAcciones(insPreAccionDao.obtenerListaAccionesByInspeccionPresencial(segDetInsPresencial));
            if(this.getListaInspreAcciones() !=null && !this.getListaInspreAcciones().isEmpty()){
                this.setListaAccionesVacia(false);
            }else{
                this.setListaAccionesVacia(true);
            }
            this.setAccionTomada(null);
            
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarSeguimiento(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.accionTomada != null && !"".equals(this.accionTomada.trim())){
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
                InsPreAccionDao insPreAccionDao =(InsPreAccionDao)ServiceFinder.findBean("InsPreAccionDao");
                SegDetInspreAccionesId segDetInspreAccionesId = new SegDetInspreAccionesId();
                segDetInspreAccionesId.setNCodAccion(BigDecimal.valueOf(insPreAccionDao.nextSequenceValue()));
                segDetInspreAccionesId.setNCodInspresencial(this.getSelectedInsPresencial().getId().getNCodInspresencial());
                segDetInspreAccionesId.setNCodEmpresa(this.getSelectedInsPresencial().getId().getNCodEmpresa());
                SegDetInspreAcciones segDetInspreAcciones = new SegDetInspreAcciones();
                segDetInspreAcciones.setId(segDetInspreAccionesId);
                segDetInspreAcciones.setNCodAccion(segDetInspreAccionesId.getNCodAccion());
                segDetInspreAcciones.setNCodInspresencial(segDetInspreAccionesId.getNCodInspresencial());
                segDetInspreAcciones.setNCodEmpresa(segDetInspreAccionesId.getNCodEmpresa());
                segDetInspreAcciones.setDFecHora(new Date());
                segDetInspreAcciones.setVDescripcion(this.accionTomada != null ? this.accionTomada.toUpperCase().trim() : null);
                segDetInspreAcciones.setDFecCreacion(new Date());
                segDetInspreAcciones.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetInspreAcciones.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                insPreAccionDao.registrarAccion(segDetInspreAcciones);
                
                if(this.getListaInsPresencial().contains(this.getSelectedInsPresencial())){
                    int index = this.getListaInsPresencial().indexOf(this.getSelectedInsPresencial());
                    this.getSelectedInsPresencial().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_SEGUIMIENTO"))));
                    this.getSelectedInsPresencial().setNSeguimiento(BigDecimal.ONE);
                    this.getSelectedInsPresencial().setDFecModificacion(new Date());
                    this.getSelectedInsPresencial().setVUsuModificacion(usuarioSession.getVUsuario());
                    this.getSelectedInsPresencial().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                    insPresencialDao.registrarInspeccionPresencial(this.getSelectedInsPresencial());
                    this.getListaInsPresencial().set(index, this.getSelectedInsPresencial());
                }
                this.setAccionTomada(null);
                if(this.isFromMatrix()){
                    this.setAction("document.getElementById('hiddenForm:hiddenBtn').click();");
                }else{
                    this.setAction("Richfaces.hideModalPanel('segDlg')");
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la nueva acción a tomar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarCerrarSeguimiento(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.accionTomada != null && !"".equals(this.accionTomada.trim())){
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                InsPresencialDao insPresencialDao = (InsPresencialDao) ServiceFinder.findBean("InsPresencialDao");
                InsPreAccionDao insPreAccionDao =(InsPreAccionDao)ServiceFinder.findBean("InsPreAccionDao");
                SegDetInspreAccionesId segDetInspreAccionesId = new SegDetInspreAccionesId();
                segDetInspreAccionesId.setNCodAccion(BigDecimal.valueOf(insPreAccionDao.nextSequenceValue()));
                segDetInspreAccionesId.setNCodInspresencial(this.getSelectedInsPresencial().getId().getNCodInspresencial());
                segDetInspreAccionesId.setNCodEmpresa(this.getSelectedInsPresencial().getId().getNCodEmpresa());
                SegDetInspreAcciones segDetInspreAcciones = new SegDetInspreAcciones();
                segDetInspreAcciones.setId(segDetInspreAccionesId);
                segDetInspreAcciones.setNCodAccion(segDetInspreAccionesId.getNCodAccion());
                segDetInspreAcciones.setNCodInspresencial(segDetInspreAccionesId.getNCodInspresencial());
                segDetInspreAcciones.setNCodEmpresa(segDetInspreAccionesId.getNCodEmpresa());
                segDetInspreAcciones.setDFecHora(new Date());
                segDetInspreAcciones.setVDescripcion(this.accionTomada != null ? this.accionTomada.toUpperCase().trim() : null);
                segDetInspreAcciones.setDFecCreacion(new Date());
                segDetInspreAcciones.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetInspreAcciones.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                insPreAccionDao.registrarAccion(segDetInspreAcciones);
                
                if(this.getListaInsPresencial().contains(this.getSelectedInsPresencial())){
                    int index = this.getListaInsPresencial().indexOf(this.getSelectedInsPresencial());
                    this.getSelectedInsPresencial().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
                    this.getSelectedInsPresencial().setNSeguimiento(BigDecimal.ZERO);
                    this.getSelectedInsPresencial().setDFecModificacion(new Date());
                    this.getSelectedInsPresencial().setVUsuModificacion(usuarioSession.getVUsuario());
                    this.getSelectedInsPresencial().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                    insPresencialDao.registrarInspeccionPresencial(this.getSelectedInsPresencial());
                    this.getListaInsPresencial().set(index, this.getSelectedInsPresencial());
                }
                this.setAccionTomada(null);
                if(this.isFromMatrix()){
                    this.setAction("document.getElementById('hiddenForm:hiddenBtn').click();");
                }else{
                    this.setAction("Richfaces.hideModalPanel('segDlg')");
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la nueva acción a tomar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public String toMatrix(){
        return "matriz";
    }
    
    public void toEvaluacion(ActionEvent actionEvent){
        ResourceBundle bundle;
        String rowkey = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rowkey = BaseBean.getRequestParameter("rowkey");
            SegDetInsPresencial segDetInsPresencial = this.getListaInsPresencial().get(Integer.parseInt(rowkey));
            this.setSelectedInsPresencial(segDetInsPresencial);
            
            InspreEvaluacionDao inspreEvaluacionDao =(InspreEvaluacionDao)ServiceFinder.findBean("InspreEvaluacionDao");
            InspreEvaluacionDetalleDao inspreEvaluacionDetalleDao =(InspreEvaluacionDetalleDao)ServiceFinder.findBean("InspreEvaluacionDetalleDao");
            this.setInspeccionEvaluacion(inspreEvaluacionDao.obtenerEvaluacionInspeccion(segDetInsPresencial));
            if(this.getInspeccionEvaluacion() !=null){
                if(this.getInspeccionEvaluacion().getNEstado() == null){
                    this.getInspeccionEvaluacion().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE_ANALISIS"))));
                }
                List<SegDetInspreevalDetalle> detalles = inspreEvaluacionDetalleDao.obtenerListaDetalleEvaluacionInspeccion(this.getInspeccionEvaluacion());
                this.getInspeccionEvaluacion().setSegDetInspreevalDetalles(detalles);
            }else{
                this.setInspeccionEvaluacion(new SegDetInspreEvaluacion());
            }
            this.setDiagnostico(null);
            this.setRecomendacion(null);
            
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarEvaluacion(ActionEvent actionEvent){
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
                                segDetInspreEvaluacionId.setNCodEvaluacion(BigDecimal.valueOf(inspreEvaluacionDao.nextSequenceValue()));
                                segDetInspreEvaluacionId.setNCodInspresencial(this.getSelectedInsPresencial().getId().getNCodInspresencial());
                                segDetInspreEvaluacionId.setNCodEmpresa(this.getSelectedInsPresencial().getId().getNCodEmpresa());
                                segDetInspreEvaluacion.setId(segDetInspreEvaluacionId);
                                segDetInspreEvaluacion.setNImpacto(this.getInspeccionEvaluacion().getNImpacto() != null ? this.getInspeccionEvaluacion().getNImpacto() : null);
                                segDetInspreEvaluacion.setNOcurrencia(this.getInspeccionEvaluacion().getNOcurrencia() != null ? this.getInspeccionEvaluacion().getNOcurrencia() : null);
                                segDetInspreEvaluacion.setNNivelImpacto(BigDecimal.ONE);
                                segDetInspreEvaluacion.setNNivelOcurrencia(BigDecimal.ONE);
                                segDetInspreEvaluacion.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_EVALUACION"))));
                                segDetInspreEvaluacion.setDFecCreacion(new Date());
                                segDetInspreEvaluacion.setVUsuCreacion(usuarioSession.getVUsuario());
                                segDetInspreEvaluacion.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                                inspreEvaluacionDao.registrarEvaluacion(segDetInspreEvaluacion);
                            }

                            SegDetInspreevalDetalleId segDetInspreevalDetalleId = new SegDetInspreevalDetalleId();
                            segDetInspreevalDetalleId.setNCodDetalle(BigDecimal.valueOf(inspreEvaluacionDetalleDao.nextSequenceValue()));
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
                            segDetInspreevalDetalle.setVDiagnostico(this.getDiagnostico() != null ? this.getDiagnostico().toUpperCase().trim() : null);
                            segDetInspreevalDetalle.setVRecomendacion(this.getRecomendacion() != null ? this.getRecomendacion().toUpperCase().trim() : null);
                            segDetInspreevalDetalle.setDFecCreacion(new Date());
                            segDetInspreevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetInspreevalDetalle.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                            inspreEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetInspreevalDetalle);
                            
                            this.setDiagnostico(null);
                            this.setRecomendacion(null);
                            if(this.isFromMatrix()){
                                this.setAction("document.getElementById('hiddenForm:hiddenBtn').click();");
                            }else{
                                this.setAction("Richfaces.hideModalPanel('evalDlg')");
                            }
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese una recomendación.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese su diagnostico.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique cuál es el impacto.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique la probabilidad de ocurrencia.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarCerrarEvaluacion(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.getInspeccionEvaluacion().getNOcurrencia() != null){
                if(this.getInspeccionEvaluacion().getNImpacto() != null){
                    if(this.getDiagnostico() != null && !"".equals(this.diagnostico.trim())){
                        if(this.getRecomendacion() != null && !"".equals(this.recomendacion.trim())){
                            bundle = ResourceBundle.getBundle(Parameters.getParameters());
                            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                            InsPresencialDao insPresencialDao =(InsPresencialDao)ServiceFinder.findBean("InsPresencialDao");
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
                                segDetInspreEvaluacionId.setNCodEvaluacion(BigDecimal.valueOf(inspreEvaluacionDao.nextSequenceValue()));
                                segDetInspreEvaluacionId.setNCodInspresencial(this.getSelectedInsPresencial().getId().getNCodInspresencial());
                                segDetInspreEvaluacionId.setNCodEmpresa(this.getSelectedInsPresencial().getId().getNCodEmpresa());
                                segDetInspreEvaluacion.setId(segDetInspreEvaluacionId);
                                segDetInspreEvaluacion.setNImpacto(this.getInspeccionEvaluacion().getNImpacto() != null ? this.getInspeccionEvaluacion().getNImpacto() : null);
                                segDetInspreEvaluacion.setNOcurrencia(this.getInspeccionEvaluacion().getNOcurrencia() != null ? this.getInspeccionEvaluacion().getNOcurrencia() : null);
                                segDetInspreEvaluacion.setNNivelImpacto(BigDecimal.ONE);
                                segDetInspreEvaluacion.setNNivelOcurrencia(BigDecimal.ONE);
                                segDetInspreEvaluacion.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_SOLUCIONADA"))));
                                segDetInspreEvaluacion.setDFecCreacion(new Date());
                                segDetInspreEvaluacion.setVUsuCreacion(usuarioSession.getVUsuario());
                                segDetInspreEvaluacion.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                                inspreEvaluacionDao.registrarEvaluacion(segDetInspreEvaluacion);
                            }

                            SegDetInspreevalDetalleId segDetInspreevalDetalleId = new SegDetInspreevalDetalleId();
                            segDetInspreevalDetalleId.setNCodDetalle(BigDecimal.valueOf(inspreEvaluacionDetalleDao.nextSequenceValue()));
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
                            segDetInspreevalDetalle.setVDiagnostico(this.getDiagnostico() != null ? this.getDiagnostico().toUpperCase().trim() : null);
                            segDetInspreevalDetalle.setVRecomendacion(this.getRecomendacion() != null ? this.getRecomendacion().toUpperCase().trim() : null);
                            segDetInspreevalDetalle.setDFecCreacion(new Date());
                            segDetInspreevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetInspreevalDetalle.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                            inspreEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetInspreevalDetalle);
                            
                            if(this.getListaInsPresencial().contains(this.getSelectedInsPresencial())){
                                int index = this.getListaInsPresencial().indexOf(this.getSelectedInsPresencial());
                                this.getSelectedInsPresencial().setNAnalisis(BigDecimal.ZERO);
                                this.getSelectedInsPresencial().setDFecModificacion(new Date());
                                this.getSelectedInsPresencial().setVUsuModificacion(usuarioSession.getVUsuario());
                                this.getSelectedInsPresencial().setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                                insPresencialDao.registrarInspeccionPresencial(this.getSelectedInsPresencial());
                                this.getListaInsPresencial().set(index, this.getSelectedInsPresencial());
                            }
                            this.setDiagnostico(null);
                            this.setRecomendacion(null);
                            if(this.isFromMatrix()){
                                this.setAction("document.getElementById('hiddenForm:hiddenBtn').click();");
                            }else{
                                this.setAction("Richfaces.hideModalPanel('evalDlg')");
                            }
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese una recomendación.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese su diagnostico.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique cuál es el impacto.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique la probabilidad de ocurrencia.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public boolean errorValidation(SegDetInsPresencial presencial){
        FacesMessage message = null;
        boolean error = false;
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            if(presencial.getBPersona() == null && presencial.getBActivo() == null && presencial.getBProceso() == null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique qué se controló.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNTipoInspeccion() == null || presencial.getNTipoInspeccion().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el tipo de inspección.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNTipoInspeccion().toString().equals(bundle.getString("TIPO_INSPECCION_ESPECIFICA"))){
                if(presencial.getNCodNovedad() == null || presencial.getNCodNovedad().compareTo(BigDecimal.valueOf(-1))==0){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione la novedad.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                    error = true;
                    return error;
                }
            }else if(presencial.getDFecHora() == null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la fecha y hora de la inspección.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getVDescBreve() == null || "".equals(presencial.getVDescBreve().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción breve de la inspección.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getVDescripcion() == null || "".equals(presencial.getVDescripcion().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción de la inspección.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNLocal() == null || presencial.getNLocal().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el local de la inspección.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNArea() == null || presencial.getNArea().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el área del local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNLugar() == null || presencial.getNLugar().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el lugar del área.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNResponsable() == null || presencial.getNResponsable().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el responsable del área.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNCargo() == null || presencial.getNCargo().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el cargo del responsable.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getVAccTomadas() == null || "".equals(presencial.getVAccTomadas().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la acción tomada.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNCumpleControl() == null || presencial.getNCumpleControl().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique si la inspección cumple con los controles.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(presencial.getNControlAdicional() == null || presencial.getNControlAdicional().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique si la inspección necesita control adicional.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(this.isVisualizar() && (presencial.getVControlAdicional() == null || "".equals(presencial.getVControlAdicional().trim()))){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese el control adicional.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(this.isVisualizar() && (presencial.getNAnalisis() == null || presencial.getNAnalisis().compareTo(BigDecimal.valueOf(-1))==0)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique si la inspección necesita un análisis de riesgo.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(this.isVisualizar() && (presencial.getNSeguimiento() == null || presencial.getNSeguimiento().compareTo(BigDecimal.valueOf(-1))==0)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique si la inspección necesita seguimiento.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
        }catch(Exception e){
            e.getMessage();
            
        }
        return error;
    }
    
    public void listarLocales(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            this.setListaLocales(localDao.obtenerListaLocalesActivosByEmpresa(empresaSession));
            this.setDescripcionLocal(null);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarLocal(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(this.getDescripcionLocal() != null && !"".equals(this.getDescripcionLocal().trim())){
                SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
                SegDetLocalId segDetLocalId = new SegDetLocalId();
                segDetLocalId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                SegDetLocal segDetLocal = new SegDetLocal();
                segDetLocal.setId(segDetLocalId);
                segDetLocal.setVDescripcion(this.getDescripcionLocal().toUpperCase().trim());
                
                List<SegDetLocal> lista = localDao.buscarLocales(segDetLocal);
                if(!(lista != null && !lista.isEmpty())){
                    segDetLocalId.setNCodLocal(BigDecimal.valueOf(localDao.nextSequenceValue()));
                    segDetLocalId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                    segDetLocal.setId(segDetLocalId);
                    segDetLocal.setNCodLocal(segDetLocalId.getNCodLocal());
                    segDetLocal.setNCodEmpresa(segDetLocalId.getNCodEmpresa());
                    segDetLocal.setNFlgActivo(BigDecimal.ONE);
                    segDetLocal.setDFecCreacion(new Date());
                    segDetLocal.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetLocal.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    localDao.registrarLocal(segDetLocal);
                    if(this.getListaLocales() == null)
                        this.setListaLocales(new ArrayList<SegDetLocal>());
                    this.getListaLocales().add(segDetLocal);
                    this.setDescripcionLocal(null);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
                    BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción del local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarLocal(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegDetLocal segDetLocal = (SegDetLocal) actionEvent.getSource();
            if(segDetLocal.getVDescripcion() != null && !"".equals(segDetLocal.getVDescripcion().trim())){
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                segDetLocal.setVDescripcion(segDetLocal.getVDescripcion().toUpperCase().trim());
                segDetLocal.setDFecModificacion(new Date());
                segDetLocal.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetLocal.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
                localDao.registrarLocal(segDetLocal);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
                BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción del local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarLocal(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            localDao.eliminarLocal(this.getSelectedLocal());
            this.getListaLocales().remove(this.getSelectedLocal());
            ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
            BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void listarAreas(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            segDetLocalId.setNCodLocal(this.getLocal());
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            this.setListaAreas(areaDao.obtenerListaAreasActivasByLocal(segDetLocal));
            this.setDescripcionArea(null);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarArea(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(this.getDescripcionArea() != null && !"".equals(this.getDescripcionArea().trim())){
                SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
                SegDetAreaId segDetAreaId = new SegDetAreaId();
                segDetAreaId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                segDetAreaId.setNCodLocal(this.getLocal());
                SegDetArea segDetArea = new SegDetArea();
                segDetArea.setId(segDetAreaId);
                segDetArea.setVDescripcion(this.getDescripcionArea().toUpperCase().trim());
                
                List<SegDetArea> lista = areaDao.buscarAreas(segDetArea);
                if(!(lista != null && !lista.isEmpty())){
                    segDetAreaId.setNCodArea(BigDecimal.valueOf(areaDao.nextSequenceValue()));
                    segDetAreaId.setNCodLocal(this.getLocal());
                    segDetAreaId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                    segDetArea.setId(segDetAreaId);
                    segDetArea.setNCodArea(segDetAreaId.getNCodArea());
                    segDetArea.setNCodLocal(segDetAreaId.getNCodLocal());
                    segDetArea.setNCodEmpresa(segDetAreaId.getNCodEmpresa());
                    segDetArea.setNFlgActivo(BigDecimal.ONE);
                    segDetArea.setDFecCreacion(new Date());
                    segDetArea.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetArea.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    areaDao.registrarArea(segDetArea);
                    if(this.getListaAreas() == null)
                        this.setListaAreas(new ArrayList<SegDetArea>());
                    this.getListaAreas().add(segDetArea);
                    this.setDescripcionArea(null);
                    SegDetLocalId segDetLocalId = new SegDetLocalId();
                    segDetLocalId.setNCodLocal(this.getLocal());
                    SegDetLocal segDetLocal = new SegDetLocal();
                    segDetLocal.setId(segDetLocalId);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
                    listasSessionMB.setListaArea(new Items(areaDao.obtenerListaAreasActivasByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
                    BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción del área.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarArea(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegDetArea segDetArea = (SegDetArea) actionEvent.getSource();
            if(segDetArea.getVDescripcion() != null && !"".equals(segDetArea.getVDescripcion().trim())){
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                segDetArea.setVDescripcion(segDetArea.getVDescripcion().toUpperCase().trim());
                segDetArea.setDFecModificacion(new Date());
                segDetArea.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetArea.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
                areaDao.registrarArea(segDetArea);
                SegDetLocalId segDetLocalId = new SegDetLocalId();
                segDetLocalId.setNCodLocal(this.getLocal());
                SegDetLocal segDetLocal = new SegDetLocal();
                segDetLocal.setId(segDetLocalId);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
                listasSessionMB.setListaArea(new Items(areaDao.obtenerListaAreasActivasByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
                BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción del área.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarArea(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            areaDao.eliminarArea(this.getSelectedArea());
            this.getListaAreas().remove(this.getSelectedArea());
            ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodLocal(this.getLocal());
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
            listasSessionMB.setListaArea(new Items(areaDao.obtenerListaAreasActivasByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
            BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void listarLugares(ActionEvent actionEvent){
        try{
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(this.getArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            this.setListaLugares(lugarDao.obtenerListaLugaresActivosByArea(segDetArea));
            this.setDescripcionLugar(null);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarLugar(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(this.getDescripcionLugar() != null && !"".equals(this.getDescripcionLugar().trim())){
                SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
                SegDetLugarId segDetLugarId = new SegDetLugarId();
                segDetLugarId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                segDetLugarId.setNCodLocal(this.getLocal());
                segDetLugarId.setNCodArea(this.getArea());
                SegDetLugar segDetLugar = new SegDetLugar();
                segDetLugar.setId(segDetLugarId);
                segDetLugar.setVDescripcion(this.getDescripcionLugar().toUpperCase().trim());
                
                List<SegDetLugar> lista = lugarDao.buscarLugares(segDetLugar);
                if(!(lista != null && !lista.isEmpty())){
                    segDetLugarId.setNCodLugar(BigDecimal.valueOf(lugarDao.nextSequenceValue()));
                    segDetLugarId.setNCodArea(this.getArea());
                    segDetLugarId.setNCodLocal(this.getLocal());
                    segDetLugarId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                    segDetLugar.setId(segDetLugarId);
                    segDetLugar.setNCodLugar(segDetLugarId.getNCodLugar());
                    segDetLugar.setNCodArea(segDetLugarId.getNCodArea());
                    segDetLugar.setNCodLocal(segDetLugarId.getNCodLocal());
                    segDetLugar.setNCodEmpresa(segDetLugarId.getNCodEmpresa());
                    segDetLugar.setNFlgActivo(BigDecimal.ONE);
                    segDetLugar.setDFecCreacion(new Date());
                    segDetLugar.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetLugar.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    lugarDao.registrarLugar(segDetLugar);
                    if(this.getListaLugares() == null)
                        this.setListaLugares(new ArrayList<SegDetLugar>());
                    this.getListaLugares().add(segDetLugar);
                    this.setDescripcionLugar(null);
                    SegDetAreaId segDetAreaId = new SegDetAreaId();
                    segDetAreaId.setNCodArea(this.getArea());
                    SegDetArea segDetArea = new SegDetArea();
                    segDetArea.setId(segDetAreaId);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                    listasSessionMB.setListaLugar(new Items(lugarDao.obtenerListaLugaresActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                    BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción del lugar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarLugar(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            SegDetLugar segDetLugar = (SegDetLugar) actionEvent.getSource();
            if(segDetLugar.getVDescripcion() != null && !"".equals(segDetLugar.getVDescripcion().trim())){
                SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                segDetLugar.setVDescripcion(segDetLugar.getVDescripcion().toUpperCase().trim());
                segDetLugar.setDFecModificacion(new Date());
                segDetLugar.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetLugar.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
                lugarDao.registrarLugar(segDetLugar);
                SegDetAreaId segDetAreaId = new SegDetAreaId();
                segDetAreaId.setNCodArea(this.getArea());
                SegDetArea segDetArea = new SegDetArea();
                segDetArea.setId(segDetAreaId);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                listasSessionMB.setListaLugar(new Items(lugarDao.obtenerListaLugaresActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción del lugar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarLugar(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            lugarDao.eliminarLugar(this.getSelectedLugar());
            this.getListaLugares().remove(this.getSelectedLugar());
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(this.getArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
            listasSessionMB.setListaLugar(new Items(lugarDao.obtenerListaLugaresActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
            BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void listarResponsables(ActionEvent actionEvent){
        try{
            ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(this.getArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            this.setListaResponsables(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea));
            this.setNombreResponsable(null);
            this.setApellidoResponsable(null);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarResponsable(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(this.getNombreResponsable() != null && !"".equals(this.getNombreResponsable().trim())){
                if(this.getApellidoResponsable() != null && !"".equals(this.getApellidoResponsable().trim())){
                    SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                    ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                    SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                    ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
                    SegDetResponsableId segDetResponsableId = new SegDetResponsableId();
                    segDetResponsableId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                    segDetResponsableId.setNCodLocal(this.getLocal());
                    segDetResponsableId.setNCodArea(this.getArea());
                    SegDetResponsable segDetResponsable = new SegDetResponsable();
                    segDetResponsable.setId(segDetResponsableId);
                    segDetResponsable.setVNombres(this.getNombreResponsable().toUpperCase().trim());
                    segDetResponsable.setVApellidos(this.getApellidoResponsable().toUpperCase().trim());
                    segDetResponsable.setVNombrecompleto(segDetResponsable.getVNombres()+" "+segDetResponsable.getVApellidos());

                    List<SegDetResponsable> lista = responsableDao.buscarResponsables(segDetResponsable);
                    if(!(lista != null && !lista.isEmpty())){
                        segDetResponsableId.setNCodResponsable(BigDecimal.valueOf(responsableDao.nextSequenceValue()));
                        segDetResponsableId.setNCodArea(this.getArea());
                        segDetResponsableId.setNCodLocal(this.getLocal());
                        segDetResponsableId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                        segDetResponsable.setId(segDetResponsableId);
                        segDetResponsable.setNCodResponsable(segDetResponsableId.getNCodResponsable());
                        segDetResponsable.setNCodArea(segDetResponsableId.getNCodArea());
                        segDetResponsable.setNCodLocal(segDetResponsableId.getNCodLocal());
                        segDetResponsable.setNCodEmpresa(segDetResponsableId.getNCodEmpresa());
                        segDetResponsable.setNFlgActivo(BigDecimal.ONE);
                        segDetResponsable.setDFecCreacion(new Date());
                        segDetResponsable.setVUsuCreacion(usuarioSession.getVUsuario());
                        segDetResponsable.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                        responsableDao.registrarResponsable(segDetResponsable);
                        if(this.getListaResponsables() == null)
                            this.setListaResponsables(new ArrayList<SegDetResponsable>());
                        this.getListaResponsables().add(segDetResponsable);
                        this.setNombreResponsable(null);
                        this.setApellidoResponsable(null);
                        SegDetAreaId segDetAreaId = new SegDetAreaId();
                        segDetAreaId.setNCodArea(this.getArea());
                        SegDetArea segDetArea = new SegDetArea();
                        segDetArea.setId(segDetAreaId);
                        listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                        listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
                        listasSessionMB.setListaResponsable(new Items(responsableDao.obtenerListaResponsablesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
                        BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese los apellidos del responsable.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese los nombres del responsable.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarResponsable(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            SegDetResponsable segDetResponsable = (SegDetResponsable) actionEvent.getSource();
            if(segDetResponsable.getVNombres() != null && !"".equals(segDetResponsable.getVNombres().trim())){
                if(segDetResponsable.getVApellidos() != null && !"".equals(segDetResponsable.getVApellidos().trim())){
                    SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                    ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                    SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                    segDetResponsable.setVNombres(segDetResponsable.getVNombres().toUpperCase().trim());
                    segDetResponsable.setVApellidos(segDetResponsable.getVApellidos().toUpperCase().trim());
                    segDetResponsable.setVNombrecompleto(segDetResponsable.getVNombres()+" "+segDetResponsable.getVApellidos());
                    segDetResponsable.setDFecModificacion(new Date());
                    segDetResponsable.setVUsuModificacion(usuarioSession.getVUsuario());
                    segDetResponsable.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                    ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
                    responsableDao.registrarResponsable(segDetResponsable);
                    SegDetAreaId segDetAreaId = new SegDetAreaId();
                    segDetAreaId.setNCodArea(this.getArea());
                    SegDetArea segDetArea = new SegDetArea();
                    segDetArea.setId(segDetAreaId);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
                    listasSessionMB.setListaResponsable(new Items(responsableDao.obtenerListaResponsablesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
                    BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese los apellidos del responsable.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese los nombres del responsable.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarResponsable(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
            responsableDao.eliminarResponsable(this.getSelectedResponsable());
            this.getListaResponsables().remove(this.getSelectedResponsable());
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(this.getArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
            listasSessionMB.setListaResponsable(new Items(responsableDao.obtenerListaResponsablesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
            BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void listarCargos(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            this.setListaCargos(cargoDao.obtenerListaCargosActivosByEmpresa(empresaSession));
            this.setDescripcionCargo(null);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarCargo(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(this.getDescripcionCargo() != null && !"".equals(this.getDescripcionCargo().trim())){
                SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
                SegDetCargo segDetCargo = new SegDetCargo();
                segDetCargo.setVDescripcion(this.getDescripcionCargo().toUpperCase().trim());
                
                List<SegDetCargo> lista = cargoDao.buscarCargos(segDetCargo);
                if(!(lista != null && !lista.isEmpty())){
                    segDetCargo.setNCodCargo(BigDecimal.valueOf(cargoDao.nextSequenceValue()));
                    segDetCargo.setNCodEmpresa(empresaSession.getNCodEmpresa());
                    segDetCargo.setNFlgActivo(BigDecimal.ONE);
                    segDetCargo.setDFecCreacion(new Date());
                    segDetCargo.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetCargo.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    cargoDao.registrarCargo(segDetCargo);
                    if(this.getListaCargos() == null)
                        this.setListaCargos(new ArrayList<SegDetCargo>());
                    this.getListaCargos().add(segDetCargo);
                    this.setDescripcionCargo(null);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaCargoActivoByEmpresa(new Items(cargoDao.obtenerListaCargosActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
                    BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción del cargo.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarCargo(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegDetCargo segDetCargo = (SegDetCargo) actionEvent.getSource();
            if(segDetCargo.getVDescripcion() != null && !"".equals(segDetCargo.getVDescripcion().trim())){
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                segDetCargo.setVDescripcion(segDetCargo.getVDescripcion().toUpperCase().trim());
                segDetCargo.setDFecModificacion(new Date());
                segDetCargo.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetCargo.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
                cargoDao.registrarCargo(segDetCargo);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaCargoActivoByEmpresa(new Items(cargoDao.obtenerListaCargosActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
                BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripción del cargo.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarCargo(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            cargoDao.eliminarCargo(this.getSelectedCargo());
            this.getListaCargos().remove(this.getSelectedCargo());
            ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            listasSessionMB.setListaCargoActivoByEmpresa(new Items(cargoDao.obtenerListaCargosActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
            BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
}
