/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.com.segrop.sgsapp.dao.HistorialDao;
import pe.com.segrop.sgsapp.domain.SegDetDocumento;
import pe.com.segrop.sgsapp.domain.SegDetHistorial;

/**
 *
 * @author JJ
 */
@Repository(value="HistorialDao")
public class HistorialDaoHibernate extends HibernateDaoSupport implements HistorialDao{
    
    /**
     * Crea una nueva instancia de HistorialDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public HistorialDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_HISTORIAL.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_HISTORIAL') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public Integer obtenerMaximoVersionDocumento(SegDetDocumento segDetDocumento){
        final String sql = "select max(n_version) as version from seg_det_historial where n_cod_documento = ".concat(segDetDocumento.getId().getNCodDocumento().toString());
        return (Integer)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return (Integer) session.createSQLQuery(sql).addScalar("version", IntegerType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public void registrarHistorial(SegDetHistorial historial) {
        getHibernateTemplate().saveOrUpdate(historial);
    }
    
    @Override
    public void eliminarHistorial(SegDetHistorial historial) {
        getHibernateTemplate().delete(historial);
    }
}
