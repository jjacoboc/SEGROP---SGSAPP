/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetCapacitacion;
import pe.com.segrop.sgsapp.domain.SegDetParticipante;

/**
 *
 * @author JJ
 */
public interface ParticipanteDao {
    
    public Long nextSequenceValue();
    public SegDetParticipante buscarParticipante(SegDetParticipante participante);
    public SegDetParticipante obtenerParticipanteByCapacitacion(SegDetParticipante participante);
    public List<SegDetParticipante> obtenerListaParticipantesByCapacitacion(SegDetCapacitacion capacitacion);
    public void registrarParticipante(SegDetParticipante participante);
    public void eliminarParticipante(SegDetParticipante participante);
}
