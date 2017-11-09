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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import pe.com.segrop.sgsapp.dao.AccionDao;
import pe.com.segrop.sgsapp.dao.AreaDao;
import pe.com.segrop.sgsapp.dao.CargoDao;
import pe.com.segrop.sgsapp.dao.ListasSessionDao;
import pe.com.segrop.sgsapp.dao.LocalDao;
import pe.com.segrop.sgsapp.dao.LugarDao;
import pe.com.segrop.sgsapp.dao.NovedadDao;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDao;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.dao.ResponsableDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabMaestro;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetAcciones;
import pe.com.segrop.sgsapp.domain.SegDetAccionesId;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetAreaId;
import pe.com.segrop.sgsapp.domain.SegDetCargo;
import pe.com.segrop.sgsapp.domain.SegDetLocal;
import pe.com.segrop.sgsapp.domain.SegDetLocalId;
import pe.com.segrop.sgsapp.domain.SegDetLugar;
import pe.com.segrop.sgsapp.domain.SegDetLugarId;
import pe.com.segrop.sgsapp.domain.SegDetMaestrodetalle;
import pe.com.segrop.sgsapp.domain.SegDetNovEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetNovEvaluacionId;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;
import pe.com.segrop.sgsapp.domain.SegDetNovedadId;
import pe.com.segrop.sgsapp.domain.SegDetNovevalDetalle;
import pe.com.segrop.sgsapp.domain.SegDetNovevalDetalleId;
import pe.com.segrop.sgsapp.domain.SegDetResponsable;
import pe.com.segrop.sgsapp.domain.SegDetResponsableId;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
@ManagedBean
@ViewScoped
public class NovedadMB implements Serializable{
    
    private BigDecimal searchEmpresa;
    private BigDecimal searchAfectado;
    private BigDecimal searchTipoNovedad;
    private BigDecimal searchTipoEvento;
    private BigDecimal searchLocal;
    private BigDecimal searchArea;
    private BigDecimal searchLugar;
    private BigDecimal searchResponsable;
    private BigDecimal searchCargo;
    private Boolean persona;
    private Boolean activo;
    private Boolean proceso;
    private BigDecimal tipoNovedad;
    private BigDecimal tipoEvento;
    private Date fechaHora;
    private String descBreve;
    private String descripcion;
    private BigDecimal empresa;
    private BigDecimal local;
    private BigDecimal area;
    private BigDecimal lugar;
    private BigDecimal responsable;
    private BigDecimal cargo;
    private String accionTomada;
    private String analisis;
    private String seguimiento;
    private boolean visualizar;
    private boolean listaAccionesVacia;
    private SegDetNovedad selectedNovedad;
    private List<SegDetNovedad> listaNovedad;
    private List<SegDetAcciones> listaAcciones;
    private SegDetNovEvaluacion novedadEvaluacion;
    private BigDecimal ocurrencia;
    private BigDecimal impacto;
    private String diagnostico;
    private String recomendacion;
    private String descripcionTipoEvento;
    private SegDetMaestrodetalle selectedTipoEvento;
    private List<SegDetMaestrodetalle> listaTipoEventos;
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
    private Boolean flag;

    /** Creates a new instance of NovedadMB */
    public NovedadMB() {
        this.selectedNovedad = new SegDetNovedad();
        this.novedadEvaluacion = new SegDetNovEvaluacion();
        this.disabledArea = true;
        this.renderedArea = false;
        this.disabledLugar = true;
        this.renderedLugar = false;
        this.disabledResponsable = true;
        this.renderedResponsable = false;
        this.fromMatrix = false;
    }

    /**
     * @return the searchEmpresa
     */
    public BigDecimal getSearchEmpresa() {
        return searchEmpresa;
    }

    /**
     * @param searchEmpresa the searchEmpresa to set
     */
    public void setSearchEmpresa(BigDecimal searchEmpresa) {
        this.searchEmpresa = searchEmpresa;
    }

    public BigDecimal getSearchAfectado() {
        return searchAfectado;
    }

    public void setSearchAfectado(BigDecimal searchAfectado) {
        this.searchAfectado = searchAfectado;
    }

    public BigDecimal getSearchTipoNovedad() {
        return searchTipoNovedad;
    }

    public void setSearchTipoNovedad(BigDecimal searchTipoNovedad) {
        this.searchTipoNovedad = searchTipoNovedad;
    }

    public BigDecimal getSearchTipoEvento() {
        return searchTipoEvento;
    }

    public void setSearchTipoEvento(BigDecimal searchTipoEvento) {
        this.searchTipoEvento = searchTipoEvento;
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

    public BigDecimal getTipoNovedad() {
        return tipoNovedad;
    }

    public void setTipoNovedad(BigDecimal tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public BigDecimal getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(BigDecimal tipoEvento) {
        this.tipoEvento = tipoEvento;
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

    /**
     * @return the empresa
     */
    public BigDecimal getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(BigDecimal empresa) {
        this.empresa = empresa;
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

    public SegDetNovedad getSelectedNovedad() {
        return selectedNovedad;
    }

    public void setSelectedNovedad(SegDetNovedad selectedNovedad) {
        this.selectedNovedad = selectedNovedad;
    }

    public List<SegDetNovedad> getListaNovedad() {
        return listaNovedad;
    }

    public void setListaNovedad(List<SegDetNovedad> listaNovedad) {
        this.listaNovedad = listaNovedad;
    }

    public List<SegDetAcciones> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(List<SegDetAcciones> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public SegDetNovEvaluacion getNovedadEvaluacion() {
        return novedadEvaluacion;
    }

    public void setNovedadEvaluacion(SegDetNovEvaluacion novedadEvaluacion) {
        this.novedadEvaluacion = novedadEvaluacion;
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

    public String getDescripcionTipoEvento() {
        return descripcionTipoEvento;
    }

    public void setDescripcionTipoEvento(String descripcionTipoEvento) {
        this.descripcionTipoEvento = descripcionTipoEvento;
    }

    public SegDetMaestrodetalle getSelectedTipoEvento() {
        return selectedTipoEvento;
    }

    public void setSelectedTipoEvento(SegDetMaestrodetalle selectedTipoEvento) {
        this.selectedTipoEvento = selectedTipoEvento;
    }

    public List<SegDetMaestrodetalle> getListaTipoEventos() {
        return listaTipoEventos;
    }

    public void setListaTipoEventos(List<SegDetMaestrodetalle> listaTipoEventos) {
        this.listaTipoEventos = listaTipoEventos;
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

    /**
     * @return the flag
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
    
    @PostConstruct
    public void init() {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            if (rucSegrop.equals(segCabEmpresa.getVRuc())) {
                ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaLocalActivoByEmpresa(new Items(null, Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
                JSFUtils.setSessionAttribute("listasSessionMB", listasSessionMB);
                this.setFlag(true);
            } else {
                this.setFlag(false);
            }
            this.setSelectedNovedad(new SegDetNovedad());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void handleSelectTipoNovedad(AjaxBehaviorEvent event){
        ResourceBundle bundle;
        String codTipoNovedad = null;
        try{
            if(event != null){
                BigDecimal value = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                codTipoNovedad = value.toString();
                if(codTipoNovedad.equals(bundle.getString("TIPO_NOVEDAD_FALLA"))){
                    this.setVisualizar(true);
                    this.setSeguimiento("1");
                    this.setAnalisis("1");
                }else{
                    this.setVisualizar(false);
                    this.setSeguimiento("0");
                    this.setAnalisis("0");
                }
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void handleChangeEmpresa(AjaxBehaviorEvent event){
        try{
            if(event != null){
                ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                BigDecimal value = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                SegCabEmpresa segCabEmpresa = new SegCabEmpresa();            
                segCabEmpresa.setNCodEmpresa(value);
                LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
                listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal","VDescripcion").getItems());
                JSFUtils.setSessionAttribute("listasSessionMB", listasSessionMB);
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void handleChangeLocalValue(AjaxBehaviorEvent event){
        try{
            if(event != null){
                ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                BigDecimal codLocal = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                SegDetLocalId segDetLocalId = new SegDetLocalId();
                segDetLocalId.setNCodLocal(codLocal);
                SegDetLocal segDetLocal = new SegDetLocal();            
                segDetLocal.setId(segDetLocalId);
                AreaDao areaDao =(AreaDao)ServiceFinder.findBean("AreaDao");
                listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems());
                JSFUtils.setSessionAttribute("listasSessionMB", listasSessionMB);
                if("".equals(codLocal.toString())){
                    this.setDisabledArea(true);
                    this.setRenderedArea(false);
                }else{
                    this.setDisabledArea(false);
                    this.setRenderedArea(true);
                }
                this.setLocal(codLocal);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void handleChangeAreaValue(AjaxBehaviorEvent event){
        try{
            if(event != null){
                ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                BigDecimal codArea = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                SegDetAreaId segDetAreaId = new SegDetAreaId();
                segDetAreaId.setNCodArea(codArea);
                SegDetArea segDetArea = new SegDetArea();
                segDetArea.setId(segDetAreaId);
                LugarDao lugarDao =(LugarDao)ServiceFinder.findBean("LugarDao");
                listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems());
                ResponsableDao responsableDao =(ResponsableDao)ServiceFinder.findBean("ResponsableDao");
                listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable","VNombrecompleto").getItems());
                JSFUtils.setSessionAttribute("listasSessionMB", listasSessionMB);
                if("".equals(codArea.toString())){
                    this.setDisabledLugar(true);
                    this.setRenderedLugar(false);
                    this.setDisabledResponsable(true);
                    this.setRenderedResponsable(false);
                }else{
                    this.setDisabledLugar(false);
                    this.setRenderedLugar(true);
                    this.setDisabledResponsable(false);
                    this.setRenderedResponsable(true);
                }
                this.setArea(codArea);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void buscarNovedad(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            SegDetNovedadId segDetNovedadId = new SegDetNovedadId();
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                segDetNovedadId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            } else {
                segDetNovedadId.setNCodEmpresa(this.getSearchEmpresa());
            }
            SegDetNovedad segDetNovedad = new SegDetNovedad();
            segDetNovedad.setId(segDetNovedadId);
            segDetNovedad.setNCodEmpresa(segDetNovedadId.getNCodEmpresa());
            segDetNovedad.setNTipoNovedad(this.getSearchTipoNovedad() != null ? this.getSearchTipoNovedad() : null);
            segDetNovedad.setNTipoEvento(this.getSearchTipoEvento() != null ? this.getSearchTipoEvento() : null);
            segDetNovedad.setNLocal(this.getSearchLocal() != null ? this.getSearchLocal() : null);
            segDetNovedad.setNArea(this.getSearchArea() != null ? this.getSearchArea() : null);
            segDetNovedad.setNLugar(this.getSearchLugar() != null ? this.getSearchLugar() : null);
            segDetNovedad.setNResponsable(this.getSearchResponsable() != null ? this.getSearchResponsable() : null);
            segDetNovedad.setNCargo(this.getSearchCargo() != null ? this.getSearchCargo() : null);
            NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
            setListaNovedad(novedadDao.buscarNovedades(segDetNovedad));
        } catch (Exception e) {
            e.getMessage();

        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            this.setPersona(null);
            this.setActivo(null);
            this.setProceso(null);
            this.setTipoNovedad(null);
            this.setTipoEvento(null);
            this.setFechaHora(null);
            this.setLocal(null);
            this.setArea(null);
            this.setLugar(null);
            this.setResponsable(null);
            this.setCargo(null);
            this.setDescBreve(null);
            this.setDescripcion(null);
            this.setAccionTomada(null);
            this.setAnalisis(null);
            this.setSeguimiento(null);
            this.setVisualizar(false);
            this.setDisabledArea(true);
            this.setRenderedArea(false);
            this.setDisabledLugar(true);
            this.setRenderedLugar(false);
            this.setDisabledResponsable(true);
            this.setRenderedResponsable(false);
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB.setListaAreaActivaByLocal(new Items(null, Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems());
            listasSessionMB.setListaLugarActivoByArea(new Items(null, Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems());
            listasSessionMB.setListaResponsableActivoByArea(new Items(null, Items.FIRST_ITEM_SELECT, "NCodResponsable","VNombrecompleto").getItems());
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void registrarNovedad(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            if (!rucSegrop.equals(empresaSession.getVRuc())) {
                this.setEmpresa(empresaSession.getNCodEmpresa());
            }
            NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
            SegDetNovedadId segDetNovedadId = new SegDetNovedadId();
            segDetNovedadId.setNCodEmpresa(this.getEmpresa() != null ? this.getEmpresa() : null);
            SegDetNovedad segDetNovedad = new SegDetNovedad();
            segDetNovedad.setId(segDetNovedadId);
            segDetNovedad.setNCodNovedad(segDetNovedadId.getNCodNovedad());
            segDetNovedad.setNCodEmpresa(segDetNovedadId.getNCodEmpresa());
            segDetNovedad.setBPersona(this.persona != null ? this.persona : null);
            segDetNovedad.setBActivo(this.activo != null ? this.activo : null);
            segDetNovedad.setBProceso(this.proceso != null ? this.proceso : null);
            segDetNovedad.setNTipoNovedad(this.tipoNovedad != null ? this.tipoNovedad : null);
            segDetNovedad.setNTipoEvento(this.tipoEvento != null ? this.tipoEvento : null);
            segDetNovedad.setDFecHora(this.fechaHora != null ? this.fechaHora : null);
            segDetNovedad.setVDescBreve(this.descBreve != null ? this.descBreve.toUpperCase().trim() : null);
            segDetNovedad.setVDescripcion(this.descripcion != null ? this.descripcion.toUpperCase().trim() : null);
            segDetNovedad.setNLocal(this.local != null ? this.local : null);
            segDetNovedad.setNArea(this.area != null ? this.area : null);
            segDetNovedad.setNLugar(this.lugar != null ? this.lugar : null);
            segDetNovedad.setNResponsable(this.responsable != null ? this.responsable : null);
            segDetNovedad.setNCargo(this.cargo != null ? this.cargo : null);
            segDetNovedad.setVAccTomadas(this.accionTomada != null ? this.accionTomada.toUpperCase().trim() : null);
            
            if(segDetNovedad.getNTipoNovedad().toString().equals(bundle.getString("TIPO_NOVEDAD_FALLA"))){
                segDetNovedad.setNAnalisis(this.analisis != null ? BigDecimal.valueOf(Long.parseLong(this.analisis)) : null);
                segDetNovedad.setNSeguimiento(this.seguimiento != null ? BigDecimal.valueOf(Long.parseLong(this.seguimiento)) : null);
                if(segDetNovedad.getNSeguimiento().equals(BigDecimal.ONE)){
                    segDetNovedad.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE"))));
                }else if(segDetNovedad.getNSeguimiento().equals(BigDecimal.ZERO)){
                    segDetNovedad.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
                }
            }else{
                segDetNovedad.setNAnalisis(BigDecimal.ZERO);
                segDetNovedad.setNSeguimiento(BigDecimal.ZERO);
                segDetNovedad.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
            }
            
            if(!errorValidation(segDetNovedad)){
                segDetNovedad.getId().setNCodNovedad(BigDecimal.valueOf(novedadDao.nextSequenceValue()));
                segDetNovedad.setDFecCreacion(new Date());
                segDetNovedad.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetNovedad.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                novedadDao.registrarNovedad(segDetNovedad);
                RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
            }
            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toVer(ActionEvent event){
        String rowKey = null;
        try{
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            
            rowKey = JSFUtils.getRequestParameter("rowKey");
            
            SegDetNovedad segDetNovedad = this.getListaNovedad().get(Integer.parseInt(rowKey));
            this.setSelectedNovedad(segDetNovedad);
            
            SegCabEmpresa segCabEmpresa = new SegCabEmpresa();
            segCabEmpresa.setNCodEmpresa(this.getSelectedNovedad().getNCodEmpresa());
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal","VDescripcion").getItems());
            
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodLocal(this.getSelectedNovedad().getNLocal());
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            AreaDao areaDao =(AreaDao)ServiceFinder.findBean("AreaDao");
            listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems());
            
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(this.getSelectedNovedad().getNArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            LugarDao lugarDao =(LugarDao)ServiceFinder.findBean("LugarDao");
            listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems());
            ResponsableDao responsableDao =(ResponsableDao)ServiceFinder.findBean("ResponsableDao");
            listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable","VNombrecompleto").getItems());
            
            AccionDao accionDao =(AccionDao)ServiceFinder.findBean("AccionDao");
            this.setListaAcciones(accionDao.obtenerListaAccionesByNovedad(segDetNovedad));
            if(this.getListaAcciones() !=null && !this.getListaAcciones().isEmpty()){
                this.setListaAccionesVacia(false);
            }else{
                this.setListaAccionesVacia(true);
            }
            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
            NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
            this.setNovedadEvaluacion(novedadEvaluacionDao.obtenerEvaluacionNovedad(segDetNovedad));
            if(this.getNovedadEvaluacion() !=null){
                List<SegDetNovevalDetalle> detalles = novedadEvaluacionDetalleDao.obtenerListaDetalleEvaluacionNovedad(this.getNovedadEvaluacion());
                this.getNovedadEvaluacion().setSegDetNovevalDetalles(detalles);
            }else{
                this.setNovedadEvaluacion(new SegDetNovEvaluacion());
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toEdit(ActionEvent event){
        ResourceBundle bundle;
        String rowKey = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            
            rowKey = JSFUtils.getRequestParameter("rowKey");
            
            SegDetNovedad segDetNovedad = this.getListaNovedad().get(Integer.parseInt(rowKey));
            this.setSelectedNovedad(segDetNovedad);
            
            SegCabEmpresa segCabEmpresa = new SegCabEmpresa();
            segCabEmpresa.setNCodEmpresa(this.getSelectedNovedad().getNCodEmpresa());
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal","VDescripcion").getItems());
            
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodLocal(this.getSelectedNovedad().getNLocal());
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            AreaDao areaDao =(AreaDao)ServiceFinder.findBean("AreaDao");
            listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems());
            
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(this.getSelectedNovedad().getNArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            LugarDao lugarDao =(LugarDao)ServiceFinder.findBean("LugarDao");
            listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems());
            ResponsableDao responsableDao =(ResponsableDao)ServiceFinder.findBean("ResponsableDao");
            listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable","VNombrecompleto").getItems());
            
            if(segDetNovedad.getNTipoNovedad().toString().equals(bundle.getString("TIPO_NOVEDAD_FALLA"))){
                this.setVisualizar(true);
            }else{
                this.setVisualizar(false);
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void editarNovedad(ActionEvent actionEvent) {
        ResourceBundle bundle;
        String rucSegrop = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetNovedad segDetNovedad = this.getSelectedNovedad();
            if (!rucSegrop.equals(empresaSession.getVRuc())) {
                segDetNovedad.getId().setNCodEmpresa(empresaSession.getNCodEmpresa());
            }
            segDetNovedad.setNCodEmpresa(segDetNovedad.getId().getNCodEmpresa() != null ? segDetNovedad.getId().getNCodEmpresa() : null);
            segDetNovedad.setBPersona(segDetNovedad.getBPersona() != null ? segDetNovedad.getBPersona() : null);
            segDetNovedad.setBActivo(segDetNovedad.getBActivo() != null ? segDetNovedad.getBActivo() : null);
            segDetNovedad.setBProceso(segDetNovedad.getBProceso() != null ? segDetNovedad.getBProceso() : null);
            segDetNovedad.setNTipoNovedad(segDetNovedad.getNTipoNovedad() != null ? segDetNovedad.getNTipoNovedad() : null);
            segDetNovedad.setNTipoEvento(segDetNovedad.getNTipoEvento() != null ? segDetNovedad.getNTipoEvento() : null);
            segDetNovedad.setDFecHora(segDetNovedad.getDFecHora() != null ? segDetNovedad.getDFecHora() : null);
            segDetNovedad.setVDescBreve(segDetNovedad.getVDescBreve() != null ? segDetNovedad.getVDescBreve().toUpperCase().trim() : null);
            segDetNovedad.setVDescripcion(segDetNovedad.getVDescripcion() != null ? segDetNovedad.getVDescripcion().toUpperCase().trim() : null);
            segDetNovedad.setNLocal(segDetNovedad.getNLocal() != null ? segDetNovedad.getNLocal() : null);
            segDetNovedad.setNArea(segDetNovedad.getNArea() != null ? segDetNovedad.getNArea() : null);
            segDetNovedad.setNLugar(segDetNovedad.getNLugar() != null ? segDetNovedad.getNLugar() : null);
            segDetNovedad.setNResponsable(segDetNovedad.getNResponsable() != null ? segDetNovedad.getNResponsable() : null);
            segDetNovedad.setNCargo(segDetNovedad.getNCargo() != null ? segDetNovedad.getNCargo() : null);
            segDetNovedad.setVAccTomadas(segDetNovedad.getVAccTomadas() != null ? segDetNovedad.getVAccTomadas().toUpperCase().trim() : null);
            
            if(segDetNovedad.getNTipoNovedad().toString().equals(bundle.getString("TIPO_NOVEDAD_FALLA"))){
                segDetNovedad.setNAnalisis(segDetNovedad.getNAnalisis() != null ? segDetNovedad.getNAnalisis() : null);
                segDetNovedad.setNSeguimiento(segDetNovedad.getNSeguimiento() != null ? segDetNovedad.getNSeguimiento() : null);
                if(segDetNovedad.getNSeguimiento().equals(BigDecimal.ONE)){
                    segDetNovedad.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE"))));
                }else if(segDetNovedad.getNSeguimiento().equals(BigDecimal.ZERO)){
                    segDetNovedad.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
                }
            }else{
                segDetNovedad.setNAnalisis(BigDecimal.ZERO);
                segDetNovedad.setNSeguimiento(BigDecimal.ZERO);
                segDetNovedad.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
            }
            
            if(!errorValidation(segDetNovedad)){
                segDetNovedad.setDFecModificacion(new Date());
                segDetNovedad.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetNovedad.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
                novedadDao.registrarNovedad(segDetNovedad);
                RequestContext.getCurrentInstance().execute("PF('editDlg').hide();");
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void toSeguimiento(ActionEvent actionEvent){
        try{
            String rowKey = JSFUtils.getRequestParameter("rowKey");
            SegDetNovedad segDetNovedad = this.getListaNovedad().get(Integer.parseInt(rowKey));
            this.setSelectedNovedad(segDetNovedad);
            
            AccionDao accionDao =(AccionDao)ServiceFinder.findBean("AccionDao");
            this.setListaAcciones(accionDao.obtenerListaAccionesByNovedad(segDetNovedad));
            if(this.getListaAcciones() !=null && !this.getListaAcciones().isEmpty()){
                this.setListaAccionesVacia(false);
            }else{
                this.setListaAccionesVacia(true);
            }
            this.setAccionTomada(null);
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
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
                AccionDao accionDao =(AccionDao)ServiceFinder.findBean("AccionDao");
                SegDetAccionesId segDetAccionesId = new SegDetAccionesId();
                segDetAccionesId.setNCodAccion(BigDecimal.valueOf(accionDao.nextSequenceValue()));
                segDetAccionesId.setNCodNovedad(this.getSelectedNovedad().getId().getNCodNovedad());
                segDetAccionesId.setNCodEmpresa(this.getSelectedNovedad().getId().getNCodEmpresa());
                SegDetAcciones segDetAcciones = new SegDetAcciones();
                segDetAcciones.setId(segDetAccionesId);
                segDetAcciones.setNCodAccion(segDetAccionesId.getNCodAccion());
                segDetAcciones.setNCodNovedad(segDetAccionesId.getNCodNovedad());
                segDetAcciones.setNCodEmpresa(segDetAccionesId.getNCodEmpresa());
                segDetAcciones.setDFecHora(new Date());
                segDetAcciones.setVDescripcion(this.accionTomada != null ? this.accionTomada.toUpperCase().trim() : null);
                segDetAcciones.setDFecCreacion(new Date());
                segDetAcciones.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetAcciones.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                accionDao.registrarAccion(segDetAcciones);

                if(this.getListaNovedad().contains(this.getSelectedNovedad())){
                    int index = this.getListaNovedad().indexOf(this.getSelectedNovedad());
                    this.getSelectedNovedad().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_SEGUIMIENTO"))));
                    this.getSelectedNovedad().setNSeguimiento(BigDecimal.ONE);
                    this.getSelectedNovedad().setDFecModificacion(new Date());
                    this.getSelectedNovedad().setVUsuModificacion(usuarioSession.getVUsuario());
                    this.getSelectedNovedad().setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    novedadDao.registrarNovedad(this.getSelectedNovedad());
                    this.getListaNovedad().set(index, this.getSelectedNovedad());
                }
                this.setAccionTomada(null);
                if(this.isFromMatrix()){
                    RequestContext.getCurrentInstance().execute("document.getElementById('hiddenForm:hiddenBtn').click();");
                }else{
                    RequestContext.getCurrentInstance().execute("PF('segDlg').hide();");
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la nueva acciÃ³n tomada.");
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
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
                AccionDao accionDao =(AccionDao)ServiceFinder.findBean("AccionDao");
                SegDetAccionesId segDetAccionesId = new SegDetAccionesId();
                segDetAccionesId.setNCodAccion(BigDecimal.valueOf(accionDao.nextSequenceValue()));
                segDetAccionesId.setNCodNovedad(this.getSelectedNovedad().getId().getNCodNovedad());
                segDetAccionesId.setNCodEmpresa(this.getSelectedNovedad().getId().getNCodEmpresa());
                SegDetAcciones segDetAcciones = new SegDetAcciones();
                segDetAcciones.setId(segDetAccionesId);
                segDetAcciones.setNCodAccion(segDetAccionesId.getNCodAccion());
                segDetAcciones.setNCodNovedad(segDetAccionesId.getNCodNovedad());
                segDetAcciones.setNCodEmpresa(segDetAccionesId.getNCodEmpresa());
                segDetAcciones.setDFecHora(new Date());
                segDetAcciones.setVDescripcion(this.accionTomada != null ? this.accionTomada.toUpperCase().trim() : null);
                segDetAcciones.setDFecCreacion(new Date());
                segDetAcciones.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetAcciones.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());            
                accionDao.registrarAccion(segDetAcciones);

                if(this.getListaNovedad().contains(this.getSelectedNovedad())){
                    int index = this.getListaNovedad().indexOf(this.getSelectedNovedad());
                    this.getSelectedNovedad().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
                    this.getSelectedNovedad().setNSeguimiento(BigDecimal.ZERO);
                    this.getSelectedNovedad().setDFecModificacion(new Date());
                    this.getSelectedNovedad().setVUsuModificacion(usuarioSession.getVUsuario());
                    this.getSelectedNovedad().setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    novedadDao.registrarNovedad(this.getSelectedNovedad());
                    this.getListaNovedad().set(index, this.getSelectedNovedad());
                }
                this.setAccionTomada(null);
                if(this.isFromMatrix()){
                    RequestContext.getCurrentInstance().execute("document.getElementById('hiddenForm:hiddenBtn').click();");
                }else{
                    RequestContext.getCurrentInstance().execute("PF('segDlg').hide();");
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la nueva acciÃ³n tomada.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
            
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void toEvaluacion(ActionEvent actionEvent){
        ResourceBundle bundle;
        String rowKey = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rowKey = JSFUtils.getRequestParameter("rowKey");
            SegDetNovedad segDetNovedad = this.getListaNovedad().get(Integer.parseInt(rowKey));
            this.setSelectedNovedad(segDetNovedad);
            
            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
            NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
            this.setNovedadEvaluacion(novedadEvaluacionDao.obtenerEvaluacionNovedad(segDetNovedad));
            if(this.getNovedadEvaluacion() !=null){
                if(this.getNovedadEvaluacion().getNEstado() == null){
                    this.getNovedadEvaluacion().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE_ANALISIS"))));
                }
                List<SegDetNovevalDetalle> detalles = novedadEvaluacionDetalleDao.obtenerListaDetalleEvaluacionNovedad(this.getNovedadEvaluacion());
                this.getNovedadEvaluacion().setSegDetNovevalDetalles(detalles);
            }else{
                this.setNovedadEvaluacion(new SegDetNovEvaluacion());
            }
            this.setDiagnostico(null);
            this.setRecomendacion(null);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void registrarEvaluacion(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.getNovedadEvaluacion().getNOcurrencia() != null){
                if(this.getNovedadEvaluacion().getNImpacto() != null){
                    if(this.diagnostico != null && !"".equals(this.diagnostico.trim())){
                        if(this.recomendacion != null && !"".equals(this.recomendacion.trim())){
                            bundle = ResourceBundle.getBundle(Parameters.getParameters());
                            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
                            NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
                            SegDetNovEvaluacionId segDetNovEvaluacionId = new SegDetNovEvaluacionId();
                            SegDetNovEvaluacion segDetNovEvaluacion = new SegDetNovEvaluacion();
                            if(this.getNovedadEvaluacion().getDFecCreacion() != null){
                                this.getNovedadEvaluacion().setDFecModificacion(new Date());
                                this.getNovedadEvaluacion().setVUsuModificacion(usuarioSession.getVUsuario());
                                this.getNovedadEvaluacion().setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                                novedadEvaluacionDao.registrarEvaluacion(this.getNovedadEvaluacion());
                            }else{
                                segDetNovEvaluacionId.setNCodEvaluacion(BigDecimal.valueOf(novedadEvaluacionDao.nextSequenceValue()));
                                segDetNovEvaluacionId.setNCodNovedad(this.getSelectedNovedad().getId().getNCodNovedad());
                                segDetNovEvaluacionId.setNCodEmpresa(this.getSelectedNovedad().getId().getNCodEmpresa());
                                segDetNovEvaluacion.setId(segDetNovEvaluacionId);
                                segDetNovEvaluacion.setNImpacto(this.getNovedadEvaluacion().getNImpacto() != null ? this.getNovedadEvaluacion().getNImpacto() : null);
                                segDetNovEvaluacion.setNOcurrencia(this.getNovedadEvaluacion().getNOcurrencia() != null ? this.getNovedadEvaluacion().getNOcurrencia() : null);
                                segDetNovEvaluacion.setNNivelImpacto(BigDecimal.ONE);
                                segDetNovEvaluacion.setNNivelOcurrencia(BigDecimal.ONE);
                                segDetNovEvaluacion.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_EVALUACION"))));
                                segDetNovEvaluacion.setDFecCreacion(new Date());
                                segDetNovEvaluacion.setVUsuCreacion(usuarioSession.getVUsuario());
                                segDetNovEvaluacion.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                                novedadEvaluacionDao.registrarEvaluacion(segDetNovEvaluacion);
                            }

                            SegDetNovevalDetalleId segDetNovevalDetalleId = new SegDetNovevalDetalleId();
                            segDetNovevalDetalleId.setNCodDetalle(BigDecimal.valueOf(novedadEvaluacionDetalleDao.nextSequenceValue()));
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
                            segDetNovevalDetalle.setVDiagnostico(this.diagnostico != null ? this.diagnostico.toUpperCase().trim() : null);
                            segDetNovevalDetalle.setVRecomendacion(this.recomendacion != null ? this.recomendacion.toUpperCase().trim() : null);
                            segDetNovevalDetalle.setDFecCreacion(new Date());
                            segDetNovevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetNovevalDetalle.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                            novedadEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetNovevalDetalle);
                            this.setDiagnostico(null);
                            this.setRecomendacion(null);
                            if(this.isFromMatrix()){
                                RequestContext.getCurrentInstance().execute("document.getElementById('hiddenForm:hiddenBtn').click();");
                            }else{
                                RequestContext.getCurrentInstance().execute("PF('evalDlg').hide();");
                            }
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese una recomendaciÃ³n.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese su diagnostico.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique cuÃ¡l es el impacto.");
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
            if(this.getNovedadEvaluacion().getNOcurrencia() != null){
                if(this.getNovedadEvaluacion().getNImpacto() != null){
                    if(this.diagnostico != null && !"".equals(this.diagnostico.trim())){
                        if(this.recomendacion != null && !"".equals(this.recomendacion.trim())){
                            bundle = ResourceBundle.getBundle(Parameters.getParameters());
                            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                            NovedadDao novedadDao = (NovedadDao) ServiceFinder.findBean("NovedadDao");
                            NovedadEvaluacionDao novedadEvaluacionDao =(NovedadEvaluacionDao)ServiceFinder.findBean("NovedadEvaluacionDao");
                            NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao =(NovedadEvaluacionDetalleDao)ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
                            SegDetNovEvaluacionId segDetNovEvaluacionId = new SegDetNovEvaluacionId();
                            SegDetNovEvaluacion segDetNovEvaluacion = new SegDetNovEvaluacion();
                            if(this.getNovedadEvaluacion() != null){
                                this.getNovedadEvaluacion().setDFecModificacion(new Date());
                                this.getNovedadEvaluacion().setVUsuModificacion(usuarioSession.getVUsuario());
                                this.getNovedadEvaluacion().setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                                novedadEvaluacionDao.registrarEvaluacion(this.getNovedadEvaluacion());
                            }else{
                                segDetNovEvaluacionId.setNCodEvaluacion(BigDecimal.valueOf(novedadEvaluacionDao.nextSequenceValue()));
                                segDetNovEvaluacionId.setNCodNovedad(this.getSelectedNovedad().getId().getNCodNovedad());
                                segDetNovEvaluacionId.setNCodEmpresa(this.getSelectedNovedad().getId().getNCodEmpresa());
                                segDetNovEvaluacion.setId(segDetNovEvaluacionId);
                                segDetNovEvaluacion.setNImpacto(this.getNovedadEvaluacion().getNImpacto() != null ? this.getNovedadEvaluacion().getNImpacto() : null);
                                segDetNovEvaluacion.setNOcurrencia(this.getNovedadEvaluacion().getNOcurrencia() != null ? this.getNovedadEvaluacion().getNOcurrencia() : null);
                                segDetNovEvaluacion.setNNivelImpacto(BigDecimal.ONE);
                                segDetNovEvaluacion.setNNivelOcurrencia(BigDecimal.ONE);
                                segDetNovEvaluacion.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_SOLUCIONADA"))));
                                segDetNovEvaluacion.setDFecCreacion(new Date());
                                segDetNovEvaluacion.setVUsuCreacion(usuarioSession.getVUsuario());
                                segDetNovEvaluacion.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                                novedadEvaluacionDao.registrarEvaluacion(segDetNovEvaluacion);
                            }

                            SegDetNovevalDetalleId segDetNovevalDetalleId = new SegDetNovevalDetalleId();
                            segDetNovevalDetalleId.setNCodDetalle(BigDecimal.valueOf(novedadEvaluacionDetalleDao.nextSequenceValue()));
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
                            segDetNovevalDetalle.setVDiagnostico(this.diagnostico != null ? this.diagnostico.toUpperCase().trim() : null);
                            segDetNovevalDetalle.setVRecomendacion(this.recomendacion != null ? this.recomendacion.toUpperCase().trim() : null);
                            segDetNovevalDetalle.setDFecCreacion(new Date());
                            segDetNovevalDetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetNovevalDetalle.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                            novedadEvaluacionDetalleDao.registrarEvaluacionDetalle(segDetNovevalDetalle);
                            
                            if(this.getListaNovedad().contains(this.getSelectedNovedad())){
                                int index = this.getListaNovedad().indexOf(this.getSelectedNovedad());
                                this.getSelectedNovedad().setNAnalisis(BigDecimal.ZERO);
                                this.getSelectedNovedad().setDFecModificacion(new Date());
                                this.getSelectedNovedad().setVUsuModificacion(usuarioSession.getVUsuario());
                                this.getSelectedNovedad().setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                                novedadDao.registrarNovedad(this.getSelectedNovedad());
                                this.getListaNovedad().set(index, this.getSelectedNovedad());
                            }
                            this.setDiagnostico(null);
                            this.setRecomendacion(null);
                            if(this.isFromMatrix()){
                                RequestContext.getCurrentInstance().execute("document.getElementById('hiddenForm:hiddenBtn').click();");
                            }else{
                                RequestContext.getCurrentInstance().execute("PF('evalDlg').hide();");
                            }
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese una recomendaciÃ³n.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese su diagnostico.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique cuÃ¡l es el impacto.");
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
    
    public boolean errorValidation(SegDetNovedad novedad){
        FacesMessage message = null;
        boolean error = false;
        try{
            if((novedad.getNPersona() == null || novedad.getNPersona().compareTo(BigDecimal.valueOf(-1))==0) &&
               (novedad.getNActivo() == null || novedad.getNActivo().compareTo(BigDecimal.valueOf(-1))==0) && 
               (novedad.getNProceso() == null || novedad.getNProceso().compareTo(BigDecimal.valueOf(-1))==0)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique a quÃ© afectÃ³ la novedad a registrar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNTipoNovedad() == null || novedad.getNTipoNovedad().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el tipo de novedad.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNTipoEvento() == null || novedad.getNTipoEvento().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el tipo de evento.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getDFecHora() == null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la fecha y hora de la novedad.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getVDescBreve() == null || "".equals(novedad.getVDescBreve().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n breve de la novedad.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getVDescripcion() == null || "".equals(novedad.getVDescripcion().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n de la novedad.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNLocal() == null || novedad.getNLocal().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el local de ocurrencia.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNArea() == null || novedad.getNArea().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el Ã¡rea del local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNLugar() == null || novedad.getNLugar().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el lugar del Ã¡rea.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNResponsable() == null || novedad.getNResponsable().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el responsable del Ã¡rea.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNCargo() == null || novedad.getNCargo().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Seleccione el cargo del responsable.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getVAccTomadas() == null || "".equals(novedad.getVAccTomadas().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la acciÃ³n tomada.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNAnalisis() == null || novedad.getNAnalisis().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique si la novedad necesita un anÃ¡lisis de riesgo.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(novedad.getNSeguimiento() == null || novedad.getNSeguimiento().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Indique si la novedad necesita seguimiento.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
        }catch(Exception e){
            e.getMessage();

        }
        return error;
    }
    
    
    public void listarTipoEventos(ActionEvent actionEvent){
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_EVENTO"))));
            this.setListaTipoEventos(listasSessionDao.obtenerMaestroDetalle(maestro));
            this.setSelectedTipoEvento(new SegDetMaestrodetalle());
            this.setDescripcionTipoEvento(null);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void registrarTipoEvento(ActionEvent actionEvent){
        ResourceBundle bundle;
        FacesMessage message = null;
        try{
            if(this.descripcionTipoEvento != null && !"".equals(this.descripcionTipoEvento.trim())){
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
                SegDetMaestrodetalle segDetMaestrodetalle = new SegDetMaestrodetalle();
                segDetMaestrodetalle.setVDescripcion(this.descripcionTipoEvento.toUpperCase().trim());
                
                if(listasSessionDao.buscarMaestroDetalle(segDetMaestrodetalle) == null){
                    SegCabMaestro segCabMaestro = new SegCabMaestro();
                    segCabMaestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_EVENTO"))));
                    segDetMaestrodetalle.setSegCabMaestro(segCabMaestro);
                    segDetMaestrodetalle.setNCodMaestrodetalle(BigDecimal.valueOf(listasSessionDao.getNextPk()));
                    segDetMaestrodetalle.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_EVENTO"))));
                    segDetMaestrodetalle.setNEstado(BigDecimal.ONE);
                    segDetMaestrodetalle.setDFecCreacion(new Date());
                    segDetMaestrodetalle.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetMaestrodetalle.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    listasSessionDao.registrarMaestroDetalle(segDetMaestrodetalle);
                    if(this.getListaTipoEventos() == null)
                        this.setListaTipoEventos(new ArrayList<SegDetMaestrodetalle>());
                    this.getListaTipoEventos().add(segDetMaestrodetalle);
                    this.setDescripcionTipoEvento(null);
                    SegCabMaestro maestro = new SegCabMaestro();
                    maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_EVENTO"))));
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaTipoEvento(new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
                this.setDescripcionTipoEvento(null);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del tipo de evento.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void editarTipoEvento(ActionEvent actionEvent){
        ResourceBundle bundle;
        FacesMessage message = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegDetMaestrodetalle segDetMaestrodetalle = (SegDetMaestrodetalle) actionEvent.getSource();
            if(segDetMaestrodetalle.getVDescripcion() != null && !"".equals(segDetMaestrodetalle.getVDescripcion().trim())){
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                segDetMaestrodetalle.setVDescripcion(segDetMaestrodetalle.getVDescripcion().toUpperCase().trim());
                segDetMaestrodetalle.setDFecModificacion(new Date());
                segDetMaestrodetalle.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetMaestrodetalle.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
                listasSessionDao.registrarMaestroDetalle(segDetMaestrodetalle);
                SegCabMaestro maestro = new SegCabMaestro();
                maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_EVENTO"))));
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaTipoEvento(new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                this.setDescripcionTipoEvento(null);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del tipo de evento.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void eliminarTipoEvento(ActionEvent actionEvent){
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            listasSessionDao.eliminarMaestroDetalle(this.getSelectedTipoEvento());
            this.getListaTipoEventos().remove(this.getSelectedTipoEvento());
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_EVENTO"))));
            listasSessionMB.setListaTipoEvento(new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems());
            JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void listarLocales(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
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
                SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
                SegDetLocalId segDetLocalId = new SegDetLocalId();
                segDetLocalId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                SegDetLocal segDetLocal = new SegDetLocal();
                segDetLocal.setId(segDetLocalId);
                segDetLocal.setVDescripcion(this.getDescripcionLocal().toUpperCase().trim());
                
                List lista = localDao.buscarLocales(segDetLocal);
                if(!(lista != null && !lista.isEmpty())){
                    segDetLocalId.setNCodLocal(BigDecimal.valueOf(localDao.nextSequenceValue()));
                    segDetLocalId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                    segDetLocal.setId(segDetLocalId);
                    segDetLocal.setNCodLocal(segDetLocalId.getNCodLocal());
                    segDetLocal.setNCodEmpresa(segDetLocalId.getNCodEmpresa());
                    segDetLocal.setNFlgActivo(BigDecimal.ONE);
                    segDetLocal.setDFecCreacion(new Date());
                    segDetLocal.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetLocal.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    localDao.registrarLocal(segDetLocal);
                    if(this.getListaLocales() == null)
                        this.setListaLocales(new ArrayList());
                    this.getListaLocales().add(segDetLocal);
                    this.setDescripcionLocal(null);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void editarLocal(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetLocal segDetLocal = (SegDetLocal) actionEvent.getSource();
            if(segDetLocal.getVDescripcion() != null && !"".equals(segDetLocal.getVDescripcion().trim())){
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                segDetLocal.setVDescripcion(segDetLocal.getVDescripcion().toUpperCase().trim());
                segDetLocal.setDFecModificacion(new Date());
                segDetLocal.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetLocal.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
                localDao.registrarLocal(segDetLocal);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void eliminarLocal(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            localDao.eliminarLocal(this.getSelectedLocal());
            this.getListaLocales().remove(this.getSelectedLocal());
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
            JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void listarAreas(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
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
                SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
                SegDetAreaId segDetAreaId = new SegDetAreaId();
                segDetAreaId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                segDetAreaId.setNCodLocal(this.getLocal());
                SegDetArea segDetArea = new SegDetArea();
                segDetArea.setId(segDetAreaId);
                segDetArea.setVDescripcion(this.getDescripcionArea().toUpperCase().trim());
                
                List lista = areaDao.buscarAreas(segDetArea);
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
                    segDetArea.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    areaDao.registrarArea(segDetArea);
                    if(this.getListaAreas() == null)
                        this.setListaAreas(new ArrayList());
                    this.getListaAreas().add(segDetArea);
                    this.setDescripcionArea(null);
                    SegDetLocalId segDetLocalId = new SegDetLocalId();
                    segDetLocalId.setNCodLocal(this.getLocal());
                    SegDetLocal segDetLocal = new SegDetLocal();
                    segDetLocal.setId(segDetLocalId);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
                    listasSessionMB.setListaArea(new Items(areaDao.obtenerListaAreasActivasByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del Ã¡rea.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void editarArea(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetArea segDetArea = (SegDetArea) actionEvent.getSource();
            if(segDetArea.getVDescripcion() != null && !"".equals(segDetArea.getVDescripcion().trim())){
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                segDetArea.setVDescripcion(segDetArea.getVDescripcion().toUpperCase().trim());
                segDetArea.setDFecModificacion(new Date());
                segDetArea.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetArea.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
                areaDao.registrarArea(segDetArea);
                SegDetLocalId segDetLocalId = new SegDetLocalId();
                segDetLocalId.setNCodLocal(this.getLocal());
                SegDetLocal segDetLocal = new SegDetLocal();
                segDetLocal.setId(segDetLocalId);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
                listasSessionMB.setListaArea(new Items(areaDao.obtenerListaAreasActivasByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del Ã¡rea.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void eliminarArea(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            areaDao.eliminarArea(this.getSelectedArea());
            this.getListaAreas().remove(this.getSelectedArea());
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodLocal(this.getLocal());
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
            listasSessionMB.setListaArea(new Items(areaDao.obtenerListaAreasActivasByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
            JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
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
                SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
                SegDetLugarId segDetLugarId = new SegDetLugarId();
                segDetLugarId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                segDetLugarId.setNCodLocal(this.getLocal());
                segDetLugarId.setNCodArea(this.getArea());
                SegDetLugar segDetLugar = new SegDetLugar();
                segDetLugar.setId(segDetLugarId);
                segDetLugar.setVDescripcion(this.getDescripcionLugar().toUpperCase().trim());
                
                List lista = lugarDao.buscarLugares(segDetLugar);
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
                    segDetLugar.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    lugarDao.registrarLugar(segDetLugar);
                    if(this.getListaLugares() == null)
                        this.setListaLugares(new ArrayList());
                    this.getListaLugares().add(segDetLugar);
                    this.setDescripcionLugar(null);
                    SegDetAreaId segDetAreaId = new SegDetAreaId();
                    segDetAreaId.setNCodArea(this.getArea());
                    SegDetArea segDetArea = new SegDetArea();
                    segDetArea.setId(segDetAreaId);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                    listasSessionMB.setListaLugar(new Items(lugarDao.obtenerListaLugaresActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del lugar.");
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
                SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                segDetLugar.setVDescripcion(segDetLugar.getVDescripcion().toUpperCase().trim());
                segDetLugar.setDFecModificacion(new Date());
                segDetLugar.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetLugar.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
                lugarDao.registrarLugar(segDetLugar);
                SegDetAreaId segDetAreaId = new SegDetAreaId();
                segDetAreaId.setNCodArea(this.getArea());
                SegDetArea segDetArea = new SegDetArea();
                segDetArea.setId(segDetAreaId);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                listasSessionMB.setListaLugar(new Items(lugarDao.obtenerListaLugaresActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del lugar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void eliminarLugar(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            lugarDao.eliminarLugar(this.getSelectedLugar());
            this.getListaLugares().remove(this.getSelectedLugar());
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(this.getArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            listasSessionMB.setListaLugarActivoByArea(new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
            listasSessionMB.setListaLugar(new Items(lugarDao.obtenerListaLugaresActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
            JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
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
                    SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                    ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                    SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
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

                    List lista = responsableDao.buscarResponsables(segDetResponsable);
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
                        segDetResponsable.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                        responsableDao.registrarResponsable(segDetResponsable);
                        if(this.getListaResponsables() == null)
                            this.setListaResponsables(new ArrayList());
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
                        JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
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
                    SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                    ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                    SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                    segDetResponsable.setVNombres(segDetResponsable.getVNombres().toUpperCase().trim());
                    segDetResponsable.setVApellidos(segDetResponsable.getVApellidos().toUpperCase().trim());
                    segDetResponsable.setVNombrecompleto(segDetResponsable.getVNombres()+" "+segDetResponsable.getVApellidos());
                    segDetResponsable.setDFecModificacion(new Date());
                    segDetResponsable.setVUsuModificacion(usuarioSession.getVUsuario());
                    segDetResponsable.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
                    responsableDao.registrarResponsable(segDetResponsable);
                    SegDetAreaId segDetAreaId = new SegDetAreaId();
                    segDetAreaId.setNCodArea(this.getArea());
                    SegDetArea segDetArea = new SegDetArea();
                    segDetArea.setId(segDetAreaId);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
                    listasSessionMB.setListaResponsable(new Items(responsableDao.obtenerListaResponsablesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
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
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
            responsableDao.eliminarResponsable(this.getSelectedResponsable());
            this.getListaResponsables().remove(this.getSelectedResponsable());
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea(this.getArea());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            listasSessionMB.setListaResponsableActivoByArea(new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
            listasSessionMB.setListaResponsable(new Items(responsableDao.obtenerListaResponsablesActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems());
            JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void listarCargos(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
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
                SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
                SegDetCargo segDetCargo = new SegDetCargo();
                segDetCargo.setVDescripcion(this.getDescripcionCargo().toUpperCase().trim());
                
                List lista = cargoDao.buscarCargos(segDetCargo);
                if(!(lista != null && !lista.isEmpty())){
                    segDetCargo.setNCodCargo(BigDecimal.valueOf(cargoDao.nextSequenceValue()));
                    segDetCargo.setNCodEmpresa(empresaSession.getNCodEmpresa());
                    segDetCargo.setNFlgActivo(BigDecimal.ONE);
                    segDetCargo.setDFecCreacion(new Date());
                    segDetCargo.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetCargo.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    cargoDao.registrarCargo(segDetCargo);
                    if(this.getListaCargos() == null)
                        this.setListaCargos(new ArrayList());
                    this.getListaCargos().add(segDetCargo);
                    this.setDescripcionCargo(null);
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaCargoActivoByEmpresa(new Items(cargoDao.obtenerListaCargosActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del cargo.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void editarCargo(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetCargo segDetCargo = (SegDetCargo) actionEvent.getSource();
            if(segDetCargo.getVDescripcion() != null && !"".equals(segDetCargo.getVDescripcion().trim())){
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                segDetCargo.setVDescripcion(segDetCargo.getVDescripcion().toUpperCase().trim());
                segDetCargo.setDFecModificacion(new Date());
                segDetCargo.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetCargo.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
                cargoDao.registrarCargo(segDetCargo);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaCargoActivoByEmpresa(new Items(cargoDao.obtenerListaCargosActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la descripciÃ³n del cargo.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();

        }
    }
    
    public void eliminarCargo(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            cargoDao.eliminarCargo(this.getSelectedCargo());
            this.getListaCargos().remove(this.getSelectedCargo());
            ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            listasSessionMB.setListaCargoActivoByEmpresa(new Items(cargoDao.obtenerListaCargosActivosByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
            JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(Exception e){
            e.getMessage();

        }
    }
}
