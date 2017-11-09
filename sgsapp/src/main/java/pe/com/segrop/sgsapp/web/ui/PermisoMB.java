/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import pe.com.segrop.sgsapp.dao.PerfilDao;
import pe.com.segrop.sgsapp.dao.PermisoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetObjeto;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegDetPerfilId;
import pe.com.segrop.sgsapp.domain.SegRelPermiso;
import pe.com.segrop.sgsapp.domain.SegRelPermisoId;
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
public class PermisoMB implements Serializable{
    
    private String searchEmpresa;
    private String searchNombre;
    private List<SegDetPerfil> listaPerfil;
    private List<SelectItem> itemsNombre;
    private SegDetPerfil selectedPerfil;
    private List<SegDetObjeto> target;
    private List<SegDetObjeto> source;
    private SegDetObjeto selectedObjeto;
    private boolean visible;
    private Boolean flag;

    /** Creates a new instance of PermisoMB */
    public PermisoMB() {
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

    public SegDetPerfil getSelectedPerfil() {
        return selectedPerfil;
    }

    public void setSelectedPerfil(SegDetPerfil selectedPerfil) {
        this.selectedPerfil = selectedPerfil;
    }

    public List<SegDetObjeto> getTarget() {
        return target;
    }

    public void setTarget(List<SegDetObjeto> objetos) {
        this.target = objetos;
    }

    public List<SegDetObjeto> getSource() {
        return source;
    }

    public void setSource(List<SegDetObjeto> source) {
        this.source = source;
    }

    public SegDetObjeto getSelectedObjeto() {
        return selectedObjeto;
    }

    public void setSelectedObjeto(SegDetObjeto selectedObjeto) {
        this.selectedObjeto = selectedObjeto;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
            this.setSource(new ArrayList());
            this.setTarget(new ArrayList());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void buscarPerfil(ActionEvent event) {
        try {
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            SegDetPerfilId segDetPerfilId = new SegDetPerfilId();
            segDetPerfilId.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            SegDetPerfil segDetPerfil = new SegDetPerfil();
            segDetPerfil.setId(segDetPerfilId);
            segDetPerfil.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            segDetPerfil.setVNombre(this.getSearchNombre() != null ? this.getSearchNombre().toUpperCase().trim() : null);
            setListaPerfil(perfilDao.buscarPerfiles(segDetPerfil));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void handleSelectedPerfil(ActionEvent event){
        try{
            String rowkey = JSFUtils.getRequestParameter("rowkey");
            SegDetPerfil perfil = this.getListaPerfil().get(Integer.parseInt(rowkey));
            PermisoDao permisoDao = (PermisoDao) ServiceFinder.findBean("PermisoDao");
            List noAsignados = permisoDao.obtenerObjetosNoAsignadosByPerfil(perfil);
            List asignados = permisoDao.obtenerObjetosAsignadosByPerfil(perfil);
            this.setSource(noAsignados != null ? noAsignados : new ArrayList());
            this.setTarget(asignados != null ? asignados : new ArrayList());
            this.setSelectedPerfil(perfil);
            this.setVisible(true);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void onRowSourceSelected(SelectEvent event) {
        if(event != null){
            SegDetObjeto objeto = (SegDetObjeto) event.getObject();
            if(objeto != null){
                this.setSelectedObjeto(objeto);
                this.getTarget().add(this.getSelectedObjeto());
                this.getSource().remove(this.getSelectedObjeto());
            }
        }
    }

    public void onRowTargetSelected(SelectEvent event) {
        if(event != null){
            SegDetObjeto objeto = (SegDetObjeto) event.getObject();
            if(objeto != null){
                this.setSelectedObjeto(objeto);
                this.getSource().add(this.getSelectedObjeto());
                this.getTarget().remove(this.getSelectedObjeto());
            }
        }
    }
    
    public void registrarPermisos(ActionEvent event){
        FacesMessage message = null;
        try{
            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            PermisoDao permisoDao = (PermisoDao) ServiceFinder.findBean("PermisoDao");
            
            if(this.getSource() != null && !this.getSource().isEmpty()){
                for(int i=0;i<this.getSource().size();i++){
                    SegDetObjeto objeto = this.getSource().get(i);
                    SegRelPermisoId segRelPermisoId = new SegRelPermisoId();
                    segRelPermisoId.setNCodObjeto(objeto.getNCodObjeto());
                    segRelPermisoId.setNCodEmpresa(this.getSelectedPerfil().getId().getNCodEmpresa());
                    segRelPermisoId.setNCodPerfil(this.getSelectedPerfil().getId().getNCodPerfil());
                    SegRelPermiso segRelPermiso = new SegRelPermiso();
                    segRelPermiso.setId(segRelPermisoId);

                    SegRelPermiso permiso = permisoDao.obtenerPermisoById(segRelPermiso);
                    if(permiso != null){
                        permiso.setNFlgActivo(BigDecimal.ZERO);
                        permiso.setDFecModificacion(new Date());
                        permiso.setVUsuModificacion(usuarioSession.getVUsuario());
                        permiso.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                        permisoDao.registrarPermiso(permiso);
                    }
                }
            }
            if(this.getTarget() != null && !this.getTarget().isEmpty()){
                for(int i=0;i<this.getTarget().size();i++){
                    SegDetObjeto objeto = this.getTarget().get(i);
                    SegRelPermisoId segRelPermisoId = new SegRelPermisoId();
                    segRelPermisoId.setNCodObjeto(objeto.getNCodObjeto());
                    segRelPermisoId.setNCodEmpresa(this.getSelectedPerfil().getId().getNCodEmpresa());
                    segRelPermisoId.setNCodPerfil(this.getSelectedPerfil().getId().getNCodPerfil());
                    SegRelPermiso segRelPermiso = new SegRelPermiso();
                    segRelPermiso.setId(segRelPermisoId);

                    SegRelPermiso permiso = permisoDao.obtenerPermisoById(segRelPermiso);
                    if(permiso != null){
                        if(permiso.getNFlgActivo()==BigDecimal.ZERO){
                            permiso.setNFlgActivo(BigDecimal.ONE);
                            permiso.setDFecModificacion(new Date());
                            permiso.setVUsuModificacion(usuarioSession.getVUsuario());
                            permiso.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                            permisoDao.registrarPermiso(permiso);
                        }
                    }else{
                        segRelPermiso.setNFlgActivo(BigDecimal.ONE);
                        segRelPermiso.setDFecModificacion(new Date());
                        segRelPermiso.setVUsuModificacion(usuarioSession.getVUsuario());
                        segRelPermiso.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                        permisoDao.registrarPermiso(segRelPermiso);
                    }
                }
            }
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO.", "Permisos asignados con éxito.");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ocurrió un error inesperado. Póngase en contacto con el administrador del servicio.");
            FacesContext.getCurrentInstance().addMessage(null,message);
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
