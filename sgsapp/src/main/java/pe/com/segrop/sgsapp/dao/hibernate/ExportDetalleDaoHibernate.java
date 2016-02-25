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
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.com.segrop.sgsapp.dao.ExportDetalleDao;
import pe.com.segrop.sgsapp.domain.SegDetExport;
import pe.com.segrop.sgsapp.domain.SegDetExportdetalle;

/**
 *
 * @author JJ
 */
@Repository(value="ExportDetalleDao")
public class ExportDetalleDaoHibernate extends HibernateDaoSupport implements ExportDetalleDao{
    
    /**
     * Crea una nueva instancia de ExportDetalleDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public ExportDetalleDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_EXPORTDETALLE.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_EXPORTDETALLE') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public void registrarDetalleConfiguracion(SegDetExportdetalle export) {
        getHibernateTemplate().saveOrUpdate(export);
    }
    
    @Override
    public void eliminarDetalleConfiguracionById(SegDetExport export) {
        StringBuilder sql = new StringBuilder();
        Query query;
        try{
            sql.append("DELETE ");
            sql.append("FROM SegDetExportdetalle ");
            sql.append("WHERE id.NCodExport = ").append(export.getId().getNCodExport()).append(" ");
            sql.append("AND id.NCodEmpresa = ").append(export.getId().getNCodEmpresa());
            query = this.currentSession().createQuery(sql.toString());
            query.executeUpdate();
        }catch(DataAccessResourceFailureException e){
            e.getMessage();
        } catch (IllegalStateException e) {
            e.getMessage();
        }
    }
}
