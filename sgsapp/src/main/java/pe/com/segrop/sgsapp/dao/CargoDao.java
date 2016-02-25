/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetCargo;

/**
 *
 * @author JJ
 */
public interface CargoDao {
    
    public Long nextSequenceValue();
    public List<SegDetCargo> buscarCargos(SegDetCargo cargo);
    public List<SegDetCargo> obtenerListaCargos();
    public List<SegDetCargo> obtenerListaCargosByEmpresa(SegCabEmpresa empresa);
    public List<SegDetCargo> obtenerListaCargosActivos();
    public List<SegDetCargo> obtenerListaCargosActivosByEmpresa(SegCabEmpresa empresa);
    public SegDetCargo obtenerCargoByDescripcion(SegDetCargo cargo);
    public void registrarCargo(SegDetCargo cargo);
    public void eliminarCargo(SegDetCargo cargo);
}
