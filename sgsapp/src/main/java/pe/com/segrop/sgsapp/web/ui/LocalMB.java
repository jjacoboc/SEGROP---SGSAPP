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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import pe.com.segrop.sgsapp.dao.LocalDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
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
@ViewScoped
public class LocalMB implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String searchEmpresa;
    private String searchDescripcion;
    private BigDecimal empresa;
    private String descripcion;
    private List<SegDetLocal> listaLocal;
    private List<SelectItem> itemsDescripcion;
    private SegDetLocal selectedItem;
    private String action;
    private Boolean flag;
    
    /** Creates a new instance of LocalMB */
    public LocalMB() {
    }

    public String getSearchEmpresa() {
        return searchEmpresa;
    }

    public void setSearchEmpresa(String searchEmpresa) {
        this.searchEmpresa = searchEmpresa;
    }

    public String getSearchDescripcion() {
        return searchDescripcion;
    }

    public void setSearchDescripcion(String searchDescripcion) {
        this.searchDescripcion = searchDescripcion;
    }

    public BigDecimal getEmpresa() {
        return empresa;
    }

    public void setEmpresa(BigDecimal empresa) {
        this.empresa = empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SegDetLocal> getListaLocal() {
        return listaLocal;
    }

    public void setListaLocal(List<SegDetLocal> listaLocal) {
        this.listaLocal = listaLocal;
    }

    public List<SelectItem> getItemsDescripcion() {
        if (this.itemsDescripcion == null) {
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            itemsDescripcion = new Items(localDao.obtenerListaLocales(), null, "NCodLocal", "VDescripcion").getItems();
        }
        return itemsDescripcion;
    }

    public void setItemsDescripcion(List<SelectItem> itemsDescripcion) {
        this.itemsDescripcion = itemsDescripcion;
    }

    /**
     * @return the selectedItem
     */
    public SegDetLocal getSelectedItem() {
        return selectedItem;
    }

    /**
     * @param selectedItem the selectedItem to set
     */
    public void setSelectedItem(SegDetLocal selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
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
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            if(rucSegrop.equals(segCabEmpresa.getVRuc())) {
                this.setFlag(true);
            } else {
                this.setFlag(false);
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void buscarLocal(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                segDetLocalId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            }else{
                segDetLocalId.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            }
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            segDetLocal.setVDescripcion(this.getSearchDescripcion() != null ? this.getSearchDescripcion().toUpperCase().trim() : null);
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            setListaLocal(localDao.buscarLocales(segDetLocal));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void handleChangeValue(AjaxBehaviorEvent event) {
        try {
            if(event != null) {
                BigDecimal id = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                this.setEmpresa(id);
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            this.setEmpresa(null);
            this.setDescripcion(null);
            this.setAction(null);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toEditar(ActionEvent actionEvent){
        try{
            this.setAction(null);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void editarLocal(ActionEvent event) {
        FacesMessage message = null;
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario segCabUsuario = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetLocal segDetLocal = this.getSelectedItem();
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                segDetLocal.getId().setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            }
            segDetLocal.setNCodEmpresa(segDetLocal.getId().getNCodEmpresa() != null ? segDetLocal.getId().getNCodEmpresa() : null);
            segDetLocal.setVDescripcion(segDetLocal.getVDescripcion() != null ? segDetLocal.getVDescripcion().toUpperCase() : "");
            if(!errorValidation(segDetLocal)){
                LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
                SegDetLocal local = localDao.obtenerLocalByDescripcion(segDetLocal); //validando si existe un local con la misma descripción
                if(local != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " El local ingresado ya se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetLocal.setDFecModificacion(new Date());
                    segDetLocal.setVUsuModificacion(segCabUsuario.getVUsuario());
                    segDetLocal.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    localDao.registrarLocal(segDetLocal);
                    if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                        setListaLocal(localDao.obtenerListaLocalesByEmpresa(segCabEmpresa));
                    }else{
                        setListaLocal(localDao.obtenerListaLocales());
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarLocal(ActionEvent actionEvent) {
        FacesMessage message = null;
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario segCabUsuario = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                this.setEmpresa(segCabEmpresa.getNCodEmpresa());
            }
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodEmpresa(this.getEmpresa() != null ? this.getEmpresa() : null);
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            segDetLocal.setVDescripcion(this.getDescripcion() != null ? this.getDescripcion().toUpperCase().trim() : null);
            if(!errorValidation(segDetLocal)){
                LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
                SegDetLocal local = localDao.obtenerLocalByDescripcion(segDetLocal); //validando si existe un local con la misma descripción
                if(local != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " El local ingresado ya se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetLocal.getId().setNCodLocal(BigDecimal.valueOf(localDao.nextSequenceValue()));
                    segDetLocal.setNFlgActivo(BigDecimal.ONE);
                    segDetLocal.setDFecCreacion(new Date());
                    segDetLocal.setVUsuCreacion(segCabUsuario.getVUsuario());
                    segDetLocal.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    localDao.registrarLocal(segDetLocal);
                    if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                        setListaLocal(localDao.obtenerListaLocalesByEmpresa(segCabEmpresa));
                    }else{
                        setListaLocal(localDao.obtenerListaLocales());
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Desactiva el local seleccionadel método.
     * @param actionEvent
     */
    public void desactivar(ActionEvent actionEvent){
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try{
            HttpSession session = JSFUtils.getSession();
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            localDao.registrarLocal(getSelectedItem());
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                setListaLocal(localDao.obtenerListaLocalesByEmpresa(segCabEmpresa));
            }else{
                setListaLocal(localDao.obtenerListaLocales());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Activa el local seleccionado.
     * @param actionEvent
     */
    public void activar(ActionEvent actionEvent){
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try{
            HttpSession session = JSFUtils.getSession();
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            localDao.registrarLocal(getSelectedItem());
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                setListaLocal(localDao.obtenerListaLocalesByEmpresa(segCabEmpresa));
            }else{
                setListaLocal(localDao.obtenerListaLocales());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(SegDetLocal local){
        FacesMessage message = null;
        boolean error = false;
        try{
            if(local.getId().getNCodEmpresa() == null || local.getId().getNCodEmpresa().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " Seleccione la empresa del local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(local.getVDescripcion() == null || "".equals(local.getVDescripcion().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " Ingrese la descripción del local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return error;
    }
    
    public List<SelectItem> completeDescripcion(String query) {
        List<SelectItem> suggestions = new ArrayList<>();
        try {
            for (SelectItem p : getItemsDescripcion()) {
                if (p.getLabel().startsWith(query.toUpperCase())) {
                    suggestions.add(p);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return suggestions;
    }
}
