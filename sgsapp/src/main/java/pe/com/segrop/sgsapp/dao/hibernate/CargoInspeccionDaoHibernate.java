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
import pe.com.segrop.sgsapp.dao.CargoInspeccionDao;
import pe.com.segrop.sgsapp.domain.SegCabCargo;

/**
 *
 * @author JJ
 */
@Repository(value="CargoInspeccionDao")
public class CargoInspeccionDaoHibernate extends HibernateDaoSupport implements CargoInspeccionDao{
    
    /**
     * Crea una nueva instancia de CargoInspeccionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public CargoInspeccionDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_CARGO_INSPECCION.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_CARGO_INSPECCION') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public SegCabCargo buscarCargo(SegCabCargo cargo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabCargo.class);
        if(cargo.getVDescripcion()!=null && !"".equals(cargo.getVDescripcion())){
            criteria.add(Restrictions.eq("VDescripcion", cargo.getVDescripcion()));
        }
        return (SegCabCargo) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<SegCabCargo> obtenerListaCargos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabCargo.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegCabCargo>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegCabCargo> obtenerListaCargosActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabCargo.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegCabCargo>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void registrarCargo(SegCabCargo cargo) {
        getHibernateTemplate().saveOrUpdate(cargo);
    }
    
    @Override
    public void eliminarCargo(SegCabCargo cargo) {
        getHibernateTemplate().delete(cargo);
    }
}
