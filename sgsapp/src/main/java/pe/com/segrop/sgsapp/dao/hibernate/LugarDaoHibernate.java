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
import pe.com.segrop.sgsapp.dao.LugarDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetLugar;

/**
 *
 * @author JJ
 */
@Repository(value="LugarDao")
public class LugarDaoHibernate extends HibernateDaoSupport implements LugarDao{
    
    /**
     * Crea una nueva instancia de LugarDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public LugarDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_LUGAR.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_LUGAR') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetLugar> buscarLugares(SegDetLugar lugar) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugar.class);        
        if(lugar.getId().getNCodArea()!=null && !"-1".equals(lugar.getId().getNCodArea().toString())){
            criteria.add(Restrictions.eq("NCodArea", lugar.getId().getNCodArea()));
        }
        if(lugar.getVDescripcion()!=null && !"".equals(lugar.getVDescripcion())){
            criteria.add(Restrictions.like("VDescripcion", "%"+lugar.getVDescripcion()+"%"));
        }
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLugar>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetLugar> obtenerListaLugares() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugar.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLugar>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetLugar> obtenerListaLugaresByArea(SegDetArea area) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugar.class);
        criteria.add(Restrictions.eq("NCodArea", area.getId().getNCodArea()));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLugar>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetLugar> obtenerListaLugaresActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugar.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLugar>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetLugar> obtenerListaLugaresActivosByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugar.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLugar>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetLugar> obtenerListaLugaresActivosByArea(SegDetArea area) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugar.class);
        criteria.add(Restrictions.eq("NCodArea", area.getId().getNCodArea()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLugar>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegDetLugar obtenerLugarByDescripcion(SegDetLugar lugar) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugar.class);
        criteria.add(Restrictions.eq("NCodEmpresa", lugar.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodLocal", lugar.getId().getNCodLocal()));
        criteria.add(Restrictions.eq("NCodArea", lugar.getId().getNCodArea()));
        criteria.add(Restrictions.eq("VDescripcion", lugar.getVDescripcion()));
        return (SegDetLugar) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public void registrarLugar(SegDetLugar lugar) {
        getHibernateTemplate().saveOrUpdate(lugar);
    }
    
    @Override
    public void eliminarLugar(SegDetLugar lugar) {
        getHibernateTemplate().delete(lugar);
    }
}
