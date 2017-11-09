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
import pe.com.segrop.sgsapp.dao.UsuarioPerfilDao;
import pe.com.segrop.sgsapp.domain.SegCabUsuario;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegDetPerfilId;
import pe.com.segrop.sgsapp.domain.SegRelUsuarioperfil;

/**
 *
 * @author JJ
 */
@Repository(value="UsuarioPerfilDao")
public class UsuarioPerfilDaoHibernate extends HibernateDaoSupport implements UsuarioPerfilDao{

    /**
     * Crea una nueva instancia de UsuarioPerfilDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public UsuarioPerfilDaoHibernate(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<SegDetPerfil> obtenerPerfilesAsignadosByUsuario(SegCabUsuario usuario) {
        final StringBuffer sql = new StringBuffer("");
        sql.append("SELECT p.n_cod_perfil, p.n_cod_empresa, p.v_descripcion, p.v_nombre, ");
        sql.append("p.n_flg_activo, p.v_usu_creacion, p.d_fec_creacion, p.v_ip_creacion, ");
        sql.append("p.v_usu_modificacion, p.d_fec_modificacion, p.v_ip_modificacion ");
        sql.append("FROM SEG_DET_PERFIL p WHERE p.n_cod_perfil IN(");
        sql.append("SELECT up.n_cod_perfil FROM seg_rel_usuarioperfil up WHERE ");
        sql.append("up.n_cod_usuario = ").append(usuario.getNCodUsuario()).append(" AND ");
        sql.append("up.n_cod_empresa = ").append(usuario.getNCodEmpresa()).append(" AND ");
        sql.append("up.n_flg_activo = ").append(BigDecimal.ONE).append(") ");
        sql.append("AND p.n_flg_activo = ").append(BigDecimal.ONE).append(" ");
        sql.append("AND p.n_cod_empresa = ").append(usuario.getNCodEmpresa());
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<SegDetPerfil> lista = new ArrayList();
        if(resultSet!=null && !resultSet.isEmpty()){
            for (Object resultSet1 : resultSet) {
                Object[] objeto = (Object[]) resultSet1;
                SegDetPerfilId perfilId = new SegDetPerfilId();
                perfilId.setNCodPerfil((BigDecimal)objeto[0]);
                perfilId.setNCodEmpresa((BigDecimal)objeto[1]);
                SegDetPerfil perfil = new SegDetPerfil();
                perfil.setId(perfilId);
                perfil.setNCodPerfil(perfilId.getNCodPerfil());
                perfil.setNCodEmpresa(perfilId.getNCodEmpresa());
                perfil.setVDescripcion((String)objeto[2]);
                perfil.setVNombre((String)objeto[3]);
                perfil.setNFlgActivo((BigDecimal)objeto[4]);
                perfil.setVUsuCreacion(((String)objeto[5]));
                perfil.setDFecCreacion((Date)objeto[6]);
                perfil.setVIpCreacion((String)objeto[7]);
                perfil.setVUsuModificacion(((String)objeto[8]));
                perfil.setDFecModificacion((Date)objeto[9]);
                perfil.setVIpModificacion((String)objeto[10]);
                lista.add(perfil);
            }
        }
        return lista;
    }

    @Override
    public List<SegDetPerfil> obtenerPerfilesNoAsignadosByUsuario(SegCabUsuario usuario) {
        final StringBuffer sql = new StringBuffer("");
        sql.append("SELECT p.n_cod_perfil, p.n_cod_empresa, p.v_descripcion, p.v_nombre, ");
        sql.append("p.n_flg_activo, p.v_usu_creacion, p.d_fec_creacion, p.v_ip_creacion, ");
        sql.append("p.v_usu_modificacion, p.d_fec_modificacion, p.v_ip_modificacion ");
        sql.append("FROM SEG_DET_PERFIL p WHERE p.n_cod_perfil NOT IN(");
        sql.append("SELECT up.n_cod_perfil FROM seg_rel_usuarioperfil up WHERE ");
        sql.append("up.n_cod_usuario = ").append(usuario.getNCodUsuario()).append(" AND ");
        sql.append("up.n_cod_empresa = ").append(usuario.getNCodEmpresa()).append(" AND ");
        sql.append("up.n_flg_activo = ").append(BigDecimal.ONE).append(") ");
        sql.append("AND p.n_flg_activo = ").append(BigDecimal.ONE).append(" ");
        sql.append("AND p.n_cod_empresa = ").append(usuario.getNCodEmpresa());
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<SegDetPerfil> lista = new ArrayList();
        if(resultSet!=null && !resultSet.isEmpty()){
            for (Object resultSet1 : resultSet) {
                Object[] objeto = (Object[]) resultSet1;
                SegDetPerfilId perfilId = new SegDetPerfilId();
                perfilId.setNCodPerfil((BigDecimal)objeto[0]);
                perfilId.setNCodEmpresa((BigDecimal)objeto[1]);
                SegDetPerfil perfil = new SegDetPerfil();
                perfil.setId(perfilId);
                perfil.setNCodPerfil(perfilId.getNCodPerfil());
                perfil.setNCodEmpresa(perfilId.getNCodEmpresa());
                perfil.setVDescripcion((String)objeto[2]);
                perfil.setVNombre((String)objeto[3]);
                perfil.setNFlgActivo((BigDecimal)objeto[4]);
                perfil.setVUsuCreacion(((String)objeto[5]));
                perfil.setDFecCreacion((Date)objeto[6]);
                perfil.setVIpCreacion((String)objeto[7]);
                perfil.setVUsuModificacion(((String)objeto[8]));
                perfil.setDFecModificacion((Date)objeto[9]);
                perfil.setVIpModificacion((String)objeto[10]);
                lista.add(perfil);
            }
        }
        return lista;
    }
    
    @Override
    public SegRelUsuarioperfil obtenerUsuarioPerfilById(SegRelUsuarioperfil usuarioPerfil){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegRelUsuarioperfil.class);
        criteria.add(Restrictions.eq("NCodUsuario", usuarioPerfil.getId().getNCodUsuario()));
        criteria.add(Restrictions.eq("NCodEmpresa", usuarioPerfil.getId().getNCodEmpresa()));
        criteria.add(Restrictions.eq("NCodPerfil", usuarioPerfil.getId().getNCodPerfil()));
        return (SegRelUsuarioperfil) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    @Transactional(readOnly = false)
    public void eliminarAsignacion(SegRelUsuarioperfil usuarioPerfil){
        getHibernateTemplate().delete(usuarioPerfil);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void registrarAsignacion(SegRelUsuarioperfil usuarioPerfil){
        getHibernateTemplate().saveOrUpdate(usuarioPerfil);
    }
}
