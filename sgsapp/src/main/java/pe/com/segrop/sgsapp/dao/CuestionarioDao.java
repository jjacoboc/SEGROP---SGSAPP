/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import pe.com.segrop.sgsapp.domain.SegRelCuestionario;

/**
 *
 * @author JJ
 */
public interface CuestionarioDao {
    
    public Long nextSequenceValue();
    public void registrarCuestionario(SegRelCuestionario cuestionario);
}
