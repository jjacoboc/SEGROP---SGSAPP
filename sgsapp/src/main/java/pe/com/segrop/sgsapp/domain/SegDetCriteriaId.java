package pe.com.segrop.sgsapp.domain;
// Generated 27-may-2012 20:47:48 by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;

/**
 * SegDetParticipanteId generated by hbm2java
 */
public class SegDetCriteriaId implements java.io.Serializable {

    private BigDecimal NCodEmpresa;
    private BigDecimal NCodExport;
    private BigDecimal NCodCriteria;

    public SegDetCriteriaId() {
        super();
    }

    public SegDetCriteriaId(BigDecimal NCodEmpresa, BigDecimal NCodExport, BigDecimal NCodCriteria) {
        this.NCodEmpresa = NCodEmpresa;
        this.NCodExport = NCodExport;
        this.NCodCriteria = NCodCriteria;
    }

    public BigDecimal getNCodEmpresa() {
        return this.NCodEmpresa;
    }

    public void setNCodEmpresa(BigDecimal NCodEmpresa) {
        this.NCodEmpresa = NCodEmpresa;
    }

    public BigDecimal getNCodExport() {
        return this.NCodExport;
    }

    public void setNCodExport(BigDecimal NCodExport) {
        this.NCodExport = NCodExport;
    }

    public BigDecimal getNCodCriteria() {
        return this.NCodCriteria;
    }

    public void setNCodCriteria(BigDecimal NCodCriteria) {
        this.NCodCriteria = NCodCriteria;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof SegDetCriteriaId)) {
            return false;
        }
        SegDetCriteriaId castOther = (SegDetCriteriaId) other;

        return ((this.getNCodEmpresa() == castOther.getNCodEmpresa()) || (this.getNCodEmpresa() != null && castOther.getNCodEmpresa() != null && this.getNCodEmpresa().equals(castOther.getNCodEmpresa())))
                && ((this.getNCodExport() == castOther.getNCodExport()) || (this.getNCodExport() != null && castOther.getNCodExport() != null && this.getNCodExport().equals(castOther.getNCodExport())))
                && ((this.getNCodCriteria() == castOther.getNCodCriteria()) || (this.getNCodCriteria() != null && castOther.getNCodCriteria() != null && this.getNCodCriteria().equals(castOther.getNCodCriteria())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getNCodEmpresa() == null ? 0 : this.getNCodEmpresa().hashCode());
        result = 37 * result + (getNCodExport() == null ? 0 : this.getNCodExport().hashCode());
        result = 37 * result + (getNCodCriteria() == null ? 0 : this.getNCodCriteria().hashCode());
        return result;
    }
}
