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
        <title>SGSWEB - Gestión de Documentos</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
        <script type="text/javascript" src="../js/locales.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="documentoForm">
                <rich:panel id="pnlDocumento" header="GESTION DE DOCUMENTOS" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid columns="2" columnClasses="columnLbl1, columnTxt1">
                            <%--
                            <h:outputText value="Empresa: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{documentoMB.searchEmpresa}" style="float: left;font-size: 11px" > 
                                <f:selectItems value="#{listasSessionMB.listaEmpresaActiva}"/>
                            </h:selectOneMenu>
                            --%>
                            
                            <h:outputText value="Procedencia: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{documentoMB.searchProcedencia}" style="float: left;font-size: 11px" >
                                <f:selectItems value="#{listasSessionMB.listaProcedencia}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Tipo de Documento: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{documentoMB.searchTipoDocumento}" style="float: left;font-size: 11px" >
                                <f:selectItems value="#{listasSessionMB.listaTipoDocumento}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Rango Fechas: " styleClass="Etiqueta1" />
                            <h:panelGrid columns="5" cellpadding="0" cellspacing="0">
                                <rich:calendar id="searchFechaInicio" value="#{documentoMB.searchFechaInicio}" popup="true" datePattern="dd/MM/yyyy" 
                                               jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                                <rich:spacer width="10"/>
                                <h:outputText value="al" style="font-weight: bold;" styleClass="Etiqueta1" />
                                <rich:spacer width="10"/>
                                <rich:calendar id="searchFechaFin" value="#{documentoMB.searchFechaFin}" popup="true" datePattern="dd/MM/yyyy" 
                                               jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                            </h:panelGrid>
                        </h:panelGrid>
                    </fieldset>
                    
                    <h:panelGrid columns="1">
                        <h:panelGroup>
                            <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{documentoMB.buscarDocumento}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                            <a4j:commandButton id="btnNuevo" value="Nuevo" actionListener="#{documentoMB.toRegistrar}" reRender="dlgMsgs, pnlNew" style="font-size: 11px;height: 2em">
                                <rich:componentControl for="dlg" attachTo="btnNuevo" operation="show" event="onclick"/>
                            </a4j:commandButton>
                        </h:panelGroup>
                    </h:panelGrid>
                    
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Documentos</legend>
                        <rich:dataTable id="tbl" var="row" value="#{documentoMB.listaDocumento}" rows="10" rowKeyVar="rowKey" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" maxPages="10"/>
                            </f:facet>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="PROCEDENCIA" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NProcedencia}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterProcedencia"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="TIPO DOCUMENTO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NTipoDocumento}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterTipoDocumento"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="DESCRIPCIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="VERSIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NVersion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="NOMBRE" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VNombre}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="FECHA EMISIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.DFecEmision}" style="font-size: 11px;font-weight: normal;">
                                    <f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;width: 10%;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="elink" actionListener="#{documentoMB.toEdit}" oncomplete="#{rich:component('editDlg')}.show()" reRender="editDlgMsgs, pnlEdit">
                                    <f:setPropertyActionListener value="#{row}" target="#{documentoMB.selectedDocument}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/pencil.png" alt="editar datos del documento" title="editar datos del documento"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <h:commandLink id="dlink" action="#{documentoMB.handleFileDownload2}" target="_blank">
                                    <f:param value="#{rowKey}" name="rowKey"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{documentoMB.selectedDocument}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/download.png" height="16" width="16" alt="descargar documento" title="descargar documento"/>
                                </h:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="vlink" actionListener="#{documentoMB.toRegistrar}" oncomplete="#{rich:component('versionDlg')}.show();">
                                    <f:setPropertyActionListener value="#{row}" target="#{documentoMB.selectedDocument}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/document-move.png" height="16" width="16" alt="versionar documento" title="versionar documento"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="hlink" actionListener="#{documentoMB.listarHistorico}" oncomplete="#{rich:component('histDlg')}.show();" reRender="tblHist">
                                    <f:param value="#{rowKey}" name="rowKey"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/document-copies.png" height="16" width="16" alt="historial del documento" title="historial del documento"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="deslink">
                                    <f:setPropertyActionListener value="#{row}" target="#{documentoMB.selectedDocument}"/>
                                    <rich:componentControl for="desdlg" attachTo="deslink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/page_delete.png" alt="desactivar documento" title="desactivar documento"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            
            <rich:modalPanel id="dlg" resizeable="false" moveable="true" autosized="true" width="650">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="NUEVO DOCUMENTO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="newhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlg" attachTo="newhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgForm" prependId="false" enctype="multipart/form-data">
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
                    <h:panelGrid id="pnlNew" columns="2">                        
                        <h:outputText value="Procedencia: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{documentoMB.procedencia}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaProcedencia}"/>
                        </h:selectOneMenu>
                        
                        <h:outputText value="Tipo de Documento: " styleClass="Etiqueta1" />
                        <h:panelGroup>
                            <h:selectOneMenu value="#{documentoMB.tipoDocumento}" style="float: left;font-size: 11px" >
                                <f:selectItems value="#{listasSessionMB.listaTipoDocumento}"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{documentoMB.listarTipoDocumentos}" reRender="tipodocumentos" oncomplete="#{rich:component('dlgTipoDocumento')}.show();">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" alt="añadir tipo de documento" title="añadir tipo de documento"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        
                        <h:outputLabel value="Descripción:" styleClass="Etiqueta1"/>
                        <h:inputText value="#{documentoMB.descripcion}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Fecha de Emisión:" styleClass="Etiqueta1"/>
                        <rich:calendar id="idFechaHora" value="#{documentoMB.fechaEmision}" popup="true" datePattern="dd/M/yyyy hh:mm a" showApplyButton="true" 
                                       jointPoint="top-left" direction="bottom-right" locale="es" inputSize="20" style="float: left;font-size: 11px"/>
                        
                        <h:outputLabel value="Archivo:" styleClass="Etiqueta1"/>
                        <h:panelGrid columns="1">
                            <rich:fileUpload acceptedTypes="doc,docx,pdf,txt,xls,xlsx,ppt,pptx,jpg,jpge,png,gif" maxFilesQuantity="1" fileUploadListener="#{documentoMB.handleFileUpload}" noDuplicate="true" listHeight="60" listWidth="510"
                                             addControlLabel="Agregar" clearControlLabel="Limpiar" stopControlLabel="Parar" stopEntryControlLabel="Parar" cancelEntryControlLabel="Cancelar" sizeErrorLabel="El archivo es mayor al tamaño máximo permitido."
                                             uploadControlLabel="Cargar" clearAllControlLabel="Limpiar" progressLabel="Cargando..." doneLabel="Finalizado" transferErrorLabel="Error al cargar archivo." >
                                <f:facet name="label">
                                    <h:outputText value="{_KB}KB de {KB}KB cargados --- {mm}:{ss}" />
                                </f:facet>
                            </rich:fileUpload>
                            <h:outputText value="- Los tipos de archivo aceptados son doc, docx, xls, xlsx, ppt, pptx, pdf, txt, jpg, png, gif." style="font-size: 9px;color: red;"/>
                            <h:outputText value="- El archivo debe tener como máximo 1024KB." style="font-size: 9px;color: red;"/>
                        </h:panelGrid>
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton value="Grabar" onclick="#{rich:component('confirmDlg')}.show()" style="font-size: 11px;height: 2em" />
                        <a4j:commandButton value="Cancelar" onclick="#{rich:component('dlg')}.hide()" style="font-size: 11px;height: 2em"/>
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
                    <h:outputText value="Se procederá a registrar el documento." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="dlgMsgs" actionListener="#{documentoMB.registrarDocumento}" oncomplete="#{documentoMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            
            <rich:modalPanel id="editDlg" resizeable="false" moveable="true" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="ACTUALIZAR DOCUMENTO"></h:outputText>
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
                    <h:panelGrid id="pnlEdit" columns="2">                        
                        <h:outputText value="Procedencia: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{documentoMB.selectedDocument.NProcedencia}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaProcedencia}"/>
                        </h:selectOneMenu>
                        
                        <h:outputText value="Tipo de Documento: " styleClass="Etiqueta1" />
                        <h:panelGroup>
                            <h:selectOneMenu value="#{documentoMB.selectedDocument.NTipoDocumento}" style="float: left;font-size: 11px" >
                                <f:selectItems value="#{listasSessionMB.listaTipoDocumento}"/>
                            </h:selectOneMenu>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{documentoMB.listarTipoDocumentos}" reRender="tipodocumentos" oncomplete="#{rich:component('dlgTipoDocumento')}.show();">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/textfield-add.png" alt="añadir tipo de documento" title="añadir tipo de documento"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        
                        <h:outputLabel value="Descripción:" styleClass="Etiqueta1"/>
                        <h:inputText value="#{documentoMB.selectedDocument.VDescripcion}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Fecha de Emisión:" styleClass="Etiqueta1"/>
                        <rich:calendar id="idFechaHora" value="#{documentoMB.selectedDocument.DFecEmision}" popup="true" datePattern="dd/M/yyyy hh:mm a" showApplyButton="true" 
                                       jointPoint="top-left" direction="bottom-right" locale="es" inputSize="20" style="float: left;font-size: 11px"/>
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton value="Grabar" onclick="#{rich:component('confirmEditDlg')}.show();" style="font-size: 11px;height: 2em" />
                        <a4j:commandButton value="Cancelar" onclick="#{rich:component('editDlg')}.hide();" style="font-size: 11px;height: 2em"/>
                    </h:panelGroup>
                </h:form>  
            </rich:modalPanel>
            <rich:modalPanel id="confirmEditDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEditDlgForm">
                    <h:outputText value="Se procederá con la actualización del documento." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="editDlgMsgs" actionListener="#{documentoMB.editarDocumento}" oncomplete="#{documentoMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
                    
            <rich:modalPanel id="dlgTipoDocumento" resizeable="false" moveable="true" autosized="true" width="750">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="AÑADIR TIPO DE DOCUMENTO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="eventohidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlgTipoDocumento" attachTo="eventohidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgTipoDocumentoForm">
                    <rich:messages id="dlgTipoDocumentoMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <h:panelGrid id="pnlTipoDocumento" columns="1">
                        <h:panelGroup>
                            <h:outputText value="Tipo de Documento : " styleClass="Etiqueta1"/>
                            <h:inputText id="txttipodocumento" size="95" maxlength="150" value="#{documentoMB.descripcionTipoDocumento}" style="font-size: 11px;text-transform: uppercase;"/>
                            <rich:spacer width="10px;"/>
                            <a4j:commandLink actionListener="#{documentoMB.registrarTipoDocumento}" reRender="pnlSearch, pnlNew, pnlEdit, dlgTipoDocumentoMsgs, pnlTipoDocumento">
                                <h:graphicImage style="border: 0;vertical-align: middle;" value="/pages/images/add.png" title="añadir tipo de documento" alt="añadir tipo de documento"/>
                            </a4j:commandLink>
                        </h:panelGroup>
                        <rich:spacer/>
                        <rich:dataTable id="tipodocumentos" var="row" rowKeyVar="rowkey" value="#{documentoMB.listaTipoDocumentos}" rows="10" style="width: 720px;">
                            <f:facet name="header">
                                <rich:datascroller for="tipodocumentos" maxPages="10"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="TIPO DE DOCUMENTO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;width:10px;">
                                <f:facet name="header">
                                    <h:outputText value="ELIMINAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink onclick="#{rich:component('confirmDelTipoDocumentoDlg')}.show()">
                                    <f:setPropertyActionListener value="#{row}" target="#{documentoMB.selectedTipoDocumento}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="eliminar tipo de documento" title="eliminar tipo de documento"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDelTipoDocumentoDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDelTipoDocumentoDlgForm">
                    <h:outputText value="Se procederá a eliminar el tipo de documento." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="pnlSearch, pnlNew, pnlEdit, dlgTipoDocumentoMsgs, pnlTipoDocumento" actionListener="#{documentoMB.eliminarTipoDocumento}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelTipoDocumentoDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDelTipoDocumentoDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            
            <rich:modalPanel id="versionDlg" resizeable="false" moveable="true" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="VERSIONAR DOCUMENTO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="versionhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="versionDlg" attachTo="versionhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="versionDlgForm" enctype="multipart/form-data">
                    <h:panelGrid columns="2" style="margin-bottom:10px">
                        <h:outputLabel value="Archivo:" styleClass="Etiqueta1"/>
                        <h:panelGrid columns="1">
                            <rich:fileUpload acceptedTypes="doc,docx,pdf,txt,xls,xlsx,ppt,pptx,jpg,jpge,png,gif" maxFilesQuantity="1" fileUploadListener="#{documentoMB.handleFileUpload}" noDuplicate="true" listHeight="58" listWidth="600"
                                             addControlLabel="Agregar" clearControlLabel="Limpiar" stopControlLabel="Parar" stopEntryControlLabel="Parar" cancelEntryControlLabel="Cancelar" 
                                             uploadControlLabel="Cargar" clearAllControlLabel="Limpiar" progressLabel="Cargando..." doneLabel="Finalizado" transferErrorLabel="Error al cargar archivo"
                                             sizeErrorLabel="El tamaño del archivo debe ser como máximo 1024KB" >
                                <f:facet name="label">
                                    <h:outputText value="{_KB}KB de {KB}KB cargados --- {mm}:{ss}" />
                                </f:facet>
                            </rich:fileUpload>
                            <h:outputText value="- Los tipos de archivo aceptados son doc, docx, xls, xlsx, ppt, pptx, pdf, txt, jpg, png, gif." style="font-size: 9px;color: red;"/>
                            <h:outputText value="- El archivo debe tener como máximo 1024KB." style="font-size: 9px;color: red;"/>
                        </h:panelGrid>
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton value="Grabar" onclick="#{rich:component('confirmVersionDlg')}.show();" style="font-size: 11px;height: 2em" />
                        <a4j:commandButton value="Cancelar" onclick="#{rich:component('versionDlg')}.hide();" style="font-size: 11px;height: 2em"/>
                    </h:panelGroup>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmVersionDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmación"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmVersionDlgForm">
                    <h:outputText value="Se actualizará el documento a la nueva versión seleccionada." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" actionListener="#{documentoMB.versionarDocumento}" oncomplete="#{documentoMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmVersionDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmVersionDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            
            <rich:modalPanel id="histDlg" resizeable="false" moveable="true" autosized="true" width="650">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="RESTAURAR DOCUMENTO HISTÓRICO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="histhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="histDlg" attachTo="histhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="histDlgForm">
                    <h:panelGrid columns="1" style="margin-bottom:10px;" width="100%">
                        <rich:dataTable id="tblHist" var="row" rowKeyVar="rowKey" value="#{documentoMB.listaHistorial}" rows="10" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tblHist" maxPages="10"/>
                            </f:facet>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="TIPO DOCUMENTO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NTipoDocumento}" >
                                    <f:converter converterId="converterTipoDocumento"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="DESCRIPCIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" />
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="VERSIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NVersion}" />
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="FECHA EMISIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.DFecEmision}" >
                                    <f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;width: 12%;">
                                <f:facet name="header">
                                    <h:outputText value="RESTAURAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink actionListener="#{documentoMB.restaurarDocumento}" oncomplete="#{documentoMB.action}" reRender="tbl">
                                    <f:param value="#{rowKey}" name="rowKey"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{documentoMB.selectedHistorial}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/document-split.png" height="16" width="16" alt="restaurar documento" title="restaurar documento"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="desdlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Desactivación del Documento"></h:outputText>
                </f:facet>
                <h:form id="mpdForm">
                    <h:outputText value="Se procederá a desactivar el documento."/><br>
                    <h:outputText value="Desea Continuar?"/><br>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tbl" actionListener="#{documentoMB.desactivarDocumento}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="desdlg" attachTo="dbtnSI" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="dbtnNO" value="NO" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="desdlg" attachTo="dbtnNO" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
