/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEntidad;
import pe.com.segrop.sgsapp.domain.SegDetAtributo;
import pe.com.segrop.sgsapp.domain.SegDetCriteria;
import pe.com.segrop.sgsapp.domain.SegDetExport;
import pe.com.segrop.sgsapp.domain.SegDetExportdetalle;
import pe.com.segrop.sgsapp.web.common.Criteria;
import pe.com.segrop.sgsapp.web.common.Data;

/**
 *
 * @author JJ
 */
public interface ExportDao {
    
    public Long nextSequenceValue();
    public List<SegCabEntidad> obtenerListaEntidades();
    public List<SegDetAtributo> obtenerListaAtributosByEntidad(SegCabEntidad entidad);
    public List<SegDetExport> obtenerListaExport();
    public List<SegDetExportdetalle> obtenerListaExportDetalle(SegDetExport export);
    public List<SegDetCriteria> obtenerListaCriteria(SegDetExport export);
    public SegCabEntidad obtenerEntidadByExport(SegDetExport export);
    public List<SegDetAtributo> obtenerListaAtributosSeleccionados(List<SegDetExportdetalle> listaDetalle);
    public List<SegDetAtributo> obtenerListaAtributosNoSeleccionados(List<SegDetExportdetalle> listaDetalle);
    public List<Data> getDataTableNovedades(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria);
    public List<Data> getDataTableInspeccionPresencial(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria);
    public List<Data> getDataTableInspeccionTelefonica(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria);
    public List<Data> getDataTableCapacitacion(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria);
    public List<Data> getDataTableDocumento(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria);
    public List<Data> getDataTableRiesgo(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria);
    public void registrarConfiguracion(SegDetExport export);
    public void deleteExport(SegDetExport export);
}
