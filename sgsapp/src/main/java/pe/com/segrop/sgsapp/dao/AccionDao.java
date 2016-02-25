/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetAcciones;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;

/**
 *
 * @author JJ
 */
public interface AccionDao {
    
    public Long nextSequenceValue();
    public List<SegDetAcciones> obtenerListaAccionesByNovedad(SegDetNovedad novedad);
    public void registrarAccion(SegDetAcciones accion);
}
