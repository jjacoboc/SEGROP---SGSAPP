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
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.ObjetoDao;
import pe.com.segrop.sgsapp.domain.SegDetObjeto;

/**
 *
 * @author JJ
 */
@Repository(value="ObjetoDao")
public class ObjetoDaoHibernate extends HibernateDaoSupport implements ObjetoDao{
    
    /**
     * Crea una nueva instancia de ObjetoDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public ObjetoDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_OBJETO.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_OBJETO') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegDetObjeto> buscarObjetos(SegDetObjeto objeto) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetObjeto.class);
        if(objeto.getVNombre()!=null && !"".equals(objeto.getVNombre())){
            criteria.add(Restrictions.like("VNombre", "%"+objeto.getVNombre()+"%"));
        }
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetObjeto>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetObjeto> obtenerListaObjetos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetObjeto.class);
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetObjeto>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetObjeto> obtenerListaObjetosActivos() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetObjeto.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetObjeto>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarObjeto(SegDetObjeto objeto) {
        getHibernateTemplate().saveOrUpdate(objeto);
    }
    
}
