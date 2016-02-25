/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetLocal;

/**
 *
 * @author JJ
 */
public interface LocalDao {
    
    public Long nextSequenceValue();    
    public List<SegDetLocal> buscarLocales(SegDetLocal local);    
    public List<SegDetLocal> obtenerListaLocales();
    public List<SegDetLocal> obtenerListaLocalesByEmpresa(SegCabEmpresa empresa);
    public List<SegDetLocal> obtenerListaLocalesActivos();
    public List<SegDetLocal> obtenerListaLocalesActivosByEmpresa(SegCabEmpresa empresa);
    public SegDetLocal obtenerLocalByDescripcion(SegDetLocal local);
    public void registrarLocal(SegDetLocal local);
    public void eliminarLocal(SegDetLocal local);
}
