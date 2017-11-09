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
    
    Long nextSequenceValue();    
    List<SegDetLocal> buscarLocales(SegDetLocal local);    
    List<SegDetLocal> obtenerListaLocales();
    List<SegDetLocal> obtenerListaLocalesByEmpresa(SegCabEmpresa empresa);
    List<SegDetLocal> obtenerListaLocalesActivos();
    List<SegDetLocal> obtenerListaLocalesActivosByEmpresa(SegCabEmpresa empresa);
    SegDetLocal obtenerLocalByDescripcion(SegDetLocal local);
    void registrarLocal(SegDetLocal local);
    void eliminarLocal(SegDetLocal local);
}
