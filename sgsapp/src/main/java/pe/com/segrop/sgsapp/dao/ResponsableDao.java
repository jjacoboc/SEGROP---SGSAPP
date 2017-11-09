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
    
    Long nextSequenceValue();    
    List<SegDetResponsable> buscarResponsables(SegDetResponsable responsable);    
    List<SegDetResponsable> obtenerListaResponsables();
    List<SegDetResponsable> obtenerListaResponsablesByEmpresa(SegCabEmpresa empresa);
    List<SegDetResponsable> obtenerListaResponsablesByArea(SegDetArea area);
    List<SegDetResponsable> obtenerListaResponsablesActivos();
    List<SegDetResponsable> obtenerListaResponsablesActivosByEmpresa(SegCabEmpresa empresa);
    List<SegDetResponsable> obtenerListaResponsablesActivosByArea(SegDetArea area);
    SegDetResponsable obtenerResponsableByNombreApellido(SegDetResponsable responsable);
    void registrarResponsable(SegDetResponsable responsable);
    void eliminarResponsable(SegDetResponsable responsable);
}
