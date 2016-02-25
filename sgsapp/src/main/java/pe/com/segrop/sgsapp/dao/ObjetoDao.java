/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetObjeto;

/**
 *
 * @author JJ
 */
public interface ObjetoDao {
    
    public Long nextSequenceValue();    
    public List<SegDetObjeto> buscarObjetos(SegDetObjeto objeto);    
    public List<SegDetObjeto> obtenerListaObjetos();
    public List<SegDetObjeto> obtenerListaObjetosActivos();
    public void registrarObjeto(SegDetObjeto objeto);
}
