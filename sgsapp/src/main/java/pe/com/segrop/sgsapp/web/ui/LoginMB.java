/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import pe.com.segrop.sgsapp.dao.ClaveDao;
import pe.com.segrop.sgsapp.dao.EmpresaDao;
import pe.com.segrop.sgsapp.dao.PerfilDao;
import pe.com.segrop.sgsapp.dao.PermisoDao;
import pe.com.segrop.sgsapp.dao.UsuarioDao;
import pe.com.segrop.sgsapp.dao.UsuarioPerfilDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegCabUsuarioId;
import pe.com.segrop.sgsapp.domain.SegDetClave;
import pe.com.segrop.sgsapp.domain.SegDetObjeto;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegDetPerfilId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.SHA1BASE64;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;
import pe.com.segrop.sgsapp.web.common.Util;

/**
 *
 * @author JJ
 */
@ManagedBean
@ViewScoped
public class LoginMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int MINUTES = 1;
    private String empresa;
    private String usuario;
    private String clave;
    private String claveSecundaria;
    private BigDecimal perfil;
    private String destinationPage;
    private boolean flgClave;
    private String claveGenerada;
    private SegCabUsuario usuarioSession;
    private SegCabEmpresa empresaSession;
    private SegDetPerfil perfilSession;
    private StreamedContent file;
    private String mime;
    private HashMap<String, Boolean> mapObjetos;
    private List<SegCabEmpresa> listaEmpresaActiva;
    private SegCabEmpresa selectedEmpresa;
    private List<SelectItem> listaPerfilesAsignados;
    private Timer timer;

    /**
     * Creates a new instance of LoginMB
     */
    public LoginMB() {
        this.flgClave = false;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the claveSecundaria
     */
    public String getClaveSecundaria() {
        return claveSecundaria;
    }

    /**
     * @param claveSecundaria the claveSecundaria to set
     */
    public void setClaveSecundaria(String claveSecundaria) {
        this.claveSecundaria = claveSecundaria;
    }

    public BigDecimal getPerfil() {
        return perfil;
    }

    public void setPerfil(BigDecimal perfil) {
        this.perfil = perfil;
    }

    public String getDestinationPage() {
        return destinationPage;
    }

    public void setDestinationPage(String destinationPage) {
        this.destinationPage = destinationPage;
    }

    public boolean isFlgClave() {
        return flgClave;
    }

    public void setFlgClave(boolean flgClave) {
        this.flgClave = flgClave;
    }

    public String getClaveGenerada() {
        return claveGenerada;
    }

    public void setClaveGenerada(String claveGenerada) {
        this.claveGenerada = claveGenerada;
    }

    public SegCabUsuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(SegCabUsuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public SegCabEmpresa getEmpresaSession() {
        return empresaSession;
    }

    public void setEmpresaSession(SegCabEmpresa empresaSession) {
        this.empresaSession = empresaSession;
    }

    public SegDetPerfil getPerfilSession() {
        return perfilSession;
    }

    public void setPerfilSession(SegDetPerfil perfilSession) {
        this.perfilSession = perfilSession;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public HashMap<String, Boolean> getMapObjetos() {
        return mapObjetos;
    }

    public void setMapObjetos(HashMap<String, Boolean> mapObjetos) {
        this.mapObjetos = mapObjetos;
    }

    public List<SegCabEmpresa> getListaEmpresaActiva() {
        if (this.listaEmpresaActiva == null) {
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            listaEmpresaActiva = empresaDao.obtenerListaEmpresasActivas();
        }
        return listaEmpresaActiva;
    }

    public void setListaEmpresaActiva(List<SegCabEmpresa> listaEmpresaActiva) {
        this.listaEmpresaActiva = listaEmpresaActiva;
    }

    /**
     * @return the selectedEmpresa
     */
    public SegCabEmpresa getSelectedEmpresa() {
        return selectedEmpresa;
    }

    /**
     * @param selectedEmpresa the selectedEmpresa to set
     */
    public void setSelectedEmpresa(SegCabEmpresa selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }

    public List<SelectItem> getListaPerfilesAsignados() {
        return listaPerfilesAsignados;
    }

    public void setListaPerfilesAsignados(List<SelectItem> listaPerfilesAsignados) {
        this.listaPerfilesAsignados = listaPerfilesAsignados;
    }

    public void setPerfilesDeUsuario(List<SegDetPerfil> listaPerfilesAsignados) {
        this.listaPerfilesAsignados = new Items(listaPerfilesAsignados, null, "NCodPerfil", "VNombre").getItems();
    }

    public void clear() {
        this.setEmpresa(null);
        this.setUsuario(null);
        this.setClave(null);
        this.setClaveGenerada(null);
        this.setClaveSecundaria(null);
        this.setFlgClave(false);
    }

    public void paint(OutputStream stream, Object object) throws IOException {
        if (object != null && object instanceof File) {
            FileInputStream is = new FileInputStream((File) object);
            int c;
            while ((c = is.read()) != -1) {
                stream.write(c);
            }
            is.close();
        }
    }

    public void mime(String name) {
        String mimetype = "image/unknown";
        String extension = null;
        try {
            if (this.getFile() != null) {
                int extDot = name.lastIndexOf('.');
                if (extDot > 0) {
                    extension = name.substring(extDot + 1);
                    if ("bmp".equals(extension)) {
                        mimetype = "image/bmp";
                    } else if ("jpg".equals(extension)) {
                        mimetype = "image/jpeg";
                    } else if ("gif".equals(extension)) {
                        mimetype = "image/gif";
                    } else if ("png".equals(extension)) {
                        mimetype = "image/png";
                    } else {
                        mimetype = "image/unknown";
                    }
                }
            }
            this.setMime(mimetype);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Login del sistema.
     *
     * @return destino Página a la que redirecciona el método.
     */
    public String login() {
        String sessionAttempt = "";
        String[] to = new String[1];
        String subject = "";
        StringBuilder body = new StringBuilder("");
        try {
            HttpSession session = BaseBean.getSession();
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            ClaveDao claveDao = (ClaveDao) ServiceFinder.findBean("ClaveDao");
            sessionAttempt = session.getAttribute("sessionAttempt") != null ? (String) session.getAttribute("sessionAttempt") : "1";
            SegCabEmpresa segCabEmpresa = new SegCabEmpresa();
            segCabEmpresa.setVRuc(this.empresa);
            segCabEmpresa = empresaDao.obtenerEmpresaByRuc(segCabEmpresa);
            if (segCabEmpresa != null) {
                SegCabUsuarioId segCabUsuarioId = new SegCabUsuarioId();
                segCabUsuarioId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
                SegCabUsuario segCabUsuario = new SegCabUsuario();
                segCabUsuario.setId(segCabUsuarioId);
                segCabUsuario.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
                segCabUsuario.setVUsuario(this.getUsuario().trim());
                segCabUsuario = usuarioDao.obtenerUsuarioByUser(segCabUsuario);
                if (segCabUsuario != null) {
                    SegDetClave segDetClave = claveDao.obtenerClaveActiva(segCabUsuario);
                    if (segDetClave != null && segDetClave.getNFlgBloqueo().compareTo(BigDecimal.ZERO) == 0) {
                        if (Integer.parseInt(sessionAttempt) < 3) {
                            String claveEncriptada = SHA1BASE64.encriptar(this.getClave().trim());
                            if (claveEncriptada.equals(segDetClave.getVClave())) {
                                if (segCabUsuario.getNFlgClave().compareTo(BigDecimal.ZERO) == 0) {
                                    this.setEmpresa(this.empresa);
                                    this.setUsuarioSession(segCabUsuario);
                                    this.setEmpresaSession(segCabEmpresa);
                                    BaseBean.getSession().setAttribute("empresa", segCabEmpresa);
                                    BaseBean.getSession().setAttribute("usuario", segCabUsuario);
                                    FileInputStream fis = new FileInputStream(new File(this.getEmpresaSession().getVRutaLogo()));
                                    file = new DefaultStreamedContent(new ByteArrayInputStream(IOUtils.toByteArray(fis)));
                                    this.mime(this.getFile().getName());
                                    UsuarioPerfilDao usuarioPerfilDao = (UsuarioPerfilDao) ServiceFinder.findBean("UsuarioPerfilDao");
                                    List<SegDetPerfil> listaPerfil = usuarioPerfilDao.obtenerPerfilesAsignadosByUsuario(segCabUsuario);
                                    if (listaPerfil != null && !listaPerfil.isEmpty()) {
                                        this.setPerfilesDeUsuario(listaPerfil);
                                        if (listaPerfil.size() > 1) {
                                            this.setDestinationPage("seleccionPerfil");
                                        } else {
                                            PermisoDao permisoDao = (PermisoDao) ServiceFinder.findBean("PermisoDao");
                                            SegDetPerfil segDetPerfil = listaPerfil.get(0);
                                            List<SegDetObjeto> listaObjetos = permisoDao.obtenerObjetosAsignadosByPerfil(segDetPerfil);
                                            HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();

                                            if (listaObjetos != null && !listaObjetos.isEmpty()) {
                                                for (SegDetObjeto obj : listaObjetos) {
                                                    if (obj.getNFlgActivo().compareTo(BigDecimal.ONE) == 0) {
                                                        mapa.put(obj.getVNombre(), true);
                                                    } else if (obj.getNFlgActivo().compareTo(BigDecimal.ZERO) == 0) {
                                                        mapa.put(obj.getVNombre(), false);
                                                    }
                                                }
                                                this.setPerfilSession(segDetPerfil);
                                                this.setMapObjetos(mapa);

                                                BaseBean.getSession().setAttribute("mapaObjetos", mapa);
                                                BaseBean.getSession().setAttribute("perfil", segDetPerfil);
                                                if (segCabUsuario.getNFlgCambioclave().compareTo(BigDecimal.ZERO) == 0) {
                                                    this.setDestinationPage("bienvenida");
                                                } else {
                                                    this.setDestinationPage("cambioClave");
                                                }
                                            } else {
                                                this.setDestinationPage("login");
                                                FacesContext.getCurrentInstance().addMessage(null,
                                                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su perfil no tiene permisos asignados. Comuníquese con el administrador del servicio."));
                                            }
                                        }
                                    } else {
                                        this.setDestinationPage("login");
                                        FacesContext.getCurrentInstance().addMessage(null,
                                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su usuario no tiene un perfil asignado. Comuníquese con el administrador del servicio."));
                                    }
                                } else {
                                    if (!this.flgClave) {
                                        this.setClaveGenerada(Util.generarClave());
                                        to[0] = segCabUsuario.getVCorreo();
                                        subject = "SISTEMA DE GESTIÓN DE SEGURIDAD - CLAVE DE CONFIRMACIÓN";
                                        body.append("Estimado(a) ").append(segCabUsuario.getVNombres()).append(" ").append(segCabUsuario.getVApellidos()).append(",<br/>");
                                        body.append("Su clave de confirmación es: ").append(this.getClaveGenerada());
                                        Util.enviarCorreo(to, subject, body.toString());

                                        this.setFlgClave(true);
                                        this.setDestinationPage("login");
                                        
                                        this.delay(MINUTES);

                                        FacesContext.getCurrentInstance().addMessage(null,
                                                new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Se ha enviado un email con la clave de confirmación a " + segCabUsuario.getVCorreo() + ", por favor verifique su correo."));
                                    } else {
                                        if (this.claveSecundaria.equals(this.claveGenerada)) {
                                            this.setEmpresa(this.empresa);
                                            this.setUsuarioSession(segCabUsuario);
                                            this.setEmpresaSession(segCabEmpresa);
                                            BaseBean.getSession().setAttribute("empresa", segCabEmpresa);
                                            BaseBean.getSession().setAttribute("usuario", segCabUsuario);
                                            FileInputStream fis = new FileInputStream(new File(this.getEmpresaSession().getVRutaLogo()));
                                            file = new DefaultStreamedContent(new ByteArrayInputStream(IOUtils.toByteArray(fis)));
                                            this.mime(this.getFile().getName());
                                            UsuarioPerfilDao usuarioPerfilDao = (UsuarioPerfilDao) ServiceFinder.findBean("UsuarioPerfilDao");
                                            List<SegDetPerfil> listaPerfil = usuarioPerfilDao.obtenerPerfilesAsignadosByUsuario(segCabUsuario);
                                            if (listaPerfil != null && !listaPerfil.isEmpty()) {
                                                this.setPerfilesDeUsuario(listaPerfil);
                                                if (listaPerfil.size() > 1) {
                                                    this.setDestinationPage("seleccionPerfil");
                                                } else {
                                                    PermisoDao permisoDao = (PermisoDao) ServiceFinder.findBean("PermisoDao");
                                                    SegDetPerfil segDetPerfil = listaPerfil.get(0);
                                                    List<SegDetObjeto> listaObjetos = permisoDao.obtenerObjetosAsignadosByPerfil(segDetPerfil);
                                                    HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();

                                                    if (listaObjetos != null && !listaObjetos.isEmpty()) {
                                                        for (SegDetObjeto obj : listaObjetos) {
                                                            if (obj.getNFlgActivo().compareTo(BigDecimal.ONE) == 0) {
                                                                mapa.put(obj.getVNombre(), true);
                                                            } else if (obj.getNFlgActivo().compareTo(BigDecimal.ZERO) == 0) {
                                                                mapa.put(obj.getVNombre(), false);
                                                            }
                                                        }
                                                        this.setPerfilSession(segDetPerfil);
                                                        this.setMapObjetos(mapa);
                                                        BaseBean.getSession().setAttribute("mapaObjetos", mapa);
                                                        BaseBean.getSession().setAttribute("perfil", segDetPerfil);
                                                        if (segCabUsuario.getNFlgCambioclave().compareTo(BigDecimal.ZERO) == 0) {
                                                            this.setDestinationPage("bienvenida");
                                                        } else {
                                                            this.setDestinationPage("cambioClave");
                                                        }
                                                    } else {
                                                        this.setDestinationPage("login");
                                                        FacesContext.getCurrentInstance().addMessage(null,
                                                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su perfil no tiene permisos asignados. Comuníquese con el administrador del servicio."));
                                                    }
                                                }
                                            } else {
                                                this.setDestinationPage("login");
                                                FacesContext.getCurrentInstance().addMessage(null,
                                                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su usuario no tiene un perfil asignado. Comuníquese con el administrador del servicio."));
                                            }
                                        } else {
                                            FacesContext.getCurrentInstance().addMessage(null,
                                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta.", "Intente nuevamente."));
                                            session.setAttribute("sessionAttempt", String.valueOf(Integer.parseInt(sessionAttempt) + 1));
                                            this.setDestinationPage("login");
                                        }
                                    }
                                }
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta.", "Intente nuevamente."));
                                session.setAttribute("sessionAttempt", String.valueOf(Integer.parseInt(sessionAttempt) + 1));
                                this.setDestinationPage("login");
                            }
                        } else {
                            SegDetClave password = claveDao.obtenerClaveActiva(segCabUsuario);
                            password.setNFlgBloqueo(BigDecimal.ONE);
                            password.setDFecBloqueo(new Date());
                            password.setVUsuModificacion(segCabUsuario.getVUsuario());
                            password.setDFecModificacion(password.getDFecBloqueo());
                            password.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                            claveDao.registrarClave(password);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            to[0] = segCabUsuario.getVCorreo();
                            subject = "SISTEMA DE GESTION DE SEGURIDAD - BLOQUEO DE CLAVE";
                            body.append("Su clave ha sido bloqueada. <br>Se ingresó con el usuario '");
                            body.append(segCabUsuario.getVUsuario()).append("' y clave incorrecta en más de tres intentos.<br><br>");
                            body.append("Usuario: ").append(segCabUsuario.getVUsuario()).append("<br>");
                            body.append("Fecha: ").append(sdf.format(password.getDFecModificacion())).append("<br>");
                            body.append("Ip: ").append(password.getVIpModificacion()).append("<br><br>");
                            body.append("Comuníquese con el administrador del servicio.");
                            Util.enviarCorreo(to, subject, body.toString());
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Clave bloqueada.", "Comuníquese con el administrador del servicio."));
                            this.setDestinationPage("login");
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña bloqueada.", "Comuníquese con el administrador del servicio."));
                        this.setDestinationPage("login");
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario incorrecto.", "Intente nuevamente."));
                    this.setDestinationPage("login");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe la empresa ingresada.", "Intente nuevamente."));
                this.setDestinationPage("login");
            }
        } catch (IllegalStateException e) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } catch (IOException e) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return this.getDestinationPage();
    }

    public String obtenerPermisos() {
        try {
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            SegDetPerfilId segDetPerfilId = new SegDetPerfilId();
            segDetPerfilId.setNCodPerfil(this.getPerfil());
            SegDetPerfil segDetPerfil = new SegDetPerfil();
            segDetPerfil.setId(segDetPerfilId);
            segDetPerfil = perfilDao.obtenerPerfilById(segDetPerfil);

            PermisoDao permisoDao = (PermisoDao) ServiceFinder.findBean("PermisoDao");
            List<SegDetObjeto> listaObjetos = permisoDao.obtenerObjetosAsignadosByPerfil(segDetPerfil);

            HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();
            if (listaObjetos != null && !listaObjetos.isEmpty()) {
                for (SegDetObjeto obj : listaObjetos) {
                    if (obj.getNFlgActivo().compareTo(BigDecimal.ONE) == 0) {
                        mapa.put(obj.getVNombre(), true);
                    } else if (obj.getNFlgActivo().compareTo(BigDecimal.ZERO) == 0) {
                        mapa.put(obj.getVNombre(), false);
                    }
                }
                this.setPerfilSession(segDetPerfil);
                this.setMapObjetos(mapa);
                BaseBean.getSession().setAttribute("mapaObjetos", mapa);
                BaseBean.getSession().setAttribute("perfil", segDetPerfil);
                if (this.getUsuarioSession().getNFlgCambioclave().compareTo(BigDecimal.ZERO) == 0) {
                    this.setDestinationPage("bienvenida");
                } else {
                    this.setDestinationPage("cambioClave");
                }
            } else {
                this.setDestinationPage("seleccionPerfil");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Su perfil no tiene permisos asignados. Comuníquese con el administrador del servicio."));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return this.getDestinationPage();
    }

    public void resetearClave(ActionEvent event) {
        String[] to = new String[1];
        String subject = "";
        StringBuilder body = new StringBuilder("");
        try {
            if (this.empresa != null && !this.empresa.isEmpty()) {
                if (this.usuario != null && !this.usuario.isEmpty()) {
                    UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
                    EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
                    ClaveDao claveDao = (ClaveDao) ServiceFinder.findBean("ClaveDao");
                    SegCabEmpresa segCabEmpresa = new SegCabEmpresa();
                    segCabEmpresa.setVRuc(this.empresa);
                    segCabEmpresa = empresaDao.obtenerEmpresaByRuc(segCabEmpresa);
                    SegCabUsuarioId segCabUsuarioId = new SegCabUsuarioId();
                    segCabUsuarioId.setNCodEmpresa(segCabEmpresa != null ? segCabEmpresa.getNCodEmpresa() : null);
                    SegCabUsuario segCabUsuario = new SegCabUsuario();
                    segCabUsuario.setId(segCabUsuarioId);
                    segCabUsuario.setNCodEmpresa(segCabEmpresa != null ? segCabEmpresa.getNCodEmpresa() : null);
                    segCabUsuario.setVUsuario(this.usuario != null ? this.usuario.trim() : null);

                    SegCabUsuario user = usuarioDao.obtenerUsuarioByUser(segCabUsuario);
                    if (user != null) {
                        SegDetClave segDetClave = claveDao.obtenerClaveActiva(user);
                        segDetClave.setVClave(SHA1BASE64.encriptar(Util.generarClave()));
                        segDetClave.setNFlgActivo(BigDecimal.ONE);
                        segDetClave.setNFlgBloqueo(BigDecimal.ZERO);
                        segDetClave.setDFecBloqueo(null);
                        segDetClave.setDFecModificacion(new Date());
                        segDetClave.setVUsuModificacion(user.getVUsuario());
                        segDetClave.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());

                        claveDao.registrarClave(segDetClave);

                        user.setNFlgCambioclave(BigDecimal.ONE);

                        usuarioDao.registrarUsuario(user);

                        to[0] = segCabUsuario.getVCorreo();
                        subject = "SISTEMA DE GESTION DE SEGURIDAD - RESETEO DE CLAVE";
                        body.append("Su clave ha sido reseteada satisfactoriamente.<br>");
                        body.append("Usuario: <strong>").append(segCabUsuario.getVUsuario()).append("</strong><br>");
                        body.append("Nueva Clave: <strong>").append(segDetClave.getVClave()).append("</strong><br>");
                        Util.enviarCorreo(to, subject, body.toString());

                        this.setEmpresa(null);
                        this.setUsuario(null);

                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Se ha enviado un email con su nueva clave al correo " + segCabUsuario.getVCorreo() + ", por favor verifique su correo e intente nuevamente."));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Debe ingresar un usuario de logueo."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Debe seleccionar una empresa."));
            }
        } catch (IllegalStateException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String destino() {
        return this.getDestinationPage();
    }

    public List<SegCabEmpresa> complete(String query) {
        List<SegCabEmpresa> suggestions = new ArrayList<SegCabEmpresa>();
        try {
            for (SegCabEmpresa p : getListaEmpresaActiva()) {
                if (p.getVRazonSocial().startsWith(query.toUpperCase())) {
                    suggestions.add(p);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return suggestions;
    }

    public List<SegCabEmpresa> autocomplete(Object suggest) {
        String pref = (String) suggest;
        ArrayList<SegCabEmpresa> result = new ArrayList<SegCabEmpresa>();

        Iterator<SegCabEmpresa> iterator = getListaEmpresaActiva().iterator();
        while (iterator.hasNext()) {
            SegCabEmpresa elem = iterator.next();
            if ((elem.getVRazonSocial() != null && elem.getVRazonSocial().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref)) {
                result.add(elem);
            }
        }
        return result;
    }

    /**
     * Cierra la sesión del usuario.
     *
     * @return destino Página a la que redirecciona el método.
     */
    public String cerrarSesion() {
        String destino = null;
        try {
            BaseBean.getSession().invalidate();
            destino = "login";
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return destino;
    }
    
    class Task extends TimerTask {
        
        @Override
        public void run() {
            setClaveSecundaria(StringUtils.EMPTY);
            System.out.println("Clave Secundaria expirada... ");
            timer.cancel();
        }
    }
    
    public void delay(int minutes) {
        this.timer = new Timer();
        this.timer.schedule(new Task(), minutes*60*1000); //delay in milliseconds
    }
}
