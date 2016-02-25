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
import pe.com.segrop.sgsapp.dao.ResponsableInspeccionDao;
import pe.com.segrop.sgsapp.domain.SegCabResponsable;

/**
 *
 * @author JJ
 */
@Repository(value="ResponsableInspeccionDao")
public class ResponsableInspeccionDaoHibernate extends HibernateDaoSupport implements ResponsableInspeccionDao{
    
    /**
     * Crea una nueva instancia de ResponsableInspeccionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public ResponsableInspeccionDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_RESPONSABLE_INSPECCION.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_RESPONSABLE_INSPECCION') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public SegCabResponsable buscarResponsable(SegCabResponsable responsable) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabResponsable.class);
        criteria.add(Restrictions.eq("VNombres", responsable.getVNombres()));
        criteria.add(Restrictions.eq("VApellidos", responsable.getVApellidos()));
        return (SegCabResponsable) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<SegCabResponsable> obtenerListaResponsables() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabResponsable.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegCabResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegCabResponsable> obtenerListaResponsablesActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabResponsable.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegCabResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void registrarResponsable(SegCabResponsable responsable) {
        getHibernateTemplate().saveOrUpdate(responsable);
    }
    
    @Override
    public void eliminarResponsable(SegCabResponsable responsable) {
        getHibernateTemplate().delete(responsable);
    }
    
}
