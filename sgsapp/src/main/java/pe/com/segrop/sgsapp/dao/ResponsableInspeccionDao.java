/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabResponsable;

/**
 *
 * @author JJ
 */
public interface ResponsableInspeccionDao {
    
    public Long nextSequenceValue();
    public SegCabResponsable buscarResponsable(SegCabResponsable responsable);
    public List<SegCabResponsable> obtenerListaResponsables();
    public List<SegCabResponsable> obtenerListaResponsablesActivos();
    public void registrarResponsable(SegCabResponsable responsable);
    public void eliminarResponsable(SegCabResponsable responsable);
}
