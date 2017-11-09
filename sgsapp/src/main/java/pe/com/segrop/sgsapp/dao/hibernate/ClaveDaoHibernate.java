/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.ClaveDao;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetClave;

/**
 *
 * @author JJ
 */
@Repository(value="ClaveDao")
public class ClaveDaoHibernate extends HibernateDaoSupport implements ClaveDao{
    
    /**
     * Crea una nueva instancia de ClaveDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public ClaveDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_CLAVE.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_CLAVE') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public SegDetClave obtenerClaveActiva(SegCabUsuario usuario) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetClave.class);
        criteria.add(Restrictions.eq("NCodUsuario", usuario.getId().getNCodUsuario()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        return (SegDetClave) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarClave(SegDetClave clave) {
        getHibernateTemplate().saveOrUpdate(clave);
    }
    
}
