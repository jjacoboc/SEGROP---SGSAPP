/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetInsTelefonica;

/**
 *
 * @author JJ
 */
public interface InsTelefonicaDao {
    
    public Long nextSequenceValue();
    public List<SegDetInsTelefonica> buscarInspeccionesTelefonicas(SegDetInsTelefonica insTelefonica);    
    public List<SegDetInsTelefonica> obtenerListaInspeccionesTelefonicas();
    public List<SegDetInsTelefonica> obtenerListaInspeccionesTelefonicasByEmpresa(SegCabEmpresa empresa);
    public void registrarInspeccionTelefonica(SegDetInsTelefonica insTelefonica);
}
