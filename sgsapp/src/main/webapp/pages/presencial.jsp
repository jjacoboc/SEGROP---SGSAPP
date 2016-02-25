<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Gestión de Inspecciones Presenciales</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
        <script type="text/javascript" src="../js/locales.js"></script>
        <script type="text/javascript">
            window.onload = function WindowLoad(event) {
                var value = document.getElementById('hiddenForm:action').value;
                if(value !== ''){
                    document.getElementById('hiddenForm:invisibleButton').click();
                    document.getElementById('hiddenForm:action').value = '';
                }
            };
            function backToMatrix(){
                document.getElementById('hiddenForm:hiddenBtn').click();
            }
        </script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="presencialForm">
                <rich:panel header="GESTIÓN DE INSPECCIONES PRESENCIALES" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid id="pnlSearch" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Tipo de Inspección: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{presencialMB.searchTipoInspeccion}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaTipoInspeccion}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Novedad: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{presencialMB.searchNovedad}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaTipoEvento}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Local: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchLocal" value="#{presencialMB.searchLocal}" valueChangeListener="#{listasSessionMB.obtenerListaAreaByLocal}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaLocalActivoByEmpresa}"/>
                                <a4j:support event="onchange" reRender="searchArea" ajaxSingle="true"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Area: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchArea" value="#{presencialMB.searchArea}" valueChangeListener="#{listasSessionMB.obtenerListaLugarByArea}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaAreaActivaByLocal}"/>
                                <a4j:support event="onchange" reRender="searchLugar, searchResponsable" ajaxSingle="true"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchLugar" value="#{presencialMB.searchLugar}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaLugarActivoByArea}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Responsable: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchResponsable" value="#{presencialMB.searchResponsable}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaResponsableActivoByArea}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Cargo: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchCargo" value="#{presencialMB.searchCargo}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaCargoActivoByEmpresa}"/>
                            </h:selectOneMenu>
                        </h:panelGrid>
                    </fieldset>
                    
                    <h:panelGrid columns="1">
                        <h:panelGroup>
                            <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{presencialMB.buscarInspeccionPresencial}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                            <a4j:commandButton id="btnNuevo" value="Nuevo" actionListener="#{presencialMB.toRegistrar}" reRender="dlgMsgs, pnlNew, outputpanel1, outputpanel2" style="font-size: 11px;height: 2em">
                                <rich:componentControl for="dlg" attachTo="btnNuevo" operation="show" event="onclick"/>
                            </a4j:commandButton>
                        </h:panelGroup>
                    </h:panelGrid>
                    
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Inspecciones</legend>
                        <rich:dataTable id="tbl" var="row" value="#{presencialMB.listaInsPresencial}" 
                                        rows="10" rowKeyVar="rowkey" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="TIPO DE INSPECCIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NTipoInspeccion}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterTipoInspeccion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="DESCRIPCIÓN BREVE" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescBreve}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="LOCAL" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NLocal}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterLocal"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="RESPONSABLE" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NResponsable}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterResponsable"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="CARGO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NCargo}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterCargo"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="FECHA Y HORA" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.DFecHora}" style="font-size: 11px;font-weight: normal;">
                                    <f:convertDateTime type="date" pattern="dd/MM/yyyy h:mm a"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ESTADO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NEstado}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterEstadoInspeccion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;width: 20;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="vlink" actionListener="#{presencialMB.toVer}" oncomplete="#{rich:component('verDlg')}.show()" reRender="verDlg, verPanel, vrepeat, txtEval, vevalPanel">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/magnifier.png" alt="ver inspección" title="ver inspección"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="elink" actionListener="#{presencialMB.toEdit}" oncomplete="#{rich:component('editDlg')}.show()" reRender="editDlgMsgs, pnlEdit, panelgrid1, panelgrid2">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/pencil.png" alt="editar inspección" title="editar inspección"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="slink" actionListener="#{presencialMB.toSeguimiento}" oncomplete="#{rich:component('segDlg')}.show()" reRender="segPanel, repeat, pnlAccionTomada" rendered="#{row.NSeguimiento != 0}">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/date_go.png" alt="seguimiento de inspección" title="seguimiento de inspección"/>
                                </a4j:commandLink>
                                <h:graphicImage style="border: 0;" url="/pages/images/date_go_disabled.png" alt="seguimiento de inspección cerrado" title="seguimiento de inspección cerrado" rendered="#{row.NSeguimiento == 0}"/>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="alink" actionListener="#{presencialMB.toEvaluacion}" oncomplete="#{rich:component('evalDlg')}.show()" reRender="novPanel, evalPanel" rendered="#{row.NAnalisis != 0}">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/table_edit.png" alt="evaluar inspección" title="evaluar inspección"/>
                                </a4j:commandLink>
                                <h:graphicImage style="border: 0;" url="/pages/images/table_edit_disabled.png" alt="evaluación de inspección cerrada" title="evaluación de inspección cerrada" rendered="#{row.NAnalisis == 0}"/>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="true" width="520" height="513">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="NUEVA INSPECCIÓN"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="newhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlg" attachTo="newhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <div style="overflow-y: auto;height:473px;">
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
                    <h:panelGrid id="pnlNew" columns="2" columnClasses="columnLbl, columnTxt">
                        <h:outputText value="Se Controló: " styleClass="Etiqueta1" />
                        <h:panelGroup>
                            <h:outputLabel for="chkpersona" value="Personas" styleClass="Etiqueta1" />
                            <h:selectBooleanCheckbox id="chkpersona" value="#{presencialMB.persona}" style="font-size: 11px"/>
                            <rich:spacer width="20"/>
                            <h:outputLabel for="chkactivo" value="Activos" styleClass="Etiqueta1" />
                            <h:selectBooleanCheckbox id="chkactivo" value="#{presencialMB.activo}" style="font-size: 11px"/>
                            <rich:spacer width="20"/>
                            <h:outputLabel for="chkproceso" value="Procesos" styleClass="Etiqueta1" />
                            <h:selectBooleanCheckbox id="chkproceso" value="#{presencialMB.proceso}" style="font-size: 11px"/>
                        </h:panelGroup>
                        
                        <h:outputText value="Tipo de Inspección: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{presencialMB.tipoInspeccion}" valueChangeListener="#{presencialMB.handleSelectTipoInspeccion}" style="float: left;font-size: 11px"> 
                            <f:selectItems value="#{listasSessionMB.listaTipoInspeccion}"/>
                            <a4j:support event="onchange" reRender="novedad, groupLocal, groupArea, groupLugar, groupResponsable, groupCargo" ajaxSingle="true"/>
                        </h:selectOneMenu>
                        
                        <h:outputText value="Novedad: " styleClass="Etiqueta1" />
                        <h:selectOneMenu id="novedad" value="#{presencialMB.novedad}" valueChangeListener="#{presencialMB.handleSelectNovedad}" disabled="#{presencialMB.disabledNovedad}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaNovedad}"/>
                            <a4j:support event="onchange" reRender="groupLocal, groupArea, groupLugar, groupResponsable, groupCargo" ajaxSingle="true"/>
                        </h:selectOneMenu>
                        
                        <h:outputLabel value="Fecha y Hora:" styleClass="Etiqueta1"/>
                        <rich:calendar value="#{presencialMB.fechaHora}" popup="true" datePattern="dd/M/yyyy hh:mm a" showApplyButton="true" 
                                jointPoint="top-left" direction="bottom-right" locale="es" inputSize="20"  style="float: left;font-size: 11px"/>
                        
                        <h:outputLabel value="Descripción Breve:" styleClass="Etiqueta1"/>
                        <h:inputText value="#{presencialMB.descBreve}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Descripción:" styleClass="Etiqueta1"/>
                        <h:inputTextarea value="#{presencialMB.descripcion}" cols="50" rows="3" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                        <h:outputText value="Local: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupLocal">
                            <h:selectOneMenu id="local" value="#{presencialMB.local}" valueChangeListener="#{listasSessionMB.obtenerListaAreaByLocal}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaLocalActivoByEmpresa}"/>
                                <a4j:support event="onchange" reRender="groupArea" actionListener="#{presencialMB.handleChangeLocalValue}" ajaxSingle="true"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarLocales}" reRender="pnlLocal" oncomplete="#{rich:component('dlgLocal')}.show()">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir local"/>
                            </a4j:commandLink>
                        </h:panelGroup>

                        <h:outputText value="Area: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupArea">
                            <h:selectOneMenu id="area" value="#{presencialMB.area}" valueChangeListener="#{listasSessionMB.obtenerListaLugarByArea}" 
                                             style="float: left;font-size: 11px" disabled="#{presencialMB.disabledArea}">
                                <f:selectItems value="#{listasSessionMB.listaAreaActivaByLocal}"/>
                                <a4j:support event="onchange" reRender="groupLugar, groupResponsable" actionListener="#{presencialMB.handleChangeAreaValue}" ajaxSingle="true"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarAreas}" reRender="pnlArea" oncomplete="#{rich:component('dlgArea')}.show()" rendered="#{presencialMB.renderedArea}">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir area" />
                            </a4j:commandLink>
                            <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir area" rendered="#{!presencialMB.renderedArea}"/>
                        </h:panelGroup>

                        <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupLugar">
                            <h:selectOneMenu id="lugar" value="#{presencialMB.lugar}" style="float: left;font-size: 11px" disabled="#{presencialMB.disabledLugar}">
                                <f:selectItems value="#{listasSessionMB.listaLugarActivoByArea}"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarLugares}" reRender="pnlLugar" oncomplete="#{rich:component('dlgLugar')}.show()" rendered="#{presencialMB.renderedLugar}">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir lugar" alt="añadir lugar"/>
                            </a4j:commandLink>
                            <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir lugar" alt="añadir lugar" rendered="#{!presencialMB.renderedLugar}"/>
                        </h:panelGroup>

                        <h:outputText value="Responsable: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupResponsable">
                            <h:selectOneMenu id="responsable" value="#{presencialMB.responsable}" style="float: left;font-size: 11px" disabled="#{presencialMB.disabledResponsable}">
                                <f:selectItems value="#{listasSessionMB.listaResponsableActivoByArea}"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarResponsables}" reRender="pnlResponsable" oncomplete="#{rich:component('dlgResponsable')}.show()" rendered="#{presencialMB.renderedResponsable}">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir responsable" alt="añadir responsable"/>
                            </a4j:commandLink>
                            <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir responsable" alt="añadir responsable" rendered="#{!presencialMB.renderedResponsable}"/>
                        </h:panelGroup>

                        <h:outputText value="Cargo: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupCargo">
                            <h:selectOneMenu id="cargo" value="#{presencialMB.cargo}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaCargoActivoByEmpresa}"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarCargos}" reRender="pnlCargo" oncomplete="#{rich:component('dlgCargo')}.show()">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir cargo"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                    
                        <h:outputLabel value="Acción Tomada:" styleClass="Etiqueta1"/>
                        <h:inputTextarea value="#{presencialMB.accionTomada}" cols="50" rows="3" style="float: left;font-size: 11px;text-transform: uppercase;"/>
                    </h:panelGrid>
                    <h:panelGrid id="outputpanel1" columns="2" columnClasses="columnLbl, columnTxt">
                        <h:outputLabel value="Cumple Controles:" styleClass="Etiqueta1"/>
                        <h:selectOneRadio value="#{presencialMB.cumpleControles}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneRadio>
                        
                        <h:outputLabel value="Necesita Control Adicional:" style="width: 150px" styleClass="Etiqueta1"/>
                        <h:selectOneRadio value="#{presencialMB.controlAdicional}" valueChangeListener="#{presencialMB.handleSelectControlAdicional}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                            <a4j:support event="onchange" reRender="outputpanel2" ajaxSingle="true"/>
                        </h:selectOneRadio>
                    </h:panelGrid>
                    <h:panelGrid id="outputpanel2" columns="2" columnClasses="columnLbl, columnTxt" style="margin-bottom:10px">
                        <h:outputLabel value="Controles Adicionales:" styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                        <h:inputTextarea value="#{presencialMB.controlesAdicionales}" cols="50" rows="3" style="float: left;font-size: 11px;text-transform: uppercase;" rendered="#{presencialMB.visualizar}"/>
                        
                        <h:outputLabel value="Análisis de Riesgo:" styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                        <h:selectOneRadio value="#{presencialMB.analisis}" style="float: left;font-size: 11px" rendered="#{presencialMB.visualizar}"> 
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneRadio>

                        <h:outputLabel value="Necesita Seguimiento:" styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                        <h:selectOneRadio value="#{presencialMB.seguimiento}" style="float: left;font-size: 11px" rendered="#{presencialMB.visualizar}"> 
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneRadio>
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton id="submitButton" value="Grabar" onclick="#{rich:component('confirmDlg')}.show()" style="font-size: 11px;height: 2em" />
                        <a4j:commandButton id="cancelButton" value="Cancelar" onclick="#{rich:component('dlg')}.hide()" style="font-size: 11px;height: 2em"/>
                    </h:panelGroup>
                </h:form>
                </div>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDlgForm">
                    <h:outputText value="Se procederá a registrar la inspección." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="dlgMsgs" actionListener="#{presencialMB.registrarInspeccionPresencial}" oncomplete="#{presencialMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="editDlg" resizeable="false" moveable="true" width="520" height="513">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="ACTUALIZAR INSPECCIÓN"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="edithidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="editDlg" attachTo="edithidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <div style="overflow-y: auto;height:473px;">
                <h:form id="editDlgForm">
                    <rich:messages id="editDlgMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <h:panelGrid id="pnlEdit" columns="2" columnClasses="columnLbl, columnTxt">
                        <h:outputText value="Se Controló: " styleClass="Etiqueta1" />
                        <h:panelGroup>
                            <h:outputLabel for="chkpersona" value="Personas" styleClass="Etiqueta1" />
                            <h:selectBooleanCheckbox id="chkpersona" value="#{presencialMB.selectedInsPresencial.BPersona}" style="font-size: 11px"/>
                            <rich:spacer width="20"/>
                            <h:outputLabel for="chkactivo" value="Activos" styleClass="Etiqueta1" />
                            <h:selectBooleanCheckbox id="chkactivo" value="#{presencialMB.selectedInsPresencial.BActivo}" style="font-size: 11px"/>
                            <rich:spacer width="20"/>
                            <h:outputLabel for="chkproceso" value="Procesos" styleClass="Etiqueta1" />
                            <h:selectBooleanCheckbox id="chkproceso" value="#{presencialMB.selectedInsPresencial.BProceso}" style="font-size: 11px"/>
                        </h:panelGroup>
                        
                        <h:outputText value="Tipo de Inspección: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{presencialMB.selectedInsPresencial.NTipoInspeccion}" valueChangeListener="#{presencialMB.handleSelectTipoInspeccion}" style="float: left;font-size: 11px"> 
                            <f:selectItems value="#{listasSessionMB.listaTipoInspeccion}"/>
                            <a4j:support event="onchange" reRender="editNovedad, groupEditSelLocal, groupEditSelArea, groupEditSelLugar, groupEditSelResponsable, groupEditSelCargo" ajaxSingle="true"/>
                        </h:selectOneMenu>
                        
                        <h:outputText value="Novedad: " styleClass="Etiqueta1" />
                        <h:selectOneMenu id="editNovedad" value="#{presencialMB.selectedInsPresencial.NCodNovedad}" valueChangeListener="#{presencialMB.handleSelectNovedad}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaNovedad}"/>
                            <a4j:support event="onchange" reRender="groupEditSelLocal, groupEditSelArea, groupEditSelLugar, groupEditSelResponsable, groupEditSelCargo" ajaxSingle="true"/>
                        </h:selectOneMenu>
                        
                        <h:outputLabel value="Fecha y Hora:" styleClass="Etiqueta1"/>
                        <rich:calendar value="#{presencialMB.selectedInsPresencial.DFecHora}" popup="true" datePattern="dd/M/yyyy hh:mm a" showApplyButton="true"
                                jointPoint="top-left" direction="bottom-right" locale="es" inputSize="20" style="float: left;font-size: 11px"/>
                        
                        <h:outputLabel value="Descripción Breve:" styleClass="Etiqueta1"/>
                        <h:inputText value="#{presencialMB.selectedInsPresencial.VDescBreve}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Descripción:" styleClass="Etiqueta1"/>
                        <h:inputTextarea value="#{presencialMB.selectedInsPresencial.VDescripcion}" cols="50" rows="3" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                        <h:outputText value="Local: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupEditSelLocal">
                            <h:selectOneMenu id="editSelLocal" value="#{presencialMB.selectedInsPresencial.NLocal}" valueChangeListener="#{listasSessionMB.obtenerListaAreaByLocal}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaLocalActivoByEmpresa}"/>
                                <a4j:support event="onchange" reRender="groupEditSelArea" actionListener="#{presencialMB.handleChangeLocalValue}" ajaxSingle="true"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarLocales}" reRender="pnlLocal" oncomplete="#{rich:component('dlgLocal')}.show()">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir local"/>
                            </a4j:commandLink>
                        </h:panelGroup>

                        <h:outputText value="Area: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupEditSelArea">
                            <h:selectOneMenu id="editSelArea" value="#{presencialMB.selectedInsPresencial.NArea}" valueChangeListener="#{listasSessionMB.obtenerListaLugarByArea}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaAreaActivaByLocal}"/>
                                <a4j:support event="onchange" reRender="groupEditSelLugar, groupEditSelResponsable" actionListener="#{presencialMB.handleChangeAreaValue}" ajaxSingle="true"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarAreas}" reRender="pnlArea" oncomplete="#{rich:component('dlgArea')}.show()" rendered="#{presencialMB.renderedArea}">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir area" />
                            </a4j:commandLink>
                            <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir area" rendered="#{!presencialMB.renderedArea}"/>
                        </h:panelGroup>

                        <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupEditSelLugar">
                            <h:selectOneMenu id="editSelLugar" value="#{presencialMB.selectedInsPresencial.NLugar}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaLugarActivoByArea}"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarLugares}" reRender="pnlLugar" oncomplete="#{rich:component('dlgLugar')}.show()" rendered="#{presencialMB.renderedLugar}">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir lugar" alt="añadir lugar"/>
                            </a4j:commandLink>
                            <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir lugar" alt="añadir lugar" rendered="#{!presencialMB.renderedLugar}"/>
                        </h:panelGroup>

                        <h:outputText value="Responsable: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupEditSelResponsable">
                            <h:selectOneMenu id="editSelResponsable" value="#{presencialMB.selectedInsPresencial.NResponsable}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaResponsableActivoByArea}"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarResponsables}" reRender="pnlResponsable" oncomplete="#{rich:component('dlgResponsable')}.show()" rendered="#{presencialMB.renderedResponsable}">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir responsable" alt="añadir responsable"/>
                            </a4j:commandLink>
                            <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir responsable" alt="añadir responsable" rendered="#{!presencialMB.renderedResponsable}"/>
                        </h:panelGroup>

                        <h:outputText value="Cargo: " styleClass="Etiqueta1" />
                        <h:panelGroup id="groupEditSelCargo">
                            <h:selectOneMenu id="editSelCargo" value="#{presencialMB.selectedInsPresencial.NCargo}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaCargoActivoByEmpresa}"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.listarCargos}" reRender="pnlCargo" oncomplete="#{rich:component('dlgCargo')}.show()">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir cargo"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        
                        <h:outputLabel value="Acción Tomada:" styleClass="Etiqueta1"/>
                        <h:inputTextarea value="#{presencialMB.selectedInsPresencial.VAccTomadas}" cols="50" rows="3" style="float: left;font-size: 11px;text-transform: uppercase;"/>
                    </h:panelGrid>
                    <h:panelGrid id="panelgrid1" columns="2" columnClasses="columnLbl, columnTxt">    
                        <h:outputLabel value="Cumple Controles:" styleClass="Etiqueta1"/>
                        <h:selectOneRadio value="#{presencialMB.selectedInsPresencial.NCumpleControl}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneRadio>

                        <h:outputLabel value="Necesita Control Adicional:" styleClass="Etiqueta1"/>
                        <h:selectOneRadio value="#{presencialMB.selectedInsPresencial.NControlAdicional}" valueChangeListener="#{presencialMB.handleSelectControlAdicional}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                            <a4j:support event="onchange" reRender="panelgrid2" ajaxSingle="true"/>
                        </h:selectOneRadio>
                    </h:panelGrid>
                    <h:panelGrid id="panelgrid2" columns="2" columnClasses="columnLbl, columnTxt" style="margin-bottom:10px">
                        <h:outputLabel value="Controles Adicionales:" styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                        <h:inputTextarea value="#{presencialMB.selectedInsPresencial.VControlAdicional}" cols="50" rows="3" style="float: left;font-size: 11px;text-transform: uppercase;" rendered="#{presencialMB.visualizar}"/>
                        
                        <h:outputLabel value="Análisis de Riesgo:" styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                        <h:selectOneRadio value="#{presencialMB.selectedInsPresencial.NAnalisis}" style="float: left;font-size: 11px" rendered="#{presencialMB.visualizar}"> 
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneRadio>
                        
                        <h:outputLabel value="Necesita Seguimiento:" styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                        <h:selectOneRadio value="#{presencialMB.selectedInsPresencial.NSeguimiento}" style="float: left;font-size: 11px" rendered="#{presencialMB.visualizar}"> 
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneRadio>
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton id="submitButton" value="Grabar" onclick="#{rich:component('confirmEditDlg')}.show()" style="font-size: 11px;height: 2em" />
                        <a4j:commandButton id="cancelButton" value="Cancelar" onclick="#{rich:component('editDlg')}.hide()" style="font-size: 11px;height: 2em"/>
                    </h:panelGroup>
                </h:form>
                </div>
            </rich:modalPanel>
            <rich:modalPanel id="confirmEditDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEditDlgForm">
                    <h:outputText value="Se procederá con la actualización de la inspección." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="editDlgMsgs" actionListener="#{presencialMB.editarInspeccionPresencial}" oncomplete="#{presencialMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="verDlg" resizeable="false" moveable="true" autosized="true" width="520" height="413">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="VER INSPECCIÓN"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="verhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="verDlg" attachTo="verhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <div style="overflow-y: auto;height:473px;">
                <h:form id="verDlgForm">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Inspección Presencial</legend>
                        <h:panelGrid id="verPanel" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <%--
                            <h:outputText value="Se Controló: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{presencialMB.selectedInsPresencial.NControlado}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaControlado}"/>
                            </h:selectOneMenu>
                            --%>

                            <h:outputText value="Tipo de Inspección: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NTipoInspeccion}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterTipoInspeccion"/>
                            </h:outputText>

                            <h:outputText value="Novedad: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NCodNovedad}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterNovedad"/>
                            </h:outputText>

                            <h:outputText value="Fecha y Hora:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.DFecHora}" style="float: left;font-size: 11px"> 
                                <f:convertDateTime type="date" pattern="dd/MM/yyyy h:mm a"/>
                            </h:outputText>

                            <h:outputText value="Descripción Breve:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VDescBreve}" style="float: left;font-size: 11px;"/>

                            <h:outputText value="Descripción:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VDescripcion}" style="float: left;font-size: 11px;"/>

                            <h:outputText value="Local: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NLocal}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterLocal"/>
                            </h:outputText>

                            <h:outputText value="Area: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NArea}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterArea"/>
                            </h:outputText>

                            <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NLugar}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterLugar"/>
                            </h:outputText>

                            <h:outputText value="Responsable: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NResponsable}" style="float: left;font-size: 11px">
                                <f:converter converterId="converterResponsable"/>
                            </h:outputText>

                            <h:outputText value="Cargo: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NCargo}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterCargo"/>
                            </h:outputText>

                            <h:outputText value="Acción Tomada:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VAccTomadas}" style="float: left;font-size: 11px;"/>

                            <h:outputText value="Cumple Controles: " styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NCumpleControl}" style="float: left;font-size: 11px">
                                <f:converter converterId="converterSiNo"/>
                            </h:outputText>

                            <h:outputText value="Necesita Control Adicional: " styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NControlAdicional}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterSiNo"/>
                            </h:outputText>

                            <h:outputText value="Controles Adicionales: " styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VControlAdicional}" style="float: left;font-size: 11px;" rendered="#{presencialMB.visualizar}"/>

                            <h:outputText value="Análisis de Riesgo: " styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NAnalisis}" style="float: left;font-size: 11px" rendered="#{presencialMB.visualizar}"> 
                                <f:converter converterId="converterSiNo"/>
                            </h:outputText>

                            <h:outputText value="Necesita Seguimiento: " styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NSeguimiento}" style="float: left;font-size: 11px" rendered="#{presencialMB.visualizar}"> 
                                <f:converter converterId="converterSiNo"/>
                            </h:outputText>
                        </h:panelGrid>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Seguimiento</legend>
                            <rich:dataTable id="vrepeat" value="#{presencialMB.listaInspreAcciones}" var="table" style="font-size: 11px;" width="100%" rendered="#{not presencialMB.listaAccionesVacia}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{table.DFecHora}" style="font-size: 11px;font-weight: bold;"> 
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss a"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{table.VDescripcion}" style="font-size: 11px;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:outputText id="txtEval" value="Ningún seguimiento para mostrar." styleClass="Etiqueta1" rendered="#{presencialMB.listaAccionesVacia}"/>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Evaluación</legend>
                        <h:panelGrid id="vevalPanel" columns="1" width="100%">
                            <h:panelGrid columns="2" cellpadding="0" width="55%" cellspacing="0" rendered="#{not empty presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}">
                                <h:outputLabel value="Ocurrencia: " style="font-weight: bold;" styleClass="Etiqueta1"/>
                                <h:outputText value="#{presencialMB.inspeccionEvaluacion.NOcurrencia}" style="float: left;font-size: 11px"> 
                                    <f:converter converterId="converterOcurrencia"/>
                                </h:outputText>
                                <rich:spacer height="5px;"/><rich:spacer height="5px;"/>
                                <h:outputLabel value="Impacto: " style="font-weight: bold;" styleClass="Etiqueta1"/>
                                <h:outputText value="#{presencialMB.inspeccionEvaluacion.NImpacto}" style="float: left;font-size: 11px"> 
                                    <f:converter converterId="converterImpacto"/>
                                </h:outputText>
                            </h:panelGrid>
                            <h:outputLabel value="Explicación:" style="font-weight: bold;" styleClass="Etiqueta1" rendered="#{not empty presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}"/>
                            <rich:dataTable value="#{presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}" var="row" width="100%" style="font-size: 11px;" rendered="#{not empty presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{row.DFechora}" style="font-size: 11px;font-weight: bold;"> 
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss a"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{row.VDiagnostico}" style="font-size: 11px;font-weight: normal;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:outputLabel value="Recomendaciones:" style="font-weight: bold;" styleClass="Etiqueta1" rendered="#{not empty presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}"/>
                            <rich:dataTable value="#{presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}" var="row" width="100%" style="font-size: 11px;" rendered="#{not empty presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{row.DFechora}" style="font-size: 11px;font-weight: bold;">
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss a"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{row.VRecomendacion}" style="font-size: 11px;font-weight: normal;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:outputText value="Ninguna evaluación para mostrar." styleClass="Etiqueta1" rendered="#{empty presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}"/>
                        </h:panelGrid>
                    </fieldset>
                </h:form>
                </div>
            </rich:modalPanel>
            <rich:modalPanel id="segDlg" resizeable="false" moveable="true" width="520" height="478">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="SEGUIMIENTO DE LA INSPECCIÓN PRESENCIAL"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="seghidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="segDlg" attachTo="seghidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <div style="overflow-y: auto;height:438px;">
                <h:form id="segDlgForm">
                    <rich:messages id="segDlgMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Inspección Presencial</legend>
                        <h:panelGrid id="segPanel" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Tipo de Inspección: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NTipoInspeccion}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterTipoInspeccion"/>
                            </h:outputText>

                            <h:outputText value="Novedad: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NCodNovedad}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterNovedad"/>
                            </h:outputText>

                            <h:outputText value="Fecha y Hora:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.DFecHora}" style="float: left;font-size: 11px"> 
                                <f:convertDateTime type="date" pattern="dd/MM/yyyy h:mm a"/>
                            </h:outputText>

                            <h:outputText value="Descripción Breve:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VDescBreve}" style="float: left;font-size: 11px;"/>

                            <h:outputText value="Descripción:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VDescripcion}" style="float: left;font-size: 11px;"/>

                            <h:outputText value="Local: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NLocal}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterLocal"/>
                            </h:outputText>

                            <h:outputText value="Area: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NArea}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterArea"/>
                            </h:outputText>

                            <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NLugar}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterLugar"/>
                            </h:outputText>

                            <h:outputText value="Responsable: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NResponsable}" style="float: left;font-size: 11px">
                                <f:converter converterId="converterResponsable"/>
                            </h:outputText>

                            <h:outputText value="Cargo: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NCargo}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterCargo"/>
                            </h:outputText>

                            <h:outputText value="Acción Tomada:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VAccTomadas}" style="float: left;font-size: 11px;"/>

                            <h:outputText value="Cumple Controles: " styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NCumpleControl}" style="float: left;font-size: 11px">
                                <f:converter converterId="converterSiNo"/>
                            </h:outputText>

                            <h:outputText value="Necesita Control Adicional: " styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NControlAdicional}" style="float: left;font-size: 11px"> 
                                <f:converter converterId="converterSiNo"/>
                            </h:outputText>

                            <h:outputText value="Controles Adicionales: " styleClass="Etiqueta1" rendered="#{presencialMB.visualizar}"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VControlAdicional}" style="float: left;font-size: 11px;" rendered="#{presencialMB.visualizar}"/>

                            <h:outputLabel value="Seguimiento:" styleClass="Etiqueta1" rendered="#{not presencialMB.listaAccionesVacia}"/>
                            <rich:spacer rendered="#{not presencialMB.listaAccionesVacia}"/>
                        </h:panelGrid>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Seguimiento</legend>
                        <h:panelGrid id="repeat" columns="1" width="100%" cellpadding="0" cellspacing="0">
                            <rich:dataTable value="#{presencialMB.listaInspreAcciones}" var="table" style="font-size: 11px;" width="100%" rendered="#{not presencialMB.listaAccionesVacia}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{table.DFecHora}" style="font-size: 11px;font-weight: bold;"> 
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss a"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{table.VDescripcion}" style="font-size: 11px;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                        </h:panelGrid>
                        <h:panelGrid id="pnlAccionTomada" columns="1">
                            <h:outputLabel value="Nueva Acción a Tomar:" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:inputTextarea value="#{presencialMB.accionTomada}" cols="81" rows="3" style="float: left;font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer/>
                        </h:panelGrid>
                    </fieldset>
                    <h:panelGroup>
                        <a4j:commandButton value="Grabar Seguimiento" onclick="#{rich:component('confirmSegDlg')}.show()" style="font-size: 11px;height: 2em" />
                        <a4j:commandButton value="Grabar y Cerrar Seguimiento" onclick="#{rich:component('confirmSegCerDlg')}.show()" style="font-size: 11px;height: 2em"/>
                    </h:panelGroup>
                </h:form>
                </div>
            </rich:modalPanel>
            <rich:modalPanel id="confirmSegDlg" height="100" width="350" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmSegDlgForm">
                    <h:outputText value="Se procederá con la grabar el seguimiento de la inspección." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="segDlgMsgs, tbl" actionListener="#{presencialMB.registrarSeguimiento}" oncomplete="#{presencialMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmSegDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmSegDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmSegCerDlg" height="100" width="380" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmSegCerDlgForm">
                    <h:outputText value="Se procederá con la grabar y cerrar el seguimiento de la inspección." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="segDlgMsgs, tbl" actionListener="#{presencialMB.registrarCerrarSeguimiento}" oncomplete="#{presencialMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmSegCerDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmSegCerDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="evalDlg" resizeable="false" moveable="true" width="520" height="535">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="EVALUACIÓN DE LA INSPECCIÓN PRESENCIAL"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="evalhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="evalDlg" attachTo="evalhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <div style="overflow-y: auto;height:500px;">
                <h:form id="evalDlgForm">
                    <rich:messages id="evalDlgMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Inspección Presencial</legend>
                        <h:panelGrid id="novPanel" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputLabel value="Código:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NCodInspresencial}" style="float: left;font-size: 11px;font-weight: normal;"/>
                            
                            <h:outputLabel value="Nombre:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VDescBreve}" style="float: left;font-size: 11px;font-weight: normal;"/>

                            <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                            <h:outputText value="#{presencialMB.selectedInsPresencial.NLugar}" style="float: left;font-size: 11px;font-weight: normal;"> 
                                <f:converter converterId="converterLugar"/>
                            </h:outputText>
                            
                            <h:outputLabel value="Fecha y Hora:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.DFecHora}" style="float: left;font-size: 11px;font-weight: normal;"> 
                                <f:convertDateTime type="date" pattern="dd/MM/yyyy h:mm a"/>
                            </h:outputText>
                            
                            <h:outputLabel value="Estado:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.inspeccionEvaluacion.NEstado}" style="float: left;font-size: 11px;font-weight: normal;"
                                          rendered="#{presencialMB.inspeccionEvaluacion.NEstado!=null}">
                                <f:converter converterId="converterEstadoEvaluacion"/>
                            </h:outputText>
                            <h:outputText value="PENDIENTE DE ANALISIS" style="float: left;font-size: 11px;font-weight: normal;" rendered="#{presencialMB.inspeccionEvaluacion.NEstado==null}"/>
                            
                            <h:outputLabel value="Descripción:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{presencialMB.selectedInsPresencial.VDescripcion}" style="float: left;font-size: 11px;font-weight: normal;"/>
                        </h:panelGrid>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Evaluación</legend>
                        <h:panelGrid id="evalPanel" columns="1" >
                            <h:outputLabel value="Cuál es la probabilidad de ocurrencia?" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:selectOneRadio id="ocurrencia" value="#{presencialMB.inspeccionEvaluacion.NOcurrencia}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaOcurrencia}"/>
                            </h:selectOneRadio>
                            <a4j:repeat var="var" stateVar="iter" value="#{listasSessionMB.listaAyudaOcurrencia}" rowKeyVar="rowKey">
                                <rich:toolTip direction="top left" mode="ajax" attached="label[for='evalDlgForm:ocurrencia:#{rowKey}']">
                                    <h:outputText value="#{var.label}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:toolTip>
                            </a4j:repeat>
                            <rich:spacer/>
                            <h:outputLabel value="Cuál es el impacto?" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:selectOneRadio id="impacto" value="#{presencialMB.inspeccionEvaluacion.NImpacto}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaImpacto}"/>
                            </h:selectOneRadio>
                            <a4j:repeat var="var" stateVar="iter" value="#{listasSessionMB.listaAyudaImpacto}" rowKeyVar="rowKey">
                                <rich:toolTip direction="top-left" mode="ajax" attached="label[for='evalDlgForm:impacto:#{rowKey}']">
                                    <h:outputText value="#{var.label}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:toolTip>
                            </a4j:repeat>
                            <rich:spacer/>
                            <h:outputLabel value="Explicación:" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <rich:dataTable id="diagnostico" value="#{presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}" var="row" width="100%" style="font-size: 11px;" rendered="#{not empty presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{row.DFechora}" style="font-size: 11px;font-weight: bold;"> 
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss a"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{row.VDiagnostico}" style="font-size: 11px;font-weight: normal;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:inputTextarea value="#{presencialMB.diagnostico}" rows="3" cols="81" style="font-size: 11px;font-weight: normal;text-transform: uppercase;"/>
                            <rich:spacer/>
                            <h:outputLabel value="Recomendaciones:" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <rich:dataTable id="recomendacion" value="#{presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}" var="row" width="100%" style="font-size: 11px;" rendered="#{not empty presencialMB.inspeccionEvaluacion.segDetInspreevalDetalles}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{row.DFechora}" style="font-size: 11px;font-weight: bold;">
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss a"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{row.VRecomendacion}" style="font-size: 11px;font-weight: normal;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:inputTextarea value="#{presencialMB.recomendacion}" rows="3" cols="81" style="font-size: 11px;font-weight: normal;text-transform: uppercase;"/>
                        </h:panelGrid>
                    </fieldset>
                    <h:panelGrid columns="1">
                        <h:panelGroup>
                            <a4j:commandButton value="Grabar Evaluación" onclick="#{rich:component('confirmEvalDlg')}.show()" style="font-size: 11px;height: 2em" />
                            <a4j:commandButton value="Grabar y Cerrar Evaluación" onclick="#{rich:component('confirmEvalCerDlg')}.show()" style="font-size: 11px;height: 2em"/>
                        </h:panelGroup>
                    </h:panelGrid>
                </h:form>
                </div>
            </rich:modalPanel>
            <rich:modalPanel id="confirmEvalDlg" height="100" width="400" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEvalDlgForm">
                    <h:outputText value="Se procederá con la grabar la evaluación de la inspección presencial." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="evalDlgMsgs, tbl" actionListener="#{presencialMB.registrarEvaluacion}" oncomplete="#{presencialMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmEvalCerDlg" height="100" width="430" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEvalCerDlgForm">
                    <h:outputText value="Se procederá con la grabar y cerrar la evaluación de la inspección presencial." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="evalDlgMsgs, tbl" actionListener="#{presencialMB.registrarCerrarEvaluacion}" oncomplete="#{presencialMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalCerDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalCerDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="dlgLocal" resizeable="false" moveable="true" autosized="true" width="750">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="AÑADIR LOCAL"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="localhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlgLocal" attachTo="localhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgLocalForm">
                    <rich:messages id="dlgLocalMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <h:panelGrid id="pnlLocal" columns="1">
                        <h:panelGroup>
                            <h:outputText value="Local : " styleClass="Etiqueta1"/>
                            <h:inputText id="txtlocal" size="95" maxlength="150" value="#{presencialMB.descripcionLocal}" style="font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.registrarLocal}" reRender="pnlSearch, pnlNew, pnlEdit, dlgLocalMsgs, pnlLocal">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir local" alt="añadir local"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        <rich:spacer/>
                        <rich:dataTable id="locales" var="row" rowKeyVar="rowkey" value="#{presencialMB.listaLocales}" rows="10" style="width: 720px;">
                            <f:facet name="header">
                                <rich:datascroller for="locales" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="LOCAL" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;width:10px;">
                                <f:facet name="header">
                                    <h:outputText value="ELIMINAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink onclick="#{rich:component('confirmDelLocalDlg')}.show()">
                                    <f:setPropertyActionListener value="#{row}" target="#{presencialMB.selectedLocal}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar local" title="eliminar local"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDelLocalDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDelLocalDlgForm">
                    <h:outputText value="Se procederá a eliminar el local." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="pnlSearch, pnlNew, pnlEdit, dlgLocalMsgs, pnlLocal" actionListener="#{presencialMB.eliminarLocal}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelLocalDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelLocalDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="dlgArea" resizeable="false" moveable="true" autosized="true" width="750">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="AÑADIR AREA"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="areahidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlgArea" attachTo="areahidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgAreaForm">
                    <rich:messages id="dlgAreaMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <h:panelGrid id="pnlArea" columns="1">
                        <h:panelGroup>
                            <h:outputText value="Area : " styleClass="Etiqueta1"/>
                            <h:inputText id="txtarea" size="95" maxlength="150" value="#{presencialMB.descripcionArea}" style="font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.registrarArea}" reRender="pnlSearch, pnlNew, pnlEdit, dlgAreaMsgs, pnlArea">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir area" alt="añadir area"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        <rich:spacer/>
                        <rich:dataTable id="areas" var="row" rowKeyVar="rowkey" value="#{presencialMB.listaAreas}" rows="10" style="width: 720px;">
                            <f:facet name="header">
                                <rich:datascroller for="areas" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="AREA" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;width:10px;">
                                <f:facet name="header">
                                    <h:outputText value="ELIMINAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink onclick="#{rich:component('confirmDelAreaDlg')}.show()">
                                    <f:setPropertyActionListener value="#{row}" target="#{presencialMB.selectedArea}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar area" title="eliminar area"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDelAreaDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDelAreaDlgForm">
                    <h:outputText value="Se procederá a eliminar el área." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="pnlSearch, pnlNew, pnlEdit, dlgAreaMsgs, pnlArea" actionListener="#{presencialMB.eliminarArea}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelAreaDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelAreaDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="dlgLugar" resizeable="false" moveable="true" autosized="true" width="750">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="AÑADIR LUGAR"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="lugarhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlgLugar" attachTo="lugarhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgLugarForm">
                    <rich:messages id="dlgLugarMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <h:panelGrid id="pnlLugar" columns="1">
                        <h:panelGroup>
                            <h:outputText value="Lugar : " styleClass="Etiqueta1"/>
                            <h:inputText id="txtlugar" size="95" maxlength="150" value="#{presencialMB.descripcionLugar}" style="font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.registrarLugar}" reRender="pnlLugar, dlgLugarMsgs, pnlSearch, pnlNew, pnlEdit">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir lugar" alt="añadir lugar"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        <rich:spacer/>
                        <rich:dataTable id="lugares" var="row" rowKeyVar="rowkey" value="#{presencialMB.listaLugares}" rows="10" style="width: 720px;">
                            <f:facet name="header">
                                <rich:datascroller for="lugares" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="LUGAR" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;width:10px;">
                                <f:facet name="header">
                                    <h:outputText value="ELIMINAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink onclick="#{rich:component('confirmDelLugarDlg')}.show()">
                                    <f:setPropertyActionListener value="#{row}" target="#{presencialMB.selectedLugar}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar lugar" title="eliminar lugar"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDelLugarDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDelLugarDlgForm">
                    <h:outputText value="Se procederá a eliminar el lugar." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="pnlLugar, dlgLugarMsgs, pnlSearch, pnlNew, pnlEdit" actionListener="#{presencialMB.eliminarLugar}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelLugarDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelLugarDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="dlgResponsable" resizeable="false" moveable="true" autosized="true" width="750">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="AÑADIR RESPONSABLE"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="responsablehidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlgResponsable" attachTo="responsablehidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgResponsableForm">
                    <rich:messages id="dlgResponsableMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <h:panelGrid id="pnlResponsable" columns="1">
                        <h:panelGroup>
                            <h:outputText value="Responsable : " styleClass="Etiqueta1"/>
                            <h:inputText id="txtnombre" size="48" maxlength="90" value="#{presencialMB.nombreResponsable}" style="font-size: 11px;text-transform: uppercase;"/>
                            <h:inputText id="txtapellido" size="48" maxlength="90" value="#{presencialMB.apellidoResponsable}" style="font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.registrarResponsable}" 
                                           reRender="pnlResponsable, dlgResponsableMsgs, pnlSearch, pnlNew, pnlEdit">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir responsable" alt="añadir responsable"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        <rich:spacer/>
                        <rich:dataTable id="responsables" var="row" rowKeyVar="rowkey" value="#{presencialMB.listaResponsables}" style="width: 720px;">
                            <f:facet name="header">
                                <rich:datascroller for="responsables" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="NOMBRES" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VNombres}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="APELLIDOS" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VApellidos}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;width:10px;">
                                <f:facet name="header">
                                    <h:outputText value="ELIMINAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink onclick="#{rich:component('confirmDelResponsableDlg')}.show()">
                                    <f:setPropertyActionListener value="#{row}" target="#{presencialMB.selectedResponsable}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar responsable" title="eliminar responsable"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDelResponsableDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDelResponsableDlgForm">
                    <h:outputText value="Se procederá a eliminar al responsable." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="pnlResponsable, dlgResponsableMsgs, pnlSearch, pnlNew, pnlEdit" actionListener="#{presencialMB.eliminarResponsable}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelResponsableDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelResponsableDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="dlgCargo" resizeable="false" moveable="true" autosized="true" width="750">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="AÑADIR CARGO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="cargohidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlgCargo" attachTo="cargohidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgCargoForm">
                    <rich:messages id="dlgCargoMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <h:panelGrid id="pnlCargo" columns="1">
                        <h:panelGroup>
                            <h:outputText value="Cargo : " styleClass="Etiqueta1"/>
                            <h:inputText id="txtcargo" size="95" maxlength="150" value="#{presencialMB.descripcionCargo}" style="font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{presencialMB.registrarCargo}" reRender="pnlCargo, dlgCargoMsgs, pnlSearch, pnlNew, pnlEdit">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir cargo" alt="añadir cargo"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        <rich:spacer/>
                        <rich:dataTable id="cargos" var="row" rowKeyVar="rowkey" value="#{presencialMB.listaCargos}" rows="10" style="width: 720px;">
                            <f:facet name="header">
                                <rich:datascroller for="cargos" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="CARGO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;width:10px;">
                                <f:facet name="header">
                                    <h:outputText value="ELIMINAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink onclick="#{rich:component('confirmDelCargoDlg')}.show()">
                                    <f:setPropertyActionListener value="#{row}" target="#{presencialMB.selectedCargo}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar cargo" title="eliminar cargo"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDelCargoDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDelCargoDlgForm">
                    <h:outputText value="Se procederá a eliminar al cargo." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="pnlCargo, dlgCargoMsgs, pnlSearch, pnlNew, pnlEdit" actionListener="#{presencialMB.eliminarCargo}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelCargoDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelCargoDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <h:form id="hiddenForm" style="display: none;">
                <h:inputHidden id="action" value="#{presencialMB.actionOnLoad}" />
                <a4j:commandButton process="@form" id="invisibleButton" onclick="#{presencialMB.actionOnLoad}"/>
                <a4j:commandButton process="@form" id="hiddenBtn" actionListener="#{menuMB.toMatrizRiesgo}" action="matriz" />
            </h:form>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
