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
import pe.com.segrop.sgsapp.dao.PerfilDao;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegDetPerfilId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class PerfilMB implements Serializable{

    private String searchEmpresa;
    private String searchNombre;
    private String empresa;
    private String nombre;
    private String descripcion;
    private List<SegDetPerfil> listaPerfil;
    private List<SelectItem> itemsNombre;
    private SegDetPerfil selectedItem;
    private String action;
    
    /** Creates a new instance of PerfilMB */
    public PerfilMB() {
    }

    public String getSearchEmpresa() {
        return searchEmpresa;
    }

    public void setSearchEmpresa(String searchEmpresa) {
        this.searchEmpresa = searchEmpresa;
    }

    public String getSearchNombre() {
        return searchNombre;
    }

    public void setSearchNombre(String searchNombre) {
        this.searchNombre = searchNombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SegDetPerfil> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<SegDetPerfil> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public List<SelectItem> getItemsNombre() {
        if (this.itemsNombre == null) {
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            itemsNombre = new Items(perfilDao.obtenerListaPerfiles(), null, "NCodPerfil", "VNombre").getItems();
        }
        return itemsNombre;
    }

    public void setItemsNombre(List<SelectItem> itemsNombre) {
        this.itemsNombre = itemsNombre;
    }

    public SegDetPerfil getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(SegDetPerfil selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public void buscarPerfil(ActionEvent actionEvent) {
        try {
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            SegDetPerfil segDetPerfil = new SegDetPerfil();
            segDetPerfil.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            segDetPerfil.setVNombre(this.getSearchNombre() != null ? this.getSearchNombre().toUpperCase().trim() : null);
            setListaPerfil(perfilDao.buscarPerfiles(segDetPerfil));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            this.setEmpresa(null);
            this.setNombre(null);
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
    
    public void editarPerfil(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegDetPerfil segDetPerfil = this.getSelectedItem();
            segDetPerfil.getId().setNCodEmpresa(segDetPerfil.getId().getNCodEmpresa() != null ? segDetPerfil.getId().getNCodEmpresa() : null);
            segDetPerfil.setVNombre(segDetPerfil.getVNombre() != null ? segDetPerfil.getVNombre().toUpperCase().trim() : null);
            segDetPerfil.setVDescripcion(segDetPerfil.getVDescripcion() != null ? segDetPerfil.getVDescripcion().toUpperCase().trim() : null);
            
            if(!errorValidation(segDetPerfil)){
                PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
                //SegDetPerfil perfil = perfilDao.obtenerPerfilByNombre(segDetPerfil);
//                if(perfil != null){
//                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " El perfil ingresado ya se encuentra registrado para la empresa seleccionada.");
//                    FacesContext.getCurrentInstance().addMessage(null,message);
//                }else{
                    segDetPerfil.setDFecModificacion(new Date());
                    segDetPerfil.setVUsuModificacion(usuario.getVUsuario());
                    segDetPerfil.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                    perfilDao.registrarPerfil(segDetPerfil);
                    setListaPerfil(perfilDao.obtenerListaPerfiles());
                    this.action = "Richfaces.hideModalPanel('dlgEdit')";
                //}
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarPerfil(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegDetPerfilId segDetPerfilId = new SegDetPerfilId();
            segDetPerfilId.setNCodEmpresa(this.empresa != null ? BigDecimal.valueOf(Long.parseLong(this.empresa)) : null);
            SegDetPerfil segDetPerfil = new SegDetPerfil();
            segDetPerfil.setId(segDetPerfilId);
            segDetPerfil.setVNombre(this.nombre != null ? this.nombre.toUpperCase().trim() : null);
            segDetPerfil.setVDescripcion(this.descripcion != null ? this.descripcion.toUpperCase().trim() : null);

            if(!errorValidation(segDetPerfil)){
                PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
                SegDetPerfil perfil = perfilDao.obtenerPerfilByNombre(segDetPerfil);
                if(perfil != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " El perfil ingresado ya se encuentra registrado para la empresa seleccionada.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetPerfil.getId().setNCodPerfil(BigDecimal.valueOf(perfilDao.nextSequenceValue().longValue()));
                    segDetPerfil.setNCodEmpresa(segDetPerfilId.getNCodEmpresa());
                    segDetPerfil.setNCodPerfil(segDetPerfilId.getNCodPerfil());
                    segDetPerfil.setNFlgActivo(BigDecimal.ONE);
                    segDetPerfil.setDFecCreacion(new Date());
                    segDetPerfil.setVUsuCreacion(usuario.getVUsuario());
                    segDetPerfil.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    perfilDao.registrarPerfil(segDetPerfil);
                    setListaPerfil(perfilDao.obtenerListaPerfiles());
                    this.action = "Richfaces.hideModalPanel('dlg')";
                }
                
            }
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Desactiva el perfil seleccionado.
     * @return destino Página a la que redirecciona el método.
     */
    public void desactivar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            perfilDao.registrarPerfil(getSelectedItem());
            setListaPerfil(perfilDao.obtenerListaPerfiles());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(SegDetPerfil perfil){
        FacesMessage message = null;
        boolean error = false;
        try{
            if(perfil.getId().getNCodEmpresa() == null || perfil.getId().getNCodEmpresa().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " Seleccione la empresa del perfil.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(perfil.getVNombre() == null || "".equals(perfil.getVNombre().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " Ingrese el nombre del perfil.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(perfil.getVDescripcion() == null || "".equals(perfil.getVDescripcion().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR.", " Ingrese la descripción del perfil.");
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
    
    /**
     * Activa el perfil seleccionado.
     * @return destino Página a la que redirecciona el método.
     */
    public void activar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            perfilDao.registrarPerfil(getSelectedItem());
            setListaPerfil(perfilDao.obtenerListaPerfiles());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public List<SelectItem> completeNombre(String query) {
        List<SelectItem> suggestions = new ArrayList<SelectItem>();
        try {
            for (SelectItem p : getItemsNombre()) {
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
