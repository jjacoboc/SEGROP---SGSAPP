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
    
    public Long nextSequenceValue();    
    public List<SegDetLugar> buscarLugares(SegDetLugar lugar);    
    public List<SegDetLugar> obtenerListaLugares();
    public List<SegDetLugar> obtenerListaLugaresByArea(SegDetArea area);
    public List<SegDetLugar> obtenerListaLugaresActivos();
    public List<SegDetLugar> obtenerListaLugaresActivosByEmpresa(SegCabEmpresa empresa);
    public List<SegDetLugar> obtenerListaLugaresActivosByArea(SegDetArea area);
    public SegDetLugar obtenerLugarByDescripcion(SegDetLugar lugar);
    public void registrarLugar(SegDetLugar lugar);
    public void eliminarLugar(SegDetLugar lugar);
}
