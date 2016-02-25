/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;

/**
 *
 * @author JJ
 */
public interface PerfilDao {
    
    public Long nextSequenceValue();    
    public List<SegDetPerfil> buscarPerfiles(SegDetPerfil perfil);    
    public List<SegDetPerfil> obtenerListaPerfiles();
    public List<SegDetPerfil> obtenerListaPerfilesByEmpresa(SegDetPerfil perfil);
    public List<SegDetPerfil> obtenerListaPerfilesActivos();
    public List<SegDetPerfil> obtenerListaPerfilesActivosByEmpresa(SegDetPerfil perfil);
    public SegDetPerfil obtenerPerfilById(SegDetPerfil perfil);
    public SegDetPerfil obtenerPerfilByNombre(SegDetPerfil perfil);
    public void registrarPerfil(SegDetPerfil perfil);
}
