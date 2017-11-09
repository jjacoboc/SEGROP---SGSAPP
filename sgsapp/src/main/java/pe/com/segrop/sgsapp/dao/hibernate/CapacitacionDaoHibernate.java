/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import pe.com.segrop.sgsapp.dao.CapacitacionDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetCapacitacion;

/**
 *
 * @author JJ
 */
@Repository(value="CapacitacionDao")
public class CapacitacionDaoHibernate extends HibernateDaoSupport implements CapacitacionDao {

    /**
     * Crea una nueva instancia de CapacitacionDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public CapacitacionDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public Long nextSequenceValue() {
        return (Long)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_CAPACITACION.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_CAPACITACION') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetCapacitacion> buscarCapacitaciones(SegDetCapacitacion capacitacion) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCapacitacion.class);
        if(capacitacion.getNCodEmpresa()!=null && !"-1".equals(capacitacion.getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", capacitacion.getNCodEmpresa()));
        }
        if(capacitacion.getNModalidad()!=null && !"-1".equals(capacitacion.getNModalidad().toString())){
            criteria.add(Restrictions.eq("NModalidad", capacitacion.getNModalidad()));
        }
        if(capacitacion.getNTipoCapacitacion()!=null && !"-1".equals(capacitacion.getNTipoCapacitacion().toString())){
            criteria.add(Restrictions.eq("NTipoCapacitacion", capacitacion.getNTipoCapacitacion()));
        }
        if(capacitacion.getVNombre()!=null && !"".equals(capacitacion.getVNombre())){
            criteria.add(Restrictions.sqlRestriction("V_NOMBRE like '%"+capacitacion.getVNombre()+"%'"));
        }
        if(capacitacion.getDFecInicio()!=null){
            try {
                Date fromDate = sdf.parse(df.format(capacitacion.getDFecInicio())+" 00:00:00");
                criteria.add(Restrictions.ge("DFechaHora", fromDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(capacitacion.getDFecFin()!=null){
            try {
                Date toDate = sdf.parse(df.format(capacitacion.getDFecFin())+" 23:59:59");
                criteria.add(Restrictions.le("DFechaHora", toDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(capacitacion.getVDescripcion()!=null && !"".equals(capacitacion.getVDescripcion())){
            criteria.add(Restrictions.sqlRestriction("N_COD_CAPACITACION IN (select distinct N_COD_CAPACITACION from SGSWEB.SEG_DET_PARTICIPANTE where V_NOMBRE_COMPLETO like '%"+capacitacion.getVDescripcion()+"%')"));
        }
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetCapacitacion>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetCapacitacion> obtenerListaCapacitacionesByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCapacitacion.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetCapacitacion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarCapacitacion(SegDetCapacitacion capacitacion) {
        getHibernateTemplate().saveOrUpdate(capacitacion);
    }
}
