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
import pe.com.segrop.sgsapp.dao.InsTelefonicaDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetInsTelefonica;

/**
 *
 * @author JJ
 */
@Repository(value="InsTelefonicaDao")
public class InsTelefonicaDaoHibernate extends HibernateDaoSupport implements InsTelefonicaDao{
    
    /**
     * Crea una nueva instancia de InsTelefonicaDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public InsTelefonicaDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_INSTELEFONICA.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_INSTELEFONICA') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetInsTelefonica> buscarInspeccionesTelefonicas(SegDetInsTelefonica insTelefonica) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInsTelefonica.class);
        criteria.add(Restrictions.eq("NCodEmpresa", insTelefonica.getNCodEmpresa()));
        if(insTelefonica.getNCodLugar()!=null && !"-1".equals(insTelefonica.getNCodLugar().toString())){
            criteria.add(Restrictions.eq("NCodLugar", insTelefonica.getNCodLugar()));
        }
        if(insTelefonica.getNCodResponsable()!=null && !"-1".equals(insTelefonica.getNCodResponsable().toString())){
            criteria.add(Restrictions.eq("NCodResponsable", insTelefonica.getNCodResponsable()));
        }
        if(insTelefonica.getNCodCargo()!=null && !"-1".equals(insTelefonica.getNCodCargo().toString())){
            criteria.add(Restrictions.eq("NCodCargo", insTelefonica.getNCodCargo()));
        }
        if(insTelefonica.getDFecInicio()!=null){
            try {
                Date fromDate = sdf.parse(df.format(insTelefonica.getDFecInicio())+" 00:00:00");
                criteria.add(Restrictions.ge("DFecCreacion", fromDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(insTelefonica.getDFecFin()!=null){
            try {
                Date toDate = sdf.parse(df.format(insTelefonica.getDFecFin())+" 23:59:59");
                criteria.add(Restrictions.le("DFecCreacion", toDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(insTelefonica.getVPregunta()!=null && !"".equals(insTelefonica.getVPregunta())){
            criteria.add(Restrictions.sqlRestriction("N_COD_INSTELEFONICA IN (select distinct T.N_COD_INSTELEFONICA " + 
                                                                                "from SEG_REL_CUESTIONARIO C, " +
                                                                                "     SEG_DET_PREGUNTA P, " +
                                                                                "     SEG_DET_INS_TELEFONICA T " +
                                                                                "where T.N_COD_INSTELEFONICA = C.N_COD_INSTELEFONICA " +
                                                                                "and P.N_COD_PREGUNTA = C.N_COD_PREGUNTA " +
                                                                                "and P.V_DESCRIPCION like '%"+insTelefonica.getVPregunta()+"%')"));
        }
        criteria.addOrder(Order.desc("DFecCreacion"));
        return (List<SegDetInsTelefonica>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetInsTelefonica> obtenerListaInspeccionesTelefonicas() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInsTelefonica.class);
        criteria.addOrder(Order.desc("DFecCreacion"));
        return (List<SegDetInsTelefonica>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetInsTelefonica> obtenerListaInspeccionesTelefonicasByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetInsTelefonica.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.desc("DFecCreacion"));
        return (List<SegDetInsTelefonica>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void registrarInspeccionTelefonica(SegDetInsTelefonica insTelefonica) {
        getHibernateTemplate().saveOrUpdate(insTelefonica);
    }
    
}
