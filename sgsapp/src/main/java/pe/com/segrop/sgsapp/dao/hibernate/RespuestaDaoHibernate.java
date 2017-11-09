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
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.RespuestaDao;
import pe.com.segrop.sgsapp.domain.SegDetInsTelefonica;
import pe.com.segrop.sgsapp.domain.SegDetPregunta;
import pe.com.segrop.sgsapp.domain.SegDetRespuesta;

/**
 *
 * @author JJ
 */
@Repository(value="RespuestaDao")
public class RespuestaDaoHibernate extends HibernateDaoSupport implements RespuestaDao{
    
    /**
     * Crea una nueva instancia de RespuestaDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public RespuestaDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_RESPUESTA.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_RESPUESTA') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetRespuesta> obtenerListaRespuestasByPreguntaInspeccion(SegDetPregunta pregunta, SegDetInsTelefonica inspeccion) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetRespuesta.class);
        criteria.add(Restrictions.eq("NCodEmpresa", pregunta.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodPregunta", pregunta.getNCodPregunta()));
        criteria.add(Restrictions.eq("NCodInstelefonica", inspeccion.getNCodInstelefonica()));
        criteria.addOrder(Order.asc("NCodRespuesta"));
        return (List<SegDetRespuesta>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarRespuesta(SegDetRespuesta respuesta) {
        getHibernateTemplate().saveOrUpdate(respuesta);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarRespuesta(SegDetRespuesta respuesta) {
        getHibernateTemplate().delete(respuesta);
    }
    
}
