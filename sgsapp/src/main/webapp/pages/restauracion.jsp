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
        <title>SGSWEB - Restauración de Documentos</title>
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
                <rich:panel id="pnlDocumento" header="RESTAURACIÓN DE DOCUMENTOS" style="font-size: 13px;">
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
                        </h:panelGrid>
                    </fieldset>
                    
                    <h:panelGrid columns="1">
                        <h:panelGroup>
                            <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{documentoMB.buscarDocumentoDesactivado}" reRender="tbl" style="font-size: 11px;height: 2em"/>
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
                            <rich:column style="text-align: center;width: 8%;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <h:commandLink id="dlink" action="#{documentoMB.handleFileDownload2}" target="_blank">
                                    <f:param value="#{rowKey}" name="rowKey"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{documentoMB.selectedDocument}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/download.png" height="16" width="16" alt="descargar documento" title="descargar documento"/>
                                </h:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="actlink">
                                    <f:setPropertyActionListener value="#{row}" target="#{documentoMB.selectedDocument}"/>
                                    <rich:componentControl for="actdlg" attachTo="actlink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/page_refresh.png" alt="restaurar documento" title="restaurar documento"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="actdlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Restauración del Documento"></h:outputText>
                </f:facet>
                <h:form id="mpdForm">
                    <h:outputText value="Se procederá a restaurar el documento."/><br>
                    <h:outputText value="Desea Continuar?"/><br>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tbl" actionListener="#{documentoMB.activarDocumento}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="actdlg" attachTo="dbtnSI" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="dbtnNO" value="NO" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="actdlg" attachTo="dbtnNO" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
