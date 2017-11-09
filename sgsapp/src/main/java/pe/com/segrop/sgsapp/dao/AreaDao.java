/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetLocal;

/**
 *
 * @author JJ
 */
public interface AreaDao {
    
    Long nextSequenceValue();
    List<SegDetArea> buscarAreas(SegDetArea area);    
    List<SegDetArea> obtenerListaAreas();
    List<SegDetArea> obtenerListaAreasByLocal(SegDetLocal local);
    List<SegDetArea> obtenerListaAreasByEmpresa(SegCabEmpresa empresa);
    List<SegDetArea> obtenerListaAreasActivas();
    List<SegDetArea> obtenerListaAreasActivasByEmpresa(SegCabEmpresa empresa);
    List<SegDetArea> obtenerListaAreasActivasByLocal(SegDetLocal local);
    SegDetArea obtenerAreaByDescripcion(SegDetArea area);
    void registrarArea(SegDetArea area);
    void eliminarArea(SegDetArea area);
}
