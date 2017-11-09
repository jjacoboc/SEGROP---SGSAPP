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
import pe.com.segrop.sgsapp.dao.PerfilDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;

/**
 *
 * @author JJ
 */
@Repository(value="PerfilDao")
public class PerfilDaoHibernate extends HibernateDaoSupport implements PerfilDao{
    
    /**
     * Crea una nueva instancia de PerfilDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public PerfilDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_PERFIL.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_PERFIL') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public List<SegDetPerfil> buscarPerfiles(SegDetPerfil perfil) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPerfil.class);        
        if(perfil.getNCodEmpresa()!=null && !"-1".equals(perfil.getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", perfil.getNCodEmpresa()));
        }
        if(perfil.getVNombre()!=null && !"".equals(perfil.getVNombre())){
            criteria.add(Restrictions.like("VNombre", "%"+perfil.getVNombre()+"%"));
        }
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetPerfil>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetPerfil> obtenerListaPerfiles() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPerfil.class);
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetPerfil>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetPerfil> obtenerListaPerfilesByEmpresa(SegCabEmpresa segCabEmpresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPerfil.class);
        criteria.add(Restrictions.eq("NCodEmpresa", segCabEmpresa.getNCodEmpresa()));
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetPerfil>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetPerfil> obtenerListaPerfilesActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPerfil.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetPerfil>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetPerfil> obtenerListaPerfilesActivosByEmpresa(SegCabEmpresa segCabEmpresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPerfil.class);
        criteria.add(Restrictions.eq("NCodEmpresa", segCabEmpresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetPerfil>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegDetPerfil obtenerPerfilById(SegDetPerfil perfil) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPerfil.class);
        criteria.add(Restrictions.eq("NCodPerfil", perfil.getId().getNCodPerfil()));
        return (SegDetPerfil) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public SegDetPerfil obtenerPerfilByNombre(SegDetPerfil perfil) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPerfil.class);
        criteria.add(Restrictions.eq("NCodEmpresa", perfil.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("VNombre", perfil.getVNombre()));
        return (SegDetPerfil) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarPerfil(SegDetPerfil perfil) {
        getHibernateTemplate().saveOrUpdate(perfil);
    }
}
