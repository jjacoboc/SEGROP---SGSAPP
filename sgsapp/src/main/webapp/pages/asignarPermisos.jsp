<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Asignación de Permisos al Perfil</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="formId">
                <rich:panel header="ASIGNAR PERMISOS AL PERFIL" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid columns="2" width="100%">
                            <h:outputText value="Empresa: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{permisoMB.searchEmpresa}" style="float: left;font-size: 11px" styleClass="ui-inputfield ui-widget ui-state-default ui-corner-all"> 
                                <f:selectItems value="#{listasSessionMB.listaEmpresaActiva}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Nombre: " styleClass="Etiqueta1" />
                            <h:inputText value="#{permisoMB.searchNombre}" size="100" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        </h:panelGrid>
                            
                        <h:panelGrid columns="1">
                            <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{permisoMB.buscarPerfil}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                        </h:panelGrid>
                    </fieldset>
                    
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Perfiles</legend>
                        <rich:dataTable id="tbl" var="row" rowKeyVar="rowkey" value="#{permisoMB.listaPerfil}" rows="10" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" maxPages="15"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="EMPRESA" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NCodEmpresa}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterEmpresa"/>
                                </h:outputText>
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
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ACTIVO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NFlgActivo}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterSiNo"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;width: 5%">
                                <f:facet name="header">
                                    <h:outputText value="ASIGNAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink actionListener="#{permisoMB.handleSelectedPerfil}" oncomplete="#{rich:component('dlg')}.show()" reRender="dlg, source, target">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{permisoMB.selectedPerfil}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/contact_new.png" alt="asignar permisos" title="asignar permisos"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="true" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGrid columns="1" cellpadding="0" cellspacing="0">
                        <h:outputText value="ASIGNACIÓN DE PERMISOS" style="font-size: 11px;font-weight: bold;"></h:outputText>
                        <h:outputText value="EMPRESA: #{permisoMB.selectedPerfil.NCodEmpresa}" style="font-size: 11px;font-weight: bold;">
                            <f:converter converterId="converterEmpresa"/>
                        </h:outputText>
                        <h:outputText value="PERFIL: #{permisoMB.selectedPerfil.VNombre}" style="font-size: 11px;font-weight: bold;"></h:outputText>
                    </h:panelGrid>
                </f:facet>
                <h:form>
                <center>
                    <h:panelGrid columns="2" columnClasses="ap-rola-list-column, ap-rols-list-column" width="100%">
                        <rich:dataTable id="source" var="s" value="#{permisoMB.source}" rows="10" style="font-size: 11px;"
                            onRowMouseOver="this.style.backgroundColor='#F2F2F2';this.style.cursor='hand';" width="100%" rowKeyVar="rowKey"
                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}';this.style.cursor='default';">
                            <f:facet name="header">
                                <h:panelGrid columns="1" cellspacing="0" cellpadding="0" width="100%">
                                    <h:outputText value="PERMISOS NO ASIGNADOS" />
                                    <rich:spacer height="10"/>
                                    <rich:datascroller for="source" maxPages="15"/>
                                </h:panelGrid>
                            </f:facet>
                            <rich:column>
                                <h:outputText value="#{s.VNombre}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <a4j:support reRender="source,target" actionListener="#{permisoMB.onRowSourceSelected}" event="onRowClick">
                                <f:param value="#{rowKey}" name="rowKey"/>
                                <f:setPropertyActionListener value="#{s}" target="#{permisoMB.selectedObjeto}"/>
                            </a4j:support>
                        </rich:dataTable>
                        <rich:dataTable id="target" var="t" value="#{permisoMB.target}" rows="10" style="font-size: 11px;"
                            onRowMouseOver="this.style.backgroundColor='#F2F2F2';this.style.cursor='hand';" width="100%" rowKeyVar="rowKey"
                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}';this.style.cursor='default';">
                            <f:facet name="header">
                                <h:panelGrid columns="1" cellspacing="0" cellpadding="0" width="100%">
                                    <h:outputText value="PERMISOS ASIGNADOS" />
                                    <rich:spacer height="10"/>
                                    <rich:datascroller for="target" maxPages="15"/>
                                </h:panelGrid>
                            </f:facet>
                            <rich:column>
                                <h:outputText value="#{t.VNombre}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <a4j:support reRender="source,target" actionListener="#{permisoMB.onRowTargetSelected}" event="onRowClick">
                                <f:param value="#{rowKey}" name="rowKey"/>
                                <f:setPropertyActionListener value="#{t}" target="#{permisoMB.selectedObjeto}"/>
                            </a4j:support>
                        </rich:dataTable>
                    </h:panelGrid>
                </center>
                <rich:spacer />
                <a4j:commandButton value="Asignar Perfiles" actionListener="#{permisoMB.registrarPermisos}" oncomplete="#{rich:component('dlg')}.hide()" style="font-size: 11px;height: 2em"/>
                <a4j:commandButton id="btnCancelar" value="Cancelar" style="font-size: 11px;height: 2em">
                    <rich:componentControl for="dlg" attachTo="btnCancelar" operation="hide" event="onclick"/>
                </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
