/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetTipoDocumento;

/**
 *
 * @author JJ
 */
public interface TipoDocumentoDao {
    
    public Long nextSequenceValue();
    public SegDetTipoDocumento buscarTipoDocumentoByEmpresa(SegDetTipoDocumento tipoDocumento);
    public List<SegDetTipoDocumento> obtenerListaTiposDocumento();
    public List<SegDetTipoDocumento> obtenerListaTiposDocumentoByEmpresa(SegCabEmpresa empresa);
    public void registrarTipoDocumento(SegDetTipoDocumento tipoDocumento);
    public void eliminarTipoDocumento(SegDetTipoDocumento tipoDocumento);
}
