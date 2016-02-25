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
        <title>SGSWEB - Gestión de Inspecciones Telefónicas</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
        <script type="text/javascript" src="../js/locales.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="telefonicaForm">
                <rich:panel header="GESTIÓN DE INSPECIONES TELEFÓNICAS" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid id="pnlSearch" columns="2" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Pregunta: " styleClass="Etiqueta1" />
                            <h:inputText id="searchPregunta" size="150" maxlength="150" value="#{telefonicaMB.searchPregunta}" style="font-size: 11px;text-transform: uppercase;"/>
                            
                            <h:outputText value="Lugar: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchLugar" value="#{telefonicaMB.searchLugar}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaLugarInspeccion}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Responsable: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchResponsable" value="#{telefonicaMB.searchResponsable}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaResponsableInspeccion}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Cargo: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchCargo" value="#{telefonicaMB.searchCargo}" style="float: left;font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaCargoInspeccion}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Rango Fechas: " styleClass="Etiqueta1" />
                            <h:panelGrid columns="5" cellpadding="0" cellspacing="0">
                                <rich:calendar id="searchFechaInicio" value="#{telefonicaMB.searchFechaInicio}" popup="true" datePattern="dd/MM/yyyy" 
                                               jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                                <rich:spacer width="10"/>
                                <h:outputText value="al" style="font-weight: bold;" styleClass="Etiqueta1" />
                                <rich:spacer width="10"/>
                                <rich:calendar id="searchFechaFin" value="#{telefonicaMB.searchFechaFin}" popup="true" datePattern="dd/MM/yyyy" 
                                               jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                            </h:panelGrid>
                            
                        </h:panelGrid>
                    </fieldset>
                    
                    <h:panelGrid columns="1">
                        <h:panelGroup>
                            <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{telefonicaMB.buscarInspeccionTelefonica}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                            <a4j:commandButton id="btnNuevo" value="Nuevo" actionListener="#{telefonicaMB.toRegistrar}" oncomplete="#{rich:component('dlg')}.show();" 
                                               reRender="groupIndividual, groupMasivo, groupButton, pnlTipoCarga, pnlPreguntas" immediate="true" style="font-size: 11px;height: 2em"/>
                        </h:panelGroup>
                    </h:panelGrid>
                    
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Inspecciones Telefónicas</legend>
                        <rich:dataTable id="tbl" var="row" value="#{telefonicaMB.listaInsTelefonica}" rows="10" rowKeyVar="rowkey" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" />
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="LUGAR" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.segCabLugar.NCodLugar}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterLugarInspeccion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="RESPONSABLE" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.segCabResponsable.NCodResponsable}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterResponsableInspeccion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="CARGO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.segCabCargo.NCodCargo}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterCargoInspeccion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="TELÉFONO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VTelefono}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ESTADO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NEstado}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterEstadoInspeccion"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="FECHA CREACIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.DFecCreacion}" style="font-size: 11px;font-weight: normal;">
                                    <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;width: 20;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="vlink" actionListener="#{telefonicaMB.toVer}" immediate="true" oncomplete="#{rich:component('verDlg')}.show()" reRender="verPanel, lstPreguntas">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{telefonicaMB.selectedInsTelefonica}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/magnifier.png" alt="ver inspección" title="ver inspección"/>
                                </a4j:commandLink>
                                <rich:spacer/>
                                <a4j:commandLink id="slink" actionListener="#{telefonicaMB.toSeguimiento}" immediate="true" oncomplete="#{rich:component('segDlg')}.show()" reRender="showpnl,pgrid">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{telefonicaMB.selectedInsTelefonica}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/date_go.png" alt="seguimiento de inspección" title="seguimiento de inspección" rendered="#{row.NSeguimiento != 0}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/date_magnify.png" alt="ver seguimiento" title="ver seguimiento" rendered="#{row.NSeguimiento == 0}"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="true" autosized="true" width="770" height="535">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="NUEVA INSPECCIÓN TELEFÓNICA"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="newhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlg" attachTo="newhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <div style="overflow-y: auto;height:500px;">
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
                    <h:panelGrid id="pnlPreguntas" columns="1">
                        <h:panelGroup>
                            <h:outputText value="Pregunta : " style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:inputText id="preg" size="115" maxlength="150" value="#{telefonicaMB.pregunta}" style="font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{telefonicaMB.registrarPregunta}" reRender="preg, preguntas, dlgMsgs">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" alt="añadir pregunta"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        <rich:spacer/>
                        <rich:dataTable id="preguntas" var="row" value="#{telefonicaMB.listaPreguntas}" rows="5" rowKeyVar="rowKey" style="width: 720px;">
                            <f:facet name="header">
                                <rich:datascroller for="preguntas"/>
                            </f:facet>
                            <rich:column style="text-align: center;width: 5px">
                                <f:facet name="header">
                                    <h:outputText value="SEL" style="font-size: 11px;"/>
                                </f:facet>
                                <h:selectBooleanCheckbox value="#{telefonicaMB.listaSelectedRowKeys[rowKey]}"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="PREGUNTA" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;width: 10px">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink onclick="#{rich:component('delConfirmDlg')}.show()">
                                    <f:setPropertyActionListener value="#{row}" target="#{telefonicaMB.selectedPregunta}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar pregunta" title="eliminar pregunta"/>
                                </a4j:commandLink>
                                <a4j:commandLink id="elink" oncomplete="#{rich:component('editPreguntaDlg')}.show()" reRender="pnlEditPregunta">
                                    <f:setPropertyActionListener value="#{row}" target="#{telefonicaMB.selectedPregunta}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/pencil.png" alt="editar pregunta" title="editar pregunta"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                    <h:panelGrid id="pnlTipoCarga" columns="1">
                        <h:selectOneRadio id="tipoCarga" value="#{telefonicaMB.tipoCarga}" valueChangeListener="#{telefonicaMB.handleChangeTipoCarga}" styleClass="Etiqueta1">
                            <f:selectItem itemValue="1" itemLabel="Carga Individual"/>
                            <f:selectItem itemValue="2" itemLabel="Carga Masiva"/>
                            <a4j:support event="onchange" reRender="groupIndividual, groupMasivo, groupButton"/>
                        </h:selectOneRadio>
                    </h:panelGrid>
                    <h:panelGroup id="groupIndividual">
                        <h:panelGrid id="idGrid" columns="4" width="100%" rendered="#{telefonicaMB.cargaIndividual}">
                            <h:outputText value="Lugar: " style="font-weight: bold;" styleClass="Etiqueta1" />
                            <h:panelGroup id="groupLugar">
                                <h:selectOneMenu id="lugar"  value="#{telefonicaMB.lugar}" style="float: left;font-size: 11px">
                                    <f:selectItems value="#{listasSessionMB.listaLugarInspeccion}"/>
                                </h:selectOneMenu>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{telefonicaMB.listarLugares}" reRender="pnlLugar" oncomplete="#{rich:component('dlgLugar')}.show();">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir lugar" alt="añadir lugar"/>
                                </a4j:commandLink>
                            </h:panelGroup>

                            <h:outputLabel value="Teléfono: " style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:inputText value="#{telefonicaMB.telefono}" size="15" maxlength="15" onkeypress="return valida(this,'tlf');" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                            <h:outputText value="Responsable: " style="font-weight: bold;" styleClass="Etiqueta1" />
                            <h:panelGroup id="groupResponsable">
                                <h:selectOneMenu id="responsable" value="#{telefonicaMB.responsable}" style="float: left;font-size: 11px">
                                    <f:selectItems value="#{listasSessionMB.listaResponsableInspeccion}"/>
                                </h:selectOneMenu>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{telefonicaMB.listarResponsables}" reRender="pnlResponsable" oncomplete="#{rich:component('dlgResponsable')}.show();">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir responsable" alt="añadir responsable"/>
                                </a4j:commandLink>
                            </h:panelGroup>

                            <h:outputText value="Cargo: " style="font-weight: bold;" styleClass="Etiqueta1" />
                            <h:panelGroup id="groupCargo">
                                <h:selectOneMenu id="cargo" value="#{telefonicaMB.cargo}" style="float: left;font-size: 11px">
                                    <f:selectItems value="#{listasSessionMB.listaCargoInspeccion}"/>
                                </h:selectOneMenu>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{telefonicaMB.listarCargos}" reRender="pnlCargo" oncomplete="#{rich:component('dlgCargo')}.show();">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" title="añadir cargo"/>
                                </a4j:commandLink>
                            </h:panelGroup>
                            <rich:spacer/>
                        </h:panelGrid>
                    </h:panelGroup>
                    <h:panelGroup id="groupMasivo">
                        <h:panelGrid id="idGridFile" columns="2" rendered="#{telefonicaMB.cargaMasiva}">
                            <h:outputLabel value="Archivo:" style="font-weight: bold;" styleClass="Etiqueta1"/>
                            <h:panelGrid columns="1">
                                <rich:fileUpload acceptedTypes="xls" maxFilesQuantity="1" fileUploadListener="#{telefonicaMB.uploadFile}" noDuplicate="true" listHeight="58" listWidth="670" 
                                                 addControlLabel="Agregar" clearControlLabel="Limpiar" stopControlLabel="Parar" stopEntryControlLabel="Parar" cancelEntryControlLabel="Cancelar"
                                                 uploadControlLabel="Cargar" clearAllControlLabel="Limpiar" progressLabel="Cargando..." doneLabel="Finalizado" transferErrorLabel="Error al cargar archivo">
                                    <f:facet name="label">
                                        <h:outputText value="{_KB}KB de {KB}KB cargados --- {mm}:{ss}" />
                                    </f:facet>
                                    <a4j:support event="onuploadcomplete" reRender="info, idSheet"/>
                                </rich:fileUpload>
                                <h:panelGroup id="info">
                                    <a4j:commandLink value="Download" rendered="#{telefonicaMB.file != null}" >
                                        <h:outputText value="#{telefonicaMB.file.name}" styleClass="Etiqueta1"/>
                                        <h:graphicImage value="pdficon.gif" />
                                    </a4j:commandLink>
                                </h:panelGroup>
                            </h:panelGrid>
                            <rich:spacer width="10px;"/>
                            <h:panelGroup>
                                <h:commandLink action="#{telefonicaMB.downloadTemplate}" target="_blank">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/documentExcel.png" alt="descargar plantilla" title="descargar plantilla"/>
                                </h:commandLink>
                                <rich:spacer width="10px;"/>
                                <h:outputText value="Descargar Plantilla Carga Masiva" styleClass="Etiqueta1" />
                            </h:panelGroup>
                        </h:panelGrid>
                        <rich:dataTable id="idSheet" value="#{telefonicaMB.listaInspeccion}" var="row" rows="10" rendered="#{telefonicaMB.cargaMasiva}" width="100%">
                            <f:facet name="caption">
                                <h:outputText value="LISTA DE INSPECCIONES" styleClass="Etiqueta1" />
                            </f:facet>
                            <f:facet name="header">
                                <rich:datascroller for="idSheet" />
                            </f:facet>
                            <rich:column style="width:125px">
                                <f:facet name="header">
                                    <h:outputText value="LUGAR" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.lugar}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="width:240px">
                                <f:facet name="header">
                                    <h:outputText value="RESPONSABLE" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.responsable}"  style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="width:125px">
                                <f:facet name="header">
                                    <h:outputText value="CARGO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.cargo}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="width:99px">
                                <f:facet name="header">
                                    <h:outputText value="TELEFONO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.telefono}" />
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGroup>
                    <rich:spacer height="20"/>
                    <h:panelGroup id="groupButton">
                        <a4j:commandButton id="btnSave" value="Grabar" onclick="#{rich:component('confirmDlg')}.show();" style="font-size: 11px;height: 2em" rendered="#{telefonicaMB.cargaIndividual}"/>
                        <a4j:commandButton id="btnSaveFile" value="Grabar" reRender="pnlSearch, groupIndividual" onclick="#{rich:component('confirmFileDlg')}.show();" style="font-size: 11px;height: 2em" rendered="#{telefonicaMB.cargaMasiva}"/>
                        <a4j:commandButton id="btnCancel" value="Cancelar" onclick="#{rich:component('dlg')}.hide();" style="font-size: 11px;height: 2em"/>
                    </h:panelGroup>
                </h:form>
                </div>
            </rich:modalPanel>
            <rich:modalPanel id="editPreguntaDlg" height="100" width="600" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="EDITAR PREGUNTA"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="prerhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="editPreguntaDlg" attachTo="prerhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="editPreguntaDlgForm">
                    <h:panelGrid id="pnlEditPregunta" columns="2" width="100%">
                        <h:outputText value="Pregunta: " style="font-weight: bold;" styleClass="Etiqueta1"/>
                        <h:inputText id="epreg" size="95" maxlength="150" value="#{telefonicaMB.selectedPregunta.VDescripcion}" style="font-size: 11px;text-transform: uppercase;"/>
                    </h:panelGrid>
                    <hr>
                    <a4j:commandButton id="btnSi" value="Grabar" reRender="epreg, preguntas, dlgMsgs" actionListener="#{telefonicaMB.editarPregunta}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="editPreguntaDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="Cancelar" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="editPreguntaDlg" attachTo="btnNo" operation="hide" event="onclick"/>
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
                    <h:outputText value="Se procederá a eliminar la pregunta." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="preguntas" actionListener="#{telefonicaMB.eliminarPregunta}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="delConfirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="delConfirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            
            <rich:modalPanel id="verDlg" resizeable="false" moveable="true" autosized="false" width="750" height="370">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="INSPECCIÓN TELEFÓNICA"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="verhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="verDlg" attachTo="verhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <div style="overflow-y: auto;height:345px;">
                <h:form id="verDlgForm">
                    <h:panelGrid id="verPanel" columns="4" width="100%">
                        <h:outputText value="Lugar: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                        <h:outputText value="#{telefonicaMB.selectedInsTelefonica.segCabLugar.NCodLugar}" style="font-size: 11px;font-weight: normal;">
                            <f:converter converterId="converterLugarInspeccion"/>
                        </h:outputText>

                        <h:outputLabel value="Teléfono: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                        <h:outputText value="#{telefonicaMB.selectedInsTelefonica.VTelefono}" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                        <h:outputText value="Responsable: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                        <h:outputText value="#{telefonicaMB.selectedInsTelefonica.segCabResponsable.NCodResponsable}" style="font-size: 11px;font-weight: normal;">
                            <f:converter converterId="converterResponsableInspeccion"/>
                        </h:outputText>

                        <h:outputText value="Cargo: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                        <h:outputText value="#{telefonicaMB.selectedInsTelefonica.segCabCargo.NCodCargo}" style="font-size: 11px;font-weight: normal;">
                            <f:converter converterId="converterCargoInspeccion"/>
                        </h:outputText>
                        <rich:spacer/>
                    </h:panelGrid>
                    <rich:dataTable id="lstPreguntas" var="row" value="#{telefonicaMB.selectedInsTelefonica.segDetPreguntas}" 
                                 rows="10" rowKeyVar="index" style="width: 720px;">
                        <f:facet name="header">
                            <rich:datascroller for="lstPreguntas" />
                        </f:facet>
                        <rich:column style="text-align: center;width: 5%">
                            <f:facet name="header">
                                <h:outputText value="ITEM" style="font-size: 11px;"/>
                            </f:facet>
                            <h:outputText value="#{index+1}" style="font-size: 11px;font-weight: normal;"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                                <h:outputText value="PREGUNTA" style="font-size: 11px;"/>
                            </f:facet>
                            <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                        </rich:column>
                    </rich:dataTable>
                </h:form>
                </div>
            </rich:modalPanel>
                    
                    <rich:modalPanel id="segDlg" resizeable="false" moveable="true" autosized="true" width="770" height="535">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="SEGUIMIENTO DE INSPECCIÓN TELEFÓNICA"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="seghidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="segDlg" attachTo="seghidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <div style="overflow-y: auto;height:500px;">
                <h:form id="segDlgForm">
                    <h:panelGrid id="showpnl" columns="4" width="100%">
                        <h:outputText value="Lugar: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                        <h:outputText value="#{telefonicaMB.selectedInsTelefonica.segCabLugar.NCodLugar}" style="font-size: 11px;font-weight: normal;">
                            <f:converter converterId="converterLugarInspeccion"/>
                        </h:outputText>

                        <h:outputLabel value="Teléfono: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                        <h:outputText value="#{telefonicaMB.selectedInsTelefonica.VTelefono}" style="float: left;font-size: 11px;text-transform: uppercase;"/>

                        <h:outputText value="Responsable: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                        <h:outputText value="#{telefonicaMB.selectedInsTelefonica.segCabResponsable.NCodResponsable}" style="font-size: 11px;font-weight: normal;">
                            <f:converter converterId="converterResponsableInspeccion"/>
                        </h:outputText>

                        <h:outputText value="Cargo: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                        <h:outputText value="#{telefonicaMB.selectedInsTelefonica.segCabCargo.NCodCargo}" style="font-size: 11px;font-weight: normal;">
                            <f:converter converterId="converterCargoInspeccion"/>
                        </h:outputText>
                        <rich:spacer/>
                    </h:panelGrid>
                    <h:panelGrid id="pgrid" columns="1" width="100%">
                        <rich:dataGrid id="dgrid" columns="1" value="#{telefonicaMB.selectedInsTelefonica.segDetPreguntas}" var="row" width="100%">
                            <rich:column>
                                <rich:panel header="#{row.VDescripcion}" style="font-size: 11px;font-weight: bold;">
                                    <rich:dataTable var="r" value="#{row.segDetRespuestas}" width="100%" rendered="#{not empty row.segDetRespuestas}">
                                        <rich:column>
                                            <h:panelGroup>
                                                <h:outputText value="#{r.DFecCreacion}" style="font-size: 11px;font-weight: bold;">
                                                    <f:convertDateTime type="date" locale="Locale.SPAIN" dateStyle="SHORT" pattern="dd/MM/yyyy hh:mm:ss"/>
                                                </h:outputText>
                                                <rich:spacer width="5"/>
                                                <h:outputText value="#{r.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                                            </h:panelGroup>
                                        </rich:column>
                                    </rich:dataTable>
                                    <rich:spacer height="5" width="5" rendered="#{telefonicaMB.selectedInsTelefonica.NSeguimiento != 0}"/>
                                    <h:inputTextarea value="#{row.VRespuesta}" rows="5" cols="130" style="font-size: 11px;font-weight: normal;text-transform: uppercase;" rendered="#{telefonicaMB.selectedInsTelefonica.NSeguimiento != 0}"/>
                                </rich:panel>
                            </rich:column>
                        </rich:dataGrid>
                        <h:panelGrid columns="2" rendered="#{telefonicaMB.selectedInsTelefonica.NSeguimiento != 0}">
                            <h:outputText value="Necesita Seguimiento: " styleClass="Etiqueta1" style="font-weight: bold;"/>
                            <h:selectOneRadio id="sorFollow" value="#{telefonicaMB.seguimiento}" valueChangeListener="#{telefonicaMB.handleChangeReqMonitor}" style="font-size: 11px">
                                <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                                <a4j:support event="onchange" reRender="pgrid"/>
                            </h:selectOneRadio>
                        </h:panelGrid>
                        <h:panelGroup rendered="#{telefonicaMB.selectedInsTelefonica.NSeguimiento != 0}">
                            <a4j:commandButton value="Grabar" oncomplete="#{rich:component('segConfirmDlg')}.show();" rendered="#{!telefonicaMB.cerrar}"/>
                            <a4j:commandButton value="Grabar" oncomplete="#{rich:component('segCloseConfirmDlg')}.show();" rendered="#{telefonicaMB.cerrar}"/>
                            <rich:spacer/>
                            <a4j:commandButton value="Cancelar" oncomplete="#{rich:component('segDlg')}.hide();"/>
                        </h:panelGroup>
                    </h:panelGrid>
                </h:form>
                </div>
            </rich:modalPanel>
            <rich:modalPanel id="segConfirmDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="segConfirmDlgForm">
                    <h:outputText value="Se procederá a registrar las respuestas." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="tbl" actionListener="#{telefonicaMB.registrarSeguimiento}" oncomplete="#{telefonicaMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="segConfirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="segConfirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="segCloseConfirmDlg" height="100" width="330" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="segCloseConfirmDlgForm">
                    <h:outputText value="Se procederá a registrar las respuestas y cerrar la inspección." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="tbl" actionListener="#{telefonicaMB.registrarCerrarSeguimiento}" oncomplete="#{telefonicaMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="segCloseConfirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="segCloseConfirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
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
                                <h:outputText value="Lugar : " style="font-weight: bold;" styleClass="Etiqueta1"/>
                                <h:inputText id="txtlugar" size="123" maxlength="150" value="#{telefonicaMB.descripcionLugar}" style="font-size: 11px;text-transform: uppercase;"/>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{telefonicaMB.registrarLugar}" reRender="txtlugar, lugares, dlgLugarMsgs, groupLugar, pnlSearch">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir lugar" alt="añadir lugar"/>
                                </a4j:commandLink>
                            </h:panelGroup>
                            <rich:spacer/>
                            <rich:dataTable id="lugares" var="row" rowKeyVar="rowkey" value="#{telefonicaMB.listaLugares}" rows="10" style="width: 720px;">
                                <f:facet name="header">
                                    <rich:datascroller for="lugares" />
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
                                        <f:setPropertyActionListener value="#{row}" target="#{telefonicaMB.selectedLugar}"/>
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
                        <a4j:commandButton id="btnSi" value="SI" reRender="lugares, dlgLugarMsgs, groupLugar, pnlSearch" actionListener="#{telefonicaMB.eliminarLugar}" style="font-size: 11px;height: 2em">
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
                            <h:panelGrid columns="2">
                                <rich:spacer width="10px;"/>
                                <h:panelGrid columns="2" width="95%" cellpadding="0" cellspacing="0" style="text-align: center;">
                                    <h:outputText value="Nombres del Responsable" style="font-size: 9px;"/>
                                    <h:outputText value="Apellidos del Responsable" style="font-size: 9px;"/>
                                </h:panelGrid>
                                <h:outputText value="Responsable : " style="font-weight: bold;" styleClass="Etiqueta1"/>
                                <h:panelGroup>
                                    <h:inputText id="idNombres" value="#{telefonicaMB.nombreResponsable}" size="54" maxlength="80" style="font-size: 11px;text-transform: uppercase;"/>
                                    <h:inputText id="idApellido" value="#{telefonicaMB.apellidoResponsable}" size="54" maxlength="80" style="font-size: 11px;text-transform: uppercase;"/>
                                    <rich:spacer width="10px;"/>
                                    <a4j:commandLink actionListener="#{telefonicaMB.registrarResponsable}" 
                                                     reRender="pnlResponsable, dlgResponsableMsgs, groupResponsable, pnlSearch">
                                        <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir responsable" alt="añadir responsable"/>
                                    </a4j:commandLink>
                                </h:panelGroup>
                            </h:panelGrid>
                            <rich:spacer/>
                            <rich:dataTable id="responsables" var="row" rowKeyVar="rowkey" rows="10" value="#{telefonicaMB.listaResponsables}" style="width: 720px;">
                                <f:facet name="header">
                                    <rich:datascroller for="responsables" />
                                </f:facet>
                                <rich:column>
                                    <f:facet name="header">
                                        <h:outputText value="RESPONSABLE" style="font-size: 11px;"/>
                                    </f:facet>
                                    <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                                </rich:column>
                                <rich:column style="text-align: center;width:10px;">
                                    <f:facet name="header">
                                        <h:outputText value="ELIMINAR" style="font-size: 11px;"/>
                                    </f:facet>
                                    <a4j:commandLink onclick="#{rich:component('confirmDelResponsableDlg')}.show()">
                                        <f:setPropertyActionListener value="#{row}" target="#{telefonicaMB.selectedResponsable}"/>
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
                        <a4j:commandButton id="btnSi" value="SI" reRender="pnlResponsable, dlgResponsableMsgs, groupResponsable, pnlSearch" actionListener="#{telefonicaMB.eliminarResponsable}" style="font-size: 11px;height: 2em">
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
                                <h:outputText value="Cargo : " style="font-weight: bold;" styleClass="Etiqueta1"/>
                                <h:inputText id="txtcargo" size="123" maxlength="150" value="#{telefonicaMB.descripcionCargo}" style="font-size: 11px;text-transform: uppercase;"/>
                                <rich:spacer width="10px;"/>
                                <a4j:commandLink actionListener="#{telefonicaMB.registrarCargo}" reRender="txtcargo, cargos, dlgCargoMsgs, groupCargo, pnlSearch">
                                    <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir cargo" alt="añadir cargo"/>
                                </a4j:commandLink>
                            </h:panelGroup>
                            <rich:spacer/>
                            <rich:dataTable id="cargos" var="row" rowKeyVar="rowkey" value="#{telefonicaMB.listaCargos}" rows="10" style="width: 720px;">
                                <f:facet name="header">
                                    <rich:datascroller for="cargos" />
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
                                        <f:setPropertyActionListener value="#{row}" target="#{telefonicaMB.selectedCargo}"/>
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
                        <a4j:commandButton id="btnSi" value="SI" reRender="cargos, dlgCargoMsgs, groupCargo, pnlSearch" actionListener="#{telefonicaMB.eliminarCargo}" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmDelCargoDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                        <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmDelCargoDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                    </h:form>
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
                        <a4j:commandButton id="btnSi" value="SI" reRender="dlgMsgs" actionListener="#{telefonicaMB.registrarInspeccion}" oncomplete="#{telefonicaMB.action}" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                        <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                    </h:form>
                </rich:modalPanel>
            
                <rich:modalPanel id="confirmFileDlg" height="100" keepVisualState="true" resizeable="false">
                    <f:facet name="header">
                        <h:panelGroup>
                            <h:outputText value="Confirmación"></h:outputText>
                        </h:panelGroup>
                    </f:facet>
                    <h:form id="confirmFileDlgForm">
                        <h:outputText value="Se procederá a registrar las inspecciones cargadas." style="font-size: 11px;"/><br>
                        <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                        <hr>
                        <a4j:commandButton id="btnSi" value="SI" reRender="groupCargo, groupResponsable, groupLugar, searchLugar, searchResponsable, searchCargo, dlgMsgs" actionListener="#{telefonicaMB.registrarInspeccionMasiva}" oncomplete="#{telefonicaMB.action}" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmFileDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                        <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmFileDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                    </h:form>
                </rich:modalPanel>
                <rich:spacer/>
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
