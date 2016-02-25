/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.dao.hibernate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.com.segrop.sgsapp.dao.ExportDao;
import pe.com.segrop.sgsapp.domain.SegCabEntidad;
import pe.com.segrop.sgsapp.domain.SegDetAtributo;
import pe.com.segrop.sgsapp.domain.SegDetAtributoId;
import pe.com.segrop.sgsapp.domain.SegDetCriteria;
import pe.com.segrop.sgsapp.domain.SegDetExport;
import pe.com.segrop.sgsapp.domain.SegDetExportdetalle;
import pe.com.segrop.sgsapp.web.common.Criteria;
import pe.com.segrop.sgsapp.web.common.Data;

/**
 *
 * @author JJ
 */
@Repository(value="ExportDao")
public class ExportDaoHibernate extends HibernateDaoSupport implements ExportDao{
    
    /**
     * Crea una nueva instancia de ExportDaoHibernate
     * @param sessionFactory
     */
    @Autowired
    public ExportDaoHibernate(SessionFactory sessionFactory) {
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
                    //return (Long) session.createSQLQuery("select SGSWEB.SEQ_EXPORT.NEXTVAL as id from dual").addScalar("id", LongType.INSTANCE).uniqueResult();
                    return (Long) session.createSQLQuery("SELECT NEXTVAL('SGSWEB.SEQ_EXPORT') as id").addScalar("id", LongType.INSTANCE).uniqueResult();
                }
            });
    }

    @Override
    public List<SegCabEntidad> obtenerListaEntidades() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabEntidad.class);
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegCabEntidad>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<SegDetAtributo> obtenerListaAtributosByEntidad(SegCabEntidad entidad) {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetAtributo.class);
        criteria.add(Restrictions.eq("NCodEntidad", entidad.getNCodEntidad()));
        criteria.addOrder(Order.asc("VDescripcion"));
        return (List<SegDetAtributo>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetExport> obtenerListaExport() {
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetExport.class);
        criteria.addOrder(Order.asc("VNombre"));
        return (List<SegDetExport>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public SegCabEntidad obtenerEntidadByExport(SegDetExport export){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegCabEntidad.class);
        criteria.add(Restrictions.eq("NCodEntidad", export.getNCodEntidad()));
        return (SegCabEntidad) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<SegDetExportdetalle> obtenerListaExportDetalle(SegDetExport export){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetExportdetalle.class);
        criteria.add(Restrictions.eq("id.NCodExport", export.getId().getNCodExport()));
        criteria.addOrder(Order.asc("NOrden"));
        return (List<SegDetExportdetalle>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetCriteria> obtenerListaCriteria(SegDetExport export){
        DetachedCriteria criteria = DetachedCriteria.forClass(SegDetCriteria.class);
        criteria.add(Restrictions.eq("id.NCodExport", export.getId().getNCodExport()));
        return (List<SegDetCriteria>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<SegDetAtributo> obtenerListaAtributosSeleccionados(List<SegDetExportdetalle> listaDetalle){
        StringBuilder sql = new StringBuilder();
        List<SegDetAtributo> result = null;
        Query query;
        try{
            sql.append("SELECT ");
            sql.append("a.NCodEntidad, ");
            sql.append("a.NCodAtributo, ");
            sql.append("a.VNombre, ");
            sql.append("a.VNombreApp, ");
            sql.append("a.VDescripcion, ");
            sql.append("a.VComentario, ");
            sql.append("a.VUsuCreacion, ");
            sql.append("a.DFecCreacion, ");
            sql.append("a.VIpCreacion, ");
            sql.append("a.VUsuModificacion, ");
            sql.append("a.DFecModificacion, ");
            sql.append("a.VIpModificacion ");
            sql.append("FROM SegDetAtributo a, SegDetExportdetalle d ");
            sql.append("WHERE a.NCodAtributo IN (");
            for(int i=0;i<listaDetalle.size();i++){
                SegDetExportdetalle detalle = listaDetalle.get(i);
                if(i==0){
                    sql.append(detalle.getNCodAtributo());
                }else{
                    sql.append(",").append(detalle.getNCodAtributo());
                }
            }
            sql.append(") ");
            sql.append("AND a.NCodAtributo = d.NCodAtributo ");
            sql.append("AND d.id.NCodExport = ").append(listaDetalle.get(0).getId().getNCodExport().toString()).append(" ");
            sql.append("ORDER BY d.NOrden");
            query = this.currentSession().createQuery(sql.toString());
            List lista = query.list();
            Iterator it = lista.iterator();
            result = new ArrayList<SegDetAtributo>();
            while(it.hasNext()){
                SegDetAtributoId atrId = new SegDetAtributoId();
                SegDetAtributo atr = new SegDetAtributo();
                Object o[] = (Object[])it.next();
                atrId.setNCodEntidad(BigDecimal.valueOf(Long.parseLong(o[0].toString())));
                atrId.setNCodAtributo(BigDecimal.valueOf(Long.parseLong(o[1].toString())));
                atr.setId(atrId);
                atr.setNCodEntidad(atrId.getNCodEntidad());
                atr.setNCodAtributo(atrId.getNCodAtributo());
                atr.setVNombre(o[2] != null ? o[2].toString() : "");
                atr.setVNombreApp(o[3] != null ? o[3].toString() : "");
                atr.setVDescripcion(o[4] != null ? o[4].toString() : "");
                atr.setVComentario(o[5] != null ? o[5].toString() : "");
                atr.setVUsuCreacion(o[6] != null ? o[6].toString() : "");
                atr.setDFecCreacion(o[7] != null ? new Date(((Timestamp)o[7]).getTime()) : null);
                atr.setVIpCreacion(o[8] != null ? o[8].toString() : "");
                atr.setVUsuModificacion(o[9] != null ? o[9].toString() : "");
                atr.setDFecModificacion(o[10] != null ? new Date(((Timestamp)o[10]).getTime()) : null);
                atr.setVIpModificacion(o[11] != null ? o[11].toString() : "");
                result.add(atr);
            }
            
        }catch(DataAccessResourceFailureException e){
            e.getMessage();
        } catch (IllegalStateException e) {
            e.getMessage();
        } catch (NumberFormatException e) {
            e.getMessage();
        }
        return result;
    }
    
    @Override
    public List<SegDetAtributo> obtenerListaAtributosNoSeleccionados(List<SegDetExportdetalle> listaDetalle){
        StringBuilder sql = new StringBuilder();
        List<SegDetAtributo> result = null;
        Query query;
        try{
            sql.append("SELECT ");
            sql.append("NCodEntidad, ");
            sql.append("NCodAtributo, ");
            sql.append("VNombre, ");
            sql.append("VNombreApp, ");
            sql.append("VDescripcion, ");
            sql.append("VComentario, ");
            sql.append("VUsuCreacion, ");
            sql.append("DFecCreacion, ");
            sql.append("VIpCreacion, ");
            sql.append("VUsuModificacion, ");
            sql.append("DFecModificacion, ");
            sql.append("VIpModificacion ");
            sql.append("FROM SegDetAtributo ");
            sql.append("WHERE NCodAtributo NOT IN (");
            for(int i=0;i<listaDetalle.size();i++){
                SegDetExportdetalle detalle = listaDetalle.get(i);
                if(i==0){
                    sql.append(detalle.getNCodAtributo());
                }else{
                    sql.append(",").append(detalle.getNCodAtributo());
                }
            }
            sql.append(") ");
            sql.append("ORDER BY VDescripcion");
            query = this.currentSession().createQuery(sql.toString());
            List lista = query.list();
            Iterator it = lista.iterator();
            result = new ArrayList<SegDetAtributo>();
            while(it.hasNext()){
                SegDetAtributoId atrId = new SegDetAtributoId();
                SegDetAtributo atr = new SegDetAtributo();
                Object o[] = (Object[])it.next();
                atrId.setNCodEntidad(BigDecimal.valueOf(Long.parseLong(o[0].toString())));
                atrId.setNCodAtributo(BigDecimal.valueOf(Long.parseLong(o[1].toString())));
                atr.setId(atrId);
                atr.setNCodEntidad(atrId.getNCodEntidad());
                atr.setNCodAtributo(atrId.getNCodAtributo());
                atr.setVNombre(o[2] != null ? o[2].toString() : "");
                atr.setVNombreApp(o[3] != null ? o[3].toString() : "");
                atr.setVDescripcion(o[4] != null ? o[4].toString() : "");
                atr.setVComentario(o[5] != null ? o[5].toString() : "");
                atr.setVUsuCreacion(o[6] != null ? o[6].toString() : "");
                atr.setDFecCreacion(o[7] != null ? new Date(((Timestamp)o[7]).getTime()) : null);
                atr.setVIpCreacion(o[8] != null ? o[8].toString() : "");
                atr.setVUsuModificacion(o[9] != null ? o[9].toString() : "");
                atr.setDFecModificacion(o[10] != null ? new Date(((Timestamp)o[10]).getTime()) : null);
                atr.setVIpModificacion(o[11] != null ? o[11].toString() : "");
                result.add(atr);
            }
        }catch(IllegalStateException e){
            e.getMessage();
        } catch (NumberFormatException e) {
            e.getMessage();
        } catch (DataAccessResourceFailureException e) {
            e.getMessage();
        }
        return result;
    }
    
    @Override
    public List<Data> getDataTableNovedades(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria) {
        StringBuilder sql = new StringBuilder();
        List<Data> listMap = null;
        Data data;
        String[] values;
        String column;
        try{
            sql.append("SELECT ");
            for(int i=0;i<listaAtributos.size();i++){
                SegDetAtributo atributo = listaAtributos.get(i);
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    column = "e.VRazonSocial";
                }else if("NTipoNovedad".equals(column)){
                    column = "tn.VDescripcion AS VTipoNovedad";
                }else if("NTipoEvento".equals(column)){
                    column = "te.VDescripcion AS VTipoEvento";
                }else if("NEstado".equals(column)){
                    column = "es.VDescripcion AS VEstado";
                }else if("NLocal".equals(column)){
                    column = "l.VDescripcion AS VLocal";
                }else if("NArea".equals(column)){
                    column = "a.VDescripcion AS VArea";
                }else if("NLugar".equals(column)){
                    column = "lu.VDescripcion AS VLugar";
                }else if("NResponsable".equals(column)){
                    column = "r.VNombres || ' ' || r.VApellidos AS VResponsable";
                }else if("NCargo".equals(column)){
                    column = "c.VDescripcion AS VCargo";
                }else if("DFecHora".equals(column)){
                    column = "TO_CHAR(p.DFecHora, 'dd/mm/yyyy hh24:mi:ss') AS VFecHora";
                }else if("DFecCreacion".equals(column)){
                    column = "TO_CHAR(p.DFecCreacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecCreacion";
                }else if("DFecModificacion".equals(column)){
                    column = "TO_CHAR(p.DFecModificacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecModificacion";
                }else{
                    column = "p.".concat(column);
                }
                
                if(i==0){
                    sql.append(column);
                }else{
                    sql.append(", ").append(column);
                }
            }
            sql.append(" FROM SegDetNovedad p");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append(", SegCabEmpresa e");
                }else if("NTipoNovedad".equals(column)){
                    sql.append(", SegDetMaestrodetalle tn");
                }else if("NTipoEvento".equals(column)){
                    sql.append(", SegDetMaestrodetalle te");
                }else if("NEstado".equals(column)){
                    sql.append(", SegDetMaestrodetalle es");
                }else if("NLocal".equals(column)){
                    sql.append(", SegDetLocal l");
                }else if("NArea".equals(column)){
                    sql.append(", SegDetArea a");
                }else if("NLugar".equals(column)){
                    sql.append(", SegDetLugar lu");
                }else if("NResponsable".equals(column)){
                    sql.append(", SegDetResponsable r");
                }else if("NCargo".equals(column)){
                    sql.append(", SegDetCargo c");
                }
            }
            sql.append(" WHERE 1=1 ");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append("AND p.NCodEmpresa = e.NCodEmpresa ");
                }else if("NTipoNovedad".equals(column)){
                    sql.append("AND tn.NCodMaestrodetalle = p.NTipoNovedad ");
                }else if("NTipoEvento".equals(column)){
                    sql.append("AND te.NCodMaestrodetalle = p.NTipoEvento ");
                }else if("NEstado".equals(column)){
                    sql.append("AND es.NCodMaestrodetalle = p.NEstado ");
                }else if("NLocal".equals(column)){
                    sql.append("AND l.NCodLocal = p.NLocal ");
                }else if("NArea".equals(column)){
                    sql.append("AND a.NCodArea = p.NArea ");
                }else if("NLugar".equals(column)){
                    sql.append("AND lu.NCodLugar = p.NLugar ");
                }else if("NResponsable".equals(column)){
                    sql.append("AND r.NCodResponsable = p.NResponsable ");
                }else if("NCargo".equals(column)){
                    sql.append("AND c.NCodCargo = p.NCargo ");
                }
            }
            if(listaCriteria != null && !listaCriteria.isEmpty()){
                for (Criteria c : listaCriteria) {
                    String col = c.getColumn();
                    String cond = c.getCondition();
                    String val = c.getValue();
                    String pre = c.getPrefijo();
                    String su = c.getSufijo();
                    if(col != null && !col.isEmpty() && cond != null && !cond.isEmpty() && val != null && !val.isEmpty()){
                        if("NCodEmpresa".equals(col)){
                            sql.append("AND e.VRazonSocial ").append(cond).append(pre).append(val).append(su);
                        }else if("NTipoNovedad".equals(col)){
                            sql.append("AND tn.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NTipoEvento".equals(col)){
                            sql.append("AND te.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NEstado".equals(col)){
                            sql.append("AND es.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NLocal".equals(col)){
                            sql.append("AND l.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NArea".equals(col)){
                            sql.append("AND a.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NLugar".equals(col)){
                            sql.append("AND lu.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NResponsable".equals(col)){
                            sql.append("AND r.VNombrecompleto ").append(cond).append(pre).append(val).append(su);
                        }else if("NCargo".equals(col)){
                            sql.append("AND c.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else{
                            sql.append("AND p.").append(col).append(" ").append(cond).append(pre).append(val).append(su);
                        }
                    }
                }
            }
            Query query = this.currentSession().createQuery(sql.toString()).setMaxResults(5);
            List result = query.list();
            Iterator it = result.iterator();
            listMap = new ArrayList<Data>();
            while(it.hasNext()){
                data = new Data();
                values = new String[listaAtributos.size()];
                Object o[] = (Object[])it.next();
                for(int i=0;i<listaAtributos.size();i++){
                    SegDetAtributo atributo = listaAtributos.get(i);
                    column = atributo.getVNombreApp();
                    if("NPersona".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NActivo".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NProceso".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NAnalisis".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NSeguimiento".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else{
                        values[i] = o[i].toString();
                    }
                }
                data.setValues(values);
                listMap.add(data);
            }
        }catch(DataAccessResourceFailureException e){
            e.getMessage();
        } catch (IllegalStateException e) {
            e.getMessage();
        }
        return listMap;
    }
    
    @Override
    public List<Data> getDataTableInspeccionPresencial(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria) {
        StringBuilder sql = new StringBuilder();
        ArrayList<Data> listMap = null;
        Data data;
        String[] values;
        String column;
        try{
            sql.append("SELECT ");
            for(int i=0;i<listaAtributos.size();i++){
                SegDetAtributo atributo = listaAtributos.get(i);
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    column = "e.VRazonSocial";
                }else if("NTipoInspeccion".equals(column)){
                    column = "tn.VDescripcion AS VTipoInspeccion";
                }else if("NCodNovedad".equals(column)){
                    column = "n.VDescBreve AS VNombreNovedad";
                }else if("NTipoEvento".equals(column)){
                    column = "te.VDescripcion AS VTipoEvento";
                }else if("NEstado".equals(column)){
                    column = "es.VDescripcion AS VEstado";
                }else if("NLocal".equals(column)){
                    column = "l.VDescripcion AS VLocal";
                }else if("NArea".equals(column)){
                    column = "a.VDescripcion AS VArea";
                }else if("NLugar".equals(column)){
                    column = "lu.VDescripcion AS VLugar";
                }else if("NResponsable".equals(column)){
                    column = "r.VNombres || ' ' || r.VApellidos AS VResponsable";
                }else if("NCargo".equals(column)){
                    column = "c.VDescripcion AS VCargo";
                }else if("DFecHora".equals(column)){
                    column = "TO_CHAR(p.DFecHora, 'dd/mm/yyyy hh24:mi:ss') AS VFecHora";
                }else if("DFecCreacion".equals(column)){
                    column = "TO_CHAR(p.DFecCreacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecCreacion";
                }else if("DFecModificacion".equals(column)){
                    column = "TO_CHAR(p.DFecModificacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecModificacion";
                }else{
                    column = "p.".concat(column);
                }
                
                if(i==0){
                    sql.append(column);
                }else{
                    sql.append(", ").append(column);
                }
            }
            sql.append(" FROM SegDetInsPresencial p");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append(", SegCabEmpresa e");
                }else if("NCodNovedad".equals(column)){
                    sql.append(", SegDetNovedad n");
                }else if("NTipoInspeccion".equals(column)){
                    sql.append(", SegDetMaestrodetalle tn");
                }else if("NTipoEvento".equals(column)){
                    sql.append(", SegDetMaestrodetalle te");
                }else if("NEstado".equals(column)){
                    sql.append(", SegDetMaestrodetalle es");
                }else if("NLocal".equals(column)){
                    sql.append(", SegDetLocal l");
                }else if("NArea".equals(column)){
                    sql.append(", SegDetArea a");
                }else if("NLugar".equals(column)){
                    sql.append(", SegDetLugar lu");
                }else if("NResponsable".equals(column)){
                    sql.append(", SegDetResponsable r");
                }else if("NCargo".equals(column)){
                    sql.append(", SegDetCargo c");
                }
            }
            sql.append(" WHERE 1=1 ");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append("AND p.NCodEmpresa = e.NCodEmpresa ");
                }else if("NCodNovedad".equals(column)){
                    sql.append("AND n.NCodNovedad = p.NCodNovedad ");
                }else if("NTipoInspeccion".equals(column)){
                    sql.append("AND tn.NCodMaestrodetalle = p.NTipoInspeccion ");
                }else if("NTipoEvento".equals(column)){
                    sql.append("AND te.NCodMaestrodetalle = p.NTipoEvento ");
                }else if("NEstado".equals(column)){
                    sql.append("AND es.NCodMaestrodetalle = p.NEstado ");
                }else if("NLocal".equals(column)){
                    sql.append("AND l.NCodLocal = p.NLocal ");
                }else if("NArea".equals(column)){
                    sql.append("AND a.NCodArea = p.NArea ");
                }else if("NLugar".equals(column)){
                    sql.append("AND lu.NCodLugar = p.NLugar ");
                }else if("NResponsable".equals(column)){
                    sql.append("AND r.NCodResponsable = p.NResponsable ");
                }else if("NCargo".equals(column)){
                    sql.append("AND c.NCodCargo = p.NCargo ");
                }
            }
            if(listaCriteria != null && !listaCriteria.isEmpty()){
                for (Criteria c : listaCriteria) {
                    String col = c.getColumn();
                    String cond = c.getCondition();
                    String val = c.getValue();
                    String pre = c.getPrefijo();
                    String su = c.getSufijo();
                    if(col != null && !col.isEmpty() && cond != null && !cond.isEmpty() && val != null && !val.isEmpty()){
                        if("NCodEmpresa".equals(col)){
                            sql.append("AND e.VRazonSocial ").append(cond).append(pre).append(val).append(su);
                        }else if("NCodNovedad".equals(col)){
                            sql.append("AND n.VDescBreve ").append(cond).append(pre).append(val).append(su);
                        }else if("NTipoInspeccion".equals(col)){
                            sql.append("AND tn.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NTipoEvento".equals(col)){
                            sql.append("AND te.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NEstado".equals(col)){
                            sql.append("AND es.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NLocal".equals(col)){
                            sql.append("AND l.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NArea".equals(col)){
                            sql.append("AND a.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NLugar".equals(col)){
                            sql.append("AND lu.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NResponsable".equals(col)){
                            sql.append("AND r.VNombrecompleto ").append(cond).append(pre).append(val).append(su);
                        }else if("NCargo".equals(col)){
                            sql.append("AND c.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else{
                            sql.append("AND p.").append(col).append(" ").append(cond).append(pre).append(val).append(su);
                        }
                    }
                }
            }
            Query query = this.currentSession().createQuery(sql.toString()).setMaxResults(5);
            List result = query.list();
            Iterator it = result.iterator();
            listMap = new ArrayList<Data>();
            while(it.hasNext()){
                data = new Data();
                values = new String[listaAtributos.size()];
                Object o[] = (Object[])it.next();
                for(int i=0;i<listaAtributos.size();i++){
                    SegDetAtributo atributo = listaAtributos.get(i);
                    column = atributo.getVNombreApp();
                    if("NPersona".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NActivo".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NProceso".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NAnalisis".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NSeguimiento".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NCumpleControl".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else if("NControlAdicional".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else{
                        values[i] = o[i].toString();
                    }
                }
                data.setValues(values);
                listMap.add(data);
            }
        }catch(DataAccessResourceFailureException e){
            e.getMessage();
        } catch (IllegalStateException e) {
            e.getMessage();
        }
        return listMap;
    }
    
    @Override
    public List<Data> getDataTableInspeccionTelefonica(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria) {
        StringBuilder sql = new StringBuilder();
        ArrayList<Data> listMap = null;
        Data data;
        String[] values;
        String column;
        try{
            sql.append("SELECT ");
            for(int i=0;i<listaAtributos.size();i++){
                SegDetAtributo atributo = listaAtributos.get(i);
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    column = "e.VRazonSocial";
                }else if("NEstado".equals(column)){
                    column = "es.VDescripcion AS VEstado";
                }else if("NLugar".equals(column)){
                    column = "lu.VDescripcion AS VLugar";
                }else if("NResponsable".equals(column)){
                    column = "r.VNombres || ' ' || r.VApellidos AS VResponsable";
                }else if("NCargo".equals(column)){
                    column = "c.VDescripcion AS VCargo";
                }else if("DFecCreacion".equals(column)){
                    column = "TO_CHAR(p.DFecCreacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecCreacion";
                }else if("DFecModificacion".equals(column)){
                    column = "TO_CHAR(p.DFecModificacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecModificacion";
                }else{
                    column = "p.".concat(column);
                }
                
                if(i==0){
                    sql.append(column);
                }else{
                    sql.append(", ").append(column);
                }
            }
            sql.append(" FROM SegDetInsTelefonica p");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append(", SegCabEmpresa e");
                }else if("NEstado".equals(column)){
                    sql.append(", SegDetMaestrodetalle es");
                }else if("NLugar".equals(column)){
                    sql.append(", SegCabLugar lu");
                }else if("NResponsable".equals(column)){
                    sql.append(", SegCabResponsable r");
                }else if("NCargo".equals(column)){
                    sql.append(", SegCabCargo c");
                }
            }
            sql.append(" WHERE 1=1 ");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append("AND p.NCodEmpresa = e.NCodEmpresa ");
                }else if("NEstado".equals(column)){
                    sql.append("AND es.NCodMaestrodetalle = p.NEstado ");
                }else if("NLugar".equals(column)){
                    sql.append("AND lu.NCodLugar = p.NLugar ");
                }else if("NResponsable".equals(column)){
                    sql.append("AND r.NCodResponsable = p.NResponsable ");
                }else if("NCargo".equals(column)){
                    sql.append("AND c.NCodCargo = p.NCargo ");
                }
            }
            if(listaCriteria != null && !listaCriteria.isEmpty()){
                for (Criteria c : listaCriteria) {
                    String col = c.getColumn();
                    String cond = c.getCondition();
                    String val = c.getValue();
                    String pre = c.getPrefijo();
                    String su = c.getSufijo();
                    if(col != null && !col.isEmpty() && cond != null && !cond.isEmpty() && val != null && !val.isEmpty()){
                        if("NCodEmpresa".equals(col)){
                            sql.append("AND e.VRazonSocial ").append(cond).append(pre).append(val).append(su);
                        }else if("NEstado".equals(col)){
                            sql.append("AND es.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NLugar".equals(col)){
                            sql.append("AND lu.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NResponsable".equals(col)){
                            sql.append("AND r.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NCargo".equals(col)){
                            sql.append("AND c.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else{
                            sql.append("AND p.").append(col).append(" ").append(cond).append(pre).append(val).append(su);
                        }
                    }
                }
            }
            Query query = this.currentSession().createQuery(sql.toString()).setMaxResults(5);
            List result = query.list();
            Iterator it = result.iterator();
            listMap = new ArrayList<Data>();
            while(it.hasNext()){
                data = new Data();
                values = new String[listaAtributos.size()];
                Object o[] = (Object[])it.next();
                for(int i=0;i<listaAtributos.size();i++){
                    SegDetAtributo atributo = listaAtributos.get(i);
                    column = atributo.getVNombreApp();
                    if("NSeguimiento".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else{
                        values[i] = o[i].toString();
                    }
                }
                data.setValues(values);
                listMap.add(data);
            }
        }catch(DataAccessResourceFailureException e){
            e.getMessage();
        } catch (IllegalStateException e) {
            e.getMessage();
        }
        return listMap;
    }
    
    @Override
    public List<Data> getDataTableCapacitacion(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria) {
        StringBuilder sql = new StringBuilder();
        ArrayList<Data> listMap = null;
        Data data;
        String[] values;
        String column;
        try{
            sql.append("SELECT ");
            for(int i=0;i<listaAtributos.size();i++){
                SegDetAtributo atributo = listaAtributos.get(i);
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    column = "e.VRazonSocial";
                }else if("NEstado".equals(column)){
                    column = "es.VDescripcion AS VEstado";
                }else if("NLugar".equals(column)){
                    column = "lu.VDescripcion AS VLugar";
                }else if("NResponsable".equals(column)){
                    column = "r.VNombres || ' ' || r.VApellidos AS VResponsable";
                }else if("NCargo".equals(column)){
                    column = "c.VDescripcion AS VCargo";
                }else if("DFecCreacion".equals(column)){
                    column = "TO_CHAR(p.DFecCreacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecCreacion";
                }else if("DFecModificacion".equals(column)){
                    column = "TO_CHAR(p.DFecModificacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecModificacion";
                }else{
                    column = "p.".concat(column);
                }
                
                if(i==0){
                    sql.append(column);
                }else{
                    sql.append(", ").append(column);
                }
            }
            sql.append(" FROM SegDetCapacitacion p");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append(", SegCabEmpresa e");
                }else if("NEstado".equals(column)){
                    sql.append(", SegDetMaestrodetalle es");
                }else if("NLugar".equals(column)){
                    sql.append(", SegCabLugar lu");
                }else if("NResponsable".equals(column)){
                    sql.append(", SegCabResponsable r");
                }else if("NCargo".equals(column)){
                    sql.append(", SegCabCargo c");
                }
            }
            sql.append(" WHERE 1=1 ");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append("AND p.NCodEmpresa = e.NCodEmpresa ");
                }else if("NEstado".equals(column)){
                    sql.append("AND es.NCodMaestrodetalle = p.NEstado ");
                }else if("NLugar".equals(column)){
                    sql.append("AND lu.NCodLugar = p.NLugar ");
                }else if("NResponsable".equals(column)){
                    sql.append("AND r.NCodResponsable = p.NResponsable ");
                }else if("NCargo".equals(column)){
                    sql.append("AND c.NCodCargo = p.NCargo ");
                }
            }
            if(listaCriteria != null && !listaCriteria.isEmpty()){
                for (Criteria c : listaCriteria) {
                    String col = c.getColumn();
                    String cond = c.getCondition();
                    String val = c.getValue();
                    String pre = c.getPrefijo();
                    String su = c.getSufijo();
                    if(col != null && !col.isEmpty() && cond != null && !cond.isEmpty() && val != null && !val.isEmpty()){
                        if("NCodEmpresa".equals(col)){
                            sql.append("AND e.VRazonSocial ").append(cond).append(pre).append(val).append(su);
                        }else if("NEstado".equals(col)){
                            sql.append("AND es.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NLugar".equals(col)){
                            sql.append("AND lu.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NResponsable".equals(col)){
                            sql.append("AND r.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NCargo".equals(col)){
                            sql.append("AND c.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else{
                            sql.append("AND p.").append(col).append(" ").append(cond).append(pre).append(val).append(su);
                        }
                    }
                }
            }
            Query query = this.currentSession().createQuery(sql.toString()).setMaxResults(5);
            List result = query.list();
            Iterator it = result.iterator();
            listMap = new ArrayList<Data>();
            while(it.hasNext()){
                data = new Data();
                values = new String[listaAtributos.size()];
                Object o[] = (Object[])it.next();
                for(int i=0;i<listaAtributos.size();i++){
                    SegDetAtributo atributo = listaAtributos.get(i);
                    column = atributo.getVNombreApp();
                    if("NSeguimiento".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else{
                        values[i] = o[i].toString();
                    }
                }
                data.setValues(values);
                listMap.add(data);
            }
        }catch(DataAccessResourceFailureException e){
            e.getMessage();
        } catch (IllegalStateException e) {
            e.getMessage();
        }
        return listMap;
    }
    
    @Override
    public List<Data> getDataTableDocumento(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria) {
        StringBuilder sql = new StringBuilder();
        ArrayList<Data> listMap = null;
        Data data;
        String[] values;
        String column;
        try{
            sql.append("SELECT ");
            for(int i=0;i<listaAtributos.size();i++){
                SegDetAtributo atributo = listaAtributos.get(i);
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    column = "e.VRazonSocial";
                }else if("NProcedencia".equals(column)){
                    column = "pr.VDescripcion AS VProcedencia";
                }else if("NTipoDocumento".equals(column)){
                    column = "td.VDescripcion AS VTipoDocumento";
                }else if("DFecEmision".equals(column)){
                    column = "TO_CHAR(p.DFecEmision, 'dd/mm/yyyy hh24:mi:ss') AS VFecEmision";
                }else if("DFecCreacion".equals(column)){
                    column = "TO_CHAR(p.DFecCreacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecCreacion";
                }else if("DFecModificacion".equals(column)){
                    column = "TO_CHAR(p.DFecModificacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecModificacion";
                }else{
                    column = "p.".concat(column);
                }
                
                if(i==0){
                    sql.append(column);
                }else{
                    sql.append(", ").append(column);
                }
            }
            sql.append(" FROM SegDetDocumento p");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append(", SegCabEmpresa e");
                }else if("NProcedencia".equals(column)){
                    sql.append(", SegDetMaestrodetalle pr");
                }else if("NTipoDocumento".equals(column)){
                    sql.append(", SegDetMaestrodetalle td");
                }
            }
            sql.append(" WHERE 1=1 ");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append("AND p.NCodEmpresa = e.NCodEmpresa ");
                }else if("NProcedencia".equals(column)){
                    sql.append("AND pr.NCodMaestrodetalle = p.NProcedencia ");
                }else if("NTipoDocumento".equals(column)){
                    sql.append("AND td.NCodMaestrodetalle = p.NTipoDocumento ");
                }
            }
            if(listaCriteria != null && !listaCriteria.isEmpty()){
                for (Criteria c : listaCriteria) {
                    String col = c.getColumn();
                    String cond = c.getCondition();
                    String val = c.getValue();
                    String pre = c.getPrefijo();
                    String su = c.getSufijo();
                    if(col != null && !col.isEmpty() && cond != null && !cond.isEmpty() && val != null && !val.isEmpty()){
                        if("NCodEmpresa".equals(col)){
                            sql.append("AND e.VRazonSocial ").append(cond).append(pre).append(val).append(su);
                        }else if("NProcedencia".equals(col)){
                            sql.append("AND pr.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NTipoDocumento".equals(col)){
                            sql.append("AND td.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else{
                            sql.append("AND p.").append(col).append(" ").append(cond).append(pre).append(val).append(su);
                        }
                    }
                }
            }
            Query query = this.currentSession().createQuery(sql.toString()).setMaxResults(5);
            List result = query.list();
            Iterator it = result.iterator();
            listMap = new ArrayList<Data>();
            while(it.hasNext()){
                data = new Data();
                values = new String[listaAtributos.size()];
                Object o[] = (Object[])it.next();
                for(int i=0;i<listaAtributos.size();i++){
                    SegDetAtributo atributo = listaAtributos.get(i);
                    column = atributo.getVNombreApp();
                    if("NActivo".equals(column)){
                        values[i] = "1".equals(o[i].toString()) ? "SI" : "NO";
                    }else{
                        values[i] = o[i].toString();
                    }
                }
                data.setValues(values);
                listMap.add(data);
            }
        }catch(DataAccessResourceFailureException e){
            e.getMessage();
        } catch (IllegalStateException e) {
            e.getMessage();
        }
        return listMap;
    }
    
    @Override
    public List<Data> getDataTableRiesgo(List<SegDetAtributo> listaAtributos, List<Criteria> listaCriteria) {
        StringBuilder sql = new StringBuilder();
        ArrayList<Data> listMap = null;
        Data data;
        String[] values;
        String column;
        try{
            sql.append("SELECT ");
            for(int i=0;i<listaAtributos.size();i++){
                SegDetAtributo atributo = listaAtributos.get(i);
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    column = "e.VRazonSocial";
                }else if("NOcurrencia".equals(column)){
                    column = "oc.VDescripcion AS VOcurrencia";
                }else if("NImpacto".equals(column)){
                    column = "im.VDescripcion AS VImpacto";
                }else if("DFecCreacion".equals(column)){
                    column = "TO_CHAR(p.DFecCreacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecCreacion";
                }else if("DFecModificacion".equals(column)){
                    column = "TO_CHAR(p.DFecModificacion, 'dd/mm/yyyy hh24:mi:ss') AS VFecModificacion";
                }else{
                    column = "p.".concat(column);
                }
                
                if(i==0){
                    sql.append(column);
                }else{
                    sql.append(", ").append(column);
                }
            }
            sql.append(" FROM SegDetRiesgo p");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append(", SegCabEmpresa e");
                }else if("NOcurrencia".equals(column)){
                    sql.append(", SegDetMaestrodetalle oc");
                }else if("NImpacto".equals(column)){
                    sql.append(", SegDetMaestrodetalle im");
                }
            }
            sql.append(" WHERE 1=1 ");
            for (SegDetAtributo atributo : listaAtributos) {
                column = atributo.getVNombreApp();
                if("NCodEmpresa".equals(column)){
                    sql.append("AND p.NCodEmpresa = e.NCodEmpresa ");
                }else if("NOcurrencia".equals(column)){
                    sql.append("AND oc.NCodMaestrodetalle = p.NOcurrencia ");
                }else if("NImpacto".equals(column)){
                    sql.append("AND im.NCodMaestrodetalle = p.NImpacto ");
                }
            }
            if(listaCriteria != null && !listaCriteria.isEmpty()){
                for (Criteria c : listaCriteria) {
                    String col = c.getColumn();
                    String cond = c.getCondition();
                    String val = c.getValue();
                    String pre = c.getPrefijo();
                    String su = c.getSufijo();
                    if(col != null && !col.isEmpty() && cond != null && !cond.isEmpty() && val != null && !val.isEmpty()){
                        if("NCodEmpresa".equals(col)){
                            sql.append("AND e.VRazonSocial ").append(cond).append(pre).append(val).append(su);
                        }else if("NOcurrencia".equals(col)){
                            sql.append("AND oc.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else if("NImpacto".equals(col)){
                            sql.append("AND im.VDescripcion ").append(cond).append(pre).append(val).append(su);
                        }else{
                            sql.append("AND p.").append(col).append(" ").append(cond).append(pre).append(val).append(su);
                        }
                    }
                }
            }
            Query query = this.currentSession().createQuery(sql.toString()).setMaxResults(5);
            List result = query.list();
            Iterator it = result.iterator();
            listMap = new ArrayList<Data>();
            while(it.hasNext()){
                data = new Data();
                values = new String[listaAtributos.size()];
                Object o[] = (Object[])it.next();
                for(int i=0;i<listaAtributos.size();i++){
                    values[i] = o[i].toString();
                }
                data.setValues(values);
                listMap.add(data);
            }
        }catch(DataAccessResourceFailureException e){
            e.getMessage();
        } catch (IllegalStateException e) {
            e.getMessage();
        }
        return listMap;
    }
    
    @Override
    public void registrarConfiguracion(SegDetExport export) {
        getHibernateTemplate().saveOrUpdate(export);
    }
    
    @Override
    public void deleteExport(SegDetExport export){
        getHibernateTemplate().delete(export);
    }
}
