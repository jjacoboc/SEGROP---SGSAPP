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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import pe.com.segrop.sgsapp.dao.CargoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetCargo;
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
public class CargoMB implements Serializable{

    private BigDecimal searchEmpresa;
    private String searchDescripcion;
    private BigDecimal empresa;
    private String descripcion;
    private List<SegDetCargo> listaCargo;
    private List<SelectItem> itemsDescripcion;
    private SegDetCargo selectedItem;
    private String action;
    private Boolean flag;
    
    /** Creates a new instance of CargoMB */
    public CargoMB() {
    }

    public BigDecimal getSearchEmpresa() {
        return searchEmpresa;
    }

    public void setSearchEmpresa(BigDecimal searchEmpresa) {
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

    public List<SegDetCargo> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<SegDetCargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public List<SelectItem> getItemsDescripcion() {
        if (this.itemsDescripcion == null) {
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            itemsDescripcion = new Items(cargoDao.obtenerListaCargos(), null, "NCodCargo", "VDescripcion").getItems();
        }
        return itemsDescripcion;
    }

    public void setItemsDescripcion(List<SelectItem> itemsDescripcion) {
        this.itemsDescripcion = itemsDescripcion;
    }

    public SegDetCargo getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(SegDetCargo selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getAction() {
        return action;
    }

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
    
    public void buscarCargo(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetCargo segDetCargo = new SegDetCargo();
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                segDetCargo.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            }else{
                segDetCargo.setNCodEmpresa(this.getSearchEmpresa());
            }
            segDetCargo.setVDescripcion(this.getSearchDescripcion() != null ? this.getSearchDescripcion().toUpperCase().trim() : null);
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            setListaCargo(cargoDao.buscarCargos(segDetCargo));
        } catch (Exception e) {
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
    
    public void registrarCargo(ActionEvent actionEvent) {
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
            SegDetCargo segDetCargo = new SegDetCargo();
            segDetCargo.setNCodEmpresa(this.getEmpresa() != null ? this.getEmpresa() : null);
            segDetCargo.setVDescripcion(this.getDescripcion() != null ? this.getDescripcion().toUpperCase().trim() : null);
            if(!errorValidation(segDetCargo)){
                CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
                SegDetCargo cargo = cargoDao.obtenerCargoByDescripcion(segDetCargo); //validando si existe un cargo con la misma descripción
                if(cargo !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El cargo ingresada ya se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetCargo.setNCodCargo(BigDecimal.valueOf(cargoDao.nextSequenceValue()));
                    segDetCargo.setNFlgActivo(BigDecimal.ONE);
                    segDetCargo.setDFecCreacion(new Date());
                    segDetCargo.setVUsuCreacion(segCabUsuario.getVUsuario());
                    segDetCargo.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    cargoDao.registrarCargo(segDetCargo);
                    if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                        setListaCargo(cargoDao.obtenerListaCargosByEmpresa(segCabEmpresa));
                    }else{
                        setListaCargo(cargoDao.obtenerListaCargos());
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void editarCargo(ActionEvent actionEvent) {
        FacesMessage message = null;
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario segCabUsuario = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetCargo segDetCargo = this.getSelectedItem();
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                segDetCargo.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            }
            segDetCargo.setNCodEmpresa(segDetCargo.getNCodEmpresa() != null ? segDetCargo.getNCodEmpresa() : null);
            segDetCargo.setVDescripcion(segDetCargo.getVDescripcion() != null ? segDetCargo.getVDescripcion().toUpperCase().trim() : null);
            if(!errorValidation(segDetCargo)){
                CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
                SegDetCargo cargo = cargoDao.obtenerCargoByDescripcion(segDetCargo); //validando si existe un cargo con la misma descripción
                if(cargo !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El cargo ingresada ya se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetCargo.setDFecModificacion(new Date());
                    segDetCargo.setVUsuModificacion(segCabUsuario.getVUsuario());
                    segDetCargo.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    cargoDao.registrarCargo(segDetCargo);
                    if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                        setListaCargo(cargoDao.obtenerListaCargosByEmpresa(segCabEmpresa));
                    }else{
                        setListaCargo(cargoDao.obtenerListaCargos());
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Desactiva el local seleccionado.
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
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            cargoDao.registrarCargo(getSelectedItem());
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                setListaCargo(cargoDao.obtenerListaCargosByEmpresa(segCabEmpresa));
            }else{
                setListaCargo(cargoDao.obtenerListaCargos());
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
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            cargoDao.registrarCargo(getSelectedItem());
            if(!rucSegrop.equals(segCabEmpresa.getVRuc())){
                setListaCargo(cargoDao.obtenerListaCargosByEmpresa(segCabEmpresa));
            }else{
                setListaCargo(cargoDao.obtenerListaCargos());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(SegDetCargo cargo){
        FacesMessage message = null;
        boolean error = false;
        try{
            if(cargo.getNCodEmpresa() == null || cargo.getNCodEmpresa().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione la empresa del cargo.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(cargo.getVDescripcion() == null || "".equals(cargo.getVDescripcion().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la descripción del cargo.");
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
        List<SelectItem> suggestions = new ArrayList<SelectItem>();
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
