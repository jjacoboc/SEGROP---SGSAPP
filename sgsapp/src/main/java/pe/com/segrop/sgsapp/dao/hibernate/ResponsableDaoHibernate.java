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
import pe.com.segrop.sgsapp.dao.ResponsableDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetArea;
import pe.com.segrop.sgsapp.domain.SegDetResponsable;

/**
 *
 * @author JJ
 */
@Repository(value="ResponsableDao")
public class ResponsableDaoHibernate extends HibernateDaoSupport implements ResponsableDao{
    
    /**
     * Crea una nueva instancia de ResponsableDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public ResponsableDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_RESPONSABLE.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_RESPONSABLE') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetResponsable> buscarResponsables(SegDetResponsable responsable) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetResponsable.class);
        if(responsable.getId().getNCodEmpresa()!=null && !"-1".equals(responsable.getId().getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", responsable.getId().getNCodEmpresa()));
        }
        if(responsable.getId().getNCodLocal()!=null && !"-1".equals(responsable.getId().getNCodLocal().toString())){
            criteria.add(Restrictions.eq("NCodLocal", responsable.getId().getNCodLocal()));
        }
        if(responsable.getId().getNCodArea()!=null && !"-1".equals(responsable.getId().getNCodArea().toString())){
            criteria.add(Restrictions.eq("NCodArea", responsable.getId().getNCodArea()));
        }
        if(responsable.getVNombres()!=null && !"".equals(responsable.getVNombres())){
            criteria.add(Restrictions.like("VNombres", "%"+responsable.getVNombres()+"%"));
        }
        if(responsable.getVApellidos()!=null && !"".equals(responsable.getVApellidos())){
            criteria.add(Restrictions.like("VApellidos", "%"+responsable.getVApellidos()+"%"));
        }
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegDetResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetResponsable> obtenerListaResponsables() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetResponsable.class);
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegDetResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetResponsable> obtenerListaResponsablesByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetResponsable.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegDetResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetResponsable> obtenerListaResponsablesByArea(SegDetArea area) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetResponsable.class);
        criteria.add(Restrictions.eq("NCodArea", area.getId().getNCodArea()));
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegDetResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetResponsable> obtenerListaResponsablesActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetResponsable.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegDetResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetResponsable> obtenerListaResponsablesActivosByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetResponsable.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegDetResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetResponsable> obtenerListaResponsablesActivosByArea(SegDetArea area) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetResponsable.class);
        criteria.add(Restrictions.eq("NCodArea", area.getId().getNCodArea()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegDetResponsable>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegDetResponsable obtenerResponsableByNombreApellido(SegDetResponsable responsable) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetResponsable.class);
        criteria.add(Restrictions.eq("NCodEmpresa", responsable.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodLocal", responsable.getId().getNCodLocal()));
        criteria.add(Restrictions.eq("NCodArea", responsable.getId().getNCodArea()));
        criteria.add(Restrictions.eq("VNombres", responsable.getVNombres()));
        criteria.add(Restrictions.eq("VApellidos", responsable.getVApellidos()));
        return (SegDetResponsable) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarResponsable(SegDetResponsable responsable) {
        getHibernateTemplate().saveOrUpdate(responsable);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarResponsable(SegDetResponsable responsable) {
        getHibernateTemplate().delete(responsable);
    }
    
}
