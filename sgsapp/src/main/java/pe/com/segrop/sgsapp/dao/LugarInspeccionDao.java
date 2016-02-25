/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabLugar;

/**
 *
 * @author JJ
 */
public interface LugarInspeccionDao {
    
    public Long nextSequenceValue();
    public SegCabLugar buscarLugar(SegCabLugar lugar);
    public List<SegCabLugar> obtenerListaLugares();
    public List<SegCabLugar> obtenerListaLugaresActivos();
    public void registrarLugar(SegCabLugar lugar);
    public void eliminarLugar(SegCabLugar lugar);
}
