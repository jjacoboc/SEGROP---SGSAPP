/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetInspreevalDetalle;

/**
 *
 * @author JJ
 */
@Repository(value="InspreEvaluacionDetalleDao")
public class InspreEvaluacionDetalleDaoHibernate extends HibernateDaoSupport implements InspreEvaluacionDetalleDao{

    /**
     * Crea una nueva instancia de InspreEvaluacionDetalleDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public InspreEvaluacionDetalleDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_INSPREEVAL_DETALLE.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_INSPREEVAL_DETALLE') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetInspreevalDetalle> obtenerListaDetalleEvaluacionInspeccion(SegDetInspreEvaluacion evaluacion) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInspreevalDetalle.class);
        criteria.add(Restrictions.eq("NCodEmpresa", evaluacion.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodEvaluacion", evaluacion.getId().getNCodEvaluacion()));
        criteria.add(Restrictions.eq("NCodInspresencial", evaluacion.getId().getNCodInspresencial()));
        criteria.addOrder(Order.desc("DFechora"));
        return (List<SegDetInspreevalDetalle>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarEvaluacionDetalle(SegDetInspreevalDetalle detalle) {
        getHibernateTemplate().saveOrUpdate(detalle);
    }
    
}
