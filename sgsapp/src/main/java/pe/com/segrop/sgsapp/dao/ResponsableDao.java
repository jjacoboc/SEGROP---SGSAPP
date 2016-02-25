/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetResponsable;

/**
 *
 * @author JJ
 */
public interface ResponsableDao {
    
    public Long nextSequenceValue();    
    public List<SegDetResponsable> buscarResponsables(SegDetResponsable responsable);    
    public List<SegDetResponsable> obtenerListaResponsables();
    public List<SegDetResponsable> obtenerListaResponsablesByArea(SegDetArea area);
    public List<SegDetResponsable> obtenerListaResponsablesActivos();
    public List<SegDetResponsable> obtenerListaResponsablesActivosByEmpresa(SegCabEmpresa empresa);
    public List<SegDetResponsable> obtenerListaResponsablesActivosByArea(SegDetArea area);
    public SegDetResponsable obtenerResponsableByNombreApellido(SegDetResponsable responsable);
    public void registrarResponsable(SegDetResponsable responsable);
    public void eliminarResponsable(SegDetResponsable responsable);
}
