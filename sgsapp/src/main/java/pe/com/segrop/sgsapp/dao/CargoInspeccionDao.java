/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabCargo;

/**
 *
 * @author JJ
 */
public interface CargoInspeccionDao {
    
    public Long nextSequenceValue();
    public SegCabCargo buscarCargo(SegCabCargo cargo);
    public List<SegCabCargo> obtenerListaCargos();
    public List<SegCabCargo> obtenerListaCargosActivos();
    public void registrarCargo(SegCabCargo cargo);
    public void eliminarCargo(SegCabCargo cargo);
}
