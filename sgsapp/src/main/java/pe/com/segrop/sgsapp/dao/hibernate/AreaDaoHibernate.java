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
import pe.com.segrop.sgsapp.dao.AreaDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetLocal;

/**
 *
 * @author JJ
 */
@Repository(value="AreaDao")
public class AreaDaoHibernate extends HibernateDaoSupport implements AreaDao{
    
    /**
     * Crea una nueva instancia de AreaDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public AreaDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_AREA.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_AREA') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetArea> buscarAreas(SegDetArea area) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetArea.class);
        if(area.getId().getNCodEmpresa()!=null && !"-1".equals(area.getId().getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", area.getId().getNCodEmpresa()));
        }
        if(area.getId().getNCodLocal()!=null && !"-1".equals(area.getId().getNCodLocal().toString())){
            criteria.add(Restrictions.eq("NCodLocal", area.getId().getNCodLocal()));
        }
        if(area.getVDescripcion()!=null && !"".equals(area.getVDescripcion())){
            criteria.add(Restrictions.like("VDescripcion", "%"+area.getVDescripcion()+"%"));
        }
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetArea>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetArea> obtenerListaAreas() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetArea.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetArea>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetArea> obtenerListaAreasByLocal(SegDetLocal local) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetArea.class);
        criteria.add(Restrictions.eq("NCodLocal", local.getId().getNCodLocal()));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetArea>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetArea> obtenerListaAreasByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetArea.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetArea>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetArea> obtenerListaAreasActivas() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetArea.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetArea>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetArea> obtenerListaAreasActivasByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetArea.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetArea>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetArea> obtenerListaAreasActivasByLocal(SegDetLocal local) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetArea.class);
        criteria.add(Restrictions.eq("NCodLocal", local.getId().getNCodLocal()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetArea>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegDetArea obtenerAreaByDescripcion(SegDetArea area) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetArea.class);
        criteria.add(Restrictions.eq("NCodEmpresa", area.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodLocal", area.getId().getNCodLocal()));
        criteria.add(Restrictions.eq("VDescripcion", area.getVDescripcion()));
        return (SegDetArea) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarArea(SegDetArea area) {
        getHibernateTemplate().saveOrUpdate(area);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void eliminarArea(SegDetArea area) {
        getHibernateTemplate().delete(area);
    }
}
