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
import pe.com.segrop.sgsapp.dao.LocalDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetLocal;

/**
 *
 * @author JJ
 */
@Repository(value="LocalDao")
public class LocalDaoHibernate extends HibernateDaoSupport implements LocalDao{
    
    /**
     * Crea una nueva instancia de LocalDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public LocalDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_LOCAL.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_LOCAL') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetLocal> buscarLocales(SegDetLocal local) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLocal.class);
        if(local.getId().getNCodEmpresa()!=null && !"-1".equals(local.getId().getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", local.getId().getNCodEmpresa()));
        }
        if(local.getVDescripcion()!=null && !"".equals(local.getVDescripcion())){
            criteria.add(Restrictions.like("VDescripcion", "%"+local.getVDescripcion()+"%"));
        }
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLocal>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetLocal> obtenerListaLocales() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLocal.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLocal>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetLocal> obtenerListaLocalesByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLocal.class);
        criteria.add(Restrictions.eq("id.NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLocal>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetLocal> obtenerListaLocalesActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLocal.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLocal>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetLocal> obtenerListaLocalesActivosByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLocal.class);
        criteria.add(Restrictions.eq("id.NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLocal>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegDetLocal obtenerLocalByDescripcion(SegDetLocal local) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLocal.class);
        criteria.add(Restrictions.eq("id.NCodEmpresa", local.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("VDescripcion", local.getVDescripcion()));
        return (SegDetLocal) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarLocal(SegDetLocal local) {
        getHibernateTemplate().saveOrUpdate(local);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void eliminarLocal(SegDetLocal local) {
        getHibernateTemplate().delete(local);
    }
    
}
