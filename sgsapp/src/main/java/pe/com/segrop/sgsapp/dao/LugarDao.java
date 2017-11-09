/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetLugar;

/**
 *
 * @author JJ
 */
public interface LugarDao {
    
    Long nextSequenceValue();    
    List<SegDetLugar> buscarLugares(SegDetLugar lugar);    
    List<SegDetLugar> obtenerListaLugares();
    List<SegDetLugar> obtenerListaLugaresByEmpresa(SegCabEmpresa empresa);
    List<SegDetLugar> obtenerListaLugaresByArea(SegDetArea area);
    List<SegDetLugar> obtenerListaLugaresActivos();
    List<SegDetLugar> obtenerListaLugaresActivosByEmpresa(SegCabEmpresa empresa);
    List<SegDetLugar> obtenerListaLugaresActivosByArea(SegDetArea area);
    SegDetLugar obtenerLugarByDescripcion(SegDetLugar lugar);
    void registrarLugar(SegDetLugar lugar);
    void eliminarLugar(SegDetLugar lugar);
}
