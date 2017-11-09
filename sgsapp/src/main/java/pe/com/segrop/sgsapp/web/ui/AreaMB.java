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
import pe.com.segrop.sgsapp.dao.AreaDao;
import pe.com.segrop.sgsapp.dao.LocalDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetAreaId;
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
public class AreaMB implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private BigDecimal searchEmpresa;
    private BigDecimal searchLocal;
    private String searchDescripcion;
    private BigDecimal empresa;
    private BigDecimal local;
    private String descripcion;
    private List<SegDetArea> listaArea;
    private List<SelectItem> itemsDescripcion;
    private SegDetArea selectedItem;
    private String action;
    private Boolean flag;

    /**
     * Creates a new instance of AreaMB
     */
    public AreaMB() {
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
    
    public BigDecimal getSearchLocal() {
        return searchLocal;
    }
    
    public void setSearchLocal(BigDecimal searchLocal) {
        this.searchLocal = searchLocal;
    }
    
    public String getSearchDescripcion() {
        return searchDescripcion;
    }
    
    public void setSearchDescripcion(String searchDescripcion) {
        this.searchDescripcion = searchDescripcion;
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
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                this.setFlag(false);
            } else {
                this.setFlag(true);
            }
            this.setSelectedItem(new SegDetArea());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void buscarArea(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                segDetAreaId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            } else {
                segDetAreaId.setNCodEmpresa(this.getSearchEmpresa());
            }
            segDetAreaId.setNCodLocal(this.getSearchLocal());
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            segDetArea.setVDescripcion(this.getSearchDescripcion() != null ? this.getSearchDescripcion().toUpperCase().trim() : null);
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            setListaArea(areaDao.buscarAreas(segDetArea));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent) {
        try {
            this.setEmpresa(null);
            this.setLocal(null);
            this.setDescripcion(null);
            this.setAction(null);
            ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            if(this.getFlag()) {
                listasSessionMB.setListaLocalActivoByEmpresa(new Items(null, Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
            } else {
                SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
                LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
                listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
            }
            JSFUtils.setSessionAttribute("listasSessionMB", listasSessionMB);
            Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
            if (iter.hasNext() == true) {
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toEditar(ActionEvent actionEvent) {
        try {
            this.setAction(null);
            String rowkey = JSFUtils.getRequestParameter("rowkey");
            this.setSelectedItem(this.getListaArea().get(Integer.parseInt(rowkey)));
            ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            SegCabEmpresa segCabEmpresa = new SegCabEmpresa();            
            segCabEmpresa.setNCodEmpresa(this.getSelectedItem().getNCodEmpresa());
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
            JSFUtils.setSessionAttribute("listasSessionMB", listasSessionMB);
            Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
            if (iter.hasNext() == true) {
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarArea(ActionEvent actionEvent) {
        FacesMessage message = null;
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario segCabUsuario = (SegCabUsuario) JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                this.setEmpresa(segCabEmpresa.getNCodEmpresa());
            }
            SegDetAreaId segDetAreaId = new SegDetAreaId();
            segDetAreaId.setNCodLocal(this.getLocal() != null ? this.getLocal() : null);
            segDetAreaId.setNCodEmpresa(this.getEmpresa() != null ? this.getEmpresa() : null);
            SegDetArea segDetArea = new SegDetArea();
            segDetArea.setId(segDetAreaId);
            segDetArea.setVDescripcion(this.getDescripcion() != null ? this.getDescripcion().toUpperCase().trim() : null);
            if (!errorValidation(segDetArea)) {
                AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
                SegDetArea area = areaDao.obtenerAreaByDescripcion(segDetArea); //validando si existe un area con la misma descripciÃ³n
                if (area != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El Ã¡rea ingresada ya se encuentra registrada en el local seleccionado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    segDetArea.getId().setNCodArea(BigDecimal.valueOf(areaDao.nextSequenceValue()));
                    segDetArea.setNFlgActivo(BigDecimal.ONE);
                    segDetArea.setDFecCreacion(new Date());
                    segDetArea.setVUsuCreacion(segCabUsuario.getVUsuario());
                    segDetArea.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    areaDao.registrarArea(segDetArea);
                    if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                        setListaArea(areaDao.obtenerListaAreasByEmpresa(segCabEmpresa));
                    } else {
                        setListaArea(areaDao.obtenerListaAreas());
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void editarArea(ActionEvent actionEvent) {
        FacesMessage message = null;
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario segCabUsuario = (SegCabUsuario) JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            SegDetArea segDetArea = this.getSelectedItem();
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                segDetArea.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            }
            segDetArea.getId().setNCodEmpresa(segDetArea.getNCodEmpresa() != null ? segDetArea.getNCodEmpresa() : null);
            segDetArea.getId().setNCodLocal(segDetArea.getNCodLocal() != null ? segDetArea.getNCodLocal() : null);
            segDetArea.setVDescripcion(segDetArea.getVDescripcion() != null ? segDetArea.getVDescripcion().toUpperCase().trim() : null);
            if (!errorValidation(segDetArea)) {
                AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
                SegDetArea area = areaDao.obtenerAreaByDescripcion(segDetArea); //validando si existe un area con la misma descripciÃ³n
                if (area != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El Ã¡rea ingresada ya se encuentra registrada en el local seleccionado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    segDetArea.setDFecModificacion(new Date());
                    segDetArea.setVUsuModificacion(segCabUsuario.getVUsuario());
                    segDetArea.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    areaDao.registrarArea(segDetArea);
                    if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                        setListaArea(areaDao.obtenerListaAreasByEmpresa(segCabEmpresa));
                    } else {
                        setListaArea(areaDao.obtenerListaAreas());
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
     * Desactiva el area selecciona el mÃ©todo.
     *
     * @param actionEvent
     */
    public void desactivar(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            HttpSession session = JSFUtils.getSession();
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            areaDao.registrarArea(getSelectedItem());
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                setListaArea(areaDao.obtenerListaAreasByEmpresa(segCabEmpresa));
            } else {
                setListaArea(areaDao.obtenerListaAreas());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Activa el area seleccionado.
     *
     * @param actionEvent
     */
    public void activar(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            HttpSession session = JSFUtils.getSession();
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            areaDao.registrarArea(getSelectedItem());
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                setListaArea(areaDao.obtenerListaAreasByEmpresa(segCabEmpresa));
            } else {
                setListaArea(areaDao.obtenerListaAreas());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(SegDetArea area) {
        FacesMessage message = null;
        boolean error = false;
        try {
            if (area.getId().getNCodEmpresa() == null || area.getId().getNCodEmpresa().compareTo(BigDecimal.valueOf(-1)) == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " ERROR.", "Seleccione la empresa del Ã¡rea.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (area.getId().getNCodLocal() == null || area.getId().getNCodLocal().compareTo(BigDecimal.valueOf(-1)) == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el local del Ã¡rea.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (area.getVDescripcion() == null || "".equals(area.getVDescripcion().trim())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripciÃ³n del Ã¡rea.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
        } catch (Exception e) {
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
