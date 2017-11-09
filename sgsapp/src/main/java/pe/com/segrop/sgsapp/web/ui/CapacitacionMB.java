/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
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
import pe.com.segrop.sgsapp.dao.CapacitacionDao;
import pe.com.segrop.sgsapp.dao.LugarCapacitacionDao;
import pe.com.segrop.sgsapp.dao.ParticipanteDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetCapacitacion;
import pe.com.segrop.sgsapp.domain.SegDetCapacitacionId;
import pe.com.segrop.sgsapp.domain.SegDetLugarCapacitacion;
import pe.com.segrop.sgsapp.domain.SegDetLugarCapacitacionId;
import pe.com.segrop.sgsapp.domain.SegDetParticipante;
import pe.com.segrop.sgsapp.domain.SegDetParticipanteId;
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
public class CapacitacionMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal searchEmpresa;
    private String searchNombre;
    private BigDecimal searchModalidad;
    private BigDecimal searchTipoCapacitacion;
    private Date searchFechaInicio;
    private Date searchFechaFin;
    private String searchParticipante;
    private List<SelectItem> itemsNombre;
    private BigDecimal empresa;
    private String nombre;
    private String descripcion;
    private BigDecimal codLugar;
    private BigDecimal codModalidad;
    private BigDecimal codTipoCapacitacion;
    private Date fechaHora;
    private String nombreParticipante;
    private String apellidoParticipante;
    private List<SegDetCapacitacion> listaCapacitacion;
    private List<SegDetParticipante> listaParticipante;
    private boolean showLugar;
    private SegDetCapacitacion selectedCapacitacion;
    private SegDetParticipante selectedParticipante;
    private String action;
    private boolean cargaIndividual;
    private boolean cargaMasiva;
    private String tipoCarga;
    private String descripcionLugar;
    private SegDetLugarCapacitacion selectedLugar;
    private List<SegDetLugarCapacitacion> listaLugares;
    private UploadedFile file;
    private Boolean flag;

    /**
     * Creates a new instance of CapacitacionMB
     */
    public CapacitacionMB() {
        selectedCapacitacion = new SegDetCapacitacion();
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
     * @return the searchModalidad
     */
    public BigDecimal getSearchModalidad() {
        return searchModalidad;
    }

    /**
     * @param searchModalidad the searchModalidad to set
     */
    public void setSearchModalidad(BigDecimal searchModalidad) {
        this.searchModalidad = searchModalidad;
    }

    /**
     * @return the searchTipoCapacitacion
     */
    public BigDecimal getSearchTipoCapacitacion() {
        return searchTipoCapacitacion;
    }

    /**
     * @param searchTipoCapacitacion the searchTipoCapacitacion to set
     */
    public void setSearchTipoCapacitacion(BigDecimal searchTipoCapacitacion) {
        this.searchTipoCapacitacion = searchTipoCapacitacion;
    }

    /**
     * @return the searchFechaInicio
     */
    public Date getSearchFechaInicio() {
        return searchFechaInicio;
    }

    /**
     * @param searchFechaInicio the searchFechaInicio to set
     */
    public void setSearchFechaInicio(Date searchFechaInicio) {
        this.searchFechaInicio = searchFechaInicio;
    }

    /**
     * @return the searchFechaFin
     */
    public Date getSearchFechaFin() {
        return searchFechaFin;
    }

    /**
     * @param searchFechaFin the searchFechaFin to set
     */
    public void setSearchFechaFin(Date searchFechaFin) {
        this.searchFechaFin = searchFechaFin;
    }

    public String getSearchParticipante() {
        return searchParticipante;
    }

    public void setSearchParticipante(String searchParticipante) {
        this.searchParticipante = searchParticipante;
    }

    /**
     * @return the itemsNombre
     */
    public List<SelectItem> getItemsNombre() {
        if (this.itemsNombre == null) {
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            CapacitacionDao capacitacionDao = (CapacitacionDao) ServiceFinder.findBean("CapacitacionDao");
            itemsNombre = new Items(capacitacionDao.obtenerListaCapacitacionesByEmpresa(segCabEmpresa), Items.FIRST_ITEM_SELECT, "NCodPregunta", "VDescripcion").getItems();
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCodLugar() {
        return codLugar;
    }

    public void setCodLugar(BigDecimal codLugar) {
        this.codLugar = codLugar;
    }

    /**
     * @return the codModalidad
     */
    public BigDecimal getCodModalidad() {
        return codModalidad;
    }

    /**
     * @param codModalidad the codModalidad to set
     */
    public void setCodModalidad(BigDecimal codModalidad) {
        this.codModalidad = codModalidad;
    }

    /**
     * @return the codTipoCapacitacion
     */
    public BigDecimal getCodTipoCapacitacion() {
        return codTipoCapacitacion;
    }

    /**
     * @param codTipoCapacitacion the codTipoCapacitacion to set
     */
    public void setCodTipoCapacitacion(BigDecimal codTipoCapacitacion) {
        this.codTipoCapacitacion = codTipoCapacitacion;
    }

    /**
     * @return the fechaHora
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }

    public String getApellidoParticipante() {
        return apellidoParticipante;
    }

    public void setApellidoParticipante(String apellidoParticipante) {
        this.apellidoParticipante = apellidoParticipante;
    }

    /**
     * @return the listaCapacitacion
     */
    public List<SegDetCapacitacion> getListaCapacitacion() {
        return listaCapacitacion;
    }

    /**
     * @param listaCapacitacion the listaCapacitacion to set
     */
    public void setListaCapacitacion(List<SegDetCapacitacion> listaCapacitacion) {
        this.listaCapacitacion = listaCapacitacion;
    }

    /**
     * @return the listaParticipante
     */
    public List<SegDetParticipante> getListaParticipante() {
        return listaParticipante;
    }

    /**
     * @param listaParticipante the listaParticipante to set
     */
    public void setListaParticipante(List<SegDetParticipante> listaParticipante) {
        this.listaParticipante = listaParticipante;
    }

    /**
     * @return the showLugar
     */
    public boolean isShowLugar() {
        return showLugar;
    }

    /**
     * @param showLugar the showLugar to set
     */
    public void setShowLugar(boolean showLugar) {
        this.showLugar = showLugar;
    }

    /**
     * @return the selectedCapacitacion
     */
    public SegDetCapacitacion getSelectedCapacitacion() {
        return selectedCapacitacion;
    }

    /**
     * @param selectedCapacitacion the selectedCapacitacion to set
     */
    public void setSelectedCapacitacion(SegDetCapacitacion selectedCapacitacion) {
        this.selectedCapacitacion = selectedCapacitacion;
    }

    /**
     * @return the selectedParticipante
     */
    public SegDetParticipante getSelectedParticipante() {
        return selectedParticipante;
    }

    /**
     * @param selectedParticipante the selectedParticipante to set
     */
    public void setSelectedParticipante(SegDetParticipante selectedParticipante) {
        this.selectedParticipante = selectedParticipante;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean getCargaIndividual() {
        return cargaIndividual;
    }

    public void setCargaIndividual(boolean cargaIndividual) {
        this.cargaIndividual = cargaIndividual;
    }

    public boolean getCargaMasiva() {
        return cargaMasiva;
    }

    public void setCargaMasiva(boolean cargaMasiva) {
        this.cargaMasiva = cargaMasiva;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public String getDescripcionLugar() {
        return descripcionLugar;
    }

    public void setDescripcionLugar(String descripcionLugar) {
        this.descripcionLugar = descripcionLugar;
    }

    public SegDetLugarCapacitacion getSelectedLugar() {
        return selectedLugar;
    }

    public void setSelectedLugar(SegDetLugarCapacitacion selectedLugar) {
        this.selectedLugar = selectedLugar;
    }

    public List<SegDetLugarCapacitacion> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<SegDetLugarCapacitacion> listaLugares) {
        this.listaLugares = listaLugares;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
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
            this.setSelectedCapacitacion(new SegDetCapacitacion());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void handleChangeTipoCarga(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                String selectedTipoCarga = (String) ((SelectOneRadio) event.getSource()).getValue();
                if ("1".equals(selectedTipoCarga)) {
                    this.setCargaIndividual(true);
                    this.setCargaMasiva(false);
                } else {
                    this.setCargaIndividual(false);
                    this.setCargaMasiva(true);
                }
                this.setTipoCarga(selectedTipoCarga);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void buscarCapacitaciones(ActionEvent event) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        try {bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            SegDetCapacitacionId segDetCapacitacionId = new SegDetCapacitacionId();
            if (!rucSegrop.equals(segCabEmpresa.getVRuc())) {
                segDetCapacitacionId.setNCodEmpresa(segCabEmpresa.getNCodEmpresa());
            } else {
                segDetCapacitacionId.setNCodEmpresa(this.getSearchEmpresa());
            }
            SegDetCapacitacion segDetCapacitacion = new SegDetCapacitacion();
            segDetCapacitacion.setId(segDetCapacitacionId);
            segDetCapacitacion.setNCodEmpresa(segDetCapacitacionId.getNCodEmpresa());
            segDetCapacitacion.setNModalidad(this.getSearchModalidad() != null ? this.getSearchModalidad() : null);
            segDetCapacitacion.setNTipoCapacitacion(this.getSearchTipoCapacitacion() != null ? this.getSearchTipoCapacitacion() : null);
            segDetCapacitacion.setVNombre(this.getSearchNombre() != null ? this.getSearchNombre().toUpperCase().trim() : null);
            segDetCapacitacion.setDFecInicio(this.getSearchFechaInicio() != null ? this.getSearchFechaInicio() : null);
            segDetCapacitacion.setDFecFin(this.getSearchFechaFin() != null ? this.getSearchFechaFin() : null);
            segDetCapacitacion.setVDescripcion(this.getSearchParticipante() != null ? this.getSearchParticipante().toUpperCase().trim() : null);
            CapacitacionDao capacitacionDao = (CapacitacionDao) ServiceFinder.findBean("CapacitacionDao");
            setListaCapacitacion(capacitacionDao.buscarCapacitaciones(segDetCapacitacion));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toRegistrar(ActionEvent event) {
        try {
            this.setNombre(null);
            this.setDescripcion(null);
            this.setCodModalidad(BigDecimal.valueOf(-1));
            this.setCodTipoCapacitacion(BigDecimal.valueOf(-1));
            this.setShowLugar(false);
            this.setCodLugar(null);
            this.setFechaHora(null);
            this.setNombreParticipante(null);
            this.setApellidoParticipante(null);
            this.setListaParticipante(new ArrayList<SegDetParticipante>());
            this.setTipoCarga("1");
            this.setCargaIndividual(true);
            this.setCargaMasiva(false);
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

    public void agregarParticipante(ActionEvent event) {
        FacesMessage message = null;
        try {
            if (this.nombreParticipante != null && !"".equals(this.nombreParticipante.trim())
                    && this.apellidoParticipante != null && !"".equals(this.apellidoParticipante.trim())) {
                SegDetParticipante segDetParticipante = new SegDetParticipante();
                segDetParticipante.setVNombres(this.nombreParticipante.trim().toUpperCase().trim());
                segDetParticipante.setVApellidos(this.apellidoParticipante.trim().toUpperCase().trim());
                segDetParticipante.setVNombreCompleto(segDetParticipante.getVNombres() + " " + segDetParticipante.getVApellidos());
                if (this.getListaParticipante() != null) {
                    if (!this.listHas(this.getListaParticipante(), segDetParticipante)) {
                        this.getListaParticipante().add(segDetParticipante);
                    } else {
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El participante ya ha sido agregado.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                } else {
                    this.setListaParticipante(new ArrayList<SegDetParticipante>());
                    this.getListaParticipante().add(segDetParticipante);
                }
                this.setNombreParticipante(null);
                this.setApellidoParticipante(null);
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre completo del participante a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void registrarCapacitacion(ActionEvent event) {
        ResourceBundle bundle = null;
        String rucSegrop = null;
        FacesMessage message = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            if (!rucSegrop.equals(empresaSession.getVRuc())) {
                this.setEmpresa(empresaSession.getNCodEmpresa());
            }
            SegDetCapacitacion segDetCapacitacion = new SegDetCapacitacion();
            segDetCapacitacion.setVNombre(this.nombre.toUpperCase().trim());
            segDetCapacitacion.setVDescripcion(this.descripcion.toUpperCase().trim());
            segDetCapacitacion.setNTipoCapacitacion(this.codTipoCapacitacion);
            segDetCapacitacion.setNModalidad(this.codModalidad);
            segDetCapacitacion.setNLugar(this.codLugar);
            segDetCapacitacion.setDFechaHora(this.fechaHora);
            if (!errorValidation(segDetCapacitacion)) {
                if (this.getListaParticipante() != null && !this.getListaParticipante().isEmpty()) {
                    CapacitacionDao capacitacionDao = (CapacitacionDao) ServiceFinder.findBean("CapacitacionDao");
                    ParticipanteDao participanteDao = (ParticipanteDao) ServiceFinder.findBean("ParticipanteDao");
                    SegDetCapacitacionId segDetCapacitacionId = new SegDetCapacitacionId();
                    segDetCapacitacionId.setNCodCapacitacion(BigDecimal.valueOf(capacitacionDao.nextSequenceValue()));
                    segDetCapacitacionId.setNCodEmpresa(this.getEmpresa() != null ? this.getEmpresa() : null);
                    segDetCapacitacion.setId(segDetCapacitacionId);
                    segDetCapacitacion.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetCapacitacion.setDFecCreacion(new Date());
                    segDetCapacitacion.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    capacitacionDao.registrarCapacitacion(segDetCapacitacion);
                    for (int i = 0; i < this.getListaParticipante().size(); i++) {
                        SegDetParticipante segDetParticipante = this.getListaParticipante().get(i);
                        segDetParticipante.setId(new SegDetParticipanteId());
                        segDetParticipante.getId().setNCodEmpresa(empresaSession.getNCodEmpresa());
                        segDetParticipante.getId().setNCodCapacitacion(segDetCapacitacionId.getNCodCapacitacion());
                        segDetParticipante.getId().setNCodParticipante(BigDecimal.valueOf(participanteDao.nextSequenceValue()));
                        segDetParticipante.setSegDetCapacitacion(segDetCapacitacion);
                        segDetParticipante.setVUsuCreacion(usuarioSession.getVUsuario());
                        segDetParticipante.setDFecCreacion(new Date());
                        segDetParticipante.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                        participanteDao.registrarParticipante(segDetParticipante);
                    }
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe ingresar al menos un participante.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toEdit(ActionEvent event) {
        try {
            String rowkey = JSFUtils.getRequestParameter("rowkey");
            SegDetCapacitacion segDetCapacitacion = this.getListaCapacitacion().get(Integer.parseInt(rowkey));
            ParticipanteDao participanteDao = (ParticipanteDao) ServiceFinder.findBean("ParticipanteDao");
            this.setSelectedCapacitacion(segDetCapacitacion);
            this.setListaParticipante(participanteDao.obtenerListaParticipantesByCapacitacion(segDetCapacitacion));
            this.setTipoCarga("1");
            this.setCargaIndividual(true);
            this.setCargaMasiva(false);
        } catch (NumberFormatException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editCapacitacion(ActionEvent event) {
        ResourceBundle bundle;
        String rucSegrop = null;
        FacesMessage message = null;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            rucSegrop = bundle.getString("rucSegrop");
            SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            if (!rucSegrop.equals(empresaSession.getVRuc())) {
                this.getSelectedCapacitacion().getId().setNCodEmpresa(empresaSession.getNCodEmpresa());
            }
            this.getSelectedCapacitacion().setVNombre(this.getSelectedCapacitacion().getVNombre().toUpperCase().trim());
            this.getSelectedCapacitacion().setVDescripcion(this.getSelectedCapacitacion().getVDescripcion().toUpperCase().trim());
            this.getSelectedCapacitacion().setNTipoCapacitacion(this.getSelectedCapacitacion().getNTipoCapacitacion());
            this.getSelectedCapacitacion().setNModalidad(this.getSelectedCapacitacion().getNModalidad());
            this.getSelectedCapacitacion().setNLugar(this.getSelectedCapacitacion().getNLugar());
            this.getSelectedCapacitacion().setDFechaHora(this.getSelectedCapacitacion().getDFechaHora());
            if (!errorValidation(this.getSelectedCapacitacion())) {
                if (this.getListaParticipante() != null && !this.getListaParticipante().isEmpty()) {
                    CapacitacionDao capacitacionDao = (CapacitacionDao) ServiceFinder.findBean("CapacitacionDao");
                    ParticipanteDao participanteDao = (ParticipanteDao) ServiceFinder.findBean("ParticipanteDao");
                    this.getSelectedCapacitacion().setVUsuModificacion(usuarioSession.getVUsuario());
                    this.getSelectedCapacitacion().setDFecModificacion(new Date());
                    this.getSelectedCapacitacion().setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    capacitacionDao.registrarCapacitacion(this.getSelectedCapacitacion());
                    for (int i = 0; i < this.getListaParticipante().size(); i++) {
                        SegDetParticipante segDetParticipante = this.getListaParticipante().get(i);
                        if (segDetParticipante.getDFecCreacion() == null) {
                            segDetParticipante.setId(new SegDetParticipanteId());
                            segDetParticipante.getId().setNCodEmpresa(empresaSession.getNCodEmpresa());
                            segDetParticipante.getId().setNCodCapacitacion(this.getSelectedCapacitacion().getId().getNCodCapacitacion());
                            segDetParticipante.getId().setNCodParticipante(BigDecimal.valueOf(participanteDao.nextSequenceValue()));
                            segDetParticipante.setSegDetCapacitacion(this.getSelectedCapacitacion());
                            segDetParticipante.setVUsuCreacion(usuarioSession.getVUsuario());
                            segDetParticipante.setDFecCreacion(new Date());
                            segDetParticipante.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                        }
                        participanteDao.registrarParticipante(segDetParticipante);
                    }
                    RequestContext.getCurrentInstance().execute("PF('editDlg').hide();");
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe ingresar al menos un participante.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void registrarParticipante(ActionEvent event) {
        ResourceBundle bundle;
        String rucSegrop = null;
        FacesMessage message = null;
        try {
            if (this.nombreParticipante != null && !"".equals(this.nombreParticipante.trim())
                    && this.apellidoParticipante != null && !"".equals(this.apellidoParticipante.trim())) {
                bundle = ResourceBundle.getBundle(Parameters.getParameters());
                rucSegrop = bundle.getString("rucSegrop");
                SegCabUsuario usuarioSession = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
                SegCabEmpresa empresaSession = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
                if (!rucSegrop.equals(empresaSession.getVRuc())) {
                    this.setEmpresa(empresaSession.getNCodEmpresa());
                }
                ParticipanteDao participanteDao = (ParticipanteDao) ServiceFinder.findBean("ParticipanteDao");
                SegDetParticipanteId segDetParticipanteId = new SegDetParticipanteId();
                segDetParticipanteId.setNCodParticipante(BigDecimal.valueOf(participanteDao.nextSequenceValue()));
                segDetParticipanteId.setNCodCapacitacion(this.getSelectedCapacitacion().getId().getNCodCapacitacion());
                segDetParticipanteId.setNCodEmpresa(this.getEmpresa() != null ? this.getEmpresa() : null);
                SegDetParticipante segDetParticipante = new SegDetParticipante();
                segDetParticipante.setId(segDetParticipanteId);
                segDetParticipante.setVNombres(this.nombreParticipante.trim().toUpperCase());
                segDetParticipante.setVApellidos(this.apellidoParticipante.trim().toUpperCase());
                segDetParticipante.setVNombreCompleto(segDetParticipante.getVNombres() + " " + segDetParticipante.getVApellidos());
                segDetParticipante.setVUsuCreacion(usuarioSession.getVUsuario());
                segDetParticipante.setDFecCreacion(new Date());
                segDetParticipante.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                if (participanteDao.obtenerParticipanteByCapacitacion(segDetParticipante) == null) {
                    participanteDao.registrarParticipante(segDetParticipante);
                    this.getListaParticipante().add(segDetParticipante);
                    this.setNombreParticipante(null);
                    this.setApellidoParticipante(null);
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre completo del participante a registrar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El participante ya se encuentra registrado en esta capacitación.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toEditarParticipante(ActionEvent actionEvent) {
        try {
            if (actionEvent != null) {
                String rowkey = JSFUtils.getRequestParameter("rowkey");
                SegDetParticipante segDetParticipante = this.getListaParticipante().get(Integer.parseInt(rowkey));
                this.setSelectedParticipante(segDetParticipante);
                this.setNombreParticipante(segDetParticipante.getVNombres());
                this.setApellidoParticipante(segDetParticipante.getVApellidos());
            }
        } catch (NumberFormatException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editarParticipante(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegCabUsuario usuario = (SegCabUsuario) JSFUtils.getSessionAttribute("usuario");
            SegDetParticipante segDetParticipante = this.getSelectedParticipante();
            if (this.getNombreParticipante() != null && !"".equals(this.getNombreParticipante().trim())
                    && this.getApellidoParticipante() != null && !"".equals(this.getApellidoParticipante().trim())) {
                ParticipanteDao participanteDao = (ParticipanteDao) ServiceFinder.findBean("ParticipanteDao");
                SegDetParticipanteId searchId = new SegDetParticipanteId();
                searchId.setNCodEmpresa(this.getSelectedCapacitacion().getId().getNCodEmpresa());
                searchId.setNCodCapacitacion(this.getSelectedCapacitacion().getId().getNCodCapacitacion());
                SegDetParticipante search = new SegDetParticipante();
                search.setId(searchId);
                search.setVNombres(this.getNombreParticipante().trim().toUpperCase());
                search.setVApellidos(this.getApellidoParticipante().trim().toUpperCase());
                if (participanteDao.obtenerParticipanteByCapacitacion(search) != null) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El participante ingresado ya se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    segDetParticipante.setVNombres(this.getNombreParticipante().trim().toUpperCase());
                    segDetParticipante.setVApellidos(this.getApellidoParticipante().trim().toUpperCase());
                    segDetParticipante.setVNombreCompleto(segDetParticipante.getVNombres() + " " + segDetParticipante.getVApellidos());
                    segDetParticipante.setDFecModificacion(new Date());
                    segDetParticipante.setVUsuModificacion(usuario.getVUsuario());
                    segDetParticipante.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                    participanteDao.registrarParticipante(segDetParticipante);
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre completo del participante a editar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void eliminarParticipante(ActionEvent event) {
        try {
            ParticipanteDao participanteDao = (ParticipanteDao) ServiceFinder.findBean("ParticipanteDao");
            participanteDao.eliminarParticipante(this.getSelectedParticipante());
            this.getListaParticipante().remove(this.getSelectedParticipante());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void uploadFile(FileUploadEvent event) {
        FacesMessage message = null;
        try {
            if (event != null) {
                this.setFile(event.getFile());
                if (this.getFile() != null) {
                    if (!errorFileValidation(this.getFile())) {
                        if ("application/vnd.ms-excel".equals(this.getFile().getContentType())) {
                            HSSFWorkbook workbook = new HSSFWorkbook(this.getFile().getInputstream());
                            HSSFSheet sheet = workbook.getSheetAt(0);
                            if (sheet.getPhysicalNumberOfRows() > 1) {
                                if (this.getListaParticipante() == null) {
                                    this.setListaParticipante(new ArrayList<SegDetParticipante>());
                                }
                                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                                    HSSFRow row = sheet.getRow(i);
                                    String content = row.getCell(0).getRichStringCellValue().getString();
                                    if (!"".equals(content)) {
                                        SegDetParticipante segDetParticipante = new SegDetParticipante();
                                        if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                            segDetParticipante.setVNombres(row.getCell(0).getRichStringCellValue().getString().toUpperCase().trim());
                                        } else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                                            segDetParticipante.setVNombres(row.getCell(0).getStringCellValue());
                                        }
                                        if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                            segDetParticipante.setVApellidos(row.getCell(1).getRichStringCellValue().getString().toUpperCase().trim());
                                        } else if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                            row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                                            segDetParticipante.setVApellidos(row.getCell(1).getStringCellValue());
                                        }
                                        segDetParticipante.setVNombreCompleto(segDetParticipante.getVNombres() + " " + segDetParticipante.getVApellidos());
                                        if (!this.listHas(this.getListaParticipante(), segDetParticipante)) {
                                            this.getListaParticipante().add(segDetParticipante);
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            } else {
                                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe ingresar al menos un(01) participante.");
                                FacesContext.getCurrentInstance().addMessage(null, message);
                            }
                        } else {
                            XSSFWorkbook workbook = new XSSFWorkbook(this.getFile().getInputstream());
                            XSSFSheet sheet = workbook.getSheetAt(0);
                            if (this.getListaParticipante() == null) {
                                this.setListaParticipante(new ArrayList<SegDetParticipante>());
                            }
                            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                                XSSFRow row = sheet.getRow(i);
                                String content = row.getCell(0).getRichStringCellValue().getString();
                                if (!"".equals(content)) {
                                    SegDetParticipante segDetParticipante = new SegDetParticipante();
                                    if (row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_STRING) {
                                        segDetParticipante.setVNombres(row.getCell(0).getRichStringCellValue().getString().toUpperCase().trim());
                                    } else if (row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                                        row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                                        segDetParticipante.setVNombres(row.getCell(0).getStringCellValue());
                                    }
                                    if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_STRING) {
                                        segDetParticipante.setVApellidos(row.getCell(1).getRichStringCellValue().getString().toUpperCase().trim());
                                    } else if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                                        row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                                        segDetParticipante.setVApellidos(row.getCell(1).getStringCellValue());
                                    }
                                    segDetParticipante.setVNombreCompleto(segDetParticipante.getVNombres() + " " + segDetParticipante.getVApellidos());
                                    if (!this.listHas(this.getListaParticipante(), segDetParticipante)) {
                                        this.getListaParticipante().add(segDetParticipante);
                                    }
                                } else {
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void listarLugares(ActionEvent actionEvent) {
        try {
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            LugarCapacitacionDao lugarCapacitacionDao = (LugarCapacitacionDao) ServiceFinder.findBean("LugarCapacitacionDao");
            this.setListaLugares(lugarCapacitacionDao.obtenerListaLugaresCapacitacionByEmpresa(segCabEmpresa));
            this.setSelectedLugar(new SegDetLugarCapacitacion());
            this.setDescripcionLugar(null);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void registrarLugar(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            if (this.descripcionLugar != null && !"".equals(this.descripcionLugar.trim())) {
                ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario) JSFUtils.getSessionAttribute("usuario");
                SegCabEmpresa empresaSession = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
                LugarCapacitacionDao lugarCapacitacionDao = (LugarCapacitacionDao) ServiceFinder.findBean("LugarCapacitacionDao");
                SegDetLugarCapacitacionId segDetLugarCapacitacionId = new SegDetLugarCapacitacionId();
                segDetLugarCapacitacionId.setNCodEmpresa(empresaSession.getNCodEmpresa());
                SegDetLugarCapacitacion segDetLugarCapacitacion = new SegDetLugarCapacitacion();
                segDetLugarCapacitacion.setVDescripcion(this.descripcionLugar.toUpperCase().trim());

                if (lugarCapacitacionDao.buscarLugarCapacitacionByEmpresa(segDetLugarCapacitacion) == null) {
                    segDetLugarCapacitacionId.setNCodLugarCapacitacion(BigDecimal.valueOf(lugarCapacitacionDao.nextSequenceValue()));
                    segDetLugarCapacitacion.setId(segDetLugarCapacitacionId);
                    segDetLugarCapacitacion.setNCodEmpresa(segDetLugarCapacitacionId.getNCodEmpresa());
                    segDetLugarCapacitacion.setNCodLugarCapacitacion(segDetLugarCapacitacionId.getNCodLugarCapacitacion());
                    segDetLugarCapacitacion.setDFecCreacion(new Date());
                    segDetLugarCapacitacion.setVUsuCreacion(usuarioSession.getVUsuario());
                    segDetLugarCapacitacion.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                    lugarCapacitacionDao.registrarLugarCapacitacion(segDetLugarCapacitacion);
                    if (this.getListaLugares() == null) {
                        this.setListaLugares(new ArrayList());
                    }
                    this.getListaLugares().add(segDetLugarCapacitacion);
                    this.setDescripcionLugar(null);

                    listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                    listasSessionMB.setListaLugarCapacitacion(new Items(lugarCapacitacionDao.obtenerListaLugaresCapacitacionByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugarCapacitacion", "VDescripcion").getItems());
                    JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción del lugar de la capacitación.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editarLugar(ActionEvent actionEvent) {
        FacesMessage message = null;
        try {
            SegDetLugarCapacitacion segDetLugarCapacitacion = (SegDetLugarCapacitacion) actionEvent.getSource();
            if (segDetLugarCapacitacion.getVDescripcion() != null && !"".equals(segDetLugarCapacitacion.getVDescripcion().trim())) {
                ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
                SegCabUsuario usuarioSession = (SegCabUsuario) JSFUtils.getSessionAttribute("usuario");
                segDetLugarCapacitacion.setVDescripcion(segDetLugarCapacitacion.getVDescripcion().toUpperCase().trim());
                segDetLugarCapacitacion.setDFecModificacion(new Date());
                segDetLugarCapacitacion.setVUsuModificacion(usuarioSession.getVUsuario());
                segDetLugarCapacitacion.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                LugarCapacitacionDao lugarCapacitacionDao = (LugarCapacitacionDao) ServiceFinder.findBean("LugarCapacitacionDao");
                lugarCapacitacionDao.registrarLugarCapacitacion(segDetLugarCapacitacion);
                listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
                SegCabEmpresa empresaSession = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
                listasSessionMB.setListaLugarCapacitacion(new Items(lugarCapacitacionDao.obtenerListaLugaresCapacitacionByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugarCapacitacion", "VDescripcion").getItems());
                JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción del lugar de la capacitación.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void eliminarLugar(ActionEvent actionEvent) {
        try {
            LugarCapacitacionDao lugarCapacitacionDao = (LugarCapacitacionDao) ServiceFinder.findBean("LugarCapacitacionDao");
            lugarCapacitacionDao.eliminarLugarCapacitacion(this.getSelectedLugar());
            this.getListaLugares().remove(this.getSelectedLugar());

            ListasSessionMB listasSessionMB = (ListasSessionMB) JSFUtils.getSessionAttribute("listasSessionMB");
            listasSessionMB = listasSessionMB != null ? listasSessionMB : new ListasSessionMB();
            SegCabEmpresa empresaSession = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            listasSessionMB.setListaLugarCapacitacion(new Items(lugarCapacitacionDao.obtenerListaLugaresCapacitacionByEmpresa(empresaSession), Items.FIRST_ITEM_SELECT, "NCodLugarCapacitacion", "VDescripcion").getItems());
            JSFUtils.getSession().setAttribute("listasSessionMB", listasSessionMB);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void descargarPlantilla(ActionEvent actionEvent) {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            File f = new File(bundle.getString("templateTraining"));
            Desktop.getDesktop().open(f);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public String downloadTemplate() {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            JSFUtils.getRequest().setAttribute("filePath", bundle.getString("templatePath"));
            JSFUtils.getRequest().setAttribute("fileName", bundle.getString("trainingFile"));
        } catch (Exception e) {
            e.getMessage();
        }
        return "fileDownload";
    }

    public boolean errorFileValidation(UploadedFile file) {
        FacesMessage message = null;
        boolean error = false;
        int count = 0;
        try {
            if ("application/vnd.ms-excel".equals(file.getContentType())) {
                HSSFWorkbook workbook = new HSSFWorkbook(file.getInputstream());
                HSSFSheet sheet = workbook.getSheetAt(0);
                for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                    HSSFRow row = sheet.getRow(i);
                    row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                    row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String value0 = row.getCell(0).getStringCellValue();
                    String value1 = row.getCell(1).getStringCellValue();
                    if (value0 != null && !value0.isEmpty() && value1 != null && !value1.isEmpty()) {
                        count++;
                    } else {
                        if (value0.isEmpty() && value1.isEmpty()) {
                            break;
                        } else {
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe ingresar todos los datos de los participantes.");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                            error = true;
                            return error;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return error;
    }

    public boolean errorValidation(SegDetCapacitacion segDetCapacitacion) {
        FacesMessage message = null;
        boolean error = false;
        try {
            if (segDetCapacitacion.getVNombre() == null || "".equals(segDetCapacitacion.getVNombre().trim())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre de la capacitación.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (segDetCapacitacion.getVDescripcion() == null || "".equals(segDetCapacitacion.getVDescripcion().trim())) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción de la capacitación.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (segDetCapacitacion.getNTipoCapacitacion() == null || segDetCapacitacion.getNTipoCapacitacion().compareTo(BigDecimal.valueOf(-1)) == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de capacitación.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (segDetCapacitacion.getNModalidad() == null || segDetCapacitacion.getNModalidad().compareTo(BigDecimal.valueOf(-1)) == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la modalidad de capacitación.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (segDetCapacitacion.getNLugar() == null || segDetCapacitacion.getNLugar().compareTo(BigDecimal.valueOf(-1)) == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el lugar de la capacitación.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (segDetCapacitacion.getDFechaHora() == null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la fecha y hora de la capacitación.");
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

    public boolean listHas(List<SegDetParticipante> lista, SegDetParticipante participante) {
        boolean encontrado = false;
        try {
            for (SegDetParticipante lista1 : lista) {
                SegDetParticipante nodo = (SegDetParticipante) lista1;
                if (nodo.getVNombres().equals(participante.getVNombres())
                        && nodo.getVApellidos().equals(participante.getVApellidos())) {
                    encontrado = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return encontrado;
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
}
