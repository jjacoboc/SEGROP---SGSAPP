/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.segrop.sgsapp.dao.PermisoDao;
import pe.com.segrop.sgsapp.domain.SegDetObjeto;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegRelPermiso;

/**
 *
 * @author JJ
 */
@Repository(value="PermisoDao")
public class PermisoDaoHibernate extends HibernateDaoSupport implements PermisoDao{

    /**
     * Crea una nueva instancia de PermisoDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public PermisoDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<SegDetObjeto> obtenerObjetosAsignadosByPerfil(SegDetPerfil perfil) {
        final StringBuffer sql = new StringBuffer("");
        sql.append("SELECT o.n_cod_objeto, o.v_descripcion, o.v_nombre, ");
        sql.append("o.n_flg_activo, o.v_usu_creacion, o.d_fec_creacion, o.v_ip_creacion, ");
        sql.append("o.v_usu_modificacion, o.d_fec_modificacion, o.v_ip_modificacion ");
        sql.append("FROM SEG_DET_OBJETO o WHERE o.n_cod_objeto IN(");
        sql.append("SELECT p.n_cod_objeto FROM seg_rel_permiso p WHERE ");
        sql.append("p.n_cod_perfil = ").append(perfil.getId().getNCodPerfil()).append(" AND ");
        sql.append("p.n_cod_empresa = ").append(perfil.getId().getNCodEmpresa()).append(" AND ");
        sql.append("p.n_flg_activo = ").append(BigDecimal.ONE).append(") ");
        sql.append("AND o.n_flg_activo = ").append(BigDecimal.ONE);
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<SegDetObjeto> lista = new ArrayList();
        if(resultSet!=null && !resultSet.isEmpty()){
            for (Object resultSet1 : resultSet) {
                Object[] objeto = (Object[]) resultSet1;
                SegDetObjeto segDetObjeto = new SegDetObjeto();
                segDetObjeto.setNCodObjeto((BigDecimal)objeto[0]);
                segDetObjeto.setVDescripcion((String)objeto[1]);
                segDetObjeto.setVNombre((String)objeto[2]);
                segDetObjeto.setNFlgActivo((BigDecimal)objeto[3]);
                segDetObjeto.setVUsuCreacion(((String)objeto[4]));
                segDetObjeto.setDFecCreacion((Date)objeto[5]);
                segDetObjeto.setVIpCreacion((String)objeto[6]);
                segDetObjeto.setVUsuModificacion(((String)objeto[7]));
                segDetObjeto.setDFecModificacion((Date)objeto[8]);
                segDetObjeto.setVIpModificacion((String)objeto[9]);
                lista.add(segDetObjeto);
            }
        }
        return lista;
    }

    @Override
    public List<SegDetObjeto> obtenerObjetosNoAsignadosByPerfil(SegDetPerfil perfil) {
        final StringBuffer sql = new StringBuffer("");
        sql.append("SELECT o.n_cod_objeto, o.v_descripcion, o.v_nombre, ");
        sql.append("o.n_flg_activo, o.v_usu_creacion, o.d_fec_creacion, o.v_ip_creacion, ");
        sql.append("o.v_usu_modificacion, o.d_fec_modificacion, o.v_ip_modificacion ");
        sql.append("FROM SEG_DET_OBJETO o WHERE o.n_cod_objeto NOT IN(");
        sql.append("SELECT p.n_cod_objeto FROM seg_rel_permiso p WHERE ");
        sql.append("p.n_cod_perfil = ").append(perfil.getId().getNCodPerfil()).append(" AND ");
        sql.append("p.n_cod_empresa = ").append(perfil.getId().getNCodEmpresa()).append(" AND ");
        sql.append("p.n_flg_activo = ").append(BigDecimal.ONE).append(") ");
        sql.append("AND o.n_flg_activo = ").append(BigDecimal.ONE);
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<SegDetObjeto> lista = new ArrayList();
        if(resultSet!=null && !resultSet.isEmpty()){
            for (Object resultSet1 : resultSet) {
                Object[] objeto = (Object[]) resultSet1;
                SegDetObjeto segDetObjeto = new SegDetObjeto();
                segDetObjeto.setNCodObjeto((BigDecimal)objeto[0]);
                segDetObjeto.setVDescripcion((String)objeto[1]);
                segDetObjeto.setVNombre((String)objeto[2]);
                segDetObjeto.setNFlgActivo((BigDecimal)objeto[3]);
                segDetObjeto.setVUsuCreacion(((String)objeto[4]));
                segDetObjeto.setDFecCreacion((Date)objeto[5]);
                segDetObjeto.setVIpCreacion((String)objeto[6]);
                segDetObjeto.setVUsuModificacion(((String)objeto[7]));
                segDetObjeto.setDFecModificacion((Date)objeto[8]);
                segDetObjeto.setVIpModificacion((String)objeto[9]);
                lista.add(segDetObjeto);
            }
        }
        return lista;
    }

    @Override
    public SegRelPermiso obtenerPermisoById(SegRelPermiso permiso) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegRelPermiso.class);
        criteria.add(Restrictions.eq("NCodObjeto", permiso.getId().getNCodObjeto()));
        criteria.add(Restrictions.eq("NCodEmpresa", permiso.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodPerfil", permiso.getId().getNCodPerfil()));
        return (SegRelPermiso) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarPermiso(SegRelPermiso permiso) {
        getHibernateTemplate().saveOrUpdate(permiso);
    }
    
}
