/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao;

import java.util.List;
import pe.com.segrop.sgsapp.domain.SegDetDocumento;
import pe.com.segrop.sgsapp.domain.SegDetHistorial;

/**
 *
 * @author JJ
 */
public interface DocumentoDao {
    
    public Long nextSequenceValue();
    public List<SegDetDocumento> buscarDocumentos(SegDetDocumento documento);
    public List<SegDetDocumento> buscarDocumentosDesactivados(SegDetDocumento documento);
    public List<SegDetDocumento> obtenerListaDocumentos();
    public List<SegDetDocumento> obtenerListaDocumentosActivos();
    public SegDetDocumento obtenerDocumentoById(SegDetDocumento documento);
    public void registrarDocumento(SegDetDocumento documento);
    public List<SegDetHistorial> listarHistorial(SegDetDocumento documento);
    public void eliminarDocumento(SegDetDocumento documento);
}
