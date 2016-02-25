/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;

/**
 *
 * @author JJ
 */
public interface UsuarioDao {
    
    public Long nextSequenceValue();
    public List<SegCabUsuario> buscarUsuarios(SegCabUsuario usuario);
    public List<SegCabUsuario> buscarUsuariosActivos(SegCabUsuario usuario);
    public List<SegCabUsuario> obtenerListaUsuarios();
    public List<SegCabUsuario> obtenerListaUsuariosActivos();
    public SegCabUsuario obtenerUsuarioByUser(SegCabUsuario usuario);
    public SegCabUsuario obtenerUsuarioByNumeroDocumento(SegCabUsuario usuario);
    public void registrarUsuario(SegCabUsuario usuario);
}
