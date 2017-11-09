/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;

/**
 *
 * @author JJ
 */
public interface NovedadDao {
    
    Long nextSequenceValue();
    List<SegDetNovedad> buscarNovedades(SegDetNovedad novedad);
    SegDetNovedad obtenerNovedadById(SegDetNovedad novedad);
    List<SegDetNovedad> obtenerListaNovedades();
    List<SegDetNovedad> obtenerListaNovedadesNoCerradas();
    List<SegDetNovedad> obtenerListaNovedadesNoCerradasByEmpresa(BigDecimal idEmpresa);
    List<SegDetNovedad> obtenerListaNovedadesByEmpresa(SegCabEmpresa empresa);
    void registrarNovedad(SegDetNovedad novedad);
}
