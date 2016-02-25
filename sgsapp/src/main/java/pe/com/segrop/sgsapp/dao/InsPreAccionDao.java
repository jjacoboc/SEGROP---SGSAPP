/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;
import pe.com.segrop.sgsapp.domain.SegDetInspreAcciones;

/**
 *
 * @author JJ
 */
public interface InsPreAccionDao {
    
    public Long nextSequenceValue();
    public List<SegDetInspreAcciones> obtenerListaAccionesByInspeccionPresencial(SegDetInsPresencial insPresencial);
    public void registrarAccion(SegDetInspreAcciones accion);
}
