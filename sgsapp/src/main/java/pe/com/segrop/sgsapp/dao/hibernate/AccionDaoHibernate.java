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
import pe.com.segrop.sgsapp.dao.AccionDao;
import pe.com.segrop.sgsapp.domain.SegDetAcciones;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;

/**
 *
 * @author JJ
 */
@Repository(value="AccionDao")
public class AccionDaoHibernate extends HibernateDaoSupport implements AccionDao{
    
    /**
     * Crea una nueva instancia de AccionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public AccionDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_ACCIONES.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_ACCIONES') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SegDetAcciones> obtenerListaAccionesByNovedad(SegDetNovedad novedad) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetAcciones.class);
        criteria.add(Restrictions.eq("NCodEmpresa", novedad.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodNovedad", novedad.getId().getNCodNovedad()));
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetAcciones>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarAccion(SegDetAcciones accion) {
        getHibernateTemplate().saveOrUpdate(accion);
    }
    
}
