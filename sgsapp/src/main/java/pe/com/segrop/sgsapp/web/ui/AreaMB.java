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
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetAreaId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class AreaMB implements Serializable{
    private static final long serialVersionUID = 1L;

    private String searchLocal;
    private String searchDescripcion;
    private BigDecimal local;
    private String descripcion;
    private List<SegDetArea> listaArea;
    private List<SelectItem> itemsDescripcion;
    private SegDetArea selectedItem;
    private String action;
    
    /** Creates a new instance of AreaMB */
    public AreaMB() {
    }

    public String getSearchLocal() {
        return searchLocal;
    }

    public void setSearchLocal(String searchLocal) {
        this.searchLocal = searchLocal;
    }

    public String getSearchDescripcion() {
        return searchDescripcion;
    }

    public void setSearchDescripcion(String searchDescripcion) {
        this.searchDescripcion = searchDescripcion;
    }

    public BigDecimal getLocal() {
        return local;
    }

    public void setLocal(BigDecimal local) {
        this.local = local;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SegDetArea> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<SegDetArea> listaArea) {
        this.listaArea = listaArea;
    }

    public List<SelectItem> getItemsDescripcion() {
        if (this.itemsDescripcion == null) {
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            itemsDescripcion = new Items(areaDao.obtenerListaAreas(), null, "NCodArea", "VDescripcion").getItems();
        }
        return itemsDescripcion;
    }

    public void setItemsDescripcion(List<SelectItem> itemsDescripcion) {
        this.itemsDescripcion = itemsDescripcion;
    }

    public SegDetArea getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(SegDetArea selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public void buscarArea(ActionEvent actionEvent) {
        try {
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodLocal(this.getSearchLocal() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchLocal())) : null);
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            segDetArea.setVDescripcion(this.getSearchDescripcion() != null ? this.getSearchDescripcion().toUpperCase().trim() : null);
            setListaArea(areaDao.buscarAreas(segDetArea));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            this.setLocal(null);
            this.setDescripcion(null);
            this.setAction(null);
            Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
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
    
    public void registrarArea(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegCabEmpresa empresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodLocal(this.local != null ? this.local : null);
            segDetAreaId.setNCodEmpresa(empresa.getNCodEmpresa());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            segDetArea.setVDescripcion(this.descripcion != null ? this.descripcion.toUpperCase().trim() : null);
            if(!errorValidation(segDetArea)){
                AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
                SegDetArea area = areaDao.obtenerAreaByDescripcion(segDetArea); //validando si existe un area con la misma descripción
                if(area != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El área ingresada ya se encuentra registrada en el local seleccionado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{                    
                    segDetArea.getId().setNCodArea(BigDecimal.valueOf(areaDao.nextSequenceValue().longValue()));
                    segDetArea.setNFlgActivo(BigDecimal.ONE);
                    segDetArea.setDFecCreacion(new Date());
                    segDetArea.setVUsuCreacion(usuario.getVUsuario());
                    segDetArea.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    areaDao.registrarArea(segDetArea);
                    setListaArea(areaDao.obtenerListaAreas());
                    this.action = "Richfaces.hideModalPanel('dlg')";
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void editarArea(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegDetArea segDetArea = this.getSelectedItem();
            segDetArea.getId().setNCodLocal(segDetArea.getId().getNCodLocal() != null ? segDetArea.getId().getNCodLocal() : null);
            segDetArea.setVDescripcion(segDetArea.getVDescripcion() != null ? segDetArea.getVDescripcion().toUpperCase().trim() : null);
            if(!errorValidation(segDetArea)){
                AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
                SegDetArea area = areaDao.obtenerAreaByDescripcion(segDetArea); //validando si existe un area con la misma descripción
                if(area != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El área ingresada ya se encuentra registrada en el local seleccionado.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetArea.setDFecModificacion(new Date());
                    segDetArea.setVUsuModificacion(usuario.getVUsuario());
                    segDetArea.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                    areaDao.registrarArea(segDetArea);
                    setListaArea(areaDao.obtenerListaAreas());
                    this.action = "Richfaces.hideModalPanel('dlgEdit')";
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Desactiva el area seleccionado.
     * @return destino Página a la que redirecciona el método.
     */
    public void desactivar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            areaDao.registrarArea(getSelectedItem());
            setListaArea(areaDao.obtenerListaAreas());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Activa el area seleccionado.
     * @return destino Página a la que redirecciona el método.
     */
    public void activar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            areaDao.registrarArea(getSelectedItem());
            setListaArea(areaDao.obtenerListaAreas());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(SegDetArea area){
        FacesMessage message = null;
        boolean error = false;
        try{
            if(area.getId().getNCodLocal() == null || area.getId().getNCodLocal().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el local del área.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(area.getVDescripcion() == null || "".equals(area.getVDescripcion().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la descripción del área.");
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
