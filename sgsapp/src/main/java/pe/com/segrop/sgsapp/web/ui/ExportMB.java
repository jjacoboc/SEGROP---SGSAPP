/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import pe.com.segrop.sgsapp.dao.CriteriaDao;
import pe.com.segrop.sgsapp.dao.ExportDao;
import pe.com.segrop.sgsapp.dao.ExportDetalleDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabEntidad;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetAtributo;
import pe.com.segrop.sgsapp.domain.SegDetCriteria;
import pe.com.segrop.sgsapp.domain.SegDetCriteriaId;
import pe.com.segrop.sgsapp.domain.SegDetExport;
import pe.com.segrop.sgsapp.domain.SegDetExportId;
import pe.com.segrop.sgsapp.domain.SegDetExportdetalle;
import pe.com.segrop.sgsapp.domain.SegDetExportdetalleId;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.web.common.Criteria;
import pe.com.segrop.sgsapp.web.common.Data;
import pe.com.segrop.sgsapp.web.common.GeneralBean;
import pe.com.segrop.sgsapp.web.common.Items;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */

public class ExportMB implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private SegCabEntidad selectedEntidad;
    private SegDetAtributo selectedAtributo;
    private SegDetExport selectedExport;
    private List<SegDetAtributo> selectedAtributoList;
    private List<SegCabEntidad> listaEntidades;
    private List<SegDetAtributo> listaAtributos;
    private List<SegDetAtributo> listaPreviewAtributos;
    private List<SegDetExport> listaExport;
    private List<String> columns;
    private List<Data> model;
    private List<Criteria> listaCriteria;
    private List<SelectItem> listSelectItems;
    private int size;
    private String nombre;
    private String descripcion;
    private String action;

    /** Creates a new instance of ExportMB */
    public ExportMB() {
        listaCriteria = new ArrayList<Criteria>();
    }

    public SegCabEntidad getSelectedEntidad() {
        return selectedEntidad;
    }

    public void setSelectedEntidad(SegCabEntidad selectedEntidad) {
        this.selectedEntidad = selectedEntidad;
    }

    public SegDetAtributo getSelectedAtributo() {
        return selectedAtributo;
    }

    public void setSelectedAtributo(SegDetAtributo selectedAtributo) {
        this.selectedAtributo = selectedAtributo;
    }

    public SegDetExport getSelectedExport() {
        return selectedExport;
    }

    public void setSelectedExport(SegDetExport selectedExport) {
        this.selectedExport = selectedExport;
    }

    public List<SegDetAtributo> getSelectedAtributoList() {
        return selectedAtributoList;
    }

    public void setSelectedAtributoList(List<SegDetAtributo> selectedAtributoList) {
        this.selectedAtributoList = selectedAtributoList;
    }

    public List<SegCabEntidad> getListaEntidades() {
        if(listaEntidades ==null){
            ExportDao exportDao = (ExportDao) ServiceFinder.findBean("ExportDao");
            this.setListaEntidades(exportDao.obtenerListaEntidades());
        }
        return listaEntidades;
    }

    public void setListaEntidades(List<SegCabEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public List<SegDetAtributo> getListaAtributos() {
        return listaAtributos;
    }

    public void setListaAtributos(List<SegDetAtributo> listaAtributos) {
        this.listaAtributos = listaAtributos;
    }

    public List<SegDetAtributo> getListaPreviewAtributos() {
        return listaPreviewAtributos;
    }

    public void setListaPreviewAtributos(List<SegDetAtributo> listaPreviewAtributos) {
        this.listaPreviewAtributos = listaPreviewAtributos;
    }

    public List<SegDetExport> getListaExport() {
        return listaExport;
    }

    public void setListaExport(List<SegDetExport> listaExport) {
        this.listaExport = listaExport;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Data> getModel() {
        return model;
    }

    public void setModel(List<Data> model) {
        this.model = model;
    }

    public List<Criteria> getListaCriteria() {
        return listaCriteria;
    }

    public void setListaCriteria(List<Criteria> listaCriteria) {
        this.listaCriteria = listaCriteria;
    }

    public List<SelectItem> getListSelectItems() {
        return listSelectItems;
    }

    public void setListSelectItems(List<SelectItem> listSelectItems) {
        this.listSelectItems = listSelectItems;
    }

    public int getSize() {
        return this.getListaCriteria().size();
    }

    public void setSize(int size) {
        this.size = size;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public void obtenerAtributosByEntidad(ActionEvent event){
        try{
            String rowKey = JSFUtils.getRequestParameter("rowKey");
            this.setSelectedEntidad(this.getListaEntidades().get(Integer.parseInt(rowKey)));
            ExportDao exportDao = (ExportDao) ServiceFinder.findBean("ExportDao");
            this.setListaAtributos(exportDao.obtenerListaAtributosByEntidad(this.getSelectedEntidad()));
            this.setSelectedAtributoList(new ArrayList<SegDetAtributo>());
            this.setListSelectItems(new Items(this.getListaAtributos(), Items.FIRST_ITEM_SELECT, "VNombreApp", "VDescripcion").getItems());
            this.getListaCriteria().clear();
            this.getListaCriteria().add(new Criteria());
            this.setSelectedExport(null);
            this.setColumns(new ArrayList<String>());
            this.setModel(new ArrayList<Data>());
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void handleAttributeSelection(ActionEvent event){
        try{
            String rowKey = JSFUtils.getRequestParameter("rowKey");
            this.setSelectedAtributo(this.getListaAtributos().get(Integer.parseInt(rowKey)));
            if(this.getSelectedAtributoList() == null){
                this.setSelectedAtributoList(new ArrayList<SegDetAtributo>());
            }
            this.getSelectedAtributoList().add(this.getSelectedAtributo());
            this.getListaAtributos().remove(this.getSelectedAtributo());
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void handleOrderedAttributeSelection(ActionEvent event){
        try{
            String rowKey = JSFUtils.getRequestParameter("rowKey");
            this.setSelectedAtributo(this.getSelectedAtributoList().get(Integer.parseInt(rowKey)));
            if(this.getListaAtributos() == null){
                this.setListaAtributos(new ArrayList<SegDetAtributo>());
            }
            this.getListaAtributos().add(this.getSelectedAtributo());
            this.getSelectedAtributoList().remove(this.getSelectedAtributo());
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void handleCriteriaSelected(ActionEvent event){
        String rowKey = null;
        String column = null;
        String table = null;
        Criteria criteria = null;
        List<GeneralBean> lista = null;
        try{
            rowKey = JSFUtils.getRequestParameter("rowKey");
            criteria = this.getListaCriteria().get(Integer.parseInt(rowKey));
            table = this.getSelectedEntidad().getVNombreApp();
            column = criteria.getColumn();
            if("SegDetNovedad".equals(table)){
                if(column.matches("NCodEmpresa|NTipoNovedad|NTipoEvento|NLocal|NArea|NLugar|NResponsable|NCargo|VDescBreve|VDescripcion|VAccTomadas|VUsuCreacion|VIpCreacion|VUsuModificacion|VIpModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("NCodEmpresa|NPersona|NActivo|NProceso|NAnalisis|NSeguimiento")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    criteria.setFlagSelectOneMenu(Boolean.TRUE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.FALSE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("DFecHora|DFecCreacion|DFecModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("<","<"));
                    lista.add(new GeneralBean(">",">"));
                    lista.add(new GeneralBean("<=","<="));
                    lista.add(new GeneralBean(">=",">="));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.TRUE);
                    criteria.setFlagInput(Boolean.FALSE);
                    criteria.setFlagDate(Boolean.FALSE);
                }
            }else if("SegDetInsPresencial".equals(table)){
                if(column.matches("NCodEmpresa|NCodNovedad|NTipoNovedad|NTipoInspeccion|NLocal|NArea|NLugar|NResponsable|NCargo|VDescBreve|VDescripcion|VAccTomadas|VControlAdicional|VUsuCreacion|VIpCreacion|VUsuModificacion|VIpModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("NCodEmpresa|NPersona|NActivo|NProceso|NAnalisis|NSeguimiento|NCumpleControl|NControlAdicional")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    criteria.setFlagSelectOneMenu(Boolean.TRUE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.FALSE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("DFecHora|DFecCreacion|DFecModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("<","<"));
                    lista.add(new GeneralBean(">",">"));
                    lista.add(new GeneralBean("<=","<="));
                    lista.add(new GeneralBean(">=",">="));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.TRUE);
                    criteria.setFlagInput(Boolean.FALSE);
                    criteria.setFlagDate(Boolean.FALSE);
                }
            }else if("SegDetInsTelefonica".equals(table)){
                if(column.matches("NCodEmpresa|NLugar|NResponsable|NCargo|VTelefono|VUsuCreacion|VIpCreacion|VUsuModificacion|VIpModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("NCodEmpresa|NLugar|NResponsable|NCargo|NSeguimiento|NEstado")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("DFecCreacion|DFecModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("<","<"));
                    lista.add(new GeneralBean(">",">"));
                    lista.add(new GeneralBean("<=","<="));
                    lista.add(new GeneralBean(">=",">="));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.TRUE);
                    criteria.setFlagInput(Boolean.FALSE);
                    criteria.setFlagDate(Boolean.FALSE);
                }
            }else if("SegDetCapacitacion".equals(table)){
                if(column.matches("NCodEmpresa|NTipoCapacitacion|NModalidad|NLugar|VNombre|VDescripcion|VUsuCreacion|VIpCreacion|VUsuModificacion|VIpModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("NCodEmpresa|NTipoCapacitacion|NModalidad|NLugar")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("DFechaHora|DFecCreacion|DFecModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("<","<"));
                    lista.add(new GeneralBean(">",">"));
                    lista.add(new GeneralBean("<=","<="));
                    lista.add(new GeneralBean(">=",">="));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.TRUE);
                    criteria.setFlagInput(Boolean.FALSE);
                    criteria.setFlagDate(Boolean.FALSE);
                }
            }else if("SegDetDocumento".equals(table)){
                if(column.matches("NCodEmpresa|NProcedencia|NTipoDocumento|VNombreOriginal|VRuta|VNombre|VDescripcion|VUsuCreacion|VIpCreacion|VUsuModificacion|VIpModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("NCodEmpresa|NProcedencia|NTipoDocumento|NVersion|NActivo")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("DFecEmision|DFecCreacion|DFecModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("<","<"));
                    lista.add(new GeneralBean(">",">"));
                    lista.add(new GeneralBean("<=","<="));
                    lista.add(new GeneralBean(">=",">="));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.TRUE);
                    criteria.setFlagInput(Boolean.FALSE);
                    criteria.setFlagDate(Boolean.FALSE);
                }
            }else if("SegDetRiesgo".equals(table)){
                if(column.matches("NCodEmpresa|NTipoRiesgo|VTipoRiesgo|NLocal|VLocal|NArea|VArea|NLugar|VLugar|NResponsable|VResponsable|NCargo|VCargo|VDescBreve|VDescripcion|VAccTomadas|VNombre|VDescripcion|VUsuCreacion|VIpCreacion|VUsuModificacion|VIpModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("NCodEmpresa|NTipoRiesgo|NLocal|NArea|NLugar|NResponsable|NCargo|NSeguimiento|NOcurrencia|NImpacto|NNivelOcurrencia|NNivelImpacto|NEstado")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("LIKE","LIKE"));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.FALSE);
                    criteria.setFlagInput(Boolean.TRUE);
                    criteria.setFlagDate(Boolean.FALSE);
                }else if(column.matches("DFecHora|DFecCreacion|DFecModificacion")){
                    lista = new ArrayList<GeneralBean>();
                    lista.add(new GeneralBean("=","="));
                    lista.add(new GeneralBean("<","<"));
                    lista.add(new GeneralBean(">",">"));
                    lista.add(new GeneralBean("<=","<="));
                    lista.add(new GeneralBean(">=",">="));
                    criteria.setFlagSelectOneMenu(Boolean.FALSE);
                    criteria.setFlagDateHour(Boolean.TRUE);
                    criteria.setFlagInput(Boolean.FALSE);
                    criteria.setFlagDate(Boolean.FALSE);
                }
            }
            
            criteria.setListCondition(new Items(lista, Items.FIRST_ITEM_BLANK, "codigo", "descripcion").getItems());
            this.getListaCriteria().set(Integer.parseInt(rowKey), criteria);
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void handleConditionSelected(ActionEvent event){
        String rowKey = null;
        Criteria criteria = null;
        try{
            rowKey = JSFUtils.getRequestParameter("rowKey");
            criteria = this.getListaCriteria().get(Integer.parseInt(rowKey));
            if(criteria.getFlagSelectOneMenu()){
                criteria.setPrefijo(" ");
                criteria.setSufijo(" ");
            }else if(criteria.getFlagInput()){
                if("=".equals(criteria.getCondition())){
                    criteria.setPrefijo(" '");
                    criteria.setSufijo("' ");
                }else{
                    criteria.setPrefijo(" '%");
                    criteria.setSufijo("%' ");
                }
            }else if(criteria.getFlagDate()){
                criteria.setPrefijo(" TO_DATE('");
                criteria.setSufijo("', 'dd/mm/yyyy') ");
            }else if(criteria.getFlagDateHour()){
                criteria.setPrefijo(" TO_DATE('");
                criteria.setSufijo("', 'dd/mm/yyyy hh24:mi') ");
            }
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void handleAddCriteria(ActionEvent event){
        try{
            this.getListaCriteria().add(new Criteria());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void handleDeleteCriteria(ActionEvent event){
        String rowKey = null;
        try{
            rowKey = JSFUtils.getRequestParameter("rowKey");
            if(this.getListaCriteria().size() > 1){
                this.getListaCriteria().remove(Integer.parseInt(rowKey));
            }
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void first(ActionEvent event){
        try{
            int index = this.getSelectedAtributoList().indexOf(this.getSelectedAtributo());
            this.getSelectedAtributoList().add(0, this.getSelectedAtributo());
            this.getSelectedAtributoList().remove(index + 1);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void up(ActionEvent event){
        try{
            int index = this.getSelectedAtributoList().indexOf(this.getSelectedAtributo());
            if(index > 0){
                this.getSelectedAtributoList().add(index - 1, this.getSelectedAtributo());
                this.getSelectedAtributoList().remove(index + 1);
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void down(ActionEvent event){
        try{
            int index = this.getSelectedAtributoList().indexOf(this.getSelectedAtributo());
            if(index < (this.getSelectedAtributoList().size() - 1)){
                index++;
                this.setSelectedAtributo(this.getSelectedAtributoList().get(index));
                this.getSelectedAtributoList().add(index - 1, this.getSelectedAtributo());
                this.getSelectedAtributoList().remove(index + 1);
                this.setSelectedAtributo(this.getSelectedAtributoList().get(index--));
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void last(ActionEvent event){
        try{
            int index = this.getSelectedAtributoList().indexOf(this.getSelectedAtributo());
            this.getSelectedAtributoList().add(this.getSelectedAtributo());
            this.getSelectedAtributoList().remove(index);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void preview(ActionEvent event){
        try{
            this.setListaPreviewAtributos(new ArrayList());
            this.getListaPreviewAtributos().addAll(this.getSelectedAtributoList());
            
            this.setColumns(new ArrayList<String>());
            for(int i=0;i<this.getListaPreviewAtributos().size();i++){
                SegDetAtributo atributo = this.getListaPreviewAtributos().get(i);
                this.getColumns().add(atributo.getVDescripcion());
            }
            this.setModel(getDataTable());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public List<Data> getDataTable(){
        List<Data> listaDatos = null;
        String table = null;
        try{
            ExportDao exportDao = (ExportDao) ServiceFinder.findBean("ExportDao");
            table = this.getSelectedEntidad().getVNombre();
            if("SEG_DET_NOVEDAD".equals(table)){
                listaDatos = exportDao.getDataTableNovedades(this.getListaPreviewAtributos(), this.getListaCriteria());
            }else if("SEG_DET_INS_PRESENCIAL".equals(table)){
                listaDatos = exportDao.getDataTableInspeccionPresencial(this.getListaPreviewAtributos(), this.getListaCriteria());
            }else if("SEG_DET_INS_TELEFONICA".equals(table)){
                listaDatos = exportDao.getDataTableInspeccionTelefonica(this.getListaPreviewAtributos(), this.getListaCriteria());
            }else if("SEG_DET_CAPACITACION".equals(table)){
                listaDatos = exportDao.getDataTableCapacitacion(this.getListaPreviewAtributos(), this.getListaCriteria());
            }else if("SEG_DET_DOCUMENTO".equals(table)){
                listaDatos = exportDao.getDataTableDocumento(this.getListaPreviewAtributos(), this.getListaCriteria());
            }else if("SEG_DET_RIESGO".equals(table)){
                listaDatos = exportDao.getDataTableRiesgo(this.getListaPreviewAtributos(), this.getListaCriteria());
            }
        }catch(Exception e){
            e.getMessage();
        }
        return listaDatos;
    }
    
    public void saveConfiguration(ActionEvent event){
        try{
            ExportDao exportDao = (ExportDao) ServiceFinder.findBean("ExportDao");
            CriteriaDao criteriaDao = (CriteriaDao) ServiceFinder.findBean("CriteriaDao");
            ExportDetalleDao exportDetalleDao = (ExportDetalleDao) ServiceFinder.findBean("ExportDetalleDao");
            SegCabUsuario usuario = (SegCabUsuario)JSFUtils.getSessionAttribute("usuario");
            SegCabEmpresa empresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            SegDetExportId segDetExportId = new SegDetExportId();
            SegDetExport segDetExport = new SegDetExport();
            if(this.getSelectedExport() != null){
                segDetExportId.setNCodEmpresa(this.getSelectedExport().getId().getNCodEmpresa());
                segDetExportId.setNCodExport(this.getSelectedExport().getId().getNCodExport());
                segDetExport.setId(segDetExportId);
                segDetExport.setNCodExport(segDetExportId.getNCodExport());
                segDetExport.setNCodEmpresa(segDetExportId.getNCodEmpresa());
                segDetExport.setNCodEntidad(this.getSelectedExport().getNCodEntidad());
                segDetExport.setVNombre(this.getNombre().toUpperCase().trim());
                segDetExport.setVDescripcion(this.getDescripcion().toUpperCase().trim());
                segDetExport.setDFecModificacion(new Date());
                segDetExport.setVUsuModificacion(usuario.getVUsuario());
                segDetExport.setVIpModificacion(JSFUtils.getRequest().getRemoteAddr());
                exportDetalleDao.eliminarDetalleConfiguracionById(segDetExport);
                criteriaDao.eliminarCriteriaById(segDetExport);
            }else{
                segDetExportId.setNCodEmpresa(empresa.getNCodEmpresa());
                segDetExportId.setNCodExport(BigDecimal.valueOf(exportDao.nextSequenceValue()));
                segDetExport.setId(segDetExportId);
                segDetExport.setNCodExport(segDetExportId.getNCodExport());
                segDetExport.setNCodEmpresa(segDetExportId.getNCodEmpresa());
                segDetExport.setNCodEntidad(this.getSelectedEntidad().getNCodEntidad());
                segDetExport.setVNombre(this.getNombre().toUpperCase().trim());
                segDetExport.setVDescripcion(this.getDescripcion().toUpperCase().trim());
                segDetExport.setDFecCreacion(new Date());
                segDetExport.setVUsuCreacion(usuario.getVUsuario());
                segDetExport.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
            }
            exportDao.registrarConfiguracion(segDetExport);
            
            if(this.getListaPreviewAtributos() != null && !this.getListaPreviewAtributos().isEmpty()){
                for(int i=0;i<this.getListaPreviewAtributos().size();i++){
                    SegDetAtributo atributo = this.getListaPreviewAtributos().get(i);

                    SegDetExportdetalleId segDetExportdetalleId = new SegDetExportdetalleId();
                    segDetExportdetalleId.setNCodEmpresa(empresa.getNCodEmpresa());
                    segDetExportdetalleId.setNCodExport(segDetExportId.getNCodExport());
                    segDetExportdetalleId.setNCodExportDetalle(BigDecimal.valueOf(exportDetalleDao.nextSequenceValue()));
                    SegDetExportdetalle segDetExportdetalle = new SegDetExportdetalle();
                    segDetExportdetalle.setId(segDetExportdetalleId);
                    segDetExportdetalle.setNCodAtributo(atributo.getNCodAtributo());
                    segDetExportdetalle.setNOrden(BigDecimal.valueOf(i+1));
                    segDetExportdetalle.setDFecCreacion(new Date());
                    segDetExportdetalle.setVUsuCreacion(usuario.getVUsuario());
                    segDetExportdetalle.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());

                    exportDetalleDao.registrarDetalleConfiguracion(segDetExportdetalle);
                }
            }
            if(this.getListaCriteria() != null && !this.getListaCriteria().isEmpty()){
                for(int i=0;i<this.getListaCriteria().size();i++){
                    Criteria c = this.getListaCriteria().get(i);
                    String col = c.getColumn();
                    String cond = c.getCondition();
                    String val = c.getValue();
                    String pre = c.getPrefijo();
                    String su = c.getSufijo();
                    if(col != null && !col.isEmpty() && cond != null && !cond.isEmpty() && val != null && !val.isEmpty()){
                        SegDetCriteriaId criteriaId = new SegDetCriteriaId();
                        criteriaId.setNCodEmpresa(empresa.getNCodEmpresa());
                        criteriaId.setNCodExport(segDetExportId.getNCodExport());
                        criteriaId.setNCodCriteria(BigDecimal.valueOf(criteriaDao.nextSequenceValue()));
                        SegDetCriteria criteria = new SegDetCriteria();
                        criteria.setId(criteriaId);
                        criteria.setVColumn(col);
                        criteria.setVCondition(cond);
                        criteria.setVValue(val);
                        criteria.setVPrefijo(pre);
                        criteria.setVSufijo(su);
                        criteria.setDFecCreacion(new Date());
                        criteria.setVUsuCreacion(usuario.getVUsuario());
                        criteria.setVIpCreacion(JSFUtils.getRequest().getRemoteAddr());
                        criteriaDao.registrarCriteria(criteria);
                    }
                }
            }
            this.setNombre(null);
            this.setDescripcion(null);
            this.setAction("Richfaces.hideModalPanel('dlg')");
            this.setListaExport(exportDao.obtenerListaExport());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void deleteConfiguration(ActionEvent event){
        try{
            ExportDao exportDao = (ExportDao) ServiceFinder.findBean("ExportDao");
            exportDao.deleteExport(this.getSelectedExport());
            this.getListaExport().remove(this.getSelectedExport());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void chargeConfiguration(ActionEvent event){
        String rowKey = null;
        List<GeneralBean> lista = null;
        List<SegDetExportdetalle> listaDetalle = null;
        List<SegDetCriteria> listaSegDetCriteria = null;
        try{
            rowKey = JSFUtils.getRequestParameter("rowKey");
            this.setSelectedExport(this.getListaExport().get(Integer.parseInt(rowKey)));
            this.setNombre(this.getSelectedExport().getVNombre());
            this.setDescripcion(this.getSelectedExport().getVDescripcion());
            ExportDao exportDao = (ExportDao) ServiceFinder.findBean("ExportDao");
            this.setSelectedEntidad(exportDao.obtenerEntidadByExport(this.getSelectedExport()));
            this.setListaAtributos(new ArrayList<SegDetAtributo>());
            this.setSelectedAtributoList(new ArrayList<SegDetAtributo>());
            listaDetalle = exportDao.obtenerListaExportDetalle(this.getSelectedExport());
            this.setSelectedAtributoList(exportDao.obtenerListaAtributosSeleccionados(listaDetalle));
            this.setListaAtributos(exportDao.obtenerListaAtributosNoSeleccionados(listaDetalle));
            listaSegDetCriteria = exportDao.obtenerListaCriteria(this.getSelectedExport());
            if(listaSegDetCriteria != null && !listaSegDetCriteria.isEmpty()){
                this.setListaCriteria(new ArrayList<Criteria>());
                for (SegDetCriteria segDetCriteria : listaSegDetCriteria) {
                    Criteria criteria = new Criteria();
                    criteria.setColumn(segDetCriteria.getVColumn());
                    criteria.setCondition(segDetCriteria.getVCondition());
                    criteria.setValue(segDetCriteria.getVValue());
                    criteria.setPrefijo(segDetCriteria.getVPrefijo());
                    criteria.setSufijo(segDetCriteria.getVSufijo());
                    if(criteria.getColumn().matches("NCodEmpresa|NTipoNovedad|NTipoEvento|NLocal|NArea|NLugar|NResponsable|NCargo|VDescBreve|VDescripcion|VAccTomadas|VUsuCreacion|VIpCreacion|VUsuModificacion|VIpModificacion")){
                        lista = new ArrayList<GeneralBean>();
                        lista.add(new GeneralBean("=","="));
                        lista.add(new GeneralBean("LIKE","LIKE"));
                        criteria.setFlagSelectOneMenu(Boolean.FALSE);
                        criteria.setFlagDateHour(Boolean.FALSE);
                        criteria.setFlagInput(Boolean.TRUE);
                        criteria.setFlagDate(Boolean.FALSE);
                    }else if(criteria.getColumn().matches("NCodEmpresa|NPersona|NActivo|NProceso")){
                        lista = new ArrayList<GeneralBean>();
                        lista.add(new GeneralBean("=","="));
                        criteria.setFlagSelectOneMenu(Boolean.TRUE);
                        criteria.setFlagDateHour(Boolean.FALSE);
                        criteria.setFlagInput(Boolean.FALSE);
                        criteria.setFlagDate(Boolean.FALSE);
                    }else if(criteria.getColumn().matches("DFecHora|DFecCreacion|DFecModificacion")){
                        lista = new ArrayList<GeneralBean>();
                        lista.add(new GeneralBean("=","="));
                        lista.add(new GeneralBean("<","<"));
                        lista.add(new GeneralBean(">",">"));
                        lista.add(new GeneralBean("<=","<="));
                        lista.add(new GeneralBean(">=",">="));
                        criteria.setFlagSelectOneMenu(Boolean.FALSE);
                        criteria.setFlagDateHour(Boolean.TRUE);
                        criteria.setFlagInput(Boolean.FALSE);
                        criteria.setFlagDate(Boolean.FALSE);
                    }
                    criteria.setListCondition(new Items(lista, Items.FIRST_ITEM_BLANK, "codigo", "descripcion").getItems());
                    this.getListaCriteria().add(criteria);
                }
            }
            this.preview(event);
        }catch(NumberFormatException e){
            e.getMessage();
        }
    }
    
    public void exportDataToExcel(ActionEvent event){
        int cols = 0;
        Label label = null;
        String title = null;
        List<Data> listaData = null;
        List<String> listHeader = null;
        ResourceBundle bundle = null;
        String filepath = null;
        String filename = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabEmpresa segCabEmpresa = (SegCabEmpresa)JSFUtils.getSessionAttribute("empresa");
            filepath = bundle.getString("filePath").concat(segCabEmpresa.getVRuc()).concat("\\"); //Para WINDOWS
            //filepath = bundle.getString("filepath").concat(segCabEmpresa.getVRuc()).concat("/"); //Para LINUX
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            filename = "export".concat(sdf.format(new Date())).concat(".xls");
            listaData = this.getModel();
            listHeader = this.getColumns();
            title = "LISTADO DE ".concat(this.getSelectedEntidad().getVDescripcion());
            cols = listHeader.size();
            WritableWorkbook workbook = Workbook.createWorkbook(new File(filepath.concat(filename)));
            WritableSheet sheet = workbook.createSheet("REPORTE", 0);
            
            WritableFont headerFont = new WritableFont(WritableFont.TIMES,9,WritableFont.BOLD,false);
            WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
            headerFormat.setAlignment(Alignment.CENTRE);
            headerFormat.setBackground(Colour.ICE_BLUE);
            headerFormat.setBorder(Border.ALL,BorderLineStyle.DOUBLE);
            
            WritableFont dataFont = new WritableFont(WritableFont.TIMES,8);
            WritableCellFormat dataFormat = new WritableCellFormat(dataFont);
            dataFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
            
            WritableFont titleFont = new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD,false,UnderlineStyle.SINGLE);
            WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
            titleFormat.setAlignment(Alignment.CENTRE);
            titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            for(int i=0;i<listHeader.size();i++){
                label = new Label(i,0, listHeader.get(i),headerFormat);
                sheet.addCell(label);
            }
            for(int i=0;i<listaData.size();i++){
                Data data = listaData.get(i);
                String[] values = data.getValues();
                for(int j=0;j<cols;j++){
                    if(values[j] != null){
                        label = new Label(j,i+1, values[j],dataFormat);
                    }else{
                        label = new Label(j,i+1,"",dataFormat);
                    }
                    sheet.addCell(label);
                }
            }
            
            CellView cf = new CellView();
            cf.setAutosize(true);
            
            for(int j=0;j<cols;j++){
                sheet.setColumnView(j,cf);
            }
            
            sheet.insertRow(0);
            sheet.insertRow(1);
            sheet.insertRow(2);
            sheet.mergeCells(0,0,cols-1,2);
            label = new Label(0,0,title,titleFormat);
            sheet.addCell(label);
            
            sheet.getSettings().setPaperSize(PaperSize.A4);
            sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE);
            sheet.getSettings().setHorizontalCentre(true);
            sheet.getSettings().setPrintArea(0,0,cols-1,listaData.size()+3);
            
            workbook.write();
            workbook.close();
            
            openFile(filepath.concat(filename));
        }catch(IOException e){
            e.getMessage();
        } catch (WriteException e) {
            e.getMessage();
        }
    }
    
    public void openFile(String file){
        try{
            /*
            File ficheroXLS = new File(file);
            FacesContext ctx = FacesContext.getCurrentInstance();
            FileInputStream fis = new FileInputStream(ficheroXLS);
            byte[] bytes = new byte[1000];
            int read = 0;

            if (!ctx.getResponseComplete()) {
               String fileName = ficheroXLS.getName();
               String contentType = "application/octet-stream";
               HttpServletResponse response =(HttpServletResponse) ctx.getExternalContext().getResponse();
               response.setContentType(contentType);
               response.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
               ServletOutputStream out = response.getOutputStream();

               while ((read = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
               }

               out.flush();
               out.close();
               ctx.responseComplete();
            }
            */
            File f = new File(file);
            Desktop.getDesktop().open(f);
        }catch(FileNotFoundException fnfe){
            fnfe.getMessage();
        }catch(IOException ioe){
            ioe.getMessage();
        }
    }
}
