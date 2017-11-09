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
import pe.com.segrop.sgsapp.dao.CargoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetCargo;

/**
 *
 * @author JJ
 */
@Repository(value="CargoDao")
public class CargoDaoHibernate extends HibernateDaoSupport implements CargoDao{
    
    /**
     * Crea una nueva instancia de CargoDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public CargoDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_CARGO.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_CARGO') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetCargo> buscarCargos(SegDetCargo cargo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCargo.class);        
        if(cargo.getNCodEmpresa()!=null && !"-1".equals(cargo.getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", cargo.getNCodEmpresa()));
        }
        if(cargo.getVDescripcion()!=null && !"".equals(cargo.getVDescripcion())){
            criteria.add(Restrictions.like("VDescripcion", "%"+cargo.getVDescripcion()+"%"));
        }
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetCargo>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetCargo> obtenerListaCargos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCargo.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetCargo>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetCargo> obtenerListaCargosByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCargo.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetCargo>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetCargo> obtenerListaCargosActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCargo.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetCargo>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetCargo> obtenerListaCargosActivosByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCargo.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetCargo>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegDetCargo obtenerCargoByDescripcion(SegDetCargo cargo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCargo.class);
        criteria.add(Restrictions.eq("NCodEmpresa", cargo.getNCodEmpresa()));
        criteria.add(Restrictions.eq("VDescripcion", cargo.getVDescripcion()));
        return (SegDetCargo) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarCargo(SegDetCargo cargo) {
        getHibernateTemplate().saveOrUpdate(cargo);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarCargo(SegDetCargo cargo) {
        getHibernateTemplate().delete(cargo);
    }
    
    
}
