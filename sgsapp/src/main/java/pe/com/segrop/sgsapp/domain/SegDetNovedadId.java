package pe.com.segrop.sgsapp.domain;
// Generated 12-mar-2012 0:45:44 by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;

/**
 * SegDetNovedadId generated by hbm2java
 */
public class SegDetNovedadId implements java.io.Serializable {

    private BigDecimal NCodNovedad;
    private BigDecimal NCodEmpresa;

    public SegDetNovedadId() {
        super();
    }

    public SegDetNovedadId(BigDecimal NCodNovedad, BigDecimal NCodEmpresa) {
        this.NCodNovedad = NCodNovedad;
        this.NCodEmpresa = NCodEmpresa;
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
        if (!(other instanceof SegDetNovedadId)) {
            return false;
        }
        SegDetNovedadId castOther = (SegDetNovedadId) other;

        return ((this.getNCodNovedad() == castOther.getNCodNovedad()) || (this.getNCodNovedad() != null && castOther.getNCodNovedad() != null && this.getNCodNovedad().equals(castOther.getNCodNovedad())))
                && ((this.getNCodEmpresa() == castOther.getNCodEmpresa()) || (this.getNCodEmpresa() != null && castOther.getNCodEmpresa() != null && this.getNCodEmpresa().equals(castOther.getNCodEmpresa())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getNCodNovedad() == null ? 0 : this.getNCodNovedad().hashCode());
        result = 37 * result + (getNCodEmpresa() == null ? 0 : this.getNCodEmpresa().hashCode());
        return result;
    }
}