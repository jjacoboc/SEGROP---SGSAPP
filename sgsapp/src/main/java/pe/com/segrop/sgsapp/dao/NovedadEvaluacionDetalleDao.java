/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetNovEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetNovevalDetalle;

/**
 *
 * @author JJ
 */
public interface NovedadEvaluacionDetalleDao {
    
    public Long nextSequenceValue();
    public List<SegDetNovevalDetalle> obtenerListaDetalleEvaluacionNovedad(SegDetNovEvaluacion evaluacion);
    public void registrarEvaluacionDetalle(SegDetNovevalDetalle detalle);
}
