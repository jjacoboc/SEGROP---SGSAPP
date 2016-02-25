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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import pe.com.segrop.sgsapp.dao.ClaveDao;
import pe.com.segrop.sgsapp.dao.UsuarioDao;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegCabUsuarioId;
import pe.com.segrop.sgsapp.domain.SegDetClave;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.SHA1BASE64;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;
import pe.com.segrop.sgsapp.web.common.StringUtil;
import pe.com.segrop.sgsapp.web.common.Util;

/**
 *
 * @author JJ
 */
public class UsuarioMB implements Serializable{

    private String searchEmpresa;
    private String searchNumTipoDocumento;
    private String searchNumDocumento;
    private String searchNombre;
    private String searchApellido;
    private BigDecimal empresa;
    private String tipoNumDocumento;
    private String numDocumento;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String usuario;
    private String comfirmacion;
    private SegCabUsuario selectedUsuario;
    private List<SegCabUsuario> listaUsuario;
    private List<SelectItem> itemsNumDocumento;
    private List<SelectItem> itemsNombre;
    private List<SelectItem> itemsApellido;
    private String action;
    
    /** Creates a new instance of UsuarioMB */
    public UsuarioMB() {
        this.selectedUsuario = new SegCabUsuario();
    }

    /**
     * @return the searchEmpresa
     */
    public String getSearchEmpresa() {
        return searchEmpresa;
    }

    /**
     * @param searchEmpresa the searchEmpresa to set
     */
    public void setSearchEmpresa(String searchEmpresa) {
        this.searchEmpresa = searchEmpresa;
    }

    /**
     * @return the searchNumTipoDocumento
     */
    public String getSearchNumTipoDocumento() {
        return searchNumTipoDocumento;
    }

    /**
     * @param searchNumTipoDocumento the searchNumTipoDocumento to set
     */
    public void setSearchNumTipoDocumento(String searchNumTipoDocumento) {
        this.searchNumTipoDocumento = searchNumTipoDocumento;
    }

    /**
     * @return the searchNumDocumento
     */
    public String getSearchNumDocumento() {
        return searchNumDocumento;
    }

    /**
     * @param searchNumDocumento the searchNumDocumento to set
     */
    public void setSearchNumDocumento(String searchNumDocumento) {
        this.searchNumDocumento = searchNumDocumento;
    }

    /**
     * @return the searchNombre
     */
    public String getSearchNombre() {
        return searchNombre;
    }

    /**
     * @param searchNombre the searchNombre to set
     */
    public void setSearchNombre(String searchNombre) {
        this.searchNombre = searchNombre;
    }

    /**
     * @return the searchApellido
     */
    public String getSearchApellido() {
        return searchApellido;
    }

    /**
     * @param searchApellido the searchApellido to set
     */
    public void setSearchApellido(String searchApellido) {
        this.searchApellido = searchApellido;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
     * @return the tipoNumDocumento
     */
    public String getTipoNumDocumento() {
        return tipoNumDocumento;
    }

    /**
     * @param tipoNumDocumento the tipoNumDocumento to set
     */
    public void setTipoNumDocumento(String tipoNumDocumento) {
        this.tipoNumDocumento = tipoNumDocumento;
    }

    /**
     * @return the numDocumento
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * @param numDocumento the numDocumento to set
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
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

    public String getComfirmacion() {
        return comfirmacion;
    }

    public void setComfirmacion(String comfirmacion) {
        this.comfirmacion = comfirmacion;
    }

    public SegCabUsuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(SegCabUsuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    /**
     * @return the listaUsuario
     */
    public List<SegCabUsuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<SegCabUsuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    /**
     * @return the itemsNumDocumento
     */
    public List<SelectItem> getItemsNumDocumento() {
        if (this.itemsNumDocumento == null) {
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            itemsNumDocumento = new Items(usuarioDao.obtenerListaUsuarios(), null, "NCodUsuario", "VNumDocumento").getItems();
        }
        return itemsNumDocumento;
    }

    /**
     * @param itemsNumDocumento the itemsNumDocumento to set
     */
    public void setItemsNumDocumento(List<SelectItem> itemsNumDocumento) {
        this.itemsNumDocumento = itemsNumDocumento;
    }

    /**
     * @return the itemsNombre
     */
    public List<SelectItem> getItemsNombre() {
        if (this.itemsNombre == null) {
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            itemsNombre = new Items(usuarioDao.obtenerListaUsuarios(), null, "NCodUsuario", "VNombres").getItems();
        }
        return itemsNombre;
    }

    /**
     * @param itemsNombre the itemsNombre to set
     */
    public void setItemsNombre(List<SelectItem> itemsNombre) {
        this.itemsNombre = itemsNombre;
    }

    /**
     * @return the itemsApellidos
     */
    public List<SelectItem> getItemsApellido() {
        if (this.itemsApellido == null) {
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            itemsApellido = new Items(usuarioDao.obtenerListaUsuarios(), null, "NCodUsuario", "VApellidos").getItems();
        }
        return itemsApellido;
    }

    /**
     * @param itemsApellidos the itemsApellidos to set
     */
    public void setItemsApellido(List<SelectItem> itemsApellido) {
        this.itemsApellido = itemsApellido;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public void buscarUsuario(ActionEvent actionEvent) {
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
            setListaUsuario(usuarioDao.buscarUsuarios(segCabUsuario));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            this.setEmpresa(null);
            this.setNombre(null);
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
            String rowKey = BaseBean.getRequestParameter("rowKey");
            this.setSelectedUsuario(this.getListaUsuario().get(Integer.parseInt(rowKey)));
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

    public void editarUsuario(ActionEvent actionEvent) {
        SegCabUsuario user = null;
        try {
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            SegCabUsuario segCabUsuario = this.getSelectedUsuario();
            segCabUsuario.getId().setNCodEmpresa(segCabUsuario.getId().getNCodEmpresa() != null ? segCabUsuario.getId().getNCodEmpresa() : null);
            segCabUsuario.setNCodEmpresa(segCabUsuario.getId().getNCodEmpresa() != null ? segCabUsuario.getId().getNCodEmpresa() : null);
            segCabUsuario.setNTipNumDocumento(segCabUsuario.getNTipNumDocumento() != null ? segCabUsuario.getNTipNumDocumento() : null);
            segCabUsuario.setVNumDocumento(segCabUsuario.getVNumDocumento() != null ? segCabUsuario.getVNumDocumento().toUpperCase().trim() : null);
            segCabUsuario.setVNombres(segCabUsuario.getVNombres() != null ? segCabUsuario.getVNombres().toUpperCase().trim() : null);
            segCabUsuario.setVApellidos(segCabUsuario.getVApellidos() != null ? segCabUsuario.getVApellidos().toUpperCase().trim() : null);
            segCabUsuario.setVCorreo(segCabUsuario.getVCorreo() != null ? segCabUsuario.getVCorreo().trim() : null);
            segCabUsuario.setVMovil(segCabUsuario.getVMovil() != null ? segCabUsuario.getVMovil().toUpperCase().trim() : null);
            segCabUsuario.setVUsuario(segCabUsuario.getVUsuario() != null ? segCabUsuario.getVUsuario().trim() : null);
            segCabUsuario.setDFecModificacion(new Date());
            segCabUsuario.setVUsuModificacion(usuarioSession.getVUsuario());
            segCabUsuario.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
            
            if(!errorValidation(segCabUsuario)){
                user = usuarioDao.obtenerUsuarioByNumeroDocumento(segCabUsuario);
                if((user == null) || (user != null && user.getId().getNCodUsuario().equals(segCabUsuario.getId().getNCodUsuario()))){
                    user = usuarioDao.obtenerUsuarioByUser(segCabUsuario);
                    if((user == null) || (user != null && user.getId().getNCodUsuario().equals(segCabUsuario.getId().getNCodUsuario()))){
                        usuarioDao.registrarUsuario(segCabUsuario);
                        //setListaUsuario(usuarioDao.obtenerListaUsuarios());
                        this.setAction("Richfaces.hideModalPanel('dlgEdit')");
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error.", "El usuario ingresado ya se encuentra en uso."));
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error.", "El Número de Documento ingresado ya se encuentra registrado."));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void registrarUsuario(ActionEvent actionEvent) {
        SegCabUsuario user = null;
        String[] to = new String[1];
        String subject = "";
        StringBuilder body = new StringBuilder("");
        try {
            SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            SegCabUsuarioId segCabUsuarioId = new SegCabUsuarioId();
            segCabUsuarioId.setNCodUsuario(BigDecimal.valueOf(usuarioDao.nextSequenceValue().longValue()));
            segCabUsuarioId.setNCodEmpresa(this.empresa != null ? this.empresa : null);
            SegCabUsuario segCabUsuario = new SegCabUsuario();
            segCabUsuario.setId(segCabUsuarioId);
            segCabUsuario.setNCodUsuario(segCabUsuarioId.getNCodUsuario());
            segCabUsuario.setNCodEmpresa(segCabUsuarioId.getNCodEmpresa());
            segCabUsuario.setNTipNumDocumento(this.tipoNumDocumento != null ? BigDecimal.valueOf(Long.parseLong(this.tipoNumDocumento)) : null);
            segCabUsuario.setVNumDocumento(this.numDocumento != null ? this.numDocumento.toUpperCase().trim() : null);
            segCabUsuario.setVNombres(this.nombre != null ? this.nombre.toUpperCase().trim() : null);
            segCabUsuario.setVApellidos(this.apellido != null ? this.apellido.toUpperCase().trim() : null);
            segCabUsuario.setVCorreo(this.correo != null ? this.correo.trim() : null);
            segCabUsuario.setVMovil(this.telefono != null ? this.telefono.toUpperCase().trim() : null);
            segCabUsuario.setVUsuario(this.usuario != null ? this.usuario.trim() : null);
            segCabUsuario.setNFlgClave(this.comfirmacion != null ? BigDecimal.valueOf(Long.parseLong(this.comfirmacion)) : null);
            segCabUsuario.setNFlgActivo(BigDecimal.ONE);
            segCabUsuario.setNFlgCambioclave(BigDecimal.ONE);
            segCabUsuario.setDFecCreacion(new Date());
            segCabUsuario.setVUsuCreacion(usuarioSession.getVUsuario());
            segCabUsuario.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());

            if(!errorValidation(segCabUsuario)){
                user = usuarioDao.obtenerUsuarioByNumeroDocumento(segCabUsuario);
                if(user == null){
                    user = usuarioDao.obtenerUsuarioByUser(segCabUsuario);
                    if(user == null){
                        usuarioDao.registrarUsuario(segCabUsuario);
                        ClaveDao claveDao = (ClaveDao) ServiceFinder.findBean("ClaveDao");
                        SegDetClave segDetClave = new SegDetClave();
                        segDetClave.setSegCabUsuario(segCabUsuario);
                        segDetClave.setNCodClave(BigDecimal.valueOf(claveDao.nextSequenceValue().longValue()));
                        segDetClave.setSegCabUsuario(segCabUsuario);
                        String clave = Util.generarClave();
                        segDetClave.setVClave(SHA1BASE64.encriptar(clave));
                        segDetClave.setNFlgBloqueo(BigDecimal.ZERO);
                        segDetClave.setNFlgActivo(BigDecimal.ONE);
                        segDetClave.setDFecCreacion(new Date());
                        segDetClave.setVUsuCreacion(usuarioSession.getVUsuario());
                        segDetClave.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                        claveDao.registrarClave(segDetClave);

                        to[0] = segCabUsuario.getVCorreo();
                        subject = "SISTEMA DE GESTION DE SEGURIDAD - REGISTRO DE USUARIO";
                        body.append("Su usuario ha sido registrado satisfactoriamente.<br>");
                        body.append("Usuario: <strong>").append(segCabUsuario.getVUsuario()).append("</strong><br>");
                        body.append("Clave: <strong>").append(clave).append("</strong><br><br>");
                        body.append("Nota: Su usuario y clave son estrictamente personales. El uso de los mismos quedan bajo la responsabilidad del usuario.");
                        Util.enviarCorreo(to, subject, body.toString());

                        //setListaUsuario(usuarioDao.obtenerListaUsuarios());
                        this.setAction("Richfaces.hideModalPanel('dlg')");
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error.", "El usuario o login ingresado ya se encuentra en uso."));
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error.", "El Número de Documento ingresado ya se encuentra registrado."));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Desactiva al usuario seleccionada.
     * @return destino Página a la que redirecciona el método.
     */
    public void desactivar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            getSelectedUsuario().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            usuarioDao.registrarUsuario(getSelectedUsuario());
            setListaUsuario(usuarioDao.obtenerListaUsuarios());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Activa lemprea sa seleccionada.
     * @return destino Página a la que redirecciona el método.
     */
    public void activar(ActionEvent actionEvent){
        try{
            HttpSession session = BaseBean.getSession();
            UsuarioDao usuarioDao = (UsuarioDao) ServiceFinder.findBean("UsuarioDao");
            getSelectedUsuario().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            usuarioDao.registrarUsuario(getSelectedUsuario());
            setListaUsuario(usuarioDao.obtenerListaUsuarios());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(SegCabUsuario usuario){
        FacesMessage message = null;
        boolean error = false;
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            if(usuario.getId().getNCodEmpresa() == null || usuario.getId().getNCodEmpresa().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione la empresa del usuario.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getNTipNumDocumento() == null || usuario.getNTipNumDocumento().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Selecione el tipo de documento del usuario.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getVNumDocumento() == null || "".equals(usuario.getVNumDocumento().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese el número de documento del usuario.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getNTipNumDocumento() != null 
                    && usuario.getNTipNumDocumento().compareTo(BigDecimal.valueOf(Long.parseLong(bundle.getString("DNI"))))==0
                    && !StringUtil.isNumeric(usuario.getVNumDocumento())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Formato de DNI incorrecto. Solo debe contener dígitos numéricos.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getNTipNumDocumento() != null 
                    && usuario.getNTipNumDocumento().compareTo(BigDecimal.valueOf(Long.parseLong(bundle.getString("DNI"))))==0
                    && usuario.getVNumDocumento().length()!=8){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El DNI debe tener 8 dígitos numéricos.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getNTipNumDocumento() != null 
                    && usuario.getNTipNumDocumento().compareTo(BigDecimal.valueOf(Long.parseLong(bundle.getString("PASAPORTE"))))==0
                    && !StringUtil.isNumeric(usuario.getVNumDocumento())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Formato de Pasaporte incorrecto. Solo debe contener dígitos numéricos.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getNTipNumDocumento() != null 
                    && usuario.getNTipNumDocumento().compareTo(BigDecimal.valueOf(Long.parseLong(bundle.getString("PASAPORTE"))))==0
                    && usuario.getVNumDocumento().length()!=8){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El Pasaporte debe tener 8 dígitos.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getNTipNumDocumento() != null 
                    && usuario.getNTipNumDocumento().compareTo(BigDecimal.valueOf(Long.parseLong(bundle.getString("CARNE_EXTRANJERIA"))))==0
                    && usuario.getVNumDocumento().length()!=8){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El Carné de Extranjería debe tener 8 dígitos.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getVNombres() == null || "".equals(usuario.getVNombres().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese el(los) nombre(s) del usuario.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getVApellidos() == null || "".equals(usuario.getVApellidos().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese los apellidos del usuario.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getVCorreo() == null || "".equals(usuario.getVCorreo().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese el correo del usuario.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getVUsuario() == null || "".equals(usuario.getVUsuario().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese el usuario o login.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(usuario.getNFlgClave() == null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Indique si el usuario necesitará confirmación de clave de acceso.");
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