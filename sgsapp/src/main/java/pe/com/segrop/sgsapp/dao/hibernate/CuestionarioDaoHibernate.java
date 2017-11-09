/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.CuestionarioDao;
import pe.com.segrop.sgsapp.domain.SegRelCuestionario;

/**
 *
 * @author JJ
 */
@Repository(value="CuestionarioDao")
public class CuestionarioDaoHibernate extends HibernateDaoSupport implements CuestionarioDao{
    
    /**
     * Crea una nueva instancia de CuestionarioDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public CuestionarioDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_CUESTIONARIO.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_CUESTIONARIO') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarCuestionario(SegRelCuestionario cuestionario) {
        getHibernateTemplate().saveOrUpdate(cuestionario);
    }
    
}
