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
import pe.com.segrop.sgsapp.dao.LugarDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetLocal;
import pe.com.segrop.sgsapp.domain.SegDetLocalId;
import pe.com.segrop.sgsapp.domain.SegDetLugar;
import pe.com.segrop.sgsapp.domain.SegDetLugarId;
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
public class LugarMB implements Serializable {

    private BigDecimal searchEmpresa;
    private BigDecimal searchLocal;
    private BigDecimal searchArea;
    private String searchDescripcion;
    private BigDecimal empresa;
    private BigDecimal local;
    private BigDecimal area;
    private String descripcion;
    private List<SegDetLugar> listaLugar;
    private List<SelectItem> itemsDescripcion;
    private SegDetLugar selectedItem;
    private String action;
    private Boolean flag;

    /**
     * Creates a new instance of LugarMB
     */
    public LugarMB() {
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

    /**
     * @return the segCabEmpresa
     */
    public BigDecimal getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the segCabEmpresa to set
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
                this.setFlag(true);
            } else {
                this.setFlag(false);
            }
            this.setSelectedItem(new SegDetLugar());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void buscarLugar(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            SegDetLugarId segDetLugarId = new SegDetLugarId();
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                segDetLugarId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            } else {
                segDetLugarId.setNCodEmpresa(this.getSearchEmpresa());
            }
            segDetLugarId.setNCodArea(this.getSearchArea() != null ? this.getSearchArea() : null);
            SegDetLugar segDetLugar = new SegDetLugar();
            segDetLugar.setId(segDetLugarId);
            segDetLugar.setVDescripcion(this.getSearchDescripcion() != null ? this.getSearchDescripcion().toUpperCase().trim() : null);
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            setListaLugar(lugarDao.buscarLugares(segDetLugar));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toRegistrar(ActionEvent actionEvent) {
        try {
            this.setLocal(null);
            this.setArea(null);
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
            listasSessionMB.setListaAreaActivaByLocal(new Items(null, Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
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
            String rowkey = JSFUtils.getRequestParameter("rowkey");
            this.setSelectedItem(this.getListaLugar().get(Integer.parseInt(rowkey)));
            ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSession().getAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            SegCabEmpresa segCabEmpresa = new SegCabEmpresa();            
            segCabEmpresa.setNCodEmpresa(this.getSelectedItem().getId().getNCodEmpresa());
            LocalDao localDao = (LocalDao) ServiceFinder.findBean("LocalDao");
            listasSessionMB.setListaLocalActivoByEmpresa(new Items(localDao.obtenerListaLocalesActivosByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
            SegDetLocalId segDetLocalId = new SegDetLocalId();
            segDetLocalId.setNCodEmpresa(this.getSelectedItem().getId().getNCodEmpresa());
            segDetLocalId.setNCodLocal(this.getSelectedItem().getId().getNCodLocal());
            SegDetLocal segDetLocal = new SegDetLocal();
            segDetLocal.setId(segDetLocalId);
            AreaDao areaDao = (AreaDao) ServiceFinder.findBean("AreaDao");
            listasSessionMB.setListaAreaActivaByLocal(new Items(areaDao.obtenerListaAreasActivasByLocal(segDetLocal), Items.FIRST_ITEM_SELECT, "NCodArea", "VDescripcion").getItems());
            JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            this.setAction(null);
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

    public void registrarLugar(ActionEvent actionEvent) {
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
            SegDetLugarId segDetLugarId = new SegDetLugarId();
            segDetLugarId.setNCodArea(this.area != null ? this.area : null);
            segDetLugarId.setNCodLocal(this.local != null ? this.local : null);
            segDetLugarId.setNCodEmpresa(this.getEmpresa() != null ? this.getEmpresa() : null);
            SegDetLugar segDetLugar = new SegDetLugar();
            segDetLugar.setId(segDetLugarId);
            segDetLugar.setVDescripcion(this.descripcion != null ? this.descripcion.toUpperCase().trim() : null);
            if (!errorValidation(segDetLugar)) {
                LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
                SegDetLugar lugar = lugarDao.obtenerLugarByDescripcion(segDetLugar); //validando si existe un lugar con la misma descripción
                if (lugar != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El lugar ingresado ya se encuentra registrado en el área seleccionada.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    segDetLugar.getId().setNCodLugar(BigDecimal.valueOf(lugarDao.nextSequenceValue()));
                    segDetLugar.setNFlgActivo(BigDecimal.ONE);
                    segDetLugar.setDFecCreacion(new Date());
                    segDetLugar.setVUsuCreacion(segCabUsuario.getVUsuario());
                    segDetLugar.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    lugarDao.registrarLugar(segDetLugar);
                    if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                        setListaLugar(lugarDao.obtenerListaLugaresByEmpresa(segCabEmpresa));
                    } else {
                        setListaLugar(lugarDao.obtenerListaLugares());
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editarLugar(ActionEvent event) {
        FacesMessage message = null;
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario segCabUsuario = (SegCabUsuario) JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            SegDetLugar segDetLugar = this.getSelectedItem();
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                segDetLugar.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            }
            segDetLugar.getId().setNCodLocal(segDetLugar.getId().getNCodLocal() != null ? segDetLugar.getId().getNCodLocal() : null);
            segDetLugar.getId().setNCodArea(segDetLugar.getId().getNCodArea() != null ? segDetLugar.getId().getNCodArea() : null);
            segDetLugar.setVDescripcion(segDetLugar.getVDescripcion() != null ? segDetLugar.getVDescripcion().toUpperCase().trim() : null);
            if (!errorValidation(segDetLugar)) {
                LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
                SegDetLugar lugar = lugarDao.obtenerLugarByDescripcion(segDetLugar); //validando si existe un lugar con la misma descripción
                if (lugar != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El lugar ingresado ya se encuentra registrado en el área seleccionada.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    segDetLugar.setDFecModificacion(new Date());
                    segDetLugar.setVUsuModificacion(segCabUsuario.getVUsuario());
                    segDetLugar.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    lugarDao.registrarLugar(segDetLugar);
                    if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                        setListaLugar(lugarDao.obtenerListaLugaresByEmpresa(segCabEmpresa));
                    } else {
                        setListaLugar(lugarDao.obtenerListaLugares());
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
     * Desactiva el lugar seleccionado.
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
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            lugarDao.registrarLugar(getSelectedItem());
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                setListaLugar(lugarDao.obtenerListaLugaresByEmpresa(segCabEmpresa));
            } else {
                setListaLugar(lugarDao.obtenerListaLugares());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Activa el lugar seleccionado.
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
            LugarDao lugarDao = (LugarDao) ServiceFinder.findBean("LugarDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            lugarDao.registrarLugar(getSelectedItem());
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                setListaLugar(lugarDao.obtenerListaLugaresByEmpresa(segCabEmpresa));
            } else {
                setListaLugar(lugarDao.obtenerListaLugares());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public boolean errorValidation(SegDetLugar lugar) {
        FacesMessage message = null;
        boolean error = false;
        try {
            if (lugar.getId().getNCodLocal() == null || lugar.getId().getNCodLocal().compareTo(BigDecimal.valueOf(-1)) == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el local del lugar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            if (lugar.getId().getNCodArea() == null || lugar.getId().getNCodArea().compareTo(BigDecimal.valueOf(-1)) == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el area del lugar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (lugar.getVDescripcion() == null || "".equals(lugar.getVDescripcion().trim())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción del lugar.");
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
