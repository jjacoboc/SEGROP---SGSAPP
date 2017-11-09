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
import pe.com.segrop.sgsapp.dao.InsPreAccionDao;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;
import pe.com.segrop.sgsapp.domain.SegDetInspreAcciones;

/**
 *
 * @author JJ
 */
@Repository(value="InsPreAccionDao")
public class InsPreAccionDaoHibernate extends HibernateDaoSupport implements InsPreAccionDao{
    
    /**
     * Crea una nueva instancia de InsPreAccionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public InsPreAccionDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_INSPRE_ACCION.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_INSPRE_ACCION') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetInspreAcciones> obtenerListaAccionesByInspeccionPresencial(SegDetInsPresencial insPresencial) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInspreAcciones.class);
        criteria.add(Restrictions.eq("NCodInspresencial", insPresencial.getId().getNCodInspresencial()));
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetInspreAcciones>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarAccion(SegDetInspreAcciones accion) {
        getHibernateTemplate().saveOrUpdate(accion);
    }
    
}
