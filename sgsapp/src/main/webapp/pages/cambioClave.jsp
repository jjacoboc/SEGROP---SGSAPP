<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Cambio de Clave</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <h:panelGrid columns="2" width="100%" cellpadding="0" style="vertical-align: middle" styleClass="ui-widget ui-widget-header ui-corner-all">
                <h:graphicImage style="border: 0;" value="/pages/images/logoSegrop.png"/>
                <rich:spacer height="15"/>
            </h:panelGrid>
            <h:panelGrid columns="1" width="100%" cellpadding="0" cellspacing="0" >
                <rich:spacer height="150"/>
                <h:form id="login">
                    <center>
                        <rich:panel id="pnl" header="CAMBIO DE CLAVE" style="font-size: 13px;width: 350px;text-align: left;" >
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
                            <h:panelGrid columns="2" width="100%">
                                <h:panelGrid columns="2">
                                    <h:outputText value="Clave Actual: " styleClass="Etiqueta1"/>
                                    <h:inputSecret value="#{claveMB.claveActual}" maxlength="8" style="float: left;font-size: 11px" />
                                    
                                    <h:outputText value="Nueva Clave: " styleClass="Etiqueta1"/>
                                    <h:inputSecret value="#{claveMB.nuevaClave}" maxlength="8" style="float: left;font-size: 11px" />
                                    
                                    <h:outputText value="Confirmación: " styleClass="Etiqueta1"/>
                                    <h:inputSecret value="#{claveMB.confirmacion}" maxlength="8" style="float: left;font-size: 11px" />
                                </h:panelGrid>
                                <h:graphicImage style="border: 0;float: right;" value="/pages/images/keychain.png"/>
                            </h:panelGrid>
                            <h:panelGrid columns="1" width="100%" style="text-align: center;">
                                <h:commandButton value="Registrar" action="#{claveMB.cambiarClave}" style="font-size: 11px;height: 2em"/>
                            </h:panelGrid>
                        </rich:panel>
                    </center>
                </h:form>
                <rich:spacer height="150"/>
                <%@include file="../commons/footer.jsp" %>
            </h:panelGrid>
        </f:view>
    </body>
</html>
