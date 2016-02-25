/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetInsTelefonica;
import pe.com.segrop.sgsapp.domain.SegDetPregunta;

/**
 *
 * @author JJ
 */
public interface PreguntaDao {
    
    public Long nextSequenceValue();
    public SegDetPregunta buscarPregunta(SegDetPregunta pregunta);
    public List<SegDetPregunta> obtenerListaPreguntas();
    public List<SegDetPregunta> obtenerListaPreguntasByEmpresa(SegCabEmpresa empresa);
    public List<SegDetPregunta> obtenerListaPreguntasActivas();
    public List<SegDetPregunta> obtenerListaPreguntasActivasByEmpresa(SegCabEmpresa empresa);
    public List<SegDetPregunta> obtenerListaPreguntasByInspeccion(SegDetInsTelefonica inspeccion);
    public boolean isPreguntaEnCuestionario(SegDetPregunta pregunta);
    public void registrarPregunta(SegDetPregunta pregunta);
    public void eliminarPregunta(SegDetPregunta pregunta);
}
