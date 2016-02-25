<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Asignación de Perfiles al Usuario</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="formId">
                <rich:panel header="ASIGNAR PERFILES AL USUARIO" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid columns="2" width="100%">
                            <h:outputText value="Empresa: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{usuarioPerfilMB.searchEmpresa}" style="float: left;font-size: 11px" styleClass="ui-inputfield ui-widget ui-state-default ui-corner-all"> 
                                <f:selectItems value="#{listasSessionMB.listaEmpresaActiva}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Tipo de Documento: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{usuarioPerfilMB.searchNumTipoDocumento}" style="float: left;font-size: 11px" styleClass="ui-inputfield ui-widget ui-state-default ui-corner-all">
                                <f:selectItems value="#{listasSessionMB.listaTipoNumDocumento}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Número de Documento: " styleClass="Etiqueta1" />
                            <h:inputText value="#{usuarioPerfilMB.searchNumDocumento}" size="15" maxlength="15" style="float: left;font-size: 11px;text-transform: uppercase;" />

                            <h:outputText value="Nombres: " styleClass="Etiqueta1" />
                            <h:inputText value="#{usuarioPerfilMB.searchNombre}" size="100" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />

                            <h:outputText value="Apellidos: " styleClass="Etiqueta1" />
                            <h:inputText value="#{usuarioPerfilMB.searchApellido}" size="100" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        </h:panelGrid>
                            
                        <h:panelGrid columns="1">
                            <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{usuarioPerfilMB.buscarUsuario}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                        </h:panelGrid>
                    </fieldset>
                    
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Usuarios</legend>
                        <rich:dataTable id="tbl" var="row" rowKeyVar="rowkey" value="#{usuarioPerfilMB.listaUsuario}" rows="10" style="font-size: 11px;" width="100%">
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
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="NÚMERO DOCUMENTO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VNumDocumento}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="NOMBRE COMPLETO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VNombres} #{row.VApellidos}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="USUARIO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VUsuario}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;width: 5%">
                                <f:facet name="header">
                                    <h:outputText value="ASIGNAR" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink actionListener="#{usuarioPerfilMB.handleSelectedUsuario}" oncomplete="#{rich:component('dlg')}.show()" reRender="dlg, source, target">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{usuarioPerfilMB.selectedUsuario}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/contact_new.png" alt="asignar perfil" title="asignar perfil"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="true" autosized="true" minWidth="500" >
                <f:facet name="header">
                    <h:panelGrid columns="1" cellpadding="0" cellspacing="0">
                        <h:outputText value="ASIGNACIÓN DE PERFILES" style="font-size: 11px;font-weight: bold;"></h:outputText>
                        <h:outputText value="EMPRESA: #{usuarioPerfilMB.selectedUsuario.NCodEmpresa}" style="font-size: 11px;font-weight: bold;">
                            <f:converter converterId="converterEmpresa"/>
                        </h:outputText>
                        <h:outputText value="USUARIO: #{usuarioPerfilMB.selectedUsuario.VUsuario}" style="font-size: 11px;font-weight: bold;"></h:outputText>
                    </h:panelGrid>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="newhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlg" attachTo="newhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form>
                <center>
                    <h:panelGrid columns="2" columnClasses="ap-rola-list-column, ap-rols-list-column" width="100%">
                        <rich:dataTable id="source" var="s" value="#{usuarioPerfilMB.source}" rows="10" style="font-size: 11px;"
                            onRowMouseOver="this.style.backgroundColor='#F2F2F2';this.style.cursor='hand';" width="100%"
                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}';this.style.cursor='default';">
                            <f:facet name="header">
                                <h:panelGrid columns="1" cellspacing="0" cellpadding="0" width="100%">
                                    <h:outputText value="PERFILES NO ASIGNADOS" />
                                    <rich:spacer height="10"/>
                                    <rich:datascroller for="source" maxPages="15"/>
                                </h:panelGrid>
                            </f:facet>
                            <rich:column>
                                <h:outputText value="#{s.VNombre}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <a4j:support reRender="source,target" actionListener="#{usuarioPerfilMB.onRowSourceSelected}" event="onRowClick">
                                <f:setPropertyActionListener value="#{s}" target="#{usuarioPerfilMB.selectedPerfil}"/>
                            </a4j:support>
                        </rich:dataTable>
                        <rich:dataTable id="target" var="t" value="#{usuarioPerfilMB.target}" rows="10" style="font-size: 11px;"
                            onRowMouseOver="this.style.backgroundColor='#F2F2F2';this.style.cursor='hand';" width="100%"
                            onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}';this.style.cursor='default';">
                            <f:facet name="header">
                                <h:panelGrid columns="1" cellspacing="0" cellpadding="0" width="100%">
                                    <h:outputText value="PERFILES ASIGNADOS" />
                                    <rich:spacer height="10"/>
                                    <rich:datascroller for="source" maxPages="15"/>
                                </h:panelGrid>
                            </f:facet>
                            <rich:column>
                                <h:outputText value="#{t.VNombre}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <a4j:support reRender="source,target" actionListener="#{usuarioPerfilMB.onRowTargetSelected}" event="onRowClick">
                                <f:setPropertyActionListener value="#{t}" target="#{usuarioPerfilMB.selectedPerfil}"/>
                            </a4j:support>
                        </rich:dataTable>
                    </h:panelGrid>
                </center>
                <rich:spacer />
                <%--
                <a4j:commandButton value="Asignar Perfiles" actionListener="#{usuarioPerfilMB.registrarAsignacion}" oncomplete="#{rich:component('dlg')}.hide()" style="font-size: 11px;height: 2em"/>
                <a4j:commandButton id="btnCancelar" value="Cancelar" style="font-size: 11px;height: 2em">
                    <rich:componentControl for="dlg" attachTo="btnCancelar" operation="hide" event="onclick"/>
                </a4j:commandButton>
                --%>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
