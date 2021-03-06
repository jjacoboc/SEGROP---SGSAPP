package pe.com.segrop.sgsapp.domain;
// Generated 13-mar-2012 2:47:19 by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * SegDetInspreAcciones generated by hbm2java
 */
public class SegDetInspreAcciones implements java.io.Serializable {

    private SegDetInspreAccionesId id;
    private SegDetInsPresencial segDetInsPresencial;
    private BigDecimal NCodAccion;
    private BigDecimal NCodInspresencial;
    private BigDecimal NCodEmpresa;
    private String VDescripcion;
    private Date DFecHora;
    private String VUsuCreacion;
    private Date DFecCreacion;
    private String VIpCreacion;
    private String VUsuModificacion;
    private Date DFecModificacion;
    private String VIpModificacion;

    public SegDetInspreAcciones() {
        super();
    }

    public SegDetInspreAcciones(SegDetInspreAccionesId id, SegDetInsPresencial segDetInsPresencial) {
        this.id = id;
        this.segDetInsPresencial = segDetInsPresencial;
    }

    public SegDetInspreAcciones(SegDetInspreAccionesId id, SegDetInsPresencial segDetInsPresencial, BigDecimal NCodAccion, BigDecimal NCodInspresencial, BigDecimal NCodEmpresa, String VDescripcion, Date DFecHora, String VUsuCreacion, Date DFecCreacion, String VIpCreacion, String VUsuModificacion, Date DFecModificacion, String VIpModificacion) {
        this.id = id;
        this.segDetInsPresencial = segDetInsPresencial;
        this.NCodAccion = NCodAccion;
        this.NCodInspresencial = NCodInspresencial;
        this.NCodEmpresa = NCodEmpresa;
        this.VDescripcion = VDescripcion;
        this.DFecHora = DFecHora;
        this.VUsuCreacion = VUsuCreacion;
        this.DFecCreacion = DFecCreacion;
        this.VIpCreacion = VIpCreacion;
        this.VUsuModificacion = VUsuModificacion;
        this.DFecModificacion = DFecModificacion;
        this.VIpModificacion = VIpModificacion;
    }

    public SegDetInspreAccionesId getId() {
        return this.id;
    }

    public void setId(SegDetInspreAccionesId id) {
        this.id = id;
    }

    public SegDetInsPresencial getSegDetInsPresencial() {
        return this.segDetInsPresencial;
    }

    public void setSegDetInsPresencial(SegDetInsPresencial segDetInsPresencial) {
        this.segDetInsPresencial = segDetInsPresencial;
    }

    public BigDecimal getNCodAccion() {
        return NCodAccion;
    }

    public void setNCodAccion(BigDecimal NCodAccion) {
        this.NCodAccion = NCodAccion;
    }

    public BigDecimal getNCodInspresencial() {
        return NCodInspresencial;
    }

    public void setNCodInspresencial(BigDecimal NCodInspresencial) {
        this.NCodInspresencial = NCodInspresencial;
    }

    public BigDecimal getNCodEmpresa() {
        return NCodEmpresa;
    }

    public void setNCodEmpresa(BigDecimal NCodEmpresa) {
        this.NCodEmpresa = NCodEmpresa;
    }

    public String getVDescripcion() {
        return this.VDescripcion;
    }

    public void setVDescripcion(String VDescripcion) {
        this.VDescripcion = VDescripcion;
    }

    public Date getDFecHora() {
        return this.DFecHora;
    }

    public void setDFecHora(Date DFecHora) {
        this.DFecHora = DFecHora;
    }

    public String getVUsuCreacion() {
        return this.VUsuCreacion;
    }

    public void setVUsuCreacion(String VUsuCreacion) {
        this.VUsuCreacion = VUsuCreacion;
    }

    public Date getDFecCreacion() {
        return this.DFecCreacion;
    }

    public void setDFecCreacion(Date DFecCreacion) {
        this.DFecCreacion = DFecCreacion;
    }

    public String getVIpCreacion() {
        return this.VIpCreacion;
    }

    public void setVIpCreacion(String VIpCreacion) {
        this.VIpCreacion = VIpCreacion;
    }

    public String getVUsuModificacion() {
        return this.VUsuModificacion;
    }

    public void setVUsuModificacion(String VUsuModificacion) {
        this.VUsuModificacion = VUsuModificacion;
    }

    public Date getDFecModificacion() {
        return this.DFecModificacion;
    }

    public void setDFecModificacion(Date DFecModificacion) {
        this.DFecModificacion = DFecModificacion;
    }

    public String getVIpModificacion() {
        return this.VIpModificacion;
    }

    public void setVIpModificacion(String VIpModificacion) {
        this.VIpModificacion = VIpModificacion;
    }
}
