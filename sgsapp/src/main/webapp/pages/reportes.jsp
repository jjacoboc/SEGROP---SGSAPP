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
        <title>SGSWEB - Reporte de Novedades</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="reporteForm" >
                <rich:panel id="pnlReporte" header="REPORTES" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Filtros del Reporte</legend>
                        <h:panelGrid id="pnlFilter" columns="2">
                            <h:outputText value="Rango Fechas: " styleClass="Etiqueta1" />
                            <h:panelGrid columns="5" cellpadding="0" cellspacing="0">
                                <rich:calendar value="#{reporteMB.fechaInicio}" popup="true" datePattern="dd/MM/yyyy"
                                jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                                <rich:spacer width="10"/>
                                <h:outputText value="al" style="font-weight: bold;" styleClass="Etiqueta1" />
                                <rich:spacer width="10"/>
                                <rich:calendar value="#{reporteMB.fechaFin}" popup="true" datePattern="dd/MM/yyyy"
                                jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                            </h:panelGrid>
                        </h:panelGrid>
                    </fieldset>
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Selección del Reporte</legend>
                        <h:panelGrid id="pnlSearch" columns="1">
                            <h:selectOneRadio id="sorReport" value="#{reporteMB.report}" layout="pageDirection">
                                <f:selectItem itemValue="1" itemLabel="Reporte de Novedades por Local y Área"/>
                                <f:selectItem itemValue="2" itemLabel="Reporte de Novedades por Local y Lugar"/>
                                <f:selectItem itemValue="3" itemLabel="Reporte de Seguimiento de Novedades"/>
                                <f:selectItem itemValue="4" itemLabel="Reporte de Evaluación de Novedades"/>
                                <f:selectItem itemValue="5" itemLabel="Reporte de Inspecciones Presenciales por Local y Área"/>
                                <f:selectItem itemValue="6" itemLabel="Reporte de Inspecciones Presenciales por Local y Lugar"/>
                                <f:selectItem itemValue="7" itemLabel="Reporte de Seguimiento de Inspecciones Presenciales"/>
                                <f:selectItem itemValue="8" itemLabel="Reporte de Evaluación de Inspecciones Presenciales"/>
                                <f:selectItem itemValue="9" itemLabel="Reporte de Inspecciones Telefónicas"/>
                                <f:selectItem itemValue="10" itemLabel="Reporte de Inspecciones Telefónicas mas Preguntas"/>
                                <f:selectItem itemValue="11" itemLabel="Reporte de Inspecciones Telefónicas mas Preguntas y Respuestas"/>
                                <f:selectItem itemValue="12" itemLabel="Reporte de Documentos"/>
                                <f:selectItem itemValue="13" itemLabel="Reporte de Capacitaciones"/>
                                <f:selectItem itemValue="14" itemLabel="Reporte de Capacitaciones y Participantes"/>
                            </h:selectOneRadio>
                        </h:panelGrid>
                    </fieldset>
                    
                    <h:panelGrid columns="2">
                        <h:commandButton id="btnDoc" value="Generar Doc" action="#{reporteMB.handlerButtonDoc}" style="font-size: 11px;height: 2em"/>
                        <h:commandButton id="btnPdf" value="Generar Pdf" action="#{reporteMB.handlerButtonPdf}" style="font-size: 11px;height: 2em"/>
                    </h:panelGrid>
                    
                    
                </rich:panel>
            </h:form>
            <rich:spacer/>
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
