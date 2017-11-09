/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;

/**
 *
 * @author JJ
 */
public interface UsuarioDao {
    
    Long nextSequenceValue();
    List<SegCabUsuario> buscarUsuarios(SegCabUsuario usuario);
    List<SegCabUsuario> buscarUsuariosActivos(SegCabUsuario usuario);
    List<SegCabUsuario> obtenerListaUsuarios();
    List<SegCabUsuario> obtenerListaUsuariosActivos();
    List<SegCabUsuario> obtenerListaUsuariosByEmpresa(SegCabEmpresa segCabEmpresa);
    List<SegCabUsuario> obtenerListaUsuariosActivosByEmpresa(SegCabEmpresa segCabEmpresa);
    SegCabUsuario obtenerUsuarioByUser(SegCabUsuario usuario);
    SegCabUsuario obtenerUsuarioByNumeroDocumento(SegCabUsuario usuario);
    void registrarUsuario(SegCabUsuario usuario);
}
