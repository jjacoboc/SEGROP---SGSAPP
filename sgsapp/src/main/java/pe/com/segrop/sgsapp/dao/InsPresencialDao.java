/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;

/**
 *
 * @author JJ
 */
public interface InsPresencialDao {
    
    public Long nextSequenceValue();
    public List<SegDetInsPresencial> buscarInspeccionesPresenciales(SegDetInsPresencial insPresencial);
    public SegDetInsPresencial obtenerInspeccionPresencialById(SegDetInsPresencial insPresencial);
    public List<SegDetInsPresencial> obtenerListaInspeccionesPresenciales();
    public List<SegDetInsPresencial> obtenerListaInspeccionesPresencialesByEmpresa(SegCabEmpresa empresa);
    public void registrarInspeccionPresencial(SegDetInsPresencial insPresencial);
}
