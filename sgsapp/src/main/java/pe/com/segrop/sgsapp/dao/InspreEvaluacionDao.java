/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacion;

/**
 *
 * @author JJ
 */
public interface InspreEvaluacionDao {
    
    public Long nextSequenceValue();
    public SegDetInspreEvaluacion obtenerEvaluacionInspeccion(SegDetInsPresencial presencial);
    public void registrarEvaluacion(SegDetInspreEvaluacion evaluacion);
}
