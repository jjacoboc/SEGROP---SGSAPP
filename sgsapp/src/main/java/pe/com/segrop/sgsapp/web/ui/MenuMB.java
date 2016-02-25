/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.servlet.http.HttpSession;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import pe.com.segrop.sgsapp.dao.AccionDao;
import pe.com.segrop.sgsapp.dao.ExportDao;
import pe.com.segrop.sgsapp.dao.InsPreAccionDao;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDao;
import pe.com.segrop.sgsapp.dao.InspreEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDao;
import pe.com.segrop.sgsapp.dao.NovedadEvaluacionDetalleDao;
import pe.com.segrop.sgsapp.dao.PerfilDao;
import pe.com.segrop.sgsapp.dao.PermisoDao;
import pe.com.segrop.sgsapp.dao.RiesgoDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencial;
import pe.com.segrop.sgsapp.domain.SegDetInsPresencialId;
import pe.com.segrop.sgsapp.domain.SegDetNovedad;
import pe.com.segrop.sgsapp.domain.SegDetNovedadId;
import pe.com.segrop.sgsapp.domain.SegDetObjeto;
import pe.com.segrop.sgsapp.domain.SegDetPerfil;
import pe.com.segrop.sgsapp.domain.SegDetPerfilId;
import pe.com.segrop.sgsapp.domain.SegDetRiesgo;
import pe.com.segrop.sgsapp.domain.SegDetRiesgoId;
import pe.com.segrop.sgsapp.web.common.BaseBean;
import pe.com.segrop.sgsapp.web.common.Parameters;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author JJ
 */
@ManagedBean
@SessionScoped
public class MenuMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private MenuModel model;

    /**
     * Creates a new instance of MenuMB
     */
    public MenuMB() {

        /*
         FacesContext facesCtx = FacesContext.getCurrentInstance();
         ELContext elCtx = facesCtx.getELContext();
         ExpressionFactory expFact = facesCtx.getApplication().getExpressionFactory();
         LoginMB loginMB = (LoginMB)BaseBean.getSessionAttribute("loginMB");
         HashMap mapObjetos = loginMB.getMapObjetos();
         */
//        model = new DefaultMenuModel();
//        DefaultSubMenu submenu = new DefaultSubMenu();
//        submenu.setLabel("Administracion");
//        //submenu.setIcon("ui-icon-lightbulb");
//        submenu.setId("administracion");
        /*
         if(mapObjetos.containsKey("OPC_MENU_ADMINISTRACION")){
         submenu.setRendered((Boolean)mapObjetos.get("OPC_MENU_ADMINISTRACION"));
         }else{
         submenu.setRendered(false);
         }
         */
//        DefaultSubMenu menuGroup = new DefaultSubMenu();
//        menuGroup.setLabel("Gestion");
//        //menuGroup.setIcon("ui-icon-gear");
//        menuGroup.setId("gestion");
        /*
         if(mapObjetos.containsKey("OPC_MENU_GESTION")){
         menuGroup.setRendered((Boolean)mapObjetos.get("OPC_MENU_GESTION"));
         }else{
         menuGroup.setRendered(false);
         }
         */
//        DefaultMenuItem item = new DefaultMenuItem();
//        item.setValue("Locales");
//        //item.setIcon("ui-icon-home");
//        item.setAjax(true);
//        item.setId("locales");
        /*
         if(mapObjetos.containsKey("OPC_MENU_LOCAL")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_LOCAL"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "local", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionLocal}", Void.TYPE, new Class[]{ActionEvent.class}));
         item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionLocal}", String.class, new Class[0]));
         */
//        menuGroup.addElement(item);
//        
//        submenu.addElement(menuGroup);
//        
//        model.addElement(submenu);
//        model.addElement(item);
//        
//        model.generateUniqueIds();
        /*
         Menuitem dropDownMenu = new HtmlDropDownMenu();
         dropDownMenu.setValue("AdministraciÃƒÂ³n");
         //submenu.setIcon("ui-icon-lightbulb");
         dropDownMenu.setId("administracion");
         if(mapObjetos.containsKey("OPC_MENU_ADMINISTRACION")){
         dropDownMenu.setRendered((Boolean)mapObjetos.get("OPC_MENU_ADMINISTRACION"));
         }else{
         dropDownMenu.setRendered(false);
         }
        
         HtmlMenuGroup menuGroup = new HtmlMenuGroup();
         menuGroup.setValue("GestiÃƒÂ³n");
         //menuGroup.setIcon("ui-icon-gear");
         menuGroup.setId("gestion");
         if(mapObjetos.containsKey("OPC_MENU_GESTION")){
         menuGroup.setRendered((Boolean)mapObjetos.get("OPC_MENU_GESTION"));
         }else{
         menuGroup.setRendered(false);
         }
         dropDownMenu.getChildren().add(menuGroup);
        
         HtmlMenuItem item = new HtmlMenuItem();
         item.setValue("Locales");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-home");
         item.setSubmitMode("ajax");
         item.setId("locales");
         if(mapObjetos.containsKey("OPC_MENU_LOCAL")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_LOCAL"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "local", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionLocal}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionLocal}", String.class, new Class[0]));
         menuGroup.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Areas");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-arrow-4-diag");
         item.setSubmitMode("ajax");
         item.setId("area");
         if(mapObjetos.containsKey("OPC_MENU_AREA")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_AREA"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "area", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionArea}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionArea}", String.class, new Class[0]));
         menuGroup.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Lugares");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-flag");
         item.setSubmitMode("ajax");
         item.setId("lugares");
         if(mapObjetos.containsKey("OPC_MENU_LUGAR")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_LUGAR"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "lugar", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionLugar}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionLugar}", String.class, new Class[0]));
         menuGroup.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Responsables");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-person");
         item.setSubmitMode("ajax");
         item.setId("responsables");
         if(mapObjetos.containsKey("OPC_MENU_RESPONSABLE")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_RESPONSABLE"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "responsable", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionResponsable}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionResponsable}", String.class, new Class[0]));
         menuGroup.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Cargos");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-contact");
         item.setSubmitMode("ajax");
         item.setId("cargos");
         if(mapObjetos.containsKey("OPC_MENU_CARGO")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_CARGO"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "cargo", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionCargo}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionCargo}", String.class, new Class[0]));
         menuGroup.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Empresas");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-suitcase");
         item.setSubmitMode("ajax");
         item.setId("empresas");
         if(mapObjetos.containsKey("OPC_MENU_EMPRESA")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_EMPRESA"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "empresa", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionEmpresa}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionEmpresa}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Usuarios");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-person");
         item.setSubmitMode("ajax");
         item.setId("usuarios");
         if(mapObjetos.containsKey("OPC_MENU_USUARIO")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_USUARIO"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "usuario", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionUsuario}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionUsuario}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Perfiles");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-contact");
         item.setSubmitMode("ajax");
         item.setId("perfiles");
         if(mapObjetos.containsKey("OPC_MENU_PERFIL")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_PERFIL"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "perfil", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionPerfil}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionPerfil}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Asignar Perfiles");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-transfer-e-w");
         item.setSubmitMode("ajax");
         item.setId("asignarperfiles");
         if(mapObjetos.containsKey("OPC_MENU_ASIGNARPERFIL")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_ASIGNARPERFIL"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "asignarperfil", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionAsignarPerfil}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionAsignarPerfil}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Asignar Permisos");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-transfer-e-w");
         item.setSubmitMode("ajax");
         item.setId("asignarpermisos");
         if(mapObjetos.containsKey("OPC_MENU_ASIGNARPERMISO")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_ASIGNARPERMISO"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "asignarpermiso", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionAsignarPermiso}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionAsignarPermiso}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         model.getChildren().add(dropDownMenu);
         */
        /**
         * **********************************************************************************************************************************
         */
        /*
         dropDownMenu = new HtmlDropDownMenu();
         dropDownMenu.setValue("InspecciÃƒÂ³n");
         //dropDownMenu.setIcon("ui-icon-search");
         dropDownMenu.setId("inspeccion");
         if(mapObjetos.containsKey("OPC_MENU_INSPECCION")){
         dropDownMenu.setRendered((Boolean)mapObjetos.get("OPC_MENU_INSPECCION"));
         }else{
         dropDownMenu.setRendered(false);
         }
        
         item = new HtmlMenuItem();
         item.setValue("Presencial");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-clipboard");
         item.setSubmitMode("ajax");
         item.setId("presencial");
         if(mapObjetos.containsKey("OPC_MENU_PRESENCIAL")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_PRESENCIAL"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "presencial", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionInspeccionPresencial}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionInspeccionPresencial}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("TelefÃƒÂ³nica");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-link");
         item.setSubmitMode("ajax");
         item.setId("telefonica");
         if(mapObjetos.containsKey("OPC_MENU_TELEFONICA")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_TELEFONICA"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "telefonica", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionInspeccionTelefonica}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionInspeccionTelefonica}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         model.getChildren().add(dropDownMenu);
         */
        /**
         * ***********************************************************************************************************************************
         */
        /*
         dropDownMenu = new HtmlDropDownMenu();
         dropDownMenu.setValue("Documentos");
         //dropDownMenu.setIcon("ui-icon-search");
         dropDownMenu.setId("documentos");
         if(mapObjetos.containsKey("OPC_MENU_DOCUMENTO")){
         dropDownMenu.setRendered((Boolean)mapObjetos.get("OPC_MENU_DOCUMENTO"));
         }else{
         dropDownMenu.setRendered(false);
         }
        
         item = new HtmlMenuItem();
         item.setValue("GestiÃƒÂ³n");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-document");
         item.setSubmitMode("ajax");
         item.setId("gestiondoc");
         if(mapObjetos.containsKey("OPC_MENU_DOCUMENTO_GESTION")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_DOCUMENTO_GESTION"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "documento", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionDocumento}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionDocumento}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("RestauraciÃƒÂ³n");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-document");
         item.setSubmitMode("ajax");
         item.setId("restauracion");
         if(mapObjetos.containsKey("OPC_MENU_RESTAURACION")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_RESTAURACION"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "restauracion", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toRestauracionDocumento}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionDocumento}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("AnulaciÃƒÂ³n");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-document");
         item.setSubmitMode("ajax");
         item.setId("anulacion");
         if(mapObjetos.containsKey("OPC_MENU_ANULACION")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_ANULACION"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "anulacion", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toAnulacionDocumento}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionDocumento}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         model.getChildren().add(dropDownMenu);
         */
        /**
         * **********************************************************************************************************************************
         */
        /*
         dropDownMenu = new HtmlDropDownMenu();
         dropDownMenu.setValue("Seguridad");
         //dropDownMenu.setIcon("ui-icon-search");
         dropDownMenu.setId("seguridad");
         if(mapObjetos.containsKey("OPC_MENU_SEGURIDAD")){
         dropDownMenu.setRendered((Boolean)mapObjetos.get("OPC_MENU_SEGURIDAD"));
         }else{
         dropDownMenu.setRendered(false);
         }
        
         item = new HtmlMenuItem();
         item.setValue("Novedades");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-note");
         item.setSubmitMode("ajax");
         item.setId("novedades");
         if(mapObjetos.containsKey("OPC_MENU_NOVEDADES")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_NOVEDADES"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "novedad", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionNovedad}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionNovedad}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Capacitaciones");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-note");
         item.setSubmitMode("ajax");
         item.setId("capacitaciones");
         if(mapObjetos.containsKey("OPC_MENU_CAPACITACIONES")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_CAPACITACIONES"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "capacitacion", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toGestionCapacitacion}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionCapacitacion}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         model.getChildren().add(dropDownMenu);
         */
        /**
         * **********************************************************************************************************************************
         */
        /*
         dropDownMenu = new HtmlDropDownMenu();
         dropDownMenu.setValue("Riesgos");
         //dropDownMenu.setIcon("ui-icon-search");
         dropDownMenu.setId("riesgos");
         if(mapObjetos.containsKey("OPC_MENU_RIESGOS")){
         dropDownMenu.setRendered((Boolean)mapObjetos.get("OPC_MENU_RIESGOS"));
         }else{
         dropDownMenu.setRendered(false);
         }
        
         item = new HtmlMenuItem();
         item.setValue("EvaluaciÃƒÂ³n");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-check");
         item.setSubmitMode("ajax");
         item.setId("evaluacion");
         if(mapObjetos.containsKey("OPC_MENU_EVALUACION")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_EVALUACION"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "riesgo", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toEvaluacionRiesgo}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toEvaluacionRiesgo}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Matriz");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-calculator");
         item.setSubmitMode("ajax");
         item.setId("matriz");
         if(mapObjetos.containsKey("OPC_MENU_MATRIZ")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_MATRIZ"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "matriz", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toMatrizRiesgo}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toMatrizRiesgo}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         model.getChildren().add(dropDownMenu);
         */
        /**
         * **********************************************************************************************************************************
         */
        /*
         dropDownMenu = new HtmlDropDownMenu();
         dropDownMenu.setValue("Herramientas");
         //dropDownMenu.setIcon("ui-icon-search");
         dropDownMenu.setId("reportes");
         if(mapObjetos.containsKey("OPC_MENU_HERRAMIENTAS")){
         dropDownMenu.setRendered((Boolean)mapObjetos.get("OPC_MENU_HERRAMIENTAS"));
         }else{
         dropDownMenu.setRendered(false);
         }
        
         item = new HtmlMenuItem();
         item.setValue("Reportes");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-document");
         item.setSubmitMode("ajax");
         item.setId("reporteNovedades");
         if(mapObjetos.containsKey("OPC_MENU_REPORTES")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_DOCUMENTO"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "reportes", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toReportes}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionDocumento}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         item = new HtmlMenuItem();
         item.setValue("Exportar");
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-document");
         item.setSubmitMode("ajax");
         item.setId("exportar");
         if(mapObjetos.containsKey("OPC_MENU_EXPORTAR")){
         item.setRendered((Boolean)mapObjetos.get("OPC_MENU_EXPORTAR"));
         }else{
         item.setRendered(false);
         }
         item.setActionExpression(expFact.createMethodExpression(elCtx, "export", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.toExport}", Void.TYPE, new Class[]{ActionEvent.class}));
         //item.setActionExpression(expFact.createMethodExpression(elCtx, "#{menuMB.toGestionDocumento}", String.class, new Class[0]));
         dropDownMenu.getChildren().add(item);
        
         model.getChildren().add(dropDownMenu);
         */
        /**
         * **********************************************************************************************************************************
         */
        /*
         dropDownMenu = new HtmlDropDownMenu();
         dropDownMenu.setValue("Cambiar Perfil");
         //dropDownMenu.setIcon("ui-icon-search");
         dropDownMenu.setId("cambiarperfil");
         dropDownMenu.setRendered(loginMB.getListaPerfilesAsignados().size()>1);
         dropDownMenu.setStyle("float: right;");
        
         if(loginMB.getListaPerfilesAsignados() !=null && !loginMB.getListaPerfilesAsignados().isEmpty()){
         for(int i=0;i<loginMB.getListaPerfilesAsignados().size();i++){
         SelectItem selectItem = (SelectItem)loginMB.getListaPerfilesAsignados().get(i);
         if(!loginMB.getPerfilSession().getId().getNCodPerfil().toString().equals(selectItem.getValue())){
         item = new HtmlMenuItem();
         item.setValue(selectItem.getLabel());
         item.setStatus("headerstatus");
         //item.setIcon("ui-icon-bullet");
         item.setSubmitMode("ajax");
         item.setActionExpression(expFact.createMethodExpression(elCtx, "bienvenida", null, new Class[0]));
         item.addActionListener(createMethodActionListener("#{menuMB.switchProfile}", Void.TYPE, new Class[]{ActionEvent.class}));
         item.setId("_"+(String)selectItem.getValue());
         dropDownMenu.getChildren().add(item);
         }
         }
         }
        
         HtmlToolBarGroup toolBarGroup = new HtmlToolBarGroup();
         toolBarGroup.setLocation("right");
         toolBarGroup.getChildren().add(dropDownMenu);
        
         model.getChildren().add(toolBarGroup);
         */
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    @PostConstruct
    public void init() {
        try {
            model = new DefaultMenuModel();

            //First submenu
            DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");

            DefaultMenuItem item = new DefaultMenuItem("External");
            item.setUrl("http://www.primefaces.org");
            item.setIcon("ui-icon-home");
            firstSubmenu.addElement(item);

            model.addElement(firstSubmenu);

            //Second submenu
            DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");

            item = new DefaultMenuItem("Save");
            item.setIcon("ui-icon-disk");
//        item.setCommand("#{menuBean.save}");
//        item.setUpdate("messages");
            secondSubmenu.addElement(item);

            item = new DefaultMenuItem("Delete");
            item.setIcon("ui-icon-close");
//        item.setCommand("#{menuBean.delete}");
            item.setAjax(false);
            secondSubmenu.addElement(item);

            item = new DefaultMenuItem("Redirect");
            item.setIcon("ui-icon-search");
//        item.setCommand("#{menuBean.redirect}");
            secondSubmenu.addElement(item);

            model.addElement(secondSubmenu);
            model.generateUniqueIds();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionUsuario(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("usuarioMB", new UsuarioMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionEmpresa(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("empresaMB", new EmpresaMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionPerfil(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("perfilMB", new PerfilMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionObjeto(ActionEvent event) {
        try {
            this.cleanSession();
            //BaseBean.getSession().setAttribute("objetoMB", new ObjetoMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionDocumento(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("documentoMB", new DocumentoMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toRestauracionDocumento(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("documentoMB", new DocumentoMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toAnulacionDocumento(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("documentoMB", new DocumentoMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionCargo(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("cargoMB", new CargoMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionLocal(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("localMB", new LocalMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionArea(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("areaMB", new AreaMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionLugar(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("lugarMB", new LugarMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionResponsable(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("responsableMB", new ResponsableMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionNovedad(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("novedadMB", new NovedadMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionInspeccionPresencial(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("presencialMB", new PresencialMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionInspeccionTelefonica(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("telefonicaMB", new TelefonicaMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionAsignarPerfil(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("usuarioPerfilMB", new UsuarioPerfilMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionAsignarPermiso(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("permisoMB", new PermisoMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toEvaluacionRiesgo(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("riesgoMB", new RiesgoMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toMatrizRiesgo(ActionEvent event) {
        ResourceBundle bundle;
        try {
            this.cleanSession();
            bundle = ResourceBundle.getBundle(Parameters.getParameters());
            SegCabEmpresa empresaSession = (SegCabEmpresa) BaseBean.getSessionAttribute("empresa");
            BaseBean.getSession().setAttribute("matrizMB", new MatrizMB());
            RiesgoDao riesgoDao = (RiesgoDao) ServiceFinder.findBean("RiesgoDao");
            SegDetRiesgoId segDetRiesgoId = new SegDetRiesgoId();
            segDetRiesgoId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            SegDetRiesgo segDetRiesgo = new SegDetRiesgo();
            segDetRiesgo.setId(segDetRiesgoId);
            List lista = riesgoDao.listarRiesgosMatriz(segDetRiesgo);
            if (lista != null && !lista.isEmpty()) {
                for (Object lista1 : lista) {
                    SegDetRiesgo riesgo = (SegDetRiesgo) lista1;
                    if (riesgo.getNTipoRiesgo().toString().equals(bundle.getString("TIPO_RIESGO_NOVEDAD"))) {
                        AccionDao accionDao = (AccionDao) ServiceFinder.findBean("AccionDao");
                        NovedadEvaluacionDao novedadEvaluacionDao = (NovedadEvaluacionDao) ServiceFinder.findBean("NovedadEvaluacionDao");
                        NovedadEvaluacionDetalleDao novedadEvaluacionDetalleDao = (NovedadEvaluacionDetalleDao) ServiceFinder.findBean("NovedadEvaluacionDetalleDao");
                        SegDetNovedadId segDetNovedadId = new SegDetNovedadId();
                        segDetNovedadId.setNCodEmpresa(riesgo.getNCodEmpresa());
                        segDetNovedadId.setNCodNovedad(riesgo.getNCodRiesgo());
                        SegDetNovedad segDetNovedad = new SegDetNovedad();
                        segDetNovedad.setId(segDetNovedadId);
                        segDetNovedad.setNCodEmpresa(segDetNovedadId.getNCodEmpresa());
                        segDetNovedad.setNCodNovedad(segDetNovedadId.getNCodNovedad());
                        riesgo.setSegDetAcciones(accionDao.obtenerListaAccionesByNovedad(segDetNovedad));
                        riesgo.setSegDetNovEvaluacion(novedadEvaluacionDao.obtenerEvaluacionNovedad(segDetNovedad));
                        riesgo.getSegDetNovEvaluacion().setSegDetNovevalDetalles(novedadEvaluacionDetalleDao.obtenerListaDetalleEvaluacionNovedad(riesgo.getSegDetNovEvaluacion()));
                    } else {
                        InsPreAccionDao insPreAccionDao = (InsPreAccionDao) ServiceFinder.findBean("InsPreAccionDao");
                        InspreEvaluacionDao inspreEvaluacionDao = (InspreEvaluacionDao) ServiceFinder.findBean("InspreEvaluacionDao");
                        InspreEvaluacionDetalleDao inspreEvaluacionDetalleDao = (InspreEvaluacionDetalleDao) ServiceFinder.findBean("InspreEvaluacionDetalleDao");
                        SegDetInsPresencialId segDetInsPresencialId = new SegDetInsPresencialId();
                        segDetInsPresencialId.setNCodEmpresa(riesgo.getNCodEmpresa());
                        segDetInsPresencialId.setNCodInspresencial(riesgo.getNCodRiesgo());
                        SegDetInsPresencial segDetInsPresencial = new SegDetInsPresencial();
                        segDetInsPresencial.setId(segDetInsPresencialId);
                        segDetInsPresencial.setNCodEmpresa(segDetInsPresencialId.getNCodEmpresa());
                        segDetInsPresencial.setNCodInspresencial(segDetInsPresencialId.getNCodInspresencial());
                        riesgo.setSegDetInspreAcciones(insPreAccionDao.obtenerListaAccionesByInspeccionPresencial(segDetInsPresencial));
                        riesgo.setSegDetInspreEvaluacion(inspreEvaluacionDao.obtenerEvaluacionInspeccion(segDetInsPresencial));
                        riesgo.getSegDetInspreEvaluacion().setSegDetInspreevalDetalles(inspreEvaluacionDetalleDao.obtenerListaDetalleEvaluacionInspeccion(riesgo.getSegDetInspreEvaluacion()));
                    }
                }
            }
            BaseBean.getSession().setAttribute("listaRiesgo", lista);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionCapacitacion(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("capacitacionMB", new CapacitacionMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toReportes(ActionEvent event) {
        try {
            this.cleanSession();
            BaseBean.getSession().setAttribute("reporteMB", new ReporteMB());
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toExport(ActionEvent event) {
        try {
            this.cleanSession();
            ExportDao exportDao = (ExportDao) ServiceFinder.findBean("ExportDao");
            ExportMB exportMB = new ExportMB();
            exportMB.setListaExport(exportDao.obtenerListaExport());
            BaseBean.getSession().setAttribute("exportMB", exportMB);
            BaseBean.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void cleanSession() {
        try {
            HttpSession session = BaseBean.getSession();
            if (session != null) {
                Enumeration attributeNames = session.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    String sAttribute = attributeNames.nextElement().toString();
                    if (!sAttribute.matches("empresa|usuario|perfil|mapaObjetos|menuMB|loginMB")) {
                        session.removeAttribute(sAttribute);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void sessionTimeOut(ActionEvent event) {
        try {
            BaseBean.getSession().invalidate();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    private MethodExpression createMethodExpression(String valueExpression, Class<?> valueType, Class<?>[] expectedParamTypes) {

        MethodExpression methodExpression = null;
        try {
            ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
            methodExpression = factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), valueExpression, valueType, expectedParamTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return methodExpression;
    }

    private MethodExpressionActionListener createMethodActionListener(String valueExpression, Class<?> valueType, Class<?>[] expectedParamTypes) {

        MethodExpressionActionListener actionListener = null;
        try {
            actionListener = new MethodExpressionActionListener(createMethodExpression(valueExpression, valueType, expectedParamTypes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actionListener;
    }

    public void switchProfile(ActionEvent event) {
        try {
            SegCabEmpresa empresaSession = (SegCabEmpresa) BaseBean.getSessionAttribute("empresa");
            LoginMB loginMB = (LoginMB) BaseBean.getSessionAttribute("loginMB");
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
            PermisoDao permisoDao = (PermisoDao) ServiceFinder.findBean("PermisoDao");

            UIMenuItem selectedMenuItem = (UIMenuItem) event.getComponent();
            String id = selectedMenuItem.getId().substring(1);

            SegDetPerfilId perfilId = new SegDetPerfilId();
            perfilId.setNCodPerfil(BigDecimal.valueOf(Long.parseLong(id)));
            perfilId.setNCodEmpresa(empresaSession.getNCodEmpresa());
            SegDetPerfil perfil = new SegDetPerfil();
            perfil.setId(perfilId);
            perfil = perfilDao.obtenerPerfilById(perfil);

            List listaObjetos = permisoDao.obtenerObjetosAsignadosByPerfil(perfil);
            HashMap mapa = new HashMap();

            if (listaObjetos != null && !listaObjetos.isEmpty()) {
                for (Object listaObjeto : listaObjetos) {
                    SegDetObjeto obj = (SegDetObjeto) listaObjeto;
                    if (obj.getNFlgActivo().compareTo(BigDecimal.ONE) == 0) {
                        mapa.put(obj.getVNombre(), true);
                    } else if (obj.getNFlgActivo().compareTo(BigDecimal.ZERO) == 0) {
                        mapa.put(obj.getVNombre(), false);
                    }
                }
                loginMB.setPerfilSession(perfil);
                loginMB.setMapObjetos(mapa);

                BaseBean.getSession().setAttribute("loginMB", loginMB);
                BaseBean.getSession().setAttribute("mapaObjetos", mapa);
                BaseBean.getSession().setAttribute("menuMB", new MenuMB());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static String putStyle(int x, int y) {
        String style = null;
        try {
            switch (x) {
                case 44:
                    switch (y) {
                        case 53:
                            style = "width: 8px;height: 8px;background: orange;";
                            break;
                        case 52:
                            style = "width: 8px;height: 8px;background: orange;";
                            break;
                        case 51:
                            style = "width: 8px;height: 8px;background: red;";
                            break;
                        case 50:
                            style = "width: 8px;height: 8px;background: red;";
                            break;
                        case 49:
                            style = "width: 8px;height: 8px;background: red;";
                            break;
                    }
                    ;
                    break;
                case 45:
                    switch (y) {
                        case 53:
                            style = "width: 8px;height: 8px;background: yellow;";
                            break;
                        case 52:
                            style = "width: 8px;height: 8px;background: orange;";
                            break;
                        case 51:
                            style = "width: 8px;height: 8px;background: orange;";
                            break;
                        case 50:
                            style = "width: 8px;height: 8px;background: red;";
                            break;
                        case 49:
                            style = "width: 8px;height: 8px;background: red;";
                            break;
                    }
                    ;
                    break;
                case 46:
                    switch (y) {
                        case 53:
                            style = "width: 8px;height: 8px;background: green;";
                            break;
                        case 52:
                            style = "width: 8px;height: 8px;background: yellow;";
                            break;
                        case 51:
                            style = "width: 8px;height: 8px;background: orange;";
                            break;
                        case 50:
                            style = "width: 8px;height: 8px;background: red;";
                            break;
                        case 49:
                            style = "width: 8px;height: 8px;background: red;";
                            break;
                    }
                    ;
                    break;
                case 47:
                    switch (y) {
                        case 53:
                            style = "width: 8px;height: 8px;background: green;";
                            break;
                        case 52:
                            style = "width: 8px;height: 8px;background: green;";
                            break;
                        case 51:
                            style = "width: 8px;height: 8px;background: yellow;";
                            break;
                        case 50:
                            style = "width: 8px;height: 8px;background: orange;";
                            break;
                        case 49:
                            style = "width: 8px;height: 8px;background: red;";
                            break;
                    }
                    ;
                    break;
                case 48:
                    switch (y) {
                        case 53:
                            style = "width: 8px;height: 8px;background: green;";
                            break;
                        case 52:
                            style = "width: 8px;height: 8px;background: green;";
                            break;
                        case 51:
                            style = "width: 8px;height: 8px;background: yellow;";
                            break;
                        case 50:
                            style = "width: 8px;height: 8px;background: orange;";
                            break;
                        case 49:
                            style = "width: 8px;height: 8px;background: orange;";
                            break;
                    }
                    ;
                    break;
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return style;
    }
}
