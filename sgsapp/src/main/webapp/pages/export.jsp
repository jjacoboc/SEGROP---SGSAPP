<?xml version="1.0" encoding="UTF-8"?>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<%@include file="../commons/include.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="pe.com.segrop.sgs.web.ui.ExportMB"%>
<%@page import="pe.com.segrop.sgs.web.common.Data"%>
<%
    ExportMB exportMB = (ExportMB) session.getAttribute("exportMB");
    List<String> listaColumns = (List<String>) exportMB.getColumns();
    ArrayList<Data> listaModel = (ArrayList<Data>) exportMB.getModel();
%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Configuración de Exportación</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css"/>
        <link type="text/css" rel="stylesheet" href="../css/demo.css"/>
        <style type="text/css">
        /* NOTE: width and height for div#wn also specified in scrollbar_h.css */
        div#wn	{ 
            position:relative; 
            overflow:auto; /* for non-javascript */
        }
        /* NOTE: styles for code are in external css file named scrollbar_hv.css.
        Script segment below writes link tag. */
        </style>
        <script type="text/javascript" src="../js/dw_scroll_c.js"></script>
        <script type="text/javascript" src="../js/general.js"></script>
        <script type="text/javascript">
            function setStyle(){
                obj = document.getElementById("wn");
                obj.style.width = ( window.innerWidth - 68 ) + 'px';
                obj.style.maxHeight = '160px';
            }
            
            function init_dw_Scroll() {
                // Initialize scroll area
                // arguments: id of scroll area div, id of content div
                var wndo = new dw_scrollObj('wn', 'lyr');

                // Initialize scrollbar and up/down or left/right controls
                // args: id, axis ('v' or 'h'), event type for arrows, 
                // include track and dragBar? true or false
                wndo.buildScrollControls('scrollbar_v', 'v', 'mouseover', true);
                wndo.buildScrollControls('scrollbar_h', 'h', 'mouseover', true);
            }
            
            // if code supported, link in the style sheet (optional) and call the init function onload
            if ( dw_scrollObj.isSupported() ) {
                //dw_Util.writeStyleSheet('css/scrollbars_hv.css');
                dw_Event.add( window, 'load', init_dw_Scroll);
            }
        </script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="exportForm" >
                <rich:panel id="pnlExport" header="CONFIGURACIÓN DE EXPORTACIÓN" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Configuración de la Exportación</legend>
                        <table width="100%">
                            <tr>
                                <td align="center">
                                    <fieldset style="width: 220px">
                                        <legend style="font-size: 11px;font-weight: bold;">1. Selección de Entidad</legend>
                                        <rich:scrollableDataTable id="tblEntidad" var="item" value="#{exportMB.listaEntidades}" 
                                            selectionMode="single" rowKeyVar="rowKey" width="217px" height="150px" >
                                            <rich:column width="196px">
                                                <f:facet name="header">
                                                    <h:outputText value="ENTIDAD" />
                                                </f:facet> 
                                                <rich:spacer width="5"/><h:outputText value="#{item.VDescripcion}"></h:outputText>
                                            </rich:column>
                                            <a4j:support actionListener="#{exportMB.obtenerAtributosByEntidad}" event="onRowClick" reRender="tblAtributo, ordListAtributo, pnlCriteria">
                                                <f:param name="rowKey" value="#{rowKey}"/>
                                            </a4j:support>
                                        </rich:scrollableDataTable >
                                    </fieldset>
                                </td>
                                <td align="center">
                                    <fieldset style="width: 220px">
                                        <legend style="font-size: 11px;font-weight: bold;">2. Selección de Atributos</legend>
                                        <rich:scrollableDataTable id="tblAtributo" var="item" value="#{exportMB.listaAtributos}" 
                                            selectionMode="single" width="217px" height="150px" rowKeyVar="rowKey">
                                            <rich:column width="196px">
                                                <f:facet name="header">
                                                    <h:outputText value="ATRIBUTO" />
                                                </f:facet> 
                                                <rich:spacer width="5"/><h:outputText value="#{item.VDescripcion}"></h:outputText>
                                            </rich:column>
                                            <a4j:support actionListener="#{exportMB.handleAttributeSelection}" event="onRowDblClick" reRender="tblAtributo, ordListAtributo, grid, btnPreview">
                                                <f:param name="rowKey" value="#{rowKey}"/>
                                            </a4j:support>
                                        </rich:scrollableDataTable>
                                    </fieldset>
                                </td>
                                <td align="center">
                                    <fieldset style="width: 280px">
                                        <legend style="font-size: 11px;font-weight: bold;">3. Ordenamiento de Atributos</legend>
                                        <h:panelGrid columns="3" cellspacing="0" cellpadding="0">
                                            <rich:scrollableDataTable id="ordListAtributo" var="item" value="#{exportMB.selectedAtributoList}" 
                                                selectionMode="single" width="233px" height="150px" rowKeyVar="rowKey">
                                                <rich:column style="text-align: center;" width="15px">
                                                    <f:facet name="header">
                                                        <h:outputText value="#" />
                                                    </f:facet> 
                                                    <h:outputText value="#{rowKey + 1}"/>
                                                </rich:column>
                                                <rich:column width="196px">
                                                    <f:facet name="header">
                                                        <h:outputText value="ATRIBUTO" />
                                                    </f:facet> 
                                                    <rich:spacer width="5"/><h:outputText value="#{item.VDescripcion}"></h:outputText>
                                                </rich:column>
                                                <a4j:support actionListener="#{exportMB.handleOrderedAttributeSelection}" event="onRowDblClick" reRender="tblAtributo, ordListAtributo, grid, btnPreview">
                                                    <f:param name="rowKey" value="#{rowKey}"/>
                                                </a4j:support>
                                                <a4j:support event="onRowClick">
                                                    <f:setPropertyActionListener value="#{item}" target="#{exportMB.selectedAtributo}"/>
                                                </a4j:support>
                                            </rich:scrollableDataTable>
                                            <rich:spacer width="10"/>
                                            <h:panelGrid id="grid" columns="1" style="text-align: center;">
                                                <a4j:commandLink actionListener="#{exportMB.first}" reRender="ordListAtributo" disabled="#{empty exportMB.selectedAtributoList}">
                                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/first_.png" width="24px" height="24px" alt="primero" title="primero"/>
                                                </a4j:commandLink>
                                                <a4j:commandLink actionListener="#{exportMB.up}" reRender="ordListAtributo" disabled="#{empty exportMB.selectedAtributoList}">
                                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/up_.png" width="24px" height="24px" alt="arriba" title="arriba"/>
                                                </a4j:commandLink>
                                                <a4j:commandLink actionListener="#{exportMB.down}" reRender="ordListAtributo" disabled="#{empty exportMB.selectedAtributoList}">
                                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/down_.png" width="24px" height="24px" alt="abajo" title="abajo"/>
                                                </a4j:commandLink>
                                                <a4j:commandLink actionListener="#{exportMB.last}" reRender="ordListAtributo" disabled="#{empty exportMB.selectedAtributoList}">
                                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/last_.png" width="24px" height="24px" alt="último" title="último"/>
                                                </a4j:commandLink>
                                            </h:panelGrid>
                                        </h:panelGrid>
                                    </fieldset>
                                </td>
                                <td style="vertical-align: bottom">
                                    <a4j:commandButton process="@form" id="btnPreview" value="Vista Previa" actionListener="#{exportMB.preview}" oncomplete="setStyle();" style="font-size: 11px;height: 2.5em;" reRender="pnlPreview" disabled="#{empty exportMB.selectedAtributoList}"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <a4j:outputPanel id="pnlCriteria">
                                        <table width="100%">
                                            <a4j:repeat id="repeatCriteria" value="#{exportMB.listaCriteria}" var="criteria" rowKeyVar="row" rendered="#{not empty exportMB.listaAtributos}">
                                                <tr>
                                                    <td>
                                                    <h:outputText value="#{row + 1}." style="font-size: 11px;font-weight: bold;"/>
                                                    <rich:spacer width="10px"/>
                                                    <h:selectOneMenu value="#{criteria.column}" style="font-size: 11px" >
                                                        <f:selectItems value="#{exportMB.listSelectItems}"/>
                                                        <a4j:support event="onchange" actionListener="#{exportMB.handleCriteriaSelected}" reRender="pnlCriteria" >
                                                            <f:param name="rowKey" value="#{row}"/>
                                                        </a4j:support>
                                                    </h:selectOneMenu>
                                                    <rich:spacer width="10px"/>
                                                    <h:selectOneMenu value="#{criteria.condition}" style="font-size: 11px">
                                                        <f:selectItems value="#{criteria.listCondition}"/>
                                                        <a4j:support event="onchange" actionListener="#{exportMB.handleConditionSelected}">
                                                            <f:param name="rowKey" value="#{row}"/>
                                                        </a4j:support>
                                                    </h:selectOneMenu>
                                                    <rich:spacer width="10px"/>
                                                    <h:inputText value="#{criteria.value}" size="50" maxlength="100" style="font-size: 11px;" rendered="#{criteria.flagInput}"/>
                                                    <h:selectOneMenu value="#{criteria.value}" style="font-size: 11px" rendered="#{criteria.flagSelectOneMenu}">
                                                        <f:selectItem itemValue="1" itemLabel="SI"/>
                                                        <f:selectItem itemValue="0" itemLabel="NO"/>
                                                    </h:selectOneMenu>
                                                    <rich:calendar value="#{criteria.value}" popup="true" datePattern="dd/M/yyyy hh:mm" showApplyButton="true" jointPoint="top-left" 
                                                                   direction="bottom-right" locale="es" inputSize="20" style="font-size: 11px" rendered="#{criteria.flagDateHour}"/>
                                                    <rich:spacer width="10px"/>
                                                    <a4j:commandLink actionListener="#{exportMB.handleAddCriteria}" reRender="pnlCriteria" rendered="#{exportMB.size == row + 1}">
                                                        <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" alt="agregar filtro" title="agregar filtro"/>
                                                        <f:param name="rowKey" value="#{row}"/>
                                                    </a4j:commandLink>
                                                    <a4j:commandLink actionListener="#{exportMB.handleDeleteCriteria}" reRender="pnlCriteria" rendered="#{exportMB.size != row + 1}">
                                                        <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/delete.png" alt="eliminar filtro" title="eliminar filtro"/>
                                                        <f:param name="rowKey" value="#{row}"/>
                                                    </a4j:commandLink>
                                                    </td>
                                                </tr>
                                            </a4j:repeat>
                                        </table>
                                    </a4j:outputPanel>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;" onload="setStyle(this)">Vista Previa</legend>
                        <a4j:outputPanel id="pnlPreview">
                            <%if(listaColumns != null && !listaColumns.isEmpty()){%>
                                <table cellpadding="0" cellspacing="0" width="100%">
                                    <tr>
                                        <td>
                                            <div id="wn">
                                                <div id="lyr">
                                                <table cellpadding="0" cellspacing="0" width="100%">
                                                    <caption style="font-size: 12px;"><b>LISTADO DE <h:outputText value="#{exportMB.selectedEntidad.VDescripcion}" /></b></caption>
                                                    <thead class="dr-table-thead">
                                                        <tr class="dr-table-header rich-table-header">
                                                        <%for(int i=0;i<listaColumns.size();i++){%>
                                                        <%  String c = (String) listaColumns.get(i);%>
                                                            <th class="dr-table-headercell rich-table-headercell"><%=c%></th>
                                                        <%}%>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%if(listaModel != null && !listaModel.isEmpty()){%>
                                                            <%for(int k=0;k<listaModel.size();k++){%>
                                                            <%  Data m = (Data) listaModel.get(k);%>
                                                            <%  String[] v = (String[]) m.getValues();%>
                                                                <tr>
                                                                <%for(int i=0;i<listaColumns.size();i++){%>
                                                                <%  String c = (String) listaColumns.get(i);%>
                                                                    <td class="dr-subtable-cell rich-subtable-cell"><%=v[i]%></td>
                                                                <%}%>
                                                                </tr>
                                                            <%}%>
                                                        <%}else{%>
                                                        <tr><td colspan="<%=listaColumns.size()%>" class="dr-subtable-cell rich-subtable-cell"><h:outputText value="Ningún registro para mostrar." /></td></tr>
                                                        <%}%>
                                                    </tbody>
                                                </table>
                                                </div>
                                            </div>
                                        </td>
                                        <td><div id="scrollbar_v"></div></td>
                                    </tr>
                                    <tr>
                                        <td><div id="scrollbar_h"></div></td><td></td>
                                    </tr>
                                </table>
                                <h:panelGrid columns="1">
                                    <h:panelGroup>
                                        <a4j:commandLink id="btnSaveConfig" reRender="dlgMsgs, nombre, descripcion" disabled="#{empty exportMB.selectedAtributoList}">
                                            <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/disk.png" width="24px" height="24px" alt="guardar configuración" title="guardar configuración"/>
                                            <rich:componentControl for="dlg" attachTo="btnSaveConfig" operation="show" event="onclick"/>
                                        </a4j:commandLink>
                                        <rich:spacer width="10"/>
                                        <a4j:commandLink id="btnExport" actionListener="#{exportMB.exportDataToExcel}" disabled="#{empty exportMB.selectedAtributoList}">
                                            <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/export-excel.png" width="24px" height="24px" alt="exportar configuración" title="exportar configuración"/>
                                        </a4j:commandLink>
                                    </h:panelGroup>
                                </h:panelGrid>
                                <rich:dataTable></rich:dataTable>
                            <%}else{%>
                                <h:outputText value="Ningún atributo seleccionado." />
                            <%}%>
                        </a4j:outputPanel>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Repositorio de Configuraciones</legend>
                        <rich:dataTable id="tblConfig" var="row" value="#{exportMB.listaExport}" rowKeyVar="rowKey" rendered="#{not empty exportMB.listaExport}" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tblConfig" maxPages="5"/>
                            </f:facet>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ITEM" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{rowKey + 1}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="NOMBRE" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VNombre}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="DESCRIPCIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column title="" style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="elink" actionListener="#{exportMB.chargeConfiguration}" reRender="tblEntidad, tblAtributo, ordListAtributo, grid, btnPreview, pnlCriteria, tblConfig, pnlPreview">
                                    <f:param name="rowKey" value="#{rowKey}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/arrow-refresh.png" width="16px" height="16px" alt="cargar configuración" title="cargar configuración"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="dlink">
                                    <f:setPropertyActionListener value="#{row}" target="#{exportMB.selectedExport}"/>
                                    <rich:componentControl for="delDlg" attachTo="dlink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar configuración" title="eliminar configuración"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                        <h:outputText value="Ninguna configuración registrada." rendered="#{empty exportMB.listaExport}"/>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="false" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="GUARDAR CONFIGURACIÓN"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgForm">
                    <rich:messages id="dlgMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
                        <f:facet name="errorMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_critical.png"/>
                        </f:facet>
                        <f:facet name="infoMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_info.png"/>
                        </f:facet>
                        <f:facet name="warnMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_warning.png"/>
                        </f:facet>
                    </rich:messages>
                    <h:panelGrid columns="2" style="margin-bottom:10px">
                        <h:outputLabel for="nombre" value="Nombre: " styleClass="Etiqueta1"/>
                        <h:inputText id="nombre" value="#{exportMB.nombre}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel for="descripcion" value="Descripción: " styleClass="Etiqueta1"/>
                        <h:inputTextarea id="descripcion" value="#{exportMB.descripcion}" rows="3" cols="75" style="float: left;font-size: 11px;text-transform: uppercase;" />
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton id="btnGrabar" value="Grabar" onclick="confirmDlg.show();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmDlg" attachTo="btnGrabar" operation="show" event="onclick"/>
                        </a4j:commandButton>
                        <a4j:commandButton id="btnCancelar" value="Cancelar" onclick="dlg.hide();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="dlg" attachTo="btnCancelar" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                    </h:panelGroup>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDlgForm">
                    <h:outputText value="Se procederá a registrar la configuración." style="font-size: 11px;"/><br/>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br/>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="dlgMsgs, tblConfig" actionListener="#{exportMB.saveConfiguration}" oncomplete="#{exportMB.action}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="delDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Eliminación de la Configuración"></h:outputText>
                </f:facet>
                <h:form id="mpdForm">
                    <h:outputText value="Se procederá a eliminar la configuración."/><br/>
                    <h:outputText value="Desea Continuar?"/><br/>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tblConfig" actionListener="#{exportMB.deleteConfiguration}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="delDlg" attachTo="dbtnSI" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="dbtnNO" value="NO" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="delDlg" attachTo="dbtnNO" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>