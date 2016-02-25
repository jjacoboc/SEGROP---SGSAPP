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
import pe.com.segrop.sgsapp.dao.ParticipanteDao;
import pe.com.segrop.sgsapp.domain.SegDetCapacitacion;
import pe.com.segrop.sgsapp.domain.SegDetParticipante;

/**
 *
 * @author JJ
 */
@Repository(value="ParticipanteDao")
public class ParticipanteDaoHibernate extends HibernateDaoSupport implements ParticipanteDao {
    
    /**
     * Crea una nueva instancia de ParticipanteDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public ParticipanteDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public Long nextSequenceValue() {
        return (Long)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_PARTICIPANTE.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_PARTICIPANTE') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public SegDetParticipante buscarParticipante(SegDetParticipante participante) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetParticipante.class);
        criteria.add(Restrictions.eq("id.NCodCapacitacion", participante.getId().getNCodCapacitacion()));
        criteria.add(Restrictions.eq("VNombreCompleto", participante.getVNombreCompleto()));
        return (SegDetParticipante) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public SegDetParticipante obtenerParticipanteByCapacitacion(SegDetParticipante participante) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetParticipante.class);
        criteria.add(Restrictions.eq("id.NCodEmpresa", participante.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("id.NCodCapacitacion", participante.getId().getNCodCapacitacion()));
        criteria.add(Restrictions.eq("VNombres", participante.getVNombres()));
        criteria.add(Restrictions.eq("VApellidos", participante.getVApellidos()));
        return (SegDetParticipante) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<SegDetParticipante> obtenerListaParticipantesByCapacitacion(SegDetCapacitacion capacitacion) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetParticipante.class);
        criteria.add(Restrictions.eq("id.NCodEmpresa", capacitacion.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("id.NCodCapacitacion", capacitacion.getId().getNCodCapacitacion()));
        criteria.addOrder(Order.asc("VNombreCompleto"));
        return (List<SegDetParticipante>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void registrarParticipante(SegDetParticipante participante) {
        getHibernateTemplate().saveOrUpdate(participante);
    }
    
    @Override
    public void eliminarParticipante(SegDetParticipante participante){
        getHibernateTemplate().delete(participante);
    }
}
