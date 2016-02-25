/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDao;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;
import pe.com.segrop.sgsapp.domain.SegDetInspreEvaluacion;

/**
 *
 * @author JJ
 */
@Repository(value="InspreEvaluacionDao")
public class InspreEvaluacionDaoHibernate extends HibernateDaoSupport implements InspreEvaluacionDao{

    /**
     * Crea una nueva instancia de InspreEvaluacionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public InspreEvaluacionDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_INSPRE_EVALUACION.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_INSPRE_EVALUACION') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public SegDetInspreEvaluacion obtenerEvaluacionInspeccion(SegDetInsPresencial presencial) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInspreEvaluacion.class);
        criteria.add(Restrictions.eq("NCodEmpresa", presencial.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodInspresencial", presencial.getId().getNCodInspresencial()));
        return (SegDetInspreEvaluacion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public void registrarEvaluacion(SegDetInspreEvaluacion evaluacion) {
        getHibernateTemplate().saveOrUpdate(evaluacion);
    }
    
}
