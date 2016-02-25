/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.com.segrop.sgsapp.dao.CriteriaDao;
import pe.com.segrop.sgsapp.domain.SegDetCriteria;
import pe.com.segrop.sgsapp.domain.SegDetExport;

/**
 *
 * @author JJ
 */
@Repository(value="CriteriaDao")
public class CriteriaDaoHibernate extends HibernateDaoSupport implements CriteriaDao{
    
    /**
     * Crea una nueva instancia de CriteriaDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public CriteriaDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_CRITERIA.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_CRITERIA') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public void registrarCriteria(SegDetCriteria criteria) {
        getHibernateTemplate().saveOrUpdate(criteria);
    }
    
    @Override
    public void eliminarCriteriaById(SegDetExport export) {
        StringBuilder sql = new StringBuilder();
        Query query;
        try{
            sql.append("DELETE ");
            sql.append("FROM SegDetCriteria ");
            sql.append("WHERE id.NCodExport = ").append(export.getId().getNCodExport()).append(" ");
            sql.append("AND id.NCodEmpresa = ").append(export.getId().getNCodEmpresa());
            query = this.currentSession().createQuery(sql.toString());
            query.executeUpdate();
        }catch(Exception e){
            e.getMessage();
        }
    }
}
