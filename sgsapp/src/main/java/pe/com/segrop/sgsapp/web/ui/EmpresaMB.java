/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.activation.MimetypesFileTypeMap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.StreamedContent;
import pe.com.segrop.sgsapp.dao.EmpresaDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
@ManagedBean
@SessionScoped
public class EmpresaMB implements Serializable {

    private String searchRuc;
    private String searchRazonSocial;
    private String ruc;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private String rutaLogo;
    private UploadedFile file;
    private StreamedContent content;
    private String mime;
    private String nombreOriginal;
    private SegCabEmpresa selectedEmpresa;
    private List<SegCabEmpresa> listaEmpresa;
    private List<SelectItem> itemsRuc;
    private List<SelectItem> itemsRazonSocial;
    private String action;

    /** Creates a new instance of EmpresaMB */
    public EmpresaMB() {
    }

    /**
     * @return the searchRuc
     */
    public String getSearchRuc() {
        return searchRuc;
    }

    /**
     * @param searchRuc the searchRuc to set
     */
    public void setSearchRuc(String searchRuc) {
        this.searchRuc = searchRuc;
    }

    /**
     * @return the searchRazonSocial
     */
    public String getSearchRazonSocial() {
        return searchRazonSocial;
    }

    /**
     * @param searchRazonSocial the searchRazonSocial to set
     */
    public void setSearchRazonSocial(String searchRazonSocial) {
        this.searchRazonSocial = searchRazonSocial;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
     * @return the rutaLogo
     */
    public String getRutaLogo() {
        return rutaLogo;
    }

    /**
     * @param rutaLogo the rutaLogo to set
     */
    public void setRutaLogo(String rutaLogo) {
        this.rutaLogo = rutaLogo;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the content
     */
    public StreamedContent getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    public SegCabEmpresa getSelectedEmpresa() {
        return selectedEmpresa;
    }

    public void setSelectedEmpresa(SegCabEmpresa selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }

    /**
     * @return the listaEmpresa
     */
    public List<SegCabEmpresa> getListaEmpresa() {
        return listaEmpresa;
    }

    /**
     * @param listaEmpresa the listaEmpresa to set
     */
    public void setListaEmpresa(List<SegCabEmpresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public List<SelectItem> getItemsRuc() {
        if (this.itemsRuc == null) {
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            itemsRuc = new Items(empresaDao.obtenerListaEmpresas(), null, "NCodEmpresa", "VRuc").getItems();
        }
        return itemsRuc;
    }

    public void setItemsRuc(List<SelectItem> itemsRuc) {
        this.itemsRuc = itemsRuc;
    }

    public List<SelectItem> getItemsRazonSocial() {
        if (this.itemsRazonSocial == null) {
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            itemsRazonSocial = new Items(empresaDao.obtenerListaEmpresas(), Items.FIRST_ITEM_SELECT, "NCodEmpresa", "VRazonSocial").getItems();
        }
        return itemsRazonSocial;
    }

    public void setItemsRazonSocial(List<SelectItem> itemsRazonSocial) {
        this.itemsRazonSocial = itemsRazonSocial;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public void listpaint(OutputStream stream, Object object) throws IOException {
        if(object != null){
            String ruta = this.getListaEmpresa().get((Integer)object).getVRutaLogo();
            this.mime(ruta);
            FileInputStream is = new FileInputStream(this.getListaEmpresa().get((Integer)object).getFile());
            int c;
            while ((c = is.read()) != -1){
                stream.write(c);
            }
            is.close();
        }
    }
    
    public void paint(OutputStream stream, Object object) throws IOException {
        //if(object != null && object instanceof UploadItem){
        if(object != null){
            //System.out.println("paint");
            stream.write(((UploadedFile)object).getContents());
        }
    }
    
    public void mime(String name) {
        String mimeType = "image/unknown";
        String extension = null;
        try{
            if(this.getFile() != null){
                int extDot = name.lastIndexOf('.');
                if(extDot > 0){
                    extension = name.substring(extDot +1);
                    if("bmp".equals(extension)){
                        mimeType="image/bmp";
                    } else if("jpg".equals(extension)){
                        mimeType="image/jpeg";
                    } else if("gif".equals(extension)){
                        mimeType="image/gif";
                    } else if("png".equals(extension)){
                        mimeType="image/png";
                    } else {
                        mimeType = "image/unknown";
                    }
                }
            }
            this.setMime(mimeType);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void handleFileUpload(FileUploadEvent event){
        String filepath = null;
        String temppath = null;
        ResourceBundle bundle = null;
        String fileName = null;
        String fileExt = null;
        FacesMessage message = null;
        try {
            if(event != null){
                this.setFile(event.getFile());
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                BufferedImage image = ImageIO.read(this.getFile().getInputstream());
                int height = image.getHeight();
                int width = image.getWidth();
                if(height <= Integer.parseInt(bundle.getString("logoDefaultHeight"))
                    || width <= Integer.parseInt(bundle.getString("logoDefaultWidth"))){
                    bundle = ResourceBundle.getBundle(Parameters.getParameters());
                    SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                    temppath = bundle.getString("tempPath").concat(segCabEmpresa.getVRuc()).concat("\\"); //Para WINDOWS
    //                    temppath = bundle.getString("tempPath").concat(segCabEmpresa.getVRuc()).concat("/"); //Para LINUX

                    File direc = new File(temppath);
                    direc.mkdirs();
                    fileName = this.getFile().getFileName();
                    fileExt = fileName.substring(fileName.lastIndexOf("."));
                    File filetoCreate = new File(temppath, "logo".concat(fileExt));
                    FileOutputStream fileOutStream = new FileOutputStream(filetoCreate);
                    fileOutStream.write(this.getFile().getContents());
                    fileOutStream.flush();
                    fileOutStream.close();

                    this.setNombreOriginal(this.getFile().getFileName());
                    this.mime(this.getFile().getContentType());
                    this.setContent(new DefaultStreamedContent(this.getFile().getInputstream(), this.getFile().getContentType(), this.getFile().getFileName()));
                }else{
                    this.setFile(null);
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR. ", "La dimesiÃ³n mÃ¡xima del logo debe ser de 230 pixeles de largo por 50 pixeles de alto.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public void buscarEmpresa(ActionEvent actionEvent) {
        try {
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            SegCabEmpresa empresa = new SegCabEmpresa();
            empresa.setVRuc(this.getSearchRuc() != null ? this.getSearchRuc().toUpperCase().trim() : null);
            empresa.setVRazonSocial(this.getSearchRazonSocial() != null ? this.getSearchRazonSocial().toUpperCase().trim() : null);
            setListaEmpresa(empresaDao.buscarEmpresas(empresa));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            this.setRuc(null);
            this.setRazonSocial(null);
            this.setDireccion(null);
            this.setTelefono(null);
            this.setRutaLogo(null);
            this.setFile(null);
            this.setNombreOriginal(null);
            this.setAction(null);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public void registrarEmpresa(ActionEvent actionEvent) {
        FacesMessage message = null;
        String filepath = null;
        ResourceBundle bundle = null;
        String fileName = null;
        String fileExt = null;
        try {
            if(this.getRuc() != null && !"".equals(this.getRuc())){
                if(this.getRuc().length() == 11){
                    if(this.getRazonSocial() != null && !"".equals(this.getRazonSocial())){
                        if(this.getDireccion() != null && !"".equals(this.getDireccion())){
                            if(this.getTelefono() != null && !"".equals(this.getTelefono())){
//                                if(this.getNombreOriginal() != null && !"".equals(this.getNombreOriginal())){
                                    SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                                    EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");

                                    bundle = ResourceBundle.getBundle(Parameters.getParameters());
                                    if(this.getFile() == null){
                                        String defaultFile = bundle.getString("defaultFile");
                                        File f = new File(defaultFile);
                                        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("fileUpload", "image/png", true, f.getName());
                                        this.setFile(new DefaultUploadedFile(fileItem));
                                    }
                                    
                                    filepath = bundle.getString("filePath").concat(this.getRuc().trim()).concat("\\"); //Para WINDOWS
                                    
                                    File direc = new File(filepath);
                                    direc.mkdirs();
                                    fileName = this.getFile().getFileName();
                                    fileExt = fileName.substring(fileName.lastIndexOf("."));
                                    File filetoCreate = new File(filepath, "logo".concat(fileExt));
                                    FileOutputStream fileOutStream = new FileOutputStream(filetoCreate);
                                    fileOutStream.write(this.getFile().getContents());
                                    fileOutStream.flush();
                                    fileOutStream.close();

                                    SegCabEmpresa empresa = new SegCabEmpresa();
                                    empresa.setNCodEmpresa(BigDecimal.valueOf(empresaDao.nextSequenceValue()));
                                    empresa.setVRuc(this.ruc != null ? this.ruc.toUpperCase().trim() : null);
                                    empresa.setVRazonSocial(this.razonSocial != null ? this.razonSocial.toUpperCase().trim() : null);
                                    empresa.setVDireccion(this.direccion != null ? this.direccion.toUpperCase().trim() : null);
                                    empresa.setVTelefono(this.telefono != null ? this.telefono.toUpperCase().trim() : null);
                                    empresa.setVRutaLogo(filepath.concat("logo".concat(fileExt)));
                                    empresa.setNFlgActivo(BigDecimal.ONE);
                                    empresa.setDFecCreacion(new Date());
                                    empresa.setVUsuCreacion(usuarioSession.getVUsuario());
                                    empresa.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());

                                    empresaDao.registrarEmpresa(empresa);
                                    setListaEmpresa(empresaDao.obtenerListaEmpresas());
                                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
//                                }else{
//                                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Cargue el logotipo de la empresa.");
//                                    FacesContext.getCurrentInstance().addMessage(null,message);
//                                }
                            }else{
                                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la telÃ©fono de la empresa.");
                                FacesContext.getCurrentInstance().addMessage(null,message);
                            }
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la direcciÃ³n de la empresa.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese la razÃ³n social de la empresa.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "El RUC debe tener 11 dÃ­gitos.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR," ERROR. ", "Ingrese el nÃºmero de RUC de la empresa.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    public void toEditar(ActionEvent actionEvent){
        try{
            String rowKey = JSFUtils.getRequestParameter("rowKey");
            this.setSelectedEmpresa(this.getListaEmpresa().get(Integer.parseInt(rowKey)));
            String defaultFile = this.getSelectedEmpresa().getVRutaLogo();
            File f = new File(defaultFile);
            this.setNombreOriginal(f.getName());
            this.setContent(new DefaultStreamedContent(FileUtils.openInputStream(f), new MimetypesFileTypeMap().getContentType(f), f.getName()));
            this.setAction(null);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch (NumberFormatException | IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void editarEmpresa(ActionEvent actionEvent) {
        FacesMessage message = null;
        String filepath = null;
        ResourceBundle bundle = null;
        String fileName = null;
        String fileExt = null;
        try {
            if(this.getSelectedEmpresa().getVRuc() != null && !"".equals(this.getSelectedEmpresa().getVRuc())){
                if(this.getSelectedEmpresa().getVRuc().length() == 11){
                    if(this.getSelectedEmpresa().getVRazonSocial() != null && !"".equals(this.getSelectedEmpresa().getVRazonSocial())){
                        if(this.getSelectedEmpresa().getVDireccion() != null && !"".equals(this.getSelectedEmpresa().getVDireccion())){
                            if(this.getSelectedEmpresa().getVTelefono() != null && !"".equals(this.getSelectedEmpresa().getVTelefono())){
//                                if(this.getNombreOriginal() != null && !"".equals(this.getNombreOriginal())){
                                    SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                                    EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
                                    SegCabEmpresa empresa = this.getSelectedEmpresa();
                                    
                                    if(this.getFile() != null){
                                        bundle = ResourceBundle.getBundle(Parameters.getParameters());
                                        filepath = bundle.getString("filePath").concat(empresa.getVRuc().trim()).concat("\\"); //Para WINDOWS
                                        File direc = new File(filepath);
                                        direc.mkdirs();
                                        fileName = direc.getName();
                                        fileExt = fileName.substring(fileName.lastIndexOf("."));
                                        File filetoCreate = new File(filepath, "logo".concat(fileExt));
                                        FileOutputStream fileOutStream = new FileOutputStream(filetoCreate);
                                        fileOutStream.write(this.getFile().getContents());
                                        fileOutStream.flush();
                                        fileOutStream.close();
                                        empresa.setVRutaLogo(filepath.concat("logo".concat(fileExt)));
                                    }

                                    empresa.setVRuc(empresa.getVRuc() != null ? empresa.getVRuc().toUpperCase().trim() : null);
                                    empresa.setVRazonSocial(empresa.getVRazonSocial() != null ? empresa.getVRazonSocial().toUpperCase().trim() : null);
                                    empresa.setVDireccion(empresa.getVDireccion() != null ? empresa.getVDireccion().toUpperCase().trim() : null);
                                    empresa.setVTelefono(empresa.getVTelefono() != null ? empresa.getVTelefono().toUpperCase().trim() : null);
                                    empresa.setDFecModificacion(new Date());
                                    empresa.setVUsuModificacion(usuarioSession.getVUsuario());
                                    empresa.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());

                                    empresaDao.registrarEmpresa(empresa);
                                    setListaEmpresa(empresaDao.obtenerListaEmpresas());
                                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
//                                }else{
//                                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Cargue el logotipo de la empresa.");
//                                    FacesContext.getCurrentInstance().addMessage(null,message);
//                                }
                            }else{
                                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la telÃ©fono de la empresa.");
                                FacesContext.getCurrentInstance().addMessage(null,message);
                            }
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la direcciÃ³n de la empresa.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la razÃ³n social de la empresa.");
                        FacesContext.getCurrentInstance().addMessage(null,message);
                    }
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "El RUC debe tener 11 dÃ­gitos.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese el nÃºmero de RUC de la empresa.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    /**
     * Desactiva la empresa seleccionada.
     * @param actionEvent
     */
    public void desactivar(ActionEvent actionEvent){
        try{
            HttpSession session = JSFUtils.getSession();
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            getSelectedEmpresa().setNFlgActivo(BigDecimal.ZERO); //INACTIVO = 0
            empresaDao.registrarEmpresa(getSelectedEmpresa());
            setListaEmpresa(empresaDao.obtenerListaEmpresas());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    /**
     * Activa la empresa seleccionada.
     * @param actionEvent
     */
    public void activar(ActionEvent actionEvent){
        try{
            HttpSession session = JSFUtils.getSession();
            EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
            getSelectedEmpresa().setNFlgActivo(BigDecimal.ONE); //ACTIVO = 1
            empresaDao.registrarEmpresa(getSelectedEmpresa());
            setListaEmpresa(empresaDao.obtenerListaEmpresas());
            session.setAttribute("listasSessionMB", new ListasSessionMB());
        }catch(Exception e){
            e.getMessage();
        }
    }

    public List<SelectItem> completeRuc(String query) {
        List<SelectItem> suggestions = new ArrayList<SelectItem>();
        try {
            for (SelectItem p : getItemsRuc()) {
                if (p.getLabel().startsWith(query.toUpperCase())) {
                    suggestions.add(p);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return suggestions;
    }

    public List<SelectItem> completeRazonSocial(String query) {
        List<SelectItem> suggestions = new ArrayList<SelectItem>();
        try {
            for (SelectItem p : getItemsRazonSocial()) {
                if (p.getLabel().startsWith(query.toUpperCase())) {
                    suggestions.add(p);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return suggestions;
    }
}