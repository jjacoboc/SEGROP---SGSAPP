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
import pe.com.segrop.sgsapp.dao.LugarDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetLocal;
import pe.com.segrop.sgsapp.domain.SegDetLocalId;
import pe.com.segrop.sgsapp.domain.SegDetLugar;
import pe.com.segrop.sgsapp.domain.SegDetLugarId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class LugarMB implements Serializable{

    private BigDecimal searchLocal;
    private BigDecimal searchArea;
    private String searchDescripcion;
    private BigDecimal local;
    private BigDecimal area;
    private String descripcion;
    private List<SegDetLugar> listaLugar;
    private List<SelectItem> itemsDescripcion;
    private SegDetLugar selectedItem;
    private String action;
    
    /** Creates a new instance of LugarMB */
    public LugarMB() {
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SegDetLugar> getListaLugar() {
        return listaLugar;
    }

    public void setListaLugar(List<SegDetLugar> listaLugar) {
        this.listaLugar = listaLugar;
    }

    public List<SelectItem> getItemsDescripcion() {
        if (this.itemsDescripcion == null) {
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            itemsDescripcion = new Items(lugarDao.obtenerListaLugares(), null, "NCodLugar", "VDescripcion").getItems();
        }
        return itemsDescripcion;
    }

    public void setItemsDescripcion(List<SelectItem> itemsDescripcion) {
        this.itemsDescripcion = itemsDescripcion;
    }

    public SegDetLugar getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(SegDetLugar selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public void buscarLugar(ActionEvent actionEvent) {
        try {
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            SegDetLugarId segDetLugarId = new SegDetLugarId();
            segDetLugarId.setNCodArea(this.getSearchArea() != null ? this.getSearchArea() : null);
            SegDetLugar segDetLugar = new SegDetLugar();
            segDetLugar.setId(segDetLugarId);
            segDetLugar.setVDescripcion(this.getSearchDescripcion() != null ? this.getSearchDescripcion().toUpperCase().trim() : null);
            setListaLugar(lugarDao.buscarLugares(segDetLugar));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            this.setLocal(null);
            this.setArea(null);
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
            String rowkey = BaseBean.getRequestParameter("rowkey");
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodEmpresa(this.getListaLugar().get(Integer.parseInt(rowkey)).getId().getNCodEmpresa());
            segDetLocalId.setNCodLocal(this.getListaLugar().get(Integer.parseInt(rowkey)).getId().getNCodLocal());
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
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarLugar(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegCabEmpresa empresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            SegDetLugarId segDetLugarId = new SegDetLugarId();
            segDetLugarId.setNCodArea(this.area != null ? this.area : null);
            segDetLugarId.setNCodLocal(this.local != null ? this.local : null);
            segDetLugarId.setNCodEmpresa(empresa.getNCodEmpresa());
            SegDetLugar segDetLugar = new SegDetLugar();
            segDetLugar.setId(segDetLugarId);
            segDetLugar.setVDescripcion(this.descripcion != null ? this.descripcion.toUpperCase().trim() : null);
            if(!errorValidation(segDetLugar)){
                LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
                SegDetLugar lugar = lugarDao.obtenerLugarByDescripcion(segDetLugar); //validando si existe un lugar con la misma descripción
                if(lugar !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El lugar ingresado ya se encuentra registrado en el área seleccionada.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetLugar.getId().setNCodLugar(BigDecimal.valueOf(lugarDao.nextSequenceValue().longValue()));
                    segDetLugar.setNFlgActivo(BigDecimal.ONE);
                    segDetLugar.setDFecCreacion(new Date());
                    segDetLugar.setVUsuCreacion(usuario.getVUsuario());
                    segDetLugar.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    lugarDao.registrarLugar(segDetLugar);
                    setListaLugar(lugarDao.obtenerListaLugares());
                    this.action = "Richfaces.hideModalPanel('dlg')";
                }
            }
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void editarLugar(ActionEvent event) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegDetLugar segDetLugar = this.getSelectedItem();
            segDetLugar.getId().setNCodLocal(segDetLugar.getId().getNCodLocal() != null ? segDetLugar.getId().getNCodLocal() : null);
            segDetLugar.getId().setNCodArea(segDetLugar.getId().getNCodArea() != null ? segDetLugar.getId().getNCodArea() : null);
            segDetLugar.setVDescripcion(segDetLugar.getVDescripcion() != null ? segDetLugar.getVDescripcion().toUpperCase().trim() : null);
            if(!errorValidation(segDetLugar)){
                LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
                SegDetLugar lugar = lugarDao.obtenerLugarByDescripcion(segDetLugar); //validando si existe un lugar con la misma descripción
                if(lugar !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El lugar ingresado ya se encuentra registrado en el área seleccionada.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    segDetLugar.setDFecModificacion(new Date());
                    segDetLugar.setVUsuModificacion(usuario.getVUsuario());
                    segDetLugar.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                    lugarDao.registrarLugar(segDetLugar);
                    setListaLugar(lugarDao.obtenerListaLugares());
                    this.action = "Richfaces.hideModalPanel('dlgEdit')";
                }
            }
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Desactiva el lugar seleccionado.
     * @return destino Página a la que redirecciona el método.
     */
    public void desactivar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            lugarDao.registrarLugar(getSelectedItem());
            setListaLugar(lugarDao.obtenerListaLugares());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Activa el lugar seleccionado.
     * @return destino Página a la que redirecciona el método.
     */
    public void activar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            lugarDao.registrarLugar(getSelectedItem());
            setListaLugar(lugarDao.obtenerListaLugares());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(SegDetLugar lugar){
        FacesMessage message = null;
        boolean error = false;
        try{
            if(lugar.getId().getNCodLocal() == null || lugar.getId().getNCodLocal().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el local del lugar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }if(lugar.getId().getNCodArea() == null || lugar.getId().getNCodArea().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el area del lugar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(lugar.getVDescripcion() == null || "".equals(lugar.getVDescripcion().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la descripción del lugar.");
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
