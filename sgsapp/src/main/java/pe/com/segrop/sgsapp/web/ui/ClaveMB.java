/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import pe.com.segrop.sgsapp.dao.ClaveDao;
import pe.com.segrop.sgsapp.dao.UsuarioDao;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetClave;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.util.SHA1BASE64;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
@ManagedBean
@ViewScoped
public class ClaveMB implements Serializable{

    private String claveActual;
    private String nuevaClave;
    private String confirmacion;
    
    /** Creates a new instance of ClaveMB */
    public ClaveMB() {
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
    
    public String cambiarClave(){
        String clave = null;
        String destino = null;
        try{
            SegCabUsuario usuario = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            ClaveDao claveDao = (ClaveDao) ServiceFinder.findBean("ClaveDao");
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            if(this.getClaveActual()!=null && !"".equals(this.getClaveActual())){
                if(this.getClaveActual()!=null && !"".equals(this.getClaveActual())){
                    clave = SHA1BASE64.encriptar(this.getClaveActual().trim());
                    SegDetClave segDetClave = claveDao.obtenerClaveActiva(usuario);
                    if(segDetClave!=null && clave.equals(segDetClave.getVClave())){
                        if(!this.getNuevaClave().equals(this.getClaveActual())){
                            if(this.getNuevaClave().length() >= 8){
                                if(this.getNuevaClave().equals(this.getConfirmacion())){
                                    segDetClave.setVClave(SHA1BASE64.encriptar(this.getNuevaClave()));
                                    segDetClave.setDFecModificacion(new Date());
                                    segDetClave.setVUsuModificacion(usuario.getVUsuario());
                                    segDetClave.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                                    claveDao.registrarClave(segDetClave);
                                    
                                    usuario.setNFlgCambioclave(BigDecimal.ZERO);
                                    usuario.setDFecModificacion(new Date());
                                    usuario.setVUsuModificacion(usuario.getVUsuario());
                                    usuario.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                                    usuarioDao.registrarUsuario(usuario);
                                    
                                    this.setClaveActual("");
                                    this.setNuevaClave("");
                                    this.setConfirmacion("");

                                    FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO.", "Su nueva clave se actualizó con éxito."));
                                    destino = "/pages/bienvenida";
                                }else{
                                    FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Confirme su nueva clave correctamente."));
                                    destino = "cambioClave";
                                }
                            }else{
                                FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "La nueva clave debe tener al menos 8 caracteres."));
                                destino = "cambioClave";
                            }
                        }else{
                            FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "La nueva clave debe ser diferente a la actual."));
                            destino = "cambioClave";
                        }
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Clave actual incorrecta."));
                        destino = "cambioClave";
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese su nueva clave."));
                    destino = "cambioClave";
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese su clave actual."));
                destino = "cambioClave";
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return destino;
    }
    
    public void cambioClave(ActionEvent event){
        String clave = null;
        try{
            SegCabUsuario usuario = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            ClaveDao claveDao = (ClaveDao) ServiceFinder.findBean("ClaveDao");
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            if(this.getClaveActual()!=null && !"".equals(this.getClaveActual())){
                if(this.getClaveActual()!=null && !"".equals(this.getClaveActual())){
                    clave = SHA1BASE64.encriptar(this.getClaveActual().trim());
                    SegDetClave segDetClave = claveDao.obtenerClaveActiva(usuario);
                    if(segDetClave!=null && clave.equals(segDetClave.getVClave())){
                        if(!this.getNuevaClave().equals(this.getClaveActual())){
                            if(this.getNuevaClave().length() >= 8){
                                if(this.getNuevaClave().equals(this.getConfirmacion())){
                                    segDetClave.setVClave(SHA1BASE64.encriptar(this.getNuevaClave()));
                                    segDetClave.setDFecModificacion(new Date());
                                    segDetClave.setVUsuModificacion(usuario.getVUsuario());
                                    segDetClave.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                                    claveDao.registrarClave(segDetClave);
                                    
                                    usuario.setNFlgCambioclave(BigDecimal.ZERO);
                                    usuario.setDFecModificacion(new Date());
                                    usuario.setVUsuModificacion(usuario.getVUsuario());
                                    usuario.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                                    usuarioDao.registrarUsuario(usuario);

                                    this.setClaveActual("");
                                    this.setNuevaClave("");
                                    this.setConfirmacion("");
                                    
                                    FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO.", "Su nueva clave se actualizó con éxito."));
                                }else{
                                    FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Confirme su nueva clave correctamente."));
                                }
                            }else{
                                FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "La nueva clave debe tener al menos 8 caracteres."));
                            }
                        }else{
                            FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "La nueva clave debe ser diferente a la actual."));
                        }
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Clave actual incorrecta."));
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese su nueva clave."));
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese su clave actual."));
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
}
