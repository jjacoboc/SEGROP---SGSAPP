/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import pe.com.segrop.sgsapp.domain.SegDetExport;
import pe.com.segrop.sgsapp.domain.SegDetExportdetalle;

/**
 *
 * @author JJ
 */
public interface ExportDetalleDao {
    
    public Long nextSequenceValue();
    public void registrarDetalleConfiguracion(SegDetExportdetalle export);
    public void eliminarDetalleConfiguracionById(SegDetExport export);
}
