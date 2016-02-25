/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetInspreevalDetalle;

/**
 *
 * @author JJ
 */
public interface InspreEvaluacionDetalleDao {
    
    public Long nextSequenceValue();
    public List<SegDetInspreevalDetalle> obtenerListaDetalleEvaluacionInspeccion(SegDetInspreEvaluacion evaluacion);
    public void registrarEvaluacionDetalle(SegDetInspreevalDetalle detalle);
}
