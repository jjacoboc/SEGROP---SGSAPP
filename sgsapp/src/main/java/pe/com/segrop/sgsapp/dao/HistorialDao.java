/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import pe.com.segrop.sgsapp.domain.SegDetDocumento;
import pe.com.segrop.sgsapp.domain.SegDetHistorial;

/**
 *
 * @author JJ
 */
public interface HistorialDao {
    
    public Long nextSequenceValue();
    public Integer obtenerMaximoVersionDocumento(SegDetDocumento segDetDocumento);
    public void registrarHistorial(SegDetHistorial historial);
    public void eliminarHistorial(SegDetHistorial historial);
}
