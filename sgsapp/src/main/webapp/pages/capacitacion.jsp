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
        <title>SGSWEB - Gestión de Capacitaciones</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="capacitacionForm">
                <rich:panel header="GESTIÓN DE CAPACITACIONES" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid columns="2" columnClasses="columnLbl, columnTxt">
                            <h:outputText value="Participante: " styleClass="Etiqueta1" />
                            <h:inputText id="searchParticipante" size="100" maxlength="100" value="#{capacitacionMB.searchParticipante}" style="font-size: 11px;text-transform: uppercase;"/>
                            
                            <h:outputText value="Nombre: " styleClass="Etiqueta1" />
                            <h:inputText id="searchNombre" size="100" maxlength="100" value="#{capacitacionMB.searchNombre}" style="font-size: 11px;text-transform: uppercase;"/>
                            
                            <h:outputText value="Tipo Capacitación: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchTipoCapacitacion" value="#{capacitacionMB.searchTipoCapacitacion}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaTipoCapacitacion}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Modalidad Capacitación: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchModalidad" value="#{capacitacionMB.searchModalidad}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaModalidadCapacitacion}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Rango Fechas: " styleClass="Etiqueta1" />
                            <h:panelGrid columns="5" cellpadding="0" cellspacing="0">
                                <rich:calendar id="searchFechaInicio" value="#{capacitacionMB.searchFechaInicio}" popup="true" datePattern="dd/MM/yyyy" 
                                               jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                                <rich:spacer width="10"/>
                                <h:outputText value="al" style="font-weight: bold;" styleClass="Etiqueta1" />
                                <rich:spacer width="10"/>
                                <rich:calendar id="searchFechaFin" value="#{capacitacionMB.searchFechaFin}" popup="true" datePattern="dd/MM/yyyy" 
                                               jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                            </h:panelGrid>
                            
                            
                        </h:panelGrid>
                    </fieldset>
                    
                    <h:panelGrid columns="1">
                        <h:panelGroup>
                            <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{capacitacionMB.buscarCapacitaciones}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                            <a4j:commandButton id="btnNuevo" value="Nuevo" actionListener="#{capacitacionMB.toRegistrar}" reRender="dlgMsgs, pnlCap, pnlNew, cap" style="font-size: 11px;height: 2em">
                                <rich:componentControl for="dlg" attachTo="btnNuevo" operation="show" event="onclick"/>
                            </a4j:commandButton>
                        </h:panelGroup>
                    </h:panelGrid>
                    
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Capacitaciones</legend>
                        <rich:dataTable id="tbl" var="row" value="#{capacitacionMB.listaCapacitacion}" rows="10" rowKeyVar="rowkey" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="NOMBRE" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VNombre}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="MODALIDAD" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NModalidad}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterModalidadCapacitacion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="TIPO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NTipoCapacitacion}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterTipoCapacitacion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="LUGAR" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NLugar}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterLugarCapacitacion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="FECHA Y HORA" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.DFechaHora}" style="font-size: 11px;font-weight: normal;">
                                    <f:convertDateTime type="date" locale="ISO-8859-1" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;width: 5%;">
                                <f:facet name="header">
                                    <h:outputText value="EDITAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="elink" actionListener="#{capacitacionMB.toEdit}" oncomplete="#{rich:component('editDlg')}.show()" reRender="editPnlCap, pnlEdit, editCap">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/pencil.png" alt="editar capacitación" title="editar capacitación"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="true" autosized="true" width="550">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="NUEVA CAPACITACIÓN"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="hidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlg" attachTo="hidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgForm" prependId="true">
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
                    <fieldset id="fsCap">
                        <legend style="font-size: 11px;font-weight: bold;">Datos de la Capacitación</legend>
                        <h:panelGrid id="pnlCap" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Nombre: " styleClass="Etiqueta1" />
                            <h:inputText id="idNombre" value="#{capacitacionMB.nombre}" size="71" maxlength="70" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                            <h:outputText value="Descripción: " styleClass="Etiqueta1" />
                            <h:inputTextarea id="idDescripcion" value="#{capacitacionMB.descripcion}" cols="68" rows="2" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                            <h:outputText value="Tipo Capacitación: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="idTipoCapacitacion" value="#{capacitacionMB.codTipoCapacitacion}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaTipoCapacitacion}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Modalidad: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="idModalidad" value="#{capacitacionMB.codModalidad}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaModalidadCapacitacion}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Lugar: " styleClass="Etiqueta1"/>
                            <h:panelGroup>
                                <h:selectOneMenu value="#{capacitacionMB.codLugar}" style="float: left;font-size: 11px" >
                                    <f:selectItems value="#{listasSessionMB.listaLugarCapacitacion}"/>
                                </h:selectOneMenu>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{capacitacionMB.listarLugares}" reRender="lugares" oncomplete="#{rich:component('dlgLugar')}.show();">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" alt="añadir lugar" title="añadir lugar"/>
                                </a4j:commandLink>
                            </h:panelGroup>

                            <h:outputText value="Fecha y Hora:" styleClass="Etiqueta1"/>
                            <rich:calendar id="idFechaHora" value="#{capacitacionMB.fechaHora}" popup="true" datePattern="dd/M/yyyy hh:mm a" showApplyButton="true" 
                                    jointPoint="top-left" direction="bottom-right" locale="es" inputSize="20" style="float: left;font-size: 11px"/>
                        </h:panelGrid>
                    </fieldset>
                    <rich:spacer/>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Datos de los Participantes</legend>
                        <h:panelGrid id="pnlNew" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Tipo de Carga: " style="float: left;" styleClass="Etiqueta1" />
                            <h:selectOneRadio id="tipoCarga" value="#{capacitacionMB.tipoCarga}" valueChangeListener="#{capacitacionMB.handleChangeTipoCarga}" style="float: left;font-size: 11px;">
                                <f:selectItem itemValue="1" itemLabel="Carga Individual"/>
                                <f:selectItem itemValue="2" itemLabel="Carga Masiva"/>
                                <a4j:support event="onchange" reRender="pnlNew"/>
                            </h:selectOneRadio>
                            <rich:spacer width="10px;" rendered="#{capacitacionMB.cargaIndividual}"/>
                            <h:panelGrid columns="2" width="85%" cellpadding="0" cellspacing="0" style="text-align: center;" rendered="#{capacitacionMB.cargaIndividual}">
                                <h:outputText value="Nombres del Participante" style="font-size: 9px;"/>
                                <h:outputText value="Apellidos del Participante" style="font-size: 9px;"/>
                            </h:panelGrid>
                            <h:outputText value="Nombre Completo: " styleClass="Etiqueta1" rendered="#{capacitacionMB.cargaIndividual}"/>
                            <h:panelGroup id="groupIndividual" rendered="#{capacitacionMB.cargaIndividual}">
                                <h:inputText id="idNombres" value="#{capacitacionMB.nombreParticipante}" size="26" maxlength="80" style="font-size: 11px;text-transform: uppercase;"/>
                                <h:inputText id="idApellido" value="#{capacitacionMB.apellidoParticipante}" size="26" maxlength="80" style="font-size: 11px;text-transform: uppercase;"/>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{capacitacionMB.agregarParticipante}" reRender="idNombres, idApellido, cap, dlgMsgs">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" alt="agregar participante" title="agregar participante"/>
                                </a4j:commandLink>
                            </h:panelGroup>
                            <h:outputLabel value="Archivo:" styleClass="Etiqueta1" rendered="#{capacitacionMB.cargaMasiva and empty capacitacionMB.listaParticipante}"/>
                            <rich:fileUpload id="idArchivo" acceptedTypes="xls" maxFilesQuantity="1" fileUploadListener="#{capacitacionMB.uploadFile}" noDuplicate="true" listHeight="58" listWidth="380" 
                                             addControlLabel="Agregar" clearControlLabel="Limpiar" stopControlLabel="Parar" stopEntryControlLabel="Parar" cancelEntryControlLabel="Cancelar"
                                             uploadControlLabel="Cargar" clearAllControlLabel="Limpiar" progressLabel="Cargando..." doneLabel="Finalizado" transferErrorLabel="Error al cargar archivo"
                                             rendered="#{capacitacionMB.cargaMasiva  and empty capacitacionMB.listaParticipante}">
                                <f:facet name="label">
                                    <h:outputText value="{_KB}KB de {KB}KB cargados --- {mm}:{ss}" />
                                </f:facet>
                                <a4j:support event="onuploadcomplete" reRender="pnlNew, cap, dlgMsgs"/>
                            </rich:fileUpload>
                            <rich:spacer width="10px;" rendered="#{capacitacionMB.cargaMasiva  and empty capacitacionMB.listaParticipante}"/>
                            <h:panelGroup rendered="#{capacitacionMB.cargaMasiva  and empty capacitacionMB.listaParticipante}">
                                <h:commandLink action="#{capacitacionMB.downloadTemplate}" target="_blank">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/documentExcel.png" alt="descargar plantilla" title="descargar plantilla"/>
                                </h:commandLink>
                                <rich:spacer width="10px;"/>
                                <h:outputText value="Plantilla Carga Masiva" styleClass="Etiqueta1" />
                            </h:panelGroup>
                            <h:outputLabel value="Archivo:" styleClass="Etiqueta1" rendered="#{not empty capacitacionMB.listaParticipante}"/>
                            <h:outputText value="Carga finalizada." styleClass="Etiqueta1" rendered="#{not empty capacitacionMB.listaParticipante}"/>
                        </h:panelGrid>
                        
                            <rich:dataTable id="cap" var="row" value="#{capacitacionMB.listaParticipante}" rows="5" style="font-size: 11px;" width="100%">
                                <f:facet name="caption">
                                    <h:outputText value="LISTA DE PARTICIPANTES" styleClass="Etiqueta1" />
                                </f:facet>
                                <f:facet name="header">
                                    <rich:datascroller for="cap" />
                                </f:facet>
                                <rich:column>
                                    <f:facet name="header">
                                        <h:outputText value="NOMBRE PARTICIPANTE" style="font-size: 11px;"/>
                                    </f:facet>
                                    <h:outputText value="#{row.VNombreCompleto}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:column>
                            </rich:dataTable>
                        
                    </fieldset>
                    <h:panelGrid columns="1">
                        <a4j:commandButton value="Grabar" onclick="#{rich:component('confirmDlg')}.show();" style="font-size: 11px;height: 2em"/>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDlgForm">
                    <h:outputText value="Se procederá a registrar la capacitación." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="dlgMsgs" actionListener="#{capacitacionMB.registrarCapacitacion}" oncomplete="#{capacitacionMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="editDlg" resizeable="false" moveable="true" autosized="true" width="550">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="EDITAR CAPACITACIÓN"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="edithidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="editDlg" attachTo="edithidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
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
                    <fieldset id="editFsCap">
                        <legend style="font-size: 11px;font-weight: bold;">Datos de la Capacitación</legend>
                        <h:panelGrid id="editPnlCap" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Nombre: " styleClass="Etiqueta1" />
                            <h:inputText id="editNombre" value="#{capacitacionMB.selectedCapacitacion.VNombre}" size="71" maxlength="50" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                            <h:outputText value="Descripción: " styleClass="Etiqueta1" />
                            <h:inputTextarea id="editDescripcion" value="#{capacitacionMB.selectedCapacitacion.VDescripcion}" cols="68" rows="3" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                            <h:outputText value="Tipo Capacitación: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="editTipoCapacitacion" value="#{capacitacionMB.selectedCapacitacion.NTipoCapacitacion}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaTipoCapacitacion}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Modalidad: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="editModalidad" value="#{capacitacionMB.selectedCapacitacion.NModalidad}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaModalidadCapacitacion}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Lugar: " styleClass="Etiqueta1"/>
                            <h:panelGroup>
                                <h:selectOneMenu value="#{capacitacionMB.selectedCapacitacion.NLugar}" style="float: left;font-size: 11px" >
                                    <f:selectItems value="#{listasSessionMB.listaLugarCapacitacion}"/>
                                </h:selectOneMenu>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{capacitacionMB.listarLugares}" reRender="lugares" oncomplete="#{rich:component('dlgLugar')}.show();">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" alt="añadir lugar" title="añadir lugar"/>
                                </a4j:commandLink>
                            </h:panelGroup>

                            <h:outputText value="Fecha y Hora: " styleClass="Etiqueta1"/>
                            <rich:calendar id="idFechaHora" value="#{capacitacionMB.selectedCapacitacion.DFechaHora}" popup="true" datePattern="dd/M/yyyy hh:mm a" showApplyButton="true" 
                                               jointPoint="top-left" direction="bottom-right" locale="es" inputSize="20" style="float: left;font-size: 11px"/>
                        </h:panelGrid>
                    </fieldset>
                    <rich:spacer/>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Datos de los Participantes</legend>
                        <h:panelGrid id="pnlEdit" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Tipo de Carga: " style="float: left;" styleClass="Etiqueta1" />
                            <h:selectOneRadio value="#{capacitacionMB.tipoCarga}" valueChangeListener="#{capacitacionMB.handleChangeTipoCarga}" style="float: left;font-size: 11px;">
                                <f:selectItem itemValue="1" itemLabel="Carga Individual"/>
                                <f:selectItem itemValue="2" itemLabel="Carga Masiva"/>
                                <a4j:support event="onchange" reRender="pnlEdit" ajaxSingle="true"/>
                            </h:selectOneRadio>
                            <rich:spacer width="10px;"/>
                            <h:panelGrid columns="2" width="92%" cellpadding="0" cellspacing="0" style="text-align: center;">
                                <h:outputText value="Nombres del Participante" style="font-size: 9px;"/>
                                <h:outputText value="Apellidos del Participante" style="font-size: 9px;"/>
                            </h:panelGrid>
                            <h:outputText value="Nombre Completo: " styleClass="Etiqueta1" />
                            <h:panelGroup>
                                <h:inputText id="editNombres" value="#{capacitacionMB.nombreParticipante}" size="28" maxlength="80" style="font-size: 11px;text-transform: uppercase;"/>
                                <h:inputText id="editApellido" value="#{capacitacionMB.apellidoParticipante}" size="28" maxlength="80" style="font-size: 11px;text-transform: uppercase;"/>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{capacitacionMB.registrarParticipante}" reRender="editNombres, editApellido, editCap, editDlgMsgs">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" alt="agregar participante" title="agregar participante"/>
                                </a4j:commandLink>
                            </h:panelGroup>
                            <h:outputLabel value="Archivo:" styleClass="Etiqueta1"/>
                            <rich:fileUpload id="ideditArchivo" acceptedTypes="xls" maxFilesQuantity="1" fileUploadListener="#{capacitacionMB.uploadFile}" noDuplicate="true" listHeight="58" listWidth="380" 
                                             addControlLabel="Agregar" clearControlLabel="Limpiar" stopControlLabel="Parar" stopEntryControlLabel="Parar" cancelEntryControlLabel="Cancelar"
                                             uploadControlLabel="Cargar" clearAllControlLabel="Limpiar" progressLabel="Cargando..." doneLabel="Finalizado" transferErrorLabel="Error al cargar archivo">
                                <f:facet name="label">
                                    <h:outputText value="{_KB}KB de {KB}KB cargados --- {mm}:{ss}" />
                                </f:facet>
                                <a4j:support event="onuploadcomplete" reRender="ideditArchivo, editCap, editDlgMsgs"/>
                            </rich:fileUpload>
                            <rich:spacer width="10px;"/>
                            <h:panelGroup>
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/documentExcelDisabled.png" alt="descargar plantilla" title="descargar plantilla"/>
                                <rich:spacer width="10px;"/>
                                <h:outputText value="Descargar Plantilla Carga Masiva" styleClass="Etiqueta1" />
                            </h:panelGroup>
                            <h:panelGroup>
                                <h:commandLink action="#{capacitacionMB.downloadTemplate}" target="_blank">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/documentExcel.png" alt="descargar plantilla" title="descargar plantilla"/>
                                </h:commandLink>
                                <rich:spacer width="10px;"/>
                                <h:outputText value="Descargar Plantilla Carga Masiva" styleClass="Etiqueta1" />
                            </h:panelGroup>
                        </h:panelGrid>
                        <h:panelGrid columns="1" width="100%">
                            <rich:dataTable id="editCap" var="row" value="#{capacitacionMB.listaParticipante}" rows="5" rowKeyVar="rowkey" style="font-size: 11px;" width="100%">
                                <f:facet name="header">
                                    <rich:datascroller for="editCap" maxPages="10"/>
                                </f:facet>
                                <rich:column>
                                    <f:facet name="header">
                                        <h:outputText value="NOMBRE PARTICIPANTE" style="font-size: 11px;"/>
                                    </f:facet>
                                    <h:outputText value="#{row.VNombreCompleto}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:column>
                                <rich:column style="text-align: center;width: 10px;">
                                    <f:facet name="header">
                                        <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                    </f:facet>
                                    <a4j:commandLink id="elink" actionListener="#{capacitacionMB.toEditarParticipante}" oncomplete="#{rich:component('editParticipanteDlg')}.show()" reRender="pnlEditParticipante">
                                        <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                        <f:setPropertyActionListener value="#{row}" target="#{capacitacionMB.selectedParticipante}"/>
                                        <h:graphicImage style="border: 0;" url="/pages/images/pencil.png" alt="editar participante" title="editar participante"/>
                                    </a4j:commandLink>
                                    <a4j:commandLink onclick="#{rich:component('delConfirmDlg')}.show();">
                                        <f:setPropertyActionListener value="#{row}" target="#{capacitacionMB.selectedParticipante}"/>
                                        <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar participante" title="eliminar participante"/>
                                    </a4j:commandLink>
                                </rich:column>
                            </rich:dataTable>
                        </h:panelGrid>
                    </fieldset>
                    <h:panelGrid columns="1">
                        <a4j:commandButton value="Grabar" onclick="#{rich:component('editConfirmDlg')}.show();" style="font-size: 11px;height: 2em"/>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="editParticipanteDlg" height="110" width="500" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="EDITAR PARTICIPANTE"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="editparhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="editParticipanteDlg" attachTo="editparhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="editParticipanteDlgForm">
                    <h:panelGrid id="pnlEditParticipante" columns="2" width="100%">
                        <rich:spacer width="10px;"/>
                        <h:panelGrid columns="2" width="92%" cellpadding="0" cellspacing="0" style="text-align: center;">
                            <h:outputText value="Nombres del Participante" style="font-size: 9px;"/>
                            <h:outputText value="Apellidos del Participante" style="font-size: 9px;"/>
                        </h:panelGrid>
                        <h:outputText value="Nombre Completo: " style="font-size: 11px;"/>
                        <h:panelGroup>
                            <h:inputText value="#{capacitacionMB.nombreParticipante}" size="32" maxlength="80" style="font-size: 11px;text-transform: uppercase;"/>
                            <h:inputText value="#{capacitacionMB.apellidoParticipante}" size="32" maxlength="80" style="font-size: 11px;text-transform: uppercase;"/>
                        </h:panelGroup>
                    </h:panelGrid>
                    <hr>
                    <a4j:commandButton id="btnSi" value="Grabar" reRender="editCap, editDlgMsgs" actionListener="#{capacitacionMB.editarParticipante}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="editParticipanteDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="Cancelar" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="editParticipanteDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="editConfirmDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="editConfirmDlgForm">
                    <h:outputText value="Se procederá a actualizar la capacitación." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="editDlgMsgs" actionListener="#{capacitacionMB.editCapacitacion}" oncomplete="#{capacitacionMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="editConfirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="editConfirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="delConfirmDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="delConfirmDlgForm">
                    <h:outputText value="Se procederá a eliminar al participante." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="editCap" actionListener="#{capacitacionMB.eliminarParticipante}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="delConfirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="delConfirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="dlgLugar" resizeable="false" moveable="true" autosized="true" width="750">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="AÑADIR LUGAR DE CAPACITACIÓN"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="eventohidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlgLugar" attachTo="eventohidelink" operation="hide" event="onclick"/>
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
                            <h:inputText id="txtlugar" size="95" maxlength="150" value="#{capacitacionMB.descripcionLugar}" style="font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{capacitacionMB.registrarLugar}" reRender="pnlSearch, pnlCap, editPnlCap, dlgLugarMsgs, pnlLugar">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir lugar" alt="añadir lugar"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        <rich:spacer/>
                        <rich:dataTable id="lugares" var="row" rowKeyVar="rowkey" value="#{capacitacionMB.listaLugares}" rows="10" style="width: 720px;">
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
                                    <f:setPropertyActionListener value="#{row}" target="#{capacitacionMB.selectedLugar}"/>
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
                    <a4j:commandButton id="btnSi" value="SI" reRender="pnlSearch, pnlCap, editPnlCap, dlgLugarMsgs, pnlLugar" actionListener="#{capacitacionMB.eliminarLugar}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelLugarDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelLugarDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
