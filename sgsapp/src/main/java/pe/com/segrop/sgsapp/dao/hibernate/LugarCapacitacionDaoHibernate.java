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
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.LugarCapacitacionDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetLugarCapacitacion;

/**
 *
 * @author JJ
 */
@Repository(value="LugarCapacitacionDao")
public class LugarCapacitacionDaoHibernate extends HibernateDaoSupport implements LugarCapacitacionDao{

    /**
     * Crea una nueva instancia de LugarCapacitacionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public LugarCapacitacionDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_LUGAR_CAPACITACION.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_LUGAR_CAPACITACION') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public SegDetLugarCapacitacion buscarLugarCapacitacionByEmpresa(SegDetLugarCapacitacion lugar) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugarCapacitacion.class);
        criteria.add(Restrictions.eq("NCodEmpresa", lugar.getNCodEmpresa()));
        criteria.add(Restrictions.eq("VDescripcion", lugar.getVDescripcion()));
        return (SegDetLugarCapacitacion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<SegDetLugarCapacitacion> obtenerListaLugaresCapacitacion() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugarCapacitacion.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLugarCapacitacion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetLugarCapacitacion> obtenerListaLugaresCapacitacionByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetLugarCapacitacion.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetLugarCapacitacion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarLugarCapacitacion(SegDetLugarCapacitacion lugar) {
        getHibernateTemplate().saveOrUpdate(lugar);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarLugarCapacitacion(SegDetLugarCapacitacion lugar) {
        getHibernateTemplate().delete(lugar);
    }
    
}
