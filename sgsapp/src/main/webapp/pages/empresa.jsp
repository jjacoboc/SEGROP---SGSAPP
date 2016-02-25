<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Gestión de Empresas</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="empresaForm">
                <rich:panel header="GESTION DE EMPRESAS" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                        <h:panelGrid columns="2" width="100%" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="RUC: " styleClass="Etiqueta1" />
                            <h:inputText value="#{empresaMB.searchRuc}" size="12" maxlength="11" style="float: left;font-size: 11px;text-transform: uppercase;" />
                            
                            <h:outputText value="Razón Social: " styleClass="Etiqueta1" />
                            <h:inputText value="#{empresaMB.searchRazonSocial}" size="100" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        </h:panelGrid>
                        <h:panelGrid columns="1">
                            <h:panelGroup>
                                <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{empresaMB.buscarEmpresa}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                                <a4j:commandButton id="btnNuevo" value="Nuevo" actionListener="#{empresaMB.toRegistrar}" reRender="dlgMsgs, descripcion" style="font-size: 11px;height: 2em">
                                    <rich:componentControl for="dlg" attachTo="btnNuevo" operation="show" event="onclick"/>
                                </a4j:commandButton>
                            </h:panelGroup>
                        </h:panelGrid>
                    </fieldset>

                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Empresas</legend>
                        <rich:messages id="tblMsgs" showSummary="true" showDetail="true"/>
                        <rich:dataTable id="tbl" var="row" value="#{empresaMB.listaEmpresa}" rows="10" rowKeyVar="rowkey" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" maxPages="15"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="RUC" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VRuc}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="RAZÓN SOCIAL" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VRazonSocial}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="DIRECCIÓN" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDireccion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="TELÉFONO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VTelefono}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="LOGO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VRutaLogo}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ACTIVO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NFlgActivo}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterSiNo"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column title="" style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="elink" actionListener="#{empresaMB.toEditar}" oncomplete="#{rich:component('dlgEdit')}.show()" reRender="gridEdit, editmedia">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{empresaMB.selectedEmpresa}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/pencil.png" alt="editar empresa" title="editar empresa"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="dlink" rendered="#{row.NFlgActivo==1}" >
                                    <f:setPropertyActionListener value="#{row}" target="#{empresaMB.selectedEmpresa}"/>
                                    <rich:componentControl for="cdlg" attachTo="dlink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="desactivar empresa" title="desactivar empresa"/>
                                </a4j:commandLink>
                                <a4j:commandLink id="alink" rendered="#{row.NFlgActivo==0}">
                                    <f:setPropertyActionListener value="#{row}" target="#{empresaMB.selectedEmpresa}"/>
                                    <rich:componentControl for="cadlg" attachTo="alink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/tick.png" alt="activar empresa" title="activar empresa"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="false" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="NUEVA EMPRESA"></h:outputText>
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
                        <h:outputLabel for="ruc" value="Ruc: " styleClass="Etiqueta1"/>
                        <h:inputText id="ruc" value="#{empresaMB.ruc}" size="12" maxlength="11" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel for="razonsocial" value="Razón Social: " styleClass="Etiqueta1"/>
                        <h:inputText id="razonsocial" value="#{empresaMB.razonSocial}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel for="direccion" value="Dirección: " styleClass="Etiqueta1"/>
                        <h:inputText id="direccion" value="#{empresaMB.direccion}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel for="telefono" value="Teléfono: " styleClass="Etiqueta1"/>
                        <h:inputText id="telefono" value="#{empresaMB.telefono}" size="15" maxlength="15" style="float: left;font-size: 11px;text-transform: uppercase;" />
                         
                        <h:outputLabel value="Logo:" styleClass="Etiqueta1"/>
                        <rich:fileUpload acceptedTypes="jpg, gif, png, bmp" maxFilesQuantity="1" noDuplicate="true" listHeight="63" listWidth="380" fileUploadListener="#{empresaMB.handleFileUpload}"
                                         addControlLabel="Agregar" clearControlLabel="Limpiar" stopControlLabel="Parar" stopEntryControlLabel="Parar" cancelEntryControlLabel="Cancelar"
                                         uploadControlLabel="Cargar" clearAllControlLabel="Limpiar" progressLabel="Cargando..." doneLabel="Finalizado" transferErrorLabel="Error al cargar archivo">
                            <f:facet name="label">
                                <h:outputText value="{_KB}KB de {KB}KB cargados --- {mm}:{ss}" />
                            </f:facet>
                            <a4j:support event="onuploadcomplete" reRender="media, dlgMsgs"/>
                        </rich:fileUpload>
                        
                        <rich:spacer/>
                        <h:panelGroup id="media">
                            <a4j:mediaOutput element="img" value="#{empresaMB.file}" mimeType="#{empresaMB.mime}" createContent="#{empresaMB.paint}" cacheable="false" />
                        </h:panelGroup>
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
                    <h:outputText value="Se procederá a registrar la empresa." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="dlgMsgs" actionListener="#{empresaMB.registrarEmpresa}" oncomplete="#{empresaMB.action}" style="font-size: 11px;height: 2em">
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
                        <h:outputText value="EDITAR EMPRESA"></h:outputText>
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
                    <h:panelGrid id="gridEdit" columns="2" style="margin-bottom:10px">
                        <h:outputLabel for="rucedit" value="Ruc: " styleClass="Etiqueta1"/>
                        <h:inputText id="rucedit" value="#{empresaMB.selectedEmpresa.VRuc}" size="12" maxlength="11" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel for="razonsocialedit" value="Razón Social: " styleClass="Etiqueta1"/>
                        <h:inputText id="razonsocialedit" value="#{empresaMB.selectedEmpresa.VRazonSocial}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel for="direccionedit" value="Dirección: " styleClass="Etiqueta1"/>
                        <h:inputText id="direccionedit" value="#{empresaMB.selectedEmpresa.VDireccion}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        
                        <h:outputLabel for="telefonoedit" value="Teléfono: " styleClass="Etiqueta1"/>
                        <h:inputText id="telefonoedit" value="#{empresaMB.selectedEmpresa.VTelefono}" size="15" maxlength="15" style="float: left;font-size: 11px;text-transform: uppercase;" />
                    
                        <h:outputLabel value="Logo:" styleClass="Etiqueta1"/>
                        <rich:fileUpload acceptedTypes="jpg, gif, png, bmp" maxFilesQuantity="1" noDuplicate="true" listHeight="63" listWidth="380" fileUploadListener="#{empresaMB.handleFileUpload}"
                                         addControlLabel="Agregar" clearControlLabel="Limpiar" stopControlLabel="Parar" stopEntryControlLabel="Parar" cancelEntryControlLabel="Cancelar"
                                         uploadControlLabel="Cargar" clearAllControlLabel="Limpiar" progressLabel="Cargando..." doneLabel="Finalizado" transferErrorLabel="Error al cargar archivo">
                            <f:facet name="label">
                                <h:outputText value="{_KB}KB de {KB}KB cargados --- {mm}:{ss}" />
                            </f:facet>
                            <a4j:support event="onuploadcomplete" reRender="editmedia, dlgEditMsgs"/>
                        </rich:fileUpload>
                        
                        <rich:spacer/>
                        <h:panelGroup id="editmedia">
                            <a4j:mediaOutput element="img" value="#{empresaMB.file}" mimeType="#{empresaMB.mime}" createContent="#{empresaMB.paint}" cacheable="false" />
                        </h:panelGroup>
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
                    <h:outputText value="Se procederá a editar la empresa." style="font-size: 11px;"/><br>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br>
                    <hr>
                    <a4j:commandButton id="btnSi_" value="SI" reRender="dlgEditMsgs, tbl" actionListener="#{empresaMB.editarEmpresa}" oncomplete="#{empresaMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnSi_" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo_" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnNo_" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="cdlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Desactivación de la Empresa"></h:outputText>
                </f:facet>
                <h:form id="mpdForm">
                    <h:outputText value="Se procederá a desactivar la empresa."/><br>
                    <h:outputText value="Desea Continuar?"/><br>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tbl" actionListener="#{empresaMB.desactivar}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cdlg" attachTo="dbtnSI" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="dbtnNO" value="NO" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cdlg" attachTo="dbtnNO" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="cadlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Activación de la Empresa"></h:outputText>
                </f:facet>
                <h:form id="mpaForm">
                    <h:outputText value="Se procederá a activar la empresa."/><br>
                    <h:outputText value="Desea Continuar?"/><br>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tbl" actionListener="#{empresaMB.activar}" status="commonstatus" style="font-size: 11px;height: 2em">
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
