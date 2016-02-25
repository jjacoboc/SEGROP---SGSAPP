/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetLugarCapacitacion;

/**
 *
 * @author JJ
 */
public interface LugarCapacitacionDao {
    
    public Long nextSequenceValue();
    public SegDetLugarCapacitacion buscarLugarCapacitacionByEmpresa(SegDetLugarCapacitacion lugar);
    public List<SegDetLugarCapacitacion> obtenerListaLugaresCapacitacion();
    public List<SegDetLugarCapacitacion> obtenerListaLugaresCapacitacionByEmpresa(SegCabEmpresa empresa);
    public void registrarLugarCapacitacion(SegDetLugarCapacitacion lugar);
    public void eliminarLugarCapacitacion(SegDetLugarCapacitacion lugar);
}
