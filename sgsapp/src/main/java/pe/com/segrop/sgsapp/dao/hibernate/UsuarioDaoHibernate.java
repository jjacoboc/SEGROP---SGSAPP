/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
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
import pe.com.segrop.sgsapp.dao.UsuarioDao;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.web.common.Parameters;

/**
 *
 * @author JJ
 */
@Repository(value="UsuarioDao")
public class UsuarioDaoHibernate extends HibernateDaoSupport implements UsuarioDao{
    
    /**
     * Crea una nueva instancia de UsuarioDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public UsuarioDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_USUARIO.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_USUARIO') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegCabUsuario> buscarUsuarios(SegCabUsuario usuario) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabUsuario.class);        
        if(usuario.getId().getNCodEmpresa()!=null && !"-1".equals(usuario.getId().getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", usuario.getId().getNCodEmpresa()));
        }
        if(usuario.getNTipNumDocumento()!=null && !"-1".equals(usuario.getNTipNumDocumento().toString())){
            criteria.add(Restrictions.eq("NTipNumDocumento", usuario.getNTipNumDocumento()));
        }
        if(usuario.getVNumDocumento()!=null && !"".equals(usuario.getVNumDocumento())){
            criteria.add(Restrictions.eq("VNumDocumento", usuario.getVNumDocumento()));
        }
        if(usuario.getVNombres()!=null && !"".equals(usuario.getVNombres())){
            criteria.add(Restrictions.like("VNombres", "%"+usuario.getVNombres()+"%"));
        }
        if(usuario.getVApellidos()!=null && !"".equals(usuario.getVApellidos())){
            criteria.add(Restrictions.like("VApellidos", "%"+usuario.getVApellidos()+"%"));
        }
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegCabUsuario>) getHibernateTemplate().findByCriteria(criteria);
    }
    @Override
    public List<SegCabUsuario> buscarUsuariosActivos(SegCabUsuario usuario) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabUsuario.class);        
        if(usuario.getId().getNCodEmpresa()!=null && !"-1".equals(usuario.getId().getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", usuario.getId().getNCodEmpresa()));
        }
        if(usuario.getNTipNumDocumento()!=null && !"-1".equals(usuario.getNTipNumDocumento().toString())){
            criteria.add(Restrictions.eq("NTipNumDocumento", usuario.getNTipNumDocumento()));
        }
        if(usuario.getVNumDocumento()!=null && !"".equals(usuario.getVNumDocumento())){
            criteria.add(Restrictions.eq("VNumDocumento", usuario.getVNumDocumento()));
        }
        if(usuario.getVNombres()!=null && !"".equals(usuario.getVNombres())){
            criteria.add(Restrictions.like("VNombres", "%"+usuario.getVNombres()+"%"));
        }
        if(usuario.getVApellidos()!=null && !"".equals(usuario.getVApellidos())){
            criteria.add(Restrictions.like("VApellidos", "%"+usuario.getVApellidos()+"%"));
        }
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegCabUsuario>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegCabUsuario> obtenerListaUsuarios() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabUsuario.class);
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegCabUsuario>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegCabUsuario> obtenerListaUsuariosActivos() {
        ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabUsuario.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VNombres"));
        criteria.addOrder(Order.asc("VApellidos"));
        return (List<SegCabUsuario>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegCabUsuario obtenerUsuarioByUser(SegCabUsuario usuario){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabUsuario.class);
        criteria.add(Restrictions.eq("NCodEmpresa", usuario.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("VUsuario", usuario.getVUsuario()));
        return (SegCabUsuario) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public SegCabUsuario obtenerUsuarioByNumeroDocumento(SegCabUsuario usuario){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabUsuario.class);
        criteria.add(Restrictions.eq("NCodEmpresa", usuario.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("VNumDocumento", usuario.getVNumDocumento()));
        return (SegCabUsuario) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public void registrarUsuario(SegCabUsuario usuario) {
        getHibernateTemplate().saveOrUpdate(usuario);
    }
}
