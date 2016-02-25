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
    
    public Long nextSequenceValue();
    public List<SegDetArea> buscarAreas(SegDetArea area);    
    public List<SegDetArea> obtenerListaAreas();
    public List<SegDetArea> obtenerListaAreasByLocal(SegDetLocal local);
    public List<SegDetArea> obtenerListaAreasActivas();
    public List<SegDetArea> obtenerListaAreasActivasByEmpresa(SegCabEmpresa empresa);
    public List<SegDetArea> obtenerListaAreasActivasByLocal(SegDetLocal local);
    public SegDetArea obtenerAreaByDescripcion(SegDetArea area);
    public void registrarArea(SegDetArea area);
    public void eliminarArea(SegDetArea area);
}
