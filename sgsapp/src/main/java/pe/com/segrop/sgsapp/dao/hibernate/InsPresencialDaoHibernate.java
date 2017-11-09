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
import pe.com.segrop.sgsapp.dao.InsPresencialDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;

/**
 *
 * @author JJ
 */
@Repository(value = "InsPresencialDao")
public class InsPresencialDaoHibernate extends HibernateDaoSupport implements InsPresencialDao {

    /**
     * Crea una nueva instancia de InsPresencialDaoHibernate
     *
     * @param sessionFactory
     */
    @Autowired
    public InsPresencialDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    /**
     * Devuelve el siguiente valor de la secuencia en consulta.
     *
     * @return El valor de la secuencia.
     */
    @Override
    public Long nextSequenceValue() {
        return (Long) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        //return (Long) session.createSQLQuery("select SGSWEB.SEQ_INSPRESENCIAL.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                        return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_INSPRESENCIAL') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                    }
                });
    }

    @Override
    public List<SegDetInsPresencial> buscarInspeccionesPresenciales(SegDetInsPresencial insPresencial) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInsPresencial.class);
        if (insPresencial.getNCodEmpresa() != null && !"-1".equals(insPresencial.getNCodEmpresa().toString())) {
            criteria.add(Restrictions.eq("NCodEmpresa", insPresencial.getNCodEmpresa()));
        }
        if (insPresencial.getNTipoInspeccion() != null && !"-1".equals(insPresencial.getNTipoInspeccion().toString())) {
            criteria.add(Restrictions.eq("NTipoInspeccion", insPresencial.getNTipoInspeccion()));
        }
        if (insPresencial.getNLocal() != null && !"-1".equals(insPresencial.getNLocal().toString())) {
            criteria.add(Restrictions.eq("NLocal", insPresencial.getNLocal()));
        }
        if (insPresencial.getNArea() != null && !"-1".equals(insPresencial.getNArea().toString())) {
            criteria.add(Restrictions.eq("NArea", insPresencial.getNArea()));
        }
        if (insPresencial.getNLugar() != null && !"-1".equals(insPresencial.getNLugar().toString())) {
            criteria.add(Restrictions.eq("NLugar", insPresencial.getNLugar()));
        }
        if (insPresencial.getNResponsable() != null && !"-1".equals(insPresencial.getNResponsable().toString())) {
            criteria.add(Restrictions.eq("NResponsable", insPresencial.getNResponsable()));
        }
        if (insPresencial.getNCargo() != null && !"-1".equals(insPresencial.getNCargo().toString())) {
            criteria.add(Restrictions.eq("NCargo", insPresencial.getNCargo()));
        }
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetInsPresencial>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public SegDetInsPresencial obtenerInspeccionPresencialById(SegDetInsPresencial insPresencial) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInsPresencial.class);
        criteria.add(Restrictions.eq("NCodEmpresa", insPresencial.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodInspresencial", insPresencial.getId().getNCodInspresencial()));
        return (SegDetInsPresencial) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<SegDetInsPresencial> obtenerListaInspeccionesPresenciales() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInsPresencial.class);
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetInsPresencial>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetInsPresencial> obtenerListaInspeccionesPresencialesByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInsPresencial.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetInsPresencial>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarInspeccionPresencial(SegDetInsPresencial insPresencial) {
        getHibernateTemplate().saveOrUpdate(insPresencial);
    }

}
