/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import pe.com.segrop.sgsapp.domain.SegDetCriteria;
import pe.com.segrop.sgsapp.domain.SegDetExport;

/**
 *
 * @author JJ
 */
public interface CriteriaDao {
    
    public Long nextSequenceValue();
    public void registrarCriteria(SegDetCriteria criteria);
    public void eliminarCriteriaById(SegDetExport export);
}
