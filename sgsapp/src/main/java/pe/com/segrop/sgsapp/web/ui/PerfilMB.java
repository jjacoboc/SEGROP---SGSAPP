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
import pe.com.segrop.sgsapp.dao.PerfilDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegDetPerfilId;
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
public class PerfilMB implements Serializable {

    private BigDecimal searchEmpresa;
    private String searchNombre;
    private BigDecimal empresa;
    private String nombre;
    private String descripcion;
    private List<SegDetPerfil> listaPerfil;
    private List<SelectItem> itemsNombre;
    private SegDetPerfil selectedItem;
    private String action;
    private Boolean flag;

    /**
     * Creates a new instance of PerfilMB
     */
    public PerfilMB() {
    }

    public BigDecimal getSearchEmpresa() {
        return searchEmpresa;
    }

    public void setSearchEmpresa(BigDecimal searchEmpresa) {
        this.searchEmpresa = searchEmpresa;
    }

    public String getSearchNombre() {
        return searchNombre;
    }

    public void setSearchNombre(String searchNombre) {
        this.searchNombre = searchNombre;
    }

    public BigDecimal getEmpresa() {
        return empresa;
    }

    public void setEmpresa(BigDecimal empresa) {
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
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void buscarPerfil(ActionEvent actionEvent) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            SegDetPerfil segDetPerfil = new SegDetPerfil();
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                segDetPerfil.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            } else {
                segDetPerfil.setNCodEmpresa(this.getSearchEmpresa());
            }
            segDetPerfil.setNCodEmpresa(this.getSearchEmpresa());
            segDetPerfil.setVNombre(this.getSearchNombre() != null ? this.getSearchNombre().toUpperCase().trim() : null);
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            setListaPerfil(perfilDao.buscarPerfiles(segDetPerfil));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toRegistrar(ActionEvent actionEvent) {
        try {
            this.setEmpresa(null);
            this.setNombre(null);
            this.setDescripcion(null);
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

    public void toEditar(ActionEvent actionEvent) {
        try {
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

    public void editarPerfil(ActionEvent actionEvent) {
        FacesMessage message = null;
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario segCabUsuario = (SegCabUsuario) JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            SegDetPerfil segDetPerfil = this.getSelectedItem();
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                segDetPerfil.getId().setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            }
            segDetPerfil.getId().setNCodEmpresa(segDetPerfil.getId().getNCodEmpresa() != null ? segDetPerfil.getId().getNCodEmpresa() : null);
            segDetPerfil.setVNombre(segDetPerfil.getVNombre() != null ? segDetPerfil.getVNombre().toUpperCase().trim() : null);
            segDetPerfil.setVDescripcion(segDetPerfil.getVDescripcion() != null ? segDetPerfil.getVDescripcion().toUpperCase().trim() : null);

            if (!errorValidation(segDetPerfil)) {
                PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
                SegDetPerfil perfil = perfilDao.obtenerPerfilByNombre(segDetPerfil);
                if (perfil != null && !perfil.getId().getNCodPerfil().equals(segDetPerfil.getId().getNCodPerfil())) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " ERROR.", " El perfil ingresado ya se encuentra registrado para la empresa seleccionada.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    segDetPerfil.setDFecModificacion(new Date());
                    segDetPerfil.setVUsuModificacion(segCabUsuario.getVUsuario());
                    segDetPerfil.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    perfilDao.registrarPerfil(segDetPerfil);
                    if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                        setListaPerfil(perfilDao.obtenerListaPerfilesByEmpresa(segCabEmpresa));
                    } else {
                        setListaPerfil(perfilDao.obtenerListaPerfiles());
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void registrarPerfil(ActionEvent actionEvent) {
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
            SegDetPerfilId segDetPerfilId = new SegDetPerfilId();
            segDetPerfilId.setNCodEmpresa(this.getEmpresa());
            SegDetPerfil segDetPerfil = new SegDetPerfil();
            segDetPerfil.setId(segDetPerfilId);
            segDetPerfil.setVNombre(this.getNombre() != null ? this.getNombre().toUpperCase().trim() : null);
            segDetPerfil.setVDescripcion(this.getDescripcion() != null ? this.getDescripcion().toUpperCase().trim() : null);

            if (!errorValidation(segDetPerfil)) {
                PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
                SegDetPerfil perfil = perfilDao.obtenerPerfilByNombre(segDetPerfil);
                if (perfil != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " ERROR.", " El perfil ingresado ya se encuentra registrado para la empresa seleccionada.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    segDetPerfil.getId().setNCodPerfil(BigDecimal.valueOf(perfilDao.nextSequenceValue()));
                    segDetPerfil.setNCodEmpresa(segDetPerfilId.getNCodEmpresa());
                    segDetPerfil.setNCodPerfil(segDetPerfilId.getNCodPerfil());
                    segDetPerfil.setNFlgActivo(BigDecimal.ONE);
                    segDetPerfil.setDFecCreacion(new Date());
                    segDetPerfil.setVUsuCreacion(segCabUsuario.getVUsuario());
                    segDetPerfil.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    perfilDao.registrarPerfil(segDetPerfil);
                    if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                        setListaPerfil(perfilDao.obtenerListaPerfilesByEmpresa(segCabEmpresa));
                    } else {
                        setListaPerfil(perfilDao.obtenerListaPerfiles());
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
     * Desactiva el perfil seleccionado.
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
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            perfilDao.registrarPerfil(getSelectedItem());
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                setListaPerfil(perfilDao.obtenerListaPerfilesByEmpresa(segCabEmpresa));
            } else {
                setListaPerfil(perfilDao.obtenerListaPerfiles());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public boolean errorValidation(SegDetPerfil perfil) {
        FacesMessage message = null;
        boolean error = false;
        try {
            if (perfil.getId().getNCodEmpresa() == null || perfil.getId().getNCodEmpresa().compareTo(BigDecimal.valueOf(-1)) == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " ERROR.", " Seleccione la empresa del perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (perfil.getVNombre() == null || "".equals(perfil.getVNombre().trim())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " ERROR.", " Ingrese el nombre del perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (perfil.getVDescripcion() == null || "".equals(perfil.getVDescripcion().trim())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " ERROR.", " Ingrese la descripción del perfil.");
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

    /**
     * Activa el perfil seleccionado.
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
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            getSelectedItem().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            perfilDao.registrarPerfil(getSelectedItem());
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                setListaPerfil(perfilDao.obtenerListaPerfilesByEmpresa(segCabEmpresa));
            } else {
                setListaPerfil(perfilDao.obtenerListaPerfiles());
            }
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public List<SelectItem> completeNombre(String query) {
        List<SelectItem> suggestions = new ArrayList<>();
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
