/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
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
import pe.com.segrop.sgsapp.dao.NovedadDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;
import pe.com.segrop.sgsapp.web.common.Parameters;

/**
 *
 * @author JJ
 */
@Repository(value = "NovedadDao")
public class NovedadDaoHibernate extends HibernateDaoSupport implements NovedadDao {

    private final ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());

    ;
    
    /**
     * Crea una nueva instancia de NovedadDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public NovedadDaoHibernate(SessionFactory sessionFactory) {
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
                        //return (Long) session.createSQLQuery("select SGSWEB.SEQ_NOVEDAD.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                        return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_NOVEDAD') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                    }
                });
    }

    @Override
    public List<SegDetNovedad> buscarNovedades(SegDetNovedad novedad) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetNovedad.class);
        if (novedad.getNCodEmpresa() != null && !"-1".equals(novedad.getNCodEmpresa().toString())) {
            criteria.add(Restrictions.eq("NCodEmpresa", novedad.getNCodEmpresa()));
        }
        if (novedad.getNTipoNovedad() != null && !"-1".equals(novedad.getNTipoNovedad().toString())) {
            criteria.add(Restrictions.eq("NTipoNovedad", novedad.getNTipoNovedad()));
        }
        if (novedad.getNTipoEvento() != null && !"-1".equals(novedad.getNTipoEvento().toString())) {
            criteria.add(Restrictions.eq("NTipoEvento", novedad.getNTipoEvento()));
        }
        if (novedad.getNLocal() != null && !"-1".equals(novedad.getNLocal().toString())) {
            criteria.add(Restrictions.eq("NLocal", novedad.getNLocal()));
        }
        if (novedad.getNArea() != null && !"-1".equals(novedad.getNArea().toString())) {
            criteria.add(Restrictions.eq("NArea", novedad.getNArea()));
        }
        if (novedad.getNLugar() != null && !"-1".equals(novedad.getNLugar().toString())) {
            criteria.add(Restrictions.eq("NLugar", novedad.getNLugar()));
        }
        if (novedad.getNResponsable() != null && !"-1".equals(novedad.getNResponsable().toString())) {
            criteria.add(Restrictions.eq("NResponsable", novedad.getNResponsable()));
        }
        if (novedad.getNCargo() != null && !"-1".equals(novedad.getNCargo().toString())) {
            criteria.add(Restrictions.eq("NCargo", novedad.getNCargo()));
        }
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetNovedad>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public SegDetNovedad obtenerNovedadById(SegDetNovedad novedad) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetNovedad.class);
        criteria.add(Restrictions.eq("NCodNovedad", novedad.getId().getNCodNovedad()));
        return (SegDetNovedad) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<SegDetNovedad> obtenerListaNovedades() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetNovedad.class);
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetNovedad>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetNovedad> obtenerListaNovedadesNoCerradas() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetNovedad.class);
        criteria.add(Restrictions.not(Restrictions.eq("NEstado", BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))))));
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetNovedad>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetNovedad> obtenerListaNovedadesNoCerradasByEmpresa(BigDecimal idEmpresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetNovedad.class);
        criteria.add(Restrictions.not(Restrictions.eq("NEstado", BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_CERRADA"))))));
        criteria.add(Restrictions.eq("NCodEmpresa", idEmpresa));
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetNovedad>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetNovedad> obtenerListaNovedadesByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetNovedad.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.desc("DFecHora"));
        return (List<SegDetNovedad>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarNovedad(SegDetNovedad novedad) {
        getHibernateTemplate().saveOrUpdate(novedad);
    }

}
