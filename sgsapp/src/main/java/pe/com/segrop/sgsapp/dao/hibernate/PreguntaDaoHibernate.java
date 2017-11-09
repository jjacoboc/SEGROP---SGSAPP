/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import pe.com.segrop.sgsapp.dao.PreguntaDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetInsTelefonica;
import pe.com.segrop.sgsapp.domain.SegDetPregunta;
import pe.com.segrop.sgsapp.domain.SegDetPreguntaId;
import pe.com.segrop.sgsapp.domain.SegRelCuestionario;

/**
 *
 * @author JJ
 */
@Repository(value="PreguntaDao")
public class PreguntaDaoHibernate extends HibernateDaoSupport implements PreguntaDao{
    
    /**
     * Crea una nueva instancia de PreguntaDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public PreguntaDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_PREGUNTA.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_PREGUNTA') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }
    
    @Override
    public SegDetPregunta buscarPregunta(SegDetPregunta pregunta) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPregunta.class);
        if(pregunta.getId().getNCodEmpresa()!=null && !"-1".equals(pregunta.getId().getNCodEmpresa().toString())){
            criteria.add(Restrictions.eq("NCodEmpresa", pregunta.getId().getNCodEmpresa()));
        }
        if(pregunta.getVDescripcion()!=null && !"".equals(pregunta.getVDescripcion())){
            criteria.add(Restrictions.eq("VDescripcion", pregunta.getVDescripcion()));
        }
        return (SegDetPregunta) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<SegDetPregunta> obtenerListaPreguntas() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPregunta.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetPregunta> obtenerListaPreguntasByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPregunta.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetPregunta> obtenerListaPreguntasActivas() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPregunta.class);
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetPregunta> obtenerListaPreguntasActivasByEmpresa(SegCabEmpresa empresa) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetPregunta.class);
        criteria.add(Restrictions.eq("NCodEmpresa", empresa.getNCodEmpresa()));
        criteria.add(Restrictions.eq("NFlgActivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetPregunta> obtenerListaPreguntasByInspeccion(SegDetInsTelefonica inspeccion){
        final StringBuffer sql = new StringBuffer("");
        sql.append("SELECT p.n_cod_empresa, p.n_cod_pregunta, p.v_descripcion, p.n_flg_activo, ");
        sql.append("       p.v_usu_creacion, p.d_fec_creacion, p.v_ip_creacion, ");
        sql.append("       p.v_usu_modificacion, p.d_fec_modificacion, p.v_ip_modificacion ");
        sql.append("  FROM SEG_REL_CUESTIONARIO c, ");
        sql.append("       SEG_DET_PREGUNTA p ");
        sql.append(" WHERE c.n_cod_empresa = p.n_cod_empresa ");
        sql.append("   AND c.n_cod_pregunta = p.n_cod_pregunta ");
        sql.append("   AND c.n_cod_instelefonica = ").append(inspeccion.getNCodInstelefonica());
        
        List resultSet = (List)getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql.toString()).list();
                }
            });
        
        List<SegDetPregunta> lista = new ArrayList();
        if(resultSet!=null && !resultSet.isEmpty()){
            for (Object resultSet1 : resultSet) {
                Object[] objeto = (Object[]) resultSet1;
                SegDetPreguntaId segDetPreguntaId = new SegDetPreguntaId();
                segDetPreguntaId.setNCodEmpresa((BigDecimal)objeto[0]);
                segDetPreguntaId.setNCodPregunta((BigDecimal)objeto[1]);
                SegDetPregunta segDetPregunta = new SegDetPregunta();
                segDetPregunta.setId(segDetPreguntaId);
                segDetPregunta.setNCodEmpresa((BigDecimal)objeto[0]);
                segDetPregunta.setNCodPregunta((BigDecimal)objeto[1]);
                segDetPregunta.setVDescripcion((String)objeto[2]);
                segDetPregunta.setNFlgActivo((BigDecimal)objeto[3]);
                segDetPregunta.setVUsuCreacion(((String)objeto[4]));
                segDetPregunta.setDFecCreacion((Date)objeto[5]);
                segDetPregunta.setVIpCreacion((String)objeto[6]);
                segDetPregunta.setVUsuModificacion(((String)objeto[7]));
                segDetPregunta.setDFecModificacion((Date)objeto[8]);
                segDetPregunta.setVIpModificacion((String)objeto[9]);
                lista.add(segDetPregunta);
            }
        }
        return lista;
    }
    
    @Override
    public boolean isPreguntaEnCuestionario(SegDetPregunta pregunta){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegRelCuestionario.class);
        criteria.add(Restrictions.eq("id.NCodPregunta", pregunta.getId().getNCodPregunta()));
        //criteria.setProjection(Projections.rowCount());
        List list = getHibernateTemplate().findByCriteria(criteria);
        Iterator iter = list.iterator();
        return iter.hasNext();
    }

    @Override
    @Transactional(readOnly = false)
    public void registrarPregunta(SegDetPregunta pregunta) {
        getHibernateTemplate().saveOrUpdate(pregunta);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void eliminarPregunta(SegDetPregunta pregunta) {
        getHibernateTemplate().delete(pregunta);
    }
}
