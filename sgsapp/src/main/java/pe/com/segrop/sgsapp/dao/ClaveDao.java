/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetClave;

/**
 *
 * @author JJ
 */
public interface ClaveDao {
    
    public Long nextSequenceValue();
    public SegDetClave obtenerClaveActiva(SegCabUsuario usuario);
    public void registrarClave(SegDetClave clave);
}
