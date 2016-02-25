/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.UploadedFile;
import pe.com.segrop.sgsapp.dao.DocumentoDao;
import pe.com.segrop.sgsapp.dao.HistorialDao;
import pe.com.segrop.sgsapp.dao.TipoDocumentoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetDocumento;
import pe.com.segrop.sgsapp.domain.SegDetDocumentoId;
import pe.com.segrop.sgsapp.domain.SegDetHistorial;
import pe.com.segrop.sgsapp.domain.SegDetHistorialId;
import pe.com.segrop.sgsapp.domain.SegDetTipoDocumento;
import pe.com.segrop.sgsapp.domain.SegDetTipoDocumentoId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;
import pe.com.segrop.sgsapp.web.common.StringUtil;

/**
 *
 * @author JJ
 */
public class DocumentoMB implements Serializable{

    private String searchEmpresa;
    private String searchProcedencia;
    private String searchTipoDocumento;
    private Date searchFechaInicio;
    private Date searchFechaFin;
    private BigDecimal empresa;
    private BigDecimal procedencia;
    private BigDecimal tipoDocumento;
    private Date fechaEmision;
    private String descripcion;
    private String nombreOriginal;
    private String nombre;
    private String ruta;
    private UploadedFile file;
//    private StreamedContent downloadFile;
    private SegDetDocumento selectedDocument;
    private SegDetHistorial selectedHistorial;
    private List<SegDetDocumento> listaDocumento;
    private List<SegDetHistorial> listaHistorial;
    private String action;
    private String descripcionTipoDocumento;
    private SegDetTipoDocumento selectedTipoDocumento;
    private List<SegDetTipoDocumento> listaTipoDocumentos;
    
    /** Creates a new instance of DocumentoMB */
    public DocumentoMB() {
        selectedDocument = new SegDetDocumento();
    }

    public String getSearchEmpresa() {
        return searchEmpresa;
    }

    public void setSearchEmpresa(String searchEmpresa) {
        this.searchEmpresa = searchEmpresa;
    }

    public String getSearchProcedencia() {
        return searchProcedencia;
    }

    public void setSearchProcedencia(String searchProcedencia) {
        this.searchProcedencia = searchProcedencia;
    }

    public String getSearchTipoDocumento() {
        return searchTipoDocumento;
    }

    public void setSearchTipoDocumento(String searchTipoDocumento) {
        this.searchTipoDocumento = searchTipoDocumento;
    }

    public Date getSearchFechaInicio() {
        return searchFechaInicio;
    }

    public void setSearchFechaInicio(Date searchFechaInicio) {
        this.searchFechaInicio = searchFechaInicio;
    }

    public Date getSearchFechaFin() {
        return searchFechaFin;
    }

    public void setSearchFechaFin(Date searchFechaFin) {
        this.searchFechaFin = searchFechaFin;
    }

    public BigDecimal getEmpresa() {
        return empresa;
    }

    public void setEmpresa(BigDecimal empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(BigDecimal procedencia) {
        this.procedencia = procedencia;
    }

    public BigDecimal getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(BigDecimal tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

//    public StreamedContent getDownloadFile() {
//        return downloadFile;
//    }
//
//    public void setDownloadFile(StreamedContent downloadFile) {
//        this.downloadFile = downloadFile;
//    }

    public SegDetDocumento getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(SegDetDocumento selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public SegDetHistorial getSelectedHistorial() {
        return selectedHistorial;
    }

    public void setSelectedHistorial(SegDetHistorial selectedHistorial) {
        this.selectedHistorial = selectedHistorial;
    }

    public List<SegDetDocumento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<SegDetDocumento> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public List<SegDetHistorial> getListaHistorial() {
        return listaHistorial;
    }

    public void setListaHistorial(List<SegDetHistorial> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescripcionTipoDocumento() {
        return descripcionTipoDocumento;
    }

    public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
        this.descripcionTipoDocumento = descripcionTipoDocumento;
    }

    public SegDetTipoDocumento getSelectedTipoDocumento() {
        return selectedTipoDocumento;
    }

    public void setSelectedTipoDocumento(SegDetTipoDocumento selectedTipoDocumento) {
        this.selectedTipoDocumento = selectedTipoDocumento;
    }

    public List<SegDetTipoDocumento> getListaTipoDocumentos() {
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<SegDetTipoDocumento> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }
    
    public void clear(){
        this.empresa = null;
        this.procedencia = null;
        this.tipoDocumento = null;
        this.fechaEmision = null;
        this.descripcion = null;
        this.nombreOriginal = null;
        this.nombre = null;
        this.ruta = null;
        this.file = null;
        this.selectedDocument = new SegDetDocumento();
    }
    
    public void handleFileUpload(){
        String filepath = null;
        String temppath = null;
        ResourceBundle bundle = null;
        try {
            if(this.getFile() != null){
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                temppath = bundle.getString("tempPath").concat(segCabEmpresa.getVRuc()).concat("\\"); //Para WINDOWS
//                    temppath = bundle.getString("tempPath").concat(segCabEmpresa.getVRuc()).concat("/"); //Para LINUX

                File direc = new File(temppath);
                direc.mkdirs();
                File filetoCreate = new File(temppath, this.getFile().getFileName());
                FileOutputStream fileOutStream = new FileOutputStream(filetoCreate);
                fileOutStream.write(this.getFile().getContents());
                fileOutStream.flush();
                fileOutStream.close();
                
                this.setNombreOriginal(this.file.getFileName());
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public void handleFileDownload(){
        String filePath = null;
        String fileName = null;
        try{
            String rowKey = BaseBean.getRequestParameter("rowKey");
            SegDetDocumento doc = this.getListaDocumento().get(Integer.parseInt(rowKey));
            if(doc != null){
                filePath = doc.getVRuta();
                fileName = doc.getVNombre();
                
                File f = new File(filePath.concat(fileName));
                FileInputStream fileIn = new FileInputStream(f);
                HttpServletResponse response = BaseBean.getResponse();
                System.out.println("file length: "+f.length());
                System.out.println("file path: "+filePath);
                System.out.println("file name: "+fileName);
                response.setContentLength(fileIn.available());
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition","attachment;filename=\"".concat(fileName).concat("\""));
                ServletOutputStream out = response.getOutputStream();
                
                int c;
                while((c=fileIn.read()) != -1){
                    out.write(c);
                }
                /*
                int len = (int)f.length();
                byte[] outputByte = new byte[len];
                //copy binary contect to output stream
                while(fileIn.read(outputByte, 0, len) != -1){
                    out.write(outputByte, 0, len);
                }
                */
                
                
                /*
                byte[] outputByte = IOUtils.toByteArray(fileIn);
                int read = 0;
                //copy binary contect to output stream
                while((read = fileIn.read(outputByte)) != -1){
                    out.write(outputByte, 0, read);
                }
                */
                out.flush();
                out.close();
                fileIn.close();
                //Desktop.getDesktop().open(f);
            }
        }catch(IOException e){
            e.getMessage();
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }
    
    public String handleFileDownload2(){
        try{
            String rowKey = BaseBean.getRequestParameter("rowKey");
            SegDetDocumento doc = this.getListaDocumento().get(Integer.parseInt(rowKey));
            BaseBean.getRequest().setAttribute("filePath", doc.getVRuta());
            BaseBean.getRequest().setAttribute("fileName", doc.getVNombre());
        }catch(NumberFormatException e){
            e.getMessage();
        }
        return "fileDownload";
    }
    
    public void buscarDocumento(ActionEvent actionEvent) {
        try {
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            SegDetDocumentoId segDetDocumentoId = new SegDetDocumentoId();
            segDetDocumentoId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            SegDetDocumento segDetDocumento = new SegDetDocumento();
            segDetDocumento.setId(segDetDocumentoId);
            segDetDocumento.setNCodEmpresa(segDetDocumentoId.getNCodEmpresa());
            //segDetDocumento.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            segDetDocumento.setNProcedencia(this.getSearchProcedencia() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchProcedencia())) : null);
            segDetDocumento.setNTipoDocumento(this.getSearchTipoDocumento() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchTipoDocumento())) : null);
            segDetDocumento.setDFecInicio(this.getSearchFechaInicio() != null ? this.getSearchFechaInicio() : null);
            segDetDocumento.setDFecFin(this.getSearchFechaFin() != null ? this.getSearchFechaFin() : null);
            setListaDocumento(documentoDao.buscarDocumentos(segDetDocumento));
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }
    
    public void buscarDocumentoDesactivado(ActionEvent actionEvent) {
        try {
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            SegDetDocumentoId segDetDocumentoId = new SegDetDocumentoId();
            segDetDocumentoId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            SegDetDocumento segDetDocumento = new SegDetDocumento();
            segDetDocumento.setId(segDetDocumentoId);
            segDetDocumento.setNCodEmpresa(segDetDocumentoId.getNCodEmpresa());
            //segDetDocumento.setNCodEmpresa(this.getSearchEmpresa() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchEmpresa())) : null);
            segDetDocumento.setNProcedencia(this.getSearchProcedencia() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchProcedencia())) : null);
            segDetDocumento.setNTipoDocumento(this.getSearchTipoDocumento() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchTipoDocumento())) : null);
            setListaDocumento(documentoDao.buscarDocumentosDesactivados(segDetDocumento));
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }
    
    public void toRegistrar(ActionEvent event){
        try{
            this.clear();
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void registrarDocumento(ActionEvent event){
        String temppath = null;
        String filepath = null;
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            if(this.procedencia != null && !"-1".equals(this.procedencia.toString())){
                if(this.tipoDocumento != null && !"-1".equals(this.tipoDocumento.toString())){
                    if(this.descripcion != null && !"".equals(this.descripcion)){
                        if(this.fechaEmision != null && !"".equals(this.fechaEmision.toString())){
                            temppath = bundle.getString("tempPath").concat(segCabEmpresa.getVRuc()).concat("\\"); //Para WINDOWS
                            filepath = bundle.getString("filePath").concat(segCabEmpresa.getVRuc()).concat("\\"); //Para WINDOWS
//                                temppath = bundle.getString("tempPath").concat(segCabEmpresa.getVRuc()).concat("/"); //Para LINUX
//                                filepath = bundle.getString("filepath").concat(segCabEmpresa.getVRuc()).concat("/"); //Para LINUX

                            long seqValue = documentoDao.nextSequenceValue();
                            String name = this.getFile().getFileName();
                            String extension = name.substring(name.lastIndexOf("."));
                            String fileName = "doc".concat(StringUtil.integerAsString(seqValue)).concat("_01").concat(extension);
                            File direc = new File(filepath);
                            direc.mkdirs();
                            File filetoCreate = new File(filepath, fileName);
                            FileOutputStream fileOutStream = new FileOutputStream(filetoCreate);
                            fileOutStream.write(this.getFile().getContents());
                            fileOutStream.flush();
                            fileOutStream.close();

                            direc = new File(temppath);
                            direc.delete();

                            SegDetDocumentoId segDetDocumentoId = new SegDetDocumentoId();
                            segDetDocumentoId.setNCodDocumento(BigDecimal.valueOf(seqValue));
                            segDetDocumentoId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
                            
                            SegDetDocumento segDetDocumento = new SegDetDocumento();
                            segDetDocumento.setId(segDetDocumentoId);
                            segDetDocumento.setNCodDocumento(BigDecimal.valueOf(seqValue));
                            segDetDocumento.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
                            segDetDocumento.setNProcedencia(procedencia);
                            segDetDocumento.setNTipoDocumento(tipoDocumento);
                            segDetDocumento.setVDescripcion(descripcion.toUpperCase().trim());
                            segDetDocumento.setDFecEmision(fechaEmision);
                            segDetDocumento.setNVersion(BigDecimal.ONE);
                            segDetDocumento.setNActivo(BigDecimal.ONE);
                            segDetDocumento.setVNombreOriginal(this.getNombreOriginal());
                            segDetDocumento.setVNombre(fileName);
                            segDetDocumento.setVRuta(filepath); //Windows
                            segDetDocumento.setDFecCreacion(new Date());
                            segDetDocumento.setVUsuCreacion(usuario.getVUsuario());
                            segDetDocumento.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                            documentoDao.registrarDocumento(segDetDocumento);
                            
                            this.setAction("Richfaces.hideModalPanel('dlg')");
                        }else{
                            FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la fecha de emisión del documento."));
                        }
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la descripción del documento."));
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el tipo de documento."));
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione la procedencia del documento."));
            }
        }catch(IOException e){
            e.getMessage();
        }
    }
    
    public void toEdit(ActionEvent event){
        try{
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void editarDocumento(ActionEvent event){
        try{
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            SegDetDocumento segDetDocumento = this.getSelectedDocument();
            if(segDetDocumento.getNProcedencia() != null && !"-1".equals(segDetDocumento.getNProcedencia().toString())){
                if(segDetDocumento.getNTipoDocumento() != null && !"-1".equals(segDetDocumento.getNTipoDocumento().toString())){
                    if(segDetDocumento.getVDescripcion() != null && !"".equals(segDetDocumento.getVDescripcion())){
                        if(segDetDocumento.getDFecEmision() != null && !"".equals(segDetDocumento.getDFecEmision().toString())){
                            segDetDocumento.setNProcedencia(segDetDocumento.getNProcedencia());
                            segDetDocumento.setNTipoDocumento(segDetDocumento.getNTipoDocumento());
                            segDetDocumento.setVDescripcion(segDetDocumento.getVDescripcion().toUpperCase().trim());
                            segDetDocumento.setDFecEmision(segDetDocumento.getDFecEmision());
                            segDetDocumento.setDFecModificacion(new Date());
                            segDetDocumento.setVUsuModificacion(usuario.getVUsuario());
                            segDetDocumento.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                            documentoDao.registrarDocumento(segDetDocumento);

                            this.setAction("Richfaces.hideModalPanel('editDlg')");
                        }else{
                            FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la fecha de emisión del documento."));
                        }
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la descripción del documento."));
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el tipo de documento."));
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione la procedencia del documento."));
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void eliminarDocumento(ActionEvent event){
        try{
            SegDetDocumento segDetDocumento = this.getSelectedDocument();
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            documentoDao.eliminarDocumento(segDetDocumento);
            this.getListaDocumento().remove(segDetDocumento);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void desactivarDocumento(ActionEvent event){
        try{
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            SegDetDocumento segDetDocumento = this.getSelectedDocument();
            segDetDocumento.setNActivo(BigDecimal.ZERO);
            segDetDocumento.setDFecModificacion(new Date());
            segDetDocumento.setVUsuModificacion(usuario.getVUsuario());
            segDetDocumento.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
            documentoDao.registrarDocumento(segDetDocumento);
            this.getListaDocumento().remove(segDetDocumento);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void activarDocumento(ActionEvent event){
        try{
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            SegDetDocumento segDetDocumento = this.getSelectedDocument();
            segDetDocumento.setNActivo(BigDecimal.ONE);
            segDetDocumento.setDFecModificacion(new Date());
            segDetDocumento.setVUsuModificacion(usuario.getVUsuario());
            segDetDocumento.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
            documentoDao.registrarDocumento(segDetDocumento);
            this.getListaDocumento().remove(segDetDocumento);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void versionarDocumento(ActionEvent event){
        String temppath = null;
        String filepath = null;
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            HistorialDao historialDao = (HistorialDao) ServiceFinder.findBean("HistorialDao");
            SegCabUsuario usuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            
            temppath = bundle.getString("tempPath").concat(segCabEmpresa.getVRuc()).concat("\\"); //Para WINDOWS
            filepath = bundle.getString("filePath").concat(segCabEmpresa.getVRuc()).concat("\\"); //Para WINDOWS
//            temppath = bundle.getString("tempPath").concat(segCabEmpresa.getVRuc()).concat("/"); //Para LINUX
//            filepath = bundle.getString("filepath").concat(segCabEmpresa.getVRuc()).concat("/"); //Para LINUX

            SegDetDocumento segDetDocumento = this.getSelectedDocument();
            
            // se registra la version del documento actual en el historial.
            SegDetHistorialId segDetHistorialId = new SegDetHistorialId();
            segDetHistorialId.setNCodHistorial(BigDecimal.valueOf(historialDao.nextSequenceValue()));
            segDetHistorialId.setNCodDocumento(segDetDocumento.getNCodDocumento());
            segDetHistorialId.setNCodEmpresa(segDetDocumento.getNCodEmpresa());
            SegDetHistorial segDetHistorial = new SegDetHistorial();
            segDetHistorial.setId(segDetHistorialId);
            segDetHistorial.setNCodHistorial(segDetHistorialId.getNCodHistorial());
            segDetHistorial.setNCodDocumento(segDetDocumento.getNCodDocumento());
            segDetHistorial.setNCodEmpresa(segDetDocumento.getNCodEmpresa());
            segDetHistorial.setNProcedencia(segDetDocumento.getNProcedencia());
            segDetHistorial.setNTipoDocumento(segDetDocumento.getNTipoDocumento());
            segDetHistorial.setVDescripcion(segDetDocumento.getVDescripcion());
            segDetHistorial.setDFecEmision(segDetDocumento.getDFecEmision());
            segDetHistorial.setNVersion(segDetDocumento.getNVersion());
            segDetHistorial.setVNombreOriginal(segDetDocumento.getVNombreOriginal());
            segDetHistorial.setVNombre(segDetDocumento.getVNombre());
            segDetHistorial.setVRuta(segDetDocumento.getVRuta()); //Windows
            segDetHistorial.setDFecCreacion(new Date());
            segDetHistorial.setVUsuCreacion(usuario.getVUsuario());
            segDetHistorial.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
            historialDao.registrarHistorial(segDetHistorial);
            
            //se guarda la nueva version del documento en disco.
            String codigo = StringUtil.integerAsString(segDetDocumento.getNCodDocumento().longValue());
            long version = historialDao.obtenerMaximoVersionDocumento(segDetDocumento);
            if(version > segDetDocumento.getNVersion().longValue()){
                version++;
            }else{
                version = segDetDocumento.getNVersion().longValue()+1;
            }
            
            String name = this.getFile().getFileName();
            String extension = name.substring(name.lastIndexOf("."));
            String fileName = "doc".concat(codigo).concat("_").concat(versionToString(version)).concat(extension);
            File direc = new File(filepath);
            direc.mkdirs();
            File filetoCreate = new File(filepath, fileName);
            FileOutputStream fileOutStream = new FileOutputStream(filetoCreate);
            fileOutStream.write(this.getFile().getContents());
            fileOutStream.flush();
            fileOutStream.close();

            direc = new File(temppath);
            direc.delete();
            
            //se actualiza la version del documento.
            segDetDocumento.setVNombre(fileName);
            segDetDocumento.setVNombreOriginal(this.getFile().getFileName());
            segDetDocumento.setNVersion(BigDecimal.valueOf(version));
            segDetDocumento.setDFecModificacion(new Date());
            segDetDocumento.setVUsuModificacion(usuario.getVUsuario());
            segDetDocumento.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
            documentoDao.registrarDocumento(segDetDocumento);
            this.setAction("Richfaces.hideModalPanel('versionDlg')");
        }catch(IOException e){
            e.getMessage();
        }
    }
    
    public void listarHistorico(ActionEvent event){
        try{
            String rowKey = BaseBean.getRequestParameter("rowKey");
            SegDetDocumento segDetDocumento = this.getListaDocumento().get(Integer.parseInt(rowKey));
            this.setSelectedDocument(segDetDocumento);
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            setListaHistorial(documentoDao.listarHistorial(segDetDocumento));
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void restaurarDocumento(ActionEvent event){
        try{
            String rowKey = BaseBean.getRequestParameter("rowKey");
            SegDetHistorial historial = this.getListaHistorial().get(Integer.parseInt(rowKey));
            DocumentoDao documentoDao = (DocumentoDao) ServiceFinder.findBean("DocumentoDao");
            HistorialDao historialDao = (HistorialDao) ServiceFinder.findBean("HistorialDao");
            SegCabUsuario segCabUsuario = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
            
            SegDetDocumento segDetDocumento = this.getSelectedDocument();
            
            // se registra la version del documento actual en el historial.
            SegDetHistorialId segDetHistorialId = new SegDetHistorialId();
            segDetHistorialId.setNCodHistorial(BigDecimal.valueOf(historialDao.nextSequenceValue()));
            segDetHistorialId.setNCodDocumento(segDetDocumento.getNCodDocumento());
            segDetHistorialId.setNCodEmpresa(segDetDocumento.getNCodEmpresa());
            SegDetHistorial segDetHistorial = new SegDetHistorial();
            segDetHistorial.setId(segDetHistorialId);
            segDetHistorial.setNCodHistorial(segDetHistorialId.getNCodHistorial());
            segDetHistorial.setNCodDocumento(segDetDocumento.getNCodDocumento());
            segDetHistorial.setNCodEmpresa(segDetDocumento.getNCodEmpresa());
            segDetHistorial.setNProcedencia(segDetDocumento.getNProcedencia());
            segDetHistorial.setNTipoDocumento(segDetDocumento.getNTipoDocumento());
            segDetHistorial.setVDescripcion(segDetDocumento.getVDescripcion());
            segDetHistorial.setDFecEmision(segDetDocumento.getDFecEmision());
            segDetHistorial.setNVersion(segDetDocumento.getNVersion());
            segDetHistorial.setVNombreOriginal(segDetDocumento.getVNombreOriginal());
            segDetHistorial.setVNombre(segDetDocumento.getVNombre());
            segDetHistorial.setVRuta(segDetDocumento.getVRuta()); //Windows
            segDetHistorial.setDFecCreacion(new Date());
            segDetHistorial.setVUsuCreacion(segCabUsuario.getVUsuario());
            segDetHistorial.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
            historialDao.registrarHistorial(segDetHistorial);
            this.getListaDocumento().remove(segDetDocumento);
            
            //se guarda la nueva version del documento en disco.
//            String codigo = StringUtil.integerAsString(segDetDocumento.getNCodDocumento().longValue());
//            String version = StringUtil.integerAsString(segDetDocumento.getNVersion().longValue()+1);
//            String fileName = "doc".concat(codigo).concat("_").concat(version).concat(".doc");
//            File direc = new File(historial.getVRuta());
//            direc.mkdirs();
//            
//            FileInputStream fis = new FileInputStream(historial.getVRuta()+historial.getVNombre());
//            byte[] data = new byte[fis.available()];
//            fis.read(data);
//            fis.close();
//            
//            File filetoCreate = new File(historial.getVRuta(), fileName);
//            FileOutputStream fileOutStream = new FileOutputStream(filetoCreate);
//            fileOutStream.write(data);
//            fileOutStream.flush();
//            fileOutStream.close();
            
            //se actualiza la version del documento.
            segDetDocumento.setNProcedencia(historial.getNProcedencia());
            segDetDocumento.setNTipoDocumento(historial.getNTipoDocumento());
            segDetDocumento.setVDescripcion(historial.getVDescripcion());
            segDetDocumento.setDFecEmision(historial.getDFecEmision());
            segDetDocumento.setVRuta(historial.getVRuta());
            segDetDocumento.setVNombre(historial.getVNombre());
            segDetDocumento.setVNombreOriginal(historial.getVNombreOriginal());
            segDetDocumento.setNVersion(historial.getNVersion());
            segDetDocumento.setDFecModificacion(new Date());
            segDetDocumento.setVUsuModificacion(segCabUsuario.getVUsuario());
            segDetDocumento.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
            documentoDao.registrarDocumento(segDetDocumento);
            historialDao.eliminarHistorial(historial);
            this.getListaDocumento().add(segDetDocumento);
            this.setAction("Richfaces.hideModalPanel('histDlg')");
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public String versionToString(long version){
        String versionString = null;
        try{
            if((version / 10) > 0)
                versionString = Long.toString(version);
            else
                versionString = "0".concat(Long.toString(version));
        }catch(Exception e){
            e.getMessage();
        }
        return versionString;
    }
    
    public void listarTipoDocumentos(ActionEvent actionEvent){
        try{
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            TipoDocumentoDao tipoDocumentoDao = (TipoDocumentoDao) ServiceFinder.findBean("TipoDocumentoDao");
            this.setListaTipoDocumentos(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(segCabEmpresa));
            this.setSelectedTipoDocumento(new SegDetTipoDocumento());
            this.setDescripcionTipoDocumento(null);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void registrarTipoDocumento(ActionEvent actionEvent){
        ResourceBundle bundle;
        FacesMessage message = null;
        try{
            if(this.descripcionTipoDocumento != null && !"".equals(this.descripcionTipoDocumento.trim())){
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                SegCabEmpresa segCabEmpresa = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                TipoDocumentoDao tipoDocumentoDao = (TipoDocumentoDao) ServiceFinder.findBean("TipoDocumentoDao");
                SegDetTipoDocumentoId segDetTipoDocumentoId = new SegDetTipoDocumentoId();
                segDetTipoDocumentoId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
                SegDetTipoDocumento segDetTipoDocumento = new SegDetTipoDocumento();
                segDetTipoDocumento.setNCodEmpresa(segDetTipoDocumentoId.getNCodEmpresa());
                segDetTipoDocumento.setVDescripcion(this.descripcionTipoDocumento.toUpperCase().trim());
                
                if(tipoDocumentoDao.buscarTipoDocumentoByEmpresa(segDetTipoDocumento) == null){
                    segDetTipoDocumentoId.setNCodTipoDocumento(BigDecimal.valueOf(tipoDocumentoDao.nextSequenceValue()));
                    segDetTipoDocumento.setId(segDetTipoDocumentoId);                    
                    segDetTipoDocumento.setNCodTipoDocumento(segDetTipoDocumentoId.getNCodTipoDocumento());
                    segDetTipoDocumento.setNFlgActivo(BigDecimal.ONE);
                    segDetTipoDocumento.setDFecCreacion(new Date());
                    segDetTipoDocumento.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetTipoDocumento.setVIpCreacion(BaseBean.getRequest().getRemoteAddr());
                    tipoDocumentoDao.registrarTipoDocumento(segDetTipoDocumento);
                    if(this.getListaTipoDocumentos() == null)
                        this.setListaTipoDocumentos(new ArrayList<SegDetTipoDocumento>());
                    this.getListaTipoDocumentos().add(segDetTipoDocumento);
                    this.setDescripcionTipoDocumento(null);
                    
                    SegCabEmpresa owner = new SegCabEmpresa();
                    owner.setNCodEmpresa(BigDecimal.valueOf(Long.parseLong(bundle.getString("COD_OWNER"))));
                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaTipoDocumento(new Items(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(owner), Items.FIRST_ITEM_SELECT, "NCodTipoDocumento","VDescripcion").getItems());
                    SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                    if(!bundle.getString("COD_OWNER").equals(empresaSession.getNCodEmpresa().toString())){
                        listasSessionMB.getListaTipoDocumento().addAll(new Items(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodTipoDocumento","VDescripcion").getItems());
                    }
                    BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la descripción del tipo de documento.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void editarTipoDocumento(ActionEvent actionEvent){
        ResourceBundle bundle;
        FacesMessage message = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegDetTipoDocumento segDetTipoDocumento = (SegDetTipoDocumento) actionEvent.getSource();
            if(segDetTipoDocumento.getVDescripcion() != null && !"".equals(segDetTipoDocumento.getVDescripcion().trim())){
                ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario)BaseBean.getSessionAttribute("usuario");
                segDetTipoDocumento.setVDescripcion(segDetTipoDocumento.getVDescripcion().toUpperCase().trim());
                segDetTipoDocumento.setDFecModificacion(new Date());
                segDetTipoDocumento.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetTipoDocumento.setVIpModificacion(BaseBean.getRequest().getRemoteAddr());
                TipoDocumentoDao tipoDocumentoDao = (TipoDocumentoDao) ServiceFinder.findBean("TipoDocumentoDao");
                tipoDocumentoDao.registrarTipoDocumento(segDetTipoDocumento);
                SegCabEmpresa owner = new SegCabEmpresa();
                owner.setNCodEmpresa(BigDecimal.valueOf(Long.parseLong(bundle.getString("COD_OWNER"))));
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaTipoDocumento(new Items(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(owner), Items.FIRST_ITEM_SELECT, "NCodTipoDocumento","VDescripcion").getItems());
                SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
                if(!bundle.getString("COD_OWNER").equals(empresaSession.getNCodEmpresa().toString())){
                    listasSessionMB.getListaTipoDocumento().addAll(new Items(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodTipoDocumento","VDescripcion").getItems());
                }
                BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la descripción del tipo de documento.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void eliminarTipoDocumento(ActionEvent actionEvent){
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            TipoDocumentoDao tipoDocumentoDao = (TipoDocumentoDao) ServiceFinder.findBean("TipoDocumentoDao");
            tipoDocumentoDao.eliminarTipoDocumento(this.getSelectedTipoDocumento());
            this.getListaTipoDocumentos().remove(this.getSelectedTipoDocumento());
            ListasSessionMB listasSessionMB = (ListasSessionMB)BaseBean.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            SegCabEmpresa owner = new SegCabEmpresa();
            owner.setNCodEmpresa(BigDecimal.valueOf(Long.parseLong(bundle.getString("COD_OWNER"))));
            listasSessionMB.setListaTipoDocumento(new Items(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(owner), Items.FIRST_ITEM_SELECT, "NCodTipoDocumento","VDescripcion").getItems());
            SegCabEmpresa empresaSession = (SegCabEmpresa)BaseBean.getSessionAttribute("empresa");
            if(!bundle.getString("COD_OWNER").equals(empresaSession.getNCodEmpresa().toString())){
                listasSessionMB.getListaTipoDocumento().addAll(new Items(tipoDocumentoDao.obtenerListaTiposDocumentoByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodTipoDocumento","VDescripcion").getItems());
            }
            BaseBean.getSession().setAttribute("listasSessionMB", listasSessionMB);
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
}
