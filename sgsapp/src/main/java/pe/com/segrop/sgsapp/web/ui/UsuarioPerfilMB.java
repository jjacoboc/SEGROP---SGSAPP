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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import pe.com.segrop.sgsapp.dao.UsuarioDao;
import pe.com.segrop.sgsapp.dao.UsuarioPerfilDao;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegCabUsuarioId;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegRelUsuarioperfil;
import pe.com.segrop.sgsapp.domain.SegRelUsuarioperfilId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
public class UsuarioPerfilMB implements Serializable{

    private String searchEmpresa;
    private String searchNumTipoDocumento;
    private String searchNumDocumento;
    private String searchNombre;
    private String searchApellido;
    private List<SegCabUsuario> listaUsuario;
    private List<SelectItem> itemsNumDocumento;
    private List<SelectItem> itemsNombre;
    private List<SelectItem> itemsApellido;
    private SegCabUsuario selectedUsuario;
    private List<SegDetPerfil> source;
    private List<SegDetPerfil> target;
    private SegDetPerfil selectedPerfil;
    private boolean visible;
    
    /** Creates a new instance of UsuarioPerfilMB */
    public UsuarioPerfilMB() {
        this.setSource(new ArrayList());
        this.setTarget(new ArrayList());
    }

    public String getSearchEmpresa() {
        return searchEmpresa;
    }

    public void setSearchEmpresa(String searchEmpresa) {
        this.searchEmpresa = searchEmpresa;
    }

    public String getSearchNumTipoDocumento() {
        return searchNumTipoDocumento;
    }

    public void setSearchNumTipoDocumento(String searchNumTipoDocumento) {
        this.searchNumTipoDocumento = searchNumTipoDocumento;
    }

    public String getSearchNumDocumento() {
        return searchNumDocumento;
    }

    public void setSearchNumDocumento(String searchNumDocumento) {
        this.searchNumDocumento = searchNumDocumento;
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

    public List<SegCabUsuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<SegCabUsuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<SelectItem> getItemsNumDocumento() {
        if (this.itemsNumDocumento == null) {
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            itemsNumDocumento = new Items(usuarioDao.obtenerListaUsuarios(), null, "NCodUsuario", "VNumDocumento").getItems();
        }
        return itemsNumDocumento;
    }

    public void setItemsNumDocumento(List<SelectItem> itemsNumDocumento) {
        this.itemsNumDocumento = itemsNumDocumento;
    }

    public List<SelectItem> getItemsNombre() {
        if (this.itemsNombre == null) {
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            itemsNombre = new Items(usuarioDao.obtenerListaUsuarios(), null, "NCodUsuario", "VNombres").getItems();
        }
        return itemsNombre;
    }

    public void setItemsNombre(List<SelectItem> itemsNombre) {
        this.itemsNombre = itemsNombre;
    }

    public List<SelectItem> getItemsApellido() {
        if (this.itemsApellido == null) {
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            itemsApellido = new Items(usuarioDao.obtenerListaUsuarios(), null, "NCodUsuario", "VApellidos").getItems();
        }
        return itemsApellido;
    }

    public void setItemsApellido(List<SelectItem> itemsApellido) {
        this.itemsApellido = itemsApellido;
    }

    public SegCabUsuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(SegCabUsuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<SegDetPerfil> getSource() {
        return source;
    }

    public void setSource(List<SegDetPerfil> source) {
        this.source = source;
    }

    public List<SegDetPerfil> getTarget() {
        return target;
    }

    public void setTarget(List<SegDetPerfil> target) {
        this.target = target;
    }

    public SegDetPerfil getSelectedPerfil() {
        return selectedPerfil;
    }

    public void setSelectedPerfil(SegDetPerfil selectedPerfil) {
        this.selectedPerfil = selectedPerfil;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public void buscarUsuario(ActionEvent event) {
        try {
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            SegCabUsuarioId segCabUsuarioId = new SegCabUsuarioId();
            segCabUsuarioId.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            SegCabUsuario segCabUsuario = new SegCabUsuario();
            segCabUsuario.setId(segCabUsuarioId);
            segCabUsuario.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            segCabUsuario.setNTipNumDocumento(this.getSearchNumTipoDocumento() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchNumTipoDocumento())) : null);
            segCabUsuario.setVNumDocumento(this.getSearchNumDocumento() != null ? this.getSearchNumDocumento().toUpperCase().trim() : null);
            segCabUsuario.setVNombres(this.getSearchNombre() != null ? this.getSearchNombre().toUpperCase().trim() : null);
            segCabUsuario.setVApellidos(this.getSearchApellido() != null ? this.getSearchApellido().toUpperCase().trim() : null);
            setListaUsuario(usuarioDao.buscarUsuariosActivos(segCabUsuario));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void handleSelectedUsuario(ActionEvent event){
        try{
            String rowkey = BaseBean.getRequestParameter("rowkey");
            SegCabUsuario usuario = this.getListaUsuario().get(Integer.parseInt(rowkey));
            UsuarioPerfilDao usuarioPerfilDao = (UsuarioPerfilDao) ServiceFinder.findBean("UsuarioPerfilDao");
            List noAsignados = usuarioPerfilDao.obtenerPerfilesNoAsignadosByUsuario(usuario);
            List asignados = usuarioPerfilDao.obtenerPerfilesAsignadosByUsuario(usuario);
            this.setSource(noAsignados != null ? noAsignados : new ArrayList());
            this.setTarget(asignados != null ? asignados : new ArrayList());
            this.setSelectedUsuario(usuario);
            this.setVisible(true);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void onRowSourceSelected(ActionEvent event) {
        try{
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            UsuarioPerfilDao usuarioPerfilDao = (UsuarioPerfilDao) ServiceFinder.findBean("UsuarioPerfilDao");
            if(this.getSelectedPerfil() != null){
                SegDetPerfil perfil = this.getSelectedPerfil();
                SegCabUsuario usuario = this.getSelectedUsuario();
                SegRelUsuarioperfilId segRelUsuarioperfilId = new SegRelUsuarioperfilId();
                segRelUsuarioperfilId.setNCodUsuario(usuario.getId().getNCodUsuario());
                segRelUsuarioperfilId.setNCodEmpresa(usuario.getId().getNCodEmpresa());
                segRelUsuarioperfilId.setNCodPerfil(perfil.getId().getNCodPerfil());
                SegRelUsuarioperfil segRelUsuarioperfil = new SegRelUsuarioperfil();
                segRelUsuarioperfil.setId(segRelUsuarioperfilId);
                segRelUsuarioperfil.setNFlgActivo(BigDecimal.ONE);
                segRelUsuarioperfil.setDFecModificacion(new Date());
                segRelUsuarioperfil.setVUsuModificacion(usuarioSession.getVUsuario());
                segRelUsuarioperfil.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                usuarioPerfilDao.registrarAsignacion(segRelUsuarioperfil);

                this.getTarget().add(this.getSelectedPerfil());
                this.getSource().remove(this.getSelectedPerfil());
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onRowTargetSelected(ActionEvent event) {
        try{
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            UsuarioPerfilDao usuarioPerfilDao = (UsuarioPerfilDao) ServiceFinder.findBean("UsuarioPerfilDao");
            if(this.getSelectedPerfil() != null){
                SegDetPerfil perfil = this.getSelectedPerfil();
                SegCabUsuario usuario = this.getSelectedUsuario();
                SegRelUsuarioperfilId segRelUsuarioperfilId = new SegRelUsuarioperfilId();
                segRelUsuarioperfilId.setNCodUsuario(usuario.getId().getNCodUsuario());
                segRelUsuarioperfilId.setNCodEmpresa(usuario.getId().getNCodEmpresa());
                segRelUsuarioperfilId.setNCodPerfil(perfil.getId().getNCodPerfil());
                SegRelUsuarioperfil segRelUsuarioperfil = new SegRelUsuarioperfil();
                segRelUsuarioperfil.setId(segRelUsuarioperfilId);
                segRelUsuarioperfil.setNFlgActivo(BigDecimal.ZERO);
                segRelUsuarioperfil.setDFecModificacion(new Date());
                segRelUsuarioperfil.setVUsuModificacion(usuarioSession.getVUsuario());
                segRelUsuarioperfil.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                usuarioPerfilDao.registrarAsignacion(segRelUsuarioperfil);
                
                this.getSource().add(this.getSelectedPerfil());
                this.getTarget().remove(this.getSelectedPerfil());
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void registrarAsignacion(ActionEvent event){
        FacesMessage message = null;
        try{
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            UsuarioPerfilDao usuarioPerfilDao = (UsuarioPerfilDao) ServiceFinder.findBean("UsuarioPerfilDao");
            
            if(this.getSource() != null && !this.getSource().isEmpty()){
                for(int i=0;i<this.getSource().size();i++){
                    SegDetPerfil perfil = this.getSource().get(i);
                    SegRelUsuarioperfilId segRelUsuarioperfilId = new SegRelUsuarioperfilId();
                    segRelUsuarioperfilId.setNCodUsuario(usuarioSession.getId().getNCodUsuario());
                    segRelUsuarioperfilId.setNCodEmpresa(usuarioSession.getId().getNCodEmpresa());
                    segRelUsuarioperfilId.setNCodPerfil(perfil.getId().getNCodPerfil());
                    SegRelUsuarioperfil segRelUsuarioperfil = new SegRelUsuarioperfil();
                    segRelUsuarioperfil.setId(segRelUsuarioperfilId);

                    SegRelUsuarioperfil usuarioperfil = usuarioPerfilDao.obtenerUsuarioPerfilById(segRelUsuarioperfil);
                    if(usuarioperfil != null){
                        usuarioperfil.setNFlgActivo(BigDecimal.ZERO);
                        usuarioperfil.setDFecModificacion(new Date());
                        usuarioperfil.setVUsuModificacion(usuarioSession.getVUsuario());
                        usuarioperfil.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                        usuarioPerfilDao.registrarAsignacion(usuarioperfil);
                    }
                }
            }
            if(this.getTarget() != null && !this.getTarget().isEmpty()){
                for(int i=0;i<this.getTarget().size();i++){
                    SegDetPerfil perfil = this.getTarget().get(i);
                    SegRelUsuarioperfilId segRelUsuarioperfilId = new SegRelUsuarioperfilId();
                    segRelUsuarioperfilId.setNCodUsuario(usuarioSession.getId().getNCodUsuario());
                    segRelUsuarioperfilId.setNCodEmpresa(usuarioSession.getId().getNCodEmpresa());
                    segRelUsuarioperfilId.setNCodPerfil(perfil.getId().getNCodPerfil());
                    SegRelUsuarioperfil segRelUsuarioperfil = new SegRelUsuarioperfil();
                    segRelUsuarioperfil.setId(segRelUsuarioperfilId);

                    SegRelUsuarioperfil usuarioperfil = usuarioPerfilDao.obtenerUsuarioPerfilById(segRelUsuarioperfil);
                    if(usuarioperfil != null){
                        if(usuarioperfil.getNFlgActivo()==BigDecimal.ZERO){
                            usuarioperfil.setNFlgActivo(BigDecimal.ONE);
                            usuarioperfil.setDFecModificacion(new Date());
                            usuarioperfil.setVUsuModificacion(usuarioSession.getVUsuario());
                            usuarioperfil.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                            usuarioPerfilDao.registrarAsignacion(usuarioperfil);
                        }
                    }else{
                        segRelUsuarioperfil.setNFlgActivo(BigDecimal.ONE);
                        segRelUsuarioperfil.setDFecModificacion(new Date());
                        segRelUsuarioperfil.setVUsuModificacion(usuarioSession.getVUsuario());
                        segRelUsuarioperfil.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                        usuarioPerfilDao.registrarAsignacion(segRelUsuarioperfil);
                    }
                }
            }
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO.", "Perfiles asignados con éxito.");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ocurrió un error inesperado. Póngase en contacto con el administrador del servicio.");
            FacesContext.getCurrentInstance().addMessage(null,message);
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public List<SelectItem> completeNumDocumento(String query) {
        List<SelectItem> suggestions = new ArrayList<SelectItem>();
        try {
            for (SelectItem p : getItemsNumDocumento()) {
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
            e.printStackTrace();
        }
        return suggestions;
    }
}
