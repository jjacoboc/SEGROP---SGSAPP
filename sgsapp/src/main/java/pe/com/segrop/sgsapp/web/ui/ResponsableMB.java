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
import pe.com.segrop.sgsapp.dao.AreaDao;
import pe.com.segrop.sgsapp.dao.ResponsableDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetLocal;
import pe.com.segrop.sgsapp.domain.SegDetLocalId;
import pe.com.segrop.sgsapp.domain.SegDetResponsable;
import pe.com.segrop.sgsapp.domain.SegDetResponsableId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class ResponsableMB implements Serializable{

    private BigDecimal searchLocal;
    private BigDecimal searchArea;
    private String searchNombre;
    private String searchApellido;
    private BigDecimal local;
    private BigDecimal area;
    private String nombre;
    private String apellido;
    private List<SegDetResponsable> listaResponsable;
    private List<SelectItem> itemsNombre;
    private List<SelectItem> itemsApellido;
    private SegDetResponsable selectedItem;
    private String action;
    
    /** Creates a new instance of ResponsableMB */
    public ResponsableMB() {
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

    public String getSearchNombre() {
        return searchNombre;
    }

    public void setSearchNombre(String searchNombre) {
        this.searchNombre = searchNombre;
    }

    public String getSearchApellido() {
        return searchApellido;
    }

    public void setSearchApellido(String searchApellido) {
        this.searchApellido = searchApellido;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<SegDetResponsable> getListaResponsable() {
        return listaResponsable;
    }

    public void setListaResponsable(List<SegDetResponsable> listaResponsable) {
        this.listaResponsable = listaResponsable;
    }

    public List<SelectItem> getItemsNombre() {
        if (this.itemsNombre == null) {
            ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
            itemsNombre = new Items(responsableDao.obtenerListaResponsables(), null, "NCodResponsable", "VNombres").getItems();
        }
        return itemsNombre;
    }

    public void setItemsNombre(List<SelectItem> itemsNombre) {
        this.itemsNombre = itemsNombre;
    }

    public List<SelectItem> getItemsApellido() {
        if (this.itemsApellido == null) {
            ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
            itemsApellido = new Items(responsableDao.obtenerListaResponsables(), null, "NCodResponsable", "VApellidos").getItems();
        }
        return itemsApellido;
    }

    public void setItemsApellido(List<SelectItem> itemsApellido) {
        this.itemsApellido = itemsApellido;
    }

    public SegDetResponsable getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(SegDetResponsable selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public void buscarResponsable(ActionEvent actionEvent) {
        try {
            ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
            SegDetResponsableId segDetResponsableId = new SegDetResponsableId();
            segDetResponsableId.setNCodLocal(this.getSearchLocal() != null ? this.getSearchLocal() : null);
            segDetResponsableId.setNCodArea(this.getSearchArea() != null ? this.getSearchArea() : null);
            SegDetResponsable segDetResponsable = new SegDetResponsable();
            segDetResponsable.setId(segDetResponsableId);
            segDetResponsable.setVNombres(this.getSearchNombre() != null ? this.getSearchNombre().toUpperCase().trim() : null);
            segDetResponsable.setVApellidos(this.getSearchApellido() != null ? this.getSearchApellido().toUpperCase().trim() : null);
            setListaResponsable(responsableDao.buscarResponsables(segDetResponsable));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            this.setLocal(null);
            this.setArea(null);
            this.setNombre(null);
            this.setApellido(null);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void toEditar(ActionEvent actionEvent){
        try{
            String rowkey = BaseBean.getRequestParameter("rowkey");
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodEmpresa(this.getListaResponsable().get(Integer.parseInt(rowkey)).getId().getNCodEmpresa());
            segDetLocalId.setNCodLocal(this.getListaResponsable().get(Integer.parseInt(rowkey)).getId().getNCodLocal());
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            List lista = areaDao.obtenerListaAreasByLocal(segDetLocal);
            ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSession().getAttribute("listasSessionMB");
            listasSessionMB.setListaAreaActivaByLocal(new Items(lista, Items.FIRST_ITEM_SELECT, "NCodArea","VDescripcion").getItems());
            BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
            this.setAction(null);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void registrarResponsable(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegCabEmpresa empresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegDetResponsableId segDetResponsableId = new SegDetResponsableId();
            segDetResponsableId.setNCodArea(this.area != null ? this.area : null);
            segDetResponsableId.setNCodLocal(this.local != null ? this.local : null);
            segDetResponsableId.setNCodEmpresa(empresa.getNCodEmpresa());
            SegDetResponsable segDetResponsable = new SegDetResponsable();
            segDetResponsable.setId(segDetResponsableId);
            segDetResponsable.setVNombres(this.nombre != null ? this.nombre.toUpperCase().trim() : null);
            segDetResponsable.setVApellidos(this.apellido != null ? this.apellido.toUpperCase().trim() : null);
            segDetResponsable.setVNombrecompleto(segDetResponsable.getVNombres()+" "+segDetResponsable.getVApellidos());
            if(!errorValidation(segDetResponsable)){
                ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
                SegDetResponsable responsable = responsableDao.obtenerResponsableByNombreApellido(segDetResponsable); //validando si existe el responsable ingresado.
                if(responsable !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El responsable del área seleccionada ya se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetResponsable.getId().setNCodResponsable(BigDecimal.valueOf(responsableDao.nextSequenceValue()));
                    segDetResponsable.setNFlgActivo(BigDecimal.ONE);
                    segDetResponsable.setDFecCreacion(new Date());
                    segDetResponsable.setVUsuCreacion(usuario.getVUsuario());
                    segDetResponsable.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    responsableDao.registrarResponsable(segDetResponsable);
                    setListaResponsable(responsableDao.obtenerListaResponsables());
                    this.action = "Richfaces.hideModalPanel('dlg')";
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void editarResponsable(ActionEvent event) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegDetResponsable segDetResponsable = this.getSelectedItem();
            segDetResponsable.getId().setNCodLocal(segDetResponsable.getId().getNCodLocal() != null ? segDetResponsable.getId().getNCodLocal() : null);
            segDetResponsable.getId().setNCodArea(segDetResponsable.getId().getNCodArea() != null ? segDetResponsable.getId().getNCodArea() : null);
            segDetResponsable.setVNombres(segDetResponsable.getVNombres() != null ? segDetResponsable.getVNombres().toUpperCase().trim() : null);
            segDetResponsable.setVApellidos(segDetResponsable.getVApellidos() != null ? segDetResponsable.getVApellidos().toUpperCase().trim() : null);
            segDetResponsable.setVNombrecompleto(segDetResponsable.getVNombres()+" "+segDetResponsable.getVApellidos());
            if(!errorValidation(segDetResponsable)){
                ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
                SegDetResponsable responsable = responsableDao.obtenerResponsableByNombreApellido(segDetResponsable); //validando si existe el responsable ingresado.
                if(responsable !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El responsable ingresado ya se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetResponsable.setDFecModificacion(new Date());
                    segDetResponsable.setVUsuModificacion(usuario.getVUsuario());
                    segDetResponsable.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                    responsableDao.registrarResponsable(segDetResponsable);
                    setListaResponsable(responsableDao.obtenerListaResponsables());
                    this.action = "Richfaces.hideModalPanel('dlgEdit')";
                }
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    /**
     * Desactiva el responsable seleccionado.
     * @param actionEvent
     */
    public void desactivar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            responsableDao.registrarResponsable(getSelectedItem());
            setListaResponsable(responsableDao.obtenerListaResponsables());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    /**
     * Activa el responsable seleccionado.
     * @param actionEvent
     */
    public void activar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            ResponsableDao responsableDao = (ResponsableDao) ServiceFinder.findBean("ResponsableDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            responsableDao.registrarResponsable(getSelectedItem());
            setListaResponsable(responsableDao.obtenerListaResponsables());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(SegDetResponsable responsable){
        FacesMessage message = null;
        boolean error = false;
        try{
            if(responsable.getId().getNCodLocal() == null || responsable.getId().getNCodLocal().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el local.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }if(responsable.getId().getNCodArea() == null || responsable.getId().getNCodArea().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el área.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(responsable.getVNombres() == null || "".equals(responsable.getVNombres().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese el(los) nombre(s) del responsable.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(responsable.getVApellidos() == null || "".equals(responsable.getVApellidos().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese el(los) apellido(s) del responsable.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
        }catch(Exception e){
            e.getMessage();
        }
        return error;
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
        }
        return suggestions;
    }
    
    public List<SelectItem> completeApellido(String query) {
        List<SelectItem> suggestions = new ArrayList<SelectItem>();
        try {
            for (SelectItem p : getItemsApellido()) {
                if (p.getLabel().startsWith(query.toUpperCase())) {
                    suggestions.add(p);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return suggestions;
    }
}
