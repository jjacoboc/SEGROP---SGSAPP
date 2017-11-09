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
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDao;
import pe.com.segrop.sgsapp.domain.SegDetNovEvaluacion;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;

/**
 *
 * @author JJ
 */
@Repository(value="NovedadEvaluacionDao")
public class NovedadEvaluacionDaoHibernate extends HibernateDaoSupport implements NovedadEvaluacionDao{

    /**
     * Crea una nueva instancia de NovedadEvaluacionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public NovedadEvaluacionDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_NOV_EVALUACION.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_NOV_EVALUACION') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public SegDetNovEvaluacion obtenerEvaluacionNovedad(SegDetNovedad novedad) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetNovEvaluacion.class);
        criteria.add(Restrictions.eq("NCodEmpresa", novedad.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodNovedad", novedad.getId().getNCodNovedad()));
        return (SegDetNovEvaluacion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarEvaluacion(SegDetNovEvaluacion evaluacion) {
        getHibernateTemplate().saveOrUpdate(evaluacion);
    }
}
