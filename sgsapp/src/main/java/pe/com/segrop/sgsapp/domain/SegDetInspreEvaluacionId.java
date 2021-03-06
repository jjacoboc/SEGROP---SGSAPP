package pe.com.segrop.sgsapp.domain;
// Generated 13-mar-2012 2:47:19 by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;

/**
 * SegDetInspreEvaluacionId generated by hbm2java
 */
public class SegDetInspreEvaluacionId implements java.io.Serializable {

    private BigDecimal NCodEvaluacion;
    private BigDecimal NCodInspresencial;
    private BigDecimal NCodEmpresa;

    public SegDetInspreEvaluacionId() {
        super();
    }

    public SegDetInspreEvaluacionId(BigDecimal NCodEvaluacion, BigDecimal NCodInspresencial, BigDecimal NCodEmpresa) {
        this.NCodEvaluacion = NCodEvaluacion;
        this.NCodInspresencial = NCodInspresencial;
        this.NCodEmpresa = NCodEmpresa;
    }

    public BigDecimal getNCodEvaluacion() {
        return this.NCodEvaluacion;
    }

    public void setNCodEvaluacion(BigDecimal NCodEvaluacion) {
        this.NCodEvaluacion = NCodEvaluacion;
    }

    public BigDecimal getNCodInspresencial() {
        return this.NCodInspresencial;
    }

    public void setNCodInspresencial(BigDecimal NCodInspresencial) {
        this.NCodInspresencial = NCodInspresencial;
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
        if (!(other instanceof SegDetInspreEvaluacionId)) {
            return false;
        }
        SegDetInspreEvaluacionId castOther = (SegDetInspreEvaluacionId) other;

        return ((this.getNCodEvaluacion() == castOther.getNCodEvaluacion()) || (this.getNCodEvaluacion() != null && castOther.getNCodEvaluacion() != null && this.getNCodEvaluacion().equals(castOther.getNCodEvaluacion())))
                && ((this.getNCodInspresencial() == castOther.getNCodInspresencial()) || (this.getNCodInspresencial() != null && castOther.getNCodInspresencial() != null && this.getNCodInspresencial().equals(castOther.getNCodInspresencial())))
                && ((this.getNCodEmpresa() == castOther.getNCodEmpresa()) || (this.getNCodEmpresa() != null && castOther.getNCodEmpresa() != null && this.getNCodEmpresa().equals(castOther.getNCodEmpresa())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getNCodEvaluacion() == null ? 0 : this.getNCodEvaluacion().hashCode());
        result = 37 * result + (getNCodInspresencial() == null ? 0 : this.getNCodInspresencial().hashCode());
        result = 37 * result + (getNCodEmpresa() == null ? 0 : this.getNCodEmpresa().hashCode());
        return result;
    }
}
