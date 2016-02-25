<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Gestión de Usuarios</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form>
                <rich:panel header="GESTION DE USUARIOS" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid columns="2" width="100%">
                            <h:outputText value="Empresa: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{usuarioMB.searchEmpresa}" style="float: left;font-size: 11px" styleClass="ui-inputfield ui-widget ui-state-default ui-corner-all"> 
                                <f:selectItems value="#{listasSessionMB.listaEmpresaActiva}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Tipo de Documento: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{usuarioMB.searchNumTipoDocumento}" style="float: left;font-size: 11px" styleClass="ui-inputfield ui-widget ui-state-default ui-corner-all">
                                <f:selectItems value="#{listasSessionMB.listaTipoNumDocumento}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Número de Documento: " styleClass="Etiqueta1" />
                            <h:inputText value="#{usuarioMB.searchNumDocumento}" size="15" maxlength="15" style="float: left;font-size: 11px;text-transform: uppercase;" />

                            <h:outputText value="Nombres: " styleClass="Etiqueta1" />
                            <h:inputText value="#{usuarioMB.searchNombre}" size="100" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />

                            <h:outputText value="Apellidos: " styleClass="Etiqueta1" />
                            <h:inputText value="#{usuarioMB.searchApellido}" size="100" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        </h:panelGrid>
                        
                        <h:panelGrid columns="1">
                            <h:panelGroup>
                                <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{usuarioMB.buscarUsuario}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                                <a4j:commandButton id="btnNuevo" value="Nuevo" actionListener="#{usuarioMB.toRegistrar}" style="font-size: 11px;height: 2em">
                                    <rich:componentControl for="dlg" attachTo="btnNuevo" operation="show" event="onclick"/>
                                </a4j:commandButton>
                            </h:panelGroup>
                        </h:panelGrid>
                    </fieldset>
                    
                    
                    
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Usuarios</legend>
                        <rich:messages id="tblMsgs" showSummary="true" showDetail="true"/>
                        <rich:dataTable id="tbl" var="row" value="#{usuarioMB.listaUsuario}" rows="10" rowKeyVar="rowKey" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" maxPages="15"/>
                            </f:facet>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="NÚMERO DOCUMENTO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VNumDocumento}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
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
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="USUARIO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VUsuario}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
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
                                    <h:outputText value="ACTIVO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NFlgActivo}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterSiNo"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column style="text-align: center;width: 10px;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="elink" actionListener="#{usuarioMB.toEditar}" reRender="editPanel">
                                    <f:param value="#{rowKey}" name="rowKey"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{usuarioMB.selectedUsuario}"/>
                                    <rich:componentControl for="dlgEdit" attachTo="elink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/pencil.png" alt="editar usuario" title="editar usuario"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="dlink" rendered="#{row.NFlgActivo==1}" >
                                    <f:setPropertyActionListener value="#{row}" target="#{usuarioMB.selectedItem}"/>
                                    <rich:componentControl for="cdlg" attachTo="dlink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="desactivar usuario" title="desactivar usuario"/>
                                </a4j:commandLink>
                                <a4j:commandLink id="alink" rendered="#{row.NFlgActivo==0}">
                                    <f:setPropertyActionListener value="#{row}" target="#{usuarioMB.selectedItem}"/>
                                    <rich:componentControl for="cadlg" attachTo="alink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/tick.png" alt="activar usuario" title="activar usuario"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="false" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="NUEVO USUARIO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="newhidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlg" attachTo="newhidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
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
                    <h:panelGrid columns="2" style="margin-bottom:10px">
                        <h:outputText value="Empresa: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{usuarioMB.empresa}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaEmpresaActiva}"/>
                        </h:selectOneMenu>
                        
                        <h:outputText value="Tipo de Documento: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{usuarioMB.tipoNumDocumento}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaTipoNumDocumento}"/>
                        </h:selectOneMenu>
                        
                        <h:outputLabel value="Número de Documento: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.numDocumento}" size="15" maxlength="15" onkeypress="return valida(this,'afn');" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Nombres: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.nombre}" size="50" maxlength="100" onkeypress="return valida(this,'afn');" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Apellidos: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.apellido}" size="50" maxlength="100" onkeypress="return valida(this,'afn');" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Correo: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.correo}" size="50" maxlength="100" onkeypress="return valida(this,'email');" style="float: left;font-size: 11px;" />
                        
                        <h:outputLabel value="Telefono: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.telefono}" size="15" maxlength="15" onkeypress="return valida(this,'tlf');" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Usuario: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.usuario}" size="45" maxlength="45" onkeypress="return valida(this,'afn');" style="float: left;font-size: 11px;" />
                        
                        <h:outputLabel value="Confirmación de Clave? " styleClass="Etiqueta1"/>
                        <h:selectOneRadio value="#{usuarioMB.comfirmacion}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneRadio>
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton id="btnGrabar" value="Grabar" onclick="confirmDlg.show();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmDlg" attachTo="btnGrabar" operation="show" event="onclick"/>
                        </a4j:commandButton>
                        <a4j:commandButton id="btnCancelar" value="Cancelar" onclick="dlg.hide();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="dlg" attachTo="btnCancelar" operation="hide" event="onclick"/>
                        </a4j:commandButton>
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
                    <h:outputText value="Se procederá a registrar al usuario." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="dlgMsgs" actionListener="#{usuarioMB.registrarUsuario}" oncomplete="#{usuarioMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="dlgEdit" resizeable="false" moveable="false" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="EDITAR USUARIO"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage id="edithidelink" style="border: 0;cursor: pointer;" url="/pages/images/fileclose.png"/>
                        <rich:componentControl for="dlgEdit" attachTo="edithidelink" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgEditForm">
                    <rich:messages id="dlgEditMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
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
                    <h:panelGrid id="editPanel" columns="2" style="margin-bottom:10px">
                        <h:outputText value="Empresa: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{usuarioMB.selectedUsuario.NCodEmpresa}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaEmpresaActiva}"/>
                        </h:selectOneMenu>
                        
                        <h:outputText value="Tipo de Documento: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{usuarioMB.selectedUsuario.NTipNumDocumento}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaTipoNumDocumento}"/>
                        </h:selectOneMenu>
                        
                        <h:outputLabel value="Número de Documento: " styleClass="Etiqueta1" />
                        <h:inputText value="#{usuarioMB.selectedUsuario.VNumDocumento}" size="15" maxlength="15" onkeypress="return valida(this,'afn');" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Nombres: " styleClass="Etiqueta1" />
                        <h:inputText value="#{usuarioMB.selectedUsuario.VNombres}" size="50" maxlength="100" onkeypress="return valida(this,'afn');" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Apellidos: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.selectedUsuario.VApellidos}" size="50" maxlength="100" onkeypress="return valida(this,'afn');" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Correo: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.selectedUsuario.VCorreo}" size="50" maxlength="100" onkeypress="return valida(this,'email');" style="float: left;font-size: 11px;" />
                        
                        <h:outputLabel value="Telefono: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.selectedUsuario.VMovil}" size="15" maxlength="15" onkeypress="return valida(this,'tlf');" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel value="Usuario: " styleClass="Etiqueta1"/>
                        <h:inputText value="#{usuarioMB.selectedUsuario.VUsuario}" size="45" maxlength="45" onkeypress="return valida(this,'afn');" style="float: left;font-size: 11px;" />
                        
                        <h:outputText value="Activo: " styleClass="Etiqueta1" />
                        <h:selectOneMenu value="#{usuarioMB.selectedUsuario.NFlgActivo}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneMenu>
                        
                        <h:outputLabel value="Confirmación de Clave? " styleClass="Etiqueta1"/>
                        <h:selectOneRadio value="#{usuarioMB.selectedUsuario.NFlgClave}" style="float: left;font-size: 11px" >
                            <f:selectItems value="#{listasSessionMB.listaSiNo}"/>
                        </h:selectOneRadio>
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton id="btnGrabar_" value="Grabar" onclick="confirmEditDlg.show();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmEditDlg" attachTo="btnGrabar_" operation="show" event="onclick"/>
                        </a4j:commandButton>
                        <a4j:commandButton id="btnCancelar_" value="Cancelar" onclick="dlgEdit.hide();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="dlgEdit" attachTo="btnCancelar_" operation="hide" event="onclick"/>
                        </a4j:commandButton>
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
                    <h:outputText value="Se procederá a editar el usuario." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi_" value="SI" reRender="dlgEditMsgs, tbl" actionListener="#{usuarioMB.editarUsuario}" oncomplete="#{usuarioMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnSi_" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo_" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnNo_" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="cdlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Desactivación del Usuario"></h:outputText>
                </f:facet>
                <h:form id="mpdForm">
                    <h:outputText value="Se procederá a desactivar el usuario."/><br>
                    <h:outputText value="Desea Continuar?"/><br>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tbl" actionListener="#{usuarioMB.desactivar}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cdlg" attachTo="dbtnSI" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="dbtnNO" value="NO" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cdlg" attachTo="dbtnNO" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="cadlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Activación del Usuario"></h:outputText>
                </f:facet>
                <h:form id="mpaForm">
                    <h:outputText value="Se procederá a activar el usuario."/><br>
                    <h:outputText value="Desea Continuar?"/><br>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tbl" actionListener="#{usuarioMB.activar}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cadlg" attachTo="dbtnSI" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="dbtnNO" value="NO" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cadlg" attachTo="dbtnNO" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
