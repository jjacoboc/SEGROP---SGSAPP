package pe.com.segrop.sgsapp.domain;
// Generated 08-abr-2012 18:19:08 by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;

/**
 * SegDetNovevalDetalleId generated by hbm2java
 */
public class SegDetNovevalDetalleId implements java.io.Serializable {

    private BigDecimal NCodDetalle;
    private BigDecimal NCodEvaluacion;
    private BigDecimal NCodNovedad;
    private BigDecimal NCodEmpresa;

    public SegDetNovevalDetalleId() {
        super();
    }

    public SegDetNovevalDetalleId(BigDecimal NCodDetalle, BigDecimal NCodEvaluacion, BigDecimal NCodNovedad, BigDecimal NCodEmpresa) {
        this.NCodDetalle = NCodDetalle;
        this.NCodEvaluacion = NCodEvaluacion;
        this.NCodNovedad = NCodNovedad;
        this.NCodEmpresa = NCodEmpresa;
    }

    public BigDecimal getNCodDetalle() {
        return this.NCodDetalle;
    }

    public void setNCodDetalle(BigDecimal NCodDetalle) {
        this.NCodDetalle = NCodDetalle;
    }

    public BigDecimal getNCodEvaluacion() {
        return this.NCodEvaluacion;
    }

    public void setNCodEvaluacion(BigDecimal NCodEvaluacion) {
        this.NCodEvaluacion = NCodEvaluacion;
    }

    public BigDecimal getNCodNovedad() {
        return this.NCodNovedad;
    }

    public void setNCodNovedad(BigDecimal NCodNovedad) {
        this.NCodNovedad = NCodNovedad;
    }

    public BigDecimal getNCodEmpresa() {
        return this.NCodEmpresa;
    }

    public void setNCodEmpresa(BigDecimal NCodEmpresa) {
        this.NCodEmpresa = NCodEmpresa;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof SegDetNovevalDetalleId)) {
            return false;
        }
        SegDetNovevalDetalleId castOther = (SegDetNovevalDetalleId) other;

        return ((this.getNCodDetalle() == castOther.getNCodDetalle()) || (this.getNCodDetalle() != null && castOther.getNCodDetalle() != null && this.getNCodDetalle().equals(castOther.getNCodDetalle())))
                && ((this.getNCodEvaluacion() == castOther.getNCodEvaluacion()) || (this.getNCodEvaluacion() != null && castOther.getNCodEvaluacion() != null && this.getNCodEvaluacion().equals(castOther.getNCodEvaluacion())))
                && ((this.getNCodNovedad() == castOther.getNCodNovedad()) || (this.getNCodNovedad() != null && castOther.getNCodNovedad() != null && this.getNCodNovedad().equals(castOther.getNCodNovedad())))
                && ((this.getNCodEmpresa() == castOther.getNCodEmpresa()) || (this.getNCodEmpresa() != null && castOther.getNCodEmpresa() != null && this.getNCodEmpresa().equals(castOther.getNCodEmpresa())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getNCodDetalle() == null ? 0 : this.getNCodDetalle().hashCode());
        result = 37 * result + (getNCodEvaluacion() == null ? 0 : this.getNCodEvaluacion().hashCode());
        result = 37 * result + (getNCodNovedad() == null ? 0 : this.getNCodNovedad().hashCode());
        result = 37 * result + (getNCodEmpresa() == null ? 0 : this.getNCodEmpresa().hashCode());
        return result;
    }
}