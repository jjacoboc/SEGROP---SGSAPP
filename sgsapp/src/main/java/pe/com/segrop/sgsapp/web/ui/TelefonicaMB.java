/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.awt.Desktop;
import java.io.InputStream;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import pe.com.segrop.sgsapp.dao.CargoInspeccionDao;
import pe.com.segrop.sgsapp.dao.CuestionarioDao;
import pe.com.segrop.sgsapp.dao.InsTelefonicaDao;
import pe.com.segrop.sgsapp.dao.LugarInspeccionDao;
import pe.com.segrop.sgsapp.dao.PreguntaDao;
import pe.com.segrop.sgsapp.dao.ResponsableInspeccionDao;
import pe.com.segrop.sgsapp.dao.RespuestaDao;
import pe.com.segrop.sgsapp.domain.SegCabCargo;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabLugar;
import pe.com.segrop.sgsapp.domain.SegCabResponsable;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetInsTelefonica;
import pe.com.segrop.sgsapp.domain.SegDetPregunta;
import pe.com.segrop.sgsapp.domain.SegDetPreguntaId;
import pe.com.segrop.sgsapp.domain.SegDetRespuesta;
import pe.com.segrop.sgsapp.domain.SegDetRespuestaId;
import pe.com.segrop.sgsapp.domain.SegRelCuestionario;
import pe.com.segrop.sgsapp.domain.SegRelCuestionarioId;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.web.common.File;
import pe.com.segrop.sgsapp.web.common.InspeccionTelefonica;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
@ManagedBean
@ViewScoped
public class TelefonicaMB implements Serializable{
    private static final long serialVersionUID = 1L;

    private String searchPregunta;
    private String searchLugar;
    private String searchResponsable;
    private String searchCargo;
    private Date searchFechaInicio;
    private Date searchFechaFin;
    private String lugar;
    private String responsable;
    private String cargo;
    private String telefono;
    private String pregunta;
    private String descripcionLugar;
    private String descripcionCargo;
    private String nombreResponsable;
    private String apellidoResponsable;
    private List<SegDetPregunta> itemsPregunta;
    private SegDetInsTelefonica selectedInsTelefonica;
    private List<SegDetInsTelefonica> listaInsTelefonica;
    private List<SegDetPregunta> listaPreguntas;
    private SegDetPregunta selectedPregunta;
    private List<SegDetPregunta> listaSelectedPregunta;
    private Boolean[] listaSelectedRowKeys;
    private SegCabLugar selectedLugar;
    private List<SegCabLugar> listaLugares;
    private SegCabResponsable selectedResponsable;
    private List<SegCabResponsable> listaResponsables;
    private SegCabCargo selectedCargo;
    private List<SegCabCargo> listaCargos;
    private String tipoCarga;
    private boolean cargaIndividual;
    private boolean cargaMasiva;
    private File file;
    private List<InspeccionTelefonica> listaInspeccion;
    private String action;
    private String seguimiento;
    private boolean cerrar;
    private String actionOnLoad;
    private boolean fromMatrix;
    private Boolean flag;
    
    /** Creates a new instance of TelefonicaMB */
    public TelefonicaMB() {
        InputStream stream = this.getClass().getResourceAsStream("/resources/plantillaInspeccionTelefonica.xls");
//        downloadedFile = new DefaultStreamedContent(stream, "application/vnd.ms-excel", "inspeccionMasiva.xls");
        selectedInsTelefonica = new SegDetInsTelefonica();
    }

    /**
     * @return the searchPregunta
     */
    public String getSearchPregunta() {
        return searchPregunta;
    }

    /**
     * @param searchPregunta the searchPregunta to set
     */
    public void setSearchPregunta(String searchPregunta) {
        this.searchPregunta = searchPregunta;
    }

    /**
     * @return the searchLugar
     */
    public String getSearchLugar() {
        return searchLugar;
    }

    /**
     * @param searchLugar the searchLugar to set
     */
    public void setSearchLugar(String searchLugar) {
        this.searchLugar = searchLugar;
    }

    /**
     * @return the searchResponsable
     */
    public String getSearchResponsable() {
        return searchResponsable;
    }

    /**
     * @param searchResponsable the searchResponsable to set
     */
    public void setSearchResponsable(String searchResponsable) {
        this.searchResponsable = searchResponsable;
    }

    /**
     * @return the searchCargo
     */
    public String getSearchCargo() {
        return searchCargo;
    }

    /**
     * @param searchCargo the searchCargo to set
     */
    public void setSearchCargo(String searchCargo) {
        this.searchCargo = searchCargo;
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

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
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
     * @return the pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return the descripcionLugar
     */
    public String getDescripcionLugar() {
        return descripcionLugar;
    }

    /**
     * @param descripcionLugar the descripcionLugar to set
     */
    public void setDescripcionLugar(String descripcionLugar) {
        this.descripcionLugar = descripcionLugar;
    }

    /**
     * @return the descripcionCargo
     */
    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    /**
     * @param descripcionCargo the descripcionCargo to set
     */
    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    /**
     * @return the itemsPregunta
     */
    public List<SegDetPregunta> getItemsPregunta() {
        if (this.itemsPregunta == null) {
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
            itemsPregunta = preguntaDao.obtenerListaPreguntasActivasByEmpresa(segCabEmpresa);
        }
        return itemsPregunta;
    }

    /**
     * @param itemsPregunta the itemsPregunta to set
     */
    public void setItemsPregunta(List<SegDetPregunta> itemsPregunta) {
        this.itemsPregunta = itemsPregunta;
    }

    /**
     * @return the selectedInsTelefonica
     */
    public SegDetInsTelefonica getSelectedInsTelefonica() {
        return selectedInsTelefonica;
    }

    /**
     * @param selectedInsTelefonica the selectedInsTelefonica to set
     */
    public void setSelectedInsTelefonica(SegDetInsTelefonica selectedInsTelefonica) {
        this.selectedInsTelefonica = selectedInsTelefonica;
    }

    /**
     * @return the listaInsTelefonica
     */
    public List<SegDetInsTelefonica> getListaInsTelefonica() {
        return listaInsTelefonica;
    }

    /**
     * @param listaInsTelefonica the listaInsTelefonica to set
     */
    public void setListaInsTelefonica(List<SegDetInsTelefonica> listaInsTelefonica) {
        this.listaInsTelefonica = listaInsTelefonica;
    }

    /**
     * @return the listaPreguntas
     */
    public List<SegDetPregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    /**
     * @param listaPreguntas the listaPreguntas to set
     */
    public void setListaPreguntas(List<SegDetPregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    /**
     * @return the selectedPregunta
     */
    public SegDetPregunta getSelectedPregunta() {
        return selectedPregunta;
    }

    /**
     * @param selectedPregunta the selectedPregunta to set
     */
    public void setSelectedPregunta(SegDetPregunta selectedPregunta) {
        this.selectedPregunta = selectedPregunta;
    }

    /**
     * @return the listaSelectedPregunta
     */
    public List<SegDetPregunta> getListaSelectedPregunta() {
        return listaSelectedPregunta;
    }

    /**
     * @param listaSelectedPregunta the listaSelectedPregunta to set
     */
    public void setListaSelectedPregunta(List<SegDetPregunta> listaSelectedPregunta) {
        this.listaSelectedPregunta = listaSelectedPregunta;
    }

    public Boolean[] getListaSelectedRowKeys() {
        if(listaSelectedRowKeys != null)
            return listaSelectedRowKeys;
        else
            return new Boolean[0];
    }

    public void setListaSelectedRowKeys(Boolean[] listaSelectedRowKeys) {
        this.listaSelectedRowKeys = listaSelectedRowKeys;
    }

    /**
     * @return the selectedLugar
     */
    public SegCabLugar getSelectedLugar() {
        return selectedLugar;
    }

    /**
     * @param selectedLugar the selectedLugar to set
     */
    public void setSelectedLugar(SegCabLugar selectedLugar) {
        this.selectedLugar = selectedLugar;
    }

    /**
     * @return the listaLugares
     */
    public List<SegCabLugar> getListaLugares() {
        if(listaLugares == null){
            LugarInspeccionDao lugarInspeccionDao = (LugarInspeccionDao) ServiceFinder.findBean("LugarInspeccionDao");
            listaLugares = lugarInspeccionDao.obtenerListaLugares();
        }
        return listaLugares;
    }

    /**
     * @param listaLugares the listaLugares to set
     */
    public void setListaLugares(List<SegCabLugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    /**
     * @return the selectedResponsable
     */
    public SegCabResponsable getSelectedResponsable() {
        return selectedResponsable;
    }

    /**
     * @param selectedResponsable the selectedResponsable to set
     */
    public void setSelectedResponsable(SegCabResponsable selectedResponsable) {
        this.selectedResponsable = selectedResponsable;
    }

    /**
     * @return the listaResponsables
     */
    public List<SegCabResponsable> getListaResponsables() {
        if(listaResponsables == null){
            ResponsableInspeccionDao responsableInspeccionDao = (ResponsableInspeccionDao) ServiceFinder.findBean("ResponsableInspeccionDao");
            listaResponsables = responsableInspeccionDao.obtenerListaResponsables();
        }
        return listaResponsables;
    }

    /**
     * @param listaResponsables the listaResponsables to set
     */
    public void setListaResponsables(List<SegCabResponsable> listaResponsables) {
        this.listaResponsables = listaResponsables;
    }

    /**
     * @return the selectedCargo
     */
    public SegCabCargo getSelectedCargo() {
        return selectedCargo;
    }

    /**
     * @param selectedCargo the selectedCargo to set
     */
    public void setSelectedCargo(SegCabCargo selectedCargo) {
        this.selectedCargo = selectedCargo;
    }

    /**
     * @return the listaCargos
     */
    public List<SegCabCargo> getListaCargos() {
        if(listaCargos == null){
            CargoInspeccionDao cargoInspeccionDao = (CargoInspeccionDao) ServiceFinder.findBean("CargoInspeccionDao");
            listaCargos = cargoInspeccionDao.obtenerListaCargos();
        }
        return listaCargos;
    }

    /**
     * @param listaCargos the listaCargos to set
     */
    public void setListaCargos(List<SegCabCargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    /**
     * @return the tipoCarga
     */
    public String getTipoCarga() {
        return tipoCarga;
    }

    /**
     * @param tipoCarga the tipoCarga to set
     */
    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    /**
     * @return the cargaIndividual
     */
    public boolean isCargaIndividual() {
        return cargaIndividual;
    }

    /**
     * @return the cargaMasiva
     */
    public boolean isCargaMasiva() {
        return cargaMasiva;
    }

    /**
     * @param cargaIndividual the cargaIndividual to set
     */
    public void setCargaIndividual(boolean cargaIndividual) {
        this.cargaIndividual = cargaIndividual;
    }

    /**
     * @param cargaMasiva the cargaMasiva to set
     */
    public void setCargaMasiva(boolean cargaMasiva) {
        this.cargaMasiva = cargaMasiva;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the listaInspeccion
     */
    public List<InspeccionTelefonica> getListaInspeccion() {
        return listaInspeccion;
    }

    /**
     * @param listaInspeccion the listaInspeccion to set
     */
    public void setListaInspeccion(List<InspeccionTelefonica> listaInspeccion) {
        this.listaInspeccion = listaInspeccion;
    }
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }

    public boolean isCerrar() {
        return cerrar;
    }

    public void setCerrar(boolean cerrar) {
        this.cerrar = cerrar;
    }

    /**
     * @return the actionOnLoad
     */
    public String getActionOnLoad() {
        return actionOnLoad;
    }

    /**
     * @param actionOnLoad the actionOnLoad to set
     */
    public void setActionOnLoad(String actionOnLoad) {
        this.actionOnLoad = actionOnLoad;
    }

    /**
     * @return the fromMatrix
     */
    public boolean isFromMatrix() {
        return fromMatrix;
    }

    /**
     * @param fromMatrix the fromMatrix to set
     */
    public void setFromMatrix(boolean fromMatrix) {
        this.fromMatrix = fromMatrix;
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
                ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaLocalActivoByEmpresa(new Items(null, Items.FIRST_ITEM_SELECT, "NCodLocal", "VDescripcion").getItems());
                JSFUtils.setSessionAttribute("listasSessionMB", listasSessionMB);
                this.setFlag(true);
            } else {
                this.setFlag(false);
            }
            this.setSelectedInsTelefonica(new SegDetInsTelefonica());
            this.setFromMatrix(false);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void handleChangeTipoCarga(AjaxBehaviorEvent event){
        try{
            if(event != null){
                String selectedTipoCarga = (String) ((SelectOneRadio) event.getSource()).getValue();
                if("1".equals(selectedTipoCarga)){
                    this.setCargaIndividual(true);
                    this.setCargaMasiva(false);
                }else{
                    this.setCargaIndividual(false);
                    this.setCargaMasiva(true);
                }
                this.setTipoCarga(selectedTipoCarga);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void handleChangeReqMonitor(AjaxBehaviorEvent event){
        try{
            if(event != null){
                String selectedReqMonitor = (String) ((SelectOneRadio) event.getSource()).getValue();
                if("1".equals(selectedReqMonitor)){
                    this.setCerrar(false);
                }else{
                    this.setCerrar(true);
                }
                this.setSeguimiento(selectedReqMonitor);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void handleUploadFile(FileUploadEvent event){
        try{
            UploadedFile item = event.getFile();
            File uploadfile = new File();
            uploadfile.setName(item.getFileName());
            uploadfile.setLength(item.getSize());
            uploadfile.setData(item.getContents());
            uploadfile.setMime(item.getContentType());
            this.setFile(uploadfile);
        }catch(Exception e){
            e.getMessage();
            
        }
        
    }
    
    public void buscarInspeccionTelefonica(ActionEvent actionEvent) {
        try {
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            InsTelefonicaDao insTelefonicaDao = (InsTelefonicaDao) ServiceFinder.findBean("InsTelefonicaDao");
            SegDetInsTelefonica segDetInsTelefonica = new SegDetInsTelefonica();
            segDetInsTelefonica.setNCodEmpresa(empresaSession.getNCodEmpresa());
            segDetInsTelefonica.setNCodLugar(this.getSearchLugar() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchLugar())) : null);
            segDetInsTelefonica.setNCodResponsable(this.getSearchResponsable() != null ? BigDecimal.valueOf(Long.parseLong(this.getSearchResponsable())) : null);
            segDetInsTelefonica.setNCodCargo(this.getSearchCargo() !=null ? BigDecimal.valueOf(Long.parseLong(this.getSearchCargo())) : null);
            segDetInsTelefonica.setDFecInicio(this.getSearchFechaInicio() != null ? this.getSearchFechaInicio() : null);
            segDetInsTelefonica.setDFecFin(this.getSearchFechaFin() != null ? this.getSearchFechaFin() : null);
            segDetInsTelefonica.setVPregunta(this.getSearchPregunta() != null ? this.getSearchPregunta().toUpperCase().trim() : null);
            setListaInsTelefonica(insTelefonicaDao.buscarInspeccionesTelefonicas(segDetInsTelefonica));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toRegistrar(ActionEvent actionEvent){
        try{
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
            List<SegDetPregunta> lista = preguntaDao.obtenerListaPreguntasActivasByEmpresa(empresaSession);
            this.setListaPreguntas(lista != null ? lista : new ArrayList<SegDetPregunta>());
            this.setListaSelectedPregunta(new ArrayList<SegDetPregunta>());
            this.setListaSelectedRowKeys(new Boolean[this.getListaPreguntas().size()]);
            this.setLugar("-1");
            this.setCargo("-1");
            this.setResponsable("-1");
            this.setTelefono("");
            this.setPregunta("");
            this.setFile(null);
            this.setListaInspeccion(new ArrayList<InspeccionTelefonica>());
            this.setTipoCarga("1");
            this.setCargaIndividual(true);
            this.setCargaMasiva(false);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void toVer(ActionEvent actionEvent){
        try{
            if(actionEvent != null){
                String rowkey = JSFUtils.getRequestParameter("rowkey");
                SegDetInsTelefonica segDetInsTelefonica = this.getListaInsTelefonica().get(Integer.parseInt(rowkey));
                this.setSelectedInsTelefonica(segDetInsTelefonica);
                PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
                this.getSelectedInsTelefonica().setSegDetPreguntas(preguntaDao.obtenerListaPreguntasByInspeccion(this.getSelectedInsTelefonica()));
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void toSeguimiento(ActionEvent actionEvent){
        try{
            if(actionEvent != null){
                String rowkey = JSFUtils.getRequestParameter("rowkey");
                SegDetInsTelefonica segDetInsTelefonica = this.getListaInsTelefonica().get(Integer.parseInt(rowkey));
                this.setSelectedInsTelefonica(segDetInsTelefonica);
                PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
                RespuestaDao respuestaDao = (RespuestaDao) ServiceFinder.findBean("RespuestaDao");
                this.getSelectedInsTelefonica().setSegDetPreguntas(preguntaDao.obtenerListaPreguntasByInspeccion(this.getSelectedInsTelefonica()));
                List<SegDetPregunta> listaPreguntasInspeccion = this.getSelectedInsTelefonica().getSegDetPreguntas();
                if(listaPreguntasInspeccion != null && !listaPreguntasInspeccion.isEmpty()){
                    for (SegDetPregunta preguntaInspeccion : listaPreguntasInspeccion) {
                        preguntaInspeccion.setSegDetRespuestas(respuestaDao.obtenerListaRespuestasByPreguntaInspeccion(preguntaInspeccion, segDetInsTelefonica));
                    }
                }
                this.setCerrar(false);
                this.setSeguimiento("1");
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarSeguimiento(ActionEvent actionEvent){
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            InsTelefonicaDao insTelefonicaDao = (InsTelefonicaDao) ServiceFinder.findBean("InsTelefonicaDao");
            RespuestaDao respuestaDao = (RespuestaDao) ServiceFinder.findBean("RespuestaDao");
            List<SegDetPregunta> listaPreguntasInspeccion = this.getSelectedInsTelefonica().getSegDetPreguntas();
            if(listaPreguntasInspeccion != null && !listaPreguntasInspeccion.isEmpty()){
                for (SegDetPregunta preguntaInspeccion : listaPreguntasInspeccion) {
                    SegDetRespuestaId segDetRespuestaId = new SegDetRespuestaId();
                    segDetRespuestaId.setNCodRespuesta(BigDecimal.valueOf(respuestaDao.nextSequenceValue()));
                    segDetRespuestaId.setNCodInstelefonica(this.getSelectedInsTelefonica().getNCodInstelefonica());
                    segDetRespuestaId.setNCodPregunta(preguntaInspeccion.getNCodPregunta());
                    segDetRespuestaId.setNCodEmpresa(preguntaInspeccion.getNCodEmpresa());
                    SegDetRespuesta segDetRespuesta = new SegDetRespuesta();
                    segDetRespuesta.setId(segDetRespuestaId);
                    String respuesta = preguntaInspeccion.getVRespuesta().trim();
                    segDetRespuesta.setVDescripcion((respuesta != null && !"".equals(respuesta)) ? respuesta.toUpperCase() : "---");
                    segDetRespuesta.setNFlgActivo(BigDecimal.ONE);
                    segDetRespuesta.setDFecCreacion(new Date());
                    segDetRespuesta.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetRespuesta.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    respuestaDao.registrarRespuesta(segDetRespuesta);
                }
                if(this.getListaInsTelefonica().contains(this.getSelectedInsTelefonica())){
                    SegDetInsTelefonica segDetInsTelefonica = this.getListaInsTelefonica().get(this.getListaInsTelefonica().lastIndexOf(this.getSelectedInsTelefonica()));
                    segDetInsTelefonica.setNSeguimiento(BigDecimal.ONE);
                    segDetInsTelefonica.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_SEGUIMIENTO"))));
                    this.getSelectedInsTelefonica().setNSeguimiento(BigDecimal.ONE);
                    this.getSelectedInsTelefonica().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_SEGUIMIENTO"))));
                    insTelefonicaDao.registrarInspeccionTelefonica(segDetInsTelefonica);
                }
                RequestContext.getCurrentInstance().execute("PF('segDlg').hide();");
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarCerrarSeguimiento(ActionEvent actionEvent){
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            InsTelefonicaDao insTelefonicaDao = (InsTelefonicaDao) ServiceFinder.findBean("InsTelefonicaDao");
            RespuestaDao respuestaDao = (RespuestaDao) ServiceFinder.findBean("RespuestaDao");
            List<SegDetPregunta> listaPreguntasInspeccion = this.getSelectedInsTelefonica().getSegDetPreguntas();
            if(listaPreguntasInspeccion != null && !listaPreguntasInspeccion.isEmpty()){
                for (SegDetPregunta preguntaInspeccion : listaPreguntasInspeccion) {
                    SegDetRespuestaId segDetRespuestaId = new SegDetRespuestaId();
                    segDetRespuestaId.setNCodRespuesta(BigDecimal.valueOf(respuestaDao.nextSequenceValue()));
                    segDetRespuestaId.setNCodInstelefonica(this.getSelectedInsTelefonica().getNCodInstelefonica());
                    segDetRespuestaId.setNCodPregunta(preguntaInspeccion.getNCodPregunta());
                    segDetRespuestaId.setNCodEmpresa(preguntaInspeccion.getNCodEmpresa());
                    SegDetRespuesta segDetRespuesta = new SegDetRespuesta();
                    segDetRespuesta.setId(segDetRespuestaId);
                    String respuesta = preguntaInspeccion.getVRespuesta().trim();
                    segDetRespuesta.setVDescripcion((respuesta != null && !"".equals(respuesta)) ? respuesta.toUpperCase() : "---");
                    segDetRespuesta.setNFlgActivo(BigDecimal.ONE);
                    segDetRespuesta.setDFecCreacion(new Date());
                    segDetRespuesta.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetRespuesta.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    respuestaDao.registrarRespuesta(segDetRespuesta);
                }
                if(this.getListaInsTelefonica().contains(this.getSelectedInsTelefonica())){
                    SegDetInsTelefonica segDetInsTelefonica = this.getListaInsTelefonica().get(this.getListaInsTelefonica().lastIndexOf(this.getSelectedInsTelefonica()));
                    segDetInsTelefonica.setNSeguimiento(BigDecimal.ZERO);
                    segDetInsTelefonica.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
                    this.getSelectedInsTelefonica().setNSeguimiento(BigDecimal.ZERO);
                    this.getSelectedInsTelefonica().setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))));
                    insTelefonicaDao.registrarInspeccionTelefonica(segDetInsTelefonica);
                }
                RequestContext.getCurrentInstance().execute("PF('segDlg').hide();");
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarPregunta(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(this.pregunta != null && !"".equals(this.pregunta.trim())){
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
                SegDetPreguntaId segDetPreguntaId = new SegDetPreguntaId();
                segDetPreguntaId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                SegDetPregunta segDetPregunta = new SegDetPregunta();
                segDetPregunta.setId(segDetPreguntaId);
                segDetPregunta.setNCodEmpresa(segDetPreguntaId.getNCodEmpresa());
                segDetPregunta.setNCodPregunta(segDetPreguntaId.getNCodPregunta());
                segDetPregunta.setVDescripcion(this.pregunta.toUpperCase());

                if(preguntaDao.buscarPregunta(segDetPregunta) == null){
                    segDetPregunta.getId().setNCodPregunta(BigDecimal.valueOf(preguntaDao.nextSequenceValue()));
                    segDetPregunta.setNFlgActivo(BigDecimal.ONE);
                    segDetPregunta.setDFecCreacion(new Date());
                    segDetPregunta.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetPregunta.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    preguntaDao.registrarPregunta(segDetPregunta);
                    this.setListaPreguntas(preguntaDao.obtenerListaPreguntasActivasByEmpresa(empresaSession));
                    this.setListaSelectedRowKeys(new Boolean[this.getListaPreguntas().size()]);
                    this.setPregunta(null);
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "La pregunta ya se encuentra registrada.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la pregunta a registrar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void toEditarPregunta(ActionEvent actionEvent){
        try{
            if(actionEvent != null){
                String rowkey = JSFUtils.getRequestParameter("rowkey");
                SegDetPregunta segDetPregunta = this.getListaPreguntas().get(Integer.parseInt(rowkey));
                this.setSelectedPregunta(segDetPregunta);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarPregunta(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(actionEvent != null){
                SegDetPregunta segDetPregunta = this.getSelectedPregunta();
                if(segDetPregunta.getVDescripcion() != null && !"".equals(segDetPregunta.getVDescripcion().trim())){
                    SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                    PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");

                    segDetPregunta.setVDescripcion(segDetPregunta.getVDescripcion().toUpperCase());
                    segDetPregunta.setDFecModificacion(new Date());
                    segDetPregunta.setVUsuModificacion(usuarioSession.getVUsuario());
                    segDetPregunta.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());

                    preguntaDao.registrarPregunta(segDetPregunta);
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la pregunta a registrar.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarPregunta(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(actionEvent != null){
                PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
                if(!preguntaDao.isPreguntaEnCuestionario(this.getSelectedPregunta())){
                    preguntaDao.eliminarPregunta(this.getSelectedPregunta());
                    this.getListaPreguntas().remove(this.getSelectedPregunta());
                    this.setListaSelectedRowKeys(new Boolean[this.getListaPreguntas().size()]);
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "No puede eliminar la pregunta por haber sido utilizada en una inspección");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void listarLugares(ActionEvent actionEvent){
        try{
            LugarInspeccionDao lugarInspeccionDao = (LugarInspeccionDao) ServiceFinder.findBean("LugarInspeccionDao");
            this.setListaLugares(lugarInspeccionDao.obtenerListaLugares());
            this.setDescripcionLugar(null);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarLugar(ActionEvent actionEvent){
        FacesMessage message = null;
        try{
            if(this.descripcionLugar != null && !"".equals(this.descripcionLugar.trim())){
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                LugarInspeccionDao lugarInspeccionDao = (LugarInspeccionDao) ServiceFinder.findBean("LugarInspeccionDao");
                SegCabLugar segCabLugar = new SegCabLugar();
                segCabLugar.setVDescripcion(this.descripcionLugar.toUpperCase().trim());
                
                if(lugarInspeccionDao.buscarLugar(segCabLugar) == null){
                    segCabLugar.setNCodLugar(BigDecimal.valueOf(lugarInspeccionDao.nextSequenceValue()));
                    segCabLugar.setNFlgActivo(BigDecimal.ONE);
                    segCabLugar.setDFecCreacion(new Date());
                    segCabLugar.setVUsuCreacion(usuarioSession.getVUsuario());
                    segCabLugar.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    lugarInspeccionDao.registrarLugar(segCabLugar);
                    if(this.getListaLugares() == null)
                        this.setListaLugares(new ArrayList());
                    this.getListaLugares().add(segCabLugar);
                    this.setDescripcionLugar(null);
                    //this.setLugar(segCabLugar);
                    ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                    listasSessionMB.setListaLugarInspeccion(new Items(lugarInspeccionDao.obtenerListaLugaresActivos(), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese la descripción del lugar.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarLugar(ActionEvent actionEvent){
        try{
            SegCabLugar segCabLugar = (SegCabLugar) actionEvent.getSource();
            if(segCabLugar.getVDescripcion() != null && !"".equals(segCabLugar.getVDescripcion().trim())){
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                segCabLugar.setVDescripcion(segCabLugar.getVDescripcion().toUpperCase().trim());
                segCabLugar.setDFecModificacion(new Date());
                segCabLugar.setVUsuModificacion(usuarioSession.getVUsuario());
                segCabLugar.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                LugarInspeccionDao lugarInspeccionDao = (LugarInspeccionDao) ServiceFinder.findBean("LugarInspeccionDao");
                lugarInspeccionDao.registrarLugar(segCabLugar);
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB.setListaLugarInspeccion(new Items(lugarInspeccionDao.obtenerListaLugaresActivos(), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarLugar(ActionEvent actionEvent){
        try{
            if(actionEvent != null){
                LugarInspeccionDao lugarInspeccionDao = (LugarInspeccionDao) ServiceFinder.findBean("LugarInspeccionDao");
                lugarInspeccionDao.eliminarLugar(this.getSelectedLugar());
                this.getListaLugares().remove(this.getSelectedLugar());
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaLugarInspeccion(new Items(lugarInspeccionDao.obtenerListaLugaresActivos(), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void listarResponsables(ActionEvent actionEvent){
        try{
            ResponsableInspeccionDao responsableInspeccionDao = (ResponsableInspeccionDao) ServiceFinder.findBean("ResponsableInspeccionDao");
            this.setListaResponsables(responsableInspeccionDao.obtenerListaResponsables());
            this.setNombreResponsable(null);
            this.setApellidoResponsable(null);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarResponsable(ActionEvent actionEvent){
        try{
            if(this.getNombreResponsable() != null && !"".equals(this.getNombreResponsable().trim())){
                if(this.getApellidoResponsable() != null && !"".equals(this.getApellidoResponsable().trim())){
                    SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                    ResponsableInspeccionDao responsableInspeccionDao = (ResponsableInspeccionDao) ServiceFinder.findBean("ResponsableInspeccionDao");
                    SegCabResponsable segCabResponsable = new SegCabResponsable();
                    segCabResponsable.setVNombres(this.getNombreResponsable().toUpperCase().trim());
                    segCabResponsable.setVApellidos(this.getApellidoResponsable().toUpperCase().trim());
                    segCabResponsable.setVDescripcion(segCabResponsable.getVNombres()+" "+segCabResponsable.getVApellidos());

                    if(responsableInspeccionDao.buscarResponsable(segCabResponsable) == null){
                        segCabResponsable.setNCodResponsable(BigDecimal.valueOf(responsableInspeccionDao.nextSequenceValue()));
                        segCabResponsable.setNFlgActivo(BigDecimal.ONE);
                        segCabResponsable.setDFecCreacion(new Date());
                        segCabResponsable.setVUsuCreacion(usuarioSession.getVUsuario());
                        segCabResponsable.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                        responsableInspeccionDao.registrarResponsable(segCabResponsable);
                        if(this.getListaResponsables() == null)
                            this.setListaResponsables(new ArrayList());
                        this.getListaResponsables().add(segCabResponsable);
                        this.setNombreResponsable(null);
                        this.setApellidoResponsable(null);
                        ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                        listasSessionMB.setListaResponsableInspeccion(new Items(responsableInspeccionDao.obtenerListaResponsablesActivos(), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VDescripcion").getItems());
                        JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                    }
                }
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarResponsable(ActionEvent actionEvent){
        try{
            SegCabResponsable segCabResponsable = (SegCabResponsable) actionEvent.getSource();
            if(segCabResponsable.getVNombres() != null && !"".equals(segCabResponsable.getVNombres().trim())){
                if(segCabResponsable.getVApellidos() != null && !"".equals(segCabResponsable.getVApellidos().trim())){
                    SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                    segCabResponsable.setVNombres(segCabResponsable.getVNombres().toUpperCase().trim());
                    segCabResponsable.setVApellidos(segCabResponsable.getVApellidos().toUpperCase().trim());
                    segCabResponsable.setVDescripcion(segCabResponsable.getVNombres()+" "+segCabResponsable.getVApellidos());
                    segCabResponsable.setDFecModificacion(new Date());
                    segCabResponsable.setVUsuModificacion(usuarioSession.getVUsuario());
                    segCabResponsable.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    ResponsableInspeccionDao responsableInspeccionDao = (ResponsableInspeccionDao) ServiceFinder.findBean("ResponsableInspeccionDao");
                    responsableInspeccionDao.registrarResponsable(segCabResponsable);
                    ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                    listasSessionMB.setListaResponsableInspeccion(new Items(responsableInspeccionDao.obtenerListaResponsablesActivos(), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarResponsable(ActionEvent actionEvent){
        try{
            if(actionEvent != null){
                ResponsableInspeccionDao responsableInspeccionDao = (ResponsableInspeccionDao) ServiceFinder.findBean("ResponsableInspeccionDao");
                responsableInspeccionDao.eliminarResponsable(this.getSelectedResponsable());
                this.getListaResponsables().remove(this.getSelectedResponsable());
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaResponsableInspeccion(new Items(responsableInspeccionDao.obtenerListaResponsablesActivos(), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void listarCargos(ActionEvent actionEvent){
        try{
            CargoInspeccionDao cargoInspeccionDao = (CargoInspeccionDao) ServiceFinder.findBean("CargoInspeccionDao");
            this.setListaCargos(cargoInspeccionDao.obtenerListaCargos());
            this.setDescripcionCargo(null);
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarCargo(ActionEvent actionEvent){
        try{
            if(this.descripcionCargo != null && !"".equals(this.descripcionCargo.trim())){
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                CargoInspeccionDao cargoInspeccionDao = (CargoInspeccionDao) ServiceFinder.findBean("CargoInspeccionDao");
                SegCabCargo segCabCargo = new SegCabCargo();
                segCabCargo.setVDescripcion(this.descripcionCargo.toUpperCase().trim());
                
                if(cargoInspeccionDao.buscarCargo(segCabCargo) == null){
                    segCabCargo.setNCodCargo(BigDecimal.valueOf(cargoInspeccionDao.nextSequenceValue()));
                    segCabCargo.setNFlgActivo(BigDecimal.ONE);
                    segCabCargo.setDFecCreacion(new Date());
                    segCabCargo.setVUsuCreacion(usuarioSession.getVUsuario());
                    segCabCargo.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    cargoInspeccionDao.registrarCargo(segCabCargo);
                    if(this.getListaCargos() == null)
                        this.setListaCargos(new ArrayList());
                    this.getListaCargos().add(segCabCargo);
                    this.setDescripcionCargo(null);
                    //this.setCargo(segCabCargo);
                    ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                    listasSessionMB.setListaCargoInspeccion(new Items(cargoInspeccionDao.obtenerListaCargosActivos(), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void editarCargo(ActionEvent actionEvent){
        try{
            SegCabCargo segCabCargo = (SegCabCargo) actionEvent.getSource();
            if(segCabCargo.getVDescripcion() != null && !"".equals(segCabCargo.getVDescripcion().trim())){
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                segCabCargo.setVDescripcion(segCabCargo.getVDescripcion().toUpperCase().trim());
                segCabCargo.setDFecModificacion(new Date());
                segCabCargo.setVUsuModificacion(usuarioSession.getVUsuario());
                segCabCargo.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                CargoInspeccionDao cargoInspeccionDao = (CargoInspeccionDao) ServiceFinder.findBean("CargoInspeccionDao");
                cargoInspeccionDao.registrarCargo(segCabCargo);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void eliminarCargo(ActionEvent actionEvent){
        try{
            if(actionEvent != null){
                CargoInspeccionDao cargoInspeccionDao = (CargoInspeccionDao) ServiceFinder.findBean("CargoInspeccionDao");
                cargoInspeccionDao.eliminarCargo(this.getSelectedCargo());
                this.getListaCargos().remove(this.getSelectedCargo());
                ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                listasSessionMB.setListaCargoInspeccion(new Items(cargoInspeccionDao.obtenerListaCargosActivos(), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarInspeccion(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            InsTelefonicaDao insTelefonicaDao = (InsTelefonicaDao) ServiceFinder.findBean("InsTelefonicaDao");
            SegCabLugar segCabLugar = new SegCabLugar();
            segCabLugar.setNCodLugar(BigDecimal.valueOf(Long.parseLong(this.lugar)));
            SegCabCargo segCabCargo = new SegCabCargo();
            segCabCargo.setNCodCargo(BigDecimal.valueOf(Long.parseLong(this.cargo)));
            SegCabResponsable segCabResponsable = new SegCabResponsable();
            segCabResponsable.setNCodResponsable(BigDecimal.valueOf(Long.parseLong(this.responsable)));
            SegDetInsTelefonica segDetInsTelefonica = new SegDetInsTelefonica();
            segDetInsTelefonica.setNCodInstelefonica(BigDecimal.valueOf(insTelefonicaDao.nextSequenceValue()));
            segDetInsTelefonica.setNCodEmpresa(empresaSession.getNCodEmpresa());
            segDetInsTelefonica.setSegCabLugar(segCabLugar);
            segDetInsTelefonica.setNCodLugar(BigDecimal.valueOf(Long.parseLong(this.lugar)));
            segDetInsTelefonica.setSegCabResponsable(segCabResponsable);
            segDetInsTelefonica.setNCodResponsable(BigDecimal.valueOf(Long.parseLong(this.responsable)));
            segDetInsTelefonica.setSegCabCargo(segCabCargo);
            segDetInsTelefonica.setNCodCargo(BigDecimal.valueOf(Long.parseLong(this.cargo)));
            segDetInsTelefonica.setVTelefono(this.telefono);
            if(!errorValidation(segDetInsTelefonica)){
                if(this.getListaSelectedRowKeys() != null && this.getListaSelectedRowKeys().length > 0){
                    this.setListaSelectedPregunta(new ArrayList<SegDetPregunta>());
                    for(int i=0;i<this.getListaSelectedRowKeys().length;i++){
                        Boolean valor = this.getListaSelectedRowKeys()[i];
                        if(valor){
                            this.getListaSelectedPregunta().add(this.getListaPreguntas().get(i));
                        }
                    }
                }
                if(this.getListaSelectedPregunta() != null && !this.getListaSelectedPregunta().isEmpty()){
                    segDetInsTelefonica.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE"))));
                    segDetInsTelefonica.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetInsTelefonica.setDFecCreacion(new Date());
                    segDetInsTelefonica.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    insTelefonicaDao.registrarInspeccionTelefonica(segDetInsTelefonica);
                    
                    CuestionarioDao cuestionarioDao = (CuestionarioDao) ServiceFinder.findBean("CuestionarioDao");
                    for(int i=0;i<this.getListaSelectedPregunta().size();i++){
                        SegDetPregunta segDetPregunta = this.getListaSelectedPregunta().get(i);
                        SegRelCuestionarioId segRelCuestionarioId = new SegRelCuestionarioId();
                        segRelCuestionarioId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                        segRelCuestionarioId.setNCodInstelefonica(segDetInsTelefonica.getNCodInstelefonica());
                        segRelCuestionarioId.setNCodPregunta(segDetPregunta.getNCodPregunta());
                        SegRelCuestionario segRelCuestionario = new SegRelCuestionario();
                        segRelCuestionario.setId(segRelCuestionarioId);
                        cuestionarioDao.registrarCuestionario(segRelCuestionario);
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }else{
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione al menos una pregunta para realizar la inspección.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }
            }
        }catch(Exception e){
            e.getMessage();
            
        }
    }
    
    public void registrarInspeccionMasiva(ActionEvent actionEvent){
        FacesMessage message = null;
        ResourceBundle bundle;
        try{
            if(this.getListaSelectedRowKeys() != null && this.getListaSelectedRowKeys().length > 0){
                this.setListaSelectedPregunta(new ArrayList<SegDetPregunta>());
                for(int j=0;j<this.getListaSelectedRowKeys().length;j++){
                    Boolean valor = this.getListaSelectedRowKeys()[j];
                    if(valor != null && valor){
                        this.getListaSelectedPregunta().add(this.getListaPreguntas().get(j));
                    }
                }
            }
            
            if(this.getListaSelectedPregunta() != null && this.getListaSelectedPregunta().size() > 0){
                if(this.getListaInspeccion() != null && !this.getListaInspeccion().isEmpty()){
                    bundle = ResourceBundle.getBundle(Parameters.getParameters());
                    SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                    SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                    CuestionarioDao cuestionarioDao = (CuestionarioDao) ServiceFinder.findBean("CuestionarioDao");
                    InsTelefonicaDao insTelefonicaDao = (InsTelefonicaDao) ServiceFinder.findBean("InsTelefonicaDao");
                    LugarInspeccionDao lugarInspeccionDao = (LugarInspeccionDao) ServiceFinder.findBean("LugarInspeccionDao");
                    CargoInspeccionDao cargoInspeccionDao = (CargoInspeccionDao) ServiceFinder.findBean("CargoInspeccionDao");
                    ResponsableInspeccionDao responsableInspeccionDao = (ResponsableInspeccionDao) ServiceFinder.findBean("ResponsableInspeccionDao");
                    for(int i=0;i<this.getListaInspeccion().size();i++){
                        InspeccionTelefonica inspeccion = this.getListaInspeccion().get(i);
                        
                        SegCabLugar segCabLugar = new SegCabLugar();
                        segCabLugar.setVDescripcion(inspeccion.getLugar());
                        if(lugarInspeccionDao.buscarLugar(segCabLugar) == null){
                            segCabLugar.setNCodLugar(BigDecimal.valueOf(lugarInspeccionDao.nextSequenceValue()));
                            segCabLugar.setNFlgActivo(BigDecimal.ONE);
                            segCabLugar.setDFecCreacion(new Date());
                            segCabLugar.setVUsuCreacion(usuarioSession.getVUsuario());
                            segCabLugar.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                            lugarInspeccionDao.registrarLugar(segCabLugar);
                        }
                        
                        SegCabCargo segCabCargo = new SegCabCargo();
                        segCabCargo.setVDescripcion(inspeccion.getCargo());
                        if(cargoInspeccionDao.buscarCargo(segCabCargo) == null){
                            segCabCargo.setNCodCargo(BigDecimal.valueOf(cargoInspeccionDao.nextSequenceValue()));
                            segCabCargo.setNFlgActivo(BigDecimal.ONE);
                            segCabCargo.setDFecCreacion(new Date());
                            segCabCargo.setVUsuCreacion(usuarioSession.getVUsuario());
                            segCabCargo.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                            cargoInspeccionDao.registrarCargo(segCabCargo);
                        }
                        
                        SegCabResponsable segCabResponsable = new SegCabResponsable();
                        segCabResponsable.setVDescripcion(inspeccion.getResponsable());
                        if(responsableInspeccionDao.buscarResponsable(segCabResponsable) == null){
                            segCabResponsable.setNCodResponsable(BigDecimal.valueOf(responsableInspeccionDao.nextSequenceValue()));
                            segCabResponsable.setNFlgActivo(BigDecimal.ONE);
                            segCabResponsable.setDFecCreacion(new Date());
                            segCabResponsable.setVUsuCreacion(usuarioSession.getVUsuario());
                            segCabResponsable.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                            responsableInspeccionDao.registrarResponsable(segCabResponsable);
                        }
                        
                        ListasSessionMB listasSessionMB = (ListasSessionMB)JSFUtils.getSessionAttribute("listasSessionMB");
                        listasSessionMB.setListaLugarInspeccion(new Items(lugarInspeccionDao.obtenerListaLugaresActivos(), Items.FIRST_ITEM_SELECT, "NCodLugar", "VDescripcion").getItems());
                        listasSessionMB.setListaCargoInspeccion(new Items(cargoInspeccionDao.obtenerListaCargosActivos(), Items.FIRST_ITEM_SELECT, "NCodCargo", "VDescripcion").getItems());
                        listasSessionMB.setListaResponsableInspeccion(new Items(responsableInspeccionDao.obtenerListaResponsablesActivos(), Items.FIRST_ITEM_SELECT, "NCodResponsable", "VDescripcion").getItems());
                        JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                        
                        SegDetInsTelefonica segDetInsTelefonica = new SegDetInsTelefonica();
                        segDetInsTelefonica.setNCodInstelefonica(BigDecimal.valueOf(insTelefonicaDao.nextSequenceValue()));
                        segDetInsTelefonica.setNCodEmpresa(empresaSession.getNCodEmpresa());
                        segDetInsTelefonica.setSegCabLugar(segCabLugar);
                        segDetInsTelefonica.setNCodLugar(segCabLugar.getNCodLugar());
                        segDetInsTelefonica.setSegCabResponsable(segCabResponsable);
                        segDetInsTelefonica.setNCodResponsable(segCabResponsable.getNCodResponsable());
                        segDetInsTelefonica.setSegCabCargo(segCabCargo);
                        segDetInsTelefonica.setNCodCargo(segCabCargo.getNCodCargo());
                        segDetInsTelefonica.setVTelefono(inspeccion.getTelefono());
                        segDetInsTelefonica.setNEstado(BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_PENDIENTE"))));
                        segDetInsTelefonica.setVUsuCreacion(usuarioSession.getVUsuario());
                        segDetInsTelefonica.setDFecCreacion(new Date());
                        segDetInsTelefonica.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                        insTelefonicaDao.registrarInspeccionTelefonica(segDetInsTelefonica);
                        
                        for(int j=0;j<this.getListaSelectedPregunta().size();j++){
                            SegDetPregunta segDetPregunta = this.getListaSelectedPregunta().get(j);
                            SegRelCuestionarioId segRelCuestionarioId = new SegRelCuestionarioId();
                            segRelCuestionarioId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                            segRelCuestionarioId.setNCodInstelefonica(segDetInsTelefonica.getNCodInstelefonica());
                            segRelCuestionarioId.setNCodPregunta(segDetPregunta.getNCodPregunta());
                            SegRelCuestionario segRelCuestionario = new SegRelCuestionario();
                            segRelCuestionario.setId(segRelCuestionarioId);
                            cuestionarioDao.registrarCuestionario(segRelCuestionario);
                        }
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione al menos una pregunta para realizar la inspección.");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void descargarPlantilla(ActionEvent actionEvent){
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            java.io.File f = new java.io.File(bundle.getString("templateInspection"));
            Desktop.getDesktop().open(f);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public String downloadTemplate(){
        ResourceBundle bundle;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            JSFUtils.getRequest().setAttribute("filePath", bundle.getString("templatePath"));
            JSFUtils.getRequest().setAttribute("fileName", bundle.getString("inspectionFile"));
        }catch(Exception e){
            e.getMessage();
        }
        return "fileDownload";
    }
    
    public boolean errorValidation(SegDetInsTelefonica segDetInsTelefonica){
        FacesMessage message = null;
        boolean error = false;
        try{
            if(segDetInsTelefonica.getNCodLugar() == null || segDetInsTelefonica.getNCodLugar().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el lugar de la inspección.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(segDetInsTelefonica.getNCodResponsable() == null || segDetInsTelefonica.getNCodResponsable().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione al responsable del lugar de la inspección.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(segDetInsTelefonica.getNCodCargo() == null || segDetInsTelefonica.getNCodCargo().compareTo(BigDecimal.valueOf(-1))==0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Seleccione el cargo del responsable.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }else if(segDetInsTelefonica.getVTelefono() == null || "".equals(segDetInsTelefonica.getVTelefono().trim())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Ingrese el teléfono de contacto.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
        }catch(Exception e){
            e.getMessage();
        }
        return error;
    }
    
    public void uploadFile(FileUploadEvent event){
        FacesMessage message = null;
        try{
            UploadedFile item = event.getFile();
            if(item != null){
                if(!errorFileValidation(item)){
                    if("application/vnd.ms-excel".equals(item.getContentType())){
                        HSSFWorkbook workbook = new HSSFWorkbook(item.getInputstream());
                        HSSFSheet sheet = workbook.getSheetAt(0);
                        if(sheet.getPhysicalNumberOfRows()>1){
                            this.setListaInspeccion(new ArrayList<InspeccionTelefonica>());
                            for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
                                HSSFRow row = sheet.getRow(i);
                                String content = row.getCell(0).getRichStringCellValue().getString();
                                if(!"".equals(content)){
                                    InspeccionTelefonica inspeccion = new InspeccionTelefonica();
                                    if(row.getCell(0).getCellType()==HSSFCell.CELL_TYPE_STRING){
                                        inspeccion.setLugar(row.getCell(0).getRichStringCellValue().getString().toUpperCase());
                                    }else if(row.getCell(0).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                                        row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                                        inspeccion.setLugar(row.getCell(0).getStringCellValue());
                                    }
                                    if(row.getCell(1).getCellType()==HSSFCell.CELL_TYPE_STRING){
                                        inspeccion.setResponsable(row.getCell(1).getRichStringCellValue().getString().toUpperCase());
                                    }else if(row.getCell(1).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                                        row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                                        inspeccion.setResponsable(row.getCell(1).getStringCellValue());
                                    }
                                    if(row.getCell(2).getCellType()==HSSFCell.CELL_TYPE_STRING){
                                        inspeccion.setCargo(row.getCell(2).getRichStringCellValue().getString().toUpperCase());
                                    }else if(row.getCell(2).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                                        row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
                                        inspeccion.setCargo(row.getCell(2).getStringCellValue());
                                    }
                                    if(row.getCell(3).getCellType()==HSSFCell.CELL_TYPE_STRING){
                                        inspeccion.setTelefono(row.getCell(3).getRichStringCellValue().getString().toUpperCase());
                                    }else if(row.getCell(3).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                                        row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
                                        inspeccion.setTelefono(row.getCell(3).getStringCellValue());
                                    }
                                    this.getListaInspeccion().add(inspeccion);
                                }else{
                                    break;
                                }
                            }
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Debe ingresar al menos los datos de una inspección telfónica.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                        }
                    }else{
                        XSSFWorkbook workbook = new XSSFWorkbook(item.getInputstream());
                        XSSFSheet sheet = workbook.getSheetAt(0);
                        this.setListaInspeccion(new ArrayList<InspeccionTelefonica>());
                        for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
                            XSSFRow row = sheet.getRow(i);
                            String content = row.getCell(0).getRichStringCellValue().getString();
                            if(!"".equals(content)){
                                InspeccionTelefonica inspeccion = new InspeccionTelefonica();
                                if(row.getCell(0).getCellType()==XSSFCell.CELL_TYPE_STRING){
                                    inspeccion.setLugar(row.getCell(0).getRichStringCellValue().getString().toUpperCase());
                                }else if(row.getCell(0).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
                                    inspeccion.setLugar(Double.toString(row.getCell(0).getNumericCellValue()).toUpperCase());
                                }
                                if(row.getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
                                    inspeccion.setResponsable(row.getCell(1).getRichStringCellValue().getString().toUpperCase());
                                }else if(row.getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
                                    inspeccion.setResponsable(Double.toString(row.getCell(1).getNumericCellValue()).toUpperCase());
                                }
                                if(row.getCell(2).getCellType()==XSSFCell.CELL_TYPE_STRING){
                                    inspeccion.setCargo(row.getCell(2).getRichStringCellValue().getString().toUpperCase());
                                }else if(row.getCell(2).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
                                    inspeccion.setCargo(Double.toString(row.getCell(2).getNumericCellValue()).toUpperCase());
                                }
                                if(row.getCell(3).getCellType()==XSSFCell.CELL_TYPE_STRING){
                                    inspeccion.setTelefono(row.getCell(3).getRichStringCellValue().getString().toUpperCase());
                                }else if(row.getCell(3).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
                                    inspeccion.setTelefono(Double.toString(row.getCell(3).getNumericCellValue()).toUpperCase());
                                }
                                this.getListaInspeccion().add(inspeccion);

                            }else{
                                break;
                            }                        
                        }
                    }
                }
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorFileValidation(UploadedFile item){
        FacesMessage message = null;
        boolean error = false;
        int count = 0;
        try{
            if("application/vnd.ms-excel".equals(item.getContentType())){
                HSSFWorkbook workbook = new HSSFWorkbook(item.getInputstream());
                HSSFSheet sheet = workbook.getSheetAt(0);
                for(int i=0;i<sheet.getPhysicalNumberOfRows();i++){
                    HSSFRow row = sheet.getRow(i);
                    row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                    row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                    row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
                    row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String value0 = row.getCell(0).getStringCellValue();
                    String value1 = row.getCell(1).getStringCellValue();
                    String value2 = row.getCell(2).getStringCellValue();
                    String value3 = row.getCell(3).getStringCellValue();
                    if(value0!=null && !value0.isEmpty() && value1!=null && !value1.isEmpty()
                        && value2!=null && !value2.isEmpty() && value3!=null && !value3.isEmpty()){
                        count++;
                    }else{
                        if(StringUtils.isBlank(value0) && StringUtils.isBlank(value1) 
                                && StringUtils.isBlank(value2) && StringUtils.isBlank(value3)){
                            break;
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Debe ingresar todos los datos de la inspección telefónica.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                            error = true;
                            return error;
                        }
                    }
                }
            } else {
                XSSFWorkbook workbook = new XSSFWorkbook(item.getInputstream());
                XSSFSheet sheet = workbook.getSheetAt(0);
                for(int i=0;i<sheet.getPhysicalNumberOfRows();i++){
                    XSSFRow row = sheet.getRow(i);
                    row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                    row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                    row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
                    row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String value0 = row.getCell(0).getStringCellValue();
                    String value1 = row.getCell(1).getStringCellValue();
                    String value2 = row.getCell(2).getStringCellValue();
                    String value3 = row.getCell(3).getStringCellValue();
                    if(value0!=null && !value0.isEmpty() && value1!=null && !value1.isEmpty()
                        && value2!=null && !value2.isEmpty() && value3!=null && !value3.isEmpty()){
                        count++;
                    }else{
                        if(StringUtils.isBlank(value0) && StringUtils.isBlank(value1) 
                                && StringUtils.isBlank(value2) && StringUtils.isBlank(value3)){
                            break;
                        }else{
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Debe ingresar todos los datos de la inspección telefónica.");
                            FacesContext.getCurrentInstance().addMessage(null,message);
                            error = true;
                            return error;
                        }
                    }
                }
            }
        }catch(Exception e){
            e.getMessage();
        }
        return error;
    }
    
    public List<SegDetPregunta> completePregunta(String query) {
        List<SegDetPregunta> suggestions = new ArrayList<SegDetPregunta>();
        try {
            for (SegDetPregunta p : getItemsPregunta()) {
                if (p.getVDescripcion().startsWith(query.toUpperCase())) {
                    suggestions.add(p);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return suggestions;
    }
    
    public void save(){
        FacesMessage message = null;
        try{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO.", "Save successful.");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }catch(Exception e){
            e.getMessage();
        }
    }
}
