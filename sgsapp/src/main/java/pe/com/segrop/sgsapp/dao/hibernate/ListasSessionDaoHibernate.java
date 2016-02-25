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
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.com.segrop.sgsapp.dao.ListasSessionDao;
import pe.com.segrop.sgsapp.domain.SegCabMaestro;
import pe.com.segrop.sgsapp.domain.SegDetMaestrodetalle;

/**
 *
 * @author JJ
 */
@Repository(value="ListasSessionDao")
public class ListasSessionDaoHibernate extends HibernateDaoSupport implements ListasSessionDao{

    /**
     * Crea una nueva instancia de ListasSessionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public ListasSessionDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public Long getNextPk() {
        return (Long)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return (Long) session.createSQLQuery("select MAX(N_COD_MAESTRODETALLE)+1 as id from SGSWEB.SEG_DET_MAESTRODETALLE").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public SegDetMaestrodetalle buscarMaestroDetalle(SegDetMaestrodetalle maestroDetalle) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetMaestrodetalle.class);        
        if(maestroDetalle.getNCodMaestrodetalle()!=null){
            criteria.add(Restrictions.eq("NCodMaestrodetalle", maestroDetalle.getNCodMaestrodetalle()));
        }
        if(maestroDetalle.getVDescripcion()!=null && !"".equals(maestroDetalle.getVDescripcion())){
            criteria.add(Restrictions.like("VDescripcion", "%"+maestroDetalle.getVDescripcion()+"%"));
        }
        criteria.addOrder(Order.asc("VDescripcion"));
        getHibernateTemplate().setCacheQueries(true);
        return (SegDetMaestrodetalle) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<SegDetMaestrodetalle> obtenerMaestroDetalle(SegCabMaestro maestro) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetMaestrodetalle.class);
        criteria.add(Restrictions.eq("NCodMaestro", maestro.getNCodMaestro()));
        criteria.addOrder(Order.asc("NCodMaestrodetalle"));
        getHibernateTemplate().setCacheQueries(true);
        return (List<SegDetMaestrodetalle>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetMaestrodetalle> obtenerMaestroDetalleOrderDesc(SegCabMaestro maestro) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetMaestrodetalle.class);
        criteria.add(Restrictions.eq("NCodMaestro", maestro.getNCodMaestro()));
        criteria.addOrder(Order.desc("NCodMaestrodetalle"));
        getHibernateTemplate().setCacheQueries(true);
        return (List<SegDetMaestrodetalle>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void registrarMaestroDetalle(SegDetMaestrodetalle maestroDetalle) {
        getHibernateTemplate().saveOrUpdate(maestroDetalle);
    }
    
    @Override
    public void eliminarMaestroDetalle(SegDetMaestrodetalle maestroDetalle) {
        getHibernateTemplate().delete(maestroDetalle);
    }
}
