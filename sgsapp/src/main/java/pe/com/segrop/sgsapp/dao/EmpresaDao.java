/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;

/**
 *
 * @author JJ
 */
public interface EmpresaDao {
    
    public Long nextSequenceValue();
    public List<SegCabEmpresa> buscarEmpresas(SegCabEmpresa empresa);
    public List<SegCabEmpresa> obtenerListaEmpresas();
    public List<SegCabEmpresa> obtenerListaEmpresasActivas();
    public SegCabEmpresa obtenerEmpresaById(SegCabEmpresa empresa);
    public SegCabEmpresa obtenerEmpresaByRuc(SegCabEmpresa empresa);
    public void registrarEmpresa(SegCabEmpresa empresa);
}