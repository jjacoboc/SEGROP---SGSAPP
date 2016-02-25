/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabMaestro;
import pe.com.segrop.sgsapp.domain.SegDetMaestrodetalle;

/**
 *
 * @author JJ
 */
public interface ListasSessionDao {
    
    public Long getNextPk();
    public SegDetMaestrodetalle buscarMaestroDetalle(SegDetMaestrodetalle maestroDetalle);
    public List<SegDetMaestrodetalle> obtenerMaestroDetalle(SegCabMaestro maestro);
    public List<SegDetMaestrodetalle> obtenerMaestroDetalleOrderDesc(SegCabMaestro maestro);
    public void registrarMaestroDetalle(SegDetMaestrodetalle maestroDetalle);
    public void eliminarMaestroDetalle(SegDetMaestrodetalle maestroDetalle);
}
