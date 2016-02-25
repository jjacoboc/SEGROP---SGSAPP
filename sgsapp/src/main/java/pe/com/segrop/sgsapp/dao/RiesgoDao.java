/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetRiesgo;

/**
 *
 * @author JJ
 */
public interface RiesgoDao {
    
    public List<SegDetRiesgo> buscarRiesgos(SegDetRiesgo riesgo);
    public List<SegDetRiesgo> buscarRiesgosMatriz(SegDetRiesgo riesgo);
    public List<SegDetRiesgo> listarRiesgosMatriz(SegDetRiesgo riesgo);
    public SegDetRiesgo getRiesgoById(String tipo, String id);
}
