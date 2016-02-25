/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetInsTelefonica;
import pe.com.segrop.sgsapp.domain.SegDetPregunta;
import pe.com.segrop.sgsapp.domain.SegDetRespuesta;

/**
 *
 * @author JJ
 */
public interface RespuestaDao {
    
    public Long nextSequenceValue();
    public List<SegDetRespuesta> obtenerListaRespuestasByPreguntaInspeccion(SegDetPregunta pregunta, SegDetInsTelefonica inspeccion);
    public void registrarRespuesta(SegDetRespuesta respuesta);
    public void eliminarRespuesta(SegDetRespuesta respuesta);
}
