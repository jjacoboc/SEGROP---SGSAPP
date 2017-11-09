/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import pe.com.segrop.sgsapp.dao.AreaDao;
import pe.com.segrop.sgsapp.dao.CargoDao;
import pe.com.segrop.sgsapp.dao.CargoInspeccionDao;
import pe.com.segrop.sgsapp.dao.EmpresaDao;
import pe.com.segrop.sgsapp.dao.ListasSessionDao;
import pe.com.segrop.sgsapp.dao.LocalDao;
import pe.com.segrop.sgsapp.dao.LugarCapacitacionDao;
import pe.com.segrop.sgsapp.dao.LugarDao;
import pe.com.segrop.sgsapp.dao.LugarInspeccionDao;
import pe.com.segrop.sgsapp.dao.NovedadDao;
import pe.com.segrop.sgsapp.dao.PerfilDao;
import pe.com.segrop.sgsapp.dao.ResponsableDao;
import pe.com.segrop.sgsapp.dao.ResponsableInspeccionDao;
import pe.com.segrop.sgsapp.dao.TipoDocumentoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabLugar;
import pe.com.segrop.sgsapp.domain.SegCabMaestro;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetAreaId;
import pe.com.segrop.sgsapp.domain.SegDetLocal;
import pe.com.segrop.sgsapp.domain.SegDetLocalId;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
@ManagedBean
@SessionScoped
public class ListasSessionMB implements Serializable{
    private static final long serialVersionUID = 1L;

    private ResourceBundle bundle;
    private List<SelectItem> listaActivo;
    private List<SelectItem> listaSiNo;
    private List<SelectItem> listaTipoNumDocumento;
    private List<SelectItem> listaTipoDocumento;
    private List<SelectItem> listaProcedencia;
    private List<SelectItem> listaTipoNovedad;
    private List<SelectItem> listaTipoEvento;
    private List<SelectItem> listaAfectado;
    private List<SelectItem> listaEmpresa;
    private List<SelectItem> listaEmpresaActiva;
    private List<SelectItem> listaLocalByEmpresa;
    private List<SelectItem> listaLocalActivoByEmpresa;
    private List<SelectItem> listaAreaActivaByLocal;
    private List<SelectItem> listaLugarActivoByArea;
    private List<SelectItem> listaResponsableActivoByArea;
    private List<SelectItem> listaCargoActivoByEmpresa;
    private List<SelectItem> listaPerfilActivo;
    private List<SelectItem> listaLocal;
    private List<SelectItem> listaLocalActivo;
    private List<SelectItem> listaArea;
    private List<SelectItem> listaAreaActiva;
    private List<SelectItem> listaLugar;
    private List<SelectItem> listaResponsable;
    private List<SelectItem> listaTipoInspeccion;
    private List<SelectItem> listaControlado;
    private List<SelectItem> listaNovedad;
    private List<SelectItem> listaEstadoInspeccion;
    private List<SelectItem> listaLugarInspeccion;
    private HashMap<String, String> mapaLugarInspeccion;
    private List<SelectItem> listaResponsableInspeccion;
    private List<SelectItem> listaCargoInspeccion;
    private List<SelectItem> listaOcurrencia;
    private List<SelectItem> listaImpacto;
    private List<SelectItem> listaAyudaImpacto;
    private List<SelectItem> listaAyudaOcurrencia;
    private List<SelectItem> listaEstadoEvaluacion;
    private List<SelectItem> listaTipoRiesgo;
    private List<SelectItem> listaTipoCapacitacion;
    private List<SelectItem> listaModalidadCapacitacion;
    private List<SelectItem> listaLugarCapacitacion;
    
    /** Creates a new instance of ListasSessionMB */
    public ListasSessionMB() {
        bundle = ResourceBundle.getBundle(Parameters.getParameters());
    }

    /**
     * @return the listaActivo
     */
    public List<SelectItem> getListaActivo() {
        if(this.listaActivo==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("OPCION_ACTIVO"))));
            listaActivo =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaActivo;
    }

    /**
     * @param listaActivo the listaActivo to set
     */
    public void setListaActivo(List<SelectItem> listaActivo) {
        this.listaActivo = listaActivo;
    }

    public List<SelectItem> getListaSiNo() {
        if(this.listaSiNo==null){
            listaSiNo =  new Items(Parameters.getListaSINO(), null, "codigo","descripcion").getItems();
        }
        return listaSiNo;
    }

    public void setListaSiNo(List<SelectItem> listaSiNo) {
        this.listaSiNo = listaSiNo;
    }

    /**
     * @return the listaTipoNumDocumento
     */
    public List<SelectItem> getListaTipoNumDocumento() {
        if(this.listaTipoNumDocumento==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_NUMERO_DOCUMENTO"))));
            listaTipoNumDocumento =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaTipoNumDocumento;
    }

    /**
     * @param listaTipoNumDocumento the listaTipoNumDocumento to set
     */
    public void setListaTipoNumDocumento(List<SelectItem> listaTipoNumDocumento) {
        this.listaTipoNumDocumento = listaTipoNumDocumento;
    }

    /**
     * @return the listaTipoDocumento
     */
    public List<SelectItem> getListaTipoDocumento() {
        if(this.listaTipoDocumento==null){
            TipoDocumentoDao tipoDocumentoDao = (TipoDocumentoDao) ServiceFinder.findBean("TipoDocumentoDao");
            SegCabEmpresa owner = new SegCabEmpresa();
            owner.setNCodEmpresa(BigDecimal.valueOf(Long.parseLong(bundle.getString("COD_OWNER"))));
            listaTipoDocumento =  new Items(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(owner), Items.FIRST_ITEM_SELECT, "NCodTipoDocumento","VDescripcion").getItems();
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            if(!bundle.getString("COD_OWNER").equals(empresaSession.getNCodEmpresa().toString())){
                listaTipoDocumento.addAll(new Items(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(empresaSession), null, "NCodTipoDocumento","VDescripcion").getItems());
            }
            /*
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_DOCUMENTO"))));
            listaTipoDocumento =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
            */
        }
        return listaTipoDocumento;
    }

    /**
     * @param listaTipoDocumento the listaTipoDocumento to set
     */
    public void setListaTipoDocumento(List<SelectItem> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    /**
     * @return the listaProcedencia
     */
    public List<SelectItem> getListaProcedencia() {
        if(this.listaProcedencia==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("PROCEDENCIA"))));
            listaProcedencia =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaProcedencia;
    }

    /**
     * @param listaProcedencia the listaProcedencia to set
     */
    public void setListaProcedencia(List<SelectItem> listaProcedencia) {
        this.listaProcedencia = listaProcedencia;
    }

    public List<SelectItem> getListaTipoNovedad() {
        if(this.listaTipoNovedad==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_NOVEDAD"))));
            listaTipoNovedad =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaTipoNovedad;
    }

    public void setListaTipoNovedad(List<SelectItem> listaTipoNovedad) {
        this.listaTipoNovedad = listaTipoNovedad;
    }

    public List<SelectItem> getListaTipoEvento() {
        if(this.listaTipoEvento==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_EVENTO"))));
            listaTipoEvento =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaTipoEvento;
    }

    public void setListaTipoEvento(List<SelectItem> listaTipoEvento) {
        this.listaTipoEvento = listaTipoEvento;
    }

    public List<SelectItem> getListaAfectado() {
        if(this.listaAfectado==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("AFECTADO"))));
            listaAfectado =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), null, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaAfectado;
    }

    public void setListaAfectado(List<SelectItem> listaAfectado) {
        this.listaAfectado = listaAfectado;
    }

    /**
     * @return the listaEmpresa
     */
    public List<SelectItem> getListaEmpresa() {
        if(this.listaEmpresa==null){
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            listaEmpresa = new Items(empresaDao.obtenerListaEmpresas(), Items.FIRST_ITEM_SELECT, "NCodEmpresa", "VRazonSocial").getItems();
        }
        return listaEmpresa;
    }

    /**
     * @param listaEmpresa the listaEmpresa to set
     */
    public void setListaEmpresa(List<SelectItem> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    /**
     * @return the listaEmpresaActiva
     */
    public List<SelectItem> getListaEmpresaActiva() {
        if(this.listaEmpresaActiva==null){
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            listaEmpresaActiva = new Items(empresaDao.obtenerListaEmpresasActivas(), Items.FIRST_ITEM_SELECT, "NCodEmpresa", "VRazonSocial").getItems();
        }
        return listaEmpresaActiva;
    }

    /**
     * @param listaEmpresaActiva the listaEmpresaActiva to set
     */
    public void setListaEmpresaActiva(List<SelectItem> listaEmpresaActiva) {
        this.listaEmpresaActiva = listaEmpresaActiva;
    }

    /**
     * @return the listaLocalByEmpresa
     */
    public List<SelectItem> getListaLocalByEmpresa() {
        if(this.listaLocalByEmpresa==null){
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listaLocalByEmpresa = new Items(localDao.obtenerListaLocalesByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems();
        }
        return listaLocalByEmpresa;
    }

    /**
     * @param listaLocalByEmpresa the listaLocalByEmpresa to set
     */
    public void setListaLocalByEmpresa(List<SelectItem> listaLocalByEmpresa) {
        this.listaLocalByEmpresa = listaLocalByEmpresa;
    }

    public List<SelectItem> getListaLocalActivoByEmpresa() {
        if(this.listaLocalActivoByEmpresa==null){
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listaLocalActivoByEmpresa = new Items(localDao.obtenerListaLocalesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems();
        }
        return listaLocalActivoByEmpresa;
    }

    public void setListaLocalActivoByEmpresa(List<SelectItem> listaLocalActivoByEmpresa) {
        this.listaLocalActivoByEmpresa = listaLocalActivoByEmpresa;
    }
    
    /**
     * Devuelve la lista de locales de una empresa seleccionada.
     * @param e Evento producido al seleccionar una empresa.
     * @return listaLocalActivoByEmpresa Lista de locales.
     */
    public void obtenerListaLocalByEmpresa(AjaxBehaviorEvent e) {
        if(e!=null){
            BigDecimal value = (BigDecimal) ((SelectOneMenu) e.getSource()).getValue();
            SegCabEmpresa segCabEmpresa = new SegCabEmpresa();            
            segCabEmpresa.setNCodEmpresa(value);
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listaLocalActivoByEmpresa =  new Items(localDao.obtenerListaLocalesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal","VDescripcion").getItems();
        }else{
            listaLocalActivoByEmpresa =  new Items(null, Items.FIRST_ITEM_SELECT, "NCodLocal","VDescripcion").getItems();
        }
    }

    public List<SelectItem> getListaAreaActivaByLocal() {
        if(this.listaAreaActivaByLocal==null){
            listaAreaActivaByLocal = new Items(null, Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems();
        }
        return listaAreaActivaByLocal;
    }

    public void setListaAreaActivaByLocal(List<SelectItem> listaAreaActivaByLocal) {
        this.listaAreaActivaByLocal = listaAreaActivaByLocal;
    }
    
    /**
     * Devuelve la lista de areas de un local seleccionado.
     * @param e Evento producido al seleccionar un local.
     */
    public void obtenerListaAreaByLocal(AjaxBehaviorEvent e) {
        if(e!=null){
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodLocal((BigDecimal)((SelectOneMenu) e.getSource()).getValue());
            SegDetLocal segDetLocal = new SegDetLocal();            
            segDetLocal.setId(segDetLocalId);
            AreaDao areaDao =(AreaDao)ServiceFinder.findBean("AreaDao");
            listaAreaActivaByLocal =  new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems();
        }else{
            listaAreaActivaByLocal =  new Items(null, Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems();
        }
    }

    public List<SelectItem> getListaLugarActivoByArea() {
        if(this.listaLugarActivoByArea==null){
            listaLugarActivoByArea = new Items(null, Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems();
        }
        return listaLugarActivoByArea;
    }

    public void setListaLugarActivoByArea(List<SelectItem> listaLugarActivoByArea) {
        this.listaLugarActivoByArea = listaLugarActivoByArea;
    }
    
    /**
     * Devuelve la lista de lugares de un area seleccionado.
     * @param e Evento producido al seleccionar un area.
     */
    public void obtenerListaLugarByArea(AjaxBehaviorEvent e) {
        if(e!=null){
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea((BigDecimal)((SelectOneMenu) e.getSource()).getValue());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            LugarDao lugarDao =(LugarDao)ServiceFinder.findBean("LugarDao");
            listaLugarActivoByArea =  new Items(lugarDao.obtenerListaLugaresActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems();
        }else{
            listaLugarActivoByArea =  new Items(null, Items.FIRST_ITEM_SELECT, "NCodLugar","VDescripcion").getItems();
        }
        if(e!=null){
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea((BigDecimal)((SelectOneMenu) e.getSource()).getValue());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            ResponsableDao responsableDao =(ResponsableDao)ServiceFinder.findBean("ResponsableDao");
            listaResponsableActivoByArea =  new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable","VNombrecompleto").getItems();
        }else{
            listaResponsableActivoByArea =  new Items(null, Items.FIRST_ITEM_SELECT, "NCodResponsable","VNombrecompleto").getItems();
        }
    }

    public List<SelectItem> getListaResponsableActivoByArea() {
        if(this.listaResponsableActivoByArea==null){
            listaResponsableActivoByArea = new Items(null, Items.FIRST_ITEM_SELECT, "NCodResponsable","VDescripcion").getItems();
        }
        return listaResponsableActivoByArea;
    }

    public void setListaResponsableActivoByArea(List<SelectItem> listaResponsableActivoByArea) {
        this.listaResponsableActivoByArea = listaResponsableActivoByArea;
    }
    
    /**
     * Devuelve la lista de los responsables de un area seleccionada.
     * @param e Evento producido al seleccionar un area.
     * @return listaAreaActivaByLocal Lista de responsables.
     */
    public List<SelectItem> obtenerListaResponsableByArea(ValueChangeEvent e) {
        if(e!=null){
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodArea((BigDecimal)e.getNewValue());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            ResponsableDao responsableDao =(ResponsableDao)ServiceFinder.findBean("ResponsableDao");
            listaResponsableActivoByArea =  new Items(responsableDao.obtenerListaResponsablesActivosByArea(segDetArea), Items.FIRST_ITEM_SELECT, "NCodResponsable","VNombrecompleto").getItems();
        }else{
            listaResponsableActivoByArea =  new Items(null, Items.FIRST_ITEM_SELECT, "NCodResponsable","VNombrecompleto").getItems();
        }
        return listaResponsableActivoByArea;
    }

    public List<SelectItem> getListaCargoActivoByEmpresa() {
        if(this.listaCargoActivoByEmpresa==null){
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            listaCargoActivoByEmpresa = new Items(cargoDao.obtenerListaCargosActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems();
        }
        return listaCargoActivoByEmpresa;
    }

    public void setListaCargoActivoByEmpresa(List<SelectItem> listaCargoActivoByEmpresa) {
        this.listaCargoActivoByEmpresa = listaCargoActivoByEmpresa;
    }

    public List<SelectItem> getListaPerfilActivo() {
        if(this.listaPerfilActivo==null){
            listaPerfilActivo = new Items(null, Items.FIRST_ITEM_SELECT, "NCodPerfil","VNombre").getItems();
        }
        return listaPerfilActivo;
    }

    public void setListaPerfilActivo(List<SelectItem> listaPerfilActivo) {
        this.listaPerfilActivo = listaPerfilActivo;
    }
    
    /**
     * Devuelve la lista de los perfil de una empresa seleccionada.
     * @param e Evento producido al seleccionar una empresa.
     * @return listaPerfilActivo Lista de perfiles.
     */
    public List<SelectItem> obtenerListaPerfilByEmpresa(ValueChangeEvent e) {
        if(e!=null){
            SegCabEmpresa segCabEmpresa = new SegCabEmpresa();
            segCabEmpresa.setNCodEmpresa((BigDecimal)e.getNewValue());
            PerfilDao perfilDao =(PerfilDao)ServiceFinder.findBean("PerfilDao");
            listaPerfilActivo =  new Items(perfilDao.obtenerListaPerfilesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodPerfil","VNombre").getItems();
        }else{
            listaPerfilActivo =  new Items(null, Items.FIRST_ITEM_SELECT, "NCodPerfil","VNombre").getItems();
        }
        return listaPerfilActivo;
    }

    /**
     * @return the listaLocal
     */
    public List<SelectItem> getListaLocal() {
        if(this.listaLocal==null){
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listaLocal = new Items(localDao.obtenerListaLocales(), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems();
        }
        return listaLocal;
    }

    /**
     * @param listaLocal the listaLocal to set
     */
    public void setListaLocal(List<SelectItem> listaLocal) {
        this.listaLocal = listaLocal;
    }

    /**
     * @return the listaLocalActivo
     */
    public List<SelectItem> getListaLocalActivo() {
        if(this.listaLocalActivo==null){
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listaLocalActivo = new Items(localDao.obtenerListaLocalesActivos(), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems();
        }
        return listaLocalActivo;
    }

    /**
     * @param listaLocalActivo the listaLocalActivo to set
     */
    public void setListaLocalActivo(List<SelectItem> listaLocalActivo) {
        this.listaLocalActivo = listaLocalActivo;
    }

    public List<SelectItem> getListaArea() {
        if(this.listaArea==null){
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            listaArea = new Items(areaDao.obtenerListaAreas(), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems();
        }
        return listaArea;
    }

    public void setListaArea(List<SelectItem> listaArea) {
        this.listaArea = listaArea;
    }

    /**
     * @return the listaAreaActiva
     */
    public List<SelectItem> getListaAreaActiva() {
        if(this.listaAreaActiva==null){
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            listaAreaActiva = new Items(areaDao.obtenerListaAreasActivas(), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems();
        }
        return listaAreaActiva;
    }

    /**
     * @param listaAreaActiva the listaAreaActiva to set
     */
    public void setListaAreaActiva(List<SelectItem> listaAreaActiva) {
        this.listaAreaActiva = listaAreaActiva;
    }

    public List<SelectItem> getListaLugar() {
        if(this.listaLugar==null){
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            listaLugar = new Items(lugarDao.obtenerListaLugaresActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems();
        }
        return listaLugar;
    }

    public void setListaLugar(List<SelectItem> listaLugar) {
        this.listaLugar = listaLugar;
    }

    public List<SelectItem> getListaResponsable() {
        if(this.listaResponsable==null){
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            ResponsableDao responsableDao =(ResponsableDao)ServiceFinder.findBean("ResponsableDao");
            listaResponsable = new Items(responsableDao.obtenerListaResponsablesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VNombrecompleto").getItems();
        }
        return listaResponsable;
    }

    public void setListaResponsable(List<SelectItem> listaResponsable) {
        this.listaResponsable = listaResponsable;
    }

    public List<SelectItem> getListaTipoInspeccion() {
        if(this.listaTipoInspeccion==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_INSPECCION"))));
            listaTipoInspeccion =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaTipoInspeccion;
    }

    public void setListaTipoInspeccion(List<SelectItem> listaTipoInspeccion) {
        this.listaTipoInspeccion = listaTipoInspeccion;
    }

    public List<SelectItem> getListaControlado() {
        if(this.listaControlado==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("CONTROLADO"))));
            listaControlado =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), null, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaControlado;
    }

    public void setListaControlado(List<SelectItem> listaControlado) {
        this.listaControlado = listaControlado;
    }

    public List<SelectItem> getListaNovedad() {
        return listaNovedad;
    }

    public void setListaNovedad(List<SelectItem> listaNovedad) {
        this.listaNovedad = listaNovedad;
    }

    /**
     * @return the listaEstadoInspeccion
     */
    public List<SelectItem> getListaEstadoInspeccion() {
        if(this.listaEstadoInspeccion==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_INSPECCION"))));
            listaEstadoInspeccion =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), null, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaEstadoInspeccion;
    }

    /**
     * @param listaEstadoInspeccion the listaEstadoInspeccion to set
     */
    public void setListaEstadoInspeccion(List<SelectItem> listaEstadoInspeccion) {
        this.listaEstadoInspeccion = listaEstadoInspeccion;
    }

    /**
     * @return the listaLugarInspeccion
     */
    public List<SelectItem> getListaLugarInspeccion() {
        if(this.listaLugarInspeccion==null){
            LugarInspeccionDao lugarInspeccionDao = (LugarInspeccionDao) ServiceFinder.findBean("LugarInspeccionDao");
            listaLugarInspeccion = new Items(lugarInspeccionDao.obtenerListaLugares(), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems();
        }
        return listaLugarInspeccion;
    }

    /**
     * @param listaLugarInspeccion the listaLugarInspeccion to set
     */
    public void setListaLugarInspeccion(List<SelectItem> listaLugarInspeccion) {
        this.listaLugarInspeccion = listaLugarInspeccion;
    }

    /**
     * @return the mapaLugarInspeccion
     */
    public HashMap<String, String> getMapaLugarInspeccion() {
        if(this.mapaLugarInspeccion==null){
            LugarInspeccionDao lugarInspeccionDao = (LugarInspeccionDao) ServiceFinder.findBean("LugarInspeccionDao");
            List lista = lugarInspeccionDao.obtenerListaLugares();
            if(lista != null && !lista.isEmpty()){
                this.mapaLugarInspeccion = new HashMap();
                for(int i=0;i<lista.size();i++){
                    SegCabLugar lugar = (SegCabLugar)lista.get(i);
                    this.mapaLugarInspeccion.put(lugar.getVDescripcion(),lugar.getNCodLugar().toString());
                }
            }
        }
        return mapaLugarInspeccion;
    }

    /**
     * @param mapaLugarInspeccion the mapaLugarInspeccion to set
     */
    public void setMapaLugarInspeccion(HashMap<String, String> mapaLugarInspeccion) {
        this.mapaLugarInspeccion = mapaLugarInspeccion;
    }

    /**
     * @return the listaResponsableInspeccion
     */
    public List<SelectItem> getListaResponsableInspeccion() {
        if(this.listaResponsableInspeccion==null){
            ResponsableInspeccionDao responsableInspeccionDao = (ResponsableInspeccionDao) ServiceFinder.findBean("ResponsableInspeccionDao");
            listaResponsableInspeccion = new Items(responsableInspeccionDao.obtenerListaResponsables(), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VDescripcion").getItems();
        }
        return listaResponsableInspeccion;
    }

    /**
     * @param listaResponsableInspeccion the listaResponsableInspeccion to set
     */
    public void setListaResponsableInspeccion(List<SelectItem> listaResponsableInspeccion) {
        this.listaResponsableInspeccion = listaResponsableInspeccion;
    }

    /**
     * @return the listaCargoInspeccion
     */
    public List<SelectItem> getListaCargoInspeccion() {
        if(this.listaCargoInspeccion==null){
            CargoInspeccionDao cargoInspeccionDao = (CargoInspeccionDao) ServiceFinder.findBean("CargoInspeccionDao");
            listaCargoInspeccion = new Items(cargoInspeccionDao.obtenerListaCargos(), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems();
        }
        return listaCargoInspeccion;
    }

    /**
     * @param listaCargoInspeccion the listaCargoInspeccion to set
     */
    public void setListaCargoInspeccion(List<SelectItem> listaCargoInspeccion) {
        this.listaCargoInspeccion = listaCargoInspeccion;
    }

    public List<SelectItem> getListaOcurrencia() {
        if(this.listaOcurrencia==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("OCURRENCIA"))));
            listaOcurrencia =  new Items(listasSessionDao.obtenerMaestroDetalleOrderDesc(maestro), null, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaOcurrencia;
    }

    public void setListaOcurrencia(List<SelectItem> listaOcurrencia) {
        this.listaOcurrencia = listaOcurrencia;
    }

    public List<SelectItem> getListaImpacto() {
        if(this.listaImpacto==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("IMPACTO"))));
            listaImpacto =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), null, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaImpacto;
    }

    public void setListaImpacto(List<SelectItem> listaImpacto) {
        this.listaImpacto = listaImpacto;
    }

    /**
     * @return the listaAyudaImpacto
     */
    public List<SelectItem> getListaAyudaImpacto() {
        if(this.listaAyudaImpacto==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("AYUDA_IMPACTO"))));
            listaAyudaImpacto =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), null, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaAyudaImpacto;
    }

    /**
     * @param listaAyudaImpacto the listaAyudaImpacto to set
     */
    public void setListaAyudaImpacto(List<SelectItem> listaAyudaImpacto) {
        this.listaAyudaImpacto = listaAyudaImpacto;
    }

    /**
     * @return the listaAyudaOcurrencia
     */
    public List<SelectItem> getListaAyudaOcurrencia() {
        if(this.listaAyudaOcurrencia==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("AYUDA_OCURRENCIA"))));
            listaAyudaOcurrencia =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), null, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaAyudaOcurrencia;
    }

    /**
     * @param listaAyudaOcurrencia the listaAyudaOcurrencia to set
     */
    public void setListaAyudaOcurrencia(List<SelectItem> listaAyudaOcurrencia) {
        this.listaAyudaOcurrencia = listaAyudaOcurrencia;
    }

    public List<SelectItem> getListaEstadoEvaluacion() {
        if(this.listaEstadoEvaluacion==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EVALUACION"))));
            listaEstadoEvaluacion =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), null, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaEstadoEvaluacion;
    }

    public void setListaEstadoEvaluacion(List<SelectItem> listaEstadoEvaluacion) {
        this.listaEstadoEvaluacion = listaEstadoEvaluacion;
    }

    /**
     * @return the listaTipoRiesgo
     */
    public List<SelectItem> getListaTipoRiesgo() {
        if(this.listaTipoRiesgo==null){
            listaTipoRiesgo =  new Items(Parameters.getListaTipoRiesgo(), Items.FIRST_ITEM_SELECT, "codigo","descripcion").getItems();
        }
        return listaTipoRiesgo;
    }

    /**
     * @param listaTipoRiesgo the listaTipoRiesgo to set
     */
    public void setListaTipoRiesgo(List<SelectItem> listaTipoRiesgo) {
        this.listaTipoRiesgo = listaTipoRiesgo;
    }

    /**
     * @return the listaTipoCapacitacion
     */
    public List<SelectItem> getListaTipoCapacitacion() {
        if(this.listaTipoCapacitacion==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("TIPO_CAPACITACION"))));
            listaTipoCapacitacion =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaTipoCapacitacion;
    }

    /**
     * @param listaTipoCapacitacion the listaTipoCapacitacion to set
     */
    public void setListaTipoCapacitacion(List<SelectItem> listaTipoCapacitacion) {
        this.listaTipoCapacitacion = listaTipoCapacitacion;
    }

    /**
     * @return the listaModalidadCapacitacion
     */
    public List<SelectItem> getListaModalidadCapacitacion() {
        if(this.listaModalidadCapacitacion==null){
            ListasSessionDao listasSessionDao = (ListasSessionDao) ServiceFinder.findBean("ListasSessionDao");
            SegCabMaestro maestro = new SegCabMaestro();
            maestro.setNCodMaestro(BigDecimal.valueOf(Long.parseLong(bundle.getString("MODALIDAD_CAPACITACION"))));
            listaModalidadCapacitacion =  new Items(listasSessionDao.obtenerMaestroDetalle(maestro), Items.FIRST_ITEM_SELECT, "NCodMaestrodetalle","VDescripcion").getItems();
        }
        return listaModalidadCapacitacion;
    }

    /**
     * @param listaModalidadCapacitacion the listaModalidadCapacitacion to set
     */
    public void setListaModalidadCapacitacion(List<SelectItem> listaModalidadCapacitacion) {
        this.listaModalidadCapacitacion = listaModalidadCapacitacion;
    }

    public List<SelectItem> getListaLugarCapacitacion() {
        if(this.listaLugarCapacitacion==null){
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            LugarCapacitacionDao lugarCapacitacionDao = (LugarCapacitacionDao) ServiceFinder.findBean("LugarCapacitacionDao");
            listaLugarCapacitacion = new Items(lugarCapacitacionDao.obtenerListaLugaresCapacitacionByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLugarCapacitacion", "VDescripcion").getItems();
        }
        return listaLugarCapacitacion;
    }

    public void setListaLugarCapacitacion(List<SelectItem> listaLugarCapacitacion) {
        this.listaLugarCapacitacion = listaLugarCapacitacion;
    }
}
