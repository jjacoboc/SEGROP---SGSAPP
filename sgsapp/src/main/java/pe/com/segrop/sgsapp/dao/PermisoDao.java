/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetObjeto;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegRelPermiso;

/**
 *
 * @author JJ
 */
public interface PermisoDao {
    
    public List<SegDetObjeto> obtenerObjetosAsignadosByPerfil(SegDetPerfil perfil);
    public List<SegDetObjeto> obtenerObjetosNoAsignadosByPerfil(SegDetPerfil perfil);
    public SegRelPermiso obtenerPermisoById(SegRelPermiso permiso);
    public void registrarPermiso(SegRelPermiso permiso);
}
