/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import pe.com.segrop.sgsapp.domain.SegDetNovEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;

/**
 *
 * @author JJ
 */
public interface NovedadEvaluacionDao {
    
    public Long nextSequenceValue();
    public SegDetNovEvaluacion obtenerEvaluacionNovedad(SegDetNovedad novedad);
    public void registrarEvaluacion(SegDetNovEvaluacion evaluacion);
}
