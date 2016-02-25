/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;

/**
 *
 * @author JJ
 */
public interface NovedadDao {
    
    public Long nextSequenceValue();
    public List<SegDetNovedad> buscarNovedades(SegDetNovedad novedad);
    public SegDetNovedad obtenerNovedadById(SegDetNovedad novedad);
    public List<SegDetNovedad> obtenerListaNovedades();
    public List<SegDetNovedad> obtenerListaNovedadesNoCerradas();
    public List<SegDetNovedad> obtenerListaNovedadesByEmpresa(SegCabEmpresa empresa);
    public void registrarNovedad(SegDetNovedad novedad);
}
