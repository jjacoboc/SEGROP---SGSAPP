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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import pe.com.segrop.sgsapp.dao.CargoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetCargo;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class CargoMB implements Serializable{

    private String searchEmpresa;
    private String searchDescripcion;
    private String empresa;
    private String descripcion;
    private List<SegDetCargo> listaCargo;
    private List<SelectItem> itemsDescripcion;
    private SegDetCargo selectedItem;
    private String action;
    
    /** Creates a new instance of CargoMB */
    public CargoMB() {
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
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
    
    public void buscarCargo(ActionEvent actionEvent) {
        try {
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            SegDetCargo segDetCargo = new SegDetCargo();
            segDetCargo.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            segDetCargo.setVDescripcion(this.getSearchDescripcion() != null ? this.getSearchDescripcion().toUpperCase().trim() : null);
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
        try {
            SegCabUsuario segCabUsuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegDetCargo segDetCargo = new SegDetCargo();
            //segDetCargo.setNCodEmpresa(this.empresa != null ? BigDecimal.valueOf(Long.parseLong(this.empresa)) : null);
            segDetCargo.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            segDetCargo.setVDescripcion(this.descripcion != null ? this.descripcion.toUpperCase().trim() : null);
            if(!errorValidation(segDetCargo)){
                CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
                SegDetCargo cargo = cargoDao.obtenerCargoByDescripcion(segDetCargo); //validando si existe un cargo con la misma descripción
                if(cargo !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El cargo ingresada ya se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetCargo.setNCodCargo(BigDecimal.valueOf(cargoDao.nextSequenceValue().longValue()));
                    segDetCargo.setNFlgActivo(BigDecimal.ONE);
                    segDetCargo.setDFecCreacion(new Date());
                    segDetCargo.setVUsuCreacion(segCabUsuario.getVUsuario());
                    segDetCargo.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    cargoDao.registrarCargo(segDetCargo);
                    setListaCargo(cargoDao.obtenerListaCargos());
                    this.action = "Richfaces.hideModalPanel('dlg')";
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void editarCargo(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegCabUsuario segCabUsuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegDetCargo segDetCargo = this.getSelectedItem();
            //segDetCargo.setNCodEmpresa(segDetCargo.getNCodEmpresa() != null ? segDetCargo.getNCodEmpresa() : null);
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
                    segDetCargo.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                    cargoDao.registrarCargo(segDetCargo);
                    setListaCargo(cargoDao.obtenerListaCargos());
                    this.action = "Richfaces.hideModalPanel('dlgEdit')";
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Desactiva el local seleccionado.
     * @return destino Página a la que redirecciona el método.
     */
    public void desactivar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            cargoDao.registrarCargo(getSelectedItem());
            setListaCargo(cargoDao.obtenerListaCargos());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Activa el local seleccionado.
     * @return destino Página a la que redirecciona el método.
     */
    public void activar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            CargoDao cargoDao = (CargoDao) ServiceFinder.findBean("CargoDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            cargoDao.registrarCargo(getSelectedItem());
            setListaCargo(cargoDao.obtenerListaCargos());
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
