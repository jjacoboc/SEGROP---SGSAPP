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
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.DocumentoDao;
import pe.com.segrop.sgsapp.domain.SegDetDocumento;
import pe.com.segrop.sgsapp.domain.SegDetHistorial;

/**
 *
 * @author JJ
 */
@Repository(value="DocumentoDao")
public class DocumentoDaoHibernate extends HibernateDaoSupport implements DocumentoDao{
    
    /**
     * Crea una nueva instancia de DocumentoDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public DocumentoDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_DOCUMENTO.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_DOCUMENTO') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetDocumento> buscarDocumentos(SegDetDocumento documento) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetDocumento.class);        
        criteria.add(Restrictions.eq("NCodEmpresa", documento.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NActivo", BigDecimal.ONE));
        if(documento.getNProcedencia()!=null && !"-1".equals(documento.getNProcedencia().toString())){
            criteria.add(Restrictions.eq("NProcedencia", documento.getNProcedencia()));
        }
        if(documento.getNTipoDocumento()!=null && !"-1".equals(documento.getNTipoDocumento().toString())){
            criteria.add(Restrictions.eq("NTipoDocumento", documento.getNTipoDocumento()));
        }
        if(documento.getDFecInicio()!=null){
            try {
                Date fromDate = sdf.parse(df.format(documento.getDFecInicio())+" 00:00:00");
                criteria.add(Restrictions.ge("DFecEmision", fromDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(documento.getDFecFin()!=null){
            try {
                Date toDate = sdf.parse(df.format(documento.getDFecFin())+" 23:59:59");
                criteria.add(Restrictions.le("DFecEmision", toDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        criteria.addOrder(Order.desc("DFecEmision"));
        return (List<SegDetDocumento>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetDocumento> buscarDocumentosDesactivados(SegDetDocumento documento) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetDocumento.class);        
        criteria.add(Restrictions.eq("NCodEmpresa", documento.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NActivo", BigDecimal.ZERO));
        if(documento.getNProcedencia()!=null && !"-1".equals(documento.getNProcedencia().toString())){
            criteria.add(Restrictions.eq("NProcedencia", documento.getNProcedencia()));
        }
        if(documento.getNTipoDocumento()!=null && !"-1".equals(documento.getNTipoDocumento().toString())){
            criteria.add(Restrictions.eq("NTipoDocumento", documento.getNTipoDocumento()));
        }
        if(documento.getDFecInicio()!=null){
            try {
                Date fromDate = sdf.parse(df.format(documento.getDFecInicio())+" 00:00:00");
                criteria.add(Restrictions.ge("DFecEmision", fromDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(documento.getDFecFin()!=null){
            try {
                Date toDate = sdf.parse(df.format(documento.getDFecFin())+" 23:59:59");
                criteria.add(Restrictions.le("DFecEmision", toDate));
            } catch (ParseException ex) {
                Logger.getLogger(CapacitacionDaoHibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        criteria.addOrder(Order.desc("DFecEmision"));
        return (List<SegDetDocumento>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetDocumento> obtenerListaDocumentos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetDocumento.class);
        criteria.addOrder(Order.desc("DFecEmision"));
        return (List<SegDetDocumento>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetDocumento> obtenerListaDocumentosActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetDocumento.class);
        criteria.add(Restrictions.eq("NActivo", BigDecimal.ONE));
        criteria.addOrder(Order.desc("DFecEmision"));
        return (List<SegDetDocumento>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public SegDetDocumento obtenerDocumentoById(SegDetDocumento documento) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetDocumento.class);
        criteria.add(Restrictions.eq("NCodDocumento", documento.getNCodDocumento()));
        return (SegDetDocumento) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarDocumento(SegDetDocumento documento) {
        getHibernateTemplate().saveOrUpdate(documento);
    }
    
    @Override
    public List<SegDetHistorial> listarHistorial(SegDetDocumento documento) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetHistorial.class);
        criteria.add(Restrictions.eq("NCodDocumento", documento.getNCodDocumento()));
        criteria.addOrder(Order.desc("NVersion"));
        return (List<SegDetHistorial>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void eliminarDocumento(SegDetDocumento documento) {
        getHibernateTemplate().delete(documento);
    }
}
