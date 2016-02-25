<?xml version="1.0" encoding="UTF-8"?>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Evaluación de Riesgos</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
        <script type="text/javascript" src="../js/locales.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="riesgoForm">
                <rich:panel header="EVALUACIÓN DE RIESGOS" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Tipo de Riesgo: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchTipoRiesgo" value="#{riesgoMB.searchTipoRiesgo}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaTipoRiesgo}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Local: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchLocal" value="#{riesgoMB.searchLocal}" valueChangeListener="#{listasSessionMB.obtenerListaAreaByLocal}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaLocalActivoByEmpresa}"/>
                                <a4j:support event="onchange" reRender="searchArea" ajaxSingle="true"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Area: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchArea" value="#{riesgoMB.searchArea}" valueChangeListener="#{listasSessionMB.obtenerListaLugarByArea}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaAreaActivaByLocal}"/>
                                <a4j:support event="onchange" reRender="searchLugar, searchResponsable" ajaxSingle="true"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchLugar" value="#{riesgoMB.searchLugar}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaLugarActivoByArea}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Responsable: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchResponsable" value="#{riesgoMB.searchResponsable}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaResponsableActivoByArea}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Cargo: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchCargo" value="#{riesgoMB.searchCargo}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaCargoActivoByEmpresa}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Estado: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchEstado" value="#{riesgoMB.searchEstado}" style="float: left;font-size: 11px">
                                <f:selectItem itemValue="-1" itemLabel="Seleccione"/>
                                <f:selectItems value="#{listasSessionMB.listaEstadoEvaluacion}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Rango Fechas: " styleClass="Etiqueta1" />
                            <h:panelGrid columns="5" cellpadding="0" cellspacing="0">
                                <rich:calendar value="#{riesgoMB.searchFechaInicio}" popup="true" datePattern="dd/MM/yyyy"
                                jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                                <rich:spacer width="10"/>
                                <h:outputText value="al" style="font-weight: bold;" styleClass="Etiqueta1" />
                                <rich:spacer width="10"/>
                                <rich:calendar value="#{riesgoMB.searchFechaFin}" popup="true" datePattern="dd/MM/yyyy"
                                jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                            </h:panelGrid>
                        </h:panelGrid>
                    </fieldset>
                    
                    <h:panelGrid columns="1">
                        <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{riesgoMB.buscarRiesgo}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                    </h:panelGrid>
                    
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Riesgos</legend>
                        <rich:dataTable id="tbl" var="row" rowKeyVar="rowKey" value="#{riesgoMB.listaRiesgo}" rows="10" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="TIPO RIESGO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.id.NTipoRiesgo}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterTipoRiesgo"/>
                                </h:outputText>
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
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="DESCRIPCIÓN BREVE" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescBreve}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="FECHA Y HORA" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.DFecHora}" style="font-size: 11px;font-weight: normal;">
                                    <f:convertDateTime type="date" pattern="dd/MM/yyyy h:mm a"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="ESTADO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NEstado}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterEstadoEvaluacion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="elink" actionListener="#{riesgoMB.toEvaluacion}" oncomplete="#{riesgoMB.action}" reRender="novPanel, evalNovPanel, insPanel, evalInsPanel" rendered="#{row.NEstado != 55}">
                                    <f:param value="#{rowKey}" name="rowKey"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{riesgoMB.selectedRiesgo}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/table_edit.png" alt="evaluar riesgo" title="evaluar riesgo"/>
                                </a4j:commandLink>
                                <h:graphicImage style="border: 0;" url="/pages/images/table_edit_disabled.png" alt="evaluar riesgo" title="evaluar riesgo" rendered="#{row.NEstado == 55}"/>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="alink" actionListener="#{riesgoMB.reabrirEvaluacion}" reRender="tbl, novPanel, evalNovPanel, insPanel, evalInsPanel" rendered="#{row.NEstado == 55}">
                                    <f:param value="#{rowKey}" name="rowKey"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{riesgoMB.selectedRiesgo}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/open_folder.png" width="16" height="16" alt="reabrir riesgo" title="reabrir riesgo"/>
                                </a4j:commandLink>
                                <h:graphicImage style="border: 0;" url="/pages/images/open_folder_disabled.png" width="16" height="16" alt="reabrir riesgo" title="reabrir riesgo" rendered="#{row.NEstado != 55}"/>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="evalNovDlg" resizeable="false" moveable="true" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="EVALUACIÓN DEL RIESGO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="evalhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="evalNovDlg" attachTo="evalhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="evalNovDlgForm">
                    <rich:messages id="evalNovDlgMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                        <legend style="font-size: 11px;font-weight: bold;">Riesgo</legend>
                        <h:panelGrid id="novPanel" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputLabel value="Código:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.selectedNovedad.NCodNovedad}" style="float: left;font-size: 11px;font-weight: normal;"/>
                            
                            <h:outputLabel value="Nombre:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.selectedNovedad.VDescBreve}" style="float: left;font-size: 11px;font-weight: normal;"/>

                            <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                            <h:outputText value="#{riesgoMB.selectedNovedad.NLugar}" style="float: left;font-size: 11px;font-weight: normal;"> 
                                <f:converter converterId="converterLugar"/>
                            </h:outputText>
                            
                            <h:outputLabel value="Fecha y Hora:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.selectedNovedad.DFecHora}" style="float: left;font-size: 11px;font-weight: normal;"> 
                                <f:convertDateTime type="date" pattern="dd/MM/yyyy h:mm a"/>
                            </h:outputText>
                            
                            <h:outputLabel value="Estado:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.novedadEvaluacion.NEstado}" style="float: left;font-size: 11px;font-weight: normal;"
                                          rendered="#{riesgoMB.novedadEvaluacion.NEstado!=null}">
                                <f:converter converterId="converterEstadoEvaluacion"/>
                            </h:outputText>
                            <h:outputText value="PENDIENTE DE ANALISIS" style="float: left;font-size: 11px;font-weight: normal;" rendered="#{riesgoMB.novedadEvaluacion.NEstado==null}"/>
                            
                            <h:outputLabel value="Descripción:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.selectedNovedad.VDescripcion}" style="float: left;font-size: 11px;font-weight: normal;"/>
                        </h:panelGrid>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Evaluación</legend>
                        <h:panelGrid id="evalNovPanel" columns="1" >
                            <h:outputLabel value="Cuál es la probabilidad de ocurrencia?" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:selectOneRadio id="ocurrencia" value="#{riesgoMB.novedadEvaluacion.NOcurrencia}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaOcurrencia}"/>
                            </h:selectOneRadio>
                            <a4j:repeat var="var" stateVar="iter" value="#{listasSessionMB.listaAyudaOcurrencia}" rowKeyVar="rowKey">
                                <rich:toolTip direction="top left" mode="ajax" attached="label[for='evalNovDlgForm:ocurrencia:#{rowKey}']">
                                    <h:outputText value="#{var.label}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:toolTip>
                            </a4j:repeat>
                            <rich:spacer/>
                            <h:outputLabel value="Cuál es el impacto?" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:selectOneRadio id="impacto" value="#{riesgoMB.novedadEvaluacion.NImpacto}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaImpacto}"/>
                            </h:selectOneRadio>
                            <a4j:repeat var="var" stateVar="iter" value="#{listasSessionMB.listaAyudaImpacto}" rowKeyVar="rowKey">
                                <rich:toolTip direction="top left" mode="ajax" attached="label[for='evalNovDlgForm:impacto:#{rowKey}']">
                                    <h:outputText value="#{var.label}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:toolTip>
                            </a4j:repeat>
                            <rich:spacer/>
                            <h:outputLabel value="Explicación:" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <rich:dataTable id="diagnostico" value="#{riesgoMB.novedadEvaluacion.segDetNovevalDetalles}" var="row" style="font-size: 11px;" width="100%" rendered="#{not empty riesgoMB.novedadEvaluacion.segDetNovevalDetalles}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{row.DFechora}" style="font-size: 11px;font-weight: bold;"> 
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{row.VDiagnostico}" style="font-size: 11px;font-weight: normal;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:inputTextarea value="#{riesgoMB.diagnostico}" rows="5" cols="81" style="font-size: 11px;font-weight: normal;"/>
                            <rich:spacer/>
                            <h:outputLabel value="Recomendaciones:" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <rich:dataTable id="recomendacion" value="#{riesgoMB.novedadEvaluacion.segDetNovevalDetalles}" var="row" style="font-size: 11px;" width="100%" rendered="#{not empty riesgoMB.novedadEvaluacion.segDetNovevalDetalles}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{row.DFechora}" style="font-size: 11px;font-weight: bold;">
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{row.VRecomendacion}" style="font-size: 11px;font-weight: normal;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:inputTextarea value="#{riesgoMB.recomendacion}" rows="5" cols="81" style="font-size: 11px;font-weight: normal;"/>
                        </h:panelGrid>
                    </fieldset>
                    <h:panelGrid columns="1">
                        <h:panelGroup>
                            <a4j:commandButton value="Grabar Evaluación" onclick="#{rich:component('confirmEvalNovDlg')}.show();" style="font-size: 11px;height: 2em" />
                            <a4j:commandButton value="Grabar y Cerrar Evaluación" onclick="#{rich:component('confirmEvalCerNovDlg')}.show();" style="font-size: 11px;height: 2em"/>
                        </h:panelGroup>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmEvalNovDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEvalNovDlgForm">
                    <h:outputText value="Se procederá a grabar la evaluación del riesgo." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="evalNovDlgMsgs" actionListener="#{riesgoMB.registrarEvaluacionNovedad}" oncomplete="#{riesgoMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalNovDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalNovDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmEvalCerNovDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEvalCerNovDlgForm">
                    <h:outputText value="Se procederá a grabar y cerrar la evaluación del riesgo." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="evalNovDlgMsgs" actionListener="#{riesgoMB.registrarCerrarEvaluacionNovedad}" oncomplete="#{riesgoMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalCerNovDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalCerNovDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="evalInsDlg" resizeable="false" moveable="true" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="EVALUACIÓN DEL RIESGO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="evalhidelink_" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="evalInsDlg" attachTo="evalhidelink_" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="evalInsDlgForm">
                    <rich:messages id="evalInsDlgMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                        <legend style="font-size: 11px;font-weight: bold;">Riesgo</legend>
                        <h:panelGrid id="insPanel" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputLabel value="Código:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.selectedInsPresencial.NCodInspresencial}" style="float: left;font-size: 11px;font-weight: normal;"/>
                            
                            <h:outputLabel value="Nombre:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.selectedInsPresencial.VDescBreve}" style="float: left;font-size: 11px;font-weight: normal;"/>

                            <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                            <h:outputText value="#{riesgoMB.selectedInsPresencial.NLugar}" style="float: left;font-size: 11px;font-weight: normal;"> 
                                <f:converter converterId="converterLugar"/>
                            </h:outputText>
                            
                            <h:outputLabel value="Fecha y Hora:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.selectedInsPresencial.DFecHora}" style="float: left;font-size: 11px;font-weight: normal;"> 
                                <f:convertDateTime type="date" pattern="dd/MM/yyyy h:mm a"/>
                            </h:outputText>
                            
                            <h:outputLabel value="Estado:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.inspeccionEvaluacion.NEstado}" style="float: left;font-size: 11px;font-weight: normal;"
                                          rendered="#{riesgoMB.inspeccionEvaluacion.NEstado!=null}">
                                <f:converter converterId="converterEstadoEvaluacion" />
                            </h:outputText>
                            <h:outputText value="PENDIENTE DE ANALISIS" style="float: left;font-size: 11px;font-weight: normal;" rendered="#{riesgoMB.inspeccionEvaluacion.NEstado==null}"/>
                            
                            <h:outputLabel value="Descripción:" styleClass="Etiqueta1"/>
                            <h:outputText value="#{riesgoMB.selectedInsPresencial.VDescripcion}" style="float: left;font-size: 11px;font-weight: normal;"/>
                        </h:panelGrid>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Evaluación</legend>
                        <h:panelGrid id="evalInsPanel" columns="1" >
                            <h:outputLabel value="Cuál es la probabilidad de ocurrencia?" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:selectOneRadio id="ocurrencia" value="#{riesgoMB.inspeccionEvaluacion.NOcurrencia}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaOcurrencia}"/>
                            </h:selectOneRadio>
                            <a4j:repeat var="var" stateVar="iter" value="#{listasSessionMB.listaAyudaOcurrencia}" rowKeyVar="rowKey">
                                <rich:toolTip direction="top left" mode="ajax" attached="label[for='evalInsDlgForm:ocurrencia:#{rowKey}']">
                                    <h:outputText value="#{var.label}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:toolTip>
                            </a4j:repeat>
                            <rich:spacer/>
                            <h:outputLabel value="Cuál es el impacto?" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:selectOneRadio id="impacto" value="#{riesgoMB.inspeccionEvaluacion.NImpacto}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaImpacto}"/>
                            </h:selectOneRadio>
                            <a4j:repeat var="var" stateVar="iter" value="#{listasSessionMB.listaAyudaImpacto}" rowKeyVar="rowKey">
                                <rich:toolTip direction="top left" mode="ajax" attached="label[for='evalInsDlgForm:impacto:#{rowKey}']">
                                    <h:outputText value="#{var.label}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:toolTip>
                            </a4j:repeat>
                            <rich:spacer/>
                            <h:outputLabel value="Explicación:" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <rich:dataTable id="diagnostico" value="#{riesgoMB.inspeccionEvaluacion.segDetInspreevalDetalles}" var="row" width="100%" style="font-size: 11px;" rendered="#{not empty riesgoMB.inspeccionEvaluacion.segDetInspreevalDetalles}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{row.DFechora}" style="font-size: 11px;font-weight: bold;"> 
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{row.VDiagnostico}" style="font-size: 11px;font-weight: normal;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:inputTextarea value="#{riesgoMB.diagnostico}" rows="5" cols="81" style="font-size: 11px;font-weight: normal;"/>
                            <rich:spacer/>
                            <h:outputLabel value="Recomendaciones:" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <rich:dataTable id="recomendacion" value="#{riesgoMB.inspeccionEvaluacion.segDetInspreevalDetalles}" var="row" width="100%" style="font-size: 11px;" rendered="#{not empty riesgoMB.inspeccionEvaluacion.segDetInspreevalDetalles}">
                                <rich:column>
                                    <h:panelGroup>
                                        <h:outputText value="#{row.DFechora}" style="font-size: 11px;font-weight: bold;">
                                            <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss"/>
                                        </h:outputText>
                                        <rich:spacer width="5"/>
                                        <h:outputText value="#{row.VRecomendacion}" style="font-size: 11px;font-weight: normal;"/>
                                    </h:panelGroup>
                                </rich:column>
                            </rich:dataTable>
                            <h:inputTextarea value="#{riesgoMB.recomendacion}" rows="5" cols="81" style="font-size: 11px;font-weight: normal;"/>
                        </h:panelGrid>
                    </fieldset>
                    <h:panelGrid columns="1">
                        <h:panelGroup>
                            <a4j:commandButton value="Grabar Evaluación" onclick="#{rich:component('confirmEvalInsDlg')}.show();" style="font-size: 11px;height: 2em" />
                            <a4j:commandButton value="Grabar y Cerrar Evaluación" onclick="#{rich:component('confirmEvalCerInsDlg')}.show();" style="font-size: 11px;height: 2em"/>
                        </h:panelGroup>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmEvalInsDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEvalInsDlgForm">
                    <h:outputText value="Se procederá a grabar la evaluación del riesgo." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="evalInsDlgMsgs" actionListener="#{riesgoMB.registrarEvaluacionInspeccion}" oncomplete="#{riesgoMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalInsDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalInsDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmEvalCerInsDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEvalCerInsDlgForm">
                    <h:outputText value="Se procederá a grabar y cerrar la evaluación del riesgo." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="evalInsDlgMsgs" actionListener="#{riesgoMB.registrarCerrarEvaluacionInspeccion}" oncomplete="#{riesgoMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalCerInsDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEvalCerInsDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
