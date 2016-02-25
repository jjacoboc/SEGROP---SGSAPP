/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetCapacitacion;

/**
 *
 * @author JJ
 */
public interface CapacitacionDao {
    
    public Long nextSequenceValue();
    public List<SegDetCapacitacion> buscarCapacitaciones(SegDetCapacitacion capacitacion);
    public List<SegDetCapacitacion> obtenerListaCapacitacionesByEmpresa(SegCabEmpresa empresa);
    public void registrarCapacitacion(SegDetCapacitacion capacitacion);
}
