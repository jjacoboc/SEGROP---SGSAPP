/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.TipoDocumentoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetTipoDocumento;

/**
 *
 * @author JJ
 */
@Repository(value="TipoDocumentoDao")
public class TipoDocumentoDaoHibernate extends HibernateDaoSupport implements TipoDocumentoDao{
    
    /**
     * Crea una nueva instancia de TipoDocumentoDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public TipoDocumentoDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    /**
     * Devuelve el siguiente valor de la secuencia en consulta.
     * @return El valor de la secuencia.
     */
    @Override
    public Long nextSequenceValue() {
        return (Long)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_TIPODOCUMENTO.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_TIPODOCUMENTO') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public SegDetTipoDocumento buscarTipoDocumentoByEmpresa(SegDetTipoDocumento tipoDocumento){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetTipoDocumento.class);
        criteria.add(Restrictions.eq("NCodEmpresa", tipoDocumento.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.add(Restrictions.eq("VDescripcion", tipoDocumento.getVDescripcion()));
        return (SegDetTipoDocumento) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<SegDetTipoDocumento> obtenerListaTiposDocumento() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetTipoDocumento.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetTipoDocumento>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetTipoDocumento> obtenerListaTiposDocumentoByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetTipoDocumento.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetTipoDocumento>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarTipoDocumento(SegDetTipoDocumento tipoDocumento) {
        getHibernateTemplate().saveOrUpdate(tipoDocumento);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarTipoDocumento(SegDetTipoDocumento tipoDocumento) {
        getHibernateTemplate().delete(tipoDocumento);
    }
}
