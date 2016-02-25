/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegRelUsuarioperfil;

/**
 *
 * @author JJ
 */
public interface UsuarioPerfilDao {
    
    public List<SegDetPerfil> obtenerPerfilesAsignadosByUsuario(SegCabUsuario usuario);
    public List<SegDetPerfil> obtenerPerfilesNoAsignadosByUsuario(SegCabUsuario usuario);
    public SegRelUsuarioperfil obtenerUsuarioPerfilById(SegRelUsuarioperfil usuarioPerfil);
    public void eliminarAsignacion(SegRelUsuarioperfil usuarioPerfil);
    public void registrarAsignacion(SegRelUsuarioperfil usuarioPerfil);
}