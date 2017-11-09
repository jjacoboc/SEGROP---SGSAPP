/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.EmpresaDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;

/**
 *
 * @author JJ
 */
@Repository(value="EmpresaDao")
public class EmpresaDaoHibernate extends HibernateDaoSupport implements EmpresaDao{
    
    /**
     * Crea una nueva instancia de EmpresaDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public EmpresaDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_EMPRESA.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_EMPRESA') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public List<SegCabEmpresa> buscarEmpresas(SegCabEmpresa empresa){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabEmpresa.class);        
        if(empresa.getVRuc()!=null && !"".equals(empresa.getVRuc())){
            criteria.add(Restrictions.eq("VRuc", empresa.getVRuc()));
        }
        if(empresa.getVRazonSocial()!=null && !"".equals(empresa.getVRazonSocial())){
            criteria.add(Restrictions.like("VRazonSocial", "%"+empresa.getVRazonSocial()+"%"));
        }
        criteria.addOrder(Order.asc("VRazonSocial"));
        return (List<SegCabEmpresa>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegCabEmpresa> obtenerListaEmpresas(){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabEmpresa.class);
        criteria.addOrder(Order.asc("VRazonSocial"));
        return (List<SegCabEmpresa>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegCabEmpresa> obtenerListaEmpresasActivas(){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabEmpresa.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VRazonSocial"));
        return (List<SegCabEmpresa>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegCabEmpresa obtenerEmpresaById(SegCabEmpresa empresa){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabEmpresa.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        return (SegCabEmpresa) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public SegCabEmpresa obtenerEmpresaByRuc(SegCabEmpresa empresa){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabEmpresa.class);
        criteria.add(Restrictions.eq("VRuc", empresa.getVRuc()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        return (SegCabEmpresa) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    /**
     * Registra la empresa en base de datos.
     * @param empresa Datos del empresa a registrar.
     */
    @Override
    @Transactional(readOnly = false)
    public void registrarEmpresa(SegCabEmpresa empresa) {
        getHibernateTemplate().saveOrUpdate(empresa);
    }
}
