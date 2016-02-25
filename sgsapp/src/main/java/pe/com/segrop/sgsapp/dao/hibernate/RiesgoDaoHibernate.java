/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.com.segrop.sgsapp.dao.RiesgoDao;
import pe.com.segrop.sgsapp.domain.SegDetRiesgo;
import pe.com.segrop.sgsapp.web.common.Parameters;

/**
 *
 * @author JJ
 */
@Repository(value="RiesgoDao")
public class RiesgoDaoHibernate extends HibernateDaoSupport implements RiesgoDao{

    /**
     * Crea una nueva instancia de RiesgoDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public RiesgoDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public List<SegDetRiesgo> buscarRiesgos(SegDetRiesgo riesgo) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetRiesgo.class);
        criteria.add(Restrictions.eq("NCodEmpresa", riesgo.getId().getNCodEmpresa()));
        if(riesgo.getId().getNTipoRiesgo()!=null && !"-1".equals(riesgo.getId().getNTipoRiesgo().toString())){
            criteria.add(Restrictions.eq("NTipoRiesgo", riesgo.getId().getNTipoRiesgo()));
        }
        if(riesgo.getNLocal()!=null && !"-1".equals(riesgo.getNLocal().toString())){
            criteria.add(Restrictions.eq("NLocal", riesgo.getNLocal()));
        }
        if(riesgo.getNArea()!=null && !"-1".equals(riesgo.getNArea().toString())){
            criteria.add(Restrictions.eq("NArea", riesgo.getNArea()));
        }
        if(riesgo.getNLugar()!=null && !"-1".equals(riesgo.getNLugar().toString())){
            criteria.add(Restrictions.eq("NLugar", riesgo.getNLugar()));
        }
        if(riesgo.getNResponsable()!=null && !"-1".equals(riesgo.getNResponsable().toString())){
            criteria.add(Restrictions.eq("NResponsable", riesgo.getNResponsable()));
        }
        if(riesgo.getNCargo()!=null && !"-1".equals(riesgo.getNCargo().toString())){
            criteria.add(Restrictions.eq("NCargo", riesgo.getNCargo()));
        }
        if(riesgo.getNEstado()!=null && !"-1".equals(riesgo.getNEstado().toString())){
            criteria.add(Restrictions.eq("NEstado", riesgo.getNEstado()));
        }
        if(riesgo.getDFecInicio()!=null){
            try {
                Date fromDate = sdf.parse(df.format(riesgo.getDFecInicio())+" 00:00:00");
                criteria.add(Restrictions.ge("DFecHora", fromDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(riesgo.getDFecFin()!=null){
            try {
                Date toDate = sdf.parse(df.format(riesgo.getDFecFin())+" 23:59:59");
                criteria.add(Restrictions.le("DFecHora", toDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        criteria.addOrder(Order.desc("DFecHora"));
         return (List<SegDetRiesgo>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetRiesgo> buscarRiesgosMatriz(SegDetRiesgo riesgo) {
        ResourceBundle bundle;
        DetachedCriteria criteria = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            criteria = DetachedCriteria.forClass(SegDetRiesgo.class);
            criteria.add(Restrictions.eq("NCodEmpresa", riesgo.getId().getNCodEmpresa()));
            criteria.add(Restrictions.eq("NEstado", BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_EVALUACION")))));
            if(riesgo.getDFecInicio()!=null){
                try {
                    Date fromDate = sdf.parse(df.format(riesgo.getDFecInicio())+" 00:00:00");
                    criteria.add(Restrictions.ge("DFecHora", fromDate));
                } catch (ParseException ex) {
                    Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(riesgo.getDFecFin()!=null){
                try {
                    Date toDate = sdf.parse(df.format(riesgo.getDFecFin())+" 23:59:59");
                    criteria.add(Restrictions.le("DFecHora", toDate));
                } catch (ParseException ex) {
                    Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            criteria.addOrder(Order.asc("NOcurrencia"));
            criteria.addOrder(Order.desc("NImpacto"));
        }catch(NumberFormatException e){
            e.getMessage();
        }
        return (List<SegDetRiesgo>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetRiesgo> listarRiesgosMatriz(SegDetRiesgo riesgo){
        ResourceBundle bundle;
        DetachedCriteria criteria = null;
        try{
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            criteria = DetachedCriteria.forClass(SegDetRiesgo.class);
            criteria.add(Restrictions.eq("NCodEmpresa", riesgo.getId().getNCodEmpresa()));
            criteria.add(Restrictions.eq("NEstado", BigDecimal.valueOf(Long.parseLong(bundle.getString("ESTADO_EN_EVALUACION")))));
            criteria.addOrder(Order.asc("NOcurrencia"));
            criteria.addOrder(Order.desc("NImpacto"));
        }catch(NumberFormatException e){
            e.getMessage();
        }
        return (List<SegDetRiesgo>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegDetRiesgo getRiesgoById(String tipo, String id){
        SegDetRiesgo riesgo = null;
        try{
            DetachedCriteria criteria = DetachedCriteria.forClass(SegDetRiesgo.class);
            criteria.add(Restrictions.eq("NCodRiesgo", BigDecimal.valueOf(Long.parseLong(id))));
            criteria.add(Restrictions.eq("NTipoRiesgo", BigDecimal.valueOf(Long.parseLong(tipo))));
            riesgo = (SegDetRiesgo) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
        }catch(NumberFormatException e){
            e.getMessage();
        } catch (DataAccessException e) {
            e.getMessage();
        }
        return riesgo;
    }
}
