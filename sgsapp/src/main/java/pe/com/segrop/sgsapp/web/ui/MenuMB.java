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
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.servlet.http.HttpSession;
import org.primefaces.component.menuitem.UIMenuItem;
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
import pe.com.segrop.sgsapp.util.JSFUtils;
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
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void toGestionUsuario(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("usuarioMB", new UsuarioMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionEmpresa(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("empresaMB", new EmpresaMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionPerfil(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("perfilMB", new PerfilMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionObjeto(ActionEvent event) {
        try {
            this.cleanSession();
            //BaseBean.getSession().setAttribute("objetoMB", new ObjetoMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionDocumento(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("documentoMB", new DocumentoMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toRestauracionDocumento(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("documentoMB", new DocumentoMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toAnulacionDocumento(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("documentoMB", new DocumentoMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionCargo(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("cargoMB", new CargoMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionLocal(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("localMB", new LocalMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionArea(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("areaMB", new AreaMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionLugar(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("lugarMB", new LugarMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionResponsable(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("responsableMB", new ResponsableMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionNovedad(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("novedadMB", new NovedadMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionInspeccionPresencial(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("presencialMB", new PresencialMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionInspeccionTelefonica(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("telefonicaMB", new TelefonicaMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionAsignarPerfil(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("usuarioPerfilMB", new UsuarioPerfilMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionAsignarPermiso(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("permisoMB", new PermisoMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toEvaluacionRiesgo(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("riesgoMB", new RiesgoMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
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
            SegCabEmpresa empresaSession = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            JSFUtils.getSession().setAttribute("matrizMB", new MatrizMB());
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
            JSFUtils.getSession().setAttribute("listaRiesgo", lista);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toGestionCapacitacion(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("capacitacionMB", new CapacitacionMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toReportes(ActionEvent event) {
        try {
            this.cleanSession();
            JSFUtils.getSession().setAttribute("reporteMB", new ReporteMB());
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
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
            JSFUtils.getSession().setAttribute("exportMB", exportMB);
            JSFUtils.getSession().setAttribute("listasSessionMB", new ListasSessionMB());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void cleanSession() {
        try {
            HttpSession session = JSFUtils.getSession();
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
            JSFUtils.getSession().invalidate();
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
            SegCabEmpresa empresaSession = (SegCabEmpresa) JSFUtils.getSessionAttribute("empresa");
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
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

                JSFUtils.getSession().setAttribute("loginMB", loginMB);
                JSFUtils.getSession().setAttribute("mapaObjetos", mapa);
                JSFUtils.getSession().setAttribute("menuMB", new MenuMB());
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
