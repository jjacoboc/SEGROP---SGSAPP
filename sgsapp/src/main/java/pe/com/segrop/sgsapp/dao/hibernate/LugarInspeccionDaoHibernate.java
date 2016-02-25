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
import pe.com.segrop.sgsapp.dao.LugarInspeccionDao;
import pe.com.segrop.sgsapp.domain.SegCabLugar;

/**
 *
 * @author JJ
 */
@Repository(value="LugarInspeccionDao")
public class LugarInspeccionDaoHibernate extends HibernateDaoSupport implements LugarInspeccionDao{
    
    /**
     * Crea una nueva instancia de LugarInspeccionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public LugarInspeccionDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_LUGAR_INSPECCION.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_LUGAR_INSPECCION') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public SegCabLugar buscarLugar(SegCabLugar lugar) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabLugar.class);
        if(lugar.getVDescripcion()!=null && !"".equals(lugar.getVDescripcion())){
            criteria.add(Restrictions.eq("VDescripcion", lugar.getVDescripcion()));
        }
        return (SegCabLugar) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<SegCabLugar> obtenerListaLugares() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabLugar.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegCabLugar>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegCabLugar> obtenerListaLugaresActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabLugar.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegCabLugar>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void registrarLugar(SegCabLugar lugar) {
        getHibernateTemplate().saveOrUpdate(lugar);
    }
    
    @Override
    public void eliminarLugar(SegCabLugar lugar) {
        getHibernateTemplate().delete(lugar);
    }
    
}
